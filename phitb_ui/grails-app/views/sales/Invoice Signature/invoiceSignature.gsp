<!doctype html>
<html class="no-js " lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="PharmIT">

    <title>:: PharmIt :: Invoice Signature</title>
    <link rel="icon" type="image/x-icon" href="${assetPath(src: '/themeassets/images/favicon.ico')}"/>
    <!-- Favicon-->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/bootstrap/css/bootstrap.min.css"/>
    <!-- JQuery DataTable Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/plugins/jquery-datatable/dataTables.bootstrap4.min.css"/>
    <!-- Custom Css -->
    <asset:stylesheet rel="stylesheet" src="/themeassets/css/main.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/css/color_skins.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/sweetalert/sweetalert.css"/>
    <asset:stylesheet rel="stylesheet" href="/themeassets/plugins/multi-select/css/multi-select.css"/>
    <asset:stylesheet src="/themeassets/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <asset:stylesheet src="/themeassets/js/pages/forms/basic-form-elements.js" rel="stylesheet"/>
    <asset:stylesheet
            src="/themeassets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
            rel="stylesheet"/>
    <style>

    .invoice-detail {
        padding-bottom: 10px; /* Adjust the value as needed */
    }

    .input-error {
        border-color: red;
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
                    <h2>Sales Group</h2>
                    <ul class="breadcrumb padding-0">
                        <li class="breadcrumb-item"><a href="#"><i class="zmdi zmdi-home"></i></a></li>
                        <li class="breadcrumb-item active">Invoice Signature</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="body">
        <div class="row">
            <div class="col-md-12 mt-2">
                <div class="row">
                    <div class="col-md-4 form-group form-float">
                        <label for="invoice">
                            Invoice Number <span class="required-indicator" style="color: red;">*</span>
                        </label>
                        <input type="text" id="invoice" class="form-control invoice" name="invoice"
                               placeholder="Invoice" required/>
                        <!-- Error message div placed directly below the input field -->
                        <div id="invoiceError" style="color: red; display: none;">Please enter the invoice number.</div>
                    </div>

                    <div class="col-lg-12">
                        <button id="save" type="submit" class="btn btn-default btn-round waves-effect"
                                name="submituser">
                            <font style="vertical-align: inherit;"><font style="vertical-align: inherit;">SUBMIT</font>
                            </font>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <br>

    <div class="card mb-3" id="invoiceDetailsCard" style="display: none;"><!-- Initially hidden -->
        <div class="body" style="padding: 10px;">
            <div class="row" id="invoiceDetails">
                <!-- Dynamic invoice details will be inserted here by JavaScript -->
            </div>

            <div class="row">
                <div class="col-12 mt-3">
                    <button type="button" class="btn btn-round btn-primary" data-toggle="modal"
                            data-target="#addproductTypeModal" id="acknowledge">Acknowledge</button>
                </div>
            </div>
        </div>
    </div>
</section>

<g:include view="controls/sales/add-invoice-signature.gsp"/>
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
<asset:javascript src="/themeassets/plugins/multi-select/js/jquery.multi-select.js" type="text/javascript"/>

<script>

    // Define a variable to store the invoice details globally
    var invoiceDetails = {};

    $(document).ready(function () {
        $("#save").click(function (event) {
            event.preventDefault();
            var invoiceNumber = $("#invoice").val().trim();

            if (invoiceNumber === "") {
                $("#invoiceError").show();
                $("#invoice").addClass("input-error");
                $("#invoice").focus();
            } else {
                $("#invoiceError").hide();
                $("#invoice").removeClass("input-error");

                var id = $("#invoice").val();
                $.ajax({
                    type: 'GET',
                    url: '/invoiceSignature/getbyinvoicenumber/' + id,
                    dataType: 'json',
                    success: function (json) {
                        // Populate the global variable
                        invoiceDetails = json;

                        // Populate the card with invoice details
                        var detailsHtml = "<div class='col-12 invoice-detail'><strong>Invoice Number:</strong> " + json.invoiceNumber + "</div>" +
                            "<div class='col-12 invoice-detail'><strong>No. of Quantity:</strong> " + json.totalQty + "</div>" +
                            "<div class='col-12 invoice-detail'><strong>Gross Amt:</strong> " + Number(json.grossAmount).toFixed(2) + "</div>" +
                            "<div class='col-12 invoice-detail'><strong>GST:</strong> " + json.totalGst + "</div>" +
                            "<div class='col-12 invoice-detail'><strong>Discount:</strong> " + Number(json.totalDiscount).toFixed(2) + "</div>" +
                            "<div class='col-12 invoice-detail'><strong>Net Amount:</strong> " + Number(json.totalAmount).toFixed(2) + "</div>";
                        $('#invoiceDetails').html(detailsHtml);
                        $('#invoiceDetailsCard').show();
                    },
                    error: function (xhr, status, error) {
                        console.error('Error loading invoice details:', error);
                    }
                });
            }
        });

        $("#acknowledge").click(async function () {
            try {
                console.log("Fetching position...");
                const position = await getCurrentPosition();
                console.log("Position fetched:", position);

                console.log("Fetching IP address...");
                const ipAddress = await fetchIpAddress();
                console.log("IP address fetched:", ipAddress);

                // Fetch entity details
                var entityId = 1; // Ensure this is the correct way to obtain entityId
                $.ajax({
                    type: 'GET',
                    url: '/getEntityById/' + entityId,
                    dataType: 'json',
                    success: function (json) {
                        console.log("Entity name fetched:", json.entityName);
                        // Now call drawDetails with all the necessary details including entityName
                        drawDetails(position.coords.latitude, position.coords.longitude, ipAddress, json.entityName);
                    },
                    error: function (xhr, status, error) {
                        console.error("Error fetching entity name:", error);
                    }
                });
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        });

        function getCurrentPosition() {
            return new Promise((resolve, reject) => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(resolve, reject);
                } else {
                    reject(new Error("Geolocation is not supported by this browser."));
                }
            });
        }

        async function fetchIpAddress() {
            const response = await fetch('https://api.ipify.org?format=json');
            const data = await response.json();
            return data.ip;
        }

        function drawDetails(latitude, longitude, ipAddress, entityName) {
            var canvas = document.getElementById('drawCanvas');
            if (canvas && canvas.getContext) {
                var ctx = canvas.getContext('2d');
                ctx.clearRect(0, 0, canvas.width, canvas.height); // Clear existing content
                ctx.font = '12px Arial';
                ctx.fillStyle = 'black';
                var startY = canvas.height - 130; // Adjust startY as needed
                var lineHeight = 20;

                // Drawing the geolocation details
                ctx.fillText("Invoice Number: " + invoiceDetails.invoiceNumber, 10, startY);
                ctx.fillText("Quantity: " + invoiceDetails.totalQty, 10, startY + lineHeight);
                ctx.fillText("Total Amount: " + Number(invoiceDetails.totalAmount).toFixed(2), 10, startY + (lineHeight * 2));

                ctx.fillText("Latitude: " + latitude.toFixed(2), 10, startY + (lineHeight * 3));
                ctx.fillText("Longitude: " + longitude.toFixed(2), 10, startY + (lineHeight * 4));
                // Drawing the IP address
                ctx.fillText("IP Address: " + ipAddress, 10, startY + (lineHeight * 5));
                // Drawing the entity name
                if (entityName) { // Check if entityName is available
                    ctx.fillText("Customer Name: " + entityName, 10, startY + (lineHeight * 6));
                }
            }
        }
    })


    var canvas = document.getElementById('drawCanvas');
    var clearButton = document.getElementById('clearCanvas');
    clearButton.addEventListener('click', function () {
        var canvas = document.getElementById('drawCanvas');
        var ctx = canvas.getContext('2d');
        // Fill the canvas with white color or any background color you're using
        ctx.fillStyle = 'white';
        ctx.fillRect(0, 0, canvas.width, canvas.height);
    });
    if (canvas) {
        canvas.width = 300;
        canvas.height = 300;

        var ctx = canvas.getContext('2d');
        ctx.fillStyle = 'white';
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        var painting = false;


        canvas.addEventListener('mousedown', function (e) {
            painting = true;
            draw(e);

        });

        canvas.addEventListener('mouseup', function () {
            painting = false;
            ctx.beginPath();
        });

        canvas.addEventListener('mousemove', function (e) {
            if (!painting) return;
            var mousePos = getMousePosition(canvas, e);
            ctx.lineWidth = 5;
            ctx.lineCap = 'round';
            ctx.strokeStyle = 'black';
            ctx.lineTo(mousePos.x, mousePos.y);
            ctx.stroke();
            ctx.beginPath();
            ctx.moveTo(mousePos.x, mousePos.y);
        });

        function getMousePosition(canvas, evt) {
            var rect = canvas.getBoundingClientRect();
            return {
                x: evt.clientX - rect.left,
                y: evt.clientY - rect.top
            };
        }
    }

    var canvas = document.getElementById('drawCanvas');
    var ctx = canvas.getContext('2d');
    // Example initial drawing
    ctx.fillRect(10, 10, 100, 50);

    $("#submit").click(function (event) {
        event.preventDefault(); // Prevent the default form submission action

        // Create a temporary canvas to ensure the final image has a white background
        var tempCanvas = document.createElement('canvas');
        tempCanvas.width = canvas.width;
        tempCanvas.height = canvas.height;
        var tempCtx = tempCanvas.getContext('2d');

        // Fill the temporary canvas with white
        tempCtx.fillStyle = 'white';
        tempCtx.fillRect(0, 0, tempCanvas.width, tempCanvas.height);

        // Draw the original canvas content onto the temporary canvas
        tempCtx.drawImage(canvas, 0, 0);

        // Convert the temporary canvas to a Blob, then to a File
        tempCanvas.toBlob(function (blob) {
            // Use the invoice number as the file name, ensure invoiceDetails.invoiceNumber is valid
            var fileName = invoiceDetails.invoiceNumber + ".jpg"; // Using invoice number as file name

            var file = new File([blob], fileName, {type: "image/jpeg"});

            // Ask the user for confirmation before uploading
            Swal.fire({
                title: 'Confirm Upload',
                text: 'Do you want to upload the created image file?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Upload',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Display uploading popup
                    Swal.fire({
                        title: 'Uploading...',
                        text: 'Please wait while the file is being uploaded.',
                        allowOutsideClick: false,
                        showConfirmButton: false
                    });

                    const formData = new FormData();
                    formData.append('file', file);
                    formData.append("type", "eva");


                    fetch('/files/savecanvasimagetoftp', {
                        method: 'POST',
                        body: formData,
                    }).then((response) => {
                        if (response.ok) {
                            return response.json();
                        }
                        throw new Error('File upload failed.');
                    }).then((data) => {
                        Swal.fire('Success', 'File uploaded successfully!', 'success');
                        var ftpPath = data.file; // Adjust 'filePath' based on your actual data structure

                        // Print the FTP path and invoice number to the console
                        console.log("Uploaded Image Path:", ftpPath);
                        console.log("Invoice Number:", invoiceDetails.invoiceNumber);

                        Swal.fire('Success', 'File uploaded successfully!', 'success');


                        // Preparing the object with invoice number and FTP path
                        var details = {
                            invoiceNumber: invoiceDetails.invoiceNumber,
                            ftpPath: ftpPath
                        };

                        // AJAX call to send the object to the server
                        $.ajax({
                            type: 'POST', // Typically, you'd use POST for such an operation
                            url: '/salebill/updatecanvasimageurl',
                            data: details, // Directly sending the object without stringifying
                            success: function (response) {
                                console.log('Update successful:', response);
                                // Handle success
                            },
                            error: function (xhr, status, error) {
                                console.error('Error updating sale bill details:', error);
                                // Handle error
                            }
                        });
                    })
                }
            })
        })
    })


    // Clear canvas button functionality
    var clearButton = document.getElementById('clearCanvas');
    clearButton.addEventListener('click', function () {
        ctx.fillStyle = 'white';
        ctx.fillRect(0, 0, canvas.width, canvas.height);
    });

</script>

<g:include view="controls/footer-content.gsp"/>
<script>
    selectSideMenu("sales-menu");
</script>

</body>
</html>