package phitb_entity

import grails.converters.JSON

import java.sql.Time

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Date) {
            return it?.format("dd/MM/yyyy HH:mm:ss")
        }

        JSON.registerObjectMarshaller(Time) {
            return it?.format("HH:mm:ss")
        }

        //Create SMS Template
        SMSTemplate smsTemplate = SMSTemplate.findByTemplateName("SALE_INVOICE_SMS")
        if(smsTemplate == null)
        {
            smsTemplate = new SMSTemplate()
            smsTemplate.template = "Hello \$customer, \$seller generated an Invoice \$docNo dt.\$orderDate with value \$amount. CurBal is \$balance - Sw by PHARMIT"
            smsTemplate.templateName = "SALE_INVOICE_SMS"
            smsTemplate.templateId = "1007192061927728749"
            smsTemplate.senderId = "PHAERP"
            smsTemplate.active = true
            smsTemplate.save(flush:true)

        }
    }
    def destroy = {
    }
}
