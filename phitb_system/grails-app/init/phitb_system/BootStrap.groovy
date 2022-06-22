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


    }
    def destroy = {
    }
}
