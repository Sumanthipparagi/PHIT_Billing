package phitb_ui.entity

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EmailService
import phitb_ui.EntityService

class EntitySettingsController {

    def index() {
        try {
            def entity = new ArrayList<>()
            if(session.getAttribute("role").toString().equalsIgnoreCase(Constants.SUPER_USER)) {

                def apiResponse = new EntityService().getParentEntities()
                if (apiResponse?.status == 200) {
                    entity = new JSONArray(apiResponse.readEntity(String.class))
                }
            }
            else
            {
                entity.add(new EntityService().getEntityById(session.getAttribute("entityId").toString()))
            }
            render(view: '/entity/entitySettings/index', model: [entity: entity])
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().showSettings(jsonObject)
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

    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().saveSettings(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri: "/entity-settings")
            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update() {
        try {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().putSettings(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
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

    def delete() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().deleteSettings(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
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


    def settings() {
        String id = params.id
        if(id.equalsIgnoreCase(session.getAttribute("entityId").toString())
                || session.getAttribute("role").toString().equalsIgnoreCase(Constants.SUPER_USER)) {
            def entity = new EntityService().getEntityById(params.id)
            def entitySettings = new EntityService().getEntitySettingsByEntity(params.id)
            def entityConfigs = new EntityService().getEntityConfigByEntity(params.id)
            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            render(view: '/entity/entitySettings/settings', model: [entity       : entity, entitySettings: entitySettings, emailSettings: emailSettings,
                                                                    entityConfigs: entityConfigs])
        }
        else
        {
            redirect(uri: "/dashboard")
        }
    }

}
