<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Customer ledger</title>
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
                                 alt="pharmit"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Customer Ledger</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Customer Ledger</li>
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
                            %{--                            <div class="col-md-2">--}%
                            %{--                                <div class="form-group">--}%
                            %{--                                    <label for="sortBy">Sort By:</label>--}%
                            %{--                                    <select style="margin-top: 5px; border-radius: 6px;" id="sortBy" class="sortBy form-control" name="sortBy">--}%
                            %{--                                        <option value="default">DEFAULT</option>--}%
                            %{--                                        <option value="invoice-date">INVOICE DATE</option>--}%
                            %{--                                    </select>--}%
                            %{--                                </div>--}%
                            %{--                            </div>--}%
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

                            <div class="form-group">
                                <label>Customer:</label>

                                <div class="input-group">
                                    <select class="customerSelect" name="customer"
                                            style="margin-top: 10px; width: 100%;">
                                        <g:each in="${entities}" var="entity">
                                            <option value="${entity.id}">${entity.entityName} (${entity.entityType.name})</option>
                                        </g:each>
                                    </select>
                                    <button class="input-group-btn btn btn-info btn-sm"
                                            onclick="getReport()">Get Report</button>
                                </div>
                            </div>
                            </div>
                            <div class="col-lg-6  d-flex justify-content-center">
                                <div class="form-group">
                                    <label>Export</label>

                                    <div class="input-group">
                                        <button class="input-group-btn btn btn-info" id="btnExport">Excel</button>
                                        %{-- <button class="input-group-btn btn btn-success" id="btnPdf">PDF</button>--}%
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
            url: "/reports/sales/get-customer-ledger??dateRange=" + dateRange,
            type: "GET",
            contentType: false,
            processData: false,
            success: function (data) {
                var content = "";
                var grandTotal = 0.00;
                var mainTableHeader = "<table class='table-bordered table-sm' style='width: 100%;color: #212529;'><thead>" +
                    "<tr><td data-f-bold='true' colspan='11'><h3 style='margin-bottom:0 !important;'>${session.getAttribute('entityName')}</h3></td></tr>" +
                    "<tr><td colspan='10'>${session.getAttribute('entityAddress1')} ${session.getAttribute('entityAddress2')} ${session.getAttribute('entityPinCode')}, ph: ${session.getAttribute('entityMobileNumber')}</td></tr>" +
                    "<tr><th data-f-bold='true' colspan='10'>Customer Ledger, Date: " + dateRange + "</th></tr>" +
                    "<tr><th data-f-bold='true'>Date.</th><th data-f-bold='true'>Transaction No.</th><th data-f-bold='true'>Transfer.</th><th data-f-bold='true'>Debit</th>" +
                    "<th data-f-bold='true'>Credit</th><th data-f-bold='true'>Balance</th></tr></thead><tbody>";
                var index = 1;

                var mainTableFooter = "</tbody></table>";

                $("#result").html(mainTableHeader + content + mainTableFooter);
                loading.close();
                $("#grandTotal").text(grandTotal.toFixed(2));
            },
            error: function () {
                loading.close();
                swal("Error!", "Unable to generate report at the moment", "error");
            }
        })
    }

    $("#btnExport").click(function () {
        let table = document.getElementById("result");
        TableToExcel.convert(table, {
            name: 'customerwise-sales-report.xlsx',
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
        var date = new Date(dt);
        return moment(date).format('DD/MM/YYYY hh:mm:ss a');

    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>