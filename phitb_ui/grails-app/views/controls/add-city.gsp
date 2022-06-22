<div class="example-modal">
    <div class="modal fade" id="addCityModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="cityTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="cityForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="areaName">
                                    Area Name
                                </label>
                                <input type="text" id="areaName" class="form-control areaName" name="areaName" placeholder="Area Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="areaCode">
                                    Area Code
                                </label>
                                <input type="text" id="areaCode" class="form-control areaCode" name="areaCode"
                                       placeholder="Area Code" required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="circleName">
                                    Circle Name
                                </label>
                                <input type="text" id="circleName" class="form-control circleName" name="circleName"
                                       plceholder="Circle Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="pincode">
                                  Pincode
                                </label>
                                <input type="number" id="pincode" class="form-control pincode" name="pincode"
                                       placeholder="Pincode"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="latitude">
                                    Latitude
                                </label>
                                <input type="number" id="latitude" class="form-control latitude" name="latitude"
                                       placeholder="Latitude"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="logitude">
                                    Longitude
                                </label>
                                <input type="number" id="logitude" class="form-control logitude" name="logitude"
                                       placeholder="Longitude"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="region">
                                    Region
                                </label>
                                <select class="form-control show-tick region" name="region" id="region"  style="width:
                                350px">
                                    <option value="">--Please Select--</option>
                                    <g:each var="r" in="${region}">
                                        <option value="${r.id}">${r.regionName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="division">
                                    Division
                                </label>
                                <select class="form-control show-tick division" name="division" id="division"  style="width:
                                350px">
                                    <option value="">--Please Select--</option>
                                    <g:each var="d" in="${division}">
                                        <option value="${d.id}">${d.divisionName}</option>
                                    </g:each>
                                </select>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="district">
                                    District
                                </label>
                                <select class="form-control show-tick district" name="district" id="district"  style="width:
                                350px" required>
                                    <option value="">--Please Select--</option>
                                    <g:each var="d" in="${district}">
                                        <option value="${d.id}">${d.district}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="state">
                                    State
                                </label><br>
                                <select class="form-control show-tick state" name="state"  id="state" style="width:
                                350px">
                                    <option value="">--Please Select--</option>
                                    <g:each var="s" in="${state}">
                                        <option value="${s.id}">${s.name}</option>
                                    </g:each>
                                </select>
                            </div>



                            %{--                            <div class="col-lg-6 form-group  form-float">--}%

%{--                                <label for="entity">--}%
%{--                                    Entity--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick entity" name="entityId" id="entity" required>--}%
%{--                                    <option value="">Please select</option>--}%

%{--                                    <g:each var="e" in="${entity}">--}%
%{--                                        <option value="${e.id}">${e.entityName}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%

%{--                            <div class="col-lg-6 form-group  form-float">--}%

%{--                                <label for="state">--}%
%{--                                    State--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick state" name="stateId" id="state">--}%
%{--                                    <g:each var="state" in="${stateArrayList}">--}%
%{--                                        <option value="${state.id}">${state.name}</option>--}%
%{--                                    </g:each>--}%
%{--                                </select>--}%
%{--                            </div>--}%
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

