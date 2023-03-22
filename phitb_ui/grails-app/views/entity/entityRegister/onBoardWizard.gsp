<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Entity On Board</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
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
%{--<g:include view="controls/sidebar.gsp"/>--}%

<section class="">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="card">
                        <div class="header">
                            <h2>Entity on board information</h2>
                        </div>

                        <form id="wizard_with_validation"  action="/"  method="POST">
                            <h3>Series</h3>
                                                        <fieldset>

                                                            <div class="row">
                                                                <div class="col-lg-6">
                                                                    <div class="form-group form-float">
                                                                        <label for="seriesName">
                                                                            Series Name
                                                                        </label>
                                                                        <input type="text" id="seriesName" class="form-control seriesName"
                                                                               name="seriesName"
                                                                               placeholder="Series Name"
                                                                               required/>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <div class="form-group form-float">
                                                                        <label for="seriesCode">
                                                                            Series Code
                                                                        </label>
                                                                        <input type="text" id="seriesCode" class="form-control seriesCode"
                                                                               name="seriesCode"
                                                                               placeholder="Series Code" required/>
                                                                    </div>
                                                                </div>
<hr style="border: 0;clear:both;display:block;width: 100%;background-color:#000000;height: 1px;"/>
                                                                <div class="col-lg-12 mb-3"><h5>Below are the details
                                                                for the series code of the generated document</h5></div>
                                                                <br>
                                                                <div class="col-lg-6">
                                                                    <label for="saleSeries">
                                                                        Sale
                                                                    </label>
                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="saleSeries" class="form-control"
                                                                               placeholder="Sale Series"
                                                                               min="0"
                                                                               name="saleId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="purId">
                                                                        Purchase
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="purId" class="form-control"
                                                                               placeholder="Purchase Series"
                                                                               min="0" name="purId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="saleReturnId">
                                                                        Sale Return
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="saleReturnId" class="form-control"
                                                                               placeholder="Sale Return Series"
                                                                               min="0"
                                                                               name="saleReturnId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="saleOrderId">
                                                                        Sale Order
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="saleOrderId" class="form-control"
                                                                               placeholder="Sale Order Series"
                                                                               min="0"
                                                                               name="saleOrderId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="purchaseReturnId">
                                                                        Purchase Return
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="purchaseReturnId" class="form-control"
                                                                               placeholder="Purchase Return Series" min="0" name="purchaseReturnId"
                                                                               required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="purchaseOrderId">
                                                                        Purchase Order
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="purchaseOrderId" class="form-control"
                                                                               placeholder="Purchase Order Series"
                                                                               min="0"
                                                                               name="purchaseOrderId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <div class="form-group form-float">
                                                                        <label for="sampleInvoiceId">
                                                                            Sample Invoice
                                                                        </label>
                                                                        <input type="number" id="sampleInvoiceId" class="form-control"
                                                                               placeholder="Sample Invoice Order Series" min="0"
                                                                               name="sampleInvoiceId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="deliveryChallanId">
                                                                        Delivery Challan
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="deliveryChallanId" class="form-control"
                                                                               placeholder="Delivery Challan Series" min="0"
                                                                               name="deliveryChallanId" required>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6">
                                                                    <label for="goodsTransferId">
                                                                        GTN Series
                                                                    </label>

                                                                    <div class="form-group form-float">
                                                                        <input type="number" id="goodsTransferId" class="form-control"
                                                                               placeholder="GTN Series"
                                                                               name="goodsTransferId" min="0" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                                            <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                                            <input type="hidden" name="entityTypeId"
                                                                   value="${session.getAttribute('entityTypeId')}">
                                                        </fieldset>

                            <h3>Division</h3>
                            <fieldset>
                                <div class="row">
                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="divisionName">
                                            Division Name
                                        </label>
                                        <input type="text" id="divisionName" class="form-control divisionName"
                                               name="divisionName"
                                               placeholder="Division Name"
                                               required/>
                                    </div>

                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="divisionShortName">
                                            Division Short Name
                                        </label>
                                        <input type="text" id="divisionShortName" class="form-control divisionShortName"
                                               name="divisionShortName"
                                               placeholder="Division Short Name"
                                               required/>
                                    </div>


                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="cityIds">
                                            City
                                        </label>
                                        <br>
                                        <select class="form-control show-tick cityIds" name="cityIds" id="cityIds"
                                                multiple="multiple" style="width: 420px;" required>
                                        %{--                                    <option value="0">Please Select</option>--}%
                                            <g:each var="d" in="${districts}">
                                                <option value="${d.id}">${d.district}</option>
                                            </g:each>
                                        </select>
                                    </div>


                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="stateIds">
                                            State
                                        </label>
                                        <br>
                                        <select class="form-control show-tick stateIds" name="stateIds" id="stateIds"
                                                multiple="multiple" style="width: 420px;" required>
                                            <g:each var="s" in="${statelist}">
                                                <option value="${s.id}">${s.name}</option>
                                            </g:each>
                                        </select>
                                    </div>


                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="zoneIds">
                                            Zone
                                        </label>
                                        <br>
                                        <select class="form-control show-tick zoneIds" name="zoneIds" id="zoneIds"
                                                multiple="multiple" style="width: 420px;" required>
                                            <g:each var="z" in="${zoneList}">
                                                <option value="${z.id}">${z.name}</option>
                                            </g:each>
                                        </select>
                                    </div>



                                    <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                    <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                    <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                    <input type="hidden" name="entityTypeId" value="${session.getAttribute('entityTypeId')}">

                                </div>
                            </fieldset>

                            <h3>Priority</h3>
                            <fieldset>
                                <div class="row">
                                    <div class="col-lg-6 form-group  form-float">
                                        <label for="priority">
                                            Priority
                                        </label>
                                        <input type="text" id="priority" class="form-control priority" name="priority"
                                               placeholder="Priority"
                                               required/>
                                    </div>
                                    <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                    <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                    <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                    <input type="hidden" name="entityTypeId" value="${session.getAttribute('entityTypeId')}">

                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/> <!-- Lib Scripts Plugin Js -->
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/> <!-- Lib Scripts Plugin Js -->
<asset:javascript
        src="/themeassets/plugins/jquery-validation/jquery.validate.js"/> <!-- Jquery Validation Plugin Css -->
<asset:javascript src="/themeassets/plugins/jquery-steps/jquery.steps.js"/> <!-- JQuery Steps Plugin Js -->
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/><!-- Custom Js -->
<asset:javascript src="/themeassets/js/pages/forms/form-wizard.js"/>
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>
%{--<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>--}%


<script>

    $(function () {
        $('#stateIds').select2();
        $('#cityIds').select2();
        $('#zoneIds').select2();

    });

</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>