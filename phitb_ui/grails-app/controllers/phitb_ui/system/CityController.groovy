package phitb_ui.system

import grails.artefact.Controller
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Links
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController

class CityController {

    def index()
    {
        try
        {
            ArrayList<String> region = new SystemService().getRegionList() as ArrayList<String>
            ArrayList<String> division = new DivisionMasterController().show() as ArrayList<String>
            ArrayList<String> district = new DistrictController().show() as ArrayList<String>
            ArrayList<String> state = new StateController().show() as ArrayList<String>
            render(view: '/system/city/city',model: [state:state,region:region,division:division,district:district])
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
        String limit = params.limit
        String offset = params.offset
        String query = params.query
        String page = params.page
        String type = params.type
        if(query == null)
            query = params.search //by select2
        def apiResponse = new SystemService().getCityList(limit, offset, query)
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
            ArrayList<JSONObject> arrayList = new ArrayList<>(jsonArray)
            ArrayList<JSONObject> cities = new ArrayList<>(jsonArray)
            if(type?.equalsIgnoreCase("select2"))
            {
                for (JSONObject jsonObject : arrayList) {
                    JSONObject city = new JSONObject()
                    city.put("id", jsonObject.get("id"))
                    city.put("text", jsonObject.get("areaName") + " ("+jsonObject.get("districtName")+ ")")
                    city.put("pincode", jsonObject.get("pincode"))
                    cities.add(city)
                }
                respond cities, formats: ['json']
            }
            else {
                //not select2 so return arraylist
                return arrayList
            }
        }
        else
        {
            return []
        }
    }

    def getCityByPincode()
    {
        try
        {
            String pincode = params.pincode
            if(pincode!="")
            {
                def apiResponse = new SystemService().getCityByPin(pincode)
                if (apiResponse?.status == 200)
                {
                    JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
//                    ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                    respond jsonArray,formats:['json'], status: 200;
                }
                else
                {
                    return []
                }
            }
            else {
                return []
            }

        }
       catch(Exception ex)
       {
           System.out.println(controllerName+": "+ex)
           log.error(controllerName+": "+ex)
       }
    }

    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SystemService().showCity(jsonObject)
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
            if(params.entityId!=null || params.entityId!="")
            {
                jsonObject.put("entityId", session.getAttribute("entityId"))
            }
            if(params.entityTypeId!=null || params.entityTypeId!="")
            {
                jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            }
            def apiResponse = new SystemService().saveCity(jsonObject)
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
            if(params.entityId!=null || params.entityId!="")
            {
                jsonObject.put("entityId", session.getAttribute("entityId"))
            }
            if(params.entityTypeId!=null || params.entityTypeId!="")
            {
                jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            }
            def apiResponse = new SystemService().putCity(jsonObject)
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
            def apiResponse = new SystemService().deleteCity(jsonObject)
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

    def getCityById()
    {
        def city = new SystemService().getCityById(params.id)
        respond city, formats: ['json'],status: 200
    }

}
