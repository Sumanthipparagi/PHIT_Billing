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
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main-2.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/select-2-editor/select2.min.css" rel="stylesheet" />

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
                        <h2>Update Settings</h2>
                    </div>
                    <div class="body">
                        <form action="/entity-settings" method="post">
                            <g:if test="${entitySettings.size()!=0}">
                        <div class="row clearfix">
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Invoice Generation Methods</b>
                                <select class="form-control show-tick" name="igm">
                                    <option value="${Constants.SINGLE_INV_ENTIRE_ENTITY}"
                                            <g:if test="${entitySettings.IGM == Constants.SINGLE_INV_ENTIRE_ENTITY}">selected</g:if>>Single Invoice
                                    for
                                    EntireEntity</option>
                                    <option value="${Constants.SEPARATE_INV_EACH_DIVISION}" <g:if test="${entitySettings.IGM == Constants.SEPARATE_INV_EACH_DIVISION}">selected</g:if>>Separate Invoice for Each Division</option>
                                    <option  value="${Constants.SEPARATE_INV_EACH_FLOOR}" <g:if test="${entitySettings.IGM == Constants.SEPARATE_INV_EACH_FLOOR}">selected</g:if>>Separate Invoice for Each
                                    Floor	</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Default Sales Invoice Series</b>
                                <select class="form-control show-tick" name="dsi">
                                    <option value="${Constants.YES}" <g:if
                                            test="${entitySettings.DSI == Constants.YES}">selected</g:if>>Yes</option>
                                    <option value="${Constants.NO}"  <g:if test="${entitySettings.DSI == Constants.NO}">selected</g:if>>No</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Invoice Print Grouping</b>
                                <select class="form-control show-tick" name="ipg">
                                    <option value="${Constants.DIVISION_WISE}" <g:if test="${entitySettings.IPG ==
                                            Constants.DIVISION_WISE}">selected</g:if>>Division
                                    Wise</option>
                                    <option value="${Constants.TAX_WISE}" <g:if test="${entitySettings.IPG == Constants.TAX_WISE}">selected</g:if>>Tax wise</option>
                                    <option value="${Constants.PRODUCT_GROUPING}" <g:if test="${entitySettings.IPG ==
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
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20" >
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
                                    <option value="${Constants.NO}"  <g:if
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
                                            <option  value="${Constants.SEPARATE_INV_EACH_DIVISION}">Separate Invoice for Each Division</option>
                                            <option  value="${Constants.SEPARATE_INV_EACH_FLOOR}">Separate Invoice for Each
                                            Floor	</option>
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
                                            <option value="${Constants.DIVISION_WISE}" >Division Wise</option>
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
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20" >
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
                                            <option value="${Constants.LOWER_SCHEME}">Lower Scheme Auto Adjust (Profitable)	</option>
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
                            <button type="submit" class="btn btn-default btn-round waves-effect"><font
                                    style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
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
<asset:javascript  src="/themeassets/plugins/select-2-editor/select2.js" />
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("settings-menu");
</script>
</body>
</html>