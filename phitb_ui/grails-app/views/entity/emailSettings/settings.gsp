<%@ page import="phitb_ui.Constants" %>
<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt ::  Email Settings</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main-2.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/plugins/select-2-editor/select2.min.css" rel="stylesheet"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>

    <style>
    table {
        overflow-y: scroll;
        display: block;
    }

    th {
        color: white;
        background: #22252b;
    }

    .hidden {
        display: none;
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
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Entity Settings</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Email Settings</li>
                    </ul>
                </div>

%{--                <div class="col-lg-7 col-md-7 col-sm-12">--}%
%{--                    <div class="input-group m-b-0">--}%
%{--                        <input type="text" class="form-control" placeholder="Search...">--}%
%{--                        <span class="input-group-addon">--}%
%{--                            <i class="zmdi zmdi-search"></i>--}%
%{--                        </span>--}%
%{--                    </div>--}%
%{--                </div>--}%
            </div>
        </div>
        <!-- Basic Examples -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h2>${entity.entityName}<br><span style="font-size: 15px;">${entity.entityType.name}</span></h2>

                <div class="card">
                    <div class="header">
                        %{--<h2><strong>Email Settings</strong></h2>--}%
                    </div>

                    <div class="body">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#home"><i
                                    class="zmdi zmdi-settings"></i> Email Settings</a></li>
                            <li class="nav-item"><a id="logButton" class="nav-link" data-toggle="tab" href="#logs"><i
                                    class="zmdi zmdi-book"></i> Email Usage Logs</a></li>
                        <li class="nav-item"><a  class="nav-link"  href="/email-config?id=${params.id}"><i
                                class="zmdi zmdi-settings"></i>Email Configs</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane in active" id="home">
                                <form action="/email-settings/save" method="post" id="emailSettings">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <p>You will be billed for emails if <strong>default</strong> e-mail service is selected.
                                                </p>
                                                <label for="emailService"><strong>Email Service</strong></label>
                                                <select onchange="emailServiceChanged()" class="form-control show-tick"
                                                        name="emailService" id="emailService">
                                                    <option
                                                        <g:if test="${emailSettings == null || emailSettings?.active == false}">selected</g:if>>DISABLED</option>
                                                    <option
                                                        <g:if test="${emailSettings?.emailService == "DEFAULT" && emailSettings?.active == true}">selected</g:if>>DEFAULT</option>
                                                    <option
                                                        <g:if test="${emailSettings?.emailService == "CUSTOM" && emailSettings?.active == true}">selected</g:if>>CUSTOM</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hidden" id="customMailSettings" style="border-top: #0D0A0A;">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="smtpServer">SMTP Server</label>
                                                    <input value="${emailSettings?.smtpServer}" maxlength="500"
                                                           class="form-control"
                                                           id="smtpServer" name="smtpServer" type="text"
                                                           placeholder="Enter SMTP Server"/>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="smtpPort">SMTP Port</label>
                                                    <input maxlength="500" value="${emailSettings?.smtpPort}"
                                                           class="form-control"
                                                           id="smtpPort" placeholder="ex: 587, 25" name="smtpPort"
                                                           type="text"/>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="senderMail">Sender Mail</label>
                                                    <input class="form-control" value="${emailSettings?.senderMail}"
                                                           id="senderMail"
                                                           name="senderMail"
                                                           type="text" maxlength="100"
                                                           placeholder="ex: contact@company.com"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group" style="padding: 10px;">
                                                    <div class="checkbox">
                                                        <input class="form-control"
                                                               <g:if test="${emailSettings?.authenticationRequired}">checked</g:if>
                                                               id="authenticationRequired" name="authenticationRequired"
                                                               type="checkbox"/>
                                                        <label for="authenticationRequired">Authentication Required?</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row hidden" id="authenticationRequiredContainer">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="smtpUsername">Username</label>
                                                    <input class="form-control" value="${emailSettings?.smtpUsername}"
                                                           id="smtpUsername" placeholder="Enter SMTP Username"
                                                           name="smtpUsername"
                                                           type="text"
                                                           maxlength="500"/>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="smtpPassword">Password</label>
                                                    <input class="form-control" id="smtpPassword" type="password"
                                                           name="smtpPassword"
                                                           maxlength="500" value="${emailSettings?.smtpPassword}"/>
                                                </div>
                                            </div>

                                            <div class="col-md-4" id="authenticationRequiredType">
                                                <label for="encryptionType"><strong>Encryption Type</strong></label>
                                                <select class="form-control show-tick" name="encryptionType"
                                                        id="encryptionType">
                                                    <option
                                                        <g:if test="${emailSettings?.encryptionType == Constants.EMAIL_ENCRYPTION_TYPE_SSL}">selected</g:if>>${Constants.EMAIL_ENCRYPTION_TYPE_SSL}</option>
                                                    <option
                                                        <g:if test="${emailSettings?.encryptionType == Constants.EMAIL_ENCRYPTION_TYPE_STARTLS}">selected</g:if>>${Constants.EMAIL_ENCRYPTION_TYPE_STARTLS}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <input value="${emailSettings?.id}" name="emailSettingsId" type="hidden"/>
                                            <input value="${params.id}" name="entityId" type="hidden"/>
                                            <button type="submit"
                                                    class="btn btn-default btn-round waves-effect"
                                                    style="background-color: green;">SUBMIT</button>
                                        </div>
                                    </div>
                                </form>

                                <div class="row mt-5 hidden" id="testMailContainer">
                                    <div class="col-md-12">
                                        <h6>Test Email</h6>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Send test mail to:</label>
                                                    <input type="text" name="testMailTo" id="testMailTo" class="form-control"/>
                                                    <button onclick="sendTestMail()" class="btn btn-info btn-round"><i
                                                            class="zmdi zmdi-mail-send"></i> Send</button>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <p class="mt-4 pt-2" id="testMailResult"></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div role="tabpanel" class="tab-pane" id="logs">
                                <p>Log of all the emails sent by you is listed here:</p>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="table-responsive">
                                            <table class="table table-bordered emailLogTable">
                                                <thead style="width: 100%;">
                                                <tr>
                                                    <th>Sent To</th>
                                                    <th>Date</th>
                                                    <th>Subject</th>
                                                </tr>
                                                </thead>
                                                <tbody style="width: 100%;">
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

%{--<g:include view="controls/entity/add-entity-settings.gsp"/>--}%
<g:include view="controls/delete-modal.gsp"/>

<!-- Jquery Core Js -->
<asset:javascript src="/themeassets/bundles/libscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/vendorscripts.bundle.js"/>
<asset:javascript src="/themeassets/bundles/datatablescripts.bundle.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/dataTables.buttons.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.bootstrap4.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.colVis.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.html5.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-datatable/buttons/buttons.print.min.js"/>
<asset:javascript src="/themeassets/bundles/mainscripts-2.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/select-2-editor/select2.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.all.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("settings-menu");
</script>

<script>

    $(document).ready(function () {
        emailServiceChanged();
    });

    $('#emailSettings').submit(function (event) {
        event.preventDefault();
        var formData = $(this);
        $.ajax({
            type: 'POST',
            url: "/email-settings/save",
            data: formData.serialize(),
            beforeSend: function () {
                beforeSendSwal = Swal.fire({
                    // title: "Loading",
                    html: '<img src="${assetPath(src: "/themeassets/images/1476.gif")}" width="100" height="100"/>',
                    showDenyButton: false,
                    showCancelButton: false,
                    showConfirmButton: false,
                    allowOutsideClick: false,
                    background: 'transparent'
                });
            },
            success: function (data) {
                Swal.fire("Email settings saved.")
            },
            error: function (data) {
                console.log("Email settings saving failed");
            }
        });
    });

    function emailServiceChanged() {
        var emailService = $("#emailService").val();
        if (emailService == "CUSTOM") {
            $("#customMailSettings").removeClass("hidden");
        } else
            $("#customMailSettings").addClass("hidden");

        if (emailService != "DISABLED") {
            $("#testMailContainer").removeClass("hidden");
        } else
            $("#testMailContainer").addClass("hidden");

        var authenticationRequired = $("#authenticationRequired").is(":checked");
        if (authenticationRequired) {
            $("#authenticationRequiredContainer").removeClass("hidden");
        } else {
            $("#authenticationRequiredContainer").addClass("hidden");
        }
    }

    $("#authenticationRequired").change(function () {
        if (this.checked) {
            $("#authenticationRequiredContainer").removeClass("hidden");
        } else {
            $("#authenticationRequiredContainer").addClass("hidden");
        }
    });

    function emailLogTable() {
        $(".emailLogTable").DataTable({
            "order": [[0, "desc"]],
            sPaginationType: "simple_numbers",
            responsive: {
                details: false
            },
            destroy: true,
            autoWidth: false,
            bJQueryUI: true,
            sScrollX: "100%",
            info: true,
            processing: true,
            serverSide: true,
            language: {
                searchPlaceholder: "Search Email Log"
            },
            ajax: {
                type: 'GET',
                url: '/email-log/datatable',
                dataType: 'json',
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        return_data.push({
                            'sentto': json.data[i].sentTo,
                            'date': dateFormat(json.data[i].dateCreated),
                            'subject': json.data[i].emailSubject,
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'sentto', 'width': '20%'},
                {'data': 'date', 'width': '20%'},
                {'data': 'subject', 'width': '60%'}
            ]
        });
    }

    $("#logButton").on("click", function () {
        emailLogTable();
    });

    function sendTestMail() {
        var testMailTo = $("#testMailTo").val();
        if(testMailTo)
        {
            $("#testMailResult").html("<span style='color: green;'>Sending..</span>");
            $.ajax({
                url: "email-settings/testmail?to="+testMailTo,
                method: "GET",
                success: function(){
                    $("#testMailResult").html("<span style='color: green;'><i class='zmdi zmdi-check'></i> Test Mail Sent</span>");
                },
                error: function(){
                    $("#testMailResult").html("<span style='color: red;'><i class='zmdi zmdi-close'></i> Sending test mail failed, please check e-mail settings.</span>");
                }
            })
        }
    }

    function dateFormat(date)
    {
        var pattern = "DD/MM/YYYY hh:mm:ss a";
        return moment(date).format(pattern);
    }
</script>
</body>
</html>


