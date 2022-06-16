<div class="example-modal">
    <div class="modal fade" id="addRegionModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="regionTitle"></h4>
                </div>
                <form action="" id="form_validation" method="post" role="form" class="regionForm"
                      enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-6 form-group  form-float">
                                <label for="regionName">
                                    Region Name
                                </label>
                                <input type="text" id="regionName" class="form-control regionName" name="regionName" placeholder="Region Name"
                                       required/>
                            </div>


                            <div class="col-lg-6 form-group  form-float">

                                <label for="regionCode">
                                    Region Code
                                </label>
                                <input type="text" id="regionCode" class="form-control regionCode" name="regionCode"
                                       placeholder="Region Code" required/>
                            </div>
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

