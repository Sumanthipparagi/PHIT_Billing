package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CreditJvTransactionDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CreditJvTransactionDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CreditJvTransactionDetail.findAllByTransactionIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return CreditJvTransactionDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CreditJvTransactionDetail.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    CreditJvTransactionDetail get(String id) {
        return CreditJvTransactionDetail.findById(Long.parseLong(id))
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

        def creditJvTransactionDetailCriteria = CreditJvTransactionDetail.createCriteria()
        def creditJvTransactionDetailArrayList = creditJvTransactionDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = creditJvTransactionDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", creditJvTransactionDetailArrayList)
        return jsonObject
    }

    CreditJvTransactionDetail save(JSONObject jsonObject) {
        CreditJvTransactionDetail creditJvTransactionDetail = new CreditJvTransactionDetail()
        creditJvTransactionDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        creditJvTransactionDetail.transactionId = jsonObject.get("transactionId").toString()
        creditJvTransactionDetail.expenseDate = sdf.parse(jsonObject.get("expenseDate").toString())
        creditJvTransactionDetail.expenseType = jsonObject.get("expenseType").toString()
        creditJvTransactionDetail.deductFrom = jsonObject.get("deductFrom").toString()
        creditJvTransactionDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
        creditJvTransactionDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
        creditJvTransactionDetail.dbAdjAmount = Double.parseDouble(jsonObject.get("dbAdjAmount").toString())
        creditJvTransactionDetail.debitIds = jsonObject.get("debitIds").toString()
        creditJvTransactionDetail.note = jsonObject.get("note").toString()
        creditJvTransactionDetail.paid = jsonObject.get("paid").toString()
        creditJvTransactionDetail.financialYear = jsonObject.get("financialYear").toString()
        creditJvTransactionDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        creditJvTransactionDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        creditJvTransactionDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        creditJvTransactionDetail.save(flush: true)
        if (!creditJvTransactionDetail.hasErrors())
            return creditJvTransactionDetail
        else
            throw new BadRequestException()

    }

    CreditJvTransactionDetail update(JSONObject jsonObject, String id) {

        CreditJvTransactionDetail creditJvTransactionDetail = CreditJvTransactionDetail.findById(Long.parseLong(id))
        if (creditJvTransactionDetail) {
            creditJvTransactionDetail.isUpdatable = true
            creditJvTransactionDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            creditJvTransactionDetail.transactionId = jsonObject.get("transactionId").toString()
            creditJvTransactionDetail.expenseDate = sdf.parse(jsonObject.get("expenseDate").toString())
            creditJvTransactionDetail.expenseType = jsonObject.get("expenseType").toString()
            creditJvTransactionDetail.deductFrom = jsonObject.get("deductFrom").toString()
            creditJvTransactionDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
            creditJvTransactionDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
            creditJvTransactionDetail.dbAdjAmount = Double.parseDouble(jsonObject.get("dbAdjAmount").toString())
            creditJvTransactionDetail.debitIds = jsonObject.get("debitIds").toString()
            creditJvTransactionDetail.note = jsonObject.get("note").toString()
            creditJvTransactionDetail.paid = jsonObject.get("paid").toString()
            creditJvTransactionDetail.financialYear = jsonObject.get("financialYear").toString()
            creditJvTransactionDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            creditJvTransactionDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            creditJvTransactionDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            creditJvTransactionDetail.save(flush: true)
            if (!creditJvTransactionDetail.hasErrors())
                return creditJvTransactionDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            CreditJvTransactionDetail creditJvTransactionDetail = CreditJvTransactionDetail.findById(Long.parseLong(id))
            if (creditJvTransactionDetail) {
                creditJvTransactionDetail.isUpdatable = true
                creditJvTransactionDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
