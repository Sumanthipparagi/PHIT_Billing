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
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">

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
                        <form action="/add-scheme-entry" id="form_validation" method="POST" role="form"
                              class="schemeRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-6 form-group  form-float">
                                    <label for="zone">
                                       Zone
                                    </label>
                                    <select class="form-control show-tick zone" name="zoneIds" id="zone">
                                        <option value="">--Please Select--</option>
                                        <g:each var="z" in="${zoneList}">
                                            <option value="${z.id}">${z.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="state">
                                        State
                                    </label>
                                    <select class="form-control show-tick state" name="stateIds" id="state">
                                        <option value="">--Please Select--</option>
                                        <g:each var="s" in="${stateList}">
                                            <option value="${s.id}">${s.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="city">
                                        City
                                    </label>
                                    <select class="form-control show-tick city" name="cityIds" id="city">
                                        <option value="">--Please Select--</option>
                                        <g:each var="c" in="${cityList}">
                                            <option value="${c.id}">${c.name}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="customer">
                                        Customer
                                    </label>
                                    <select class="form-control show-tick customer" name="customerIds" id="customer">
                                        <option value="">--Please Select--</option>
                                        <g:each var="e" in="${entityList}">
                                            <option value="${e.id}">${e.entityName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="distributor">
                                        Distributor
                                    </label>
                                    <select class="form-control show-tick distributor" name="distributorId"
                                            id="distributor">
                                        <option value="">--Please Select--</option>
                                        <g:each var="d" in="${distributorList}">
                                            <option value="${d.id}">${d.entityName}</option>
                                        </g:each>
                                    </select>
                                </div>


                                <div class="col-lg-6 form-group  form-float">
                                    <label for="productId">
                                        Product
                                    </label>
                                    <select class="form-control show-tick productId" name="productId" id="productId" onchange="getBatches(this.value)">
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
                                    <select class="form-control show-tick batch" name="batch" id="batch">

                                        %{--                                        <g:each var="p" in="${batchList}">--}%
%{--                                            <option value="${p.batchNumber}">${p.batchNumber}</option>--}%
%{--                                        </g:each>--}%
                                    </select>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1MinQty">
                                       Slab 1 Min Quantity
                                    </label>
                                    <input type="text" id="slab1MinQty" class="form-control slab1MinQty"
                                           name="slab1MinQty" placeholder="Slab 1 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1SchemeQty">
                                        Slab 1 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab1SchemeQty" class="form-control slab1SchemeQty"
                                           name="slab1SchemeQty" placeholder="Slab 1 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1BulkStatus">
                                        Slab 1 Bulk Status
                                    </label>
                                    <input type="text" id="slab1BulkStatus" class="form-control slab1BulkStatus"
                                           name="slab1BulkStatus" placeholder=" Slab 1 bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab1Status">
                                        Slab 1 Status
                                    </label>
                                    <input type="text" id="slab1Status" class="form-control slab1Status"
                                           name="slab1Status" placeholder="Slab 1 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2MinQty">
                                        Slab 2 Min Quantity
                                    </label>
                                    <input type="text" id="slab2MinQty" class="form-control slab2MinQty"
                                           name="slab2MinQty" placeholder="Slab 2 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2SchemeQty">
                                        Slab 2 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab2SchemeQty" class="form-control slab2SchemeQty"
                                           name="slab2SchemeQty" placeholder="Slab 2 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2BulkStatus">
                                        Slab 2 Bulk Status
                                    </label>
                                    <input type="text" id="slab2BulkStatus" class="form-control slab2BulkStatus"
                                           name="slab2BulkStatus" placeholder="Slab 2 Bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab2Status">
                                        Slab 2 Status
                                    </label>
                                    <input type="text" id="slab2Status" class="form-control slab2Status"
                                           name="slab2Status" placeholder="Slab 2 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3MinQty">
                                        Slab 3 Min Quantity
                                    </label>
                                    <input type="text" id="slab3MinQty" class="form-control slab3MinQty"
                                           name="slab3MinQty" placeholder="Slab 3 Min Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3SchemeQty">
                                        Slab 3 Scheme Quantity
                                    </label>
                                    <input type="text" id="slab3SchemeQty" class="form-control slab3SchemeQty"
                                           name="slab3SchemeQty" placeholder="Slab 3 Scheme Quantity"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3BulkStatus">
                                        Slab 3 Bulk Status
                                    </label>
                                    <input type="text" id="slab3BulkStatus" class="form-control slab3BulkStatus"
                                           name="slab3BulkStatus" placeholder="Slab 3 Bulk Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slab3Status">
                                        Slab 3 Status
                                    </label>
                                    <input type="text" id="slab3Status" class="form-control slab3Status"
                                           name="slab3Status" placeholder="Slab 3 Status"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slabValidityFrom">
                                       Slab Validity From
                                    </label>
                                    <input type="text" id="slabValidityFrom" class="form-control slabValidityFrom date"
                                           name="slabValidityFrom" placeholder="Slab Validity From"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="slabValidityTo">
                                        Slab Validity to
                                    </label>
                                    <input type="text" id="slabValidityTo" class="form-control slabValidityTo date"
                                           name="slabValidityTo" placeholder=" Slab Validity to"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialDiscount">
                                        Special Discount
                                    </label>
                                    <input type="text" id="specialDiscount" class="form-control specialDiscount"
                                           name="specialDiscount" placeholder="Special Discount"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialDiscountValidFrom">
                                        Special Discount Valid From
                                    </label>
                                    <input type="text" id="specialDiscountValidFrom"
                                           class="form-control specialDiscountValidFrom date"
                                           name="specialDiscountValidFrom" placeholder="Special Discount Valid From"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialDiscountValidTo">
                                        Special Discount Valid to
                                    </label>
                                    <input type="text" id="specialDiscountValidTo"
                                           class="form-control specialDiscountValidTo date"
                                           name="specialDiscountValidTo" placeholder="Special Discount Valid to"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialRate">
                                        Special Rate
                                    </label>
                                    <input type="text" id="specialRate"
                                           class="form-control specialRate"
                                           name="specialRate" placeholder="Special Rate"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialRateValidFrom">
                                        Special Rate Valid From
                                    </label>
                                    <input type="text" id="specialRateValidFrom"
                                           class="form-control specialRateValidFrom date"
                                           name="specialRateValidFrom" placeholder="Special Rate Valid From"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="specialRateValidTo">
                                        Special Rate Valid to
                                    </label>
                                    <input type="text" id="specialRateValidTo"
                                           class="form-control specialRateValidTo date"
                                           name="specialRateValidTo" placeholder="Special Rate Valid to"
                                           required/>
                                </div>
                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                                <input type="hidden" name="entityTypeId" value="${session.getAttribute('entityTypeId')}">
                                <input type="hidden" name="schemeStatus" value="1">
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<script>

    $(function () {
        //Datetimepicker plugin
        $('.date').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        $('#productId').select2()
        $('#customer').select2()
        $('#state').select2()
        $('#zone').select2()
        $('#city').select2()
        $('#distributor').select2()

        // $('#batch').select2()
    });

    function getBatches(id)
    {
        var option = '';
        $.ajax({
            url: '/batch-register/product/'+id,
            dataType: 'json',
            type: 'GET',
            success: function(data) {
                for (var i=0; i<data.length; i++) {
                    option += '<option value="'+ data[i].batchNumber + '">' + data[i].batchNumber + '</option>';
                }
                $('#batch').empty()
                $('#batch').html(option);
                if(data.length===0)
                {
                    $('#batch').prop('disabled', true);
                }
                else {
                    $('#batch').prop('disabled', false);

                }
            },
            error: function(x, e) {
                console.log("Something went wrong!")
            }

        });
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>
</body>
</html>