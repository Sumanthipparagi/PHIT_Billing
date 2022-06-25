package phitb_ui.entity

import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.facility.CcmController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class TaxController {

    def index()
    {
        try
        {
            ArrayList<String> ccm = new CcmController().show() as ArrayList<String>
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/tax/tax',model: [entity:entity,
                                                     statelist:statelist,countrylist:countrylist,
                                                     salesmanList:salesmanList,
                                                     managerList:managerList,zoneList:zoneList,ccm:ccm])
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
            jsonObject.put("entityId", session.getAttribute("entityId"))

            def apiResponse = new EntityService().showTax(jsonObject)
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
            if(params.entity!=null || params.entity!="")
            {
                jsonObject.put("entity", session.getAttribute("entityId"))
            }
            if(params.entityType!=null || params.entityType!="")
            {
                jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            }
            def apiResponse = new EntityService().saveTax(jsonObject)
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

    def update()
    {
        try
        {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            if(params.entity!=null || params.entity!="")
            {
                jsonObject.put("entity", session.getAttribute("entityId"))
            }
            if(params.entityType!=null || params.entityType!="")
            {
                jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            }
            def apiResponse = new EntityService().putTax(jsonObject)
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
            def apiResponse = new EntityService().deleteTax(jsonObject)
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


    def show(String id)
    {
        try
        {
            if(!id)
                id = params.id
            def apiResponse = new EntityService().getTaxRegister(id)
            if (apiResponse?.status == 200)
            {
                if(id) {
                    JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                    return jsonObject
                }
                else
                {
                    JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                    return jsonArray
                }
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


    def showTaxForReturn(String id)
    {
        try
        {
            if(!id)
                id = params.id
            def apiResponse = new EntityService().getTaxRegister(id)
            if (apiResponse?.status == 200)
            {
                if(id) {
                    JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                    respond jsonObject, formats: ['json'],status: 200;
                }
                else
                {
                    JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                    respond jsonArray, formats: ['json'],status: 200;
                }
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
}
