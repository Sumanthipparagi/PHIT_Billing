<div class="example-modal">
    <div class="modal fade" id="addbatchModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="batchTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="batchForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="product">
                                    Product
                                </label>
                                <select class="form-control show-tick product" style="width: 100%" name="product"
                                        id="product">
                                    <g:each var="p" in="${productlist}">
                                        <option value="${p.id}">${p.productName}</option>
                                    </g:each>
                                </select>
                                <input type="hidden" class="productId" name="productId"/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="batchNumber">
                                    Batch Number
                                </label>
                                <input type="text" id="batchNumber" class="form-control batchNumber" name="batchNumber"
                                       placeholder="Batch Number"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="manfDate">
                                    Manfacture Date
                                </label>
                                <input type="text" id="manfDate" class="form-control manfDate" name="manfDate"
                                       placeholder="Manfacture Date"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="expiryDate">
                                   Expiry Date
                                </label>
                                <input type="text" id="expiryDate" class="form-control expiryDate" name="expiryDate"
                                       placeholder="Expiry Date"
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
                                    Sale Rate
                                </label>
                                <input type="text" id="saleRate" class="form-control saleRate" name="saleRate"
                                       placeholder="Sale Rate" pattern="^\d*(\.\d{0,2})?$"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="ptr">
                                    PTR
                                </label>
                                <input type="text" id="ptr" class="form-control ptr" name="ptr"
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
                                <label for="qty">
                                    Quantity
                                </label>
                                <input type="number" id="qty" class="form-control qty" name="qty"
                                       placeholder="Quantity"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="box">
                                    Box
                                </label>
                                <input type="number" id="box" class="form-control box" name="box"
                                       placeholder="Box"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="caseWt">
                                    Case Wt
                                </label>
                                <input type="text" id="caseWt" class="form-control caseWt" name="caseWt"
                                       placeholder="Case Wt"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="productCat">
                                    Product Category
                                </label>
                                <select class="form-control show-tick productCat" name="productCat" id="productCat">
                                    <g:each var="pc" in="${productcatList}">
                                        <option value="${pc.id}">${pc.categoryName}</option>
                                    </g:each>
                                </select>
                            </div>


%{--                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityId">
                                    Entity
                                </label>
                                <select class="form-control show-tick entityId" name="entityId" id="entityId" required>
                                    <option value="">-- Please select --</option>

                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>--}%


%{--                            <div class="col-lg-6 form-group  form-float">--}%
%{--                                <label for="entityTypeId">--}%
%{--                                    Entity Type--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick entityType" name="entityTypeId" id="entityTypeId">--}%
%{--                                    <g:each var="et" in="${entitytype}">--}%
%{--                                        <option value="${et.id}">${et.name}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%

                            <input type="hidden" value="${session.getAttribute("entityTypeId")}" name="entityTypeId">
                            <input type="hidden" value="${session.getAttribute("entityId")}" name="entityId">
                            <input type="hidden" name="createdUser" value="${session.getAttribute("userId")}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute("userId")}">
                            <input type="hidden" name="status" value="1">
                            <input type="hidden" name="syncStatus" value="1">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font
                                style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
                        <button type="button" class="btn btn-danger btn-simple btn-round waves-effect" data-dismiss="modal"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">CLOSE</font></font></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

