<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

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
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="Alpino"></div>
        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Add User Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/user-register">User Register</a></li>
                        <li class="breadcrumb-item active">Add User Register</li>
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
                        <form action="/user-register" id="form_validation" method="POST" role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-6 form-group  form-float">
                                    <label for="userName">
                                        User Name
                                    </label>
                                    <input type="text" id="userName" class="form-control userName" name="userName"
                                           placeholder="User Name"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="mobileNumber">
                                        Mobile Number
                                    </label>
                                    <input type="text" id="mobileNumber" class="form-control mobileNumber" name="mobileNumber"
                                           placeholder="Mobile Number"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="contactNumber">
                                        Contact Number
                                    </label>
                                    <input type="text" id="contactNumber" class="form-control contactNumber" name="contactNumber"
                                           placeholder="Contact Number"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="aadharId">
                                        Aadhar Id
                                    </label>
                                    <input type="text" id="aadharId" class="form-control aadharId" name="aadharId"
                                           placeholder="Aadhar Id"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="reportTo">
                                        Report To
                                    </label>
                                    <select class="form-control show-tick reportTo" name="reportTo" id="reportTo">
                                        <g:each var="u" in="${userregister}">
                                            <option value="${u.id}">${u.userName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="email">
                                        Email
                                    </label>
                                    <input type="email" id="email" class="form-control email"
                                           name="email" placeholder="Email"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="genderId">
                                        Gender
                                    </label>
                                    <select class="form-control show-tick genderId" name="genderId" id="genderId">
                                        <g:each var="g" in="${gender}">
                                            <option value="${g.id}">${g.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="photo">
                                        Photo
                                    </label>
%{--                                    <input type="file" id="input-file-now photo" class="dropify"--}%
%{--                                           data-default-file=""--}%
%{--                                           name="photo"  accept="image/png, image/gif, image/jpeg"/>--}%

                                    <input type="text" id="input-file-now photo" class="dropify"
                                           data-default-file=""
                                           name="photo"  accept="image/png, image/gif, image/jpeg"/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="genderId">
                                        Nationality
                                    </label>
                                    <input type="text" id="nationality" class="form-control nationality"
                                           name="nationality" placeholder="Nationality"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="genderId">
                                        Address
                                    </label>
                                    <input type="text" id="address" class="form-control address"
                                           name="address" placeholder="Address"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="countryId">
                                        Country
                                    </label>
                                    <select class="form-control show-tick countryId" name="countryId" id="countryId">
                                        <g:each var="country" in="${countrylist}">
                                            <option value="${country.id}">${country.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="stateId">
                                        State
                                    </label>
                                    <select class="form-control show-tick stateId" name="stateId" id="stateId">
                                        <g:each var="state" in="${statelist}">
                                            <option value="${state.id}">${state.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="cityId">
                                        City
                                    </label>
                                    <select class="form-control show-tick cityId" name="cityId" id="cityId">
                                        <g:each var="city" in="${citylist}">
                                            <option value="${city.id}">${city.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="referredBy">
                                        Referred By
                                    </label>
                                    <select class="form-control show-tick referredBy" name="referredBy" id="referredBy">
                                        <g:each var="u" in="${userregister}">
                                            <option value="${u.id}">${u.userName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="referenceRelation">
                                        Reference Relation
                                    </label>
                                    <input type="text" id="referenceRelation" class="form-control referenceRelation"
                                           name="referenceRelation" placeholder="Reference Relation"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="pincode">
                                        Pincode
                                    </label>
                                    <input type="text" id="pincode" class="form-control pincode"
                                           name="pincode" placeholder="pincode"
                                           required/>
                                </div>



                                <div class="col-lg-6 form-group  form-float">
                                    <label for="joiningDate">
                                        Joining Date
                                    </label>
                                    <input type="text" id="joiningDate" class="form-control joiningDate"
                                           name="joiningDate" placeholder="Joining Date"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="department">
                                       Department
                                    </label>
                                    <select class="form-control show-tick department" name="department" id="department">
                                        <g:each var="d" in="${department}">
                                            <option value="${d.id}">${d.name}</option>
                                        </g:each>
                                    </select>
                                </div>




                                <div class="col-lg-6 form-group  form-float">
                                    <label for="permissions">
                                        Permissions
                                    </label>
                                    <input type="text" id="permissions" class="form-control permissions"
                                           name="permissions" placeholder="Permissions"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="dob">
                                        Date of Birth
                                    </label>
                                    <input type="text" id="dob" class="form-control dob"
                                           name="dob" placeholder="Date of Birth"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="anniversaryDate">
                                        Anniversary Date
                                    </label>
                                    <input type="text" id="anniversaryDate" class="form-control anniversaryDate"
                                           name="anniversaryDate" placeholder="Anniversary Date"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="approvedSalary">
                                        Approved Salary
                                    </label>
                                    <input type="number" id="approvedSalary" class="form-control approvedSalary"
                                           name="approvedSalary" placeholder="Approved Salary"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="designationSalary">
                                        Designation Salary
                                    </label>
                                    <input type="number" id="designationSalary" class="form-control designationSalary"
                                           name="designationSalary" placeholder="Designation Salary"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="lastPaidDate">
                                        Last Paid Date
                                    </label>
                                    <input type="text" id="lastPaidDate" class="form-control lastPaidDate"
                                           name="lastPaidDate" placeholder="Last Paid Date"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="paymentModeId">
                                        Payment Id
                                    </label>
                                    <input type="text" id="paymentModeId" class="form-control paymentModeId"
                                           name="paymentModeId" placeholder="Payment Id"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="bankAccount">
                                        Bank Account
                                    </label>
                                    <input type="text" id="bankAccount" class="form-control bankAccount"
                                           name="bankAccount" placeholder="Bank Account"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="bankId">
                                        Bank
                                    </label>
                                    <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                        <g:each var="b" in="${bank}">
                                            <option value="${b.id}">${b.bankName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="assignedHolidays">
                                        Assigned Holidays
                                    </label>
                                    <input type="text" id="assignedHolidays" class="form-control assignedHolidays"
                                           name="assignedHolidays" placeholder="Assigned Holidays"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialization">
                                        Specialization
                                    </label>
                                    <input type="text" id="specialization" class="form-control specialization"
                                           name="specialization" placeholder="Specialization"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="licenceNumber">
                                        Licence Number
                                    </label>
                                    <input type="text" id="licenceNumber" class="form-control licenceNumber"
                                           name="licenceNumber" placeholder="Licence Number"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="account">
                                        Account
                                    </label>
                                    <select class="form-control show-tick account" name="account" id="account">
                                        <g:each var="a" in="${account}">
                                            <option value="${a.id}">${a.accountName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="role">
                                        Role
                                    </label>
                                    <select class="form-control show-tick role" name="role" id="role">
                                        <g:each var="r" in="${role}">
                                            <option value="${r.id}">${r.name}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="divisionId">
                                        Division
                                    </label>
                                    <select class="form-control show-tick divisionId" name="divisionId" id="divisionId">
                                        <g:each var="d" in="${division}">
                                            <option value="${d.id}">${d.divisionName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="entityType">
                                        Entity Type
                                    </label>
                                    <select class="form-control show-tick entityType" name="entityType" id="entityType">
                                        <g:each var="et" in="${entitytype}">
                                            <option value="${et.id}">${et.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="entity">
                                        Entity
                                    </label>
                                    <select class="form-control show-tick entity" name="entity" id="entity">
                                        <g:each var="e" in="${entity}">
                                            <option value="${e.id}">${e.entityName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="zoneId">
                                        Zone
                                    </label>
                                    <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">
                                        <g:each var="zone" in="${zoneList}">
                                            <option value="${zone.id}">${zone.name}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <input type="hidden" name="entityId" value="1">
                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="syncStatus" value="1">
                                <input type="hidden" name="lastLoginDate" value="12/02/2020">
                                <input type="hidden" name="createdUser" value="1">
                                <input type="hidden" name="modifiedUser" value="1">
                                <div class="col-lg-12">
                                    <div class="" style="float: right;">
                                        <input name="id" id="id" class="id" type="hidden">
                                        <input name="type" class="type" value="add" type="hidden">
                                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
                                        <button type="reset" class="btn btn-danger btn-simple btn-round waves-effect"
                                                data-dismiss="modal"><font style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">RESET</font></font></button>
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
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>

<script>

    $(function () {
        //Datetimepicker plugin
        $('.dob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        $('.joiningDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        $('.lastPaidDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

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

</script>


</body>
</html>