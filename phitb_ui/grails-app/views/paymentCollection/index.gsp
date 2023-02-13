<%--
  Created by IntelliJ IDEA.
  User: arjun
  Date: 23-01-2023
  Time: 01:24 pm
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Payment Collection</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/fonts/font-awesome/css/font-awesome.css" rel="stylesheet"/>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt=""></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Payment Collection</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Payment Collection</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-md-12">
                <div class="card">
                    <div class="body">
                        <div class="row justify-content-end">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12" id="listContainer">
                <div class="card">
                    <div class="body">
                        <div class="table-responsive">
                            <table id="paymentCollectionTable"
                                   class="table paymentCollectionTable dataTable js-exportable">
                                <thead>
                                <tr><th>Invoices</th></tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<div class="modal fade detailsModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Pay Amount</h4>
                <hr>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <p><strong>Invoice Number: </strong></p>
                    </div>
                    <div class="col-6">
                        <p><span id="invoiceNumber"></span></p>
                    </div>

                </div>
                <div class="row">
                    <div class="col-6">
                        <p><strong>Invoice Amount</strong></p>
                    </div>
                    <div class="col-6">
                        <p><span id="invoiceTotal"></span></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <p><strong>Outstanding Amount</strong></p>
                    </div>
                    <div class="col-6">
                        <p><span id="balance" class="text-primary"></span></p>
                    </div>
                </div>
                <hr>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="amount">Amount: <span style="color: red" class="required-indicator">*</span></label>
                            <input class="form-control" %{--onblur="amoutFormat(this)" onkeyup="setTwoNumberDecimal(this)"--}% pattern="^\d*(\.\d{0,2})?$" type="number" step="0.01" min="0"
                                   value="0.00" id="amount" name="amount" required/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="paymentMode">Payment Mode: <span style="color: red"
                                                                         class="required-indicator">*</span></label>
                            <select onchange="paymentModeChange()" class="form-control" id="paymentMode" name="paymentMode"
                                    required>
                                <g:each in="${paymentModes}" var="pm">
                                    <option value="${pm.id}">${pm.name}</option>
                                </g:each>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="chequeNumber">Cheque Number:</label>
                            <input class="form-control" type="number" id="chequeNumber" name="chequeNumber"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6" id="paymentMethodContainer">
                        <div class="form-group">
                            <label for="paymentMethod">Payment Method: <span style="color: red"
                                                                             class="required-indicator">*</span></label>
                            <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                                <g:each in="${accountMode}" var="am">
                                    <option value="${am.id}">${am.mode}</option>
                                </g:each>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-6" id="depositToContainer">
                        <div class="form-group">
                            <label for="depositTo">Deposit To: <span style="color: red" class="required-indicator">*</span>
                            </label>
                            <select class="form-control" id="depositTo" name="depositTo" required>
                                <g:each in="${accountRegister}" var="ar">
                                    <option value="${ar.id}">${ar.accountName}</option>
                                </g:each>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row" id="payeeBankerContainer">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="payeeBanker">Payee Banker: <span style="color: red"
                                                                         class="required-indicator">*</span></label>
                            <select class="form-control" id="payeeBanker" name="payeeBanker" required>
                                <g:each in="${bank}" var="bk">
                                    <option value="${bk.id}">${bk.bankName}</option>
                                </g:each>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row hidden" id="cardNumberContainer">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="cardNumber">Card Number:</label>
                            <input class="form-control" type="number" id="cardNumber" name="cardNumber"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6" id="paymentDateContainer">
                        <div class="form-group">
                            <label for="paymentDate">Payment Date: <span style="color: red"
                                                                         class="required-indicator">*</span></label>
                            <input class="form-control date" type="date" id="paymentDate" name="paymentDate" required/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group" id="instrumentIdContainer">
                            <label for="instrumentId">Instrument ID:</label>
                            <input class="form-control" type="text" id="instrumentId" name="instrumentId"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="remarks">Remarks: <small style="font-size: 10px;"><span
                                    id="remarksCharacters">0</span>/100</small></label>
                            <textarea rows="2" class="form-control" id="remarks" name="remarks" maxlength="100"></textarea>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 clearfix">
                        <div class="pull-right">
                            <table class="table">
                                <thead></thead>
                                <tbody>
                                <tr><td>Total Due</td><td class="totalDue" id="totalDueOfSelected"
                                                          style="text-align: left; color: red"></td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="saleBillId" id="saleBillId"/>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="dt"
                        onclick="recordPayment();">Yes</button>
            </div>

        </div>
    </div>
