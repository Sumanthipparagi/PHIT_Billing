package phitb_ui.accounts

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
        def test1 = new EntityService().getEntityByUserRoute(userId)
        def test = new SalesService().getSaleBillDetailsByPendingPayment(financialYear, entityId)
        respond test, formats: ['json']
    }
}
