<div class="example-modal">
    <div class="modal fade" id="addHqareaModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="hqAreaTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="hqAreaForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="hqName">
                                    HQ Name
                                </label>
                                <input type="text" id="hqName" class="form-control hqName" name="hqName"
                                       placeholder="HQ Name"
                                       required/>
                            </div>


                            <div class="col-lg-6">
                                <label for="cityIds">
                                    Cities
                                </label>
                                <select style="width: 100%"  name="cityIds" id="cityIds"
                                        required multiple>
                                    <g:each var="c" in="${cities}">
                                        <option value="${c.id}">${c.areaName}</option>
                                    </g:each>
                                </select>
                            </div>

                            <input type="hidden" id="entityTypeId" class="entityType" name="entityType" value="${session.getAttribute('entityTypeId')}">
                            <input type="hidden" id="entity" class="entity" name="entity" value="${session.getAttribute('entityId')}">
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="status" value="0">
                            <input type="hidden" name="syncStatus" value="0">
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

