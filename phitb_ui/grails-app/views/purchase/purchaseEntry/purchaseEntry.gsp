<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Purchase Entry</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>

    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/select2/dist/css/select2.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/handsontable/handsontable.full.css" rel="stylesheet"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    %{--    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.css">--}%

    <style>
    .form-control {
        border-radius: 7px !important;
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
        <div class="block-header" style="padding: 1px;">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    %{--<h2>Sale Entry</h2>--}%
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Purchase Entry</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card" style="margin-bottom: 10px;">
                    <div class="header" style="padding: 1px;">

                    </div>

                    <div class="body">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control date" name="date" id="date"/>
                            </div>

                            <div class="col-md-4">
                                <label for="series">Series:</label>
                                <select onchange="seriesChanged()" class="form-control" id="series" name="series">
                                    <g:each in="${series}" var="sr">
                                        <option data-seriescode="${sr.seriesCode}"
                                                value="${sr.id}">${sr.seriesName} (${sr.seriesCode})</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label for="supplier">Supplier:</label>
                                <select class="form-control show-tick" id="supplier"
                                        onchange="supplierChanged()">
                                    <g:each in="${customers}" var="cs">
                                        <g:if test="${cs.id != session.getAttribute("entityId")}"><option
                                                value="${cs.id}"
                                                data-state="${cs.stateId}">${cs.entityName} (${cs.entityType
                                                    .name})</option>
                                        </g:if>
                                    </g:each>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-3">
                                <label for="priority">Priority:</label>
                                <select class="form-control" id="priority" name="priority">
                                    <g:each in="${priorityList}" var="pr">
                                        <option value="${pr.id}">${pr.priority}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label for="supplierBillId">Supplier Invoice Number:</label>
                                <input type="text" maxlength="100" class="form-control" name="supplierBillId"
                                       id="supplierBillId"/>
                            </div>

                            <div class="col-md-3">
                                <label for="supplierBillDate">Supplier Invoice Date:</label>
                                <input type="date" class="form-control supplierBillDate" name="supplierBillDate"
                                       id="supplierBillDate"/>
                            </div>

                            <div class="col-md-3">
                                <label for="duedate">Due Date:</label>
                                <input type="date" class="form-control date" name="duedate" id="duedate"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12" style="margin-bottom: 0;">
                <div class="card" style="margin-bottom: 10px;">
                    <div class="body" style="background-color: #313740;padding: 2px; color: #fff;">
                        <div class="row" style="margin: 0; font-size: 14px;">
                            <div class="col-md-2"><strong>Total GST:</strong> &#x20b9;<span id="totalGST">0</span></div>

                            <div class="col-md-2"><strong>Total SGST:</strong> &#x20b9;<span id="totalSGST">0</span>
                            </div>

                            <div class="col-md-2"><strong>Total CGST:</strong> &#x20b9;<span id="totalCGST">0</span>
                            </div>

                            <div class="col-md-2"><strong>Total IGST:</strong>&nbsp;&#x20b9;<span
                                    id="totalIGST">0</span></div>

                            <div class="col-md-2"><strong>Total Qty:</strong> <span id="totalQty">0</span></div>

                            <div class="col-md-2"><strong>Total Free Qty:</strong> <span id="totalFQty">0</span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card" style="margin-bottom:10px;">
                    <div class="body">
                        <div class="table-responsive">
                            <div id="purchaseTable" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-4" style="margin-bottom: 10px;">
                <p style="margin: 0; font-size: 10px;">Keyboard Shortcuts - Delete Row: <strong>Ctrl+Alt+C</strong>, Reset Table: <strong>Ctrl+Alt+R</strong>
                </p>
            </div>

            <div class="col-lg-4" style="margin-bottom: 10px;">

            </div>

            <div class="col-lg-4" style="margin-bottom: 10px;">
%{--                <p style="margin: 0; font-size: 10px;color: red;">Offers: <span id="offers"></span>--}%
%{--                </p>--}%
            </div>
        </div>

        <div class="row clearfix">

            <div class="col-lg-8">
                <div class="card">
                    <div class="header" style="padding: 1px;">
                        Stocks
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <div id="batchTable" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="card">
                    <div class="header" style="padding: 1px;">
                        -
                    </div>

                    <div class="body">
                        <div class="row">
                            <div class="col-md-6">
                                Total: <p>&#x20b9;&nbsp;<span id="totalAmt">0</span></p>
                            </div>

                            <div class="col-md-6">
                                Inv No: <span id="invNo"></span>
                            </div>
                        </div>

                        <div class="row">
                            <button onclick="resetPage()" class="btn btn-danger">Reset</button>
                            %{--<button onclick="savePurchaseInvoice('DRAFT')" class="btn btn-primary">Save Draft</button>--}%
                            <button onclick="savePurchaseInvoice('ACTIVE')" class="btn btn-primary">Save</button>
                            %{-- <button onclick="printInvoice()" class="btn btn-secondary">Print</button>--}%
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</section>

<div class="example-modal">
    <div class="modal fade" id="addSchemeModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="schemeModalTitle">Scheme Entry</h4>
                </div>
                <form action="" id="schemeForm" method="post" role="form"  class="schemeForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row clearfix">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="card">
                                    <div class="header">
                                        <h6>SLAB 1</h6>
                                    </div>

                                    <div class="body">
                                        <div class="row">
                                            <div class="col-md-12 mt-2">
                                                <div class="row">
                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab1MinQty">
                                                            Slab 1 Min Quantity
                                                        </label>
                                                        <input type="number" id="slab1MinQty"
                                                               class="form-control slab1MinQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab1MinQty" placeholder="Slab 1 Min Quantity"
                                                               required/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab1SchemeQty">
                                                            Slab 1 Scheme Quantity
                                                        </label>
                                                        <input type="number" id="slab1SchemeQty"
                                                               class="form-control slab1SchemeQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab1SchemeQty" placeholder="Slab 1 Scheme Quantity"
                                                               required/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab1BulkStatus">
                                                            Slab 1 Bulk Status
                                                        </label>
                                                        <input type="number" id="slab1BulkStatus"
                                                               class="form-control slab1BulkStatus" value ="0"
                                                               name="slab1BulkStatus" placeholder=" Slab 1 bulk Status"
                                                               required/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab1Status">
                                                            Slab 1 Status
                                                        </label>
                                                        <input type="number" id="slab1Status" class="form-control slab1Status"
                                                               name="slab1Status" placeholder="Slab 1 Status" value ="0"
                                                               required/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="card">
                                    <div class="header">
                                        <h6>SLAB 2</h6>
                                    </div>

                                    <div class="body">
                                        <div class="row">
                                            <div class="col-md-12 mt-2">
                                                <div class="row">
                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab2MinQty">
                                                            Slab 2 Min Quantity
                                                        </label>
                                                        <input type="number" id="slab2MinQty"
                                                               class="form-control slab2MinQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab2MinQty" placeholder="Slab 2 Min Quantity"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab2SchemeQty">
                                                            Slab 2 Scheme Quantity
                                                        </label>
                                                        <input type="number" id="slab2SchemeQty"
                                                               class="form-control slab2SchemeQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab2SchemeQty" placeholder="Slab 2 Scheme Quantity"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab2BulkStatus">
                                                            Slab 2 Bulk Status
                                                        </label>
                                                        <input type="number" id="slab2BulkStatus"
                                                               class="form-control slab2BulkStatus" value ="0"
                                                               name="slab2BulkStatus" placeholder="Slab 2 Bulk Status"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab2Status">
                                                            Slab 2 Status
                                                        </label>
                                                        <input type="number" id="slab2Status"
                                                               class="form-control slab2Status" value ="0"
                                                               name="slab2Status" placeholder="Slab 2 Status"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="card">
                                    <div class="header">
                                        <h6>SLAB 3</h6>
                                    </div>

                                    <div class="body">
                                        <div class="row">
                                            <div class="col-md-12 mt-2">
                                                <div class="row">
                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab3MinQty">
                                                            Slab 3 Min Quantity
                                                        </label>
                                                        <input type="number" id="slab3MinQty"
                                                               class="form-control slab3MinQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab3MinQty" placeholder="Slab 3 Min Quantity"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab3SchemeQty">
                                                            Slab 3 Scheme Quantity
                                                        </label>
                                                        <input type="number" id="slab3SchemeQty"
                                                               class="form-control slab3SchemeQty"
                                                               onblur="setTwoNumberDecimal" value ="0"
                                                               name="slab3SchemeQty" placeholder="Slab 3 Scheme Quantity"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab3BulkStatus">
                                                            Slab 3 Bulk Status
                                                        </label>
                                                        <input type="number" id="slab3BulkStatus"
                                                               class="form-control slab3BulkStatus" value ="0"
                                                               name="slab3BulkStatus" placeholder="Slab 3 Bulk Status"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slab3Status">
                                                            Slab 3 Status
                                                        </label>
                                                        <input type="number" id="slab3Status"
                                                               class="form-control slab3Status" value ="0"
                                                               name="slab3Status" placeholder="Slab 3 Status"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="card">
                                    <div class="header">
                                        <h6>OTHER INFORMATION</h6>
                                    </div>

                                    <div class="body">
                                        <div class="row">
                                            <div class="col-md-12 mt-2">
                                                <div class="row">
                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slabValidityFrom">
                                                            Slab Validity From
                                                        </label>
                                                        <input type="text" id="slabValidityFrom"
                                                               class="form-control slabValidityFrom date"
                                                               name="slabValidityFrom" placeholder="Slab Validity From"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="slabValidityTo">
                                                            Slab Validity to
                                                        </label>
                                                        <input type="text" id="slabValidityTo"
                                                               class="form-control slabValidityTo date"
                                                               name="slabValidityTo" placeholder=" Slab Validity to"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialDiscount">
                                                            Special Discount
                                                        </label>
                                                        <input type="text" id="specialDiscount"
                                                               class="form-control specialDiscount" value ="0" onblur="setTwoNumberDecimal()"
                                                               name="specialDiscount" placeholder="Special Discount"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialDiscountValidFrom">
                                                            Special Discount Valid From
                                                        </label>
                                                        <input type="text" id="specialDiscountValidFrom"
                                                               class="form-control specialDiscountValidFrom date"
                                                               name="specialDiscountValidFrom"
                                                               placeholder="Special Discount Valid From"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialDiscountValidTo">
                                                            Special Discount Valid to
                                                        </label>
                                                        <input type="text" id="specialDiscountValidTo"
                                                               class="form-control specialDiscountValidTo date"
                                                               name="specialDiscountValidTo"
                                                               placeholder="Special Discount Valid to"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialRate">
                                                            Special Rate
                                                        </label>
                                                        <input type="number" id="specialRate"
                                                               class="form-control specialRate" onblur="setTwoNumberDecimal()" value ="0"
                                                               name="specialRate" placeholder="Special Rate"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialRateValidFrom">
                                                            Special Rate Valid From
                                                        </label>
                                                        <input type="text" id="specialRateValidFrom"
                                                               class="form-control specialRateValidFrom date"
                                                               name="specialRateValidFrom"
                                                               placeholder="Special Rate Valid From"/>
                                                    </div>

                                                    <div class="col-lg-6 form-group  form-float">
                                                        <label for="specialRateValidTo">
                                                            Special Rate Valid to
                                                        </label>
                                                        <input type="text" id="specialRateValidTo"
                                                               class="form-control specialRateValidTo date"
                                                               name="specialRateValidTo"
                                                               placeholder="Special Rate Valid to"/>
                                                    </div>
                                                    <input type="hidden" name="schemeStatus" value="1">
                                                    <input type="hidden" class="hotRow" name="row" >
                                                    <input type="hidden" name="status" value="1">
                                                    <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                                    <input type="hidden" name="entityTypeId" value="${session.getAttribute('entityTypeId')}">
                                                    <input type="hidden" name="distributorId" value="0">
                                                    <input type="hidden" name="syncStatus" value="1">
                                                    <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                                    <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font
                                style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
                        <button type="button" class="btn btn-danger btn-simple btn-round waves-effect" data-dismiss="modal"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">CLOSE</font></font></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<g:include view="controls/sales/batch-detail.gsp"/>
<g:include view="controls/delete-modal.gsp"/>


<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/plugins/handsontable/handsontable.full.js"/>
%{--<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js"/>--}%
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.js"></script>--}%
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<script>

    var headerRow = [
        '<strong></strong>',
        '<strong>Product</strong>',
        '<strong>Batch</strong>',
        '<strong>Exp Dt</strong>',
        '<strong>Pur. Qty</strong>',
        '<strong>Free Qty</strong>',
        '<strong>Pur. Rate</strong>',
        '<strong>Sale Rate</strong>',
        '<strong>MRP</strong>',
        '<strong>Disc.(%)</strong>',
        '<strong>Pack</strong>',
        '<strong>GST.(%)</strong>',
        '<strong>GST</strong>',
        '<strong>Value</strong>',
        'SGST',
        'CGST',
        'IGST',
        'Manf. Date',
        'scheme',
        'tax_id'
    ];

    var batchHeaderRow = [
        '<strong>Batch</strong>',
        '<strong>Exp Dt</strong>',
        '<strong>Manf Dt</strong>',
        '<strong>Rem Qty</strong>',
        '<strong>Free Qty</strong>',
        '<strong>Pur. Rate</strong>',
        '<strong>Sale Rate</strong>',
        '<strong>MRP</strong>',
        '<strong>Pack</strong>',
        '<strong>GST%</strong>',
        'SGST',
        'CGST',
        'IGST',
        'id'];


    const batchContainer = document.getElementById('batchTable');
    var batchHot;
    var hot;
    var purchaseData = [];
    var batchData = [];
    var taxRegister = [];
    var mainTableRow = 0;
    var gst = 0;
    var cgst = 0;
    var sgst = 0;
    var igst = 0;
    var totalQty = 0;
    var totalFQty = 0;
    var remainingQty = 0;
    var remainingFQty = 0;
    var totalAmt = 0;
    var series = [];
    var products = [];
    var customers = [];
    var readOnly = false;
    var scheme = null;
    var stateId = null;
    $(document).ready(function () {
        window.localStorage.clear();
        console.log(localStorage)
        $("#supplier").select2();
        $('#date').val(moment().format('YYYY-MM-DD'));
        $('#date').attr("readonly");
        <g:each in="${customers}" var="cs">
        customers.push({"id": ${cs.id}, "noOfCrDays": ${cs.noOfCrDays}});
        </g:each>
        <g:each in="${taxRegister}" var="tr">
        taxRegister.push({"id": '${tr.id+"|"+tr.taxValue}', "text": '${tr.taxName+" | "+tr.taxValue}'});
        </g:each>
        supplierChanged();
        const container = document.getElementById('purchaseTable');
        hot = new Handsontable(container, {
            data: purchaseData,
            minRows: 1,
            height: '250',
            width: 'auto',
            rowHeights: 25,
            manualRowResize: true,
            manualColumnResize: true,
            persistentState: true,
            contextMenu: false,
            rowHeaders: true,
            colHeaders: headerRow,
            columns: [
                {type: 'text'},
                {
                    editor: 'select2',
                    renderer: productsDropdownRenderer,
                    select2Options: {
                        data: products,
                        dropdownAutoWidth: true,
                        allowClear: true,
                        width: '0'
                    }
                },
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'text'},
                {type: 'text'},
                {type: 'text', readOnly: true},
                {type: 'text'},
                {type: 'text'},
                {
                    editor: 'select2',
                    renderer: taxRegisterDropdownRenderer,
                    select2Options: {
                        data: taxRegister,
                        dropdownAutoWidth: true,
                        allowClear: true,
                        width: '0'
                    }
                },
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true}
            ],
            hiddenColumns: true,
            hiddenColumns: {
                // specify columns hidden by default
                columns: [19]
            },
            minSpareRows: 0,
            minSpareColumns: 0,
            enterMoves: {row: 0, col: 1},
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation',
            afterChange: (changes, source) => {
                if (changes) {
                    changes.forEach(([row, prop, oldValue, newValue]) => {
                        if (prop === 1) //first col product dropdown
                        {
                            mainTableRow = row;
                            batchSelection(newValue, row);
                        }
                    });
                }
            },
            afterOnCellMouseDown: function (e, coords, TD) {
                if (coords.col === 0) {
                    var id = hot.getDataAtCell(coords.row, 15);
                    deleteTempStockRow(id, coords.row);
                    calculateTotalAmt();
                }

                if (coords.col === 18) {
                    var tId = hot.getDataAtCell(coords.row, 19);
                    addScheme(tId,coords.row);
                }
            },

            cells: function (row, col) {
                const cellPrp = {};
                if (col === 0) {
                    cellPrp.readOnly = true;
                    cellPrp.renderer = function (
                        instance,
                        td,
                        row,
                        col,
                        prop,
                        value,
                        cellProperties
                    ) {
                        Handsontable.renderers.TextRenderer.apply(this, arguments);
                        td.innerHTML = '<button class="btn-danger" style="margin: 2px;">Delete</button>';
                    };
                }

                if(col === 18)
                {
                    cellPrp.readOnly = true;
                    cellPrp.renderer = function (
                        instance,
                        td,
                        row,
                        col,
                        prop,
                        value,
                        cellProperties
                    ) {
                        Handsontable.renderers.TextRenderer.apply(this, arguments);
                        var pid;
                        var batch;
                        if(row!=='' || row!== null)
                        {
                            if(hot!==undefined)
                            {
                                // console.log(hot.getDataAtCell(row,1))
                                pid = hot.getDataAtCell(row,1);
                                batch = hot.getDataAtCell(row,2);
                                if(pid==null)
                                {
                                    pid=""
                                }
                                if(batch==null)
                                {
                                    batch=''
                                }
                            }
                            // var batch = hot.getDataAtCell(row,2);
                            td.innerHTML =
                                '<button class="btn-success schBtn"  id="'+"sch"+pid+batch+'" style="margin: 2px;">+</button>';
                        }

                    };
                }
                return cellPrp;
            },


        });
        hot.selectCell(0, 1);
        hot.updateSettings({
            beforeKeyDown(e) {
                var pRate = 0;
                var sQty = 0;
                var fQty = 0;
                const row = hot.getSelected()[0][0];
                const selection = hot.getSelected()[0][1];
                if (selection === 11) {
                    if (e.key === 'Delete' || e.key === 'Backspace' || e.keyCode === 27) {
                        e.stopImmediatePropagation();
                    }
                }
                if (selection === 1) {
                    if (e.keyCode === 13) {
                        batchHot.selectCell(0, 0);
                        $("#batchTable").focus();
                    }
                } else if (selection === 0) {
                    if (e.keyCode === 13)
                        deleteTempStockRow("", row);
                } else if (selection === 18) {
                    if ((e.keyCode === 13 || e.keyCode === 9) && !readOnly) {
                        //check if sqty is empty
                        var sqty = hot.getDataAtCell(row, 4);
                        var fqty = hot.getDataAtCell(row, 5);
                        var taxslab = hot.getDataAtCell(row, 11);
                        if (sqty && sqty > 0 && taxslab !== "") {
                            var batchId = hot.getCellMeta(row, 2)?.batchId; //batch
                            var dt = hot.getDataAtRow(row);
                            dt.push(batchId);
                            console.log("Data added");
                            for (var j = 0; j < 17; j++) {
                                hot.setCellMeta(row, j, 'readOnly', true);
                                // hot.setCellMeta(row,j,'disableVisualSelection', true)
                            }
                            //
                            if(selection === 18)
                            {
                                addScheme("",row)
                            }
                                // Swal.fire({
                                //     title: 'Are you sure?',
                                //     text: "Do you want to enter a scheme for this product?",
                                //     showCancelButton: true,
                                //     confirmButtonColor: '#3085d6',
                                //     cancelButtonColor: '#d33',
                                //     confirmButtonText: 'Yes'
                                // }).then((result) => {
                                //     if (result.isConfirmed) {
                                //         $("#addSchemeModal").modal("show");
                                //         $('#addSchemeModal').modal({
                                //             backdrop: 'static',
                                //             keyboard: false
                                //         });
                                //         $("#schemeForm").submit(function(e){
                                //             e.preventDefault();
                                //             const data = new FormData(e.target);
                                //             const formJSON = Object.fromEntries(data.entries());
                                //             formJSON.productId = hot.getDataAtCell(row, 1);
                                //             formJSON.batch = hot.getDataAtCell(row, 2);
                                //             $("#addSchemeModal").modal("hide");
                                //         });
                                //         mainTableRow = row + 1;
                                //         calculateTotalAmt();
                                //         hot.alter('insert_row');
                                //         hot.selectCell(mainTableRow, 0);
                                //     }
                                //     else
                                //     {
                                //         mainTableRow = row + 1;
                                //         calculateTotalAmt();
                                //         hot.alter('insert_row');
                                //         hot.selectCell(mainTableRow, 0);
                                //     }
                                // });

                            mainTableRow = row + 1;
                            calculateTotalAmt();
                            hot.alter('insert_row');
                            hot.selectCell(mainTableRow, 0);

                        } else {
                            if (sqty <= 0) {
                                alert("Invalid Quantity, please enter quantity greater than 0");
                            }

                            if (taxslab === "") {
                                alert("Please select any tax slab");
                            }
                        }

                    }
                } else if (selection === 4 || selection === 5 || selection === 8 || selection === 6 || selection ===
                    9 || selection === 11) {
                    if (e.keyCode === 13 || e.keyCode === 9) {
                        var discount = 0;
                        if (selection === 6) {
                            var mrp = hot.getDataAtCell(row, 8);
                            var oldSaleRate = hot.getDataAtCell(row, 6);
                            var saleRate = Number(this.getActiveEditor().TEXTAREA.value);
                            if (saleRate > mrp) {
                                hot.setDataAtCell(row, 6, oldSaleRate);
                                this.getActiveEditor().TEXTAREA.value = oldSaleRate;
                                alert("Purchase Rate exceeds MRP!");
                            } else {
                                hot.setDataAtCell(row, 6, Number(this.getActiveEditor().TEXTAREA.value));
                                this.selectCell(row, selection + 1);
                            }
                        }
                        if (selection === 4) {
                            this.getActiveEditor().enableFullEditMode();
                            this.getActiveEditor().beginEditing();
                            sQty = Number(this.getActiveEditor().TEXTAREA.value);
                            // var sq = this.getDataAtCell(row,4);
                            // alert(sq)
                            this.setDataAtCell(row, 4, sQty);
                            this.selectCell(row, selection + 1);
                        } else {
                            sQty = Number(this.getDataAtCell(row, 4));
                        }

                        if (selection === 5) {
                            fQty = Number(this.getActiveEditor().TEXTAREA.value);
                            hot.setDataAtCell(row, 5, fQty);
                            this.selectCell(row, selection + 1);
                        } else {
                            fQty = Number(this.getDataAtCell(row, 5));
                        }

                        if (selection === 9) {
                            discount = Number(this.getActiveEditor().TEXTAREA.value);
                            if (discount > 100) {
                                alert("Invalid Discount");
                                hot.setDataAtCell(row, 9, 0);
                                this.getActiveEditor().TEXTAREA.value = 0;
                                hot.selectCell(row, 9);
                                return;
                            } else {
                                hot.setDataAtCell(row, 9, discount);
                                this.selectCell(row, selection + 1);
                            }
                        } else {
                            discount = hot.getDataAtCell(row, 9);
                        }

                        var allowEntry = false;
                        var pid = hot.getDataAtCell(row, 1);
                        var batch = hot.getDataAtCell(row, 2);
                        var batchId = hot.getCellMeta(row, 2)?.batchId;
                        var remQty = 0;
                        var remFQty = 0;
                        var freeQtyEntry = false;
                        if (pid && batch && batchId !== "new") {
                            $.ajax({
                                    type: "POST",
                                    url: "/stockbook/product/" + pid + "/batch/" + batch,
                                    dataType: 'json',
                                    success: function (data) {
                                        /* remQty = remQty + data.remainingQty;
                                         remFQty = remFQty + data.remainingFreeQty;
                                         if (remQty >= sQty) {
                                             allowEntry = true;
                                         }
                                         else if (sQty >= remQty && remFQty >= sQty) {
                                             allowEntry = true;
                                         }

                                         else if ((remQty + remFQty) >= sQty) {
                                             allowEntry = true;
                                         }

                                         if(selection === 5)
                                         {
                                             if(remFQty >= fQty)
                                             {
                                                 freeQtyEntry = true;
                                             }

                                             else if ((remQty + remFQty) >= sQty+fQty) {
                                                 freeQtyEntry = true;
                                                 allowEntry = true;
                                             }
                                             else
                                             {
                                                 freeQtyEntry = false;
                                                 allowEntry = false;
                                             }

                                             if(freeQtyEntry!==true)
                                             {
                                                 // hot.setDataAtCell(row, 5, 0);
                                                 alert("Entered Free quantity exceeds available quantity");
                                             }
                                         }
                                         if (!allowEntry) {
                                             // this.getActiveEditor().TEXTAREA.value = "";
                                             hot.setDataAtCell(row, 4, 0);
                                             hot.setDataAtCell(row, 5, 0);
                                             hot.setDataAtCell(row, 11, 0);
                                             hot.setDataAtCell(row, 12, 0);
                                             hot.setDataAtCell(row, 13, 0);
                                             hot.setDataAtCell(row, 14, 0);
                                             alert("Entered quantity exceeds available quantity");
                                             return;
                                         }
                                         else
                                         {
                                             hot.setDataAtCell(row,5,fQty)
                                         }*/
                                    },
                                    error: function (data) {
                                        alert("Something went Wrong!")
                                    }
                                }
                            );
                        }
                        // applySchemes(row, sQty);
                        if (selection === 6) {
                            pRate = Number(this.getActiveEditor().TEXTAREA.value);
                            if (pRate === 0) {
                                pRate = hot.getDataAtCell(row, 6);
                            }
                        } else {
                            pRate = hot.getDataAtCell(row, 6);
                        }

                        var value = pRate * sQty;
                        var priceBeforeGst = value - (value * discount / 100);
                        var finalPrice = priceBeforeGst + (priceBeforeGst * (Number(gst) / 100));
                        hot.setDataAtCell(row, 13, Number(finalPrice).toFixed(2));
                        var gstAmount;
                        var supplierState = $('#supplier').find(':selected').data('state');
                        if (supplierState === ${session.getAttribute('stateId')}) {
                            if (gst !== 0) {
                                gstAmount = priceBeforeGst * (gst / 100);
                                var sgstAmount = priceBeforeGst * (sgst / 100);
                                var cgstAmount = priceBeforeGst * (cgst / 100);
                                hot.setDataAtCell(row, 12, Number(gstAmount).toFixed(2)); //GST
                                hot.setDataAtCell(row, 14, Number(sgstAmount).toFixed(2)); //SGST
                                hot.setDataAtCell(row, 15, Number(cgstAmount).toFixed(2)); //CGST
                            } else {
                                hot.setDataAtCell(row, 12, 0); //GST
                                hot.setDataAtCell(row, 14, 0); //SGST
                                hot.setDataAtCell(row, 15, 0); //CGST
                            }
                            hot.setDataAtCell(row, 16, 0);
                            // if (igst !== "0") {
                            //     var igstAmount = priceBeforeGst * (igst / 100);
                            //     hot.setDataAtCell(row, 16, Number(igstAmount).toFixed(2)); //IGST
                            // } else{hot.setDataAtCell(row, 16, 0);}
                        } else {
                            //setting up igst
                            gstAmount = priceBeforeGst * (gst / 100);
                            hot.setDataAtCell(row, 12, gstAmount.toFixed(2)); //GST
                            hot.setDataAtCell(row, 14, 0); //SGST
                            hot.setDataAtCell(row, 15, 0); //CGST
                            hot.setDataAtCell(row, 16, gstAmount.toFixed(2))
                        }
                        if (selection === 11) {
                            this.selectCell(row, selection + 1);
                        }
                    }

                }
            }

        });

        hot.addHook('afterSelection', (row, col) => {
            if (col === 2) {
                batchSelection(hot.getDataAtCell(row, 1), row, false);
            }
        });

        stateId = $('#supplier option:selected').attr('data-state');
        $('#supplier').change(function () {
            stateId = $('#supplier option:selected').attr('data-state');
        });

        function productsDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < products.length; index++) {
                if (parseInt(value) === products[index].id) {
                    selectedId = products[index].id;
                    value = products[index].text;
                }
            }
            Handsontable.renderers.TextRenderer.apply(this, arguments);
        }

        function taxRegisterDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            var taxId;
            for (var index = 0; index < taxRegister.length; index++) {
                if (value === taxRegister[index].id) {
                    selectedId = taxRegister[index].id;
                    value = taxRegister[index].text;
                }
            }
            Handsontable.renderers.TextRenderer.apply(this, arguments);
            if (selectedId !== undefined) {
                taxId = selectedId.split('|');
                $.ajax({
                    type: "POST",
                    url: "/tax/showtax/" + taxId[0].trim(),
                    dataType: 'json',
                    success: function (data) {
                        const row = hot.getSelected()[0][0];
                        hot.setDataAtCell(row, 11, Number(taxId[1]).toFixed(2));
                        hot.setDataAtCell(row, 19, taxId[0].trim());
                        var pR = hot.getDataAtCell(row, 6);
                        var sq = hot.getDataAtCell(row, 4);
                        var disc = hot.getDataAtCell(row, 9);
                        const selection = hot.getSelected()[0][1];
                        hot.selectCell(row, selection + 1);
                        var value = pR * sq;
                        var priceBeforeGst = value - (value * disc / 100);
                        gst = taxId[1];
                        sgst = data.purchaseSgst;
                        cgst = data.purchaseCgst;
                        var finalPrice = priceBeforeGst + (priceBeforeGst * (gst / 100));
                        hot.setDataAtCell(row, 13, Number(finalPrice).toFixed(2));
                        var gstAmount;
                        if (stateId === '${session.getAttribute('stateId')}') {
                            if (taxId[1] !== 0) {
                                gstAmount = priceBeforeGst * (gst / 100);
                                var sgstAmount = priceBeforeGst * (data.purchaseSgst / 100);
                                var cgstAmount = priceBeforeGst * (data.purchaseCgst / 100);
                                hot.setDataAtCell(row, 12, Number(gstAmount).toFixed(2)); //GST
                                hot.setDataAtCell(row, 14, Number(sgstAmount).toFixed(2)); //SGST
                                hot.setDataAtCell(row, 15, Number(cgstAmount).toFixed(2)); //CGST
                                calculateTotalAmt();
                            } else {
                                hot.setDataAtCell(row, 12, 0); //GST
                                hot.setDataAtCell(row, 14, 0); //SGST
                                hot.setDataAtCell(row, 15, 0); //CGST
                            }
                        } else {
                            // hot.setDataAtCell(row, 12, 0); //GST
                            hot.setDataAtCell(row, 14, 0); //SGST
                            hot.setDataAtCell(row, 15, 0); //CGST
                            // if (data.purchaseIgst !== 0) {
                            //     var igstAmount = priceBeforeGst * (data.purchaseIgst / 100);
                            //     hot.setDataAtCell(row, 16, Number(igstAmount).toFixed(2)); //IGST
                            //     calculateTotalAmt();
                            // } else
                            // {
                            //     hot.setDataAtCell(row, 16, 0);
                            // }
                            gstAmount = priceBeforeGst * (gst / 100);
                            hot.setDataAtCell(row, 16, gstAmount.toFixed());
                        }
                    },
                    error: function (data) {
                        alert("Something went Wrong!")
                    }
                });
            }
        }

        batchHot = new Handsontable(batchContainer, {
            data: batchData,
            minRows: 1,
            height: '120',
            width: 'auto',
            rowHeights: 25,
            manualRowResize: true,
            manualColumnResize: true,
            persistentState: true,
            contextMenu: true,
            rowHeaders: true,
            selectionMode: 'range',
            colHeaders: batchHeaderRow,
            columns: [
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true}
            ],
            hiddenColumns: true,
            hiddenColumns: {
                // specify columns hidden by default
                columns: [10, 11, 12, 13]
            },
            minSpareRows: 0,
            minSpareCols: 0,
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation'
        });


        batchHot.updateSettings({
            beforeKeyDown(e) {
                const selection = batchHot.getSelected()[0][0];
                var rowData = batchHot.getDataAtRow(selection);
                if (e.keyCode === 13) {

                    if (!checkForDuplicateEntry(rowData[0])) {
                        //check for schemes
                        // checkSchemes(hot.getDataAtCell(mainTableRow, 1), rowData[0]); //product, batch
                        var batchId = rowData[13];
                        hot.setDataAtCell(mainTableRow, 2, rowData[0]);
                        hot.setCellMeta(mainTableRow, 2, "batchId", batchId);
                        hot.setDataAtCell(mainTableRow, 3, rowData[1]);
                        hot.setDataAtCell(mainTableRow, 5, 0);
                        hot.setDataAtCell(mainTableRow, 6, rowData[5]);
                        hot.setDataAtCell(mainTableRow, 7, rowData[6]);
                        hot.setDataAtCell(mainTableRow, 8, rowData[7]);
                        hot.setDataAtCell(mainTableRow, 9, 0);
                        hot.setDataAtCell(mainTableRow, 10, rowData[8]);
                        hot.setDataAtCell(mainTableRow, 11, rowData[9]?.toFixed(2));
                        hot.setDataAtCell(mainTableRow, 17, rowData[2]);
                        gst = rowData[9];
                        sgst = rowData[10];
                        cgst = rowData[11];
                        igst = rowData[12];
                        hot.selectCell(mainTableRow, 4);
                        hot.setDataAtCell(mainTableRow, 18, 0);
                        hot.setDataAtCell(mainTableRow, 19, sgst);
                        hot.setDataAtCell(mainTableRow, 20, cgst);
                        hot.setDataAtCell(mainTableRow, 21, igst);
                        remainingQty = rowData[3];
                        remainingFQty = rowData[4];
                        $("#purchaseTable").focus();
                    } else {
                        alert("Selected product and batch already entered, duplicate entries not allowed");
                    }
                }
            }


        });

        $('#series').trigger('change');
    });

    function batchSelection(selectedId, mainRow, selectCell = true) {
        if (selectedId != null) {
            var url = "/stockbook/purchase/product/" + selectedId;
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'json',
                success: function (data) {
                    if (data) {
                        batchData = [];
                        for (var i = 0; i < data.length; i++) {
                            var batchdt = [];
                            batchdt.push(data[i].batchNumber);
                            batchdt.push(data[i].expDate.split("T")[0]);
                            batchdt.push(data[i].manufacturingDate.split("T")[0]);
                            batchdt.push(data[i].remainingQty);
                            batchdt.push(data[i].remainingFreeQty);
                            batchdt.push(data[i].purchaseRate);
                            batchdt.push(data[i].saleRate);
                            batchdt.push(data[i].mrp);
                            batchdt.push(data[i].packingDesc);
                            batchdt.push(data[i].gst);
                            batchdt.push(data[i].sgst);
                            batchdt.push(data[i].cgst);
                            batchdt.push(data[i].igst);
                            if (data[i].id == null)
                                batchdt.push("new");
                            else
                                batchdt.push(data[i].id);
                            batchData.push(batchdt);
                            console.log(batchData)
                        }
                        batchHot.updateSettings({
                            data: []
                        });
                        if (batchdt?.length > 0) {
                            batchHot.loadData(batchData);
                            if (selectCell) {
                                $("#batchTable").focus();
                                batchHot.selectCell(0, 0);
                            }
                        }
                    }
                },
                error: function (data) {
                    console.log("Failed");
                }
            });
        }
    }

    function supplierChanged() {
        var noOfCrDays = 0;
        var customerId = $("#supplier").val();
        for (var i = 0; i < customers.length; i++) {
            if (customerId === customers[i].id) {
                noOfCrDays = customers[i].noOfCrDays;
            }
        }
        $('#duedate').prop("readonly", false);
        $("#duedate").val(moment().add(noOfCrDays, 'days').format('YYYY-MM-DD'));
        $('#duedate').prop("readonly", true);

        calculateTaxes();
    }

    function calculateTotalAmt() {
        totalAmt = 0;
        totalGst = 0;
        totalCgst = 0;
        totalSgst = 0;
        totalIgst = 0;
        totalQty = 0;
        totalFQty = 0;
        var data = hot.getData();
        for (var i = 0; i < data.length; i++) {
            if (data[i][4])
                totalQty += Number(data[i][4]);
            if (data[i][5])
                totalFQty += Number(data[i][5]);
            if (data[i][13])
                totalAmt += Number(data[i][13]);
            if (data[i][12])
                totalGst += Number(data[i][12]);
            if (data[i][14])
                totalSgst += Number(data[i][14]);
            if (data[i][15])
                totalCgst += Number(data[i][15]);
            if (data[i][16])
                totalIgst += Number(data[i][16]);
        }
        $("#totalAmt").text(totalAmt.toFixed(2));
        $("#totalGST").text(totalGst.toFixed(2));
        $("#totalSGST").text(totalSgst.toFixed(2));
        $("#totalCGST").text(totalCgst.toFixed(2));
        $("#totalIGST").text(totalIgst.toFixed(2));
        $("#totalQty").text(totalQty.toFixed(2));
        $("#totalFQty").text(totalFQty.toFixed(2));
    }


    function checkForDuplicateEntry(batchNumber) {
        var productId = hot.getDataAtCell(mainTableRow, 1);
        var purchaseTableData = hot.getData();
        for (var i = 0; i < purchaseTableData.length; i++) {
            if (productId === purchaseTableData[i][1]) {
                if (purchaseTableData[i][2] !== null && purchaseTableData[i][2] === batchNumber)
                    return true;
            }
        }
        return false;
    }

    function loadTempStockBookData() {
        /*  var userId = "
        ${session.getAttribute("userId")}";
        $.ajax({
            type: "GET",
            url: "tempstockbook/user/"+userId,
            dataType: 'json',
            success: function (data) {
                purchaseData = data;

                for(var i = 0; i < purchaseData.length; i++) {
                    hot.selectCell(i, 1);
                    var sRate = purchaseData[i]["saleRate"];
                    var sQty = purchaseData[i]["userOrderQty"];
                    batchSelection(purchaseData[i]["productId"], null, false);
                    var batchId = purchaseData[i][12];
                    hot.setDataAtCell(i, 1, purchaseData[i]["productId"]);

                    hot.setDataAtCell(i, 2, purchaseData[i]["batchNumber"]);
                    hot.setCellMeta(i, 2, "batchId", batchId);

                    hot.setDataAtCell(i, 3, purchaseData[i]["expDate"].split("T")[0]);
                    hot.setDataAtCell(i, 5, 0);
                    hot.setDataAtCell(i, 6, sRate);
                    hot.setDataAtCell(i, 4, sQty);
                    hot.setDataAtCell(i, 7, purchaseData[i]["mrp"]);
                    hot.setDataAtCell(i, 8, 0);
                    hot.setDataAtCell(i, 9, purchaseData[i]["packingDesc"]);
                    gst = purchaseData[i]["gst"];
                    sgst = purchaseData[i]["sgst"];
                    cgst = purchaseData[i]["cgst"];
                    igst = purchaseData[i]["igst"];

                    // var discount = hot.getDataAtCell(i, 8);
                    var discount = 0; //TODO: discount to be set
                    //var gst = hot.getDataAtCell(row, 10);
                    var priceBeforeGst = (sRate * sQty) - ((sRate * sQty) * discount) / 100;
                    var finalPrice = priceBeforeGst + (priceBeforeGst * (gst / 100));
                    hot.setDataAtCell(i, 11, finalPrice);

                    if(gst != 0) {
                        hot.setDataAtCell(i, 10, priceBeforeGst * (gst / 100)); //GST
                        hot.setDataAtCell(i, 12, priceBeforeGst * (sgst / 100)); //SGST
                        hot.setDataAtCell(i, 13, priceBeforeGst * (cgst / 100)); //CGST
                    }
                    else
                    {
                        hot.setDataAtCell(i, 10, 0); //GST
                        hot.setDataAtCell(i, 12, 0); //SGST
                        hot.setDataAtCell(i, 13, 0); //CGST
                    }
                    if(igst != "0")
                        hot.setDataAtCell(i, 14, priceBeforeGst*(igst/100)); //IGST
                    else
                        hot.setDataAtCell(i, 14, 0);

                    hot.setDataAtCell(i, 15, purchaseData[i].id)
                }

                setTimeout(function () {
                    hot.selectCell(0, 1);
                    calculateTotalAmt();
                },1000);

            }
        })*/
    }

    function deleteTempStockRow(id, row) {
        if (!readOnly) {
            /*  if(id) {
                  $.ajax({
                      type: "POST",
                      url: "tempstockbook/delete/" + id,
                      dataType: 'json',
                      success: function (data) {
                          hot.alter("remove_row", row);
                          swal("Success", "Row Deleted", "").fire();
                      }
                  });
              }
              else*/
            var productId = hot.getDataAtCell(row,1);
            var batch = hot.getDataAtCell(row,2);
            if(localStorage.getItem(productId+"-"+batch)!=null){
                localStorage.removeItem(productId+"-"+batch)
            }
            hot.alter("remove_row", row);
            hot.selectCell(row, 0);
        } else
            alert("Can't change this now, invoice has been saved already.")
    }


    function addScheme(id,row){
        alert(id);
       if(id==='' || id=== null)
       {
           Swal.fire({
               title: 'Are you sure?',
               text: "Do you want to enter a scheme for this product?",
               showCancelButton: true,
               confirmButtonColor: '#3085d6',
               cancelButtonColor: '#d33',
               confirmButtonText: 'Yes'
           }).then((result) => {
               if (result.isConfirmed) {
                   $("#addSchemeModal").modal("show");
                   $('.hotRow').val(row);
                   $('.date').bootstrapMaterialDatePicker({
                       format: 'DD/MM/YYYY',
                       clearButton: true,
                       time: false,
                       weekStart: 1
                   });
                   jQuery("#schemeForm").submit(function(e){
                       e.preventDefault();
                       $("#addSchemeModal").modal("hide");
                       const data = new FormData(e.target);
                       const formJSON = Object.fromEntries(data.entries());
                       var productId = hot.getDataAtCell(formJSON?.row, 1);
                       var batch = hot.getDataAtCell(formJSON?.row, 2);
                       formJSON.productId = productId;
                       formJSON.batch = batch;
                       formJSON.customerIds = $("#supplier").val();
                       if(formJSON?.batch!==null && formJSON?.productId!==null)
                       {
                           localStorage.setItem(formJSON.productId+"-"+formJSON.batch,JSON.stringify(formJSON));
                           // console.log("sch"+productId+batch === "sch115CB21003");
                           // console.log( $("#sch"+productId+batch));

                           $('#sch'+productId+batch).text("added");
                       }
                       return true;
                   });
                   // mainTableRow = row + 1;
                   // calculateTotalAmt();
                   // hot.alter('insert_row');
                   // hot.selectCell(mainTableRow, 0);
                   // console.log("nothing!")
               }
               else
               {
                   console.log("nothing!")
               }
           });
       }
       else
       {
           $("#addSchemeModal").modal("show");
       }
    }

    var purchasebillid = 0;

    function savePurchaseInvoice(billStatus) {
        var waitingSwal = Swal.fire({
            title: "Generating Purchase Invoice, Please wait!",
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false
        });

        var supplierBillId = $("#supplierBillId").val();
        var supplierBillDate = $("#supplierBillDate").val();
        if (supplierBillId?.length === 0 || supplierBillDate?.length === 0) {
            Swal.fire({
                title: "Please enter supplier invoice number and date",
                showDenyButton: false,
                showCancelButton: false,
                showConfirmButton: true,
                allowOutsideClick: false
            });
            return;
        }
        var supplier = $("#supplier").val();
        var series = $("#series").val();
        var seriesCode = $("#series").find(':selected').data('seriescode');
        var duedate = $("#duedate").val();
        duedate = moment(duedate, 'YYYY-MM-DD').toDate();
        duedate = moment(duedate).format('DD/MM/YYYY');

        supplierBillDate = moment(supplierBillDate, 'YYYY-MM-DD').toDate();
        supplierBillDate = moment(supplierBillDate).format('DD/MM/YYYY');
        var schemeData =[];
        var keys = Object.keys(localStorage);
        keys.forEach((e) =>{
            console.log(JSON.parse(localStorage.getItem(e.toString())));
            schemeData.push(JSON.parse(localStorage.getItem(e.toString())));
        });
        var priority = $("#priority").val();

        if (!series) {
            alert("Please select series.");
            waitingSwal.close();
            return;
        }

        if (!supplier) {
            alert("Please select supplier.");
            waitingSwal.close();
            return;
        }

        var purchaseData = JSON.stringify(hot.getSourceData());

        $.ajax({
            type: "POST",
            url: "/purchase-entry",
            dataType: 'json',
            data: {
                purchaseData: purchaseData,
                supplier: supplier,
                series: series,
                duedate: duedate,
                priority: priority,
                billStatus: billStatus,
                seriesCode: seriesCode,
                schemeData: JSON.stringify(schemeData),
                supplierBillDate: supplierBillDate,
                supplierBillId: supplierBillId,
                uuid: self.crypto.randomUUID()
            },
            success: function (data) {
                console.log(data);
                readOnly = true;
                var rowData = hot.getData();
                for (var j = 0; j < rowData.length; j++) {
                    for (var i = 0; i < 16; i++) {
                        hot.setCellMeta(j, i, 'readOnly', true);
                    }
                }
                purchasebillid = data.purchaseBillDetail.id;
                var datepart = data.purchaseBillDetail.entryDate.split("T")[0];
                var month = datepart.split("-")[1];
                var year = datepart.split("-")[0];
                var seriesCode = data.series.seriesCode;
                var invoiceNumber = data.purchaseBillDetail.invoiceNumber;
                $("#invNo").html("<p><strong>" + invoiceNumber + "</strong></p>");
                var message = "";
                var draftInvNo = "";
                if (billStatus === "DRAFT") {
                    draftInvNo = '<p><strong>' + data.purchaseBillDetail.entityId + "/DR/S/" + month + year + "/"
                        + seriesCode + "/__" + '<p><strong>';
                    $("#invNo").html(draftInvNo);
                }
                if (billStatus !== "DRAFT") {
                    message = 'Purchase Invoice Generated: ' + invoiceNumber;
                } else {
                    message = 'Draft Invoice Generated: ' + data.purchaseBillDetail.entityId + "/DR/S/" + month + year + "/"
                        + seriesCode + "/__";
                }
                waitingSwal.close();
                Swal.fire({
                    title: message,
                    showDenyButton: true,
                    showCancelButton: false,
                    confirmButtonText: 'Print',
                    denyButtonText: 'New Entry',
                }).then((result) => {
                    if (result.isConfirmed) {
                        printInvoice();
                    } else if (result.isDenied) {
                        resetData();
                        location.reload();
                    }
                });


            },
            error: function () {
                waitingSwal.close();
                Swal.fire({
                    title: "Unable to generate Invoice at the moment.",
                    confirmButtonText: 'OK'
                });
            }
        });

    }

    function printInvoice() {
        if (readOnly) {
            window.open(
                '/purchase-entry/print-order?id=' + purchasebillid,
                '_blank'
            );
            resetData();
        }
    }

    function resetPage() {
        Swal.fire({
            title: "Reset Contents?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: 'OK',
        }).then((result) => {
            if (result) {
                resetData();
            }
        });
    }

    function resetData() {
        $("#invNo").html("");
        purchaseData.length = 0;
        batchData.length = 0;
        mainTableRow = 0;
        gst = 0;
        cgst = 0;
        sgst = 0;
        igst = 0;
        totalQty = 0;
        totalFQty = 0;
        remainingQty = 0;
        remainingFQty = 0;
        totalAmt = 0;
        readOnly = false;
        scheme = null;

        batchHot.updateSettings({
            data: []
        });
        hot.updateSettings({
            data: []
        });

        calculateTotalAmt();
    }

    function seriesChanged() {
        var series = $("#series").val();
        loadProducts(series);

    }

    function loadProducts(series) {
        products.length = 0;//remove all elements
        $.ajax({
            type: "GET",
            url: "/product/series/" + series,
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    products.push({id: data[i].id, text: data[i].productName});
                }
                loadTempStockBookData();
            },
            error: function () {
                products.length = 0; //remove all elements
            }
        });
    }

    function checkSchemes(productId, batchNumber) {
        scheme = null;
        $.ajax({
            type: "GET",
            url: "/sales/check-scheme",
            data: {
                productId: productId,
                batchNumber: batchNumber
            },
            dataType: 'json',
            success: function (data) {
                scheme = data;
                var offers = "";

                if (data.slab1Status === 1) {
                    offers = "S1: " + data.slab1MinQty + "+" + data.slab1SchemeQty;
                }

                if (data.slab2Status === 1) {
                    offers += " | S2: " + data.slab2MinQty + "+" + data.slab2SchemeQty;
                }

                if (data.slab3Status === 1) {
                    offers += " | S3: " + data.slab3MinQty + "+" + data.slab3SchemeQty;
                }

                $("#offers").html(offers)
            },
            error: function () {
                $("#offers").html("");
            }
        });
    }

    function applySchemes(row, saleQty) {
        if (scheme && saleQty > 0) {
            if (saleQty >= scheme.slab1MinQty && saleQty < scheme.slab2MinQty) {
                if (scheme.slab1Status == 1) {
                    if (scheme.slab2BulkStatus == 1) {
                        var slab1Multiplier = Math.floor(parseInt(saleQty) / scheme.slab1MinQty);
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;
                        hot.setDataAtCell(row, 5, slab1Qty);
                    } else {
                        hot.setDataAtCell(row, 5, scheme.slab1SchemeQty);
                    }
                } else
                    hot.setDataAtCell(row, 5, 0);
            } else if (saleQty >= scheme.slab2MinQty && saleQty < scheme.slab3MinQty) {
                if (scheme.slab2Status == 1) {
                    if (scheme.slab2BulkStatus == 1) {

                        var slab2Multiplier = Math.floor(parseInt(saleQty) / scheme.slab2MinQty);
                        var slab2Qty = slab2Multiplier * scheme.slab2MinQty;
                        var slb2RemQty = saleQty - slab2Qty;

                        var slab1Multiplier = Math.floor(parseInt(slb2RemQty) / scheme.slab1MinQty);
                        var slab2Qty = scheme.slab2SchemeQty * slab2Multiplier;
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;

                        hot.setDataAtCell(row, 5, slab2Qty + slab1Qty);
                    } else {
                        hot.setDataAtCell(row, 5, scheme.slab2SchemeQty);
                    }
                } else
                    hot.setDataAtCell(row, 5, 0);
            } else if (saleQty >= scheme.slab3MinQty) {
                if (scheme.slab3Status == 1) {
                    if (scheme.slab3BulkStatus == 1) {
                        var slab3Multiplier = Math.floor(parseInt(saleQty) / scheme.slab3MinQty);
                        var slab3Qty = slab3Multiplier * scheme.slab3MinQty;
                        var slb3RemQty = saleQty - slab3Qty;

                        var slab2Qty = 0;
                        var slb2RemQty = 0;
                        var slab2Multiplier = Math.floor(parseInt(slb3RemQty) / scheme.slab2MinQty);
                        if (slab2Multiplier > 0) {
                            slab2Qty = slab2Multiplier * scheme.slab2MinQty;
                            slb2RemQty = slb3RemQty - slab2Qty;
                        } else {
                            slb2RemQty = slb3RemQty;
                        }

                        var slab1Multiplier = Math.floor(parseInt(slb2RemQty) / scheme.slab1MinQty);

                        slab3Qty = scheme.slab3SchemeQty * slab3Multiplier;
                        slab2Qty = scheme.slab2SchemeQty * slab2Multiplier;
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;

                        hot.setDataAtCell(row, 5, slab3Qty + slab2Qty + slab1Qty);
                    } else {
                        hot.setDataAtCell(row, 5, scheme.slab3SchemeQty);
                    }
                } else
                    hot.setDataAtCell(row, 5, 0);
            }
        }
    }

    document.addEventListener("keydown", function (event) {
        var ctrl = event.ctrlKey;
        var alt = event.altKey;
        var key = event.key;
        if (ctrl) {
            if (alt) {
                var result = false;
                if (key === 'c') {
                    result = confirm("Delete this row?");
                    if (result) {
                        const selection = hot.getSelected()[0];
                        var id = hot.getDataAtCell(selection, 15);
                        deleteTempStockRow(id, selection);
                        calculateTotalAmt();
                    }
                }
                if (key === 'r') {
                    result = confirm("Clear and Reset table?");
                    if (result) {
                        batchHot.updateSettings({
                            data: []
                        });
                        hot.updateSettings({
                            data: []
                        });
                        calculateTotalAmt();
                    }
                }
            }

        }
    });


    function calculateTaxes() {
        if (hot == null)
            return;
        var data = hot.getData();
        for (var row = 0; row < data.length; row++) {
            var sgstAmount = Number(hot.getDataAtCell(row, 14));
            var cgstAmount = Number(hot.getDataAtCell(row, 15));
            var igstAmount = Number(hot.getDataAtCell(row, 16));

            var sgstPercentage = hot.getDataAtCell(row, 19);
            var cgstPercentage = hot.getDataAtCell(row, 20);

            if (stateId === '${session.getAttribute('stateId')}') {
                if (igstAmount !== 0) {
                    hot.setDataAtCell(row, 14, Number(igstAmount / 2).toFixed(2)); //SGST
                    hot.setDataAtCell(row, 15, Number(igstAmount / 2).toFixed(2)); //CGST
                    hot.setDataAtCell(row, 16, 0); //IGST

                    hot.setDataAtCell(row, 19, sgstPercentage);
                    hot.setDataAtCell(row, 20, cgstPercentage);
                    hot.setDataAtCell(row, 21, 0);
                }
            } else {
                if (sgstAmount !== 0 && cgstAmount !== 0) {
                    hot.setDataAtCell(row, 14, 0); //SGST
                    hot.setDataAtCell(row, 15, 0); //CGST
                    hot.setDataAtCell(row, 16, (sgstAmount + cgstAmount).toFixed(2)); //IGST

                    hot.setDataAtCell(row, 19, 0);
                    hot.setDataAtCell(row, 20, 0);
                    hot.setDataAtCell(row, 21, sgstPercentage + cgstPercentage);
                }
            }
        }
    }

    /// select2 plugin
    (function (Handsontable) {
        "use strict";

        var Select2Editor = Handsontable.editors.TextEditor.prototype.extend();
        Select2Editor.prototype.prepare = function (row, col, prop, td, originalValue, cellProperties) {
            Handsontable.editors.TextEditor.prototype.prepare.apply(this, arguments);
            this.options = {};
            if (this.cellProperties.select2Options) {
                this.options = $.extend(this.options, cellProperties.select2Options);
            }
        };

        Select2Editor.prototype.createElements = function () {
            this.$body = $(document.body);
            this.TEXTAREA = document.createElement('input');
            this.TEXTAREA.setAttribute('type', 'text');
            this.$textarea = $(this.TEXTAREA);

            Handsontable.dom.addClass(this.TEXTAREA, 'handsontableInput');

            this.textareaStyle = this.TEXTAREA.style;
            this.textareaStyle.width = 0;
            this.textareaStyle.height = 0;

            this.TEXTAREA_PARENT = document.createElement('DIV');
            Handsontable.dom.addClass(this.TEXTAREA_PARENT, 'handsontableInputHolder');

            this.textareaParentStyle = this.TEXTAREA_PARENT.style;
            this.textareaParentStyle.top = 0;
            this.textareaParentStyle.left = 0;
            this.textareaParentStyle.display = 'none';

            this.TEXTAREA_PARENT.appendChild(this.TEXTAREA);

            this.instance.rootElement.appendChild(this.TEXTAREA_PARENT);

            var that = this;
            this.instance._registerTimeout(setTimeout(function () {
                that.refreshDimensions();
            }, 0));
        };

        var onSelect2Changed = function () {
            this.close();
            this.finishEditing();
        };
        var onSelect2Closed = function () {
            this.close();
            this.finishEditing();
        };
        var onBeforeKeyDown = function (event) {
            var instance = this;
            var that = instance.getActiveEditor();

            var keyCodes = Handsontable.helper.KEY_CODES;
            var ctrlDown = (event.ctrlKey || event.metaKey) && !event.altKey; //catch CTRL but not right ALT (which in some systems triggers ALT+CTRL)


            //Process only events that have been fired in the editor
            if (!$(event.target).hasClass('select2-input') /*|| event?.isImmediatePropagationStopped()*/) {
                return;
            }
            if (event.keyCode === 17 || event.keyCode === 224 || event.keyCode === 91 || event.keyCode === 93) {
                //when CTRL or its equivalent is pressed and cell is edited, don't prepare selectable text in textarea
                event.stopImmediatePropagation();
                return;
            }

            var target = event.target;

            switch (event.keyCode) {
                case keyCodes.ARROW_RIGHT:
                    if (Handsontable.dom.getCaretPosition(target) !== target.value.length) {
                        event?.stopImmediatePropagation();
                    } else {
                        that.$textarea.select2('close');
                    }
                    break;

                case keyCodes.ARROW_LEFT:
                    if (Handsontable.dom.getCaretPosition(target) !== 0) {
                        event?.stopImmediatePropagation();
                    } else {
                        that.$textarea.select2('close');
                    }
                    break;

                case keyCodes.ENTER:
                    var selected = that.instance.getSelected();
                    var isMultipleSelection = !(selected[0] === selected[2] && selected[1] === selected[3]);
                    if ((ctrlDown && !isMultipleSelection) || event.altKey) { //if ctrl+enter or alt+enter, add new line
                        if (that.isOpened()) {
                            that.val(that.val() + '\n');
                            that.focus();
                        } else {
                            that.beginEditing(that.originalValue + '\n')
                        }
                        event?.stopImmediatePropagation();
                    }
                    event.preventDefault(); //don't add newline to field
                    break;

                case keyCodes.A:
                case keyCodes.X:
                case keyCodes.C:
                case keyCodes.V:
                    if (ctrlDown) {
                        event?.stopImmediatePropagation(); //CTRL+A, CTRL+C, CTRL+V, CTRL+X should only work locally when cell is edited (not in table context)
                    }
                    break;

                case keyCodes.BACKSPACE:
                case keyCodes.DELETE:
                case keyCodes.HOME:
                case keyCodes.END:
                    event?.stopImmediatePropagation(); //backspace, delete, home, end should only work locally when cell is edited (not in table context)
                    break;
            }

        };

        Select2Editor.prototype.open = function (keyboardEvent) {
            this.refreshDimensions();
            this.textareaParentStyle.display = 'block';
            this.textareaParentStyle.zIndex = 20000;
            this.instance.addHook('beforeKeyDown', onBeforeKeyDown);

            this.$textarea.css({
                height: $(this.TD).height() + 4,
                'min-width': 0, //this is workaround to enable mouse selection
                'opacity': 0
                //'min-width': $(this.TD).outerWidth() - 4,
            });

            //display the list
            this.$textarea.show();

            var self = this;
            this.$textarea.select2(this.options)
                .on('change', onSelect2Changed.bind(this))
                .on('select2-close', onSelect2Closed.bind(this));
            self.$textarea.select2('open');

            // Pushes initial character entered into the search field, if available
            if (keyboardEvent && keyboardEvent.keyCode) {
                var key = keyboardEvent.keyCode;
                var keyText = (String.fromCharCode((96 <= key && key <= 105) ? key - 48 : key)).toLowerCase();
                console.log("KeyText: " + keyText);
                self.$textarea.select2('search', keyText.slice(0, -1));
            }

        };

        Select2Editor.prototype.init = function () {
            Handsontable.editors.TextEditor.prototype.init.apply(this, arguments);
        };

        Select2Editor.prototype.close = function () {
            this.instance.listen();
            this.instance.removeHook('beforeKeyDown', onBeforeKeyDown);
            this.$textarea.off();
            this.$textarea.hide();
            Handsontable.editors.TextEditor.prototype.close.apply(this, arguments);
        };

        Select2Editor.prototype.val = function (value) {
            if (typeof value == 'undefined') {
                return this.$textarea.val();
            } else {
                this.$textarea.val(value);
            }
        };

        Select2Editor.prototype.focus = function () {
            this.instance.listen();

            // DO NOT CALL THE BASE TEXTEDITOR FOCUS METHOD HERE, IT CAN MAKE THIS EDITOR BEHAVE POORLY AND HAS NO PURPOSE WITHIN THE CONTEXT OF THIS EDITOR
            Handsontable.editors.TextEditor.prototype.focus.apply(this, arguments);
        };

        Select2Editor.prototype.beginEditing = function (initialValue) {
            var onBeginEditing = this.instance.getSettings().onBeginEditing;
            if (onBeginEditing && onBeginEditing() === false) {
                return;
            }

            Handsontable.editors.TextEditor.prototype.beginEditing.apply(this, arguments);

        };

        Select2Editor.prototype.finishEditing = function (isCancelled, ctrlDown) {
            this.instance.listen();
            return Handsontable.editors.TextEditor.prototype.finishEditing.apply(this, arguments);
        };

        Handsontable.editors.Select2Editor = Select2Editor;
        Handsontable.editors.registerEditor('select2', Select2Editor);

    })(Handsontable);

    function setTwoNumberDecimal(event) {
        this.value = parseFloat(this.value);
    }


</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("purchase-menu");
</script>
</body>
</html>