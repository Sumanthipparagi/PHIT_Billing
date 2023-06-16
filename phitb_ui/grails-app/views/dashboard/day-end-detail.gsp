<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="phitb">
    <title>:: PharmIT Billing :: Day End Details</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}">
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/morrisjs/morris.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jvectormap/jquery-jvectormap-2.0.3.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/color_skins.css"/>

    <style>
    body {
        counter-reset: Serial; /* Set the Serial counter to 0 */
    }

    table {
        border-collapse: separate;
    }

    tr td:first-child:before {
        counter-increment: Serial; /* Increment the Serial counter */
        content: "Serial is: " counter(Serial); /* Display the counter */
    }

    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="phitb"></div>

        <p>Please wait...</p>
    </div>
</div>

<g:include view="controls/sidebar.gsp"/>


<!-- Main Content -->
<section class="content home">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Day End Details</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Day End Details</li>
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

        %{--        <div class="table-responsive">--}%
        <div class="row">
            <div class="col-lg-6">
                <h5>Draft Sale Products</h5>
                <g:if test="${draftSaleBillDetails?.size() != 0 && draftSaleBillDetails != null}">
                    <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                        <thead>
                        <tr>
%{--                            <th scope="col">#</th>--}%
                            <th scope="col">Product Name</th>
                            <th scope="col">Sale Qty</th>
                            <th scope="col">Free Qty</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tbody>
                        <g:each in="${draftSaleBillDetails}" var="d" status="i">
                            <g:each in="${d?.products}" var="product" status="j">
                                <tr>
%{--                                    <th scope="row" class="slno"></th>--}%
                                    <td style="white-space: break-spaces;">${product?.product?.productName}</td>
                                    <td>${product?.sqty}</td>
                                    <td>${product?.freeQty}</td>
                                </tr>
                            </g:each>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:else>
                    <p>No details found</p>
                </g:else>
            </div>

            <div class="col-lg-6">
                <h5>Draft Purchase Products</h5>
                <g:if test="${draftPurchaseBillDetails?.size() != 0 && draftPurchaseBillDetails != null}">
                    <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                        <thead>
                        <tr>
%{--                            <th scope="col">#</th>--}%
                            <th scope="col">Product Name</th>
                            <th scope="col">Sale Qty</th>
                            <th scope="col">Free Qty</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tbody>
                        <g:each in="${draftPurchaseBillDetails}" var="p" status="i">
                            <g:each in="${p?.products}" var="product">
                                <tr>
%{--                                    <th scope="row">${i + 1}</th>--}%
                                    <td style="white-space: break-spaces;">${product?.product?.productName}</td>
                                    <td>${product?.sqty}</td>
                                    <td>${product?.freeQty}</td>
                                </tr>
                            </g:each>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>
                <g:else>
                    <p>No details found</p>
                </g:else>

            </div>
        </div>

        <button type="button" class="btn btn-danger float-right" id="dayEnd">Day End</button>

        %{--        </div>--}%

    </div>
</section>
<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/knob.bundle.js"/>
<asset:javascript src="/themeassets/bundles/jvectormap.bundle.js"/>
<asset:javascript src="/themeassets/bundles/morrisscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/sparkline.bundle.js"/>
%{--<asset:javascript src="/themeassets/bundles/doughnut.bundle.js"/>--}%
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/index.js"/>
<g:include view="controls/footer-content.gsp"/>
<script>

    /* day end */

    $("#dayEnd").on("click", function (e) {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "All draft details and temp stock data will be removed!!",
            icon: 'warning',
            showCancelButton: true,
            cancelButtonText: 'No',
            confirmButtonText: 'Yes',
            reverseButtons: false
        }).then((result) => {
            if (result.isConfirmed) {
                /*   swalWithBootstrapButtons.fire(
                       'Day end completed',
                       'Day end Sucessful',
                       'success'
                   )*/
                $.ajax({
                    type: "POST",
                    url: "/day-end",
                    dataType: 'json',
                    success: function (data) {
                        if (Object.keys(data).length !== 0) {
                            if (data?.saleStocks === 'SUCCESS') {
                                alert("Sale stocks updated")
                            } else {
                                alert("Sale stocks not found")
                            }

                            if (data?.tempstocks === 'SUCCESS') {
                                alert("Temp stock book cleared!")
                            } else {
                                alert("Temp stock  not found")
                            }

                            if (data?.draftSaleBillDelete === 'SUCCESS') {
                                alert("Draft Delete success")
                            } else {
                                alert("Draft bills not found")
                            }

                            if (data?.purchaseStocks === 'SUCCESS') {
                                alert("Purchase Stocks updated")
                            } else {
                                alert("Purchase Stocks  not found")
                            }

                            if (data?.deleteDraftPurchaseBill === 'SUCCESS') {
                                alert("Purchase Draft Bill Delete success")
                            } else {
                                alert("Purchase Draft Bills not found")
                            }
                        } else {
                            alert("No details found!")
                        }

                        swalWithBootstrapButtons.fire(
                            'Day end completed',
                            'Day end Sucessful',
                            'success'
                        )

                    },
                    error: function (data) {
                        console.log("Failed");
                    }
                });

            } else {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'Day end terminated by user',
                    'error'
                )
            }
        })
    });
</script>

<script>
    selectSideMenu("dashboard-menu");
</script>
</body>
</html>