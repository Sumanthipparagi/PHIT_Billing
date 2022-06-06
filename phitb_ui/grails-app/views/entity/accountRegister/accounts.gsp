<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Accounts</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css">
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
<style>
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
<g:include view="controls/entity/add-accounts.gsp"/>
<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Accounts</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="#">Accounts</a></li>
                        <li class="breadcrumb-item active">Accounts</li>
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


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Inline</strong> Layout</h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu dropdown-menu-right">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
                    <div class="body">
                    <button type="button" class="btn btn-primary addbtn" data-toggle="modal"
                            data-target="#addaccountsModal">Add Accounts</button>
                    <div class="col-md-12">
                        <h4 class="card-title"></h4>
                        <div id="treeview1" class=""></div>
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
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-treeview-master/dist/bootstrap-treeview.min.js"/>

<script>

    $(function () {
        var defaultData = [
            <g:each var="i" in="${account}">
                <g:if test="${i.subAccountType == 0}">
                {
                    text: '${i.accountName}',
                    href: '${i.accountName}',
                    nodes: [
                    <g:each var="j" in="${account}">
                        <g:if test="${j.subAccountType == i.id}">
                        {text: '${j.accountName}', href: '${j.accountName}',
                            nodes:[
                            <g:each var="k" in="${account}">
                                <g:if test="${k.subAccountType == j.id}">
                                {text: '${k.accountName}', href: '${k.accountName}'},
                                    </g:if>
                                </g:each>
                            ]},
                        </g:if>
                        </g:each>
                    ]
                },
                </g:if>
            </g:each>
        ];


        $('#treeview1').treeview({
            levels: 1,
            selectedBackColor: "#313740",
            onhoverColor: "rgba(0, 0, 0, 0.05)",
            expandIcon: 'ti-plus',
            collapseIcon: 'ti-minus',
            nodeIcon: 'fa fa-folder fa-2xl',
            data: defaultData,
        });
    });


    %{--$(document).ready(function() {--}%
    %{--    $.ajax({--}%
    %{--        url:'${createLink(controller: 'accountRegister', action: 'getTreeData')}',--}%
    %{--        dataType:"json",--}%
    %{--        success:function(data){--}%
    %{--            console.log(data)--}%
    %{--            $('#treeview1').treeview({--}%
    %{--                selectedBackColor: "#313740",--}%
    %{--                onhoverColor: "rgba(0, 0, 0, 0.05)",--}%
    %{--                expandIcon: 'ti-plus',--}%
    %{--                collapseIcon: 'ti-minus',--}%
    %{--                nodeIcon: 'fa fa-folder fa-2xl',--}%
    %{--                data:data--}%
    %{--            });--}%
    %{--        }--}%
    %{--    });--}%

    %{--});--}%



    // $(".addaccountsForm").submit(function (event) {
    //     //disable the default form submission
    //     event.preventDefault();
    //     //grab all form data
    //     var formData = new FormData(this);
    //     console.log(formData);
    //     var url = '';
    //     var type = '';
    //     if (id) {
    //         url = '/account-register/update/' + id;
    //         type = 'POST'
    //     } else {
    //         url = '/account-register';
    //         type = 'POST'
    //     }
    //     console.log(type);
    //     $.ajax({
    //         url: url,
    //         type: type,
    //         data: formData,
    //         contentType: false,
    //         processData: false,
    //         success: function () {
    //             swal("Success!", "Submitted Successfully", "success");
    //             $('#addaccountsModal').modal('hide');
    //         },
    //         error: function () {
    //             swal("Error!", "Something went wrong", "error");
    //         }
    //     });
    // });
    $(document).on("click", ".addbtn", function () {
        $(".addaccountsTitle").text("Add Account");
        $(".addaccountsForm")[0].reset();
    });
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>

</body>
</html>