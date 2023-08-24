package phitb_ui.accounts

import netscape.javascript.JSObject
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.SalesService
import phitb_ui.SystemService

import java.text.SimpleDateFormat

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

        render(view: 'index', model: [bank  : bank,
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
            JSONArray modifiedData = new JSONArray()
            JSONArray data = saleInvoices.get("data")
            for (JSONObject saleInvoice : data) {
                JSONObject customer = new EntityService().getEntityById(saleInvoice.customerId.toString())
                saleInvoice.put("customer", customer)
                modifiedData.put(saleInvoice)
            }
            saleInvoices.put("data", modifiedData)
            respond saleInvoices, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }


    def dataTable() {
        try {
            String fromDate, toDate
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute('entityId'))
            jsonObject.put("userId", session.getAttribute('userId'))
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
            def apiResponse = new AccountsService().showPaymentCollection(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    for(JSONObject jsonObject1:jsonArray){
                        def entity = new EntityService().getEntityById(jsonObject1.entityId.toString())
                        jsonObject1.put('entityId',entity)
                        def receiptResponse = new AccountsService().getReciptById(jsonObject1.receiptId.toString())
                        if(receiptResponse?.status == 200){
                            JSONObject receiptObj = new JSONObject(receiptResponse.readEntity(String.class))
                            jsonObject1.put('receipt',receiptObj)
                            if(receiptObj?.depositTo!=null){
                                def bank = new AccountsService().getBankById(receiptObj?.depositTo)
                                if(bank?.status ==200){
                                    JSONObject bankObj = new JSONObject(bank.readEntity(String.class))
                                    jsonObject1.put("bank", bankObj)
                                }
                            }
                        }

                    }
                    responseObject.put("data", jsonArray)
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

    def paymentCollectionLogs(){
        render(view:"/paymentCollection/paymentCollectionLogs")
    }

    def paymentCollectionChangeStatus(){
        try{
            def changeStatus = new AccountsService().changeStatusPaymentCollection(params.id,params.status)
            if(changeStatus){
                respond changeStatus,formats: ['json'],status: 200
            }
        }
        catch (Exception ex){
            println(controllerName+" "+ex)
        }
    }

    def approveAllPaymentCollection(){
        try{
            JSONArray jsonArray = new JSONArray(params.pcData)
            def updatePaymentCollections = new AccountsService().approveAllPaymentCollection(jsonArray)
            def saleBillResponse
            if(updatePaymentCollections.size()!=0 && updatePaymentCollections){
                for(JSONObject pc: updatePaymentCollections){
                    if(pc.status=="CANCELLED" || pc.status == "RETURNED"){
                      def cancelReceipt = new AccountsService().cancelReceiptPayments(pc.receiptId.toString())
                        JSONArray billDetailLogs = cancelReceipt.get("billDetailLogs") as JSONArray
                        if (billDetailLogs)
                        {
                            for (JSONObject billDetailLog : billDetailLogs)
                            {
                                billDetailLog.put("status","CANCELLED")
                                billDetailLog.put('paidNow',billDetailLog.amountPaid)
                                billDetailLog.put('id',billDetailLog.billId)
                                println(billDetailLog.transId)
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
                            }
                        }
                    }
                }
                respond updatePaymentCollections,formats: ['json'],status: 200
            }
        }
        catch (Exception ex){
            println(controllerName+" "+ex)
        }
    }

    def updateBulkPaymentCollection(){
        try{
            JSONArray jsonArray = new JSONArray(params.pcData)
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("pcData",jsonArray)
            jsonObject.put("status",params.bulkStatus)
            def updatePaymentCollections = new AccountsService().updatePaymentColletionBulkUpdate(jsonObject)
            def saleBillResponse
            if(updatePaymentCollections.size()!=0 && updatePaymentCollections){
                for(JSONObject pc: updatePaymentCollections){
                    if(pc.check!=""){
                        if(params.bulkStatus=="CANCELLED" || params.bulkStatus == "RETURNED"){
                            def cancelReceipt = new AccountsService().cancelReceiptPayments(pc.receiptId.toString())
                            JSONArray billDetailLogs = cancelReceipt.get("billDetailLogs") as JSONArray
                            if (billDetailLogs)
                            {
                                for (JSONObject billDetailLog : billDetailLogs)
                                {
                                    billDetailLog.put("status","CANCELLED")
                                    billDetailLog.put('paidNow',billDetailLog.amountPaid)
                                    billDetailLog.put('id',billDetailLog.billId)
                                    println(billDetailLog.transId)
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
                                }
                            }
                        }
                    }
                }
                respond updatePaymentCollections,formats: ['json'],status: 200
            }
        }
        catch (Exception ex){
            println(controllerName+" "+ex)
        }
    }


}
