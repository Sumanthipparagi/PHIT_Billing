<ul class="nav nav-tabs padding-0">
    <li class="nav-item inlineblock"><a class="nav-link active" data-toggle="tab" href="#details"
                                        aria-expanded="true">Details</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#payments"
                                        aria-expanded="false">Record Payments</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#salesreturn"
                                        aria-expanded="false">Sales Return</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#activity"
                                        aria-expanded="false">Activity</a></li>
</ul>

<div class="tab-content">
    <div role="tabpanel" class="tab-pane in active" id="details" aria-expanded="true">
        <div style="padding: 20px 0 0 10px;">
            <div class="row" id="detailsSpinner">
                <div class="col-md-12">
                    <div class="text-center">
                        <div class="spinner-border" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                    </div>
                </div>
            </div>

            <div id="detailsContentError" class="hidden">
                <p>Unable to get the invoice, try again later.</p>
            </div>

            <div id="detailsContent" class="hidden">
                <div class="row">
                    <div class="col-md-6">
                        <h6 id="invoiceNumber" class="text-primary"></h6>

                        <p id="entityDetails"></p>
                    </div>

                    <div class="col-md-6">
                        <h6>Bill To:</h6>

                        <p id="customerDetails"></p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6" id="badgeContainer">
                    </div>

                    <div class="col-md-6">
                        <div class="typography-line">
                            <h6 style="font-size: 13px;">Invoice Date: <span id="invoiceDate"
                                                                             style="font-size: 13px; font-weight: normal;"></span>
                            </h6>
                            <h6 style="font-size: 13px;">Due Date: <span id="dueDate"
                                                                         style="font-size: 13px; font-weight: normal;"></span>
                            </h6>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                            <thead>
                            <tr>
                                <th style="width: 5%;">#</th>
                                <th style="width: 25%;">Product</th>
                                <th>S.Qty</th>
                                <th>F.Qty</th>
                                <th>Amt.</th>
                                <th>GST</th>
                                <th>Net Amt.</th>
                            </tr>
                            </thead>
                            <tbody id="saleProductsTableBody"
                                   style="white-space: normal !important; word-wrap: break-word;">

                            </tbody>

                        </table>
                    </div>

                </div>

                <div class="row clearfix">
                    <div class="col-md-6 col-sm-12">
                    </div>

                    <div class="col-md-6 col-sm-12 clearfix">
                        <div class="pull-left"></div>

                        <div class="pull-right">
                            <table class="table table-responsive">
                                <thead>
                                <tr><th></th><th></th></tr>
                                </thead>
                                <tbody>
                                <tr><td><strong>Sub Total</strong></td><td id="subtotal"
                                                                           style="text-align: left;"></td></tr>
                                <tr><td><strong>Total GST</strong></td><td id="totaltax"
                                                                           style="text-align: left;"></td></tr>
                                <tr><td><strong>Total</strong></td><td id="totalAmt"
                                                                       style="text-align: left; font-weight: bold"></td>
                                </tr>
                                <tr><td><strong>Total Paid</strong></td><td id="totalPaid"
                                                                            style="text-align: left;"></td>
                                </tr>
                                <tr><td style="color: red;"><strong>Total Due</strong></td><td id="totalDue"
                                                                                               style="text-align: left; color: red"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="payments" aria-expanded="false">
        <div style="padding: 20px 0 0 10px;">
            <div class="row">
                <div class="col-md-12">
                    <h6><u>Payment History</u></h6>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                        <thead>
                        <tr>
                            <th style="width: 5%;">#</th>
                            <th style="width: 25%;">Receipt No.</th>
                            <th>Date</th>
                            <th>Amt. Paid</th>
                            <th>-</th>
                        </tr>
                        </thead>
                        <tbody id="previousPaymentsTable"
                               style="white-space: normal !important; word-wrap: break-word;">

                        </tbody>

                    </table>
                </div>

            </div>
        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="salesreturn" aria-expanded="false">
        <div style="padding: 20px 0 0 10px;;">
        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="activity" aria-expanded="false">
        <div style="padding: 20px 0 0 10px;">
        </div>
    </div>
</div>
