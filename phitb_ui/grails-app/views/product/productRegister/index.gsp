<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt ::  Products</title>
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
    <asset:stylesheet  src="/themeassets/plugins/handsontable/handsontable.full.css" rel="stylesheet" />


</head>
<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="PharmIT"></div>
        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header" style="padding: 1px;">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Product</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Product</li>
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
                    <div class="header" style="padding: 1px;">
                    <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"
                            data-target="#addAccountModeModal"><span class="glyphicon glyphicon-save-file"></span> Save</button>
                </div>
                    <div class="body">
                        <div class="table-responsive">
                            <div id="productTable" style="width:100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<g:include view="controls/add-account-mode.gsp"/>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
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
<asset:javascript src="/themeassets/plugins/handsontable/handsontable.full.js"/>


<script>
    const data = [

        ['','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','']
    ];
   var productGrp = [];
   var productTypes = [];
   var divisions = [];
   var productCategories = [];
   var productSchedules = [];
   var racks = [];
   var compositions = [];
   <g:each in="${productGroups}" var="pg">
        productGrp.push('${pg.groupName}');
   </g:each>
    <g:each in="${productTypes}" var="pt">
    productTypes.push('${pt.productType}');
    </g:each>
    <g:each in="${divisions}" var="dv">
    divisions.push('${dv.divisionName}');
    </g:each>
    <g:each in="${productCategories}" var="pc">
    productCategories.push('${pc.categoryName}');
    </g:each>
    <g:each in="${productSchedules}" var="ps">
    productSchedules.push('${ps.scheduleCode}');
    </g:each>
    <g:each in="${racks}" var="r">
    racks.push('${r.rackName}');
    </g:each>

    <g:each in="${compositions}" var="c">
    compositions.push('${c.compositionName}');
    </g:each>
    const container = document.getElementById('productTable');
    const hot = new Handsontable(container, {
        data: data,
        minRows: 20,
        height: '500',
        width: 'auto',
        rowHeights: 25,
        manualRowResize: true,
        manualColumnResize: true,
        persistentState: true,
        contextMenu: true,
        colHeaders: ['<strong>Code</strong>', '<strong>Name</strong>', '<strong>Manufac.</strong>','<strong>Mkt. Cmpny.</strong>', '<strong>HSN</strong>',
            '<strong>Rack</strong>',
            '<strong>Division</strong>','<strong>Grp</strong>','<strong>Schedule</strong>','<strong>Category</strong>', '<strong>Composition</strong>', '<strong>Cost Range</strong>',
            '<strong>Type</strong>', '<strong>Unit</strong>', '<strong>Unit Pck</strong>', '<strong>Per Lot Qty</strong>',
            '<strong>Pur. Rate</strong>', '<strong>Pur. Trade Disc.</strong>', '<strong>Pur. Mrgn %</strong>',
            '<strong>Sale Rate</strong>', '<strong>Sale Trade disc.</strong>',
            '<strong>Sale. Mrgn %</strong>', '<strong>Salesman %', '<strong>VIP Pur. Rate</strong>', '<strong>VIP Sale Rate</strong>',
            '<strong>MRP</strong>', '<strong>PTR</strong>', '<strong>Restricted Rt.</strong>', '<strong>NRI Rt.</strong>', '<strong>Salesman Comm.</strong>',
            '<strong>Gross Prft. %</strong>', '<strong>Tax</strong>', '<strong>Thrshld Lvl</strong>', '<strong>Order Qty</strong>',
            '<strong>Disc Allwd</strong>', '<strong>CCM Prdct</strong>', '<strong>Narration</strong>', '<strong>Restricted Asignmt</strong>'],
        columns: [
            {type: 'text'},
            {type: 'text'},
            {type: 'dropdown', source: ['Cipla', 'pfizer', 'Dr. Reddy', 'green', 'blue', 'gray', 'black', 'white']},
            {type: 'dropdown', source: ['Chethana Pharma', 'Embiotic', 'orange', 'green', 'blue', 'gray', 'black', 'white']},
            {type: 'text'},
            {type: 'dropdown', source: racks},
            {type: 'dropdown', source: divisions},
            {type: 'dropdown', source: productGrp},
            {type: 'dropdown', source: productSchedules},
            {type: 'dropdown', source: productCategories},
            {type: 'dropdown', source: compositions},
            {type: 'dropdown', source: ['yellow', 'red', 'orange', 'green', 'blue', 'gray', 'black', 'white']},
            {type: 'dropdown', source: productTypes},
            {type: 'dropdown', source: ['yellow', 'red', 'orange', 'green', 'blue', 'gray', 'black', 'white']},
            {type: 'text'},
            {type: 'text'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'numeric'},
            {type: 'dropdown', source: ['yellow', 'red', 'orange', 'green', 'blue', 'gray', 'black', 'white']},
            {type: 'text'},
            {type: 'numeric'},
            {type: 'text'},
            {type: 'text'},
            {type: 'text'},
            {type: 'text'}
        ],
        minSpareRows: 1,
        fixedColumnsLeft: 2,
        licenseKey: 'non-commercial-and-evaluation'
    });
</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>