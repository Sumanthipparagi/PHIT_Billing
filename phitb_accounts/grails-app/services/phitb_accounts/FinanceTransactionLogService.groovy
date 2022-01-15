package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class FinanceTransactionLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return FinanceTransactionLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FinanceTransactionLog.findAllByAccountIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    FinanceTransactionLog get(String id) {
        return FinanceTransactionLog.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "finId"
                break;
            case '1':
                orderColumn = "accountId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def financeTransactionLogCriteria = FinanceTransactionLog.createCriteria()
        def financeTransactionLogArrayList = financeTransactionLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('accountId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = financeTransactionLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", financeTransactionLogArrayList)
        return jsonObject
    }

    FinanceTransactionLog save(JSONObject jsonObject) {
        FinanceTransactionLog financeTransactionLog = new FinanceTransactionLog()
        financeTransactionLog.finId = Long.parseLong(jsonObject.get("finId").toString())
        financeTransactionLog.transactionType = jsonObject.get("transactionType").toString()
        financeTransactionLog.accountModeId = jsonObject.get("accountModeId").toString()
        financeTransactionLog.accountId = jsonObject.get("accountId").toString()
        financeTransactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        financeTransactionLog.billId = jsonObject.get("billId").toString()
        financeTransactionLog.serBillId = jsonObject.get("serBillId").toString()
        financeTransactionLog.description = jsonObject.get("description").toString()
        financeTransactionLog.context = jsonObject.get("context").toString()
        financeTransactionLog.transferredAmount = Double.parseDouble(jsonObject.get("transferredAmount").toString())
        financeTransactionLog.balance = Double.parseDouble(jsonObject.get("balance").toString())
        financeTransactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        financeTransactionLog.financialYear = jsonObject.get("financialYear").toString()
        financeTransactionLog.status = Long.parseLong(jsonObject.get("status").toString())
        financeTransactionLog.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        financeTransactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        financeTransactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        financeTransactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        financeTransactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

        financeTransactionLog.save(flush: true)
        if (!financeTransactionLog.hasErrors())
            return financeTransactionLog
        else
            throw new BadRequestException()

    }

    FinanceTransactionLog update(JSONObject jsonObject, String id) {

        FinanceTransactionLog financeTransactionLog = FinanceTransactionLog.findById(Long.parseLong(id))
        if (financeTransactionLog) {
            financeTransactionLog.isUpdatable = true
            financeTransactionLog.finId = Long.parseLong(jsonObject.get("finId").toString())
            financeTransactionLog.transactionType = jsonObject.get("transactionType").toString()
            financeTransactionLog.accountModeId = jsonObject.get("accountModeId").toString()
            financeTransactionLog.accountId = jsonObject.get("accountId").toString()
            financeTransactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            financeTransactionLog.billId = jsonObject.get("billId").toString()
            financeTransactionLog.serBillId = jsonObject.get("serBillId").toString()
            financeTransactionLog.description = jsonObject.get("description").toString()
            financeTransactionLog.context = jsonObject.get("context").toString()
            financeTransactionLog.transferredAmount = Double.parseDouble(jsonObject.get("transferredAmount").toString())
            financeTransactionLog.balance = Double.parseDouble(jsonObject.get("balance").toString())
            financeTransactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            financeTransactionLog.financialYear = jsonObject.get("financialYear").toString()
            financeTransactionLog.status = Long.parseLong(jsonObject.get("status").toString())
            financeTransactionLog.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            financeTransactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            financeTransactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            financeTransactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            financeTransactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            financeTransactionLog.save(flush: true)
            if (!financeTransactionLog.hasErrors())
                return financeTransactionLog
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            FinanceTransactionLog financeTransactionLog = FinanceTransactionLog.findById(Long.parseLong(id))
            if (financeTransactionLog) {
                financeTransactionLog.isUpdatable = true
                financeTransactionLog.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
