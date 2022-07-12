<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Sample Conversion</title>
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
    <asset:stylesheet src="/themeassets/plugins/dropify/dist/css/dropify.min.css"/>
%{--    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css"/>--}%

    <asset:stylesheet src="/themeassets/plugins/select2/dist/css/select2.min.css"/>

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
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Sample Conversion</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/sample-conversion">Sample Conversion</a></li>
                        <li class="breadcrumb-item active">Sample Conversion</li>
                    </ul>
                </div>

            </div>
        </div>


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    <div class="body">
%{--                        <form id="sampleConversionForm" action="" method="POST">--}%
                            <h3>Saleable</h3>

                            <div class="row">
                                <div class="col-md-6">

                                    <div class="form-group">
                                        <label for="salebleitem">Item</label>
                                        <select id="salebleitem" name="salebleitem" class="form-control"  onchange="getSaleableBatch()">
                                            <option value="">--Please Select--</option>
                                            <g:each in="${productList}" var="p">
                                                <g:if test="${p.saleType == Constants.SALEABLE}">
                                                <option value="${p.id}">${p.productName}</option>
                                                </g:if>
                                            </g:each>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="saleblebatch">Batch</label>
                                        <select id="saleblebatch" name="saleblebatch" class="form-control" onchange="salebaleBatchChanged()">
%{--                                            <option value="">B001</option>--}%
%{--                                            <option value="">B002</option>--}%
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Quantity</label>
                                        <input type="number" name="saleableQuantity"  id="saleableQuantity"
                                               class="saleableQuantity form-control" data-qty="" readonly/>
                                    </div>
                                </div>
                            </div>
                            <br>

                            <h3>Sample</h3>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="sampleitem">Item</label>
                                        <select id="sampleitem" name="sampleitem" class="form-control"  onchange="getSampleBatch()">
                                            <option value="">--Please Select--</option>
                                            <g:each in="${productList}" var="p">
                                                <g:if test="${p.saleType == Constants.SAMPLE}">
                                                    <option value="${p.id}">${p.productName}</option>
                                                </g:if>
                                            </g:each>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="samplebatch">Batch</label>
                                        <select id="samplebatch" name="samplebatch" class="form-control"
                                                onchange="sampleBatchChanged()">
%{--                                            <option value="">B001</option>--}%
%{--                                            <option value="">B002</option>--}%
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Quantity</label>
                                        <input type="number" name="sampleQuantity"  id="sampleQuantity" onblur="validateSampleQty()"
                                               class="sampleQuantity form-control"/>
                                    </div>
                                </div>
                            </div>
                            <br>

                            <div class="row">
                                <div class="col-md-6">
                                    <button class="btn btn-primary" type="submit" onclick="saveSampleConversion()">Save</button>
                                </div>
                            </div>
%{--                        </form>--}%

                    </div>
                </div>
            </div>
        </div>

    </div>
</section>
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
%{--<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>--}%
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<asset:javascript src="/themeassets/plugins/select2/dist/js/select2.full.min.js"/>

<script>
    $("#salebleitem").select2();
    $("#sampleitem").select2();

    $("#saleblebatch").select2();
    $("#samplebatch").select2();


    var salableBatches = [];
    function getSaleableBatch() {
        var id = $("#salebleitem").val();
        var $select = $('#saleblebatch');
        $(".saleableQuantity").val("");
        $.ajax({
            type: 'POST',
            url: '/stockbook/product/' + id,
            dataType: 'json',
            success: function (data) {
                if(data != null && data.length>0)
                {
                    $select.find('option').remove();
                    $select.append('<option selected disabled>SELECT BATCH</option>');
                    $.each(data, function (i) {
                        salableBatches.push(data[i]);
                        key = data[i].batchNumber;
                        value = data[i].batchNumber;
                        $select.append('<option data-id='+data[i].id+' value="' + key + '">' + value + '</option>');
                    });
                }
                else {
                    salableBatches = [];
                    $select.find('option').remove();
                }
            }, error: function () {
                salableBatches = [];
                $select.find('option').remove();
            }
        });

    }
    function salebaleBatchChanged()
    {
        var batchId = $('#saleblebatch').find(':selected').data('id');
        if(batchId) {
            for (var i = 0; i < salableBatches.length; i++) {
                if (salableBatches[i].id === batchId) {
                    $(".saleableQuantity").val(salableBatches[i].remainingQty);
                    $('#saleableQuantity').attr("data-qty",salableBatches[i].remainingQty);

                }
            }
        }
    }


    var sampleBatches = [];
    function getSampleBatch() {
        var id = $("#sampleitem").val();
        var $select = $('#samplebatch');
        // $(".saleableQuantity").val("");
        $.ajax({
            type: 'POST',
            url: '/stockbook/product/' + id,
            dataType: 'json',
            success: function (data) {
                if(data != null && data.length>0)
                {
                    $select.find('option').remove();
                    $select.append('<option selected disabled>SELECT BATCH</option>');
                    $.each(data, function (i) {
                        salableBatches.push(data[i]);
                        key = data[i].batchNumber;
                        value = data[i].batchNumber;
                        $select.append('<option data-id='+data[i].id+' value="' + key + '">' + value + '</option>');
                    });
                }
                else {
                    sampleBatches = [];
                    $select.find('option').remove();
                }
            }, error: function () {
                salableBatches = [];
                $select.find('option').remove();
            }
        });

    }
    function sampleBatchChanged()
    {
        var batchId = $('#samplebatch').find(':selected').data('id');
        console.log(batchId)
        // if(batchId) {
        //     for (var i = 0; i < salableBatches.length; i++) {
        //         if (salableBatches[i].id === batchId) {
        //             // $(".sampleQuantity").val(salableBatches[i].remainingQty);
        //         }
        //     }
        // }
    }
    function validateSampleQty()
    {
        var sampleQty = $("#sampleQuantity").val();
        var saleableQty = $("#saleableQuantity").val();
        if(Number(sampleQty) > Number(saleableQty))
        {
            $("#sampleQuantity").val(0);
        }
    }

    function saveSampleConversion() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, Please!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {

                Swal.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        var waitingSwal = Swal.fire({
                            title: "Please wait!",
                            showDenyButton: false,
                            showCancelButton: false,
                            showConfirmButton: false,
                            allowOutsideClick: false
                        });

                        var saleableProduct = $("#salebleitem").val();
                        var saleableBatch = $("#saleblebatch").val();
                        var saleableQty = $("#saleableQuantity").val();
                        var sampleProduct = $("#sampleitem").val();
                        var sampleBatch = $("#samplebatch").val();
                        var sampleQty = $("#sampleQuantity").val();

                        if (!saleableProduct) {
                            alert("Please select saleable product.");
                            waitingSwal.close();
                            return;
                        }

                        if (!saleableBatch) {
                            alert("Please select saleable batch.");
                            waitingSwal.close();
                            return;
                        }

                        if (!saleableQty) {
                            alert("Please enter saleable quantity.");
                            waitingSwal.close();
                            return;
                        }

                        if (!sampleProduct) {
                            alert("Please select sample product.");
                            waitingSwal.close();
                            return;
                        }

                        if (!sampleBatch) {
                            alert("Please select sample batch.");
                            waitingSwal.close();
                            return;
                        }

                        if (!sampleQty) {
                            alert("Please select sample quantity.");
                            waitingSwal.close();
                            return;
                        }

                        if(Number(sampleQty) > Number(saleableQty))
                        {
                            alert("Sample quantity should not be greater");
                            waitingSwal.close();
                            return;
                        }

                        $.ajax({
                            type: "POST",
                            url: "/sample-conversion/save",
                            dataType: 'json',
                            data: {
                                saleableProduct: saleableProduct,
                                saleableBatch: saleableBatch,
                                saleableQty: saleableQty,
                                sampleProduct: sampleProduct,
                                sampleBatch: sampleBatch,
                                sampleQty: sampleQty,
                            },
                            success: function (data) {
                                waitingSwal.close();
                                alert("success!!")
                            },
                            error: function () {
                                waitingSwal.close();
                                Swal.fire({
                                    title: "Something went wrong",
                                    confirmButtonText: 'OK',
                                    allowOutsideClick: false
                                }).then((result) => {
                                    // resetData();
                                });
                            }
                        });
                    }
                })

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'Convertion aborted!',
                    'error'
                )
            }
        })
    }



</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>