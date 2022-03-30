package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.product.BatchRegisterController
import phitb_ui.product.ProductController
import phitb_ui.system.CityController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class SchemeEntryController {

    def index() {
        JSONArray products = new ProductService().getProductsByEntityId(session.getAttribute("entityId").toString())
        render(view: "/sales/schemeEntry/index", model: [products:products])
    }


    def addScheme()
    {
        ArrayList<String> zoneList = new ZoneController().show()
        ArrayList<String> stateList = new StateController().show() as ArrayList<String>
        ArrayList<String> cityList = new CityController().show() as ArrayList<String>
        ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> productList = new ProductController().show() as ArrayList<String>
        ArrayList<String> batchList = new BatchRegisterController().show() as ArrayList<String>
        ArrayList<String> distributorList = []
        entityList.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_DISTRIBUTOR))
            {
                distributorList.add(it)
            }
        }
        render(view: '/sales/schemeEntry/add-scheme',model: [zoneList       :zoneList, stateList:stateList,
                                                             cityList       :cityList,
                                                             distributorList:distributorList,
                                                             productList    :productList, batchList:batchList,
                                                             entityList     :entityList])
    }

    def updateScheme()
    {
        JSONObject scheme = new SalesService().getSchemeById(params.id)
        ArrayList<String> zoneList = new ZoneController().show()
        ArrayList<String> stateList = new StateController().show() as ArrayList<String>
        ArrayList<String> cityList = new CityController().show() as ArrayList<String>
        ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> productList = new ProductController().show() as ArrayList<String>
        ArrayList<String> batchList = new BatchRegisterController().show() as ArrayList<String>
        ArrayList<String> distributorList = []
        entityList.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_DISTRIBUTOR))
            {
                distributorList.add(it)
            }
        }
        render(view: '/sales/schemeEntry/edit-scheme',model: [zoneList       :zoneList, stateList:stateList,
                                                             cityList       :cityList,
                                                             distributorList:distributorList,
                                                             productList    :productList, batchList:batchList,
                                                             entityList     :entityList,scheme:scheme])
    }



    def saveScheme()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().saveScheme(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri:'/scheme-entry')
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
            def apiResponse = new SalesService().showScheme(jsonObject)
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


    def update()
    {
        try
        {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().putScheme(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri:"/scheme-entry")
//                respond obj, formats: ['json'], status: 200
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
            def apiResponse = new SalesService().deleteScheme(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success","success")
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

}