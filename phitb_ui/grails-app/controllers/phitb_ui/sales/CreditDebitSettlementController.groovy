package phitb_ui.sales

import phitb_ui.EntityService
import phitb_ui.entity.EntityRegisterController

class CreditDebitSettlementController {

    def index() {

        String entityId = session.getAttribute('entityId').toString()
        ArrayList<String> entity = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        render(view:'/sales/creditDebitSettlement/credit-debit-settlement',model: [entity:entity])
    }


}
