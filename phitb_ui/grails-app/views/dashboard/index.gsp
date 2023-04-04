<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="phitb">
    <title>:: PharmIT Billing :: Dashboard</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}">
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/morrisjs/morris.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jvectormap/jquery-jvectormap-2.0.3.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/color_skins.css"/>

</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="100" height="100"
                                 alt="phramIT"></div>

        <p>Please wait...</p>
    </div>
</div>

<g:include view="controls/sidebar.gsp"/>


<!-- Main Content -->
<section class="content home">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Dashboard</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
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
            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-shopping-basket zmdi-hc-3x col-amber"></i></p>
                        <span>My Sales</span>

                        <h3 class="m-b-10">₹<span class="number" id="salesCurrentMonth">0</span></h3>
                        <small class="text-muted"><span id="salesPreviousMonth">-</span></small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-assignment zmdi-hc-3x col-blue"></i></p>
                        <span>Total Outstanding</span>

                        <h3 class="m-b-10 number">₹<span class="number" id="totalOutstanding">0</span></h3>
                        <small class="text-muted">-</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-shopping-basket zmdi-hc-3x"></i></p>
                        <span>Total Sale Return</span>

                        <h3 class="m-b-10 number">₹<span class="number" id="salesReturnCurrentMonth">0</span></h3>
                        <small class="text-muted"><span id="salesReturnPreviousMonth">-</span></small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-book zmdi-hc-3x col-green"></i></p>
                        <span>Draft Invoices</span>

                        <h3 class="m-b-10 number"><span class="number" id="totalDraftInvoice">0</span></h3>
                        <small class="text-muted">-</small>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12">
                <div class="card visitors-map">
                    <div class="header">
                        <h2><strong>Sales</strong> Statistics</h2>
                        <ul class="header-dropdown">
                            <li class="dropdown"><a href="javascript:void(0);" class="dropdown-toggle"
                                                    data-toggle="dropdown" role="button" aria-haspopup="true"
                                                    aria-expanded="false"><i class="zmdi zmdi-more"></i></a>
                                %{--<ul class="dropdown-menu slideUp">
                                    <li><a href="javascript:void(0);">Action</a></li>
                                    <li><a href="javascript:void(0);">Another action</a></li>
                                    <li><a href="javascript:void(0);">Something else</a></li>
                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>
                                </ul>--}%
                            </li>
                        </ul>
                    </div>

                    <div class="body m-b-10">
                        <div id="salesChart" style="height: 300px;">
                            <p id="gettingData">Getting Data...</p>
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
<asset:javascript src="/themeassets/bundles/knob.bundle.js"/>
<asset:javascript src="/themeassets/bundles/jvectormap.bundle.js"/>
<asset:javascript src="/themeassets/bundles/morrisscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/sparkline.bundle.js"/>
%{--<asset:javascript src="/themeassets/bundles/doughnut.bundle.js"/>--}%
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
%{--<asset:javascript src="/themeassets/js/pages/index.js"/>--}%

