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
                            <div class="col-md-5">
                                <div class="form-group">
                                    <div class="input-group inlineblock">
                                        <label for="dateRange">Date Range:</label>
                                        <input id="dateRange" class="dateRange" type="text" name="dateRange"
                                               style="border-radius: 6px;margin: 4px;"/>
                                        <button class="input-group-btn btn btn-info btn-sm"
                                                onclick="getReport()">Get Report</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-5 d-flex justify-content-center">
                                <div class="form-group">
                                    <div class="input-group inlineblock">
                                        <label>Export:</label>
                                        <button class="input-group-btn btn btn-info btn-sm" id="btnExport"><i class="fa fa-file-excel-o"></i> Excel</button>
                                        <button class="input-group-btn btn btn-danger btn-sm" id="btnPrint"><i class="fa fa-print"></i> Print</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2 d-flex justify-content-center">
                                <div class="checkbox inlineblock">
                                    <input id="paidInvoice" type="checkbox" checked/>
                                    <label for="paidInvoice">
                                        Show Paid Invoices
                                    </label>
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
    var dueOnDate = "";
    $('.dateRange').daterangepicker({
        locale: {
            format: "DD/MM/YYYY"
        },
        maxDate: moment()
    }).on('apply.daterangepicker', function(ev, picker) {
        dueOnDate = moment(picker.endDate).format('DD/MM/YYYY');
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
        var customerDue = 0;
        var customerBalance = 0;
        var customerTotalDue = 0;
        // var sortBy = $('.sortBy').val();
        var paidInvoice = $("#paidInvoice").is(":checked") ? "true" : "false";
        $.ajax({
            url: "/reports/accounts/getoutstanding?dateRange=" + dateRange + "&paidInvoice="+paidInvoice,
            type: "GET",
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                var content = "";
                var mainTableHeader = "<table class='table table-bordered table-sm' style='width: 100%;'><thead>" +
                    "<tr><td data-f-bold='true' colspan='10'><h3 style='margin-bottom:0 !important;'>${session.getAttribute('entityName')}</h3></td></tr>" +
                    "<tr><td colspan='10'>${session.getAttribute('entityAddress1')} ${session.getAttribute('entityAddress2')} ${session.getAttribute('entityPinCode')}, ph: ${session.getAttribute('entityMobileNumber')}</td></tr>" +
                    "<tr><th data-f-bold='true' colspan='10'>Outstanding Report, Date: " +
                    dateRange + "</th></tr>" +
                    //"<tr><th colspan='6'></th><th data-f-bold='true'><strong>Grand Total:</strong> <span id='grandTotal'></span></th></tr>" +
                    //"<tr><th data-f-bold='true'>Customer</th><th data-f-bold='true'>Net Amount</th>"+
                    "<th data-f-bold='true'>Customer</th><th data-f-bold='true'>Fin. Year</th><th data-f-bold='true'>Tran. Type</th><th data-f-bold='true'>Tran. No.</th><th data-f-bold='true'>Date</th><th data-f-bold='true'>Due Date</th><th data-f-bold='true'>Due On "+dueOnDate+"</th><th data-f-bold='true'>Not Due</th><th data-f-bold='true'>Total Due</th><th data-f-bold='true'>Days</th></tr></thead><tbody>";
                var billDetails = "";
                $.each(data, function (key, city) {
                    billDetails = "";
                    var cityName = "<tr><td colspan='9' data-f-bold='true'>Area: <span class='customerData cust" +
                        key + "'><strong>" + key + "</strong></span></td></tr>";
                    var customerInfo = "";
                    var customerDue = 0;
                    var customerBalance = 0;
                    var customerTotalDue = 0;
                    $.each(city, function (customer, invs) {
                        var bills = "<tr><td colspan='10' data-f-bold='true'>"+customer+"</td></tr>";
                        var customerDue = 0;
                        var customerBalance = 0;
                        var customerTotalDue = 0;
                        $.each(invs, function (key, bill) {
                            var totalDue = bill.balance - bill.due;
                            customerDue += bill.due;
                            customerBalance += bill.balance;
                            customerTotalDue += totalDue;
                            console.log(bill.balance)
                            bills += "<tr><td></td>" +
                                "<td>" + bill.financialYear + "</td>" +
                                "<td>" + bill.transactionType + "</td>" +
                                "<td>" + bill.transactionNumber + "</td>" +
                                "<td>" + dateFormat(bill.transactionDate) + "</td>" +
                                "<td>" + dateFormat(bill.dueDate) + "</td>" +
                                "<td>" + formatNumber(bill.due.toFixed(2)) + "</td>" +
                                "<td>" + formatNumber(bill.balance.toFixed(2)) + "</td>" +
                                "<td>" + formatNumber(totalDue.toFixed(2)) + "</td>" +
                                "<td>" + moment(new Date()).diff(moment(dateFormat(bill.dueDate), "DD/MM/YYYY"), 'days') + "</td></tr>";
                        });
                        var customerTotal =
                            "<tr><td colspan='6'></td><td data-f-bold='true'><u><strong>"+formatNumber(customerDue.toFixed(2))+"</strong></u></td><td data-f-bold='true'><u><strong>"+formatNumber(customerBalance.toFixed(2))+"</strong></u></td>" +
                            "<td data-f-bold='true'><u><strong>"+formatNumber(customerTotalDue.toFixed(2))+"</strong></u></td><td data-f-bold='true'></td></tr>";
                        customerInfo += (bills + customerTotal);
                    });
                    billDetails += customerInfo;
                    content += cityName + billDetails;
                });
                var mainTableFooter = "</tbody></table>";
                $("#result").html(mainTableHeader + content + mainTableFooter);
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


    function formatNumber(n)
    {
        return Number(n).toLocaleString()
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>