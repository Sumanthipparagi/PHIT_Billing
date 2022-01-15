package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseTransactionLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseTransactionLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseTransactionLog.findAllByTransactionTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    PurchaseTransactionLog get(String id) {
        return PurchaseTransactionLog.findById(Long.parseLong(id))
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
                orderColumn = "finId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def purchaseTransactionLogCriteria = PurchaseTransactionLog.createCriteria()
        def purchaseTransactionLogArrayList = purchaseTransactionLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionType', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseTransactionLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseTransactionLogArrayList)
        return jsonObject
    }

    PurchaseTransactionLog save(JSONObject jsonObject) {
        PurchaseTransactionLog purchaseTransactionLog = new PurchaseTransactionLog()

        purchaseTransactionLog.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseTransactionLog.transactionType = jsonObject.get("transactionType").toString()
        purchaseTransactionLog.accountModeId = jsonObject.get("accountModeId").toString()
        purchaseTransactionLog.accountId = jsonObject.get("accountId").toString()
        purchaseTransactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseTransactionLog.billId = jsonObject.get("billId").toString()
        purchaseTransactionLog.serBillId = jsonObject.get("serBillId").toString()
        purchaseTransactionLog.description = jsonObject.get("description").toString()
        purchaseTransactionLog.context = jsonObject.get("context").toString()
        purchaseTransactionLog.transferredAmount = Double.parseDouble(jsonObject.get("transferredAmount").toString())
        purchaseTransactionLog.balance = Double.parseDouble(jsonObject.get("balance").toString())
        purchaseTransactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        purchaseTransactionLog.status = Long.parseLong(jsonObject.get("status").toString())
        purchaseTransactionLog.financialYear = jsonObject.get("financialYear").toString()
        purchaseTransactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseTransactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseTransactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        purchaseTransactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

        purchaseTransactionLog.save(flush: true)
        if (!purchaseTransactionLog.hasErrors())
            return purchaseTransactionLog
        else
            throw new BadRequestException()

    }

    PurchaseTransactionLog update(JSONObject jsonObject, String id) {

        PurchaseTransactionLog purchaseTransactionLog = PurchaseTransactionLog.findById(Long.parseLong(id))
        if (purchaseTransactionLog) {
            purchaseTransactionLog.isUpdatable = true
            purchaseTransactionLog.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseTransactionLog.transactionType = jsonObject.get("transactionType").toString()
            purchaseTransactionLog.accountModeId = jsonObject.get("accountModeId").toString()
            purchaseTransactionLog.accountId = jsonObject.get("accountId").toString()
            purchaseTransactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseTransactionLog.billId = jsonObject.get("billId").toString()
            purchaseTransactionLog.serBillId = jsonObject.get("serBillId").toString()
            purchaseTransactionLog.description = jsonObject.get("description").toString()
            purchaseTransactionLog.context = jsonObject.get("context").toString()
            purchaseTransactionLog.transferredAmount = Double.parseDouble(jsonObject.get("transferredAmount").toString())
            purchaseTransactionLog.balance = Double.parseDouble(jsonObject.get("balance").toString())
            purchaseTransactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            purchaseTransactionLog.status = Long.parseLong(jsonObject.get("status").toString())
            purchaseTransactionLog.financialYear = jsonObject.get("financialYear").toString()
            purchaseTransactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseTransactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseTransactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            purchaseTransactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            purchaseTransactionLog.save(flush: true)
            if (!purchaseTransactionLog.hasErrors())
                return purchaseTransactionLog
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseTransactionLog purchaseTransactionLog = PurchaseTransactionLog.findById(Long.parseLong(id))
            if (purchaseTransactionLog) {
                purchaseTransactionLog.isUpdatable = true
                purchaseTransactionLog.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
