<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Entity Register</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet src="/themeassets/plugins/handsontable/handsontable.full.css" rel="stylesheet"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">

    <style>
    .error {
        color: red;
    }
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
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="card">
                        <div class="header">
                            <h2>Entity on board information</h2>
                        </div>

                        <form id="wizard_with_validation" action="/" method="POST">
                            <h3>Produt Register</h3>
                            <fieldset>
                                <button type="button" class="btn btn-primary" id="saveProduct"
                                        onclick="saveProducts()">Save
                                </button><br>

                                <div id="productTable" style="width:100%;"></div>
                            </fieldset>

                            <h3>Batch Register</h3>
                            <fieldset>
                                <button type="button" class="btn btn-primary" id="saveBatch"
                                        onclick="saveProducts()">Save
                                </button>
                                %{--                                <button type="button" class="btn btn-primary" id="loadProducts"--}%
                                %{--                                        onclick="loadProductsData()">load--}%
                                %{--                                </button><br>--}%

                                <div id="batchTable" style="width:100%;"></div>
                            </fieldset>

                            <h3>Inventory</h3>
                            <fieldset>

                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="progress">
        <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0"
             aria-valuemax="100" style="width: 0%;">
            0%
        </div>
    </div>
</section>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/> <!-- Lib Scripts Plugin Js -->
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/> <!-- Lib Scripts Plugin Js -->
<asset:javascript
        src="/themeassets/plugins/jquery-validation/jquery.validate.js"/> <!-- Jquery Validation Plugin Css -->
