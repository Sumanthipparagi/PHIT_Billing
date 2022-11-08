<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  GST Sales Report</title>
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
    <asset:stylesheet src="/themeassets/plugins/daterangepicker/daterangepicker.css" rel="stylesheet"/>

    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>

    <style>

    @media print {
        .pagebreak {
            page-break-before: always;
        }
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
                    <h2>GST Report</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">GST Sales Report</li>
                    </ul>
                </div>

                %{--                <div class="col-lg-7 col-md-7 col-sm-12">--}%
                %{--                    <div class="input-group m-b-0">--}%
                %{--                        <input type="text" class="form-control" placeholder="Search...">--}%
                %{--                        <span class="input-group-addon">--}%
                %{--                            <i class="zmdi zmdi-search"></i>--}%
                %{--                        </span>--}%
                %{--                    </div>--}%
                %{--                </div>--}%
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="header">
                        <div class="row">
                            %{--                            <div class="col-md-2">--}%
                            %{--                                <div class="form-group">--}%
                            %{--                                    <label for="sortBy">Sort By:</label>--}%
                            %{--                                    <select style="margin-top: 5px; border-radius: 6px;" id="sortBy" class="sortBy form-control" name="sortBy">--}%
                            %{--                                        <option value="default">DEFAULT</option>--}%
                            %{--                                        <option value="invoice-date">INVOICE DATE</option>--}%
                            %{--                                    </select>--}%
                            %{--                                </div>--}%
                            %{--                            </div>--}%
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label>Date Range:</label>

                                    <div class="input-group">
                                        <input class="dateRange" type="text" name="dateRange"
                                               style="border-radius: 6px;margin: 4px;"/>
                                        <button class="input-group-btn btn btn-info"
                                                onclick="getReport()">Get Report</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3 mt-3">
                                <div class="form-group">
                                    <label for="gstReportType">Report Type:</label><br>
                                    <select name="gstReportType" id="gstReportType" onchange="getReport()">
                                        <option value="SALES">SALES</option>
                                        <option value="CREDIT_NOTE">CREDIT NOTE</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-4 ">
                                <div class="form-group">
                                    <label>Export</label>
                                    <div class="input-group">
                                        <button class="input-group-btn btn btn-info" id="btnExport">Excel</button>
                                        <button class="input-group-btn btn btn-danger" id="btnPrint"
                                                disabled>Print</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="body">
                        <div id="result">
                            <h2 style="color: rgba(26,131,185,0.74);">Select Date Range and Click "Get Reports"</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>


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
<asset:javascript src="/themeassets/plugins/daterangepicker/moment.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/daterangepicker.js"/>
<asset:javascript src="/themeassets/plugins/tabletoexcel/tableToExcel.js"/>
<asset:javascript src="/themeassets/plugins/jQuery.print/jQuery.print.min.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
%{--<asset:javascript src="/themeassets/plugins/jspdf/jspdf.umd.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jspdf/jspdf.plugin.autotable.js"/>--}%
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>--}%
%{--
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.js" integrity="sha512-Bw9Zj8x4giJb3OmlMiMaGbNrFr0ERD2f9jL3en5FmcTXLhkI+fKyXVeyGyxKMIl1RfgcCBDprJJt4JvlglEb3A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.23/jspdf.plugin.autotable.js" integrity="sha512-P3z5YHtqjIxRAu1AjkWiIPWmMwO9jApnCMsa5s0UTgiDDEjTBjgEqRK0Wn0Uo8Ku3IDa1oer1CIBpTWAvqbmCA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
--}%
<script>
    $('.dateRange').daterangepicker({
        locale: {
            format: "DD/MM/YYYY"
        }
    });




    function getReport() {
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });
        var dateRange = $('.dateRange').val();
        // var sortBy = $('.sortBy').val();
        var reportType =  $('#gstReportType').val();
        var url = '';

        if(reportType === "SALES"){
            url = "/reports/sales/getgstreport?dateRange="+dateRange
        }else if(reportType === "CREDIT_NOTE"){
            url = "/reports/sales/get-credit-note-gst-report?dateRange="+dateRange
        }
        $.ajax({
            url:url,
            type: "GET",
            contentType: false,
            processData: false,
            success: function (data) {
                var colSpan = 0;
                var taxes = data.taxes;
                var gstDetails = data.gstDetails;
                colSpan = 6 + (taxes.length * 5) + 1;
                var content = "";
                var mainTableHeader = "<table class='table table-bordered table-sm table-responsive' style='width: 100%;'><thead>" +
                    "<tr><td data-f-bold='true' colspan='" + colSpan + "'><h3 style='margin-bottom:0 !important;'>${session.getAttribute('entityName')}</h3></td></tr>" +
                    "<tr><td colspan='" + colSpan + "'>${session.getAttribute('entityAddress1')} ${session.getAttribute('entityAddress2')} ${session.getAttribute('entityPinCode')}, ph: ${session.getAttribute('entityMobileNumber')}</td></tr>" +
                    "<tr><th data-f-bold='true' colspan='" + colSpan + "'>GST Details* Detail, Date: " +
                    dateRange + "</th></tr>";
                var header = "<tr><th data-f-bold='true'><strong>Series</strong></th><th data-f-bold='true'><strong>Invoice No.</strong></th><th data-f-bold='true'><strong>Date</strong></th><th data-f-bold='true'><strong>Party Name</strong></th><th data-f-bold='true'><strong>Town</strong></th><th data-f-bold='true'><strong>Party GSTIN</strong></th>";
                var taxColumns = "";
                $.each(taxes, function (key, tax) {
                    taxColumns += "<th data-f-bold='true'><strong>Taxable " + tax + "%</strong></th>";
                    taxColumns += "<th data-f-bold='true'><strong>GST @" + tax + "%</strong></th>";
                    taxColumns += "<th data-f-bold='true'><strong>IGST @" + tax + "%</strong></th>";
                    taxColumns += "<th data-f-bold='true'><strong>CGST @" + tax / 2 + "%</strong></th>";
                    taxColumns += "<th data-f-bold='true'><strong>SCGST @" + tax / 2 + "%</strong></th>";
                });

                taxColumns += "<th data-f-bold='true'><strong>Bill Amount</strong></th></tr></thead><tbody>";

                mainTableHeader += header + taxColumns;
                var totalInvoiceAmount = 0.0;
                var gstData = [];
                $.each(gstDetails, function (key, gstDetail) {
                    var row = "<tr><td>" + gstDetail.seriesId + "</td><td>" + gstDetail.invoiceNumber + "</td><td>" + dateFormat(gstDetail.orderDate.split("T")[0]) + "</td><td>" + gstDetail.customerId + "</td><td>" + gstDetail.town + "</td><td>" + gstDetail.gstin + "</td>";
                    var index = 0;
                    var gstArray = [];
                    $.each(taxes, function (key, tax) {
                        var gstAmountKey = tax.toFixed(1) + "_gst_amount";
                        var gstKey = tax.toFixed(1) + "_gst";
                        var cgstKey = tax.toFixed(1) + "_cgst_" + (tax / 2).toFixed(1);
                        var sgstKey = tax.toFixed(1) + "_sgst_" + (tax / 2).toFixed(1);
                        var igstKey = tax.toFixed(1) + "_igst_" + tax.toFixed(1);

                        //GST Calculation
                        if (gstDetail[gstAmountKey]) {
                            row += "<td>" + gstDetail[gstAmountKey].toFixed(2) + "</td>";
                            gstArray.push(gstDetail[gstAmountKey]);
                        } else {
                            row += "<td>0.00</td>";
                            gstArray.push(0);
                        }

                        if (gstDetail[gstKey]) {
                            row += "<td>" + gstDetail[gstKey].toFixed(2) + "</td>";
                            gstArray.push(gstDetail[gstKey]);
                        } else {
                            row += "<td>0.00</td>";
                            gstArray.push(0);
                        }

                        if (gstDetail[igstKey]) {
                            row += "<td>" + gstDetail[igstKey].toFixed(2) + "</td>";
                            gstArray.push(gstDetail[igstKey]);
                        } else {
                            row += "<td>0.00</td>";
                            gstArray.push(0);
                        }

                        if (gstDetail[cgstKey]) {
                            row += "<td>" + gstDetail[cgstKey].toFixed(2) + "</td>";
                            gstArray.push(gstDetail[cgstKey]);
                        } else {
                            row += "<td>0.00</td>";
                            gstArray.push(0);
                        }

                        if (gstDetail[sgstKey]) {
                            row += "<td>" + gstDetail[sgstKey].toFixed(2) + "</td>";
                            gstArray.push(gstDetail[sgstKey]);
                        } else {
                            row += "<td>0.00</td>";
                            gstArray.push(0);
                        }
                    });
                    gstData.push(gstArray);
                    totalInvoiceAmount += gstDetail.invoiceTotal;
                    row += "<td>" + gstDetail.invoiceTotal.toFixed(2) + "</td></tr>";
                    content += row;
                });

                var gstTotals = [];
                if (gstData?.length > 0)
                    gstTotals = gstData.reduce((r, a) => r.map((b, i) => a[i] + b));
                var total = "<tr><td colspan='6' data-f-bold='true' style='align-content: center;text-align: center;'><strong>TOTAL</strong></td>";
                var taxCols = "";
                for (var i = 0; i < gstTotals.length; i++) {
                    taxCols += "<td data-f-bold='true'><strong>" + gstTotals[i].toFixed(2) + "</strong></td>";
                }

                total += taxCols + "<td data-f-bold='true'><strong>" + totalInvoiceAmount.toFixed(2) + "</strong></td></tr>";
                var mainTableFooter = "</tbody></table>";
                $("#result").html(mainTableHeader + content + total + mainTableFooter);
                loading.close();
            },
            error: function () {
                loading.close();
                Swal.fire("Error!", "Unable to generate report at the moment", "error");
            }
        })
    }

    $("#btnExport").click(function () {
        let table = document.getElementById("result");
        TableToExcel.convert(table, {
            name: 'gst-sales-report.xlsx',
            sheet: {
                name: 'Sheet 1' // sheetName
            }
        });
    });

    $("#btnPrint").click(function () {
        $("#result").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null,
            manuallyCopyFormValues: true,
            deferred: $.Deferred(),
            timeout: 750,
            title: null,
            doctype: '<!doctype html>'
        });
    });

    function dateFormat(date) {
        var dt = moment(date, 'YYYY-MM-DD');
        return dt.format('DD-MM-YYYY');
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>