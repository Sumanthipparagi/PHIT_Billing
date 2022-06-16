<div class="example-modal">
    <div class="modal fade" id="addDistrictModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="districtTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="districtForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="districtName">
                                    District
                                </label>
                                <input type="text" id="districtName" class="form-control districtName" name="district" placeholder="District" required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="districtCode">
                                    District Code
                                </label>
                                <input type="text" id="districtCode" class="form-control districtCode"
                                       name="districtCode"
                                       placeholder="District Code" required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="state">
                                    State
                                </label>
                                <select class="form-control show-tick state" name="state" id="state" required>
                                    <option value="">Please Select</option>
                                    <g:each var="c" in="${state}">
                                        <option value="${c?.id}">${c?.name}</option>
                                    </g:each>
                                </select>
                            </div>
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

