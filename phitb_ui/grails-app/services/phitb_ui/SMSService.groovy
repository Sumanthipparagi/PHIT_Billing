package phitb_ui

import grails.gorm.transactions.Transactional
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONObject
import phitb_ui.entity.EntityRegisterController

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

@Transactional
class SMSService {

    def sendSaleInvoiceSMS(JSONObject saleInvoice)
    {
        try {
            String entityId = saleInvoice.get("entityId").toString()
            String customerId = saleInvoice.get("customerId").toString()
            JSONObject entity = new EntityRegisterController().getEnitityById(entityId)
            JSONObject customer = new EntityRegisterController().getEnitityById(customerId)
            JSONObject entityConfig = new EntityService().getEntityConfigByEntity(entityId)
            if (entity && customer && customer.has("mobileNumber") && entityConfig?.get("SEND_SMS")?.saleEntry) {
                String userId = saleInvoice.get("createdUser")
                String docType = "SALE_INVOICE"
                String docId = saleInvoice.get("id")
                String docNo = saleInvoice.get("invoiceNumber")
                String orderDate = saleInvoice.get("orderDate")
                orderDate = orderDate.split("T")[0].trim()
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
                orderDate = sdf.parse(orderDate).format("dd/MM/yyyy")
                String invoiceTotal = saleInvoice.get("invoiceTotal")
                long it = (long) UtilsService.round(Double.parseDouble(invoiceTotal), 0)
                String balance = saleInvoice.get("balance")
                long bal = (long) UtilsService.round(Double.parseDouble(balance), 0)
                String entityName = sanitizeStringForSMS(entity.get("entityName").toString())
                String customerName = sanitizeStringForSMS(customer.get("entityName").toString())
                String mobileNumber = customer.get("mobileNumber")
                if (mobileNumber.length() > 0) {

                    JSONObject smsTemplate = getSMSTemplate(new Constants().SALE_INVOICE_SMS)
                    if(smsTemplate) {
                        String message = smsTemplate.get("template")
                        //message = message.replace("\$customer", customerName)//Not sending customer name
                        message = message.replace("\$customer", "")//Not sending customer name
                        message = message.replace("\$seller", entityName)
                        message = message.replace("\$docNo", docNo)
                        message = message.replace("\$orderDate", orderDate)
                        message = message.replace("\$amount", it.toString())
                        message = message.replace("\$balance", bal.toString())
                        new Thread(new Runnable() {
                            @Override
                            void run() {
                                try {
                                    String response = sendSMS(mobileNumber, message, smsTemplate.get("senderId").toString())
                                    if (response) {
                                        JSONObject responseObject = new JSONObject(response)
                                        if (!responseObject.get("Status").toString().equalsIgnoreCase("Error")) {
                                            String messageId = responseObject.get("Details")
                                            buildSMSLog(entityId, userId, mobileNumber, messageId, message, docType, docId, docNo)
                                        } else {
                                            log.info("SMS send failed: " + mobileNumber)
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    println("sendSMS failed")
                                    println(ex.stackTrace)
                                }
                            }
                        }).start()
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            println("SMS sending failed")
        }
    }


    def sendSMS(String mobileNumber, String message, String senderId = null) {
        Form form = new Form()
        if(senderId == null)
            senderId = new Constants().SMS_SENDER_ID
        form.param("from", senderId)
        form.param("apikey", new Constants().SMS_API_KEY)
        form.param("to", mobileNumber)
        form.param("msg", message)
        form.param("module", "TRANS_SMS")
        println(message)
        String url = new Constants().SMS_URL
        Client client = ClientBuilder.newClient()
        Response apiResponse = client.target(url)
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE))
        if (apiResponse.status == 200)
        {
            String response = apiResponse.readEntity(String.class)
            println(response)
            return response
        }
        else
        {
            String response = apiResponse.readEntity(String.class)
            println(response)
            println("SMS sending failed")
            return null
        }
    }

    JSONObject buildSMSLog(String entityId, String userId,
                           String mobileNumber, String messageId,
                           String smsContent, String docType = null, String docId = null, String docNo = null)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entity",entityId)
        jsonObject.put("sentByUser",userId)
        jsonObject.put("mobileNumber", mobileNumber)
        jsonObject.put("messageId", messageId)
        jsonObject.put("smsContent", smsContent)
        jsonObject.put("deliveryStatus", "SENT")
        jsonObject.put("docType", docType)
        jsonObject.put("docId", docId)
        jsonObject.put("docNo", docNo)

        saveSMSLog(jsonObject)

    }

    static JSONObject saveSMSLog(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SMS_LOG_SAVE)
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
            System.err.println('Service :SMSService , action :  saveSMSLog, Ex:' + ex)
            return null
        }

    }

    static JSONObject updateSMSLog(JSONObject jsonObject) {
        String id = jsonObject.get("id")
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SMS_LOG_UPDATE)
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
            System.err.println('Service :SMSService , action :  updateSMSLog , Ex:' + ex)
            return null
        }

    }

    static JSONObject smsLogDatatable(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SMS_LOG_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SMSService , action :  emailLogDatatable  , Ex:' + ex)
            return null
        }

    }


    static JSONObject getSMSTemplate(String templateName, String entityId = null) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SMS_TEMPLATE)
                    .queryParam("templateName",templateName)
                    .queryParam("entityId",entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject resultObject = new JSONObject(apiResponse.readEntity(String.class))
                return resultObject
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SMSService , action :  getSMSTemplate  , Ex:' + ex)
            return null
        }

    }

    String sanitizeStringForSMS(String str)
    {
        if(str?.length()>0)
        {
            str = str.trim()
            str = str.replaceAll(">","")
            str = str.replaceAll("<","")
            str = str.replaceAll("&","")
            str = str.replaceAll(" +", " ") //replace consecutive spaces with single space

            if(str.length()>17)
            {
                str = StringUtils.left(str, 17) + ".."
            }
        }
        return str
    }

}
