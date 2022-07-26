<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Purchase Order</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/fonts/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <style>

    table.dataTable tbody td {
        word-break: break-word;
        vertical-align: top;
    }

    /*
        div.dataTables_scrollBody table tbody  td {
            border-top: none;
            padding: 0.9px;
            text-align: center;

        }
    */

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
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Purchase Orders</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Purchase Orders</li>
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
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="header">
                        <div class="row">
                            <div class="col-md-4">
                            </div>

                            <div class="col-md-4">
                            </div>

                            %{--                            <div class="col-md-4">--}%
                            %{--                                <div class="form-group">--}%
                            %{--                                    <label for="invoiceStatus">Invoice Status</label>--}%
                            %{--                                    <select onchange="invoiceStatusChanged()" id="invoiceStatus" class="form-control">--}%
                            %{--                                        <option>All</option>--}%
                            %{--                                        <option>DRAFT</option>--}%
                            %{--                                        <option>ACTIVE</option>--}%
                            %{--                                        <option>CANCELLED</option>--}%
                            %{--                                    </select>--}%
                            %{--                                </div>--}%
                            %{--                            </div>--}%
                        </div>
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover purchaseInvoiceTable dataTable js-exportable">
                                <thead>
                                <tr>
                                    <th>-</th>
                                    <th>Customer</th>
                                    <th>Invoice No.</th>
                                    <th>Date & Time</th>
                                    <th>GST Amt</th>
                                    <th>Gross Amt</th>
                                    <th>Net Amt</th>
                                    <th>City</th>
                                    <th>Bill Status</th>
                                    <th>Balance</th>
                                    <th>Financial Year</th>
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


%{--<g:include view="controls/entity/add-entity-register.gsp"/>--}%
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
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js" integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>

<script>

    var entityregister;
    var id = null;
    $(function () {
        purchaseOrderTable();
        // var $demoMaskedInput = $('.demo-masked-input');
        // $demoMaskedInput.find('.datetime').inputmask('d/m/y h:m:s', { placeholder: '__/__/____ __:__:__:__', alias:
        //         "datetime", hourFormat: '12' });

    });

    function purchaseOrderTable() {
        var invoiceStatus = $("#invoiceStatus").val();
        entityregister = $(".purchaseInvoiceTable").DataTable({
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
                    exportOptions: { columns: ':visible:not(:first-child)' }
                },
                {
                    'extend': 'pdf',
                    exportOptions: { columns: ':visible:not(:first-child)' }
                },
                {
                    'extend': 'print',
                    exportOptions: { columns: ':visible:not(:first-child)' }
                }
            ],
            language: {
                searchPlaceholder: "Search Sale Bill"
            },
            ajax: {
                type: 'GET',
                url: '/purchase-order/datatable',
                data: {
                    invoiceStatus: invoiceStatus
                },
                dataType: 'json',

                dataSrc: function (json) {
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveInvoice = "";
                        var cancelInvoice = "";
                        var editInvoice = "";
                        if (json.data[i].billStatus !== "CANCELLED") {
                            cancelInvoice = '<a class="btn btn-sm btn-info" title="Cancel" onclick="cancelBill(' + json.data[i].id +')" href="#"><i class="fa fa-times"></i></a>';
                        }
                        else if(json.data[i].billStatus!== "DRAFT")
                        {
                            approveInvoice =  '';

                        }
                        var printbtn = '<a target="_blank" class="btn btn-sm btn-danger" data-id="' + json.data[i].id
                            + '" href="/purchase-order/print-order?id=' + json.data[i].id +
                            '"><i class="fa fa-print"></i></a>';
                        var invoiceNumber = json.data[i].invoiceNumber;
                        if (invoiceNumber === undefined)
                            invoiceNumber = "";
                        if(json.data[i].billStatus=== "DRAFT")
                        {
                            editInvoice = '<a class="btn btn-sm btn-warning"  href="/edit-sale-entry?saleBillId=' +
                                json.data[i].id + '"><i class="fa fa-edit"></i></a>';
                        }
                        var grossAmt = (json.data[i].totalAmount - json.data[i].totalGst).toFixed(2);
                        return_data.push({
                            'action': cancelInvoice + " " + approveInvoice + " " + printbtn+" "+editInvoice,
                            /*'action': '',*/
                            'customer': json.data[i].supplier.entityName,
                            'invNo': invoiceNumber,
                            'gstAmt': json.data[i].totalGst.toFixed(2),
                            'grossAmt': grossAmt,
                            'date': moment(json.data[i].dateCreated).format('DD-MM-YYYY  h:mm a'),
                            'netAmt': json.data[i].totalAmount.toFixed(2),
                            'city': json.data[i]?.supplier?.city?.areaName + "<br><small>(" + json.data[i]?.supplier?.city?.districtName + ")</small>",
                            'bill_status': json.data[i].billStatus,
                            'balance': json.data[i].balAmount.toFixed(2),
                            'finYear': json.data[i].financialYear

                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'action'},
                {'data': 'customer', 'width': '10%'},
                {'data': 'invNo'},
                {'data': 'date'},
                {'data': 'gstAmt'},
                {'data': 'grossAmt'},
                {'data': 'netAmt'},
                {'data': 'city'},
                {'data': 'bill_status'},
                {'data': 'balance'},
                {'data': 'finYear'}

                /* {'data': 'action', 'width': '4%'},
                 {'data': 'customer', 'width': '5%'},
                 {'data': 'invNo', 'width': '10%'},
                 {'data': 'gstAmt', 'width': '10%'},
                 {'data': 'netAmt', 'width': '10%'},
                 {'data': 'grossAmt', 'width': '10%'},
                 {'data': 'city', 'width': '10%'},
                 {'data': 'bill_status', 'width': '5%'},
                 {'data': 'balance', 'width': '5%'},
                 {'data': 'finYear', 'width': '30%'}*/
            ]
        });
    }

    function cancelBill(id) {
        Swal.fire({
            title: "Cancel this Purchase Order? this can't be undone.",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/purchase-order/cancel-order?id=' + id;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    success: function (data) {
                        Swal.fire(
                            'Success!',
                            'Order Cancelled',
                            'success'
                        );
                        purchaseOrderTable();
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to cancel order at the moment, try later.',
                            'danger'
                        );
                    }
                });
            } else if (result.isDenied) {

            }
        });


    }

    function invoiceStatusChanged() {
        purchaseOrderTable();
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>