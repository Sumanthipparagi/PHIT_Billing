package phitb_ui.accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.SalesService

/**
 * This controller is for Payment Collection purposes by executive
 */
class PaymentCollectionController {

    def index() { }

    def getPendingSaleInvoices()
    {
        String entityId = session.getAttribute("entityId").toString()
        String userId = session.getAttribute("userId").toString()
        String financialYear = session.getAttribute("financialYear").toString()
        JSONArray entities = new EntityService().getEntityByUserRoute(userId)
        if(entities?.size() >0) {
            String customerIds = ""
            for (Object entity : entities) {
                customerIds += entity.id +","
            }

            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", entityId)
            jsonObject.put("financialYear", financialYear)
            jsonObject.put("customerIds", customerIds.toString())

            JSONObject saleInvoices = new SalesService().getSaleBillDetailsByPendingPayment(jsonObject)
            respond saleInvoices, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }
}
