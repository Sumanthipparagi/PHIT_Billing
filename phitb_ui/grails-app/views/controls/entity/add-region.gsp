<div class="example-modal">
  <div class="modal fade" id="addregionModal">
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
                <input type="text" id="regionName" class="form-control regionName" name="regionName"
                       placeholder="Region Name"
                       required/>
              </div>

              <div class="col-lg-6 form-group  form-float">
                <label for="shortName">
                  Short Name
                </label>
                <input type="text" id="shortName" class="form-control shortName" name="shortName"
                       placeholder="Short Name"
                       required/>
              </div>


              <div class="col-lg-6 form-group  form-float">
                <label for="countryId">
                  Country
                </label>
                <select class="form-control show-tick countryId" name="countryId" id="countryId">
                  <g:each var="c" in="${countrylist}">
                    <option value="${c.id}">${c.name}</option>
                  </g:each>
                </select>
              </div>

              <div class="col-lg-6 form-group  form-float">
                <label for="countryId">
                  Region States
                </label>
                <select class="form-control show-tick regionStateIds" name="regionStateIds" id="regionStateIds" multiple="multiple" required>
                  <g:each var="s" in="${statelist}">
                    <option value="${s.id}">${s.name}</option>
                  </g:each>
                </select>
              </div>

              <div class="col-lg-6 form-group  form-float">
                <label for="entity">
                  Entity
                </label>
                <select class="form-control show-tick entity" name="entity" id="entity" required>
                  <option value="">-- Please select --</option>

                  <g:each var="e" in="${entity}">
                    <option value="${e.id}"  data-type="${e.entityType.id}">${e.entityName}</option>
                  </g:each>
                </select>
              </div>


%{--              <div class="col-lg-6 form-group  form-float">--}%
%{--                <label for="entityTypeId">--}%
%{--                  Entity Type--}%
%{--                </label>--}%
%{--                <select class="form-control show-tick entityType" name="entityType" id="entityTypeId">--}%
%{--                  <g:each var="et" in="${entitytype}">--}%
%{--                    <option value="${et.id}">${et.name}</option>--}%
%{--                  </g:each>--}%
%{--                </select>--}%
%{--              </div>--}%

              <input type="hidden" id="entityTypeId" class="entityType" name="entityType">
              <input type="hidden" name="createdUser" value="1">
              <input type="hidden" name="modifiedUser" value="1">
              <input type="hidden" name="status" value="1">
              <input type="hidden" name="syncStatus" value="1">
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

