<div class="example-modal">
    <div class="modal fade" id="adddivisionGroupModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="divisionGroupTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="divisionGroupForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="divisionGroupName">
                                    Division Group Name
                                </label>
                                <input type="text" id="divisionGroupName" class="form-control divisionGroupName" name="divisionGroupName"
                                       placeholder="Division Group Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="divGroupShortName">
                                    Division Group Short Name
                                </label>
                                <input type="text" id="divGroupShortName" class="form-control divGroupShortName" name="divGroupShortName"
                                       placeholder="Short Name"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="divisionIds">
                                    Divisions
                                </label>
                                <select class="form-control show-tick divisionIds" name="divisionIds" id="divisionIds"
                                        multiple="multiple" required>
                                    <g:each var="d" in="${divisionList}">
                                        <option value="${d.id}">${d.divisionName}</option>
                                    </g:each>
                                </select>
                            </div>



                            <input type="hidden" name="entityTypeId" value="1">
                            <input type="hidden" name="entityId" value="1">
                            <input type="hidden" name="createdUser" value="1">
                            <input type="hidden" name="modifiedUser" value="1">
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

