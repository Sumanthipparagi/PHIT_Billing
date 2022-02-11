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


        JSON.registerObjectMarshaller(StateMaster) {
                    def returnArray = [:]
                    returnArray['id'] = it.id
                    returnArray['name'] = it.name
                    returnArray['entityId'] = it.entityId
                    returnArray['zone'] = it.zone
                    returnArray['country'] = it.country
                    return returnArray
                }

        JSON.registerObjectMarshaller(ZoneMaster) {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['name'] = it.name
            returnArray['entityId'] = it.entityId
            return returnArray
        }

        JSON.registerObjectMarshaller(CountryMaster) {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['name'] = it.name
            returnArray['entityId'] = it.entityId
            return returnArray
        }


    }
    def destroy = {
    }
}