</div>

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
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<g:include view="controls/footer-content.gsp"/>
<script>

    $(document).ready(function() {
        paymentCollectionTable();

        $('#remarks').on("input", function(){
            var maxlength = $(this).attr("maxlength");
            var currentLength = $(this).val().length;

            if( currentLength >= maxlength ){
                $("#remarksCharacters").addClass("danger");
                console.log("You have reached the maximum number of characters.");
            }else{
                console.log(maxlength - currentLength + " chars left");
                $("#remarksCharacters").removeClass("danger");
            }
            $("#remarksCharacters").text(currentLength);
            $('#paymentDate').val(new Date().toDateInputValue());
        });
    });

    function paymentCollectionTable() {
        var paymentCollectionTable = $(".paymentCollectionTable").DataTable({
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
          //  dom: 'lfrtip',
           // dom: '<"top"<"dt-left-col"l><"dt-center-col"B><"dt-right-col"f>>rt<"bottom"ip><"clear">',
            oLanguage: {
                sLengthMenu: "_MENU_",
            },
            /*buttons: [
                {
                    extend: 'collection',
                    text: 'Export',
                    buttons: [
                        {
                            'extend': 'excel',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        },
                        {
                            'extend': 'pdf',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        },
                        {
                            'extend': 'print',
                            exportOptions: {columns: ':visible:not(:first-child)'}
                        }
                    ],
                    dropup: true,
                    className: 'dropdown-toggle btn-sm'
                }
            ],*/
            language: {
                searchPlaceholder: "Search Invoice"
            },
            ajax: {
                type: 'GET',
                url: '/payment-collection/get-invoices',
               /* data: {
                    invoiceStatus: invoiceStatus
                },*/
                dataType: 'json',
                dataSrc: function (json) {
                   var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var badgeContainer = ""
                        if(json.data[i].balance === 0)
                        {
                            badgeContainer += "<div class=\"badge badge-success ml-2\">PAID</div>"
                        }
                        else if(json.data[i].balance === json.data[i].invoiceTotal)
                        {
                            badgeContainer += "<div class=\"badge badge-danger ml-2\">UNPAID</div>"
                        }
                        else
                        {
                            badgeContainer += "<div class=\"badge badge-warning ml-2\">PARTIALLY PAID</div>"
                        }

                        var invoiceDetails = "<div class='card'><div class='body'><div class='row'><div class='col-lg-9 col-6'><span class='h5'>#"+json.data[i].invoiceNumber+"</span></div><div class='col-lg-3 col-6'><span class='h5 text-primary pull-right'>₹"+ Number(json.data[i].balance).toFixed(2) + "</span></div>";
                        invoiceDetails += "<div class='col-lg-6 col-6'><p>Invoice Date: "+ dateFormat(json.data[i].orderDate) + "</p></div>";
                        invoiceDetails += "<div class='col-lg-6 col-6'><p class='pull-right'>Due Date: "+  dateFormat(json.data[i].dueDate)+ "</p></div>";
                        invoiceDetails += "<div class='col-lg-12 col-12 d-flex align-items-center'><p class='badge'>Invoice Amount: ₹"+Number(json.data[i].invoiceTotal).toFixed(2) +"</p>"+badgeContainer+"</div><div class='col-lg-12 col-12'><p><button type='button' style='width: 100%;' class='btn btn-sm btn-info btn-round viewbtn' onclick='viewBtnClick(this)' data-id='"+json.data[i].id+"'  data-invoicenumber='"+json.data[i].invoiceNumber+"'  data-invoicetotal='"+json.data[i].invoiceTotal+"'  data-balance='"+json.data[i].balance+"'><i class='zmdi zmdi-file'></i> View</button></p></div></div></div>";
                        return_data.push({
                            'col': invoiceDetails
                        });
                    }
                    return return_data;
                }
            },
            columns: [
                {'data': 'col'}
            ]
        });

        //paymentCollectionTable.buttons().container().appendTo('#saleInvoiceTable_wrapper .col-md-6:eq(0)');
    }

    function dateFormat(dt, time = false)
    {
        dt = dt.replace("T", " ").replace("Z", '');
        var date = new Date(dt);
        if(time)
            return moment(date).format('DD/MM/YYYY hh:mm:ss a');
        else
            return moment(date).format('DD/MM/YYYY');
    }

    function viewBtnClick(btn)
    {
        var id = $(btn).data('id');
        var invoiceNumber = $(btn).data('invoicenumber');
        var invoiceTotal = $(btn).data('invoicetotal');
        var balance = $(btn).data('balance');
        $("#invoiceNumber").text(invoiceNumber);
        $("#invoiceTotal").text("₹" +invoiceTotal);
        $("#balance").text("₹" +balance);
        $("#totalDueOfSelected").text(balance);
        $("#saleBillId").val(id);
        $(".detailsModal").modal('toggle');
    }

    function paymentModeChange() {
        var paymentMode = $("#paymentMode :selected").text();
        if(paymentMode === "CARD")
        {
            $("#cardNumberContainer").removeClass("hidden");
            $("#instrumentIdContainer").addClass("hidden");
            $("#payeeBankerContainer").addClass("hidden");
            $("#depositToContainer").removeClass("hidden");
            $("#paymentMethodContainer").removeClass("hidden");

            $("#paymentDateContainer").removeClass("col-md-6");
            $("#paymentDateContainer").addClass("col-md-12");
        }
        else if(paymentMode === "BANK")
        {
            $("#cardNumberContainer").addClass("hidden");
            $("#instrumentIdContainer").removeClass("hidden");
            $("#payeeBankerContainer").removeClass("hidden");
            $("#depositToContainer").removeClass("hidden");
            $("#paymentMethodContainer").removeClass("hidden");

            $("#paymentDateContainer").removeClass("col-md-12");
            $("#paymentDateContainer").addClass("col-md-6");
        }
        else
        {
            //cash
            $("#cardNumberContainer").addClass("hidden");
            $("#instrumentIdContainer").addClass("hidden");
            $("#payeeBankerContainer").addClass("hidden");
            $("#depositToContainer").addClass("hidden");
            $("#paymentMethodContainer").addClass("hidden");

            $("#paymentDateContainer").removeClass("col-md-6");
            $("#paymentDateContainer").addClass("col-md-12");

        }
    }

    function recordPayment() {
        var totalDueOfSelected = parseFloat2Decimal($("#totalDueOfSelected").text());
        if(totalDueOfSelected === 0)
        {
            Swal.fire({
                title: "Invoice settled already!",
                text: "There is no due for this invoice",
                showDenyButton: false,
                showCancelButton: false,
                showCloseButton: true,
                showConfirmButton: true
            });
            return
        }
        var spinner = "<div class=\"col-md-12\">\n" +
            "                    <div class=\"text-center\">\n" +
            "                        <div class=\"spinner-border\" role=\"status\">\n" +
            "                            <span class=\"sr-only\">We are recording payment information please wait!</span>\n" +
            "                        </div>\n" +
            "<p>We are recording payment information please wait!</p>\n" +
            "                    </div>\n" +
            "                </div>";
        var processingSwal = Swal.fire({
            title: "Recording Payment..",
            html: spinner,
            showDenyButton: false,
            showCancelButton: false,
            showCloseButton: false,
            showConfirmButton: false
        });
        var amount = parseFloat2Decimal($("#amount").val());
        var paymentMode = $("#paymentMode").val();
        var paymentMethod = $("#paymentMethod").val();
        var depositTo = $("#depositTo").val();
        var payeeBanker = $("#payeeBanker").val();
        var cardNumber = $("#cardNumber").val();
        var paymentDate = $("#paymentDate").val();
        var instrumentId = $("#instrumentId").val();
        var remarks = $("#remarks").val();
        var saleBillId = $("#saleBillId").val();
        var saleReturnIds = $("#saleReturnIds").val();
        var creditsApplied = parseFloat2Decimal($("#creditsApplied").text());
        var type = "PAYMENT_COLLECTION";
        var chequeNumber = $("#chequeNumber").val();

        if(paymentDate == null || paymentDate === "")
        {
            processingSwal.close();

            Swal.fire({
                title: "Error",
                html: "Please select payment date",
                icon: 'error'
            });
            return;
        }

        if((amount + creditsApplied) >totalDueOfSelected)
        {
            processingSwal.close();
            Swal.fire({
                title: "Error",
                html: "Payment amount should be less than Due Amount",
                icon: 'error'
            });
            return;
        }


        $.ajax({
            url: "/sale-bill/record-payment",
            method: "POST",
            data:{
                amount: amount,
                paymentMode: paymentMode,
                paymentMethod: paymentMethod,
                depositTo: depositTo,
                payeeBanker: payeeBanker,
                cardNumber: cardNumber,
                chequeNumber: chequeNumber,
                paymentDate: paymentDate,
                instrumentId: instrumentId,
                remarks: remarks,
                saleBillId: saleBillId,
                saleReturnIds: saleReturnIds,
                creditsApplied: creditsApplied,
                type:type
            },
            success: function(data)
            {
                processingSwal.close();
                Swal.fire({
                    title: "Success!",
                    html: "Payment recorded for this invoice",
                    icon: 'success'
                });
                paymentCollectionTable();
                $(".detailsModal").modal('hide');

            },
            error: function (data) {
                processingSwal.close();
                var text = "Please try later!";
                if(data !== undefined)
                    text = data.responseText;
                Swal.fire({
                    title: "Error!",
                    html: text,
                    icon: 'error'
                });
            }
        })
    }

    function parseFloat2Decimal(num)
    {
        if(!isNaN(num)) {
            num = Number(num);
            num = Math.round(num * 1e2) / 1e2;
            return num;
        }
    }

</script>
</body>
</html>