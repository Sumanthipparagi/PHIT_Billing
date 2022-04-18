package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ReceiptDetailLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return BillPaymentLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return BillPaymentLog.findAllByBillTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    ReceiptDetailLog get(String id) {
        return ReceiptDetailLog.findById(Long.parseLong(id))
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

        def ReceiptDetailLogCriteria = ReceiptDetailLog.createCriteria()
        def ReceiptDetailLogArrayList = ReceiptDetailLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('billType', '%' + searchTerm + '%')
                    ilike('amountPaid', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = ReceiptDetailLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", ReceiptDetailLogArrayList)
        return jsonObject
    }

    ReceiptDetailLog save(JSONObject jsonObject) {
        ReceiptDetailLog receiptDetailLog = new ReceiptDetailLog()
        receiptDetailLog.billId = Long.parseLong(jsonObject.get("billId").toString())
        receiptDetailLog.billType = jsonObject.get("billType").toString()
        receiptDetailLog.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
        receiptDetailLog.paymentRecord = jsonObject.get("paymentRecord").toString()
        receiptDetailLog.approvedBy = Long.parseLong(jsonObject.get("approvedBy").toString())
        receiptDetailLog.currentFinancialYear = jsonObject.get("currentFinancialYear").toString()
        receiptDetailLog.financialYear = jsonObject.get("financialYear").toString()
        receiptDetailLog.status = Long.parseLong(jsonObject.get("status").toString())
        receiptDetailLog.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        receiptDetailLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        receiptDetailLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        receiptDetailLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        receiptDetailLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        receiptDetailLog.save(flush: true)
        if (!receiptDetailLog.hasErrors())
            return receiptDetailLog
        else
            throw new BadRequestException()

    }

    ReceiptDetailLog update(JSONObject jsonObject, String id) {
        ReceiptDetailLog receiptDetailLog = ReceiptDetailLog.findById(Long.parseLong(id))
        if (receiptDetailLog) {
            receiptDetailLog.isUpdatable = true
            receiptDetailLog.billId = Long.parseLong(jsonObject.get("billId").toString())
            receiptDetailLog.billType = jsonObject.get("billType").toString()
            receiptDetailLog.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
            receiptDetailLog.paymentRecord = jsonObject.get("paymentRecord").toString()
            receiptDetailLog.approvedBy = Long.parseLong(jsonObject.get("approvedBy").toString())
            receiptDetailLog.currentFinancialYear = jsonObject.get("currentFinancialYear").toString()
            receiptDetailLog.financialYear = jsonObject.get("financialYear").toString()
            receiptDetailLog.status = Long.parseLong(jsonObject.get("status").toString())
            receiptDetailLog.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            receiptDetailLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            receiptDetailLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            receiptDetailLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            receiptDetailLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            receiptDetailLog.save(flush: true)
            if (!receiptDetailLog.hasErrors())
                return receiptDetailLog
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ReceiptDetailLog receiptDetailLog = ReceiptDetailLog.findById(Long.parseLong(id))
            if (receiptDetailLog) {
                receiptDetailLog.isUpdatable = true
                receiptDetailLog.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


}
