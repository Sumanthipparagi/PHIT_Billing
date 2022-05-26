<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Outstanding Report</title>
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
                    <h2>Outstanding Report</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Outstanding Report</li>
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
            <div class="col-lg-12">
                <div class="card">
                    <div class="header">
                        <div class="row">
                            <div class="col-lg-6">
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

                            <div class="col-lg-6 d-flex justify-content-center">
                                <div class="form-group">
                                    <label>Export</label>

                                    <div class="input-group">
                                        <button class="input-group-btn btn btn-info" id="btnExport">Excel</button>
                                        <button class="input-group-btn btn btn-danger" id="btnPrint">Print</button>
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

        $.ajax({
            url: "/reports/accounts/getoutstanding?dateRange=" + dateRange,
            type: "GET",
            contentType: false,
            processData: false,
            success: function (data) {
                var content = "";
                var grandTotal = 0.00;
                var totalNetAmt = 0.00;
                var mainTableHeader = "<table class='table table-bordered table-sm' style='width: 100%;'><thead>" +
                    "<tr><td data-f-bold='true' colspan='10'><h3 style='margin-bottom:0 !important;'>${session.getAttribute('entityName')}</h3></td></tr>" +
                    "<tr><td colspan='10'>${session.getAttribute('entityAddress1')} ${session.getAttribute('entityAddress2')} ${session.getAttribute('entityPinCode')}, ph: ${session.getAttribute('entityMobileNumber')}</td></tr>" +
                    "<tr><th data-f-bold='true' colspan='10'>Customer-Bill-Area-wise Sales* Detail, Date: " +
                    dateRange + "</th></tr>" +
                    "<tr><th colspan='6'></th><th data-f-bold='true'><strong>Grand Total:</strong> <span id='grandTotal'></span></th></tr>" +
                    //"<tr><th data-f-bold='true'>Customer</th><th data-f-bold='true'>Net Amount</th>"+
                    "<th data-f-bold='true'>Customer</th><th data-f-bold='true'>Tran. Type</th><th data-f-bold='true'>Tran. No.</th><th data-f-bold='true'>Date</th><th data-f-bold='true'>Due Date</th><th data-f-bold='true'>Due On "+today()+"</th><th data-f-bold='true'>Not Due</th><th data-f-bold='true'>Total Due</th><th data-f-bold='true'>Days</th></tr></thead><tbody>";
                var billDetails = "";
                var totalSqty = 0;
                var totalfQty = 0;
                var totalNtv = 0;
                var totalDiscount = 0;
                var totalGst = 0;
                $.each(data, function (key, city) {
                    billDetails = "";
                    var cityName = "<tr><td colspan='8' data-f-bold='true'><span class='customerData cust" +
                        key + "'><strong>" + key + "</strong></span></td></tr>";
                    var cityNetAmtTotal = 0;
                    var cityTotalSqty = 0;
                    var cityTotalfQty = 0;
                    var cityTotalNtv = 0;
                    var cityTotalDiscount = 0;
                    var cityTotalGst = 0;
                    var bills = "";
                    $.each(city, function (key, bill) {
/*                        cityNetAmtTotal += product.netAmount;
                        cityTotalSqty += product.sQty;
                        cityTotalfQty += product.fQty;
                        cityTotalNtv += product.ntv;
                        cityTotalDiscount += product.discount;
                        cityTotalGst += product.gst;*/
                        var totalDue = bill.balance - bill.due;
                        bills += "<tr><td></td>" +
                            "<td>" + bill.transactionType + "</td>" +
                            "<td>" + bill.transactionNumber + "</td>" +
                            "<td>" + dateFormat(bill.transactionDate) + "</td>" +
                            "<td>" + dateFormat(bill.dueDate) + "</td>" +
                            "<td>" + bill.due + "</td>" +
                            "<td>" + bill.balance + "</td>"+
                            "<td>" + totalDue + "</td>"+
                            "<td>"+moment(new Date()).diff(moment(dateFormat(bill.dueDate), "DD/MM/YYYY"), 'days')+"</td></tr>";
                    });
                    billDetails += bills;
                   /* totalSqty += cityTotalSqty;
                    totalfQty += cityTotalfQty;
                    totalNtv += cityTotalNtv;
                    totalDiscount += cityTotalDiscount;
                    totalGst += cityTotalGst;
                    billDetails += products;
                    grandTotal += cityNetAmtTotal;
                    var cityFooter = "<tr><td></td><td data-f-bold='true'><strong>Area Total:</strong></td><td data-f-bold='true'><u><strong>" + cityTotalSqty + "</strong></u></td><td data-f-bold='true'><u><strong>" + cityTotalfQty + "</u></strong></td><td data-f-bold='true'><u><strong>" + cityTotalNtv.toFixed(2) + "</strong></u></td><td data-f-bold='true'><u><strong>" + cityTotalDiscount.toFixed(2) + "</strong></u></td><td data-f-bold='true'><u><strong>" + cityTotalGst.toFixed(2) + "</strong></u></td><td data-f-bold='true'><u><strong>" + cityNetAmtTotal.toFixed(2) + "</strong></u></td></tr>";
                    content += cityName + billDetails + cityFooter;*/

                    content += cityName + billDetails;
                });
               /* var total = "<tr><th></th><th></th><th data-f-bold='true'><u>" + totalSqty + "</u></th data-f-bold='true'><th><u>" + totalfQty + "</u></th><th data-f-bold='true'><u>" + totalNtv.toFixed(2) + "</u></th><th><u>" + totalDiscount.toFixed(2) + "</u></th><th data-f-bold='true'><u>" + totalGst.toFixed(2) + "</u></th><th data-f-bold='true'><strong><u><span id='Total'>" + grandTotal.toFixed(2) + "</span></u></strong></th></tr>"
                var mainTableFooter = "</tbody></table>";

                $("#result").html(mainTableHeader + content + total + mainTableFooter);*/
                var mainTableFooter = "</tbody></table>";
                $("#result").html(mainTableHeader + content);
                loading.close();
               /* $("#grandTotal").text(grandTotal.toFixed(2));
                $("#Total").text(grandTotal.toFixed(2));*/
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
            name: 'areawise-sales-report.xlsx',
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

    /*  $(document).ready(function() {
          window.jsPDF = window.jspdf.jsPDF;
          $("#btnPdf").click(function () {
              var doc = new jsPDF();
              doc.autoTable({ html: '#result' });
              doc.save('table.pdf');
          });
      });*/

    function dateFormat(dt)
    {
        dt = dt.replace("T", " ").replace("Z", '');
        var date = new Date(dt);
        //return moment(date).format('DD/MM/YYYY hh:mm:ss a');
        return moment(date).format('DD/MM/YYYY');
    }

    function today() {
        var date = new Date();
        return moment(date).format('DD/MM/YYYY');
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>