<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: User Register</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
    <asset:stylesheet src="/themeassets/plugins/dropify/dist/css/dropify.min.css"/>


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
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Update User Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/user-register">User Register</a></li>
                        <li class="breadcrumb-item active">Update User Register</li>
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


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Inline</strong> Layout</h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu dropdown-menu-right">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
                    <div class="body">
                        <form action="/user-register/update/${userregisterbyId.id}" id="form_validation" method="POST"
                              role="form"  class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <div class="card">
                                        <form id="wizard_with_validation" method="POST">
                                            <h3>Account Information</h3>
                                            <fieldset>
                                                <div class="form-group form-float">
                                                    <input type="password" class="form-control" placeholder="Password *" name="password" id="password" required>
                                                </div>
                                                <div class="form-group form-float">
                                                    <input type="password" class="form-control" placeholder="Confirm Password *" name="confirm" required>
                                                </div>
                                            </fieldset>
                                            <h3>Profile Information</h3>
                                            <fieldset>
                                                <div class="form-group form-float">
                                                    <input type="text" name="name" placeholder="First Name *" class="form-control" required>
                                                </div>
                                                <div class="form-group form-float">
                                                    <input type="text" name="surname" placeholder="Last Name *" class="form-control" required>
                                                </div>
                                                <div class="form-group form-float">
                                                    <input type="email" name="email" placeholder="Email *" class="form-control" required >
                                                </div>
                                                <div class="form-group form-float">
                                                    <textarea name="address" cols="30" rows="3" placeholder="Address *" class="form-control no-resize" required></textarea>
                                                </div>
                                                <div class="form-group form-float">
                                                    <input min="18" type="number" name="age" placeholder="Age *" class="form-control" required>
                                                    <div class="help-info">The warning step will show up if age is less than 18</div>
                                                </div>
                                            </fieldset>
                                            <h3>Terms & Conditions - Finish</h3>
                                            <fieldset>
                                                <input id="acceptTerms-2" name="acceptTerms" type="checkbox" required>
                                                <label for="acceptTerms-2">I agree with the Terms and Conditions.</label>
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>
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
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/js/pages/forms/form-wizard.js"/>
<asset:javascript src="/themeassets/plugins/jquery-steps/jquery.steps.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>

<script>

    $(function () {

        var dob = new Date('${userregisterbyId.dob}');
        $('.dob').val(moment(dob).format('DD/MM/YYYY'));

        //Datetimepicker plugin
        $('.dob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var joiningDate = new Date('${userregisterbyId.joiningDate}');
        $('.joiningDate').val(moment(joiningDate).format('DD/MM/YYYY'));


        $('.joiningDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var lastPaidDate = new Date('${userregisterbyId.lastPaidDate}');
        $('.lastPaidDate').val(moment(lastPaidDate).format('DD/MM/YYYY'));

        $('.lastPaidDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var anniversaryDate = new Date('${userregisterbyId.anniversaryDate}');
        $('.anniversaryDate').val(moment(anniversaryDate).format('DD/MM/YYYY'));

        $('.anniversaryDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
    });

    $(document).ready(function() {
        // Basic
        $('.dropify').dropify();

        // Translated
        $('.dropify-fr').dropify({
            messages: {
                default: 'Glissez-déposez un fichier ici ou cliquez',
                replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                remove: 'Supprimer',
                error: 'Désolé, le fichier trop volumineux'
            }
        });

        // Used events
        var drEvent = $('#input-file-events').dropify();

        drEvent.on('dropify.beforeClear', function(event, element) {
            return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
        });

        drEvent.on('dropify.afterClear', function(event, element) {
            alert('File deleted');
        });

        drEvent.on('dropify.errors', function(event, element) {
            console.log('Has Errors');
        });

        var drDestroy = $('#input-file-to-destroy').dropify();
        drDestroy = drDestroy.data('dropify')
        $('#toggleDropify').on('click', function(e) {
            e.preventDefault();
            if (drDestroy.isDropified()) {
                drDestroy.destroy();
            } else {
                drDestroy.init();
            }
        })
    });

    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityType").val(type);
    });
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>