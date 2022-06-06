package phitb_ui.reports

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.entity.EntityRegisterController

class InventoryReportController {

    def index() { }

    def statement()
    {
        String entityId = session.getAttribute("entityId")
        JSONObject loggedInEntity = new EntityService().getEntityById(entityId)
        ArrayList entities = new EntityRegisterController().getByAffiliates(entityId) as ArrayList
        entities[0] = loggedInEntity
        render(view: '/reports/inventoryReport/statement', model: [entities:entities])
    }
}
