<%--
  Created by IntelliJ IDEA.
  User: arjun
  Date: 23-01-2023
  Time: 01:24 pm
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Payment Collection</title>
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
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt=""></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Payment Collection</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Payment Collection</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-md-12">
                <div class="card">
                    <div class="body">
                        <div class="row justify-content-end">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12" id="listContainer">
                <div class="card">
                    <div class="body">
                        <div class="table-responsive">
                            <table id="paymentCollectionTable"
                                   class="table table-bordered table-striped table-hover paymentCollectionTable dataTable js-exportable">
                                <thead>
                                <tr><th></th></tr>
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
<g:include view="controls/footer-content.gsp"/>
<script>
    loadSaleInvoiceTable();
    function loadSaleInvoiceTable() {
        var invoiceStatus = $("#invoiceStatus").val();
        saleInvoiceTable = $(".saleInvoiceTable").DataTable({
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
            //dom: 'lBfrtip',
            dom: '<"top"<"dt-left-col"l><"dt-center-col"B><"dt-right-col"f>>rt<"bottom"ip><"clear">',
            oLanguage: {
                sLengthMenu: "_MENU_",
            },
            buttons: [
                {
                    extend: 'collection',
                    text: 'Export',
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
                    dropup: true,
                    className: 'dropdown-toggle btn-sm'
                }
            ],
            language: {
                searchPlaceholder: "Search Sale Bill"
            },
            ajax: {
                type: 'GET',
                url: '/sale-bill/datatable',
                data: {
                    invoiceStatus: invoiceStatus
                },
                dataType: 'json',

                dataSrc: function (json) {
                    console.log(json)
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var approveInvoice = "";
                        var cancelInvoice = "";
                        var editInvoice = "";
                        var cloneInvoice="";
                        var irn="";
                        if (json.data[i].billStatus !== "CANCELLED") {
                            cancelInvoice = '<a class="dropdown-item" title="Cancel" onclick="cancelBill(' + json.data[i].id + ')" href="#" style="color: red;"><i class="fa fa-times"></i> Cancel</a>';
                        } else if (json.data[i].billStatus !== "DRAFT") {
                            approveInvoice = '';
                        }

                        if (json.data[i].billStatus!== "DRAFT" && json.data[i].invoiceNumber!==undefined) {
                            cloneInvoice =
                                '<a class="dropdown-item" title="Clone"  href="/sale-entry/clone-invoice?saleBillId='
                                + json.data[i].id + '&type=CLONE" target="_blank"><i class="fa fa-clone"></i> Clone</a>';
                        }else{
                            cloneInvoice=""
                        }
                        if(json.data[i].receiptLog.length > 0){
                            cancelInvoice=""
                        }


                        if(json.data[i].irnDetails===undefined){

                            if(json.data[i].billStatus!=="DRAFT" && json.data[i].billStatus!=="CANCELLED"){
                                irn = '<a class="dropdown-item" title="IRN"  onclick="genrateIRN('+json.data[i].id+')" target="_blank"><i class="fa fa-print"></i> Genrate IRN</a>';
                            }
                        }else{
                            irn='';
                        }

                        %{--                <g:if test="${session.getAttribute('domainType') == Constants.FURNITURE}">--}%
                        %{--                        var printbtn = '<a target="_blank" class="dropdown-item" data-id="' + json.data[i].id + '" href="/sale-entry/print-invoice?id=' + json.data[i].id + '"><i class="fa fa-print"></i> Print</a>';--}%
                        %{--                        </g:if>--}%
                        %{--                        <g:else>--}%
                        var printbtn = '<a target="_blank" class="dropdown-item" data-id="' + json.data[i].id + '" href="/sale-entry/print-invoice?id=' + json.data[i].id + '"><i class="fa fa-print"></i> Print</a>';
                        %{--                        </g:else>--}%
                        var invoiceNumber = json.data[i].invoiceNumber;
                        if (invoiceNumber === undefined) {
                            if (json.data[i].billStatus === "CANCELLED")
                                invoiceNumber = "CANCELLED DRAFT";
                            else
                                invoiceNumber = "DRAFT";
                        }
                        if (json.data[i].billStatus === "DRAFT") {
                            editInvoice = '<a class="dropdown-item"  href="/edit-sale-entry?saleBillId=' + json.data[i].id + '"><i class="fa fa-edit"></i> Edit</a>';
                        }

                        <g:if test="${entityConfigs?.REGEN_NEW_DOC?.saleEntry}">
                        if (json.data[i].billStatus === "CANCELLED") {
                            editInvoice = '<a class="dropdown-item"  href="/edit-sale-entry?saleBillId=' + json.data[i].id + '"><i class="fa fa-edit"></i> Edit</a>';
                        }
                        </g:if>
                        // if(json.data[i].balance !== json.data[i].totalAmount)
                        // {
                        //     cancelInvoice="";
                        // }


                        var actionBtn = "<div class=\"dropdown\">\n" +
                            "  <button class=\"btn btn-primary btn-simple btn-sm dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                            " <i class='fa fa-bars'></i>" +
                            "  </button>\n" +
                            "  <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n" +
                            approveInvoice +
                            printbtn +
                            editInvoice +
                            cloneInvoice +
                            cancelInvoice +
                            irn +
                            "  </div>\n" +
                            "</div>"
                        invoiceNumber = "<a href='#' onclick='listItemClicked(" + json.data[i].id + ")'>" + invoiceNumber + "</a>";
                        var grossAmt = (json.data[i].invoiceTotal - json.data[i].totalGst).toFixed(2);
                        return_data.push({
                            'action': actionBtn,
                            'invNo': invoiceNumber,
                            'customer': json.data[i]?.customer?.entityName,
                            'gstAmt': json.data[i].totalGst.toFixed(2),
                            'grossAmt': grossAmt,
                            'date': moment(json.data[i].entryDate).format('DD-MM-YYYY  h:mm a'),
                            'netAmt': json.data[i].invoiceTotal.toFixed(2),
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
                {'data': 'invNo'},
                {'data': 'customer', 'width': '10%'},
                {'data': 'date'},
                {'data': 'gstAmt'},
                {'data': 'grossAmt'},
                {'data': 'netAmt'},
                {'data': 'city'},
                {'data': 'bill_status'},
                {'data': 'balance'},
                {'data': 'finYear'}
            ]
        });

        saleInvoiceTable.buttons().container().appendTo('#saleInvoiceTable_wrapper .col-md-6:eq(0)');
    }
</script>
</body>
</html>