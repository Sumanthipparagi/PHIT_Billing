package system

import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SystemService


class AccountModeController
{

    def index()
    {

        def url = Links.API_GATEWAY+Links.ENTITY_REGISTER_SHOW
        println(url)
        URL apiUrl = new URL(url)
        def entity = new JsonSlurper().parseText(apiUrl.text)
        render(view: '/system/accountMode/accountmodes',model: [entity:entity])
    }

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SystemService().saveAccountModes(jsonObject)
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
            def apiResponse = new SystemService().showAccountModes(jsonObject)
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

    def update()
    {
        try
        {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SystemService().putAccountMode(jsonObject)
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
            def apiResponse = new SystemService().deleteAccountModes(jsonObject)
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

    def getAllEntity()
    {
        try
        {
            def url = "http://localhost/api/v1.0/entity/entityregister"
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            respond entity, formats: ['json'], status: 200
        }
        catch (Exception ex)
        {
            System.err.println('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
            log.error('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
        }
    }


    def show()
    {
        try
        {
            def apiResponse = new SystemService().getAccountmodes()
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
}
