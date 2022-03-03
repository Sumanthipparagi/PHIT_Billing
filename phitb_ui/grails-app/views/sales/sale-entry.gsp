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
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <link rel="stylesheet" media="screen"
          href="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.css" rel="stylesheet"/>

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
                    <h2>Sale Entry</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Sale Entry</li>
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

        <div class="row clearfix">
            <div class="col-lg-6">
                <div class="card">
                    <div class="header" style="padding: 1px;">

                    </div>

                    <div class="body">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="date">Date:</label>
                                <input type="date" class="form-control date" name="date" id="date"/>
                            </div>
                            <div class="col-md-6">
                                <label for="documentId">Document ID:</label>
                                <input type="text" class="form-control documentId" name="date" id="documentId"/>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="status">Status:</label>
                                <select id="status" name="status"><option>PAID</option></select>
                            </div>
                            <div class="col-md-6">
                                <label for="series">Series:</label>
                                <select id="series" name="series">
                                    <g:each in="${series}" var="sr">
                                        <option value="${sr.id}">${sr.seriesCode}</option>
                                    </g:each>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="card">

                    <div class="body">
                        <div class="header" style="padding: 1px;">
                            Customer
                        </div>

                        <div class="form-group">
                            <select class="form-control" id="customerSelect">
                                <g:each in="${entity}" var="en">
                                    <g:if test="${en.id != session.getAttribute("entityId")}">
                                        <option value="${en.id}">${en.entityName}</option>
                                    </g:if>
                                </g:each>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="body">
                        <div class="table-responsive">
                            <div id="saleTable" style="width:100%;"></div>
                        </div>
                    </div>

                    <div class="header" style="padding: 1px;">
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                                data-target="#addAccountModeModal"><span
                                class="glyphicon glyphicon-save-file"></span> Save</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-6">
                <div class="card">
                    <div class="header" style="padding: 1px;">
                        Previous
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <div id="previousTable" style="width:100%;">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="card">
                    <div class="header" style="padding: 1px;">
                        Stocks
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <div id="C" style="width:100%;">

                            </div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/handsontable/0.16.0/handsontable.full.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<script>


    var idArray = [];
    var data = [];
    <g:each in="${salebilllist}" var="sb">
    idArray.push(${sb.id});
    data.push(['${sb.finId}', ${sb.serBillId}, ${sb.seriesId}, '${sb.paymentStatus}', '${sb.accountModeId}', '${sb.priorityId}', moment('${sb.entryDate}').format("DD/MM/YYYY"), '${sb.customerId}', '${sb.customerNumber}', ${sb.salesmanId}, '${sb.salesmanComm}', moment('${sb.orderDate}').format("DD/MM/YYYY"), '${sb.refOrderId}', moment('${sb.dueDate}').format("DD/MM/YYYY"), moment('${sb.dispatchDate}').format("DD/MM/YYYY"), '${sb.deliveryManId}', '${sb.totalSqty}', '${sb.totalFqty}', '${sb.totalItems}', '${sb.totalQty}', '${sb.totalDiscount}', '${sb.totalAmount}', '${sb.invoiceTotal}', '${sb.totalGst}', '${sb.userId}', '${sb.balance}', '${sb.grossAmount}', '${sb.taxable}', '${sb.cashDiscount}', '${sb.exempted}', '${sb.totalCgst}', '${sb.totalSgst}', '${sb.totalIgst}', '${sb.gstStatus}', ${sb.billStatus}, ${sb.lockStatus}, ${sb.creditadjAmount}, ${sb.creditIds}, ${sb.referralDoctor}, '${sb.message}', '${sb.financialYear}', ${sb.entityTypeId}, ${sb.entityId}])
    </g:each>


    var series = [];
    var products = [];
    var hsnCode = [];
    var accountMode = [];
    var customer = [];
    var salesman = [];
    var user = [];

    <g:each in="${products}" var="pd">
    products.push({id:${pd.id}, text: '${pd.productName}'});
    </g:each>

    <g:each in="${products}" var="pd">
    hsnCode.push({id:${pd.id}, text: '${pd.hsnCode}'});
    </g:each>

    <g:each in="${series}" var="s">
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
    </g:each>

    var headerRow = [
        '<strong>Product</strong>',
        '<strong>Batch</strong>',
        '<strong>Exp Dt</strong>',
        '<strong>Sale Qty</strong>',
        '<strong>Free Qty</strong>',
        '<strong>Sale Rate</strong>',
        '<strong>MRP</strong>',
        '<strong>Discount</strong>',
        '<strong>Pack</strong>',
        '<strong>GST</strong>',
        '<strong>Value</strong>',
        '<strong>SGST</strong>',
        '<strong>CGST</strong>',
        '<strong>IGST</strong>'];


    $(document).ready(function () {
        const container = document.getElementById('saleTable');
        const hot = new Handsontable(container, {
            data: data,
            minRows: 8,
            height: '250',
            width: 'auto',
            rowHeights: 25,
            manualRowResize: true,
            manualColumnResize: true,
            persistentState: true,
            contextMenu: true,
            rowHeaders: true,
            colHeaders: headerRow,
            columns: [
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
                {type: 'text'},
                {type: 'text'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'text'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'},
                {type: 'numeric'}
            ],
            minSpareRows: 1,
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation'
        });

        hot.updateSettings({
            afterChange: function (changes, source) {
                var rowThatHasBeenChanged = changes[0][0],
                    columnThatHasBeenChanged = changes[0][1],
                    previousValue = changes[0][2],
                    newValue = changes[0][3];

                var visualObjectRow = function (row) {
                    var obj = {},
                        key, name;
                    for (var i = 0; i < hot.countCols(); i++) {
                        obj[hot.colToProp(i)] = hot.getDataAtCell(row, i);
                    }
                    return obj
                };
                var json = {}
                var changedRow = visualObjectRow(rowThatHasBeenChanged)
                // console.log(visualObjectRow(rowThatHasBeenChanged))
                Object.keys(changedRow).forEach(function (key) {
                    json[headerRow[key].replace(/\s+/g, '')] = changedRow[key]
                });


                if (idArray[rowThatHasBeenChanged] != undefined) {
                    json['id'] = idArray[rowThatHasBeenChanged]
                } else {
                    json['id'] = null
                }
                console.log(json)
                // var cid = data[columnThatHasBeenChanged - 2];
                // var rId = data[rowThatHasBeenChanged];
                // var value = newValue;
                // console.log(cid);
                // console.log(rId);
                // console.log(value);


                var url = '';
                var type = '';
                var id = json['id'];
                if (idArray[rowThatHasBeenChanged] != undefined) {
                    url = '/sale-entry/update/' + id;
                    type = 'POST'
                } else {
                    url = '/sale-entry';
                    type = 'POST'
                }
                $.ajax({
                    type: type,
                    url: url,
                    dataType: 'json',
                    data: {
                        json: json
                    },
                    success: function (data) {
                        console.log("data loaded");
                    },
                    error: function (data) {
                        console.log("Failed");
                    }
                });

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
            batchSelection(selectedId, value)
            // $('#selectedId').text(selectedId);
        }

        function batchSelection(selectedId, value, row) {
            //
            $("#batchTableBody").html("");
            if (selectedId != null) {
                var url = "/stockbook/product/" + selectedId;

                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            var tableData = "";
                            for (var i = 0; i < data.length; i++) {
                                tableData += "<tr>" +
                                    "<td><a onclick='updateCell(hot, selectedId, value, row)' href='#' class='btn btn-info' id='batchTable" + i + "'>Select</a> </td>" +
                                    "<td>" + data[i].batchNumber + "</td>" +
                                    "<td>" + data[i].expDate + "</td>" +
                                    "<td>" + data[i].remainingQty + "</td>" +
                                    "<td>" + data[i].remainingFreeQty + "</td>" +
                                    "<td>" + data[i].purchaseRate + "</td>" +
                                    "<td>" + data[i].saleRate + "</td>" +
                                    "<td>-</td>" +
                                    "</tr>"
                            }

                            $("#batchTableBody").html(tableData);
                            $("#batchTable1").focus();
                        }
                    },
                    error: function (data) {
                        console.log("Failed");
                    }
                });

                $("#productNameTitle").text(value);
                $("#batchSelectModal").modal("toggle");
            }
        }

        function updateCell(hot, selectedId, value, row) {
            hot.setDataAtCell(row, 2, value);
            $("#productNameTitle").text("");
            $("#batchSelectModal").modal("toggle");
        }

        //    Series Dropdown
        function seriesDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < series.length; index++) {
                if (parseInt(value) === series[index].id) {
                    selectedId = series[index].id;
                    value = series[index].text;
                }
            }
            Handsontable.TextCell.renderer.apply(this, arguments);
            // $('#selectedId').text(selectedId);
        }

        //    Account mode Dropdown
        function accountModeDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < accountMode.length; index++) {
                if (parseInt(value) === accountMode[index].id) {
                    selectedId = accountMode[index].id;
                    value = accountMode[index].text;
                }
            }
            Handsontable.TextCell.renderer.apply(this, arguments);
            // $('#selectedId').text(selectedId);
        }

        //Customer Entity
        function customerDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < customer.length; index++) {
                if (parseInt(value) === customer[index].id) {
                    selectedId = customer[index].id;
                    value = customer[index].text;
                }
            }
            Handsontable.TextCell.renderer.apply(this, arguments);
            // $('#selectedId').text(selectedId);
        }


        //Salesman
        function salesmanDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < salesman.length; index++) {
                if (parseInt(value) === salesman[index].id) {
                    selectedId = salesman[index].id;
                    value = salesman[index].text;
                }
            }
            Handsontable.TextCell.renderer.apply(this, arguments);
            // $('#selectedId').text(selectedId);
        }

        //User
        function userDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
            var selectedId;
            for (var index = 0; index < user.length; index++) {
                if (parseInt(value) === user[index].id) {
                    selectedId = user[index].id;
                    value = user[index].text;
                }
            }
            Handsontable.TextCell.renderer.apply(this, arguments);
            // $('#selectedId').text(selectedId);
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
</script>
</body>
</html>