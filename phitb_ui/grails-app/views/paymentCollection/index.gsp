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
                                   class="table paymentCollectionTable dataTable js-exportable">
                                <thead>
                                <tr><th>Invoices</th></tr>
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

<div class="modal fade detailsModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span></button>

            </button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>

            <div class="modal-body">
                <p class="myModalText">Are you sure?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="dt"
                        onclick="deleteData();">Yes</button>
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
<g:include view="controls/footer-content.gsp"/>
<script>

    $(document).ready(function() {
        paymentCollectionTable();
    });

    function paymentCollectionTable() {
        var paymentCollectionTable = $(".paymentCollectionTable").DataTable({
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
          //  dom: 'lfrtip',
           // dom: '<"top"<"dt-left-col"l><"dt-center-col"B><"dt-right-col"f>>rt<"bottom"ip><"clear">',
            oLanguage: {
                sLengthMenu: "_MENU_",
            },
            /*buttons: [
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
            ],*/
            language: {
                searchPlaceholder: "Search Invoice"
            },
            ajax: {
                type: 'GET',
                url: '/payment-collection/get-invoices',
               /* data: {
                    invoiceStatus: invoiceStatus
                },*/
                dataType: 'json',
                dataSrc: function (json) {
                   var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var badgeContainer = ""
                        if(json.data.balance === 0)
                        {
                            badgeContainer += "<div class=\"badge badge-success ml-2\">PAID</div>"
                        }
                        else if(json.data.balance === json.data.invoiceTotal)
                        {
                            badgeContainer += "<div class=\"badge badge-danger ml-2\">UNPAID</div>"
                        }
                        else
                        {
                            badgeContainer += "<div class=\"badge badge-warning ml-2\">PARTIALLY PAID</div>"
                        }

                        var invoiceDetails = "<div class='card'><div class='body'><div class='row'><div class='col-lg-9 col-6'><span class='h5'>#"+json.data[i].invoiceNumber+"</span></div><div class='col-lg-3 col-6'><span class='h5 text-primary pull-right'>₹"+ Number(json.data[i].balance).toFixed(2) + "</span></div>";
                        invoiceDetails += "<div class='col-lg-6 col-6'><p>Invoice Date: "+ dateFormat(json.data[i].orderDate) + "</p></div>";
                        invoiceDetails += "<div class='col-lg-6 col-6'><p>Due Date: "+  dateFormat(json.data[i].dueDate)+ "</p></div>";
                        invoiceDetails += "<div class='col-lg-6 col-6 d-flex align-items-center'><p class='badge'>Invoice Amount: ₹"+Number(json.data[i].invoiceTotal).toFixed(2) +"</p>"+badgeContainer+"</div><div class='col-lg-6 col-6'><p><button type='button' class='btn btn-sm btn-info pull-right viewbtn' onclick='viewBtnClick(this)' data-id='"+json.data[i].id+"'><i class='zmdi zmdi-file'></i> View</button></p></div></div></div>";
                        return_data.push({
                            'col': invoiceDetails
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'col'}
            ]
        });

        //paymentCollectionTable.buttons().container().appendTo('#saleInvoiceTable_wrapper .col-md-6:eq(0)');
    }

    function dateFormat(dt, time = false)
    {
        dt = dt.replace("T", " ").replace("Z", '');
        var date = new Date(dt);
        if(time)
            return moment(date).format('DD/MM/YYYY hh:mm:ss a');
        else
            return moment(date).format('DD/MM/YYYY');
    }

    function viewBtnClick(btn)
    {
        alert($(btn).data('id'));

        $(".detailsModal").toggle();
    }
</script>
</body>
</html>