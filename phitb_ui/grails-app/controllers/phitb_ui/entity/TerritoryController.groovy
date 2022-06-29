package phitb_ui.entity

import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.SystemService
import phitb_ui.facility.CcmController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.DistrictController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class TerritoryController {

    def index()
    {
        try
        {
            ArrayList<String> ccm = new CcmController().show() as ArrayList<String>
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> districts = new DistrictController().show() as ArrayList<String>
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
            render(view: '/entity/territory/territory',model: [entity:entity,
                                                   statelist:statelist,countrylist:countrylist,
                                                   citylist:citylist,salesmanList:salesmanList,
                                                   managerList:managerList,zoneList:zoneList,ccm:ccm,districts:districts])
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

            def apiResponse = new EntityService().showTerritory(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {
                    JSONArray jsonArray = responseObject.data
                    JSONArray cityArray = new JSONArray()
                    for (JSONObject json : jsonArray) {
                        Object cityids = json.cityIds.split(",")
//                        println(cityids)
                        for (String id : cityids)
                        {
                             def city = new SystemService().getCityById(id.toString())
                            JSONObject cityjson = new JSONObject()
                            cityjson.put("id", city.get("id"))
                            cityjson.put("text", city.get("areaName") + " ("+city.get("districtName")+ ")")
                            cityjson.put("pincode", city.get("pincode"))
                            cityArray.add(cityjson)
                        }
                        for(JSONObject jsonObject1: jsonArray)
                        {
                            if(json.id!= jsonObject1.id)
                            {
                                jsonObject1.put("cityarray",cityArray)
                            }
                        }
                    }
                    responseObject.put("data", jsonArray)
                    responseObject.put("city", cityArray)
                }
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
            def apiResponse = new EntityService().saveTerritory(jsonObject)
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
            def apiResponse = new EntityService().putTerritory(jsonObject)
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
            def apiResponse = new EntityService().deleteTerritory(jsonObject)
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
