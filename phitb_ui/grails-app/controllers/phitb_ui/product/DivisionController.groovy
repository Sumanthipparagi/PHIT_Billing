package phitb_ui.product

import phitb_ui.EntityService
import phitb_ui.SystemService
import phitb_ui.entity.CustomerGroupController
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.facility.FridgeController
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class DivisionController {

    def index() {
        try {
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().getByEntity() as ArrayList<String>
            ArrayList<String> series = new SeriesController().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> customerGroup = new CustomerGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> managerList = []
            ArrayList<String> customerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER)) {
                    managerList.add(it)
                }
            }
            entity.each {
                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_CUSTOMER)) {
                    println(it)
                    customerList.add(it)
                }
            }


            render(view: '/product/division/division', model: [entitylist  : entity, statelist: statelist,
                                                               countrylist : countrylist,
                                                               zoneList    : zoneList,
                                                               customerList: customerGroup, series: series,
                                                               managerList : managerList])
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute('entityId'))
            def apiResponse = new ProductService().showDivisoion(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            if (params.entityId != null || params.entityId != "") {
                jsonObject.put("entityId", session.getAttribute("entityId"))
            }
            if (params.entityTypeId != null || params.entityTypeId != "") {
                jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            }
            if(!jsonObject.has("managerId"))
            {
                jsonObject.put("managerId",0)
            }
            def apiResponse = new ProductService().saveDivision(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update() {
        try {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            if (params.entityId != null || params.entityId != "") {
                jsonObject.put("entityId", session.getAttribute("entityId"))
            }
            if (params.entityTypeId != null || params.entityTypeId != "") {
                jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            }
            if(!jsonObject.has("managerId"))
            {
                jsonObject.put("managerId",0)
            }
            def apiResponse = new ProductService().putDivision(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().deleteDivision(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def show() {
        try {
            def apiResponse = new ProductService().getDivisions()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getByEntity() {
        try {
            def apiResponse = new ProductService().getDivisionsByEntityId(session.getAttribute("entityId").toString())
            if (apiResponse != null) {
//                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(apiResponse)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getDivisionById(String id) {
        return new ProductService().getDivisionById(id)
    }

}
