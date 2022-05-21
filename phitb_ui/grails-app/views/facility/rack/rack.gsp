<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Rack</title>
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

/*    div.dataTables_scrollBody table tbody  td {*/
/*        border-top: none;*/
/*        padding: 0.9px;*/
/*        text-align: center;*/
/*        border-collapse: unset!important;*/
/*    }*/

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
                    <h2>Rack</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Rack</li>
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
                                data-target="#addRackModal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Rack</font></font></button>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover rackTable dataTable">
                                <thead>
                                <tr>
                                    %{--                                    <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Rack Name</th>
                                    <th style="width: 20%">Rack Code Name</th>
                                    <th style="width: 20%">CCM Enabled</th>
                                    <th style="width: 20%">Genaral Info</th>
                                    <th style="width: 20%">Companies</th>
                                    <th style="width: 20%">Floor Number</th>
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


<g:include view="controls/add-rack.gsp"/>
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

    var racktable;
    var id = null;
    $(function () {
        rackTable();
        // var $demoMaskedInput = $('.demo-masked-input');
        // $demoMaskedInput.find('.datetime').inputmask('d/m/y h:m:s', { placeholder: '__/__/____ __:__:__:__', alias:
        //         "datetime", hourFormat: '12' });

    });

    function rackTable() {
        racktable = $(".rackTable").DataTable({
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
                searchPlaceholder: "Search Rack"
            },
            ajax: {
                type: 'GET',
                url: '/rack/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        console.log(json)
                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-rackName="' + json.data[i].rackName + '"' +
                            '" data-entityRegister="' + json.data[i].entityId + '"' +
                            '" data-floorNumber="' + json.data[i].floorNumber + '"' +
                            '" data-entitytype="' + json.data[i].entityTypeId + '"' +
                            '" data-generalInfo="' + json.data[i].generalInfo + '"' +
                            '" data-rackCodeName="' + json.data[i].rackCodeName + '"' +
                            '" data-companies="' + json.data[i].companies + '"' +
                            '" data-cccEnabled="' + json.data[i].cccEnabled + '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addRackModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'rackName': json.data[i].rackName,
                            'floorNumber': json.data[i].floorNumber,
                            'generalInfo': json.data[i].generalInfo,
                            'rackCodeName': json.data[i].rackCodeName,
                            'companies': json.data[i].companies,
                            'cccEnabled': json.data[i].cccEnabled,
                            // 'entity': json.entity[i].entityName,
                            // 'entitytype': json.entityType[i].name,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'rackName', 'width': '20%'},
                {'data': 'rackCodeName', 'width': '20%'},
                {'data': 'cccEnabled', 'width': '20%'},
                {'data': 'generalInfo', 'width': '20%'},
                {'data': 'companies', 'width': '20%'},
                {'data': 'floorNumber', 'width': '20%'},
                // {'data': 'entity', 'width': '20%'},
                // {'data': 'entitytype', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".rackForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/rack/update/' + id;
            type = 'POST'
        } else {
            url = '/rack';
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
                swal("Success!", "Rack Submitted Successfully", "success");
                rackTable();
                $('#addRackModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".rackTitle").text("Add Rack")
        $(".rackForm")[0].reset();
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
        $(".rackTitle").text("Update Rack");
    });


    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityTypeId").val(type);
    });

    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Rack?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/rack/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                rackTable();
                swal("Success!", "Rack Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }


</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("facility-menu");
</script>
</body>
</html>