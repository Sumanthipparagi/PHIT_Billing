<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

    <title>:: PharmIt :: Recipt</title>
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

    <style>
    input.chk-btn {
        display: none;
    }

    input.chk-btn + label {
        border: 1px solid grey;
        background: ghoswhite;
        padding: 5px 8px;
        cursor: pointer;
        border-radius: 5px;
    }

    input.chk-btn:not(:checked) + label:hover {
        box-shadow: 0px 1px 3px;
    }

    input.chk-btn + label:active,
    input.chk-btn:checked + label {
        box-shadow: 0px 0px 3px inset;
        background: #eee;
    }

    .table td, .table th {
        padding: 0.1rem;
    }

    .btn-sm {
        font-size: 10px;
        border-radius: 0.1875rem;
        padding: 4px 9px;
    }

    .tableFixHead {
        overflow: auto;
        height: 400px;
    }

    .tableFixHead thead th {
        position: sticky;
        top: 0;
        z-index: 1;
    }

    /* Just common table stuff. Really. */
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px 16px;
    }

    th {
        background: #eee;
    }


    </style>
</head>

<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48"
                                 alt="Alpino"></div>

        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Recipt</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="index.html"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="/recipt">Accounts</a></li>
                        <li class="breadcrumb-item active">Recipt</li>
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
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Inline</strong> Layout</h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu dropdown-menu-right">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);" class="boxs-close">Delete</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
                    <div class="body">
                        <form action="/recipt" id="form_validation" method="POST" role="form"
                              class="entityRegisterForm" enctype="multipart/form-data">
                            <div class="row clearfix">
                                <div class="col-lg-12 form-group  form-float">
                                    <label for="date">
                                        Date:
                                    </label>
                                    <input type="date" id="date" class="date" name="Recipt Date"
                                           placeholder="Recipt Date" readonly
                                           required/>
                                </div>

                                <div class="col-lg-6">
                                    <label for="customer">
                                        Customer
                                    </label>
                                    <select class="form-control show-tick customer" name="customer"
                                            id="customer" onchange="getAddress(this.value)" required>
                                        <option value="">-- Please select --</option>
                                        <g:each var="e" in="${entity}">
                                            <option value="${e.id}"
                                                    data-type="${e.entityType.id}">${e.entityName} - ${e.id}</option>
                                        </g:each>
                                    </select>

                                    <div id="caddress" class="mt-2"></div>
                                </div>

                                <div class="col-lg-6">
                                    <label for="bank">
                                        Bank
                                    </label>
                                    <select class="form-control show-tick bank" name="bank"
                                            id="bank" required>
                                        <option value="">-- Please select --</option>
                                        <g:each var="b" in="${bank}">
                                            <option value="${b.id}">${b.bankName}</option>
                                        </g:each>
                                    </select>
                                </div>

                                <div class="col-lg-3 form-group  form-float">
                                    <label for="amountPaid">
                                        Amount
                                    </label>
                                    <input type="number" id="amountPaid" class="amountPaid form-control" name="Amount"
                                           placeholder="Amount" min="1" step="any"
                                           required/>
                                </div>

                                <div class="col-lg-3 form-group  form-float">
                                    <label for="branch">
                                        Branch
                                    </label>
                                    <input type="text" id="branch" class="branch form-control"
                                           name="Branch"
                                           placeholder="Branch"
                                           required/>
                                </div>


                                <div class="col-lg-3 form-group  form-float">
                                    <label for="chequeNumber">
                                        Cheque Number
                                    </label>
                                    <input type="number" id="chequeNumber" class="chequeNumber form-control"
                                           name="Cheque Number"
                                           placeholder="Cheque Number"
                                           required/>
                                </div>

                                <div class="col-lg-3 form-group  form-float">
                                    <label for="chequeDate">
                                        Cheque Date
                                    </label>
                                    <input type="date" id="chequeDate" class="chequeDate form-control"
                                           name="Cheque Date"
                                           placeholder="Cheque Number"
                                           required/>
                                </div>


                                <div class="col-lg-3 form-group  form-float">
                                    <label for="chequeType">
                                        Cheque Type
                                    </label>
                                    <input type="text" id="chequeType" class="chequeType form-control"
                                           name="Cheque Type"
                                           placeholder="Cheque Type"
                                           required/>
                                </div>

                                <div class="col-lg-3 form-group  form-float">
                                    <label for="draweeBank">
                                        Drawee Bank
                                    </label>
                                    <input type="text" id="draweeBank" class="draweeBank form-control"
                                           name="Drawee Bank"
                                           placeholder="Drawee Bank"
                                           required/>
                                </div>



                                <div class="col-lg-6 form-group  form-float">
                                    <label for="note">
                                        Remark / Note
                                    </label>
                                    <input type="text" id="note" class="note form-control"
                                           name="note"
                                           placeholder="Remark / Note"
                                           required/>
                                </div>

                                <div class="col-lg-6 form-group  form-float">
                                    <label for="total">
                                        Total Cheque:
                                    </label>
                                    <input type="text" style="margin-left: 43px;" id="tc" class="tc" name="tc"
                                           placeholder="0.00">
                                    <br>
                                    <label for="total">
                                        Total Bill Amount:
                                    </label>
                                    <input style="margin-left: 18px;" type="text" id="tba" class="tba" name="tba"
                                           placeholder="0.00">
                                    <br>

                                    <span style="margin-left: 134px;">====================</span>
                                    <br>
                                    <input style="margin-left: 134px;" type="text" id="total" class="total" name="total"
                                           placeholder="0.00">
                                </div>
                                %{--                                <div class="col-lg-6 form-group  form-float">--}%
                                %{--                                    <label for="cardNumber">--}%
                                %{--                                        Card Number--}%
                                %{--                                    </label>--}%
                                %{--                                    <div class="demo-masked-input">--}%
                                %{--                                    <input type="number" id="cardNumber" class="credit-card form-control"--}%
                                %{--                                           name="Card Number"--}%
                                %{--                                           placeholder="Card Number"--}%
                                %{--                                           required/>--}%
                                %{--                                    </div>--}%
                                %{--                                </div>--}%

                                <div class="container">
                                    <div class="row">
                                        <div class="col-lg-6">Settled Vocher</div>

                                        <div class="col-lg-6">Unsettled Vocher</div>

                                        <div class="col-lg-6">
                                            <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                            max-height:300px;">
                                                <table class="table table-striped" id="table1" border="1">
                                                    <tr>
                                                        <thead>
                                                        <th>Doc.Type</th>
                                                        <th>Document No.</th>
                                                        <th>Document Date</th>
                                                        <th>Amount</th>
                                                        <th style="width: 2%"></th>
                                                        </thead>
                                                    </tr>
                                                    <tbody class="settledVocher">

                                                    </tbody>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>Total:&nbsp;<span class="total_bal_s"></span></td>

                                                </table>
                                            </div>
                                        </div>


                                        <div class="col-lg-6">
                                            <div class="tab tableFixHead" style="width:100%;overflow:auto;
                                            max-height:300px;">
                                                <table id="table2" class="table table-striped"
                                                       border="1">
                                                    <tr>
                                                        <thead>
                                                        <th></th>
                                                        <th>Doc.Type</th>
                                                        <th>Yr</th>
                                                        <th>Document Date</th>
                                                        <th>Amount</th>
                                                        </thead>
                                                    </tr>
                                                    <tbody class="unsettledVocher">
                                                    </tbody>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>Total:&nbsp;<span class="total_bal"></span></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <input type="hidden" name="status" value="1">
                                <input type="hidden" name="syncStatus" value="1">
                                <input type="hidden" name="createdUser" value="1">
                                <input type="hidden" name="modifiedUser" value="1">

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
<asset:javascript src="/themeassets/bundles/mainscripts.bundle.js"/>
<asset:javascript src="/themeassets/js/pages/tables/jquery-datatable.js"/>
<asset:javascript src="/themeassets/js/pages/ui/dialogs.js"/>
<asset:javascript src="/themeassets/plugins/sweetalert/sweetalert.min.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>

