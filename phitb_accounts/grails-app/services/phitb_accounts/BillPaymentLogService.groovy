package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class BillPaymentLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return BillPaymentLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return BillPaymentLog.findAllByBillTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    BillPaymentLog get(String id) {
        return BillPaymentLog.findById(Long.parseLong(id))
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
                orderColumn = "billId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def billPaymentLogCriteria = BillPaymentLog.createCriteria()
        def billPaymentLogArrayList = billPaymentLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('billType', '%' + searchTerm + '%')
                    ilike('amountPaid', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = billPaymentLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", billPaymentLogArrayList)
        return jsonObject
    }

    BillPaymentLog save(JSONObject jsonObject) {
        BillPaymentLog billPaymentLog = new BillPaymentLog()
        billPaymentLog.billId = Long.parseLong(jsonObject.get("billId").toString())
        billPaymentLog.billType = jsonObject.get("billType").toString()
        billPaymentLog.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
        billPaymentLog.paymentRecord = "0"
        billPaymentLog.approvedBy = Long.parseLong("1")
        billPaymentLog.currentFinancialYear = jsonObject.get("currentFinancialYear").toString()
        billPaymentLog.paymentId = Long.parseLong(jsonObject.get("paymentId").toString())
        billPaymentLog.transId = jsonObject.get("transId").toString()
        billPaymentLog.financialYear = jsonObject.get("financialYear").toString()
        billPaymentLog.status = Long.parseLong("1")
        billPaymentLog.syncStatus = Long.parseLong("1")
        billPaymentLog.entityTypeId = Long.parseLong("1")
        billPaymentLog.entityId = Long.parseLong("1")
        billPaymentLog.modifiedUser = Long.parseLong("1")
        billPaymentLog.createdUser = Long.parseLong("1")

        billPaymentLog.save(flush: true)
        if (!billPaymentLog.hasErrors())
            return billPaymentLog
        else
            throw new BadRequestException()

    }

    BillPaymentLog update(JSONObject jsonObject, String id) {

        BillPaymentLog billPaymentLog = BillPaymentLog.findById(Long.parseLong(id))
        if (billPaymentLog) {
            billPaymentLog.isUpdatable = true
            billPaymentLog.billId = Long.parseLong(jsonObject.get("billId").toString())
            billPaymentLog.paymentId = Long.parseLong(jsonObject.get("paymentId").toString())
            billPaymentLog.billType = jsonObject.get("billType").toString()
            billPaymentLog.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
            billPaymentLog.paymentRecord = jsonObject.get("paymentRecord").toString()
            billPaymentLog.approvedBy = Long.parseLong(jsonObject.get("approvedBy").toString())
            billPaymentLog.currentFinancialYear = jsonObject.get("currentFinancialYear").toString()
            billPaymentLog.financialYear = jsonObject.get("financialYear").toString()
            billPaymentLog.status = Long.parseLong(jsonObject.get("status").toString())
            billPaymentLog.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            billPaymentLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            billPaymentLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            billPaymentLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            billPaymentLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            billPaymentLog.save(flush: true)
            if (!billPaymentLog.hasErrors())
                return billPaymentLog
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            BillPaymentLog billPaymentLog = BillPaymentLog.findById(Long.parseLong(id))
            if (billPaymentLog) {
                billPaymentLog.isUpdatable = true
                billPaymentLog.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
