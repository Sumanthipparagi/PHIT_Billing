<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Receipts</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet  rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert2/dist/sweetalert2.css"/>
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet" />
    <asset:stylesheet  src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />

    <style>

/*    div.dataTables_scrollBody table tbody  td {*/
/*        border-top: none;*/
/*        padding: 0.9px;*/
/*        text-align: center;*/
/*        border-collapse: unset!important;*/
/*    }*/

    .editbtn
    {
        padding: 1px 9px;
    }
    .deletebtn
    {
        padding: 1px 9px;
    }

    /*tbody td {*/
    /*    padding: 0px;*/
    /*}*/

    </style>

</head>
<body class="theme-black">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img src="${assetPath(src: '/themeassets/images/logo.svg')}" width="48" height="48" alt="PharmIT"></div>
        <p>Please wait...</p>
    </div>
</div>
<g:include view="controls/sidebar.gsp"/>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">
            <div class="row clearfix">
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <h2>Receipt </h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Receipt</li>
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
            <div class="col-lg-12">
                <div class="card">
                    %{--                    <div class="header">--}%
                    %{--                        <h2><strong>Basic</strong> Examples </h2>--}%
                    %{--                        <ul class="header-dropdown">--}%
                    %{--                            <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="zmdi zmdi-more"></i> </a>--}%
                    %{--                                <ul class="dropdown-menu slideUp">--}%
                    %{--                                    <li><a href="javascript:void(0);">Action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Another action</a></li>--}%
                    %{--                                    <li><a href="javascript:void(0);">Something else</a></li>--}%
                    %{--                                </ul>--}%
                    %{--                            </li>--}%
                    %{--                            <li class="remove">--}%
                    %{--                                <a role="button" class="boxs-close"><i class="zmdi zmdi-close"></i></a>--}%
                    %{--                            </li>--}%
                    %{--                        </ul>--}%
                    %{--                    </div>--}%
%{--                    <div class="header">--}%
%{--                        <button type="button" class="btn btn-round btn-primary m-t-15 addbtn" data-toggle="modal"--}%
%{--                                data-target="#adddayEndModal"><font style="vertical-align: inherit;"><font--}%
%{--                                style="vertical-align: inherit;">Add Day End</font></font></button>--}%
%{--                    </div>--}%
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped table-hover customerGroupTable dayEndTable">
                                <thead>
                                <tr>
                                    %{--
                                                                   <th style="width: 20%">ID</th>--}%
                                    <th style="width: 20%">Action</th>
                                    <th style="width: 20%">Recipt Date</th>
                                    <th style="width: 20%">Receipt Id</th>
                                    <th style="width: 20%">Received From</th>
                                    <th style="width: 20%">Deposit To</th>
                                    <th style="width: 20%">Financial Year</th>
                                    <th style="width: 20%">Amount paid</th>
                                    <th style="width: 20%">Approved Status</th>
%{--                                    <th style="width: 20%">Bank</th>--}%

                                </tr>
                                </thead>
                                %{--                                <tfoot>--}%
                                %{--                                <tr>--}%
                                %{--                                    <th>ID</th>--}%
                                %{--                                    <th>Name</th>--}%
                                %{--                                    <th>entityRegister ID</th>--}%
                                %{--                                    <th>Action</th>--}%
                                %{--                                </tr>--}%
                                %{--                                </tfoot>--}%
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


<g:include view="controls/entity/add-day-end.gsp"/>
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
<asset:javascript src="/themeassets/plugins/sweetalert2/dist/sweetalert2.js"/>
<asset:javascript src="/themeassets/plugins/jquery-inputmask/jquery.inputmask.bundle.js"/>
<asset:javascript src="/themeassets/plugins/momentjs/moment.js"/>
<asset:javascript src="/themeassets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"/>
<asset:javascript src="/themeassets/js/pages/forms/basic-form-elements.js"/>
<asset:javascript src="/themeassets/plugins/icons/all.js"/>

