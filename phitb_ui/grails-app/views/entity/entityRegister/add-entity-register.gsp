<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Entity Register</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/dropify/dist/css/dropify.min.css"/>
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.min.css"/>
    <asset:stylesheet src="/themeassets/plugins/sweetalert2/dist/sweetalert2.min.css"/>
    %{--    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />--}%
    %{--    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>--}%

    <style>
    .error {
        color: red;
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
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <g:if test="${params.id == null || params.id == ""}">
                        <h2>Add  Entity Register</h2>
                    </g:if>
                    <g:else>
                        <h2>Update  Entity Register</h2>
                    </g:else>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/entity-register">Entity Register</a></li>
                        <g:if test="${params.id == null || params.id == ""}">
                            <li class="breadcrumb-item active">Add  Entity Register</li>
                        </g:if>
                        <g:else>
                            <li class="breadcrumb-item active">Update  Entity Register</li>
                        </g:else>
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

        <g:if test="${params.id == null || params.id == ""}">
            <!-- Inline Layout -->
            <form action="/entity-register" id="entityRegisterForm" method="POST" role="form"
                  class="entityRegisterForm" enctype="multipart/form-data">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="card">
                            <div class="header">
                                <h6>Entity Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-md-6 form-group  form-float">
                                                <label for="entityName">
                                                    Entity Name <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <input type="text" id="entityName" class="form-control entityName"
                                                       name="entityName" placeholder="Entity Name"
                                                       required/>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="entityType">
                                                    Entity Type <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick entityType" name="entityType"
                                                        id="entityType" required>
                                                    <g:each var="et" in="${entitytype}">
                                                        <option value="${et.id}">${et.name}</option>
                                                        %{--<g:if test="${session.getAttribute('role') == Constants.ENTITY_ADMIN}">
                                                            <g:if test="${et.name != "MANUFACTURER"}">
                                                                <option value="${et.id}">${et.name}</option>
                                                            </g:if>
                                                        </g:if>
                                                        <g:else>
                                                            <option value="${et.id}">${et.name}</option>
                                                        </g:else>--}%

                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-md-6 form-group">
                                                <label for="addressLine1">
                                                    Address Line 1 <span class="required-indicator"
                                                                         style="color: red;">*</span>
                                                </label>
                                                <input type="text" id="addressLine1" class="form-control addressLine1"
                                                       name="addressLine1" placeholder="Address Line 1"
                                                       required/>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="addressLine1">
                                                    Address Line 2
                                                </label>
                                                <input type="text" id="addressLine2" class="form-control addressLine2"
                                                       name="addressLine2" placeholder="Address Line 2"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="gstn">
                                                    GSTIN
                                                </label>
                                                <input maxlength="15" type="text" id="gstn" class="form-control gstn"
                                                       name="gstn" placeholder="GSTIN"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="pinCode">
                                                    PIN Code <span class="required-indicator"
                                                                   style="color: red;">*</span>
                                                </label>

                                                <div>
                                                    <select class="pinCode form-control" id="pinCode"></select>
                                                    <input type="hidden" name="pinCode" required>
                                                </div>

                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="cityId">
                                                    Area / City <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick cityId" name="cityId" id="cityId"
                                                        disabled>
                                                    <input type="hidden" name="cityId" required/>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="hqarea">
                                                    HQ Area
                                                </label>
                                                <select class="form-control show-tick hqarea" name="hqarea" id="hqarea">
                                                    <option value="0">Please Select</option>
                                                    <g:each var="hq" in="${hqareas}">
                                                        <option value="${hq.id}">${hq.hqName}</option>
                                                    </g:each>
                                                </select>
                                            </div>


                                            <div class="col-md-6 form-group  form-float">
                                                <label for="stateId">
                                                    State <span class="required-indicator" style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick stateId" name="stateId"
                                                        id="stateId" disabled>
                                                    <g:each var="state" in="${statelist}">
                                                        <option value="${state.id}">${state.name}</option>
                                                    </g:each>
                                                    <input type="hidden" name="stateId"/>
                                                </select>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="countryId">
                                                    Country <span class="required-indicator"
                                                                  style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick countryId" name="countryId"
                                                        id="countryId" disabled>
                                                    <g:each var="country" in="${countrylist}">
                                                        <option value="${country.id}">${country.name}</option>
                                                    </g:each>
                                                </select>
                                                <input type="hidden" name="countryId"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <g:if test="${(session.getAttribute('role') == Constants.SUPER_USER || session.getAttribute('role') == Constants.ENTITY_ADMIN) && parentEntities?.size() > 0}">
                    <div class="row clearfix">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <div class="card">
                                <div class="header">
                                    <h6>Affiliation Information</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-6 mt-2">
                                            <g:if test="${session.getAttribute('role') == Constants.SUPER_USER}">
                                                <div class="form-group">
                                                    <label for="isParent" class="checkbox-inline">
                                                        <input onchange="isParentChanged()" type="checkbox"
                                                               class="checkbox"
                                                               id="isParent"/> Is Parent Entity?
                                                    </label>
                                                </div>
                                            </g:if>
                                            <div class="form-group affiliatedEntityContainer">
                                                <label for="affiliatedToEntity">Affiliated to Entity: <span
                                                        class="required-indicator"
                                                        style="color: red;">*</span></label>
                                                <select name="affiliatedToEntity" style="width: 100%;"
                                                        id="affiliatedToEntity" class="form-control affiliatedToEntity"
                                                        required>
                                                    <option selected disabled>-SELECT-</option>
                                                </select>
                                            </div>
                                            <input type="hidden" id="isParentValue" name="isParent" value="false"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </g:if>


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
                                                <label for="contactName">
                                                    Contact Name
                                                </label>
                                                <input type="text" id="contactName" class="form-control contactName"
                                                       name="contactName" placeholder="Contact Name"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="phoneNumber">
                                                    Phone Number
                                                </label>
                                                <input type="number" id="phoneNumber" class="form-control phoneNumber"
                                                       name="phoneNumber" placeholder="Phone Number"/>

                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="mobileNumber">
                                                    Mobile Number
                                                </label>
                                                <input type="number" id="mobileNumber" class="form-control mobileNumber"
                                                       name="mobileNumber" placeholder="Mobile Number" minlength="10"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="email">
                                                    Email
                                                </label>
                                                <input type="email" id="email" class="form-control email"
                                                       name="email" placeholder="Email"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="aadharId">
                                                    Aadhaar Number
                                                </label>
                                                <input type="text" id="aadharId" class="form-control aadharId"
                                                       name="aadharId" placeholder="Enter Aadhaar Number"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="faxNumber">
                                                    Fax Number
                                                </label>
                                                <input type="text" id="faxNumber" class="form-control faxNumber"
                                                       name="faxNumber" placeholder="Fax Number"/>
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

                                            %{-- <div class="col-lg-6 form-group  form-float">
                                                 <label for="contact">
                                                     Contact
                                                 </label>
                                                 <input type="text" id="contact" class="form-control contact"
                                                        name="contact" placeholder="Contact"
                                                        required/>
                                             </div>--}%

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="contactDob">
                                                    Contact Date of Birth
                                                </label>
                                                <input type="text" id="contactDob" class="form-control contactDob"
                                                       name="contactDob" placeholder="Contact DOB"/>
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
                                <h6>Licensing and Limits Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="priorityId">
                                                    Priority
                                                </label>
                                                <select class="form-control show-tick priorityId" name="priorityId"
                                                        id="priorityId">
                                                    <g:each var="p" in="${priority}">
                                                        <option value="${p.id}">${p.priority}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="pan">
                                                    PAN
                                                </label>
                                                <input type="text" id="pan" class="form-control pan"
                                                       name="pan" placeholder="PAN"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="usdNumber">
                                                    CIN (Company Only)
                                                </label>
                                                <input type="text" id="usdNumber" class="form-control usdNumber"
                                                       name="usdNumber" placeholder="USD number"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicence1">
                                                    Drug Licence 1
                                                </label>
                                                <input type="text" id="drugLicence1" class="form-control drugLicence1"
                                                       name="drugLicence1" placeholder="Drug Licence 1"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicence2">
                                                    Drug Licence 2
                                                </label>
                                                <input type="text" id="drugLicence2" class="form-control drugLicence2"
                                                       name="drugLicence2" placeholder="Drug Licence 2"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicenceValidity">
                                                    Drug Licence Validity
                                                </label>
                                                <input type="text" id="drugLicenceValidity"
                                                       class="form-control drugLicenceValidity"
                                                       name="drugLicenceValidity" placeholder=" Drug Licence Validity"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="foodLicence1">
                                                    Food Licence
                                                </label>
                                                <input type="text" id="foodLicence1" class="form-control foodLicence1"
                                                       name="foodLicence1" placeholder="Food Licence"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicenceValidity">
                                                    Food Licence Validity
                                                </label>
                                                <input type="text" id="foodLicenceValidity"
                                                       class="form-control foodLicenceValidity"
                                                       name="foodLicenceValidity" placeholder="Food Licence Validity"/>
                                            </div>

                                            %{-- <div class="col-lg-6 form-group  form-float">
                                                 <label for="ptr">
                                                     PTR
                                                 </label>
                                                 <input type="text" id="ptr" onblur="setTwoNumberDecimal"
                                                        class="form-control ptr" name="ptr"
                                                        placeholder="PTR" step="0.25" value="0.00" required/>
                                             </div>--}%

                                        </div>
                                    </div>

                                    <div class="col-md-6 mt-2"
                                         style="border: 1px solid rgba(13,10,10,0.4);border-radius:10px;padding: 10px;">

                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesBalanceLimit">
                                                    Sales Balance Limit
                                                </label>
                                                <input type="number" id="salesBalanceLimit"
                                                       class="form-control salesBalanceLimit"
                                                       name="salesBalanceLimit" placeholder="Sales Balance Limit"
                                                       onblur="setTwoNumberDecimal" step="0.25" value="0.00"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="noOfCrDays">
                                                    Number Of Credit Days
                                                </label>
                                                <input type="number" id="noOfCrDays" class="form-control noOfCrDays"
                                                       name="noOfCrDays" placeholder=" Number Of Credit Days"
                                                       value="0"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="noOfGraceDays">
                                                    Number Of Grace Days
                                                </label>
                                                <input type="number" id="noOfGraceDays"
                                                       class="form-control noOfGraceDays"
                                                       name="noOfGraceDays" placeholder="Number Of Grace Days"
                                                       value="0"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="calculateOn">
                                                    Calculate On
                                                </label>
                                                <select id="calculateOn" class="form-control calculateOn"
                                                        name="calculateOn">
                                                    <option value="PTS">PTS</option>
                                                    <option value="PTR">PTR</option>
                                                    <option value="MRP">MRP</option>
                                                    <option value="VIP_RATE">VIP Rate</option>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="productMoo">
                                                    Invoice Min. Billing (MOQ)
                                                </label>
                                                <input type="number" id="productMoo" class="form-control productMoo"
                                                       name="productMoo" value="1.00"
                                                       placeholder="enter amount"/>
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
                                <h6>Accounts Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="bankId">
                                                    Bank
                                                </label>
                                                <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                    <option value="0">Please Select</option>
                                                    <g:each var="b" in="${bank}">
                                                        <option value="${b.id}">${b.bankName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="accountNo">
                                                    Account Number
                                                </label>
                                                <input type="text" id="accountNo" class="form-control accountNo"
                                                       name="accountNo" placeholder="Account Number"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="upiId">
                                                    UPI
                                                </label>
                                                <input type="text" id="upiId" class="form-control upiId"
                                                       name="upiId" placeholder="UPI Id"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="openingBalance">
                                                    Opening Balance
                                                </label>
                                                <input type="number" id="openingBalance"
                                                       class="form-control openingBalance"
                                                       onblur="setTwoNumberDecimal()"
                                                       step="0.25" value="0.00"
                                                       name="openingBalance" placeholder="Opening Balance" required/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="currentBalance">
                                                    Current Balance
                                                </label>
                                                <input type="number" id="currentBalance"
                                                       class="form-control currentBalance"
                                                       onblur="setTwoNumberDecimal()"
                                                       step="0.25" value="0.00"
                                                       name="currentBalance" placeholder="Current Balance" disabled/>
                                                <input type="hidden" name="currentBalance" value="0.00"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="discount">
                                                    Discount
                                                </label>
                                                <input type="number" id="discount" class="form-control discount"
                                                       name="discount" placeholder="Discount"
                                                       onblur="setTwoNumberDecimal()" step="0.25" value="0.00"
                                                       required/>
                                            </div>

                                            %{--  <div class="col-lg-6 form-group  form-float">
                                                  <label for="bankCommision">
                                                      Bank Commission
                                                  </label>
                                                  <input type="number" id="bankCommision" class="form-control bankCommision"
                                                         onblur="setTwoNumberDecimal()" step="0.25" value="0.00"
                                                         name="bankCommision" placeholder="Bank Commision"
                                                         required/>
                                              </div>
        --}%
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="defaultCharge">
                                                    Default Charge
                                                </label>
                                                <input type="number" id="defaultCharge"
                                                       class="form-control defaultCharge"
                                                       onblur="setTwoNumberDecimal()" step="0.25" value="0.00"
                                                       name="defaultCharge" placeholder="Default Charge"
                                                       required/>
                                            </div>


                                            %{--<div class="col-lg-6 form-group  form-float">
                                                <label for="careTaker">
                                                    Care Taker
                                                </label>
                                                <input type="number" id="careTaker" class="form-control careTaker"
                                                       name="careTaker" placeholder="Care Taker"/>
                                            </div>--}%
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
                                <h6>Executive Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="terms">
                                                    Default Message
                                                </label>
                                                <input type="text" id="terms" class="form-control terms"
                                                       name="terms" placeholder="Terms"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesman">
                                                    Salesman
                                                </label>
                                                <select class="form-control show-tick salesman" name="salesman"
                                                        id="salesman">
                                                    <g:each var="sales" in="${salesmanList}">
                                                        <option value="${sales.id}">${sales.userName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="manager">
                                                    Manager
                                                </label>
                                                <select class="form-control show-tick manager" name="manager"
                                                        id="manager">
                                                    <g:each var="manager" in="${managerList}">
                                                        <option value="${manager.id}">${manager.userName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesmanCommission">
                                                    Salesman Commission
                                                </label>
                                                <input type="number" id="salesmanCommission"
                                                       class="form-control salesmanCommission"
                                                       name="salesmanCommission" placeholder="Salesman Commission"
                                                       onblur="setTwoNumberDecimal()" step="0.25" value="0.00"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="entityRoute">
                                                    Route
                                                </label>
                                                <select class="form-control show-tick entityRoute routeselect" name="entityRoute"
                                                        id="entityRoute">
                                                    <option selected disabled>--SELECT--</option>
                                                    <g:each var="route" in="${routeregister}">
                                                        <option value="${route?.id}">${route?.routeName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="repName">
                                                    Rep Name
                                                </label>
                                                <input type="text" id="repName" class="form-control repName"
                                                       name="repName" placeholder="Rep Name"/>
                                            </div>


                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="repPhoneNumber">
                                                    Rep Phone Number
                                                </label>
                                                <input type="text" id="repPhoneNumber"
                                                       class="form-control repPhoneNumber"
                                                       name="repPhoneNumber" placeholder="Rep Phone Number"/>
                                            </div>
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="website">
                                                    Website
                                                </label>
                                                <input type="url" id="website"
                                                       class="form-control website"
                                                       name="website" placeholder="Website"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <input type="hidden" name="status" value="1">
                                    <input type="hidden" name="syncStatus" value="1">
                                    <input type="hidden" name="bankCommision" value="0">
                                    <input type="hidden" name="companyCode" value="0">
                                    <input type="hidden" name="accountId" value="0">
                                    <input type="hidden" name="contact" value="0">
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
                                            <button type="reset"
                                                    class="btn btn-danger btn-simple btn-round waves-effect"
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
        </g:if>
        <g:else>
            <form action="/entity-register/update/${entity.id}" id="entityRegisterForm" method="POST" role="form"
                  class="entityRegisterForm" enctype="multipart/form-data">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="card">
                            <div class="header">
                                <h6>Entity Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-md-6 form-group  form-float">
                                                <label for="entityName">
                                                    Entity Name <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <input type="text" id="entityName" class="form-control entityName"
                                                       name="entityName" placeholder="Entity Name"
                                                       value="${entity.entityName}"
                                                       required/>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="entityType">
                                                    Entity Type <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick entityType" name="entityType"
                                                        id="entityType" required>
                                                    <g:each var="et" in="${entitytype}">
                                                        <option value="${et.id}"
                                                                <g:if test="${et.id == entity.entityType.id}">selected</g:if>>${et.name}</option>
                                                       %{-- <g:if test="${session.getAttribute('role') == Constants.ENTITY_ADMIN}">
                                                            <g:if test="${et.name != "MANUFACTURER"}">
                                                                <option value="${et.id}"
                                                                        <g:if test="${et.id == entity.entityType.id}">selected</g:if>>${et.name}</option>
                                                            </g:if>
                                                        </g:if>
                                                        <g:else>
                                                            <option value="${et.id}"
                                                                    <g:if test="${et.id == entity.entityType.id}">selected</g:if>>${et.name}</option>
                                                        </g:else>--}%

                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-md-6 form-group">
                                                <label for="addressLine1">
                                                    Address Line 1 <span class="required-indicator"
                                                                         style="color: red;">*</span>
                                                </label>
                                                <input type="text" id="addressLine1" class="form-control addressLine1"
                                                       name="addressLine1" placeholder="Address Line 1"
                                                       value="${entity.addressLine1}"
                                                       required/>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="addressLine1">
                                                    Address Line 2
                                                </label>
                                                <input type="text" id="addressLine2" class="form-control addressLine2"
                                                       name="addressLine2" placeholder="Address Line 2"
                                                       value="${entity.addressLine2}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="gstn">
                                                    GSTIN
                                                </label>
                                                <input maxlength="15" type="text" id="gstn" class="form-control gstn"
                                                       name="gstn" placeholder="GSTIN" value="${entity.gstn}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="pinCode">
                                                    PIN Code <span class="required-indicator"
                                                                   style="color: red;">*</span>
                                                </label>

                                                <div>
                                                    <select class="pinCode form-control" id="pinCode"></select>
                                                    <input type="hidden" name="pinCode" value="${entity.pinCode}">
                                                </div>
                                                <sub id="prevPin">Previously selected pincode:
                                                    <b>${entity.pinCode}</b></sub><br>
                                                <sub id="prevArea">Previously selected area: <b>${cityId.areaName}</b>
                                                </sub>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="cityId">
                                                    Area / City <span class="required-indicator"
                                                                      style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick cityId" name="cityId" id="cityId"
                                                        disabled>
                                                    <input type="hidden" name="cityId" value="${entity.cityId}"/>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="hqarea">
                                                    HQ Area
                                                </label>
                                                <select class="form-control show-tick hqarea" name="hqarea" id="hqarea">
                                                    <option value="0">Please Select</option>
                                                    <g:each var="hq" in="${hqareas}">
                                                        <option value="${hq.id}">${hq.hqName}</option>
                                                    </g:each>
                                                </select>
                                            </div>


                                            <div class="col-md-6 form-group  form-float">
                                                <label for="stateId">
                                                    State <span class="required-indicator" style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick stateId" name="stateId"
                                                        id="stateId" disabled>
                                                    <g:each var="state" in="${statelist}">
                                                        <option value="${state.id}"
                                                                <g:if test="${state.id == entity.stateId}">selected</g:if>>${state.name}</option>
                                                    </g:each>
                                                    <input type="hidden" name="stateId" value="${entity.stateId}"/>
                                                </select>
                                            </div>

                                            <div class="col-md-6 form-group  form-float">
                                                <label for="countryId">
                                                    Country <span class="required-indicator"
                                                                  style="color: red;">*</span>
                                                </label>
                                                <select class="form-control show-tick countryId" name="countryId"
                                                        id="countryId" disabled>
                                                    <g:each var="country" in="${countrylist}">
                                                        <option value="${country.id}">${country.name}</option>
                                                    </g:each>
                                                </select>
                                                <input type="hidden" name="countryId" value="${entity.countryId}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <g:if test="${(session.getAttribute('role') == Constants.SUPER_USER || session.getAttribute('role') == Constants.ENTITY_ADMIN) && parentEntities?.size() > 0}">
                    <div class="row clearfix">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <div class="card">
                                <div class="header">
                                    <h6>Affiliation Information</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-6 mt-2">
                                            <g:if test="${session.getAttribute('role') == Constants.SUPER_USER}">
                                                <div class="form-group">
                                                    <label for="isParent" class="checkbox-inline">
                                                        <input onchange="isParentChanged()" type="checkbox"
                                                               class="checkbox"
                                                               id="isParent"/> Is Parent Entity?
                                                    </label>
                                                </div>
                                            </g:if>
                                            <div class="form-group affiliatedEntityContainer">
                                                <label for="affiliatedToEntity">Affiliated to Entity: <span
                                                        class="required-indicator"
                                                        style="color: red;">*</span></label>
                                                <select name="affiliatedToEntity" style="width: 100%;"
                                                        id="affiliatedToEntity" class="form-control affiliatedToEntity"
                                                        required>
                                                    <option selected disabled>-SELECT-</option>
                                                </select>
                                            </div>
                                            <input type="hidden" id="isParentValue" name="isParent" value="false"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </g:if>

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
                                                <label for="contactName">
                                                    Contact Name
                                                </label>
                                                <input type="text" id="contactName" class="form-control contactName"
                                                       name="contactName" placeholder="Contact Name"
                                                       value="${entity.contactName}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="phoneNumber">
                                                    Phone Number
                                                </label>
                                                <input type="number" id="phoneNumber" class="form-control phoneNumber"
                                                       name="phoneNumber" placeholder="Phone Number"
                                                       value="${entity.phoneNumber}"/>

                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="mobileNumber">
                                                    Mobile Number
                                                </label>
                                                <input type="number" id="mobileNumber" class="form-control mobileNumber"
                                                       name="mobileNumber" placeholder="Mobile Number" minlength="10"
                                                       value="${entity.mobileNumber}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="email">
                                                    Email
                                                </label>
                                                <input type="email" id="email" class="form-control email"
                                                       name="email" placeholder="Email" value="${entity.email}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="aadharId">
                                                    Aadhaar Number
                                                </label>
                                                <input type="text" id="aadharId" class="form-control aadharId"
                                                       name="aadharId" placeholder="Enter Aadhaar Number"
                                                       value="${entity.aadharId}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="faxNumber">
                                                    Fax Number
                                                </label>
                                                <input type="text" id="faxNumber" class="form-control faxNumber"
                                                       name="faxNumber" placeholder="Fax Number"
                                                       value="${entity.faxNumber}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="zoneId">
                                                    Zone
                                                </label>
                                                <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">
                                                    <g:each var="zone" in="${zoneList}">
                                                        <option value="${zone.id}" <g:if
                                                                test="${zone.id == entity.zoneId}">selected</g:if>>${zone.name}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            %{-- <div class="col-lg-6 form-group  form-float">
                                                 <label for="contact">
                                                     Contact
                                                 </label>
                                                 <input type="text" id="contact" class="form-control contact"
                                                        name="contact" placeholder="Contact"
                                                        required/>
                                             </div>--}%

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="contactDob">
                                                    Contact Date of Birth
                                                </label>
                                                <input type="text" id="contactDob" class="form-control contactDob"
                                                       name="contactDob" placeholder="Contact DOB"
                                                       value="${entity.contactDob}"/>
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
                                <h6>Licensing and Limits Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-6 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="priorityId">
                                                    Priority
                                                </label>
                                                <select class="form-control show-tick priorityId" name="priorityId"
                                                        id="priorityId">
                                                    <g:each var="p" in="${priority}">
                                                        <option value="${p.id}"
                                                                <g:if test="${p.id == entity.priorityId}">selected</g:if>>${p.priority}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="pan">
                                                    PAN
                                                </label>
                                                <input type="text" id="pan" class="form-control pan"
                                                       name="pan" placeholder="PAN" value="${entity.pan}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="usdNumber">
                                                    CIN (Company Only)
                                                </label>
                                                <input type="text" id="usdNumber" class="form-control usdNumber"
                                                       name="usdNumber" placeholder="USD number"
                                                       value="${entity.usdNumber}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicence1">
                                                    Drug Licence 1
                                                </label>
                                                <input type="text" id="drugLicence1" class="form-control drugLicence1"
                                                       name="drugLicence1" placeholder="Drug Licence 1"
                                                       value="${entity.drugLicence1}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicence2">
                                                    Drug Licence 2
                                                </label>
                                                <input type="text" id="drugLicence2" class="form-control drugLicence2"
                                                       name="drugLicence2" placeholder="Drug Licence 2"
                                                       value="${entity.drugLicence2}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicenceValidity">
                                                    Drug Licence Validity
                                                </label>
                                                <input type="text" id="drugLicenceValidity"
                                                       class="form-control drugLicenceValidity"
                                                       name="drugLicenceValidity"
                                                       placeholder=" Drug Licence Validity"
                                                       alue="${entity.drugLicenceValidity}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="foodLicence1">
                                                    Food Licence
                                                </label>
                                                <input type="text" id="foodLicence1" class="form-control foodLicence1"
                                                       name="foodLicence1" placeholder="Food Licence"
                                                       value="${entity.foodLicence1}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="drugLicenceValidity">
                                                    Food Licence Validity
                                                </label>
                                                <input type="text" id="foodLicenceValidity"
                                                       class="form-control foodLicenceValidity"
                                                       name="foodLicenceValidity" placeholder="Food Licence Validity"
                                                       value="${entity.foodLicenceValidity}"/>
                                            </div>

                                            %{-- <div class="col-lg-6 form-group  form-float">
                                                 <label for="ptr">
                                                     PTR
                                                 </label>
                                                 <input type="text" id="ptr" onblur="setTwoNumberDecimal"
                                                        class="form-control ptr" name="ptr"
                                                        placeholder="PTR" step="0.25" value="0.00" required/>
                                             </div>--}%

                                        </div>
                                    </div>

                                    <div class="col-md-6 mt-2"
                                         style="border: 1px solid rgba(13,10,10,0.4);border-radius:10px;padding: 10px;">

                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesBalanceLimit">
                                                    Sales Balance Limit
                                                </label>
                                                <input type="number" id="salesBalanceLimit"
                                                       class="form-control salesBalanceLimit"
                                                       name="salesBalanceLimit" placeholder="Sales Balance Limit"
                                                       onblur="setTwoNumberDecimal" step="0.25"
                                                       value="${entity.salesBalanceLimit}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="noOfCrDays">
                                                    Number Of Credit Days
                                                </label>
                                                <input type="number" id="noOfCrDays" class="form-control noOfCrDays"
                                                       name="noOfCrDays" placeholder=" Number Of Credit Days"
                                                       value="${entity.noOfCrDays}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="noOfGraceDays">
                                                    Number Of Grace Days
                                                </label>
                                                <input type="number" id="noOfGraceDays"
                                                       class="form-control noOfGraceDays"
                                                       name="noOfGraceDays" placeholder="Number Of Grace Days"
                                                       value="${entity.noOfGraceDays}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="calculateOn">
                                                    Calculate On
                                                </label>
                                                <select id="calculateOn" class="form-control calculateOn"
                                                        name="calculateOn">
                                                    <option value="PTS" <g:if
                                                            test="${"PTS" == entity.calculateOn}">selected</g:if>>PTS
                                                    </option>
                                                    <option value="PTR" <g:if
                                                            test="${"PTR" == entity.calculateOn}">selected</g:if>>PTR</option>
                                                    <option value="MRP" <g:if
                                                            test="${"MRP" == entity.calculateOn}">selected</g:if>>MRP</option>
                                                    <option value="VIP_RATE" <g:if
                                                            test="${"VIP_RATE" == entity.calculateOn}">selected</g:if>>VIP Rate</option>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="productMoo">
                                                    Invoice Min. Billing (MOQ)
                                                </label>
                                                <input type="number" id="productMoo" class="form-control productMoo"
                                                       name="productMoo" value="${entity.productMoo}"
                                                       placeholder="enter amount"/>
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
                                <h6>Accounts Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="bankId">
                                                    Bank
                                                </label>
                                                <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                    <option value="0">Please Select</option>
                                                    <g:each var="b" in="${bank}">
                                                        <option value="${b.id}"
                                                                <g:if test="${b.id == entity.bankId}">selected</g:if>>${b.bankName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="accountNo">
                                                    Account Number
                                                </label>
                                                <input type="text" id="accountNo" class="form-control accountNo"
                                                       name="accountNo" placeholder="Account Number"
                                                       value="${entity.accountNo}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="upiId">
                                                    UPI
                                                </label>
                                                <input type="text" id="upiId" class="form-control upiId"
                                                       name="upiId" placeholder="UPI Id" value="${entity.upiId}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="openingBalance">
                                                    Opening Balance
                                                </label>
                                                <input type="number" id="openingBalance"
                                                       class="form-control openingBalance"
                                                       onblur="setTwoNumberDecimal()"
                                                       step="0.25" value="${entity.openingBalance}"
                                                       name="openingBalance" placeholder="Opening Balance" required/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="currentBalance">
                                                    Current Balance
                                                </label>
                                                <input type="number" id="currentBalance"
                                                       class="form-control currentBalance"
                                                       onblur="setTwoNumberDecimal()"
                                                       step="0.25" value="${entity.currentBalance}"
                                                       name="currentBalance" placeholder="Current Balance" disabled/>
                                                <input type="hidden" name="currentBalance" value="0.00"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="discount">
                                                    Discount
                                                </label>
                                                <input type="number" id="discount" class="form-control discount"
                                                       name="discount" placeholder="Discount"
                                                       onblur="setTwoNumberDecimal()" step="0.25"
                                                       value="${entity.discount}" required/>
                                            </div>

                                            %{--  <div class="col-lg-6 form-group  form-float">
                                                  <label for="bankCommision">
                                                      Bank Commission
                                                  </label>
                                                  <input type="number" id="bankCommision" class="form-control bankCommision"
                                                         onblur="setTwoNumberDecimal()" step="0.25" value="0.00"
                                                         name="bankCommision" placeholder="Bank Commision"
                                                         required/>
                                              </div>
        --}%
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="defaultCharge">
                                                    Default Charge
                                                </label>
                                                <input type="number" id="defaultCharge"
                                                       class="form-control defaultCharge"
                                                       onblur="setTwoNumberDecimal()" step="0.25"
                                                       value="${entity.defaultCharge}"
                                                       name="defaultCharge" placeholder="Default Charge"
                                                       required/>
                                            </div>


                                            %{--<div class="col-lg-6 form-group  form-float">
                                                <label for="careTaker">
                                                    Care Taker
                                                </label>
                                                <input type="number" id="careTaker" class="form-control careTaker"
                                                       name="careTaker" placeholder="Care Taker"/>
                                            </div>--}%
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
                                <h6>Executive Information</h6>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <div class="row">
                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="terms">
                                                    Default Message
                                                </label>
                                                <input type="text" id="terms" class="form-control terms"
                                                       name="terms" placeholder="Terms" value="${entity.terms}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesman">
                                                    Salesman
                                                </label>
                                                <select class="form-control show-tick salesman" name="salesman"
                                                        id="salesman">
                                                    <g:each var="sales" in="${salesmanList}">
                                                        <option value="${sales.id}" <g:if
                                                                test="${sales.id == entity.salesman}">selected</g:if>>${sales.userName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="manager">
                                                    Manager
                                                </label>
                                                <select class="form-control show-tick manager" name="manager"
                                                        id="manager">
                                                    <g:each var="manager" in="${managerList}">
                                                        <option value="${manager.id}" <g:if
                                                                test="${manager.id == entity.manager}">selected</g:if>>${manager.userName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="salesmanCommission">
                                                    Salesman Commission
                                                </label>
                                                <input type="number" id="salesmanCommission"
                                                       class="form-control salesmanCommission"
                                                       name="salesmanCommission" placeholder="Salesman Commission"
                                                       onblur="setTwoNumberDecimal()" step="0.25"
                                                       value="${entity.salesmanCommission}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="entityRoute">
                                                    Route
                                                </label>
                                                <select class="form-control show-tick entityRoute routeselect" name="entityRoute"
                                                        id="entityRoute">
                                                    <g:each var="route" in="${routeregister}">
                                                        <option value="${route?.id}"
                                                                <g:if test="${route?.id == entity?.entityRoute?.id}">selected</g:if>>${route?.routeName}</option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="repName">
                                                    Rep Name
                                                </label>
                                                <input type="text" id="repName" class="form-control repName"
                                                       name="repName" placeholder="Rep Name" value="${entity.repName}"/>
                                            </div>


                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="repPhoneNumber">
                                                    Rep Phone Number
                                                </label>
                                                <input type="text" id="repPhoneNumber"
                                                       class="form-control repPhoneNumber"
                                                       name="repPhoneNumber" placeholder="Rep Phone Number"
                                                       value="${entity.repPhoneNumber}"/>
                                            </div>

                                            <div class="col-lg-6 form-group  form-float">
                                                <label for="website">
                                                    Website
                                                </label>
                                                <input type="url" id="website"
                                                       class="form-control website"
                                                       name="website" placeholder="Website" value="${entity?.repPhoneNumber}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <input type="hidden" name="status" value="1">
                                    <input type="hidden" name="syncStatus" value="1">
                                    <input type="hidden" name="bankCommision" value="0">
                                    <input type="hidden" name="companyCode" value="0">
                                    <input type="hidden" name="accountId" value="0">
                                    <input type="hidden" name="contact" value="0">
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
                                            <button type="reset"
                                                    class="btn btn-danger btn-simple btn-round waves-effect"
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
        </g:else>
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
<asset:javascript src="/themeassets/plugins/select2/"/>
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>--}%
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>



<script>

    $(function () {
        $('.routeselect').select2();
        //Datetimepicker plugin
        $('.contactDob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
        $('.foodLicenceValidity').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
        $('.drugLicenceValidity').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        $('.affiliateId').select2();

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
        <g:if test="${session.getAttribute('role') == Constants.SUPER_USER || session.getAttribute('role') == Constants.ENTITY_ADMIN}">
        $("#affiliatedToEntity").select2();
        </g:if>

        $('.pinCode').select2({
            placeholder: 'Enter Pincode',
            minimumInputLength: 3,
            required: true,
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
                    response.forEach(function (response, index) {
                        data.push({"pincode": response.pincode, "text": response.areaName, "id": response.id});
                    });
                    return {
                        results: data
                    };
                },
                cache: true
            }
        });


        $('.pinCode').on('select2:selecting', function (e) {
            var data = e.params.args.data;
            var id = data.id;
            // alert(id)
            $.ajax({
                method: 'GET',
                url: '/getcitybyid',
                data: {'id': id},
                success: function (response) {
                    console.log(response);
                    $('.stateId').val(response.state.id).change();
                    $("input[name='stateId']").val(response.state.id);
                    $("input[name='cityId']").val(response.id);
                    $('.cityId').empty();
                    $('.cityId').append("<option value='" + response.id + "'>" + response.areaName + "</option>");
                    // $('.cityId').val(response.id).change();
                    $('.pinCode').val(response.pincode);
                    $("input[name='pinCode']").val(response.pincode);
                    if (response.state.alphaCode === "FC") {
                        $('.countryId').find('option:contains("OTHER")').attr('selected', 'selected');
                        $("input[name='countryId']").val($('.countryId').val());
                    } else {
                        $('.countryId').find('option:contains("INDIA")').attr('selected', 'selected');
                        $("input[name='countryId']").val($('.countryId').val());
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });

        });

        $("#entityRegisterForm").validate();

        <g:if test="${params.code == "1"}">
        Swal.fire({
            text: "Entity Update Successful",
            title: "Success!"
        });
        </g:if>
        <g:elseif test="${params.code == "2"}">
        Swal.fire({
            text: "Entity Update Failed",
            title: "Failed!"
        });
        </g:elseif>
    });

    /*$(document).ready(function () {
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

        drEvent.on('dropify.beforeClear', function (event, element) {
            return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
        });

        drEvent.on('dropify.afterClear', function (event, element) {
            alert('File deleted');
        });

        drEvent.on('dropify.errors', function (event, element) {
            console.log('Has Errors');
        });

        var drDestroy = $('#input-file-to-destroy').dropify();
        drDestroy = drDestroy.data('dropify')
        $('#toggleDropify').on('click', function (e) {
            e.preventDefault();
            if (drDestroy.isDropified()) {
                drDestroy.destroy();
            } else {
                drDestroy.init();
            }
        })
    });*/

    function setTwoNumberDecimal(event) {
        this.value = parseFloat(this.value.toFixed(2));
    }

    <g:if test="${params.id == null || params.id == ""}">

    $("#entityRegisterForm").submit(function (event) {
        var pincode = $('.pinCode option').length;
        if (pincode === 0 || pincode < 0) {
            Swal.fire("Please enter  pincode and  select area");
            event.preventDefault();
        }
    });
    </g:if>

    // $('#updatePassword').submit(function(event) {
    //     var formData = $(this);
    //     $.ajax({
    //         type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
    //         url         : formData.attr('action'), // the url where we want to POST
    //         data        : formData.serialize(), // our data object
    //         success:function(data){
    //             $("#validation-status").text(data);
    //             Swal.fire('success','Password Changed Successfully',data);
    //         },
    //         error:function(data){
    //             console.log("Failed");
    //             $("#validation-status").text(data.responseText);
    //             Swal.fire('error','Password Change Failed',data.responseText);
    //         }
    //     });
    //     event.preventDefault();
    // });


    <g:if test="${(session.getAttribute('role') == Constants.SUPER_USER || session.getAttribute('role') == Constants.ENTITY_ADMIN) && parentEntities?.size()>0}">
    loadAffiliated();

    function loadAffiliated() {
        var url = "../getparententities";
        var affiliateId = "";
        <g:if test="${params.id == null || params.id == ""}">
        url = "getparententities";
        </g:if>
        <g:else>
        affiliateId = ${entity.affiliateId};
        </g:else>
        $.ajax({
            method: "GET",
            url: url,
            success: function (data) {
                $("#affiliatedToEntity").empty();
                $("#affiliatedToEntity").append("<option selected disabled>--SELECT--</option>");
                $.each(data, function (index, value) {
                    var entityTypeName = value.entityType.name;
                    var entityTypeId = value.entityType.id;
                    var selected = "";
                    if (value.id == affiliateId) {
                        selected = "selected";
                    }
                    $("#affiliatedToEntity").append("<option value=\"" + value.id + "_" + entityTypeId + "\" " + selected + " >" + value.entityName + " (" + entityTypeName + ")</option>");

                });
            }
        })
    }

    function isParentChanged() {
        var isParent = $("#isParent").is(":checked");
        $("#isParentValue").val(isParent);
        if (isParent) {
            $(".affiliatedEntityContainer").addClass("hidden");
            $(".affiliatedToEntity").prop("required", false);
        } else {
            $(".affiliatedEntityContainer").removeClass("hidden");
            $(".affiliatedToEntity").prop("required", true);
        }
    }

    </g:if>


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>