package phitb_ui.entity


import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.accounts.BankRegisterController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.DistrictController
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
            JSONArray parentEntities = new JSONArray()
            def parentEntitiesResponse = new EntityService().getParentEntities(session.getAttribute("entityId").toString())
            if(parentEntitiesResponse.status == 200)
            {
                parentEntities = new JSONArray(parentEntitiesResponse.readEntity(String.class))
            }
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
                                                                          entitytype  : entitytype, account: account,
                                                                          parentEntities: parentEntities
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
            String entityId = session.getAttribute("entityId").toString()
            ArrayList<String> hqareas = new HQAreasController().getByEntity() as ArrayList<String>
            //ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> account = new EntityService().getAllAccountByEntity(entityId) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().getByEntity() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>

            JSONArray routes = new EntityService().getRouteByEntity(entityId)
            def priority = new SystemService().getPriorityByEntity(entityId)
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER) && it.entityId.toString() ==
                        entityId) {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN) && it.entity.id.toString() ==
                        entityId) {
                    salesmanList.add(it)
                }
            }
            JSONArray parentEntities = new JSONArray()
            def parentEntitiesResponse = new EntityService().getParentEntities(entityId)
            if(parentEntitiesResponse.status == 200)
            {
                parentEntities = new JSONArray(parentEntitiesResponse.readEntity(String.class))
            }
            render(view: '/entity/entityRegister/add-entity-register', model: [entitytype    : entitytype,
                                                                               statelist     : statelist, countrylist: countrylist,
                                                                               salesmanList  : salesmanList,
                                                                               managerList   : managerList,
                                                                               zoneList      : zoneList,
                                                                               routeregister : routes,
                                                                               bank          : bank, parentEntities:parentEntities,
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
           // ArrayList<String> routeregister = new RouteController().show() as ArrayList<String>
            ArrayList<String> hqareas = new HQAreasController().getByEntity() as ArrayList<String>
            ArrayList<String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList<String> account = new EntityService().getAllAccountByEntity(session.getAttribute('entityId').toString()) as ArrayList<String>
            ArrayList<String> entitytype = new EntityService().getEntityType() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            JSONArray routes = new EntityService().getRouteByEntity(session.getAttribute("entityId").toString())
            def priority = new SystemService().getAllPriority()
            String city = entity.cityId.toString()
            def cityId = new SystemService().getCityById(city.toString())
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER) && it.entityId.toString() ==
                        session.getAttribute('entityId').toString()) {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN) && it.entityId.toString() ==
                        session.getAttribute('entityId').toString()) {
                    salesmanList.add(it)
                }
            }

            JSONArray parentEntities = new JSONArray()
            def parentEntitiesResponse = new EntityService().getParentEntities(session.getAttribute("entityId").toString())
            if(parentEntitiesResponse.status == 200)
            {
                parentEntities = new JSONArray(parentEntitiesResponse.readEntity(String.class))
            }

            render(view: '/entity/entityRegister/add-entity-register', model: [entity       : entity, entitytype: entitytype,
                                                                               statelist    : statelist, countrylist: countrylist,
                                                                               salesmanList : salesmanList,
                                                                               managerList  : managerList,
                                                                               zoneList     : zoneList,
                                                                               routeregister: routes, parentEntities:parentEntities,
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
            if(params.parentEntityId) {
                jsonObject.put("parentEntityId", params.parentEntityId)
            }
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

    def parentEntityDataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            if (session.getAttribute("role").toString().equalsIgnoreCase(new Constants().SUPER_USER)) {
                jsonObject.put("superuser", true)
            }
            def apiResponse = new EntityService().showParentEntities(jsonObject)
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
            if(session.getAttribute("role").toString().equalsIgnoreCase(Constants.SUPER_USER)
                    || session.getAttribute("role").toString().equalsIgnoreCase(Constants.ENTITY_ADMIN))
            {
                if(jsonObject.has("isParent")) {
                    boolean isParent = Boolean.parseBoolean(jsonObject.get("isParent"))
                    if (!isParent) {
                        String entityId = jsonObject.get("affiliatedToEntity").toString().split("_")[0]
                        String entityTypeId = jsonObject.get("affiliatedToEntity").toString().split("_")[1]
                        jsonObject.put("affiliateId", entityId)
                        jsonObject.put("parentEntity", entityId)
                        jsonObject.put("parentEntityType", entityTypeId)
                        jsonObject.put("isParent", false)
                    } else {
                        jsonObject.put("affiliateId", 0)
                        jsonObject.put("isParent", true)
                        jsonObject.put("parentEntity", 0)
                        jsonObject.put("parentEntityType", 0)
                    }
                }
                else
                {
                    jsonObject.put("affiliateId", session.getAttribute("entityId"))
                    jsonObject.put("parentEntity", session.getAttribute("entityId"))
                    jsonObject.put("parentEntityType", session.getAttribute("entityTypeId"))
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
                JSONObject entity = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: "/entity-register/update-entity-register/"+entity.id+"?code=1")
            } else {
                redirect(uri: "/entity-register/update-entity-register/"+jsonObject.id+"?code=2")
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

    def getByParent() {
        try {
            String search = params.search
            String page = params.page
            String id = session.getAttribute("entityId")
            JSONObject jsonObject = new EntityService().getByEntityPaginated(id,page,search)
            if (jsonObject) {
                JSONArray finalEntities = new JSONArray()
                JSONArray entities = jsonObject.get("entities") as JSONArray
                for (JSONObject entity : entities)
                {
                    if (entity?.cityId != 0)
                    {
                        def city = new SystemService().getCityById(entity?.cityId?.toString())
                        entity.put("city", city)

                        finalEntities.add(entity)
                    }
                }
                jsonObject.put("entities", finalEntities)
                respond jsonObject, formats:['json']
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


    def bulkImport() {
        try {

            def taxRegister = new EntityService().getTaxesByEntity(session.getAttribute('entityId').toString())
            def division = new ProductService().getDivisionsByEntityId(session.getAttribute('entityId').toString())
            def products = new ProductService().getProductByEntity(session.getAttribute('entityId').toString())
            render(view: '/entity/entityRegister/bulk-import-wizard',model:[taxRegister:taxRegister,
                                                                            division:division,products:products])
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

    def entityOnBoardInfo(){
        ArrayList<String> statelist = new StateController().show() as ArrayList<String>
        ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
        ArrayList<String> citylist = new CityController().show() as ArrayList<String>
        ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
        ArrayList<String> districts = new DistrictController().show()
        render(view:'/entity/entityRegister/onBoardWizard',model: [statelist: statelist, countrylist: countrylist,
                                                                   citylist:citylist,zoneList: zoneList,
                                                                   districts:districts])
    }

    def saveEntityOnBoardInfo(){
        JSONObject paramsJsonObject = new JSONObject(params)
        if(paramsJsonObject!=null)
        {
            JSONObject response = new JSONObject()
//        Series
            JSONObject series = new JSONObject()
            series.put("seriesName", paramsJsonObject.seriesName)
            series.put("seriesCode", paramsJsonObject.seriesCode)
            series.put("mode", "1")
            series.put("saleId", paramsJsonObject.saleId)
            series.put("saleReturnId", paramsJsonObject.saleReturnId)
            series.put("saleOrderId", paramsJsonObject.saleOrderId)
            series.put("purId", paramsJsonObject.purId)
            series.put("purchaseOrderId", paramsJsonObject.purchaseOrderId)
            series.put("goodsTransferId", paramsJsonObject.goodsTransferId)
            series.put("sampleInvoiceId", paramsJsonObject.sampleInvoiceId)
            series.put("status", "0")
            series.put("syncStatus", "0")
            series.put("entityType", session.getAttribute('entityTypeId').toString())
            series.put("entity", session.getAttribute('entityId').toString())
            series.put("modifiedUser", session.getAttribute('userId').toString())
            series.put("createdUser", session.getAttribute('userId').toString())
            def seriesResponse = new EntityService().saveSeries(series)
            JSONObject seriesObj
            if (seriesResponse?.status == 200)
            {
                seriesObj = new JSONObject(seriesResponse.readEntity(String.class))
                println(seriesResponse)
            }

            //Division
            JSONObject division = new JSONObject()
            division.put("divisionName", paramsJsonObject.divisionName)
            division.put("divisionShortName", paramsJsonObject.divisionShortName)
            division.put("cityIds", paramsJsonObject.cityIds)
            division.put("zoneIds", paramsJsonObject.zoneIds)
            division.put("stateIds", paramsJsonObject.stateIds)
            division.put("seriesId", seriesObj.id)
            division.put("managerId", session.getAttribute('userId').toString())
            division.put("customerIds", '')
            division.put("status", 0)
            division.put("syncStatus", 0)
            division.put("syncStatus", 0)
            division.put("entityTypeId", session.getAttribute('entityTypeId').toString())
            division.put("entityId", session.getAttribute('entityId').toString())
            division.put("modifiedUser", session.getAttribute('userId').toString())
            division.put("createdUser", session.getAttribute('userId').toString())
            def divisionResponse = new ProductService().saveDivision(division)
            if (divisionResponse?.status == 200)
            {
                println(divisionResponse)
            }
            //priority
            JSONObject priority = new JSONObject()
            priority.put("priority", paramsJsonObject.priority)
            priority.put("entity", session.getAttribute('entityId').toString())
            def priorityResponse = new SystemService().savePriority(priority)
            if (priorityResponse?.status == 200)
            {
                print(priorityResponse)
            }
            response.put("priority", priorityResponse.status)
            response.put("division", divisionResponse.status)
            response.put("series", seriesResponse.status)
            respond response, formats: ['json'], status: 200;
        }else{
            response.status =400
        }
    }

    def getOnBoardDetails(){
        try{
            JSONObject jsonObject = new JSONObject()
            def series = new EntityService().getSeriesByEntity(session.getAttribute('entityId').toString())
            if(series?.status == 200){
                series = new JSONArray(series.readEntity(String.class))
            }
            def division = new ProductService().getDivisionsByEntityId(session.getAttribute('entityId').toString())
            def priority = new SystemService().getPriorityByEntity(session.getAttribute('entityId').toString())
            jsonObject.put("series", series[0])
            jsonObject.put("division", division)
            jsonObject.put("priority", priority)
            respond jsonObject, formats: ['json'], status: 200
        }
        catch(Exception ex){
            println(ex)
        }
    }

    def checkExistingPhone(){
        String entityId = session.getAttribute('entityId').toString()
        def existingPhone = new EntityService().checkPhoneNumber(params.phoneNumber,entityId)
        JSONObject jsonObject = new JSONObject()
        if(existingPhone!=null){
            JSONObject entityObj = new JSONObject(existingPhone.readEntity(String.class))
            if(existingPhone?.status == 200){
                jsonObject.put('status',false)
                jsonObject.put('obj', entityObj)
                respond jsonObject, formats: ['json'], status: 200
            }else{
                jsonObject.put('status',true)
                respond jsonObject, formats: ['json'], status: 200
            }
        }else{
            response.status = 400
        }
    }

    def registerPatient(){
        try{
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put('entityId', session.getAttribute('entityId'))
            String cityId = jsonObject.get("cityId")
            JSONObject city = new SystemService().getCityById(cityId)
            jsonObject.put("stateId", city.get("state")["id"])
            jsonObject.put("pinCode", city.get("pincode"))
            def entityService = new EntityService().savePatientDetails(jsonObject)
            if(entityService?.status == 200){
                JSONObject jsonObject1 = new JSONObject(entityService.readEntity(String.class))
                respond jsonObject1, formats: ['json'], status: 200
            }
        }catch(Exception ex){
            println(ex)
        }
    }
}
