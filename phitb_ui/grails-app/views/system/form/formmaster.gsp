<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Form Master</title>
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
    <asset:stylesheet  src="/themeassets/plugins/select-2-editor/select2.min.css" rel="stylesheet" />

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
                    <h2>Form Master</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Form Master</li>
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
                                data-target="#addFormModal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Form</font></font></button>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover formTable dataTable">
                                <thead>
                                <tr>
%{--                                    <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Form Name</th>
                                    <th style="width: 20%">Form Type</th>
%{--                                    <th style="width: 20%">Config Allowed</th>--}%
%{--                                    <th style="width: 20%">Entity Type</th>--}%
%{--                                    <th style="width: 20%">Entity</th>--}%
%{--                                    <th style="width: 20%">Created User</th>--}%
%{--                                    <th style="width: 20%">Modified User</th>--}%
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

<g:include view="controls/add-form-modal.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
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
<asset:javascript  src="/themeassets/plugins/select-2-editor/select2.js" />

<script>

    var formtable;
    var id = null;
    $(function () {
        formTable();
       var t =$('.entity').find("option:first").attr("selected", true);

        console.log(t)
    });

    function formTable() {
        formtable = $(".formTable").DataTable({
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
                searchPlaceholder: "Search Form"
            },
            ajax: {
                type: 'GET',
                url: '/form/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {

                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-formName="' + json.data[i].formName + '"' +
                            '" data-entity="' + json.data[i].entityId + '"' +
                            '" data-entitytype="' + json.data[i].entityTypeId + '"' +
                            '" data-createduser="' + json.data[i].createdUser + '"' +
                            '" data-modifieduser="' + json.data[i].modifiedUser + '"' +
                            '" data-formButtonName="' + json.data[i].formButtonName + '"' +
                            '" data-formType="' + json.data[i].formType + '"' +
                            '" data-formname="' + json.data[i].formName + '"' +
                            '" data-configAllowed="' + json.data[i].configAllowed + '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addFormModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            // 'id': json.data[i].id,
                            'formname': json.data[i].formName,
                            'formType': json.data[i].formType,
                            'confallowed': json.data[i].configAllowed === "1" ? "YES" : "NO",
                            // 'entity': json.entity[i].entityName,
                            // 'entitytype': json.entityType[i].name,
                            // 'createduser': json.createduser[i].userName,
                            // 'modifieduser': json.modifiedUser[i].userName,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'formname', 'width': '20%'},
                {'data': 'formType', 'width': '20%'},
                // {'data': 'confallowed', 'width': '20%'},
                // {'data': 'entitytype', 'width': '20%'},
                // {'data': 'entity', 'width': '20%'},
                // {'data': 'createduser', 'width': '20%'},
                // {'data': 'modifieduser', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".formForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData)

        var url = '';
        var type = '';
        if (id) {
            url = '/form/update/' + id;
            type = 'POST'
        } else {
            url = '/form';
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

                Swal.fire("Success!", "Form Submitted Successfully", "success");
                formTable();
                $('#addFormModal').modal('hide');
            },
            error: function () {
                Swal.fire("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $('#entityRegister').select2();
        $(".formTitle").text("Add Form")
        $(".formForm")[0].reset();
        $('.entity').prop('selectedIndex',0);
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".formName").val($(this).attr('data-formName'));
        $(".formButtonName").val($(this).attr('data-formButtonName'));
        $(".formType").val($(this).attr('data-formType'));
        $(".entity").val($(this).data('entity')).change();
        $(".entitytype").val($(this).attr('data-entitytype'));
        $("#configAllowed").val($(this).attr('data-configAllowed'));
        $("#entityRegister").val($(this).data('entity')).change()
        $('#entityRegister').select2();
        $(".formTitle").text("Update Form");
    });


    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityTypeId").val(type);
    });


    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Form ?");
    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/form/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                formTable();
                Swal.fire("Success!", "Form Deleted Successfully", "success");
            }, error: function () {
                Swal.fire("Error!", "Something went wrong", "error");
            }
        });
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("system-menu");
</script>
</body>
</html>