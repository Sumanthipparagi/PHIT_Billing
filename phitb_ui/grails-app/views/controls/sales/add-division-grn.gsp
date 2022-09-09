<div class="example-modal">
    <div class="modal fade" id="addDivisionGrn">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h6 class="addDivisionGrnTitle">Please Select Division for below Products</h6>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="col-lg-6 form-group  form-float">
                            <label for="product">

                            </label>
                            <select style="width: 100%" onchange="getBatch()" class="form-control show-tick product" name="productId" id="product">
                                <option selected >-- Please Select --</option>
                                <g:each var="d" in="${divisions}">
                                    <option value="${d.id}">${d.divisionName}</option>
                                </g:each>
                            </select>
                        </div>

                        <table class="table table-responsive" style="width: 100%;">
                            <thead>
                            <tr>
                                <th>-</th>
                                <th>Product</th>
                                <th>Batch</th>
                                <th>Expiry</th>
                                <th>Rem Qty</th>
                                <th>Free Qty</th>
                                <th>P Rate</th>
                                <th>S Rate</th>
                                <th>GST</th>
                            </tr>
                            </thead>
                            <tbody id="grnProductList">
                            </tbody>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font
                                style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font></button>
                        <button type="button" class="btn btn-danger btn-simple btn-round waves-effect" data-dismiss="modal"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">CLOSE</font></font></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
