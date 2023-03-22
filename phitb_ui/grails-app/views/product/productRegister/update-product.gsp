<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Update Product Register</title>
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
    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css"/>


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
                    <h2>Update Products</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/product">Product</a></li>
                        <li class="breadcrumb-item active">Update Products</li>
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
                        <form action="/product/update/${product.id}" id="form_validation" method="POST" role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="productCode">
                                                Product Code
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="productCode" class="form-control productCode"
                                                   name="productCode"
                                                   placeholder="Product Code" value="${product.productCode}"
                                                   required/>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="productName">
                                                Product Name
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="productName" class="form-control productName"
                                                   name="productName"
                                                   placeholder="Product Name" value="${product.productName}"
                                                   required/>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="manufacturerId">
                                                Manufacturer
                                            </label>
                                            <select class="form-control show-tick manufacturerId"
                                                    name="manufacturerId" id="manufacturerId" style="border: 0">
                                                <option value="0">Please Select</option>
                                                <g:each var="c" in="${manufacturerList}">
                                                    <option value="${c.id}"  <g:if test="${c.id == product.manufacturerId}">selected</g:if>>${c.entityName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-md-6 form-group">
                                            <label for="mktCompanyId">
                                                Marketing Company
                                            </label>
                                            <select class="form-control show-tick mktCompanyId" name="mktCompanyId"
                                                    id="mktCompanyId" style="border: 0;">
                                                <option value="0">Please Select</option>
                                                <g:each var="c" in="${companyList}" >
                                                    <option value="${c.id}" <g:if test="${c.id == product.mktCompanyId}">selected</g:if>>${c.entityName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="rackId">
                                                Rack
                                            </label>
                                            <select class="form-control show-tick rackId" name="rackId" id="rackId">
                                                <option value="0">Please Select</option>
                                                <g:each var="r" in="${racks}">
                                                    <option value="${r?.id}"
                                                            <g:if test="${r?.id == product?.rackId}">selected</g:if>>${r.rackName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="division">
                                                Division
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <select class="form-control show-tick division" name="division"
                                                    id="division" required>
                                                <option value="">Please Select</option>
                                                <g:each var="d" in="${divisions}">
                                                    <option value="${d?.id}"  <g:if test="${d?.id ==
                                                            product?.division?.id}">selected</g:if>>${d?.divisionName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="ml-1"></div>
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;    padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-md-6 form-group  form-float">
                                            <label for="composition">
                                                Composition
                                            </label>

                                            <select class="form-control show-tick composition" name="composition"
                                                    id="composition">
                                                <option value="0">Please Select</option>
                                                <g:each var="c" in="${compositions}">
                                                    <option value="${c?.id}" <g:if test="${c?.id ==
                                                            product?.composition?.id}">selected</g:if>>${c?.compositionName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="costRange">
                                                Cost Range
                                            </label>
                                            <select class="form-control show-tick costRange" name="costRange" id="costRange">
                                                <g:each var="c" in="${productcost}">
                                                    <option value="${c?.id}" <g:if test="${c?.id ==
                                                            product?.costRange?.id}">selected</g:if>>${c?.priceType}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="productType">
                                                Product Type
                                            </label>
                                            <select class="form-control show-tick productType" name="productType"
                                                    id="productType">
                                                <g:each var="c" in="${productTypes}">
                                                    <option value="${c?.id}" <g:if test="${c?.id ==
                                                            product?.productType?.id}">selected</g:if>>${c?.productType}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="unit">
                                                Unit
                                            </label>
                                            <select class="form-control show-tick unit" name="unit" id="unit">
                                                <g:each var="u" in="${unittype}">
                                                    <option value="${u.id}" <g:if test="${u.id == product.unit.id}">selected</g:if>>${u.unitName}</option>
                                                </g:each>
                                            </select>

                                        </div>
                                        <div class="col-lg-12 form-group  form-float">
                                            <label for="unitPacking">
                                                Unit Packing
                                            </label>
                                            <input type="text" id="unitPacking" class="form-control unitPacking"
                                                   name="unitPacking" value="${product?.unitPacking}"
                                                   placeholder="Unit Packing"/>
                                        </div>
                                        %{--                                        <div class="col-lg-6 form-group  form-float">--}%
                                        %{--                                            <label for="productMoo">--}%
                                        %{--                                                Product MOQ--}%
                                        %{--                                            </label>--}%
                                        %{--                                            <input type="number" id="productMoo" class="form-control productMoo"--}%
                                        %{--                                                   name="productMoo"--}%
                                        %{--                                                   placeholder="Product MOQ"/>--}%
                                        %{--                                        </div>--}%
                                    </div>
                                </div>



                                <div class="col-md-6 mt-2" style="max-width: 49%;border: 1px solid black;
                                border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="perLotQuantity">
                                                Per Lot quantity
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="number" id="perLotQuantity" class="form-control perLotQuantity"
                                                   name="perLotQuantity" value="${product.perLotQuantity}"
                                                   placeholder="Per Lot Quantity"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="purchaseRate">
                                                Purchase Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="purchaseRate" class="form-control purchaseRate"
                                                   name="purchaseRate" onblur="setTwoNumberDecimal" step="0.25" value="${product.purchaseRate}" placeholder="Purchase Rate" required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="purchaseTradeDiscount">
                                                Purchase Trade Discount
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="purchaseTradeDiscount" onblur="setTwoNumberDecimal"
                                                   class="form-control purchaseTradeDiscount" name="purchaseTradeDiscount"
                                                   placeholder="Purchase Trade Discount" step="0.25" value="${product.purchaseTradeDiscount}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="purchaseMarginPercent">
                                                Purchase Margin percent
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="purchaseMarginPercent" onblur="setTwoNumberDecimal"
                                                   class="form-control purchaseMarginPercent" name="purchaseMarginPercent"
                                                   placeholder="Purchase Margin Percent" step="0.25" value="${product.purchaseMarginPercent}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="saleRate">
                                                Sale Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="saleRate" onblur="setTwoNumberDecimal" class="form-control saleRate" name="saleRate" placeholder="Sale Rate" step="0.25" value="${product.saleRate}" required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="saleTradeDiscount">
                                                Sale Trade Discount
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="saleTradeDiscount" onblur="setTwoNumberDecimal"
                                                   class="form-control saleTradeDiscount" name="saleTradeDiscount"
                                                   placeholder="Sale Trade Discount" step="0.25" value="${product.saleTradeDiscount}"
                                                   required/>
                                        </div>
                                      %{--  <div class="col-lg-6 form-group  form-float">
                                            <label for="saleMarginPercent">
                                                Sale Margin Percent
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>--}%
                                            <input type="hidden" id="saleMarginPercent" onblur="setTwoNumberDecimal"
                                                   class="form-control saleTradeDiscount" name="saleMarginPercent"
                                                   placeholder="Sale Margin Percent" step="0.25" value="${product.saleMarginPercent}"
                                                   required/>
