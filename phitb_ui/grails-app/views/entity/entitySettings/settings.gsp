<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Entity Settings</title>
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
                    <h2>Entity Settings</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Entity Settings</li>
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
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    <div class="header">
                        <h2>Update Settings</h2>
                    </div>
                    <div class="body">
                        <div class="row clearfix">
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Invoice Generation Methods</b>
                                <select class="form-control show-tick">
                                    <option>Single Invoice for EntireEntity</option>
                                    <option>Separate Invoice for Each Division</option>
                                    <option>Separate Inoice for Each Floor	</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Default Sales Invoice Series</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Invoice Print Grouping</b>
                                <select class="form-control show-tick">
                                    <option>Division Wise</option>
                                    <option>Tax wise</option>
                                    <option>Product Grouping</option>
                                    <option>Category</option>
                                    <option>Schedule</option>
                                    <option>Floorwise</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Invoice Print Sorting</b>
                                <select class="form-control show-tick">
                                    <option>Alphabetic</option>
                                    <option>Tax wise</option>
                                    <option>Floor-Rack</option>
                                    <option>Rack Only</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Advance Cheque Mandatory</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Utilize Local or Universal Barcode</b>
                                <select class="form-control show-tick">
                                    <option>Local</option>
                                    <option>Universal</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Max Utilization of a Single Cheque (For Invoices)</b>
                                <select class="form-control show-tick">
                                    <option>0</option>
                                    <option>Null</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Credit Limit Management</b>
                                <select class="form-control show-tick">
                                    <option>Single Limit Entire Entity</option>
                                    <option>Individual Limit for each Division</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Allow Same Batch to be repeated in a Single Sales Invoice?</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Round Off the Scheme Quantity?</b>
                                <select class="form-control show-tick">
                                    <option>Higher Scheme (Loss)</option>
                                    <option>Lower Scheme Auto Adjust (Profitable)	</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Excess/Shortage stocks rounded off to be autoadjusted in Next Invoice?</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>How to Apply Scheme?</b>
                                <select class="form-control show-tick">
                                    <option>Follow Scheme Configurator (Recommended)</option>
                                    <option>Manual Entry</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Round off Total Net Value to ?</b>
                                <select class="form-control show-tick">
                                    <option>Next Integer Value</option>
                                    <option>Previous Interger Value</option>
                                    <option>Remains Same (Recommended)</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Round off Total Net Value to ?</b>
                                <select class="form-control show-tick">
                                    <option>Next Integer Value</option>
                                    <option>Previous Interger Value</option>
                                    <option>Remains Same (Recommended)</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Can the Invoice Value be Zero?</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 m-b-20">
                                <b>Apply Transportation Chanrges to Customer? (Paid Amt.)</b>
                                <select class="form-control show-tick">
                                    <option>Yes</option>
                                    <option>No</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<g:include view="controls/entity/add-entity-settings.gsp"/>
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

    var entitySettingTable;
    var id = null;
    $(function () {
        entitySettingsTable();
    });

    function entitySettingsTable() {
        entitySettingTable = $(".entitySettingsTable").DataTable({
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
                searchPlaceholder: "Search Entity Settings"
            },
            ajax: {
                type: 'GET',
                url: '/entity-settings/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-entity="' + json.data[i].entity.id + '"' +
                            '" data-code="' + json.data[i].code + '"' +
                            '" data-name="' + json.data[i].name + '"' +
                            '" data-value="' + json.data[i].value + '"' +
                            '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addEntitySettingModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'entity': json.data[i].entity.entityName,
                            'code': json.data[i].code,
                            'name': "<div style='white-space:normal;'>"+json.data[i].name+"<div>" ,
                            'value': json.data[i].value,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'entity', 'width': '20%'},
                {'data': 'code', 'width': '20%'},
                {'data': 'name', 'width': '20%'},
                {'data': 'value', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".entitySettingsForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);

        var url = '';
        var type = '';
        if (id) {
            url = '/entity-settings/update/' + id;
            type = 'POST'
        } else {
            url = '/entity-settings';
            type = 'POST'
        }

        $.ajax({
            url: url,
            type: type,
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                swal("Success!", "Entity Settings Submitted Successfully", "success");
                entitySettingsTable();
                $('#addEntitySettingModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".entitySettingsForm")[0].reset();
        $('.entity').select2();
        $(".entitySettingTitle").text("Add Entity Settings");
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".name").val($(this).data('name'));
        $(".entity").val($(this).data('entity')).trigger('change');
        $(".code").val($(this).attr('data-code'));
        $(".value").val($(this).attr('data-value'));
        $('.entity').select2();
        $(".entitySettingTitle").text("Update Entity Settings");
    });


    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Entity Settings ?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/entity-settings/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                entitySettingsTable();
                swal("Success!", "Entity Settings Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("entity-menu");
</script>
</body>
</html>