<asset:javascript src="/themeassets/plugins/jquery-steps/jquery.steps.js"/> <!-- JQuery Steps Plugin Js -->
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/><!-- Custom Js -->
<asset:javascript src="/themeassets/js/pages/forms/form-wizard-bulk-import.js"/>
%{--<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>--}%
<asset:javascript src="/themeassets/plugins/handsontable/handsontable.full.js"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<g:include view="controls/footer-content.gsp"/>
<script>
    var prodcutHot;
    var batchHot;
    var stockHot;
    var productData = [];
    var products = [];
    var batchData = [];
    var prodcutHeaderRow = [
        'product_name',
        'product_code',
        'purchase_margin_percent',
        'vip_sale_rate',
        'sale_rate',
        'order_quantity',
        'mrp',
        'purchase_trade_discount',
        'sale_trade_discount',
        'restricted_rate',
        'ccm_product',
        'gross_profit_percentage',
        'tax',
        // 'threshold_level',
        'ptr',
        'narration',
        'hsn_code',
        'salesmen_percent',
        'purchase_rate',
        'discount_allowed',
        'sale_margin_percent',
        'vipprate',
        'restricted_assignment',
        'per_lot_quantity',
        'unit_packing',
        'nri_rate',
        'salesman_commission',
        'sale_type',
        /* 'entity_id',
         'entity_type_id',
         'division_id',*/
    ];
    var batchHeaderRow = [
        'product',
        'batch_number',
        'box',
        'qty',
        'expiry_date',
        'sale_rate',
        'mrp',
        'ptr',
        'purchase_rate',
        'manf_date',
    ];
    <g:each in="${products}" var="p">
    products.push({"id": ${p.id}, "text": '${p.productName}'});
    </g:each>
    console.log(products);
    $(document).ready(function () {
        const productContainer = document.getElementById('productTable');
        const batchContainer = document.getElementById('batchTable');
        prodcutHot = new Handsontable(productContainer, {
            data: productData,
            minRows: 1,
            height: '300',
            width: 'auto',
            rowHeights: 25,
            stretchH: 'all',
            manualRowResize: true,
            manualColumnResize: true,
            persistentState: true,
            rowHeaders: true,
            colHeaders: prodcutHeaderRow,
            columns: [
                {type: 'text', required:true},
                {type: 'text', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'text', required:true},
                {type: 'numeric', required:true},
                // {type:'numeric'},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'text', required:true},
                {type: 'text', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'text', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'text', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'text', required:true},
                /* {type:'numeric'},
                 {type:'numeric'},
                 {type:'numeric'},*/
            ],
            /*hiddenColumns: true,
            hiddenColumns: {
                columns: [13]
            },*/
            minSpareRows: 0,
            minSpareColumns: 0,
            enterMoves: {row: 0, col: 1},
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation',
            contextMenu: ['remove_row', 'row_below', 'cut', 'copy'],
            /*  beforeCopy: function(data) {
                  var headers = [];
                  var selection = this.getSelectedRange();
                  var startCol = Math.min(selection[0].from.col, selection[0].to.col);
                  var endCol = Math.max(selection[0].from.col, selection[0].to.col);

                  for (var i = startCol; i <= endCol; i++) {
                      headers.push(this.getColHeader(i));
                  }

                  data.splice(0, 0, headers);
              }*/
        });

        batchHot = new Handsontable(batchContainer, {
            data: batchData,
            minRows: 1,
            height: '300',
            width: 'auto',
            rowHeights: 25,
            stretchH: 'all',
            manualRowResize: true,
            manualColumnResize: true,
            persistentState: true,
            rowHeaders: true,
            colHeaders: batchHeaderRow,
            columns: [
                {
                    editor: 'select2',
                    renderer: productsDropdownRenderer,
                    select2Options: {
                        data: products,
                        dropdownAutoWidth: true,
                        allowClear: true,
                        width: '0'
                    },
                    required:true
                },
                {type: 'text', required:true},
                {type: 'text',  required:true},
                {type: 'numeric', required:true},
                {type: 'date',  dateFormat: 'YYYY-MM-DD', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'numeric', required:true},
                {type: 'date',  dateFormat: 'YYYY-MM-DD', required:true},
            ],
            /*hiddenColumns: true,
            hiddenColumns: {
                columns: [13]
            },*/
            minSpareRows: 0,
            minSpareColumns: 0,
            enterMoves: {row: 0, col: 1},
            fixedColumnsLeft: 0,
            licenseKey: 'non-commercial-and-evaluation',
            contextMenu: ['remove_row', 'row_below', 'cut', 'copy'],
            /*  beforeCopy: function(data) {
                  var headers = [];
                  var selection = this.getSelectedRange();
                  var startCol = Math.min(selection[0].from.col, selection[0].to.col);
                  var endCol = Math.max(selection[0].from.col, selection[0].to.col);

                  for (var i = startCol; i <= endCol; i++) {
                      headers.push(this.getColHeader(i));
                  }

                  data.splice(0, 0, headers);
              }*/
        });
    });

    function loadProductsData() {
        var entityId = "${session.getAttribute("entityId")}";
        var beforeSendSwal;
        $.ajax({
            type: "GET",
            url: '/product/get-products-by-entity',
            dataType: 'json',
            beforeSend: function () {
                beforeSendSwal = Swal.fire({
                    // title: "Loading",
                    html:
                        '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                    showDenyButton: false,
                    showCancelButton: false,
                    showConfirmButton: false,
                    allowOutsideClick: false,
                    background: 'transparent'
                });
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    batchHot.selectCell(i, 0);
                    batchHot.setDataAtCell(i, 0, data[i]["id"]);
                }
                beforeSendSwal.close();
            }
        })
    }

    function productsDropdownRenderer(instance, td, row, col, prop, value, cellProperties) {
        var selectedId;
        for (var index = 0; index < products.length; index++) {
            if (parseInt(value) === products[index].id) {
                selectedId = products[index].id;
                value = products[index].text;
            }
            console.log(selectedId)
        }
        Handsontable.renderers.TextRenderer.apply(this, arguments);
    }


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


        var onBeforeMouseDown = function (event) {
            var instance = this;
            var that = instance.instance.getSelected()
            this.$dropdownContainer.on('mousedown', function (evt) {
                evt.stopPropagation();
            });
        };


        Select2Editor.prototype.open = function (keyboardEvent) {
            this.refreshDimensions();
            this.textareaParentStyle.zIndex = 20000;
            this.instance.addHook('beforeKeyDown', onBeforeKeyDown);
            this.instance.addHook('afterOnCellMouseDown', onBeforeMouseDown);
            this.textareaParentStyle.display = 'block';
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
                $('.handsontableInput').children().show()
            } else {
                $('.handsontableInput').children().hide()
            }
            Handsontable.renderers.cellDecorator.apply(this, arguments);
        };


        Select2Editor.prototype.init = function () {
            Handsontable.editors.TextEditor.prototype.init.apply(this, arguments);
        };

        Select2Editor.prototype.close = function () {
            this.instance.listen();
            this.instance.removeHook('beforeKeyDown', onBeforeKeyDown);
            this.instance.removeHook('afterOnCellMouseDown', onBeforeMouseDown);
            this.$textarea.off();
            this.$textarea.hide();
            $('.handsontableInput').children().hide();
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

    function saveProducts() {
        prodcutHot.validateCells(function (hotIsValid) {
            if (hotIsValid === true) {
                var beforeSendSwal;
                var productData = JSON.stringify(prodcutHot.getSourceData());
                $.ajax({
                    method: "POST",
                    url: "/product/save-bulk-products",
                    data: {
                        productData: productData
                    },
                    beforeSend: function () {
                        beforeSendSwal = Swal.fire({
                            // title: "Loading",
                            html: '<!-- By Sam Herbert (@sherb), for everyone. More @ http://goo.gl/7AJzbL -->\n' +
                                '<svg width="45" height="45" viewBox="0 0 45 45" xmlns="http://www.w3.org/2000/svg" stroke="#fff">\n' +
                                '    <g fill="none" fill-rule="evenodd" transform="translate(1 1)" stroke-width="2">\n' +
                                '        <circle cx="22" cy="22" r="6" stroke-opacity="0">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="6;22"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-opacity"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="1;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-width"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="2;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '        <circle cx="22" cy="22" r="6" stroke-opacity="0">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="6;22"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-opacity"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="1;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-width"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="2;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '        <circle cx="22" cy="22" r="8">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="0s" dur="1.5s"\n' +
                                '                 values="6;1;2;3;4;5;6"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '    </g>\n' +
                                '</svg>',
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false,
                            background: 'transparent'

                        });
                    },
                    success: function (data) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: 'Product Import Successful',
                        });

                        prodcutHot.updateSettings({
                            data: []
                        });
                        prodcutHot.render()
                        $('#wizard_with_validation').steps('next');
                    },
                    error: function (data) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Something went wrong!',
                            text: 'Please delete empty rows',
                            // footer: '<a href="">Why do I have this issue?</a>'
                        });
                    }

                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Something went wrong!',
                    text: 'Please add valid data!',
                    // footer: '<a href="">Why do I have this issue?</a>'
                });
                return false;
            }
        });

    }

    function saveBatches() {
        prodcutHot.validateCells(function (hotIsValid) {
            if (hotIsValid === true) {
                var beforeSendSwal;
                var batchData = JSON.stringify(prodcutHot.getSourceData());
                $.ajax({
                    method: "POST",
                    url: "/product/save-bulk-batches",
                    data: {
                        batchData: batchData
                    },
                    beforeSend: function () {
                        beforeSendSwal = Swal.fire({
                            // title: "Loading",
                            html: '<!-- By Sam Herbert (@sherb), for everyone. More @ http://goo.gl/7AJzbL -->\n' +
                                '<svg width="45" height="45" viewBox="0 0 45 45" xmlns="http://www.w3.org/2000/svg" stroke="#fff">\n' +
                                '    <g fill="none" fill-rule="evenodd" transform="translate(1 1)" stroke-width="2">\n' +
                                '        <circle cx="22" cy="22" r="6" stroke-opacity="0">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="6;22"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-opacity"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="1;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-width"\n' +
                                '                 begin="1.5s" dur="3s"\n' +
                                '                 values="2;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '        <circle cx="22" cy="22" r="6" stroke-opacity="0">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="6;22"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-opacity"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="1;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '            <animate attributeName="stroke-width"\n' +
                                '                 begin="3s" dur="3s"\n' +
                                '                 values="2;0" calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '        <circle cx="22" cy="22" r="8">\n' +
                                '            <animate attributeName="r"\n' +
                                '                 begin="0s" dur="1.5s"\n' +
                                '                 values="6;1;2;3;4;5;6"\n' +
                                '                 calcMode="linear"\n' +
                                '                 repeatCount="indefinite" />\n' +
                                '        </circle>\n' +
                                '    </g>\n' +
                                '</svg>',
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false,
                            background: 'transparent'

                        });
                    },
                    success: function (data) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: 'Batch Import Successful',
                        });

                        prodcutHot.updateSettings({
                            data: []
                        });
                        prodcutHot.render();
                        $('#wizard_with_validation').steps('next');
                    },
                    error: function (data) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Something went wrong!',
                            text: 'Please delete empty rows',
                            // footer: '<a href="">Why do I have this issue?</a>'
                        });
                    }

                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Validation failed!',
                    text: 'Please add valid data!',
                    // footer: '<a href="">Why do I have this issue?</a>'
                });
                return false;
            }
        });

    }


</script>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>