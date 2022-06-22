package phitb_ui.accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.entity.AccountRegisterController
import phitb_ui.entity.EntityRegisterController
import phitb_ui.sales.SalebillDetailsController
import phitb_ui.system.AccountModeController
import phitb_ui.system.PaymentModeController

class PaymentDetailController {

    def index() {
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
        ArrayList<String> accountRegister = new AccountRegisterController().getAllAccounts() as ArrayList
        ArrayList<String> wallet = new WalletController().show() as ArrayList
        ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
        ArrayList<String> paymodes = new PaymentModeController().show() as ArrayList<String>
        render(view: "/accounts/payments/payments", model: [entity                           : entity, bank: bank,
                                                     accountMode:
                accountMode,
                                                                   wallet                           : wallet, saleinvoice: saleinvoice, paymodes:
                                                                           paymodes, accountRegister: accountRegister])
    }



    def paymentList()
    {
        render(view: '/accounts/payments/payment-list')
    }

    def settledVocher()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateSettledVocher(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def unsettledVocher()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateunSettledVocher(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().showPayments(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    JSONArray jsonArray2 = new JSONArray()
                    JSONArray depositArray = new JSONArray()
                    for (JSONObject json : jsonArray) {
                        json.put("transferFrom", new EntityService().getEntityById(json.get("transferFrom").toString()))
                        jsonArray2.put(json)
                    }
                    jsonArray.each {
                        if (it.paymentTo!="" && it.paymentTo!=null) {
                            def accountResp = new EntityService().getAccountById(it.get("paymentTo")?.toString())
                            it.put("paymentTo", accountResp)
                        }
                        else
                        {
                            it.put("paymentTo", "NA")
                        }
                    }
                    responseObject.put("data", jsonArray2)
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


    def getAllEntityById()
    {
        try
        {
            JSONObject jsonObject = new JSONObject()
            def apiResponse = new AccountsService().getEntityById(params.id)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getAllSaleBillEntityById()
    {
        try
        {
            JSONObject jsonObject = new JSONObject()
            def apiResponse = new AccountsService().getEntityById(params.id)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getAllSaleBillCustomerId()
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            def apiResponse = new AccountsService().getSaleBillCustomerId(params.id)
            if (apiResponse.status == 200)
            {
                JSONArray responseArry = new JSONArray(apiResponse.readEntity(String.class))
                respond responseArry, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getAllSaleBillsettled()
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            def apiResponse = new AccountsService().getSaleBillSettledCustomerId(params.id)
            if (apiResponse.status == 200)
            {
                JSONArray responseArry = new JSONArray(apiResponse.readEntity(String.class))
                respond responseArry, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute('entityId').toString())
            JSONArray billArray = new JSONArray(params.paymentData)
            double invoice = 0;
            double credit = 0;
            double goodsTransferNote = 0;
            for (JSONObject bills : billArray) {
                if (bills.get("Doc.Type") == "INVS" && bills.get("PaidNow").toString().toDouble()!=0) {
                    invoice += Double.parseDouble(bills.PaidNow)
                }
                if (bills.get("Doc.Type") == "CRNT" && bills.get("PaidNow").toString().toDouble()!= 0) {
                    credit += Double.parseDouble(bills.PaidNow)
                }
                if (bills.get("Doc.Type") == "GRN" && bills.get("PaidNow").toString().toDouble()!= 0) {
                    goodsTransferNote += Double.parseDouble(bills.PaidNow)
                }
                jsonObject.put("amountPaid",invoice+goodsTransferNote)
            }
            def apiResponse = new AccountsService().savePaymentDetail(jsonObject, session.getAttribute('financialYear') as String)
            if (apiResponse?.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                for (JSONObject bills : billArray) {
                    String paidNow = bills.get("PaidNow")
                    String BalAmt = bills.get("BalAmt")
                    String transactionId = bills.get("Trans_Id")
                    String docType = bills.get("Doc.Type")
                    String billId = bills.get("BillId")
                    String paymentId = jsonObject1.id.toString()
                    if (docType == "INVS" && paidNow.toDouble()!=0) {
                        JSONObject invObject = new JSONObject()
                        invObject.put("id", billId)
                        invObject.put("paidNow", paidNow)
                        invObject.put("status","NA")
                        def inv = new AccountsService().updatePurchaseBalance(invObject)
                        if (inv?.status == 200) {
                            invObject.remove("id");
                            invObject.remove("paidNow");
                        }
                    }
                    if (docType == "CRNT" && paidNow.toDouble()!= 0) {
                        JSONObject crntObject = new JSONObject();
                        crntObject.put("id", billId)
                        crntObject.put("paidNow", paidNow)
                        crntObject.put("status","NA")
                        def crnt = new AccountsService().updatePurchaseReturnBalance(crntObject)
                        if (crnt?.status == 200) {
                            crntObject.remove("id");
                            crntObject.remove("paidNow");
                        }
                    }
                    if (docType == "GRN" && paidNow.toDouble()!= 0) {
                        JSONObject gtnObject = new JSONObject();
                        gtnObject.put("id", billId)
                        gtnObject.put("paidNow", paidNow)
                        gtnObject.put("status","NA")
                        def gtn = new AccountsService().updateGTNBalance(gtnObject)
                        if (gtn?.status == 200) {
                            gtnObject.remove("id");
                            gtnObject.remove("paidNow");
                        }
                    }
                    JSONObject billLog = new JSONObject()
                    if (paidNow.toDouble()!= 0) {
                        billLog.put("billId", billId)
                        billLog.put("billType", docType)
                        billLog.put("amountPaid", paidNow)
                        billLog.put("currentFinancialYear", session.getAttribute('financialYear').toString())
                        billLog.put("financialYear", session.getAttribute('financialYear').toString())
                        billLog.put("paymentId", paymentId)
                        billLog.put("transId", transactionId)
                        def billLogResponse = new AccountsService().updatePaymentDetailLog(billLog)
                        if (billLogResponse?.status == 200) {
                            println("Bill Log Saved!")
                        }
                    }
                }
                respond jsonObject1, formats: ['json'], status: 200
            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update()
    {
        try
        {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().putBankRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().deleteBankRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def show()
    {
        try
        {
            def apiResponse = new ProductService().getDivisions()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getCustomerById(String id)
    {
        def cust = new AccountsService().getEntityById(id)
        if(cust.status==200)
        {
            JSONObject customer = new JSONObject(cust.readEntity(String.class))
            return customer
        }
        else {

            return []
        }
    }



    def getPaymentById(String id) {
        def payment = new AccountsService().getPaymentById(id)
        if (payment.status == 200) {
            JSONObject p1 = new JSONObject(payment.readEntity(String.class))
            return p1
        } else {

            return []
        }
    }


    def printPayment() {
        JSONObject customer = new EntityRegisterController().getEnitityById(params.custid) as JSONObject
        JSONObject payments = new PaymentDetailController().getPaymentById(params.id) as JSONObject
        JSONObject entity = new EntityRegisterController().getEnitityById(session.getAttribute('entityId').toString()) as
                JSONObject
        def reciptlogsinv = new AccountsService().getPaymentLogInvById(params.id)
        def reciptlogscrnt = new AccountsService().getPaymentLogcrntById(params.id)
        def reciptlogsgtn = new AccountsService().getPaymentLogGRNById(params.id)
        JSONArray paymentsloginvArray = new JSONArray(reciptlogsinv.readEntity(String.class))
        JSONArray paymentslogcrntArray = new JSONArray(reciptlogscrnt.readEntity(String.class))
        JSONArray paymentsloggtnArray = new JSONArray(reciptlogsgtn.readEntity(String.class))
        render(view: '/accounts/payments/payment-vocher', model: [customer            : customer, payments: payments,
                                                                  entity              : entity, paymentsloginvArray: paymentsloginvArray,
                                                                  paymentslogcrntArray: paymentslogcrntArray,
                                                                  paymentsloggtnArray :paymentsloggtnArray])
    }


    def getAllBillDetailsBySupplierId() {
        try {
            JSONArray jsonArray = new JSONArray();
            def purchaseBill = new AccountsService().getAllPurchaseBillById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getAllPurchaseReturnById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def grn = new AccountsService().getAllGTNById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (purchaseBill.status == 200 && creditNote.status == 200 && grn.status == 200) {
//                def tmp = creditNote.readEntity(String.class)
                JSONArray purchaseInvArray = new JSONArray(purchaseBill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                JSONArray grnArray = new JSONArray(grn.readEntity(String.class))
                jsonArray.add(purchaseInvArray)
                jsonArray.add(creditNoteArry)
                jsonArray.add(grnArray)
                respond jsonArray, formats: ['json'], status: 200
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

}
