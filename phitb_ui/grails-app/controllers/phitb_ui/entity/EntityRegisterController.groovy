package phitb_ui.entity

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.SystemService
import phitb_ui.accounts.BankRegisterController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.PriorityController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class EntityRegisterController
{

    def index()
    {
        try
        {
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
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

            render(view: '/entity/entityRegister/entityRegister',model: [entity:entity,
                                                                         statelist:statelist,countrylist:countrylist,
                                                                         citylist:citylist,salesmanList:salesmanList,
                                                                         managerList:managerList,zoneList:zoneList,
                                                                         entitytype:entitytype])
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
            ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().getByEntity() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            def priority = new SystemService().getAllPriority()
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
            render(view: '/entity/entityRegister/add-entity-register',model: [ entitytype:entitytype,
                                                                              statelist:statelist,countrylist:countrylist,
                                                                              citylist:citylist,salesmanList:salesmanList,
                                                                              managerList:managerList,
                                                                              zoneList:zoneList,
                                                                              routeregister:routeregister,
                                                                              bank:bank,entityList:entityList,
                                                                              priority:priority])
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
            JSONObject entity = new EntityService().getEntityById(params.id)
            ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            def priority = new SystemService().getAllPriority()
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
                                                                                 routeregister:routeregister,
                                                                                 bank:bank,entityList:entityList,
                                                                                 priority:priority])
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
            jsonObject.put("entityId", session.getAttribute("entityId"))
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
            jsonObject.put("parentEntity", session.getAttribute("entityId"))
            jsonObject.put("parentEntityType", session.getAttribute("entityTypeId"))

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
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("parentEntity", session.getAttribute("entityId"))
            jsonObject.put("parentEntityType", session.getAttribute("entityTypeId"))
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

    def getByAffiliates(String id)
    {
        try
        {
            def apiResponse = new EntityService().getEntityByAffiliates(id)
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


    def getEnitityById(String id)
    {
        return new EntityService().getEntityById(id)
    }

    def getEntityTypeById()
    {
        try
        {
            def apiResponse = new EntityService().getEntityTypeById(params.id)
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                respond jsonObject, formats: ['json'], status: 200
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
