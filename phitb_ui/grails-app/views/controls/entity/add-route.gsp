<%@ page import="phitb_ui.Constants" %>
<div class="example-modal">
    <div class="modal fade" id="addrouteModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="routeTitle"></h4>
                </div>

                <form action="" id="form_validation" method="post" role="form" class="routeForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="routeName">
                                    Route Name
                                </label>
                                <input type="text" id="routeName" class="form-control routeName" name="routeName"
                                       placeholder="Route Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="routeCode">
                                    Route Code
                                </label>
                                <input type="text" id="routeCode" class="form-control routeCode" name="routeCode"
                                       placeholder="Route Code" maxlength="10"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="apprExpense">
                                    Appr expense
                                </label>
                                <input type="number" id="apprExpense" class="form-control apprExpense"
                                       name="apprExpense"
                                       placeholder="Appr expense"
                                       required/>
                            </div>

                            <div class="col-lg-6 ">
                                <label for="zoneIds">
                                    Select Zones
                                </label>
                                <select id="zoneIds" class="zoneIds" style="width: 100%;" name="zoneIds"
                                        multiple
                                        required>
                                    <g:each in="${zoneList}" var="z">
                                        <option value="${z.id}">${z.name}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="ccmEnabled">
                                    CCM Enabled
                                </label>
                                <select class="form-control show-tick ccmEnabled" name="ccmEnabled" id="ccmEnabled">
                                    <option value="1">YES</option>
                                    <option value="0">NO</option>
                                </select>
                            </div>

                          %{--  <div class="col-lg-6 form-group  form-float">
                                <label for="areaManager">
                                    Area Manager
                                </label>
                                <select class="form-control show-tick areaManager" name="areaManager" id="areaManager">
                                    <option value="0">Please Select</option>
                                    <g:each var="manager" in="${managerList}">
                                        <option value="${manager.id}">${manager.userName}</option>
                                    </g:each>
                                </select>
                            </div>--}%

                        </div>

                        <input type="hidden" id="entityId" class="entityId" name="entityId"
                               value="${session.getAttribute('entityId')}">
                        <input type="hidden" id="entityTypeId" class="entityType" name="entityType"
                               value="${session.getAttribute('entityTypeId')}">
                        <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                        <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                        <input type="hidden" name="regionStateIds" value="1">
                        <input type="hidden" name="status" value="1">
                        <input type="hidden" name="syncStatus" value="1">
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

