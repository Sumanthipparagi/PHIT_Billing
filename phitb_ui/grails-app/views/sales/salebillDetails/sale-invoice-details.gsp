<ul class="nav nav-tabs padding-0">
    <li class="nav-item inlineblock"><a class="nav-link active" data-toggle="tab" href="#details"
                                        aria-expanded="true">Details</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#payments"
                                        aria-expanded="false">Record Payments</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#creditAdjustment"
                                        aria-expanded="false">Credit Adjustment</a></li>
    <li class="nav-item inlineblock"><a class="nav-link" data-toggle="tab" href="#paymentsHistory"
                                        aria-expanded="false">Payments History</a></li>
</ul>

<div class="tab-content">
    <div role="tabpanel" class="tab-pane in active" id="details" aria-expanded="true">
        <div style="padding: 20px 0 0 10px;">
            <div class="row detailsSpinner">
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
                                                                                               class="totalDue"
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
            <div class="row detailsSpinner">
                <div class="col-md-12">
                    <div class="text-center">
                        <div class="spinner-border" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="amount">Amount: <span style="color: red" class="required-indicator">*</span></label>
                        <input class="form-control" pattern="^\d*(\.\d{0,2})?$" type="number" step="0.01" min="0"
                               value="0.00" id="amount" name="amount" required/>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="paymentMode">Payment Mode: <span style="color: red"
                                                                     class="required-indicator">*</span></label>
                        <select onchange="paymentModeChange()" class="form-control" id="paymentMode" name="paymentMode"
                                required>
                            <g:each in="${paymentModes}" var="pm">
                                <option value="${pm.id}">${pm.name}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6" id="paymentMethodContainer">
                    <div class="form-group">
                        <label for="paymentMethod">Payment Method: <span style="color: red"
                                                                         class="required-indicator">*</span></label>
                        <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                            <g:each in="${accountMode}" var="am">
                                <option value="${am.id}">${am.mode}</option>
                            </g:each>
                        </select>
                    </div>
                </div>

                <div class="col-md-6" id="depositToContainer">
                    <div class="form-group">
                        <label for="depositTo">Deposit To: <span style="color: red" class="required-indicator">*</span>
                        </label>
                        <select class="form-control" id="depositTo" name="depositTo" required>
                            <g:each in="${accountRegister}" var="ar">
                                <option value="${ar.id}">${ar.accountName}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row" id="payeeBankerContainer">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="payeeBanker">Payee Banker: <span style="color: red"
                                                                     class="required-indicator">*</span></label>
                        <select class="form-control" id="payeeBanker" name="payeeBanker" required>
                            <g:each in="${bank}" var="bk">
                                <option value="${bk.id}">${bk.bankName}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row hidden" id="cardNumberContainer">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="cardNumber">Card Number:</label>
                        <input class="form-control" type="text" id="cardNumber" name="cardNumber"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6" id="paymentDateContainer">
                    <div class="form-group">
                        <label for="paymentDate">Payment Date: <span style="color: red"
                                                                     class="required-indicator">*</span></label>
                        <input class="form-control date" type="date" id="paymentDate" name="paymentDate" required/>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group" id="instrumentIdContainer">
                        <label for="instrumentId">Instrument ID:</label>
                        <input class="form-control" type="text" id="instrumentId" name="instrumentId"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="remarks">Remarks: <small style="font-size: 10px;"><span
                                id="remarksCharacters">0</span>/100</small></label>
                        <textarea rows="2" class="form-control" id="remarks" name="remarks" maxlength="100"></textarea>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 clearfix">
                    <div class="pull-right">
                        <table class="table">
                            <thead></thead>
                            <tbody>
                            <tr><td>Total Due</td><td class="totalDue totalDueOfSelected"
                                                      style="text-align: left; color: red"></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 clearfix">
                    <div class="pull-right">
                        <input type="hidden" class="saleBillId"/>
                        <button class="btn btn-success btn-sm" onclick="recordPayment()">Record Payment</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="creditAdjustment" aria-expanded="false">
        <div class="row detailsSpinner">
            <div class="col-md-12">
                <div class="text-center">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        </div>

        <div style="padding: 20px 0 0 10px;">
            <div class="row">
                <div class="col-md-12" id="paymentsAlert">
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <p><i>Available Credits</i></p>
                    <table class="table table-striped">
                        <thead>
                        <tr><th>Sl No.</th><th>Doc No.</th><th>Balance</th><th>-</th></tr>
                        </thead>
                        <tbody id="creditsTable">

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 clearfix">
                    <div class="pull-right">
                        <table class="table">
                            <thead></thead>
                            <tbody>
                            <tr><td>Total Due</td><td class="totalDue totalDueOfSelected"
                                                      style="text-align: left; color: red"></tr>
                            <tr><td>Credits Applied</td><td id="creditsApplied" style="text-align: left;">0.00</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 clearfix">
                    <div class="pull-right">
                        <input type="hidden" class="saleBillId"/>
                        <input type="hidden" id="saleReturnIds"/>
                        <button class="btn btn-success btn-sm" onclick="adjustCredits()">Adjust Credits</button>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="paymentsHistory" aria-expanded="false">
        <div style="padding: 20px 0 0 10px;">
            <div class="row detailsSpinner">
                <div class="col-md-12">
                    <div class="text-center">
                        <div class="spinner-border" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <p><u><i>Receipts:</i></u></p>
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
                        <tr style="text-align: center">
                            <td colspan="5">No Receipts for this Invoice</td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <p><u><i>Adjusted Credits:</i></u></p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped table-bordered" style="width: 100%;table-layout: fixed;">
                        <thead>
                        <tr>
                            <th style="width: 5%;">#</th>
                            <th style="width: 25%;">Cr. Settlement No.</th>
                            <th>Date</th>
                            <th>Amt.</th>
                            <th>-</th>
                        </tr>
                        </thead>
                        <tbody id="creditsAdjustmentTable"
                               style="white-space: normal !important; word-wrap: break-word;">
                        <tr style="text-align: center">
                            <td colspan="5">No Credit Adjustments for this Invoice</td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>

        </div>
    </div>

    <div role="tabpanel" class="tab-pane" id="activity" aria-expanded="false">
        <div class="row detailsSpinner">
            <div class="col-md-12">
                <div class="text-center">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        </div>

        <div style="padding: 20px 0 0 10px;">
        </div>
    </div>
</div>
