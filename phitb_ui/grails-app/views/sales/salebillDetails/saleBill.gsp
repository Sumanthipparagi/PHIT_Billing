<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Sale Invoices</title>
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

    .buttons-collection{
        border-width: 0 !important;
        border-radius: 0 !important;
    }

    .table > tbody > tr > td {
        vertical-align: middle;
    }

    .scroll {
        height:50rem;
        overflow-y: scroll;
        -ms-overflow-style: none;  /* IE and Edge */
        scrollbar-width: none;  /* Firefox */
    }
    .scroll::-webkit-scrollbar {
        display: none;
    }

    .dt-left-col {
        float: left;
        margin-top: 5px;
        width: 20%;
    }

    .dt-center-col {
        float: left;
        width: 25%;
    }

    .dt-right-col {
        float: left;
        margin-top: 5px;
        align-content: end;
        width: 50%;
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
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Sale Invoices</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">My Invoices</li>
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
            <div class="col-md-12">
                <div class="card">
                    <div class="body">
                        <div class="row justify-content-end">
                            <div class="col-md-4">
                                <div class="form-group">
                                    %{--<label for="invoiceStatus">Invoice Status</label>--}%
                                    <select onchange="invoiceStatusChanged()" id="invoiceStatus" class="form-control">
                                        <option>All</option>
                                        <option>DRAFT</option>
                                        <option>ACTIVE</option>
                                        <option>CANCELLED</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                            </div>

                            <div class="col-md-4 clearfix">
                                <div class="pull-left"></div>
                                <button class="btn btn-primary btn-sm pull-right" onclick="toggleDetails()"><i
                                        class="fa fa-angle-double-right" id="collapseIcon"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12" id="listContainer">
                <div class="card">
                    <div class="body">
                        <div class="table-responsive">
                            <table id="saleInvoiceTable"
                                   class="table table-bordered table-striped table-hover saleInvoiceTable dataTable js-exportable">
                                <thead>
                                <tr>
                                    <th>-</th>
                                    <th>Invoice No.</th>
                                    <th>Customer</th>
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

            <div id="detailsContainer" class="hidden" >
                <div class="card">
                    <div class="body scroll" style="min-height: 60rem;">
                        <g:include view="sales/salebillDetails/sale-invoice-details.gsp"/>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
        integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>

<script>

    var saleInvoiceTable;
    var id = null;
    $(function () {
        saleInvoiceTable();
        // var $demoMaskedInput = $('.demo-masked-input');
        // $demoMaskedInput.find('.datetime').inputmask('d/m/y h:m:s', { placeholder: '__/__/____ __:__:__:__', alias:
        //         "datetime", hourFormat: '12' });

    });

    function saleInvoiceTable() {
        var invoiceStatus = $("#invoiceStatus").val();
        saleInvoiceTable = $(".saleInvoiceTable").DataTable({
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
            //dom: 'lBfrtip',
            dom: '<"top"<"dt-left-col"l><"dt-center-col"B><"dt-right-col"f>>rt<"bottom"ip><"clear">',
            oLanguage: {
                sLengthMenu: "_MENU_",
            },
            buttons: [
                {
                    extend: 'collection',
                    text: 'Export',
                    buttons: [
                        {
                            'extend': 'excel',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        },
                        {
                            'extend': 'pdf',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        },
                        {
                            'extend': 'print',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        }
                    ],
                    dropup: true,
                    className: 'dropdown-toggle btn-sm'
                }
            ],
            language: {
                searchPlaceholder: "Search Sale Bill"
            },
            ajax: {
                type: 'GET',
                url: '/sale-bill/datatable',
                data: {
                    invoiceStatus: invoiceStatus
                },
                dataType: 'json',

                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveInvoice = "";
                        var cancelInvoice = "";
                        var editInvoice = "";
                        if (json.data[i].billStatus !== "CANCELLED") {
                            cancelInvoice = '<a class="dropdown-item" title="Cancel" onclick="cancelBill(' + json.data[i].id + ')" href="#" style="color: red;"><i class="fa fa-times"></i> Cancel</a>';
                        } else if (json.data[i].billStatus !== "DRAFT") {
                            approveInvoice = '';

                        }
                        var printbtn = '<a target="_blank" class="dropdown-item" data-id="' + json.data[i].id + '" href="/sale-entry/print-invoice?id=' + json.data[i].id + '"><i class="fa fa-print"></i> Print</a>';
                        var invoiceNumber = json.data[i].invoiceNumber;
                        if (invoiceNumber === undefined)
                            invoiceNumber = "DRAFT";
                        if (json.data[i].billStatus === "DRAFT") {
                            editInvoice = '<a class="dropdown-item"  href="/edit-sale-entry?saleBillId=' +
                                json.data[i].id + '"><i class="fa fa-edit"></i> Edit</a>';
                        }
                        var actionBtn = "<div class=\"dropdown\">\n" +
                            "  <button class=\"btn btn-primary btn-simple btn-sm dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            " <i class='fa fa-bars'></i>" +
                            "  </button>\n" +
                            "  <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                            approveInvoice +
                            printbtn +
                            editInvoice +
                            cancelInvoice +
                            "  </div>\n" +
                            "</div>"
                        invoiceNumber = "<a href='#' onclick='listItemClicked(" + json.data[i].id + ")'>" + invoiceNumber + "</a>";
                        var grossAmt = (json.data[i].invoiceTotal - json.data[i].totalGst).toFixed(2);
                        return_data.push({
                            'action': actionBtn,
                            'invNo': invoiceNumber,
                            'customer': json.data[i]?.customer?.entityName,
                            'gstAmt': json.data[i].totalGst.toFixed(2),
                            'grossAmt': grossAmt,
                            'date': moment(json.data[i].entryDate).format('DD-MM-YYYY  h:mm a'),
                            'netAmt': json.data[i].invoiceTotal.toFixed(2),
                            'city': json.data[i]?.customer?.city?.areaName + "<br><small>(" + json.data[i]?.customer?.city?.districtName + ")</small>",
                            'bill_status': json.data[i].billStatus,
                            'balance': json.data[i].balance.toFixed(2),
                            'finYear': json.data[i].financialYear

                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'action'},
                {'data': 'invNo'},
                {'data': 'customer', 'width': '10%'},
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

        saleInvoiceTable.buttons().container().appendTo('#saleInvoiceTable_wrapper .col-md-6:eq(0)');
    }

    function cancelBill(id) {
        Swal.fire({
            title: "Cancel this Sale Invoice? this can't be undone.",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/sale-entry/cancel-invoice?id=' + id;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    success: function (data) {
                        Swal.fire(
                            'Success!',
                            'Invoice Cancelled',
                            'success'
                        );
                        saleInvoiceTable();
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to cancel invoice at the moment, try later.',
                            'danger'
                        );
                    }
                });
            } else if (result.isDenied) {

            }
        });


    }

    function invoiceStatusChanged() {
        saleInvoiceTable();
    }

    function listItemClicked(id) {
        $("#detailsContentError").addClass("hidden");
        $("#detailsSpinner").removeClass("hidden");
        $("#detailsContent").addClass("hidden");
        if(!$("#detailsContainer").hasClass("opened")) {
            toggleDetails();
        }
        //Get invoice Details
        $.ajax({
            method: "GET",
            url: "../sale-bill/"+id,
            success: function (data) {
                $("#detailsSpinner").addClass("hidden");
                $("#detailsContent").removeClass("hidden");
                var saleProducts = data.saleProducts;
                var invoice = data.invoice;
                var entity = data.entity;
                var customer = data.customer;
                var entityCity = data.entityCity;
                var customerCity = data.customerCity;
                var orderDate = "-";
                var dueDate = "-";
                var badgeContainer = "";
                if(invoice.billStatus !== "DRAFT")
                {
                    orderDate = moment(invoice.orderDate.split("T")[0],"YYYY-MM-DD").format("DD/MM/YYYY");
                    dueDate = moment(invoice.dueDate.split("T")[0],"YYYY-MM-DD").format("DD/MM/YYYY");

                    if(invoice.billStatus === "ACTIVE")
                    {
                        badgeContainer += "<div class=\"badge badge-success\">ACTIVE</div>"
                    }
                    else if(invoice.billStatus === "CANCELLED")
                    {
                        badgeContainer += "<div class=\"badge badge-danger\">CANCELLED</div>"
                    }
                }
                else
                {
                    badgeContainer += "<div class=\"badge badge-warning\">DRAFT</div>"
                }

                if(invoice.billStatus === "DRAFT")
                {
                    $("#invoiceNumber").text("DRAFT INVOICE");
                }
                else {
                    $("#invoiceNumber").text(invoice.invoiceNumber);
                }
                $("#invoiceDate").text(orderDate);
                $("#dueDate").text(dueDate);
                var entityDetails = "<span style='font-weight: bold'>"+entity.entityName + "</span><br><small>"+entity.addressLine1 + ", " +entity.addressLine2 + "<br>"+entityCity.districtName+"<br>"+entity.pinCode+"</small>";
                var customerDetails = "<span style='font-weight: bold'>"+customer.entityName + "</span><br><small>"+customer.addressLine1 + ", " +customer.addressLine2 + "<br>"+customerCity.districtName+"<br>"+customer.pinCode+"</small>";
                $("#entityDetails").html(entityDetails);
                $("#customerDetails").html(customerDetails);

                var totalAmt = 0.0;
                var totaltax = 0.0;
                var tableContent = $("#saleProductsTableBody");
                tableContent.empty();
                $.each(saleProducts, function (index, saleProduct) {
                    totalAmt += (saleProduct.amount-saleProduct.gstAmount);
                    totaltax += saleProduct.gstAmount;
                    tableContent.append("<tr><td>"+(++index)+"</td><td style=\"white-space: normal !important; word-wrap: break-word;\">"+saleProduct.product.productName+"<br><small>Batch: "+saleProduct.batchNumber+"</small></td><td>"+saleProduct.sqty+"</td><td>"+saleProduct.freeQty+"</td><td>"+saleProduct.amount.toFixed(2)+"</td><td>"+saleProduct.gstAmount.toFixed(2)+"</td><td>"+(saleProduct.amount-saleProduct.gstAmount).toFixed(2)+"</td></tr>")
                });
                $("#totaltax").html(totaltax.toFixed(2));
                $("#totalAmt").html(totalAmt.toFixed(2));
                $("#badgeContainer").html(badgeContainer);
            },
            error: function () {
                $("#detailsSpinner").addClass("hidden");
                $("#detailsContentError").removeClass("hidden");
            }
        })

    }

    function toggleDetails() {
        if ($("#collapseIcon").hasClass("fa-angle-double-right")) {
            $("#collapseIcon").removeClass("fa-angle-double-right");
            $("#collapseIcon").addClass("fa-angle-double-left");

            $("#listContainer").removeClass("col-lg-12");
            $("#listContainer").removeClass("col-md-12");

            $("#listContainer").addClass("col-lg-5");
            $("#listContainer").addClass("col-md-5");


            $("#detailsContainer").removeClass("hidden");
            $("#detailsContainer").addClass("col-lg-7");
            $("#detailsContainer").addClass("col-md-7");
            $("#detailsContainer").addClass("opened");
        } else {
            $("#collapseIcon").removeClass("fa-angle-double-left");
            $("#collapseIcon").addClass("fa-angle-double-right");

            $("#listContainer").addClass("col-lg-12");
            $("#listContainer").addClass("col-md-12");

            $("#listContainer").removeClass("col-lg-5");
            $("#listContainer").removeClass("col-md-5");

            $("#detailsContainer").addClass("hidden");
            $("#detailsContainer").removeClass("col-lg-7");
            $("#detailsContainer").removeClass("col-md-7");
            $("#detailsContainer").removeClass("opened");
        }
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>