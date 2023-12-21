<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Transportation Info</title>
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
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/select2/dist/css/select2.css"/>
    <style>

    /*div.dataTables_scrollBody table tbody  td {*/
    /*    border-top: none;*/
    /*    padding: 0.9px;*/
    /*    text-align: center;*/
    /*    border-collapse: unset!important;*/
    /*}*/
    .select2-selection__rendered {
        line-height: 42px !important;
    }
    .select2-container .select2-selection--single {
        height: 42px !important;
    }
    .select2-selection__arrow {
        height: 42px !important;
    }
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
                    <h2>Transportation Info</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Transportation Info</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="header">
                        <div class="row">
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label>Document Type:</label>

                                    <div class="input-group" style=" margin:5px 0 0 0 !important;">
                                        <select id="docType" class="select2-hidden-accessible" style="width: 100%;">
                                            <option value="SALES">Sales</option>
                                            <option value="SALE_RETURN">Sale Return</option>
                                            <option value="PURCHASE">Purchase</option>
                                            <option value="PURCHASE_RETURN">Purchase Return</option>
                                        </select>


                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label>Date Range:</label>

                                    <div class="input-group">
                                        <input class="dateRange" type="text" name="dateRange"
                                               style="border-radius: 6px;margin: 4px;" id="dateRange"/>
                                        <button class="input-group-btn btn btn-info"
                                                onclick="getDocuments()"><strong><i class="zmdi zmdi-search"></i> Search</strong></button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover vehicleDetailTable dataTable">
                                <thead>
                                <tr>
                                    <th style="width: 20%">Customer Name</th>
                                    <th style="width: 20%">City</th>
                                    <th style="width: 20%">Transaction Type</th>
                                    <th style="width: 20%">Transaction Number</th>
                                    <th style="width: 20%">Transaction Date</th>
                                    <th style="width: 20%">Transporter</th>
                                    <th style="width: 20%">Transport Date</th>
                                    <th style="width: 20%">LR No.</th>
                                </tr>
                                </thead>
                                <tbody id="shipmentDetailsTableBody">

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
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/moment.min.js"/>
<asset:javascript src="/themeassets/plugins/daterangepicker/daterangepicker.js"/>
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.min.js"/>
<script>

    $("#docType").select2();
    $('.dateRange').daterangepicker({
        locale: {
            format: "DD/MM/YYYY"
        }
    });

    function getDocuments(){

        var searchingSwal = Swal.fire({
            // title: "Loading",
            html:
                '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
            showDenyButton: false,
            showCancelButton: false,
            showConfirmButton: false,
            allowOutsideClick: false,
            background: 'transparent'

        });

        try{
            var dateRange = $("#dateRange").val();
            var docType = $("#docType").val();
            $.ajax({
                type: 'POST',
                url: '/transportation-info/getdocuments',
                data:{
                    dateRange: dateRange,
                    docType: docType
                },
                dataType: 'json',
                success: function (data) {
                    $("#shipmentDetailsTableBody").empty();


                    if(data != null)
                    {
                        if(data["SALES"] != null)
                        {
                            $.each (data["SALES"], function (index, value) {
                                var lrNumberValue = "";
                                var transportDateValue = "";
                                var transporterDropdown = "<select data-doctype='SALE_INVOICE' data-id='"+value?.id+"' class='select2-container' onchange='updateDetails(this)' id='"+value.id+"'>";
                                transporterDropdown += "<option selected disabled>--SELECT--</option>"
                                <g:each in="${transporters}" var="transporter" >
                                transporterDropdown += "<option value='${transporter.id}'>${transporter.name}</option>"
                                </g:each>
                                transporterDropdown += "</select>";
                                var transportationDetails = value.transportationDetails;
                                if(transportationDetails != null)
                                {
                                    lrNumberValue = transportationDetails.lrNumber;
                                    transportDateValue = transportationDetails.lrDate;
                                    var date = moment(transportDateValue);
                                    transportDateValue = date.format("YYYY-MM-DD");
                                }

                                var transportDate = "<input type='date' data-doctype='SALE_INVOICE' data-id='"+value?.id+"' onblur='updateDetails(this)' value='"+transportDateValue+"'/>";
                                var LRNumber = "<input type='text' data-doctype='SALE_INVOICE' data-id='"+value?.id+"' value='"+lrNumberValue+"' onblur='updateDetails(this)'/>";

                                var row = "<tr> <td>"+value?.customer?.entityName+"</td><td>"+value?.city?.areaName+"</td><td>Sale Entry</td><td>"+value?.invoiceNumber+"</td><td>"+value?.entryDate+"</td><td>"+transporterDropdown+"</td><td>"+transportDate+"</td><td>"+LRNumber+"</td></tr>";
                                $("#shipmentDetailsTableBody").append(row);

                                if(transportationDetails != null) {
                                    $("#" + value.id).val(transportationDetails.transporterId);
                                }
                            });
                        }
                    }
                    searchingSwal.close();
                }, error: function () {
                    searchingSwal.close();
                }
            });
        }
        catch (ex)
        {
            searchingSwal.close();
        }
    }

    function updateDetails(element)
    {
        var value = element.value;
        var id = $(element).attr("data-id");
        var doctype = $(element).attr("data-doctype");
        alert(doctype + " : "+id + " : "+value);
    }
</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("shipments-menu");
</script>
</body>
</html>