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

    .colHeader {
        font-weight: bold;
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
                        <li class="breadcrumb-item active">Purchase Entry</li>
                    </ul>
                </div>

                %{-- <div class="col-lg-7 col-md-7 col-sm-12">
                     <div class="input-group m-b-0">
                         <input type="text" class="form-control" placeholder="Search...">
                         <span class="input-group-addon">
                             <i class="zmdi zmdi-search"></i>
                         </span>
                     </div>
                 </div>--}%
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="header" style="padding: 1px;">

                    </div>

                    <div class="body">
                        <div class="row">
                            <div class="col-md-3">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control date" name="date" id="date"/>
                            </div>

                            <div class="col-md-3">
                                <label for="series">Series:</label>
                                <select class="form-control" id="series" name="series">
                                    <g:each in="${series}" var="sr">
                                        <option value="${sr.id}">${sr.seriesCode}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label for="customerSelect">Supplier:</label>
                                <select class="form-control show-tick" id="customerSelect"
                                        onchange="customerSelectChanged()">
                                    <option selected disabled>--SELECT--</option>
                                    <g:each in="${suppliers}" var="cs">

                                        <g:if test="${cs.id != session.getAttribute("entityId")}">
                                            <option value="${cs.id}">${cs.entityName} (${cs.entityType.name})</option>
                                        </g:if>
                                    </g:each>
                                </select>
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
            <div class="col-lg-12">
                <div class="card" style="margin-bottom:10px;">
                    <div class="body">
                        <div class="table-responsive">
                            <div id="saleTable" style="width:100%;"></div>
                        </div>
                    </div>

                    %{--<div class="header" style="padding: 1px;">
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                                data-target="#addAccountModeModal"><span
                                class="glyphicon glyphicon-save-file"></span> Save</button>
                    </div>--}%
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12" style="margin-bottom: 10px;">
                <p style="margin: 0; font-size: 10px;">Keyboard Shortcuts - Delete Row: <strong>Ctrl+Alt+D</strong>, Reset Table: <strong>Ctrl+Alt+R</strong>
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
                                Total: Rs. <p id="totalAmt">0</p>
                            </div>

                            <div class="col-md-6">
                                Inv No: <span class="invno"></span>
                            </div>
                        </div>

                        <div class="row">
                            <button class="btn btn-primary save">Save</button>
                            <button class="btn btn-secondary print">Print</button>
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
<asset:javascript src="/themeassets/plugins/printThis/printThis.js"/>
%{--<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js"/>--}%
<script src="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>


<script>

    // var idArray = [];
    // (sRate * sQty) - ((sRate * sQty) * discount) / 100;
    // priceBeforeGst + (priceBeforeGst * (gst / 100));

    var idArray = [];
    var data = [];
    <g:each in="${tempStockBook.reverse()}" var="sb">
    idArray.push(${sb.id});
    data.push(['', ${sb.productId}, '${sb.batchNumber}', moment(new Date('${sb.expDate}')).format('YYYY-MM-DD'), '${sb.userOrderQty}', '${sb.userOrderFreeQty}', ${sb.purchaseRate}, ${sb.mrp}, '0', ${sb.packingDesc}, '1', ${(sb.purchaseRate * sb.userOrderQty) - ((sb.purchaseRate * sb.userOrderQty) * 0)/100 +(((sb.purchaseRate * sb.userOrderQty) - ((sb.purchaseRate * sb.userOrderQty) * 0)/100) * (10/100))}, '1', '1', '1'])


    </g:each>


    var totalAmt = 0;
    var series = [];
    var products = [];
    var hsnCode = [];
    var customers = [];
    /* var accountMode = [];
     var customer = [];
     var salesman = [];
     var user = [];*/

    <g:each in="${products}" var="pd">
    products.push({id:${pd.id}, text: '${pd.productName}'});
    </g:each>

    <g:each in="${products}" var="pd">
    hsnCode.push({id:${pd.id}, text: '${pd.hsnCode}'});
    </g:each>

    <%-- <g:each in="${series}" var="s">
     series.push({id:${s.id}, text: '${s.seriesName}'});
     </g:each>
     <g:each in="${accountMode}" var="am">
     accountMode.push({id:${am.id}, text: '${am.mode}'});
     </g:each>
     <g:each in="${customerList}" var="cl">
     customer.push({id:${cl.id}, text: '${cl.entityName}'});
     </g:each>
     <g:each in="${salesmanList}" var="sl">
     salesman.push({id:${sl.id}, text: '${sl.userName}'});
     </g:each>
     console.log(salesman);
     <g:each in="${users}" var="u">
     user.push({id:${u.id}, text: '${u.userName}'});
     </g:each> --%>

    var headerRow = [
        '</strong>',
        'Product',
        'Batch',
        'Exp Dt',
        'Purchase Qty',
        'Free Qty',
        'Purchase Rate',
        'MRP',
        'Discount',
        'Pack',
        'GST',
        'Value',
        'SGST',
        'CGST',
        'IGST'];

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
        'IGST'];

    const batchContainer = document.getElementById('batchTable');
    var batchHot;
    var hot;
    var batchData = [];
    var mainTableRow = 0;
    $(document).ready(function () {
        //$("#customerSelect").select2();
        $('#date').val(moment().format('YYYY-MM-DD'));
        $('#date').attr("readonly");
        <g:each in="${customers}" var="cs">
        customers.push({"id": ${cs.id}, "noOfCrDays": ${cs.noOfCrDays}});
        </g:each>
        const container = document.getElementById('saleTable');
        hot = new Handsontable(container, {
            data: data,
            minRows: 0,
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
                    },
                },
                {type: 'text', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'text', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true},
                {type: 'numeric', readOnly: true}
            ],
            minSpareRows: 1,
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
                    this.alter("remove_row", coords.row);
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
            beforeKeyDown(e, changes) {
                var sRate = 0;
                var sQty = 0;
                const row = hot.getSelected()[0];
                const selection = hot.getSelected()[1];
                if (selection === 1) {
                    if (e.keyCode === 13) {
                        batchHot.selectCell(0, 0);
                        $("#batchTable").focus();
                    }
                } else if (selection === 14) {
                    if (e.keyCode === 13 || e.keyCode === 9) {
                        //check if sqty is empty
                        var sqty = hot.getDataAtCell(row, 4);
                        if (sqty) {
                            mainTableRow = row + 1;
                            hot.alter('insert_row');
                            hot.selectCell(mainTableRow, 1);
                            calculateTotalAmt();
                        } else {
                            alert("Please enter Quantity");
                        }
                    }

                    // alert(hot.getDataAtRow(row))
                    // var rowThatHasBeenChanged = hot.getDataAtRow(row)
                    var visualObjectRow = function (row) {
                        var obj = {},
                            key, name;
                        for (var i = 0; i < hot.countCols(); i++) {
                            obj[hot.colToProp(i)] = hot.getDataAtCell(row, i);
                        }
                        return obj
                    };
                    var json = {};
                    var changedRow = visualObjectRow(row);
                    // console.log(visualObjectRow(rowThatHasBeenChanged))
                    Object.keys(changedRow).forEach(function (key) {
                        json[headerRow[key].replace(/\s+/g, '')] = changedRow[key]
                    });

                    // if (idArray[row] !== undefined) {
                    //     json['id'] = idArray[row]
                    // } else {
                    //     json['id'] = null
                    // }
                    // console.log(json);

                    var url = '';
                    var type = '';
                    var id = json['id'];
                    // if (idArray[rowThatHasBeenChanged] != undefined) {
                    //     url = '/sale-entry/update/' + id;
                    //     type = 'POST'
                    // } else {
                    url = '/tempstockbook';
                    type = 'POST'
                    // }
                    $.ajax({
                        type: type,
                        url: url,
                        dataType: 'json',
                        data: {
                            json: json
                        },
                        success: function (data) {
                            console.log("Data saved");
                        },
                        error: function (data) {
                            console.log("Failed");
                        }
                    });

                } else if (selection === 4) {
                    if (e.keyCode === 13 || e.keyCode === 9) {
                        sQty = this.getActiveEditor().TEXTAREA.value;
                        // sQty = hot.getDataAtCell(row,selection);
                        sRate = hot.getDataAtCell(row, 6);
                        var discount = hot.getDataAtCell(row, 8);
                        var gst = hot.getDataAtCell(row, 10);
                        var priceBeforeGst = (sRate * sQty) - ((sRate * sQty) * discount) / 100;
                        var finalPrice = priceBeforeGst + (priceBeforeGst * (gst / 100));
                        hot.setDataAtCell(row, 11, finalPrice);
                    }
                }
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

            //$('#selectedId').text(selectedId);
        }

        /* function updateCell(hot, selectedId, value, row) {
             hot.setDataAtCell(row, 2, value);
             $("#productNameTitle").text("");
             $("#batchSelectModal").modal("toggle");
         }*/


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
                {type: 'text', readOnly: true}
            ],
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
                    hot.setDataAtCell(mainTableRow, 2, rowData[0]);
                    hot.setDataAtCell(mainTableRow, 3, rowData[1]);
                    hot.setDataAtCell(mainTableRow, 5, 0);
                    hot.setDataAtCell(mainTableRow, 6, rowData[5]);
                    hot.setDataAtCell(mainTableRow, 7, rowData[6]);
                    hot.setDataAtCell(mainTableRow, 8, 0);
                    hot.setDataAtCell(mainTableRow, 9, rowData[7]);
                    hot.setDataAtCell(mainTableRow, 10, rowData[8]);
                    hot.setDataAtCell(mainTableRow, 12, rowData[9]);
                    hot.setDataAtCell(mainTableRow, 13, rowData[10]);
                    hot.setDataAtCell(mainTableRow, 14, rowData[11]);
                    hot.selectCell(mainTableRow, 3);
                    $("#saleTable").focus();

                }
            }
        });
    });

    function batchSelection(selectedId, mainRow) {
        if (selectedId != null) {
            var url = "/stockbook/product/" + selectedId;
            //var url = "/tempstockbook/product/"+ selectedId;
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'json',
                success: function (data) {
                    console.log(data)
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
                            batchData.push(batchdt);
                        }
                        batchHot.updateSettings({
                            data: []
                        });
                        if (batchdt?.length > 0) {
                            batchHot.loadData(batchData);
                            $("#batchTable").focus();
                            batchHot.selectCell(0, 0);
                        }
                    }
                },
                error: function (data) {
                    console.log("Failed");
                }
            });

            /* $("#productNameTitle").text(value);
             $("#batchSelectModal").modal("toggle");*/
        }
    }

    function customerSelectChanged() {
        var noOfCrDays = 0;
        var customerId = $("#customerSelect").val();
        for (var i = 0; i < customers.length; i++) {
            if (customerId === customers[i].id) {
                noOfCrDays = customers[i].noOfCrDays;
            }
        }
        $('#duedate').prop("readonly", false);
        $("#duedate").val(moment().add(noOfCrDays, 'days').format('YYYY-MM-DD'));
        $('#duedate').prop("readonly", true);
    }

    function calculateTotalAmt() {
        totalAmt = 0;
        var data = hot.getData();
        for (var i = 0; i < data.length; i++) {
            if (data[i][11])
                totalAmt += data[i][11]
        }
        $("#totalAmt").text(totalAmt);
    }

    var visualObjectRow = function (row) {
        var obj = {},
            key, name;
        for (var i = 0; i < hot.countCols(); i++) {
            obj[hot.colToProp(i)] = hot.getDataAtCell(row, i);
        }
        return obj
    };


    function getVisualTableData() {
        const tableData = [];
        for (let i = 0; i < this.hot.countRows(); i++) {
            tableData.push(visualObjectRow(i));
        }
        return tableData;
    }

    $(".save").on('click', function (event) {
        var batch = [];
        var data = hot.getData();
        for (var i = 0; i < data.length; i++) {
            if (data[i][2])
                batch.push({'batch': data[i][2], 'purQty': data[i][4]})
        }
        var batchDataString = JSON.stringify(batch);
        var batchData = JSON.parse(batchDataString);
        var url ="/stockbook/purchase";
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'json',
            data: {
                json: batchData
            },
            success: function (data) {
               swal("Data saved");
            },
            error: function (data) {
                console.log("Failed");
            }
        });
    });


    document.addEventListener("keydown", function (event) {
        var ctrl = event.ctrlKey;
        var alt = event.altKey;
        var key = event.key;
        console.log(ctrl + " " + alt + " " + key);
        if (ctrl) {
            if (alt) {
                var result = false;
                if (key === 'd' || key === 'ḍ') {
                    result = confirm("Delete this row?");
                    if (result) {
                        const selection = hot.getSelected()[0];
                        hot.alter("remove_row", selection);
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
            if (!$(event.target).hasClass('select2-input') || event.isImmediatePropagationStopped()) {
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


    function printInvoice() {
        /* window.addEventListener('load', function () {*/
        $('#invoiceprint').printThis({
            importCSS: true,
            printDelay: 2000,
            importStyle: true,
            base: "",
            pageTitle: ""
        });
    }

    $(".print").click(function(){
        $("#printabel").remove();
        purchasePrint()
    });

    function purchasePrint() {
        $("<iframe id='printabel'>")
            .hide()
            .attr("src", "/purchase-retrun")
            .appendTo("body");
    }

</script>
</body>
</html>