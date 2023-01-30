<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Update User</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
    <asset:stylesheet src="/themeassets/plugins/dropify/dist/css/dropify.min.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.min.css"/>

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

                                    <g:if test="${session.getAttribute('userId').toString() == params.id.toString() || session.getAttribute('role') == Constants.ENTITY_ADMIN || session.getAttribute('role') == Constants.SUPER_USER}">
                                    <div class="body m-b-10">
                                        <form action="/user-register/update/${user.id}" id="updateUser"
                                              method="POST" role="form"
                                              class="userRegisterForm" enctype="multipart/form-data">
                                            <div class="row clearfix">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="card">
                                                        <div class="header">
                                                            <h6>User Information</h6>
                                                        </div>
                                                        <div class="body">
                                                            <div class="row">
                                                                <div class="col-md-12 mt-2">
                                                                    <div class="row">
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="name">
                                                                                Name
                                                                            </label>
                                                                            <input type="text" id="name" class="form-control name" name="name"
                                                                                   placeholder="Name" value="${user.name}"
                                                                                   required/>
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="userName">
                                                                                User Name
                                                                            </label>
                                                                            <input type="text" id="userName" class="form-control userName" name="userName"
                                                                                   placeholder="User Name" value="${user.userName}"
%{--                                                                                   onblur="onblurUsername(this)"--}%
                                                                                   required readonly/>
                                                                        </div>

%{--                                                                        <div class="col-lg-6 form-group  form-float">--}%
%{--                                                                            <label for="password">--}%
%{--                                                                                Password--}%
%{--                                                                            </label>--}%
%{--                                                                            <input type="password" id="password" class="form-control password" name="password"--}%

