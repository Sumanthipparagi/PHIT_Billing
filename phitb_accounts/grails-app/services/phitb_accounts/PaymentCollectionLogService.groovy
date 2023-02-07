package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException

@Transactional
class PaymentCollectionLogService {

    PaymentCollectionLog save(JSONObject jsonObject) {
        PaymentCollectionLog paymentCollection = new PaymentCollectionLog()
        paymentCollection.collectedAmount = Double.parseDouble(jsonObject.get("collectedAmount").toString())
        paymentCollection.balance = Double.parseDouble(jsonObject.get("balance").toString())
        paymentCollection.invoiceAmount = Double.parseDouble(jsonObject.get("invoiceAmount").toString())
        paymentCollection.documentNumber = jsonObject.get("documentNumber").toString()
        paymentCollection.status = jsonObject.get("status").toString()
        paymentCollection.receiptId = Long.parseLong(jsonObject.get("receiptId").toString())
        paymentCollection.instrumentId = jsonObject.get("instrumentId").toString()
        paymentCollection.userId = Long.parseLong(jsonObject.get("userId").toString())
        paymentCollection.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        paymentCollection.entityTypeId = Long.parseLong(jsonObject.get("entityId").toString())
        paymentCollection.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        paymentCollection.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        paymentCollection.save(flush: true)
        if (!paymentCollection.hasErrors())
            return paymentCollection
        else
            throw new BadRequestException()
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        String entityId = paramsJsonObject.get("entityId")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def paymentCollectionLog = PaymentCollectionLog.createCriteria()
        def paymentCollectionArrayList = paymentCollectionLog.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('documentNumber', '%' + searchTerm + '%')
                }
            }
            eq('entityId', Long.parseLong(entityId))
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = paymentCollectionArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", paymentCollectionArrayList)
        return jsonObject
    }


    def changePaymentCollectionStatus(long id, String status){
        PaymentCollectionLog paymentCollectionLog = PaymentCollectionLog.findById(id);
        if(paymentCollectionLog){
            if(status == 'Approve'){
                paymentCollectionLog.setStatus('APPROVED')
            }else{
                paymentCollectionLog.setStatus('CANCELLED')
            }
            paymentCollectionLog.save(flush:true)
            if (!paymentCollectionLog.hasErrors())
                return paymentCollectionLog
            else
                throw new BadRequestException()
        }
    }

}
