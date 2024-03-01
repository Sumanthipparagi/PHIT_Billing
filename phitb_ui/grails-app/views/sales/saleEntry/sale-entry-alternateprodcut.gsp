<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Alternate Product</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
<asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
<!-- JQuery DataTable Css -->
<asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
<!-- Custom Css -->
%{--<asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>--}%
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
    /* Existing styles */
    html, body {
        height: 100%;
        margin: 0;
    }

    .content {
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    .header, .footer {
        flex-shrink: 0; /* Prevents these elements from shrinking */
    }

    .main-content {
        flex-grow: 1; /* Allows this element to expand to fill available space */
        overflow-y: auto; /* Adds scroll to this element if content overflows */
    }

    .contentRow {
        display: flex;
        flex-wrap: nowrap;
        position: relative;
    }

    #tableContainer {
        flex-grow: 1;
        transition: margin-right 0.5s ease;
    }

    #detailsContainer {
        width: 325px;
        background-color: #fff;
        overflow-y: auto;
        position: fixed;
        top: 355px; /* Adjusted top position to lower the panel from the top */
        right: -350px;
        bottom: 0;
        z-index: 1040; /* Ensures it's above other content */
        transition: right 0.5s ease; /* Smooth transition for showing and hiding */
    }

    #detailsContainer.open {
        right: 0;
    }

    /* New style for changing the background color */
    body {
        background-color: #f4f7f6; /* Sets the background color of the entire page */
    }

    .nav-item .nav-link {
        color: #888;
        border-radius: 25px;
        margin-right: 10px;
        padding: 5px 15px;
        font-size: 14px; /* Adjust the font size as needed */
    }

    /* Ensure active tab color is different if needed */
    .nav-item .nav-link.active {

        border-radius: 35px;
        /* Add any other styles for the active state */
    }

    .nav-tabs {
        border-bottom: none;
    }

    /* Alternatively, if the line is caused by the active tab, you might need to target the active nav-link */
    .nav-tabs .nav-link.active {
        border-color: transparent; /* This makes the bottom border transparent */
    }

    .view-btn {
        background-color: #2CA8FF; /* Sets the button color */
        color: white; /* Sets the text color to white for better readability */
        border: none; /* Optional: Removes the border */
    }

    .block-header h2 {
        color: #616161; /* Sets the color */
    }

    .form-group.form-float select.form-control {
        width: 300px; /* Set your desired width */
    }


    </style>
</head>

<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"></div>

    </div>
</div>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Alternate Products</h2>
%{--                    <ul class="breadcrumb padding-0">--}%
%{--                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>--}%
%{--                        <li class="breadcrumb-item active">Alternate Product </li>--}%
%{--                    </ul>--}%
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12 mt-4">
                <div class="d-flex align-items-center">
                    <span>Search by:</span>
                    <!-- Add ml-2 for a small margin to the left of the tabs. Adjust the number for larger spacing -->
                    <ul class="nav nav-tabs ml-2">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#productDetails" aria-expanded="true">Product</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#CompanyDetails" aria-expanded="false">Company</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#compositionDetails" aria-expanded="false">Composition</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>



        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="productDetails" aria-expanded="true">
%{--                <div class="col-lg-3 form-group form-float">--}%
%{--                    <label for="product">Product</label>--}%

%{--                    <div class="d-flex align-items-center justify-content-start">--}%
%{--                        <select class="form-control show-tick product" name="productId" id="product"--}%
%{--                                style="flex-grow: 1;">--}%
%{--                            <!-- Options here -->--}%
%{--                        </select>--}%

%{--                        <div style="margin-left: 15px;">--}%
%{--                            <button type="submit" id="prodSubmit" class="btn btn-primary">Submit</button>--}%
%{--                        </div>--}%
%{--                    </div>--}%
%{--                </div>--}%

                <div class="col-lg-3 form-group form-float">
                    <label for="product">Product</label>
                    <div>
                        <select class="form-control show-tick product" name="productId" id="product" style="width: 300px;">
                            <!-- Options here -->
                        </select>
                    </div>
                    <div style="margin-top: 15px;">
                        <button type="submit" id="prodSubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover productTable"
                               style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Product</th>
                                <th>Company</th>
                                <th>Composition</th>
                                <th>Mrp</th>
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
%{--                <div class="col-lg-2 form-group  form-float">--}%
%{--                    <label for="company">--}%
%{--                        Company--}%
%{--                    </label>--}%
%{--                    <select style="width: 100%" class="form-control show-tick company" name="companyId" id="company">--}%

%{--                    </select>--}%

