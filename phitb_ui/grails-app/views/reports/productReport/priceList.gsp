<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Price List</title>
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
                    <h2>Price List</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Price List</li>
                    </ul>
                </div>
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
                                        <button class="input-group-btn btn btn-info btn-sm"
                                                data-toggle="modal" data-target="#myModal" ><i class="fa fa-filter"></i> </button>
                                        <button class="input-group-btn btn btn-success btn-sm"
                                                onclick="getReport()">Get Report</button>

                                    </div>
                                </div>
                            </div>

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
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });
        var url = "/reports/products/getpricelist";
        var ids = null;
        switch (filterType)
        {
            case "ALL":
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
                url += "?groupids="+ids;
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
                url += "?companyids="+ids;
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
                    "<tr><th data-f-bold='true' colspan='6'>Price List</th></tr>" +
                    "<tr><th data-f-bold='true'>Sl No.</th><th data-f-bold='true'>Product Name</th><th data-f-bold='true'>Packing</th><th data-f-bold='true'>Sale Rate</th><th data-f-bold='true'>Purchase Rate</th><th data-f-bold='true'>MRP</th>" +
                    "</thead><tbody>";
                var priceDetails = "";
                var i = 0;
                $.each(data, function (key, vl) {
                    if(key !== "ALL") {
                        content += "<tr><td data-f-bold='true' colspan='6'><strong>" + key + "</strong></td></tr>";
                    }
                    $.each(vl, function (key, value) {
                        i += 1;
                        priceDetails = "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + value?.productName + "</td>" +
                            "<td>" + value?.unitPacking + "</td></td>" +
                            "<td>" + value?.saleRate + "</td><td>" + value?.purchaseRate + "</td>" +
                            "<td>" + value?.mrp + "</td></tr>";
                        content += priceDetails
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
        var date = new Date(dt);
        //return moment(date).format('DD/MM/YYYY hh:mm:ss a');
        return moment(date).format('DD/MM/YYYY');
    }

    function today() {
        var date = new Date();
        return moment(date).format('DD/MM/YYYY');
    }


</script>
<g:include view="controls/reports/pricelist-filter.gsp"/>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("reports-menu");
</script>
</body>
</html>