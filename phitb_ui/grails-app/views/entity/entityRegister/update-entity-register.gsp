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
%{--    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css"/>--}%
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <style>
    .error
    {
        color:red;
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
                    <h2>Update  Entity Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/entity-register"> Entity Register</a></li>
                        <li class="breadcrumb-item active">Update  Entity Register</li>
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
                    <div class="body">
                        <form action="/entity-register/update/${entity.id}" id="entityRegisterForm" method="POST" role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="entityName">
                                                Entity Name
                                            </label>
                                            <input type="text" id="entityName" class="form-control entityName"
                                                   name="entityName" placeholder="Entity Name" value="${entity.entityName}"
                                                   required/>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="entityType">
                                                Entity Type
                                            </label>
                                            <select class="form-control show-tick entityType" name="entityType" id="entityType">
                                                <g:each var="et" in="${entitytype}">
                                                    <option value="${et.id}" <g:if test="${et.id == entity.entityType.id}">selected</g:if>>${et.name}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        %{--<div class="col-md-6 form-group  form-float">
                                            <label for="affiliateId">
                                                Affiliate Id
                                            </label>
                                            <select class="form-control show-tick affiliateId" name="affiliateId" id="affiliateId">
                                                <g:each var="af" in="${entityList}">
                                                    <option value="${af.id}"  <g:if test="${af.id == entity.entityType.id}">selected</g:if>>${af.entityName}</option>
                                                </g:each>
                                            </select>
                                        </div>--}%
                                        <div class="col-md-6 form-group">
                                            <label for="addressLine1">
                                                Address Line 1
                                            </label>
                                            <input type="text" id="addressLine1" class="form-control addressLine1"
                                                   name="addressLine1" placeholder="Address Line 1" value="${entity.addressLine1}"
                                                   required/>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="addressLine1">
                                                Address Line 2
                                            </label>
                                            <input type="text" id="addressLine2" class="form-control addressLine2"
                                                   name="addressLine2" placeholder="Address Line 2" value="${entity.addressLine2}"
                                                   required/>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="countryId">
                                                Country
                                            </label>
                                            <select class="form-control show-tick countryId"  id="countryId" disabled>
                                                <g:each var="country" in="${countrylist}">
                                                    <option value="${country.id}" <g:if test="${country.id == entity.countryId}">selected</g:if>>${country.name}</option>
                                                </g:each>
                                                <input type="hidden" name="countryId" value="${entity.countryId}"/>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="ml-1"></div>
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;  padding: 10px;">
                                    <div class="row">

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="pinCode">
                                                Pin Code
                                            </label>

                                            <div>
                                                <select class="pinCode form-control" id="pinCode"></select>
                                                <input type="hidden" name="pinCode" value="${entity.pinCode}">
                                            </div>
                                            <sub id="prevPin">Previously selected pincode: <b>${entity.pinCode}</b></sub>
                                            <sub id="prevArea">Previously selected area: <b>${cityId.areaName}</b></sub>

                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="stateId">
                                                State
                                            </label>
                                            <select class="form-control show-tick stateId" name="stateId"
                                                    id="stateId" disabled>
                                                <g:each var="state" in="${statelist}">
                                                    <option value="${state.id}" <g:if test="${state.id == entity.stateId}">selected</g:if>>${state.name}</option>
                                                </g:each>
                                            </select>
                                            <input type="hidden" name="stateId" value="${entity.stateId}"/>

                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="cityId">
                                                City
                                            </label>
                                            <select class="form-control show-tick cityId" name="cityId" id="cityId" disabled>
                                            </select>
                                            <input type="hidden" name="cityId" value="${entity.cityId}"/>

                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="phoneNumber">
                                                Phone Number
                                            </label>
                                            <input type="number" id="phoneNumber" class="form-control phoneNumber"
                                                   name="phoneNumber" placeholder="Phone Number" value="${entity.phoneNumber}"
                                                   required/>

                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="mobileNumber">
                                                Mobile Number
                                            </label>
                                            <input type="number" id="mobileNumber" class="form-control mobileNumber"
                                                   name="mobileNumber" placeholder="Mobile Number"  minlength="10" value="${entity.mobileNumber}"
                                                   required/>
                                        </div>
                                        %{--                                        <div class="col-lg-6 form-group  form-float">--}%
                                        %{--                                            <label for="productMoo">--}%
                                        %{--                                                Product MOQ--}%
                                        %{--                                            </label>--}%
                                        %{--                                            <input type="number" id="productMoo" class="form-control productMoo"--}%
                                        %{--                                                   name="productMoo"--}%
                                        %{--                                                   placeholder="Product MOQ"/>--}%
                                        %{--                                        </div>--}%
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="email">
                                                Email
                                            </label>
                                            <input type="email" id="email" class="form-control email"
                                                   name="email" placeholder="Email" value="${entity.email}"
                                            />
                                        </div>
                                    </div>
                                </div>



                                <div class="col-md-6 mt-2" style="max-width: 49%;border: 1px solid black;
                                border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="contactName">
                                                Contact Name
                                            </label>
                                            <input type="text" id="contactName" class="form-control contactName"
                                                   name="contactName" placeholder="Contact Name" value="${entity.contactName}"
                                            />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="priorityId">
                                                Priority
                                            </label>
                                            <select class="form-control show-tick priorityId" name="priorityId" id="priorityId">
                                                <g:each var="p" in="${priority}">
                                                    <option value="${p.id}" <g:if test="${p.id == entity.priorityId}">selected</g:if>>${p.priority}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="pan">
                                                PAN
                                            </label>
                                            <input type="text" id="pan" class="form-control pan"
                                                   name="pan" placeholder="PAN"  value="${entity.pan}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="gstn">
                                                GSTIN
                                            </label>
                                            <input type="text" id="gstn" class="form-control gstn"
                                                   name="gstn" placeholder="GSTIN" value="${entity.gstn}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="gstn">
                                                USD number
                                            </label>
                                            <input type="text" id="usdNumber" class="form-control usdNumber"
                                                   name="usdNumber" placeholder="USD number" value="${entity.usdNumber}"
                                            />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="drugLicence1">
                                                Drug Licence 1
                                            </label>
                                            <input type="text" id="drugLicence1" class="form-control drugLicence1"
                                                   name="drugLicence1" placeholder="Drug Licence 1" value="${entity.drugLicence1}"
                                            />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="drugLicence2">
                                                Drug Licence 2
                                            </label>
                                            <input type="text" id="drugLicence2" class="form-control drugLicence2"
                                                   name="drugLicence2" placeholder="Drug Licence 2" value="${entity.drugLicence2}"
                                            />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="drugLicenceValidity">
                                                Drug Licence Validity
                                            </label>
                                            <input type="text" id="drugLicenceValidity" class="form-control drugLicenceValidity"
                                                   name="drugLicenceValidity" placeholder=" Drug Licence Validity" value="${entity.drugLicenceValidity}"
                                            />
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="foodLicence1">
                                                Food Licence
                                            </label>
                                            <input type="text" id="foodLicence1" class="form-control foodLicence1"
                                                   name="foodLicence1" placeholder="Food Licence" value="${entity.foodLicence1}"/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="drugLicenceValidity">
                                                Food Licence Validity
                                            </label>
                                            <input type="text" id="foodLicenceValidity" class="form-control foodLicenceValidity"
                                                   name="foodLicenceValidity" placeholder="Food Licence Validity"  value="${entity.foodLicenceValidity}"
                                            />
                                        </div>

                                        <div class="col-lg-12 form-group  form-float">
                                            <label for="salesBalanceLimit">
                                                Sales Balance Limit
                                            </label>
                                            <input type="number" id="salesBalanceLimit" class="form-control salesBalanceLimit"
                                                   name="salesBalanceLimit" placeholder="Sales Balance Limit"
                                                   onblur="setTwoNumberDecimal" step="0.25"  value="${entity.salesBalanceLimit}"
                                            required/>
                                        </div>
                                    </div>
                                </div>



                                <div class="ml-1"></div>

                                <div class="col-md-6 mt-2" style="max-width: 49%;border: 1px solid black;
                                border-radius:
                                10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="noOfCrDays">
                                                Number Of CR Days
                                            </label>
                                            <input type="number" id="noOfCrDays" class="form-control noOfCrDays"
                                                   name="noOfCrDays" placeholder=" Number Of CR Days" value="${entity.noOfCrDays}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="noOfGraceDays">
                                                Number Of Grace Days
                                            </label>
                                            <input type="number" id="noOfGraceDays" class="form-control noOfGraceDays"
                                                   name="noOfGraceDays" placeholder="Number Of Grace Days" value="${entity.noOfGraceDays}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="calculateOn">
                                                Calculate On
                                            </label>
                                            <input type="text" id="calculateOn" class="form-control calculateOn"
                                                   name="calculateOn" placeholder="Calculate On" value="${entity.calculateOn}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="bankId">
                                                Bank
                                            </label>
                                            <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                <option value="0">Please Select</option>
                                                <g:each var="b" in="${bank}">
                                                    <option value="${b.id}"  <g:if test="${b.id == entity.bankId}">selected</g:if>>${b.bankName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="accountNo">
                                                Account Number
                                            </label>
                                            <input type="text" id="accountNo" class="form-control accountNo" value="${entity.accountNo}"
                                                   name="accountNo" placeholder="Account Number" />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="upiId">
                                                UPI Id
                                            </label>
                                            <input type="text" id="upiId" class="form-control upiId" value="${entity.upiId}"
                                                   name="upiId" placeholder="UPI Id"/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="openingBalance">
                                                Opening Balance
                                            </label>
                                            <input type="number" id="openingBalance"
                                                   class="form-control openingBalance" onblur="setTwoNumberDecimal()" step="0.25" value="${entity.openingBalance}"
                                                   name="openingBalance" placeholder="Opening Balance" required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="currentBalance">
                                                Current Balance
                                            </label>
                                            <input type="number" id="currentBalance"
                                                   class="form-control currentBalance"  onblur="setTwoNumberDecimal()" step="0.25" value="${entity.currentBalance}"
                                                   name="currentBalance" placeholder="Current Balance" required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="discount">
                                                Discount
                                            </label>
                                            <input type="number" id="discount" class="form-control discount"
                                                   name="discount" placeholder="Discount"
                                                   onblur="setTwoNumberDecimal()" step="0.25" value="${entity.discount}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="bankCommision">
                                                Bank Commision
                                            </label>
                                            <input type="number" id="bankCommision" class="form-control bankCommision"  onblur="setTwoNumberDecimal()" step="0.25" value="${entity.bankCommision}"
                                                   name="bankCommision" placeholder="Bank Commision"
                                                   required/>
                                        </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="defaultCharge">
                                                Default Charge
                                            </label>
                                            <input type="number" id="defaultCharge" class="form-control defaultCharge"  onblur="setTwoNumberDecimal()" step="0.25" value="${entity.defaultCharge}"
                                                   name="defaultCharge" placeholder="Default Charge"
                                                   required />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="hqarea">
                                                HQ area
                                            </label>
                                            <select class="form-control show-tick hqarea" name="hqarea" id="hqarea">
                                                <option value="0">Please Select</option>
                                                <g:each var="hq" in="${hqareas}">
                                                    <option value="${hq.id}" <g:if
                                                            test="${hq.id == entity.hqAreaId}">selected</g:if>>${hq.hqName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        %{--                                        <div class="col-lg-6 form-group  form-float">--}%
                                        %{--                                            <label for="careTaker">--}%
                                        %{--                                                Care Taker--}%
                                        %{--                                            </label>--}%
                                        %{--                                            <input type="number" id="careTaker" class="form-control careTaker"--}%
                                        %{--                                                   name="careTaker" placeholder="Care Taker"--}%
                                        %{--                                                   />--}%
                                        %{--                                        </div>--}%
                                    </div>
                                </div>

                                <div class="col-md-12 mt-2" style="max-width: 99%;border: 1px solid black;
                                border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="terms">
                                                Terms
                                            </label>
                                            <input type="text" id="terms" class="form-control terms"
                                                   name="terms" placeholder="Terms" value="${entity.terms}"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="salesman">
                                                Salesman
                                            </label>
                                            <select class="form-control show-tick salesman" name="salesman" id="salesman">
                                                <g:each var="sales" in="${salesmanList}">
                                                    <option value="${sales.id}" <g:if
                                                            test="${sales.id == entity.salesman}">selected</g:if> >${sales.userName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="manager">
                                                Manager
                                            </label>
                                            <select class="form-control show-tick manager" name="manager" id="manager">
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
                                            <input type="number" id="salesmanCommission" class="form-control salesmanCommission"
                                                   name="salesmanCommission" placeholder="Salesman Commission"  onblur="setTwoNumberDecimal()" step="0.25" value="${entity.salesmanCommission}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="routeId">
                                                Route
                                            </label>
                                            <select class="form-control show-tick routeId" name="routeId" id="routeId">
                                                <g:each var="route" in="${routeregister}">
                                                    <option value="${route.id}" <g:if test="${route.id == entity.routeId}">selected</g:if>>${route.routeName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="accountId">
                                                Account
                                            </label>
                                            <select class="form-control show-tick accountId" name="accountId" id="accountId">
                                                <option value="0">Please Select</option>
                                                <g:each var="a" in="${account}">
                                                    <option value="${a.id}" <g:if test="${a.id == entity.accountId}">selected</g:if>>${a.accountName}</option>
                                                </g:each>
                                            </select>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="aadharId">
                                                Aadhar Id
                                            </label>
                                            <input type="text" id="aadharId" class="form-control aadharId"
                                                   name="aadharId" placeholder="Aadhar Id" value="${entity.aadharId}"/>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="companyCode">
                                                Company Code
                                            </label>
                                            <input type="text" id="companyCode" class="form-control companyCode"
                                                   name="companyCode" placeholder="Company Code" value="${entity.companyCode}"
                                            />
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="faxNumber">
                                                Fax Number
                                            </label>
                                            <input type="text" id="faxNumber" class="form-control faxNumber"
                                                   name="faxNumber" placeholder="Fax Number" value="${entity.faxNumber}"
                                            />
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="repName">
                                                Rep Name
                                            </label>
                                            <input type="text" id="repName" class="form-control repName"
                                                   name="repName" placeholder="Rep Name" value="${entity.repName}"
                                            />
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="repPhoneNumber">
                                                Rep Phone Number
                                            </label>
                                            <input type="text" id="repPhoneNumber" class="form-control repPhoneNumber"
                                                   name="repPhoneNumber" placeholder="Rep Phone Number" value="${entity.repPhoneNumber}"
                                            />
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

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="contact">
                                                Contact
                                            </label>
                                            <input type="text" id="contact" class="form-control contact"
                                                   name="contact" placeholder="Contact" value="${entity.contact}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="contactDob">
                                                Contact Dob
                                            </label>
                                            <input type="text" id="contactDob" class="form-control contactDob"
                                                   name="contactDob" placeholder="Contact Dob" value="${entity.contactDob}"
                                            />
                                        </div>
                                    </div>
                                </div>


                                <input type="hidden" name="status" value="1">
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
%{--<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>--}%
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>

<script>

    $(function () {
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
    });

    $(document).ready(function () {
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
    });

    function setTwoNumberDecimal(event) {
        console.log(this.value)
        this.value = parseFloat(this.value.toFixed(2));
    }

    $('.pinCode').select2({
        placeholder: 'Select an item',
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
                //$('.cityId').val(response.id).change();
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
    $(document).ready(function() {
        $("#entityRegisterForm").validate();
    });
    // $("#entityRegisterForm").submit(function(event) {
    //     var pincode =  $('.pinCode option').length;
    //     if(pincode === 0 || pincode < 0)
    //     {
    //         swal("Please enter  pincode and  select area");
    //         event.preventDefault();
    //     }
    // });
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>