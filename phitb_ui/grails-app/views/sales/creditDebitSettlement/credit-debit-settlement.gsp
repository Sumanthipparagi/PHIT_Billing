<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Credit-Debit Settlement</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">

    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>

    <style>
    input.chk-btn {
        display: none;
    }

    input.chk-btn + label {
        border: 1px solid grey;
        background: ghoswhite;
        padding: 5px 8px;
        cursor: pointer;
        border-radius: 5px;
    }

    input.chk-btn:not(:checked) + label:hover {
        box-shadow: 0px 1px 3px;
    }

    input.chk-btn + label:active,
    input.chk-btn:checked + label {
        box-shadow: 0px 0px 3px inset;
        background: #eee;
    }

    .table td {
        padding: 0.4rem;
    }

    .btn-sm {
        font-size: 10px;
        border-radius: 0.1875rem;
        padding: 4px 9px;
    }

    .tableFixHead {
        overflow: auto;
        height: 400px;
    }

    .tableFixHead thead th {
        position: sticky;
        top: 0;
        z-index: 1;
    }

    /* Just common table stuff. Really. */
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px 16px;
    }

    th {
        background: #313740;
        color: white;
    }


    /* Chrome, Safari, Edge, Opera  - hide input arrow keys*/
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }

    .addColor{
        background-color: darkseagreen;
    }

    .subColor{
        background-color: red;
    }

    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="PharmIt"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Credit Debit Settlement</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/recipt">Accounts</a></li>
                        <li class="breadcrumb-item active">Credit Debit Settlement</li>
                    </ul>
                </div>
                %{--
                                <div class="col-lg-7 col-md-7 col-sm-12">
                                    <div class="input-group m-b-0">
                                        <input type="text" class="form-control" placeholder="Search...">
                                        <span class="input-group-addon">
                                            <i class="zmdi zmdi-search"></i>
                                        </span>
                                    </div>
                                </div>--}%
            </div>
        </div>


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">

                    <div class="body">

                        <div class="row clearfix">

                            <div class="col-lg-6">
                                <label for="receivedFrom">
                                    Customer
                                </label><br>
                                <select class=" show-tick receivedFrom" name="receivedFrom"
                                        id="receivedFrom" onchange="getAddress(this.value)" required
                                        style="width: 460px;">
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${entity}">
                                        <g:if test="${e.id != session.getAttribute("entityId")}">
                                            <option value="${e.id}"
                                                    data-type="${e.entityType.id}">${e.entityName}  (${e.entityType.name})</option>
                                        </g:if>
                                    </g:each>
                                </select>

                                <div id="caddress" class="mt-2"></div>
                            </div>

                            <input type="hidden" id="date" class="date" name="date"
                                   placeholder="Receipt Date" readonly
                                   required/>

                            <div class="container mt-5">

                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                        max-height:300px;">
                                            <table class="table table-bordered" id="table1">
                                                <thead>
                                                <tr>
                                                    <th>D.Type</th>
                                                    <th>Doc.Id</th>
                                                    <th>Doc.Date</th>
                                                    <th>Amt.</th>
                                                    <th>Pend.Amt</th>
                                                    <th></th>
                                                    <th style="display: none;">BillId</th>
                                                </tr>
                                                </thead>
                                                <tbody id="debitDetails">
                                                <tr><td colspan='9'><div style='text-align: center;'><h2
                                                        style="font-size: 1.4em;">Please select customer</h2>
                                                </div>
                                                </td></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                        max-height:300px;">
                                            <table class="table table-bordered" id="table2">
                                                <thead>
                                                <tr>
                                                    <th>D.Type</th>
                                                    <th>Doc.Id</th>
                                                    <th>Doc.Date</th>
                                                    <th>Amt.</th>
                                                    <th>Pend.Amt</th>
                                                    <th></th>
                                                    <th style="display: none;">BillId</th>
                                                </tr>
                                                </thead>
                                                <tbody id="creditDetails">
                                                <tr>
                                                    <td colspan='9'><div
                                                        style='text-align: center;'><h2 style="font-size: 1.4em;">Please select customer</h2>
                                                </div>
                                                </td></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input type="hidden" id="bills" name="bills" value="">
                            <input type="hidden" name="status" value="1">
                            <input type="hidden" name="syncStatus" value="1">
                            <input type="hidden" name="createdUser" value="1">
                            <input type="hidden" name="modifiedUser" value="1">

                            <div class="col-lg-12">
                                <div class="" style="float: right;">
                                    <input name="id" id="id" class="id" type="hidden">
                                    <input name="type" class="type" value="add" type="hidden">
                                    <button class="btn btn-default btn-round waves-effect" id="submitData"><font
                                            style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">SUBMIT</font></font></button>

                                    %{--                                        <button type="reset" class="btn btn-danger btn-simple btn-round waves-effect"--}%
                                    %{--                                                data-dismiss="modal"><font style="vertical-align: inherit;"><font--}%
                                    %{--                                                style="vertical-align: inherit;">RESET</font></font></button>--}%
                                </div>
                            </div>
                        </div>
                        %{--                        </form>--}%
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>
<script src="https://cdn.jsdelivr.net/npm/table-to-json@1.0.0/lib/jquery.tabletojson.min.js"
        integrity="sha256-H8xrCe0tZFi/C2CgxkmiGksqVaxhW0PFcUKZJZo1yNU=" crossorigin="anonymous"></script>

