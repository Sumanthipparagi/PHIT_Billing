package entity

import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import system.CityController
import system.CountryController
import system.StateController
import system.ZoneController

class TermsConditionController {

    def index()
    {
        try
        {
            def entityurl = Links.API_GATEWAY+Links.ENTITY_REGISTER_SHOW
            def entitytypeurl = Links.API_GATEWAY+Links.ENTITY_TYPE_MASTER_SHOW
            def userregisterurl = Links.API_GATEWAY + Links.USER_REGISTER_SHOW
            def formurl = Links.API_GATEWAY + Links.FORM_MASTER_SHOW
            def ccmurl = Links.API_GATEWAY + Links.CCM_SHOW
            URL apiUrl1 = new URL(entityurl)
            URL apiUrl2 = new URL(entitytypeurl)
            URL apiUrl3 = new URL(userregisterurl)
            URL apiUrl4 = new URL(ccmurl)
            URL apiUrl5 = new URL(formurl)
            def entity = new JsonSlurper().parseText(apiUrl1.text)
            def entitytype = new JsonSlurper().parseText(apiUrl2.text)
            def userregister = new JsonSlurper().parseText(apiUrl3.text)
            def form = new JsonSlurper().parseText(apiUrl5.text)
            def ccm = new JsonSlurper().parseText(apiUrl4.text)
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
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/termsCondition/termsCondition',model: [entity:entity, entitytype:entitytype,
                                                               statelist:statelist,countrylist:countrylist,
                                                               citylist:citylist,salesmanList:salesmanList,
                                                               managerList:managerList,zoneList:zoneList,ccm:ccm,form:form])
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
            def apiResponse = new EntityService().showTermsCondtion(jsonObject)
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
            def apiResponse = new EntityService().saveTermsCondition(jsonObject)
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
            def apiResponse = new EntityService().putTermsCondtion(jsonObject)
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
            def apiResponse = new EntityService().deleteTermsCondtion(jsonObject)
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
