package phitb_ui.entity

import org.grails.web.json.JSONObject
import phitb_ui.EntityService

class DepartmentController {

    def index() {
        ArrayList<JSONObject> entityList = new ArrayList<>()
        entityList.add(new EntityService().getEntityById(session.getAttribute("entityId").toString()))
        render(view: "/entity/department/index",model: [entity     :entityList])
    }

    def save(){
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entity", session.getAttribute("entityId"))
            jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            jsonObject.put("createdUser", session.getAttribute("userId"))
            jsonObject.put("modifiedUser", session.getAttribute("userId"))

            def apiResponse = new EntityService().saveDepartment(jsonObject)
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

    def update(){
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entity", session.getAttribute("entityId"))
            jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            jsonObject.put("createdUser", session.getAttribute("userId"))
            jsonObject.put("modifiedUser", session.getAttribute("userId"))
            def apiResponse = new EntityService().putDepartment(jsonObject)
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

    def delete(){
        try
        {
            String id = params.id
            def apiResponse = new EntityService().deleteDepartment(id)
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

    def dataTable(){
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new EntityService().getDepartmentDatatable(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            } else {
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
