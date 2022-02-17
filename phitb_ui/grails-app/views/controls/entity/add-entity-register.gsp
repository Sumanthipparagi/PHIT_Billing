<div class="example-modal">
    <div class="modal fade" id="addEntityRegisterModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="entityRegisterTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="entityRegisterForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityName">
                                    Entity Name
                                </label>
                                <input type="text" id="entityName" class="form-control entityName" name="mode" placeholder="Entity Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityType">
                                    Entity Type
                                </label>
                                <select class="form-control show-tick entityType" name="entityType" id="entityType">
                                    <g:each var="et" in="${entitytype}">
                                        <option value="${et.id}">${et.name}</option>
                                    </g:each>
                                </select>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="entityName">
                                    Affiliate Id
                                </label>
                                <input type="text" id="affiliateId" class="form-control affiliateId"
                                       name="affiliateId" placeholder="Affliate Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="addressLine1">
                                    Address Line 1
                                </label>
                                <input type="text" id="addressLine1" class="form-control addressLine1"
                                       name="addressLine1" placeholder="Address Line 1"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="addressLine1">
                                    Address Line 2
                                </label>
                                <input type="text" id="addressLine2" class="form-control addressLine2"
                                       name="addressLine2" placeholder="Address Line 2"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="countryId">
                                    Country
                                </label>
                                <select class="form-control show-tick countryId" name="entityType" id="countryId">
                                    <g:each var="country" in="${countrylist}">
                                        <option value="${country.id}">${country.name}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="stateId">
                                   State
                                </label>
                                <select class="form-control show-tick stateId" name="stateId" id="stateId">
                                    <g:each var="state" in="${statelist}">
                                        <option value="${state.id}">${state.name}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="cityId">
                                    City
                                </label>
                                <select class="form-control show-tick cityId" name="cityId" id="cityId">
                                    <g:each var="city" in="${citylist}">
                                        <option value="${city.id}">${city.name}</option>
                                    </g:each>
                                </select>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="pinCode">
                                    Pin Code
                                </label>
                                <input type="text" id="pinCode" class="form-control pinCode"
                                       name="pinCode" placeholder="Pin Code"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="phoneNumber">
                                    Phone Number
                                </label>
                                <input type="text" id="phoneNumber" class="form-control phoneNumber"
                                       name="phoneNumber" placeholder="Phone Number"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="mobileNumber">
                                    Mobile Number
                                </label>
                                <input type="text" id="mobileNumber" class="form-control mobileNumber"
                                       name="mobileNumber" placeholder="Mobile Number"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="email">
                                    Email
                                </label>
                                <input type="email" id="email" class="form-control email"
                                       name="email" placeholder="Email"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="contactName">
                                    Contact Name
                                </label>
                                <input type="text" id="contactName" class="form-control contactName"
                                       name="contactName" placeholder="Contact Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="priorityId">
                                    Priority Id
                                </label>
                                <input type="text" id="priorityId" class="form-control priorityId"
                                       name="PriorityId" placeholder="Priority Id"
                                       required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="pan">
                                    PAN
                                </label>
                                <input type="text" id="pan" class="form-control pan"
                                       name="pan" placeholder="PAN"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstn">
                                    GSTIN
                                </label>
                                <input type="text" id="gstn" class="form-control gstn"
                                       name="gstn" placeholder="GSTIN"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="gstn">
                                    USD number
                                </label>
                                <input type="text" id="usdNumber" class="form-control usdNumber"
                                       name="gstn" placeholder="USD number"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="corpId">
                                    Crop Id
                                </label>
                                <input type="number" id="corpId" class="form-control corpId"
                                       name="corpId" placeholder="Crop Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="drugLicence1">
                                    Drug Licence 1
                                </label>
                                <input type="text" id="drugLicence1" class="form-control drugLicence1"
                                       name="drugLicence1" placeholder="Drug Licence 1"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="drugLicence2">
                                    Drug Licence 2
                                </label>
                                <input type="text" id="drugLicence2" class="form-control drugLicence2"
                                       name="drugLicence2" placeholder="Drug Licence 2"
                                       required/>
                            </div>
                            <div class="col-lg-6 form-group  form-float">
                                <label for="drugLicenceValidity">
                                    Drug Licence Validity
                                </label>
                                <input type="text" id="drugLicenceValidity" class="form-control drugLicenceValidity"
                                       name="drugLicence2" placeholder=" Drug Licence Validity"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="drugLicenceValidity">
                                    Food Licence Validity
                                </label>
                                <input type="text" id="foodLicenceValidity" class="form-control foodLicenceValidity"
                                       name="drugLicence2" placeholder="Food Licence Validity"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesBalanceLimit">
                                    Sales Balance Limit
                                </label>
                                <input type="text" id="salesBalanceLimit" class="form-control salesBalanceLimit"
                                       name="salesBalanceLimit" placeholder="Sales Balance Limit"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="noOfCrDays">
                                    Number Of CR Days
                                </label>
                                <input type="text" id="noOfCrDays" class="form-control noOfCrDays"
                                       name="salesBalanceLimit" placeholder=" Number Of CR Days"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="noOfGraceDays">
                                    Number Of Grace Days
                                </label>
                                <input type="text" id="noOfGraceDays" class="form-control noOfGraceDays"
                                       name="noOfGraceDays" placeholder="Number Of Grace Days"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="calculateOn">
                                    Calculate On
                                </label>
                                <input type="text" id="calculateOn" class="form-control calculateOn"
                                       name="calculateOn" placeholder="Number Of Grace Days"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="bankId">
                                    Bank Id
                                </label>
                                <input type="text" id="bankId" class="form-control calculateOn"
                                       name="bankId" placeholder="Bank Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="accountNo">
                                    Account Number
                                </label>
                                <input type="text" id="accountNo" class="form-control accountNo"
                                       name="accountNo" placeholder="Account Number"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="upiId">
                                    UPI Id
                                </label>
                                <input type="text" id="upiId" class="form-control upiId"
                                       name="upiId" placeholder="UPI Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="openingBalance">
                                    Opening Balance
                                </label>
                                <input type="text" id="openingBalance" class="form-control openingBalance"
                                       name="openingBalance" placeholder="Opening Balance"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="currentBalance">
                                    Current Balance
                                </label>
                                <input type="text" id="currentBalance" class="form-control currentBalance"
                                       name="currentBalance" placeholder="Current Balance"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="discount">
                                    Discount
                                </label>
                                <input type="text" id="discount" class="form-control discount"
                                       name="discount" placeholder="Discount"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="bankCommision">
                                    Bank Commision
                                </label>
                                <input type="text" id="bankCommision" class="form-control bankCommision"
                                       name="bankCommision" placeholder="Bank Commision"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="transportTypeId">
                                    Transport Type Id
                                </label>
                                <input type="text" id="transportTypeId" class="form-control transportTypeId"
                                       name="transportTypeId" placeholder="Transport Type Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="defaultCharge">
                                    Default Charge
                                </label>
                                <input type="text" id="defaultCharge" class="form-control defaultCharge"
                                       name="defaultCharge" placeholder="Default Charge"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="careTaker">
                                    Care Taker
                                </label>
                                <input type="text" id="careTaker" class="form-control careTaker"
                                       name="defaultCharge" placeholder="Care Taker"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="contact">
                                    Contact
                                </label>
                                <input type="text" id="contact" class="form-control contact"
                                       name="defaultCharge" placeholder="Contact"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="terms">
                                    Terms
                                </label>
                                <input type="text" id="terms" class="form-control terms"
                                       name="terms" placeholder="Terms"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesman">
                                    Salesman
                                </label>
                                <input type="text" id="salesman" class="form-control salesman"
                                       name="terms" placeholder="Salesman"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesman">
                                    Manager
                                </label>
                                <input type="text" id="manager" class="form-control manager"
                                       name="manager" placeholder="Manager"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="salesmanCommission">
                                    Salesman Commission
                                </label>
                                <input type="text" id="salesmanCommission" class="form-control salesmanCommission"
                                       name="manager" placeholder="Salesman Commission"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="routeId">
                                    Route Id
                                </label>
                                <input type="text" id="routeId" class="form-control routeId"
                                       name="manager" placeholder="Route Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="accountId">
                                    Account Id
                                </label>
                                <input type="text" id="accountId" class="form-control accountId"
                                       name="accountId" placeholder="Account Id"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="aadharId">
                                    Aadhar Id
                                </label>
                                <input type="text" id="aadharId" class="form-control aadharId"
                                       name="aadharId" placeholder="Aadhar Id"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">
                                <label for="companyCode">
                                    Company Code
                                </label>
                                <input type="text" id="companyCode" class="form-control companyCode"
                                       name="companyCode" placeholder="Company Code"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="faxNumber">
                                    Fax Number
                                </label>
                                <input type="text" id="faxNumber" class="form-control faxNumber"
                                       name="faxNumber" placeholder="Fax Number"
                                       required/>
                            </div>



                            <div class="col-lg-6 form-group  form-float">
                                <label for="repName">
                                    Rep Name
                                </label>
                                <input type="text" id="repName" class="form-control repName"
                                       name="repName" placeholder="Rep Name"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="password">
                                    Password
                                </label>
                                <input type="text" id="password" class="form-control password"
                                       name="password" placeholder="Password"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="zoneId">
                                    Zone Id
                                </label>
                                <input type="text" id="zoneId" class="form-control zoneId"
                                       name="password" placeholder="Zone Id"
                                       required/>
                            </div>

                            <div class="col-lg-6 form-group  form-float">
                                <label for="contactDob">
                                    Contact Dob
                                </label>
                                <input type="text" id="contactDob" class="form-control contactDob"
                                       name="contactDob" placeholder="Contact Dob"
                                       required/>
                            </div>
                            <input type="hidden" name="entityId" value="1">
                            <input type="hidden" name="createdUser" value="1">
                            <input type="hidden" name="modifiedUser" value="1">
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

