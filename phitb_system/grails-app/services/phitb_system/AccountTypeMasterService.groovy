package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class AccountTypeMasterService {

    /**
     * Gets all account types
     * @param query
     * @param offset
     * @param limit
     * @return list of account types
     */
    def getAll(String limit, String offset, String query, String entityId = null) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query) {
            if(entityId != null)
                return AccountTypeMaster.findAllByEntityId(Long.parseLong(entityId), [sort: 'id', max: l, offset: o, order: 'desc'])
            else
                return AccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        } else {
            if(entityId != null)
                return AccountTypeMaster.findAllByAccountTypeIlikeAndEntityId("%" + query + "%", Long.parseLong(entityId), [sort: 'id', max: l, offset: o, order: 'desc'])
            else
                return AccountTypeMaster.findAllByAccountTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])

        }
    }

    /**
     * Gets all account types using entityId
     * @param query
     * @param offset
     * @param limit
     * @return list of account types
     */
    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return AccountTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return AccountTypeMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Gets all account type using id
     * @param query
     * @return selected account type
     */
    AccountTypeMaster get(String id) {
        return AccountTypeMaster.findById(Long.parseLong(id))
    }

    /**
     * Gets all account type in datatable format
     * @param query
     * @return selected account type
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
                orderColumn = "accountType"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def accountTypeMasterCriteria = AccountTypeMaster.createCriteria()
        def accountTypeMasterArrayList = accountTypeMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('accountType', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = accountTypeMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", accountTypeMasterArrayList)
        return jsonObject
    }

    /**
     * save account type master
     * @param jsonObject
     * @return saves account type master
     */
    AccountTypeMaster save(JSONObject jsonObject) {
        String accountType = jsonObject.get("accountType")
        if (accountType) {
            AccountTypeMaster accountTypeMaster = new AccountTypeMaster()
            accountTypeMaster.accountType = accountType
            accountTypeMaster.save(flush: true)
            if (!accountTypeMaster.hasErrors())
                return accountTypeMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    /**
     * update account type master
     * @param jsonObject, id
     * @return updates account type master
     */
    AccountTypeMaster update(JSONObject jsonObject, String id) {
        String accountType = jsonObject.get("accountType")
        if (accountType && id) {
            AccountTypeMaster accountTypeMaster = AccountTypeMaster.findById(Long.parseLong(id))
            if (accountTypeMaster) {
                accountTypeMaster.isUpdatable = true
                accountTypeMaster.accountType = accountType
                accountTypeMaster.save(flush: true)
                if (!accountTypeMaster.hasErrors())
                    return accountTypeMaster
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    /**
     * delete account type master
     * @param id
     * @return delete account type master
     */
    void delete(String id) {
        if (id) {
            AccountTypeMaster accountTypeMaster = AccountTypeMaster.findById(Long.parseLong(id))
            if (accountTypeMaster) {
                accountTypeMaster.isUpdatable = true
                accountTypeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
