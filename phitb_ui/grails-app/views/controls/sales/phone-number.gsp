<!-- Modal -->
<div class="modal fade" id="phoneNumberModal" role="dialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
%{--        <button type="button" class="close" data-dismiss="modal">&times;</button>--}%
        <h4 class="modal-title" style="font-size: 20px;margin-top: -6px;"><span >Enter Mobile Number</span></h4>
      </div>
      <div class="modal-body">
        <input type="tel" class="phoneNumber" name="phoneNumber" id="phono" minlength="10"  pattern="[789][0-9]{9}"/>
      </div>
      <div class="modal-footer">
%{--
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
--}%

        <button type="button" class="btn btn-dark" style="background-color: black" id="phoneNumber">OK
        </button>
      </div>
    </div>
  </div>
</div>
