package phitb_ui.accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.SalesService
import phitb_ui.SystemService

/**
 * This controller is for Payment Collection purposes by executive
 */
class PaymentCollectionController {

    def index() {
        String entityId = session.getAttribute("entityId").toString()
        ArrayList<JSONObject> bank = new AccountsService().getBankRegisterByEntity(entityId) as ArrayList
        ArrayList<JSONObject> paymentModes = new ArrayList<>()
        def apiResponse = new SystemService().getPaymentModes()
        if (apiResponse.status == 200) {
            paymentModes = new JSONArray(apiResponse.readEntity(String.class))
            paymentModes = paymentModes.reverse()
        }

        ArrayList<JSONObject> accountMode = new SystemService().getAccountModesByEntity(entityId) as ArrayList
        ArrayList<JSONObject> accountRegister = new EntityService().getAllAccountByEntity(entityId) as ArrayList

        render(view: 'index', model: [bank           : bank,
                                                                accountMode    : accountMode,
                                                                paymentModes   : paymentModes,
                                                                accountRegister: accountRegister
        ])
    }

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
