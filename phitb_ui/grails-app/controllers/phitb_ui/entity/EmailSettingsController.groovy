package phitb_ui.entity

import org.grails.web.json.JSONObject
import phitb_ui.EmailService
import phitb_ui.EntityService

class EmailSettingsController {

    def index() {
        def entity = new EntityService().getEntityById(params.id)
        def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
        render(view: '/entity/emailSettings/settings', model: [entity: entity,emailSettings: emailSettings])
    }

    //save or update
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
        boolean authenticationRequired = true
        if(params.authenticationRequired == null)
            authenticationRequired = false
        Boolean active = true

        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entity", entityId)
        jsonObject.put("smtpServer", smtpServer)
        jsonObject.put("smtpPort", smtpPort)
        jsonObject.put("smtpUsername", smtpUsername)
        jsonObject.put("smtpPassword", smtpPassword)
        jsonObject.put("senderMail", senderMail)
        jsonObject.put("encryptionType", encryptionType)
        if(emailService.equalsIgnoreCase("DISABLED"))
        {
            jsonObject.put("emailService", "DEFAULT")
            jsonObject.put("active", false)
        }
        else {
            jsonObject.put("emailService", emailService)
            jsonObject.put("active", active)
        }
        jsonObject.put("authenticationRequired", authenticationRequired)
        if(emailSettingsId)
        {
            jsonObject.put("id", emailSettingsId)

        }

        JSONObject resultJson = EmailService.saveEmailSettings(jsonObject)
        respond resultJson, formats: ['json']
    }
}
