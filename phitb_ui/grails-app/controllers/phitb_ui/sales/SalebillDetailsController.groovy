package phitb_ui.sales

import com.google.gson.JsonObject
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.SalesService
import phitb_ui.system.AccountModeController

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
        render(view: '/sales/sale-entry', model: [series      : series, accountMode: accountMode, entity: entity,
                                                           users       : users, customerList: customerList,
                                                           salesmanList: salesmanList, salebilllist:salebilllist])
    }



    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().saveSaleBill(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: '/user-register')
//                respond obj, formats: ['json'], status: 200
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
            def apiResponse = new SalesService().p(jsonObject)
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


    def show()
    {
        try
        {
            def apiResponse = new SalesService().getSaleBillDetails()
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


    def getAllSettledById(String id,String entityId, String financialYear)
    {
        try
        {
            def apiResponse = new SalesService().getAllSettledBillsByCustomer(id,entityId,financialYear)
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

    def getAllUNSettledById(String id,String entityId, String financialYear)
    {
        try
        {
            def apiResponse = new SalesService().getAllUNSettledBillsByCustomer(id,entityId,financialYear)
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

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().showSalesService(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {
                    JSONArray jsonArray = responseObject.data
                    JSONArray jsonArray2 = new JSONArray()
                    for (JSONObject json : jsonArray) {
                        json.put("customer", new EntityService().getEntityById(json.get("customerId").toString()))
                        jsonArray2.put(json)
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


    def saleBillList()
    {
        render(view:'/sales/salebillDetails/saleBill')
    }


}