<script>

    var dayendtable;
    var id = null;
    $(function () {
        receiptTable();

    });

    function receiptTable() {
        dayendtable = $(".dayEndTable").DataTable({
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
                searchPlaceholder: "Search Receipt"
            },
            ajax: {
                type: 'GET',
                url: '/receipt-list/datatable',
                dataType: 'json',
                data: {
                    customer: "ALL",
                    fromDate:"",
                    toDate:"",
                },
                dataSrc: function (json) {
                    var return_data = [];
                    for (var i = 0; i < json.data.length; i++) {
                        var cancelInvoice = "";
                        var date = new Date(json.data[i].date);
                        var pd = new Date(json.data[i].paymentDate);
                        if (json.data[i].approvedStatus !== "CANCELLED" && json.data[i].approvedStatus !== "APPROVED" && ${session.getAttribute("financialYearValid")}) {
                            cancelInvoice = '<a class="btn btn-sm btn-info" title="Cancel" onclick="cancelReceipt(' + json.data[i].id +')" href="#"><i class="fa fa-times"></i></a>';
                        }
                        else
                        {
                            cancelInvoice =  '';

                        }
                        var editbtn = ""
                        if(${session.getAttribute("financialYearValid")})
                        {
                            editbtn = ' <button type="button" data-id="'+json.data[i].id+'" data-recievedfrom="'+json.data[i].receivedFrom.id+'" class="print btn btn-sm btn-warning editbtn"><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">print</font></font></i></button>'
                        }

                        return_data.push({
                            'action': editbtn+"  "+cancelInvoice,
                            'id': json.data[i].receiptId,
                            'date': moment(date).format('DD/MM/YYYY'),
                            'fy': json.data[i].financialYear,
                            'amountPaid': json.data[i].amountPaid.toFixed(2),
                            'approvedStatus': json.data[i].approvedStatus,
                            'receivedFrom': json.data[i].receivedFrom.entityName,
                            'depositTo': json.data[i]?.deposit === "NA" ? '' : json.data[i]?.deposit?.accountName,
                            'pd': moment(pd).format('DD/MM/YYYY'),

                        });
                    }
                    return return_data;
                }
            },
            columns: [
                // {'data': 'date', 'width': '20%'},
                {'data': 'action', 'width': '20%'},
                {'data': 'date', 'width': '20%'},
                {'data': 'id', 'width': '20%'},
                {'data': 'receivedFrom', 'width': '20%'},
                {'data': 'depositTo', 'width': '20%'},
                {'data': 'fy', 'width': '20%'},
                {'data': 'amountPaid', 'width': '20%'},
                {'data': 'approvedStatus', 'width': '20%'}
                // {'data': 'bank', 'width': '20%'},

            ]
        });
    }

    $(".dayEndForm").submit(function (event) {

        //disable the default form submission
        event.preventDefault();

        //grab all form data
        var formData = new FormData(this);
        console.log(formData);

        var url = '';
        var type = '';
        if (id) {
            url = '/day-end-master/update/' + id;
            type = 'POST'
        } else {
            url = '/day-end-master';
            type = 'POST'
        }

        console.log(type);
        $.ajax({
            url: url,
            type: type,
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                swal("Success!", "CCm Submitted Successfully", "success");
                receiptTable();
                $('#adddayEndModal').modal('hide');
            },
            error: function () {
                swal("Error!", "Something went wrong", "error");

            }
        });
    });

    $(document).on("click", ".addbtn", function () {
        $(".dayEndTitle").text("Add Day End Master")
        $(".dayEndForm")[0].reset();
        id = null
    });

    $(document).on("click", ".editbtn", function () {
        id = $(this).data('id');
        $(".date").val($(this).attr('data-date'));
        $(".endTime").val($(this).attr('data-endTime'));
        $(".entity").val($(this).attr('data-entityRegister'));
        $("#entityTypeId").val($(this).attr('data-entitytype')).change()
        $(".customerGroupTitle").text("Update Day End");
    });

    $('.entity').change(function(){
        var type = $('option:selected', this).attr('data-type');
        $(".entityType").val(type);
    });



    $(document).on("click", ".deletebtn", function () {
        id = $(this).data('id');
        $("#myModalLabel").text("Delete Day End?");

    });

    function deleteData() {
        $.ajax({
            type: 'POST',
            url: '/day-end-master/delete/' + id,
            dataType: 'json',
            success: function () {
                $('.deleteModal').modal('hide');
                receiptTable();
                swal("Success!", "Day End Deleted Successfully", "success");
            }, error: function () {
                swal("Error!", "Something went wrong", "error");
            }
        });
    }



    function printRecipt() {
        /* window.addEventListener('load', function () {*/
        $('#reciptPrint').printThis({
            importCSS: true,
            printDelay: 2000,
            importStyle: true,
            base: "",
            pageTitle: ""
        });
    }

    $(document).on("click", ".print", function () {
        var custId =  $(this).data('recievedfrom');
        var id =  $(this).data('id');
        $("#printabel").remove();
        reciptPrint(custId,id)
    });

    function reciptPrint(custId,id) {
        $("<iframe id='printabel'>")
            .hide()
            .attr("src", "/print-recipt/"+custId+"/recipt/"+id)
            .appendTo("body");
    }

    function cancelReceipt(id) {
        Swal.fire({
            title: "Cancel this Receipt? this can't be undone.",
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Yes',
            denyButtonText: 'No',
        }).then((result) => {
            if (result.isConfirmed) {
                var url = '/receipt/cancel?id=' + id;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: 'json',
                    success: function (data) {
                        Swal.fire(
                            'Success!',
                            'Receipt Cancelled',
                            'success'
                        );
                        receiptTable();
                    },
                    error: function () {
                        Swal.fire(
                            'Error!',
                            'Unable to cancel Receipt at the moment, try later.',
                            'danger'
                        );
                    }
                });
            } else if (result.isDenied) {

            }
        });


    }


</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("accounts-menu");
</script>
</body>
</html>