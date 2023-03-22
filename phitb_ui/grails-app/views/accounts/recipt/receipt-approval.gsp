<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Receipt Approval</title>
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
    <asset:stylesheet src="/themeassets/plugins/daterangepicker/daterangepicker.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">

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
                    <h2>Receipt Approval</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Receipt Approval</li>
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

                    <div class="row">
                    <div class="col-lg-6">
                        <label for="customer">
                            Customer
                        </label><br>
                        <select onchange="customerChanged()" class="show-tick customer" name="customer"
                                id="customer" style="width: 300px;">
                            <option value="all">ALL</option>
                            <g:each var="e" in="${entity}">
                                <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                            </g:each>
                        </select>
                    </div>

                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Date Range:</label>
                            <div class="input-group">
                                <input id="dateRange" class="dateRange" type="text" name="dateRange"
                                       style="border-radius: 6px;margin: 4px;" autocomplete="off">
                            </div>
                        </div>
                    </div>
                </div>

                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover recieptTable">
                                <thead>
                                <tr>
                                    %{--
                                                                   <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Recipt Date</th>
                                    <th style="width: 20%">Receipt Id</th>
                                    <th style="width: 20%">Received From</th>
                                    <th style="width: 20%">Status</th>
%{--                                    <th style="width: 20%">Deposit To</th>--}%
                                    <th style="width: 20%">Financial Year</th>
                                    <th style="width: 20%">Amount paid</th>
                                    %{--                                    <th style="width: 20%">Bank</th>--}%
                                    <th style="width: 20%">Action</th>
                                    <th style="width: 20%">Approve</th>
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
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/moment.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/daterangepicker.js"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.js"></script>

<script>

    $('.dateRange').daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: 'Clear'
        },
    });
    $('.dateRange').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        recieptTable();
    });
    recieptTable();

    $('.dateRange').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
        recieptTable();
    });

    function date()
    {
        alert($('.dateRange').val())
    }



    $(".customer").select2()
    var reciepttable;
    var id = null;
    $(function () {
        recieptTable();

    });

    function recieptTable() {
        var customer = $("#customer").val();
        var daterange = $(".dateRange").val();
        reciepttable = $(".recieptTable").DataTable({
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
                searchPlaceholder: "Search Receipt"
            },
            ajax: {
                type: 'GET',
                url: '/receipt-list/datatable',
                dataType: 'json',
                data: {
                    customer: customer,
                    daterange: daterange,
                },
                dataSrc: function (json) {
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var date = new Date(json.data[i].date);
                        var pd = new Date(json.data[i].paymentDate)
                        var editbtn =
                            ' <button type="button" data-id="'+json.data[i].id+'" data-recievedfrom="'+json.data[i].receivedFrom.id+'" class="print btn btn-sm btn-warning editbtn"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">print</font></font></i></button>'

                        var approveBtn =
                            '<a class="btn btn-sm btn-success" title="Approved" onclick="approveReceipt(' + json.data[i].id +')" href="#"><i class="fa fa-check"></i></a>';
                        // var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                        //     '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].receiptId,
                            'date': moment(date).format('DD/MM/YYYY'),
                            'fy': json.data[i].financialYear,
                            'status': json.data[i].approvedStatus,
                            'amountPaid': json.data[i].amountPaid.toFixed(2),
                            'receivedFrom': json.data[i].receivedFrom.entityName,
                            'depositTo': json.data[i]?.deposit === "NA" ? '' : json.data[i]?.deposit?.accountName,
                            'pd': moment(pd).format('DD/MM/YYYY'),
                            // 'bank': json.data[i].bank.bankName,
                            'action': editbtn,
                            'aprBtn': json.data[i]?.approvedBy === 0 && json.data[i]?.approvedStatus !=="CANCELLED" ?
                                approveBtn : json.data[i]?.approvedBy!== 0 ? json.data[i]?.approved.userName :""
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'date', 'width': '20%'},
                {'data': 'date', 'width': '20%'},
                {'data': 'id', 'width': '20%'},
                {'data': 'receivedFrom', 'width': '20%'},
                {'data': 'status', 'width': '20%'},
                // {'data': 'depositTo', 'width': '20%'},
                {'data': 'fy', 'width': '20%'},
                {'data': 'amountPaid', 'width': '20%'},
                // {'data': 'bank', 'width': '20%'},
                {'data': 'action', 'width': '20%'},
                {'data': 'aprBtn', 'width': '20%'}
            ]
        });
    }


    function printRecipt() {
        /* window.addEventListener('load', function () {*/
        $('#reciptPrint').printThis({
            importCSS: true,
            printDelay: 2000,
            importStyle: true,
            base: "",
            pageTitle: ""
        });
    }

    $(document).on("click", ".print", function () {
        var custId =  $(this).data('recievedfrom');
        var id =  $(this).data('id');
        $("#printabel").remove();
        reciptPrint(custId,id)
    });

    function reciptPrint(custId,id) {
        $("<iframe id='printabel'>")
            .hide()
            .attr("src", "/print-recipt/"+custId+"/recipt/"+id)
            .appendTo("body");
    }

    function approveReceipt(id) {
        $.ajax({
            type: 'POST',
            url: '/receipt-approve?id='+id,
            dataType: 'json',
            success: function () {
                recieptTable();
                swal("Success!", "Receipt Approved", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }


    function customerChanged() {
        recieptTable();
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>
</body>
</html>