package phitb_entity

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EntityRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return EntityRegister.findAll([sort: 'entityName', max: l, offset: o, order: 'asc'])
        }
        else
        {
            return EntityRegister.findAllByEntityNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntityType(String limit, String offset, long entityTypeId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityTypeId)
        {
            return EntityRegister.findAll([sort: 'entityName', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EntityRegister.createCriteria().list(max: l,offset:o){
                entityType{
                    eq('id',entityTypeId)
                }
                order("entityName", "asc")
            }
        }
    }


    EntityRegister get(String id)
    {
        return EntityRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        long entityId = paramsJsonObject.get("entityId")
        boolean isSuperUser = false
        if(paramsJsonObject.has("superuser"))
            isSuperUser = paramsJsonObject.get("superuser")
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        start = paramsJsonObject.get("start")
        length = paramsJsonObject.get("length")

        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "entityName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def entityRegisterCriteria = EntityRegister.createCriteria()
        def entityRegisterArrayList = entityRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('entityName', '%' + searchTerm + '%')
                }
            }

            if(!isSuperUser)
                eq('parentEntity', entityId)
            else
                eqProperty("id", "parentEntity")

            eq('deleted', false)
            order(orderColumn, orderDir)
        }

/*        def manager = []
        entityRegisterArrayList.each {
            println(it.manager)
            if(it.manager != 0) {
                def apires1 = getAllByUser(it.manager.toString())
                manager.push(apires1)
            }
        }

        def salesman = []
        entityRegisterArrayList.each {
            println(it.salesman)
            if(it.salesman != 0) {
                def apires2 = getAllByUser(it.salesman.toString())
                salesman.push(apires2)
            }
        }*/
        def recordsTotal = entityRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
