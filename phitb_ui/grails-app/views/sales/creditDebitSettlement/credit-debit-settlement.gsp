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


    .table tfoot {
        position: sticky;
        inset-block-end: 0; /* "bottom" */
    }


    /* Just common table stuff. Really. */
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px 16px;
    }

    th, tfoot {
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
                                    <div class="col-lg-6" style="padding: 2px;">
                                        <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                        max-height:300px;">
                                            <table class="table table-bordered" id="table1">
                                                <thead>
                                                <tr>
                                                    <th>Type</th>
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
                                                <tfoot class="tab">
                                                <tr>
                                                    <td>TOTAL</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td id="invTotalAmt">0.00</td>
                                                    <td id="invPendingTotalAmt">0.00</td>
                                                    <td></td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-6"  style="padding: 2px;">
                                        <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                        max-height:300px;">
                                            <table class="table table-bordered" id="table2">
                                                <thead>
                                                <tr>
                                                    <th>Type</th>
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
                                                <tfoot class="tab">
                                                <tr>
                                                    <td>TOTAL</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td id="crntTotalAmt">0.00</td>
                                                    <td id="crntPendingTotalAmt">0.00</td>
                                                    <td></td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-12" style="background-color: lightgrey;padding: 10px;">
                                        <p style="font-weight: bold;background: green;
                                        color: #fff;padding: 10px;">CALCULATING VALUE:&nbsp;<span
                                                id="totalDebitBalance">0.00</span>
                                            <span id="totalCreditBalance" style="float: right;">0.00</span></p>

                                    <p style="font-weight: bold;background-color: #313740;
                                    color: #fff;
                                    padding: 10px;">CR.DB SETTLEMENT VALUE :&nbsp;<span
                                            id="crdbAmt">0.00</span></p>
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
                    console.log(data);
                    var invHTML = '';
                    var crntHTML = '';
                    invHTML += '';
                    crntHTML += '';
                    var invoice = "INV";
                    var creditNote = "CRNT";
                    var gtn = "GTN";
                    var invPendingTotalAmt = data[0].filter(data => data.billStatus!=="CANCELLED").map(data =>
                        data.balance).reduce((acc, amount) => acc + amount, 0);
                    var invTotalAmt = data[0].filter(data => data.billStatus!=="CANCELLED").map(data =>
                        data.totalAmount).reduce((acc, amount) => acc + amount, 0);

                    var crntPendingTotalAmt = data[1].filter(data => data.returnStatus!=="CANCELLED").map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var  crntTotalAmt= data[1].filter(data => data.returnStatus!=="CANCELLED").map(data => data.totalAmount).reduce((acc, amount) => acc + amount, 0);
                    $('#totalCreditBalance').text(new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR'
                    }).format(0.00));
                    $('#totalDebitBalance').text(new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR'
                    }).format(0.00));
                    $('#crdbAmt').text(0.00);
                    $('#invTotalAmt').text(invTotalAmt.toFixed(2));
                    $('#invPendingTotalAmt').text(invPendingTotalAmt.toFixed(2));
                    $('#crntTotalAmt').text(crntPendingTotalAmt.toFixed(2));
                    $('#crntPendingTotalAmt').text(crntTotalAmt.toFixed(2));
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
                                '                           <td id="' + "invBal" + value.id +
                                '" ><input type="number" value="' + value.balance.toFixed(2) + '"  data-inid="' +
                                value.id +
                                '"   data-bal="' + value.balance +
                                '" style="width: 95%;" id="invBalance' + value.id +
                                '" class="balance" readonly></td>\n' +
                                '                                        <td><input type="checkbox" id="' +
                                "invdebitCheck" + value.id + '"  value="true" class="invdebitCheck" data-invid="'+
                                value.id +'"  data-balance="'+ value.balance + '"  data-totalAmt="'+
                                value.totalAmount + '" ></td>\n' +
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
                                '                                        <td id="' + "crntBal" + value.id + '" >'  + value.balance.toFixed(2) +
                                '</td>\n' +
                                '                                        <td><input type="checkbox" id="' +
                                "creditCheck" + value.id +
                                '"  data-balance="'+ value.balance + '" data-crntid="'+ value.id + '"  value="true" class="creditCheck" ></td>\n' +
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
            Swal.fire("Under Progress!Will be updated soon!")
        });
    });




    // $('#invdebitCheck'+id).change(function(){
    //     if($(this).is(":checked")) {
    //         $('div.menuitem').addClass("add");
    //     } else {
    //         $('div.menuitem').removeClass("sub");
    //     }
    // });

    var debitBalanceArray =[];
    $(document).on('change', '.invdebitCheck', function (e) {
        var id = $(this).attr('data-invid');
        var balance = parseFloat($(this).attr('data-balance'));
        var totalBalance;
        if($('#invdebitCheck'+id).prop('checked')){
            $('#IN'+id).css("background-color", "#d7e1f3");
            $("#invBalance"+id).attr("readonly", false);
            debitBalanceArray.push(balance);
            totalBalance = debitBalanceArray.reduce((a, b) => a + b, 0).toFixed(2);
            $('#totalDebitBalance').text(new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR' }).format(totalBalance));
            crdbVal();
        }else{
            $('#IN'+id).css("background-color", "transparent");
            $("#invBalance"+id).attr("readonly", true);
            $("#invBalance"+id).val(balance.toFixed(2));
            removeItem(debitBalanceArray, balance);
            totalBalance = debitBalanceArray.reduce((a, b) => a + b, 0).toFixed(2);
            $('#totalDebitBalance').text(new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR' }).format(totalBalance));
            crdbVal();
        }
    });


    var creditbalanceArray =[];
    $(document).on('change', '.creditCheck', function (e) {
        var id = $(this).attr('data-crntid');
        var balance = parseFloat($(this).attr('data-balance'));
        var totalBalance;
        if($('#creditCheck'+id).prop('checked')){
            $('#CN'+id).css("background-color", "#ffc0c0");
            creditbalanceArray.push(balance);
            totalBalance =
                new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR' }).format(creditbalanceArray.reduce((a, b) => a + b, 0).toFixed(2));
            $('#totalCreditBalance').text(totalBalance);
            crdbVal();
        }else{
            $('#CN'+id).css("background-color", "transparent");
            removeItem(creditbalanceArray, balance);
            totalBalance =
                new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR' }).format(creditbalanceArray.reduce((a, b) => a + b, 0).toFixed(2));
            $('#totalCreditBalance').text(totalBalance);
            crdbVal();
        }
    });




    function crdbVal() {
        if(creditbalanceArray.length!==0){
            var crdb = debitBalanceArray.reduce((a, b) => a + b, 0).toFixed(2) - creditbalanceArray.reduce((a, b) => a + b, 0).toFixed(2);
            $('#crdbAmt').text(new Intl.NumberFormat('en-US', { style: 'currency', currency: 'INR' }).format(crdb));
        }else{
            $('#crdbAmt').text(0.00);
        }
    }


    $(document).on('keyup', '.balance', function (e) {
        var id = $(this).attr('data-cnid');
        var bal = Number($(this).attr('data-crbal')).toFixed(2)
        var value = $('#paidNowCrnt' + id).val();
        if (Number(value) > Number(bal)) {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Amount should not be greater!',
                showConfirmButton: false,
                timer: 1000
            });
            $('#paidNowCrnt' + id).val(0);
        }
    });


    function removeItem(array, item){
        for(var i in array){
            if(array[i]===item){
                array.splice(i,1);
                break;
            }
        }
    }

</script>
%{--<g:include view="controls/footer-content.gsp"/>--}%
<script>
    // selectSideMenu("accounts-menu");
</script>
</body>
</html>