<script>
    getStats();

    function getStats() {
        $.ajax({
            url: "dashboard/stats",
            method: "GET",
            success: function (data) {
                var salesCurrentMonth = data["salesCurrentMonth"];
                var salesReturnCurrentMonth = data["salesReturnCurrentMonth"];
                var salesPreviousMonth = data["salesPreviousMonth"];
                var salesReturnPreviousMonth = data["salesReturnPreviousMonth"];
                var totalDraftInvoice = data["totalDraftInvoice"];
                var totalOutstanding = data["totalOutstanding"];
                $("#salesCurrentMonth").text(salesCurrentMonth);
                $("#salesReturnCurrentMonth").text(salesReturnCurrentMonth);
                $("#totalDraftInvoice").text(totalDraftInvoice);
                $("#totalOutstanding").text(totalOutstanding);

                //First
                var percentageDiff = 0;
                if (salesPreviousMonth > 0) {
                    var diff = salesCurrentMonth - salesPreviousMonth;
                    if (diff !== 0)
                        percentageDiff = (diff / salesPreviousMonth) * 100;
                }
                if (percentageDiff > 0) {
                    $("#salesPreviousMonth").html(percentageDiff.toFixed(2) + "% Growth <i class='zmdi zmdi-caret-up' style='color: lightgreen;'></i>");
                } else if (percentageDiff === 0) {
                    $("#salesPreviousMonth").html("-");
                } else {
                    $("#salesPreviousMonth").html(percentageDiff.toFixed(2) + "% Growth <i class='zmdi zmdi-caret-down' style='color: red;'></i>");
                }

                //Third
                percentageDiff = 0;
                if (salesReturnPreviousMonth > 0) {
                    diff = salesReturnCurrentMonth - salesReturnPreviousMonth;
                    if (diff !== 0)
                        percentageDiff = (diff / salesReturnPreviousMonth) * 100;
                }
                if (percentageDiff > 0) {
                    $("#salesReturnPreviousMonth").html(percentageDiff.toFixed(2) + "% Growth <i class='zmdi zmdi-caret-up' style='color: lightgreen;'></i>");
                } else if (percentageDiff === 0) {
                    $("#salesReturnPreviousMonth").html("-");
                } else {
                    $("#salesReturnPreviousMonth").html(percentageDiff.toFixed(2) + "% Growth <i class='zmdi zmdi-caret-down' style='color: red;'></i>");
                }
            }
        });

        $.ajax({
            url: "dashboard/graph",
            method: "GET",
            success: function (data) {
                MorrisArea(data);
            }
        });
    }

    // Morris-chart
    function MorrisArea(result) {
        var dataArr = [];
        $.each(result, function (index, dt) {
            dataArr.push({'period': '' + dt.month, 'Sales': dt.totalSales})
        });

        console.log(JSON.stringify(dataArr));
        Morris.Area({
            element: 'salesChart',
            data: dataArr,
            xkey: 'period',
            ykeys: ['Sales'],
            labels: ['Sales'],
            xLabels: 'month',
            pointSize: 3,
            fillOpacity: 0,
            pointStrokeColors: ['#191f28'],
            behaveLikeLine: false,
            gridLineColor: ['#e0e0e0'],
            lineWidth: 2,
            hideHover: 'auto',
            lineColors: ['#191f28'],
            resize: true
        });

        $("#gettingData").addClass('hidden');
    }
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("dashboard-menu");
</script>
<script>
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: "/get-onoboard-info",
            dataType: 'json',
            success: function (data) {
                if (data.series.length === 0 && data.division.length === 0 && data.priority.length === 0) {
                    let timerInterval
                    Swal.fire({
                        title: 'Auto close alert!',
                        html: '<b>Redirecting...</b>',
                        timer: 2000,
                        timerProgressBar: true,
                        didOpen: () => {
                            Swal.showLoading();
                            const b = Swal.getHtmlContainer().querySelector('b');
                            timerInterval = setInterval(() => {
                                b.textContent = Swal.getTimerLeft();
                            }, 100)
                        },
                        willClose: () => {
                            clearInterval(timerInterval)
                        }
                    }).then((result) => {
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            Swal.fire('I was closed by the timer')
                        }
                    });

                    window.location.href = "/entity-onborad";
                }
                // console.log(data)
                // alert(data)
            },
            error: function (data) {
                // alert("Something went Wrong!")
            }
        });
        // alert("logged In");

        //show popup if year-end
        /*if (!${session.getAttribute("financialYearValid")}) {
            Swal.fire({
                title: 'Financial Year End',
                html: 'Do you want to start new financial year?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes',
                cancelButtonText: 'No'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: "Select some options",
                        html: "<div class=\"row\">" +
                            "<div class=\"col-md-6\">" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox1\">" +
                            "<label class=\"form-check-label\" for=\"checkbox1\">" +
                            "Checkbox 1" +
                            "</label>" +
                            "</div>" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox2\">" +
                            "<label class=\"form-check-label\" for=\"checkbox2\">" +
                            "Checkbox 2" +
                            "</label>" +
                            "</div>" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox3\">" +
                            "<label class=\"form-check-label\" for=\"checkbox3\">" +
                            "Checkbox 3" +
                            "</label>" +
                            "</div>" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox4\">" +
                            "<label class=\"form-check-label\" for=\"checkbox4\">" +
                            "Checkbox 4" +
                            "</label>" +
                            "</div>" +
                            "</div>" +
                            "<div class=\"col-md-6\">" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox5\">" +
                            "<label class=\"form-check-label\" for=\"checkbox5\">" +
                            "Checkbox 5" +
                            "</label>" +
                            "</div>" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox6\">" +
                            "<label class=\"form-check-label\" for=\"checkbox6\">" +
                            "Checkbox 6" +
                            "</label>" +
                            "</div>" +
                            "<div class=\"form-check\">" +
                            "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox7\">" +
                            "<label class=\"form-check-label\" for=\"checkbox7\">" +
                            "Checkbox 7" +
                            "</label>" +
                            "</div>" +
                        "<div class=\"form-check\">"+
                                "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"checkbox8\">" +
                            "<label class=\"form-check-label\" for=\"checkbox8\">" +
                            "Checkbox 8" +
                            "</label>" +
                            "</div>" +
                            "</div>" +
                            "</div>",
                        focusConfirm: false,
                        preConfirm: () => {
                            const options = [];
                            const checkboxes = Swal.getPopup().querySelectorAll('input[type="checkbox"]');
                            checkboxes.forEach((checkbox) => {
                                if (checkbox.checked) {
                                    options.push(checkbox.value);
                                }
                            });
                            if (options.length === 0) {
                                Swal.showValidationMessage("Please select at least one option");
                            }
                            return options;
                        }
                    }).then((result) => {
                        // Show a success message with the selected options
                        Swal.fire({
                            title: "You selected:",
                            html: result.value.join(", "),
                            icon: "success"
                        });
                    });
                }
            });
        }*/
    });
</script>
</body>
</html>