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

    .table td, .table th {
        padding: 0.1rem;
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
        background: #eee;
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
                        <form action="/recipt" id="form_validation" method="POST" role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-12 form-group  form-float">
                                    <label for="date">
                                        Date:
                                    </label>
                                    <input type="text" id="date" class="date" name="date"
                                           placeholder="Receipt Date" readonly
                                           required/>
                                </div>

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
                                            id="paymentMode" onchange="payMode(this.value)" required style="height: 37px;width: 425px;">
                                        <option value="">-- Please select --</option>
                                        <g:each var="pm" in="${paymodes}">
                                        <option value="${pm.id}" data-mode="${pm.name}">${pm.name}</option>
                                        </g:each>
%{--                                        <option value="1" data-mode="BANK">BANK</option>--}%
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group form-float" id="mode">

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

                                <div class="col-lg-3 form-group  form-float">
                                    <label for="wallet">
                                        Wallet
                                    </label>
                                    <select class="form-control show-tick wallet" name="wallet"
                                            id="wallet" required>
                                        <option value="">-- Please select --</option>
                                        <g:each var="w" in="${wallet}">
                                            <option value="${w.id}">${w.walletName}</option>
                                        </g:each>
                                    </select>
                                </div>

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
                                    <input type="text" id="amountPaid" class="note form-control amountPaid"
                                           name="amountPaid"
                                           placeholder="Amount" readonly
                                           required/>
                                </div>

                                %{--                                <div class="col-lg-6 form-group  form-float">--}%
                                %{--                                    <label for="total">--}%
                                %{--                                        Total Cheque:--}%
                                %{--                                    </label>--}%
                                %{--                                    <input type="text" style="margin-left: 43px;" id="tc" class="tc" name="tc"--}%
                                %{--                                           placeholder="0.00" autocomplete="off">--}%
                                %{--                                    <br>--}%
                                %{--                                    <label for="total">--}%
                                %{--                                        Total Bill Amount:--}%
                                %{--                                    </label>--}%
                                %{--                                    <input style="margin-left: 18px;" type="text" id="tba" class="tba" name="tba"--}%
                                %{--                                           placeholder="0.00" readonly>--}%
                                %{--                                    <br>--}%

                                %{--                                    <span style="margin-left: 134px;">====================</span>--}%
                                %{--                                    <br>--}%
                                %{--                                    <input style="margin-left: 134px;" type="text" id="total" class="total" name="total"--}%
                                %{--                                           placeholder="0.00" readonly>--}%
                                %{--                                </div>--}%





                                %{--                                <div class="col-lg-6 form-group  form-float">--}%
                                %{--                                    <label for="cardNumber">--}%
                                %{--                                        Card Number--}%
                                %{--                                    </label>--}%
                                %{--                                    <div class="demo-masked-input">--}%
                                %{--                                    <input type="number" id="cardNumber" class="credit-card form-control"--}%
                                %{--                                           name="Card Number"--}%
                                %{--                                           placeholder="Card Number"--}%
                                %{--                                           required/>--}%
                                %{--                                    </div>--}%
                                %{--                                </div>--}%

                                <div class="container">
                                    <div class="row">
                                        <div class="col-lg-6">Settled Vocher List</div>

                                        <div class="col-lg-6">Unsettled Vocher List</div>

                                        <div class="col-lg-6">
                                            <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                            max-height:300px;">
                                                <table class="table table-striped" id="table1" border="1">
                                                    <tr>
                                                        <thead>
                                                        <th>Doc.Type</th>
                                                        <th>Yr</th>
                                                        <th>DD</th>
                                                        <th>Invoice Number</th>
                                                        <th>Amount</th>
                                                        <th>Adj Amount</th>
                                                        <th style="width: 2%"></th>
                                                        </thead>
                                                    </tr>
                                                    <tbody class="settledVocher">
                                                    </tbody>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>Total:&nbsp;<span class="total_bal_s"></span></td>

                                                </table>
                                            </div>
                                        </div>


                                        <div class="col-lg-6">
                                            <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                            max-height:300px;">
                                                <table id="table2" class="table table-striped"
                                                       border="1">
                                                    <tr>
                                                        <thead>
                                                        <th></th>
                                                        <th>Doc.Type</th>
                                                        <th>Yr</th>
                                                        <th>DD</th>
                                                        <th>Invoice Number</th>
                                                        <th>Amount</th>
                                                        </thead>
                                                    </tr>
                                                    <tbody class="unsettledVocher">
                                                    </tbody>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>Total:&nbsp;<span class="total_bal"></span></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="syncStatus" value="1">
                                <input type="hidden" name="createdUser" value="1">
                                <input type="hidden" name="modifiedUser" value="1">

                                <div class="col-lg-12">
                                    <div class="" style="float: right;">
                                        <input name="id" id="id" class="id" type="hidden">
                                        <input name="type" class="type" value="add" type="hidden">
                                        <button type="submit" class="btn btn-default btn-round waves-effect"
                                                name="submituser"><font style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">SUBMIT</font></font></button>
                                        <button type="reset" class="btn btn-danger btn-simple btn-round waves-effect"
                                                data-dismiss="modal"><font style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">RESET</font></font></button>
                                    </div>
                                </div>
                            </div>
                        </form>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

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
            html=' <label for="bank">\n' +
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
        } else if ($('#paymentMode option:selected').attr('data-mode') === "CARD") {
            html='<label for="cardNumber">\n' +
                '        Card Number\n' +
                '    </label>\n' +
                '    <input type="number" id="cardNumber" class="cardNumber form-control"\n' +
                '    name="cardNumber"\n' +
                '    placeholder="Card Number"\n' +
                '    />'
            $(".cheque").hide().prop('required',false)
            $(".cardNumber").prop('required',false)

        }
        else if($('#paymentMode option:selected').attr('data-mode') === "CASH")
        {
            $("#mode").hide().prop('required',false)
            $(".cheque").hide().prop('required',false)
        }
        $('#mode').html(html)
    }


    function getAddress(id) {
        $.ajax({
            type: 'GET',
            url: '/getbyentity/' + id,
            dataType: 'json',
            success: function (data) {
                getUnsettledByCustomer(id)
                getsettledSaleBillByCustomer(id)
                var trHTML = '';
                trHTML += '<p><b>' + data.entityName + '</b><br>' + data.addressLine1 + '' + data.addressLine2 + '</p>';
                $('#caddress').html(trHTML);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });

    }

    function getUnsettledByCustomer(id) {
        $.ajax({
            type: 'GET',
            url: '/getallunsettledbycustomer/' + id,
            dataType: 'json',
            success: function (data) {
                var trHTML = '';
                var trHTML1 = '';
                trHTML += '';
                trHTML1 += '';
                var invoice = "INVS";
                var cred = "CRNT";
                if (data.length !== 0) {
                    var inv = data[0].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                    var crnt = data[1].map(data => data.balance).reduce((acc, amount) => acc + amount, 0)
                    var total_bal = inv - crnt
                } else {
                    total_bal = 0;
                }
                $('.total_bal').text(parseFloat(total_bal).toFixed(2));

                $.each(data[0], function (key, value) {
                    if(value.balance!==0 && value.billStatus!=='DRAFT') {
                        trHTML1 +=
                            '<tr id="' + "IN" + value.id + '"><td><button type="button" data-id="' + value.id +
                            '"  data-custId="' + value.customerId +
                            '"   data-adj="' + value.adjAmount + '" class="btn-sm btn-primary"  id="settled"><-</button></td><td>' + invoice +
                            '</td><td>' + value.financialYear +
                            '</td><td>' + moment(value.dateCreated).format('DD-MM-YYYY') +
                            '</td><td>' + value.invoiceNumber +
                            '</td><td><input type="number" name="balunsettled" value="' + value.balance +
                            '" id="balunsettled" data-inid="' + value.id + '"  data-custId="' + value.customerId +
                            '" style="width: 65%;" readonly></td></tr>';
                    }
                });

                $.each(data[1], function (key, value) {
                    var date = new Date(value.entryDate);
                    if(value.balance!==0) {
                        trHTML +=
                            '<tr id="' + "CR" + value.id + '"><td><button type="button" data-id="' + value.id +
                            '"  data-custId="' + value.customerId +
                            '"   data-adj="' + value.adjAmount + '" class="btn-sm btn-primary" id="cnsettled"><-</button></td><td>' + cred + '</td><td>' + value.financialYear +
                            '</td><td>' + moment(date).format('DD-MM-YYYY') +
                            '</td><td>' + value.invoiceNumber +'</td><td><input type="number" value="' + value.balance +
                            '" id="' + "CR" + value.id + '" data-cnid="' + value.id + '" style="width: 65%;" readonly></td></tr>';
                    }
                });
                $('.unsettledVocher').html(trHTML+trHTML1);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }


    function getsettledSaleBillByCustomer(id) {
        $.ajax({
            type: 'GET',
            url: '/getallsettledbycustomer/' + id,
            dataType: 'json',
            success: function (data) {
                var trHTML = '';
                var trHTML1 = '';
                trHTML += '';
                trHTML1 += '';
                var invoice = "INVS";
                var cred = "CRNT";
                var inv = data[0].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                var invAdjAmt = data[0].map(data => data.adjAmount).reduce((acc, adjAmt) => acc + adjAmt, 0);
                var crnt = data[1].map(data => data.balance).reduce((acc, amount) => acc + amount, 0);
                var  crntAdjAmt= data[1].map(data => data.adjAmount).reduce((acc, adjAmount) => acc + adjAmount, 0);
                var total_bal_s = invAdjAmt - crntAdjAmt;
                $('.total_bal_s').text(parseFloat(total_bal_s).toFixed(2));
                $('.tba').val(total_bal_s.toFixed(2));
                $('.amountPaid').val(total_bal_s.toFixed(2));
                $.each(data[0], function (key, value) {
                    if(value.balance!==0 && value.billStatus!=='DRAFT')
                    {
                        trHTML +=
                            '<tr id="' + "IN" + value.id + '"><td>' + invoice +
                            '</td><td>' + value.financialYear +
                            '</td><td>' + moment(value.dateCreated).format('DD-MM-YYYY') +
                            '</td><td>' + value.invoiceNumber +'</td><td><input type="number" value="' + value.balance +
                            '" id="INVbalsettled" data-inid="' + value.id + '"  data-custId="' + value.customerId
                            + '" data-invbal="' + value.balance + '" ></td><td>' + value?.adjAmount +
                            '</td><td><button type="button" data-id="' + value.id + '"  data-custId="' +
                            value.customerId + '" data-adjAmt="' + value.adjAmount + '" class="btn-sm btn-primary" id="unsettled">-></button></td></tr>';
                    }

                });
                $.each(data[1], function (key, value) {
                    var date = new Date(value.entryDate);
                    if(value.balance!==0) {
                        trHTML +=
                            '<tr id="' + "CR" + value.id + '"><td>' + cred +
                            '</td><td>' + value.financialYear +
                            '</td><td>' + moment(date).format('DD-MM-YYYY') +
                            '</td><td>' + value.invoiceNumber +'</td><td><input type="number" value="' + value.balance +
                            '" id="CRbalsettled" data-cnid="' + value.id + '"  data-custId="' + value.customerId
                            + '"  data-crbal="' + value.balance + '" ></td><td>' + value.adjAmount +
                            '</td><td><button type="button" data-id="' + value.id +
                            '"  data-custId="' + value.customerId +
                            '"  data-adj="' + value.adjAmount + '" class="btn-sm btn-primary" id="cnunsettled">-></button></td></tr>';
                    }
                });
                $('.settledVocher').html(trHTML+trHTML1);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }

    // var input = document.getElementById("tc");
    // input.addEventListener("keydown", function (e) {
    //     if (e.target.value !== 0) {
    //         if (e.key === "Enter") {
    //             $(".total").val(total().toFixed(2))
    //         }
    //     }
    // });
    //
    // function total() {
    //     // Capture the entered values of two input boxes
    //     var my_input1 = document.getElementById('tc').value;
    //     var my_input2 = document.getElementById('tba').value;
    //     return parseFloat(my_input1) - parseInt(my_input2)
    // }

    $(document).on('click', '#settled', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        var custId = $(this).attr('data-custId');
        var adj = $(this).attr('data-adj');
        var url = '/salesettledvocher/' + id;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table2 tr#IN' + id).remove();
                getUnsettledByCustomer(custId)
                getsettledSaleBillByCustomer(custId)
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });


    $(document).on('click', '#unsettled', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        var custId = $(this).attr('data-custId');
        var url = '/saleunsettledvocher/' + id;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table1 tr#IN' + id).remove();
                getUnsettledByCustomer(custId);
                getsettledSaleBillByCustomer(custId)
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });


    $(document).on('click', '#cnsettled', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        var adj = $(this).attr('data-adj');
        var custId = $(this).attr('data-custId');
        var url = '/creditsettledvocher/' + id+"?adj="+adj;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table2 tr#CN' + id).remove();
                getUnsettledByCustomer(custId);
                getsettledSaleBillByCustomer(custId);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });


    $(document).on('click', '#cnunsettled', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        var adj = $(this).attr('data-adj');
        var custId = $(this).attr('data-custId');
        var url = '/creditunsettledvocher/' + id+"?adj="+adj;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table1 tr#CN' + id).remove();
                getUnsettledByCustomer(custId);
                getsettledSaleBillByCustomer(custId)
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });

    $(document).ready(function () {
        $(window).keydown(function (event) {
            if (event.keyCode === 13) {
                event.preventDefault();
                return false;
            }
        });


        $(document).on('keydown','#INVbalsettled',function(e){
            if (e.keyCode === 13 || e.which === '13') {
                var balance = Number($(this).val());
                var id = $(this).attr('data-inid');
                var invbal = $(this).attr('data-invbal');
                var custId = $(this).attr('data-custId');
                var billType = "INVOICE";
                var url="/updatesalebalance?id="+id+"&balance="+balance;
                if(balance!==0 && balance<=invbal)
                {
                    $.ajax({
                        url: url,
                        type: "POST",
                        contentType: false,
                        processData: false,
                        data: {
                            balance: balance,
                            id:id
                        },
                        success: function () {
                            $.ajax({
                                type: "POST",
                                url: "/updatereciptlog",
                                data: {
                                    amountPaid: balance,
                                    billId:id,
                                    billType:billType,
                                    financialYear:'${session.getAttribute('financialYear')}',
                                    currentFinancialYear:'${session.getAttribute('financialYear')}',
                                },
                                success: function (data) {
                                    getUnsettledByCustomer(custId);
                                    getsettledSaleBillByCustomer(custId)
                                }
                            });
                        },
                        error: function () {
                            swal("Error!", "Something went wrong", "error");
                        }
                    });
                }
               else
                {
                    if(balance === 0)
                    {
                        swal("Balance should not be zero!!");
                        $('#balsettled').val(invbal)
                    }
                    else
                    {
                        swal("Balance should not exceed!!");
                        $('#balsettled').val(invbal)
                    }
                    $('#balsettled').val(invbal)
                }
            }
        });


        $(document).on('keydown','#CRbalsettled',function(e){
            if (e.keyCode === 13 || e.which === '13') {
                var balance = Number($(this).val());
                var id = $(this).attr('data-cnid');
                var invbal = $(this).attr('data-crbal');
                var custId = $(this).attr('data-custId');
                var billType = "CRNT";
                var url="/updatesalereturnbalance?id="+id+"&balance="+balance;
                if(balance!==0 && balance<=invbal)
                {
                    $.ajax({
                        url: url,
                        type: "POST",
                        contentType: false,
                        processData: false,
                        data: {
                            balance: balance,
                            id:id
                        },
                        success: function () {
                            $.ajax({
                                type: "POST",
                                url: "/updatereciptlog",
                                data: {
                                    amountPaid: balance,
                                    billId:id,
                                    billType:billType,
                                    financialYear:'${session.getAttribute('financialYear')}',
                                    currentFinancialYear:'${session.getAttribute('financialYear')}',
                                },
                                success: function (data) {
                                    getUnsettledByCustomer(custId);
                                    getsettledSaleBillByCustomer(custId)
                                }
                            });
                        },
                        error: function () {
                            swal("Error!", "Something went wrong", "error");
                        }
                    });
                }
                else
                {
                    if(balance === 0)
                    {
                        swal("Balance should not be zero!!");
                        $('#balsettled').val(invbal)
                    }
                    else
                    {
                        swal("Balance should not exceed!!");
                        $('#balsettled').val(invbal)
                    }
                    $('#balsettled').val(invbal)
                }
            }
        });




    });

</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>
</body>
</html>