<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Receipt</title>
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
                    <h2>Receipt</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/recipt">Accounts</a></li>
                        <li class="breadcrumb-item active">Receipt</li>
                    </ul>
                </div>

                <div class="col-lg-7 col-md-7 col-sm-12">
                    <div class="input-group m-b-0">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-addon">
                            <i class="zmdi zmdi-search"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Inline</strong> Layout</h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu dropdown-menu-right">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
                    <div class="body">
                        %{--                        <form action="/recipt" id="form_validation" method="POST" role="form"--}%
                        %{--                              class="entityRegisterForm" enctype="multipart/form-data">--}%
                        <div class="row clearfix">
                            %{--                                <div class="col-lg-12 form-group  form-float">--}%
                            %{--                                    <label for="date">--}%
                            %{--                                        Date:--}%
                            %{--                                    </label>--}%
                            %{--                                  --}%
                            %{--                                </div>--}%

                            <div class="col-lg-6">
                                <label for="receivedFrom">
                                    Customer
                                </label><br>
                                <select class=" show-tick receivedFrom" name="receivedFrom"
                                        id="receivedFrom" onchange="getAddress(this.value)" required
                                        style="width: 460px;">
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}"
                                                data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>

                                <div id="caddress" class="mt-2"></div>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="paymentMode">
                                    Payment Mode
                                </label><br>
                                <select class="show-tick paymentMode" name="paymentMode"
                                        id="paymentMode" onchange="payMode(this.value)" required
                                        style="height: 37px;width: 425px;">
                                    <option value="">-- Please select --</option>
                                    <g:each var="pm" in="${paymodes.reverse()}">
                                        <option value="${pm.id}" data-mode="${pm.name}">${pm.name}</option>
                                    </g:each>
                                %{--                                        <option value="1" data-mode="BANK">BANK</option>--}%
                                </select>
                            </div>

                            <div class="form-group form-float" id="mode">

                            </div>

                            <div class="col-lg-3 form-group  form-float">
                                <label for="accountMode">
                                    Account Mode
                                </label>
                                <select class="form-control show-tick accountMode" name="accountModeId"
                                        id="accountMode" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="am" in="${accountMode}">
                                        <option value="${am.id}">${am.mode}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-3 form-group  form-float">
                                <label for="accountMode">
                                    Deposit To
                                </label>
                                <select class="form-control show-tick depositTo" name="depositTo"
                                        id="depositTo" required style="padding: 0">
                                    <option value="">-- Please select --</option>
                                    <g:each var="ar" in="${accountRegister}">
                                        <option value="${ar.id}">${ar.accountName}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-3 form-group  form-float cheque">
                                <label for="chequeNumber">
                                    Cheque Number
                                </label>
                                <input type="number" id="chequeNumber" class="chequeNumber form-control"
                                       name="chequeNumber"
                                       placeholder="Cheque Number"/>
                            </div>

                            <div class="col-lg-3 form-group  form-float">
                                <label for="paymentDate">
                                    Payment Date
                                </label>
                                <input type="text" id="paymentDate" class="paymentDate form-control datetimepicker"
                                       name="paymentDate"
                                       placeholder="Payment Date"
                                       required/>
                            </div>


                            %{--                                <div class="col-lg-3 form-group  form-float">--}%
                            %{--                                    <label for="financialYear">--}%
                            %{--                                        Financial Year--}%
                            %{--                                    </label>--}%
                            %{--                                    <input type="text" id="financialYear" class="financialYear form-control"--}%
                            %{--                                           name="Financial Year"--}%
                            %{--                                           placeholder="Financial Year"--}%
                            %{--                                           required/>--}%
                            %{--                                </div>--}%

                            %{--                                <div class="col-lg-3 form-group  form-float">--}%
                            %{--                                    <label for="wallet">--}%
                            %{--                                        Wallet--}%
                            %{--                                    </label>--}%
                            %{--                                    <select class="form-control show-tick wallet" name="wallet"--}%
                            %{--                                            id="wallet" required>--}%
                            %{--                                        <option value="">-- Please select --</option>--}%
                            %{--                                        <g:each var="w" in="${wallet}">--}%
                            %{--                                            <option value="${w.id}">${w.walletName}</option>--}%
                            %{--                                        </g:each>--}%
                            %{--                                    </select>--}%
                            %{--                                </div>--}%

                            <div class="col-lg-3 form-group  form-float">
                                <label for="note">
                                    Remarks / Note
                                </label>
                                <input type="text" id="note" class="note form-control"
                                       name="narration"
                                       placeholder="Remark / Note"
                                       required/>
                            </div>

                            <div class="col-lg-3 form-group  form-float">
                                <label for="amountPaid">
                                    Amount
                                </label>
                                <input type="text" id="amountPaid" class="note form-control "
                                       name="amountPaid"
                                       placeholder="Amount" value="0"
                                       required/>
                            </div>

                            <div class="col-lg-3 form-group  form-float mt-4">
                                <button type="button" class="btn btn-secondary" id="autoAdj">Auto Adjust</button>
                            </div>

                            <input type="hidden" id="date" class="date" name="date"
                                   placeholder="Receipt Date" readonly
                                   required/>

                            <div class="container mt-5">
                                <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                max-height:300px;">
                                    <table class="table table-bordered" id="table1">
                                        <thead>
                                        <tr>
                                            <th>Doc.Type</th>
                                            <th>Trans_Id</th>
                                            <th>Bill Date</th>
                                            <th>Prev.Paid Amt</th>
                                            <th>Bal Amt</th>
                                            <th>Paid Now</th>
                                            <th>Total Amt</th>
                                            <th>No of Days</th>
                                            <th>Fin_Year</th>
                                            <th style="display: none;">BillId</th>
                                        </tr>
                                        </thead>
                                        <tbody id="billDetails">
                                        <tr><td colspan='9'><div
                                                style='text-align: center;'><h2>Please select customer</h2></div>
                                        </td></tr>
                                        </tbody>
                                    </table>
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

    function payMode(mode) {
        var html = '';
        if ($('#paymentMode option:selected').attr('data-mode') === "BANK") {
            $("#mode").show()
            $("#mode").addClass('col-lg-6')
            html = ' <label for="bank">\n' +
                '                                        Bank\n' +
                '                                    </label>\n' +
                '                                    <select class="form-control show-tick bank" name="bank"\n' +
                '                                            id="bank" required>\n' +
                '                                        <option value="">-- Please select --</option>\n' +
                '                                        <g:each var="b" in="${bank}">\n' +
                '                                            <option value="${b.id}">${b.bankName}</option>\n' +
                '                                        </g:each>\n' +
                '                                    </select>'
            $(".cheque").show()
        }

        if ($('#paymentMode option:selected').attr('data-mode') === "CARD") {
            $("#mode").addClass('col-lg-6')
            html = '<label for="cardNumber">\n' +
                '        Card Number\n' +
                '    </label>\n' +
                '    <input type="number" id="cardNumber" class="cardNumber form-control"\n' +
                '    name="cardNumber"\n' +
                '    placeholder="Card Number"\n' +
                '    />'
            $(".cheque").hide().prop('required', false)
            $(".cardNumber").prop('required', false)

        }
        if ($('#paymentMode option:selected').attr('data-mode') === "CASH") {
            $("#mode").addClass('col-lg-6')
            $("#mode").hide().prop('required', false)
            $(".cheque").hide().prop('required', false)
        }
        if ($('#paymentMode option:selected').attr('data-mode') === "") {
            $('#mode').removeClass('col-lg-6');
        }
        $('#mode').html(html)
    }

    function calculateNoOfDays(date) {
        const oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
        const firstDate = new Date(date);
        const secondDate = new Date();
        const diffDays = Math.round(Math.abs((firstDate - secondDate) / oneDay));
        return diffDays
    }

    function getAddress(id) {
        if (id) {
            $.ajax({
                type: 'GET',
                url: '/getbyentity/' + id,
                dataType: 'json',
                success: function (data) {
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


    let invIdArray = [];

    function getAllSaleBillDetails(id) {
        if (id) {
            $.ajax({
                type: 'GET',
                url: '/recipts/getallbilldetails?id=' + id,
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    var trHTML = '';
                    var trHTML1 = '';
                    trHTML += '';
                    trHTML1 += '';
                    var invoice = "INVS";
                    var creditNote = "CRNT";
                    var inv = data[0].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var invAdjAmt = data[0].map(data => data.adjAmount).reduce((acc, adjAmt) => acc + adjAmt, 0);
                    var crnt = data[1].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var crntAdjAmt = data[1].map(data => data.adjAmount).reduce((acc, adjAmount) => acc + adjAmount, 0);
                    var total_bal_s = invAdjAmt - crntAdjAmt;
                    $('.total_bal_s').text(parseFloat(total_bal_s).toFixed(2));
                    $('.tba').val(total_bal_s.toFixed(2));
                    $('.amountPaid').val(total_bal_s.toFixed(2));
                    $.each(data[0], function (key, value) {
                        if (value.balance !== 0 && value.billStatus !== 'DRAFT') {
                            trHTML += ' <tr id="' + "IN" + value.id + '">\n' +
                                '                                        <td>' + invoice + '</td>\n' +
                                '                                        <td>' + value.invoiceNumber + '</td>\n' +
                                '                                        <td>' + moment(value.dateCreated).format('DD-MM-YYYY') + '</td>\n' +
                                '                                        <td id="' + "invAdjAmt" + value.id + '">' + value.adjAmount.toFixed(2) + '</td>\n' +
                                '                                        <td id="' + "invBal" + value.id + '" >' +
                                value.balance.toFixed(2) +
                                '</td>\n' +
                                '                                        <td><input type="number" class="paidNowInv txt" id="paidNowInv' + value.id + '" name="paidNowInv" data-inid="' + value.id + '" data-bal="' + value.balance + '" style="width: 100px;" pattern="\\d{1,10}(?:\\.\\d{1,3})?$" value="0"></td>\n' +
                                '                                        <td>' + value.totalAmount.toFixed(2) + '</td>\n' +
                                '                                        <td>' + calculateNoOfDays(value.dateCreated) + '</td>\n' +
                                '                                        <td>' + value.financialYear + '</td>\n' +
                                '                                        <td style="display: none;">' + value.id + '</td>\n' +
                                '                                        </tr>';

                            invIdArray.push(value.id);
                            console.log(invIdArray)
                        }
                    });

                    $.each(data[1], function (key, value) {
                        var date = new Date(value.entryDate);
                        if (value.balance !== 0) {
                            trHTML1 += ' <tr id="' + "CR" + value.id + '">\n' +
                                '                                        <td>' + creditNote + '</td>\n' +
                                '                                        <td  >' + value.invoiceNumber + '</td>\n' +
                                '                                        <td>' + moment(value.dateCreated).format('DD-MM-YYYY') + '</td>\n' +
                                '                                        <td>' + "-" + value.totalAmount.toFixed(2) + '</td>\n' +
                                '                                        <td>' + value?.adjAmount.toFixed(2) + '</td>\n' +
                                '                                        <td id="inv' + id + '">' + "-" +
                                value.balance.toFixed(2) +
                                '</td>\n' +
                                '<td><input type="number" class="paidNowCrt txt" name="paidNowCrt" style="width: 100px;" value="0"></td>\n' +
                                '                                        <td>' + calculateNoOfDays(value.dateCreated) + '</td>\n' +
                                '                                        <td>' + value.financialYear + '</td>\n' +
                                '                                        <td style="display: none;">' + value.id + '</td>\n' +
                                '                                        </tr>';
                        }
                    });
                    $('#billDetails').html(trHTML + trHTML1);

                    if (data[0].length === 0 && data[1].length === 0) {
                        $('#billDetails').html("<tr><td colspan='9'><div style='text-align: center;'><h2>No Data Found</h2></div></td></tr>");
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

    $(document).on('keyup', '.txt', function (e) {
        var id = $(this).attr('data-inid');
        var bal = $(this).attr('data-bal');
        var value = $('#paidNowInv' + id).val();
        if (Number(value) > Number(bal)) {
            Swal.fire({
                position: 'top-end',
                icon: 'error',
                title: 'Amount should not be greater!',
                showConfirmButton: false,
                timer: 1000
            });
            $('#paidNowInv' + id).val(0);
        }
        if (e.keyCode === 13 || e.which === '13') {
            var index = $('.txt').index(this) + 1;
            $('.txt').eq(index).focus();

            // var prevPaid = $('table#table1 tr#IN'+id+' td#invAdjAmt'+id+'').text();
            // var paid = parseFloat(prevPaid) + parseFloat($('#paidNowInv').val());
            // $('table#table1 tr#IN'+id+' td#invAdjAmt'+id+'').text(parseFloat(paid).toFixed());
            // alert(id)
            // if (this.value.length === this.maxLength) {
            //     alert(id)
            //     $(this).next('.paidNowInv').focus();
            // }

        }
    });

    $(document).on('click', '#autoAdj', function (e) {
        var amountPaid = Number($('#amountPaid').val());
        if(amountPaid!==0 && amountPaid!=="")
        {
            $.each(invIdArray, function (key, value) {
                var invBal = Number($('#invBal' + value).text());
                if (invBal > amountPaid) {
                    $('#paidNowInv' + value).val(amountPaid)
                    amountPaid = 0;
                } else {
                    amountPaid = amountPaid - invBal;
                    $('#paidNowInv' + value).val(invBal)
                }
            });
        }
        else
        {
            Swal.fire({
                icon: 'error',
                title: 'Enter vaild amount!',
                showConfirmButton: false,
                timer: 1000
            });
        }
        // var databal = $('.paidNowInv').attr('data-bal');
        // var inputs = $(".paidNowInv");
        // var invoices = $('.paidNowInv').length;
        // var crnt = $('.paidNowCrt').length;
        // $(".paidNowInv[data-bal]").each(function () {
        //     var databal = $(this).attr('data-bal');
        //     if (value !== 0) {
        //         var paidNow = parseFloat(value) / parseFloat(invoices);
        //         $('.paidNowInv').val(parseFloat(paidNow).toFixed(2));
        //         $('.paidNowCrt').val(parseFloat(0).toFixed(2));
        //     }
        //     else
        //     {
        //         $('.paidNowInv').val(parseFloat(0).toFixed(2));
        //     }
        // });
        // var table = $('#table1').tableToJSON();
        // $('#bills').val(table.toString())
        // console.log(table)
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
            if (!depositTo) {
                alert("Please select deposit account.");
                waitingSwal.close();
                return;
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
                url: "/recipt",
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
                        }else if (result.isDenied) {
                            location.reload();
                            window.open("/print-recipt/"+data.receivedFrom+"/recipt/"+data.id, '_blank');
                        }
                    });
                },
                error: function () {
                    console.log('ERR')
                }
            });
        });
    });

</script>
%{--<g:include view="controls/footer-content.gsp"/>--}%
<script>
    // selectSideMenu("accounts-menu");
</script>
</body>
</html>