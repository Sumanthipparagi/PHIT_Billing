package phitb_ui.entity

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile
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

import java.text.SimpleDateFormat

class EntityRegisterController {

    def index() {
        try {
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> account = new AccountRegisterController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('MANAGER')) {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('SALESMAN')) {
                    salesmanList.add(it)
                }
            }

            render(view: '/entity/entityRegister/entityRegister', model: [entity      : entity,
                                                                          statelist   : statelist, countrylist: countrylist,
                                                                          salesmanList: salesmanList,
                                                                          managerList : managerList, zoneList: zoneList,
                                                                          entitytype  : entitytype, account: account
            ])
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def addEntity() {
        try {
            ArrayList<String> hqareas = new HQAreasController().getByEntity() as ArrayList<String>
            ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> account = new EntityService().getAllAccountByEntity(session.getAttribute('entityId').toString()) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().getByEntity() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            def priority = new SystemService().getAllPriority()
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER)) {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/entityRegister/add-entity-register', model: [entitytype    : entitytype,
                                                                               statelist     : statelist, countrylist: countrylist,
                                                                               salesmanList  : salesmanList,
                                                                               managerList   : managerList,
                                                                               zoneList      : zoneList,
                                                                               routeregister : routeregister,
                                                                               bank          : bank,
                                                                               priority      : priority, hqareas: hqareas, account: account
            ])
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def updateEntity() {
        try {
            JSONObject entity = new EntityService().getEntityById(params.id)
            ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> hqareas = new HQAreasController().getByEntity() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> account = new EntityService().getAllAccountByEntity(session.getAttribute('entityId').toString()) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            def priority = new SystemService().getAllPriority()
            String city = entity.cityId.toString()
            def cityId = new SystemService().getCityById(city.toString())
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER)) {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                    salesmanList.add(it)
                }
            }

            render(view: '/entity/entityRegister/add-entity-register', model: [entity       : entity, entitytype: entitytype,
                                                                               statelist    : statelist, countrylist: countrylist,
                                                                               salesmanList : salesmanList,
                                                                               managerList  : managerList,
                                                                               zoneList     : zoneList,
                                                                               routeregister: routeregister,
                                                                               bank         : bank, entityList: entityList,
                                                                               priority     : priority, hqareas: hqareas,
                                                                               account      : account, cityId: cityId
            ])
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
            jsonObject.put("entityId", session.getAttribute("entityId"))
            if (session.getAttribute("role").toString().equalsIgnoreCase(new Constants().SUPER_USER)) {
                jsonObject.put("superuser", true)
            }
            def apiResponse = new EntityService().showEntity(jsonObject)
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
            if(session.getAttribute("role").toString().equalsIgnoreCase(Constants.SUPER_USER))
            {
                boolean isParent = Boolean.parseBoolean(jsonObject.get("isParent"))
                if(!isParent)
                {
                    String entityId = jsonObject.get("affiliatedToEntity").toString().split("_")[0]
                    String entityTypeId = jsonObject.get("affiliatedToEntity").toString().split("_")[1]
                    jsonObject.put("affiliateId", entityId)
                    jsonObject.put("parentEntity", entityId)
                    jsonObject.put("parentEntityType", entityTypeId)
                    jsonObject.put("isParent", false)
                }
                else
                {
                    jsonObject.put("affiliateId", 0)
                    jsonObject.put("isParent", true)
                    jsonObject.put("parentEntity", 0)
                    jsonObject.put("parentEntityType", 0)
                }

            }
            else {
                jsonObject.put("affiliateId", session.getAttribute("entityId"))
                jsonObject.put("parentEntity", session.getAttribute("entityId"))
                jsonObject.put("parentEntityType", session.getAttribute("entityTypeId"))
            }

            def apiResponse = new EntityService().saveEntity(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject createdEntity = new JSONObject(apiResponse.readEntity(String.class))
                if(createdEntity)
                {
                    //create financial year
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
                    String fromDate
                    String toDate
                    Calendar cal = Calendar.getInstance()
                    cal.setTime(new Date())

                    if(cal.get(Calendar.MONTH) >= 0 && cal.get(Calendar.MONTH) < 3)
                    {
                        cal.add(Calendar.YEAR, -1)
                        fromDate = sdf.format(cal.getTime())
                        cal.add(Calendar.YEAR, 1)
                        toDate = sdf.format(cal.getTime())
                    }
                    else
                    {
                        fromDate = sdf.format(cal.getTime())
                        cal.add(Calendar.YEAR, 1)
                        toDate = sdf.format(cal.getTime())
                    }

                    JSONObject financialYear = new JSONObject()
                    financialYear.put("startDate", fromDate)
                    financialYear.put("endDate", toDate)
                    financialYear.put("status", 1)
                    financialYear.put("syncStatus", 1)
                    financialYear.put("entityType", createdEntity.get("entityType")["id"])
                    financialYear.put("entity", createdEntity.get("id"))
                    financialYear.put("modifiedUser", session.getAttribute("userId"))
                    financialYear.put("createdUser", session.getAttribute("userId"))

                    new EntityService().saveFinancialYear(financialYear)
                }
                render(view: '/entity/entityRegister/entityRegister')
            }
            else
            {
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
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("parentEntity", session.getAttribute("entityId"))
            jsonObject.put("parentEntityType", session.getAttribute("entityTypeId"))
            jsonObject.put("affiliateId", session.getAttribute("entityId"))
            def apiResponse = new EntityService().putEntity(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: "/entity-register")
//                respond obj, formats: ['json'], status: 200
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
            def apiResponse = new EntityService().deleteEntity(jsonObject)
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
            def apiResponse = new EntityService().getEntity()
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

    def getByAffiliates() {
        try {
            String id = params.id
            def apiResponse = new EntityService().getEntityByAffiliates(id)
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                respond arrayList, formats: ['json']
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

    def getByAffiliateById(String id) {
        try {
            def apiResponse = new EntityService().getEntityByAffiliates(id)
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
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


    def getEnitityById(String id) {
        return new EntityService().getEntityById(id)
    }

    def getEntityTypeById() {
        try {
            def apiResponse = new EntityService().getEntityTypeById(params.id)
            if (apiResponse?.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                respond jsonObject, formats: ['json'], status: 200
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


    def bulkImportCustomer() {
        try {
            render(view: '/entity/entityRegister/bulk-import-customer')
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def customerImport() {
        try {
//            println(params)
//            MultipartFile file = params.file
////            FileInputStream fis=new FileInputStream(file.getInputStream());
//            println(fis)

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getParentEntities() {
        try {
            String affiliateId = null
            if(session.getAttribute("role").toString().equalsIgnoreCase(Constants.ENTITY_ADMIN))
                affiliateId = session.getAttribute("entityId")
            def apiResponse = new EntityService().getParentEntities(affiliateId)
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                respond jsonArray, formats: ['json'], status: 200
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
}