<script>
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    var today = year + "-" + month + "-" + day;
    document.getElementById("date").value = moment(today).format('DD/MM/YYYY');

    $('.receivedFrom').select2()
    $('.depositTo').select2()
    var $demoMaskedInput = $('.demo-masked-input');
    $demoMaskedInput.find('.credit-card').inputmask('9999 9999 9999 9999', {placeholder: '____ ____ ____ ____'});


    $('.datetimepicker').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        time: false,
        weekStart: 1
    });


    function calculateNoOfDays(date) {
        const oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
        const firstDate = new Date(date);
        const secondDate = new Date();
        const diffDays = Math.round(Math.abs((firstDate - secondDate) / oneDay));
        return diffDays
    }

    let invIdArray = [];
    let gtnIdArray = [];
    let crntIdArray = [];

    function getAddress(id) {
        if (id) {
            $.ajax({
                type: 'GET',
                url: '/getbyentity/' + id,
                dataType: 'json',
                success: function (data) {
                    invIdArray = [];
                    gtnIdArray = [];
                    crntIdArray = [];
                    getAllSaleBillDetails(id)
                    var trHTML = '';
                    trHTML += '<p><b>' + data.entityName + '</b><br>' + data.addressLine1 + '' + data.addressLine2 + '</p>';
                    $('#caddress').html(trHTML);
                },
                error: function () {
                    swal("Error!", "Something went wrong", "error");
                }
            });
        } else {
            getAllSaleBillDetails(id)
            $('#caddress').html("");
        }
    }


    function getAllSaleBillDetails(id) {
        if (id) {
            $.ajax({
                type: 'GET',
                url: '/recipts/getallbilldetails?id=' + id,
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    var invHTML = '';
                    var crntHTML = '';
                    invHTML += '';
                    crntHTML += '';
                    var invoice = "INVS";
                    var creditNote = "CRNT";
                    var gtn = "GTN";
                    var inv = data[0].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var invAdjAmt = data[0].map(data => data.adjAmount).reduce((acc, adjAmt) => acc + adjAmt, 0);
                    var crnt = data[1].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var crntAdjAmt = data[1].map(data => data.adjAmount).reduce((acc, adjAmount) => acc + adjAmount, 0);
                    var total_bal_s = invAdjAmt - crntAdjAmt;
                    $('.total_bal_s').text(parseFloat(total_bal_s).toFixed(2));
                    $('.tba').val(total_bal_s.toFixed(2));
                    $('.amountPaid').val(total_bal_s.toFixed(2));
                    var invoiceData = [];
                    var crntData = [];
                    $.each(data[0], function (key, value) {
                        var balance = value.balance.toFixed(2);
                        if (Number(balance) !== 0 && value.billStatus !== 'DRAFT' && value.billStatus !== 'CANCELLED') {
                            invHTML += ' <tr id="' + "IN" + value.id + '">\n' +
                                '                                        <td>' + invoice + '</td>\n' +
                                '                                        <td>' + value.invoiceNumber + '</td>\n' +
                                '                                        <td>' + moment(value.dateCreated).format('DD-MM-YYYY') + '</td>\n' +
                                '                                        <td id="' + "invAdjAmt" + value.id + '">' + value.totalAmount.toFixed(2) + '</td>\n' +
                                '                                        <td id="' + "invBal" + value.id + '" >' + value.balance.toFixed(2) + '</td>\n' +
                                '                                        <td><input type="checkbox" id="' + "invdebitCheck" + value.id + '"  value="true"></td>\n' +
                                '                                        <td style="display: none;">' + value.id + '</td>\n' +
                                '                                        </tr>';

                            invoiceData.push(value.id);
                            invIdArray.push(value.id);
                            console.log(invIdArray)
                        }
                    });
                    $.each(data[1], function (key, value) {
                        var date = new Date(value.entryDate);
                        if (value.balance !== 0 && value.returnStatus !== 'CANCELLED') {
                            crntHTML += ' <tr id="' + "CN" + value.id + '">\n' +
                                '                                        <td>' + creditNote + '</td>\n' +
                                '                                        <td>' + value.invoiceNumber + '</td>\n' +
                                '                                        <td>' + moment(value.dateCreated).format('DD-MM-YYYY') + '</td>\n' +
                                '                                        <td id="' + "crntAdjAmt" + value.id + '">' + value.totalAmount.toFixed(2) + '</td>\n' +
                                '                                        <td id="' + "crntBal" + value.id + '" >' + "-" + value.balance.toFixed(2) +
                                '</td>\n' +
                                '                                        <td><input type="checkbox" id="' + "creditCheck" + value.id + '"  value="true"></td>\n' +
                                '                                        <td style="display: none;">' + value.id + '</td>\n' +
                                '                                        </tr>';
                            crntData.push(value.id);
                            crntIdArray.push(value.id);
                            console.log(crntIdArray)

                        }
                    });
                    $('#debitDetails').html(invHTML);
                    $('#creditDetails').html(crntHTML);
                    if (invoiceData.length === 0) {
                        $('#debitDetails').html("<tr><td colspan='9'><div style='text-align: center;'><p style='font-size: 1.4 em;'>No Data Found</p></div></td></tr>");
                    }
                    if(crntData.length === 0){
                        $('#creditDetails').html("<tr><td colspan='9'><div style='text-align: center;'><p style='font-size: 1.4 em;'>No Data Found</p></div></td></tr>");
                    }
                },
                error: function () {
                    swal("Error!", "Something went wrong", "error");
                }
            });
        } else {
            $('#billDetails').html("<tr><td colspan='9'><div style='text-align: center;'><h2>Please select customer</h2></div></td></tr>");
        }
    }


    $(document).ready(function () {
        $(window).keydown(function (event) {
            if (event.keyCode === 13) {
                event.preventDefault();
                return false;
            }
        });


    });



    $(document).ready(function () {

        $(document).on('click', '#submitData', function (e) {
            var sum = 0.0;
            $('.paidNowInv').each(function () {
                sum += parseFloat($(this).val());
            });
            e.preventDefault();
            var receivedFrom = $("#receivedFrom").val();
            var date = $("#date").val();
            var paymentMode = $("#paymentMode").val();
            var accountMode = $("#accountMode").val();
            var bank = $("#bank").val();
            var cardNumber = $("#cardNumber").val();
            var paymentDate = $("#paymentDate").val();
            var chequeNumber = $("#chequeNumber").val();
            var depositTo = $("#depositTo").val();
            var wallet = "0";
            var note = $("#note").val();
            var amount = sum;
            if (!paymentDate) {
                alert("Please select payment Date.");
                waitingSwal.close();
                return;
            }
            if (!receivedFrom) {
                alert("Please select customer.");
                waitingSwal.close();
                return;
            }

            if (!paymentMode && paymentMode !== '') {
                alert("Please select customer.");
                waitingSwal.close();
                return;
            }

            if ($('#paymentMode option:selected').attr('data-mode') !== "CASH") {
                if (!depositTo) {
                    alert("Please select deposit account.");
                    return;
                }
            }


            if ($('#paymentMode option:selected').attr('data-mode') === "BANK") {
                if (!bank) {
                    alert("Please select Bank.");
                    waitingSwal.close();
                    return;
                }
                if (!chequeNumber) {
                    alert("Please select Cheque number.");
                    waitingSwal.close();
                    return;
                }
            } else if ($('#paymentMode option:selected').attr('data-mode') === "CARD") {
                if (!cardNumber) {
                    alert("Please select card number.");
                    waitingSwal.close();
                    return;
                }
            }
            var tbl = $('#table1 tbody tr').map(function (idxRow, ele) {
                //
                // start building the retVal object
                //
                var retVal = {id: ++idxRow};
                //
                // for each cell
                //
                var $td = $(ele).find('td').map(function (idxCell, ele) {
                    var input = $(ele).find(':input');
                    //
                    // if cell contains an input or select....
                    //
                    if (input.length === 1) {
                        var attr = $('#table1 thead tr th').eq(idxCell).text();
                        retVal[attr] = input.val();
                    } else {
                        var attr = $('#table1 thead tr th').eq(idxCell).text();
                        retVal[attr] = $(ele).text();
                    }
                });
                return retVal;
            }).get();
            var reciptData = JSON.stringify(tbl).replace(/\s(?=\w+":)/g, "");
            var waitingSwal = Swal.fire({
                title: "Saving, Please wait!",
                showDenyButton: false,
                showCancelButton: false,
                showConfirmButton: false,
                allowOutsideClick: false
            });
            $.ajax({
                type: "POST",
                url: "/receipt",
                dataType: 'json',
                data: {
                    reciptData: reciptData,
                    receivedFrom: receivedFrom,
                    paymentMode: paymentMode,
                    cardNumber: cardNumber,
                    bank: bank,
                    accountModeId: accountMode,
                    paymentDate: paymentDate,
                    wallet: wallet,
                    chequeNumber: chequeNumber,
                    narration: note,
                    amountPaid: amount,
                    depositTo: depositTo,
                    date: date,
                    createdUser: '${session.getAttribute('userId')}',
                    modifiedUser: '${session.getAttribute('userId')}',
                    status: 1,
                    syncStatus: 1,
                },
                success: function (data) {
                    Swal.fire({
                        title: "Success!",
                        showDenyButton: true,
                        showCancelButton: false,
                        confirmButtonText: 'OK',
                        denyButtonText: 'Print',
                        allowOutsideClick: false
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        } else if (result.isDenied) {
                            location.reload();
                            window.open("/print-recipt/" + data.receivedFrom + "/recipt/" + data.id, '_blank');
                        }
                    });
                },
                error: function () {
                    Swal.fire({
                        title: 'Something went wrong!',
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            });
        });
    });




    $('#invdebitCheck'+id).change(function(){
        if($(this).is(":checked")) {
            $('div.menuitem').addClass("add");
        } else {
            $('div.menuitem').removeClass("sub");
        }
    });


</script>
%{--<g:include view="controls/footer-content.gsp"/>--}%
<script>
    // selectSideMenu("accounts-menu");
</script>
</body>
</html>