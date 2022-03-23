<div class="example-modal">
    <div class="modal fade" id="addaccountsModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="addaccountsTitle"></h4>
                </div>
                <form action="" id="form_validation" action="/account-register" method="post" role="form" class="addaccountsForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="accountName">
                                   Account Name
                                </label>
                                <input type="text" id="accountName" class="form-control accountName" name="accountName"
                                       placeholder="Account Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="accountMode">
                                    Account Mode
                                </label>
                                <select class="form-control show-tick accountMode" name="accountMode" id="accountMode" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="am" in="${accountMode}">
                                        <option value="${am.id}">${am.mode}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="balance">
                                    Balance
                                </label>
                                <input type="number" id="balance" class="form-control balance" name="balance"
                                       placeholder="Balance" step="any"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="yearlyBudget">
                                    Yearly Budget
                                </label>
                                <input type="number" id="yearlyBudget" class="form-control yearlyBudget" name="yearlyBudget"
                                       placeholder="Yearly Budget"
                                       required/>
                            </div>

%{--                            <div class="col-lg-6 form-group  form-float">--}%
%{--                                <label for="responsibleUserId">--}%
%{--                                    Responsible User--}%
%{--                                </label>--}%
%{--                                <input type="text" id="responsibleUserId" class="form-control responsibleUserId" name="responsibleUserId"--}%
%{--                                       placeholder="Responsible User"--}%
%{--                                       required/>--}%
%{--                            </div>--}%


                            <div class="col-lg-6 form-group  form-float">
                                <label for="subAccountType">
                                   Sub Account Type
                                </label>
                                <select class="form-control show-tick subAccountType" name="subAccountType" id="subAccountType" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="am" in="${account}">
                                        <option value="${am.id}">${am.accountName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="AccountType">
                                    Account Type
                                </label>
                                <input type="text" id="AccountType" class="form-control AccountType"
                                       name="Account Type"
                                       placeholder="Account Type"
                                       required/>
                            </div>

                            %{--<div class="col-lg-6 form-group  form-float">
                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entityName" name="entityName" id="entity" required>
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
                            %{--                                <select class="form-control show-tick entityType" name="entityType" id="entityTypeId">--}%
                            %{--                                    <g:each var="et" in="${entitytype}">--}%
                            %{--                                        <option value="${et.id}">${et.name}</option>--}%
                            %{--                                    </g:each>--}%
                            %{--                                </select>--}%
                            %{--                            </div>--}%

                            <input type="hidden" id="entityTypeId" class="entityType" name="entityType">
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

