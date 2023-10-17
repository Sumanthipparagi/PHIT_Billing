<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Sale Entry</title>
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
    %{--    <link rel="stylesheet" media="screen"
              href="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.css">--}%

    <style>
    .form-control {
        border-radius: 7px !important;
    }

    /*.handsontableInputHolder*/
    /*{*/
    /*    display: none!important;*/
    /*}*/


    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="PharmIT"></div>

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
                        <li class="breadcrumb-item active"><g:if test="${saleBillDetail}">Edit</g:if> Sale
                        Entry</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card" style="margin-bottom: 10px;">
                    <div class="header" style="padding: 1px;">
                        <div class="row">
                            <div class="col-md-12">
                                <a title="Click to keep focus on Product and Batch Details" class="btn btn-sm btn-primary waves-effect" role="button"
                                   data-toggle="collapse" href="#collapseTopBar" aria-expanded="false"
                                   aria-controls="collapseTopBar" id="topBarCollapseButton"><i class="zmdi zmdi-long-arrow-down"></i> Top Bar Toggle
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="body" id="collapseTopBar">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Patient:</label>
                                <input type="hidden" class="customerId"/>
                                <input type="hidden" class="stateId"/>
                                <span id="patientDetails"></span>
                            </div>

                            <div class="col-md-2">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control date" name="date" id="date" <g:if
                                        test="${entityConfigs?.DATE_EDITABLE?.saleEntry != true}">readonly</g:if>/>
                            </div>

                            <div class="col-md-2">
                                <label for="series">Series:</label>
                                <select onchange="seriesChanged()" class="form-control" id="series" name="series">
                                    <g:each in="${series}" var="sr">
                                        <option data-seriescode="${sr.seriesCode}"
                                                value="${sr.id}"
                                                <g:if test="${saleBillDetail?.seriesId == sr.id}">selected</g:if>>${sr.seriesName} (${sr.seriesCode})</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label for="priority">Priority:</label>
                                <select class="form-control" id="priority" name="priority">
                                    <g:each in="${priorityList}" var="pr">
                                        <option value="${pr.id}"
                                                <g:if test="${saleBillDetail?.priorityId == pr.id}">selected</g:if>>${pr.priority}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label for="drname">Dr Name:</label>
                                <input type="text" class="form-control drname" name="drname" id="drname"/>
                            </div>
                        </div>

                        <div class="row">

                            <div class="col-md-2">
                                <label for="priority">Invoice Type:</label>
                                <select class="form-control" id="invType" name="invType">
                                    <option value="${Constants.REGULAR}"
                                            <g:if test="${saleBillDetail?.priorityId == Constants.REGULAR}">selected</g:if>>REGULAR</option>
                                    <option value="${Constants.REPLACEMENT}"
                                            <g:if test="${saleBillDetail?.priorityId == Constants.REPLACEMENT}">selected</g:if>>REPLACEMENT</option>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label for="repSelect">Rep:</label>
                                <select class="form-control show-tick" id="repSelect">
                                    <option selected disabled>--SELECT--</option>
                                    <g:each in="${users}" var="u">
                                        <option value="${u.id}"
                                                <g:if test="${saleBillDetail?.rep == u.id.toString()}">selected</g:if>>${u.userName} ${"- " + u?.name}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-md-2">
                                <label for="refNum">Ref. Number:</label>
                                <input type="text" maxlength="100" class="form-control" name="refNum" id="refNum"
                                       value="${saleBillDetail?.refNo}"/>
                            </div>

                            <div class="col-md-2">
                                <label for="refDate">Ref. Date:</label>
                                <input type="date" class="form-control date" name="refDate" id="refDate"/>
                            </div>


                            <div class="col-md-1 mx-0 px-0" style="max-width: 60px;">
                                <br>
                                <a title="Shipment" class="btn btn-sm btn-primary waves-effect" role="button"
                                   data-toggle="collapse"
                                   href="#shipmentDetails" aria-expanded="false"
                                   aria-controls="shipmentDetails"><i class="zmdi zmdi-truck"></i>
                                </a>
                            </div>

                            <div class="col-md-1 mx-0 px-0" style="max-width: 60px;">
                                <br>
                                <a title="Add Note" class="btn btn-sm btn-primary waves-effect collapsed" role="button"
                                   data-toggle="collapse" href="#noteDetails" aria-expanded="false"
                                   aria-controls="noteDetails"><i class="zmdi zmdi-edit"></i>
                                </a>
                            </div>

                            %{--<g:if test="${customer!=null}">--}%
                            <div class="col-md-1 mx-0 px-0 " style="max-width: 60px;">
                                <br>
                                <a title="Mass Discount" role="button" style="color: white;"
                                   class="btn btn-sm btn-primary waves-effect collapsed"
                                   data-toggle="modal" data-target="#massDiscountModal">Disc
                                </a>
                            </div>
                            %{--</g:if>--}%
                            <div class="col-md-1 mx-0 px-0 ml-2" style="max-width: 60px;">
                                <br>
                                <a title="Add Row" class="btn btn-sm btn-primary waves-effect"
                                   id="addNewRow" style="background-color: green;color: white;"><i
                                        class="zmdi zmdi-plus"></i>
                                </a>
                            </div>

                            <div class="col-md-1 mx-0 px-0" style="margin-top: 5px;">
                                <br>
                                <label for="gstInclusive"><strong>Incl. GST?</strong></label>
                                <input id="gstInclusive" name="gstInclusive" type="checkbox"
                                       class="checkbox checkbox-inline" style="margin-top: 5px;margin-left: 5px;"
                                       value="false"/>
                            </div>
                        %{--                            data-toggle="modal"--}%
                        %{--                            data-target="#myModal"--}%
                            <g:if test="${tempStockArray != null}">
                                <div class="col-md-3 mt-2">
                                    <br>
                                    <a class="btn btn-primary waves-effect" role="button" data-toggle="collapse"
                                       href="#tempStockDetails" aria-expanded="false" aria-controls="tempStockDetails"
                                       style="color:white;"><i class="zmdi zmdi-long-arrow-up"></i>&nbsp;
                                    Products(${tempStockArray.size()})
                                    </a>
                                </div>
                            </g:if>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div class="collapse" id="noteDetails">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="publicNote">Public Note</label>
                                                <textarea id="publicNote" rows="1" maxlength="500" name="publicNote"
                                                          class="form-control">${saleBillDetail?.publicNote}</textarea>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div class="form-group">
                                                    <label for="privateNote">Private Note</label>
                                                    <textarea id="privateNote" rows="1" maxlength="500"
                                                              name="privateNote"
                                                              class="form-control">${saleBillDetail?.privateNote}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div class="collapse" id="shipmentDetails">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="lrNumber">LR No.</label>
                                                <input type="text" maxlength="150" id="lrNumber" name="lrNumber"
                                                       class="form-control" value="${saleTransportDetail?.lrNumber}"/>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="lrDate">LR Date</label>
                                                <input type="date" maxlength="150" id="lrDate" name="lrDate"
                                                       class="form-control"/>
                                            </div>
                                        </div>
                                        <input type="hidden" name="saleTransportDetailsId" id="saleTransportDetailsId"
                                               value="${saleTransportDetail?.id}">

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="transportType">Transporter</label>
                                                <select id="transportType" name="transportType"
                                                        class="form-control">
                                                    <option value="">--Please Select--</option>
                                                    <g:each in="${transporter}" var="t">
                                                        <option value="${t.id}"
                                                                <g:if test="${saleTransportDetail?.transporterId == t.id}">selected</g:if>>${t.name}</option>
                                                    </g:each>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div id="FileUploadContainer">
                                <label><i class="fa fa-upload"></i> Attachment:</label> <input id="attachment" onchange="uploadAttachment()" type="file"/>

                                </div>
                                <div id="FileUploadedContainer" class="hidden">
                                    <input type="hidden" name="uploadedAttachment" id="uploadedAttachment"/>
                                    Attachment Uploaded <i class="zmdi zmdi-check"></i> &nbsp;&nbsp;<span><a href="#" onclick="downloadFile()"><i class="zmdi zmdi-download" ></i> Download</a></span> &nbsp;&nbsp; <span><a href="#" class="danger" onclick="deleteFile()"><i class="zmdi zmdi-delete" ></i> Delete</a></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div class="collapse" id="tempStockDetails">
                                    <g:if test="${tempStockArray?.size() > 0}">
                                        <div class="">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Product</th>
                                                    <th scope="col">Batch Number</th>
                                                    <th scope="col">Sale Qty</th>
                                                    <th scope="col">Free Qty</th>
                                                    <th scope="col">Remaining Qty</th>
                                                    <th scope="col">Remaining Free Qty</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <g:each var="ta" in="${tempStockArray}">
                                                    <tr>
                                                        <td>${ta.product.productName}</td>
                                                        <td>${ta.batchNumber}</td>
                                                        <td>${ta.userOrderQty}</td>
                                                        <td>${ta.userOrderFreeQty}</td>
                                                        <td>${ta.remainingQty}</td>
                                                        <td>${ta.remainingFreeQty}</td>
                                                    </tr>
                                                </g:each>
                                                </tbody>
                                            </table>
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <br>
                                        <br>
                                        <h5 style="text-align: center;">All Products are updated..</h5>
                                    </g:else>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div id="address">

                                </div>
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
                            %{-- <p class="loadTable">Loading...<img src="${assetPath(src: '/themeassets/images/3.gif')}"
                                                                 width="25" height="25"/>
                             </p>--}%

                            <div id="saleTable" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-4" style="margin-bottom: 10px;">
                <p style="margin: 0; font-size: 10px;">Keyboard Shortcuts - Delete Row: <strong>Ctrl+Alt+D</strong>,
                Reset Table: <strong>Ctrl+Alt+R</strong>, Register Patient: <strong>Ctrl+M</strong>
                </p>
            </div>

            <div class="col-lg-4" style="margin-bottom: 10px;">

            </div>

            <div class="col-lg-4" style="margin-bottom: 10px;">
                <p style="margin: 0; font-size: 10px;color: red;">Offers: <span id="offers"></span>
                </p>
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
                            %{--                            <button id="saveDraftBtn" onclick="saveSaleInvoice('DRAFT')"--}%
                            %{--                                    class="btn btn-primary">Save Draft</button>--}%
                            <button id="saveBtn" onclick="saveSaleInvoice('ACTIVE')"
                                    class="btn btn-primary">Save</button>
                            %{--<button onclick="printInvoice()" class="btn btn-secondary">Print</button>--}%
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</section>

