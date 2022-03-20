<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Stock Entry</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
%{--    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>--}%
    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>


    <style>


    .editbtn {
        padding: 1px 9px;
    }

    .deletebtn {
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
                    <h2>Stock Book</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Stock Entry</li>
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
                    <div class="header">
                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                                data-target="#addstockModal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">Add Stocks</font></font></button>
                    </div>

                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover stockTable dataTable">
                                <thead>
                                <tr>
                                    <th style="width: 20%">Product</th>
                                    <th style="width: 20%">Batch Number</th>
                                    <th style="width: 20%">Manufacture Date</th>
                                    <th style="width: 20%">Expiry Date</th>
                                    <th style="width: 20%">Purchase Rate</th>
                                    <th style="width: 20%">Sale Rate</th>
                                    <th style="width: 20%">MRP</th>
                                    <th style="width: 20%">Rem Qty</th>
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


<g:include view="controls/inventory/add-stock.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
%{--<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>--}%
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
%{--<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>--}%
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
%{--<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>--}%
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.js" type="text/javascript"/>

<script>

    var fridgetable;
    var id = null;
    $(function () {
       $("#product").select2();
       $("#batchNumber").select2();
       $("#supplierId").select2();
        stockTable();

    });

    $('#expDate').bootstrapMaterialDatePicker({
        time:false,
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $('#purcDate').bootstrapMaterialDatePicker({
        time:false,
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $('#manufacturingDate').bootstrapMaterialDatePicker({
        time:false,
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });



    function stockTable() {
        fridgetable = $(".stockTable").DataTable({
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
                searchPlaceholder: "Search Batch Register"
            },
            ajax: {
                type: 'GET',
                url: '/stockbook/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var manfDate = new Date(json.data[i].manufacturingDate);
                        var expiryDate = new Date(json.data[i].expDate);
                        var editbtn = '<button type="button" data-id="' + json.data[i].id +
                            '" data-product="' + json.data[i].productId + '"' +
                            '" data-batchNumber="' + json.data[i].batchNumber + '"' +
                            '" data-manfDate="' + moment(manufacturingDate).format('DD/MM/YYYY') + '"' +
                            '" data-expiryDate="' + moment(expDate).format('DD/MM/YYYY') + '"' +
                            '" data-purcDate="' + moment(purcDate).format('DD/MM/YYYY') + '"' +
                            '" data-purchaseRate="' + json.data[i].purchaseRate + '"' +
                            '" data-purcTradeDiscount="' + json.data[i].purcTradeDiscount + '"' +
                            '" data-purcSeriesId="' + json.data[i].purcSeriesId + '"' +
                            '" data-supplierId="' + json.data[i].supplierId + '"' +
                            '" data-remainingQty="' + json.data[i].remainingQty + '"' +
                            '" data-remainingFreeQty="' + json.data[i].remainingFreeQty + '"' +
                            '" data-saleRate="' + json.data[i].saleRate + '"' +
                            '" data-mrp="' + json.data[i].mrp + '"' +
                            '" data-packingDesc="' + json.data[i].packingDesc + '"' +
                            '" data-purcProductValue="' + json.data[i].purcProductValue + '"' +
                            '" data-taxId="' + json.data[i].taxId.id + '"' +
                            '" data-entityId="' + json.data[i].entityId + '"' +
                            '" data-entityType="' + json.data[i].entityTypeId + '"' +
                            '"' +
                            ' class="editbtn btn btn-sm btn-warning  editbtn" data-toggle="modal" data-target="#addstockModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">edit</font></font></i></button>'
                        var deletebtn = '<button type="button" data-id="' + json.data[i].id +
                            '" class="btn btn-sm btn-danger deletebtn" data-toggle="modal" data-target=".deleteModal"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">delete</font></font></i></button>'
                        return_data.push({
                            'id': json.data[i].id,
                            'product': json.data[i].productId,
                            'batchNumber': json.data[i].batchNumber,
                            'manfDate': moment(manfDate).format('DD/MM/YYYY'),
                            'expiryDate': moment(expiryDate).format('DD/MM/YYYY'),
                            'purchaseRate': json.data[i].purchaseRate,
                            'saleRate': json.data[i].saleRate,
                            'mrp': json.data[i].mrp,
                            'remainingQty': json.data[i].remainingQty,
                            'action': editbtn + ' ' + deletebtn
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'product', 'width': '20%'},
                {'data': 'batchNumber', 'width': '20%'},
                {'data': 'manfDate', 'width': '20%'},
                {'data': 'expiryDate', 'width': '20%'},
                {'data': 'purchaseRate', 'width': '20%'},
                {'data': 'saleRate', 'width': '20%'},
                {'data': 'mrp', 'width': '20%'},
                {'data': 'remainingQty', 'width': '20%'},
                {'data': 'action', 'width': '20%'}
            ]
        });
    }


    $('.manfDate').bootstrapMaterialDatePicker({
        time: false,
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $('.expiryDate').bootstrapMaterialDatePicker({
        time: false,
        format: 'DD/MM/YYYY',
        clearButton: true,
        shortTime: true,
        weekStart: 1
    });

    $(".stockForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();
        var entityTypeId = $("#entityId").find(':selected').attr("data-type");
        $(".entityType").val(entityTypeId);


        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/stockbook/' + id;
            type = 'POST'
        } else {
            url = '/stockbook';
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
                swal("Success!", "Stocks added Successfully", "success");
                stockTable();
                $('#addstockModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".stockTitle").text("Add Stocks");
        $(".stockForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".product").val($(this).attr('data-product')).change();
        $(".batchNumber").val($(this).attr('data-batchNumber'));
        $(".manfDate").val($(this).attr('data-manfDate'));
        $(".expiryDate").val($(this).attr('data-expiryDate'));
        $(".purchaseRate").val($(this).attr('data-purchaseRate'));
        $(".ptr").val($(this).attr('data-ptr'));
        $(".mrp").val($(this).attr('data-mrp'));
        $(".box").val($(this).attr('data-box'));
        $(".saleRate").val($(this).attr('data-saleRate'));
        $(".qty").val($(this).attr('data-qty'));
        $(".caseWt").val($(this).attr('data-caseWt'));
        $(".productCat").val($(this).attr('data-productCat')).change();
        $(".entityId").val($(this).attr('data-entityId')).change();
        $(".entityType").val($(this).attr('data-entityType')).change();
        $(".stockTitle").text("Update Batch Register");
    });

    $('.entityId').change(function () {
        var type = $('option:selected', this).attr('data-type');
        $(".entityTypeId").val(type);
    });


    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Batch Register ?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/stockbook/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                stockTable();
                swal("Success!", "Stock Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }

    var batches = [];
    function getBatch() {
        var id = $("#product").val();
        var $select = $('#batchNumber');
        $.ajax({
            type: 'POST',
            url: '/batch-register/product/' + id,
            dataType: 'json',
            success: function (data) {
                if(data != null && data.length>0)
                {
                    $select.find('option').remove();
                    $select.append('<option selected disabled>SELECT BATCH</option>');
                    $.each(data, function (i) {
                        batches.push(data[i]);
                        key = data[i].batchNumber + "_"+ data[i].id;
                        value = data[i].batchNumber;
                        $select.append('<option value="' + key + '">' + value + '</option>');
                    });
                }
                else {
                    batches = [];
                    $select.find('option').remove();
                }
            }, error: function () {
                batches = [];
                $select.find('option').remove();
            }
        });

    }

    function batchChanged()
    {
        var batchId = $('#batchNumber').val();
        if(batchId) {
            batchId = batchId.split("_")[1];
            for (var i = 0; i < batches.length; i++) {
                if (batches[i].id == batchId) {
                    $(".manufacturingDate").val(moment(batches[i].manfDate).format('DD/MM/YYYY'));
                    $(".expDate").val(moment(batches[i].expiryDate).format('DD/MM/YYYY'));
                    $(".purchaseRate").val(batches[i].purchaseRate);
                    $(".saleRate").val(batches[i].saleRate);
                    $(".mrp").val(batches[i].mrp);
                    $(".remainingQty").val("0");
                }
            }
        }
    }
</script>

</body>
</html>