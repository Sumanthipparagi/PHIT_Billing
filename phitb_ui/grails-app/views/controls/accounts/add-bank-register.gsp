<div class="example-modal">
    <div class="modal fade" id="addbankRegisterModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="bankTitle"></h4>
                </div>

                <form action="" id="form_validation" method="post" role="form" class="bankForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="bankName">
                                    Bank Name
                                </label>
                                <input type="text" id="bankName" class="form-control bankName" name="bankName"
                                       placeholder="Bank Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="cityId">

                                </label>
                                <select class="form-control show-tick cityId" name="cityId" id="cityId">
                                    <g:each var="cl" in="${citylist}">
                                        <option value="${cl.id}">${cl.name}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="ifscCode">
                                    IFSC code
                                </label>
                                <input type="text" id="ifscCode" class="form-control ifscCode" name="ifscCode"
                                       placeholder="IFSC code"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityId">
                                    Entity
                                </label>
                                <select class="form-control show-tick entityId" name="entityId" id="entityId">
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}"data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>


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

                            <input type="hidden" class="entityTypeId"  name="entityTypeId">
%{--                            <input type="hidden" name="entityId" value="1">--}%
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

