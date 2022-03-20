<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Sale Entry</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>

    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/select2/dist/css/select2.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">
    <link rel="stylesheet" media="screen"
          href="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.css">

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
                                 alt="Alpino"></div>

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
                        <li class="breadcrumb-item active">Sale Entry</li>
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
                            <div class="col-md-2">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control date" name="date" id="date"/>
                            </div>

                            <div class="col-md-2">
                                <label for="series">Series:</label>
                                <select onchange="seriesChanged()" class="form-control" id="series" name="series">
                                    <option selected disabled>--SELECT--</option>
                                    <g:each in="${series}" var="sr">
                                        <option value="${sr.id}">${sr.seriesName} (${sr.seriesCode})</option>
                                    </g:each>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label for="priority">Priority:</label>
                                <select class="form-control" id="priority" name="priority">
                                    <g:each in="${priorityList}" var="pr">
                                        <option value="${pr.id}">${pr.priority}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label for="customerSelect">Customer:</label>
                                <select class="form-control show-tick" id="customerSelect"
                                        onchange="customerSelectChanged()">
                                    <option selected disabled>--SELECT--</option>
                                    <g:each in="${customers}" var="cs">

                                        <g:if test="${cs.id != session.getAttribute("entityId")}">
                                            <option value="${cs.id}">${cs.entityName} (${cs.entityType.name})</option>
                                        </g:if>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-2">
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
                            <div class="col-md-2"><strong>Total GST:</strong> ₹<span id="totalGST">0</span></div>
                            <div class="col-md-2"><strong>Total SGST:</strong> ₹<span id="totalSGST">0</span></div>
                            <div class="col-md-2"><strong>Total CGST:</strong> ₹<span id="totalCGST">0</span></div>
                            <div class="col-md-2"><strong>Total IGST:</strong>&nbsp; ₹<span id="totalIGST">0</span></div>
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
                            <div id="saleTable" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-4" style="margin-bottom: 10px;">
                <p style="margin: 0; font-size: 10px;">Keyboard Shortcuts - Delete Row: <strong>Ctrl+Alt+D</strong>, Reset Table: <strong>Ctrl+Alt+R</strong>
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
                                Total: <p>₹ <span id="totalAmt">0</span></p>
                            </div>

                            <div class="col-md-6">
                                Inv No: <span id="invNo"></span>
                            </div>
                        </div>

                        <div class="row">
                            <button onclick="saveSaleInvoiceDraft()" class="btn btn-primary">Save Draft</button>
                            <button onclick="saveSaleInvoice()" class="btn btn-primary">Save</button>
                            <button onclick="printInvoice()" class="btn btn-secondary">Print</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</section>

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
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
%{--<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js"/>--}%
<script src="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<script>
    var totalAmt = 0;
    var series = [];
    var products = [];
    var customers = [];
    var readOnly = false;
    var scheme = null;
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
        '<strong>GST</strong>',
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
    $(document).ready(function () {
        $("#customerSelect").select2();
        $('#date').val(moment().format('YYYY-MM-DD'));
        $('#date').attr("readonly");
        <g:each in="${customers}" var="cs">
        customers.push({"id": ${cs.id}, "noOfCrDays": ${cs.noOfCrDays}});
        </g:each>
        const container = document.getElementById('saleTable');
        hot = new Handsontable(container, {
            data: saleData,
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
                        width: 'auto'
                    }
                },
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric'},
                {type: 'text', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'text', readOnly: true}
            ],
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
        hot.selectCell(0, 1);
        hot.updateSettings({
            beforeKeyDown(e) {
                var sRate = 0;
                var sQty = 0;
                const row = hot.getSelected()[0];
                const selection = hot.getSelected()[1];
                if (selection === 1) {
                    if (e.keyCode === 13) {
                        batchHot.selectCell(0, 0);
                        $("#batchTable").focus();
                    }
                } else if (selection === 14 || selection === 5) {
                    if ((e.keyCode === 13 || e.keyCode === 9) && !readOnly) {
                        //check if sqty is empty
                        var sqty = hot.getDataAtCell(row, 4);
                        var fqty = hot.getDataAtCell(row, 5);
                        if (sqty && sqty>0)
                        {
                            mainTableRow = row + 1;
                            hot.alter('insert_row');
                            hot.selectCell(mainTableRow, 1);
                            calculateTotalAmt();
                            var batchId = hot.getCellMeta(row, 2)?.batchId; //batch
                            var dt = hot.getDataAtRow(row);
                            dt.push(batchId);
                            var json = JSON.stringify(dt);
                            var url = '/tempstockbook';
                            var type = 'POST';
                            $.ajax({
                                type: type,
                                url: url,
                                dataType: 'json',
                                data: {
                                    rowData: json
                                },
                                success: function (data) {
                                    console.log("Data saved");
                                    hot.setDataAtCell(row, 15, data.id)
                                },
                                error: function (data) {
                                    console.log("Failed");
                                }
                            });
                        } else {
                            alert("Invalid Quantity, please enter quantity greater than 0");
                        }
                    }
                } else if (selection === 4 || selection === 8) {
                    if (e.keyCode === 13 || e.keyCode === 9) {
                        var discount = 0;

                        if(selection === 4)
                            sQty = this.getActiveEditor().TEXTAREA.value;
                        else
                            sQty = hot.getDataAtCell(row,4);

                        if(selection === 8) {
                            discount = this.getActiveEditor().TEXTAREA.value;
                            if(discount>100)
                            {
                                hot.setDataAtCell(row,8,0);
                                this.getActiveEditor().TEXTAREA.value = 0;
                                alert("Invalid Discount");
                                hot.selectCell(row,8);
                                return;
                            }
                        }
                        else
                            sQty = hot.getDataAtCell(row,8);



                        applySchemes(row, sQty);
                        sRate = hot.getDataAtCell(row, 6);

                        var value = sRate * sQty;
                        var priceBeforeGst = value - (value * discount/100);
                        var finalPrice = priceBeforeGst + (priceBeforeGst * (gst / 100));
                        hot.setDataAtCell(row, 11, finalPrice);

                        if(gst != 0) {
                            hot.setDataAtCell(row, 10, priceBeforeGst * (gst / 100)); //GST
                            hot.setDataAtCell(row, 12, priceBeforeGst * (sgst / 100)); //SGST
                            hot.setDataAtCell(row, 13, priceBeforeGst * (cgst / 100)); //CGST
                        }
                        else
                        {
                            hot.setDataAtCell(row, 10, 0); //GST
                            hot.setDataAtCell(row, 12, 0); //SGST
                            hot.setDataAtCell(row, 13, 0); //CGST
                        }
                        if(igst != "0")
                            hot.setDataAtCell(row, 14, priceBeforeGst*(igst/100)); //IGST
                        else
                            hot.setDataAtCell(row, 14, 0);
                    }
                }
            }
        });

        hot.addHook('afterSelection', (row, col) => {
            if(col == 2) {
                batchSelection(hot.getDataAtCell(row,1),row,false);
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
            Handsontable.TextCell.renderer.apply(this, arguments);
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
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true}
            ],
            hiddenColumns: {
                // specify columns hidden by default
                columns: [11]
            },
            minSpareRows: 0,
            minSpareCols: 0,
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation'
        });

        batchHot.updateSettings({
            beforeKeyDown(e) {
                const selection = batchHot.getSelected()[0];
                var rowData = batchHot.getDataAtRow(selection);
                console.log(rowData);
                if (e.keyCode === 13) {

                    if(!checkForDuplicateEntry(rowData[0])) {
                        //check for schemes
                        checkSchemes(hot.getDataAtCell(mainTableRow,1), rowData[0]); //product, batch
                        var batchId = rowData[12];
                        hot.setDataAtCell(mainTableRow, 2, rowData[0]);
                        hot.setCellMeta(mainTableRow, 2, "batchId", batchId);

                        hot.setDataAtCell(mainTableRow, 3, rowData[1]);
                        hot.setDataAtCell(mainTableRow, 5, 0);
                        hot.setDataAtCell(mainTableRow, 6, rowData[5]);
                        hot.setDataAtCell(mainTableRow, 7, rowData[6]);
                        hot.setDataAtCell(mainTableRow, 8, 0);
                        hot.setDataAtCell(mainTableRow, 9, rowData[7]);
                        gst = rowData[8];
                        sgst = rowData[9];
                        cgst = rowData[10];
                        igst = rowData[11];
                        hot.selectCell(mainTableRow, 4);
                        $("#saleTable").focus();
                    }
                    else
                    {
                        alert("Selected product and batch already entered, duplicate entries not allowed");
                    }
                }
            }
        });
    });

    function batchSelection(selectedId, mainRow, selectCell = true) {
        if (selectedId != null) {
            var url = "/stockbook/product/" + selectedId;
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
                        batchHot.updateSettings({
                            data: []
                        });
                        if (batchdt?.length > 0) {
                            batchHot.loadData(batchData);
                            $("#batchTable").focus();
                            if(selectCell)
                                batchHot.selectCell(0, 0);
                        }
                    }
                },
                error: function (data) {
                    console.log("Failed");
                }
            });
        }
    }



    function customerSelectChanged() {
        var noOfCrDays = 0;
        var customerId = $("#customerSelect").val();
        for (var i = 0; i < customers.length; i++) {
            if (customerId == customers[i].id) {
                noOfCrDays = customers[i].noOfCrDays;
            }
        }
        $('#duedate').prop("readonly", false);
        $("#duedate").val(moment().add(noOfCrDays, 'days').format('YYYY-MM-DD'));
        $('#duedate').prop("readonly", true);
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
                totalQty += data[i][4];
            if (data[i][5])
                totalFQty += data[i][5];
            if (data[i][11])
                totalAmt += data[i][11];
            if (data[i][10])
                totalGst += data[i][10];
            if (data[i][12])
                totalSgst += data[i][12];
            if (data[i][13])
                totalCgst += data[i][13];
            if (data[i][14])
                totalIgst += data[i][14];
        }
        $("#totalAmt").text(totalAmt);
        $("#totalGST").text(totalGst);
        $("#totalSGST").text(totalSgst);
        $("#totalCGST").text(totalCgst);
        $("#totalIGST").text(totalIgst);
        $("#totalQty").text(totalQty);
        $("#totalFQty").text(totalFQty);
    }


    function checkForDuplicateEntry(batchNumber)
    {
        var productId = hot.getDataAtCell(mainTableRow, 1);
        var saleTableData = hot.getData();
        for(var i = 0; i<saleTableData.length; i++)
        {
            if(productId == saleTableData[i][1])
            {
                if(saleTableData[i][2] !== undefined && saleTableData[i][2] == batchNumber)
                    return true;
            }
        }
        return false;
    }

    function loadTempStockBookData()
    {
        var userId = "${session.getAttribute("userId")}";
        $.ajax({
            type: "GET",
            url: "tempstockbook/user/"+userId,
            dataType: 'json',
            success: function (data) {
                saleData = data;

                for(var i = 0; i < saleData.length; i++) {
                    hot.selectCell(i, 1);
                    var sRate = saleData[i]["saleRate"];
                    var sQty = saleData[i]["userOrderQty"];
                    batchSelection(saleData[i]["productId"], null, false);
                    var batchId = saleData[i][12];
                    hot.setDataAtCell(i, 1, saleData[i]["productId"]);

                    hot.setDataAtCell(i, 2, saleData[i]["batchNumber"]);
                    hot.setCellMeta(i, 2, "batchId", batchId);

                    hot.setDataAtCell(i, 3, saleData[i]["expDate"].split("T")[0]);
                    hot.setDataAtCell(i, 5, 0);
                    hot.setDataAtCell(i, 6, sRate);
                    hot.setDataAtCell(i, 4, sQty);
                    hot.setDataAtCell(i, 7, saleData[i]["mrp"]);
                    hot.setDataAtCell(i, 8, 0);
                    hot.setDataAtCell(i, 9, saleData[i]["packingDesc"]);
                    gst = saleData[i]["gst"];
                    sgst = saleData[i]["sgst"];
                    cgst = saleData[i]["cgst"];
                    igst = saleData[i]["igst"];

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

                    hot.setDataAtCell(i, 15, saleData[i].id)
                }

                setTimeout(function () {
                    hot.selectCell(0, 1);
                    calculateTotalAmt();
                },1000);

            }
        })
    }

    function deleteTempStockRow(id, row)
    {
        if(!readOnly) {
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
        else
            alert("Can't change this now, invoice has been saved already.")
    }

    var saleBillId = 0;
    function saveSaleInvoice()
    {
        var customer = $("#customerSelect").val();
        var series = $("#series").val();
        var duedate = $("#duedate").val();
        duedate = moment(duedate, 'YYYY-MM-DD').toDate();
        duedate = moment(duedate).format('DD/MM/YYYY');
        var priority = $("#priority").val();

        if(!series) {
            alert("Please select series.");
            return;
        }

        if(!customer) {
            alert("Please select customer.");
            return;
        }

        var saleData = JSON.stringify(hot.getData());

        $.ajax({
            type: "POST",
            url: "sale-entry",
            dataType: 'json',
            data:{
                saleData: saleData,
                customer:customer,
                series:series,
                duedate:duedate,
                priority:priority
            },
            success: function (data) {
                readOnly = true;
                var rowData = hot.getData();
                for(var j = 0; j < rowData.length;j++) {
                    for (var i = 0; i < 16; i++) {
                      hot.setCellMeta(j, i,'readOnly', true);
                    }
                }
                saleBillId = data.saleBillDetail.id;
                swal("Success", "Sale Invoice Generated", "");
                var datepart = data.saleBillDetail.entryDate.split("T")[0];
                var month = datepart.split("-")[1];
                var year = datepart.split("-")[0];
                var seriesCode = data.series.seriesCode;
                var invoiceNumber = "S/"+month+year+"/"+seriesCode+"/"+data.saleBillDetail.serBillId;
                $("#invNo").html("<p><strong>"+invoiceNumber+"</strong></p>");
            }
        });

    }

    function printInvoice()
    {
        if(readOnly) {
            window.open(
                'sale-entry/print-invoice?id=' + saleBillId,
                '_blank'
            );
        }
    }

    function seriesChanged()
    {
        var series = $("#series").val();
        loadProducts(series);

    }

    function loadProducts(series)
    {
        products.length = 0;//remove all elements
        $.ajax({
            type: "GET",
            url: "/product/series/"+series,
            dataType: 'json',
            success: function (data) {
                for(var i=0; i<data.length;i++) {
                    products.push({id:data[i].id, text: data[i].productName});
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
            data:{
                productId: productId,
                batchNumber: batchNumber
            },
            dataType: 'json',
            success: function (data) {
                scheme = data;
                var offers = "";

                if(data.slab1Status == 1)
                {
                    offers = "S1: "+data.slab1MinQty + "+" + data.slab1SchemeQty;
                }

                if(data.slab2Status == 1)
                {
                    offers += " | S2: "+data.slab2MinQty + "+" + data.slab2SchemeQty;
                }

                if(data.slab3Status == 1)
                {
                    offers += " | S3: "+data.slab3MinQty + "+" + data.slab3SchemeQty;
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
            if(saleQty>=scheme.slab1MinQty && saleQty<scheme.slab2MinQty)
            {
                if(scheme.slab1Status == 1) {
                    if(scheme.slab2BulkStatus == 1) {
                        var slab1Multiplier = Math.floor(parseInt(saleQty)/scheme.slab1MinQty);
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;
                        hot.setDataAtCell(row, 5, slab1Qty);
                    }
                    else
                    {
                        hot.setDataAtCell(row, 5, scheme.slab1SchemeQty);
                    }
                }
                else
                    hot.setDataAtCell(row, 5, 0);
            }
            else if(saleQty>=scheme.slab2MinQty && saleQty<scheme.slab3MinQty)
            {
                if(scheme.slab2Status == 1) {
                    if(scheme.slab2BulkStatus == 1)
                    {

                        var slab2Multiplier = Math.floor(parseInt(saleQty)/scheme.slab2MinQty);
                        var slab2Qty = slab2Multiplier * scheme.slab2MinQty;
                        var slb2RemQty = saleQty - slab2Qty;

                        var slab1Multiplier = Math.floor(parseInt(slb2RemQty)/scheme.slab1MinQty);
                        var slab2Qty = scheme.slab2SchemeQty * slab2Multiplier;
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;

                        hot.setDataAtCell(row, 5,slab2Qty+slab1Qty);
                    }
                    else {
                        hot.setDataAtCell(row, 5,  scheme.slab2SchemeQty);
                    }
                }
                else
                    hot.setDataAtCell(row, 5, 0);
            }
            else if(saleQty>=scheme.slab3MinQty)
            {
                if(scheme.slab3Status == 1) {
                    if(scheme.slab3BulkStatus == 1)
                    {
                        var slab3Multiplier = Math.floor(parseInt(saleQty)/scheme.slab3MinQty);
                        var slab3Qty = slab3Multiplier*scheme.slab3MinQty;
                        var slb3RemQty = saleQty - slab3Qty;

                        var slab2Qty = 0;
                        var slb2RemQty = 0;
                        var slab2Multiplier = Math.floor(parseInt(slb3RemQty)/scheme.slab2MinQty);
                        if(slab2Multiplier > 0) {
                            slab2Qty = slab2Multiplier * scheme.slab2MinQty;
                            slb2RemQty = slb3RemQty - slab2Qty;
                        }
                        else
                        {
                            slb2RemQty = slb3RemQty;
                        }

                        var slab1Multiplier = Math.floor(parseInt(slb2RemQty)/scheme.slab1MinQty);

                        slab3Qty = scheme.slab3SchemeQty * slab3Multiplier;
                        slab2Qty = scheme.slab2SchemeQty * slab2Multiplier;
                        var slab1Qty = scheme.slab1SchemeQty * slab1Multiplier;

                        hot.setDataAtCell(row, 5, slab3Qty+slab2Qty+slab1Qty);
                    }
                    else {
                        hot.setDataAtCell(row, 5, scheme.slab3SchemeQty);
                    }
                }
                else
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
                if (key === 'd' || key === 'ḍ') {
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

            Handsontable.Dom.addClass(this.TEXTAREA, 'handsontableInput');

            this.textareaStyle = this.TEXTAREA.style;
            this.textareaStyle.width = 0;
            this.textareaStyle.height = 0;

            this.TEXTAREA_PARENT = document.createElement('DIV');
            Handsontable.Dom.addClass(this.TEXTAREA_PARENT, 'handsontableInputHolder');

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

            var keyCodes = Handsontable.helper.keyCode;
            var ctrlDown = (event.ctrlKey || event.metaKey) && !event.altKey; //catch CTRL but not right ALT (which in some systems triggers ALT+CTRL)


            //Process only events that have been fired in the editor
            if (!$(event.target).hasClass('select2-input') || event?.isImmediatePropagationStopped()) {
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
                    if (Handsontable.Dom.getCaretPosition(target) !== target.value.length) {
                        event.stopImmediatePropagation();
                    } else {
                        that.$textarea.select2('close');
                    }
                    break;

                case keyCodes.ARROW_LEFT:
                    if (Handsontable.Dom.getCaretPosition(target) !== 0) {
                        event.stopImmediatePropagation();
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
                        event.stopImmediatePropagation();
                    }
                    event.preventDefault(); //don't add newline to field
                    break;

                case keyCodes.A:
                case keyCodes.X:
                case keyCodes.C:
                case keyCodes.V:
                    if (ctrlDown) {
                        event.stopImmediatePropagation(); //CTRL+A, CTRL+C, CTRL+V, CTRL+X should only work locally when cell is edited (not in table context)
                    }
                    break;

                case keyCodes.BACKSPACE:
                case keyCodes.DELETE:
                case keyCodes.HOME:
                case keyCodes.END:
                    event.stopImmediatePropagation(); //backspace, delete, home, end should only work locally when cell is edited (not in table context)
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
                'min-width': $(this.TD).outerWidth() - 4
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
                self.$textarea.select2('search', keyText);
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
            //Handsontable.editors.TextEditor.prototype.focus.apply(this, arguments);
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
</script>
</body>
</html>