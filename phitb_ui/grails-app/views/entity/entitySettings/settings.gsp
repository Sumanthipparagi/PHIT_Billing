<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Entity Settings</title>
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
                    <h2>Entity Settings</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Entity Settings</li>
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
                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>

                <div class="card">
                    <div class="header">
                        <h2>Settings</h2>
                    </div>

                    <div class="body">
                        <form action="/entity-settings" method="post" id="updateEntitySettings">
                            <g:if test="${entitySettings.size() != 0}">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Generation Methods</b>
                                        <select class="form-control show-tick" name="igm">
                                            <option value="${Constants.SINGLE_INV_ENTIRE_ENTITY}"
                                                    <g:if test="${entitySettings.IGM == Constants.SINGLE_INV_ENTIRE_ENTITY}">selected</g:if>>Single Invoice
                                            for
                                            EntireEntity</option>
                                            <option value="${Constants.SEPARATE_INV_EACH_DIVISION}"
                                                    <g:if test="${entitySettings.IGM == Constants.SEPARATE_INV_EACH_DIVISION}">selected</g:if>>Separate Invoice for Each Division</option>
                                            <option value="${Constants.SEPARATE_INV_EACH_FLOOR}"
                                                    <g:if test="${entitySettings.IGM == Constants.SEPARATE_INV_EACH_FLOOR}">selected</g:if>>Separate Invoice for Each
                                            Floor</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Default Sales Invoice Series</b>
                                        <select class="form-control show-tick" name="dsi">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.DSI == Constants.YES}">selected</g:if>>Yes</option>
                                            <option value="${Constants.NO}"
                                                    <g:if test="${entitySettings.DSI == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Print Grouping</b>
                                        <select class="form-control show-tick" name="ipg">
                                            <option value="${Constants.DIVISION_WISE}"
                                                    <g:if test="${entitySettings.IPG ==
                                                            Constants.DIVISION_WISE}">selected</g:if>>Division
                                            Wise</option>
                                            <option value="${Constants.TAX_WISE}"
                                                    <g:if test="${entitySettings.IPG == Constants.TAX_WISE}">selected</g:if>>Tax wise</option>
                                            <option value="${Constants.PRODUCT_GROUPING}"
                                                    <g:if test="${entitySettings.IPG ==
                                                            Constants.PRODUCT_GROUPING}">selected</g:if>>Product Grouping</option>
                                            <option value="${Constants.CATEGORY}" <g:if
                                                    test="${entitySettings.IPG == Constants.CATEGORY}">selected</g:if>>Category</option>
                                            <option value="${Constants.SCHEDULE}" <g:if
                                                    test="${entitySettings.IPG == Constants.SCHEDULE}">selected</g:if>>Schedule</option>
                                            <option value="${Constants.FLOOR_WISE}" <g:if
                                                    test="${entitySettings.IPG == Constants.FLOOR_WISE}">selected</g:if>>Floorwise</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Print Sorting</b>
                                        <select class="form-control show-tick" name="ips">
                                            <option value="${Constants.ALPHABETIC}" <g:if
                                                    test="${entitySettings.IPS == Constants.ALPHABETIC}">selected</g:if>>
                                                Alphabetic</option>
                                            <option value="${Constants.TAX_WISE}" <g:if
                                                    test="${entitySettings.IPS == Constants.TAX_WISE}">selected</g:if>>Tax
                                            wise</option>
                                            <option value="${Constants.FLOOR_RACK}" <g:if
                                                    test="${entitySettings.IPS == Constants.FLOOR_RACK}">selected</g:if>>Floor
                                            -Rack</option>
                                            <option value="${Constants.RACK_ONLY}" <g:if
                                                    test="${entitySettings.IPS == Constants.RACK_ONLY}">selected</g:if>>Rack Only</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Advance Cheque Mandatory</b>
                                        <select class="form-control show-tick" name="acm">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.ACM == Constants.YES}">selected</g:if>>Yes</option>
                                            <option value="${Constants.NO}" <g:if
                                                    test="${entitySettings.ACM == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Utilize Local or Universal Barcode</b>
                                        <select class="form-control show-tick" name="ulub">
                                            <option value="${Constants.LOCAL}" <g:if
                                                    test="${entitySettings.ULUB == Constants.LOCAL}">selected</g:if>>Local
                                            </option>
                                            <option value="${Constants.UNIVERSAL}" <g:if
                                                    test="${entitySettings.ULUB == Constants.UNIVERSAL}">selected</g:if>>Universal
                                            </option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Max Utilization of a Single Cheque (For Invoices)</b>
                                        <select class="form-control show-tick" name="accmu">
                                            <option value="0" <g:if
                                                    test="${entitySettings.ACCMU == "0"}">selected</g:if>>0</option>
                                            <option value="NULL" <g:if
                                                    test="${entitySettings.ACCMU == "NULL"}">selected</g:if>>Null</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Credit Limit Management</b>
                                        <select class="form-control show-tick" name="clm">
                                            <option value="${Constants.SINGLE_LIMIT_ENTIRE_ENTITY}" <g:if
                                                    test="${entitySettings.CLM == Constants.SINGLE_LIMIT_ENTIRE_ENTITY}">selected</g:if>>Single Limit Entire
                                            Entity</option>
                                            <option value="${Constants.INDIVIDUAL_LIMIT_EACH_DIVISION}" <g:if
                                                    test="${entitySettings.CLM == Constants.INDIVIDUAL_LIMIT_EACH_DIVISION}">selected</g:if>>Individual Limit for each Division</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Allow Same Batch to be repeated in a Single Sales Invoice?</b>
                                        <select class="form-control show-tick" name="asb">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.ASB == Constants.YES}">selected</g:if>>Yes</option>
                                            <option value="${Constants.NO}" <g:if
                                                    test="${entitySettings.ASB == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Round Off the Scheme Quantity?</b>
                                        <select class="form-control show-tick" name="ro">
                                            <option value="${Constants.HIGHER_SCHEME}" <g:if
                                                    test="${entitySettings.RO == Constants.HIGHER_SCHEME}">selected</g:if>>Higher Scheme
                                            (Loss)</option>
                                            <option value="${Constants.LOWER_SCHEME}" <g:if
                                                    test="${entitySettings.RO == Constants.LOWER_SCHEME}">selected</g:if>>Lower Scheme Auto Adjust (Profitable)
                                            </option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Excess/Shortage stocks rounded off to be autoadjusted in Next Invoice?</b>
                                        <select class="form-control show-tick" name="essr">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.ESSR == Constants.YES}">selected</g:if>>Yes
                                            </option>
                                            <option value="${Constants.NO}" <g:if
                                                    test="${entitySettings.ESSR == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>How to Apply Scheme?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="${Constants.FOLLOW_SCHEME_CONFIGURATOR}" <g:if
                                                    test="${entitySettings.AS == Constants.FOLLOW_SCHEME_CONFIGURATOR}">selected</g:if>>Follow Scheme
                                            Configurator (Recommended)</option>
                                            <option value="${Constants.MANUAL_ENTRY}" <g:if
                                                    test="${entitySettings.AS == Constants.MANUAL_ENTRY}">selected</g:if>>Manual Entry</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Round off Total Net Value to ?</b>
                                        <select class="form-control show-tick" name="ron">
                                            <option value="${Constants.REMAINS_SAME}" <g:if
                                                    test="${entitySettings.RON == Constants.REMAINS_SAME}">selected</g:if>>Remains Same (Recommended)</option>
                                            <option value="${Constants.NEXT_INTEGER_VALUE}" <g:if
                                                    test="${entitySettings.RON == Constants.NEXT_INTEGER_VALUE}">selected</g:if>>Next Integer Value</option>
                                            <option value="${Constants.PREVIOUS_INTEGER_VALUE}" <g:if
                                                    test="${entitySettings.RON == Constants.PREVIOUS_INTEGER_VALUE}">selected</g:if>>Previous Interger Value</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Can the Invoice Value be Zero?</b>
                                        <select class="form-control show-tick" name="ValZero">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.ValZERO == Constants.YES}">selected</g:if>>Yes
                                            </option>
                                            <option value="${Constants.NO}" <g:if
                                                    test="${entitySettings.ValZERO == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Apply Transportation Chanrges to Customer? (Paid Amt.)</b>
                                        <select class="form-control show-tick" name="transportationCharges">
                                            <option value="${Constants.YES}" <g:if
                                                    test="${entitySettings.transportationCharges == Constants.YES}">selected</g:if>>Yes</option>
                                            <option value="${Constants.NO}" <g:if
                                                    test="${entitySettings.transportationCharges == Constants.NO}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                    <input type="hidden" name="entityId" value="${params.id}"/>

                                </div>
                            </g:if>
                            <g:else>
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Generation Methods</b>
                                        <select class="form-control show-tick" name="igm">
                                            <option value="${Constants.SINGLE_INV_ENTIRE_ENTITY}">Single Invoice for EntireEntity</option>
                                            <option value="${Constants.SEPARATE_INV_EACH_DIVISION}">Separate Invoice for Each Division</option>
                                            <option value="${Constants.SEPARATE_INV_EACH_FLOOR}">Separate Invoice for Each
                                            Floor</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Default Sales Invoice Series</b>
                                        <select class="form-control show-tick" name="dsi">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Print Grouping</b>
                                        <select class="form-control show-tick" name="ipg">
                                            <option value="${Constants.DIVISION_WISE}">Division Wise</option>
                                            <option value="${Constants.TAX_WISE}">Tax wise</option>
                                            <option value="${Constants.PRODUCT_GROUPING}">Product Grouping</option>
                                            <option value="${Constants.CATEGORY}">Category</option>
                                            <option value="${Constants.SCHEDULE}">Schedule</option>
                                            <option value="${Constants.FLOOR_WISE}">Floorwise</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Invoice Print Sorting</b>
                                        <select class="form-control show-tick" name="ips">
                                            <option value="${Constants.ALPHABETIC}">Alphabetic</option>
                                            <option value="${Constants.TAX_WISE}">Tax wise</option>
                                            <option value="${Constants.FLOOR_RACK}">Floor-Rack</option>
                                            <option value="${Constants.RACK_ONLY}">Rack Only</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Advance Cheque Mandatory</b>
                                        <select class="form-control show-tick" name="acm">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Utilize Local or Universal Barcode</b>
                                        <select class="form-control show-tick" name="ulub">
                                            <option value="${Constants.LOCAL}">Local</option>
                                            <option value="${Constants.UNIVERSAL}">Universal</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Max Utilization of a Single Cheque (For Invoices)</b>
                                        <select class="form-control show-tick" name="accmu">
                                            <option value="0">0</option>
                                            <option value="NULL">Null</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Credit Limit Management</b>
                                        <select class="form-control show-tick" name="clm">
                                            <option value="${Constants.SINGLE_LIMIT_ENTIRE_ENTITY}">Single Limit Entire Entity</option>
                                            <option value="${Constants.INDIVIDUAL_LIMIT_EACH_DIVISION}">Individual Limit for each Division</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Allow Same Batch to be repeated in a Single Sales Invoice?</b>
                                        <select class="form-control show-tick" name="asb">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Round Off the Scheme Quantity?</b>
                                        <select class="form-control show-tick" name="ro">
                                            <option value="${Constants.HIGHER_SCHEME}">Higher Scheme (Loss)</option>
                                            <option value="${Constants.LOWER_SCHEME}">Lower Scheme Auto Adjust (Profitable)</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Excess/Shortage stocks rounded off to be autoadjusted in Next Invoice?</b>
                                        <select class="form-control show-tick" name="essr">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>How to Apply Scheme?</b>
                                        <select class="form-control show-tick" name="as">
                                            <option value="${Constants.FOLLOW_SCHEME_CONFIGURATOR}">Follow Scheme Configurator (Recommended)</option>
                                            <option value="${Constants.MANUAL_ENTRY}">Manual Entry</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Round off Total Net Value to ?</b>
                                        <select class="form-control show-tick" name="ron">
                                            <option value="${Constants.NEXT_INTEGER_VALUE}">Next Integer Value</option>
                                            <option value="${Constants.PREVIOUS_INTEGER_VALUE}">Previous Interger Value</option>
                                            <option value="${Constants.REMAINS_SAME}">Remains Same (Recommended)</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Can the Invoice Value be Zero?</b>
                                        <select class="form-control show-tick" name="ValZero">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Apply Transportation Chanrges to Customer? (Paid Amt.)</b>
                                        <select class="form-control show-tick" name="transportationCharges">
                                            <option value="${Constants.YES}">Yes</option>
                                            <option value="${Constants.NO}">No</option>
                                        </select>
                                    </div>
                                    <input type="hidden" name="entityId" value="${params.id}"/>

                                </div>
                            </g:else>
                            <button type="submit" class="btn btn-default btn-round waves-effect"
                                    style="background-color: green;"><font
                                    style="vertical-align: inherit;"><font
                                        style="vertical-align: inherit;">SUBMIT</font></font></button>
                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="header">
                        <h2>Configuration</h2>
                    </div>

                    <div class="body">
                        <form action="/entity-config" method="post" id="updateEntityConfig">
                            <g:if test="${entityConfigs.size() != 0}">
                                <table id="entityConfig"
                                       class="table table-bordered w-100 display pb-30"
                                       cellspacing="0" width="100%">
                                    <thead>
                                    <tr>
                                        <th>Entity Config</th>
                                        <th>Purchase Order (Products)</th>
                                        <th>Purchase Entry (Products)</th>
                                        <th>Purchase Return</th>
                                        <th>Payments</th>
                                        <th>Sale Order (Products)</th>
                                        <th>Sale Entry Invoice (Products)</th>
                                        <th>Sales Return</th>
                                        <th>Receipts</th>
                                        <th>Credit JV</th>
                                        <th>Debit JV</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <td><strong class="dateEditable">Date Editable</strong></td>
                                    <td><strong><input type="checkbox" name="dateEditablePO" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.purchaseOrder == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePE" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.purchaseEntry == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePR" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.purchaseReturn == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePAT" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.payments == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSO" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.saleOrder == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSEI" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.saleEntry == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSR" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.salesReturn == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableR" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.recipts == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableCJV" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.creditJv == true}">checked</g:if>></strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableDJV" class="dateEditable" <g:if
                                            test="${entityConfigs.DE.debitJv == true}">checked</g:if>></strong>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="mad">Modify After Dayend</strong></td>
                                        <td><strong><input type="checkbox" name="madPO" class="mad" <g:if
                                                test="${entityConfigs.MAD.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPE" class="mad" <g:if
                                                test="${entityConfigs.MAD.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPR" class="mad" <g:if
                                                test="${entityConfigs.MAD.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPAT" class="mad" <g:if
                                                test="${entityConfigs.MAD.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSO" class="mad" <g:if
                                                test="${entityConfigs.MAD.saleOrder == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSEI" class="mad" <g:if
                                                test="${entityConfigs.MAD.saleEntry == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSR" class="mad" <g:if
                                                test="${entityConfigs.MAD.salesReturn == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madR" class="mad" <g:if
                                                test="${entityConfigs.MAD.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madCJV" class="mad" <g:if
                                                test="${entityConfigs.MAD.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madDJV" class="mad" <g:if
                                                test="${entityConfigs.MAD.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="dad">Delete After Dayend</strong></td>
                                        <td><strong><input type="checkbox" name="dadPO" class="dad" <g:if
                                                test="${entityConfigs.DAD.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPE" class="dad" <g:if
                                                test="${entityConfigs.DAD.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPR" class="dad" <g:if
                                                test="${entityConfigs.DAD.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPAT" class="dad" <g:if
                                                test="${entityConfigs.DAD.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSO" class="dad" <g:if
                                                test="${entityConfigs.DAD.saleOrder == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSEI" class="dad" <g:if
                                                test="${entityConfigs.DAD.saleEntry == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSR" class="dad" <g:if
                                                test="${entityConfigs.DAD.salesReturn == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadR" class="dad" <g:if
                                                test="${entityConfigs.DAD.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadCJV" class="dad" <g:if
                                                test="${entityConfigs.DAD.creditJv == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadCJV" class="dad" <g:if
                                                test="${entityConfigs.DAD.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong
                                                class="block">Block the printing if Payment Terms ADVANCE and CHQ NO Not
                                            Entered</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPO" class="block" <g:if
                                                test="${entityConfigs.BLOCK.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPE" class="block" <g:if
                                                test="${entityConfigs.BLOCK.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPR" class="block" <g:if
                                                test="${entityConfigs.BLOCK.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPAT" class="block" <g:if
                                                test="${entityConfigs.BLOCK.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSO" class="block" <g:if
                                                test="${entityConfigs.BLOCK.saleOrder == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSEI" class="block" <g:if
                                                test="${entityConfigs.BLOCK.saleEntry == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSR" class="block" <g:if
                                                test="${entityConfigs.BLOCK.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockR" class="block" <g:if
                                                test="${entityConfigs.BLOCK.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockCJV" class="block" <g:if
                                                test="${entityConfigs.BLOCK.creditJv == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockDJV" class="block" <g:if
                                                test="${entityConfigs.BLOCK.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="auto">AUTO CRNT/DBNT/CR JV/DB JV ADV ADJUST</strong></td>
                                        <td><strong><input type="checkbox" name="autoPO" class="auto" <g:if
                                                test="${entityConfigs.AUTO.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPE" class="auto" <g:if
                                                test="${entityConfigs.AUTO.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPR" class="auto" <g:if
                                                test="${entityConfigs.AUTO.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPAT" class="auto" <g:if
                                                test="${entityConfigs.AUTO.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSO" class="auto" <g:if
                                                test="${entityConfigs.AUTO.saleOrder == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSEI" class="auto" <g:if
                                                test="${entityConfigs.AUTO.saleEntry == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSR" class="auto" <g:if
                                                test="${entityConfigs.AUTO.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoR" class="auto" <g:if
                                                test="${entityConfigs.AUTO.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoCJV" class="auto" <g:if
                                                test="${entityConfigs.AUTO.creditJv == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoDJV" class="auto" <g:if
                                                test="${entityConfigs.AUTO.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="uploadProof">Upload of Proof Mandatory</strong></td>
                                        <td><strong><input type="checkbox" name="uploadProofPO" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPE" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPR" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPAT" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSO" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSEI" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSR" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofR" class="uploadProof" <g:if
                                                test="${entityConfigs.UPLOAD_PROOF.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofCJV" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofDJV" class="uploadProof"
                                                           <g:if
                                                                   test="${entityConfigs.UPLOAD_PROOF.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td><strong class="blockInv">Block the Invoice if DL No.is expire</strong></td>
                                        <td><strong><input type="checkbox" name="blockInvPO" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPE" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPR" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPAT" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSO" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSEI" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSR" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvR" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvCJV" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvDJV" class="blockInv" <g:if
                                                test="${entityConfigs.BLOCK_INV.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="sendMail">Enable Auto send Mail After Save</strong></td>
                                        <td><strong><input type="checkbox" name="sendMailPO" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPE" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPR" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPAT" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSO" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSEI" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSR" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailR" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailCJV" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailDJV" class="sendMail" <g:if
                                                test="${entityConfigs.SEND_MAIL.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="cde">Credit Days Editable</strong></td>
                                        <td><strong><input type="checkbox" name="cdePO" class="cde" <g:if
                                                test="${entityConfigs.CDE.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePE" class="cde" <g:if
                                                test="${entityConfigs.CDE.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePR" class="cde" <g:if
                                                test="${entityConfigs.CDE.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePAT" class="cde" <g:if
                                                test="${entityConfigs.CDE.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSO" class="cde" <g:if
                                                test="${entityConfigs.CDE.saleOrder == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSEI" class="cde" <g:if
                                                test="${entityConfigs.CDE.saleEntry == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSR" class="cde" <g:if
                                                test="${entityConfigs.CDE.salesReturn == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeR" class="cde" <g:if
                                                test="${entityConfigs.CDE.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeCJV" class="cde" <g:if
                                                test="${entityConfigs.CDE.creditJv == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeDJV" class="cde" <g:if
                                                test="${entityConfigs.CDE.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="discountButton">Enabled Change Discount Button</strong></td>
                                        <td><strong><input type="checkbox" name="discountButtonPO"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPE"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPR"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPAT"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSO"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSEI"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSR"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonR" class="discountButton"
                                                           <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonCJV"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonDJV"
                                                           class="discountButton" <g:if
                                                                   test="${entityConfigs.DISCOUNT_BUTTON.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong class="reCal">Enabled re-Calculation of Rate Button</strong></td>
                                        <td><strong><input type="checkbox" name="reCalPO" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPE" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPR" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPAT" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.payments == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSO" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSEI" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSR" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalR" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.recipts == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalCJV" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.creditJv == true}">checked</g:if>></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalDJV" class="reCal" <g:if
                                                test="${entityConfigs.RE_CAL.debitJv == true}">checked</g:if>></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="disableLR">Disable LR No ad Date Once entered</strong></td>
                                        <td><strong><input type="checkbox" name="disableLRPO" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPE" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPR" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPAT" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSO" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSEI" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSR" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRR" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRCJV" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRDJV" class="disableLR" <g:if
                                                test="${entityConfigs.DISABLE_LR.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="enableCashDisc">Enabled Cash Discount</strong></td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPO"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPE"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPR"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPAT"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSO"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSEI"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSR"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscR" class="enableCashDisc"
                                                           <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscCJV"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscDJV"
                                                           class="enableCashDisc" <g:if
                                                                   test="${entityConfigs.ENABLE_CASH_DISC.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong
                                                class="withoutQr">Allow Printng Without QR Code (B2B) If E- Invoice Enabled</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPO" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPE" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPR" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPAT" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSO" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSEI" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSR" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrR" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrCJV" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrDJV" class="withoutQr" <g:if
                                                test="${entityConfigs.WITHOUT_QR.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong
                                                class="regenNewdoc">Allow Re-Generate New Document and Open in Edit Mode on Canceled
                                            Document</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPO" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPE" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPR" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPAT" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSO" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSEI" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSR" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocR" class="regenNewdoc" <g:if
                                                test="${entityConfigs.REGEN_NEW_DOC.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocCJV" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocDJV" class="regenNewdoc"
                                                           <g:if
                                                                   test="${entityConfigs.REGEN_NEW_DOC.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong class="newBatchCreation">Allow New Batch Creation Option</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPO"
                                                           class="newBatchCreation"
                                                           <g:if test="${entityConfigs.NEW_BATCH_CREATION.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPE"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPR"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPAT"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSO"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSEI"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSR"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationR"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationCJV"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationDJV"
                                                           class="newBatchCreation" <g:if
                                                                   test="${entityConfigs.NEW_BATCH_CREATION.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong class="disableSchemeQty">Disable Scheme Qty in Credit Note</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPO"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPE"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPR"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPAT"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySO"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySEI"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySR"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyR"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyCJV"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyDJV"
                                                           class="disableSchemeQty" <g:if
                                                                   test="${entityConfigs.DISABLE_SCHEME_QTY.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="allowManual">Allow Manual Selection of Tax%</strong></td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPO" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPE" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPR" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPAT" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSO" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSEI" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSR" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxR" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxCJV" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxDJV" class="allowManual"
                                                           <g:if
                                                                   test="${entityConfigs.ALLOW_MANUAL.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="prescriptionUpload">Prescription Upload Mandatory</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPO"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.purchaseOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPE"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.purchaseEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPR"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.purchaseReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPAT"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.payments == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSO"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.saleOrder == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSEI"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.saleEntry == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSR"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.salesReturn == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadR"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.recipts == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadCJV"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.creditJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadDJV"
                                                           class="prescriptionUpload" <g:if
                                                                   test="${entityConfigs.PRESCRIPTION_UPLOAD.debitJv == true}">checked</g:if>>
                                        </strong>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </g:if>
                            <g:else>
                                <table id="entityConfig"
                                       class="table table-bordered w-100 display pb-30"
                                       cellspacing="0" width="100%">
                                    <thead>
                                    <tr>
                                        <th>Entity Config</th>
                                        <th>Purchase Order (Products)</th>
                                        <th>Purchase Entry (Products)</th>
                                        <th>Purchase Return</th>
                                        <th>Payments</th>
                                        <th>Sale Order (Products)</th>
                                        <th>Sale Entry Invoice (Products)</th>
                                        <th>Sales Return</th>
                                        <th>Receipts</th>
                                        <th>Credit JV</th>
                                        <th>Debit JV</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <td><strong class="dateEditable">Date Editable</strong></td>
                                    <td><strong><input type="checkbox" name="dateEditablePO" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePE" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePR" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditablePAT" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSO" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSEI" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableSR" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableR" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableCJV" class="dateEditable">
                                    </strong>
                                    </td>
                                    <td><strong><input type="checkbox" name="dateEditableDJV" class="dateEditable">
                                    </strong>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="mad">Modify After Dayend</strong></td>
                                        <td><strong><input type="checkbox" name="madPO" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPE" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPR" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madPAT" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSO" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSEI" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madSR" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madR" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madCJV" class="mad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="madDJV" class="mad"></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="dad">Delete After Dayend</strong></td>
                                        <td><strong><input type="checkbox" name="dadPO" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPE" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPR" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadPAT" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSO" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSEI" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadSR" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadR" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadCJV" class="dad"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="dadCJV" class="dad"></strong>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><strong
                                                class="block">Block the printing if Payment Terms ADVANCE and CHQ NO Not
                                            Entered</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPO" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPE" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPR" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockPAT" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSO" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSEI" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockSR" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockR" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockCJV" class="block"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockDJV" class="block"></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="auto">AUTO CRNT/DBNT/CR JV/DB JV ADV ADJUST</strong></td>
                                        <td><strong><input type="checkbox" name="autoPO" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPE" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPR" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoPAT" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSO" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSEI" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoSR" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoR" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoCJV" class="auto"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="autoDJV" class="auto"></strong>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td><strong class="uploadProof">Upload of Proof Mandatory</strong></td>
                                        <td><strong><input type="checkbox" name="uploadProofPO" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPE" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPR" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofPAT" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSO" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSEI" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofSR" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofR" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofCJV" class="uploadProof">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="uploadProofDJV" class="uploadProof">
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td><strong class="blockInv">Block the Invoice if DL No.is expire</strong></td>
                                        <td><strong><input type="checkbox" name="blockInvPO" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPE" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPR" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvPAT" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSO" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSEI" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvSR" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvR" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvCJV" class="blockInv"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="blockInvDJV" class="blockInv"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="sendMail">Enable Auto send Mail After Save</strong></td>
                                        <td><strong><input type="checkbox" name="sendMailPO" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPE" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPR" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailPAT" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSO" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSEI" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailSR" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailR" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailCJV" class="sendMail"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="sendMailDJV" class="sendMail"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="cde">Credit Days Editable</strong></td>
                                        <td><strong><input type="checkbox" name="cdePO" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePE" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePR" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdePAT" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSO" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSEI" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeSR" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeR" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeCJV" class="cde"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="cdeDJV" class="cde"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="discountButton">Enabled Change Discount Button</strong></td>
                                        <td><strong><input type="checkbox" name="discountButtonPO"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPE"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPR"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonPAT"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSO"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSEI"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonSR"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonR"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonCJV"
                                                           class="discountButton"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="discountButtonDJV"
                                                           class="discountButton"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="reCal">Enabled re-Calculation of Rate Button</strong></td>
                                        <td><strong><input type="checkbox" name="reCalPO" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPE" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPR" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalPAT" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSO" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSEI" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalSR" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalR" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalCJV" class="reCal"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="reCalDJV" class="reCal"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="disableLR">Disable LR No ad Date Once entered</strong></td>
                                        <td><strong><input type="checkbox" name="disableLRPO" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPE" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPR" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRPAT" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSO" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSEI" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRSR" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRR" class="disableLR"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRCJV" class="disableLR">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableLRDJV" class="disableLR">
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="enableCashDisc">Enabled Cash Discount</strong></td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPO"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPE"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPR"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscPAT"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSO"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSEI"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscSR"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscR"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscCJV"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="enableCashDiscDJV"
                                                           class="enableCashDisc"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong
                                                class="withoutQr">Allow Printng Without QR Code (B2B) If E- Invoice Enabled</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPO" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPE" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPR" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrPAT" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSO" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSEI" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrSR" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrR" class="withoutQr"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrCJV" class="withoutQr">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="withoutQrDJV" class="withoutQr">
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong
                                                class="regenNewdoc">Allow Re-Generate New Document and Open in Edit Mode on Canceled
                                            Document</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPO" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPE" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPR" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocPAT" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSO" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSEI" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocSR" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocR" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocCJV" class="regenNewdoc">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="regenNewdocDJV" class="regenNewdoc">
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="newBatchCreation">Allow New Batch Creation Option</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPO"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPE"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPR"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationPAT"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSO"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSEI"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationSR"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationR"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationCJV"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="newBatchCreationDJV"
                                                           class="newBatchCreation"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="disableSchemeQty">Disable Scheme Qty in Credit Note</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPO"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPE"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPR"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyPAT"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySO"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySEI"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtySR"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyR"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyCJV"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="disableSchemeQtyDJV"
                                                           class="disableSchemeQty"></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="allowManual">Allow Manual Selection of Tax%</strong></td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPO" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPE" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPR" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxPAT" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSO" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSEI" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxSR" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxR" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxCJV" class="allowManual">
                                        </strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="allowManualTaxDJV" class="allowManual">
                                        </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong class="prescriptionUpload">Prescription Upload Mandatory</strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPO"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPE"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPR"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadPAT"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSO"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSEI"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadSR"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadR"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadCJV"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                        <td><strong><input type="checkbox" name="prescriptionUploadDJV"
                                                           class="prescriptionUpload"></strong>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </g:else>

                            <button type="submit" class="btn btn-default btn-round waves-effect"><font
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