/*        jsonObject.put("salesman", salesman)
        jsonObject.put("manager", manager)*/
        jsonObject.put("data", entityRegisterArrayList)
        return jsonObject
    }

    EntityRegister save(JSONObject jsonObject)
    {
        EntityRegister entityRegister = new EntityRegister()
        boolean isParent = false
        if(jsonObject.has("isParent"))
            isParent = jsonObject.get("isParent")

        entityRegister.entityName = jsonObject.get("entityName").toString()
        entityRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        if(isParent)
        {
            entityRegister.parentEntity = 0
            entityRegister.parentEntityType = entityRegister.entityType.id
        }
        else
        {
            entityRegister.parentEntity = Long.parseLong(jsonObject.get("parentEntity").toString())
            entityRegister.parentEntityType = Long.parseLong(jsonObject.get("parentEntityType").toString())
        }

        entityRegister.affiliateId = Long.parseLong(jsonObject.get("affiliateId").toString())
        entityRegister.addressLine1 = jsonObject.get("addressLine1").toString()
        entityRegister.addressLine2 = jsonObject.get("addressLine2").toString()
        entityRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
        entityRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
        entityRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
        entityRegister.pinCode = jsonObject.get("pinCode").toString()
        entityRegister.phoneNumber = jsonObject.get("phoneNumber").toString()
        entityRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
        entityRegister.email = jsonObject.get("email").toString()
        entityRegister.contactName = jsonObject.get("contactName").toString()
        entityRegister.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        entityRegister.pan = jsonObject.get("pan").toString()
        entityRegister.gstn = jsonObject.get("gstn").toString()
        entityRegister.usdNumber = jsonObject.get("usdNumber").toString()
        entityRegister.corpId = 0
        entityRegister.drugLicence1 = jsonObject.get("drugLicence1").toString()
        entityRegister.drugLicence2 = jsonObject.get("drugLicence2").toString()
        entityRegister.foodLicence1 = jsonObject.get("foodLicence1").toString()
        entityRegister.drugLicenceValidity = jsonObject.get("drugLicenceValidity").toString()
        entityRegister.foodLicenceValidity = jsonObject.get("foodLicenceValidity").toString()
        entityRegister.salesBalanceLimit = Double.parseDouble(jsonObject.get("salesBalanceLimit").toString())
        entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
        entityRegister.noOfGraceDays = Long.parseLong(jsonObject.get("noOfGraceDays").toString())
        entityRegister.calculateOn = jsonObject.get("calculateOn").toString()
        if(jsonObject.get("bankId")!="0" || jsonObject.get("bankId")!="")
        {
            entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
        }
        else {
            entityRegister.bankId = 0
        }
        entityRegister.accountNo = jsonObject.get("accountNo").toString()
        entityRegister.upiId = jsonObject.get("upiId").toString()
        entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
        entityRegister.openingBalance = Double.parseDouble(jsonObject.get("openingBalance").toString())
        entityRegister.currentBalance = Double.parseDouble(jsonObject.get("currentBalance").toString())
        entityRegister.discount = Double.parseDouble(jsonObject.get("discount").toString())
        entityRegister.bankCommision = Double.parseDouble(jsonObject.get("bankCommision").toString())
        entityRegister.transportTypeId = Long.parseLong("0")
        entityRegister.defaultCharge = Double.parseDouble(jsonObject.get("defaultCharge").toString())
        entityRegister.careTaker = Long.parseLong("0")
        entityRegister.contact = jsonObject.get("contact").toString()
        entityRegister.terms = jsonObject.get("terms").toString()
        if(!jsonObject.isNull("salesman"))
        {
            entityRegister.salesman = Long.parseLong(jsonObject.get("salesman").toString())
        }
        else {
            entityRegister.salesman = 0
        }
        if(!jsonObject.isNull("manager"))
        {
            entityRegister.manager = Long.parseLong(jsonObject.get("manager").toString())
        }
        else {
            entityRegister.manager = 0
        }

        if(!jsonObject.isNull("routeId"))
        {
            entityRegister.routeId = Long.parseLong(jsonObject.get("routeId").toString())
        }
        else {
            entityRegister.routeId = 0
        }
        entityRegister.status = Long.parseLong(jsonObject.get("status").toString())
        if(!jsonObject.isNull("salesmanCommission"))
        {
            entityRegister.salesmanCommission = Double.parseDouble(jsonObject.get("salesmanCommission").toString())
        }
        else
        {
            entityRegister.salesmanCommission = 0
        }
        if(jsonObject.get("hqarea")!="0" || jsonObject.get("hqarea")!="")
        {
            entityRegister.hqAreaId = Long.parseLong(jsonObject.get("hqarea").toString())
        }
        else
        {
            entityRegister.hqAreaId = 0;
        }
        entityRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        entityRegister.accountId = jsonObject.get("accountId").toString()
        entityRegister.aadharId = jsonObject.get("aadharId").toString()
        entityRegister.companyCode = jsonObject.get("companyCode").toString()
        entityRegister.faxNumber = jsonObject.get("faxNumber").toString()
        entityRegister.repName = jsonObject.get("repName").toString()
        entityRegister.repPhoneNumber = jsonObject.get("repPhoneNumber").toString()
//        entityRegister.password = jsonObject.get("password").toString()
        entityRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
        entityRegister.contactDob = jsonObject.get("contactDob").toString()
        entityRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        entityRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())

        entityRegister.save(flush: true)
        if (!entityRegister.hasErrors())
        {
            if(isParent) {
                entityRegister.affiliateId = entityRegister.id
                entityRegister.parentEntity = entityRegister.id
                entityRegister.isUpdatable = true
                entityRegister.save(flush: true)
            }
            return entityRegister
        }
        else
        {
            throw new BadRequestException()
        }
    }

    EntityRegister update(JSONObject jsonObject, String id)
    {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id))
        if (entityRegister)
        {
            entityRegister.isUpdatable = true
            entityRegister.entityName = jsonObject.get("entityName").toString()
            entityRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            entityRegister.affiliateId = Long.parseLong(jsonObject.get("affiliateId").toString())
            entityRegister.addressLine1 = jsonObject.get("addressLine1").toString()
            entityRegister.addressLine2 = jsonObject.get("addressLine2").toString()
            entityRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            entityRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
            entityRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
            entityRegister.pinCode = jsonObject.get("pinCode").toString()
            entityRegister.phoneNumber = jsonObject.get("phoneNumber").toString()
            entityRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
            entityRegister.email = jsonObject.get("email").toString()
            entityRegister.contactName = jsonObject.get("contactName").toString()
            entityRegister.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
            entityRegister.pan = jsonObject.get("pan").toString()
            entityRegister.gstn = jsonObject.get("gstn").toString()
            entityRegister.usdNumber = jsonObject.get("usdNumber").toString()
            entityRegister.corpId = 0
            entityRegister.drugLicence1 = jsonObject.get("drugLicence1").toString()
            entityRegister.drugLicence2 = jsonObject.get("drugLicence2").toString()
            entityRegister.foodLicence1 = jsonObject.get("foodLicence1").toString()
            entityRegister.drugLicenceValidity = jsonObject.get("drugLicenceValidity").toString()
            entityRegister.foodLicenceValidity = jsonObject.get("foodLicenceValidity").toString()
            entityRegister.salesBalanceLimit = Double.parseDouble(jsonObject.get("salesBalanceLimit").toString())
            entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
            entityRegister.noOfGraceDays = Long.parseLong(jsonObject.get("noOfGraceDays").toString())
            entityRegister.calculateOn = jsonObject.get("calculateOn").toString()
            if (jsonObject.get("bankId") != "0" || jsonObject.get("bankId") != "")
            {
                entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
            }
            else
            {
                entityRegister.bankId = 0
            }
            entityRegister.accountNo = jsonObject.get("accountNo").toString()
            entityRegister.upiId = jsonObject.get("upiId").toString()
            entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
            entityRegister.openingBalance = Double.parseDouble(jsonObject.get("openingBalance").toString())
            entityRegister.currentBalance = Double.parseDouble(jsonObject.get("currentBalance").toString())
            entityRegister.discount = Double.parseDouble(jsonObject.get("discount").toString())
            entityRegister.bankCommision = Double.parseDouble(jsonObject.get("bankCommision").toString())
            entityRegister.transportTypeId = Long.parseLong("0")
            entityRegister.defaultCharge = Double.parseDouble(jsonObject.get("defaultCharge").toString())
            entityRegister.careTaker = Long.parseLong("0")
            entityRegister.contact = jsonObject.get("contact").toString()
            entityRegister.terms = jsonObject.get("terms").toString()
            if (!jsonObject.isNull("salesman"))
            {
                entityRegister.salesman = Long.parseLong(jsonObject.get("salesman").toString())
            }
            else
            {
                entityRegister.salesman = 0
            }
            if (!jsonObject.isNull("manager"))
            {
                entityRegister.manager = Long.parseLong(jsonObject.get("manager").toString())
            }
            else
            {
                entityRegister.manager = 0
            }

            if (!jsonObject.isNull("routeId"))
            {
                entityRegister.routeId = Long.parseLong(jsonObject.get("routeId").toString())
            }
            else
            {
                entityRegister.routeId = 0
            }
            entityRegister.status = Long.parseLong(jsonObject.get("status").toString())
            if (!jsonObject.isNull("salesmanCommission"))
            {
                entityRegister.salesmanCommission = Double.parseDouble(jsonObject.get("salesmanCommission").toString())
            }
            else
            {
                entityRegister.salesmanCommission = 0
            }
            if (jsonObject.get("hqarea") != "0" || jsonObject.get("hqarea") != "")
            {
                entityRegister.hqAreaId = Long.parseLong(jsonObject.get("hqarea").toString())
            }
            else
            {
                entityRegister.hqAreaId = 0;
            }
            entityRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            entityRegister.accountId = jsonObject.get("accountId").toString()
            entityRegister.aadharId = jsonObject.get("aadharId").toString()
            entityRegister.companyCode = jsonObject.get("companyCode").toString()
            entityRegister.faxNumber = jsonObject.get("faxNumber").toString()
            entityRegister.repName = jsonObject.get("repName").toString()
            entityRegister.repPhoneNumber = jsonObject.get("repPhoneNumber").toString()
//        entityRegister.password = jsonObject.get("password").toString()
            entityRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
            entityRegister.contactDob = jsonObject.get("contactDob").toString()
            entityRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            entityRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            entityRegister.parentEntity = Long.parseLong(jsonObject.get("parentEntity").toString())
            entityRegister.parentEntityType = Long.parseLong(jsonObject.get("parentEntityType").toString())
            entityRegister.save(flush: true)
            if (!entityRegister.hasErrors())
            {
                return entityRegister
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else {
            throw new BadRequestException()
        }
    }

    def getAllByUser(String id)
    {
        try
        {
            def url = Constants.API_GATEWAY+Constants.USER_REGISTER_SHOW+"/"+id
            URL apiUrl = new URL(url)
            def card = new JsonSlurper().parseText(apiUrl.text)
            return card
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityRegisterService , action :  getAllByUser  , Ex:' + ex)
            log.error('Service :EntityRegisterService , action :  getAllByUser  , Ex:' + ex)
        }
    }

    def getAllByAffiliateId(long affiliateId) {
        try {
            return EntityRegister.createCriteria().list(){
               /*or{
                   eq("affiliateId", affiliateId)
                   eq("parentEntity", affiliateId)
               }*/
                eq("affiliateId", affiliateId)
                eq('deleted', false)
                order("entityName", "asc")
            }
        }
        catch (Exception ex)
        {
            println(ex.message)
            throw new BadRequestException()
        }
    }


    def getByParentEntity(long entityId) {
        try {
           return EntityRegister.findAllByParentEntity(entityId)
        }
        catch (Exception ex)
        {
            println(ex.stackTrace)
            throw new BadRequestException()
        }
    }

    /**
     * This returns only parent entities which are affiliated to itself.
     * In case of entity admin, returns parent entities affiliated to it
     * @return
     */
    def getParentEntities(String affiliateId = null)
    {
        def entityRegisterCriteria = EntityRegister.createCriteria()
        def entityRegisterArrayList = entityRegisterCriteria.list() {
            eqProperty("id", "parentEntity")
            eq("deleted", false)
            if(affiliateId)
                eq("affiliateId", affiliateId)
        }
        return entityRegisterArrayList
    }
}

