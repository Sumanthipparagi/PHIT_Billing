package phitb_system

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

        JSON.registerObjectMarshaller(AccountModeMaster)
        {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['mode'] = it.mode
            returnArray['dateCreated'] = it.dateCreated
            returnArray['lastUpdated'] = it.lastUpdated
            return returnArray
        }
    }
    def destroy = {
    }
}
