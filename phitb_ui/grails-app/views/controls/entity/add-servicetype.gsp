<div class="example-modal">
    <div class="modal fade" id="addserviceTypeModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="serviceTypeTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="serviceTypeForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="serviceType">
                                    Service Type
                                </label>
                                <input type="text" id="serviceType" class="form-control serviceType" name="serviceType"
                                       placeholder="Service Type"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="description">
                                    Service Type
                                </label>
                                <input type="text" id="description" class="form-control desc" name="description"
                                       placeholder="Description"
                                       required/>
                            </div>


                            %{--<div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entity" name="entity" id="entity" >
                                    <option value="">-- Please select --</option>

                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}"  data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>--}%


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
                            <input type="hidden" name="saleId" value="1">
                            <input type="hidden" name="purId" value="1">
                            <input type="hidden" name="saleReturnId" value="1">
                            <input type="hidden" name="saleOrderId" value="1">
                            <input type="hidden" name="purchaseReturnId" value="1">
                            <input type="hidden" name="purchaseOrderId" value="1">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="regionStateIds" value="1">
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

