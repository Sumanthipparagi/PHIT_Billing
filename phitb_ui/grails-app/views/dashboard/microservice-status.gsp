<%--
  Created by IntelliJ IDEA.
  User: Capulus
  Date: 30-06-2022
  Time: 05:51 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="phitb">
    <title>:: PharmIT Billing :: Microservice Status</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}">
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/morrisjs/morris.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jvectormap/jquery-jvectormap-2.0.3.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/color_skins.css"/></head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="Alpino"></div>

        <p>Please wait...</p>
    </div>
</div>

<g:include view="controls/sidebar.gsp"/>
<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Microservices  Status</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0);">Tables</a></li>
                        <li class="breadcrumb-item active">Microservices  Status</li>
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
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
%{--                    <div class="body">--}%
%{--                        <button type="button" class="btn btn-round btn-simple btn-sm btn-default btn-filter" data-target="all">Todos</button>--}%
%{--                        <button type="button" class="btn btn-round btn-simple btn-sm btn-success btn-filter" data-target="approved">Approved</button>--}%
%{--                        <button type="button" class="btn btn-round btn-simple btn-sm btn-warning btn-filter" data-target="suspended">Suspended</button>--}%
%{--                        <button type="button" class="btn btn-round btn-simple btn-sm btn-info btn-filter" data-target="pending">Pending</button>--}%
%{--                        <button type="button" class="btn btn-round btn-simple btn-sm btn-danger btn-filter" data-target="blocked">Blocked</button>--}%
%{--                    </div>--}%
                    <div class="header">
                        <h2><strong>Microservices</strong></h2>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered">
                                <thead style="border-top: 1px solid #dee2e6;">
                                <tr>
                                    <th>Sl.No</th>
                                    <th>Microservice</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-status="approved">
                                    <td>1</td>
                                    <td><b>SYSTEM</b></td>
                                    <td><span class="badge badge-success">ONLINE</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
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