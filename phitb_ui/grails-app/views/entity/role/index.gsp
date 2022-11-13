<%@ page import="phitb_ui.UtilsService" %>
<g:set var="addPermission"
       value="${UtilsService.isPermitted(pageFeatures[0].name, (session.getAttribute("permittedFeatures").toString()))}"/>
<g:set var="viewPermission"
       value="${UtilsService.isPermitted(pageFeatures[1].name, (session.getAttribute("permittedFeatures").toString()))}"/>
<g:set var="editPermission"
       value="${UtilsService.isPermitted(pageFeatures[2].name, (session.getAttribute("permittedFeatures").toString()))}"/>
<g:set var="deletePermission"
       value="${UtilsService.isPermitted(pageFeatures[3].name, (session.getAttribute("permittedFeatures").toString()))}"/>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Role Master</title>
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
    <asset:stylesheet  src="/themeassets/plugins/iCheck/skins/all.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/sweetalert2/dist/sweetalert2.css" rel="stylesheet" />

    <style>

   /* div.dataTables_scrollBody table tbody  td {
        border-top: none;
        padding: 0.9px;
        text-align: center;
        border-collapse: unset!important;
    }

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
*/
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
                    <h2>Role Master</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Role Master</li>
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

                    <div class="header">
                    <!-- Row -->
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <section class="hk-sec-wrapper">
                                <div class="card-box table-responsive">

                                    <div class="row" style="padding-bottom: 2%">
                                        <div class="col-7">
                                            <g:if test="${addPermission}">

                                                <a class="addbtn btn btn-primary"
                                                   href="#rolenamegroup"><i class="fa fa-plus-circle"></i> Add Role
                                                </a><br/>
                                            </g:if>
                                        </div>
                                    </div>

                                    <table id="roleTable" class="table table-striped table-bordered w-100 display pb-30">
                                        <thead>
                                        <tr>
                                            %{--<th class="w-10">id</th>--}%
                                            <th class="w-50">Role</th>
                                            <th class="w-10">Action</th>
                                        </tr>
                                        </thead>
                                        %{--<tbody>
                                        <g:each in="${roles}" var="rl">
                                        <tr><td>${rl.name}</td><td></td></tr>
                                        </g:each>
                                        </tbody>--}%
                                    </table>
                                </div>
                            </section>
                        </div>

                    </div> <!-- end row -->
                    </div>
                    <div class="body">
                        <div class="row">
                            <div class="col-12">
                                <section>
                                    <div class="card-box table-responsive">
                                        <h4 class="m-t-0 header-title">Set Permissions
                                            <g:if test="${addPermission}">
                                            <button id="savebtn"
                                                    class="pull-left btn btn-sm btn-success waves-effect w-md mr-2 mb-2"
                                                    style="color:white">
                                                Save</button>
                                            </g:if>
                                        </h4>

                                        <div class="row" style="padding-bottom: 2%">
                                            <div class="col-7"></div>
                                            <div class="col-1"></div>
                                            <div class="col-4"></div>
                                        </div>

                                        <div class="form-group" id="rolenamegroup">
                                            <label class="whitecolortext">Role Name:</label>
                                            <input style="width: 40%" id="rolename" class="formstyle rolename form-control"
                                                   type="text" name="rolename"
                                                   placeholder="Role Name">
                                        </div>

                                        <table id="roleModficationTable"
                                               class="table table-bordered w-100 display pb-30"
                                               cellspacing="0" width="100%">
                                            <thead>
                                            <tr>
                                                <th>Feature</th>
                                                <th>Add</th>
                                                <th>View</th>
                                                <th>Edit</th>
                                                <th>Delete</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td><strong>Select All</strong></td>
                                                <td><strong><input type="checkbox"
                                                                   class="minimal1 selectadd"></strong>
                                                </td>
                                                <td><strong><input type="checkbox"
                                                                   class="minimal1 selectview"></strong>
                                                </td>
                                                <td><strong><input type="checkbox"
                                                                   class="minimal1 selectedit"></strong>
                                                </td>
                                                <td><strong><input type="checkbox"
                                                                   class="minimal1 selectsearch"></strong>
                                                </td>
                                            </tr>
                                            <g:set var="count" value="${0}" scope="page"/>
                                            <g:each in="${features}" var="fr" status="i">
                                                <g:if test="${i > count}">
                                                    <tr>
                                                        <g:if test="${fr.description.contains("Add")}">
                                                            <g:set var="featureDescription"
                                                                   value="${fr.description.replace("Add", " ")}"/>
                                                        </g:if>
                                                        <g:if test="${fr.description.contains("Edit")}">
                                                            <g:set var="featureDescription"
                                                                   value="${fr.description.replace("Edit", " ")}"/>
                                                        </g:if>
                                                        <g:if test="${fr.description.contains("View")}">
                                                            <g:set var="featureDescription"
                                                                   value="${fr.description.replace("View", " ")}"/>
                                                        </g:if>
                                                        <g:if test="${fr.description.contains("Delete")}">
                                                            <g:set var="featureDescription"
                                                                   value="${fr.description.replace("Delete", " ")}"/>
                                                        </g:if>
                                                        <td>${featureDescription}</td>
                                                        <td><input value="${i}" type="checkbox"
                                                                   class="minimal ${i}"></td>
                                                        <td><input value="${i + 1}" type="checkbox"
                                                                   class="minimal ${i + 1}"></td>
                                                        <td><input value="${i + 2}" type="checkbox"
                                                                   class="minimal ${i + 2}"></td>
                                                        <td><input value="${i + 3}" type="checkbox"
                                                                   class="minimal ${i + 3}"></td>
                                                        <g:set var="count" value="${i + 3}"
                                                               scope="page"/>
                                                    </tr>
                                                </g:if>
                                            </g:each>
                                            </tbody>
                                        </table>

                                        <input type="hidden" name="permissions"
                                               class="permissions">
                                        <input type="hidden" name="id" class="id" id="id">
                                    </div>
                                </section>
                            </div>
                        </div> <!-- end row -->
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>


