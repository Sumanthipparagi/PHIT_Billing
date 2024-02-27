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
        width: 325px; /* Width of the side panel */
        background-color: #fff; /* Background color for the side panel */
        overflow-y: auto; /* Allows scrolling within the side panel */
        position: fixed; /* Fixed position to stay in place during scroll */
        top: 355px; /* Adjusted top position to lower the panel from the top */
        right: -350px; /* Initially placed off-screen to the right */
        bottom: 0; /* Stretches from the top position to the bottom of the viewport */
        z-index: 1040; /* Ensures it's above other content */
        transition: right 0.5s ease; /* Smooth transition for showing and hiding */
    }

    /* Class to be added via JavaScript when the panel is open */
    #detailsContainer.open {
        right: 0; /* Brings the panel into view */
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
                <div class="col-lg-4 form-group form-float">
                    <label for="product">Product</label>
                    <div class="d-flex align-items-center justify-content-start">
                        <select class="form-control show-tick product" name="productId" id="product" style="flex-grow: 1;">
                            <!-- Options here -->
                        </select>
                        <div style="margin-left: 15px;"> <!-- Add spacing by wrapping button in a div -->
                            <button type="submit" id="prodSubmit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover productTable" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Product</th>
                                <th>Company</th>
                                <th>Composition</th>
                                <th>Mrp</th>
                                <th>View</th>
                                <th>Addtomylist</th>
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
                <div class="col-lg-3 form-group  form-float">
                    <label for="company">
                        Company
                    </label>
                    <select style="width: 100%"  class="form-control show-tick company" name="companyId" id="company">
                        %{--                         <option selected disabled>SELECT</option>--}%
                        %{--                         <g:each var="p" in="${productList}">--}%
                        %{--                             <option value="${p.id}">${p.productName}</option>--}%
                        %{--                         </g:each>--}%
                    </select>
                    <div> <!-- Add spacing by wrapping button in a div -->
                        <button type="submit" id="companysubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover companyTable" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Company</th>
                                <th>Product</th>
                                <th>Composition</th>
                                <th>Mrp</th>
                                <th>View</th>
                                <th>Addtomylist</th>
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
                <div class="col-lg-2 form-group  form-float">
                    <label for="composition">
                        Composition
                    </label>
                    <select style="width: 100%"  class="form-control show-tick composition" name="compositionId" id="composition" >
                        %{--                         <option selected disabled>SELECT</option>--}%
                        %{--                         <g:each var="p" in="${productList}">--}%
                        %{--                             <option value="${p.id}">${p.productName}</option>--}%
                        %{--                         </g:each>--}%
                    </select>
                    <div> <!-- Add spacing by wrapping button in a div -->
                        <button type="submit" id="compsubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>

