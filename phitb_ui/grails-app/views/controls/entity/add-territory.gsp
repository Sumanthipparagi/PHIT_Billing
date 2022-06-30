<div class="example-modal">
    <div class="modal fade" id="addterritoryModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="territoryTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="territoryForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="territoryName">
                                    Territory Name
                                </label>
                                <input type="text" id="territoryName" class="form-control territoryName" name="territoryName"
                                       placeholder="Territory Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="shortName">
                                    Short Name
                                </label>
                                <input type="text" id="shortName" class="form-control shortName" name="shortName"
                                       placeholder="Short Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="territoryHq">
                                    Territory Hq
                                </label>
%{--                                <input type="number" id="territoryHq" class="form-control territoryHq" name="territoryHq"--}%
%{--                                       placeholder="Territory Hq"--}%
%{--                                       required/>--}%
                                <select class="form-control show-tick territoryHq" style="width: 100%;" name="territoryHq" id="territoryHq">
                                    <g:each var="d" in="${districts}">
                                        <option value="${d.id}">${d.district}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6">
                                <label for="cityId">
                                    City
                                </label>
                                <select class="form-control show-tick cityId" style="width: 100%;" name="cityIds" id="cityId"
                                        multiple>
%{--                                    <g:each var="c" in="${citylist}">--}%
%{--                                        <option value="${c.id}">${c.areaName} (${c.areaName})</option>--}%
%{--                                    </g:each>--}%
                                </select>
                            </div>

                            <div class="col-lg-6">
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
                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entity" style="width: 100%;" name="entity"
                                        id="entity">
                                    <option value="">-- Please select --</option>
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}"  data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
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