<script>
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    var today = year + "-" + month + "-" + day;
    document.getElementById("date").value = today;

    var $demoMaskedInput = $('.demo-masked-input');
    $demoMaskedInput.find('.credit-card').inputmask('9999 9999 9999 9999', {placeholder: '____ ____ ____ ____'});

    //
    // function tab1_To_tab2() {
    //     var table1 = document.getElementById("table1"),
    //         table2 = document.getElementById("table2"),
    //         checkboxes = document.getElementsByName("check-tab1");
    //     console.log("Val1 = " + checkboxes.length);
    //     for (var i = 0; i < checkboxes.length; i++)
    //
    //         if (checkboxes[i].checked) {
    //             // create new row and cells
    //             var newRow = table2.insertRow(table2.length),
    //                 cell1 = newRow.insertCell(0),
    //                 cell2 = newRow.insertCell(1),
    //                 cell3 = newRow.insertCell(2),
    //                 cell4 = newRow.insertCell(3);
    //             // add values to the cells
    //             cell1.innerHTML = table1.rows[i + 1].cells[0].innerHTML;
    //             cell2.innerHTML = table1.rows[i + 1].cells[1].innerHTML;
    //             cell3.innerHTML = table1.rows[i + 1].cells[2].innerHTML;
    //             cell4.innerHTML =
    //                 "<label for='' style='background: green;padding: 3px;'></label><input type='checkbox' name='check-tab2' onclick='tab2_To_tab1();'>";
    //
    //             // remove the transfered rows from the first table [table1]
    //             var index = table1.rows[i + 1].rowIndex;
    //             table1.deleteRow(index);
    //             // we have deleted some rows so the checkboxes.length have changed
    //             // so we have to decrement the value of i
    //             i--;
    //             console.log(checkboxes.length);
    //         }
    // }
    //
    //
    // function tab2_To_tab1() {
    //     var table1 = document.getElementById("table1"),
    //         table2 = document.getElementById("table2"),
    //         checkboxes = document.getElementsByName("check-tab2");
    //     console.log("Val1 = " + checkboxes.length);
    //     for (var i = 0; i < checkboxes.length; i++)
    //         if (checkboxes[i].checked) {
    //             // create new row and cells
    //             var newRow = table1.insertRow(table1.length),
    //                 cell1 = newRow.insertCell(0),
    //                 cell2 = newRow.insertCell(1),
    //                 cell3 = newRow.insertCell(2),
    //                 cell4 = newRow.insertCell(3);
    //             // add values to the cells
    //             cell1.innerHTML = table2.rows[i + 1].cells[0].innerHTML;
    //             cell2.innerHTML = table2.rows[i + 1].cells[1].innerHTML;
    //             cell3.innerHTML = table2.rows[i + 1].cells[2].innerHTML;
    //             cell4.innerHTML = "<input type='checkbox' name='check-tab1' onclick='tab1_To_tab2();'>";
    //
    //             // remove the transfered rows from the second table [table2]
    //             var index = table2.rows[i + 1].rowIndex;
    //             table2.deleteRow(index);
    //             // we have deleted some rows so the checkboxes.length have changed
    //             // so we have to decrement the value of i
    //             i--;
    //             console.log(checkboxes.length);
    //         }
    // }
    // $('tbody').on('click','tr',function(){
    //     myParent=$(this).closest('table').attr('id');
    //     if(myParent == "table1") $('#table2 tbody').append('<tr>'+$(this).html()+'</tr>');
    //     else $('#table1 tbody').append('<tr>'+$(this).html()+'</tr>');
    //     $(this).remove();
    // })


    function getAddress(id) {
        $.ajax({
            type: 'GET',
            url: '/getbyentity/' + id,
            dataType: 'json',
            success: function (data) {
                getSaleBillByCustomer(id)
                getsettledSaleBillByCustomer(id)
                var trHTML = '';
                trHTML += '<p><b>' + data.entityName + '</b><br>' + data.addressLine1 + '' + data.addressLine2 + '</p>';
                $('#caddress').html(trHTML);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });

    }


    function getSaleBillByCustomer(id) {
        $.ajax({
            type: 'GET',
            url: '/getallsalebillbycustomer/' + id,
            dataType: 'json',
            success: function (data) {
                var trHTML = '';
                trHTML += '';
                var invoice = "INV"
                if(data.length!==0)
                {
                    var total_bal = data.map(data => data.balance).reduce((acc, amount) => acc + amount,0);
                }
                else {
                     total_bal = 0;
                }
                $('.total_bal').text(parseFloat(total_bal));

                $.each(data, function (key, value) {
                        trHTML +=
                            '<tr id="'+value.id+'"><td><button type="button" data-id="'+value.id+'"  data-custId="'+value.customerId+'" class="btn-sm btn-primary" id="settled"><-</button></td><td>' + invoice +
                            '</td><td>' + value.financialYear +
                            '</td><td>' + moment(value.dateCreated).format('DD-MM-YYYY') +
                            '</td><td>' + value.balance +
                            '</td></tr>';
                });
                $('.unsettledVocher').html(trHTML);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }


    function getsettledSaleBillByCustomer(id) {
        $.ajax({
            type: 'GET',
            url: '/getallsalesettledcustomer/'+id,
            dataType: 'json',
            success: function (data) {
                var trHTML = '';
                trHTML += '';
                var invoice = "INV"
                var total_bal_s = data.map(data => data.balance).reduce((acc, amount) => acc + amount,0);
                $('.total_bal_s').text(parseFloat(total_bal_s));
                $('.tba').val(parseFloat(total_bal_s));
                $.each(data, function (key, value) {
                    trHTML +=
                        '<tr id="'+value.id+'"><td>' + invoice +
                        '</td><td>' + value.financialYear +
                        '</td><td>' + moment(value.dateCreated).format('DD-MM-YYYY') +
                        '</td><td>' + value.balance +
                        '</td><td><button type="button" data-id="'+value.id+'"  class="btn-sm btn-primary" id="unsettled">-></button></td></tr>';
                });
                $('.settledVocher').html(trHTML);
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }

    var input = document.getElementById("tc");
    input.addEventListener("keydown", function (e) {
        if(e.target.value!==0) {
            if (e.key === "Enter") {
                $(".total").val(total())
            }
        }
    });
    function total()
    {
        // Capture the entered values of two input boxes
        var my_input1 = document.getElementById('tc').value;
        var my_input2 = document.getElementById('tba').value;
        return parseInt(my_input1) + parseInt(my_input2)
    }

    $(document).on('click','#settled', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        var custId = $(this).attr('data-custId');
        var url = '/settledvocher/'+id;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table2 tr#'+id).remove();
                getSaleBillByCustomer(custId)
                getsettledSaleBillByCustomer(custId)
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });


    $(document).on('click','#unsettled', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        var custId = $(this).attr('data-custId');
        var url = '/unsettledvocher/'+id;
        var type = 'GET';
        $.ajax({
            url: url,
            type: type,
            contentType: false,
            processData: false,
            success: function () {
                $('table#table1 tr#'+id).remove();
                getSaleBillByCustomer(custId);
                getsettledSaleBillByCustomer(custId)
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    });

</script>
</body>
</html>