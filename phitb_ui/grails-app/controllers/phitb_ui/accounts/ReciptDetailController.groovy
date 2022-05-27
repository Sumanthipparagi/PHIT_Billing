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

class ReciptDetailController {

//    def index()
//    {
//        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
//        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
//        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
//        ArrayList<String> wallet = new WalletController().show() as ArrayList
//        ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
//        ArrayList<String> paymodes = new PaymentModeController().show() as ArrayList<String>
//        render(view: "/accounts/recipt/customer-recipt", model: [entity: entity, bank: bank, accountMode: accountMode,
//                                                                 wallet: wallet, saleinvoice: saleinvoice,paymodes:
//                                                                         paymodes])
//    }


//    def addRecipt()
//    {
//        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
//        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
//        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
//        ArrayList<String> wallet = new WalletController().show() as ArrayList
//        render(view: '/accounts/recipt/add-recipt', model: [entity: entity, bank: bank, accountMode: accountMode, wallet: wallet])
//    }

    def index() {
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
        ArrayList<String> accountRegister = new AccountRegisterController().getAllAccounts() as ArrayList
        ArrayList<String> wallet = new WalletController().show() as ArrayList
        ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
        ArrayList<String> paymodes = new PaymentModeController().show() as ArrayList<String>
        render(view: "/accounts/recipt/customer-recipt-2", model: [entity                           : entity, bank: bank, accountMode: accountMode,
                                                                   wallet                           : wallet, saleinvoice: saleinvoice, paymodes:
                                                                           paymodes, accountRegister: accountRegister])
    }


    def reciptList() {
        render(view: '/accounts/recipt/recipt-list')
    }

