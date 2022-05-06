<div class="example-modal">
    <div class="modal fade" id="addEntitySettingModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="entitySettingTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="entitySettingsForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">

                                <label for="entity">
                                    Entity
                                </label>
                                <select class="form-control show-tick entity" name="entity" id="entity">
                                    <g:each var="e" in="${entity}">
                                        <option value="${e.id}">${e.entityName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="code">
                                    Code
                                </label>
                                <input type="text" id="code" class="form-control code" name="code" placeholder="Code"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="code">
                                    Name
                                </label>
                                <input type="text" id="name" class="form-control name" name="name" placeholder="Name"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="value">
                                    Value
                                </label>
                                <input type="text" id="value" class="form-control value" name="value" placeholder="value"
                                       required/>
                            </div>
%{--                            <div class="col-lg-6 form-group  form-float">--}%

%{--                                <label for="code">--}%
%{--                                    State--}%
%{--                                </label>--}%
%{--                                <select class="form-control show-tick code" name="code" id="code">--}%
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
