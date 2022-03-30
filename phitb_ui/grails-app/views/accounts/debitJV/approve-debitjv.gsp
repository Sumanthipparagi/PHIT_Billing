<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Debit JV Approvals</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />


    <style>

/*
    div.dataTables_scrollBody table tbody  td {
        border-top: none;
        padding: 0.9px;
        text-align: center;
        border-collapse: unset!important;
    }
*/

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
                    <h2>Debit JV - Approval</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Approvals</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover approvalTable dataTable">
                                <thead>
                                <tr>
                                    <th style="width: 20%">Trans.Id</th>
                                    <th style="width: 20%">Amount</th>
                                    <th style="width: 20%">Credit A/C</th>
                                    <th style="width: 20%">From A/C</th>
                                    <th style="width: 20%">Date</th>
                                    <th style="width: 20%">Reason</th>
                                    <th style="width: 20%">Remarks</th>
                                    <th style="width: 20%">Action</th>
                                </tr>
                                </thead>
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


<g:include view="controls/product/add-batch-register.gsp"/>
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
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js" type="text/javascript"/>

<script>

    var approvalTable;
    var id = null;
    $(function () {
        approvalTable();
    });

    function approvalTable() {
        approvalTable = $(".approvalTable").DataTable({
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
                searchPlaceholder: "Search Transaction Id"
            },
            ajax: {
                type: 'GET',
                url: '/credit-jv/approval-table',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveBtn = '<button data-amount="'+json.data[i].amount+'" data-fromaccount="'+json.data[i].fromAccount.id+'" data-creditaccount="'+json.data[i].creditAccount.id+'" data-transactionid="'+json.data[i].transactionId+'" class="btn btn-success btn-sm approveCreditJv" data-id="'+json.data[i].id+'">Approve</button> ' +
                            '<button data-amount="'+json.data[i].amount+'" data-fromaccount="'+json.data[i].fromAccount.id+'" data-creditaccount="'+json.data[i].creditAccount.id+'"data-transactionid="'+json.data[i].transactionId+'"  class="btn btn-danger btn-sm rejectCreditJv" data-id="'+json.data[i].id+'">Reject</button>';
                        return_data.push({
                            'transactionId': json.data[i].transactionId,
                            'amount': json.data[i].amount,
                            'creditAccount': json.data[i].creditAccount.accountName,
                            'fromAccount': json.data[i].fromAccount.accountName,
                            'date':  json.data[i].transactionDate,
                            'reason':  json.data[i].reason,
                            'remarks': json.data[i].remarks,
                            'action': approveBtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'id', 'width': '20%'},
                {'data': 'transactionId', 'width': '20%'},
                {'data': 'amount', 'width': '20%'},
                {'data': 'creditAccount', 'width': '20%'},
                {'data': 'fromAccount', 'width': '20%'},
                {'data': 'date', 'width': '20%'},
                {'data': 'reason', 'width': '20%'},
                {'data': 'remarks', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }
    


    $(document).on("click", ".approveCreditJv", function () {
        var id = $(this).data('id');
        var fromAccount = $(this).data('fromaccount');
        var creditAccount = $(this).data('creditaccount');
        var transactionid = $(this).data('transactionid');
        var amount = $(this).data('amount');
        Swal.fire({
            title: 'Do you want to Approve '+transactionid+'?',
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "approve?status="+1+"&id="+id+"&creditAccount="+creditAccount+"&fromAccount="+fromAccount+"&amount="+amount,
                    type: "GET",
                    contentType: false,
                    processData: false,
                    success: function () {
                        Swal.fire('Approved!', '', 'success');
                        approvalTable();
                    },
                    error: function () {
                        swal("Error!", "Something went wrong", "error");
                    }
                });

            } else if (result.isDenied) {
                Swal.fire('Cancelled', '', 'info')
            }
        })
    });

    $(document).on("click", ".rejectCreditJv", function () {
        var id = $(this).data('id');
        var fromAccount = $(this).data('fromaccount');
        var creditAccount = $(this).data('creditaccount');
        var transactionid = $(this).data('transactionid');
        var amount = $(this).data('amount');
        Swal.fire({
            title: 'Do you want to Reject '+transactionid+'?',
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "approve?status="+0+"&id="+id+"&creditAccount="+creditAccount+"&fromAccount="+fromAccount+"&amount="+amount,
                    type: "GET",
                    contentType: false,
                    processData: false,
                    success: function () {
                        Swal.fire('Rejected!', '', 'success');
                        approvalTable();
                    },
                    error: function () {
                        swal("Error!", "Something went wrong", "error");
                    }
                });

            } else if (result.isDenied) {
                Swal.fire('Cancelled', '', 'info')
            }
        });
    });


</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>

</body>
</html>