package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class BankRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return BankRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return BankRegister.findAllByBankNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    BankRegister get(String id) {
        return BankRegister.findById(Long.parseLong(id))
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
                orderColumn = "cityId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def bankRegisterCriteria = BankRegister.createCriteria()
        def bankRegisterArrayList = bankRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('bankName', '%' + searchTerm + '%')
                    ilike('ifscCode', '%' + searchTerm + '%')
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

    BankRegister save(JSONObject jsonObject) {
        BankRegister bankRegister = new BankRegister()
        bankRegister.bankName = jsonObject.get("bankName").toString()
        bankRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
        bankRegister.ifscCode = jsonObject.get("ifscCode").toString()
        bankRegister.status = Long.parseLong(jsonObject.get("status").toString())
        bankRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        bankRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        bankRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        bankRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        bankRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

        bankRegister.save(flush: true)
        if (!bankRegister.hasErrors())
            return bankRegister
        else
            throw new BadRequestException()

    }

    BankRegister update(JSONObject jsonObject, String id) {

        BankRegister bankRegister = BankRegister.findById(Long.parseLong(id))
        if (bankRegister) {
            bankRegister.isUpdatable = true
            bankRegister.bankName = jsonObject.get("bankName").toString()
            bankRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
            bankRegister.ifscCode = jsonObject.get("ifscCode").toString()
            bankRegister.status = Long.parseLong(jsonObject.get("status").toString())
            bankRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            bankRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            bankRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            bankRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            bankRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            bankRegister.save(flush: true)
            if (!bankRegister.hasErrors())
                return bankRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            BankRegister bankRegister = BankRegister.findById(Long.parseLong(id))
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
}
