package phitb_ui.accounts

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.Constants
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.entity.CustomerGroupController
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.UserRegisterController
import phitb_ui.product.DivisionController
import phitb_ui.sales.SaleEntryController
import phitb_ui.sales.SalebillDetailsController
import phitb_ui.system.AccountModeController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.PaymentModeController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

import javax.swing.text.html.parser.Entity

class ReciptDetailController
{

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

    def index()
    {
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
        ArrayList<String> wallet = new WalletController().show() as ArrayList
        ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
        ArrayList<String> paymodes = new PaymentModeController().show() as ArrayList<String>
        render(view: "/accounts/recipt/customer-recipt-2", model: [entity: entity, bank: bank, accountMode: accountMode,
                                                                 wallet: wallet, saleinvoice: saleinvoice,paymodes:
                                                                         paymodes])
    }



    def reciptList()
    {
        render(view: '/accounts/recipt/recipt-list')
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



    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().showRecipt(jsonObject)
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


    def getAllUNSaleBillCustomerId()
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            def salebill = new AccountsService().getUnSaleBillCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getCNUnsettledCustomerId(params.id,session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status == 200)
            {
//                def tmp = creditNote.readEntity(String.class)
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
                respond jsonArray, formats: ['json'], status: 200
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


    def getAllBillDetailsByCustomerId()
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            def salebill = new AccountsService().getAllSaleBillById(params.id,session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getAllSaleReturnById(params.id,session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status == 200)
            {
//                def tmp = creditNote.readEntity(String.class)
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
                respond jsonArray, formats: ['json'], status: 200
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
            def salebill = new AccountsService().getSaleBillSettledCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            def creditNote = new AccountsService().getCNsettledCustomerId(params.id, session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString())
            if (salebill.status == 200 && creditNote.status)
            {
                JSONArray salearray = new JSONArray(salebill.readEntity(String.class))
                JSONArray creditNoteArry = new JSONArray(creditNote.readEntity(String.class))
                jsonArray.add(salearray)
                jsonArray.add(creditNoteArry)
                respond jsonArray, formats: ['json'], status: 200
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


    def creditSettledVocher()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateCNSettledVocher(jsonObject)
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

    def creditUnsettledVocher()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateCNunSettledVocher(jsonObject)
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

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            JSONArray billArray = new JSONArray(params.reciptData)
            def apiResponse = new AccountsService().saveRecipt(jsonObject, session.getAttribute('financialYear') as String)
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                for (JSONObject bills : billArray)
                {
                    String paidNow = bills.get("PaidNow")
                    String BalAmt = bills.get("BalAmt")
                    String transactionId = bills.get("Trans_Id")
                    String docType = bills.get("Doc.Type")
                    String billId = bills.get("BillId")
                    String recieptId = jsonObject1.id.toString()
                    if(docType=="INVS")
                    {
                        JSONObject invObject = new JSONObject()
                        invObject.put("id",billId)
                        invObject.put("paidNow",paidNow)
                        def invoice = new AccountsService().updateSaleBalance(invObject)
                        if(invoice?.status == 200)
                        {
                            invObject.remove("id");
                            invObject.remove("paidNow");
                        }
                    }
                    if(docType=="CRNT")
                    {
                        JSONObject crntObject = new JSONObject();
                        crntObject.put("id",billId)
                        crntObject.put("paidNow",paidNow)
                        def crnt = new AccountsService().updateSaleReturnBalance(crntObject)
                        if(crnt?.status == 200)
                        {
                            crntObject.remove("id");
                            crntObject.remove("paidNow");
                        }
                    }
                    JSONObject billLog = new JSONObject()
                    billLog.put("billId",billId)
                    billLog.put("billType",docType)
                    billLog.put("amountPaid",paidNow)
                    billLog.put("currentFinancialYear",session.getAttribute('financialYear').toString())
                    billLog.put("financialYear",session.getAttribute('financialYear').toString())
                    billLog.put("recieptId",recieptId)
                    billLog.put("transId",transactionId)
                    def billLogResponse = new AccountsService().updateReceiptDetailLog(billLog)
                    if(billLogResponse?.status == 200)
                    {
                        println("Bill Log Saved!")
                    }
                }
                respond jsonObject1, formats: ['json'], status: 200
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
        if (cust.status == 200)
        {
            JSONObject customer = new JSONObject(cust.readEntity(String.class))
            return customer
        }
        else
        {

            return []
        }
    }


    def getReciptById(String id)
    {
        def recipt1 = new AccountsService().getReciptById(id)
        if (recipt1.status == 200)
        {
            JSONObject recipt = new JSONObject(recipt1.readEntity(String.class))
            return recipt
        }
        else
        {

            return []
        }
    }


    def printRecipt()
    {
        JSONObject customer = new EntityRegisterController().getEnitityById(params.custid) as JSONObject
        JSONObject recipt = new ReciptDetailController().getReciptById(params.id) as JSONObject
        JSONObject entity = new EntityRegisterController().getEnitityById(session.getAttribute('entityId').toString()) as
                JSONObject
//        ArrayList<String> settled = new SalebillDetailsController().getAllSettledById(params.custid,session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString()) as ArrayList
//        def creditNoteRespone = new AccountsService().getCNsettledCustomerId(params.custid, session.getAttribute
//        ("entityId").toString(), session.getAttribute("financialYear").toString())
//        JSONArray creditNoteArry = new JSONArray(creditNoteRespone.readEntity(String.class))
        JSONObject reciptlogs = new AccountsService().getReceiptLogById(params.id) as JSONObject

        render(view: '/accounts/recipt/recipt-temp', model: [customer: customer, settled: settled, recipt: recipt,
                                                             entity: entity,reciptlogs:reciptlogs])
    }


    def updateSaleBalance()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put('balance',params.balance)
            jsonObject.put('id',params.id)
            println(jsonObject)
            def apiResponse = new AccountsService().updateSaleBalance(jsonObject)
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

    def updateSaleReturnBalance()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put('balance',params.balance)
            jsonObject.put('id',params.id)
            println(jsonObject)
            def apiResponse = new AccountsService().updateSaleReturnBalance(jsonObject)
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

    def updateRecieptLog()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().updateReceiptDetailLog(jsonObject)
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


}
