package phitb_ui.accounts

import org.grails.web.json.JSONArray
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
            JSONArray customerIds = new JSONArray()
            for (Object entity : entities) {
                customerIds.put(entity.id)
            }
            JSONArray saleInvoices = new SalesService().getSaleBillDetailsByPendingPayment(financialYear, entityId, customerIds)
            respond saleInvoices, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }
}