%{--                                        </div>--}%
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="vipPRate">
                                                VIP Purchase Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="vipPRate" onblur="setTwoNumberDecimal"
                                                   class="form-control vipPRate" name="vipPRate"
                                                   placeholder="vipPRate" step="0.25" value="${product.vipPRate}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="vipSRate">
                                                VIP Sale Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="vipSRate" onblur="setTwoNumberDecimal"
                                                   class="form-control vipPRate" name="vipSRate"
                                                   placeholder="vipSRate" step="0.25" value="${product.vipSRate}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="mrp">
                                                MRP
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="mrp" onblur="setTwoNumberDecimal"
                                                   class="form-control mrp" name="mrp"
                                                   placeholder="MRP" step="0.25" value="${product.mrp}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="ptr">
                                                PTR
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="ptr" onblur="setTwoNumberDecimal"
                                                   class="form-control ptr" name="ptr"
                                                   placeholder="PTR" step="0.25" value="${product.ptr}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="restrictedRate">
                                                Restricted Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="text" id="restrictedRate" onblur="setTwoNumberDecimal"
                                                   class="form-control ptr" name="restrictedRate"
                                                   placeholder="Restricted Rate" step="0.25" value="${product.restrictedRate}"
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
                                            <label for="nriRate">
                                                NRI Rate
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="number" id="nriRate" onblur="setTwoNumberDecimal"
                                                   class="form-control ptr" name="nriRate"
                                                   placeholder="NRI Rate" step="0.25" value="${product.nriRate}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="salesmanCommission">
                                                Salesman Commission
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="number" id="salesmanCommission" onblur="setTwoNumberDecimal"
                                                   class="form-control salesmanCommission" name="salesmanCommission"
                                                   placeholder="Salesman Commission" step="0.25" value="${product.salesmanCommission}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="grossProfitPercentage">
                                                Gross Profit Percentage
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <input type="number" id="grossProfitPercentage" onblur="setTwoNumberDecimal"
                                                   class="form-control grossProfitPercentage" name="grossProfitPercentage"
                                                   placeholder="Gross Profit Percentage" step="0.25" value="${product.grossProfitPercentage}"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="taxId">
                                                Tax
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <select class="form-control show-tick taxId" name="taxId" id="taxId">
                                                <g:each var="t" in="${tax}">
                                                    <option value="${t.id}"
                                                            <g:if test="${t.id == product.taxId}">selected</g:if>>${t.taxName}  (${t.taxValue}%)</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="saleType">
                                                Sale Type
                                                <span class="required-indicator" style="color: red;">*</span>
                                            </label>
                                            <select class="form-control show-tick saleType" name="saleType" id="saleType">
                                                <option value="${Constants.SALEABLE}" <g:if test="${product.saleType == Constants.SALEABLE}">selected</g:if>>SALEABLE</option>
                                                <option value="${Constants.SAMPLE}" <g:if test="${product.saleType == Constants.SAMPLE}">selected</g:if>>SAMPLE</option>
                                                <option value="${Constants.PROMOTIONAL}" <g:if test="${product.saleType == Constants.PROMOTIONAL}">selected</g:if>>PROMOTIONAL</option>
                                            </select>
                                        </div>