%{--                    %{-<div role="tabpanel" class="tab-pane" id="compositionDetails" aria-expanded="false">--}%
%{--                    <div class="col-lg-2 form-group form-float">--}%
%{--                    <label for="composition">Composition</label>--}%
%{--                            <div class="d-flex align-items-center"> <!-- Flex container -->--}%
%{--                                <select class="form-control show-tick composition" name="compositionId" id="composition" style="flex: 1; margin-right: 10px;">--}%
%{--                           --}%
%{--                                </select>--}%
%{--                                    <button type="submit" class="btn btn-primary">Submit</button>--}%
%{--                                </div>--}%
%{--                            </div>--}%
%{--                        </div>  --}%



                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover compositionTable" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Composition</th>
                                <th>Product</th>
                                <th>Company</th>
                                <th>Mrp</th>
                                <th>View</th>
                                <th>Addtomylist</th>
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
                        <button type="button" class="close" aria-label="Close" onclick="hideDetailsPanel()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="card-body">
                        <!-- Details content goes here -->
                        Details Panel Content
                    </div>
                </div>
            </div>

%{--            <button class="btn btn-primary mb-2" onclick="toggleDetails()">--}%
%{--                <i class="fa fa-angle-double-right" id="collapseIcon"></i> Toggle Details--}%
%{--            </button>--}%
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

    viewPanel();

    function viewPanel() {
        $('body').on('click', '.view-btn', function () {
            var table = $(this).closest('table').DataTable(); // Get the DataTable instance
            var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
            var row = table.row(tr).data(); // Get the data for the row

            // Now you can use `row` to access the data for the clicked row
            console.log(row);

            // Example: Update the details in the side panel
            updateDetailsPanel(row);

            // Show the details panel
            showDetailsPanel();
        });
    }

    function updateDetailsPanel(rowData) {
        $('#detailsContainer .card-body').html('Product Name: ' + rowData.productName + '<br>Company: ' + rowData.company + '<br>MRP: ' + rowData.mrp + '<br>composition: ' + rowData.composition);
    }

    function showDetailsPanel() {
        var detailsContainer = document.getElementById("detailsContainer");
        detailsContainer.classList.add("open");
    }

    function hideDetailsPanel() {
        var detailsContainer = document.getElementById("detailsContainer");
        detailsContainer.classList.remove("open");
    }

    document.addEventListener("DOMContentLoaded", function () {
        // Initialize select2 elements
        initializeSelect2('#product', '/alternateproduct/getallproduct');
        initializeSelect2('#company', '/alternateproduct/getallcompany');
        initializeSelect2('#composition', '/alternateproduct/getallcomposition');

        // Attach event listeners for window resizing
        // window.onresize = alignPanel;

        // Event listener for the submit button
        document.querySelector('#compsubmit').addEventListener('click', function () {
            compositionTable();
        });

        document.querySelector('#companysubmit').addEventListener('click', function () {
            companyTable();
        });

        document.querySelector('#prodSubmit').addEventListener('click', function () {
            productTable();
        });

        // alignPanel();
    });

    function initializeSelect2(selector, url) {
        $(selector).select2({
            dropdownAutoWidth: true,
            allowClear: true,
            ajax: {
                url: url,
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        search: params.term,
                        page: params.page || 1
                    };
                },
                processResults: function (data, params) {
                    params.page = params.page || 1;
                    return {
                        results: $.map(data, function (item) {
                            return {
                                id: item.id,
                                text: item.productName || item.companyName || item.compositionName
                            };
                        }),
                        pagination: {
                            more: (params.page * 10) < data.totalCount
                        }
                    };
                },
                cache: true
            }
        });
    }

    function compositionTable() {

        // Your existing code to initialize the DataTabl
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });

        var compositionId = $('#composition').val();

        // Check if the DataTable instance already exists and destroy it if necessary
        if ($.fn.dataTable.isDataTable('.compositionTable')) {
            $('.compositionTable').DataTable().destroy();
        }

        // Initialize DataTable with new settings
        $(".compositionTable").DataTable({
            paging: true,
            responsive: {
                details: false
            },
            destroy: true, // Ensures the table can be reinitialized
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true, // Set to true if the server performs operations like sorting, pagination, etc.
            language: {
                searchPlaceholder: "Search Product"
            },
            ajax: {
                type: 'GET',
                url: "/alternateproduct/getproductbycompositionid",
                data: {
                    compositionId: compositionId
                },
                dataType: 'json',
                dataSrc: function (json) {
                        console.log('Update successful:', json);
                    var return_data = [];
                    // Use a for loop to transform each item in the response
                    for (var i = 0; i < json.length; i++) {
                        return_data.push({
                            composition: json[i].composition,
                            productName: json[i].productName,
                            company: json[i].company,
                            mrp: json[i].mrp
                        });
                    }
                    return return_data;
                }

                },
            columns: [
                {data: 'composition', width: '20%'},
                {data: 'productName', width: '20%'},
                {data: 'company', width: '20%'},
                {data: 'mrp', width: '20%'},
                // Adding the fifth column for the View button with render function
                {
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: '20%',
                    orderable: false
                },{
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">+MyList</button>';
                    },
                    width: '20%',
                    orderable: false
                }
            ]

        });
        loading.close()
    }

    $('.compositionTable tbody').on('click', '.view-btn', function() {
        var table = $('.compositionTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);

    });

    function companyTable() {

        // Your existing code to initialize the DataTabl
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });

        var companyId = $('#company').val();

        // Check if the DataTable instance already exists and destroy it if necessary
        if ($.fn.dataTable.isDataTable('.companyTable')) {
            $('.companyTable').DataTable().destroy();
        }

        // Initialize DataTable with new settings
        $(".companyTable").DataTable({
            paging: true,
            responsive: {
                details: false
            },
            destroy: true, // Ensures the table can be reinitialized
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true, // Set to true if the server performs operations like sorting, pagination, etc.
            language: {
                searchPlaceholder: "Search Product"
            },
            ajax: {
                type: 'GET',
                url: "/alternateproduct/getproductbycompanyid",
                data: {
                    companyId: companyId
                },
                dataType: 'json',
                dataSrc: function (json) {
                    console.log('Update successful:', json);
                    var return_data = [];
                    // Use a for loop to transform each item in the response
                    for (var i = 0; i < json.length; i++) {
                        return_data.push({
                            company: json[i].company,
                            productName: json[i].productName,
                            composition: json[i].composition,
                            mrp: json[i].mrp
                        });
                    }
                    return return_data;
                }

            },
            columns: [
                {data: 'company', width: '20%'},
                {data: 'productName', width: '20%'},
                {data: 'composition', width: '20%'},
                {data: 'mrp', width: '20%'},
                {
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: '20%',
                    orderable: false
                },
                {
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">+MyList</button>';
                    },
                    width: '20%',
                    orderable: false
                }
            ]

        });
        loading.close()
    }

    $('.companyTable tbody').on('click', '.view-btn', function() {
        var table = $('.companyTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);

    });


    function productTable() {

        // Your existing code to initialize the DataTabl
        var loading = Swal.fire({
            title: "Getting reports, Please wait!",
            html: '<img src="${assetPath(src: "/themeassets/images/3.gif")}" width="25" height="25"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            closeOnClickOutside: false
        });

        var productId = $('#product').val();

        // Check if the DataTable instance already exists and destroy it if necessary
        if ($.fn.dataTable.isDataTable('.productTable')) {
            $('.productTable').DataTable().destroy();
        }

        // Initialize DataTable with new settings
        $(".productTable").DataTable({
            paging: true,
            responsive: {
                details: false
            },
            destroy: true, // Ensures the table can be reinitialized
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true, // Set to true if the server performs operations like sorting, pagination, etc.
            language: {
                searchPlaceholder: "Search Product"
            },
            ajax: {
                type: 'GET',
                url: "/alternateproduct/getcompositionlistbyproductid",
                data: {
                    productId: productId
                },
                dataType: 'json',
                dataSrc: function (json) {
                    console.log('Update successful:', json);
                    var return_data = [];
                    // Use a for loop to transform each item in the response
                    for (var i = 0; i < json.length; i++) {
                        return_data.push({
                            productName: json[i].productName,
                            company: json[i].company,
                            composition: json[i].composition,
                            mrp: json[i].mrp
                        });
                    }
                    return return_data;
                }

            },
            columns: [
                {data: 'productName', width: '20%'},
                {data: 'company', width: '20%'},
                {data: 'composition', width: '20%'},
                {data: 'mrp', width: '20%'},
                {
                    data: null,
                    render: function (data, type, row) {
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: '20%',
                    orderable: false
                },
                {
                    data: null,
                    render: function (data, type, row) {
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">+MyList</button>';
                    },
                    width: '20%',
                    orderable: false
                }

            ]

        });
        loading.close()
    }

    $('.productTable tbody').on('click', '.view-btn', function() {
        var table = $('.productTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);

    });

    // function alignPanel() {
    //     var tableOffsetTop = document.querySelector('.table').getBoundingClientRect().top;
    //     var detailsPanel = document.getElementById('detailsContainer');
    //     detailsPanel.style.top = tableOffsetTop + 'px';
    // }

</script>

</body>
</html>


