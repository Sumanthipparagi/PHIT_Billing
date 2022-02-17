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
                    <h2>Update User Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/user-register">User Register</a></li>
                        <li class="breadcrumb-item active">Update User Register</li>
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
                        <form action="/user-register/update/${userregisterbyId.id}" id="form_validation" method="POST"
                              role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-6 form-group  form-float">
                                    <label for="userName">
                                        User Name
                                    </label>
                                    <input type="text" id="userName" class="form-control userName" name="userName"
                                           placeholder="User Name" value="${userregisterbyId.userName}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="mobileNumber">
                                        Mobile Number
                                    </label>
                                    <input type="text" id="mobileNumber" class="form-control mobileNumber" name="mobileNumber"
                                           placeholder="Mobile Number" value="${userregisterbyId.mobileNumber}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="contactNumber">
                                        Contact Number
                                    </label>
                                    <input type="text" id="contactNumber" class="form-control contactNumber" name="contactNumber"
                                           placeholder="Contact Number"  value="${userregisterbyId.contactNumber}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="aadharId">
                                        Aadhar Id
                                    </label>
                                    <input type="text" id="aadharId" class="form-control aadharId" name="aadharId"
                                           placeholder="Aadhar Id"  value="${userregisterbyId.aadharId}"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="reportTo">
                                        Report To
                                    </label>
                                    <select class="form-control show-tick reportTo" name="reportTo" id="reportTo">
                                        <g:each var="u" in="${userregister}">
                                            <option value="${u.id}"
                                                    <g:if test="${u.id == userregisterbyId.reportTo}">selected</g:if>>${u.userName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="email">
                                        Email
                                    </label>
                                    <input type="email" id="email" class="form-control email"
                                           name="email" placeholder="Email" value="${userregisterbyId.email}"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="genderId">
                                        Gender
                                    </label>
                                    <select class="form-control show-tick genderId" name="genderId" id="genderId">
                                        <g:each var="g" in="${gender}">
                                            <option value="${g.id}" <g:if
                                                    test="${g.id == userregisterbyId.genderId}">selected</g:if>>${g.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="photo">
                                        Photo
                                    </label>
                                    <input type="text" id="photo" class="form-control photo"
                                           name="photo" placeholder="Photo" value="${userregisterbyId.photo}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="nationality">
                                        Nationality
                                    </label>
                                    <input type="text" id="nationality" class="form-control nationality"
                                           name="nationality" placeholder="Nationality"  value="${userregisterbyId.nationality}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="genderId">
                                        Address
                                    </label>
                                    <input type="text" id="address" class="form-control address"
                                           name="address" placeholder="Address" value="${userregisterbyId.address}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="countryId">
                                        Country
                                    </label>
                                    <select class="form-control show-tick countryId" name="countryId" id="countryId">
                                        <g:each var="country" in="${countrylist}">
                                            <option value="${country.id}"  <g:if test="${country.id == userregisterbyId.countryId}">selected</g:if>>${country.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="stateId">
                                        State
                                    </label>
                                    <select class="form-control show-tick stateId" name="stateId" id="stateId">
                                        <g:each var="state" in="${statelist}">
                                            <option value="${state.id}"  <g:if test="${state.id == userregisterbyId.stateId}">selected</g:if>   >${state.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="cityId">
                                        City
                                    </label>
                                    <select class="form-control show-tick cityId" name="cityId" id="cityId">
                                        <g:each var="city" in="${citylist}">
                                            <option value="${city.id}" <g:if test="${city.id == userregisterbyId.cityId}">selected</g:if> >${city.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="referredBy">
                                        Referred By
                                    </label>
                                    <select class="form-control show-tick referredBy" name="referredBy" id="referredBy">
                                        <g:each var="u" in="${userregister}">
                                            <option value="${u.id}" <g:if test="${u.id == userregisterbyId.referredBy}">selected</g:if> >${u.userName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="referenceRelation">
                                        Reference Relation
                                    </label>
                                    <input type="text" id="referenceRelation" class="form-control referenceRelation"
                                           name="referenceRelation" placeholder="Reference Relation" value="${userregisterbyId.referenceRelation}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="pincode">
                                        Pincode
                                    </label>
                                    <input type="text" id="pincode" class="form-control pincode"
                                           name="pincode" placeholder="pincode"  value="${userregisterbyId.pincode}"
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
                                            <option value="${d.id}" <g:if
                                                    test="${d.id == userregisterbyId.department}">selected</g:if>>${d.name}</option>
                                        </g:each>
                                    </select>
                                </div>




                                <div class="col-lg-6 form-group  form-float">
                                    <label for="permissions">
                                        Permissions
                                    </label>
                                    <input type="text" id="permissions" class="form-control permissions"
                                           name="permissions" placeholder="Permissions" value="${userregisterbyId.permissions}"
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
                                           name="approvedSalary" placeholder="Approved Salary" value="${userregisterbyId.approvedSalary}"
                                           required/>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="designationSalary">
                                        Designation Salary
                                    </label>
                                    <input type="number" id="designationSalary" class="form-control designationSalary"
                                           name="designationSalary" placeholder="Designation Salary" value="${userregisterbyId.designationSalary}"
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
                                           name="paymentModeId" placeholder="Payment Id" value="${userregisterbyId.paymentModeId}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="bankAccount">
                                        Bank Account
                                    </label>
                                    <input type="text" id="bankAccount" class="form-control bankAccount"
                                           name="bankAccount" placeholder="Bank Account" value="${userregisterbyId.bankAccount}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="bankId">
                                        Bank
                                    </label>
                                    <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                        <g:each var="b" in="${bank}">
                                            <option value="${b.id}"
                                                    <g:if test="${b.id == userregisterbyId.bankId}">selected</g:if>
                                            >${b.bankName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="assignedHolidays">
                                        Assigned Holidays
                                    </label>
                                    <input type="text" id="assignedHolidays" class="form-control assignedHolidays"
                                           name="assignedHolidays" placeholder="Assigned Holidays" value="${userregisterbyId.assignedHolidays}" required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialization">
                                        Specialization
                                    </label>
                                    <input type="text" id="specialization" class="form-control specialization"
                                           name="specialization" placeholder="Specialization" value="${userregisterbyId.specialization}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="licenceNumber">
                                        Licence Number
                                    </label>
                                    <input type="text" id="licenceNumber" class="form-control licenceNumber"
                                           name="licenceNumber" placeholder="Licence Number" value="${userregisterbyId.licenceNumber}"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="account">
                                        Account
                                    </label>
                                    <select class="form-control show-tick account" name="account" id="account">
                                        <g:each var="a" in="${account}">
                                            <option value="${a.id}"
                                                    <g:if test="${a.id == userregisterbyId.account.id}">selected</g:if>
                                            >${a.accountName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="role">
                                        Role
                                    </label>
                                    <select class="form-control show-tick role" name="role" id="role">
                                        <g:each var="r" in="${role}">
                                            <option value="${r.id}"
                                                    <g:if test="${r.id == userregisterbyId.role.id}">selected</g:if>
                                            >${r.name}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="divisionId">
                                        Division
                                    </label>
                                    <select class="form-control show-tick divisionId" name="divisionId" id="divisionId">
                                        <g:each var="d" in="${division}">
                                            <option value="${d.id}"
                                                    <g:if test="${d.id == userregisterbyId.division}">selected</g:if>
                                            >${d.divisionName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="entityType">
                                        Entity Type
                                    </label>
                                    <select class="form-control show-tick entityType" name="entityType" id="entityType">
                                        <g:each var="et" in="${entitytype}">
                                            <option value="${et.id}"  <g:if
                                                    test="${et.id == userregisterbyId.entityType}">selected</g:if> >${et.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="entity">
                                        Entity
                                    </label>
                                    <select class="form-control show-tick entity" name="entity" id="entity">
                                        <g:each var="e" in="${entity}">
                                            <option value="${e.id}" <g:if
                                                    test="${e.id == userregisterbyId.entity}">selected</g:if> >${e.entityName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="zoneId">
                                        Zone
                                    </label>
                                    <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">
                                        <g:each var="zone" in="${zoneList}">
                                            <option value="${zone.id}" <g:if test="${zone.id == userregisterbyId.zoneId}">selected</g:if> >${zone.name}</option>
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
</script>

</body>
</html>