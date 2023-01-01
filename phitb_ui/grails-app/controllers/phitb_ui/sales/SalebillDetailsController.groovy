package phitb_ui.sales

import phitb_ui.AccountsService
import phitb_ui.EInvoiceService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SystemService
import phitb_ui.Tools
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.SalesService
import phitb_ui.system.AccountModeController

import java.nio.file.Files
import java.nio.file.Path
import java.text.SimpleDateFormat

class SalebillDetailsController {

    def index() {

        ArrayList<String> series = new SeriesController().show() as ArrayList<String>
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> salebilllist = new SalebillDetailsController().show() as ArrayList<String>
        ArrayList<String> customerList = []
        ArrayList<String> salesmanList = []
        entity.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_CUSTOMER)) {
                customerList.add(it)
            }
        }
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }
        render(view: '/sales/saleEntry/sale-entry', model: [series      : series, accountMode: accountMode, entity: entity,
                                                            users       : users, customerList: customerList,
                                                            salesmanList: salesmanList, salebilllist: salebilllist])
    }


    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().saveSaleBill(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: '/user-register')
//                respond obj, formats: ['json'], status: 200
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
            def apiResponse = new SalesService().updateSaleBill(jsonObject)
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


    def show() {
        try {
            def apiResponse = new SalesService().getSaleBillDetails()
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


    def getAllSettledById(String id, String entityId, String financialYear) {
        try {
            def apiResponse = new SalesService().getAllSettledBillsByCustomer(id, entityId, financialYear)
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

    def getAllUNSettledById(String id, String entityId, String financialYear) {
        try {
            def apiResponse = new SalesService().getAllUNSettledBillsByCustomer(id, entityId, financialYear)
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

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            if (session.getAttribute("role").toString().equalsIgnoreCase(Constants.ENTITY_ADMIN))
                jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new SalesService().showSalesService(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("customerId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
//                        JSONArray saleReturnAdjustmentDetails = new SalesService().getSaleReturnAdjustmentDetails(json.get("id").toString(), "INVS")
                        def receiptResponse = new AccountsService().getReceiptLogByBillTypeAndId(json.get("id").toString(), "INVS")
                        def receiptLog = []
                        if (receiptResponse?.status == 200) {
                            JSONArray receipt = new JSONArray(receiptResponse.readEntity(String.class))
                            for (JSONObject r : receipt) {
                                if (r?.receiptStatus != "CANCELLED") {
                                    receiptLog.push(r);
                                }
                            }
                        }
                        customer?.put("city", city)
                        json.put("customer", customer)
//                        json.put("saleReturnAdjustmentDetails",saleReturnAdjustmentDetails)
                        json.put("receiptLog", receiptLog)
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


    def saleBillList() {
        String entityId = session.getAttribute("entityId").toString()
        ArrayList<JSONObject> bank = new AccountsService().getBankRegisterByEntity(entityId) as ArrayList
        ArrayList<JSONObject> paymentModes = new ArrayList<>()
        def apiResponse = new SystemService().getPaymentModes()
        if (apiResponse.status == 200) {
            paymentModes = new JSONArray(apiResponse.readEntity(String.class))
            paymentModes = paymentModes.reverse()
        }

        def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
        def entityConfigs = new EntityService().getEntityConfigByEntity(session.getAttribute('entityId').toString())

        ArrayList<JSONObject> accountMode = new SystemService().getAccountModesByEntity(entityId) as ArrayList
        ArrayList<JSONObject> accountRegister = new EntityService().getAllAccountByEntity(entityId) as ArrayList
        render(view: '/sales/salebillDetails/saleBill', model: [bank           : bank,
                                                                accountMode    : accountMode,
                                                                paymentModes   : paymentModes,
                                                                accountRegister: accountRegister,
                                                                settings       : settings,
                                                                entityConfigs  : entityConfigs,
        ])
    }


    def recordPayment() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy")
        String financialYear = session.getAttribute("financialYear")
        String saleBillId = params.saleBillId
        JSONObject saleBill = new SalesService().getSaleBillDetailsById(saleBillId)
        if (saleBill.billStatus != "ACTIVE") {
            render(text: "This invoice is cancelled already", status: 403)
            return
        }
        double amount = Double.parseDouble(params.amount)
        String paymentMode = params.paymentMode
        String paymentMethod = params.paymentMethod //accountMode
        String depositTo = params.depositTo
        String payeeBanker = params.payeeBanker
        String cardNumber = params.cardNumber
        String paymentDate = params.paymentDate
        String instrumentId = params.instrumentId
        String remarks = params.remarks

        JSONObject receipt = new JSONObject()
        receipt.put("date", sdf2.format(new Date()))
        receipt.put("paymentMode", paymentMode)
        receipt.put("accountMode", paymentMethod)
        receipt.put("receivedFrom", saleBill.get("customerId"))
        receipt.put("depositTo", depositTo)
        receipt.put("amountPaid", amount)
        receipt.put("narration", remarks)
        receipt.put("paymentDate", sdf.parse(paymentDate).format("dd/MM/yyyy"))
        receipt.put("chequeNumber", "")
        receipt.put("bank", payeeBanker)
        receipt.put("wallet", 0)
        receipt.put("financialYear", financialYear)
        receipt.put("status", 1)
        receipt.put("syncStatus", 1)
        receipt.put("entityTypeId", session.getAttribute("entityTypeId"))
        receipt.put("entityId", session.getAttribute("entityId"))
        receipt.put("modifiedUser", session.getAttribute("userId"))
        receipt.put("createdUser", session.getAttribute("userId"))
        receipt.put("cardNumber", cardNumber)
        def receiptResponse = new AccountsService().saveReceipt(receipt, financialYear)
        if (receiptResponse.status == 200) {
            JSONObject savedReceipt = new JSONObject(receiptResponse.readEntity(String.class))
            String receiptId = savedReceipt.id.toString()
            if (amount != 0) {
                JSONObject invObject = new JSONObject()
                invObject.put("id", saleBill.id)
                invObject.put("paidNow", amount)
                invObject.put("status", "NA")
                invObject.put("docId", receiptId) //link receipt with Sale Return Adjustment log
                invObject.put("docType", "CRNT")
                def invs = new AccountsService().updateSaleBalance(invObject)
                if (invs?.status == 200) {
                    println("Invoice Updated")

                    JSONObject billLog = new JSONObject()
                    billLog.put("billId", saleBill.id)
                    billLog.put("billType", "INVS")
                    billLog.put("amountPaid", amount)
                    billLog.put("currentFinancialYear", session.getAttribute('financialYear').toString())
                    billLog.put("financialYear", session.getAttribute('financialYear').toString())
                    billLog.put("recieptId", receiptId)
                    billLog.put("transId", saleBill.invoiceNumber)
                    def billLogResponse = new AccountsService().updateReceiptDetailLog(billLog)
                    if (billLogResponse?.status == 200) {
                        println("Bill Log Saved!")
                    }
                }
            }
        }
        respond saleBill, formats: ['json']
    }

    def adjustCredits() {
        String saleBillId = params.saleBillId
        double creditsApplied = Double.parseDouble(params.creditsApplied)
        JSONObject saleBill = new SalesService().getSaleBillDetailsById(saleBillId)

        if (saleBill.billStatus != "ACTIVE") {
            render(text: "This invoice is cancelled already", status: 403)
            return
        }

        String saleReturnIds = params.saleReturnIds

        if (saleReturnIds && saleReturnIds.endsWith(",")) {
            saleReturnIds = saleReturnIds.substring(0, saleReturnIds.length() - 1);
        }

        double totalBalance = saleBill.balance
        if (creditsApplied > totalBalance) {
            print("Applied credits is greater than balance: " + creditsApplied + " > " + totalBalance)
            //reject this
            response.status = 400
            return
        }

        if (creditsApplied != 0) {
            JSONObject invObject = new JSONObject()
            invObject.put("id", saleBill.id)
            invObject.put("userId", session.getAttribute("userId"))
            invObject.put("status", "NA")
            invObject.put("creditsApplied", creditsApplied)
            invObject.put("saleReturnIds", saleReturnIds)
            invObject.put("docId", saleBill.id) //link invoice with Sale Return Adjustment log
            invObject.put("docType", "INVS")
            def invs = new AccountsService().updateSaleBalanceAndCredit(invObject)
            if (invs?.status == 200) {
                println("Invoice Updated")
            } else {
                println("Error Updating Invoice")
            }
        }
        respond saleBill, formats: ['json']
    }

    def getSaleBillById() {
        String id = params.id
        JSONObject saleBill = new SalesService().getSaleBillDetailsById(id)
        JSONArray saleProducts = new SalesService().getSaleProductDetailsByBill(saleBill.id.toString())
        JSONArray finalSaleProducts = new JSONArray()
        for (Object saleProduct : saleProducts) {
            JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
            saleProduct.put("product", product)
            finalSaleProducts.add(saleProduct)
        }
        JSONObject billEntity = new EntityService().getEntityById(saleBill.entityId.toString())
        JSONObject customer = new EntityService().getEntityById(saleBill.customerId.toString())
        JSONObject entityCity = new SystemService().getCityById(billEntity.cityId.toString())
        JSONObject customerCity = new SystemService().getCityById(customer.cityId.toString())
        def apiResponse = new AccountsService().getReceiptLogByBillTypeAndId(saleBill.id.toString(), "INVS")
        JSONArray receiptLog = new JSONArray()
        if (apiResponse.status == 200) {
            receiptLog = new JSONArray(apiResponse.readEntity(String.class))
        }

        for (JSONObject rLog : receiptLog) {
            String receiptId = rLog.receiptId.toString()
            apiResponse = new AccountsService().getReciptById(receiptId)
            if (apiResponse.status == 200) {
                rLog.put("receipt", new JSONObject(apiResponse.readEntity(String.class)))
            }
        }
        JSONArray saleReturnAdjustmentDetails = new SalesService().getSaleReturnAdjustmentDetails(saleBill.id.toString(), "INVS")
        //Credit Note / Sale return
        def saleReturnApiResponse = new AccountsService().getAllSaleReturnByCustomer(customer.id, session.getAttribute("entityId"), session.getAttribute("financialYear"), "ACTIVE")
        JSONArray saleReturns = new JSONArray()
        if (saleReturnApiResponse.status == 200) {
            saleReturns = new JSONArray(saleReturnApiResponse.readEntity(String.class))
        }

        JSONObject result = new JSONObject()
        result.put("invoice", saleBill)
        result.put("entity", billEntity)
        result.put("customer", customer)
        result.put("customerCity", customerCity)
        result.put("entityCity", entityCity)
        result.put("saleProducts", finalSaleProducts)
        result.put("receiptLog", receiptLog?.reverse())
        result.put("saleReturns", saleReturns)
        result.put("saleReturnAdjustmentDetails", saleReturnAdjustmentDetails)

        respond result, formats: ['json']
    }

    def exportGSTEInvoiceJSON() {
        try {
            String entityId = session.getAttribute("entityId").toString()
            String financialYear = session.getAttribute("financialYear").toString()
            JSONArray finalJson = new JSONArray()
            JSONArray saleBillDetails = new SalesService().getSaleBillDetailsByPendingIRN(financialYear, entityId)
            if(saleBillDetails) {
                println("Total Bills: " + saleBillDetails?.size())
                int i = 0;
                for (Object saleBillDetail : saleBillDetails) {
                    try {
                        println("Bill: " + (++i))
                        String json = EInvoiceService.buildIrnPayload(saleBillDetail, saleBillDetail.products)
                        if (json) {
                            JSONObject jsonObject = new JSONObject(json)
                            if (jsonObject)
                                finalJson.add(jsonObject)
                        }
                    }
                    catch (Exception ex) {
                        println("exportGSTEInvoiceJSON: Error in JSONObject, Skipping and moving to next one\n " + ex.printStackTrace())
                    }
                }
                render(text: finalJson.toString(), status: 200)
            }
            else
                response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def exportSingleGSTEInvoiceJSON() {
        try {
            String id = params.id
            String entityId = session.getAttribute("entityId").toString()
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(id)
            if(saleBillDetail && saleBillDetail.entityId.toString() == entityId) {
                JSONArray finalJson = new JSONArray()
                def products =new SalesService().getSaleProductDetailsByBill(saleBillDetail.id.toString())
                String json = EInvoiceService.buildIrnPayload(saleBillDetail, products)
                if (json) {
                    JSONObject jsonObject = new JSONObject(json)
                    if (jsonObject) {
                        finalJson.add(jsonObject)
                        render(text: finalJson, status: 200)
                    }
                    else
                        response.status = 400
                }
                else
                    response.status = 400
            }
            else
                response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex.printStackTrace())
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex.printStackTrace())
            response.status = 400
        }
    }

    def genrateIrn(){
        String billId = params.id
        def saleBillDetail = new SalesService().getSaleBillDetailsById(billId)
        if(saleBillDetail){
            String billStatus =  saleBillDetail.billStatus
            def saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillDetail.id.toString())
            try
            {
                if (billStatus.equalsIgnoreCase("ACTIVE"))
                {
                    //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                   def result = new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                    respond result, formats: ['json'],status: 200
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace()
            }

        }
    }


    def retailerBillDetails(){
        render(view: '/sales/saleEntry/retailer-bill-list')
    }

}
