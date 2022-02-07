package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class FinancialYearMasterService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return FinancialYearMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FinancialYearMaster.findAllByStartDate("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return FinancialYearMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return FinancialYearMaster.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    FinancialYearMaster get(String id) {
        return FinancialYearMaster.findById(Long.parseLong(id))
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
                orderColumn = "startDate"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def financialYearCriteria = FinancialYearMaster.createCriteria()
        def financialYearCriteriaArrayList = financialYearCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('startDate', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = financialYearCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", financialYearCriteriaArrayList)
        return jsonObject
    }

    FinancialYearMaster save(JSONObject jsonObject) {
        FinancialYearMaster financialYearMaster = new FinancialYearMaster()
        financialYearMaster.startDate = jsonObject.get("startDate").toString()
        financialYearMaster.endDate = jsonObject.get("endDate").toString()
        financialYearMaster.status = Long.parseLong(jsonObject.get("status").toString())
        financialYearMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        financialYearMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        financialYearMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        financialYearMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        financialYearMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        financialYearMaster.save(flush: true)
        if (!financialYearMaster.hasErrors())
            return financialYearMaster
        else
            throw new BadRequestException()
    }

    FinancialYearMaster update(JSONObject jsonObject, String id) {
        FinancialYearMaster financialYearMaster = FinancialYearMaster.findById(Long.parseLong(id))
        if (financialYearMaster) {
            financialYearMaster.isUpdatable = true
            financialYearMaster.startDate = jsonObject.get("startDate").toString()
            financialYearMaster.endDate = jsonObject.get("endDate").toString()
            financialYearMaster.status = Long.parseLong(jsonObject.get("status").toString())
            financialYearMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            financialYearMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            financialYearMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            financialYearMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            financialYearMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            financialYearMaster.save(flush: true)
            if (!financialYearMaster.hasErrors())
                return financialYearMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            FinancialYearMaster financialYearMaster = FinancialYearMaster.findById(Long.parseLong(id))
            if (financialYearMaster) {
                financialYearMaster.isUpdatable = true
                financialYearMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
