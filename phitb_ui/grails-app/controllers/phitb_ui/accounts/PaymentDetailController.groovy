package phitb_ui.accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.ProductService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.sales.SalebillDetailsController
import phitb_ui.system.AccountModeController

class PaymentDetailController {

    def index()
    {
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
        ArrayList<String> wallet = new WalletController().show() as ArrayList
        ArrayList<String> saleinvoice = new SalebillDetailsController().show() as ArrayList
        render(view: "/accounts/recipt/payments", model: [entity: entity, bank: bank, accountMode: accountMode,
                                                                 wallet: wallet, saleinvoice: saleinvoice])
    }

//    def addRecipt()
//    {
//        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList
//        ArrayList<String> bank = new BankRegisterController().show() as ArrayList
//        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList
//        ArrayList<String> wallet = new WalletController().show() as ArrayList
//        render(view: '/accounts/recipt/add-recipt', model: [entity: entity, bank: bank, accountMode: accountMode, wallet: wallet])
//    }

    def paymentList()
    {
        render(view: '/accounts/recipt/payment-list')
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
            def apiResponse = new AccountsService().showPayments(jsonObject)
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

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new AccountsService().savePaymentDetail(jsonObject,session.getAttribute('financialYear') as String)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri:'/payments')
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
        if(cust.status==200)
        {
            JSONObject customer = new JSONObject(cust.readEntity(String.class))
            return customer
        }
        else {

            return []
        }
    }


    def getPaymentById(String id)
    {
        def payment1 = new AccountsService().getPaymentById(id)
        if(payment1.status==200)
        {
            JSONObject payment = new JSONObject(payment1.readEntity(String.class))
            return payment
        }
        else {

            return []
        }
    }


    def printPayment()
    {
        JSONObject customer = new EntityRegisterController().getEnitityById(params.custid)  as JSONObject
        JSONObject payment = new PaymentDetailController().getPaymentById(params.id)  as JSONObject
        JSONObject entity = new EntityRegisterController().getEnitityById(session.getAttribute('entityId').toString())  as
                JSONObject
        ArrayList<String> unsettled = new SalebillDetailsController().getAllUNSettledById(params.custid,session.getAttribute("entityId").toString(), session.getAttribute("financialYear").toString()) as ArrayList
        render(view:'/accounts/recipt/payment-vocher',model: [customer:customer,unsettled:unsettled,payment:payment,
                                                         entity:entity])
    }

}
