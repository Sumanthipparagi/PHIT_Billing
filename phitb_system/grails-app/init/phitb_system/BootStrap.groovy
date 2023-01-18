package phitb_system

import grails.converters.JSON

import java.sql.Time

class BootStrap
{

    def init = {servletContext ->
        JSON.registerObjectMarshaller(Date) {
            return it?.format("dd/MM/yyyy HH:mm:ss")
        }


        JSON.registerObjectMarshaller(Time) {
            return it?.format("HH:mm:ss")
        }


        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Sale Return", "R").save()
        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Product Expired", "E").save()
        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Breakage", "B").save()
        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Other (No Stock Change)", "O").save()
        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Other (ADD)", "OA").save()
        ReasonMaster.findOrCreateByReasonNameAndReasonCode("Purchase Return", "PR").save()
    }
    def destroy = {
    }
}
