<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Product Division</title>
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
%{--    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>--}%
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.css"/>
    <style>

    /*div.dataTables_scrollBody table tbody  td {*/
    /*    border-top: none;*/
    /*    padding: 0.9px;*/
    /*    text-align: center;*/
    /*    border-collapse: unset!important;*/
    /*}*/

    .editbtn
    {
        padding: 1px 9px;
    }
    .deletebtn
    {
        padding: 1px 9px;
    }

    tbody td {
        padding: 0px;
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
                    <h2>Product Division</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Product Division</li>
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
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                                data-target="#adddivisionModal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Product Division</font></font></button>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover fridgeTable dataTable">
                                <thead>
                                <tr>
%{--                                    <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Division</th>
                                    <th style="width: 20%">Division Short Name</th>
%{--                                    <th style="width: 20%">Manager</th>--}%
%{--                                    <th style="width: 20%">Entity</th>--}%
%{--                                    <th style="width: 20%">Entity Type</th>--}%
                                    <th style="width: 20%">Action</th>
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


<g:include view="controls/product/add-division.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>
%{--<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>--}%
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
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
%{--<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>--}%
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js"/>
%{--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>--}%
<script>

    var divisiontable;
    var id = null;
    $(function () {
        $("#cityIds").select2();
        divisionTable();
    /*    $("#cityIds").select2({
            minimumInputLength: 3,
            required: true,
            ajax: {
                url: '/city/get',
                data: function (params) {
                    var query = {
                        search: params.term,
                        type: "select2"
                    }
                    // Query parameters will be ?search=[term]&page=[page]
                    return query;
                },
                processResults: function (response) {
                    var data = [];
                    response.forEach(function (response, index) {
                        data.push({"pincode": response.pincode, "text": response.areaName, "id": response.id});
                    });
                    return {
                        results: data
                    };
                },
                cache: true
            }
        });
*/
       /* $('#cityIds').each(function() {
            $(this).select2({ dropdownParent: $(this).parent()});
        })*/
    });

    function divisionTable() {
        divisiontable = $(".fridgeTable").DataTable({
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
                searchPlaceholder: "Search Division"
            },
            ajax: {
                type: 'GET',
                url: '/division/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        console.log(json)
                        var editbtn = '<button type="button" ' + 'data-id="' + json.data[i].id +'"'+
                            'data-divisionName="' + json.data[i].divisionName + '"' +
                            'data-divisionShortName= "' + json.data[i].divisionShortName + '"' +
                            'data-zoneids="' + json.data[i].zoneIds + '"' +
                            'data-stateids="' + json.data[i].stateIds + '"' +
                            'data-cityids="' + json.data[i].cityIds + '"' +
                            'data-seriesId="' + json.data[i].seriesId + '"' +
                            'data-managerId="' + json.data[i].managerId + '"' +
                            'data-customerids="' + json.data[i].customerIds + '"' +
                            'data-entityId="' + json.data[i].entityId + '"' +
                            'data-entityType="' + json.data[i].entityTypeId + '"' +
                            '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#adddivisionModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'divisionName': json.data[i].divisionName,
                            'divisionShortName': json.data[i].divisionShortName,
                            // 'manager': json.manager[i].userName,
                            // 'entity': json.entity[i].entityName,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'divisionName', 'width': '20%'},
                {'data': 'divisionShortName', 'width': '20%'},
                // {'data': 'manager', 'width': '20%'},
                // {'data': 'entity', 'width': '20%'},
                // {'data': 'entitytype', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".divisionForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/division/update/' + id;
            type = 'POST'
        } else {
            url = '/division';
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
                Swal.fire("Success!", "Division Submitted Successfully", "success");
                divisionTable();
                $('#adddivisionModal').modal('hide');
            },
            error: function () {
                Swal.fire("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".divisionForm")[0].reset();
        $(".divisionTitle").text("Add Division")
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".divisionName").val($(this).attr('data-divisionName'));
        $(".divisionShortName").val($(this).attr('data-divisionShortName'));
        var zoneIds =$(this).attr('data-zoneids');
        $(".zoneIds").val(zoneIds.split(",")).change();
        var stateIds =$(this).attr('data-stateids');
        $(".stateIds").val(stateIds.split(",")).change();
        var cityIds =$(this).attr('data-cityIds');
        $(".cityIds").val(cityIds.split(",")).change();
        var customerIds =$(this).attr('data-customerids');
        $(".customerIds").val(customerIds.split(",")).change();
        $(".entityId").val($(this).attr('data-entityId')).change();
        $(".entityType").val($(this).attr('data-entityType')).change();

        $(".divisionTitle").text("Update Division");
    });


    $('.entityId').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityType").val(type);
    });

    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Division ?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/division/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                divisionTable();
                Swal.fire("Success!", "Division Deleted Successfully", "success");
            }, error: function () {
                Swal.fire("Error!", "Something went wrong", "error");
            }
        });
    }


</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>