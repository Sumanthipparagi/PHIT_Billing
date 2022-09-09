package phitb_accounts

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DebitJvService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJv.findAllByTransIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJv.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date transactionDate = cal.getTime()
            return DebitJv.createCriteria().list {
                gt("transactionDate",transactionDate)
            }
        }
    }
    DebitJv get(String id) {
        return DebitJv.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length, long entityId) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "transactionId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def debitJvCriteria = DebitJv.createCriteria()
        def debitJvArrayList = debitJvCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionId', '%' + searchTerm + '%')
                }
            }
            eq('approvedTime', null)
            eq('deleted', false)
            eq('entityId', entityId)
            order(orderColumn, orderDir)
        }

        def recordsTotal = debitJvArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", debitJvArrayList)
        return jsonObject
    }

    DebitJv save(JSONObject jsonObject) {
        DebitJv debitJv = new DebitJv()
        long entityId = Long.parseLong(jsonObject.get("entityId").toString())
        String financialYear = jsonObject.get("financialYear").toString()
        long finId = 1
        String transactionId = ""
        DebitJv previousDJv = DebitJv.findByEntityIdAndFinancialYear(entityId, financialYear, [sort: 'id', order: 'desc', max: 1])
        if(previousDJv)
        {
            finId = previousDJv.finId+1
        }
        transactionId = entityId + "/" + "DB" + "/" + finId

        debitJv.transactionId = transactionId
        debitJv.financialYear = financialYear
        debitJv.remarks = jsonObject.get("remarks").toString()
        debitJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
        debitJv.approverId = Long.parseLong(jsonObject.get("approverId").toString())
        debitJv.amount = Double.parseDouble(jsonObject.get("amount").toString())
        debitJv.fromAccount = Long.parseLong(jsonObject.get("fromAccount").toString())
        debitJv.creditAccount = Long.parseLong(jsonObject.get("creditAccount").toString())
        debitJv.reason = Long.parseLong(jsonObject.get("reason").toString())
        debitJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        debitJv.status = Long.parseLong(jsonObject.get("status").toString())
        debitJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        debitJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        debitJv.entityId = entityId
        debitJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        debitJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        debitJv.save(flush: true)
        if (!debitJv.hasErrors())
            return debitJv
        else
            throw new BadRequestException()

    }

    DebitJv update(JSONObject jsonObject, String id) {

        DebitJv debitJv = DebitJv.findById(Long.parseLong(id))
        if (debitJv) {
            debitJv.isUpdatable = true
            debitJv.transactionId = jsonObject.get("transactionId").toString() //TODO:
            debitJv.financialYear = jsonObject.get("financialYear").toString()
            debitJv.remarks = jsonObject.get("remarks").toString()
            debitJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
            debitJv.approverId = Long.parseLong(jsonObject.get("approverId").toString())
            debitJv.amount = Double.parseDouble(jsonObject.get("amount").toString())
            debitJv.fromAccount = Long.parseLong(jsonObject.get("fromAccount").toString())
            debitJv.creditAccount = Long.parseLong(jsonObject.get("creditAccount").toString())
            debitJv.reason = Long.parseLong(jsonObject.get("reason").toString())
            debitJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            debitJv.status = Long.parseLong(jsonObject.get("status").toString())
            debitJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            debitJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            debitJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            debitJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            debitJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            debitJv.save(flush: true)
            if (!debitJv.hasErrors())
                return debitJv
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DebitJv debitJv = DebitJv.findById(Long.parseLong(id))
            if (debitJv) {
                debitJv.isUpdatable = true
                debitJv.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def approveDebitJv(long id, long entityId, long approverId)
    {
        if(id && entityId)
        {
            DebitJv debitJv = DebitJv.findByIdAndEntityId(id,entityId)
            if(debitJv)
            {
                debitJv.isUpdatable = true
                debitJv.approvedTime = new Date()
                debitJv.approverId = approverId
                debitJv.save(flush:true)

                return debitJv
            }
        }
        return null
    }

    def getByDateRangeAndEntity(String dateRange, String entityId)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(dateRange.split("-")[0].trim().toString())
            Date toDate = sdf.parse(dateRange.split("-")[1].trim().toString())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            long eid = Long.parseLong(entityId)
            ArrayList<DebitJv> debitJvArrayList = DebitJv.findAllByTransactionDateBetweenAndEntityId(fromDate,toDate,eid)
            JSONArray jsonArray = new JSONArray((debitJvArrayList as JSON).toString())
            return jsonArray
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }
}
