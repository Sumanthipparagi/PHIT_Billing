<!-- The Modal -->
<div class="modal" id="addDivisionGrn">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h5 class="modal-title">Add Division</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <form   method="post" role="form" id="addDivisionGrnForm"
                  enctype="multipart/form-data">
                <div class="modal-body">
%{--                    <div class="col-lg-6 form-group  form-float">--}%
%{--                        <label for="division">--}%
%{--                        </label>--}%
%{--                        <select class="form-control show-tick division" name="division" id="division">--}%
%{--                            <option value="">-- Please Select --</option>--}%
%{--                            <g:each var="d" in="${divisions}">--}%
%{--                                <option value="${d.id}">${d.divisionName}</option>--}%
%{--                            </g:each>--}%
%{--                        </select>--}%
%{--                    </div>--}%

                    <div class="col-lg-offset-12 col-md-offset-12 col-lg-12 col-md-12">
                    <table class="table table-bordered" id="grnProducts">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>HSN Code</th>
                            <th>Batch</th>
                            <th>sQty</th>
                            <th>fQty</th>
                            <th>sRate</th>
                            <th>Division</th>
                            <th style="display: none;">gtnId</th>
                            <th style="display: none;">productId</th>
                        </tr>
                        </thead>
                        <tbody id="grnProductList">
                        </tbody>
                    </table>
                    </div>
                </div>


            <!-- Modal footer -->
            <div class="modal-footer">
                <input name="id" id="id" class="id" type="hidden"/>
                <input name="type" class="type" value="add" type="hidden"/>
                <button type="submit" class="btn btn-default btn-round waves-effect" name="submitApproveGrn"
                        data-gtnid=""><font
                        style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font></font>
                </button>
                <button type="button" class="btn btn-danger btn-simple btn-round waves-effect"
                        data-dismiss="modal"><font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">CLOSE</font></font></button>
            </div>
        </form>
        </div>
    </div>
</div>
</div>