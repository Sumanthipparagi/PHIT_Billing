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
                                <label for="apprExpense">
                                   Appr expense
                                </label>
                                <input type="number" id="apprExpense" class="form-control apprExpense" name="apprExpense"
                                       placeholder="Appr expense"
                                       required/>
                            </div>

                            <div class="col-lg-6 ">
                                <label for="daysOfWeek">
                                    Days Of Week
                                </label>
                                <select id="daysOfWeek" class="daysOfWeek" style="width: 100%;" name="daysOfWeek"
                                        multiple
                                       required>
                                    <option value="${Constants.MONDAY}">Monday</option>
                                    <option value="${Constants.TUESDAY}">Tuesday</option>
                                    <option value="${Constants.WEDNESDAY}">Wednesday</option>
                                    <option value="${Constants.THURSDAY}">Thursday</option>
                                    <option value="${Constants.FRIDAY}">Friday</option>
                                    <option value="${Constants.SATURDAY}">Saturday</option>
                                    <option value="${Constants.SUNDAY}">Sunday</option>
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

                            <div class="col-lg-6 form-group  form-float">
                                <label for="pinCode">
                                    PIN Code
                                </label>
                                <div>
                                    <select class="pinCode form-control" style="width: 100%;" id="pinCode"></select>
                                </div>
                            </div>

                            <div class="col-lg-6 form-group  form-float addcity">
                                <label for="cityId">
                                    City
                                </label>
                                <select class="form-control show-tick cityId" style="width: 100%;" name="cityId" id="cityId">
                                    <g:each var="city" in="${citylist}">
                                        <option value="${city.id}">${city.districtName} / ${city.areaName}</option>
                                    </g:each>
                                </select>
                            </div>


%{--                            <div class="col-lg-6 form-group  form-float updatecity">--}%
%{--                                <label for="updatecity">--}%
%{--                                    City--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick updatecity" style="width: 100%;" name="cityId" id="updatecity">--}%
%{--                                    <g:each var="city" in="${citylist}">--}%
%{--                                        <option value="${city.id}">${city.districtName} / ${city.areaName}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%


                            <div class="col-lg-6 form-group  form-float">
                                <label for="countryId">
                                    Country
                                </label>
                                <select class="form-control show-tick countryId" style="width: 100%;" name="countryId" id="countryId">
                                    <g:each var="c" in="${countrylist}">
                                        <option value="${c.id}">${c.name}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="stateId">
                                    State
                                </label>
                                <select class="form-control show-tick stateId" style="width: 100%;" name="stateId" id="stateId">
                                    <g:each var="state" in="${statelist}">
                                        <option value="${state.id}">${state.name}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="areaManager">
                                    Area Manager
                                </label>
                                <select class="form-control show-tick areaManager" name="areaManager" id="areaManager">
                                    <option value="0">Please Select</option>
                                    <g:each var="manager" in="${managerList}">
                                        <option value="${manager.id}">${manager.userName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesman">
                                    Salesman
                                </label>
                                <select class="form-control show-tick salesman" name="salesman" id="salesman">
                                    <option value="0">Please Select</option>
                                    <g:each var="salesman" in="${salesmanList}">
                                        <option value="${salesman.id}">${salesman.userName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="ccmId">
                                    CCM
                                </label>
                                <select class="form-control show-tick ccmId" name="ccmId" id="ccmId">
                                    <option value="0">Please Select</option>
                                    <g:each var="cc" in="${ccm}">
                                        <option value="${cc.id}">${cc.kitName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                            <label for="entity">
                                Entity
                            </label>
                            <select class="form-control show-tick entity" name="entity" id="entity" style="width: 100%;">
                               %{-- <option value="">-- Please select --</option>
                                <g:each var="e" in="${entity}">
                                    <option value="${e.id}"  data-type="${e.entityType.id}s">${e.entityName}</option>
                                </g:each>--}%
                            </select>
                        </div>


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
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
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

