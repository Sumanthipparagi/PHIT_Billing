<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Stock  Adjustment</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    %{--    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
        <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>--}%
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/daterangepicker/daterangepicker.css" rel="stylesheet"/>


    <style>


    .editbtn {
        padding: 1px 9px;
    }

    .deletebtn {
        padding: 1px 9px;
    }

    tbody td {
        padding: 0px;
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
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Stock Adjustment List</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Stock Adjustment List</li>
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
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12">
                %{--                <div class="card"><div class="header">--}%
                %{--                    <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"--}%
                %{--                            data-target="#addstockModal"><font style="vertical-align: inherit;"><font--}%
                %{--                            style="vertical-align: inherit;">Add Stocks</font></font></button>--}%
                %{--                </div>--}%
                <div class="col-lg-6">
                    <div class="form-group">
                        <label>Date Range:</label>

                        <div class="input-group">
                            <input id="dateRange" class="dateRange" type="text" name="dateRange"
                                   style="border-radius: 6px;margin: 4px;" autocomplete="off">
                        </div>
                    </div>
                </div>

                <div class="body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped table-hover stockTable dataTable">
                            <thead>
                            <tr>
                                <th style="width: 20%">Product</th>
                                <th style="width: 20%">Batch Number</th>
                                <th style="width: 20%">Date Created</th>
                                <th style="width: 20%">Manufacture Date</th>
                                <th style="width: 20%">Expiry Date</th>
                                <th style="width: 20%">Purchase Rate</th>
                                <th style="width: 20%">Sale Rate</th>
                                <th style="width: 20%">MRP</th>
                                <th style="width: 20%">Sale Qty</th>
                                <th style="width: 20%">Free Qty</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</section>


<g:include view="controls/inventory/add-stock.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
%{--<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>--}%
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
%{--<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>--}%
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
%{--<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>--}%
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js" type="text/javascript"/>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
        integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/moment.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/daterangepicker.js"/>
<script>

    var stockentrytable;
    var id = null;
    $(function () {
        $("#product").select2();
        $("#batchNumber").select2();
        $("#supplierId").select2();
        stockTable();

    });

    $('#expDate').bootstrapMaterialDatePicker({
        time: false,
        format: 'DD-MM-YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $('#purcDate').bootstrapMaterialDatePicker({
        time: false,
        format: 'DD-MM-YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $('#manufacturingDate').bootstrapMaterialDatePicker({
        time: false,
        format: 'DD-MM-YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });


    function stockTable() {
        var daterange = $(".dateRange").val();
        stockentrytable = $(".stockTable").DataTable({
            "order": [[0, "desc"]],
            sPaginationType: "simple_numbers",
            responsive: {
                details: false
            },
            destroy: true,
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true,
            dom: 'lBfrtip',
            // buttons: [
            //     {
            //         'copy', 'csv', 'excel', 'pdf', 'print'
            //     },
            // ],
            buttons: [
                {
                    'extend': 'excel',
                },
                {
                    'extend': 'pdf',
                },
                {
                    'extend': 'print',
                }
            ],
            language: {
                searchPlaceholder: "Search"
            },
            ajax: {
                type: 'GET',
                url: '/stock-adjustment-datatable',
                dataType: 'json',
                data: {
                    daterange: daterange,
                    fromDate:"",
                    toDate:""
                },
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var manfDate = new Date(json.data[i].manfDate);
                        var expiryDate = new Date(json.data[i].expDate);
                        var date = new Date(json.data[i].dateCreated);
                        // var editbtn = '<button type="button" data-id="' + json.data[i].id +
                        //     '" data-product="' + json.data[i].product.productName + '"' +
                        //     '" data-batchNumber="' + json.data[i].batchNumber + '"' +
                        //     '" data-manfDate="' + moment(manufacturingDate).format('DD/MM/YYYY') + '"' +
                        //     '" data-expiryDate="' + moment(expDate).format('DD/MM/YYYY') + '"' +
                        //     '" data-purcDate="' + moment(purcDate).format('DD/MM/YYYY') + '"' +
                        //     '" data-purchaseRate="' + json.data[i].purchaseRate + '"' +
                        //     '" data-purcTradeDiscount="' + json.data[i].purcTradeDiscount + '"' +
                        //     '" data-purcSeriesId="' + json.data[i].purcSeriesId + '"' +
                        //     '" data-supplierId="' + json.data[i].supplierId + '"' +
                        //     '" data-remainingQty="' + json.data[i].remainingQty + '"' +
                        //     '" data-remainingFreeQty="' + json.data[i].remainingFreeQty + '"' +
                        //     '" data-saleRate="' + json.data[i].saleRate + '"' +
                        //     '" data-mrp="' + json.data[i].mrp + '"' +
                        //     '" data-packingDesc="' + json.data[i].packingDesc + '"' +
                        //     '" data-purcProductValue="' + json.data[i].purcProductValue + '"' +
                        //     '" data-taxId="' + json.data[i].taxId.id + '"' +
                        //     '" data-entityId="' + json.data[i].entityId + '"' +
                        //     '" data-entityType="' + json.data[i].entityTypeId + '"' +
                        //     '"' +
                        //     ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addstockModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        // var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                        //     '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'product': json.data[i].product.productName,
                            'batchNumber': json.data[i].batchNumber,
                            'manfDate': moment(manfDate).format('DD/MM/YYYY'),
                            'date': moment(date).format('DD/MM/YYYY'),
                            'expiryDate': moment(expiryDate).format('DD/MM/YYYY'),
                            'purchaseRate': json.data[i].pRate,
                            'saleRate': json.data[i].sRate,
                            'mrp': json.data[i].mrp,
                            'remainingQty': json.data[i].sqty,
                            'freeQty': json.data[i].fqty,
                            /* 'action': editbtn + ' ' + deletebtn*/
                            // 'action': deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'product', 'width': '20%'},
                {'data': 'batchNumber', 'width': '20%'},
                {'data': 'date', 'width': '20%'},
                {'data': 'manfDate', 'width': '20%'},
                {'data': 'expiryDate', 'width': '20%'},
                {'data': 'purchaseRate', 'width': '20%'},
                {'data': 'saleRate', 'width': '20%'},
                {'data': 'mrp', 'width': '20%'},
                {'data': 'remainingQty', 'width': '20%'},
                {'data': 'freeQty', 'width': '20%'},
            ]
        });
    }

    $('.dateRange').daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: 'Clear'
        },
    });
    $('.dateRange').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        stockTable();
    });
    stockTable();

    $('.dateRange').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
        stockTable();
    });
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("inventory-menu");
</script>
</body>
</html>