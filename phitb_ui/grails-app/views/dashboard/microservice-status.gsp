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
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/select-2-editor/select2.min.css" rel="stylesheet" />

</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="PharmIT"></div>

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
%{--                        <li class="breadcrumb-item"><a href="javascript:void(0);">Dashboard</a></li>--}%
                        <li class="breadcrumb-item active">Microservices  Status</li>
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
%{--                        <h2><strong>Microservices</strong></h2>--}%
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered">
                                <thead style="border-top: 1px solid #dee2e6;">
                                <tr>
%{--                                    <th>Sl.No</th>--}%
                                    <th>Microservice</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
%{--                                    <td>1</td>--}%
                                    <td><b>SYSTEM</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="system">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>2</td>--}%
                                    <td><b>SHIPMENTS</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="shipments">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>3</td>--}%
                                    <td><b>SALES</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="sales">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>4</td>--}%
                                    <td><b>PURCHASE</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="purchase">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>5</td>--}%
                                    <td><b>PRODUCT</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="product">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>6</td>--}%
                                    <td><b>INVENTORY</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="inventory">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>7</td>--}%
                                    <td><b>FACILITY</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="facility">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>8</td>--}%
                                    <td><b>ENTITY</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="entity">OFFLINE
                                    </button></td>
                                </tr>
                                <tr>
%{--                                    <td>9</td>--}%
                                    <td><b>ACCOUNTS</b></td>
                                    <td><button type="button"
                                                class="btn btn-round btn-simple btn-sm btn-danger btn-filter"
                                                id="accounts">OFFLINE
                                    </button></td>
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
<asset:javascript  src="/themeassets/plugins/select-2-editor/select2.js" />

<g:include view="controls/footer-content.gsp"/>
<script>
    $(function () {
        setInterval(function(){
            systemService();
            shipmentService();
            salesService();
            purchaseService();
            productService();
            inventoryService();
            facilityService();
            entityService();
            accountsService();
        },1000);
    });
    function systemService() {
        $.ajax({
            url: "/system-service-status",
            cache: false,
            success: function(data){
                console.log("System status- 200");
                $('#system').text("ONLINE");
                $('#system').addClass('btn-success');
                $('#system').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('System status - 400');
                $('#system').text("OFFLINE");
                $('#system').removeClass('btn-success');
                $('#system').addClass('btn-danger');
            }
        });
    }
    function shipmentService() {
        $.ajax({
            url: "/shipments-service-status",
            cache: false,
            success: function(data){
                console.log("shipments status- 200");
                $('#shipments').text("ONLINE");
                $('#shipments').addClass('btn-success');
                $('#shipments').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('shipments status - 400');
                $('#shipments').text("OFFLINE");
                $('#shipments').removeClass('btn-success');
                $('#shipments').addClass('btn-danger');
            }
        });
    }
    function salesService() {
        $.ajax({
            url: "/sales-service-status",
            cache: false,
            success: function(data){
                console.log("sales status- 200");
                $('#sales').text("ONLINE");
                $('#sales').addClass('btn-success');
                $('#sales').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('sales status - 400');
                $('#sales').text("OFFLINE");
                $('#sales').removeClass('btn-success');
                $('#sales').addClass('btn-danger');
            }
        });
    }
    function purchaseService() {
        $.ajax({
            url: "/purchase-service-status",
            cache: false,
            success: function(data){
                console.log("purchase status- 200");
                $('#purchase').text("ONLINE");
                $('#purchase').addClass('btn-success');
                $('#purchase').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('purchase status - 400');
                $('#purchase').text("OFFLINE");
                $('#purchase').removeClass('btn-success');
                $('#purchase').addClass('btn-danger');
            }
        });
    }
    function productService() {
        $.ajax({
            url: "/product-service-status",
            cache: false,
            success: function(data){
                console.log("product status- 200");
                $('#product').text("ONLINE");
                $('#product').addClass('btn-success');
                $('#product').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('product status - 400');
                $('#product').text("OFFLINE");
                $('#product').removeClass('btn-success');
                $('#product').addClass('btn-danger');
            }
        });
    }
    function inventoryService() {
        $.ajax({
            url: "/inventory-service-status",
            cache: false,
            success: function(data){
                console.log("inventory status- 200");
                $('#inventory').text("ONLINE");
                $('#inventory').addClass('btn-success');
                $('#inventory').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('inventory status - 400');
                $('#inventory').text("OFFLINE");
                $('#inventory').removeClass('btn-success');
                $('#inventory').addClass('btn-danger');
            }
        });
    }
    function facilityService() {
        $.ajax({
            url: "/facility-service-status",
            cache: false,
            success: function(data){
                console.log("facility status- 200");
                $('#facility').text("ONLINE");
                $('#facility').addClass('btn-success');
                $('#facility').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('facility status - 400');
                $('#facility').text("OFFLINE");
                $('#facility').removeClass('btn-success');
                $('#facility').addClass('btn-danger');
            }
        });
    }
    function entityService() {
        $.ajax({
            url: "/entity-service-status",
            cache: false,
            success: function(data){
                console.log("entity status- 200");
                $('#entity').text("ONLINE");
                $('#entity').addClass('btn-success');
                $('#entity').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('entity status - 400');
                $('#entity').text("OFFLINE");
                $('#entity').removeClass('btn-success');
                $('#entity').addClass('btn-danger');
            }
        });
    }
    function accountsService() {
        $.ajax({
            url: "/accounts-service-status",
            cache: false,
            success: function(data){
                console.log("Accounts status- 200");
                $('#accounts').text("ONLINE");
                $('#accounts').addClass('btn-success');
                $('#accounts').removeClass('btn-danger');
            },
            error: function (data) {
                console.log('Accounts status - 400');
                $('#accounts').text("OFFLINE");
                $('#accounts').removeClass('btn-success');
                $('#accounts').addClass('btn-danger');
            }
        });
    }

</script>
<script>
    selectSideMenu("dashboard-menu");
</script>