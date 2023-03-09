package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class SubAccountTypeMasterService {

    /**
     * Gets all Sub Account Types
     * @param query
     * @param offset
     * @param limit
     * @return list of Sub Account Types
     */
    def getAll(String limit, String offset, String query, String entityId = null) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query) {
            if (entityId != null)
                return SubAccountTypeMaster.findAllByEntityId(Long.parseLong(entityId), [sort: 'id', max: l, offset: o, order: 'desc'])
            else
                return SubAccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            if (entityId != null)
                return SubAccountTypeMaster.findAllByNameIlikeAndEntityId("%" + query + "%", Long.parseLong(entityId),[sort: 'id', max: l, offset: o, order: 'desc'])
            else
                return SubAccountTypeMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    /**
     * Get requested Sub Account Type
     * @param id
     * @return get requested Sub Account Type
     */
    SubAccountTypeMaster get(String id) {
        return SubAccountTypeMaster.findById(Long.parseLong(id))
    }

    /**
     * Gets all Sub Account Type by EntityId
     * @param entityId
     * @param limit
     * @param offset
     * @return list of Sub Account Types
     */
    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return SubAccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return SubAccountTypeMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }


    /**
     * Gets all Sub Account Types in datatables format
     * @param jsonObject
     * @param start
     * @param length
     * @return list of Sub Account Types
     */
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

    /**
     * Save new Sub Account Type
     * @param Sub Account Type
     * @return saved Sub Account Type
     */
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

    /**
     * update  Sub Account Type
     * @param Sub Account Type
     * @return updated Sub Account Type
     */
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

    /**
     * Delete selected Sub Account Type
     * @param id
     * @return returns status code 200
     */
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
