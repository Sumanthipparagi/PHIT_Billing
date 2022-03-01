package phitb_ui.entity

import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class EntityRegisterController
{

    def index()
    {
        try
        {
            def entityurl = Links.API_GATEWAY+Links.ENTITY_REGISTER_SHOW
            def entitytypeurl = Links.API_GATEWAY+Links.ENTITY_TYPE_MASTER_SHOW
            def userregisterurl = Links.API_GATEWAY + Links.USER_REGISTER_SHOW
            URL apiUrl1 = new URL(entityurl)
            URL apiUrl2 = new URL(entitytypeurl)
            URL apiUrl3 = new URL(userregisterurl)
            def entity = new JsonSlurper().parseText(apiUrl1.text)
            def entitytype = new JsonSlurper().parseText(apiUrl2.text)
            def userregister = new JsonSlurper().parseText(apiUrl3.text)
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('MANAGER'))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('SALESMAN'))
                {
                    salesmanList.add(it)
                }
            }

            render(view: '/entity/entityRegister/entityRegister',model: [entity:entity, entitytype:entitytype,
                                                                         statelist:statelist,countrylist:countrylist,
                                                                         citylist:citylist,salesmanList:salesmanList,
                                                                         managerList:managerList,zoneList:zoneList])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def addEntity()
    {
        try
        {
            def entityurl = Links.API_GATEWAY+Links.ENTITY_REGISTER_SHOW
            def entitytypeurl = Links.API_GATEWAY+Links.ENTITY_TYPE_MASTER_SHOW
            def userregisterurl = Links.API_GATEWAY + Links.USER_REGISTER_SHOW
            def routeregisterurl = Links.API_GATEWAY + Links.ROUTE_REGISTER_SHOW
            def bankurl = Links.API_GATEWAY + Links.BANK_REGISTER_SHOW
            URL apiUrl1 = new URL(entityurl)
            URL apiUrl2 = new URL(entitytypeurl)
            URL apiUrl3 = new URL(userregisterurl)
            URL apiUrl4 = new URL(routeregisterurl)
            URL apiUrl5 = new URL(bankurl)
            def entity = new JsonSlurper().parseText(apiUrl1.text)
            def entitytype = new JsonSlurper().parseText(apiUrl2.text)
            def userregister = new JsonSlurper().parseText(apiUrl3.text)
            def routeregister = new JsonSlurper().parseText(apiUrl4.text)
            def bank = new JsonSlurper().parseText(apiUrl5.text)
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/entityRegister/add-entity-register',model: [entity:entity, entitytype:entitytype,
                                                                              statelist:statelist,countrylist:countrylist,
                                                                              citylist:citylist,salesmanList:salesmanList,
                                                                              managerList:managerList,
                                                                              zoneList:zoneList,
                                                                              routeregister:routeregister,bank:bank])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def updateEntity()
    {
        try
        {
            def entityurl = Links.API_GATEWAY+Links.ENTITY_REGISTER_SHOW+"/"+params.id
            def entitytypeurl = Links.API_GATEWAY+Links.ENTITY_TYPE_MASTER_SHOW
            def userregisterurl = Links.API_GATEWAY + Links.USER_REGISTER_SHOW
            def routeregisterurl = Links.API_GATEWAY + Links.ROUTE_REGISTER_SHOW
            def bankurl = Links.API_GATEWAY + Links.BANK_REGISTER_SHOW
            URL apiUrl1 = new URL(entityurl)
            URL apiUrl2 = new URL(entitytypeurl)
            URL apiUrl3 = new URL(userregisterurl)
            URL apiUrl4 = new URL(routeregisterurl)
            URL apiUrl5 = new URL(bankurl)
            def entity = new JsonSlurper().parseText(apiUrl1.text)
            def entitytype = new JsonSlurper().parseText(apiUrl2.text)
            def userregister = new JsonSlurper().parseText(apiUrl3.text)
            def routeregister = new JsonSlurper().parseText(apiUrl4.text)
            def bank = new JsonSlurper().parseText(apiUrl5.text)
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }

            render(view: '/entity/entityRegister/update-entity-register',model: [entity:entity, entitytype:entitytype,
                                                                                 statelist:statelist,countrylist:countrylist,
                                                                                 citylist:citylist,salesmanList:salesmanList,
                                                                                 managerList:managerList,
                                                                                 zoneList:zoneList,
                                                                                 routeregister:routeregister,bank:bank])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().showEntity(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().saveEntity(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                render(view: '/entity/entityRegister/add-entity-register')
//                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update()
    {
        try
        {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().putEntity(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri:"/entity-register")
//                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().deleteEntity(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success","success")
                respond data, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def show()
    {
        try
        {
            def apiResponse = new EntityService().getEntity()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
