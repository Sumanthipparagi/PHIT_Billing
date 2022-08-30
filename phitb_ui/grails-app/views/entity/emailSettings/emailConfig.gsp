<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Email Config</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main-2.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css" rel="stylesheet"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>

    <style>
    table {
        overflow-y: scroll;
        display: block;
    }

    th {
        color: white;
        background: #22252b;
    }

    .hidden {
        display: none;
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
                    <h2>Email Config</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Email Config</li>
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
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
%{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                <div class="card">
                    <div class="header">
                        <h2>Email Config</h2>
                    </div>

                    <div class="body">
                        <form action="#" method="post" id="updateEntitySettings">
                                <div class="row clearfix">
                                    <div class="col-lg-12 col-md-6 col-sm-12 m-b-20">
                                        <h6>Sales</h6>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale Order is 'Saved'?</b>
                                        <select class="form-control show-tick" name="igm">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale order is clicked 'Convert'?</b>
                                        <select class="form-control show-tick" name="dsi">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in Sales Entry?</b>
                                        <select class="form-control show-tick" name="ipg">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Sales?</b>
                                        <select class="form-control show-tick" name="ips">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Sales?</b>
                                        <select class="form-control show-tick" name="acm">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in GTN?</b>
                                        <select class="form-control show-tick" name="ulub">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in GTN?</b>
                                        <select class="form-control show-tick" name="accmu">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale Order is 'Saved'?
                                        </b>
                                        <select class="form-control show-tick" name="clm">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale order is clicked 'Convert'?
                                        </b>
                                        <select class="form-control show-tick" name="asb">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving in Sales Entry?
                                        </b>
                                        <select class="form-control show-tick" name="ro">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick" name="essr">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Sales Right Window?
                                        </b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-12 col-md-6 col-sm-12 mt-3 mb-3">
                                        <h6>Receipt</h6>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?

                                        </b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?


                                        </b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Receipt Page?
                                        </b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in CrJV/CreditNote/SalesReturn?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in CrJV/CreditNote/SalesReturn?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-12 col-md-6 col-sm-12 m-b-20">
                                        <h6>Purchase</h6>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Purchase?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>


                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Settlements?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Reports Mail Button in All Reports?
                                        </b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                            <button type="submit" class="btn btn-default btn-round waves-effect"
                                    style="background-color: green;"><font
                                    style="vertical-align: inherit;"><font
                                        style="vertical-align: inherit;">SUBMIT</font></font></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

%{--<g:include view="controls/entity/add-entity-settings.gsp"/>--}%
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/bundles/mainscripts-2.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("settings-menu");
</script>
</body>
</html>


