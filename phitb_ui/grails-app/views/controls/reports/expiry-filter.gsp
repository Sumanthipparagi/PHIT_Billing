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
                                <input type="radio" id="option0" name="options" value="option1" checked> All
                            </label><br>
                            <label for="option1">
                                <input type="radio" id="option1" name="options" value="option1"> Product Wise
                            </label><br>
                            <label for="option2">
                                <input type="radio" id="option2" name="options" value="option2"> Company Wise
                            </label><br>
                            <label for="option3">
                                <input type="radio" id="option3" name="options" value="option3"> Supplier Wise
                            </label><br>
                            <label for="option4">
                                <input type="radio" id="option4" name="options" value="option4"> Group Wise
                            </label><br>
                        </form>
                    </div>
                    <div class="col-8">
                        <p style="border: 1px solid black; padding: 10px; min-height: auto">

                        </p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>