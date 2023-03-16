<style>
.modal-dialog {
    max-width: 800px;
    margin: 1.75rem auto;
}
</style>

<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Filter</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-4">
                        <form style="border: 1px solid black; padding: 10px;">
                            <label for="option0">
                                <input onchange="getSelectedValue()" type="radio" id="option0" name="options"
                                       value="option0" checked> All
                            </label><br>
                            <label for="option1">
                                <input onchange="getSelectedValue()" type="radio" id="option1" name="options"
                                       value="option1"> Product Wise
                            </label><br>
                            <label for="option2">
                                <input onchange="getSelectedValue()" type="radio" id="option2" name="options"
                                       value="option2"> Company Wise
                            </label><br>
                            <label for="option3">
                                <input onchange="getSelectedValue()" type="radio" id="option3" name="options"
                                       value="option3"> Supplier Wise
                            </label><br>
                            <label for="option4">
                                <input onchange="getSelectedValue()" type="radio" id="option4" name="options"
                                       value="option4"> Group Wise
                            </label><br>
                        </form>
                    </div>

                    <div class="col-8">
                        <p style="border: 1px solid black; padding: 5px; max-height: 205px; height: 205px; overflow: auto;" id="filterContent">
                        </p>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>

<script>
    function getSelectedValue() {
        // Get all the radio buttons with name "choice"
        var radios = document.getElementsByName("options");
        // Loop through the radio buttons
        for (var i = 0; i < radios.length; i++) {
            // Check if the current radio button is checked
            if (radios[i].checked) {

                var url = null;
                switch (radios[i].value) {
                    case "option0":
                        url = "";
                        filterType = "ALL";
                        document.getElementById("filterContent").innerHTML = "";
                        break;
                    case "option1":
                        //product wise
                        url = "/product/get-products-by-entity"
                        filterType = "PRODUCT";
                        document.getElementById("filterContent").innerHTML = "";
                        $.ajax({
                            url: url,
                            type: "GET",
                            success: function (data) {
                                var content = "<ul style=\"list-style-type: none;\">";
                                $.each(data, function(index, value){
                                    content += "<li><input type=\"checkbox\" id=\"item"+index+"\" name=\"products\" value=\""+value.id+"\">  <label for=\"item"+index+"\">"+value.productName+"</label></li>"
                                })

                                content += "</ul>";
                                $("#filterContent").append(content);
                            },
                            error: function (data) {

                            }
                        })
                        break;
                    case "option2":
                        //company wise
                    case "option3":
                        //Supplier wise
                        url = "/entity-register/getbyaffiliates/${session.getAttribute("entityId")}";
                        filterType = "SUPPLIER";
                        document.getElementById("filterContent").innerHTML = "";
                        $.ajax({
                            url: url,
                            type: "GET",
                            success: function (data) {
                                var content = "<ul style=\"list-style-type: none;\">";
                                $.each(data, function(index, value){
                                    content += "<li><input type=\"checkbox\" id=\"item"+index+"\" name=\"company\" value=\""+value.id+"\">  <label for=\"item"+index+"\">"+value.entityName+"</label></li>"
                                })

                                content += "</ul>";
                                $("#filterContent").append(content);
                            },
                            error: function (data) {

                            }
                        })
                        break;
                    case "option4":
                        //Group wise
                        url = "/product-group/getbyentity";
                        filterType = "GROUP";
                        document.getElementById("filterContent").innerHTML = "";
                        $.ajax({
                            url: url,
                            type: "GET",
                            success: function (data) {
                                var content = "<ul style=\"list-style-type: none;\">";
                                $.each(data, function(index, value){
                                    content += "<li><input type=\"checkbox\" id=\"item"+index+"\" name=\"groups\" value=\""+value.id+"\">  <label for=\"item"+index+"\">"+value.groupName+"</label></li>"
                                })

                                content += "</ul>";
                                $("#filterContent").append(content);
                            },
                            error: function (data) {

                            }
                        })
                        break;
                }

            }
        }
    }
</script>