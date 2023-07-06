<div class="example-modal">
    <div class="modal fade" id="addFridgeModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="fridgeTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="fridgeForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="fridgeName">
                                    Fridge Name
                                </label>
                                <input type="text" id="fridgeName" class="form-control fridgeName" name="fridgeName"
                                       placeholder="Fridge Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="floor">
                                   Floor
                                </label>
                                <input type="text" id="floor" class="form-control floor" name="floor" placeholder="Floor"
                                       />
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="machinePartNumber">
                                    Machine Part Number
                                </label>
                                <input type="text" id="machinePartNumber" class="form-control machinePartNumber"
                                       name="machinePartNumber" placeholder="Machine Part Number"
                                       />
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="dateOfPurchase">
                                    Date of purchase
                                </label>

                                    <input type="text"  id="dateOfPurchase"
                                           class="form-control datetimepicker dateOfPurchase" name="dateOfPurchase"
                                           placeholder="Please choose date & time..." required>
                            </div>


                           %{-- <div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entity" name="entityId" id="entity" >
                                    <option value="">Please Select</option>

                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>--}%


                            <input type="hidden" class="entityTypeId" name="entityTypeId">
                            <input type="hidden" name="entityId" value="${session.getAttribute('entityId')}">
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
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