<g:include view="controls/entity/add-role.gsp"/>
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
<asset:javascript src="/themeassets/plugins/iCheck/icheck.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>

<script>
    var rolesurl = "role/update";
    var addoredit = "edit";
    $(function () {

        displayMessageInParams();

        // Responsive Datatable
        roleTable();
    });

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-aero',
        radioClass: 'iradio_minimal-aero'
    });

    $('input[type="checkbox"].minimal1, input[type="radio"].minimal1').iCheck({
        checkboxClass: 'icheckbox_minimal-aero',
        radioClass: 'iradio_minimal-aero'
    });

    $("#checkAll").click(function () {
        $(".minimal").iCheck("toggle");
    });

    <g:if test="${deletePermission}">
    // $(document).on("click", ".deletebtn", function () {
    //     var id = $(this).data('id');
    //     var name = $(this).data('name');
    //     $(".modal-title").text("Are you sure?");
    //     $(".body-text").text("Delete role " + name + "?");
    //     $(".positivebtnform").prop("method", "post");
    //     $(".positivebtnform").prop("action", "/role/delete");
    //     $(".positiveinput").prop("value", id);
    // });

    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/role/delete/' + id,
            dataType: 'json',
            success: function (data) {
                $('.deleteModal').modal('hide');
                roleTable();
                Swal.fire("Success!", "Role Deleted Successfully", "success");
            }, error: function (data) {
                Swal.fire("Error!", "Something went wrong", "error");
            }

        });
    }
    </g:if>

    <g:if test="${addPermission}">
    $(document).on("click", ".addbtn", function () {
        $(".rolename").val("");
        $(".id").val("");
        for (var i = 1; i <= ${features.size()}; i++) {
            $("." + i).iCheck("uncheck");
        }
        if (this.hash !== "") {
            // Prevent default anchor click behavior
            event.preventDefault();

            // Store hash
            var hash = this.hash;

            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function () {

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        }
        rolesurl = "role";
        addoredit = "add";
        $("#savebtn").prop("disabled", false)

    });
    </g:if>
    <g:if test="${editPermission}">
    $(document).on("click", ".editbtn", function () {
        var id = $(this).data('editid');
        var role = $(this).data('editrole');
        var permissions = $(this).data('editpermissions');
        permissions = permissions.split(",");
        $(".rolename").val(role);
        $(".id").val(id);
        for (var i = 1; i <= ${features.size()}; i++) {
            $("." + i).iCheck("uncheck");
        }
        for (var i = 0; i < permissions.length; i++) {
            $("." + permissions[i]).iCheck("check");
        }
        if (this.hash !== "") {
            // Prevent default anchor click behavior
            event.preventDefault();

            // Store hash
            var hash = this.hash;

            // Using jQuery's animate() method to add smooth page scroll
            // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function () {

                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        }
        rolesurl = "role/update";
        addoredit = "edit";
        $("#savebtn").prop("disabled", false)
    });
    </g:if>
    <g:if test="${editPermission}">
    $("#savebtn").click(function () {
        var id = document.getElementById("id").value;
        var rolename = $("#rolename").val();
        // rolename = rolename.toLowerCase();
        if (rolename == undefined && rolename == "" && rolename == "admin") {

            Swal.fire("Error!", "Role Name can't be empty or admin", "error");
            return;
        }
        if ((addoredit == "add") || (id != undefined && id != "")) {
            console.log("id=" + id);
            var valuesArray = $('input:checkbox:checked').map(function () {
                return this.value;
            }).get().join(",");
            var permissions = $(".permissions").val(valuesArray);
            $.ajax({
                type: 'POST',
                url: rolesurl + "/" + id,
                data: {
                    'role': rolename,
                    'permissions': permissions.val()
                },
                success: function (data) {
                    window.location = "role";
                    /*$("#savebtn").prop("action", "roles");*/
                    $("#savebtn").prop("disabled", true);

                    roleTable();
                    Swal.fire("", "", "success");
                }
            });


        } else {
            Swal.fire("", "", "error");
        }
    });
    </g:if>
    $('.selectadd').on('ifToggled', function (event) {
        for (var i = 1; i <= ${features.size() - 3}; i += 4) {
            $("." + i).iCheck("toggle");
        }
    });

    $('.selectview').on('ifToggled', function (event) {
        for (var i = 2; i <= ${features.size() - 2}; i += 4) {
            $("." + i).iCheck("toggle");
        }
    });

    $('.selectedit').on('ifToggled', function (event) {
        for (var i = 3; i <= ${features.size() - 1}; i += 4) {
            $("." + i).iCheck("toggle");
        }
    });

    $('.selectsearch').on('ifToggled', function (event) {
        for (var i = 4; i <= ${features.size()}; i += 4) {
            $("." + i).iCheck("toggle");
        }
    });

    <g:if test="${viewPermission}">

    function roleTable() {
        $("#roleTable").DataTable({
            "order": [[0, "desc"]],
            sPaginationType: "simple_numbers",
            responsive: true,
            destroy: true,
            autoWidth: false,
            bJQueryUI: true,
            //sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true,
            language: {
                searchPlaceholder: "Search Role"
            },
            "ajax": {
                type: 'GET',
                url: 'role/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    var editbtn = "", deletebtn = "";
                    if (json == "") {
                        return return_data;
                    }
                    for (var i = 0; i < json.data.length; i++) {
                        <g:if test="${editPermission}">
                        var editbtn = '<a href="#roleModficationTable"' +
                            'class="editbtn btn btn-warning waves-effect w-xs mr-2 mb-2"' +
                            'data-editid="' + json.data[i].id + '"' +
                            'data-editrole="' + json.data[i].name + '"' +
                            'data-editpermissions="' + json.data[i].permittedFeatures + '"' +
                            'title="edit"  onclick=""><i class="fa fa-edit"></i> Edit</a>';
                        </g:if>

                        <g:if test="${deletePermission}">
                        var deletebtn = '<button ' +
                            'data-id="' + json.data[i].id + '"' +
                            'data-name="' + json.data[i].name + '"' +
                            'class="deletebtn btn btn-danger waves-effect w-xs mr-2 mb-2"' +
                            'data-toggle="modal" data-target=".deleteModal"' +
                            'title="delete"><i class="fa fa-trash"></i> Delete</button>';
                        </g:if>
                        var action = editbtn + "  " + deletebtn;
                        return_data.push({
                            // 'id': json.data[i].id,
                            'role': json.data[i].name,
                            'action': action
                        });
                    }
                    return return_data;
                }
            },
            "columns": [
                // {'data': 'id'},
                {'data': 'role'},
                {'data': 'action'}
            ]
        });

    }

   </g:if>

    <g:if test="${addPermission}">
    $('body').on("click", '#addRoleBtn', function () {
        $('#name').val("");
        $('#permittedFeatures').val("");

        $('#addRoleModalId').val("");
        $("#addRoleModalTitle").text("Add Role");
        $("#addRoleModalType").val("add");
        $("#addRoleForm").prop("action", "role/add");
    });
    </g:if>


    function displayMessageInParams() {
        var id = "${params.code}";

        //failed transations
        if (id == "2") {
            Swal.fire("Failed", "Not Exists", "error");
        }

        //for successful transactions
        if (id == "3") {
            Swal.fire("Role Added", " ", "success");
        }
        if (id == "4") {
            Swal.fire("Role Updated", " ", "success");
        }
        if (id == "5") {
            Swal.fire("Roles Deleted", " ", "success");
        }
        if (id == "7") {
            Swal.fire("", " ", "success");
        }
    }
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>