    def settledVocher() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateSettledVocher(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def unsettledVocher() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateunSettledVocher(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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


    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().showRecipt(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    JSONArray jsonArray2 = new JSONArray()
                    JSONArray depositArray = new JSONArray()
                    for (JSONObject json : jsonArray) {
                        json.put("receivedFrom", new EntityService().getEntityById(json.get("receivedFrom").toString()))
                        jsonArray2.put(json)
                    }

//                    for(JSONObject json1 : jsonArray2)
//                    {
//                        entityArray.put(json1.get("customer"))
//                    }
                    jsonArray.each {
                        if (it.depositTo!="" && it.depositTo!=null) {
                            def accountResp = new EntityService().getAccountById(it.get("depositTo")?.toString())
                            it.put("deposit", accountResp)
                        }
                        else
                        {
                            it.put("deposit", "NA")
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

    def getAllEntityById() {
        try {
            JSONObject jsonObject = new JSONObject()
            def apiResponse = new AccountsService().getEntityById(params.id)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
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


    def getAllSaleBillEntityById() {
        try {
            JSONObject jsonObject = new JSONObject()
            def apiResponse = new AccountsService().getEntityById(params.id)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
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


    def getAllUNSaleBillCustomerId() {
        try {
            JSONArray jsonArray = new JSONArray();
            def salebill = new AccountsService().getUnSaleBillCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getCNUnsettledCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status == 200) {
//                def tmp = creditNote.readEntity(String.class)
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
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


    def getAllBillDetailsByCustomerId() {
        try {
            JSONArray jsonArray = new JSONArray();
            def salebill = new AccountsService().getAllSaleBillById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getAllSaleReturnById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def gtn = new AccountsService().getAllGTNById(params.id, session.getAttribute("entityId").toString()
                    , session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status == 200) {
//                def tmp = creditNote.readEntity(String.class)
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                JSONArray gtnArray = new JSONArray(gtn.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
                jsonArray.add(gtnArray)
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


    def getAllSaleBillsettled() {
        try {
            JSONArray jsonArray = new JSONArray();
            def salebill = new AccountsService().getSaleBillSettledCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getCNsettledCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status) {
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
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


    def creditSettledVocher() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateCNSettledVocher(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def creditUnsettledVocher() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateCNunSettledVocher(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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


    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute('entityId').toString())
            JSONArray billArray = new JSONArray(params.reciptData)
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
                if (bills.get("Doc.Type") == "GTN" && bills.get("PaidNow").toString().toDouble()!= 0) {
                    goodsTransferNote += Double.parseDouble(bills.PaidNow)
                }
                jsonObject.put("amountPaid",invoice+goodsTransferNote)
            }
            def apiResponse = new AccountsService().saveRecipt(jsonObject, session.getAttribute('financialYear') as String)
            if (apiResponse?.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                for (JSONObject bills : billArray) {
                    String paidNow = bills.get("PaidNow")
                    String BalAmt = bills.get("BalAmt")
                    String transactionId = bills.get("Trans_Id")
                    String docType = bills.get("Doc.Type")
                    String billId = bills.get("BillId")
                    String recieptId = jsonObject1.id.toString()
                    if (docType == "INVS" && paidNow.toDouble()!=0) {
                        JSONObject invObject = new JSONObject()
                        invObject.put("id", billId)
                        invObject.put("paidNow", paidNow)
                        def invs = new AccountsService().updateSaleBalance(invObject)
                        if (invs?.status == 200) {
                            invObject.remove("id");
                            invObject.remove("paidNow");
                        }
                    }
                    if (docType == "CRNT" && paidNow.toDouble()!= 0) {
                        JSONObject crntObject = new JSONObject();
                        crntObject.put("id", billId)
                        crntObject.put("paidNow", paidNow)
                        def crnt = new AccountsService().updateSaleReturnBalance(crntObject)
                        if (crnt?.status == 200) {
                            crntObject.remove("id");
                            crntObject.remove("paidNow");
                        }
                    }
                    if (docType == "GTN" && paidNow.toDouble()!= 0) {
                        JSONObject gtnObject = new JSONObject();
                        gtnObject.put("id", billId)
                        gtnObject.put("paidNow", paidNow)
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
                        billLog.put("recieptId", recieptId)
                        billLog.put("transId", transactionId)
                        def billLogResponse = new AccountsService().updateReceiptDetailLog(billLog)
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

    def update() {
        try {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().putBankRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def delete() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().deleteBankRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
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

    def show() {
        try {
            def apiResponse = new ProductService().getDivisions()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getCustomerById(String id) {
        def cust = new AccountsService().getEntityById(id)
        if (cust.status == 200) {
            JSONObject customer = new JSONObject(cust.readEntity(String.class))
            return customer
        } else {

            return []
        }
    }


    def getReciptById(String id) {
        def recipt1 = new AccountsService().getReciptById(id)
        if (recipt1.status == 200) {
            JSONObject recipt = new JSONObject(recipt1.readEntity(String.class))
            return recipt
        } else {

            return []
        }
    }





    def printRecipt() {
        JSONObject customer = new EntityRegisterController().getEnitityById(params.custid) as JSONObject
        JSONObject recipt = new ReciptDetailController().getReciptById(params.id) as JSONObject
        JSONObject entity = new EntityRegisterController().getEnitityById(session.getAttribute('entityId').toString()) as
                JSONObject
        def reciptlogsinv = new AccountsService().getReceiptLogInvById(params.id)
        def reciptlogscrnt = new AccountsService().getReceiptLogcrntById(params.id)
        def reciptlogsgtn = new AccountsService().getReceiptLoggtnById(params.id)
        JSONArray reciptloginvArray = new JSONArray(reciptlogsinv.readEntity(String.class))
        JSONArray reciptlogcrntArray = new JSONArray(reciptlogscrnt.readEntity(String.class))
        JSONArray reciptloggtnArray = new JSONArray(reciptlogsgtn.readEntity(String.class))
        render(view: '/accounts/recipt/recipt-temp', model: [customer          : customer, recipt: recipt,
                                                             entity            : entity, reciptloginvArray: reciptloginvArray,
                                                             reciptlogcrntArray: reciptlogcrntArray,
                                                             reciptloggtnArray:reciptloggtnArray])
    }


    def updateSaleBalance() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put('balance', params.balance)
            jsonObject.put('id', params.id)
            println(jsonObject)
            def apiResponse = new AccountsService().updateSaleBalance(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def updateSaleReturnBalance() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put('balance', params.balance)
            jsonObject.put('id', params.id)
            println(jsonObject)
            def apiResponse = new AccountsService().updateSaleReturnBalance(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def updateRecieptLog() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateReceiptDetailLog(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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


    def recieptApproval()
    {
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
        render(view: '/accounts/recipt/receipt-approval',model:[entity:entity])
    }

}
