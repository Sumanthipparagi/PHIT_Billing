<%@ page import="phitb_ui.UtilsService" %>
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

                        <div class="col-lg-3 col-md-6 col-sm-12">
                            <h6>Sample Pages</h6>
                            <ul class="list-unstyled">
                                <li><a href="image-gallery.html">Image Gallery</a></li>
                                <li><a href="profile.html">Profile</a></li>
                                <li><a href="timeline.html">Timeline</a></li>
                                <li><a href="pricing.html">Pricing</a></li>
                                <li><a href="invoices.html">Invoices</a></li>
                                <li><a href="faqs.html">FAQs</a></li>
                                <li><a href="search-results.html">Search Results</a></li>
                            </ul>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-12">
                            <h6>About</h6>
                            <ul class="list-unstyled">
                                <li><a href="http://thememakker.com/about/" target="_blank">About</a></li>
                                <li><a href="http://thememakker.com/contact/" target="_blank">Contact Us</a></li>
                                <li><a href="http://thememakker.com/admin-templates/"
                                       target="_blank">Admin Templates</a></li>
                                <li><a href="http://thememakker.com/services/" target="_blank">Services</a></li>
                                <li><a href="http://thememakker.com/portfolio/" target="_blank">Portfolio</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12">
                <div class="social">
                    <a class="icon" href="https://www.facebook.com/thememakkerteam" target="_blank"><i
                            class="zmdi zmdi-facebook"></i></a>
                    <a class="icon" href="https://www.behance.net/thememakker" target="_blank"><i
                            class="zmdi zmdi-behance"></i></a>
                    <a class="icon" href="javascript:void(0);"><i class="zmdi zmdi-twitter"></i></a>
                    <a class="icon" href="javascript:void(0);"><i class="zmdi zmdi-linkedin"></i></a>

                    %{--                    <p>Coded by WrapTheme<br> Designed by <a href="http://thememakker.com/"--}%
                    %{--                                                             target="_blank">thememakker.com</a></p>--}%
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
            <a class="navbar-brand" href="#"><img src="${assetPath(src: '/themeassets/images/logo.svg')}"
                                                  alt="PharmIT"></a>
        </li>
        %{--        <li><a href="javascript:void(0);" class="btn_overlay hidden-sm-down"><i class="zmdi zmdi-search"></i></a></li>--}%
        <li><a href="javascript:void(0);" class="menu-sm"><i class="zmdi zmdi-swap"></i></a></li>

        <li><a href="javascript:void(0);" class="fullscreen" data-provide="fullscreen"><i
                class="zmdi zmdi-fullscreen"></i></a></li>
        <li class="power">
            <a href="javascript:void(0);" class="js-right-sidebar"><i class="zmdi zmdi-settings zmdi-hc-spin"></i></a>
            <a href="/logout" class="mega-menu"><i class="zmdi zmdi-power"></i></a>
        </li>
    </ul>
</aside>

