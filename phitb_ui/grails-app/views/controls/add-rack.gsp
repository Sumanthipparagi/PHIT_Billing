<div class="example-modal">
    <div class="modal fade" id="addRackModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="rackTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="rackForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="rackName">
                                    Rack Name
                                </label>
                                <input type="text" id="rackName" class="form-control rackName" name="rackName"
                                       placeholder="Rack Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="ccmEnabled">
                                    CCM Enabled
                                </label>
                                <input type="number" id="ccmEnabled" class="form-control ccmEnabled" name="cccEnabled"
                                       placeholder="CCM Enabled"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="rackCodeName">
                                    Rack Code Name
                                </label>
                                <input type="text" id="rackCodeName" class="form-control rackCodeName" name="rackCodeName"
                                       placeholder="Rack Code Name"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="genralinfo">
                                    Genaral Info
                                </label>
                                <input type="text" id="genralinfo" class="form-control generalInfo"
                                       name="generalInfo" placeholder="Genaral Info"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="floorNumber">
                                    Floor Number
                                </label>
                                <input type="text" id="floorNumber" class="form-control floorNumber"
                                       name="floorNumber" placeholder="Floor Number"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="companies">
                                   Companies
                                </label>
                                <input type="text" id="companies" class="form-control companies"
                                       name="companies" placeholder="Companies"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    Entity
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

