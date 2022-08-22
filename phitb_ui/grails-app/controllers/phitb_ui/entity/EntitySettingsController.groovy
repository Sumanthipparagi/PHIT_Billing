package phitb_ui.entity

import org.grails.web.json.JSONObject
import phitb_ui.EmailService
import phitb_ui.EntityService

class EntitySettingsController {

    def index() {
        try {
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
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
        def entity = new EntityService().getEntityById(params.id)
        def entitySettings = new EntityService().getEntitySettingsByEntity(params.id)
        def entityConfigs = new EntityService().getEntityConfigByEntity(params.id)
        def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
        render(view: '/entity/entitySettings/settings', model: [entity       : entity, entitySettings: entitySettings, emailSettings: emailSettings,
                                                                entityConfigs: entityConfigs])
    }

    def emailSettingsSave() {
        println(params)
        String entityId = params.entityId
        String smtpServer = params.smtpServer
        String smtpPort = params.smtpPort
        String smtpUsername = params.smtpUsername
        String smtpPassword = params.smtpPassword
        String senderMail = params.senderMail
        String encryptionType = params.encryptionType
        String emailService = params.emailService
        String emailSettingsId = params.emailSettingsId
        Boolean authenticationRequired = Boolean.parseBoolean(params.authenticationRequired)
        Boolean active = true

        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId", entityId)
        jsonObject.put("smtpServer", smtpServer)
        jsonObject.put("smtpPort", smtpPort)
        jsonObject.put("smtpUsername", smtpUsername)
        jsonObject.put("smtpPassword", smtpPassword)
        jsonObject.put("senderMail", senderMail)
        jsonObject.put("encryptionType", encryptionType)
        jsonObject.put("emailService", emailService)
        jsonObject.put("authenticationRequired", authenticationRequired)
        jsonObject.put("active", active)

        JSONObject resultJson = new JSONObject()
        if(!emailSettingsId)
            resultJson = EmailService.saveEmailSettings(jsonObject)
        else
        {
            jsonObject.put("id", emailSettingsId)
            resultJson = EmailService.updateEmailSettings(jsonObject)
        }
        respond resultJson, formats: ['json']
    }

    def emailSettingsUpdate() {
        String entityId = params.entityId
        String smtpServer = params.smtpServer
        String smtpPort = params.smtpPort
        String smtpUsername = params.smtpUsername
        String smtpPassword = params.smtpPassword
        String senderMail = params.senderMail
        String encryptionType = params.encryptionType
        String emailService = params.emailService
        Boolean authenticationRequired = Boolean.parseBoolean(params.authenticationRequired)
        Boolean active = true

        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId", entityId)
        jsonObject.put("smtpServer", smtpServer)
        jsonObject.put("smtpPort", smtpPort)
        jsonObject.put("smtpUsername", smtpUsername)
        jsonObject.put("smtpPassword", smtpPassword)
        jsonObject.put("senderMail", senderMail)
        jsonObject.put("encryptionType", encryptionType)
        jsonObject.put("emailService", emailService)
        jsonObject.put("authenticationRequired", authenticationRequired)
        jsonObject.put("active", active)

        JSONObject jsonObject1 = EmailService.updateEmailSettings(jsonObject)
        respond jsonObject1, formats: ['json']
    }
}