<aside class="right_menu">
    <div id="rightsidebar" class="right-sidebar">
        <ul class="nav nav-tabs">
            <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#setting">Setting</a></li>
            %{-- <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#activity">Activity</a></li>--}%
        </ul>

        <div class="tab-content slim_scroll">
            <div class="tab-pane slideRight active" id="setting">
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

                %{--<div class="card">
                    <div class="header">
                        <h2><strong>General</strong> Settings</h2>
                    </div>

                    <div class="body">
                        <ul class="setting-list list-unstyled m-b-0">
                            <li>
                                <div class="checkbox">
                                    <input id="checkbox1" type="checkbox">
                                    <label for="checkbox1">Report Panel Usage</label>
                                </div>
                            </li>
                            <li>
                                <div class="checkbox">
                                    <input id="checkbox2" type="checkbox" checked="">
                                    <label for="checkbox2">Email Redirect</label>
                                </div>
                            </li>
                            <li>
                                <div class="checkbox">
                                    <input id="checkbox3" type="checkbox">
                                    <label for="checkbox3">Notifications</label>
                                </div>
                            </li>
                            <li>
                                <div class="checkbox">
                                    <input id="checkbox4" type="checkbox">
                                    <label for="checkbox4">Auto Updates</label>
                                </div>
                            </li>
                            <li>
                                <div class="checkbox">
                                    <input id="checkbox5" type="checkbox" checked="">
                                    <label for="checkbox5">Offline</label>
                                </div>
                            </li>
                            <li>
                                <div class="checkbox m-b-0">
                                    <input id="checkbox6" type="checkbox">
                                    <label for="checkbox6">Location Permission</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
--}%
                <div class="card">
                    <div class="header">
                        <h2><strong>Left</strong> Menu</h2>
                    </div>

                    <div class="body theme-light-dark">
                        <button class="t-dark btn btn-primary btn-round btn-block">Dark</button>
                    </div>
                </div>
            </div>

            %{--<div class="tab-pane slideLeft" id="activity">
                <div class="card activities">
                    <div class="header">
                        <h2><strong>Recent</strong> Activity Feed</h2>
                    </div>

                    <div class="body">
                        <div class="streamline b-accent">
                            <div class="sl-item">
                                <div class="sl-content">
                                    <div class="text-muted">Just now</div>

                                    <p>Finished task <a href="" class="text-info">#features 4</a>.</p>
                                </div>
                            </div>

                            <div class="sl-item b-info">
                                <div class="sl-content">
                                    <div class="text-muted">10:30</div>

                                    <p><a href="">@Jessi</a> retwit your post</p>
                                </div>
                            </div>

                            <div class="sl-item b-primary">
                                <div class="sl-content">
                                    <div class="text-muted">12:30</div>

                                    <p>Call to customer <a href="" class="text-info">Jacob</a> and discuss the detail.
                                    </p>
                                </div>
                            </div>

                            <div class="sl-item b-warning">
                                <div class="sl-content">
                                    <div class="text-muted">1 days ago</div>

                                    <p><a href="" class="text-info">Jessi</a> commented your post.</p>
                                </div>
                            </div>

                            <div class="sl-item b-primary">
                                <div class="sl-content">
                                    <div class="text-muted">2 days ago</div>

                                    <p>Call to customer <a href="" class="text-info">Jacob</a> and discuss the detail.
                                    </p>
                                </div>
                            </div>

                            <div class="sl-item b-primary">
                                <div class="sl-content">
                                    <div class="text-muted">3 days ago</div>

                                    <p>Call to customer <a href="" class="text-info">Jacob</a> and discuss the detail.
                                    </p>
                                </div>
                            </div>

                            <div class="sl-item b-warning">
                                <div class="sl-content">
                                    <div class="text-muted">4 Week ago</div>

                                    <p><a href="" class="text-info">Jessi</a> commented your post.</p>
                                </div>
                            </div>

                            <div class="sl-item b-warning">
                                <div class="sl-content">
                                    <div class="text-muted">5 days ago</div>

                                    <p><a href="" class="text-info">Jessi</a> commented your post.</p>
                                </div>
                            </div>

                            <div class="sl-item b-primary">
                                <div class="sl-content">
                                    <div class="text-muted">5 Week ago</div>

                                    <p>Call to customer <a href="" class="text-info">Jacob</a> and discuss the detail.
                                    </p>
                                </div>
                            </div>

                            <div class="sl-item b-primary">
                                <div class="sl-content">
                                    <div class="text-muted">3 Week ago</div>

                                    <p>Call to customer <a href="" class="text-info">Jacob</a> and discuss the detail.
                                    </p>
                                </div>
                            </div>

                            <div class="sl-item b-warning">
                                <div class="sl-content">
                                    <div class="text-muted">1 Month ago</div>

                                    <p><a href="" class="text-info">Jessi</a> commented your post.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>--}%
        </div>
    </div>

    <div id="leftsidebar" class="sidebar">
        <div class="menu">
            <ul class="list">
                <li>
                    <div class="user-info m-b-20">
                        <div class="image">
                            <a href="/user/update-details/${session.getAttribute("userId")}"><img
                                    src="${assetPath(src: '/themeassets/images/profile_av.jpg')}" alt="User">
                            </a>
                        </div>

                        <div class="detail">
                            <h6>${session.getAttribute("userName")}</h6>

                            <p class="m-b-0">${session.getAttribute("entityName")}</p>
                            <small>Financial Year: ${session.getAttribute("financialYear")}</small>
                        </div>
                    </div>
                </li>
                <li class="header">MAIN</li>
                <li id="dashboard-menu" class="sidemenuitem active open"><a href="dashboard"><i
                        class="zmdi zmdi-home"></i><span>Dashboard</span></a>
                </li>

                <li id="purchase-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-shopping-basket"></i><span>Purchase</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Purchase Order</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/purchase-entry">Purchase Entry</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_RETURN", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Purchase Return</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_MARK_RECIEVED_GOODS", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Mark Received Goods</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PURCHASE_VOUCHER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Purchase Voucher</a></li>
                        </g:if>
                    </ul>
                </li>

                <li id="sales-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-money-box"></i><span>Sales</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_SALE_ORDER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/sale-order-entry">Sale Order</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_SALE_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/sale-entry">Sale Entry</a></li>
                            <li><a href="/sale-bill-list">My Invoices</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_SALE_RETURN", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="javascript:void(0);" class="menu-toggle">Sales Return</span> <span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/sale-return">Create Sales Return</a></li>
                                    <li><a href="/sale-return/my-returns">Sales Return List</a></li>

                                </ul>
                            </li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_SCHEME_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/scheme-entry">Scheme Entry (Offers)</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_STOCK_ADJUSTEMENT", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Stock Adjustment</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_DEBIT_SETTLEMENT", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Credit Debit Settlement</a></li>
                        </g:if>
                    </ul>
                </li>

                <li id="accounts-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-book"></i><span>Accounts</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_JV", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/credit-jv">Credit JV</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_DEBIT_JV", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/debit-jv">Debit JV</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_RECEIPT", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="javascript:void(0);" class="menu-toggle">Receipt</span> <span
                                    class="badge badge-success float-right"></span></a>
                                <ul class="ml-menu">
                                    <li><a href="/recipt">Create Receipt</a></li>
                                    <li><a href="/recipt-list">Receipt List</a></li>

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
                                    <li><a href="/payments">Create Payments</a></li>
                                    <li><a href="/payments-list">Payments List</a></li>
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
                                <li><a href="/reports/sales/areawiseconsolidatedproducts">Area-wise (Products-Consolidated)</a></li>
                                <li><a href="/reports/sales/consolidated">Consolidated</a></li>
                                <li><a href="/reports/sales/productwise">Product-Wise</a></li>
                                <li><a href="/reports/sales/gstreport">GST Report</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="header"></li>
                <li id="system-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-book-image"></i><span>System</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_STATE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/state">State</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_CITY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/city">City</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_COUNTRY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/country">Country</a></li>
                        </g:if>
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
                        <g:if test="${UtilsService.isPermitted("VIEW_ENTITY_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/entity-register">Entity Register</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_USER_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/user-register">User Register</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_CUSTOMER_GROUP_REGISTER", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/customer-group-register">Customer Group Register</a></li>
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
                            <li><a href="/route-regitser">Route Register</a></li>
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
                    </ul>
                </li>

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
                            <li><a href="/division">Division</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_CATEGORY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-category">Product Category</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_SCHEDULE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-schedule">Product Schedule</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_COMPOSITION", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-composition">Product Composition</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_TYPE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-type">Product Type</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_DIVISION_GROUP", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/division-group">Division Group</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_CLASS", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-class">Product Class</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_PRODUCT_COST_RANGE", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/product-cost-range">Product Cost Range</a></li>
                        </g:if>
                    </ul>
                </li>
                <li id="inventory-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-store-24"></i><span>Inventory</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_STOCKBOOK_ENTRY", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="stockbook">Stock Entry</a></li>
                        </g:if>
                    </ul>
                </li>

                <li id="approvals-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-check-circle"></i><span>Approvals</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                        <g:if test="${UtilsService.isPermitted("VIEW_CREDIT_JV", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/credit-jv/approval">Credit JV Approval</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_DEBIT_JV", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="/debit-jv/approval">Debit JV Approval</a></li>
                        </g:if>
                        <g:if test="${UtilsService.isPermitted("VIEW_RECEIPT", session.getAttribute("permittedFeatures").toString())}">
                            <li><a href="#">Receipt Approval</a></li>
                        </g:if>
                    </ul>
                </li>


                <li id="settings-menu" class="sidemenuitem"><a href="javascript:void(0);" class="menu-toggle"><i
                        class="zmdi zmdi-chart"></i><span>Settings</span> <span
                        class="badge badge-success float-right"></span></a>
                    <ul class="ml-menu">
                                <li><a href="/entity-settings">Entity Settings</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</aside>