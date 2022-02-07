package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DebitJvTransactionDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DebitJvTransactionDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJvTransactionDetail.findAllByTransactionIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return DebitJvTransactionDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJvTransactionDetail.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    DebitJvTransactionDetail get(String id) {
        return DebitJvTransactionDetail.findById(Long.parseLong(id))
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
                orderColumn = "transactionId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def debitJvTransactionDetailCriteria = DebitJvTransactionDetail.createCriteria()
        def debitJvTransactionDetailArrayList = debitJvTransactionDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = debitJvTransactionDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", debitJvTransactionDetailArrayList)
        return jsonObject
    }

    DebitJvTransactionDetail save(JSONObject jsonObject) {
        DebitJvTransactionDetail debitJvTransactionDetail = new DebitJvTransactionDetail()
        debitJvTransactionDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        debitJvTransactionDetail.transactionId = jsonObject.get("transactionId").toString()
        debitJvTransactionDetail.expenseDate = sdf.parse(jsonObject.get("expenseDate").toString())
        debitJvTransactionDetail.expenseType = jsonObject.get("expenseType").toString()
        debitJvTransactionDetail.deductFrom = jsonObject.get("deductFrom").toString()
        debitJvTransactionDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
        debitJvTransactionDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
        debitJvTransactionDetail.crAdjAmount = Double.parseDouble(jsonObject.get("crAdjAmount").toString())
        debitJvTransactionDetail.creditIds = jsonObject.get("creditIds").toString()
        debitJvTransactionDetail.note = jsonObject.get("note").toString()
        debitJvTransactionDetail.paid = jsonObject.get("paid").toString()
        debitJvTransactionDetail.financialYear = jsonObject.get("financialYear").toString()
        debitJvTransactionDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        debitJvTransactionDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        debitJvTransactionDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        debitJvTransactionDetail.save(flush: true)
        if (!debitJvTransactionDetail.hasErrors())
            return debitJvTransactionDetail
        else
            throw new BadRequestException()

    }

    DebitJvTransactionDetail update(JSONObject jsonObject, String id) {

        DebitJvTransactionDetail debitJvTransactionDetail = DebitJvTransactionDetail.findById(Long.parseLong(id))
        if (debitJvTransactionDetail) {
            debitJvTransactionDetail.isUpdatable = true
            debitJvTransactionDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            debitJvTransactionDetail.transactionId = jsonObject.get("transactionId").toString()
            debitJvTransactionDetail.expenseDate = sdf.parse(jsonObject.get("expenseDate").toString())
            debitJvTransactionDetail.expenseType = jsonObject.get("expenseType").toString()
            debitJvTransactionDetail.deductFrom = jsonObject.get("deductFrom").toString()
            debitJvTransactionDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
            debitJvTransactionDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
            debitJvTransactionDetail.crAdjAmount = Double.parseDouble(jsonObject.get("crAdjAmount").toString())
            debitJvTransactionDetail.creditIds = jsonObject.get("creditIds").toString()
            debitJvTransactionDetail.note = jsonObject.get("note").toString()
            debitJvTransactionDetail.paid = jsonObject.get("paid").toString()
            debitJvTransactionDetail.financialYear = jsonObject.get("financialYear").toString()
            debitJvTransactionDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            debitJvTransactionDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            debitJvTransactionDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            debitJvTransactionDetail.save(flush: true)
            if (!debitJvTransactionDetail.hasErrors())
                return debitJvTransactionDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DebitJvTransactionDetail debitJvTransactionDetail = DebitJvTransactionDetail.findById(Long.parseLong(id))
            if (debitJvTransactionDetail) {
                debitJvTransactionDetail.isUpdatable = true
                debitJvTransactionDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
