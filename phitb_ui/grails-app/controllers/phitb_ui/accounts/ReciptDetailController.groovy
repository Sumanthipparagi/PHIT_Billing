package phitb_ui.accounts


import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EmailService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.sales.SalebillDetailsController
import phitb_ui.system.PaymentModeController

import java.text.SimpleDateFormat

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
        if (session.getAttribute("financialYearValid")) {
            String entityId = session.getAttribute('entityId')
            /*ArrayList<String> entity = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>*/
            ArrayList<String> bank = new AccountsService().getBankRegisterByEntity(entityId) as ArrayList
            ArrayList<String> accountMode = new SystemService().getAccountModesByEntity(entityId) as ArrayList
            ArrayList<String> accountRegister = new EntityService().getAllAccountByEntity(entityId) as ArrayList
            ArrayList<String> wallet = new WalletController().show() as ArrayList
            ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
            ArrayList<String> paymodes = new PaymentModeController().show() as ArrayList<String>
            render(view: "/accounts/recipt/customer-recipt-2", model: [/*entity                           : entity,*/ bank  : bank, accountMode: accountMode,
                                                                                                                      wallet: wallet, saleinvoice: saleinvoice, paymodes:
                                                                               paymodes, accountRegister                    : accountRegister])
        }
        else{
            redirect(uri: "/dashboard")
        }
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
            String fromDate, toDate
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute('entityId'))
            if ((params.daterange != null) && (params.daterange != ""))
            {
                System.out.println("date=" + params.daterange.toString())
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
                fromDate = params.daterange.split("-")[0]
                System.out.println("fromdate=" + fromDate.trim())
                jsonObject.put("fromDate",fromDate.trim())
                toDate = params.daterange.split("-")[1]
                jsonObject.put("toDate",toDate.trim())
                System.out.println("toDate=" + toDate.trim())
            }
            else {
                jsonObject.put("fromDate","")
                jsonObject.put("toDate","")
            }
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
                    jsonArray.each {
                        if (it.depositTo!="" && it.depositTo!=null) {
                            def accountResp = new EntityService().getAccountById(it.get("depositTo")?.toString())
                            it.put("deposit", accountResp)
                        }
                        else
                        {
                            it.put("deposit", "NA")
                        }
                        if (it.approvedBy!=0) {
                            def userResp = new EntityService().getUser(it.get("approvedBy")?.toString())
                            it.put("approved", userResp)
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
            def creditNote = new AccountsService().getAllSaleReturnByCustomer(Long.parseLong(params.id), session.getAttribute("entityId"), session.getAttribute("financialYear").toString())
            def gtn = new AccountsService().getAllGTNById(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
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
                jsonObject.put("amountPaid",invoice+goodsTransferNote-credit)
            }
            def apiResponse = new AccountsService().saveReceipt(jsonObject, session.getAttribute('financialYear') as String)
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
                        invObject.put("status","NA")
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
                        crntObject.put("status","NA")
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
                        billLog.put("recieptId", recieptId)
                        billLog.put("transId", transactionId)
                        def billLogResponse = new AccountsService().updateReceiptDetailLog(billLog)
                        if (billLogResponse?.status == 200) {
                            println("Bill Log Saved!")
                        }
                    }
                }

                //email
                def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
                JSONObject receiptEmailConfig
                if(emailSettings!=null){
                    if(emailSettings?.receiptEmailConfig!=null && emailSettings?.receiptEmailConfig!=""){
                        receiptEmailConfig = new JSONObject(emailSettings?.receiptEmailConfig)
                    }
                    if(receiptEmailConfig?.RECEIPT_AUTO_EMAIL_AFTER_SAVE == "true"){
                        def entity = new EntityService().getEntityById(jsonObject1?.receivedFrom?.toString())
                        if(entity?.email!=null && entity?.email!="" && entity?.email!="NA")
                        {
                            def email = new EmailService().sendEmail(entity.email.trim(), "Receipt Saved",
                                    jsonObject1?.receiptId, jsonObject1?.receiptId, "RECEIPT")
                            if (email)
                            {
                                println("Mail Sent..")
                            }
                            else
                            {
                                println("Mail not Sent..")
                            }
                        }
                        else{
                            println("Email not found..")
                        }
                    }
                }
                else{
                    println("Entity Settings not found!!")
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
        JSONArray paymentLogArray = new AccountsService().getPaymentCollectionLogs(params.id)
        render(view: '/accounts/recipt/recipt-temp', model: [customer          : customer, receipt: recipt,
                                                             entity            : entity, reciptloginvArray: reciptloginvArray,
                                                             reciptlogcrntArray: reciptlogcrntArray,
                                                             reciptloggtnArray :reciptloggtnArray,
                                                             paymentLogArray:paymentLogArray
        ])
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
        if (session.getAttribute("financialYearValid")) {
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
            render(view: '/accounts/recipt/receipt-approval', model: [entity: entity])
        }
        else
        {
            redirect(uri: "/dashboard")
        }
    }

    def receiptApprove()
    {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("userId",session.getAttribute("userId").toString())
            def apiResponse = new AccountsService().approveReceipt(jsonObject)
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

    def cancelReceipt()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new AccountsService().cancelReceipt(id, entityId, financialYear)
        def receiptResponse = new AccountsService().getReciptById(id)
        JSONObject receiptObj
        if(receiptResponse?.status == 200){
            receiptObj = new JSONObject(receiptResponse.readEntity(String.class))
        }
        def saleBillResponse
        def saleReturnResponse
        def gtnResponse
        if (jsonObject)
        {
            JSONArray billDetailLogs = jsonObject.get("billDetailLogs")
            if (billDetailLogs)
            {
                for (JSONObject billDetailLog : billDetailLogs)
                {
                    billDetailLog.put("status","CANCELLED")
                    billDetailLog.put('paidNow',billDetailLog.amountPaid)
                    billDetailLog.put('id',billDetailLog.billId)
                    if(billDetailLog.billType == "INVS")
                    {
                        def salebillDetails = new SalesService().getSaleBillDetailsById(billDetailLog.billId.toString())
                        if(salebillDetails!=null){
                            if(salebillDetails.balance!= 0){
                                saleBillResponse = new AccountsService().updateSaleBalance(billDetailLog)
                                if(saleBillResponse?.status==200)
                                {
                                    println("Sale balance updated successfully!")
                                }
                            }
                            else{
                                return
                            }
                        }
                    }
                   /* if(billDetailLog.billType == "CRNT")
                    {
                        saleReturnResponse = new AccountsService().updateSaleReturnBalance(billDetailLog)
                        if(saleReturnResponse?.status==200)
                        {
                            println("Sale Return balance updated successfully!")
                        }
                    }
                    if(billDetailLog.billType == "GTN")
                    {
                        gtnResponse = new AccountsService().updateGTNBalance(billDetailLog)
                        if(gtnResponse?.status==200)
                        {
                            println("Sale Return balance updated successfully!")
                        }
                    }*/
                }
            }
            //email
            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject receiptEmailConfig
            if(emailSettings!=null){
                if(emailSettings?.receiptEmailConfig!=null && emailSettings?.receiptEmailConfig!=""){
                    receiptEmailConfig = new JSONObject(emailSettings?.receiptEmailConfig)
                }
                if(receiptEmailConfig?.RECEIPT_DOC_CANCELLED_SEND_MAIL == "true"){
                    def entity = new EntityService().getEntityById(receiptObj?.receivedFrom?.toString())
                    if(entity?.email!=null && entity?.email!="" && entity?.email!="NA")
                    {
                        def email = new EmailService().sendEmail(entity.email.trim(), "Receipt Cancelled",
                                receiptObj?.receiptId, receiptObj?.receiptId, "RECEIPT")
                        if (email)
                        {
                            println("Mail Sent..")
                        }
                        else
                        {
                            println("Mail not Sent..")
                        }
                    }
                    else{
                        println("Email not found..")
                    }
                }
            }
            else{
                println("Entity Settings not found!!")
            }
            respond jsonObject, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }



}
