<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Product Register</title>
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

/*
    div.dataTables_scrollBody table tbody  td {
        border-top: none;
        padding: 0.9px;
        text-align: center;
        border-collapse: unset!important;
    }
*/

    .editbtn
    {
        padding: 1px 9px;
    }
    .deletebtn
    {
        padding: 1px 9px;
    }

    tbody td {
        padding: 0;
    }

    </style>

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
                    <h2>Product Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Product Register</li>
                    </ul>
                </div>
%{--                <div class="col-lg-7 col-md-7 col-sm-12">--}%
%{--                    <div class="input-group m-b-0">--}%
%{--                        <input type="text" class="form-control" placeholder="Search...">--}%
%{--                        <span class="input-group-addon">--}%
%{--                            <i class="zmdi zmdi-search"></i>--}%
%{--                        </span>--}%
%{--                    </div>--}%
%{--                </div>--}%
            </div>
        </div>
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Basic</strong> Examples </h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu slideUp">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                            <li class="remove">--}%
                    %{--                                <a role="button" class="boxs-close"><i class="zmdi zmdi-close"></i></a>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
                    <div class="header">
                        <a href="/product/add-product">  <button type="button"
                                                                             class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"><font style="vertical-align: inherit;"><font
                                    style="vertical-align: inherit;">Add New Product</font></font></button></a>


                    </div>


                    <div class="body">

                        <button type="button" class="btn btn-round btn-outline-primary  addbtn"  id="exportWorksheet" >Export</button>
                        <br><br>
                        <div class="table-responsive">
                            <table
                                    class="table table-bordered table-striped table-hover productRegisterTable dataTable">
                                <thead>
                                <tr>
                                    %{--                                    <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Action</th>
                                    <th style="width: 20%">Product Name</th>
                                    <th style="width: 20%">HSN code</th>
                                    <th style="width: 20%">MRP</th>
                                    <th>Category</th>
                                    <th>GST</th>
                                    <th>Company</th>
                                </tr>
                                </thead>
                                %{--                                <tfoot>--}%
                                %{--                                <tr>--}%
                                %{--                                    <th>ID</th>--}%
                                %{--                                    <th>Name</th>--}%
                                %{--                                    <th>entityRegister ID</th>--}%
                                %{--                                    <th>Action</th>--}%
                                %{--                                </tr>--}%
                                %{--                                </tfoot>--}%
                                <tbody>

                                </tbody>
                            </table>
                        </div>
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
<asset:javascript src="/themeassets/plugins/jsonToExcel/xlsx.full.min.js"/>

<script>

    var productregister;
    var id = null;
    $(function () {
        userRegisterTable();
        // var $demoMaskedInput = $('.demo-masked-input');
        // $demoMaskedInput.find('.datetime').inputmask('d/m/y h:m:s', { placeholder: '__/__/____ __:__:__:__', alias:
        //         "datetime", hourFormat: '12' });

    });

    function userRegisterTable() {
        productregister = $(".productRegisterTable").DataTable({
            "order": [[0, "asc"]],
            sPaginationType: "simple_numbers",
            responsive: {
                details: true
            },
            destroy: true,
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true,
            language: {
                searchPlaceholder: "Search Product Register"
            },
            ajax: {
                type: 'GET',
                url: '/product/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var editbtn =
                            '<a href="/product/update-product/' + json.data[i].id
                            +'"><button type="button" data-id="' + json.data[i].id +'"class="editbtn btn btn-sm btn-warning  editbtn"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button></a>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        var categoryName = ""
                        if(json.data[i]?.category != null)
                        {
                            categoryName = json.data[i]?.category?.categoryName
                        }
                        var marketingCompanyName = ""
                        if(json.data[i].marketingCompany)
                        {
                            marketingCompanyName = json.data[i].marketingCompany.entityName
                        }
                        return_data.push({
                            'action': editbtn + ' ' + deletebtn,
                            /*'id': json.data[i].id,*/
                            'productName': json.data[i].productName,
                            'hsnCode': json.data[i].hsnCode,
                            'mrp':json.data[i].mrp,
                            'category':categoryName,
                            'gst':json.data[i].tax.taxName + " ("+json.data[i].tax.taxValue+"%)",
                            'company': marketingCompanyName
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'action'},
                {'data': 'productName'},
                {'data': 'hsnCode'},
                {'data': 'mrp'},
                {'data': 'category'},
                {'data': 'gst'},
                {'data': 'company', 'width': '20%'},

            ]
        });
    }

    $(".entityRegister").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/user-register/update/' + id;
            type = 'POST'
        } else {
            url = '/user-register';
            type = 'POST'
        }

        console.log(type);
        $.ajax({
            url: url,
            type: type,
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                swal("Success!", "User Register Submitted Successfully", "success");
                rackTable();
                $('#addEntityRegisterModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".entityRegisterTitle").text("Add Product")
        $(".entityRegisterForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".rackName").val($(this).attr('data-rackName'));
        $(".floorNumber").val($(this).attr('data-floorNumber'));
        $(".generalInfo").val($(this).attr('data-generalInfo'));
        $(".rackCodeName").val($(this).attr('data-rackCodeName'));
        $(".companies").val($(this).attr('data-companies'));
        $(".ccmEnabled").val($(this).attr('data-cccEnabled'));
        $(".entityRegister").val($(this).attr('data-entity'));
        $("#entityRegister").val($(this).attr('data-entity')).change()
        $(".entityType").val($(this).attr('data-entitytype')).change()
        $(".rackTitle").text("Update User");
    });


    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Product?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/product/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                userRegisterTable();
                Swal.fire("Success!", "Product Deleted Successfully", "success");
            }, error: function () {
                $('.deleteModal').modal('hide');
                Swal.fire("Unable to Delete!", "Please make sure the batch is not used in either sales or purchase.", "error");
            }
        });
    }


    $( document ).ready(function() {
        $("#exportWorksheet").click(function() {
            var waitSwal = Swal.fire({
                title:"Please Wait!",
                text: "We are exporting the products",
                icon: "warning",
                showCloseButton: false,
                showCancelButton: false,
                showConfirmButton: false,
                allowEscapeKey: false,
                allowEnterKey: false,
                allowOutsideClick: false
            });
            $.ajax({
                url: "/product/product-export",
                type: "GET",
                contentType: false,
                processData: false,
                success: function(data) {
                    waitSwal.close();
                    var jsonDataObject = eval(data);
                    exportWorksheet(jsonDataObject);
                },
                error: function () {
                    waitSwal.close();
                    Swal.fire("Error!", "Something went wrong", "error");

                }
            });
        });

      /*  $("#exportWorksheetPlus").click(function() {
            var josnData = $("#josnData").val();
            var jsonDataObject = eval(josnData);
            exportWSPlus(jsonDataObject);
        });*/

    });

    function exportWorksheet(jsonObject) {
        var myFile = "product-report.xlsx";
        var myWorkSheet = XLSX.utils.json_to_sheet(jsonObject);
        var myWorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(myWorkBook, myWorkSheet, "Product-List");
        XLSX.writeFile(myWorkBook, myFile);
    }
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>