<script>


    $('#updateEntitySettings').submit(function (event) {
        var formData = $(this);
        var beforeSendSwal;
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            beforeSend: function () {
                beforeSendSwal = Swal.fire({
                    // title: "Loading",
                    html: '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                    showDenyButton: false,
                    showCancelButton: false,
                    showConfirmButton: false,
                    allowOutsideClick: false,
                    background: 'transparent'
                });
            },
            success: function (data) {
                Swal.fire({
                    icon: 'success',
                    title: 'Saved Successfully',
                    showConfirmButton: true,
                });
                window.location = "/entity-settings";
            },
            error: function (data) {
                console.log("Failed");
                Swal.fire('error', 'update Failed', data.responseText);
            }
        });
        event.preventDefault();
    });

    $('#updateEntityConfig').submit(function (event) {

        var formData = $(this)
        var configData = [];
        var dateEditable = $('.dateEditable').map(function () {
            return this.checked
        }).get();
        dateEditable.unshift($('.dateEditable').text());
        dateEditable.push('DE');
        dateEditable.push('${entity.id}')
        dateEditable.push('${entity.entityType.id}')
        configData.push(Object.assign({}, dateEditable));

        var mad = $('.mad').map(function () {
            return this.checked
        }).get();
        mad.unshift($('.mad').text());
        mad.push('MAD');
        mad.push('${entity.id}');
        mad.push('${entity.entityType.id}');
        configData.push(Object.assign({}, mad));

        var dad = $('.dad').map(function () {
            return this.checked
        }).get();
        dad.unshift($('.dad').text());
        dad.push('DAD');
        dad.push('${entity.id}')
        dad.push('${entity.entityType.id}')
        configData.push(Object.assign({}, dad));

        var block = $('.block').map(function () {
            return this.checked
        }).get();
        block.unshift($('.block').text().replace(/\n/g, ''));
        block.push('BLOCK');
        block.push('${entity.id}')
        block.push('${entity.entityType.id}')
        configData.push(Object.assign({}, block));

        var auto = $('.auto').map(function () {
            return this.checked
        }).get();
        auto.unshift($('.auto').text());
        auto.push('AUTO');
        auto.push('${entity.id}')
        auto.push('${entity.entityType.id}')
        configData.push(Object.assign({}, auto));


        var uploadProof = $('.uploadProof').map(function () {
            return this.checked
        }).get();
        uploadProof.unshift($('.uploadProof').text());
        uploadProof.push('UPLOAD_PROOF');
        uploadProof.push('${entity.id}')
        uploadProof.push('${entity.entityType.id}')
        configData.push(Object.assign({}, uploadProof));

        var blockInv = $('.blockInv').map(function () {
            return this.checked
        }).get();
        blockInv.unshift($('.blockInv').text());
        blockInv.push('BLOCK_INV');
        blockInv.push('${entity.id}')
        blockInv.push('${entity.entityType.id}')
        configData.push(Object.assign({}, blockInv));

        var sendMail = $('.sendMail').map(function () {
            return this.checked
        }).get();
        sendMail.unshift($('.sendMail').text());
        sendMail.push('SEND_MAIL');
        sendMail.push('${entity.id}')
        sendMail.push('${entity.entityType.id}')
        configData.push(Object.assign({}, sendMail));

        var cde = $('.cde').map(function () {
            return this.checked
        }).get();
        cde.unshift($('.cde').text());
        cde.push('CDE');
        cde.push('${entity.id}')
        cde.push('${entity.entityType.id}')
        configData.push(Object.assign({}, cde));

        var discountButton = $('.discountButton').map(function () {
            return this.checked
        }).get();
        discountButton.unshift($('.discountButton').text());
        discountButton.push('DISCOUNT_BUTTON');
        discountButton.push('${entity.id}')
        discountButton.push('${entity.entityType.id}')
        configData.push(Object.assign({}, discountButton));

        var reCal = $('.reCal').map(function () {
            return this.checked
        }).get();
        reCal.unshift($('.reCal').text());
        reCal.push('RE_CAL');
        reCal.push('${entity.id}')
        reCal.push('${entity.entityType.id}')
        configData.push(Object.assign({}, reCal));


        var enableCashDisc = $('.enableCashDisc').map(function () {
            return this.checked
        }).get();
        enableCashDisc.unshift($('.enableCashDisc').text());
        enableCashDisc.push('ENABLE_CASH_DISC');
        enableCashDisc.push('${entity.id}')
        enableCashDisc.push('${entity.entityType.id}')
        configData.push(Object.assign({}, enableCashDisc));

        var withoutQr = $('.withoutQr').map(function () {
            return this.checked
        }).get();
        withoutQr.unshift($('.withoutQr').text());
        withoutQr.push('WITHOUT_QR');
        withoutQr.push('${entity.id}')
        withoutQr.push('${entity.entityType.id}')
        configData.push(Object.assign({}, withoutQr));

        var disableLR = $('.disableLR').map(function () {
            return this.checked
        }).get();
        disableLR.unshift($('.disableLR').text());
        disableLR.push('DISABLE_LR');
        disableLR.push('${entity.id}')
        disableLR.push('${entity.entityType.id}')
        configData.push(Object.assign({}, disableLR));


        var regenNewdoc = $('.regenNewdoc').map(function () {
            return this.checked
        }).get();
        regenNewdoc.unshift($('.regenNewdoc').text().replace(/\n/g, ''));
        regenNewdoc.push('REGEN_NEW_DOC');
        regenNewdoc.push('${entity.id}')
        regenNewdoc.push('${entity.entityType.id}')
        configData.push(Object.assign({}, regenNewdoc));


        var newBatchCreation = $('.newBatchCreation').map(function () {
            return this.checked
        }).get();
        newBatchCreation.unshift($('.newBatchCreation').text());
        newBatchCreation.push('NEW_BATCH_CREATION');
        newBatchCreation.push('${entity.id}')
        newBatchCreation.push('${entity.entityType.id}')
        configData.push(Object.assign({}, newBatchCreation));


        var disableSchemeQty = $('.disableSchemeQty').map(function () {
            return this.checked
        }).get();
        disableSchemeQty.unshift($('.disableSchemeQty').text());
        disableSchemeQty.push('DISABLE_SCHEME_QTY');
        disableSchemeQty.push('${entity.id}')
        disableSchemeQty.push('${entity.entityType.id}')
        configData.push(Object.assign({}, disableSchemeQty));

        var allowManual = $('.allowManual').map(function () {
            return this.checked
        }).get();
        allowManual.unshift($('.allowManual').text());
        allowManual.push('ALLOW_MANUAL');
        allowManual.push('${entity.id}')
        allowManual.push('${entity.entityType.id}')
        configData.push(Object.assign({}, allowManual));

        var prescriptionUpload = $('.prescriptionUpload').map(function () {
            return this.checked
        }).get();
        prescriptionUpload.unshift($('.prescriptionUpload').text());
        prescriptionUpload.push('PRESCRIPTION_UPLOAD');
        prescriptionUpload.push('${entity.id}')
        prescriptionUpload.push('${entity.entityType.id}')
        configData.push(Object.assign({}, prescriptionUpload));

        console.log(JSON.stringify(configData))

        var url = formData.attr('action')
        var beforeSendSwal;
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                configData: JSON.stringify(configData),
                entityId: '${entity.id}',
            },
            beforeSend: function () {
                beforeSendSwal = Swal.fire({
                    // title: "Loading",
                    html: '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                    showDenyButton: false,
                    showCancelButton: false,
                    showConfirmButton: false,
                    allowOutsideClick: false,
                    background: 'transparent'
                });
            },
            success: function (data) {
                Swal.fire("Success")
            },
            error: function (data) {
                console.log("Failed");
            }
        });

        event.preventDefault();
    });

</script>
</body>
</html>


