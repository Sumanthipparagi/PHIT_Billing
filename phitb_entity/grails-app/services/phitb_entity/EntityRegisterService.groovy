package phitb_entity

import grails.gorm.transactions.Transactional
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
            return EntityRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
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
            return EntityRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EntityRegister.createCriteria().list(max: l,offset:o){
                entityType{
                    eq('id',entityTypeId)
                }
            }
        }
    }


    EntityRegister get(String id)
    {
        return EntityRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

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
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = entityRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", entityRegisterArrayList)
        return jsonObject
    }

    EntityRegister save(JSONObject jsonObject)
    {
        EntityRegister entityRegister = new EntityRegister()
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
        entityRegister.mobileNumber = Long.parseLong(jsonObject.get("mobileNumber").toString())
        entityRegister.email = jsonObject.get("email").toString()
        entityRegister.contactName = jsonObject.get("contactName").toString()
        entityRegister.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        entityRegister.pan = jsonObject.get("pan").toString()
        entityRegister.gstn = jsonObject.get("gstn").toString()
        entityRegister.usdNumber = jsonObject.get("usdNumber").toString()
        entityRegister.corpId = jsonObject.get("corpId").toString()
        entityRegister.drugLicence1 = jsonObject.get("drugLicence1").toString()
        entityRegister.drugLicence2 = jsonObject.get("drugLicence2").toString()
        entityRegister.drugLicenceValidity = jsonObject.get("drugLicenceValidity").toString()
        entityRegister.foodLicenceValidity = jsonObject.get("foodLicenceValidity").toString()
        entityRegister.salesBalanceLimit = Long.parseLong(jsonObject.get("salesBalanceLimit").toString())
        entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
        entityRegister.noOfGraceDays = Long.parseLong(jsonObject.get("noOfGraceDays").toString())
        entityRegister.calculateOn = Long.parseLong(jsonObject.get("calculateOn").toString())
        entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
        entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
        entityRegister.accountNo = jsonObject.get("accountNo").toString()
        entityRegister.upiId = jsonObject.get("upiId").toString()
        entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
        entityRegister.openingBalance = Long.parseLong(jsonObject.get("openingBalance").toString())
        entityRegister.currentBalance = Long.parseLong(jsonObject.get("currentBalance").toString())
        entityRegister.discount = Long.parseLong(jsonObject.get("discount").toString())
        entityRegister.bankCommision = Long.parseLong(jsonObject.get("bankCommision").toString())
        entityRegister.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
        entityRegister.defaultCharge = Long.parseLong(jsonObject.get("defaultCharge").toString())
        entityRegister.careTaker = Long.parseLong(jsonObject.get("careTaker").toString())
        entityRegister.contact = jsonObject.get("contact").toString()
        entityRegister.terms = jsonObject.get("terms").toString()
        entityRegister.salesman = Long.parseLong(jsonObject.get("salesman").toString())
        entityRegister.manager = Long.parseLong(jsonObject.get("manager").toString())
        entityRegister.status = Long.parseLong(jsonObject.get("status").toString())
        entityRegister.salesmanCommission = Long.parseLong(jsonObject.get("salesmanCommission").toString())
        entityRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        entityRegister.routeId = Long.parseLong(jsonObject.get("routeId").toString())
        entityRegister.accountId = jsonObject.get("accountId").toString()
        entityRegister.aadharId = jsonObject.get("aadharId").toString()
        entityRegister.companyCode = jsonObject.get("companyCode").toString()
        entityRegister.faxNumber = jsonObject.get("faxNumber").toString()
        entityRegister.repName = jsonObject.get("repName").toString()
        entityRegister.repPhoneNumber = jsonObject.get("repPhoneNumber").toString()
        entityRegister.password = jsonObject.get("password").toString()
        entityRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
        entityRegister.contactDob = jsonObject.get("contactDob").toString()
        entityRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        entityRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
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
            entityRegister.mobileNumber = Long.parseLong(jsonObject.get("mobileNumber").toString())
            entityRegister.email = jsonObject.get("email").toString()
            entityRegister.contactName = jsonObject.get("contactName").toString()
            entityRegister.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
            entityRegister.pan = jsonObject.get("pan").toString()
            entityRegister.gstn = jsonObject.get("gstn").toString()
            entityRegister.usdNumber = jsonObject.get("usdNumber").toString()
            entityRegister.corpId = jsonObject.get("corpId").toString()
            entityRegister.drugLicence1 = jsonObject.get("drugLicence1").toString()
            entityRegister.drugLicence2 = jsonObject.get("drugLicence2").toString()
            entityRegister.drugLicenceValidity = jsonObject.get("drugLicenceValidity").toString()
            entityRegister.foodLicenceValidity = jsonObject.get("foodLicenceValidity").toString()
            entityRegister.salesBalanceLimit = Long.parseLong(jsonObject.get("salesBalanceLimit").toString())
            entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
            entityRegister.noOfGraceDays = Long.parseLong(jsonObject.get("noOfGraceDays").toString())
            entityRegister.calculateOn = Long.parseLong(jsonObject.get("calculateOn").toString())
            entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
            entityRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
            entityRegister.accountNo = jsonObject.get("accountNo").toString()
            entityRegister.upiId = jsonObject.get("upiId").toString()
            entityRegister.noOfCrDays = Long.parseLong(jsonObject.get("noOfCrDays").toString())
            entityRegister.openingBalance = Long.parseLong(jsonObject.get("openingBalance").toString())
            entityRegister.currentBalance = Long.parseLong(jsonObject.get("currentBalance").toString())
            entityRegister.discount = Long.parseLong(jsonObject.get("discount").toString())
            entityRegister.bankCommision = Long.parseLong(jsonObject.get("bankCommision").toString())
            entityRegister.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
            entityRegister.defaultCharge = Long.parseLong(jsonObject.get("defaultCharge").toString())
            entityRegister.careTaker = Long.parseLong(jsonObject.get("careTaker").toString())
            entityRegister.contact = jsonObject.get("contact").toString()
            entityRegister.terms = jsonObject.get("terms").toString()
            entityRegister.salesman = Long.parseLong(jsonObject.get("salesman").toString())
            entityRegister.manager = Long.parseLong(jsonObject.get("manager").toString())
            entityRegister.status = Long.parseLong(jsonObject.get("status").toString())
            entityRegister.salesmanCommission = Long.parseLong(jsonObject.get("salesmanCommission").toString())
            entityRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            entityRegister.routeId = Long.parseLong(jsonObject.get("routeId").toString())
            entityRegister.accountId = jsonObject.get("accountId").toString()
            entityRegister.aadharId = jsonObject.get("aadharId").toString()
            entityRegister.companyCode = jsonObject.get("companyCode").toString()
            entityRegister.faxNumber = jsonObject.get("faxNumber").toString()
            entityRegister.repName = jsonObject.get("repName").toString()
            entityRegister.repPhoneNumber = jsonObject.get("repPhoneNumber").toString()
            entityRegister.password = jsonObject.get("password").toString()
            entityRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
            entityRegister.contactDob = jsonObject.get("contactDob").toString()
            entityRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            entityRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
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
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id))
            if (entityRegister)
            {
                entityRegister.isUpdatable = true
                entityRegister.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }
}
