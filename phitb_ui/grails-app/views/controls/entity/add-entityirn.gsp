<div class="example-modal">
    <div class="modal fade" id="addEntityIRNModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="entityIrnTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="entityIrnForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="irnUsername">
                                    IRN Username
                                </label>
                                <input type="text" id="irnUsername" class="form-control irnUsername" name="irnUsername"
                                       placeholder="IRN Username"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="irnPassword">
                                    IRN Password
                                </label>
                                <input type="text" id="irnPassword" class="form-control irnPassword" name="irnPassword"
                                       placeholder="IRN Password"
                                       required/>
                            </div>

                            <div class="col-lg-6">
                                <div class="">
                                    <label for="isActive">isActive</label>
                                    <input type="checkbox" id="isActive" class="isActive" name="isActive" style="width: 23px;height:18px;">
                                </div>
                            </div>

                            <input type="hidden" id="entityTypeId" class="entityType" name="entityType" value="${session.getAttribute('entityTypeId')}">
                            <input type="hidden" id="entity" class="entity" name="entity" value="${session.getAttribute('entityId')}">
                            <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                            <input type="hidden" name="status" value="0">
                            <input type="hidden" name="irnGSTIN" value="">
                            <input type="hidden" name="forceRefreshAccessToken" value="">
                            <input type="hidden" name="authToken" value="">
                            <input type="hidden" name="tokenExpiry" value="">
                            <input type="hidden" name="sek" value="">
                            <input type="hidden" name="sessionId" value="">
                            <input type="hidden" name="appKey" value="">
                            <input type="hidden" name="aspSecretKey" value="">
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

