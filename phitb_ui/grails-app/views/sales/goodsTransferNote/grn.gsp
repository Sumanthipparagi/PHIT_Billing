<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Goods Reciept Note</title>
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
    <asset:stylesheet src="/themeassets/fonts/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/table-to-json@1.0.0/lib/jquery.tabletojson.min.js"
            integrity="sha256-H8xrCe0tZFi/C2CgxkmiGksqVaxhW0PFcUKZJZo1yNU=" crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>

    <style>

    table.dataTable tbody td {
        word-break: break-word;
        vertical-align: top;
    }

    /*
        div.dataTables_scrollBody table tbody  td {
            border-top: none;
            padding: 0.9px;
            text-align: center;

        }
    */

    .select2-container .select2-selection--single .select2-selection__rendered {
        width: 112px;
    }

    </style>

</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="Alpino"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>
<g:include view="controls/sales/add-division-grn.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Goods Reciept Note</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Goods Reciept Note</li>
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
                        <div class="row">
                            <div class="col-md-4">
                            </div>

                            <div class="col-md-4">
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="invoiceStatus">GRN status</label>
                                    <select onchange="invoiceStatusChanged()" id="invoiceStatus" class="form-control">
                                        <option>All</option>
                                        <option>DRAFT</option>
                                        <option>ACTIVE</option>
                                        <option>CANCELLED</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover saleInvoiceTable dataTable js-exportable">
                                <thead>
                                <tr>
                                    <th>-</th>
                                    <th>Customer</th>
                                    <th>Invoice No.</th>
                                    <th>GST Amt</th>
                                    <th>Net Amt</th>
                                    <th>City</th>
                                    <th>GTN Status</th>
                                    <th>Balance</th>
                                    <th>Financial Year</th>
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

%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/dataTables.buttons.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/buttons.flash.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/jszip.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/buttons.html5.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/buttons.print.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/pdfmake.min.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/jquery-datatable/extra-plugins/vfs_fonts.js"/>--}%
%{--<asset:javascript src="/themeassets/plugins/icons/all.js"/>--}%
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.js"
        integrity="sha256-2JRzNxMJiS0aHOJjG+liqsEOuBb6++9cY4dSOyiijX4=" crossorigin="anonymous"></script>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>


