package phitb_ui.sales

import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.SalesService
import phitb_ui.system.AccountModeController

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
            def apiResponse = new SalesService().p(jsonObject)
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
            String userId = session.getAttribute("userId")
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("userId", userId)
            def apiResponse = new SalesService().showSalesService(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("customerId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
                        customer?.put("city", city)
                        json.put("customer", customer)
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
        if(apiResponse.status == 200)
        {
            paymentModes = new JSONArray(apiResponse.readEntity(String.class))
            paymentModes = paymentModes.reverse()
        }

        ArrayList<JSONObject> accountMode = new SystemService().getAccountModesByEntity(entityId) as ArrayList
        ArrayList<JSONObject> accountRegister = new EntityService().getAllAccountByEntity(entityId) as ArrayList
        render(view: '/sales/salebillDetails/saleBill', model: [bank           : bank,
                                                                accountMode    : accountMode,
                                                                paymentModes: paymentModes,
                                                                accountRegister: accountRegister])
    }


    def recordPayment()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy")
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy")
        String financialYear = session.getAttribute("financialYear")
        String saleBillId = params.saleBillId
        JSONObject saleBill = new SalesService().getSaleBillDetailsById(saleBillId)
        double totalBalance = saleBill.balance

        String saleReturnIds = params.saleReturnIds
        double creditsApplied = Double.parseDouble(params.creditsApplied)
        double amount = Double.parseDouble(params.amount)
        String paymentMode = params.paymentMode
        String paymentMethod = params.paymentMethod //accountMode
        String depositTo = params.depositTo
        String payeeBanker = params.payeeBanker
        String cardNumber = params.cardNumber
        String paymentDate = params.paymentDate
        String instrumentId = params.instrumentId
        String remarks = params.remarks

        if((amount+creditsApplied) > totalBalance)
        {
            //reject this
            response.status = 400
            return
        }


        if(saleReturnIds)
        {
            double usedCredits = 0
            //TODO: 1. Add Sale Return Adjustment Log
            //TODO: 2. Update Sale Return
            //Credit Note / Sale return
            def saleReturnApiResponse = new AccountsService().getAllSaleReturnByCustomer(saleBill.customerId, session.getAttribute("entityId"), session.getAttribute("financialYear"), "ACTIVE")
            JSONArray saleReturns = new JSONArray()
            if (saleReturnApiResponse.status == 200) {
                saleReturns = new JSONArray(saleReturnApiResponse.readEntity(String.class))
            }
            for (JSONObject saleReturn : saleReturns) {
                if(usedCredits <= creditsApplied) {
                    double bal = saleReturn.balance - creditsApplied
                    JSONObject jsonObject = new JSONObject(params)
                    jsonObject.put('balance', bal)
                    jsonObject.put('id', saleReturn.id)
                    jsonObject.put("paidNow", amount)
                    jsonObject.put("status", "NA")
                    def saleReturnUpdate = new AccountsService().updateSaleReturnBalance(saleReturn)
                    if (saleReturnUpdate.status == 200) {
                        usedCredits += creditsApplied
                        //amount += usedCredits //adding credits to paid amount TODO: this should be changed
                        println("Sales Return updated")
                    }
                }
                else
                {
                    println("Available credits utilized")
                }
            }
        }

       // String creditadjAmount = saleBill.get("creditadjAmount")
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
        receipt.put("wallet",0)
        receipt.put("financialYear", financialYear)
        receipt.put("status", 1)
        receipt.put("syncStatus", 1)
        receipt.put("entityTypeId", session.getAttribute("entityTypeId"))
        receipt.put("entityId", session.getAttribute("entityId"))
        receipt.put("modifiedUser", session.getAttribute("userId"))
        receipt.put("createdUser", session.getAttribute("userId"))
        receipt.put("cardNumber", cardNumber)
        def receiptResponse = new AccountsService().saveReceipt(receipt, financialYear)
        if(receiptResponse.status == 200)
        {
            JSONObject savedReceipt = new JSONObject(receiptResponse.readEntity(String.class))
            String recieptId = savedReceipt.id.toString()
            if (amount.toDouble()!=0) {
                JSONObject invObject = new JSONObject()
                invObject.put("id", saleBill.id)
                invObject.put("paidNow", amount)
                invObject.put("status","NA")
                def invs = new AccountsService().updateSaleBalance(invObject)
                if (invs?.status == 200) {
                    println("Invoice Updated")

                    JSONObject billLog = new JSONObject()
                    billLog.put("billId", saleBill.id)
                    billLog.put("billType", "INVS")
                    billLog.put("amountPaid", amount)
                    billLog.put("currentFinancialYear", session.getAttribute('financialYear').toString())
                    billLog.put("financialYear", session.getAttribute('financialYear').toString())
                    billLog.put("recieptId", recieptId)
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
        String receiptId = null
        JSONArray receiptLog = new JSONArray()
        if (apiResponse.status == 200) {
            receiptLog = new JSONArray(apiResponse.readEntity(String.class))
        }
        for (Object r : receiptLog) {
            receiptId = r.receiptId.toString()
            break
        }

        JSONObject receipt = new JSONObject()
        apiResponse = new AccountsService().getReciptById(receiptId)
        if (apiResponse.status == 200) {
            receipt = new JSONObject(apiResponse.readEntity(String.class))
        }

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
        result.put("receiptLog", receiptLog)
        result.put("receipt", receipt)
        result.put("saleReturns", saleReturns)

        respond result, formats: ['json']
    }
}
