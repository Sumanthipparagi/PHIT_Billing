<div class="example-modal">
    <div class="modal fade" id="addaccountsModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="addaccountsTitle"></h4>
                </div>

                <form id="form_validation" action="/accounts" method="post" role="form" class="addaccountsForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group form-float">
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
                                <select class="form-control show-tick accountMode" name="accountMode" id="accountMode"
                                        required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="am" in="${accountMode}">
                                        <option value="${am.id}">${am.mode}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group form-float">
                                <label for="balance">
                                    Balance
                                </label>
                                <input type="number" id="balance" class="form-control balance" name="balance"
                                       placeholder="Balance" step="any"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group form-float">
                                <label for="yearlyBudget">
                                    Yearly Budget
                                </label>
                                <input type="number" id="yearlyBudget" class="form-control yearlyBudget"
                                       name="yearlyBudget"
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


                            <div class="col-lg-6 form-group form-float">
                                <label for="subAccountType">
                                    Sub Account Type
                                </label>
                                <select class="form-control show-tick subAccountType" name="subAccountType"
                                        id="subAccountType" required>
                                    <option value="">-- Please select --</option>
                                    <g:each var="am" in="${account}">
                                        <option value="${am.id}">${am.accountName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group form-float">
                                <label for="AccountType">
                                    Account Type
                                </label>
                                <select id="AccountType" class="form-control AccountType"
                                        name="accountType"
                                        required>
                                    <g:each in="${accountTypes}" var="at">
                                        <option value="${at.id}">${at.accountType}</option>
                                    </g:each>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="checkbox">
                                            <label for="showInDebit">Show in Debit</label>
                                            <input type="checkbox" id="showInDebit" class="showInDebit"
                                                   name="showInDebit"/>
                                        </div>
                                    </div>

                                    <div class="col-lg-6">
                                        <div class="checkbox">
                                            <label for="showInCredit">Show in Credit</label>
                                            <input type="checkbox" id="showInCredit" class="showInCredit"
                                                   name="showInCredit"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id="entityTypeId" class="entityType" name="entityType">
                            <input type="hidden" name="createdUser" value="${session.getAttribute("userId")}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute("userId")}">
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

