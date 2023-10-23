<style>


@media (min-width: 1281px) {

    .menu_sm .sidebar .list {
        display: none !important;
    }

}

/*
  ##Device = Laptops, Desktops
  ##Screen = B/w 1025px to 1280px
*/

@media (min-width: 1025px) and (max-width: 1280px) {

    /* CSS */
    .menu_sm .sidebar .list {
        display: none !important;
    }

}
</style>

<%@ page import="phitb_ui.Constants; phitb_ui.UtilsService" %>
<div class="overlay_menu">
    <button class="btn btn-primary btn-icon btn-icon-mini btn-round"><i class="zmdi zmdi-close"></i></button>

    <div class="container">
        <div class="row clearfix">
            <div class="card">
                <div class="body">
                    <div class="input-group m-b-0">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-addon">
                            <i class="zmdi zmdi-search"></i></span>
                    </div>

                </div>
            </div>

            <div class="card links">
                <div class="body">
                    <div class="row">
                        <div class="col-lg-3 col-md-6 col-sm-12">
                            <h6>App</h6>
                            <ul class="list-unstyled">
                                <li><a href="mail-inbox.html">Inbox</a></li>
                                <li><a href="chat.html">Chat</a></li>
                                <li><a href="events.html">Calendar</a></li>
                                <li><a href="file-dashboard.html">File Manager</a></li>
                                <li><a href="contact.html">Contact list</a></li>
                            </ul>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-12">
                            <h6>User Interface (UI)</h6>
                            <ul class="list-unstyled">
                                <li><a href="ui_kit.html">UI KIT</a></li>
                                <li><a href="tabs.html">Tabs</a></li>
                                <li><a href="range-sliders.html">Range Sliders</a></li>
                                <li><a href="modals.html">Modals</a></li>
                                <li><a href="alerts.html">Alerts</a></li>
                                <li><a href="dialogs.html">Dialogs</a></li>
                                <li><a href="collapse.html">Collapse</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12">
                <div class="social">
                    <a class="icon" href="#" target="_blank"><i
                            class="zmdi zmdi-facebook"></i></a>
                    <a class="icon" href="#" target="_blank"><i
                            class="zmdi zmdi-behance"></i></a>
                    <a class="icon" href="javascript:void(0);"><i class="zmdi zmdi-twitter"></i></a>
                    <a class="icon" href="javascript:void(0);"><i class="zmdi zmdi-linkedin"></i></a>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="overlay"></div><!-- Overlay For Sidebars -->

<!-- Left Sidebar -->
<aside id="minileftbar" class="minileftbar">
    <ul class="menu_list">
        <li>
            <a href="javascript:void(0);" class="bars"></a>
            <a class="navbar-brand" href="/dashboard"><img src="${assetPath(src: '/themeassets/images/sidebar.jpeg')}"
                                                           alt="PharmIT"></a>
        </li>
        %{--                <li><a href="javascript:void(0);" class="btn_overlay hidden-sm-down"><i class="zmdi zmdi-search"></i></a></li>--}%
        <li><a href="javascript:void(0);" class="menu-sm"><i class="zmdi zmdi-swap"></i></a></li>

        <li><a href="javascript:void(0);" class="fullscreen" data-provide="fullscreen"><i
                class="zmdi zmdi-fullscreen"></i></a></li>
        <li class="power">
            <g:if test="${session.getAttribute("financialYearValid")}">
                <a href="/day-end-details"><i class="zmdi zmdi-settings zmdi-view-day"></i></a>
            </g:if>
            <a href="javascript:void(0);" class="js-right-sidebar"><i class="zmdi zmdi-settings zmdi-info"></i></a>
            <a href="/logout" id="logout" class="mega-menu"><i class="zmdi zmdi-power"></i></a>
        </li>
    </ul>
</aside>

