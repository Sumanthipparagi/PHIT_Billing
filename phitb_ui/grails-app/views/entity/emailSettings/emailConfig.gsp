<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Email Config</title>
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
                    <h2>Email Config</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Email Config</li>
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
        <g:if test="${emailSettings.size() == 0}">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Sales</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="salesConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale Order is 'Saved'?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SENDMAIL_AFTER_SALE_ORDER_SAVED}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale order is clicked 'Convert'?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SENDMAIL_SALE_ORDER_CLICKED}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in Sales Entry?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Sales?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_MANUAL_EMAIL_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>


                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in GTN?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_AUTO_EMAIL_AFTER_SAVE_GTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in GTN?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_MANUAL_EMAIL_BTN_GTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale Order is 'Saved'?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SMS_AFTER_SALE_ORDER_SAVED}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale order is clicked 'Convert'?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SMS_SALE_ORDER_CLICKED}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving in Sales Entry?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_AUTO_SMS_AFTER_SAVE_SALE_ENTRY}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Sales Right Window?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.SALES_MANUAL_SMS_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="SALES">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Receipt</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="receiptConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?

                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Receipt Page?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_MANUAL_EMAIL_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Receipt Page?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_MANUAL_SMS_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="RECEIPT">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Credit Note/Cr JV/DebitNote/DbJV
                            </h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="crjvConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in CrJV/CreditNote/SalesReturn?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_MANUAL_EMAIL_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in CrJV/CreditNote/SalesReturn?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.CRJV_MANUAL_SMS_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="CRJV">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%
                    <div class="card">
                        <div class="header">
                            <h6>Purchase</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="purchaseConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Purchase?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.PURCHASE_MANUAL_EMAIL_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Purchase?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.PURCHASE_MANUAL_SMS_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="PURCHASE">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Cr/Db Settlement</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="crdbConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Settlements?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_MANUAL_EMAIL_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Settlements?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.CRDB_MANUAL_SMS_BTN}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="CRDB">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Reports</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="reportsConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Reports Mail Button in All Reports?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.MAIL_BTN_REPORTS}">
                                            <option value="true">Yes</option>
                                            <option value="false">No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="REPORTS">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </g:if>
        <g:else>
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Sales</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="salesConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale Order is 'Saved'?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SENDMAIL_AFTER_SALE_ORDER_SAVED}">
                                            <option value="true"
                                                    <g:if test="${salesConfig?.SALE_SENDMAIL_AFTER_SALE_ORDER_SAVED == "true"}">selected</g:if>>Yes
                                            </option>
                                            <option value="false"
                                                    <g:if test="${salesConfig?.SALE_SENDMAIL_AFTER_SALE_ORDER_SAVED == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send Mail After Sale order is clicked 'Convert'?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SENDMAIL_SALE_ORDER_CLICKED}">
                                            <option value="true"  <g:if test="${salesConfig?.SALE_SENDMAIL_SALE_ORDER_CLICKED == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_SENDMAIL_SALE_ORDER_CLICKED == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in Sales Entry?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_DOC_CANCELLED_SEND_MAIL == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_DOC_CANCELLED_SEND_MAIL == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Sales?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_MANUAL_EMAIL_BTN}">
                                            <option value="true" <g:if test="${salesConfig?.SALES_MANUAL_EMAIL_BTN == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALES_MANUAL_EMAIL_BTN == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>


                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving in GTN?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_AUTO_EMAIL_AFTER_SAVE_GTN}">
                                            <option value="true" <g:if test="${salesConfig?.SALES_AUTO_EMAIL_AFTER_SAVE_GTN == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALES_AUTO_EMAIL_AFTER_SAVE_GTN == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in GTN?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALES_MANUAL_EMAIL_BTN_GTN}">
                                            <option value="true" <g:if test="${salesConfig?.SALES_MANUAL_EMAIL_BTN_GTN == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALES_MANUAL_EMAIL_BTN_GTN == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale Order is 'Saved'?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SMS_AFTER_SALE_ORDER_SAVED}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_SMS_AFTER_SALE_ORDER_SAVED == "true"}">selected</g:if>>Yes</option>
                                            <option value="false"  <g:if test="${salesConfig?.SALE_SMS_AFTER_SALE_ORDER_SAVED == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Send SMS After Sale order is clicked 'Convert'?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_SMS_SALE_ORDER_CLICKED}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_SMS_SALE_ORDER_CLICKED == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_SMS_SALE_ORDER_CLICKED == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving in Sales Entry?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_AUTO_SMS_AFTER_SAVE_SALE_ENTRY}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_AUTO_SMS_AFTER_SAVE_SALE_ENTRY == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_AUTO_SMS_AFTER_SAVE_SALE_ENTRY == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.SALE_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true" <g:if test="${salesConfig?.SALE_DOC_CANCELLED_SEND_SMS == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALE_DOC_CANCELLED_SEND_SMS == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Sales Right Window?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.SALES_MANUAL_SMS_BTN}">
                                            <option value="true" <g:if test="${salesConfig?.SALES_MANUAL_SMS_BTN == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${salesConfig?.SALES_MANUAL_SMS_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="SALES">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Receipt</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="receiptConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?

                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_AUTO_EMAIL_AFTER_SAVE ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${receiptConfig?.RECEIPT_AUTO_EMAIL_AFTER_SAVE == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_MAIL == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_MAIL == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Receipt Page?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_MANUAL_EMAIL_BTN}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_MAIL == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_MAIL == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_AUTO_SMS_AFTER_SAVE == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${receiptConfig?.RECEIPT_AUTO_SMS_AFTER_SAVE == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_SMS == "true"}">selected</g:if>>Yes</option>
                                            <option value="false"  <g:if test="${receiptConfig?.RECEIPT_DOC_CANCELLED_SEND_SMS == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Receipt Page?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.RECEIPT_MANUAL_SMS_BTN}">
                                            <option value="true" <g:if test="${receiptConfig?.RECEIPT_MANUAL_SMS_BTN == "true"}">selected</g:if>>Yes</option>
                                            <option value="false"  <g:if test="${receiptConfig?.RECEIPT_MANUAL_SMS_BTN == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="RECEIPT">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Credit Note/Cr JV/DebitNote/DbJV
                            </h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="crjvConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_AUTO_EMAIL_AFTER_SAVE == "true"}">selected</g:if>>
                                                Yes</option>
                                            <option value="false" <g:if test="${creditConfig?.CRJV_AUTO_EMAIL_AFTER_SAVE == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send Email?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_DOC_CANCELLED_SEND_MAIL}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_DOC_CANCELLED_SEND_MAIL == "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${creditConfig?.CRJV_DOC_CANCELLED_SEND_MAIL == "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in CrJV/CreditNote/SalesReturn?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_MANUAL_EMAIL_BTN}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_MANUAL_EMAIL_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${creditConfig?.CRJV_MANUAL_EMAIL_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_AUTO_SMS_AFTER_SAVE ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${creditConfig?.CRJV_AUTO_SMS_AFTER_SAVE ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>If Document Cancelled, Send SMS?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRJV_DOC_CANCELLED_SEND_SMS}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_DOC_CANCELLED_SEND_SMS ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${creditConfig?.CRJV_DOC_CANCELLED_SEND_SMS ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in CrJV/CreditNote/SalesReturn?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.CRJV_MANUAL_SMS_BTN}">
                                            <option value="true" <g:if test="${creditConfig?.CRJV_MANUAL_SMS_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false"  <g:if test="${creditConfig?.CRJV_MANUAL_SMS_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="CRJV">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%
                    <div class="card">
                        <div class="header">
                            <h6>Purchase</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="purchaseConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Purchase?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.PURCHASE_MANUAL_EMAIL_BTN}">
                                            <option value="true"  <g:if test="${creditConfig?.PURCHASE_MANUAL_EMAIL_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false"  <g:if test="${creditConfig?.PURCHASE_MANUAL_EMAIL_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Purchase?
                                        </b>
                                        <select class="form-control show-tick"
                                                name="${Constants.PURCHASE_MANUAL_SMS_BTN}">
                                            <option value="true" <g:if test="${purchaseConfig?.PURCHASE_MANUAL_SMS_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${purchaseConfig?.PURCHASE_MANUAL_SMS_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="PURCHASE">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Cr/Db Settlement</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="crdbConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto Email After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_AUTO_EMAIL_AFTER_SAVE}">
                                            <option value="true" <g:if test="${crdbConfig?.CRDB_AUTO_EMAIL_AFTER_SAVE ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${crdbConfig?.CRDB_AUTO_EMAIL_AFTER_SAVE ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual Email Button in Settlements?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_MANUAL_EMAIL_BTN}">
                                            <option value="true" <g:if test="${crdbConfig?.CRDB_MANUAL_EMAIL_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${crdbConfig?.CRDB_MANUAL_EMAIL_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Auto SMS After Saving?</b>
                                        <select class="form-control show-tick"
                                                name="${Constants.CRDB_AUTO_SMS_AFTER_SAVE}">
                                            <option value="true" <g:if test="${crdbConfig?.CRDB_AUTO_SMS_AFTER_SAVE ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${crdbConfig?.CRDB_AUTO_SMS_AFTER_SAVE ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Manual SMS Button in Settlements?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.CRDB_MANUAL_SMS_BTN}">
                                            <option value="true" <g:if test="${crdbConfig?.CRDB_MANUAL_SMS_BTN ==
                                                    "true"}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${crdbConfig?.CRDB_MANUAL_SMS_BTN ==
                                                    "false"}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="CRDB">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    %{--                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>--}%

                    <div class="card">
                        <div class="header">
                            <h6>Reports</h6>
                        </div>

                        <div class="body">
                            <form action="/email-config" method="post" id="reportsConfig">
                                <div class="row clearfix">
                                    <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                        <b>Reports Mail Button in All Reports?
                                        </b>
                                        <select class="form-control show-tick" name="${Constants.MAIL_BTN_REPORTS}">
                                            <option value="true" <g:if test="${emailSettings?.reportMailBtn ==
                                                    true}">selected</g:if>>Yes</option>
                                            <option value="false" <g:if test="${emailSettings?.reportMailBtn ==
                                                    false}">selected</g:if>>No</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="REPORTS">
                                <input type="hidden" name="entityId" value="${params.id}">
                                <button type="submit" class="btn btn-default btn-round waves-effect"
                                        style="background-color: green;"><font
                                        style="vertical-align: inherit;"><font
                                            style="vertical-align: inherit;">SUBMIT</font></font></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </g:else>

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
</body>
</html>
<script>
    $('#salesConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
    $('#receiptConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
    $('#crjvConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
    $('#purchaseConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
    $('#crdbConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
    $('#reportsConfig').submit(function (event) {
        var formData = $(this);
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: formData.attr('action'), // the url where we want to POST
            data: formData.serialize(), // our data object
            success: function (data) {
                // $("#validation-status").text(data);
                // swal("Success!", "Updated Successfully! ", "success");
                // alert("Password Updated!!")
                Swal.fire(
                    'success',
                    'Updated Successfully',
                    'success'
                );

            },
            error: function (data) {
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                swal('error', 'Config update Failed', data.responseText);
                // swal("Error", "Request failed!"+data.responseText, "error");
                alert("error")
            }
        });
        event.preventDefault();
    });
</script>

