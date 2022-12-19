<div class="example-modal">
    <div class="modal fade" id="addPatientModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="schemeTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="patientRegistrationForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="name">
                                    Name
                                </label>
                                <input type="text" id="name" class="form-control name" name="name"
                                       placeholder="Name"
                                       />
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="age">
                                    Age
                                </label>
                                <input type="number" id="age" minlength="0" maxlength="100" class="form-control age"
                                       name="age"
                                       placeholder="Age"
                                       />
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="gender">
                                    Gender
                                </label>
                                <select class="form-control show-tick gender" name="gender" id="gender" >
                                    <option value="MALE">MALE</option>
                                    <option value="MALE">FEMALE</option>
                                </select>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="address">
                                    Address
                                </label>
                                <input type="text" id="address" class="form-control address" name="address"
                                       placeholder="Address"
                                       />
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="phno">
                                     Mobile Number
                                </label>
                                <input type="tel" id="phno" class="phno" name="phno" minlength="10"
                                       pattern="[789][0-9]{9}" required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="address">
                                    Dr Consultation
                                </label>
                                <input type="text" id="address" class="form-control address" name="address"
                                       placeholder="Address"
                                />
                            </div>

                            <input type="hidden" class="entityType" name="entityTypeId">
                            <input type="hidden" name="createdUser" value="1">
                            <input type="hidden" name="modifiedUser" value="1">
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

