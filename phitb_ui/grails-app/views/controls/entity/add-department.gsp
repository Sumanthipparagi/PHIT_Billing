<div class="example-modal">
    <div class="modal fade" id="addbankRegisterModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="departmentTitle"></h4>
                </div>

                <form action="" id="form_validation" method="post" role="form" class="bankForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-12 form-group  form-float">
                                <label for="name">
                                    Department Name
                                </label>
                                <input type="text" id="name" class="form-control name" name="name"
                                       placeholder="Department Name"
                                       required/>
                            </div>


                            <div class="col-lg-12 form-group  form-float">
                                <label for="description">
                                    Description
                                </label>
                                <input type="text" id="description" class="form-control description" name="description"
                                       placeholder="Description"
                                       required/>
                            </div>


                            <div class="col-lg-12 form-group  form-float">
                                <label for="entityId">
                                    Entity
                                </label>
                                <select style="width: 100%" class="form-control show-tick entityId select select2-hidden-accessible" name="entityId" id="entityId" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>


                            <input type="hidden" class="entityTypeId"  name="entityTypeId">
                            %{--                            <input type="hidden" name="entityId" value="1">--}%
                            <input type="hidden" name="createdUser" value="${session.get}">
                            <input type="hidden" name="modifiedUser" value="1">
                            <input type="hidden" name="status" value="1">
                            <input type="hidden" name="syncStatus" value="1">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="submit" class="btn btn-default btn-round waves-effect" name="submituser"><font
                                style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font>
                        </font></button>
                        <button type="button" class="btn btn-danger btn-simple btn-round waves-effect"
                                data-dismiss="modal"><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">CLOSE</font></font></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

