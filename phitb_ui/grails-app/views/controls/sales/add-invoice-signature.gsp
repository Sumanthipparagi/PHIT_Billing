<style>

.custom-modal-size {
    max-width: 400px;
    max-height: 400px/* Adjust as needed */
}

#drawCanvas {
    display: block;
    margin: 0 auto; /* Center the canvas */
    border: 2px solid black;
    width: 300px; /* Adjust width as needed */
    height: 300px; /* Adjust height as needed */
}

.card-inner {
    text-align: center; /* Center content within the card */
    padding: 0; /* Remove padding inside the card-inner where the canvas is */
}

.card-body {
    padding: 0; /* Optionally, reduce or remove padding around the card-body */
}

.modal-content {
    padding: 0; /* Remove padding inside the modal content if necessary */
}

.modal-footer {
    justify-content: center; /* Center-align flex items (buttons) if using Bootstrap 4+ */
    padding: 10px; /* Adjust padding for the footer if needed */
}
</style>
    <div class="modal fade" id="addproductTypeModal">
        <div class="modal-dialog custom-modal-size">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="productTypeTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="productTypeForm" enctype="multipart/form-data">
                    <div class="card-body">
                        <div class="row g-gs">
                            <div class="col-lg-12">
                                <div class="card card-bordered">
                                    <div class="card-inner">
                                        <canvas id="drawCanvas"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input name="id" id="id" class="id" type="hidden"/>
                        <input name="type" class="type" value="add" type="hidden"/>
                        <button type="button" id="clearCanvas" class="btn btn-warning btn-simple btn-round waves-effect">CLEAR</button>
                        <button id="submit" type="submit" class="btn btn-default btn-round waves-effect" name="submituser">SUBMIT</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



%{--<script>--}%
%{--    var canvas = document.getElementById('drawCanvas');--}%
%{--    var clearButton = document.getElementById('clearCanvas');--}%
%{--    clearButton.addEventListener('click', function () {--}%
%{--        var canvas = document.getElementById('drawCanvas');--}%
%{--        var ctx = canvas.getContext('2d');--}%
%{--        // Fill the canvas with white color or any background color you're using--}%
%{--        ctx.fillStyle = 'white';--}%
%{--        ctx.fillRect(0, 0, canvas.width, canvas.height);--}%
%{--    });--}%
%{--    if (canvas) {--}%
%{--        canvas.width = 300;--}%
%{--        canvas.height = 300;--}%

%{--        var ctx = canvas.getContext('2d');--}%
%{--        ctx.fillStyle = 'white';--}%
%{--        ctx.fillRect(0, 0, canvas.width, canvas.height);--}%

%{--        var painting = false;--}%


%{--        canvas.addEventListener('mousedown', function (e) {--}%
%{--            painting = true;--}%
%{--            draw(e);--}%

%{--        });--}%

%{--        canvas.addEventListener('mouseup', function () {--}%
%{--            painting = false;--}%
%{--            ctx.beginPath();--}%
%{--        });--}%

%{--        canvas.addEventListener('mousemove', function (e) {--}%
%{--            if (!painting) return;--}%
%{--            var mousePos = getMousePosition(canvas, e);--}%
%{--            ctx.lineWidth = 5;--}%
%{--            ctx.lineCap = 'round';--}%
%{--            ctx.strokeStyle = 'black';--}%
%{--            ctx.lineTo(mousePos.x, mousePos.y);--}%
%{--            ctx.stroke();--}%
%{--            ctx.beginPath();--}%
%{--            ctx.moveTo(mousePos.x, mousePos.y);--}%
%{--        });--}%

%{--        function getMousePosition(canvas, evt) {--}%
%{--            var rect = canvas.getBoundingClientRect();--}%
%{--            return {--}%
%{--                x: evt.clientX - rect.left,--}%
%{--                y: evt.clientY - rect.top--}%
%{--            };--}%
%{--        }--}%
%{--    }--}%


%{--    $(document).ready(function() {--}%
%{--        $("#save").click(function() { // Replace yourTriggerButtonId with the ID of your button--}%
%{--            var id = $("#invoice").val();--}%
%{--            $.ajax({--}%
%{--                type: 'GET',--}%
%{--                url: '/invoiceSignature/getbyinvoicenumber/' + id,--}%
%{--                dataType: 'json',--}%
%{--                success: function(json) {--}%
%{--                    var canvas = document.getElementById('drawCanvas');--}%
%{--                    if (canvas) {--}%
%{--                        var ctx = canvas.getContext('2d');--}%
%{--                        // Setting the text style--}%
%{--                        ctx.font = '12px Arial';--}%
%{--                        ctx.fillStyle = 'black';--}%

%{--                        // Calculate the starting Y position to draw from bottom, adjust as necessary--}%
%{--                        var startY = canvas.height - 70;--}%
%{--                        var lineHeight = 15; // Line height between text rows--}%

%{--                        // Drawing the invoice details--}%
%{--                        ctx.fillText("Invoice Number: " + json.invoiceNumber, 10, startY);--}%
%{--                        ctx.fillText("No. of Quantity: " + json.totalQty, 10, startY + lineHeight);--}%
%{--                        ctx.fillText("Gross Amt: ₹" + Number(json.grossAmount).toFixed(2), 10, startY + (lineHeight * 2));--}%
%{--                        ctx.fillText("GST: " + json.totalGst + "%", 10, startY + (lineHeight * 3));--}%
%{--                        ctx.fillText("Discount: ₹" + Number(json.totalDiscount).toFixed(2), 10, startY + (lineHeight * 4));--}%
%{--                        ctx.fillText("Net Amount: ₹" + Number(json.totalAmount).toFixed(2), 10, startY + (lineHeight * 5));--}%
%{--                    }--}%
%{--                },--}%
%{--                error: function(xhr, status, error) {--}%
%{--                    console.error('Error loading invoice details:', error);--}%
%{--                }--}%
%{--            });--}%
%{--        });--}%
%{--    });--}%

%{--</script>--}%