<div class="example-modal">
  <div class="modal fade" id="addStateModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span></button>
          <h4 class="stateTitle"></h4>
        </div>
        <form action="" id="form_validation" method="post" role="form" class="stateForm" enctype="multipart/form-data">
          <div class="modal-body">
            <div class="row">
              <div class="col-lg-6 form-group  form-float">
                <label for="name">
                  Name
                </label>
                <input type="text" id="name" class="form-control name" name="name" placeholder="Name"
                       required/>
              </div>

              <div class="col-lg-6 form-group  form-float">
                <label for="zone">
                  Zone
                </label>
                <select class="form-control show-tick zone" name="zoneId" id="zone">
                  <g:each var="zone" in="${zoneArrayList}">
                    <option value="${zone.id}">${zone.name}</option>
                  </g:each>
                </select>
              </div>

              <div class="col-lg-6 form-group  form-float">
                <label for="country">
                  Country
                </label>
                <select class="form-control show-tick country" name="countryId" id="country">
                  <g:each var="country" in="${countryArrayList}">
                    <option value="${country.id}">${country.name}</option>
                  </g:each>
                </select>
              </div>

              <div class="col-lg-6 form-group  form-float">

                <label for="entity">
                  Entity
                </label>
                <select class="form-control show-tick entity" name="entityId" id="entity" required>
                  <g:each var="e" in="${entity}">
                    <option value="${e.id}" data-type="${e.entityType.id}">${e.entityName}</option>
                  </g:each>
                </select>
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