%{--                    <div>--}%
%{--                        <button type="submit" id="companysubmit" class="btn btn-primary">Submit</button>--}%
%{--                    </div>--}%
%{--                </div>--}%


                <div class="col-lg-3 form-group form-float">
                    <label for="company">Company</label>
                    <div>
                        <select class="form-control show-tick product" name="companyId" id="company" style="width: 300px;">
                            <!-- Options here -->
                        </select>
                    </div>
                    <div style="margin-top: 15px;">
                        <button type="submit" id="companysubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>



                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover companyTable"
                               style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Product</th>
                                <th>Company</th>
                                <th>Composition</th>
                                <th>Mrp</th>
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
%{--                <div class="col-lg-2 form-group form-float" style="display: flex; align-items: flex-start;">--}%
%{--                    <div style="flex-grow: 1; margin-right: 10px;">--}%
%{--                        <label for="composition">--}%
%{--                            Composition--}%
%{--                        </label>--}%
%{--                        <select style="width: 100%;" class="form-control show-tick composition" name="compositionId" id="composition">--}%
%{--                            <!-- Options go here -->--}%
%{--                        </select>--}%
%{--                    </div>--}%
%{--                    <div style="margin-top: 28px;"> <!-- Adjust this value as needed to align the button with the dropdown -->--}%
%{--                        <button type="submit" id="compsubmit" class="btn btn-primary">Submit</button>--}%
%{--                    </div>--}%
%{--                </div>--}%


                <div class="col-lg-3 form-group form-float">
                    <label for="composition">Composition</label>
                    <div>
                        <select class="form-control show-tick product" name="compositionId" id="composition" style="width: 300px;">
                            <!-- Options here -->
                        </select>
                    </div>
                    <div style="margin-top: 15px;">
                        <button type="submit" id="compsubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>



                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped table-hover compositionTable"
                               style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: auto;">Product</th>
                                <th>Company</th>
                                <th>Composition</th>
                                <th>Mrp</th>
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
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
%{--<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>--}%
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
%{--<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>--}%
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js" type="text/javascript"/>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.flash.min.js"></script>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
        integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
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
            console.log(row);

            updateDetailsPanel(row);

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

        initializeSelect2('#product', '/alternateproduct/getallproduct');
        initializeSelect2('#company', '/alternateproduct/getallcompany');
        initializeSelect2('#composition', '/alternateproduct/getallcomposition');

        document.querySelector('#compsubmit').addEventListener('click', function () {
            compositionTable();
        });

        document.querySelector('#companysubmit').addEventListener('click', function () {
            companyTable();
        });

        document.querySelector('#prodSubmit').addEventListener('click', function () {
            productTable();
        });

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
                    for (var i = 0; i < json.productList.length; i++) {
                        return_data.push({
                            composition: json.productList[i].composition,
                            productName: json.productList[i].productName,
                            company: json.productList[i].company,
                            mrp: json.productList[i].mrp
                        });
                    }
                    return return_data;
                }

            },
            columns: [
                {data: 'productName', width: 'auto'},
                {data: 'company', width: 'auto'},
                {data: 'composition', width: 'auto'},
                {data: 'mrp', width: 'auto'},
                // Adding the fifth column for the View button with render function
                {
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: 'auto',
                    orderable: false
                },
            ]

        });
        loading.close()
    }

    $('.compositionTable tbody').on('click', '.view-btn', function () {
        var table = $('.compositionTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);

    });

    function companyTable() {

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

        if ($.fn.dataTable.isDataTable('.companyTable')) {
            $('.companyTable').DataTable().destroy();
        }

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
                    for (var i = 0; i < json.productList.length; i++) {
                        return_data.push({
                            composition: json.productList[i].composition,
                            productName: json.productList[i].productName,
                            company: json.productList[i].company,
                            mrp: json.productList[i].mrp
                        });
                    }
                    return return_data;
                }

            },
            columns: [
                {data: 'productName', width: 'auto'},
                {data: 'company', width: 'auto'},
                {data: 'composition', width: 'auto'},
                {data: 'mrp', width: 'auto'},
                {
                    data: null,
                    render: function (data, type, row) {
                        // Assuming 'id' is a property in your row data that you want to associate with the button
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: '20%',
                    orderable: false
                },
            ]

        });
        loading.close()
    }

    $('.companyTable tbody').on('click', '.view-btn', function () {
        var table = $('.companyTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);

    });


    function productTable() {

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
                    for (var i = 0; i < json.productList.length; i++) {
                        return_data.push({
                            composition: json.productList[i].composition,
                            productName: json.productList[i].productName,
                            company: json.productList[i].company,
                            mrp: json.productList[i].mrp
                        });
                    }
                    return return_data;
                }

            },
            columns: [
                {data: 'productName', width: 'auto'},
                {data: 'company', width: 'auto'},
                {data: 'composition', width: 'auto'},
                {data: 'mrp', width: 'auto'},
                {
                    data: null,
                    render: function (data, type, row) {
                        return '<button class="btn btn-info view-btn" data-id="' + row.id + '">View</button>';
                    },
                    width: 'auto',
                    orderable: false
                },

            ]

        });
        loading.close()
    }

    $('.productTable tbody').on('click', '.view-btn', function () {
        var table = $('.productTable').DataTable(); // Get the DataTable instance
        var tr = $(this).closest('tr'); // Find the closest tr parent to get the row
        var row = table.row(tr); // Get the DataTable row
        var rowData = row.data(); // Get the data for the row

        console.log(rowData);
    });

    document.addEventListener("DOMContentLoaded", function () {
        // Your existing initialization code...

        // Adjust columns on window resize
        $(window).resize(function() {
            $('.dataTable').DataTable().columns.adjust().draw();
        });

        // Adjust columns when tabs are switched and DataTable becomes visible
        $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
            $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
        });
    });



</script>

</body>
</html>


