<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Update password</title>
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
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
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
<section class="content profile-page">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Profile</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Profile</li>
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
            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="body bg-dark profile-header">
                        <div class="row">
                            <div class="col-lg-10 col-md-12">
                                <img src="${assetPath(src: '/themeassets/images/profile_av.jpg')}" class="user_pic rounded img-raised" alt="User">
                                <div class="detail">
                                    <div class="u_name">
                                        <h4><strong>${user.userName}</strong></h4>
                                        <span>${user.email}</span>
                                    </div>
                                    <div id="m_area_chart"></div>
                                </div>
                            </div>
%{--                            <div class="col-lg-2 col-md-12 user_earnings">--}%
%{--                                <h6>Total Earnings</h6>--}%
%{--                                <h4>$<small class="number count-to" data-from="0" data-to="2124" data-speed="1500" data-fresh-interval="1000">2124</small></h4>--}%
%{--                                <input type="text" class="knob" value="39" data-width="80" data-height="80" data-thickness="0.1" data-bgcolor="#485058" data-fgColor="#f97c53">--}%
%{--                                <span>Average 39% <i class="zmdi zmdi-caret-up text-success"></i></span>--}%
%{--                            </div>--}%
                        </div>
                    </div>
                    <ul class="nav nav-tabs profile_tab">
                        <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#overview">Overview</a></li>
