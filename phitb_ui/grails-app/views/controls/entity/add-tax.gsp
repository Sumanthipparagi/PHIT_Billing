<div class="example-modal">
    <div class="modal fade" id="addtaxModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="taxTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="taxForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="taxName">
                                    Tax Name
                                </label>
                                <input type="text" id="taxName" class="form-control taxName" name="taxName"
                                       placeholder="Tax Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="taxDescription">
                                    Tax Description
                                </label>
                                <input type="text" id="taxDescription" class="form-control taxDescription" name="taxDescription"
                                       placeholder="Tax Description"
                                       required/>
                            </div>

                            <div class="col-lg-12 form-group  form-float">
                                <label for="taxValue">
                                    Tax Value
                                </label>
                                <input type="number" step="any" id="taxValue" class="form-control taxValue" name="taxValue"
                                       placeholder="Tax Value"
                                       required/>
                            </div>

                           %{-- <div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entity" name="entity" id="entity">
                                    <option value="">-- Please select --</option>

                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>--}%
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <hr>
                                <h6>Sales</h6>
                            </div>

                           %{-- <div class="col-lg-6 form-group  form-float">
                                <label for="salesTaxType">
                                    Sales Tax Type
                                </label>
                                <input type="text" id="salesTaxType" class="form-control salesTaxType"
                                       name="salesTaxType"
                                       placeholder="Sales Tax Type"
                                       required/>
                            </div>--}%


                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesSgst">
                                    Sales SGST
                                </label>
                                <input type="number" step="any" id="salesSgst" class="form-control salesSgst"
                                       name="salesSgst"
                                       placeholder="Sales SGST"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesCgst">
                                    Sales CGST
                                </label>
                                <input type="number" step="any" id="salesCgst" class="form-control salesCgst"
                                       name="salesCgst"
                                       placeholder="Sales CGST"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesIgst">
                                    Sales IGST
                                </label>
                                <input type="number" step="any" id="salesIgst" class="form-control salesIgst"
                                       name="salesIgst"
                                       placeholder="Sales IGST"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstOnMrpSales">
                                    GST on MRP Sales
                                </label>
                                <select name="gstOnMrpSales" id="gstOnMrpSales" class="form-control gstOnMrpSales" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                                %{--<input type="text" id="gstOnMrpSales" class="form-control gstOnMrpSales"
                                       name="gstOnMrpSales"
                                       placeholder="GST on MRP Sales"
                                       required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstOnSchemeValueSales">
                                    GST on Scheme Value Sales
                                </label>
                                <select name="gstOnSchemeValueSales" id="gstOnSchemeValueSales" class="form-control gstOnSchemeValueSales" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                                %{-- <input type="text" id="gstOnSchemeValueSales" class="form-control gstOnSchemeValueSales"
                                        name="gstOnSchemeValueSales"
                                        placeholder="GST on Scheme Value Sales"
                                        required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstDiscountSales">
                                    GST on Discount Sales
                                </label>
                                <select name="gstDiscountSales" id="gstDiscountSales" class="form-control gstDiscountSales" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                                %{--  <input type="text" id="gstDiscountSales" class="form-control gstDiscountSales"
                                         name="gstDiscountSales"
                                         placeholder="GST Discount Sales"
                                         required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="saleStatus">
                                    Sale Status
                                </label>
                                <select type="text" id="saleStatus" class="form-control saleStatus"
                                        name="saleStatus"
                                        required>
                                    <option value="1">ACTIVE</option>
                                    <option value="0">INACTIVE</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <hr>
                                <h6>Purchase</h6>
                            </div>
                            %{--<div class="col-lg-6 form-group  form-float">
                                <label for="purchaseTaxType">
                                    Purchase Tax Type
                                </label>
                                <input type="text" id="purchaseTaxType" class="form-control purchaseTaxType"
                                       name="purchaseTaxType"
                                       placeholder="Purchase Tax Type"
                                       required/>
                            </div>--}%

                            <div class="col-lg-6 form-group  form-float">
                                <label for="purchaseSgst">
                                    Purchase SGST
                                </label>
                                <input type="number" step="any" id="purchaseSgst" class="form-control purchaseSgst"
                                       name="purchaseSgst"
                                       placeholder="Purchase SGST"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="purchaseCgst">
                                    Purchase CGST
                                </label>
                                <input type="number" step="any" id="purchaseCgst" class="form-control purchaseCgst"
                                       name="purchaseCgst"
                                       placeholder="Purchase CGST"
                                       required/>
                            </div>



                            <div class="col-lg-6 form-group  form-float">
                                <label for="purchaseIgst">
                                    Purchase IGST
                                </label>
                                <input type="number" step="any" id="purchaseIgst" class="form-control purchaseIgst"
                                       name="purchaseIgst"
                                       placeholder="Sales IGST"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstOnMrpPur">
                                    GST on MRP Purchase
                                </label>
                                <select name="gstOnMrpPur" id="gstOnMrpPur" class="form-control gstOnMrpPur" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>

                               %{-- <input type="text" id="gstOnMrpPur" class="form-control gstOnMrpPur"
                                       name="gstOnMrpPur"
                                       placeholder="GST on MRP Pur"
                                       required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstOnSchemeValuePur">
                                    GST on Scheme Value Purchase
                                </label>

                                <select name="gstOnSchemeValuePur" id="gstOnSchemeValuePur" class="form-control gstOnSchemeValuePur" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>

                              %{--  <input type="text" id="gstOnSchemeValuePur" class="form-control gstOnSchemeValuePur"
                                       name="gstOnSchemeValuePur"
                                       placeholder="GST on Scheme Value Pur"
                                       required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstDiscountPur">
                                    GST on Discount Purchase
                                </label>

                                <select name="gstDiscountPur" id="gstDiscountPur" class="form-control gstDiscountPur" required>
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                                %{--<input type="text" id="gstDiscountPur" class="form-control gstDiscountPur"
                                       name="gstDiscountPur"
                                       placeholder="GST Discount Pur"
                                       required/>--}%
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="purStatus">
                                    Purchase Status
                                </label>
                                <select type="text" id="purStatus" class="form-control purStatus"
                                       name="purStatus"
                                        required>
                                    <option value="1">ACTIVE</option>
                                    <option value="0">INACTIVE</option>
                                </select>
                            </div>

%{--                            <div class="col-lg-6 form-group  form-float">--}%
%{--                                <label for="entityTypeId">--}%
%{--                                    Entity Type--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick entityType" name="entityType" id="entityTypeId">--}%
%{--                                    <g:each var="et" in="${entitytype}">--}%
%{--                                        <option value="${et.id}">${et.name}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%

                            <input type="hidden" name="entity" value="${session.getAttribute('entityId')}">
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" id="entityTypeId" class="entityType" name="entityType">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="regionStateIds" value="1">
                            <input type="hidden" name="status" value="1">
                            <input type="hidden" name="syncStatus" value="1">
                            <input type="hidden" id="salesTaxType" name="salesTaxType" value="1"/>
                            <input type="hidden" id="purchaseTaxType" name="purchaseTaxType" value="1"/>
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

