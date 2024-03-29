<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Expiry Statement</title>
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
    <asset:stylesheet src="/themeassets/plugins/fontawesome/css/all.css" rel="stylesheet"/>

    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">
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
                    <h2>Expiry Statement</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Expiry Statement</li>
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
                            <div class="col-md-9">
                                <div class="form-group">
                                    <div class="input-group inlineblock">
                                        <label for="dateFrom">From:</label>
                                        <input type="month" id="dateFrom" name="dateFrom">
                                        <label for="dateTo">To:</label>
                                        <input type="month" id="dateTo" name="dateTo">
                                        <button class="input-group-btn btn btn-info btn-sm"
                                                data-toggle="modal" data-target="#myModal" ><i class="fa fa-filter"></i> </button>
                                        <button class="input-group-btn btn btn-success btn-sm"
                                                onclick="getReport()">Get Report</button>

                                    </div>
                                </div>
                            </div>

                        %{--    <div class="col-md-2 d-flex justify-content-center">
                                <div class="form-group">
                                    <div class="input-group inlineblock">
                                        <label for="entitySelect">Entity:</label>
                                        <select name="entity" id="entitySelect">
                                            <g:each in="${entities}" var="en">
                                                <option value="${en.id}">${en.entityName}</option>
                                            </g:each>
                                        </select>
                                    </div>
                                </div>
                            </div>--}%

                            <div class="col-md-3 d-flex justify-content-center">
                                <div class="form-group">
                                    <div class="input-group inlineblock">
                                        <label>Export:</label>
                                        <button class="input-group-btn btn btn-info btn-sm" id="btnExport"><i
                                                class="fa fa-file-excel"></i> Excel</button>
                                        <button class="input-group-btn btn btn-danger btn-sm" id="btnPrint"><i
                                                class="fa fa-print"></i> Print</button>
                                    </div>
                                </div>
                            </div>

                          %{--  <div class="col-lg-6">
                            <div class="form-group">
                            <label for="entitySelect">Entity:</label>
                            <select name="entity" id="entitySelect">
                                <g:each in="${entities}" var="en">
                                    <option value="${en.id}">${en.entityName}</option>
                                </g:each>
                            </select>
                            </div>
                            </div>--}%

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

<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>
<script>
    var filterType = "";
  /*  $('.dateRange').daterangepicker({
        locale: {
            format: "MMM/YYYY"
        },
        "showDropdowns": true
    }).on('apply.daterangepicker', function (ev, picker) {
        dueOnDate = moment(picker.endDate).format('MMM/YYYY');
    });*/

    $("#entitySelect").select2();

    function getReport() {

        var dateFrom = $('#dateFrom').val();
        var dateTo = $('#dateTo').val();

        if(dateFrom === null || dateFrom === "" || dateTo === null || dateTo === "")
        {
            alert("Select Month!");
            return;
        }

        var entitySelect = $("#entitySelect").val();
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });
        var url = "/reports/inventory/getexpiry?dateFrom=" + dateFrom+"&entityId="+entitySelect+"&dateTo="+dateTo;
        var ids = null;
        switch (filterType)
        {
            case "ALL":
                break;
            case "PRODUCT":

                var checkboxes = document.getElementsByName("products");
                // Loop through the radio buttons
                for (var i = 0; i < checkboxes.length; i++) {
                    // Check if the current radio button is checked
                    if (checkboxes[i].checked) {
                        if(ids === null)
                            ids = checkboxes[i].value + ",";
                        else
                            ids += checkboxes[i].value + ",";
                    }
                }
                url += "&productids="+ids;
                break;
            case "GROUP":
                var checkboxes = document.getElementsByName("groups");
                // Loop through the radio buttons
                for (var i = 0; i < checkboxes.length; i++) {
                    // Check if the current radio button is checked
                    if (checkboxes[i].checked) {
                        if(ids === null)
                            ids = checkboxes[i].value + ",";
                        else
                            ids += checkboxes[i].value + ",";
                    }
                }
                url += "&groupids="+ids;
                break;
            case "SUPPLIER":
                var checkboxes = document.getElementsByName("company");
                // Loop through the radio buttons
                for (var i = 0; i < checkboxes.length; i++) {
                    // Check if the current radio button is checked
                    if (checkboxes[i].checked) {
                        if(ids === null)
                            ids = checkboxes[i].value + ",";
                        else
                            ids += checkboxes[i].value + ",";
                    }
                }
                url += "&supplierids="+ids;
                break;
            case "COMPANY":
                var checkboxes = document.getElementsByName("company");
                // Loop through the radio buttons
                for (var i = 0; i < checkboxes.length; i++) {
                    // Check if the current radio button is checked
                    if (checkboxes[i].checked) {
                        if(ids === null)
                            ids = checkboxes[i].value + ",";
                        else
                            ids += checkboxes[i].value + ",";
                    }
                }
                url += "&companyids="+ids;
                break;
        }

        $.ajax({
            url:url,
            type: "GET",
            contentType: false,
            processData: false,
            success: function (data) {
                loading.close();
                var content = "";
                var mainTableHeader = "<table class='table table-bordered table-sm' style='width: 100%;'><thead>" +
                    "<tr><td data-f-bold='true' colspan='6'><h3 style='margin-bottom:0 !important;'>${session.getAttribute('entityName')}</h3></td></tr>" +
                    "<tr><td colspan='6'>${session.getAttribute('entityAddress1')} ${session.getAttribute('entityAddress2')} ${session.getAttribute('entityPinCode')}, ph: ${session.getAttribute('entityMobileNumber')}</td></tr>" +
                    "<tr><th data-f-bold='true' colspan='6'>Expiry Report, Date From: " +
                    dateFrom + " | Date Upto: "+dateTo+"</th></tr>" +
                    "<tr><th data-f-bold='true'>Sl No.</th><th data-f-bold='true'>Product Name</th><th data-f-bold='true'>Batch Number</th><th data-f-bold='true'>Exp Date</th><th data-f-bold='true'>Qty</th><th data-f-bold='true'>Value</th>" +
                    "</thead><tbody>";
                var stockDetails = "";
                var i = 0;
                $.each(data, function (key, vl) {
                    if(key !== "ALL") {
                        content += "<tr><td data-f-bold='true' colspan='6'><strong>" + key + "</strong></td></tr>";
                    }
                        $.each(vl, function (key, value) {
                            i += 1;
                            var qty = value?.remainingQty + value?.remainingFreeQty;
                            var val = qty * value?.saleRate;
                            stockDetails = "<tr>" +
                                "<td>" + i + "</td>" +
                                "<td>" + value?.productName + "</td>" +
                                "<td>" + value?.batchNumber + "</td></td>" +
                                "<td>" + value?.expDate + "</td><td>" + qty + "</td>" +
                                "<td>" + val.toFixed(2) + "</td></tr>";
                            content += stockDetails
                        });
                });
                var mainTableFooter = "</tbody></table>";
                $("#result").html(mainTableHeader + content + mainTableFooter);

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
            name: 'expiry-report.xlsx',
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

    function dateFormat(dt) {
        dt = dt.replace("T", " ").replace("Z", '');
        var date = moment(dt, 'DD/MM/YYYY HH:mm:ss');
        //return moment(date).format('DD/MM/YYYY hh:mm:ss a');
        return moment(date).format('DD/MM/YYYY');
    }

    function today() {
        var date = new Date();
        return moment(date).format('DD/MM/YYYY');
    }


</script>
<g:include view="controls/reports/expiry-filter.gsp"/>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>