%{--                        <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#schedule">Schedule</a></li>--}%
                        <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#usersettings">Settings</a></li>
                    </ul>
                </div>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="overview">
                        <div class="row">
                            <div class="col-lg-12 col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h2><strong>Update</strong> User</h2>
                                        <ul class="header-dropdown">
                                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>
                                                <ul class="dropdown-menu dropdown-menu-right">
                                                    <li><a href="blog-post.html">New Post</a></li>
                                                    <li><a href="blog-details.html">Post Detail</a></li>
                                                    <li><a href="blog-dashboard.html">Dashboard</a></li>
                                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="body m-b-10">
                                        <form action="/user-register/update/${user.id}" id="updateUser" method="POST"
                                              role="form"
                                              class="entityRegisterForm"  enctype="multipart/form-data">
                                            <div class="row clearfix">
                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="userName">
                                                        User Name
                                                    </label>
                                                    <input type="text" id="userName" class="form-control userName" name="userName"
                                                           placeholder="User Name" value="${user.userName}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="mobileNumber">
                                                        Mobile Number
                                                    </label>
                                                    <input type="text" id="mobileNumber" class="form-control mobileNumber" name="mobileNumber"
                                                           placeholder="Mobile Number" value="${user.mobileNumber}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="contactNumber">
                                                        Contact Number
                                                    </label>
                                                    <input type="text" id="contactNumber" class="form-control contactNumber" name="contactNumber"
                                                           placeholder="Contact Number"  value="${user.contactNumber}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="aadharId">
                                                        Aadhar Id
                                                    </label>
                                                    <input type="text" id="aadharId" class="form-control aadharId" name="aadharId"
                                                           placeholder="Aadhar Id"  value="${user.aadharId}"
                                                           required/>
                                                </div>


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="reportTo">
                                                        Report To
                                                    </label>
                                                    <select class="form-control show-tick reportTo" name="reportTo" id="reportTo">
                                                        <g:each var="u" in="${userList}">
                                                            <option value="${u.id}"
                                                                    <g:if test="${u.id == user.reportTo}">selected</g:if>>${u.userName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="email">
                                                        Email
                                                    </label>
                                                    <input type="email" id="email" class="form-control email"
                                                           name="email" placeholder="Email" value="${user.email}"
                                                           required/>
                                                </div>


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="genderId">
                                                        Gender
                                                    </label>
                                                    <select class="form-control show-tick genderId" name="genderId" id="genderId">
                                                        <g:each var="g" in="${genderList}">
                                                            <option value="${g.id}" <g:if
                                                                    test="${g.id == user.genderId}">selected</g:if>>${g.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                %{--                                <div class="col-lg-6 form-group  form-float">--}%
                                                %{--                                    <label for="photo">--}%
                                                %{--                                        Photo--}%
                                                %{--                                    </label>--}%
                                                %{--                                    --}%%{--                                    <input type="file" id="input-file-now photo" class="dropify"--}%
                                                %{--                                    --}%%{--                                           data-default-file=""--}%
                                                %{--                                    --}%%{--                                           name="photo"  accept="image/png, image/gif, image/jpeg"/>--}%
                                                %{--                                    <input type="file" id="input-file-now photo" class="dropify"--}%
                                                %{--                                           data-default-file="/api/media/user/${user.photo}"--}%
                                                %{--                                           name="photo"  accept="image/png, image/gif, image/jpeg"/>--}%
                                                %{--                                </div>--}%
                                                <input type="hidden" id="photo" class="form-control photo"
                                                       name="nationality" placeholder="Nationality"  value="1" />


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="nationality">
                                                        Nationality
                                                    </label>
                                                    <input type="text" id="nationality" class="form-control nationality"
                                                           name="nationality" placeholder="Nationality"  value="${user.nationality}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="genderId">
                                                        Address
                                                    </label>
                                                    <input type="text" id="address" class="form-control address"
                                                           name="address" placeholder="Address" value="${user.address}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="countryId">
                                                        Country
                                                    </label>
                                                    <select class="form-control show-tick countryId" name="countryId" id="countryId">
                                                        <g:each var="country" in="${countrylist}">
                                                            <option value="${country.id}"  <g:if test="${country.id == user.countryId}">selected</g:if>>${country.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="stateId">
                                                        State
                                                    </label>
                                                    <select class="form-control show-tick stateId" name="stateId" id="stateId">
                                                        <g:each var="state" in="${statelist}">
                                                            <option value="${state.id}"  <g:if test="${state.id == user.stateId}">selected</g:if>   >${state.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="cityId">
                                                        City
                                                    </label>
                                                    <select class="form-control show-tick cityId" name="cityId" id="cityId">
                                                        <g:each var="city" in="${citylist}">
                                                            <option value="${city.id}" <g:if test="${city.id == user.cityId}">selected</g:if> >${city.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="referredBy">
                                                        Referred By
                                                    </label>
                                                    <select class="form-control show-tick referredBy" name="referredBy" id="referredBy">
                                                        <g:each var="u" in="${userList}">
                                                            <option value="${u.id}" <g:if test="${u.id == user.referredBy}">selected</g:if> >${u.userName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="referenceRelation">
                                                        Reference Relation
                                                    </label>
                                                    <input type="text" id="referenceRelation" class="form-control referenceRelation"
                                                           name="referenceRelation" placeholder="Reference Relation" value="${user.referenceRelation}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="pincode">
                                                        Pincode
                                                    </label>
                                                    <input type="text" id="pincode" class="form-control pincode"
                                                           name="pincode" placeholder="pincode"  value="${user.pincode}"
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
                                                                    test="${d.id == user.department}">selected</g:if>>${d.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>




                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="permissions">
                                                        Permissions
                                                    </label>
                                                    <input type="text" id="permissions" class="form-control permissions"
                                                           name="permissions" placeholder="Permissions" value="${user.permissions}"
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
                                                           name="approvedSalary" placeholder="Approved Salary" value="${user.approvedSalary}"
                                                           required/>
                                                </div>


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="designationSalary">
                                                        Designation Salary
                                                    </label>
                                                    <input type="number" id="designationSalary" class="form-control designationSalary"
                                                           name="designationSalary" placeholder="Designation Salary" value="${user.designationSalary}"
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
                                                           name="paymentModeId" placeholder="Payment Id" value="${user.paymentModeId}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="bankAccount">
                                                        Bank Account
                                                    </label>
                                                    <input type="text" id="bankAccount" class="form-control bankAccount"
                                                           name="bankAccount" placeholder="Bank Account" value="${user.bankAccount}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="bankId">
                                                        Bank
                                                    </label>
                                                    <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                        <g:each var="b" in="${bank}">
                                                            <option value="${b.id}"
                                                                    <g:if test="${b.id == user.bankId}">selected</g:if>
                                                            >${b.bankName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="assignedHolidays">
                                                        Assigned Holidays
                                                    </label>
                                                    <input type="text" id="assignedHolidays" class="form-control assignedHolidays"
                                                           name="assignedHolidays" placeholder="Assigned Holidays" value="${user.assignedHolidays}" required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialization">
                                                        Specialization
                                                    </label>
                                                    <input type="text" id="specialization" class="form-control specialization"
                                                           name="specialization" placeholder="Specialization" value="${user.specialization}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="licenceNumber">
                                                        Licence Number
                                                    </label>
                                                    <input type="text" id="licenceNumber" class="form-control licenceNumber"
                                                           name="licenceNumber" placeholder="Licence Number" value="${user.licenceNumber}"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="account">
                                                        Account
                                                    </label>
                                                    <select class="form-control show-tick account" name="account" id="account">
                                                        <g:each var="a" in="${account}">
                                                            <option value="${a.id}"
                                                                    <g:if test="${a.id == user.account.id}">selected</g:if>
                                                            >${a.accountName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="role">
                                                        Role
                                                    </label>
                                                    <select class="form-control show-tick role" name="role" id="role">
                                                        <g:each var="r" in="${roles}">
                                                            <option value="${r.id}"
                                                                    <g:if test="${r.id == user.role.id}">selected</g:if>
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
                                                                    <g:if test="${d.id == user.division}">selected</g:if>
                                                            >${d.divisionName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                %{--                                <div class="col-lg-6 form-group  form-float">--}%
                                                %{--                                    <label for="entityType">--}%
                                                %{--                                        Entity Type--}%
                                                %{--                                    </label>--}%
                                                %{--                                    <select class="form-control show-tick entityType" name="entityType" id="entityType">--}%
                                                %{--                                        <g:each var="et" in="${entitytype}">--}%
                                                %{--                                            <option value="${et.id}"  <g:if--}%
                                                %{--                                                    test="${et.id == user.entityType}">selected</g:if> >${et.name}</option>--}%
                                                %{--                                        </g:each>--}%
                                                %{--                                    </select>--}%
                                                %{--                                </div>--}%

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="entity">
                                                        Entity
                                                    </label>
                                                    <select class="form-control show-tick entity" name="entity" id="entity" required>
                                                        <g:each var="e" in="${entity}">
                                                            <option value="${e.id}" data-type="${e.entityType.id}" <g:if
                                                                    test="${e.id == user.entity}">selected</g:if> >${e.entityName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="zoneId">
                                                        Zone
                                                    </label>
                                                    <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">
                                                        <g:each var="zone" in="${zoneList}">
                                                            <option value="${zone.id}" <g:if test="${zone.id == user.zoneId}">selected</g:if> >${zone.name}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <input type="hidden" id="entityTypeId" class="entityType"
                                                       name="entityType" value="${session.getAttribute('entityTypeId')}">
                                                <input type="hidden" name="status" value="1">
                                                <input type="hidden" name="syncStatus" value="1">
                                                <input type="hidden" name="lastLoginDate" value="12/02/2020">
                                                <input type="hidden" name="createdUser" value="1">
                                                <input type="hidden" name="modifiedUser" value="1">
                                                <div class="col-lg-12">
                                                    <div class="" style="float: right;">
                                                        <input name="id" id="id" class="id" type="hidden">
                                                        <input name="type" class="type" value="add" type="hidden">
                                                        <button type="submit" class="btn btn-primary btn-round" name="submituser"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
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

                    <div role="tabpanel" class="tab-pane page-calendar" id="schedule">
                        <div class="row">
                            <div class="col-md-12 col-lg-4">
                                <div class="card">
                                    <div class="body m-b-20">
                                        <div class="event-name b-lightred row">
                                            <div class="col-3 text-center">
                                                <h4>09<span>Dec</span><span>2017</span></h4>
                                            </div>
                                            <div class="col-9">
                                                <h6>Repeating Event</h6>
                                                <p>It is a long established fact that a reader will be distracted</p>
                                                <address><i class="zmdi zmdi-pin"></i> 123 6th St. Melbourne, FL 32904</address>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="event-name b-greensea row">
                                            <div class="col-3 text-center">
                                                <h4>16<span>Dec</span><span>2017</span></h4>
                                            </div>
                                            <div class="col-9">
                                                <h6>Repeating Event</h6>
                                                <p>It is a long established fact that a reader will be distracted</p>
                                                <address><i class="zmdi zmdi-pin"></i> 123 6th St. Melbourne, FL 32904</address>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="body m-b-20 l-blue">
                                        <div class="event-name row">
                                            <div class="col-3 text-center">
                                                <h4>28<span>Dec</span><span>2017</span></h4>
                                            </div>
                                            <div class="col-9">
                                                <h6>Google</h6>
                                                <p>It is a long established fact that a reader will be distracted</p>
                                                <address><i class="zmdi zmdi-pin"></i> 123 6th St. Melbourne, FL 32904</address>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="body m-b-5 l-green">
                                        <div class="event-name row">
                                            <div class="col-3 text-center">
                                                <h4>13<span>Dec</span><span>2017</span></h4>
                                            </div>
                                            <div class="col-9">
                                                <h6>Birthday</h6>
                                                <p>It is a long established fact that a reader will be distracted</p>
                                                <address><i class="zmdi zmdi-pin"></i> 123 6th St. Melbourne, FL 32904</address>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="body l-green">
                                        <div class="event-name row">
                                            <div class="col-3 text-center">
                                                <h4>11<span>Dec</span><span>2017</span></h4>
                                            </div>
                                            <div class="col-9">
                                                <h6>Conference</h6>
                                                <p>It is a long established fact that a reader will be distracted</p>
                                                <address><i class="zmdi zmdi-pin"></i> 123 6th St. Melbourne, FL 32904</address>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-round btn-primary m-t-15" data-toggle="modal" data-target="#addevent">Add Events</button>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-8">
                                <div class="card">
                                    <div class="body">
                                        <div id="calendar"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div role="tabpanel" class="tab-pane" id="usersettings">
                        <div class="card">
                            <div class="header">
                                <h2><strong>Security</strong> Settings</h2>
                            </div>
                            <form action="/user/update-password" method="post" id="updatePassword">
                            <div class="body">
%{--                                <div class="form-group">--}%
%{--                                    <input type="text" class="form-control" placeholder="Username">--}%
%{--                                </div>--}%
                                <div class="form-group">
                                <input
                                       type="password"
                                       class="form-control new_password"
                                       id="new_password"
                                       name="new_password"
                                       placeholder="Enter New Password"
                                       required>
                                </div>
                                <div class="form-group">
                                    <input type="password"
                                           class="form-control repeat_password"
                                           id="repeat_password"
                                           name="repeat_password"
                                           placeholder="Repeat Password"
                                           onkeyup="validate();" required>
                                </div>
                                <br>

                                <p>
                                    <small class="validation-status"></small>
                                </p>
                                <input type="hidden" name="id" value="${user.id}">

                                <button class="btn btn-info btn-round passwordChangebtn">Save Changes</button>
                            </div>
                            </form>
                        </div>
%{--                        <div class="card">--}%
%{--                            <div class="header">--}%
%{--                                <h2><strong>Account</strong> Settings</h2>--}%
%{--                            </div>--}%
%{--                            <div class="body">--}%
%{--                                <div class="row clearfix">--}%
%{--                                    <div class="col-lg-6 col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <input type="text" class="form-control" placeholder="First Name">--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-lg-6 col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <input type="text" class="form-control" placeholder="Last Name">--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-lg-4 col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <input type="text" class="form-control" placeholder="City">--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-lg-4 col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <input type="text" class="form-control" placeholder="E-mail">--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-lg-4 col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <input type="text" class="form-control" placeholder="Country">--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-md-12">--}%
%{--                                        <div class="form-group">--}%
%{--                                            <textarea rows="4" class="form-control no-resize" placeholder="Address Line 1"></textarea>--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-md-12">--}%
%{--                                        <div class="checkbox">--}%
%{--                                            <input id="procheck1" type="checkbox">--}%
%{--                                            <label for="procheck1">Profile Visibility For Everyone</label>--}%
%{--                                        </div>--}%
%{--                                        <div class="checkbox">--}%
%{--                                            <input id="procheck2" type="checkbox">--}%
%{--                                            <label for="procheck2">New task notifications</label>--}%
%{--                                        </div>--}%
%{--                                        <div class="checkbox">--}%
%{--                                            <input id="procheck3" type="checkbox">--}%
%{--                                            <label for="procheck3">New friend request notifications</label>--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div class="col-md-12">--}%
%{--                                        <button class="btn btn-primary btn-round">Save Changes</button>--}%
%{--                                    </div>--}%
%{--                                </div>--}%
%{--                            </div>--}%
%{--                        </div>--}%
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
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<asset:javascript src="/themeassets/bundles/knob.bundle.js"/>
<asset:javascript src="/themeassets/bundles/morrisscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/fullcalendarscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<g:include view="controls/footer-content.gsp"/>

<script>
    $(function () {
        var dob = new Date('${user.dob}');
        $('.dob').val(moment(dob).format('DD/MM/YYYY'));

        //Datetimepicker plugin
        $('.dob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var joiningDate = new Date('${user.joiningDate}');
        $('.joiningDate').val(moment(joiningDate).format('DD/MM/YYYY'));


        $('.joiningDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var lastPaidDate = new Date('${user.lastPaidDate}');
        $('.lastPaidDate').val(moment(lastPaidDate).format('DD/MM/YYYY'));

        $('.lastPaidDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var anniversaryDate = new Date('${user.anniversaryDate}');
        $('.anniversaryDate').val(moment(anniversaryDate).format('DD/MM/YYYY'));

        $('.anniversaryDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        var entityTypeId = $('.entity').find(':selected').attr('data-type')
        $.ajax({
            type: "POST",
            url: "/getentitytypebyId?id="+ entityTypeId,
            dataType: 'json',
            success: function (data) {
                $('#entityTypeId').val(data.id)
            },
        });
    });


    function validate() {
        var password1 = $(".new_password").val();
        var password2 = $(".repeat_password").val();
        if (password1 !== password2) {
            $(".validation-status").text("Passwords do not match!");
            $(".passwordChangebtn").attr("disabled", "disabled");

        }
        else {
            $(".validation-status").text("Passwords matched!");
            $(".passwordChangebtn").removeAttr('disabled');
        }
    }

    $('#updatePassword').submit(function(event) {
        var formData = $(this);
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : formData.attr('action'), // the url where we want to POST
            data        : formData.serialize(), // our data object
            success:function(data){
                $("#validation-status").text(data);
                swal('success','Password Changed Successfully',data);
            },
            error:function(data){
                console.log("Failed");
                $("#validation-status").text(data.responseText);
                swal('error','Password Change Failed',data.responseText);
            }
        });
        event.preventDefault();
    });

    $('#updateUser').submit(function(event) {
        var formData = $(this);
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : formData.attr('action'), // the url where we want to POST
            data        : formData.serialize(), // our data object
            success:function(data){
                // $("#validation-status").text(data);
               alert('success','User updated Successfully',data);
            },
            error:function(data){
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                alert('error','User update Failed',data.responseText);
            }
        });
        event.preventDefault();
    });

</script>

<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>