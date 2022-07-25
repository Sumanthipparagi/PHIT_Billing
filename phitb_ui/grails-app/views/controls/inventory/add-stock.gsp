<div class="example-modal">
    <div class="modal fade" id="addstockModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="stockTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="stockForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-12 form-group  form-float">
                                <label for="openingStockQty">
                                    Opening Stock Quantity
                                </label>
                                <input type="number" id="openingStockQty" class="form-control openingStockQty"
                                       name="openingStockQty"
                                       placeholder="Opening Stock"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="product">
                                    Product
                                </label>
                                <select style="width: 100%" onchange="getBatch()" class="form-control show-tick product" name="productId" id="product">
                                    <option selected disabled>SELECT</option>
                                    <g:each var="p" in="${productList}">
                                        <option value="${p.id}">${p.productName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="batchNumber">
                                    Batch
                                </label>
                                <select onchange="batchChanged()"  style="width: 100%" class="form-control show-tick batchNumber" name="batchNumber" id="batchNumber">
                                </select>

                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="manufacturingDate">
                                    Manufacture Date
                                </label>
                                <input type="text" id="manufacturingDate" class="form-control manufacturingDate" name="manufacturingDate"
                                       placeholder="Manufacture Date"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="expDate">
                                   Expiry Date
                                </label>
                                <input type="text" id="expDate" class="form-control expDate" name="expDate"
                                       placeholder="Expiry Date"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="purcDate">
                                    Purchase Date
                                </label>
                                <input type="text" id="purcDate" class="form-control purcDate" name="purcDate"
                                       placeholder="Purchase Date"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="purchaseRate">
                                    Purchase Rate
                                </label>
                                <input type="text" id="purchaseRate" class="form-control purchaseRate"
                                       name="purchaseRate"
                                       placeholder="Purchase Rate"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="saleRate">
                                    Sale Rate (PTS)
                                </label>
                                <input type="text" id="saleRate" class="form-control saleRate" name="saleRate"
                                       placeholder="Sale Rate"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="purcTradeDiscount">
                                    Purchase Trade Discount
                                </label>
                                <input type="text" id="purcTradeDiscount" class="form-control purcTradeDiscount" name="purcTradeDiscount"
                                       placeholder="Sale Rate"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="mrp">
                                    MRP
                                </label>
                                <input type="text" id="mrp" class="form-control mrp" name="mrp"
                                       placeholder="MRP"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="remainingQty">
                                    Remaining Quantity
                                </label>
                                <input type="number" id="remainingQty" class="form-control remainingQty" name="remainingQty"
                                       placeholder="Remaining Quantity"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="remainingFreeQty">
                                    Remaining Free Quantity
                                </label>
                                <input type="number" id="remainingFreeQty" class="form-control remainingFreeQty" name="remainingFreeQty"
                                       placeholder="Remaining Free Quantity"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="packingDesc">
                                    Packing Description
                                </label>
                                <input type="text" id="packingDesc" class="form-control packingDesc" name="packingDesc"
                                       placeholder="Packing Description"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="purcProductValue">
                                    Purchase Product Value
                                </label>
                                <input type="text" id="purcProductValue" class="form-control purcProductValue" name="purcProductValue"
                                       placeholder="Purchase Product Value"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="taxId">
                                    Tax Slab
                                </label>
                                <select style="width:100%;" class="form-control show-tick taxId" name="taxId" id="taxId" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${taxList}">
                                        <option value="${e.id}">${e.taxName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="supplierId">
                                    Supplier
                                </label>
                                <select style="width:100%;" class="form-control show-tick supplierId" name="supplierId" id="supplierId" required>
                                    <option value="">-- Please select --</option>

                                    <g:each var="e" in="${entityList}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>

%{--                            <input type="hidden" name="entityId" >
                            <input type="hidden" name="entityTypeId">--}%
                            <input type="hidden" class="uuid" name="uuid">

                            <input type="hidden" name="mergedWith">
                            <input type="hidden" name="createdUser">
                            <input type="hidden" name="modifiedUser">
                            <input type="hidden" name="purcSeriesId" value="1">
                            <input type="hidden" name="remainingReplQty" value="0">
                            <input type="hidden" name="status" value="1">
                            <input type="hidden" name="syncStatus" value="1">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submit"><font
                                style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
                        <button type="button" class="btn btn-danger btn-simple btn-round waves-effect" data-dismiss="modal"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">CLOSE</font></font></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