<aside class="right_menu">
    <div id="rightsidebar" class="right-sidebar">
        <ul class="nav nav-tabs">
            <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#tempStock">Temp Stock Pool</a></li>
            <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#setting"><i
                    class="zmdi zmdi-settings zmdi-hc-spin"></i> Settings</a></li>

        </ul>

        <div class="tab-content slim_scroll">
            <div class="tab-pane slideLeft active" id="tempStock">
                <div class="card">
                    <div class="header">
                        <h2><strong>Temporary</strong> Stockbook</h2>
                    </div>

                    <div class="body">
                        <div id="tempStockSidebar">
                            <div style="text-align: center">
                                <i class="zmdi zmdi-storage" style="font-size: 48px;"></i><br>
                                <small>temporary stocks empty</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane slideRight" id="setting">
                <div class="card">
                    <div class="header">
                        <h2><strong>Colors</strong> Skins</h2>
                    </div>

                    <div class="body">
                        <ul class="choose-skin list-unstyled m-b-0">
                            <li data-theme="black" class="active">
                                <div class="black"></div>
                            </li>
                            <li data-theme="purple">
                                <div class="purple"></div>
                            </li>
                            <li data-theme="blue">
                                <div class="blue"></div>
                            </li>
                            <li data-theme="cyan">
                                <div class="cyan"></div>
                            </li>
                            <li data-theme="green">
                                <div class="green"></div>
                            </li>
                            <li data-theme="orange">
                                <div class="orange"></div>
                            </li>
                            <li data-theme="blush">
                                <div class="blush"></div>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="card">
                    <div class="header">
                        <h2><strong>Left</strong> Menu</h2>
                    </div>

                    <div class="body theme-light-dark">
                        <button class="t-dark btn btn-primary btn-round btn-block">Dark</button>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div id="leftsidebar" class="sidebar">
        <div class="menu">
            <ul class="list">
                <li>
                    <div class="user-info m-b-20">

                        <div class="detail">
                            <h6><a href="/user/update-details/${session.getAttribute('userId')}">${session.getAttribute("userName")}</a>
                            </h6>

                            <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">
                                <p class="m-b-0">${session.getAttribute("entityName")}</p>
                            </g:if>
                            <g:if test="${session.getAttribute("financialYearValid")}">
                                <small>Financial Year: ${session.getAttribute("financialYear")}</small>
                            </g:if>
                            <g:else>
                                <small style="color: red;font-weight: 800;">Financial Year: ${session.getAttribute("financialYear")}</small>
                            </g:else>

                        </div>
                    </div>
                </li>
                %{--<li class="header">MAIN</li>--}%
                <li id="dashboard-menu" class="sidemenuitem active open"><a href="/dashboard"><i
                        class="zmdi zmdi-home"></i><span>Dashboard</span></a>
                </li>
                <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">
                    <li id="purchase-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-shopping-basket"></i><span>Purchase</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/purchase-order">Purchase Order</a></li>
                                </g:if>
                                <li><a href="/purchase-order-list">My Purchase Order</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_ENTRY", session.getAttribute("permittedFeatures").toString()) && session.getAttribute("financialYearValid")}">
                                <li><a href="/purchase-entry">Purchase Entry</a></li>
                            </g:if>
                            <li><a href="/purchase-bill-list">Purchase Invoices</a></li>
                            <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_RETURN", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/purchase-return">Purchase Return</a></li>
                                </g:if>
                                <li><a href="/purchase-return/purchase-return-list">My Returns</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_MARK_RECIEVED_GOODS", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="#">Mark Received Goods</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_VOUCHER", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="#">Purchase Voucher</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_SALE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/grn">Goods Receipt Note</a></li>
                            </g:if>
                        </ul>
                    </li>

                    <li id="sales-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-money-box"></i><span>Sales</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${UtilsService.isPermitted("VIEW_SALE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/sale-order-entry">Sale Order</a></li>
                                </g:if>
                                <li><a href="/sale-order-entry/my-orders">My Orders</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_SALE_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/sale-entry">Sale Entry</a></li>
                                </g:if>
                                <li><a href="/sale-bill-list">My Invoices</a></li>
                                <g:if test="${session.getAttribute('role').toString() == 'RETAILER'}">
                                    <g:if test="${session.getAttribute("financialYearValid")}">
                                        <li><a href="/sale-entry-retailer">Retailer Sale Entry</a></li>
                                    </g:if>
                                    <li><a href="/retailer-bill-list">Retailer Invoices</a></li>
                                </g:if>
                            %{-- <g:if test="${session.getAttribute('role').toString()=='RETAILER'}">
                                    <li><a href="/sale-entry-retailer">Sale Entry Retailer</a></li>
                                    <li><a href="/retailer-bill-list">Invoices</a></li>
                                </g:if>
                                <g:else>
                                    <li><a href="/sale-entry">Sale Entry</a></li>
                                    <li><a href="/sale-bill-list">My Invoices</a></li>
                                </g:else>--}%
                            </g:if>



                            <g:if test="${UtilsService.isPermitted("VIEW_SALE_RETURN", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="javascript:void(0);" class="menu-toggle">Sales Return</span> <span
                                        class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/sale-return">Create Sales Return</a></li>
                                        </g:if>
                                        <li><a href="/sale-return/my-returns">Sales Return List</a></li>

                                    </ul>
                                </li>
                            </g:if>


                            <li><a href="javascript:void(0);" class="menu-toggle">Delivery Challan</span> <span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <g:if test="${session.getAttribute("financialYearValid")}">
                                        <li><a href="/delivery-challan">Delivery Challan</a></li>
                                    </g:if>
                                    <li><a href="/delivery-challan-list">Delivery Challan List</a></li>

                                </ul>
                            </li>
                            <g:if test="${UtilsService.isPermitted("VIEW_SALE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/gtn">Goods Transfer Note</a></li>
                                </g:if>
                            </g:if>

                            <g:if test="${UtilsService.isPermitted("VIEW_SCHEME_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/scheme-entry">Scheme Entry (Offers)</a></li>
                                </g:if>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_STOCK_ADJUSTEMENT", session.getAttribute("permittedFeatures").toString())}">
                            %{--                                <li><a href="#">Stock Adjustment</a></li>--}%

                                <li><a href="javascript:void(0);" class="menu-toggle">Stock Adjustment</span> <span
                                        class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/stock-adjustment">Stock Adjustment</a></li>
                                        </g:if>
                                        <li><a href="/stock-adjustment-list">Stock Adjustment list</a></li>
                                    </ul>
                                </li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_DEBIT_SETTLEMENT", session.getAttribute("permittedFeatures").toString())}">
                            %{--                                <li><a href="/credit-debit-settlement">Credit Debit Settlement</a></li>--}%
                                <li><a href="javascript:void(0);"
                                       class="menu-toggle">Credit Debit Settlement</span> <span
                                            class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/credit-debit-settlement">Credit Debit Settlement</a></li>
                                        </g:if>
                                        <li><a href="/credit-debit-settlement/crdb-list">CR DB Settlement List</a></li>
                                    </ul>
                                </li>
                            </g:if>
                        %{--                        <li><a href="/sample-conversion">Sample Conversion</a></li>--}%

                            <li><a href="javascript:void(0);" class="menu-toggle">Sample Conversion</span> <span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <g:if test="${session.getAttribute("financialYearValid")}">
                                        <li><a href="/sample-conversion">Sample Conversion</a></li>
                                        <li><a href="/sample-conversion/sample-invoicing">Sample Invoice</a></li>
                                    </g:if>
                                    <li><a href="/sample-conversion/sample-invoice-list">Sample Invoice List</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li id="accounts-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-book"></i><span>Accounts</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${session.getAttribute("financialYearValid")}">
                                <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_JV", session.getAttribute("permittedFeatures").toString())}">
                                    <li><a href="/credit-jv">Credit JV</a></li>
                                </g:if>
                                <g:if test="${UtilsService.isPermitted("VIEW_DEBIT_JV", session.getAttribute("permittedFeatures").toString())}">
                                    <li><a href="/debit-jv">Debit JV</a></li>
                                </g:if>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_RECEIPT", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="javascript:void(0);" class="menu-toggle">Receipt</span> <span
                                        class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/receipt">Create Receipt</a></li>
                                        </g:if>
                                        <li><a href="/receipt-list">Receipt List</a></li>

                                    </ul>
                                </li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_CHEQUE_RETURNS", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="#">Cheque Returns</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PAYMENTS", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="javascript:void(0);" class="menu-toggle">Payments</span> <span
                                        class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/payments">Create Payments</a></li>
                                        </g:if>
                                        <li><a href="/payments-list">Payments List</a></li>
                                    </ul>
                                </li>
                            </g:if>

                            <g:if test="${session.getAttribute('role').toString() == Constants.EXECUTIVE}">

                                <li><a href="javascript:void(0);" class="menu-toggle">Payment Collection</span> <span
                                        class="badge badge-success float-right"></span></a>
                                    <ul class="ml-menu">
                                        <g:if test="${session.getAttribute("financialYearValid")}">
                                            <li><a href="/payment-collection">Payment Collection</a></li>
                                        </g:if>
                                        <li><a href="/payment-collection/logs">Logs</a></li>
                                    </ul>
                                </li>
                            </g:if>
                        </ul>
                    </li>





                    <li id="reports-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-chart"></i><span>Reports</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <li><a href="javascript:void(0);" class="menu-toggle">Sales Report</span><span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/reports/sales/customerwise">Customer-wise</a></li>
                                    <li><a href="/reports/sales/datewise">Date-wise</a></li>
                                    <li><a href="/reports/sales/areawise">Area-wise (Consolidated)</a></li>
                                    <li><a href="/reports/sales/areawisewithproducts">Area-wise (Products)</a></li>
                                    <li><a href="/reports/sales/areawiseconsolidatedproducts">Area-wise (Products-Consolidated)</a>
                                    </li>
                                    <li><a href="/reports/sales/consolidated">Consolidated</a></li>
                                    <li><a href="/reports/sales/productwise">Product-Wise</a></li>
                                    <li><a href="/reports/sales/gstreport">GST Report</a></li>
                                    <li><a href="/reports/sales/invoice-payment-report">Invoice Payment Report</a></li>
                                    <li><a href="/reports/sales/customer-ledger">Customer Ledger</a></li>
                                    <li><a href="/reports/sales/fastslowunsold">FSN Analysis</a></li>
                                </ul>
                            </li>
                            <li><a href="javascript:void(0);" class="menu-toggle">Accounts Report</span><span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/reports/accounts/outstanding">Outstanding</a></li>
                                    <li><a href="/reports/accounts/payments">Payments Report</a></li>
                                </ul>
                            </li>
                            <li><a href="javascript:void(0);" class="menu-toggle">Inventory Report</span><span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/reports/inventory/statement">Statement</a></li>
                                    <li><a href="/reports/inventory/stockreport">Stock Report</a></li>
                                    <li><a href="/reports/inventory/expiry">Expiry</a></li>
                                </ul>
                            </li>
                            <li><a href="javascript:void(0);" class="menu-toggle">Product Report</span><span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/reports/products/statement">Statement</a></li>
                                    <li><a href="/reports/batches/statement">Batchwise Statement</a></li>
                                    <li><a href="/reports/products/price-list">Price List</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </g:if>
                <li id="system-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-book-image"></i><span>System</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">
                            <li><a href="/accountmodes">Account Modes</a></li>
                            <li><a href="/priority">Priority</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_STATE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/state">State</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_CITY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/city">City</a></li>
                        </g:if>
                        <li><a href="/district">District</a></li>
                        <li><a href="/division-master">Division</a></li>
                        <li><a href="/region">Region</a></li>
                        <li><a href="/zone">Zone</a></li>
                    %{--                        <g:if test="${UtilsService.isPermitted("VIEW_COUNTRY", session.getAttribute("permittedFeatures").toString())}">--}%
                    %{--                            <li><a href="/country">Country</a></li>--}%
                    %{--                        </g:if>--}%
                        <g:if test="${UtilsService.isPermitted("VIEW_FORM", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/form">Form</a></li>
                        </g:if>

                    </ul>
                </li>

                <li id="facility-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-home"></i><span>Facility</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_CCM", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/ccm">CCM</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_RACK", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/rack">Rack</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_FRIDGE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/fridge">Fridge</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_GODOWN", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/godown">Godown</a></li>
                        </g:if>
                    </ul>
                </li>


                <li id="entity-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-library"></i><span>Entity</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">
                            <li><a href="/accounts">Account Register</a></li>
                            <li><a href="/accounts-list">Accounts List</a></li>
                        </g:if>

                        <g:if test="${UtilsService.isPermitted("VIEW_ENTITY_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <g:if test="${session.getAttribute('role') == Constants.ENTITY_ADMIN ||
                                    session.getAttribute('role') == Constants.SUPER_USER || session.getAttribute('role') == 'RETAILER'}">
                                <li><a href="/entity-register">Entity Register</a></li>
                            %{--<li><a href="/entity-route">Routes</a></li>--}%
                            </g:if>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_USER_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <g:if test="${session.getAttribute('role') == Constants.ENTITY_ADMIN || session.getAttribute('role') == Constants.SUPER_USER}">
                                <li><a href="/user-register">User Register</a></li>
                            </g:if>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_CUSTOMER_GROUP_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/customer-group-register">Customer Group</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_DAY_END", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/day-end-master">Day End Master</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_FINANCIAL_YEAR", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/financial-year-master">Financial Year Master</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_REGION", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/region-master">Region Master</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_ROUTE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/route-register">Route Register</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_ROLE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/role">Role</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_RULE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/rule">Rule Master</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_TAX", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/tax">Tax Register</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_BANK_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/bank-register">Bank</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_DEPARTMENT", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/department">Department</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_TERRITORY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/territory">Territory</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_TERMS_CONDITIONS", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/terms-conditions">Terms and Conditions</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_SERIES", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/series">Series</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_SERVICE_TYPE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/service-type">Service Type</a></li>
                        </g:if>
                        <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">
                            <li><a href="/hq-area">HQ areas</a></li>
                        </g:if>
                    </ul>
                </li>


                <g:if test="${session.getAttribute('role') != Constants.SUPER_USER}">

                    <li id="product-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-apps"></i><span>Product</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product">Product Register</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_BATCH_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/batch-register">Batch Register</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_DIVISION", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/division">Product Division</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_CATEGORY", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-category">Product Category</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_SCHEDULE", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-schedule">Product Schedule</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_GROUP", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-group">Product Group</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_COMPOSITION", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-composition">Product Composition</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_TYPE", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-type">Product Type</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_DIVISION_GROUP", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/division-group">Product Division Group</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_CLASS", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-class">Product Class</a></li>
                            </g:if>
                            <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_COST_RANGE", session.getAttribute("permittedFeatures").toString())}">
                                <li><a href="/product-cost-range">Product Cost Range</a></li>
                            </g:if>
                            <li><a href="/unit-type">Unit Type</a></li>
                        </ul>
                    </li>
                    <li id="inventory-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-store-24"></i><span>Inventory</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${UtilsService.isPermitted("VIEW_STOCKBOOK_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                                <g:if test="${session.getAttribute("financialYearValid")}">
                                    <li><a href="/stockbook">Stock Entry</a></li>
                                </g:if>
                            </g:if>
                        </ul>
                    </li>


                    <li id="shipments-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-truck"></i><span>Shipments</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <li><a href="/transporter">Transporter</a></li>
                            <li><a href="/transport-type">Transport Type</a></li>
                            <li><a href="/vehicle-detail">Vehicle Details</a></li>
                        </ul>
                    </li>


                    <li id="approvals-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                            class="zmdi zmdi-check-circle"></i><span>Approvals</span> <span
                            class="badge badge-success float-right"></span></a>
                        <ul class="ml-menu">
                            <g:if test="${session.getAttribute("financialYearValid")}">
                                <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_JV", session.getAttribute("permittedFeatures").toString())}">
                                    <li><a href="/credit-jv/approval">Credit JV Approval</a></li>
                                </g:if>
                                <g:if test="${UtilsService.isPermitted("VIEW_DEBIT_JV", session.getAttribute("permittedFeatures").toString())}">
                                    <li><a href="/debit-jv/approval">Debit JV Approval</a></li>
                                </g:if>
                                <g:if test="${UtilsService.isPermitted("VIEW_RECEIPT", session.getAttribute("permittedFeatures").toString())}">
                                    <li><a href="/receipt-approval">Receipt Approval</a></li>
                                </g:if>
                            </g:if>
                        </ul>
                    </li>

                </g:if>
                <li id="settings-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-settings"></i><span>Settings</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <li><a href="/entity-settings">Entity Settings</a></li>
                        <li><a href="/entity-irn">Entity IRN</a></li>
                        <g:if test="${session.getAttribute("financialYearValid")}">
                            <li><a href="/bulk-import">Bulk import</a></li>
                        </g:if>
                        <li><a href="/email-settings?id=${session.getAttribute("entityId")}">Email Settings</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</aside>

