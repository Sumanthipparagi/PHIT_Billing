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
        height:35rem;
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
                    <div class="body scroll" style="min-height: 57rem;">
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
        loadSaleInvoiceTable();
        $("#creditsApplied").text("0.00");

        $('#remarks').on("input", function(){
            var maxlength = $(this).attr("maxlength");
            var currentLength = $(this).val().length;

            if( currentLength >= maxlength ){
                $("#remarksCharacters").addClass("danger");
                console.log("You have reached the maximum number of characters.");
            }else{
                console.log(maxlength - currentLength + " chars left");
                $("#remarksCharacters").removeClass("danger");
            }
            $("#remarksCharacters").text(currentLength);
            $('#paymentDate').val(new Date().toDateInputValue());
        });
    });

    function loadSaleInvoiceTable() {
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
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveInvoice = "";
                        var cancelInvoice = "";
                        var editInvoice = "";
                        var cloneInvoice="";
                        if (json.data[i].billStatus !== "CANCELLED") {
                            cancelInvoice = '<a class="dropdown-item" title="Cancel" onclick="cancelBill(' + json.data[i].id + ')" href="#" style="color: red;"><i class="fa fa-times"></i> Cancel</a>';
                        } else if (json.data[i].billStatus !== "DRAFT") {
                            approveInvoice = '';
                        }

                        if (json.data[i].billStatus!== "DRAFT" && json.data[i].invoiceNumber!==undefined) {
                            cloneInvoice =
                                '<a class="dropdown-item" title="Clone"  href="/sale-entry/clone-invoice?saleBillId='
                                + json.data[i].id + '&type=CLONE" target="_blank"><i class="fa fa-clone"></i> Clone</a>';
                        }else{
                            cloneInvoice=""
                        }

                        var printbtn = '<a target="_blank" class="dropdown-item" data-id="' + json.data[i].id + '" href="/sale-entry/print-invoice?id=' + json.data[i].id + '"><i class="fa fa-print"></i> Print</a>';
                        var invoiceNumber = json.data[i].invoiceNumber;
                        if (invoiceNumber === undefined) {
                            if (json.data[i].billStatus === "CANCELLED")
                                invoiceNumber = "CANCELLED DRAFT";
                            else
                                invoiceNumber = "DRAFT";
                        }
                        if (json.data[i].billStatus === "DRAFT") {
                            editInvoice = '<a class="dropdown-item"  href="/edit-sale-entry?saleBillId=' + json.data[i].id + '"><i class="fa fa-edit"></i> Edit</a>';
                        }
                        if(json.data[i].balance === 0)
                        {
                            cancelInvoice ="";
                        }

                        var actionBtn = "<div class=\"dropdown\">\n" +
                            "  <button class=\"btn btn-primary btn-simple btn-sm dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            " <i class='fa fa-bars'></i>" +
                            "  </button>\n" +
                            "  <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                            approveInvoice +
                            printbtn +
                            editInvoice +
                            cloneInvoice +
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
                        loadSaleInvoiceTable();
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
        loadSaleInvoiceTable();
    }

    function listItemClicked(id) {
        $("#detailsContentError").addClass("hidden");
        $(".detailsSpinner").removeClass("hidden");
        $("#detailsContent").addClass("hidden");
        if(!$("#detailsContainer").hasClass("opened")) {
            toggleDetails();
        }
        //Get invoice Details
        $.ajax({
            method: "GET",
            url: "../sale-bill/"+id,
            success: function (data) {
                $(".detailsSpinner").addClass("hidden");
                $("#detailsContent").removeClass("hidden");
                var saleProducts = data.saleProducts;
                var invoice = data.invoice;
                var entity = data.entity;
                var customer = data.customer;
                var entityCity = data.entityCity;
                var customerCity = data.customerCity;
                var receiptLog = data.receiptLog;
                var saleReturnAdjustmentDetails = data.saleReturnAdjustmentDetails;
                var saleReturns = data.saleReturns;
                var orderDate = "-";
                var dueDate = "-";
                var badgeContainer = "";
                var saleReturnIds = "";
                var saleBillId = $(".saleBillId").val(invoice.id);
                if(invoice.billStatus !== "DRAFT")
                {
                    if(invoice.invoiceNumber !== undefined) {
                        orderDate = moment(invoice.orderDate.split("T")[0], "YYYY-MM-DD").format("DD/MM/YYYY");
                        dueDate = moment(invoice.dueDate.split("T")[0], "YYYY-MM-DD").format("DD/MM/YYYY");
                    }
                    if(invoice.billStatus === "ACTIVE")
                    {
                        badgeContainer += "<div class=\"badge badge-success\">ACTIVE</div>"
                    }
                    else if(invoice.billStatus === "CANCELLED")
                    {
                        badgeContainer += "<div class=\"badge badge-danger\">CANCELLED</div>"
                    }

                    if(invoice.balance === 0)
                    {
                        badgeContainer += "<div class=\"badge badge-success ml-2\">SETTLED</div>"
                    }
                    else if(invoice.balance === invoice.invoiceTotal)
                    {
                        badgeContainer += "<div class=\"badge badge-danger ml-2\">UNSETTLED</div>"
                    }
                    else
                    {
                        badgeContainer += "<div class=\"badge badge-warning ml-2\">PARTIALLY SETTLED</div>"
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
                var subtotal = 0.0;

                var tableContent = $("#saleProductsTableBody");
                tableContent.empty();
                $.each(saleProducts, function (index, saleProduct) {
                    subtotal += (saleProduct.amount-saleProduct.gstAmount);
                    totalAmt += saleProduct.amount;
                    totaltax += saleProduct.gstAmount;
                    tableContent.append("<tr><td>"+(++index)+"</td><td style=\"white-space: normal !important; word-wrap: break-word;\">"+saleProduct.product.productName+"<br><small>Batch: "+saleProduct.batchNumber+"</small></td><td>"+saleProduct.sqty+"</td><td>"+saleProduct.freeQty+"</td>" +
                        "<td>"+(saleProduct.amount-saleProduct.gstAmount).toFixed(2)+"</td><td>"+saleProduct.gstAmount.toFixed(2)+"</td><td>"+saleProduct.amount.toFixed(2)+"</td></tr>")
                });

                var availableCredits = 0.0;
                var availableCreditsTable = "";
                $("#creditsTable").empty();
                var i = 0;
                $.each(saleReturns, function (index, saleReturn) {
                    availableCredits += saleReturn.balance;
                    if (saleReturn.balance > 0) {
                        var checkbox = "<input type='checkbox' class='creditSelection'  data-id='"+saleReturn.id+"' data-balance='"+saleReturn.balance+"'/>";
                        availableCreditsTable += "<tr><td>" + (++i) + "</td><td>" + saleReturn.invoiceNumber + "</td><td>" + saleReturn.balance.toFixed(2) + "</td><td>"+checkbox+"</td></tr>";
                    }
                });
                $("#creditsTable").html(availableCreditsTable);

                if(availableCredits > 0)
                {
                    $("#paymentsAlert").html("<div class=\"alert alert-primary\" role=\"alert\">\n" +
                        "<div class=\"container\">\n" +
                        "    <strong><u>₹"+availableCredits.toFixed(2)+"</u> Total Credits Available!</strong>"+
                        "</div>\n" +
                        "</div>")
                }
                else
                {
                    $("#paymentsAlert").html("");
                }

                var totalPaid = totalAmt - invoice.balance;
                $("#subtotal").html(subtotal.toFixed(2));
                $("#totaltax").html(totaltax.toFixed(2));
                $("#totalAmt").html(totalAmt.toFixed(2));
                $("#totalPaid").html(totalPaid.toFixed(2));
                $(".totalDue").html(invoice.balance.toFixed(2));
                $("#badgeContainer").html(badgeContainer);

                var previousPaymentsTable = $("#previousPaymentsTable");
                var tableContent1 = "";
                var rowStyle = "";
                previousPaymentsTable.html("<td colspan=\"5\">No Receipts for this Invoice</td>");
                $.each(receiptLog, function (index, value) {
                    previousPaymentsTable.html("");
                    var cancelCreditsButton = "<a data-id="+value.receipt.id+" data-billid="+value.billId+" href='#' class='btn btn-sm btn-danger cancelReceipt'><i class='fa fa-times'></i></a>"
                    if(value.receiptStatus == "CANCELLED")
                    {
                        rowStyle = "style='text-decoration-line: line-through;'";
                        cancelCreditsButton = "";
                    }
                    else
                    {
                        rowStyle = "";
                    }
                    var date = moment(value.receipt.paymentDate.split("T")[0],"YYYY-MM-DD").format("DD/MM/YYYY");
                    tableContent1 += "<tr "+rowStyle+" ><td>"+(++index)+"</td><td>"+value.receipt.receiptId+"</td><td>"+date+"</td><td>"+value.amountPaid.toFixed(2)+"</td><td>"+cancelCreditsButton+" <a href='#' class='btn btn-sm btn-info print' data-custid="+invoice.customerId+" data-id="+value.receipt.id+"><i class='fa fa-print'></i></a></td></tr>";
                });
                previousPaymentsTable.append(tableContent1);

                var creditsAdjustmentTable = $("#creditsAdjustmentTable");
                var tableContent2 = "";
                creditsAdjustmentTable.html("<td colspan=\"5\">No Credit Adjustments for this Invoice</td>");
                $.each(saleReturnAdjustmentDetails, function (index, value) {
                    creditsAdjustmentTable.html("");
                    var date = moment(value.dateCreated.split("T")[0],"YYYY-MM-DD").format("DD/MM/YYYY");
                    tableContent2 += "<tr><td>"+(++index)+"</td><td>"+value.saleReturnAdjustment.docNo+"</td><td>"+date+"</td><td>"+value.adjAmount.toFixed(2)+"</td><td><a href='#' class='btn btn-sm btn-danger cancelCredit'><i class='fa fa-times'></i></a> <a href='#' class='btn btn-sm btn-info printCredits' data-custid="+invoice.customerId+" data-id="+invoice.id+"><i class='fa fa-print'></i></a></td></tr>";
                });
                creditsAdjustmentTable.append(tableContent2);

                //reset fields
                $("#amount").val("");
                $("#cardNumber").val("");
                $("#paymentDate").val("");
                $("#instrumentId").val("");
                $("#remarks").text("");
                $("#saleReturnIds").val("");
                $("#creditsApplied").text("");
            },
            error: function () {
                $(".detailsSpinner").addClass("hidden");
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

    function applyCredits(creditAvailable) {
        var totalDueOfSelected = parseFloat2Decimal($("#creditsTotalDue").text());
        if(creditAvailable > totalDueOfSelected) {
            $("#creditsApplied").text(totalDueOfSelected.toFixed(2));
        }
        else {
            creditAvailable = parseFloat2Decimal($("#creditsApplied").text()) + creditAvailable;
            if(creditAvailable > totalDueOfSelected)
                $("#creditsApplied").text(totalDueOfSelected.toFixed(2));
            else
                $("#creditsApplied").text(creditAvailable.toFixed(2));
        }
    }
    function removeCredits(creditAmount)
    {
        var appliedCredit = parseFloat2Decimal($("#creditsApplied").text());
        appliedCredit = appliedCredit-creditAmount;
        if(appliedCredit <= 0)
            $("#creditsApplied").text("0.00");
        else
            $("#creditsApplied").text(parseFloat2Decimal(appliedCredit));
    }

    function paymentModeChange() {
       var paymentMode = $("#paymentMode :selected").text();
       if(paymentMode === "CARD")
       {
           $("#cardNumberContainer").removeClass("hidden");
           $("#instrumentIdContainer").addClass("hidden");
           $("#payeeBankerContainer").addClass("hidden");
           $("#depositToContainer").removeClass("hidden");
           $("#paymentMethodContainer").removeClass("hidden");

           $("#paymentDateContainer").removeClass("col-md-6");
           $("#paymentDateContainer").addClass("col-md-12");
       }
       else if(paymentMode === "BANK")
       {
           $("#cardNumberContainer").addClass("hidden");
           $("#instrumentIdContainer").removeClass("hidden");
           $("#payeeBankerContainer").removeClass("hidden");
           $("#depositToContainer").removeClass("hidden");
           $("#paymentMethodContainer").removeClass("hidden");

           $("#paymentDateContainer").removeClass("col-md-12");
           $("#paymentDateContainer").addClass("col-md-6");
       }
       else
       {
           //cash
           $("#cardNumberContainer").addClass("hidden");
           $("#instrumentIdContainer").addClass("hidden");
           $("#payeeBankerContainer").addClass("hidden");
           $("#depositToContainer").addClass("hidden");
           $("#paymentMethodContainer").addClass("hidden");

           $("#paymentDateContainer").removeClass("col-md-6");
           $("#paymentDateContainer").addClass("col-md-12");

       }
    }

    function recordPayment() {
        var totalDueOfSelected = parseFloat2Decimal($("#totalDueOfSelected").text());
        if(totalDueOfSelected === 0)
        {
            Swal.fire({
                title: "Invoice settled already!",
                text: "There is no due for this invoice",
                showDenyButton: false,
                showCancelButton: false,
                showCloseButton: true,
                showConfirmButton: true
            });
            return
        }
        var spinner = "<div class=\"col-md-12\">\n" +
            "                    <div class=\"text-center\">\n" +
            "                        <div class=\"spinner-border\" role=\"status\">\n" +
            "                            <span class=\"sr-only\">We are recording payment information please wait!</span>\n" +
            "                        </div>\n" +
            "<p>We are recording payment information please wait!</p>\n" +
            "                    </div>\n" +
            "                </div>";

        var processingSwal = Swal.fire({
            title: "Recording Payment..",
            html: spinner,
            showDenyButton: false,
            showCancelButton: false,
            showCloseButton: false,
            showConfirmButton: false
        });
        var amount = parseFloat2Decimal($("#amount").val());
        var paymentMode = $("#paymentMode").val();
        var paymentMethod = $("#paymentMethod").val();
        var depositTo = $("#depositTo").val();
        var payeeBanker = $("#payeeBanker").val();
        var cardNumber = $("#cardNumber").val();
        var paymentDate = $("#paymentDate").val();
        var instrumentId = $("#instrumentId").val();
        var remarks = $("#remarks").val();
        var saleBillId = $(".saleBillId").val();
        var saleReturnIds = $("#saleReturnIds").val();
        var creditsApplied = parseFloat2Decimal($("#creditsApplied").text());

        if(paymentDate == null || paymentDate === "")
        {
            processingSwal.close();

            Swal.fire({
                title: "Error",
                html: "Please select payment date",
                icon: 'error'
            });
            return;
        }

        if((amount + creditsApplied) >totalDueOfSelected)
        {
            processingSwal.close();
            Swal.fire({
                title: "Error",
                html: "Payment amount should be less than Due Amount",
                icon: 'error'
            });
            return;
        }


        $.ajax({
            url: "/sale-bill/record-payment",
            method: "POST",
            data:{
                amount: amount,
                paymentMode: paymentMode,
                paymentMethod: paymentMethod,
                depositTo: depositTo,
                payeeBanker: payeeBanker,
                cardNumber: cardNumber,
                paymentDate: paymentDate,
                instrumentId: instrumentId,
                remarks: remarks,
                saleBillId: saleBillId,
                saleReturnIds: saleReturnIds,
                creditsApplied: creditsApplied
            },
            success: function(saleBill)
            {
                processingSwal.close();
                Swal.fire({
                    title: "Success!",
                    html: "Payment recorded for this invoice",
                    icon: 'success'
                });
                loadSaleInvoiceTable()
                listItemClicked(saleBill.id);
            },
            error: function (data) {
                processingSwal.close();
                var text = "Please try later!";
                if(data !== undefined)
                    text = data.responseText;
                Swal.fire({
                    title: "Error!",
                    html: text,
                    icon: 'error'
                });
            }
        })
    }

    function printReceipt() {
        /* window.addEventListener('load', function () {*/
        $('#reciptPrint').printThis({
            importCSS: true,
            printDelay: 2000,
            importStyle: true,
            base: "",
            pageTitle: ""
        });
    }

    $(document).on("click", ".print", function () {
        var custId =  $(this).data('custid');
        var id =  $(this).data('id');
        $("#printabel").remove();
        receiptPrint(custId, id)
    });

    function receiptPrint(custId,id) {
        $("<iframe id='printabel'>")
            .hide()
            .attr("src", "/print-recipt/"+custId+"/recipt/"+id)
            .appendTo("body");
    }

    $(document).on("click", ".printCredits", function () {
        var custId =  $(this).data('custid');
        var id =  $(this).data('id');
        $("#printabel").remove();
        creditsPrint(custId, id)
    });
    function creditsPrint(custId,id) {
        $("<iframe id='printabel'>")
            .hide()
            .attr("src", "/sale-return/sale-return-adjustment/print/"+id)
            .appendTo("body");
    }


    $(document).on("click", ".creditSelection", function () {
        var id =  $(this).data('id');
        var balance =  parseFloat2Decimal($(this).data('balance'));
        var saleReturnIds = $("#saleReturnIds").val();
        if($(this).is(':checked')) {
            if (saleReturnIds != null) {
                saleReturnIds = saleReturnIds + id + ",";
            } else
                saleReturnIds = id + ",";
            applyCredits(balance);
        }
        else
        {
            saleReturnIds = saleReturnIds.replace(id+",","");
            removeCredits(balance);

        }

        $("#saleReturnIds").val(saleReturnIds);

    });

    $(document).on("click", ".cancelReceipt", function () {
        var id =  $(this).data('id');
        var billId =  $(this).data('billid');
        Swal.fire({
            title: 'Cancel Receipt?',
            text: "Do you want to cancel this receipt?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "receipt/cancel?id="+id,
                    method: "GET",
                    success: function (data) {
                        Swal.fire({
                            title: "Receipt Cancelled!",
                            icon: "success",
                            showDenyButton: false,
                            showCancelButton: false,
                            showCloseButton: true,
                            showConfirmButton: true
                        });
                        loadSaleInvoiceTable();
                        listItemClicked(billId);
                    },
                    error: function () {
                        Swal.fire({
                            title: "Error!",
                            text: "Please try again later",
                            icon: "danger",
                            showDenyButton: false,
                            showCancelButton: false,
                            showCloseButton: true,
                            showConfirmButton: true
                        });
                    }
                })
            }
        })
    });

    $(document).on("click", ".cancelCredit", function () {
        Swal.fire({
            title: 'Revert applied credit?',
            text: "Do you want to revert credits?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Not Enabled!",
                    text: "Canceling credits will be enabled soon.",
                    icon: "warning",
                    showDenyButton: false,
                    showCancelButton: false,
                    showCloseButton: true,
                    showConfirmButton: true
                });
            }
        });
    });

    function adjustCredits()
    {
        var saleBillId = $(".saleBillId").val();
        var saleReturnIds = $("#saleReturnIds").val();
        var creditsApplied = parseFloat2Decimal($("#creditsApplied").text());
        var totalDueOfSelected = parseFloat2Decimal($("#creditsTotalDue").text());
        if(totalDueOfSelected === 0)
        {
            Swal.fire({
                title: "Invoice settled already!",
                text: "There is no due for this invoice",
                showDenyButton: false,
                showCancelButton: false,
                showCloseButton: true,
                showConfirmButton: true
            });
            return
        }

        if(creditsApplied === 0)
        {
            Swal.fire({
                title: "Credits not selected",
                text: "No credits selected to adjust.",
                showDenyButton: false,
                showCancelButton: false,
                showCloseButton: true,
                showConfirmButton: true
            });
            return
        }

        var spinner = "<div class=\"col-md-12\">\n" +
            "                    <div class=\"text-center\">\n" +
            "                        <div class=\"spinner-border\" role=\"status\">\n" +
            "                            <span class=\"sr-only\">We are adjusting available credits, please wait!</span>\n" +
            "                        </div>\n" +
            "<p>We are adjusting available credits, please wait!</p>\n" +
            "                    </div>\n" +
            "                </div>";

        var processingSwal = Swal.fire({
            title: "Adjusting Credits..",
            html: spinner,
            showDenyButton: false,
            showCancelButton: false,
            showCloseButton: false,
            showConfirmButton: false
        });
        $.ajax({
            url: "sale-bill/adjust-credits",
            method: "POST",
            data:{
                saleBillId: saleBillId,
                saleReturnIds: saleReturnIds,
                creditsApplied: creditsApplied
            },
            success: function(saleBill)
            {
                processingSwal.close();
                Swal.fire({
                    title: "Success!",
                    html: "Credits Adjusted for this invoice",
                    icon: 'success'
                });
                $("#creditsApplied").text("0.00");
                listItemClicked(saleBill.id);
            },
            error: function () {
                processingSwal.close();
                Swal.fire({
                    title: "Error!",
                    html: "Please try later!",
                    icon: 'error'
                });
            }
        })
    }
    
    function parseFloat2Decimal(num)
    {
        if(!isNaN(num)) {
            num = Number(num);
            num = Math.round(num * 1e2) / 1e2;
            return num;
        }
    }

    /*function setTwoNumberDecimal(e) {
        if(e.value.includes('.') && e.value.split(".")[2].length > 2) {
            e.value = parseFloat(e.value).toFixed(2);
        }
    }
    function amountFormat(e) {
        if(!e.value.includes("."))
        {
            alert(e.value);
            e.value = e.value + ".00";
        }
    }*/
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>