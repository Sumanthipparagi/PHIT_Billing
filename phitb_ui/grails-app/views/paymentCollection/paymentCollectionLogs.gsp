<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Receipts</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
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

    /*tbody td {*/
    /*    padding: 0px;*/
    /*}*/

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
                    <h2>Receipt </h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Receipt</li>
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
                    %{--                    <div class="header">--}%
                    %{--                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"--}%
                    %{--                                data-target="#adddayEndModal"><font style="vertical-align: inherit;"><font--}%
                    %{--                                style="vertical-align: inherit;">Add Day End</font></font></button>--}%
                    %{--                    </div>--}%
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover customerGroupTable dayEndTable">
                                <thead>
                                <tr>
                                    <th style="width: 20%">Document Number</th>
                                    <th style="width: 20%">Invoice Amount</th>
                                    <th style="width: 20%">Collected Amount</th>
                                    <th style="width: 20%">balance</th>
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


<g:include view="controls/entity/add-day-end.gsp"/>
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
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>

<script>

    var dayendtable;
    var id = null;
    $(function () {
        paymentCollectionLogTable();

    });

    function paymentCollectionLogTable() {
        dayendtable = $(".dayEndTable").DataTable({
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
                searchPlaceholder: "Search "
            },
            ajax: {
                type: 'GET',
                url: '/payment-collection/getlogs',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var cancel = "";
                        var status = "";
                        var approve = "";
                        var a ='Approve';
                        var c ='Cancel';
                        if (json.data[i].status === "ACTIVE") {
                            cancel =
                                '<a class="btn btn-sm btn-info" title="Cancel" onclick="changeStatus(this)" href="#!" data-status="Cancel" data-id="' + json.data[i].id +'"><i class="fa fa-times"></i></a>';
                            approve =
                                '<a class="btn btn-sm btn-success" title="Approve" onclick="changeStatus(this)" href="#!" data-status="Approve" data-id="' + json.data[i].id +'"><i class="fa fa-check"></i></a>';
                        }
                        else
                        {
                            cancel =  '';
                            approve = '';
                            status = json.data[i].status
                        }


                        return_data.push({
                            'documentNumber': json.data[i].documentNumber,
                            'invoiceAmount': json.data[i].invoiceAmount,
                            'collectedAmount': json.data[i].collectedAmount,
                            'balance': json.data[i].balance,
                            'action': approve+"  "+cancel + status
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'documentNumber', 'width': '20%'},
                {'data': 'invoiceAmount', 'width': '20%'},
                {'data': 'collectedAmount', 'width': '20%'},
                {'data': 'balance', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }

    $(".dayEndForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/day-end-master/update/' + id;
            type = 'POST'
        } else {
            url = '/day-end-master';
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
                swal("Success!", "CCm Submitted Successfully", "success");
                paymentCollectionLogTable();
                $('#adddayEndModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".dayEndTitle").text("Add Day End Master")
        $(".dayEndForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".date").val($(this).attr('data-date'));
        $(".endTime").val($(this).attr('data-endTime'));
        $(".entity").val($(this).attr('data-entityRegister'));
        $("#entityTypeId").val($(this).attr('data-entitytype')).change()
        $(".customerGroupTitle").text("Update Day End");
    });

    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityType").val(type);
    });



    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Day End?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/day-end-master/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                paymentCollectionLogTable();
                swal("Success!", "Day End Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }





    function changeStatus(btn) {
        Swal.fire({
            title: "Are you sure you want to "+ $(btn).data('status').toLowerCase()+ " ?",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/payment-collection/change-status';
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    data:{
                        id:$(btn).data('id'),
                        status:$(btn).data('status'),
                    },
                    success: function (data) {
                        if(data.status === 'APPROVED'){
                            Swal.fire(
                                'Success!',
                                'Payment Collection Approved!',
                                'success'
                            );
                        }else{
                            Swal.fire(
                                'Success!',
                                'Payment Collection Cancelled!',
                                'success'
                            );
                        }
                        paymentCollectionLogTable();
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to perform operation, try later.',
                            'danger'
                        );
                    }
                });
            } else if (result.isDenied) {

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