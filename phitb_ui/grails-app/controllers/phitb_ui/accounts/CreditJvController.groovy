package phitb_ui.accounts

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.SalesService

import java.text.SimpleDateFormat

class CreditJvController {

    def index() {
        String entityId = session.getAttribute("entityId").toString()
        JSONArray accounts = new EntityService().getAllAccountByEntity(entityId)
        JSONArray reasons = new SalesService().getReasons()
        ArrayList<JSONObject> debitAccounts = new ArrayList<>()
        ArrayList<JSONObject> creditAccounts = new ArrayList<>()
        if (accounts?.size() > 0) {
            for (JSONObject json : accounts) {
                if (json.get("showInDebit")) {
                    debitAccounts.add(json)
                }

                if (json.get("showInCredit")) {
                    creditAccounts.add(json)
                }

            }
        }
        render(view: "/accounts/creditJV/index", model: [debitAccounts : debitAccounts, reasons: reasons?.reverse(),
                                                         creditAccounts: creditAccounts])
    }

    def saveCreditJv() {
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

        def apiResponse = new AccountsService().saveCreditJV(jsonObject)
        redirect(uri: "/credit-jv")
    }

    def approval() {
        render(view: "/accounts/creditJV/approve-creditjv")
    }

    //unapproved list
    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId").toString())
            def apiResponse = new AccountsService().creditJVDatatables(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                for (JSONObject json : responseObject["data"]) {
                    long toAccount = json.toAccount
                    def account = new EntityService().getAccountById(toAccount.toString())
                    if(account)
                        json.put("toAccount",account)

                    long debitAccount = json.debitAccount
                    def dAccount = new EntityService().getAccountById(debitAccount.toString())
                    if(dAccount)
                        json.put("debitAccount",dAccount)

                }
                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def approveReject()
    {
        String status = params.status
        String creditJvId = params.id
        String toAccount = params.toAccount
        String debitAccount = params.debitAccount
        String amount = params.amount
        long entityId = session.getAttribute("entityId")
        long userId = session.getAttribute("userId")
        def toAcc = new EntityService().getAccountById(toAccount)
        def debAcc = new EntityService().getAccountById(debitAccount)

        if(toAcc && debAcc)
        {
            def apiResponse = new AccountsService().creditJvApprove(status, entityId, userId, creditJvId, debAcc.balance, toAcc.balance)
            if(apiResponse?.status == 200)
            {
                //update balance in accounts
                new EntityService().updateAccountBalance(amount, entityId.toString(),debAcc.id, false)
                new EntityService().updateAccountBalance(amount, entityId.toString(),toAcc.id, true)
            }
            response.status = apiResponse.status
        }
        else
            response.status = 400

    }
}