%{--                                        <div class="col-lg-6 form-group  form-float">--}%
%{--                                            <label for="thresholdLevel">--}%
%{--                                                Threshold Level--}%
%{--                                            </label>--}%
%{--                                            <input type="text" id="thresholdLevel"--}%
%{--                                                   class="form-control thresholdLevel" name="thresholdLevel" value="${product.thresholdLevel}"--}%
%{--                                                   placeholder="Threshold Level"--}%
%{--                                            />--}%
%{--                                        </div>--}%
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="orderQuantity">
                                                Order Quantity
                                            </label>
                                            <input type="number" id="orderQuantity"
                                                   class="form-control orderQuantity" name="orderQuantity"
                                                   placeholder="Order Quantity" value="${product.orderQuantity}"
                                            />
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="group">
                                                Product Group
                                            </label>
                                            <select class="form-control show-tick group" name="group" id="group">
                                                <g:each var="c" in="${productGroups}">
                                                    <option value="${c?.id}" <g:if test="${c?.id ==
                                                            product?.schedule?.id}">selected</g:if>>${c?.groupName}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="schedule">
                                                Product Schedule
                                            </label>
                                            <select class="form-control show-tick schedule" name="schedule" id="schedule">
                                                <g:each var="c" in="${productSchedules}">
                                                    <option value="${c?.id}">${c?.scheduleCode}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="category1">
                                                Product Category
                                            </label>
                                            <select class="form-control show-tick category1" name="category" id="category1">
                                                <g:each var="c" in="${productCategories}">
                                                    <option value="${c?.id}" <g:if test="${c?.id ==
                                                            product?.category?.id}">selected</g:if>>${c?.categoryName}</option>
                                                </g:each>
                                            </select>
                                        </div>
