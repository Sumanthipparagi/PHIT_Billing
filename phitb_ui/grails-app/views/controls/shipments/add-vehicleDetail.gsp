<div class="example-modal">
    <div class="modal fade" id="addVehicleDetailModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="transportTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="transportForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="transportType">
                                    Transport Type
                                </label>
                                <select class="form-control show-tick transportType" name="transportType" id="transportType" required>
                                    <option value="0">Please Select</option>
                                    <g:each var="t" in="${transportType}">
                                        <option value="${t.id}">${s.transportType}</option>
                                    </g:each>
                                </select>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="vehicleName">
                                    Vehicle Name
                                </label>
                                <input type="text" id="vehicleName" class="form-control vehicleName"
                                       name="vehicleName" placeholder=" Vehicle Name" required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="vehicleRegNo">
                                    Vehicle Reg No
                                </label>
                                <input type="text" id="vehicleRegNo" class="form-control vehicleRegNo"
                                       name="vehicleRegNo" placeholder="Vechile Reg No" required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="vehiclePurcDate">
                                    Vehicle Purchase Date
                                </label>
                                <input type="text" id="vehiclePurcDate" class="form-control vehiclePurcDate"
                                       name="vehiclePurcDate" placeholder=" Vehicle Purchase Date" required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="managerId">
                                   Manager
                                </label>
                                <select class="form-control show-tick transportType" name="managerId" id="managerId" required>
                                    <option value="0">Please Select</option>
                                    <g:each var="s" in="${series}">
                                        <option value="${s.id}">${s.seriesName}</option>
                                    </g:each>
                                </select>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="gpsKitId">
                                    GPS kit Id
                                </label>
                                <input type="text" id="gpsKitId" class="form-control gpsKitId"
                                       name="gpsKitId" placeholder="GPS kit Id" required/>
                            </div>

                        </div>
                    </div>
                    <input type="hidden" name="entityId"  value="${session.getAttribute('entityId')}">
                    <input type="hidden" name="entityTypeId"  value="${session.getAttribute('entityTypeId')}">
                    <input type="hidden" name="createdUser"  value="${session.getAttribute('userId')}">
                    <input type="hidden" name="modifiedUser"  value="${session.getAttribute('userId')}">
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

