package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class AccountModesMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return AccountModesMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return AccountModesMaster.findAllByModeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    AccountModesMaster get(String id) {
        return AccountModesMaster.findById(Long.parseLong(id))
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
                orderColumn = "mode"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def accountModesMasterCriteria = AccountModesMaster.createCriteria()
        def accountModesMasterArrayList = accountModesMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('mode', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = accountModesMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", accountModesMasterArrayList)
        return jsonObject
    }

    AccountModesMaster save(JSONObject jsonObject) {
        String mode = jsonObject.get("mode")
        if (mode) {
            AccountModesMaster accountModesMaster = new AccountModesMaster()
            accountModesMaster.mode = mode
            accountModesMaster.save(flush: true)
            if (!accountModesMaster.hasErrors())
                return accountModesMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    AccountModesMaster update(JSONObject jsonObject, String id) {
        String mode = jsonObject.get("mode")
        if (mode && id) {
            AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
            if (accountModesMaster) {
                accountModesMaster.isUpdatable = true
                accountModesMaster.mode = mode
                accountModesMaster.save(flush: true)
                if (!accountModesMaster.hasErrors())
                    return accountModesMaster
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
            AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
            if (accountModesMaster) {
                accountModesMaster.isUpdatable = true
                accountModesMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
