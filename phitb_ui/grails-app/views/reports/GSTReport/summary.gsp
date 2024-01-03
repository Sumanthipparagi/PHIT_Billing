<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  GST Summary</title>
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
                    <h2>GST Summary</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">GST Summary</li>
                    </ul>
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
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>
