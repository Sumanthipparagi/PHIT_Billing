<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: User Register</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/dropify/dist/css/dropify.min.css"/>
    %{--    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css"/>--}%
    %{--    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />--}%
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

    <style>
    .error
    {
        color:red;
    }
    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="PharmIT"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Add User Register</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="/"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/user-register"> User Register</a></li>
                        <li class="breadcrumb-item active">Add User Register</li>
                    </ul>
                </div>

                <div class="col-lg-7 col-md-7 col-sm-12">
                    <div class="input-group m-b-0">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-addon">
                            <i class="zmdi zmdi-search"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>


        <!-- Inline Layout -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    <div class="body">
                        <form action="/user-register" id="userRegisterForm" method="POST" role="form"
                              class="userRegisterForm" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="userName">
                                                User Name
                                            </label>
                                            <input type="text" id="userName" class="form-control userName" name="userName"
                                                   placeholder="User Name"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="password">
                                                Password
                                            </label>
                                            <input type="password" id="password" class="form-control password" name="password"

                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="mobileNumber">
                                                Mobile Number
                                            </label>
                                            <input type="text" id="mobileNumber" class="form-control mobileNumber"
                                                   name="mobileNumber"
                                                   placeholder="Mobile Number"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="contactNumber">
                                                Contact Number
                                            </label>
                                            <input type="text" id="contactNumber" class="form-control contactNumber"
                                                   name="contactNumber"
                                                   placeholder="Contact Number"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="aadharId">
                                                Aadhar Id
                                            </label>
                                            <input type="text" id="aadharId" class="form-control aadharId" name="aadharId"
                                                   placeholder="Aadhar Id"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="address">
                                                Address
                                            </label>
                                            <input type="text" id="address" class="form-control address"
                                                   name="address" placeholder="Address"
                                                   required/>
                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="genderId">
                                                Gender
                                            </label>
                                            <select class="form-control show-tick genderId" name="genderId" id="genderId">
                                                <g:each var="g" in="${gender}">
                                                    <option value="${g.id}">${g.name}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="reportTo">
                                                Report To
                                            </label>
                                            <select class="form-control show-tick reportTo" name="reportTo" id="reportTo">
                                                <g:each var="u" in="${userregister}">
                                                    <option value="${u.id}">${u.userName}</option>
                                                </g:each>
                                            </select>
                                        </div>


                                    </div>
                                </div>
                                <div class="ml-1"></div>
                                <div class="col-md-6" style="max-width: 49%;border: 1px solid black;  border-radius: 10px;    padding: 10px;
                                ">
                                    <div class="row">
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="pinCode">
                                                Pin Code
                                            </label>
                                            <div>
                                                <select class="pinCode form-control" id="pinCode" ></select>
                                                <input type="hidden" name="pinCode">
                                            </div>

                                        </div>
                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="nationality">
                                                Nationality
                                            </label>
                                            <input type="text" id="nationality" class="form-control nationality"
                                                   name="nationality" placeholder="Nationality"
                                                   required/>
                                        </div>



                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="countryId">
                                                Country
                                            </label>
                                            <select class="form-control show-tick countryId" name="countryId"
                                                    id="countryId" disabled>
                                                <g:each var="country" in="${countrylist}">
                                                    <option value="${country.id}">${country.name}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="stateId">
                                                State
                                            </label>
                                            <select class="form-control show-tick stateId" name="stateId" id="stateId" disabled>
                                                <g:each var="state" in="${statelist}">
                                                    <option value="${state.id}">${state.name}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="cityId">
                                                City
                                            </label>
                                            <select class="form-control show-tick cityId" name="cityId" id="cityId" disabled>
                                                <g:each var="city" in="${citylist}">
                                                    <option value="${city.id}">${city.name}</option>
                                                </g:each>
                                            </select>
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
                                            <label for="referredBy">
                                                Referred By
                                            </label>
                                            <select class="form-control show-tick referredBy" name="referredBy" id="referredBy">
                                                <g:each var="u" in="${userregister}">
                                                    <option value="${u.id}">${u.userName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="referenceRelation">
                                                Reference Relation
                                            </label>
                                            <input type="text" id="referenceRelation" class="form-control referenceRelation"
                                                   name="referenceRelation" placeholder="Reference Relation"
                                                   required/>
                                        </div>

                                    </div>
                                </div>



                                <div class="col-md-6 mt-2" style="max-width: 49%;border: 1px solid black;
                                border-radius: 10px;padding: 10px;
                                ">
                                    <div class="row">


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="joiningDate">
                                                Joining Date
                                            </label>
                                            <input type="text" id="joiningDate" class="form-control joiningDate"
                                                   name="joiningDate" placeholder="Joining Date"
                                                   required/>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="department">
                                                Department
                                            </label>
                                            <select class="form-control show-tick department" name="department" id="department">
                                                <g:each var="d" in="${department}">
                                                    <option value="${d.id}">${d.name}</option>
                                                </g:each>
                                            </select>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="permissions">
                                                Permissions
                                            </label>
                                            <input type="text" id="permissions" class="form-control permissions"
                                                   name="permissions" placeholder="Permissions"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="dob">
                                                Date of Birth
                                            </label>
                                            <input type="text" id="dob" class="form-control dob"
                                                   name="dob" placeholder="Date of Birth"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="anniversaryDate">
                                                Anniversary Date
                                            </label>
                                            <input type="text" id="anniversaryDate" class="form-control anniversaryDate"
                                                   name="anniversaryDate" placeholder="Anniversary Date"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="approvedSalary">
                                                Approved Salary
                                            </label>
                                            <input type="number" id="approvedSalary" class="form-control approvedSalary"
                                                   name="approvedSalary" placeholder="Approved Salary"
                                                   required/>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="designationSalary">
                                                Designation Salary
                                            </label>
                                            <input type="number" id="designationSalary" class="form-control designationSalary"
                                                   name="designationSalary" placeholder="Designation Salary"
                                                   required/>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="lastPaidDate">
                                                Last Paid Date
                                            </label>
                                            <input type="text" id="lastPaidDate" class="form-control lastPaidDate"
                                                   name="lastPaidDate" placeholder="Last Paid Date"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="paymentModeId">
                                                Payment Id
                                            </label>
                                            <input type="text" id="paymentModeId" class="form-control paymentModeId"
                                                   name="paymentModeId" placeholder="Payment Id"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="bankAccount">
                                                Bank Account
                                            </label>
                                            <input type="text" id="bankAccount" class="form-control bankAccount"
                                                   name="bankAccount" placeholder="Bank Account"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="bankId">
                                                Bank
                                            </label>
                                            <select class="form-control show-tick bankId" name="bankId" id="bankId">
                                                <option value="0">--Please Select--</option>
                                                <g:each var="b" in="${bank}">
                                                    <option value="${b.id}">${b.bankName}</option>
                                                </g:each>
                                            </select>
                                        </div>



                                    </div>
                                </div>



                                <div class="ml-1"></div>

                                <div class="col-md-6 mt-2" style="max-width: 49%;border: 1px solid black;
                                border-radius:
                                10px;padding: 10px;
                                ">
                                    <div class="row">

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="assignedHolidays">
                                                Assigned Holidays
                                            </label>
                                            <input type="text" id="assignedHolidays" class="form-control assignedHolidays"
                                                   name="assignedHolidays" placeholder="Assigned Holidays"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="specialization">
                                                Specialization
                                            </label>
                                            <input type="text" id="specialization" class="form-control specialization"
                                                   name="specialization" placeholder="Specialization"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="licenceNumber">
                                                Licence Number
                                            </label>
                                            <input type="text" id="licenceNumber" class="form-control licenceNumber"
                                                   name="licenceNumber" placeholder="Licence Number"
                                                   required/>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="account">
                                                Account
                                            </label>
                                            <select class="form-control show-tick account" name="account" id="account">
                                                <g:each var="a" in="${account}">
                                                    <option value="${a.id}">${a.accountName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="role">
                                                Role
                                            </label>
                                            <select class="form-control show-tick role" name="role" id="role">
                                                <g:each var="r" in="${role}">
                                                    <option value="${r.id}">${r.name}</option>
                                                </g:each>
                                            </select>
                                        </div>


                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="divisionId">
                                                Division
                                            </label>
                                            <select class="form-control show-tick divisionId" name="divisionId" id="divisionId">
                                                <g:each var="d" in="${division}">
                                                    <option value="${d.id}">${d.divisionName}</option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="col-lg-6 form-group  form-float">
                                            <label for="zoneId">
                                                Zone
                                            </label>
                                            <select class="form-control show-tick zoneId" name="zoneId" id="zoneId">
                                                <g:each var="zone" in="${zoneList}">
                                                    <option value="${zone.id}">${zone.name}</option>
                                                </g:each>
                                            </select>
                                        </div>

%{--                                        <div class="col-lg-6 form-group  form-float">--}%
%{--                                            <label for="entity">--}%
%{--                                                Entity--}%
%{--                                            </label>--}%
%{--                                            <select class="form-control show-tick entity" name="entity" id="entity" required>--}%
%{--                                                <g:each var="e" in="${entity}">--}%
%{--                                                    <option value="${e.id}"--}%
%{--                                                            data-type="${e.entityType.id}">${e.entityName}</option>--}%
%{--                                                </g:each>--}%
%{--                                            </select>--}%
%{--                                        </div>--}%


                                    </div>
                                </div>



                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="entity" value="${session.getAttribute('entityId')}">
                                <input type="hidden" name="syncStatus" value="1">
                                <input type="hidden" name="lastLoginDate" value="12/02/2020">
                                <input type="hidden" name="createdUser" value="${session.getAttribute('userId')}">
                                <input type="hidden" name="modifiedUser" value="${session.getAttribute('userId')}">
                                <div class="col-lg-12">
                                    <div class="" style="float: right;">
                                        <input name="id" id="id" class="id" type="hidden">
                                        <input name="type" class="type" value="add" type="hidden">
                                        <button type="submit" class="btn btn-default btn-round waves-effect"
                                                name="submituser"><font style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">SUBMIT</font></font></button>
                                        <button type="reset" class="btn btn-danger btn-simple btn-round waves-effect"
                                                data-dismiss="modal"><font style="vertical-align: inherit;"><font
                                                style="vertical-align: inherit;">RESET</font></font></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/plugins/select2/"/>
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
%{--<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>--}%
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/dropify/dist/js/dropify.min.js"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>



<script>

    $(function () {
        //Datetimepicker plugin
        $('.contactDob').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
        $('.foodLicenceValidity').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });
        $('.drugLicenceValidity').bootstrapMaterialDatePicker({
            format: 'DD/MM/YYYY',
            clearButton: true,
            time: false,
            weekStart: 1
        });

        $('.affiliateId').select2()

        $('#cityId').select2({
            ajax: {
                url: '/city/get',
                dataType: 'json',
                //delay: 250,
                data: function (params) {
                    return {
                        search: params.term,
                        type: 'select2'
                    };
                },
                processResults: function (data, params) {
                    return {
                        results: data
                    };
                },
            },
            placeholder: 'Search for cities',
            minimumInputLength: 2
        });

    });

    $(document).ready(function () {
        // Basic
        $('.dropify').dropify();

        // Translated
        $('.dropify-fr').dropify({
            messages: {
                default: 'Glissez-déposez un fichier ici ou cliquez',
                replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                remove: 'Supprimer',
                error: 'Désolé, le fichier trop volumineux'
            }
        });

        // Used events
        var drEvent = $('#input-file-events').dropify();

        drEvent.on('dropify.beforeClear', function (event, element) {
            return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
        });

        drEvent.on('dropify.afterClear', function (event, element) {
            alert('File deleted');
        });

        drEvent.on('dropify.errors', function (event, element) {
            console.log('Has Errors');
        });

        var drDestroy = $('#input-file-to-destroy').dropify();
        drDestroy = drDestroy.data('dropify')
        $('#toggleDropify').on('click', function (e) {
            e.preventDefault();
            if (drDestroy.isDropified()) {
                drDestroy.destroy();
            } else {
                drDestroy.init();
            }
        })
    });

    function setTwoNumberDecimal(event) {
        this.value = parseFloat(this.value.toFixed(2));
    }



    $('.pinCode').select2({
        placeholder: 'Enter Pincode',
        minimumInputLength: 3,
        required:true,
        ajax: {
            url: '/getcitybypincode',
            dataType: 'json',
            delay: 250,
            data: function (data) {
                return {
                    pincode: data.term // search term
                };
            },
            processResults: function (response) {
                var data = [];
                response.forEach(function(response, index) {
                    data.push({"pincode": response.pincode, "text": response.areaName, "id":response.id});
                });
                return {
                    results:data
                };
            },
            cache: true
        }
    });



    $('.pinCode').on('select2:selecting', function(e) {
        var data =  e.params.args.data;
        var id = data.id;
        // alert(id)
        $.ajax({
            method: 'GET',
            url: '/getcitybyid',
            data: {'id' : id},
            success: function(response){
                console.log(response);
                $('.stateId').val(response.state.id).change();
                $("input[name='stateId']").val(response.state.id);
                $("input[name='cityId']").val(response.id);
                $('.cityId').empty();
                $('.cityId').append("<option value='"+response.id+"'>"+response.areaName+"</option>");
                // $('.cityId').val(response.id).change();
                $('.pinCode').val(response.pincode);
                $("input[name='pinCode']").val(response.pincode);
                if(response.state.alphaCode === "FC")
                {
                    $('.countryId').find('option:contains("OTHER")').attr('selected', 'selected');
                    $("input[name='countryId']").val($('.countryId').val());
                }
                else {
                    $('.countryId').find('option:contains("INDIA")').attr('selected', 'selected');
                    $("input[name='countryId']").val($('.countryId').val());
                }
            },
            error: function(jqXHR, textStatus, errorThrown) { }
        });

    });

    $(document).ready(function() {
        $("#entityRegisterForm").validate();
    });

    $("#userRegisterForm").submit(function(event) {
        var pincode =  $('.pinCode option').length;
        if(pincode === 0 || pincode < 0)
        {
            swal("Please enter  pincode and  select area");
            event.preventDefault();
        }
    });

    // $('#updatePassword').submit(function(event) {
    //     var formData = $(this);
    //     $.ajax({
    //         type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
    //         url         : formData.attr('action'), // the url where we want to POST
    //         data        : formData.serialize(), // our data object
    //         success:function(data){
    //             $("#validation-status").text(data);
    //             swal('success','Password Changed Successfully',data);
    //         },
    //         error:function(data){
    //             console.log("Failed");
    //             $("#validation-status").text(data.responseText);
    //             swal('error','Password Change Failed',data.responseText);
    //         }
    //     });
    //     event.preventDefault();
    // });

    $('.dob').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        clearButton: true,
        time: false,
        weekStart: 1
    });

    $('.joiningDate').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        clearButton: true,
        time: false,
        weekStart: 1
    });

    $('.lastPaidDate').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        clearButton: true,
        time: false,
        weekStart: 1
    });

    $('.anniversaryDate').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        clearButton: true,
        time: false,
        weekStart: 1
    });



</script>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("product-menu");
</script>
</body>
</html>