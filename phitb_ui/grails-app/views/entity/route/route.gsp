<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Route Register</title>
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
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.min.css"/>

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
                    <h2> Route Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Route Register</li>
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
                    <div class="header">
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                                data-target="#addrouteModal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Route</font></font></button>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover regionTable">
                                <thead>
                                <tr>
                                    %{--                                    <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Route Name</th>
                                    <th style="width: 20%">Appr Expense</th>
%{--                                    <th style="width: 20%">Salesman</th>--}%
%{--                                    <th style="width: 20%">Manager</th>--}%
%{--                                    <th style="width: 20%">Entity Type</th>--}%
%{--                                    <th style="width: 20%">Entity</th>--}%
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


<g:include view="controls/entity/add-route.gsp"/>
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
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>

<script>

    var regiontable;
    var id = null;
    $(function () {
        routeTable();
        $("#daysOfWeek").select2()
        $("#stateId").select2()
        $('#cityId').select2({
            ajax: {
                url: '/city/get',
                dataType: 'json',
                //delay: 250,
                data: function (params) {
                    return {
                        search: params.term,
                        type: 'select2'
                    };
                },
                processResults: function (data, params) {
                    return {
                        results: data
                    };
                },
            },
            placeholder: 'Search for cities',
            minimumInputLength: 2
        });

    });

    function routeTable() {
        regiontable = $(".regionTable").DataTable({
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
                searchPlaceholder: "Search Route"
            },
            ajax: {
                type: 'GET',
                url: '/route-regitser/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        console.log(json)
                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-routeName="' + json.data[i].routeName + '"' +
                            '" data-cityId="' + json.data[i].cityId + '"' +
                            '" data-cityName="' + json.data[i].city.areaName+ '"' +
                            '" data-countryId="' + json.data[i].countryId + '"' +
                            '" data-stateId="' + json.data[i].stateId + '"' +
                            '" data-salesman="' + json.data[i]?.salesman?.id + '"' +
                            '" data-areaManager="' + json.data[i]?.areaManager?.id + '"' +
                            '" data-ccmEnabled="' + json.data[i].ccmEnabled + '"' +
                            '" data-daysOfWeek="' + json.data[i].daysOfWeek + '"' +
                            '" data-ccmId="' + json.data[i].ccmId + '"' +
                            '" data-apprExpense="' + json.data[i].apprExpense + '"' +
                            '" data-entitytype="' + json.data[i].entityType.id + '"' +
                            '" data-entityRegister="' + json.data[i].entity.id + '"' +
                            '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addrouteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'routeName': json.data[i].routeName,
                            'apprExpense': json.data[i].apprExpense,
                            // 'salesman': json.data[i].salesman.userName,
                            // 'areaManager': json.data[i].areaManager.userName,
                            // 'entity': json.data[i].entity.entityName,
                            // 'entitytype': json.data[i].entityType.name,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'routeName', 'width': '20%'},
                {'data': 'apprExpense', 'width': '20%'},
                // {'data': 'salesman', 'width': '20%'},
                // {'data': 'areaManager', 'width': '20%'},
                // {'data': 'entity', 'width': '20%'},
                // {'data': 'entitytype', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }



    $(".routeForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/route-regitser/update/' + id;
            type = 'POST'
        } else {
            url = '/route-regitser';
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
                swal("Success!", "Route Submitted Successfully", "success");
                routeTable();
                $('#addrouteModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".routeTitle").text("Add Route")
        $(".routeForm")[0].reset();
        $("#daysOfWeek").select2("");
        $("#stateId").select2("");
        // $(".cityId").prop("disabled",false);
        // $(".addcity").show();
        // $("#updatecity").prop("disabled",true);
        // $(".updatecity").show();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".routeName").val($(this).attr('data-routeName'));
        $(".apprExpense").val($(this).attr('data-apprExpense'));
        $(".ccmEnabled").val($(this).attr('data-ccmEnabled')).change();
        $('.cityId').append("<option value='" + $(this).attr('data-cityId') + "'>" + $(this).attr('data-cityName') + "</option>");
        $("#cityId").val($(this).attr('data-cityId')).change();
        $(".stateId").val($(this).attr('data-stateId')).change();
        $(".countryId").val($(this).attr('data-countryId')).change();
        $(".ccmId").val($(this).attr('data-ccmId')).change();
        var daysOfWeek =$(this).attr('data-daysOfWeek');
        $("#daysOfWeek").val(daysOfWeek.split(",")).change();
        $(".entity").val($(this).attr('data-entityRegister')).change();
        $("#entityTypeId").val($(this).attr('data-entitytype')).change();
        $(".routeTitle").text("Update Region");
    });

    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityType").val(type);
    });


    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Route ?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/route-regitser/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                routeTable();
                swal("Success!", "Route  Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }

    $('.pinCode').select2({
        placeholder: 'Enter Pincode',
        minimumInputLength: 3,
        required: true,
        ajax: {
            url: '/getcitybypincode',
            dataType: 'json',
            delay: 250,
            data: function (data) {
                return {
                    pincode: data.term // search term
                };
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


    $('.pinCode').on('select2:selecting', function (e) {
        var data = e.params.args.data;
        var id = data.id;
        // alert(id)
        $.ajax({
            method: 'GET',
            url: '/getcitybyid',
            data: {'id': id},
            success: function (response) {
                console.log(response);
                $('.stateId').val(response.state.id).change();
                $("input[name='stateId']").val(response.state.id);
                $("input[name='cityId']").val(response.id);
                $('.cityId').empty();
                $('.cityId').append("<option value='" + response.id + "'>" + response.areaName + "</option>");
                // $('.cityId').val(response.id).change();
                $('.pinCode').val(response.pincode);
                $("input[name='pinCode']").val(response.pincode);
                if (response.state.alphaCode === "FC") {
                    $('.countryId').find('option:contains("OTHER")').attr('selected', 'selected');
                    $("input[name='countryId']").val($('.countryId').val());
                } else {
                    $('.countryId').find('option:contains("INDIA")').attr('selected', 'selected');
                    $("input[name='countryId']").val($('.countryId').val());
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });

    });

</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>