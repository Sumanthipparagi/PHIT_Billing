<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Account Mode Master</title>
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
                    <h2>Account Mode Master</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Account Mode Master</li>
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
                            data-target="#addAccountModeModal"><font style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;">Add Account Mode</font></font></button>
                </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover accountmodeTable dataTable">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>entityRegister</th>
                                    <th>Action</th>
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

<g:include view="controls/add-account-mode.gsp"/>
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



<script>

    var accountmodetable;
    var id = null;
    $(function () {
        accountmodeTable();
    });

    function accountmodeTable() {

        accountmodetable = $(".accountmodeTable").DataTable({
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
                searchPlaceholder: "Search State"
            },
            ajax: {
                type: 'GET',
                url: '/accountmodes/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-mode="' + json.data[i].mode + '"' +
                            '" data-entityRegister="' + json.data[i].entityId + '"' +
                            ' class="editbtn btn btn-warning  editbtn" data-toggle="modal" data-target="#addAccountModeModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'name': json.data[i].mode,
                            'entity': json.names[i].entityName,
                            'action': editbtn + ' ' + deletebtn

                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'id'},
                {'data': 'name'},
                {'data': 'entity'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".accountModeForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData)

        var url = '';
        var type = '';
        if (id) {
            url = '/accountmodes/update/' + id;
            type = 'POST'
        } else {
            url = '/accountmodes';
            type = 'POST'
        }

        console.log(type)
        $.ajax({

            url: url,
            type: type,
            data: formData,
            contentType: false,
            processData: false,
            success: function () {

                swal("Success!", "Account Mode Submitted Successfully", "success");
                accountmodeTable();
                $('#addAccountModeModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".accountModeTitle").text("Add Account Mode Master")
        $(".accountModeForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".mode").val($(this).data('mode'));
        $(".entityRegister").val($(this).data('entity'));
        var a = $(this).data('entity');
        $("#entityRegister").val(a).change()
        $(".accountModeTitle").text("Update Account Mode Master");
    });

    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/accountmodes/delete/' + id,
            dataType: 'json',
            success: function (data) {
                $('.deleteModal').modal('hide');
                accountmodeTable();
                swal("Success!", "Account Mode Deleted Successfully", "success");
            }, error: function (data) {
                swal("Error!", "Something went wrong", "error");
            }

        });
    }


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>
</body>
</html>