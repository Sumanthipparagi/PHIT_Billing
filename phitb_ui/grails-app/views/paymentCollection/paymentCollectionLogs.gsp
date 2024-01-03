<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Payment collection logs</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/daterangepicker/daterangepicker.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.min.css"/>

    <style>

    /*    div.dataTables_scrollBody table tbody  td {*/
    /*        border-top: none;*/
    /*        padding: 0.9px;*/
    /*        text-align: center;*/
    /*        border-collapse: unset!important;*/
    /*    }*/

    .editbtn {
        padding: 1px 9px;
    }

    .deletebtn {
        padding: 1px 9px;
    }

    /*tbody td {*/
    /*    padding: 0px;*/
    /*}*/

    table.table-bordered.dataTable tbody th, table.table-bordered.dataTable tbody td {
        padding: 10px;
    }

    .hide_column {
        display: none;
    }

    </style>

</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="PharmIT"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Payment Collection Logs</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Payment Collection Logs</li>
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
                    <div class="body">
                        <div class="row">
                            <div class="col-lg-5">
                            </div>

                            <div class="col-lg-2">
                                <label for="userSelect">User</label>

                                <div class="form-group">
                                    <select id="userSelect" class="form-control" style="border-radius: 0;">
                                        <option>ALL</option>
                                        <g:each in="${executives}" var="ex">
                                            <option value="${ex.id}">${ex.name}</option>
                                        </g:each>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-2">
                                <label for="sortStatus">Status</label>

                                <div class="form-group">
                                    <select id="sortStatus" class="form-control" style="border-radius: 0;">
                                        <option value="ALL">ALL</option>
                                        <option value="ACTIVE">Active</option>
                                        <option value="APPROVED">Approved</option>
                                        <option value="RETURNED">Returned</option>
                                        <option value="CANCELLED">Cancel</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-2">
                                <div class="form-group">
                                    <label>Date Range:</label>

                                    <div class="input-group">
                                        <input id="dateRange" class="dateRange" type="text" name="dateRange"
                                               style="border-radius: 6px;margin: 4px;" autocomplete="off">
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-1">
                                <button type="button" class="btn btn-success mt-4"
                                        onclick="paymentCollectionLogTable()">VIEW</button>
                            </div>
                        </div>

                        <div class="table-responsive" style="font-size: 13px;">
                            <table class="table table-bordered table-striped table-hover paymentCollectionTable">
                                <thead>
                                <tr>
                                    <th><span style="display: none;">check</span></th>
                                    <th>Status</th>
                                    <th>Customer</th>
                                    <th>TranNo.</th>
                                    <th>Receipt Id</th>
                                    <th>Chq No</th>
                                    <th>Pay. Mode</th>
                                    <th>Account</th>
                                    <th>Inv Amt</th>
                                    <th>Collected Amount</th>
                                    <th>Balance</th>
                                    <th>Reason</th>
                                    <th>Appr Dt</th>
                                    <th>-</th>
                                    <th>Location</th>
                                    <th style="display: none;">pcId</th>
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
                        <br>

                        <div class="row">
                            <div class="col-md-4">
                                Approved: <span id="approved"></span>, Cancelled: <span id="cancelled"></span> , Returned: <span id="returned"></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="float-left">
                                    <button type="button" class="btn btn-primary" onclick="deselectAll()"
                                            style="padding: 6px
                                            22px;">Deselect All</button>
                                    <button type="button" class="btn btn-primary" onclick="selectAll()"
                                            style="padding: 6px 22px;">Select All</button>
                                    <button type="button" class="btn btn-primary" onclick="updateBulkStatusModal()"
                                            style="padding: 6px 22px;">Action(Approve, Return, Cancel)</button>
                                </div>
                            </div>

                            <div class="col-lg-8">
                                <div class="float-right">

                                    <button type="button" class="btn btn-warning reset" style="padding: 4px 22px;">RESET
                                    </button>
                                    <button type="button" onclick="approveAllPaymentCollection()"
                                            class="btn btn-success"
                                            style="padding: 4px 22px;">FINALIZE & APPROVE</button>
                                    %{--                            <button type="button" id="btnPrint" class="btn btn-primary" style="padding: 4px 22px;">PRINT</button>--}%

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<div class="modal fade selectAllModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span></button>

                <h4 class="modal-title">Bulk status</h4>
            </div>

            <div class="modal-body">
                <label for="selectAllStatus">Status</label>

                <div class="form-group">
                    <select id="selectAllStatus" class="form-control" style="border-radius: 0;">
                        <option value="APPROVED">Approved</option>
                        <option value="RETURNED">Returned</option>
                        <option value="CANCELLED">Cancel</option>
                    </select>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="dt"
                        onclick="updateBulkStatus();">Yes</button>
            </div>

        </div>
    </div>
