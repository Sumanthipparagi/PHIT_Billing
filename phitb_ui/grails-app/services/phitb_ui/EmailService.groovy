package phitb_ui

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class EmailService {


    static webUtils = WebUtils.retrieveGrailsWebRequest()
    /**
     * method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    static void sendEmail(String toEmail, String subject, String body) {
        try {
            JSONObject emailSettings = getEmailSettingsByEntity()
            if (emailSettings && emailSettings.get("active")) {
                String senderMail = new Constants().EMAIL_SENDER_ID
                if (emailSettings.has("senderMail")) {
                    senderMail = emailSettings.get("senderMail")
                }
                Session session = getEmailAuth(emailSettings)
                MimeMessage msg = new MimeMessage(session);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8")
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit")
                msg.setFrom(new InternetAddress(senderMail, "PharmIT-ERP"))
                msg.setReplyTo(InternetAddress.parse(senderMail, false))
                msg.setSubject(subject, "UTF-8")
                msg.setText(body, "UTF-8")
                msg.setSentDate(new Date())
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false))
                System.out.println("Message is ready")
                Transport.send(msg)
                System.out.println("Email Sent Successfully!!")
                //TODO: add mail log here
            } else {
                System.out.println("Email Not Enabled")
            }
        }
        catch (Exception e) {
            e.printStackTrace()
        }
    }

    static Session getEmailAuth(JSONObject emailSettings) {
        Boolean authenticationRequired = true
        String encryptionType = Constants.EMAIL_ENCRYPTION_TYPE_STARTLS
        String smtpServer = Constants.EMAIL_SMTP_SERVER
        String smtpPort = Constants.EMAIL_SMTP_PORT
        String smtpUsername = Constants.EMAIL_USERNAME
        String smtpPassword = Constants.EMAIL_PASSWORD
        if (emailSettings.get("emailService").toString().equalsIgnoreCase("CUSTOM")) {
            authenticationRequired = Boolean.parseBoolean(emailSettings.get("authenticationRequired"))
            encryptionType = emailSettings.get("encryptionType")
            smtpServer = emailSettings.get("smtpServer")
            smtpPort = emailSettings.get("smtpPort")
            smtpUsername = emailSettings.get("smtpUsername")
            smtpPassword = emailSettings.get("smtpPassword")
        }


        Properties props = new Properties()
        props.put("mail.smtp.host", smtpServer) //SMTP Host
        if (authenticationRequired) {
            props.put("mail.smtp.auth", "true") //Enabling SMTP Authentication
            if (encryptionType.equalsIgnoreCase(Constants.EMAIL_ENCRYPTION_TYPE_SSL)) {
                props.put("mail.smtp.socketFactory.port", smtpPort) //SSL Port
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory") //SSL Factory Class
            } else if (encryptionType.equalsIgnoreCase(Constants.EMAIL_ENCRYPTION_TYPE_STARTLS)) {
                props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            }
            props.put("mail.smtp.port", smtpPort) //SMTP Port
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUsername, smtpPassword);
                }
            }
            return Session.getDefaultInstance(props, auth)
        } else {
            return Session.getDefaultInstance(props, null)
        }
    }

    static JSONObject getEmailSettingsByEntity(String entityId) {
        if (entityId == null)
            entityId = webUtils.getSession().getAttribute("entityId")

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().EMAIL_SETTINGS_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                //there will be only one entry
                return jsonArray.get(0) as JSONObject
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :facility , action :  getEmailSettingsByEntity  , Ex:' + ex)
            //log.error('Service :facility , action :  getEmailSettingsByEntity  , Ex:' + ex)
            return null
        }

    }


    static JSONObject saveEmailSettings(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().EMAIL_SETTINGS_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject resultObject = new JSONObject(apiResponse.readEntity(String.class))
                return resultObject
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EmailService , action :  saveEmailSettings  , Ex:' + ex)
           // log.error('Service :EmailService , action :  saveEmailSettings  , Ex:' + ex)
            return null
        }

    }

    static JSONObject updateEmailSettings(JSONObject jsonObject) {
        String id = jsonObject.get("id")
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().EMAIL_SETTINGS_UPDATE)
                    .resolveTemplate("id", id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject resultObject = new JSONObject(apiResponse.readEntity(String.class))
                return resultObject
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EmailService , action :  updateEmailSettings , Ex:' + ex)
            //log.error('Service :EmailService , action :  updateEmailSettings  , Ex:' + ex)
            return null
        }

    }
}
