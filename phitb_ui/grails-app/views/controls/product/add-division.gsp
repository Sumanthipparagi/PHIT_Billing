<div class="example-modal">
    <div class="modal fade" id="adddivisionModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="divisionTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="divisionForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="divisionName">
                                    Division Name
                                </label>
                                <input type="text" id="divisionName" class="form-control divisionName" name="divisionName"
                                       placeholder="Customer Group Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="divisionShortName">
                                    Division Short Name
                                </label>
                                <input type="text" id="divisionShortName" class="form-control divisionShortName" name="divisionShortName"
                                       placeholder="Short Name"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="cityIds">
                                    City
                                </label>
                                <select class="form-control show-tick cityIds" name="cityIds" id="cityIds"
                                        multiple="multiple" required>
                                    <g:each var="c" in="${citylist}">
                                        <option value="${c.id}">${c.name}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="stateIds">
                                    State
                                </label>
                                <select class="form-control show-tick stateIds" name="stateIds" id="stateIds"
                                        multiple="multiple" required>
                                    <g:each var="s" in="${statelist}">
                                        <option value="${s.id}">${s.name}</option>
                                    </g:each>
                                </select>
                            </div>



                            <div class="col-lg-6 form-group  form-float">
                                <label for="zoneIds">
                                    Zone
                                </label>
                                <select class="form-control show-tick zoneIds" name="zoneIds" id="zoneIds"
                                        multiple="multiple" required>
                                    <g:each var="z" in="${zoneList}">
                                        <option value="${z.id}">${z.name}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="seriesId">
                                    Series
                                </label>
                                <select class="form-control show-tick seriesId" name="seriesId" id="seriesId" required>
                                    <g:each var="s" in="${series}">
                                        <option value="${s.id}">${s.seriesName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="managerId">
                                    Manager
                                </label>
                                <select class="form-control show-tick managerId" name="managerId" id="managerId" required>
                                    <g:each var="m" in="${managerList}">
                                        <option value="${m.id}">${m.userName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="customerIds">
                                    Customer group
                                </label>
                                <select class="form-control show-tick customerIds" name="customerIds"
                                        id="customerIds"  multiple="multiple" required>
                                    <g:each var="cust" in="${customerList}">
                                        <option value="${cust.id}">${cust.entityName}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityId">
                                    Entity
                                </label>
                                <select class="form-control show-tick entityId" name="entityId" id="entityId" required>
                                    <g:each var="e" in="${entitylist}">
                                        <option value="${e.id}"  data-type="${e.entityType.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>


%{--                            <div class="col-lg-6 form-group  form-float">--}%
%{--                                <label for="entityTypeId">--}%
%{--                                    Entity Type--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick entityType" name="entityTypeId"--}%
%{--                                        id="entityTypeId" required>--}%
%{--                                    <g:each var="et" in="${entitytype}">--}%
%{--                                        <option value="${et.id}">${et.name}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%

                            <input type="hidden" class="entityType" name="entityTypeId">
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