<div id="myModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title">Following products have been not added (User Order Qty greater than Stock
                Qty)</h6>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <g:if test="${tempStockArray?.size() > 0}">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Product</th>
                                <th scope="col">Batch Number</th>
                                <th scope="col">Sale Qty</th>
                                <th scope="col">Free Qty</th>
                                <th scope="col">Remaining Qty</th>
                                <th scope="col">Remaining Free Qty</th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each var="ta" in="${tempStockArray}">
                                <tr>
                                    <td>${ta.product.productName}</td>
                                    <td>${ta.batchNumber}</td>
                                    <td>${ta.userOrderQty}</td>
                                    <td>${ta.userOrderFreeQty}</td>
                                    <td>${ta.remainingQty}</td>
                                    <td>${ta.remainingFreeQty}</td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </g:if>
                <g:else>
                    <br>
                    <br>
                    <h5 style="text-align: center;">All Products are updated..</h5>
                </g:else>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                %{--                <button type="button" class="btn btn-primary">Save</button>--}%
            </div>
        </div>
    </div>
</div>


<g:include view="controls/sales/batch-detail.gsp"/>
<g:include view="controls/sales/mass-discount.gsp"/>
<g:include view="controls/delete-modal.gsp"/>
<g:include view="controls/sales/add-patient-modal.gsp"/>
<g:include view="controls/sales/phone-number.gsp"/>

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
        '<strong>Sale Qty</strong>',
        '<strong>Free Qty</strong>',
        '<strong>Sale Rate</strong>',
        '<strong>MRP</strong>',
        '<strong>Disc.(%)</strong>',
        '<strong>Pack</strong>',
        '<strong>GST</strong>',
        '<strong>Value</strong>',
        'SGST',
        'CGST',
        'IGST',
        'Prisc. Qty',
        'No. of days',
        'M',
        'A',
        'Schedule',
        'id'];

    var batchHeaderRow = [
        '<strong>Batch</strong>',
        '<strong>Exp Dt</strong>',
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
    var saleData = [];
    var batchData = [];
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
    var seriesId = null;

    $(document).ready(function () {
        seriesId = $("#series").val();
        $('#phoneNumberModal').modal({backdrop: 'static', keyboard: false}, 'show');
        setTimeout(function () {
            document.getElementById("phono").focus();
        }, 6000);
        $("#customerSelect").select2();
        $('#date').val(moment().format('YYYY-MM-DD'));
        $('#lrDate').val(moment('${saleTransportDetail?.lrDate}').format('YYYY-MM-DD'));
        $('#refDate').val(moment('${saleBillDetail?.refDate}').format('YYYY-MM-DD'));
        $('#date').attr("readonly");

        <g:if test="${tempStockArray!=null && params.type == "CLONE"}">
        var tempStockArray = '${tempStockArray}';
        var tempStockJSON = JSON.parse(tempStockArray.replace(/&quot;/g, '"'));
        console.log(tempStockJSON);
        <g:if test="${tempStockArray.size()!=0}">
        $('#myModal').modal('show');
        </g:if>
        </g:if>

        <g:each in="${customers}" var="cs">
        customers.push({"id": ${cs.id}, "noOfCrDays": ${cs.noOfCrDays}});
        </g:each>
        const container = document.getElementById('saleTable');
        //main table
        hot = new Handsontable(container, {
            data: saleData,
            minRows: 1,
            height: '250',
            width: 'auto',
            rowHeights: 25,
            stretchH: 'all',
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
                        //data: products,
                        dropdownAutoWidth: true,
                        allowClear: true,
                        width: '0',
                        ajax: {
                            url: "/product/series/" + seriesId,
                            dataType: 'json',
                            quietMillis: 250,
                            data: function (term, page) {
                                return {
                                    search: term,
                                    page: page || 1
                                };
                            },
                            results: function (response, page) {
                                //products = [];
                                var dropdownData = [];
                                var data = response.products
                                for (var i = 0; i < data.length; i++) {
                                    if (data[i].saleType === '${Constants.SALEABLE}') {
                                        if (!dropdownData.some(element => element.id === data[i].id)) {
                                            products.push({id: data[i].id, text: data[i].productName});
                                            dropdownData.push({id: data[i].id, text: data[i].productName});
                                        }
                                    }
                                }
                                return {
                                    results: dropdownData,
                                    more: (page * 10) < response.totalCount
                                };
                            },
                        }
                    }
                },
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'}, //sale rate
                {type: 'text', readOnly: true},
                {type: 'text'},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'}, //17 M
                {type: 'numeric'}, //18 A
                {
                    type: 'dropdown',
                    source: ['1-1-1', '1-1-0', '1-0-1', '1-0-0', '0-1-1', '0-1-0', '0-0-1', '0-0-0']
                }, //19 N
                // {type: 'numeric'}, //19 N
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true}, //GST Percentage
                {type: 'text', readOnly: true}, //SGST Percentage
                {type: 'text', readOnly: true}, //CGST Percentage
                {type: 'text', readOnly: true}, //IGST Percentage
                {type: 'text', readOnly: true},//originalSqty
                {type: 'text', readOnly: true} //originalFqty
                /*  <g:if test="${customer != null}">
                , {type: 'text', readOnly: true}, //draft sqty
                {type: 'text', readOnly: true}, //draft fqty
                {type: 'text', readOnly: true} //saved draft product id
                </g:if>*/
            ],
            hiddenColumns: true,
            hiddenColumns: {
                columns: [17, 18, 20, 21, 22, 23, 24, 25, 26]
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
                        if (!hot.isEmptyRow(0)) {
                            customerLock(true)

                        } else {
                            customerLock(false)
                        }
                    });
                }
            },
            afterOnCellMouseDown: function (e, coords, TD) {
                if (coords.col === 0) {
                    <g:if test="${customer == null}">
                    var id = hot.getDataAtCell(coords.row, 16);
                    deleteTempStockRow(id, coords.row);
                    console.log("delete temp stock row deleted!!")
                    </g:if>
                    <g:else>
                    var id = hot.getDataAtCell(coords.row, 25);
                    if (id == null && ${params.type == "CLONE"}) {
                        id = hot.getDataAtCell(coords.row, 16);
                        deleteTempStockRow(id, coords.row);
                        console.log("delete temp stock row deleted!!")
                    } else {
                        deleteSaleBillRow(id, coords.row);
                        console.log("Sale product deleted!!")
                    }
                    </g:else>

                    calculateTotalAmt();
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
                return cellPrp;
            }
        });
        hot.deselectCell()
        // hot.selectCell(0, 1);
        hot.updateSettings({
            beforeKeyDown(e) {
                var sRate = 0;
                var sQty = 0;
                var fQty = 0;
                const row = hot.getSelected()[0][0];
                const selection = hot.getSelected()[0][1];
                if (selection === 1) {
                    if (e.keyCode === 13) {
                        batchHot.selectCell(0, 0);
                        $("#batchTable").focus();
                    }
                } else if (selection === 0) {
                    var id = hot.getDataAtCell(row, 16);
                    if (e.keyCode === 13)
                        deleteTempStockRow(id, row);
                } else if (selection === 19) {
                    if ((e.keyCode === 13 || e.keyCode === 9) && !readOnly) {
                        //check if sqty is empty
                        //   var typ = hot.getCellMeta(row,15).type;
                        /*if (e.keyCode === 13 && typ === 'checkbox') {
                            e.stopImmediatePropagation();
                        }*/
                        var sqty = hot.getDataAtCell(row, 4);
                        var fqty = hot.getDataAtCell(row, 5);
                        if (sqty && sqty > 0) {
                            for (var j = 0; j < 19; j++) {
                                hot.setCellMeta(row, j, 'readOnly', true);
                                // hot.setCellMeta(row,j,'disableVisualSelection', true)
                            }
                            mainTableRow = row + 1;
                            hot.alter('insert_row');
                            hot.selectCell(mainTableRow, 0);
                            hot.render();
                            calculateTotalAmt();
                        } else {
                            alert("Invalid Quantity, please enter quantity greater than 0");

                        }

                    }
                } else if (selection === 4 || selection === 5 || selection === 8 || selection === 6 || selection > 6
                    && selection <= 25) {
                    //TODO: get cellmeta and check if readonly enabled
                    var cellMeta = hot.getCellMeta(row, 6);
                    if (!cellMeta.readOnly && (e.keyCode === 13 || e.keyCode === 9 || e.keyCode === 37 || e.keyCode === 39 || e.type === "click")) {
                        var discount = 0;
                        if (selection === 6) {
                            var mrp = hot.getDataAtCell(row, 7);
                            var oldSaleRate = hot.getDataAtCell(row, 6);
                            var saleRate = Number(this.getActiveEditor().TEXTAREA.value);
                            if (saleRate > mrp) {
                                hot.setDataAtCell(row, 6, oldSaleRate);
                                this.getActiveEditor().TEXTAREA.value = oldSaleRate;
                                alert("Sale Rate exceeds MRP!");
                            } else {
                                hot.setDataAtCell(row, 6, Number(this.getActiveEditor().TEXTAREA.value));
                                this.selectCell(row, selection + 1);
                            }
                            costInclusiveOfGST(row);
                        }
                        if (selection === 4) {
                            this.getActiveEditor().enableFullEditMode();
                            this.getActiveEditor().beginEditing();
                            sQty = Number(this.getActiveEditor().TEXTAREA.value);
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
                        if (selection === 8) {
                            discount = Number(this.getActiveEditor().TEXTAREA.value);
                            if (discount > 100) {
                                alert("Invalid Discount");
                                hot.setDataAtCell(row, 8, 0);
                                this.getActiveEditor().TEXTAREA.value = 0;
                                hot.selectCell(row, 8);
                                return;
                            } else {
                                hot.setDataAtCell(row, 8, discount);
                                this.selectCell(row, selection + 1);
                            }
                        } else {
                            discount = hot.getDataAtCell(row, 8);
                        }

                        /*if(selection === 15 || selection === 16 || selection === 17 || selection === 18 || selection
                            === 19){*/
                        if (selection === 15 || selection === 16) {
                            this.selectCell(row, selection + 1);
                        }

                        var allowEntry = false;
                        var pid = hot.getDataAtCell(row, 1);
                        var batch = hot.getDataAtCell(row, 2);
                        var remQty = 0;
                        var remFQty = 0;
                        var freeQtyEntry = false;
                        <g:if test="${settings?.ZERO_INVOICE_VALUE== Constants.YES && settings!=null}">
                        if (hot.getDataAtCell(row, 6) === 0) {
                            alert("Zero invoice not allowed");
                            hot.alter("remove_row", row);
                            return;
                        }
                        </g:if>
                        if (pid && batch) {
                            $.ajax({
                                    type: "POST",
                                    url: "/stockbook/product/" + pid + "/batch/" + batch,
                                    dataType: 'json',
                                    success: function (data) {
                                        var draftSqty = 0;
                                        var draftFqty = 0;
                                        <g:if test="${customer != null}">
                                        draftSqty = hot.getDataAtCell(row, 22); //draft sqty
                                        draftFqty = hot.getDataAtCell(row, 23); //draft fqty
                                        </g:if>

                                        remQty = remQty + data.remainingQty;
                                        remFQty = remFQty + data.remainingFreeQty;
                                        if (remQty >= sQty) {
                                            allowEntry = true;
                                        } else if (sQty >= remQty && remFQty >= sQty) {
                                            allowEntry = true;
                                        } else if ((remQty + remFQty) >= sQty) {
                                            allowEntry = true;
                                        } else if (draftSqty > 0 && sQty <= (draftSqty + draftFqty + remQty + remFQty)) {
                                            allowEntry = true; //if draft greater than zero, allow manipulation
                                        } else if (draftFqty > 0 && fqty <= (draftSqty + draftFqty + remQty + remFQty)) {
                                            allowEntry = true;
                                        }


                                        if (selection === 5) {
                                            if (remFQty >= fQty && ((remQty + remFQty) >= (sQty + fQty))) {
                                                freeQtyEntry = true;
                                            } else if ((remQty + remFQty) >= sQty + fQty) {
                                                freeQtyEntry = true;
                                                allowEntry = true;
                                            } else {
                                                freeQtyEntry = false;
                                                allowEntry = false;
                                            }

                                            if (freeQtyEntry !== true) {
                                                // hot.setDataAtCell(row, 5, 0);
                                                alert("Entered Free quantity exceeds available quantity");
                                            }
                                        }
                                        if (!allowEntry) {
                                            // this.getActiveEditor().TEXTAREA.value = "";
                                            if (draftSqty > 0 || draftFqty > 0) {
                                                hot.setDataAtCell(row, 4, draftSqty);
                                                hot.setDataAtCell(row, 5, draftFqty);
                                            } else {
                                                hot.setDataAtCell(row, 4, 0);
                                                hot.setDataAtCell(row, 5, 0);
                                                hot.setDataAtCell(row, 10, 0);
                                                hot.setDataAtCell(row, 11, 0);
                                                hot.setDataAtCell(row, 12, 0);
                                                hot.setDataAtCell(row, 13, 0);
                                                hot.setDataAtCell(row, 14, 0);
                                            }
                                            alert("Entered quantity exceeds available quantity");
                                            return;
                                        } else {
                                            hot.setDataAtCell(row, 5, fQty)
                                        }
                                    },
                                    error: function (data) {
                                        alert("Something went Wrong!")
                                    }
                                }
                            );
                        }
                        applySchemes(row, sQty);
                        if (selection === 6) {
                            if (this.getActiveEditor())
                                sRate = Number(this.getActiveEditor().TEXTAREA.value);
                            else
                                sRate = hot.getDataAtCell(row, 6);
                        } else
                            sRate = hot.getDataAtCell(row, 6);

                        var value = sRate * sQty;
                        var priceBeforeGst = value - (value * discount / 100);
                        var finalPrice = priceBeforeGst + (priceBeforeGst * (gst / 100));
                        hot.setDataAtCell(row, 11, Number(finalPrice).toFixed(2));
                        if (stateId === undefined || stateId === '${session.getAttribute('stateId')}') {
                            if (gst !== 0) {
                                var gstAmount = priceBeforeGst * (gst / 100);
                                var sgstAmount = priceBeforeGst * (sgst / 100);
                                var cgstAmount = priceBeforeGst * (cgst / 100);
                                hot.setDataAtCell(row, 10, Number(gstAmount).toFixed(2)); //GST
                                hot.setDataAtCell(row, 12, Number(sgstAmount).toFixed(2)); //SGST
                                hot.setDataAtCell(row, 13, Number(cgstAmount).toFixed(2)); //CGST
                            } else {
                                hot.setDataAtCell(row, 10, 0); //GST
                                hot.setDataAtCell(row, 12, 0); //SGST
                                hot.setDataAtCell(row, 13, 0); //CGST
                            }

                            if (igst !== "0") {
                                var igstAmount = priceBeforeGst * (igst / 100);
                                hot.setDataAtCell(row, 14, Number(igstAmount).toFixed(2)); //IGST
                            } else
                                hot.setDataAtCell(row, 14, 0);
                        } else {
                            hot.setDataAtCell(row, 12, 0); //SGST
                            hot.setDataAtCell(row, 13, 0); //CGST
                            hot.setDataAtCell(row, 10, Number(priceBeforeGst * (gst / 100)).toFixed(2)); //GST
                            hot.setDataAtCell(row, 14, Number(priceBeforeGst * (gst / 100)).toFixed(2)); //IGST
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

        function checkUnsavedTemp() {
            var data = hot.getData();
            for (let i = 0; i < data.length; i++) {
                console.log(data[i][16])
                if (data[i][16] === null) {
                    return true
                }
            }
            return false
        }

        document.querySelector('#addNewRow').addEventListener('click', function () {
            // var col = hot.countRows();
            // hot.alter('insert_row', col, 1);
            var tempArray = [];
            var data = hot.getSourceData();
            for (let i = 0; i < data.length; i++) {
                if (data[i].hasOwnProperty('16')) {
                    tempArray.push(data[i]['16'])
                }
            }
            console.log("new row add!!");
            console.log(tempArray);
            console.log(hot.countRows() + 1);
            console.log(hot.getSourceData());
            if (tempArray.length !== 0) {
                console.log(checkUnsavedTemp())
                if (checkUnsavedTemp()) {
                    alert("Table consist of unsaved data")
                    return;
                }
                if (hot.isEmptyRow(tempArray.length)) {
                    if (tempArray.length === hot.countRows() - 1) {
                        alert("Row already present!");
                        return;
                    } else {
                        hot.alter('insert_row');
                    }
                } else {
                    hot.alter('insert_row');
                }
            } else {
                alert("Row not saved properly!")
                return;
            }

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


        //batch table
        batchHot = new Handsontable(batchContainer, {
            data: batchData,
            minRows: 1,
            height: '120',
            width: 'auto',
            rowHeights: 25,
            stretchH: 'all',
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
                columns: [4, 9, 10, 11, 12]
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
                // console.log(rowData[0]);
                if (e.keyCode === 13) {

                    if (!checkForDuplicateEntry(rowData[0])) {
                        //check for schemes
                        // console.log(!checkForDuplicateEntry(rowData[0]));
                        checkSchemes(hot.getDataAtCell(mainTableRow, 1), rowData[0]); //product, batch
                        var batchId = rowData[12];
                        hot.setDataAtCell(mainTableRow, 2, rowData[0]);
                        hot.setCellMeta(mainTableRow, 2, "batchId", batchId);
                        hot.setDataAtCell(mainTableRow, 3, rowData[1]);
                        hot.setDataAtCell(mainTableRow, 5, 0);
                        hot.setDataAtCell(mainTableRow, 6, rowData[5]);
                        hot.setDataAtCell(mainTableRow, 7, rowData[6]);
                        hot.setDataAtCell(mainTableRow, 8, 0);
                        hot.setDataAtCell(mainTableRow, 9, rowData[7]);
                        hot.setDataAtCell(mainTableRow, 15, 0);
                        hot.setDataAtCell(mainTableRow, 16, 0);
                        hot.setDataAtCell(mainTableRow, 17, 0);
                        hot.setDataAtCell(mainTableRow, 18, 0);
                        hot.setDataAtCell(mainTableRow, 19, 0);

                        gst = rowData[8];
                        if (stateId === undefined || stateId === '${session.getAttribute('stateId')}') {
                            sgst = rowData[9];
                            cgst = rowData[10];
                            igst = 0
                        } else {
                            igst = rowData[11];
                            sgst = 0;
                            cgst = 0;
                        }
                        hot.selectCell(mainTableRow, 4);
                        hot.setDataAtCell(mainTableRow, 21, gst);
                        hot.setDataAtCell(mainTableRow, 22, sgst);
                        hot.setDataAtCell(mainTableRow, 23, cgst);
                        hot.setDataAtCell(mainTableRow, 24, igst);
                        hot.setDataAtCell(mainTableRow, 25, rowData[2]);
                        hot.setDataAtCell(mainTableRow, 26, rowData[3]);
                        remainingQty = rowData[2];
                        remainingFQty = rowData[3];
                        $("#saleTable").focus();
                    } else {
                        alert("Selected product and batch already entered, duplicate entries not allowed");
                    }
                }
            }
        });
        $("#customerSelect").trigger('change');
        $('#series').trigger('change');
    });

    function batchSelection(selectedId, mainRow, selectCell = true) {
        if (selectedId != null) {
            var sid = selectedId.id;
            if (sid === undefined)
                sid = selectedId;
            if (sid == null) {
                batchHot.updateSettings({
                    data: []
                });
                return;
            }
            var url = "/stockbook/product/" + sid;
            $.ajax({
                type: "GET",
                url: url,
                data: {
                    userId: "${session.getAttribute("userId")}"
                },
                dataType: 'json',
                success: function (data) {
                    if (data) {
                        batchData = [];
                        for (var i = 0; i < data.length; i++) {
                            if (Number('${session.getAttribute('entityId')}') === data[i].entityId) {
                                var batchdt = [];
                                batchdt.push(data[i].batchNumber);
                                batchdt.push(data[i].expDate.split("T")[0]);
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
                                batchdt.push(data[i].id);
                                batchData.push(batchdt);
                            }
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

    function deleteTempStockRow(id, row) {
        if (!readOnly) {
            hot.alter("remove_row", row);
            if (!hot.isEmptyRow(0)) {
                customerLock(true)
            } else {
                customerLock(false)
            }
        } else
            alert("Can't change this now, invoice has been saved already.")
    }

    function deleteSaleBillRow(id, row) {
        if (!readOnly) {
            if (id) {
                $.ajax({
                    type: "POST",
                    url: "/sale-product-details/delete?id=" + id,
                    dataType: 'json',
                    success: function (data) {
                        hot.alter("remove_row", row);
                        Swal.fire({
                            title: "Success",
                            text: "Product removed."
                        });
                        batchHot.updateSettings({
                            data: []
                        });
                    }
                });
            } else
                hot.alter("remove_row", row);
        } else
            alert("Can't change this now, invoice has been saved already.")
    }

    var saleBillId = 0;

    function saveSaleInvoice(billStatus) {
        $("#saveBtn").prop("disabled", true);
        $("#saveDraftBtn").prop("disabled", true);
        var waitingSwal = Swal.fire({
            title: "Generating Invoice, Please wait!",
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false
        });

        var customer = $(".customerId").val();
        var publicNote = $("#publicNote").val();
        var privateNote = $("#privateNote").val();
        var refNum = $("#refNum").val();
        var refDate = $("#refDate").val();
        var rep = $("#repSelect").val();
        var drname = $('.drname').val();
        if (refDate !== '') {
            refDate = moment(refDate).format("DD/MM/YYYY");
        } else {
            refDate = ''
        }
        var saleTransportDetailsId = $("#saleTransportDetailsId").val();
        var invtype = $("#invType").val();
        var series = $("#series").val();
        var seriesCode = $("#series").find(':selected').data('seriescode');
        var duedate = $("#duedate").val();
        var lrNumber = $("#lrNumber").val();
        var lrDate = $("#lrDate").val();
        var transporter = $("#transportType").val();
        duedate = moment(duedate, 'YYYY-MM-DD').toDate();
        duedate = moment(duedate).format('DD/MM/YYYY');
        var priority = $("#priority").val();
        if (!series) {
            alert("Please select series.");
            waitingSwal.close();
            $("#saveBtn").prop("disabled", false);
            $("#saveDraftBtn").prop("disabled", false);
            return;
        }
        if (!customer) {
            alert("Please select customer.");
            waitingSwal.close();
            $("#saveBtn").prop("disabled", false);
            $("#saveDraftBtn").prop("disabled", false);
            return;
        }
        if (!invtype) {
            alert("Please select Invoice Type.");
            waitingSwal.close();
            $("#saveBtn").prop("disabled", false);
            $("#saveDraftBtn").prop("disabled", false);
            return;
        }
        var attachment = $("#uploadedAttachment").val();
        var saleData = JSON.stringify(hot.getSourceData());
        var url = "";
        url = "/savesaleRetailerEntry";
        console.log("save sale entry");
        Swal.fire({
            title: 'Do you want to save the changes?',
            showDenyButton: true,
            // showCancelButton: true,
            confirmButtonText: 'Yes',
            denyButtonText: `No`,
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: 'json',
                    data: {
                        saleData: saleData,
                        customer: customer,
                        series: series,
                        duedate: duedate,
                        priority: priority,
                        billStatus: billStatus,
                        seriesCode: seriesCode,
                        saleTransportDetailsId: saleTransportDetailsId,
                        invtype: invtype,
                        lrNumber: lrNumber,
                        lrDate: lrDate,
                        refNum: refNum,
                        refDate: refDate,
                        drname: drname,
                        transporter: transporter,
                        publicNote: publicNote,
                        privateNote: privateNote,
                        rep: rep,
                        uuid: self.crypto.randomUUID(),
                        attachment: attachment
                    },
                    beforeSend: function () {
                        Swal.fire({
                            // title: "Loading",
                            html:
                                '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false,
                            background: 'transparent'

                        });
                        // document.addEventListener('keypress', function (e) {
                        //     if (e.keyCode === 13 || e.which === 13) {
                        //         e.preventDefault();
                        //         return false;
                        //     }
                        // });
                        // hot.deselectCell()
                    },
                    success: function (data) {
                        readOnly = true;
                        var rowData = hot.getData();
                        for (var j = 0; j < rowData.length; j++) {
                            for (var i = 0; i < 16; i++) {
                                hot.setCellMeta(j, i, 'readOnly', true);
                            }
                        }
                        saleBillId = data.saleBillDetail.id;
                        var datepart = data.saleBillDetail.entryDate.split("T")[0];
                        var month = datepart.split("-")[1];
                        var year = datepart.split("-")[0];
                        var seriesCode = data.series.seriesCode;
                        var invoiceNumber = data.saleBillDetail.invoiceNumber;
                        $("#invNo").html("<p><strong>" + invoiceNumber + "</strong></p>");
                        var message = "";
                        var draftInvNo = "";
                        if (billStatus === "DRAFT") {
                            draftInvNo = '<p><strong>' + data.saleBillDetail.entityId + "/DR/S/" + month + year + "/"
                                + seriesCode + "/__" + '<p><strong>';
                            $("#invNo").html(draftInvNo);
                        }
                        if (billStatus !== "DRAFT") {
                            message = 'Sale Invoice Generated: ' + invoiceNumber;
                        } else {
                            message = 'Draft Invoice Generated: ' + data.saleBillDetail.entityId + "/DR/S/" + month + year + "/"
                                + seriesCode + "/__";
                        }
                        waitingSwal.close();
                        Swal.fire({
                            title: message,
                            showDenyButton: true,
                            showCancelButton: false,
                            confirmButtonText: 'Print',
                            denyButtonText: 'New Entry',
                            allowOutsideClick: false
                        }).then((result) => {
                            if (result.isConfirmed) {
                                printInvoice();
                            } else if (result.isDenied) {
                                resetData();
                            }
                        });

                    },
                    error: function () {
                        $("#saveBtn").prop("disabled", false);
                        $("#saveDraftBtn").prop("disabled", false);
                        waitingSwal.close();
                        Swal.fire({
                            title: "Unable to generate Invoice at the moment.",
                            confirmButtonText: 'OK',
                            allowOutsideClick: false
                        }).then((result) => {
                            resetData();
                        });
                    }
                });
            } else if (result.isDenied) {
                $("#saveBtn").prop("disabled", false);
                $("#saveDraftBtn").prop("disabled", false);
                Swal.fire('Changes are not saved', '', 'info')
            }
        })

    }

    function customerSelectChanged() {
        var customerId = $("#customerSelect").val();
        var address = $('#customerSelect option:selected').attr('data-address');
        var gstin = $('#customerSelect option:selected').attr('data-gstin');
        var noOfCrDays = 0;
        if (customers.length > 0) {
            for (var i = 0; i < customers.length; i++) {
                if (customerId === customers[i].id) {
                    noOfCrDays = customers[i].noOfCrDays;
                }
            }
            if (!hot.isEmptyRow(0)) {
                // $('#customerSelect').prop('disabled', true);
                customerLock(true)
            } else {
                // $('#customerSelect').prop('disabled',false);
                customerLock(false)
            }
            if (customerId != null && customerId != '') {
                $('#address').html('Customer Address: ' +
                    '  <span style="font-size: 12px">' + address + '</span><br>GSTIN: ' + gstin + '')
            } else {
                $('#address').html('')
            }
        } else {
            <g:if test="${customer != null}">
            noOfCrDays = ${customer.noOfCrDays};
            </g:if>
        }

        $('#duedate').prop("readonly", false);
        $("#duedate").val(moment().add(noOfCrDays, 'days').format('YYYY-MM-DD'));
        $('#duedate').prop("readonly", true);
        calculateTaxes(); //this is to change IGST if in case out of state
        calculateTotalAmt();
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
                totalQty += parseFloat(data[i][4]);
            if (data[i][5])
                totalFQty += parseFloat(data[i][5]);
            if (data[i][11])
                totalAmt += parseFloat(data[i][11]);
            if (data[i][10])
                totalGst += parseFloat(data[i][10]);
            if (data[i][12])
                totalSgst += parseFloat(data[i][12]);
            if (data[i][13])
                totalCgst += parseFloat(data[i][13]);
            if (data[i][14])
                totalIgst += parseFloat(data[i][14]);
        }

        $("#totalAmt").text(Number(totalAmt).toFixed(2));
        $("#totalGST").text(Number(totalGst).toFixed(2));
        $("#totalSGST").text(Number(totalSgst).toFixed(2));
        $("#totalCGST").text(Number(totalCgst).toFixed(2));
        $("#totalIGST").text(Number(totalIgst).toFixed(2));
        $("#totalQty").text(Number(totalQty).toFixed(2));
        $("#totalFQty").text(Number(totalFQty).toFixed(2));
    }


    function calculateTaxes() {
        var data = hot.getData();
        for (var row = 0; row < data.length; row++) {
            var sgstAmount = Number(hot.getDataAtCell(row, 12));
            var cgstAmount = Number(hot.getDataAtCell(row, 13));
            var igstAmount = Number(hot.getDataAtCell(row, 14));
            var gstPercentage = hot.getDataAtCell(row, 21);
            var sgstPercentage = hot.getDataAtCell(row, 22);
            var cgstPercentage = hot.getDataAtCell(row, 23);
            if (stateId === '${session.getAttribute('stateId')}') {
                if (igstAmount !== 0) {
                    hot.setDataAtCell(row, 12, Number(igstAmount / 2).toFixed(2)); //SGST
                    hot.setDataAtCell(row, 13, Number(igstAmount / 2).toFixed(2)); //CGST
                    hot.setDataAtCell(row, 14, 0); //IGST

                    hot.setDataAtCell(row, 22, sgstPercentage);
                    hot.setDataAtCell(row, 23, cgstPercentage);
                    hot.setDataAtCell(row, 23, 0);
                }
            } else {
                if (sgstAmount !== 0 && cgstAmount !== 0) {
                    hot.setDataAtCell(row, 12, 0); //SGST
                    hot.setDataAtCell(row, 13, 0); //CGST
                    hot.setDataAtCell(row, 14, (sgstAmount + cgstAmount).toFixed(2)); //IGST

                    hot.setDataAtCell(row, 22, 0);
                    hot.setDataAtCell(row, 23, 0);
                    hot.setDataAtCell(row, 24, sgstPercentage + cgstPercentage);
                }
            }
        }
    }


    function checkForDuplicateEntry(batchNumber) {
        var productId = hot.getDataAtCell(mainTableRow, 1);
        var saleTableData = hot.getData();

        for (var i = 0; i < saleTableData.length; i++) {
            if (Number(productId) === Number(saleTableData[i][1])) {
                if (saleTableData[i][2] !== null && saleTableData[i][2] === batchNumber)
                    <g:if test="${settings?.ALLOW_SAME_BATCH!="YES" && settings!=null}">
                    return true;
                </g:if><g:else>
                return false;
                </g:else>
            }
        }
        return false;
    }


    function printInvoice() {
        if (readOnly) {
            %{--            <g:if test="${session.getAttribute('domainType') == Constants.FURNITURE}">--}%
            %{--            window.open(--}%
            %{--                '/sale-entry/print-invoice-furniture?id=' + saleBillId,--}%
            %{--                '_blank'--}%
            %{--            );--}%
            %{--            </g:if>--}%
            %{--            <g:else>--}%
            window.open(
                '/printRetailerEntry?id=' + saleBillId,
                '_blank'
            );
            %{--            </g:else>--}%

            resetData();
        }
    }

    function resetPage() {
        Swal.fire({
            title: "Reset Contents?",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'OK',
            allowOutsideClick: false
        }).then((result) => {
            if (result.isConfirmed) {
                resetData();
            }
        });
    }

    function resetData() {
        Swal.fire({
            title: "Reloading, Please wait!",
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });
        <g:if test="${customer}">
        location.href = "/sale-entry";
        </g:if>
        <g:else>
        location.reload();
        </g:else>
    }

    function seriesChanged() {
        seriesId = $("#series").val();
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


    function customerLock(lock) {
        var selectedId = $('#customerSelect').val();
        if (selectedId != null && selectedId !== '') {
            $('#customerSelect').prop('disabled', lock);
            if (lock) {
                $('#freezeContent').html('<small style="color: red;">Customer locked, clear products to unlock.</small>')
            } else {
                $('#freezeContent').html('')
            }
        } else {
            $('#customerSelect').prop('disabled', false);
            $('#freezeContent').html('')

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
                //'min-width': $(this.TD).outerWidth() - 4
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

    $(document).on('keyup', '.discount', function (e) {
        if ($('.discount').val() > 100) {
            Swal.fire({
                icon: 'error',
                title: 'Discount must be less than 100',
                text: 'Discount must be less than 100',
            });
            $('.discount').val(0);
        }

        if ($('.discount').val() < 0) {
            Swal.fire({
                icon: 'error',
                title: 'Discount must be greater than 0',
                text: 'Discount must be greater than 0',
            });
            $('.discount').val(0);
        }
    });


    $('#massDiscount').click(function (e) {
        var hotData = hot.getSourceData();
        var idArray = [];
        for (var i = 0; i < hotData.length; i++) {
            idArray.push(hotData[i][25])
        }
        if ($('.discount').val() > 100) {
            Swal.fire({
                icon: 'error',
                title: 'Discount must be less than 100',
                text: 'Discount must be less than 100',
            });
            $('.discount').val(0);
            return
        }
        if ($('.discount').val() < 0) {
            Swal.fire({
                icon: 'error',
                title: 'Discount must be greater than 0',
                text: 'Discount must be greater than 0',
            });
            $('.discount').val(0);
            return
        }
        <g:if test="${customer!=null}">
        $.ajax({
            type: "GET",
            url: '/update-mass-discount',
            dataType: 'json',
            data: {
                data: JSON.stringify(idArray),
                discount: $('.discount').val()
            },
            success: function (data) {
                $('#massDiscountModal').modal('hide');
                loadDraftProducts()
            },
            error: function (data) {
                Swal.fire(
                    'Oops',
                    'Something went wrong!',
                    'error'
                )
            }
        });
        </g:if>
        <g:else>
        setMassDiscount();
        $('#massDiscountModal').modal('hide');
        </g:else>

    });

    function setMassDiscount() {
        var discount = Number($('.discount').val())
        var data = hot.getData();
        for (var row = 0; row < data.length; row++) {
            hot.setDataAtCell(row, 8, discount)
            var gstPercentage = Number(hot.getDataAtCell(row, 21));
            var saleQty = Number(hot.getDataAtCell(row, 4));
            var saleRate = Number(hot.getDataAtCell(row, 6));
            var sgstPercentage = hot.getDataAtCell(row, 22);
            var cgstPercentage = hot.getDataAtCell(row, 23);

            var value = saleRate * saleQty;
            var priceBeforeGst = value - (value * discount / 100);
            var finalPrice = priceBeforeGst + (priceBeforeGst * (gstPercentage / 100));
            hot.setDataAtCell(row, 11, Number(finalPrice).toFixed(2));
            if (gstPercentage !== 0) {
                var gstAmount = priceBeforeGst * (gstPercentage / 100);
                var sgstAmount = priceBeforeGst * (sgstPercentage / 100);
                var cgstAmount = priceBeforeGst * (cgstPercentage / 100);
                hot.setDataAtCell(row, 10, Number(gstAmount).toFixed(2)); //GST
                hot.setDataAtCell(row, 12, Number(sgstAmount).toFixed(2)); //SGST
                hot.setDataAtCell(row, 13, Number(cgstAmount).toFixed(2)); //CGST
            } else {
                alert(gstPercentage);
                hot.setDataAtCell(row, 10, 0); //GST
                hot.setDataAtCell(row, 12, 0); //SGST
                hot.setDataAtCell(row, 13, 0); //CGST
            }

        }
        // calculateTaxes()
        calculateTotalAmt()
    }


    $(document).on('click', '#phoneNumber', function (e) {
        var phoneNumber = $('.phoneNumber').val();
        if (phoneNumber.length < 10 || phoneNumber.length > 10) {
            Swal.fire({
                icon: 'error',
                title: 'Enter valid number',
                text: 'Phone number must be of 10 digits',
            });
            return;
        }
        $.ajax({
            type: "GET",
            url: '/check-phone-exists',
            dataType: 'json',
            data: {
                phoneNumber: phoneNumber,
            },
            success: function (data) {
                console.log(data);
                if (data.status === false) {
                    $('#addPatientModal').modal('hide');
                    var html = '<p>' + data.obj.phoneNumber + ' (' + data.obj.entityName + ')</p>';
                    $('#patientDetails').html(html);
                    $('.customerId').val(data.obj.id);
                    $('#phoneNumberModal').modal('hide')
                    hot.selectCell(0, 1);
                    stateId = data.obj.stateId + "";
                } else {
                    // alert("Phone Number Not exists.");
                    $('#phoneNumberModal').modal('hide');
                    $('#addPatientModal').modal({backdrop: 'static', keyboard: false}, 'show');
                    $('#phone').val(phoneNumber);
                    $('#phone').prop("readonly", true);
                }
            },
            error: function (data) {

            }
        });
    });

    $("#patientRegistrationForm").submit(function (event) {
        //disable the default form submission
        event.preventDefault();
        //grab all form data
        var formData = new FormData(this);
        console.log(formData);
        $.ajax({
            url: '/patient-register',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                Swal.fire("Success!", "Patient added Successfully", "success");
                $('#addPatientModal').modal('hide');
                var html = '<p>' + data.phoneNumber + '</p>';
                $('#patientDetails').html(html);
                $('.customerId').val(data.id);
                hot.selectCell(0, 1);
                stateId = data.stateId + "";
            },
            error: function () {
                Swal.fire("Error!", "Something went wrong", "error");

            }
        });
    });


    $(document).on('keydown', function (e) {
        // You may replace `m` with whatever key you want
        if ((e.metaKey || e.ctrlKey) && (String.fromCharCode(e.which).toLowerCase() === 'm')) {
            if ($('#addPatientModal').hasClass('show') !== true) {
                $("#phoneNumberModal").modal('show');
            }
        }

        if ((e.metaKey || e.ctrlKey) && (String.fromCharCode(e.which).toLowerCase() === 'i')) {
            document.getElementById("phono").focus();
        }
    });


    $("#gstInclusive").on("change", function () {
        //change amounts to include GST
        if ($(this).is(':checked')) {
            Swal.fire({
                title: "Are you sure you want to include GST in the cost?",
                showDenyButton: true,
                showCancelButton: false,
                confirmButtonText: 'OK',
                denyButtonText: 'Cancel',
                allowOutsideClick: false
            }).then((result) => {
                if (result.isConfirmed) {
                    $(this).prop('disabled', true);
                    console.log(hot.getData());
                    if (hot.getData()[0][1] != null) {
                        costInclusiveOfGST();
                    }
                } else if (result.isDenied) {
                    $("#gstInclusive").prop('checked', false);
                }
            });

        } else {
            $(this).prop('disabled', false);
        }

    });

    function calculateGST(amount, gstPercentage) {
        return amount / (1 + gstPercentage / 100);
    }


    function costInclusiveOfGST(tableRow = null) {
        if ($("#gstInclusive").is(':checked')) {
            var data = hot.getData();
            for (var row = 0; row < data.length; row++) {
                if (tableRow != null && row !== tableRow) {
                    continue;
                }
                var gstPercentage = Number(hot.getDataAtCell(row, 21));
                var saleQty = Number(hot.getDataAtCell(row, 4));
                var saleRate = Number(hot.getDataAtCell(row, 6));
                if (hot.getActiveEditor())
                    saleRate = Number(hot.getActiveEditor().TEXTAREA.value);
                var discount = Number(hot.getDataAtCell(row, 8))
                if (discount > 0 && discount <= 100) {
                    saleRate = saleRate - (saleRate * discount / 100)
                }
                var value = Number(saleRate.toFixed(2)) * Number(saleQty)
                var revisedSaleRate = calculateGST(saleRate, gstPercentage);
                var gstAmount = (value * gstPercentage / 100);
                hot.setDataAtCell(row, 6, revisedSaleRate.toFixed(2));
                hot.setDataAtCell(row, 10, gstAmount.toFixed(2));
                hot.setDataAtCell(row, 11, value.toFixed(2));
                if (stateId === '${session.getAttribute('stateId')}') {
                    hot.setDataAtCell(row, 12, Number(gstAmount / 2).toFixed(2)); //SGST
                    hot.setDataAtCell(row, 13, Number(gstAmount / 2).toFixed(2)); //CGST
                    hot.setDataAtCell(row, 14, 0); //IGST
                    hot.setDataAtCell(row, 24, 0);
                } else {
                    hot.setDataAtCell(row, 12, 0); //SGST
                    hot.setDataAtCell(row, 13, 0); //CGST
                    hot.setDataAtCell(row, 14, (gstAmount).toFixed(2)); //IGST
                    hot.setDataAtCell(row, 22, 0);
                    hot.setDataAtCell(row, 23, 0);
                }
            }

            calculateTotalAmt();
        }
    }

    $('.pinCode').select2({
        placeholder: 'Enter Pincode',
        minimumInputLength: 6,
        required: true,
        ajax: {
            url: '/getcitybypincode',
            dataType: 'json',
            delay: 250,
            data: function (term) {
                return {
                    pincode: term // search term
                };
            },
            results: function (response) {
                var data = [];
                response.forEach(function (response, index) {
                    data.push({"pincode": response.pincode, "text": response.areaName, "id": response.id});
                });
                return {
                    results: data
                };
            },
            cache: true
        }
    });

    function uploadAttachment()
    {
        const fileInput = document.getElementById('attachment');
        const file = fileInput.files[0];

        if (!file) {
            Swal.fire('Error', 'Please select a file.', 'error');
            return;
        }

        // Ask the user for confirmation
        Swal.fire({
            title: 'Confirm Upload',
            text: 'Do you want to upload the selected file?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Upload',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.isConfirmed) {
                // Display uploading popup
                Swal.fire({
                    title: 'Uploading...',
                    text: 'Please wait while the file is being uploaded.',
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    onBeforeOpen: () => {
                        // Initiate the AJAX request to upload the file
                        const formData = new FormData();
                        formData.append('file', file);

                        // Replace 'your_upload_url' with the actual upload URL
                        fetch('files/upload', {
                            method: 'POST',
                            body: formData,
                            headers: {
                                'Accept': 'application/json'
                            }
                        }).then((response) => {
                                if (response.ok) {
                                    response.json().then((data) => {
                                        Swal.fire('Success', 'File uploaded successfully!', 'success');
                                        $("#FileUploadedContainer").removeClass("hidden");
                                        $("#FileUploadContainer").addClass("hidden");
                                        $("#uploadedAttachment").val(data.fileName);
                                    });

                                } else {
                                    Swal.fire('Error', 'File upload failed.', 'error');
                                    $("#FileUploadContainer").removeClass("hidden");
                                    $("#FileUploadedContainer").addClass("hidden");
                                    $("#uploadedAttachment").val("");
                                    $("#attachment").val("");
                                }
                            })
                            .catch((error) => {
                                Swal.fire('Error', 'An error occurred while uploading the file.', 'error');
                                $("#FileUploadContainer").removeClass("hidden");
                                $("#FileUploadedContainer").addClass("hidden");
                                $("#uploadedAttachment").val("");
                                $("#attachment").val("");
                            })
                            .finally(() => {
                                // Close the uploading popup
                                //Swal.close();
                            });
                    }
                });
            }
        });
    }

    function downloadFile()
    {
        var uploadedFileName = $("#uploadedAttachment").val();
        if (uploadedFileName == null || uploadedFileName.length < 2) {
            Swal.fire('Error', 'File not uploaded', 'error');
            return;
        }

        window.open("files/download?filename="+uploadedFileName);

    }

    function deleteFile()
    {
        var uploadedFileName = $("#uploadedAttachment").val();
        if (uploadedFileName == null || uploadedFileName.length < 2) {
            Swal.fire('Error', 'File not uploaded', 'error');
            return;
        }

        // Ask the user for confirmation
        Swal.fire({
            title: 'Delete file?',
            text: 'Do you want to delete the uploaded file?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Delete',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.isConfirmed) {

                fetch('files/delete?filename='+uploadedFileName, {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json'
                    }
                }).then((response) => {
                    if (response.ok) {
                        Swal.fire('Success', 'File deleted', 'success');
                        $("#FileUploadContainer").removeClass("hidden");
                        $("#FileUploadedContainer").addClass("hidden");
                        $("#uploadedAttachment").val("");
                        $("#attachment").val("");
                    }
                    else
                    {
                        Swal.fire('Error', 'An error occurred while deleting the file.', 'error');
                    }
                });

            }
        })
    }
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>