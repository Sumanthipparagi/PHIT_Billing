<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: General Scheme</title>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>

    <style>
    .form-control {
        border-radius: 0;
    }
    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="Alpino"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Add General Scheme</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        %{--                        <li class="breadcrumb-item"><a href="/entity-register">Entity Register</a></li>--}%
                        <li class="breadcrumb-item active">Add  Scheme</li>
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
                <form action="/add-scheme-entry" id="addSchemeEntry" method="POST"
                      role="form" class="schemeRegisterForm"  enctype="multipart/form-data">
                    <div class="row clearfix">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <div class="card">
                                <div class="header">
                                    <h6>Scheme Information</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-12 mt-2">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio"
                                                                       name="selections"
                                                                       id="zoneButton" value="ZONE" checked>
                                                                <label class="form-check-label" for="zoneButton">
                                                                    Zone
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-2">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio"
                                                                       name="selections"
                                                                       id="stateButton" value="STATE">
                                                                <label class="form-check-label" for="stateButton">
                                                                    State
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-2">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio"
                                                                       name="selections"
                                                                       id="cityButton" value="CITY">
                                                                <label class="form-check-label" for="cityButton">
                                                                    City / Area
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-2">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio"
                                                                       name="selections"
                                                                       id="hqButton" value="HQAREA">
                                                                <label class="form-check-label" for="hqButton">
                                                                    HQ Areas
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-2">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio"
                                                                       name="selections"
                                                                       id="clientButton" value="CLIENT">
                                                                <label class="form-check-label" for="clientButton">
                                                                    Client
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <br><br>


                                                <div class="col-lg-6 form-group  form-float selectors">

                                                </div>

                                                %{--                                            <div class="col-lg-6 form-group  form-float">--}%
                                                %{--                                                --}%
                                                %{--                                            </div>--}%

                                                %{--                                            <div class="col-lg-6 form-group  form-float">--}%
                                                %{--                                                --}%
                                                %{--                                            </div>--}%

%{--                                                <div class="col-lg-6 form-group  form-float">--}%
%{--                                                  --}%
%{--                                                </div>--}%

