package phitb_ui.entity

import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class EntityRouteController {

    def index()
    {
        try
        {
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            render(view: '/entity/entityRoute/entityRoute',model: [entity:entity])
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
            def apiResponse = new EntityService().showEntityRoute(jsonObject)
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
            def apiResponse = new EntityService().saveEntityRoute(jsonObject)
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
            JSONObject jsonObject = new JSONObject(params)
            if(params.entity!=null || params.entity!="")
            {
                jsonObject.put("entity", session.getAttribute("entityId"))
            }
            if(params.entityType!=null || params.entityType!="")
            {
                jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            }
            def apiResponse = new EntityService().putEntityRoute(jsonObject)
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
            def apiResponse = new EntityService().deleteEntityRoute(jsonObject)
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