%{--                                                                                   required/>--}%
%{--                                                                        </div>--}%


                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="aadharId">
                                                                                Aadhar  Number
                                                                            </label>
                                                                            <input type="text" id="aadharId"
                                                                                   class="form-control aadharId" name="aadharId" value="${user.aadharId}"
                                                                                   placeholder="Aadhar Number"
                                                                            />
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

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="reportTo">
                                                                                Report To
                                                                            </label>
                                                                            <select class="form-control show-tick reportTo" name="reportTo" id="reportTo">
                                                                                <option value="0">--Please Select--</option>
                                                                                <g:each var="u" in="${userregister}">
                                                                                    <option value="${u.id}" <g:if test="${u.id == user.reportTo}">selected</g:if>>${u.userName}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="referredBy">
                                                                                Referred By
                                                                            </label>
                                                                            <select class="form-control show-tick referredBy" name="referredBy" id="referredBy">
                                                                                <option value="0">--Please select--</option>
                                                                                <g:each var="u" in="${userregister}">
                                                                                    <option value="${u.id}"  <g:if test="${u.id == user.referredBy}">selected</g:if>>${u.userName}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="referenceRelation">
                                                                                Reference Relation
                                                                            </label>
                                                                            <input type="text" id="referenceRelation"
                                                                                   class="form-control referenceRelation" value="${user.referenceRelation}"
                                                                                   name="referenceRelation" placeholder="Reference Relation"
                                                                            />
                                                                        </div>



                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row clearfix">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="card">
                                                        <div class="header">
                                                            <h6>Contact Information</h6>
                                                        </div>
                                                        <div class="body">
                                                            <div class="row">



                                                                <div class="col-md-12 mt-2">
                                                                    <div class="row">
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="address">
                                                                                Address
                                                                            </label>
                                                                            <input type="text" id="address"
                                                                                   class="form-control address" value="${user.address}"
                                                                                   name="address" placeholder="Address"
                                                                            />
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="mobileNumber">
                                                                                Mobile Number
                                                                            </label>
                                                                            <input type="text" id="mobileNumber" class="form-control mobileNumber"
                                                                                   name="mobileNumber" value="${user.mobileNumber}"
                                                                                   placeholder="Mobile Number"
                                                                                   required/>
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="email">
                                                                                Email
                                                                            </label>
                                                                            <input type="email" id="email" class="form-control email"
                                                                                   name="email" placeholder="Email"
                                                                                   value="${user.email}"
                                                                            />
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="contactNumber">
                                                                                Contact Number
                                                                            </label>
                                                                            <input type="text" id="contactNumber" class="form-control contactNumber"
                                                                                   name="contactNumber" value="${user.contactNumber}"
                                                                                   placeholder="Contact Number"
                                                                            />
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="pinCode">
                                                                                PIN Code
                                                                            </label>
                                                                            <div>
                                                                                <select class="pinCode form-control" id="pinCode" ></select>
                                                                                <input type="hidden" name="pinCode"
                                                                                       value="${user.pincode}">
                                                                            </div>

                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="nationality">
                                                                                Nationality
                                                                            </label>
                                                                            <input type="text" id="nationality"
                                                                                   class="form-control nationality" value="${user.nationality}"
                                                                                   name="nationality" placeholder="Nationality"
                                                                            />
                                                                        </div>
                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="countryId">
                                                                                Country
                                                                            </label>
                                                                            <select class="form-control show-tick countryId" name="countryId"
                                                                                    id="countryId" disabled>
                                                                                <g:each var="country" in="${countrylist}">
                                                                                    <option value="${country.id}" <g:if test="${country.id == user.countryId}">selected</g:if>>${country.name}</option>
                                                                                </g:each>
                                                                            </select>
                                                                            <input type="hidden" name="countryId"
                                                                                   value="${user.countryId}"/>

                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="stateId">
                                                                                State
                                                                            </label>
                                                                            <select class="form-control show-tick stateId" name="stateId" id="stateId" disabled>
                                                                                <g:each var="state" in="${statelist}">
                                                                                    <option value="${state.id}" <g:if test="${state.id == user.stateId}">selected</g:if>>${state.name}</option>
                                                                                </g:each>
                                                                            </select>
                                                                            <input type="hidden" name="stateId"  value="${user.stateId}"/>

                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="cityId">
                                                                                Area /City
                                                                            </label>
                                                                            <select class="form-control show-tick cityId" name="cityId" id="cityId" disabled>
                                                                                <g:each var="city" in="${citylist}">
                                                                                    <option value="${city.id}" >${city.name}</option>
                                                                                </g:each>
                                                                            </select>

                                                                            <input type="hidden" name="cityId" value="${user.cityId}"/>

                                                                            <sub id="prevPin">Previously selected
                                                                            pincode: <b>${user?.pincode}</b><br></sub>
                                                                            <sub id="prevArea">Previously selected
                                                                            area: <b>${city?.areaName}</b></sub>
                                                                        </div>




                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row clearfix">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="card">
                                                        <div class="header">
                                                            <h6>Other information</h6>
                                                        </div>
                                                        <div class="body">
                                                            <div class="row">
                                                                <div class="col-md-12 mt-2">
                                                                    <div class="row">

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="joiningDate">
                                                                                Joining Date
                                                                            </label>
                                                                            <input type="text" id="joiningDate" class="form-control joiningDate"
                                                                                   name="joiningDate" placeholder="Joining Date"
                                                                            />
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


                                                                        %{--                                           <div class="col-lg-6 form-group  form-float">--}%
                                                                        %{--                                               <label for="permissions">--}%
                                                                        %{--                                                   Permissions--}%
                                                                        %{--                                               </label>--}%
                                                                        %{--                                               <input type="text" id="permissions" class="form-control permissions"--}%
                                                                        %{--                                                      name="permissions" placeholder="Permissions"--}%
                                                                        %{--                                                      required/>--}%
                                                                        %{--                                           </div>--}%

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="dob">
                                                                                Date of Birth
                                                                            </label>
                                                                            <input type="text" id="dob" class="form-control dob"
                                                                                   name="dob" placeholder="Date of Birth"
                                                                            />
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="anniversaryDate">
                                                                                Anniversary Date
                                                                            </label>
                                                                            <input type="text" id="anniversaryDate" class="form-control anniversaryDate"
                                                                                   name="anniversaryDate" placeholder="Anniversary Date"
                                                                            />
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="approvedSalary">
                                                                                Approved Salary
                                                                            </label>
                                                                            <input type="number" id="approvedSalary" class="form-control approvedSalary"
                                                                                   name="approvedSalary" onblur="setTwoNumberDecimal()" step="0.25"
                                                                                   value="${user.approvedSalary}" placeholder="Approved Salary"
                                                                            />
                                                                        </div>


                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="designationSalary">
                                                                                Designation Salary
                                                                            </label>
                                                                            <input type="number" id="designationSalary" class="form-control designationSalary"
                                                                                   name="designationSalary" onblur="setTwoNumberDecimal()" step="0.25"
                                                                                   value="${user.designationSalary}" placeholder="Designation Salary"
                                                                            />
                                                                        </div>


                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="lastPaidDate">
                                                                                Last Paid Date
                                                                            </label>
                                                                            <input type="text" id="lastPaidDate" class="form-control lastPaidDate"
                                                                                   name="lastPaidDate" placeholder="Last Paid Date"
                                                                            />
                                                                        </div>

                                                                        %{--                                           <div class="col-lg-6 form-group  form-float">--}%
                                                                        %{--                                               <label for="paymentModeId">--}%
                                                                        %{--                                                   Payment Id--}%
                                                                        %{--                                               </label>--}%
                                                                        %{--                                               <input type="text" id="paymentModeId" class="form-control paymentModeId"--}%
                                                                        %{--                                                      name="paymentModeId" placeholder="Payment Id"--}%
                                                                        %{--                                                      required/>--}%
                                                                        %{--                                           </div>--}%

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="bankAccount">
                                                                                IFSC Code
                                                                            </label>
                                                                            <input type="text" id="bankAccount"
                                                                                   class="form-control bankAccount" value="${user.bankAccount}"
                                                                                   name="bankAccount" placeholder="IFSC Code"
                                                                            />
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="bankId">
                                                                                Bank
                                                                            </label>
                                                                            <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                                                <option value="0">--Please Select--</option>
                                                                                <g:each var="b" in="${bank}">
                                                                                    <option value="${b.id}"  <g:if test="${b?.id == user.bankId}">selected</g:if>>${b.bankName}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="assignedHolidays">
                                                                                Assigned Holidays
                                                                            </label>
                                                                            <input type="text" id="assignedHolidays"
                                                                                   class="form-control assignedHolidays" value="${user.assignedHolidays}"
                                                                                   name="assignedHolidays" placeholder="Assigned Holidays"
                                                                            />
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="specialization">
                                                                                Specialization
                                                                            </label>
                                                                            <input type="text" id="specialization" class="form-control specialization"
                                                                                   name="specialization"
                                                                                   placeholder="Specialization" value="${user.specialization}"
                                                                            />
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="userStatus">
                                                                                Status
                                                                            </label>
                                                                            <select id="userStatus" class="form-control userStatus"
                                                                                    name="userStatus">
                                                                                <option value="ACTIVE"  <g:if test="${"ACTIVE" ==
                                                                                        user.userStatus}">selected</g:if>>
                                                                                    ACTIVE</option>
                                                                                <option value="INACTIVE" <g:if test="${"INACTIVE" ==
                                                                                        user.userStatus}">selected</g:if>>INACTIVE
                                                                                </option>
                                                                                <option value="RESIGNED" <g:if test="${"RESIGNED" ==
                                                                                        user.userStatus}">selected</g:if>>RESIGNED
                                                                                </option>
                                                                            </select>
                                                                        </div>


                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="licenceNumber">
                                                                                RTO Licence Number
                                                                            </label>
                                                                            <input type="text" id="licenceNumber" class="form-control licenceNumber"
                                                                                   name="licenceNumber"
                                                                                   placeholder="Licence Number" value="${user.licenceNumber}"
                                                                            />
                                                                        </div>

                                                                        %{--                                           <div class="col-lg-6 form-group  form-float">--}%
                                                                        %{--                                               <label for="account">--}%
                                                                        %{--                                                   Account--}%
                                                                        %{--                                               </label>--}%
                                                                        %{--                                               <select class="form-control show-tick account" name="account" id="account">--}%
                                                                        %{--                                                   <g:each var="a" in="${account}">--}%
                                                                        %{--                                                       <option value="${a.id}">${a.accountName}</option>--}%
                                                                        %{--                                                   </g:each>--}%
                                                                        %{--                                               </select>--}%
                                                                        %{--                                           </div>--}%

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="role">
                                                                                Role
                                                                            </label>
                                                                            <select class="form-control show-tick role" name="role" id="role">
                                                                                <g:each var="r" in="${roles}">
                                                                                    <option value="${r.id}" <g:if test="${r.id == user.role.id}">selected</g:if>>${r.name}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>


                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="divisionId">
                                                                                Division
                                                                            </label>
                                                                            <select class="form-control show-tick divisionId" name="divisionId" id="divisionId">
                                                                                <option value="0">---Please
                                                                                Select---</option>
                                                                                <g:each var="d" in="${division}">
                                                                                    <option value="${d.id}"  <g:if test="${d.id == user.division}">selected</g:if>>${d.divisionName}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>

                                                                        <div class="col-lg-6 form-group  form-float">
                                                                            <label for="entityRoute">
                                                                                Route
                                                                            </label>
                                                                            <select multiple="multiple" class="form-control show-tick entityRoute routeSelect" name="entityRoute" id="entityRoute">
                                                                                <g:each var="route" in="${routes}">
                                                                                    <option value="${route.id}" <g:if test="${user?.entityRoute?.id?.contains(route.id)}">selected</g:if>>${route.routeName}</option>
                                                                                </g:each>
                                                                            </select>
                                                                        </div>

                                                                        %{--                                           <div class="col-lg-6 form-group  form-float">--}%
                                                                        %{--                                               <label for="zoneId">--}%
                                                                        %{--                                                   Zone--}%
                                                                        %{--                                               </label>--}%
                                                                        %{--                                               <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">--}%
                                                                        %{--                                                   <g:each var="zone" in="${zoneList}">--}%
                                                                        %{--                                                       <option value="${zone.id}">${zone.name}</option>--}%
                                                                        %{--                                                   </g:each>--}%
                                                                        %{--                                               </select>--}%
                                                                        %{--                                           </div>--}%
                                                                    </div>
                                                                </div>
                                                                <input type="hidden" name="status" value="0">
                                                                <input type="hidden" name="zoneId" value="0">
                                                                <input type="hidden" name="entity" value="${session.getAttribute('entityId')}">
                                                                <input type="hidden" name="syncStatus" value="1">
                                                                <input type="hidden" name="lastLoginDate" value="12/02/2020">
                                                                <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                                                <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                                                <div class="col-lg-12">
                                                                    <div class="" style="float: right;">
                                                                        <input name="id" id="id" class="id" type="hidden">
                                                                        <input name="type" class="type" value="add" type="hidden">
                                                                        <button type="submit" class="btn btn-default btn-round waves-effect"
                                                                                name="submituser"><font style="vertical-align: inherit;"><font
                                                                                style="vertical-align: inherit;">SUBMIT</font></font></button>
                                                                        <button type="reset" class="btn btn-danger btn-simple btn-round waves-effect"
                                                                                data-dismiss="modal"><font style="vertical-align: inherit;"><font
                                                                                style="vertical-align: inherit;">RESET</font></font></button>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>

                                                    </div>

                                                </div>
                                            </div>



                                        </form>
                                    </div>
                                    </g:if>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div role="tabpanel" class="tab-pane" id="usersettings">
                        <div class="card">
                            <div class="header">
                                <h2><strong>Security</strong> Settings</h2>
                            </div>


<g:if test="${session.getAttribute('userId').toString() == params.id.toString()}">

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
</g:if>
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
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>
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
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>

<g:include view="controls/footer-content.gsp"/>

<script>
    $(function () {
        $(".routeSelect").select2();
        $(document).ready(function(){
            %{--$('.cityId').append("<option value='${city.id}'>${city.areaName}</option>");--}%
            // $('.cityId').append("<option value='"+115676+"'>"+jjdnjnsd+"</option>");

            %{--alert(${city.id});--}%
            %{--alert("<option value='${user.cityId}'>${city.areaName}</option>")--}%
            %{--$('#cityId').select2('${user.cityId}');--}%
        });
        if('${user.dob}'!=="") {
            var dob = new Date('${user.dob}');
            $('.dob').val(moment(dob).format('DD/MM/YYYY'));
        }
        else
        {
            $('.dob').val("");
        }

        //Datetimepicker plugin
        $('.dob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        if('${user.joiningDate}'!=="") {
            var joiningDate = new Date('${user.joiningDate}');
            $('.joiningDate').val(moment(joiningDate).format('DD/MM/YYYY'));
        }
        else
        {
            $('.joiningDate').val("");
        }

        $('.joiningDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        if('${user.lastPaidDate}'!=="")
        {
            var lastPaidDate = new Date('${user.lastPaidDate}');
            $('.lastPaidDate').val(moment(lastPaidDate).format('DD/MM/YYYY'));
        }else{
            $('.lastPaidDate').val("");
        }

        $('.lastPaidDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });


        if('${user.anniversaryDate}'!=="") {
            var anniversaryDate = new Date('${user.anniversaryDate}');
            $('.anniversaryDate').val(moment(anniversaryDate).format('DD/MM/YYYY'));
        }
        else {
            $('.anniversaryDate').val("")
        }

        $('.anniversaryDate').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        // var entityTypeId = $('.entity').find(':selected').attr('data-type')
        // $.ajax({
        //     type: "POST",
        //     url: "/getentitytypebyId?id="+ entityTypeId,
        //     dataType: 'json',
        //     success: function (data) {
        //         $('#entityTypeId').val(data.id)
        //     },
        // });



        function setTwoNumberDecimal(event) {
            this.value = parseFloat(this.value.toFixed(2));
        }

        $('#referredBy').select2();


        $('#cityId').select2({
            ajax: {
                url: '/city/get',
                dataType: 'json',
                //delay: 250,
                data: function (params) {
                    return {
                        search: params.term,
                        type: 'select2'
                    };
                },
                processResults: function (data, params) {
                    return {
                        results: data
                    };
                },
            },
            placeholder: 'Search for cities',
            minimumInputLength: 2
        });




        $('.pinCode').select2({
            placeholder: 'Enter Pincode',
            minimumInputLength: 3,
            required:true,
            ajax: {
                url: '/getcitybypincode',
                dataType: 'json',
                delay: 250,
                data: function (data) {
                    return {
                        pincode: data.term // search term
                    };
                },
                processResults: function (response) {
                    var data = [];
                    response.forEach(function(response, index) {
                        data.push({"pincode": response.pincode, "text": response.areaName, "id":response.id});
                    });
                    return {
                        results:data
                    };
                },
                cache: true
            }
        });



        $('.pinCode').on('select2:selecting', function(e) {
            var data =  e.params.args.data;
            var id = data.id;
            // alert(id)
            $.ajax({
                method: 'GET',
                url: '/getcitybyid',
                data: {'id' : id},
                success: function(response){
                    console.log(response);
                    $('.stateId').val(response.state.id).change();
                    $("input[name='stateId']").val(response.state.id);
                    $("input[name='cityId']").val(response.id);
                    $('.cityId').empty();
                    $('.cityId').append("<option value='"+response.id+"'>"+response.areaName+"</option>");
                    // $('.cityId').val(response.id).change();
                    $('.pinCode').val(response.pincode);
                    $("input[name='pinCode']").val(response.pincode);
                    if(response.state.alphaCode === "FC")
                    {
                        $('.countryId').find('option:contains("OTHER")').attr('selected', 'selected');
                        $("input[name='countryId']").val($('.countryId').val());
                    }
                    else {
                        $('.countryId').find('option:contains("INDIA")').attr('selected', 'selected');
                        $("input[name='countryId']").val($('.countryId').val());
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) { }
            });

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
                // Swal.fire('success','Password Changed Successfully',data);
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );
            },
            error:function(data){
                console.log("Failed");
                $("#validation-status").text(data.responseText);
                // Swal.fire('error','Password Change Failed',data.responseText);
                alert("Failed")

            }
        });
        event.preventDefault();
    });

    $('#updateUser').submit(function(event) {
        // var pincode =  $('.pinCode option').length;
        // if(pincode === 0 || pincode < 0)
        // {
        //     Swal.fire("Please enter  pincode and  select area");
        //     event.preventDefault();
        // }
        // else
        // {
            var formData = $(this);
            $.ajax({
                type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
                url         : formData.attr('action'), // the url where we want to POST
                data        : formData.serialize(), // our data object
                success:function(data){
                    // $("#validation-status").text(data);
                    // Swal.fire("Success!", "Updated Successfully! ", "success");
                    // alert("Password Updated!!")
                    Swal.fire(
                        'success',
                        'Updated Successfully',
                        'success'
                    );

                },
                error:function(data){
                    console.log("Failed");
                    // $("#validation-status").text(data.responseText);
                    Swal.fire('error','User update Failed',data.responseText);
                    // Swal.fire("Error", "Request failed!"+data.responseText, "error");
                    alert("error")


                }
            });
            event.preventDefault();
        // }

    });


    function setTwoNumberDecimal(event) {
        this.value = parseFloat(Number(this.value).toFixed(2));

    }
</script>

<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>