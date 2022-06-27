<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">
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
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="Alpino"></div>

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
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
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
            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-balance zmdi-hc-3x col-amber"></i></p>
                        <span>Total Revenue</span>

                        <h3 class="m-b-10">₹<span class="number count-to" data-from="0" data-to="2078" data-speed="2000"
                                                  data-fresh-interval="700">2078</span></h3>
                        <small class="text-muted">27% lower growth</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-assignment zmdi-hc-3x col-blue"></i></p>
                        <span>Total Orders</span>

                        <h3 class="m-b-10 number count-to" data-from="0" data-to="865" data-speed="2000"
                            data-fresh-interval="700">865</h3>
                        <small class="text-muted">88% lower growth</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-shopping-basket zmdi-hc-3x"></i></p>
                        <span>Total Sales</span>

                        <h3 class="m-b-10 number count-to" data-from="0" data-to="3502" data-speed="2000"
                            data-fresh-interval="700">3502</h3>
                        <small class="text-muted">38% lower growth</small>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="card text-center">
                    <div class="body">
                        <p class="m-b-20"><i class="zmdi zmdi-account-box zmdi-hc-3x col-green"></i></p>
                        <span>New Employees</span>

                        <h3 class="m-b-10 number count-to" data-from="0" data-to="78" data-speed="2000"
                            data-fresh-interval="700">78</h3>
                        <small class="text-muted">78% lower growth</small>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12">
                <div class="card visitors-map">
                    <div class="header">
                        <h2><strong>Visit</strong> & Sales Statistics</h2>
                        <ul class="header-dropdown">
                            <li class="dropdown"><a href="javascript:void(0);" class="dropdown-toggle"
                                                    data-toggle="dropdown" role="button" aria-haspopup="true"
                                                    aria-expanded="false"><i class="zmdi zmdi-more"></i></a>
                                <ul class="dropdown-menu slideUp">
                                    <li><a href="javascript:void(0);">Action</a></li>
                                    <li><a href="javascript:void(0);">Another action</a></li>
                                    <li><a href="javascript:void(0);">Something else</a></li>
                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="body m-b-10">
                        <div id="m_area_chart"></div>
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
<asset:javascript src="/themeassets/js/pages/index.js"/>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("dashboard-menu");
</script>
</body>
</html>