package phitb_accounts

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException

import java.text.SimpleDateFormat


@Transactional
class PaymentCollectionLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    PaymentCollectionLog save(JSONObject jsonObject) {
        PaymentCollectionLog paymentCollection = new PaymentCollectionLog()
        paymentCollection.collectedAmount = Double.parseDouble(jsonObject.get("collectedAmount").toString())
        paymentCollection.balance = Double.parseDouble(jsonObject.get("balance").toString())
        paymentCollection.invoiceAmount = Double.parseDouble(jsonObject.get("invoiceAmount").toString())
        paymentCollection.documentNumber = jsonObject.get("documentNumber").toString()
        paymentCollection.status = jsonObject.get("status").toString()
        paymentCollection.receiptId = Long.parseLong(jsonObject.get("receiptId").toString())
        paymentCollection.instrumentId = jsonObject.get("instrumentId").toString()
        paymentCollection.currentLocation = jsonObject.get("currentLocation").toString()
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
        String status = paramsJsonObject.get("status")
        String fromDate = paramsJsonObject.get("fromDate").toString()
        String toDate = paramsJsonObject.get("toDate").toString()


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
            if (fromDate != null && fromDate != '' && toDate != null && toDate != '')
            {
                between('dateCreated', sdf.parse(fromDate), sdf.parse(toDate))
            }
            if(status != '' && status!="ALL"){
                eq('status',status)
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

    def approveAllPayemtCollection(JSONArray jsonArray){
        JSONArray paymentArray = new JSONArray()
       for(JSONObject jsonObject1 : jsonArray){
           if(jsonObject1.Status!=''){
               PaymentCollectionLog paymentCollectionLog = PaymentCollectionLog.findById(Long.parseLong(jsonObject1.pcId.toString()))
               if(jsonObject1.Status == 'Approve'){
                   paymentCollectionLog.status = "APPROVED"
                   paymentCollectionLog.approvedDate = new Date()
                   paymentCollectionLog.reason = jsonObject1.Reason
               }else if (jsonObject1.Status == 'Return'){
                   paymentCollectionLog.status = 'RETURNED'
                   paymentCollectionLog.reason = jsonObject1.Reason
               }else if(jsonObject1.Status == 'Cancel'){
                   paymentCollectionLog.status = 'CANCELLED'
                   paymentCollectionLog.reason = jsonObject1.Reason
               }
               paymentCollectionLog.save(flush:true)
               paymentArray.add(paymentCollectionLog)
           }
       }
        return paymentArray
    }

    def updateBulkPayemtCollection(JSONObject jsonObject){
        JSONArray paymentArray = new JSONArray()
        for(JSONObject jsonObject1 : jsonObject.get('pcData')){
            if(jsonObject1.check!=""){
                if(jsonObject1.Status!=''){
                    PaymentCollectionLog paymentCollectionLog = PaymentCollectionLog.findById(Long.parseLong(jsonObject1.pcId.toString()))
                    if(jsonObject.status == 'APPROVED')
                    {
                        paymentCollectionLog.status = "APPROVED"
                        paymentCollectionLog.approvedDate = new Date()
                        paymentCollectionLog.reason = jsonObject1.Reason
                    }else if (jsonObject.status == 'RETURNED'){
                        paymentCollectionLog.status = 'RETURNED'
                        paymentCollectionLog.reason = jsonObject1.Reason
                    }else if(jsonObject.status == 'CANCELLED'){
                        paymentCollectionLog.status = 'CANCELLED'
                        paymentCollectionLog.reason = jsonObject1.Reason
                    }
                    paymentCollectionLog.save(flush:true)
                    paymentArray.add(paymentCollectionLog)
                }
            }
        }
        return paymentArray
    }

}
