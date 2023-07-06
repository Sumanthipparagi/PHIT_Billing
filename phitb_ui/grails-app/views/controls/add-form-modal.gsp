<%@ page import="phitb_ui.Constants" %>
<div class="example-modal">
    <div class="modal fade" id="addFormModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="formTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="formForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="name">
                                    Form Name
                                </label>
                                <input type="text" id="name" class="form-control formName" name="formName"
                                       placeholder="Form Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="formButtonName">
                                    Form Button Name
                                </label>
                                <input type="text" id="formButtonName" class="form-control formButtonName" name="formButtonName" placeholder="Form Button Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="configAllowed">
                                    Config Allowed
                                </label>

                                <select class="form-control show-tick configAllowed" name="configAllowed" id="configAllowed">
                                    <option value="1">YES</option>
                                    <option value="0">NO</option>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="formType">
                                    Form Type
                                </label>

                                <select class="form-control show-tick formType" name="formType" id="formType">
                                    <option value="${Constants.SALE_ORDER}">SALE ORDER</option>
                                    <option value="${Constants.SALE_INVOICE}">SALE INVOICE</option>
                                    <option value="${Constants.SALE_RETURN}">SALE RETURN</option>
                                    <option value="${Constants.PURCHASE_ORDER}">PURCHASE ORDER</option>
                                    <option value="${Constants.PURCHASE_INVOICE}">PURCHASE INVOICE</option>
                                    <option value="${Constants.PURCHASE_RETURN}">PURCHASE RETURN</option>
                                    <option value="${Constants.GRN}">GRN</option>
                                    <option value="${Constants.SAMPLE_INVOCIE}">SAMPLE INVOCIE</option>
                                </select>
                            </div>

                          %{--  <div class="col-lg-6 form-group  form-float">

                                <label for="entity">
                                   Entity
                                </label>
                                <select class="form-control show-tick entity" name="entityId" id="entity">
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>--}%


%{--                            <div class="col-lg-6 form-group  form-float">--}%
%{--                                <label for="entityTypeId">--}%
%{--                                    Entity Type--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick entityType" name="entityTypeId" id="entityTypeId">--}%
%{--                                    <g:each var="et" in="${entitytype}">--}%
%{--                                        <option value="${et.id}" data-type="${et.entityType.id}">${et.name}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%
                            <input type="hidden" class="entityTypeId"  name="entityTypeId">
                            <input type="hidden" name="entity" value="${session.getAttribute('entity').toString()}">
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId').toString()}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId').toString()}">
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