%{--                                        <div class="col-lg-6 form-group  form-float">--}%
%{--                                            <label for="sendMail">--}%
%{--                                                Send Mail--}%
%{--                                            </label>--}%
%{--                                            <select class="form-control show-tick sendMail" name="sendMail" id="sendMail">--}%
%{--                                                <option value="1" <g:if--}%
%{--                                                        test="${product.sendMail == "1"}">selected</g:if> >YES</option>--}%
%{--                                                <option value="0" <g:if test="${product.sendMail == "0"}">selected</g:if>>NO</option>--}%
%{--                                            </select>--}%
%{--                                        </div>--}%
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="discountAllowed">
                                                Discount Allowed
                                            </label>
                                            <select class="form-control show-tick discountAllowed"
                                                    name="discountAllowed" id="discountAllowed">
                                                <option value="1"
                                                        <g:if
                                                                test="${product?.discountAllowed == "1"}">selected</g:if>>YES</option>
                                                <option value="0"
                                                        <g:if
                                                                test="${product?.discountAllowed == "0"}">selected</g:if>>NO</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="ccmProduct">
                                                CCM Product
                                            </label>
                                            <select class="form-control show-tick ccmProduct" name="ccmProduct" id="ccmProduct">
                                                <option value="1" <g:if test="${product.ccmProduct == "1"}">selected</g:if>>YES</option>
                                                <option value="0" <g:if test="${product.ccmProduct == "0"}">selected</g:if>>NO</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 mt-2" style="max-width: 99%;border: 1px solid black;
                                border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="hsnCode">
                                                HSN Code
                                            </label>
                                            <input type="text" id="hsnCode" class="form-control hsnCode" name="hsnCode"
                                                   placeholder="HSN Code" value="${product.hsnCode}"
                                            />
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="salesmenPercent">
                                              Salesman Percent
                                                <span class="required-indicator" style="color: red;">*</span>

                                            </label>
                                            <input type="number"
                                                   class="form-control salesmenPercent" id="salesmenPercent" name="salesmenPercent"
                                                   onblur="setTwoNumberDecimal()" step="0.25"
                                                   placeholder="Salesman Percent" value="${product?.salesmenPercent}"
                                                   />
                                        </div>
                                        %{-- <div class="col-lg-6 form-group  form-float">
                                             <label for="soundexCode">
                                                 Soundex Code
                                             </label>
                                             <input type="text" id="soundexCode"
                                                    class="form-control soundexCode" name="soundexCode"
                                                    placeholder="Soundex Code"
                                                    required/>
                                         </div>--}%
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="narration">
                                                Narration
                                            </label>
                                            <textarea type="text" id="narration"
                                                      class="form-control narration" name="narration"
                                                      placeholder="Narration"
                                                      >${product.narration}</textarea>
                                        </div>
%{--                                        <div class="col-lg-6 form-group  form-float">--}%
%{--                                            <label for="restrictedAssignment">--}%
%{--                                                Restricted Assignment--}%
%{--                                            </label>--}%
%{--                                            <input type="text" id="restrictedAssignment"--}%
%{--                                                   class="form-control restrictedAssignment" name="restrictedAssignment"--}%
%{--                                                   placeholder="Restricted Assignment" value="${product.restrictedAssignment}"--}%
%{--                                                   required/>--}%
%{--                                        </div>--}%
                                        %{-- <div class="col-lg-6 form-group  form-float">
                                             <label for="entityType">
                                                 Entity Type
                                             </label>
                                             <select class="form-control show-tick entityType" name="entityTypeId"
                                                     id="entityType">
                                                 <g:each var="et" in="${entitytype}">
                                                     <option value="${et.id}">${et.name}</option>
                                                 </g:each>
                                             </select>
                                         </div>
                                         <div class="col-lg-6 form-group  form-float">
                                             <label for="entity">
                                                 Entity
                                             </label>
                                             <select class="form-control show-tick entity" name="entityId" id="entity">
                                                 <g:each var="e" in="${entity}">
                                                     <option value="${e.id}">${e.entityName}</option>
                                                 </g:each>
                                             </select>
                                         </div>--}%
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
<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>

<script>

    $(function () {

        $('#manufacturerId').select2()
        $('#mktCompanyId').select2()
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
        this.value = parseFloat(this.value.toFixed(2));
    }

</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>