<script>

    var entityregister;
    var id = null;
    $(function () {
        grnTable();
        // var $demoMaskedInput = $('.demo-masked-input');
        // $demoMaskedInput.find('.datetime').inputmask('d/m/y h:m:s', { placeholder: '__/__/____ __:__:__:__', alias:
        //         "datetime", hourFormat: '12' });

    });

    function grnTable() {
        var invoiceStatus = $("#invoiceStatus").val();
        entityregister = $(".saleInvoiceTable").DataTable({
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
            dom: 'lBfrtip',
            // buttons: [
            //     {
            //         'copy', 'csv', 'excel', 'pdf', 'print'
            //     },
            // ],
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
                    exportOptions: {columns: ':visible:not(:first-child)'}
                }
            ],
            language: {
                searchPlaceholder: "Search GRN"
            },
            ajax: {
                type: 'GET',
                url: '/grn/datatable',
                data: {
                    invoiceStatus: invoiceStatus
                },
                dataType: 'json',

                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveInvoice = "";
                        var cancelInvoice = "";
                        var editInvoice = "";
                        if (json.data[i].billStatus !== "CANCELLED" && json.data[i].billStatus !== "APPROVED") {
                            cancelInvoice = '<a class="btn btn-sm btn-info" title="Cancel" onclick="cancelBill(' + json.data[i].id + ')" href="#"><i class="fa fa-times"></i></a>';
                        }
                        if (("${(session.getAttribute('entityTypeName'))}" === "${Constants.ENTITY_C_F}" || "${session.getAttribute('entityTypeName')}" === "${Constants.ENTITY_SUPER_STOCKIST}") && json.data[i].billStatus !== "APPROVED" && json.data[i].billStatus !== "CANCELLED") {

                            approveInvoice = '<a class="btn btn-sm btn-success" title="Approved" onclick="approveGrn(' + json.data[i].id
                                + ')" href="#"><i class="fa fa-check"></i></a>';

                        }
                        var printbtn = '<a target="_blank" class="btn btn-sm btn-danger" data-id="' + json.data[i].id
                            + '" href="/grn/print-grn?id=' + json.data[i].id +
                            '"><i class="fa fa-print"></i></a>';
                        var invoiceNumber = json.data[i].invoiceNumber;
                        if (invoiceNumber === undefined)
                            invoiceNumber = "";
                        if (json.data[i].returnStatus === "DRAFT") {
                            // editInvoice = '<a class="btn btn-sm btn-warning"  href="/edit-sale-entry?saleBillId=' +
                            //     json.data[i].id + '"><i class="fa fa-edit"></i></a>';
                        }
                        var grossAmt = (json.data[i].totalAmount - json.data[i].totalGst).toFixed(2);
                        return_data.push({
                            'action': approveInvoice + " " + cancelInvoice + " " + printbtn + " " + editInvoice,
                            /*'action': '',*/
                            'customer': json.data[i].customer.entityName,
                            'invNo': invoiceNumber,
                            'gstAmt': json.data[i].totalGst.toFixed(2),
                            'netAmt': json.data[i].totalAmount.toFixed(2),
                            'city': json.data[i]?.customer?.city?.areaName + "<br><small>(" + json.data[i]?.customer?.city?.districtName + ")</small>",
                            'bill_status': json.data[i].billStatus,
                            'balance': json.data[i].balance.toFixed(2),
                            'finYear': json.data[i].financialYear
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'action'},
                {'data': 'customer', 'width': '10%'},
                {'data': 'invNo'},
                {'data': 'gstAmt'},
                {'data': 'netAmt'},
                {'data': 'city'},
                {'data': 'bill_status'},
                {'data': 'balance'},
                {'data': 'finYear'}
            ]
        });
    }

    function cancelBill(id, customerId) {
        Swal.fire({
            title: "Cancel this GRN? this can't be undone.",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/gtn/cancel?id=' + id + '&customerId=' + customerId;
                var beforeSendSwal;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    beforeSend: function () {
                        beforeSendSwal = Swal.fire({
                            // title: "Loading",
                            html: '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false,
                            background: 'transparent'
                        });
                    },
                    success: function (data) {
                        beforeSendSwal.close();
                        Swal.fire(
                            'Success!',
                            'GRN Cancelled',
                            'success'
                        );
                        grnTable();
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to cancel return at the moment, try later.',
                            'danger'
                        );
                    }
                });
            } else if (result.isDenied) {

            }
        });


    }


    function approveGrn(gtn) {
        Swal.fire({
            title: "Approve this GRN? this can't be undone.",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var beforeSendSwal;
                $.ajax({
                    type: "GET",
                    url: "/grn/bill?billId=" + gtn,
                    dataType: 'json',
                    beforeSend: function () {
                        beforeSendSwal = Swal.fire({
                            // title: "Loading",
                            html:
                                '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false,
                            background: 'transparent'
                        });
                    },
                    success: function (data) {
                        beforeSendSwal.close();
                        var trHTML = '';
                        $.each(data, function (key, value) {
                            trHTML += '<tr>' +
                                '<td>' + value.product.productName + '</td>' +
                                '<td>' + value.batchNumber + '</td>' +
                                '<td><input type="checkbox"  id="'+"newProduct"+value.id+
                                '" name="newProduct" data-id="' + value.id +
                                '"  value="YES" class="newProduct">\n</td>' +
                                '<td>                        <select class="form-control show-tick exProduct" name="exProduct" onchange="getBatches('+this.value+')" id="'+ "exProduct" + value.id + '"">\n' +
                                '                            <g:each var="p" in="${products}">\n' +
                                '                                <option value="${p.id}">${p.productName}</option>\n' +
                                '                            </g:each>\n' +
                                '                        </select>\n</td>' +
                                '<td>                        <select class="form-control show-tick existBatch" name="existBatch" id="' + "existBatch" + value.id + '">\n' +

                                '                        </select>\n</td>' +
                                '<td>                        <select class="form-control show-tick division" name="division" id="' + "division" + value.id + '">\n' +
                                '                            <g:each var="d" in="${divisions}">\n' +
                                '                                <option value="${d.id}">${d.divisionName}</option>\n' +
                                '                            </g:each>\n' +
                                '                        </select>\n</td>' +
                                '<td style="display: none;">' + value.billId + '</td>' +
                                '<td style="display: none;">' + value.product.id + '</td>' +
                                '<td style="display: none;">' + value.entityId + '</td>' +
                                '<td style="display: none;">' + value.sqty + '</td>' +
                                '<td style="display: none;">' + value.freeQty + '</td>' +
                                '<td style="display: none;">' + value.sRate + '</td>' +
                                '</tr>';
                            getBatches(value.product.id)


                        });
                        $('#id').val(gtn);
                        $('#grnProductList').html(trHTML);
                        $('.exProduct').select2();
                        $('.existBatch').select2();
                        $('#addDivisionGrn').modal('show');
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to load Data.',
                            'danger'
                        );
                    }
                });

            } else if (result.isDenied) {

            }
        });


    }

    // function newproduct(id) {
    //     alert($('#newProduct'+id).checked)
    //     var myCheckbox = $('#newProduct'+id);
    //     if(myCheckbox.is(":checked") == true){
    //         $('#exProduct'+id).hide()
    //     }else{
    //         $('#exProduct'+id).show()
    //     }
    // }

    $(document).on('click', '.newProduct', function (e) {
        var id = $(this).attr('data-id');
        var checkbox =  $(this).is(":checked");
        if(checkbox){
            $('#exProduct'+id).prop('disabled', true);
            $('#existBatch'+id).prop('disabled', true);
        }else{
            $('#exProduct'+id).prop('disabled', false);
            $('#existBatch'+id).prop('disabled', false);
        }
    });


    $("#addDivisionGrnForm").submit(function (event) {
        //disable the default form submission
        event.preventDefault();
        //grab all form data
        var beforeSendSwal;
        // var division = document.forms['addDivisionGrnForm'].elements['division'].value;
        var gtn = document.forms['addDivisionGrnForm'].elements['id'].value;

        var tbl = $('#grnProducts tbody tr').map(function (idxRow, ele) {
            //
            // start building the retVal object
            //
            var retVal = {id: ++idxRow};
            //
            // for each cell
            //
            var $td = $(ele).find('td').map(function (idxCell, ele) {
                var select = $(ele).find(':selected');
                var input = $(ele).find(':input');
                //
                // if cell contains an input or select....
                //
                if (input.length === 1) {
                    var attr = $('#grnProducts thead tr th').eq(idxCell).text().trim();
                    retVal[attr] = input.is(":checked")
                }
                if (select.length === 1) {
                    var attr = $('#grnProducts thead tr th').eq(idxCell).text().trim();
                    retVal[attr] = select.val();
                } else {
                    var attr = $('#grnProducts thead tr th').eq(idxCell).text().trim();
                    if(input.length === 0){
                        retVal[attr] = $(ele).text();
                    }
                }
            });
            return retVal;
        }).get();

        var productData = JSON.stringify(tbl).replace(/\s(?=\w+":)/g, "");
        $.ajax({
            url: '/grn/approveGRN?gtn=' + gtn,
            type: 'POST',
            data: {
                productData: productData
            },
            beforeSend: function () {
                beforeSendSwal = Swal.fire({
                    // title: "Loading",
                    html: '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                    showDenyButton: false,
                    showCancelButton: false,
                    showConfirmButton: false,
                    allowOutsideClick: false,
                    background: 'transparent'
                });
            },
            success: function () {
                beforeSendSwal.close();
                Swal.fire(
                    'Success!',
                    'GRN Approved',
                    'success'
                );
                grnTable();
                $('#addDivisionGrn').modal('hide');
            },
            error: function () {
                Swal.fire(
                    'Error!',
                    'Unable to approve GRN at the moment, try later.',
                    'danger'
                );
            }
        });
    });

    %{--var url = '/grn/approveGRN?gtn=' + gtn;--}%
    %{--var beforeSendSwal;--}%
    %{--$.ajax({--}%
    %{--    type: "GET",--}%
    %{--    url: url,--}%
    %{--    dataType: 'json',--}%
    %{--    beforeSend: function() {--}%
    %{--        beforeSendSwal = Swal.fire({--}%
    %{--            // title: "Loading",--}%
    %{--            html:--}%
    %{--                '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',--}%
    %{--            showDenyButton: false,--}%
    %{--            showCancelButton: false,--}%
    %{--            showConfirmButton: false,--}%
    %{--            allowOutsideClick: false,--}%
    %{--            background:'transparent'--}%
    %{--        });--}%
    %{--    },--}%
    %{--    success: function (data) {--}%
    %{--        beforeSendSwal.close();--}%
    %{--        Swal.fire(--}%
    %{--            'Success!',--}%
    %{--            'GRN Approved',--}%
    %{--            'success'--}%
    %{--        );--}%
    %{--        saleInvoiceTable();--}%
    %{--    },--}%
    %{--    error: function () {--}%
    %{--        Swal.fire(--}%
    %{--            'Error!',--}%
    %{--            'Unable to approve GRN at the moment, try later.',--}%
    %{--            'danger'--}%
    %{--        );--}%
    %{--    }--}%
    %{--});--}%

    function getBatches(id) {
        var option = '';
        $.ajax({
            url: '/batch-register/product/' + id,
            dataType: 'json',
            type: 'GET',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    option += '<option value="' + data[i].batchNumber + '">' + data[i].batchNumber + '</option>';
                }
                $('.existBatch').empty()
                $('.existBatch').html(option);
                if (data.length === 0) {
                    // $('#batch').prop('disabled', true);
                } else {
                    // $('#batch').prop('disabled', false);

                }
            },
            error: function (x, e) {
                console.log("Something went wrong!")
            }

        });
    }

    function invoiceStatusChanged() {
        grnTable();
    }

</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("purchase-menu");
</script>
</body>
</html>