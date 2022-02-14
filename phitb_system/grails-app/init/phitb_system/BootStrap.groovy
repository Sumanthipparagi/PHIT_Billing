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

        JSON.registerObjectMarshaller(AccountModeMaster)
                {
                    def accountmodeMaster = [:]
                    accountmodeMaster['id'] = it.id
                    accountmodeMaster['mode'] = it.mode
                    accountmodeMaster['dateCreated'] = it.dateCreated
                    accountmodeMaster['lastUpdated'] = it.lastUpdated
                    return accountmodeMaster
                }


        JSON.registerObjectMarshaller(StateMaster) {
            def stateMaster = [:]
            stateMaster['id'] = it.id
            stateMaster['name'] = it.name
            stateMaster['entityId'] = it.entityId
            stateMaster['zone'] = it.zone
            stateMaster['country'] = it.country
            return stateMaster
        }

        JSON.registerObjectMarshaller(ZoneMaster) {
            def zoneMaster = [:]
            zoneMaster['id'] = it.id
            zoneMaster['name'] = it.name
            zoneMaster['entityId'] = it.entityId
            return zoneMaster
        }

        JSON.registerObjectMarshaller(CountryMaster) {
            def countryMaster = [:]
            countryMaster['id'] = it.id
            countryMaster['name'] = it.name
            countryMaster['entityId'] = it.entityId
            return countryMaster
        }


    }
    def destroy = {
    }
}
