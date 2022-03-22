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
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />

<style>
    .form-control
    {
        border-radius: 0;
    }
</style>
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

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Add General Scheme</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
%{--                        <li class="breadcrumb-item"><a href="/entity-register">Entity Register</a></li>--}%
                        <li class="breadcrumb-item active">Add General Scheme</li>
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
                        <form action="/genaral-scheme" id="form_validation" method="POST" role="form"
                              class="schemeRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-6 form-group  form-float">
                                    <label for="zone">
                                       Zone
                                    </label>
                                    <select class="form-control show-tick zone" name="entityType" id="zone">
                                        <g:each var="z" in="${zoneList}">
                                            <option value="${z.id}">${z.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="state">
                                        State
                                    </label>
                                    <select class="form-control show-tick state" name="state" id="state">
                                        <g:each var="s" in="${stateList}">
                                            <option value="${s.id}">${s.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="city">
                                        City
                                    </label>
                                    <select class="form-control show-tick city" name="city" id="city">
                                        <g:each var="c" in="${cityList}">
                                            <option value="${c.id}">${c.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="distributor">
                                        Distributor
                                    </label>
                                    <select class="form-control show-tick distributor" name="distributor" id="distributor">
                                        <g:each var="d" in="${distributorList}">
                                            <option value="${d.id}">${d.entityName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="product">
                                        Product
                                    </label>
                                    <select class="form-control show-tick product" name="product" id="product">
                                        <g:each var="p" in="${productList}">
                                            <option value="${p.id}">${p.productName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="batch">
                                        Batch
                                    </label>
                                    <select class="form-control show-tick batch" name="batch" id="batch">
                                        <g:each var="p" in="${batchList}">
                                            <option value="${p.id}">${p.batchNumber}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1_min_qty">
                                       Slab 1 Min Quantity
                                    </label>
                                    <input type="text" id="slab1_min_qty" class="form-control slab1_min_qty"
                                           name="slab1_min_qty" placeholder="Slab 1 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1_scheme_qty">
                                        Slab 1 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab1_scheme_qty" class="form-control slab1_scheme_qty"
                                           name="slab1_min_qty" placeholder="Slab 1 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1_bulk_status">
                                        Slab 1 Bulk Status
                                    </label>
                                    <input type="text" id="slab1_bulk_status" class="form-control slab1_bulk_status"
                                           name="slab1_min_qty" placeholder=" Slab 1 bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1_status">
                                        Slab 1 Status
                                    </label>
                                    <input type="text" id="slab1_status" class="form-control slab1_status"
                                           name="slab1_min_qty" placeholder="Slab 1 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2_min_qty">
                                        Slab 2 Min Quantity
                                    </label>
                                    <input type="text" id="slab2_min_qty" class="form-control slab2_min_qty"
                                           name="slab2_min_qty" placeholder="Slab 2 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2_scheme_qty">
                                        Slab 2 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab2_scheme_qty" class="form-control slab2_scheme_qty"
                                           name="slab2_scheme_qty" placeholder="Slab 2 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2_bulk_status">
                                        Slab 2 Bulk Status
                                    </label>
                                    <input type="text" id="slab2_bulk_status" class="form-control slab2_bulk_status"
                                           name="slab2_bulk_status" placeholder="Slab 2 Bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2_status">
                                        Slab 2 Status
                                    </label>
                                    <input type="text" id="slab2_status" class="form-control slab2_status"
                                           name="slab2_status" placeholder="Slab 2 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3_min_qty">
                                        Slab 3 Min Quantity
                                    </label>
                                    <input type="text" id="slab3_min_qty" class="form-control slab3_min_qty"
                                           name="slab2_min_qty" placeholder="Slab 3 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3_scheme_qty">
                                        Slab 3 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab3_scheme_qty" class="form-control slab3_scheme_qty"
                                           name="slab3_scheme_qty" placeholder="Slab 3 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3_bulk_status">
                                        Slab 3 Bulk Status
                                    </label>
                                    <input type="text" id="slab3_bulk_status" class="form-control slab3_bulk_status"
                                           name="slab3_bulk_status" placeholder="Slab 3 Bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3_status">
                                        Slab 3 Status
                                    </label>
                                    <input type="text" id="slab3_status" class="form-control slab3_status"
                                           name="slab3_status" placeholder="Slab 3 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab_validity_from">
                                       Slab Validity From
                                    </label>
                                    <input type="text" id="slab_validity_from" class="form-control slab_validity_from date"
                                           name="slab_validity_from" placeholder="Slab Validity From"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab_validity_to">
                                        Slab Validity to
                                    </label>
                                    <input type="text" id="slab_validity_to" class="form-control slab_validity_to date"
                                           name="slab_validity_to" placeholder=" Slab Validity to"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_discount">
                                        Special Discount
                                    </label>
                                    <input type="text" id="special_discount" class="form-control special_discount"
                                           name="special_discount" placeholder="Special Discount"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_discount_valid_from">
                                        Special Discount Valid From
                                    </label>
                                    <input type="text" id="special_discount_valid_from"
                                           class="form-control special_discount_valid_from date"
                                           name="special_discount_valid_from" placeholder="Special Discount"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_discount_valid_to">
                                        Special Discount Valid to
                                    </label>
                                    <input type="text" id="special_discount_valid_to"
                                           class="form-control special_discount_valid_to date"
                                           name="special_discount_valid_to" placeholder="Special Discount Valid to"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_rate">
                                        Special Rate
                                    </label>
                                    <input type="text" id="special_rate"
                                           class="form-control special_rate"
                                           name="special_rate" placeholder="Special Rate"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_rate_valid_from">
                                        Special Rate Valid From
                                    </label>
                                    <input type="text" id="special_rate_valid_from"
                                           class="form-control special_rate_valid_from date"
                                           name="special_rate_valid_from" placeholder="Special Rate Valid From"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="special_rate_valid_to">
                                        Special Rate Valid to
                                    </label>
                                    <input type="text" id="special_rate_valid_to"
                                           class="form-control special_rate_valid_to date"
                                           name="special_rate_valid_from" placeholder="Special Rate Valid to"
                                           required/>
                                </div>
                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="syncStatus" value="1">
                                <input type="hidden" name="createdUser" value="1">
                                <input type="hidden" name="modifiedUser" value="1">
                                <div class="col-lg-12">
                                    <div class="" style="float: right;">
                                        <input name="id" id="id" class="id" type="hidden">
                                        <input name="type" class="type" value="add" type="hidden">
                                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
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
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>

<script>

    $(function () {
        //Datetimepicker plugin
        $('.date').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
    });
</script>

</body>
</html>