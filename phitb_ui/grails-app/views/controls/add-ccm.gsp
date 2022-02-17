<div class="example-modal">
    <div class="modal fade" id="addccmModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="ccmTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="ccmForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="kitName">
                                    Kit Name
                                </label>
                                <input type="text" id="kitName" class="form-control kitName" name="kitName"
                                       placeholder="Kit Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="kitNumber">
                                    Kit Number
                                </label>
                                <input type="text" id="kitNumber" class="form-control kitNumber" name="kitNumber"
                                       placeholder="Kit Number"
                                       required/>
                            </div>



                            <div class="col-lg-6 form-group  form-float">
                                <label for="purchaseDate">
                                    Purchase Date
                                </label>
                                <input type="text"  id="purchaseDate"
                                       class="form-control datetimepicker purchaseDate" name="purchaseDate"
                                       placeholder="Please choose date & time..." required>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="expiryDate">
                                   Expiry Date
                                </label>

                                <input type="text"  id="expiryDate"
                                       class="form-control datetimepicker expiryDate" name="expiryDate"
                                       placeholder="Please choose date & time..." required>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="fridge">
                                    Fridge
                                </label>
                                <select class="form-control show-tick fridge" name="fridgeId" id="fridge">
                                    <g:each var="fridge" in="${fridgeArrayList}">
                                        <option value="${fridge.id}">${fridge.fridgeName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    entityRegister
                                </label>
                                <select class="form-control show-tick entity" name="entityId" id="entity">
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityTypeId">
                                    Entity Type
                                </label>
                                <select class="form-control show-tick entityType" name="entityTypeId" id="entityTypeId">
                                    <g:each var="et" in="${entitytype}">
                                        <option value="${et.id}">${et.name}</option>
                                    </g:each>
                                </select>
                            </div>

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