%{--                                                <div class="col-lg-6 form-group  form-float">--}%
%{--                                                    <label for="distributor">--}%
%{--                                                        Distributor--}%
%{--                                                    </label>--}%
%{--                                                    <select class="form-control show-tick distributor"--}%
%{--                                                            name="distributorId"--}%
%{--                                                            id="distributor">--}%
%{--                                                        <option value="">--Please Select--</option>--}%
%{--                                                        <g:each var="d" in="${distributorList}">--}%
%{--                                                            <option value="${d.id}">${d.entityName}</option>--}%
%{--                                                        </g:each>--}%
%{--                                                    </select>--}%
%{--                                                </div>--}%


                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="productId">
                                                        Product
                                                    </label>
                                                    <select class="form-control show-tick productId" name="productId"
                                                            id="productId" onchange="getBatches(this.value)">
                                                        <option value="">--Please Select--</option>
                                                        <g:each var="p" in="${productList}">
                                                            <option value="${p.id}">${p.productName}</option>
                                                        </g:each>
                                                    </select>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="batch">
                                                        Batch
                                                    </label>
                                                    <select class="form-control show-tick batch" name="batch"
                                                            id="batch" >
                                                        <option value="">-- Please Select --</option>

                                                        %{--                                        <g:each var="p" in="${batchList}">--}%
                                                        %{--                                            <option value="${p.batchNumber}">${p.batchNumber}</option>--}%
                                                        %{--                                        </g:each>--}%
                                                    </select>
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
                                    <h6>SLAB 1</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-12 mt-2">
                                            <div class="row">
                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab1MinQty">
                                                        Slab 1 Min Quantity
                                                    </label>
                                                    <input type="number" id="slab1MinQty"
                                                           class="form-control slab1MinQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab1MinQty" placeholder="Slab 1 Min Quantity"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab1SchemeQty">
                                                        Slab 1 Scheme Quantity
                                                    </label>
                                                    <input type="number" id="slab1SchemeQty"
                                                           class="form-control slab1SchemeQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab1SchemeQty" placeholder="Slab 1 Scheme Quantity"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab1BulkStatus">
                                                        Slab 1 Bulk Status
                                                    </label>
                                                    <input type="number" id="slab1BulkStatus"
                                                           class="form-control slab1BulkStatus" value ="0"
                                                           name="slab1BulkStatus" placeholder=" Slab 1 bulk Status"
                                                           required/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab1Status">
                                                        Slab 1 Status
                                                    </label>
                                                    <input type="number" id="slab1Status" class="form-control slab1Status"
                                                           name="slab1Status" placeholder="Slab 1 Status" value ="0"
                                                           required/>
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
                                    <h6>SLAB 2</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-12 mt-2">
                                            <div class="row">
                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab2MinQty">
                                                        Slab 2 Min Quantity
                                                    </label>
                                                    <input type="number" id="slab2MinQty"
                                                           class="form-control slab2MinQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab2MinQty" placeholder="Slab 2 Min Quantity"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab2SchemeQty">
                                                        Slab 2 Scheme Quantity
                                                    </label>
                                                    <input type="number" id="slab2SchemeQty"
                                                           class="form-control slab2SchemeQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab2SchemeQty" placeholder="Slab 2 Scheme Quantity"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab2BulkStatus">
                                                        Slab 2 Bulk Status
                                                    </label>
                                                    <input type="number" id="slab2BulkStatus"
                                                           class="form-control slab2BulkStatus" value ="0"
                                                           name="slab2BulkStatus" placeholder="Slab 2 Bulk Status"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab2Status">
                                                        Slab 2 Status
                                                    </label>
                                                    <input type="number" id="slab2Status"
                                                           class="form-control slab2Status" value ="0"
                                                           name="slab2Status" placeholder="Slab 2 Status"/>
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
                                    <h6>SLAB 3</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-12 mt-2">
                                            <div class="row">
                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab3MinQty">
                                                        Slab 3 Min Quantity
                                                    </label>
                                                    <input type="number" id="slab3MinQty"
                                                           class="form-control slab3MinQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab3MinQty" placeholder="Slab 3 Min Quantity"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab3SchemeQty">
                                                        Slab 3 Scheme Quantity
                                                    </label>
                                                    <input type="number" id="slab3SchemeQty"
                                                           class="form-control slab3SchemeQty"
                                                           onblur="setTwoNumberDecimal" value ="0"
                                                           name="slab3SchemeQty" placeholder="Slab 3 Scheme Quantity"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab3BulkStatus">
                                                        Slab 3 Bulk Status
                                                    </label>
                                                    <input type="number" id="slab3BulkStatus"
                                                           class="form-control slab3BulkStatus" value ="0"
                                                           name="slab3BulkStatus" placeholder="Slab 3 Bulk Status"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slab3Status">
                                                        Slab 3 Status
                                                    </label>
                                                    <input type="number" id="slab3Status"
                                                           class="form-control slab3Status" value ="0"
                                                           name="slab3Status" placeholder="Slab 3 Status"/>
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
                                    <h6>OTHER INFORMATION</h6>
                                </div>

                                <div class="body">
                                    <div class="row">
                                        <div class="col-md-12 mt-2">
                                            <div class="row">
                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slabValidityFrom">
                                                        Slab Validity From
                                                    </label>
                                                    <input type="text" id="slabValidityFrom"
                                                           class="form-control slabValidityFrom date"
                                                           name="slabValidityFrom" placeholder="Slab Validity From"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="slabValidityTo">
                                                        Slab Validity to
                                                    </label>
                                                    <input type="text" id="slabValidityTo"
                                                           class="form-control slabValidityTo date"
                                                           name="slabValidityTo" placeholder=" Slab Validity to"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialDiscount">
                                                        Special Discount
                                                    </label>
                                                    <input type="text" id="specialDiscount"
                                                           class="form-control specialDiscount" value ="0" onblur="setTwoNumberDecimal()"
                                                           name="specialDiscount" placeholder="Special Discount"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialDiscountValidFrom">
                                                        Special Discount Valid From
                                                    </label>
                                                    <input type="text" id="specialDiscountValidFrom"
                                                           class="form-control specialDiscountValidFrom date"
                                                           name="specialDiscountValidFrom"
                                                           placeholder="Special Discount Valid From"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialDiscountValidTo">
                                                        Special Discount Valid to
                                                    </label>
                                                    <input type="text" id="specialDiscountValidTo"
                                                           class="form-control specialDiscountValidTo date"
                                                           name="specialDiscountValidTo"
                                                           placeholder="Special Discount Valid to"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialRate">
                                                        Special Rate
                                                    </label>
                                                    <input type="number" id="specialRate"
                                                           class="form-control specialRate" onblur="setTwoNumberDecimal()" value ="0"
                                                           name="specialRate" placeholder="Special Rate"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialRateValidFrom">
                                                        Special Rate Valid From
                                                    </label>
                                                    <input type="text" id="specialRateValidFrom"
                                                           class="form-control specialRateValidFrom date"
                                                           name="specialRateValidFrom"
                                                           placeholder="Special Rate Valid From"/>
                                                </div>

                                                <div class="col-lg-6 form-group  form-float">
                                                    <label for="specialRateValidTo">
                                                        Special Rate Valid to
                                                    </label>
                                                    <input type="text" id="specialRateValidTo"
                                                           class="form-control specialRateValidTo date"
                                                           name="specialRateValidTo"
                                                           placeholder="Special Rate Valid to"/>
                                                </div>
                                                <input type="hidden" name="schemeStatus" value="1">
                                                <input type="hidden" name="status" value="1">
                                                <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                                <input type="hidden" name="entityTypeId" value="${session.getAttribute('entityTypeId')}">
                                                <input type="hidden" name="distributorId" value="0">
                                                <input type="hidden" name="syncStatus" value="1">
                                                <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                                <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">

                                                <div class="col-lg-12">
                                                    <div class="" style="float: right;">
                                                        <input name="id" id="id" class="id" type="hidden">
                                                        <input name="type" class="type" value="add" type="hidden">
                                                        <button type="submit"
                                                                class="btn btn-default btn-round waves-effect"
                                                                name="submituser"><font
                                                                style="vertical-align: inherit;"><font
                                                                    style="vertical-align: inherit;">SUBMIT</font>
                                                        </font>
                                                        </button>
                                                        <button type="reset"
                                                                class="btn btn-danger btn-simple btn-round waves-effect"
                                                                data-dismiss="modal"><font
                                                                style="vertical-align: inherit;"><font
                                                                    style="vertical-align: inherit;">RESET</font></font>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script>

    $(function () {
        //Datetimepicker plugin
        $('.date').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });


        $('.selectors').removeClass('col-lg-6')
        var selectors = "";
        $('input:radio[name="selections"]').change(function () {
            $('.selectors').addClass('col-lg-6');

            if (this.value === "ZONE" && this.checked) {
                selectors = " <label for=\"zone\">\n" +
                    "                                                    Zone\n" +
                    "                                                </label>\n" +
                    "                                                <select class=\"form-control show-tick zone\" name=\"zoneIds\" id=\"zone\">\n" +
                    "                                                    <option value=\"\">--Please Select--</option>\n" +
                    "                                                    <g:each var="z" in="${zoneList}">\n" +
                    "                                                        <option value=\"${z.id}\">${z.name}</option>\n" +
                    "                                                    </g:each>\n" +
                    "                                                </select>";


            } else if (this.value === "STATE" && this.checked) {
                selectors = "<label for=\"state\">\n" +
                    "                                                    State\n" +
                    "                                                </label>\n" +
                    "                                                <select class=\"form-control show-tick state\" name=\"stateIds\" id=\"state\">\n" +
                    "                                                    <option value=\"\">--Please Select--</option>\n" +
                    "                                                    <g:each var="s" in="${stateList}">\n" +
                    "                                                        <option value=\"${s.id}\">${s.name}</option>\n" +
                    "                                                    </g:each>\n" +
                    "                                                </select>"
            } else if (this.value === "CITY" && this.checked) {
                selectors = "<label for=\"city\">\n" +
                    "                                                    City\n" +
                    "                                                </label>\n" +
                    "                                                <select class=\"form-control show-tick city\" name=\"cityIds\" id=\"city\">\n" +
                    "                                                    <option value=\"\">--Please Select--</option>\n" +
                    "                                                    <g:each var="c" in="${cityList}">\n" +
                    "                                                        <option value=\"${c.id}\">${c.name}</option>\n" +
                    "                                                    </g:each>\n" +
                    "                                                </select>"
            } else if (this.value === "HQAREA" && this.checked) {
                selectors = "<label for=\"hqarea\">\n" +
                    "                                                    HQ areas\n" +
                    "                                                </label>\n" +
                    "                                                <select class=\"form-control show-tick hqarea\" name=\"hqarea\" id=\"hqarea\">\n" +
                    "                                                    <option value=\"\">--Please Select--</option>\n" +
                    "                                                    <g:each var="h" in="${hqAreaList}">\n" +
                    "                                                        <option value=\"${h.id}\">${h.hqName}</option>\n" +
                    "                                                    </g:each>\n" +
                    "                                                </select>"
            } else if (this.value === "CLIENT" && this.checked) {
                selectors = "  <label for=\"customer\">\n" +
                    "                                                        Client\n" +
                    "                                                    </label>\n" +
                    "                                                    <select class=\"form-control show-tick customer\" name=\"customerIds\"\n" +
                    "                                                            id=\"customer\">\n" +
                    "                                                        <option value=\"\">--Please Select--</option>\n" +
                    "                                                        <g:each var="e" in="${entityList}">\n" +
                    "                                                            <option value=\"${e.id}\">${e.entityName}</option>\n" +
                    "                                                        </g:each>\n" +
                    "                                                    </select>"
            }


            else if (this.value === "") {
                selectors = "";
                $('.selectors').removeClass('col-lg-6')
            }
            $('.selectors').html(selectors);
            $('#zone').select2();
            $('#state').select2();
            $('.hqarea').select2();
            $('#customer').select2();
            $('#city').select2({
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

        }).change();
        $('.selectors').html(selectors);
        $('#zone').select2();
        $('#state').select2();
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
        $('#productId').select2();
        $('#customer').select2();
        $('#distributor').select2()

        // $('#batch').select2()
    });

    function getBatches(id) {
        var option = '';
        $.ajax({
            url: '/batch-register/product/' + id,
            dataType: 'json',
            type: 'GET',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    option += '<option value="' + data[i].batchNumber + '">' + data[i].batchNumber + '</option>';
                }
                $('#batch').empty()
                $('#batch').html(option);
                if (data.length === 0) {
                    // $('#batch').prop('disabled', true);
                } else {
                    // $('#batch').prop('disabled', false);

                }
            },
            error: function (x, e) {
                console.log("Something went wrong!")
            }

        });
    }


    $('#addSchemeEntry').submit(function(event) {

        var zone = $('.zone').val();
        var state = $('.state').val();
        var city = $('.city').val();
        var hqarea = $('.hqarea').val();
        var client = $('.customer').val();
        var batch = $('.batch').val();
        if((zone ==="" || zone === undefined) && (state ==="" || state === undefined ) && (city ==="" || city === undefined ) && (hqarea ==="" || hqarea === undefined) && (client ==="" || client === undefined ))
        {
            alert("Please enter basic details!");
            return false;
        }

        if(batch ==="" || batch === null)
        {
            alert("Please select the product with batches!");
            return false;
        }
        var formData = $(this);
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : formData.attr('action'), // the url where we want to POST
            data        : formData.serialize(), // our data object
            success:function(data){
                // $("#validation-status").text(data);
                // swal('success','User updated Successfully',data);
                swal({
                        title: "Success!",
                        text: "Created Successfully! ",
                        type: "success"
                    },
                    function(){
                        window.location.href = "/scheme-entry";

                    }
                );
            },
            error:function(data){
                console.log("Failed");
                // $("#validation-status").text(data.responseText);
                // swal('error','User update Failed',data.responseText);
                swal("Error", "Request failed!"+data.responseText, "error");

            }
        });
        event.preventDefault();
        // }

    });



    function setTwoNumberDecimal(event) {
        this.value = parseFloat(this.value.toFixed(2));
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>