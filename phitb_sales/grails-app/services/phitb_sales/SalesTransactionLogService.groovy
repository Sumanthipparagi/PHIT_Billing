package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SalesTransactionLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SalesTransactionLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SalesTransactionLog.findAllByAccountId("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SalesTransactionLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SalesTransactionLog.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }

    SalesTransactionLog get(String id)
    {
        return SalesTransactionLog.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "refNumber"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def salesTransactionLogCriteria = SalesTransactionLog.createCriteria()
        def salesTransactionLogArrayList = salesTransactionLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('accountId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = salesTransactionLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", salesTransactionLogArrayList)
        return jsonObject
    }

    SalesTransactionLog save(JSONObject jsonObject)
    {
        SalesTransactionLog salesTrasactionLog = new SalesTransactionLog()
        salesTrasactionLog.finId =  Long.parseLong(jsonObject.get("finId").toString())
        salesTrasactionLog.transactionType =  jsonObject.get("transactionType").toString()
        salesTrasactionLog.accountModeId =  jsonObject.get("accountModeId").toString()
        salesTrasactionLog.accountId =  jsonObject.get("accountId").toString()
        salesTrasactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        salesTrasactionLog.billId = jsonObject.get("seriesId").toString()
        salesTrasactionLog.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        salesTrasactionLog.description = jsonObject.get("description").toString()
        salesTrasactionLog.context = jsonObject.get("context").toString()
        salesTrasactionLog.transferedAmount = Double.parseDouble(jsonObject.get("context").toString())
        salesTrasactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        salesTrasactionLog.status = jsonObject.get("status").toString()
        salesTrasactionLog.financialYear = Long.parseLong(jsonObject.get("financialYear").toString())
        salesTrasactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        salesTrasactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        salesTrasactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        salesTrasactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        salesTrasactionLog.save(flush: true)
        if (!salesTrasactionLog.hasErrors())
        {
            return salesTrasactionLog
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SalesTransactionLog update(JSONObject jsonObject, String id)
    {
        SalesTransactionLog salesTrasactionLog = SalesTransactionLog.findById(Long.parseLong(id))
        if (salesTrasactionLog)
        {
            salesTrasactionLog.isUpdatable = true
            salesTrasactionLog.finId =  Long.parseLong(jsonObject.get("finId").toString())
            salesTrasactionLog.transactionType =  jsonObject.get("transactionType").toString()
            salesTrasactionLog.accountModeId =  jsonObject.get("accountModeId").toString()
            salesTrasactionLog.accountId =  jsonObject.get("accountId").toString()
            salesTrasactionLog.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            salesTrasactionLog.billId = jsonObject.get("billId").toString()
            salesTrasactionLog.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            salesTrasactionLog.description = jsonObject.get("description").toString()
            salesTrasactionLog.context = jsonObject.get("context").toString()
            salesTrasactionLog.transferedAmount = Double.parseDouble(jsonObject.get("transferedAmount").toString())
            salesTrasactionLog.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            salesTrasactionLog.status = jsonObject.get("status").toString()
            salesTrasactionLog.financialYear = Long.parseLong(jsonObject.get("financialYear").toString())
            salesTrasactionLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            salesTrasactionLog.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            salesTrasactionLog.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            salesTrasactionLog.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            salesTrasactionLog.save(flush: true)
            if (!salesTrasactionLog.hasErrors())
            {
                return salesTrasactionLog
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            SalesTransactionLog salesTrasactionLog = SalesTransactionLog.findById(Long.parseLong(id))
            if (salesTrasactionLog)
            {
                salesTrasactionLog.isUpdatable = true
                salesTrasactionLog.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }
}
