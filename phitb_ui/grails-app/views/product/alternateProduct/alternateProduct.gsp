<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Alternate Product</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <style>
    /* This is the styling for the main content area which contains the table and the panel */
    .contentRow {
        display: flex;
        flex-wrap: nowrap;
        position: relative; /* Ensures that absolute positioning is relative to this container */
    }

    /* Styling for the container of the table */
    #tableContainer {
        flex-grow: 1; /* Takes up the remaining space */
        transition: margin-right 0.5s ease; /* Smooth transition for margin changes */
        /* Add your additional styles here */
    }

    /* Styling for the details panel */
    #detailsContainer {
        width: 300px; /* Width of the side panel */
        background-color: #fff; /* Background color for the side panel */
        overflow-y: auto; /* Allows scrolling within the side panel */
        position: fixed; /* Fixed position to stay in place during scroll */
        top: 0; /* Top position which will be set dynamically with JavaScript */
        right: -300px; /* Initially placed off-screen to the right */
        bottom: 0; /* Stretches from the top position to the bottom of the viewport */
        z-index: 1040; /* Ensures it's above other content */
        transition: right 0.5s ease; /* Smooth transition for showing and hiding */
    }

    /* Class to be added via JavaScript when the panel is open */
    #detailsContainer.open {
        right: 0; /* Brings the panel into view */
    }

    /* Styling for the toggle button */
    .toggle-btn {
        position: fixed; /* Fixed position to stay in place during scroll */
        top: 20px; /* Position from the top of the viewport */
        right: 320px; /* Position from the right of the viewport, adjust based on the panel's width */
        z-index: 1050; /* Ensures it's above the side panel */
        cursor: pointer; /* Changes the mouse cursor to indicate it's clickable */
        /* Add your additional styles here */
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
                    <h2>Product Group</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Alternate Product</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12 mt-4">
                <div class="row">
                    <div class="col-12 form-group form-float">
                        <span>Search by: </span>
                        <ul class="nav nav-tabs justify-content-start">
                            <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#productDetails"
                                                    aria-expanded="true">Product</a></li>
                            <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#CompanyDetails"
                                                    aria-expanded="false">Company</a></li>
                            <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#compositionDetails"
                                                    aria-expanded="false">Composition</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="productDetails" aria-expanded="true">
                <div class="col-lg-4 form-group  form-float">
                    <label for="product">
                        Product
                    </label>
                    <select style="width: 100%"  class="form-control show-tick product" name="productId" id="product">
%{--                         <option selected disabled>SELECT</option>--}%
%{--                         <g:each var="p" in="${productList}">--}%
%{--                             <option value="${p.id}">${p.productName}</option>--}%
%{--                         </g:each>--}%
                    </select>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: 5%;">#</th>
                                <th style="width: 25%;">Product</th>
                                <th>Composition</th>
                                <th>Company</th>
                                <th>Amt.</th>
                                <th>View</th>
                            </tr>
                            </thead>
                            <tbody id="saleProductsTableBody"
                                   style="white-space: normal !important; word-wrap: break-word;">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane" id="CompanyDetails" aria-expanded="false">
                <div class="col-lg-4 form-group  form-float">
                    <label for="company">
                        Company
                    </label>
                    <select style="width: 100%"  class="form-control show-tick company" name="companyId" id="company">
                        %{--                         <option selected disabled>SELECT</option>--}%
                        %{--                         <g:each var="p" in="${productList}">--}%
                        %{--                             <option value="${p.id}">${p.productName}</option>--}%
                        %{--                         </g:each>--}%
                    </select>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: 5%;">#</th>
                                <th style="width: 25%;">Product</th>
                                <th>Composition</th>
                                <th>Company</th>
                                <th>Amt.</th>
                                <th>View</th>
                            </tr>
                            </thead>
                            <tbody id="companyTableBody"
                                   style="white-space: normal !important; word-wrap: break-word;">

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane" id="compositionDetails" aria-expanded="false">
                <div class="col-lg-4 form-group  form-float">
                    <label for="composition">
                        Composition
                    </label>
                    <select style="width: 100%"  class="form-control show-tick composition" name="CompositionId" id="composition">
                        %{--                         <option selected disabled>SELECT</option>--}%
                        %{--                         <g:each var="p" in="${productList}">--}%
                        %{--                             <option value="${p.id}">${p.productName}</option>--}%
                        %{--                         </g:each>--}%
                    </select>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: 5%;">#</th>
                                <th style="width: 25%;">Product</th>
                                <th>Composition</th>
                                <th>Company</th>
                                <th>Amt.</th>
                                <th>View</th>
                            </tr>
                            </thead>
                            <tbody id="compositionTableBody"
                                   style="white-space: normal !important; word-wrap: break-word;">

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>


            <div id="detailsContainer" class="side-panel">
                <div class="card">
                    <div class="card-header">
                        Product Details
                    </div>

                    <div class="card-body">
                        <!-- Details content goes here -->
                        Details Panel Content
                    </div>
                </div>
            </div>

            <button class="btn btn-primary mb-2" onclick="toggleDetails()">
                <i class="fa fa-angle-double-right" id="collapseIcon"></i> Toggle Details
            </button>
        </div>
    </div>
</section>


%{--<g:include view="controls/sales/add-invoice-signature.gsp"/>--}%
<g:include view="controls/delete-modal.gsp"/>


<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
%{--<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>--}%
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
%{--<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>--}%
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
%{--<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>--}%
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js" type="text/javascript"/>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js" integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>


<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>

<script>

    window.onload = alignPanel;
    window.onresize = alignPanel;

    function alignPanel() {
        var tableOffsetTop = document.querySelector('.table').getBoundingClientRect().top;
        var detailsPanel = document.getElementById('detailsContainer');
        detailsPanel.style.top = tableOffsetTop + 'px';
    }

    function toggleDetails() {
        var detailsContainer = document.getElementById("detailsContainer");
        var collapseIcon = document.getElementById("collapseIcon");
        if (collapseIcon.classList.contains("fa-angle-double-right")) {
            collapseIcon.classList.remove("fa-angle-double-right");
            collapseIcon.classList.add("fa-angle-double-left");
            detailsContainer.classList.add("open");
            detailsContainer.style.right = "0";
        } else {
            collapseIcon.classList.remove("fa-angle-double-left");
            collapseIcon.classList.add("fa-angle-double-right");
            detailsContainer.classList.remove("open");
            detailsContainer.style.right = "-300px";
        }
    }

        $("#product").select2({
            /*data: products,*/
            dropdownAutoWidth: true,
            allowClear: true,
            ajax: {
                url: "/alternateproduct/getallproduct",
                dataType: 'json',
                quietMillis: 250,
                data: function (data) {
                    return {
                        search: data.term,
                        page: data.page || 1
                    };
                },
                processResults: function (response, params) {
                    params.page = params.page || 1
                    products = [];
                    var data = response
                    for (var i = 0; i < data.length; i++) {
                        if (!products.some(element => element.id === data[i].id))
                            products.push({id: data[i].id, text: data[i].productName});
                    }
                    return {
                        results: products,
                        pagination: {
                            more: (params.page * 10) < response.totalCount
                        }
                    };
                },
            }
        });


    $("#company").select2({
        /*data: products,*/
        dropdownAutoWidth: true,
        allowClear: true,
        ajax: {
            url: "/alternateproduct/getallcompany",
            dataType: 'json',
            quietMillis: 250,
            data: function (data) {
                return {
                    search: data.term,
                    page: data.page || 1
                };
            },
            processResults: function (response, params) {
                params.page = params.page || 1
                company = [];
                var data = response
                for (var i = 0; i < data.length; i++) {
                    if (!company.some(element => element.id === data[i].id))
                        company.push({id: data[i].id, text: data[i].companyName});
                }
                return {
                    results: company,
                    pagination: {
                        more: (params.page * 10) < response.totalCount
                    }
                };
            },
        }
    });


    $("#composition").select2({
        /*data: products,*/
        dropdownAutoWidth: true,
        allowClear: true,
        ajax: {
            url: "/alternateproduct/getallcomposition",
            dataType: 'json',
            quietMillis: 250,
            data: function (data) {
                return {
                    search: data.term,
                    page: data.page || 1
                };
            },
            processResults: function (response, params) {
                params.page = params.page || 1
                composition = [];
                var data = response
                for (var i = 0; i < data.length; i++) {
                    if (!composition.some(element => element.id === data[i].id))
                        composition.push({id: data[i].id, text: data[i].compositionName});
                }
                return {
                    results: composition,
                    pagination: {
                        more: (params.page * 10) < response.totalCount
                    }
                };
            },
        }
    });

</script>

</body>
</html>


