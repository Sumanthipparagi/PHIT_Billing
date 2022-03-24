package phitb_ui.accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.SalesService

import java.text.SimpleDateFormat

class DebitJvController {

    def index() {
        String entityId = session.getAttribute("entityId").toString()
        JSONArray accounts = new EntityService().getAllAccountByEntity(entityId)
        JSONArray reasons = new SalesService().getReasons()
        ArrayList<JSONObject> debitAccounts = new ArrayList<>()
        ArrayList<JSONObject> creditAccounts = new ArrayList<>()
        if(accounts?.size()>0)
        {
            for (JSONObject json : accounts) {
                if(json.get("showInDebit"))
                {
                    debitAccounts.add(json)
                }

                if(json.get("showInCredit"))
                {
                    creditAccounts.add(json)
                }

            }
        }
        render(view: "/accounts/debitJV/index", model: [debitAccounts:debitAccounts, reasons:reasons?.reverse(),
                                                         creditAccounts:creditAccounts])
    }

    def saveDebitJv()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        JSONObject jsonObject = new JSONObject(params)
        jsonObject.put("status", "1")
        jsonObject.put("syncStatus", "1")
        jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
        jsonObject.put("entityId", session.getAttribute("entityId"))
        jsonObject.put("modifiedUser", session.getAttribute("userId"))
        jsonObject.put("createdUser", session.getAttribute("userId"))
        jsonObject.put("financialYear", session.getAttribute("financialYear"))
        jsonObject.put("transactionId", "0")
        jsonObject.put("transactionDate", sdf.format(new Date()))
        jsonObject.put("employeeId", session.getAttribute("userId"))
        jsonObject.put("approverId", session.getAttribute("userId"))
        jsonObject.put("amount", jsonObject.get("amount"))

        def apiResponse = new AccountsService().saveDebitJV(jsonObject)
        redirect(uri: "/debit-jv")
    }
}
