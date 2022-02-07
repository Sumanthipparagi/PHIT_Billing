package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class RuleMasterService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return RuleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RuleMaster.findAllByCheckDateIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return RuleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return RuleMaster.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    RuleMaster get(String id) {
        return RuleMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "checkDate"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def ruleMasterCriteria = RuleMaster.createCriteria()
        def ruleMasterCriteriaArrayList = ruleMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('checkDate', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = ruleMasterCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", ruleMasterCriteriaArrayList)
        return jsonObject
    }

    RuleMaster save(JSONObject jsonObject) {
        RuleMaster ruleMaster = new RuleMaster()
        ruleMaster.checkDate = jsonObject.get("checkDate").toString()
        ruleMaster.dlExpired = Long.parseLong(jsonObject.get("dlExpired").toString())
        ruleMaster.foodLicenseExpired = Long.parseLong(jsonObject.get("foodLicenseExpired").toString())
        ruleMaster.salesValueLimit = Long.parseLong(jsonObject.get("salesValueLimit").toString())
        ruleMaster.creditGraceCheck = Long.parseLong(jsonObject.get("creditGraceCheck").toString())
        ruleMaster.scheme = Long.parseLong(jsonObject.get("scheme").toString())
        ruleMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        ruleMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        ruleMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        ruleMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        ruleMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        ruleMaster.save(flush: true)
        if (!ruleMaster.hasErrors())
            return ruleMaster
        else
            throw new BadRequestException()
    }

    RuleMaster update(JSONObject jsonObject, String id) {
        RuleMaster ruleMaster = RuleMaster.findById(Long.parseLong(id))
        if (ruleMaster) {
            ruleMaster.isUpdatable = true
            ruleMaster.checkDate = jsonObject.get("checkDate").toString()
            ruleMaster.dlExpired = Long.parseLong(jsonObject.get("dlExpired").toString())
            ruleMaster.foodLicenseExpired = Long.parseLong(jsonObject.get("foodLicenseExpired").toString())
            ruleMaster.salesValueLimit = Long.parseLong(jsonObject.get("salesValueLimit").toString())
            ruleMaster.creditGraceCheck = Long.parseLong(jsonObject.get("creditGraceCheck").toString())
            ruleMaster.scheme = Long.parseLong(jsonObject.get("scheme").toString())
            ruleMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            ruleMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            ruleMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            ruleMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            ruleMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            ruleMaster.save(flush: true)
            if (!ruleMaster.hasErrors())
                return ruleMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            RuleMaster ruleMaster = RuleMaster.findById(Long.parseLong(id))
            if (ruleMaster) {
                ruleMaster.isUpdatable = true
                ruleMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
