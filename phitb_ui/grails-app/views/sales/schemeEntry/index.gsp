<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Scheme Configuration</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/editor.dataTables.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />


    <style>

/*    div.dataTables_scrollBody table tbody  td {
        border-top: none;
        padding: 0.9px;
        text-align: center;
        border-collapse: unset!important;
    }*/

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
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="Alpino"></div>
        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Scheme Configuration</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Scheme Configuration</li>
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
        <div class="row clearfix">
%{--            <div class="col-lg-3">--}%
%{--                <div class="form-group">--}%
%{--                    <label>Entities</label>--}%
%{--                    <select class="form-control" id="entitySelect"></select>--}%
%{--                </div>--}%
%{--            </div>--}%
%{--            <div class="col-lg-3">--}%
%{--                <div class="form-group">--}%
%{--                    <label>Division</label>--}%
%{--                    <select class="form-control" id="divisionSelect"></select>--}%
%{--                </div>--}%
%{--            </div>--}%
%{--            <div class="col-lg-3">--}%
%{--                <div class="form-group">--}%
%{--                    <label>Batch Status</label>--}%
%{--                    <select class="form-control" id="batchStatus"></select>--}%
%{--                </div>--}%
%{--            </div>--}%
        </div>
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="header">
                        <a href="/add-scheme-entry">
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn"
                                ><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Scheme</font></font></button>
                        </a>
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover schemeTable dataTable">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Batch Number</th>
                                    <th>Slb1 Min Qty</th>
                                    <th>Slb1 Sch Qty</th>
                                    <th>Slb2 Min Qty</th>
                                    <th>Slb2 Sch Qty</th>
                                    <th>Slb3 Min Qty</th>
                                    <th>Slb3 Sch Qty</th>
%{--                                    <th>Status</th>--}%
                                    <th>Action</th>
                                </tr>
                                </thead>
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


<g:include view="controls/sales/add-scheme.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/jquery.dataTables.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/dataTables.editor.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
%{--<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>--}%
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js" type="text/javascript"/>

<script>

    var productcategorytable;
    var id = null;
    $(function () {
        schemeTable();

    });

    function schemeTable() {
        productcategorytable = $(".schemeTable").DataTable({
            "order": [[0, "desc"]],
            sPaginationType: "simple_numbers",
            responsive: {
                details: false
            },
            destroy: true,
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true,
            language: {
                searchPlaceholder: "Search Scheme"
            },
            ajax: {
                type: 'GET',
                url: '/scheme-entry/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        console.log(json)
                        var editbtn = '<a href="/update-scheme-entry/' + json.data[i].id
                            +'"><button type="button" data-id="' +
                            json.data[i].id +
                            '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button></a>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>';
                        return_data.push({
                            'product': json.products[i].productName,
                            'batch': json.data[i].batch,
                            'slab1minQty': json.data[i].slab1MinQty,
                            'slab1SchQty':json.data[i].slab1SchemeQty,
                            'slab2minQty': json.data[i].slab2MinQty,
                            'slab2SchQty':json.data[i].slab2SchemeQty,
                            'slab3minQty': json.data[i].slab3MinQty,
                            'slab3SchQty':json.data[i].slab3SchemeQty,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }

                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'product', 'width': '20%'},
                {'data': 'batch', 'width': '20%'},
                {'data': 'slab1minQty', 'width': '20%'},
                {'data': 'slab1SchQty', 'width': '20%'},
                {'data': 'slab2minQty', 'width': '20%'},
                {'data': 'slab2SchQty', 'width': '20%'},
                {'data': 'slab3minQty', 'width': '20%'},
                {'data': 'slab3SchQty', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".productCategoryForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/product-category/update/' + id;
            type = 'POST'
        } else {
            url = '/product-category';
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
                swal("Success!", "Product Category Submitted Successfully", "success");
                schemeTable();
                $('#addSchemeModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".schemeTitle").text("Add Scheme");
        $(".productCategoryForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".categoryName").val($(this).attr('data-categoryName'));
        $(".restrictedCategory").val($(this).attr('data-restrictedCategory')).change();
        $(".accessRestriction").val($(this).attr('data-accessRestriction')).change();
        $(".entityId").val($(this).attr('data-entityId')).change();
        $(".entityType").val($(this).attr('data-entityType')).change();
        $(".divisionTitle").text("Update Product Category");
    });

    $('.entityId').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityTypeId").val(type);
    });



    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Scheme ?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/scheme-entry/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                schemeTable();
                swal("Success!", "Scheme Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }


</script>


</body>
</html>