</div>
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
<asset:javascript src="/themeassets/plugins/jQuery.print/jQuery.print.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/moment.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/daterangepicker.js"/>
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>
<script>


    var approveCount = 0;
    var returnCount = 0;
    var cancelCount = 0;
    var pendingCount = 0;

    function changeIndividualRowStatus(item) {
        if(item != null) {
            var status = $(item).val();
            switch (status) {
                case "Approve":
                    approveCount += 1;
                    if(returnCount > 0)
                        returnCount -= 1;
                    if(cancelCount > 0)
                        cancelCount -= 1;
                    if(pendingCount > 0)
                        pendingCount -= 1;

                    break;
                case "Return":
                    returnCount += 1;

                    if(approveCount > 0)
                        approveCount -= 1;

                    if(cancelCount > 0)
                        cancelCount -= 1;

                    if(pendingCount > 0)
                        pendingCount -= 1;
                    break;
                case "Cancel":
                    cancelCount += 1;
                    if(approveCount > 0)
                        approveCount -= 1;

                    if(returnCount > 0)
                        returnCount -= 1;

                    if(pendingCount > 0)
                        pendingCount -= 1;
                    break;
                case "Active":
                    pendingCount += 1;
                    if(approveCount > 0)
                        approveCount -= 1;

                    if(returnCount > 0)
                        returnCount -= 1;

                    if(cancelCount > 0)
                        cancelCount -= 1;

                    break;
            }
        }
        $("#approved").text(approveCount);
        $("#returned").text(returnCount);
        $("#cancelled").text(cancelCount);
    }

    $("#userSelect").select2({
        allowClear: true
    });
    $("#sortStatus").select2();
    $('.dateRange').daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: 'Clear'
        },
    });
    $('.dateRange').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        // paymentCollectionLogTable();
    });

    // paymentCollectionLogTable();

    $('.dateRange').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        // paymentCollectionLogTable();
    });


    var paymentCollectionTable;
    var paymentCollectionIds = [];
    var id = null;
    $(function () {
        // paymentCollectionLogTable();
        $('.theme-black').addClass('menu_sm');
        // $(".paymentCollectionTable").DataTable();
        $('.paymentCollectionTable').DataTable({
            language: {
                emptyTable: "Use above filters get payment details"
            }
        });

        document.body.style.zoom = "100%"

    });

    function paymentCollectionLogTable() {
        var daterange = $(".dateRange").val();
        var status = $("#sortStatus").val();
        var user = $("#userSelect").val();
        /* if(daterange===''){
             Swal.fire("Please select daterange")
             return;
         }*/
        paymentCollectionTable = $(".paymentCollectionTable").DataTable({
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
                searchPlaceholder: "Search",
            },
            dom: 'lBfrtip',
            buttons: [
                {
                    'extend': 'excel',
                    exportOptions: {columns: ':visible:not(:first-child)'}
                },
                {
                    'extend': 'pdf',
                    exportOptions: {columns: ':visible:not(:first-child)'}
                },
                {
                    'extend': 'print',
                    exportOptions: {columns: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]}
                }
            ],
            lengthMenu: [100, 200, 300, 400, 500, 1000],
            ajax: {
                type: 'GET',
                url: '/payment-collection/getlogs',
                dataType: 'json',
                data: {
                    daterange: daterange,
                    status: status,
                    user: user
                },
                dataSrc: function (json) {
                    approveCount = 0;
                    returnCount = 0;
                    cancelCount = 0;
                    pendingCount = 0;
                    changeIndividualRowStatus();
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var cancel = "";
                        var status = "";
                        var approve = "";
                        if (json.data[i].status === "ACTIVE") {
                            cancel =
                                '<a class="btn btn-sm btn-info" title="Cancel" onclick="changeStatus(this)" href="#!" data-status="Cancel" data-id="' + json.data[i].id + '" style="padding: 2px 7px;"><i class="fa fa-times" style="font-size: 13px;"></i></a>';
                            approve =
                                '<a class="btn btn-sm btn-success" title="Approve" onclick="changeStatus(this)" href="#!" data-status="Approve" data-id="' + json.data[i].id + '" style="padding: 2px 7px;"><i class="fa fa-check" style="font-size: 13px;"></i></a>';
                        } else {
                            cancel = '';
                            approve = '';
                            status = json.data[i].status
                        }

                        var dropdown = '';
                        var reason = '';
                        var checkbox = '';
                        if (json.data[i].status === "APPROVED" || json.data[i].status === "CANCELLED" || json.data[i].status === 'RETURNED') {
                            dropdown += "";
                            checkbox += "";
                            if (json?.data[i]?.reason !== undefined) {
                                reason += json?.data[i]?.reason
                            } else {
                                reason += '';
                            }

                        } else {
                            dropdown += '<select onchange="changeIndividualRowStatus(this)" name="status" class="status">\n' +
                                '  <option value="Active">Active</option>\n' +
                                '  <option value="Approve">Approve</option>\n' +
                                '  <option value="Return">Return</option>\n' +
                                '  <option value="Cancel">Cancel</option>\n' +
                                '</select>';

                            reason += '<input type="text" id="reason" name="reason" maxlength="20">';
                            checkbox += '<input type="checkbox" id="statusCheck" name="statusCheck" class="statusCheck" value="true">\n'
                        }

                        return_data.push({
                            'checkbox': checkbox,
                            'dropdown': dropdown,
                            'entityId': '<div style="white-space: initial">' + json.data[i]?.customer?.entityName + '</div>',
                            'paymode': json?.data[i]?.paymentMode?.name !== undefined ? json?.data[i]?.paymentMode?.name : "",
                            'documentNumber': json.data[i].documentNumber,
                            'chqNo': json?.data[i]?.receipt?.chequeNumber !== undefined ? json?.data[i]?.receipt?.chequeNumber : "",
                            'receiptId': json?.data[i]?.receipt?.receiptId !== undefined ? json?.data[i]?.receipt?.receiptId : "",
                            'invoiceAmount': Number(json.data[i].invoiceAmount).toFixed(2),
                            'collectedAmount': Number(json.data[i].collectedAmount).toFixed(2),
                            'reason': reason,
                            'location': '<a class="btn btn-primary" href="#!" data-cord="' +
                                json.data[i].currentLocation + '" onclick="locationWindow(this)" style="background-color: #007bff;">View</a>\n',
                            'balance': Number(json.data[i].balance).toFixed(2),
                            'account': json?.data[i]?.account?.accountName !== undefined ? json?.data[i]?.account?.accountName : "",
                            'approvedDt': json?.data[i]?.approvedDate !== undefined ?
                                moment(json?.data[i]?.approvedDate).format('DD-MM-YYYY') : "",
                            'action': status,
                            'id': json.data[i].id
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'checkbox', 'width': '10%'},
                {'data': 'dropdown', 'width': '10%'},
                {'data': 'entityId', 'width': '20%'},
                {'data': 'documentNumber', 'width': '10%'},
                {'data': 'receiptId', 'width': '5%'},
                {'data': 'chqNo', 'width': '5%'},
                {'data': 'paymode', 'width': '5%'},
                {'data': 'account', 'width': '5%'},
                {'data': 'invoiceAmount', 'width': '5%'},
                {'data': 'collectedAmount', 'width': '5%'},
                {'data': 'balance', 'width': '5%'},
                {'data': 'reason', 'width': '5%'},
                {'data': 'approvedDt', 'width': '5%'},
                {'data': 'action', 'width': '5%'},
                {'data': 'location', 'width': '5%'},
                {'data': 'id', 'width': '5%'}
            ],

            columnDefs: [
                {
                    targets: [15],
                    className: "hide_column"
                }
            ]
        });
    }


    $(document).on("click", ".reset", function () {
        if ($(".status").hasClass("disabled") !== true) {
            $('.status').val("Active");
        }
    });

    function changeStatus(btn) {
        Swal.fire({
            title: "Are you sure you want to " + $(btn).data('status').toLowerCase() + " ?",
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
                    data: {
                        id: $(btn).data('id'),
                        status: $(btn).data('status'),
                    },
                    success: function (data) {
                        if (data.status === 'APPROVED') {
                            Swal.fire(
                                'Success!',
                                'Payment Collection Approved!',
                                'success'
                            );
                        } else {
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


    /*
        $(document).on('change', '.approve', function() {
            var paymentId  = $(this).data('id');
            const index = paymentCollectionIds.indexOf(paymentId);
            if(this.checked) {
              paymentCollectionIds.push(paymentId);
              console.log(paymentCollectionIds)
            }else{
                if (index > -1) { // only splice array when item is found
                    paymentCollectionIds.splice(index, 1); // 2nd parameter means remove one item only
                }
                console.log(paymentCollectionIds)
            }
        });
    */

    function selectAll() {
        var x = document.getElementsByName("statusCheck");
        for (var i = 0; i < x.length; i++) {
            document.getElementsByName("statusCheck")[i].checked = true;
        }
    }

    function deselectAll() {
        var x = document.getElementsByName("statusCheck");
        for (var i = 0; i < x.length; i++) {
            document.getElementsByName("statusCheck")[i].checked = false;
        }
    }

    /* custom button event print */
    $(document).on('click', '#btnPrint', function () {
        $("#result").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null,
            manuallyCopyFormValues: true,
            deferred: $.Deferred(),
            timeout: 750,
            title: null,
            doctype: '<!doctype html>'
        });
    });


    function approveAllPaymentCollection() {

        var tbl = $('.paymentCollectionTable tbody tr').map(function (idxRow, ele) {
            //
            // start building the retVal object
            //
            var retVal = {id: ++idxRow};
            //
            // for each cell
            //
            var $td = $(ele).find('td').map(function (idxCell, ele) {
                var input = $(ele).find(':input');
                //
                // if cell contains an input or select....
                //
                if (input.length === 1) {
                    var attr = $('.paymentCollectionTable thead tr th').eq(idxCell).text();
                    retVal[attr] = input.val();
                } else {
                    var attr = $('.paymentCollectionTable thead tr th').eq(idxCell).text();
                    retVal[attr] = $(ele).text();
                }
            });
            return retVal;
        }).get();
        var pcData = JSON.stringify(tbl).replace(/\s(?=\w+":)/g, "");
        Swal.fire({
            title: "Are you sure you want to finalize ?",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/payment-collection/finalize-approve';
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: 'json',
                    data: {
                        pcData: pcData
                    },
                    success: function (data) {
                        Swal.fire(
                            'Success!',
                            'Payment Collection finalized!',
                            'success'
                        );
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

    function updateBulkStatusModal() {
        var checked = $(".statusCheck:checked").length;
        if (checked === 0) {
            Swal.fire("Please select payments")
        } else {
            $(".selectAllModal").modal('toggle');
        }
    }

    function updateBulkStatus() {
        var tbl = $('.paymentCollectionTable tbody tr').map(function (idxRow, ele) {
            //
            // start building the retVal object
            //
            var retVal = {id: ++idxRow};
            //
            // for each cell
            //
            var $td = $(ele).find('td').map(function (idxCell, ele) {
                var input = $(ele).find(':input');
                //
                // if cell contains an input or select....
                //
                if (input.length === 1) {
                    var attr = $('.paymentCollectionTable thead tr th').eq(idxCell).text();
                    retVal[attr] = input.val();
                } else {
                    var attr = $('.paymentCollectionTable thead tr th').eq(idxCell).text();
                    retVal[attr] = $(ele).text();
                }
            });
            return retVal;
        }).get();
        var pcData = JSON.stringify(tbl).replace(/\s(?=\w+":)/g, "");
        console.log(pcData);
        var bulkStatus = $('#selectAllStatus').val();
        Swal.fire({
            title: "Are you sure you want perform this operation ?",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/payment-collection/bulk-update';
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: 'json',
                    data: {
                        pcData: pcData,
                        bulkStatus: bulkStatus
                    },
                    success: function (data) {
                        Swal.fire(
                            'Success!',
                            'Payment Collection finalized!',
                            'success'
                        );
                        paymentCollectionLogTable();
                        $(".selectAllModal").modal('hide');

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

    function locationWindow(data) {
        var cord = $(data).attr("data-cord");
        window.open('https://www.google.com/maps?q=' + cord, 'location',
            'height=1550,width=1450,top=100,left=500,scrollbars=0');
    }


</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>
</body>
</html>