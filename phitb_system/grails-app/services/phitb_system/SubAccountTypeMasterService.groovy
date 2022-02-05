package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class SubAccountTypeMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return SubAccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return SubAccountTypeMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    SubAccountTypeMaster get(String id) {
        return SubAccountTypeMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(long limit, long offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return SubAccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return SubAccountTypeMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "name"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = SubAccountTypeMaster.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                    accountTypeMaster{
                        ilike('name', '%' + searchTerm + '%')
                    }
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = dayMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", dayMasterArrayList)
        return jsonObject
    }

    SubAccountTypeMaster save(JSONObject jsonObject) {
        SubAccountTypeMaster subAccountType = new SubAccountTypeMaster()
        subAccountType.name = jsonObject.get("name")
        subAccountType.accountType = AccountTypeMaster.findById(Long.parseLong(jsonObject.get("accountTypeId").toString()))
        subAccountType.save(flush: true)
        if (!subAccountType.hasErrors())
            return subAccountType
        else
            throw new BadRequestException()
    }

    SubAccountTypeMaster update(JSONObject jsonObject, String id) {
        if (id) {
            SubAccountTypeMaster subAccountType = SubAccountTypeMaster.findById(Long.parseLong(id))
            if (subAccountType) {
                subAccountType.isUpdatable = true
                subAccountType.name = jsonObject.get("name")
                subAccountType.accountType = AccountTypeMaster.findById(Long.parseLong(jsonObject.get("accountTypeId").toString()))
                subAccountType.save(flush: true)
                if (!subAccountType.hasErrors())
                    return subAccountType
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            SubAccountTypeMaster subAccountType = SubAccountTypeMaster.findById(Long.parseLong(id))
            if (subAccountType) {
                subAccountType.isUpdatable = true
                subAccountType.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
