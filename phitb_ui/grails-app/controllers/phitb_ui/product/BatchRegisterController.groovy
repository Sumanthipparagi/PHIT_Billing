package phitb_ui.product


import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.entity.CustomerGroupController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

import java.text.SimpleDateFormat

class BatchRegisterController
{

    def index()
    {
        try
        {
            ArrayList<String> customer = new CustomerGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().getByEntity() as ArrayList<String>
            ArrayList<String> series = new SeriesController().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productlist = new ProductService().getProductByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productcatList = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())

            render(view: '/product/batchRegister/batchRegister', model: [entity        : entity, statelist: statelist,
                                                                         countrylist   : countrylist, citylist: citylist,
                                                                         zoneList      : zoneList,
                                                                         customer      : customer, series: series,
                                                                         managerList   : managerList,
                                                                         productlist   : productlist,
                                                                         settings      : settings,
                                                                         productcatList: productcatList])
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
            jsonObject.put("entityId", session.getAttribute('entityId'))
            def apiResponse = new ProductService().showBatchRegister(jsonObject)
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

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().saveBatchRegister(jsonObject)
            if (apiResponse?.status == 200)
            {
                // JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: "/batch-register")
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
            def apiResponse = new ProductService().putBatchRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                //JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                //respond obj, formats: ['json'], status: 200
                redirect(uri: "/batch-register")
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
            def apiResponse = new ProductService().deleteBatchRegister(jsonObject)
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
            def apiResponse = new ProductService().getBatchRegister()
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


    def getByProduct()
    {
        try
        {
            String id = params.id
            def apiResponse = new ProductService().getBatchesOfProduct(id)
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                respond jsonArray, formats: ['json']
            }
            else
            {
                response.status = apiResponse?.status
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getBatchesForPurchase()
    {
        String productId = params.id
        def apiResponse = new ProductService().getBatchesOfProduct(productId)
        if (apiResponse?.status == 200)
        {
            JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : stockBookData)
            {
                String id = json["product"]["taxId"]
                def tax = new TaxController().show(id)
                println(tax.taxValue)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            respond responseArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }
    }

    def savebulkBatchRegister()
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd')
            JSONArray params = new JSONArray(params.batchData)
            JSONArray productArray = new JSONArray()
            JSONObject responseObject = new JSONObject()
            for (JSONObject jsonObject : params)
            {
                jsonObject.put("productId", jsonObject.get('0'))
                jsonObject.put("batchNumber", jsonObject.get('1'))
                jsonObject.put("box", jsonObject.get('2'))
                jsonObject.put("qty", jsonObject.get('3'))
                jsonObject.put("expiryDate", sdf.parse(jsonObject.get('4').toString()))
                jsonObject.put("saleRate", jsonObject.get('5'))
                jsonObject.put("mrp", jsonObject.get('6'))
                jsonObject.put("ptr", jsonObject.get('7'))
                jsonObject.put("purchaseRate", jsonObject.get('8'))
                jsonObject.put("manfDate", sdf.parse(jsonObject.get('9').toString()))
                jsonObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                jsonObject.put("entityId", session.getAttribute('entityId'))
                jsonObject.put("createdUser", session.getAttribute('createdUser'))
                jsonObject.put("modifiedUser", session.getAttribute('createdUser'))
                productArray.add(jsonObject)
            }
            JSONObject jsonObject = new ProductService().saveBulkBatchRegister(productArray)
            if (jsonObject != null)
            {
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400;
            }
        }
        catch (Exception ex)
        {
            println(ex)
        }

    }
}
