package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class AccountRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return AccountRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return AccountRegister.findAllByAccountNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    AccountRegister get(String id) {
        return AccountRegister.findById(Long.parseLong(id))
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
                orderColumn = "generalId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def bankRegisterCriteria = AccountRegister.createCriteria()
        def bankRegisterArrayList = bankRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('accountName', '%' + searchTerm + '%')
                    ilike('balance', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = bankRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", bankRegisterArrayList)
        return jsonObject
    }

    AccountRegister save(JSONObject jsonObject) {
        AccountRegister accountRegister = new AccountRegister()
        accountRegister.generalId = Long.parseLong(jsonObject.get("generalId").toString())
        accountRegister.accountName = jsonObject.get("accountName").toString()
        accountRegister.accountStatus = Long.parseLong(jsonObject.get("accountStatus").toString())
        accountRegister.subAccountType = Long.parseLong(jsonObject.get("subAccountType").toString())
        accountRegister.accountMode = Long.parseLong(jsonObject.get("accountMode").toString())
        accountRegister.responsibleUserId = Long.parseLong(jsonObject.get("responsibleUserId").toString())
        accountRegister.yearlyBudget = jsonObject.get("yearlyBudget").toString()
        accountRegister.balance = Long.parseLong(jsonObject.get("balance").toString())
        accountRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        accountRegister.entityType =  EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        accountRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        accountRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        accountRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        accountRegister.save(flush: true)
        if (!accountRegister.hasErrors())
            return accountRegister
        else
            throw new BadRequestException()
    }

    AccountRegister update(JSONObject jsonObject, String id) {

        AccountRegister accountRegister = AccountRegister.findById(Long.parseLong(id))
        if (accountRegister) {
            accountRegister.isUpdatable = true
            accountRegister.generalId = Long.parseLong(jsonObject.get("generalId").toString())
            accountRegister.accountName = jsonObject.get("accountName").toString()
            accountRegister.accountStatus = Long.parseLong(jsonObject.get("accountStatus").toString())
            accountRegister.subAccountType = Long.parseLong(jsonObject.get("subAccountType").toString())
            accountRegister.accountMode = Long.parseLong(jsonObject.get("accountMode").toString())
            accountRegister.responsibleUserId = Long.parseLong(jsonObject.get("accountMode").toString())
            accountRegister.yearlyBudget = jsonObject.get("yearlyBudget").toString()
            accountRegister.balance = Long.parseLong(jsonObject.get("balance").toString())
            accountRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            accountRegister.entityType =  EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            accountRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            accountRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            accountRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            accountRegister.save(flush: true)
            if (!accountRegister.hasErrors())
                return accountRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            AccountRegister bankRegister = AccountRegister.findById(Long.parseLong(id))
            if (bankRegister) {
                bankRegister.isUpdatable = true
                bankRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    ArrayList<AccountRegister> getAllByEntity(String id) {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id))
        return AccountRegister.findAllByEntity(entityRegister)
    }
}
