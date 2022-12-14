package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class CreditDebitSettlementService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return CreditDebitSettlement.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return CreditDebitSettlement.findAllByDateIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return CreditDebitSettlement.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return CreditDebitSettlement.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }
    CreditDebitSettlement get(String id)
    {
        return CreditDebitSettlement.findById(Long.parseLong(id))
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
                orderColumn = "date"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def creditDebitSettlementCriteria = CreditDebitSettlement.createCriteria()
        def creditDebitSettlementArrayList = creditDebitSettlementCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('date', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = creditDebitSettlementArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", creditDebitSettlementArrayList)
        return jsonObject
    }

    CreditDebitSettlement save(JSONObject jsonObject)
    {
        CreditDebitSettlement creditDebitSettlement = new CreditDebitSettlement()
        creditDebitSettlement.finId =  Long.parseLong(jsonObject.get("finId").toString())
        creditDebitSettlement.userId = Long.parseLong(jsonObject.get("userId").toString())
        creditDebitSettlement.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        creditDebitSettlement.status = jsonObject.get("status").toString()
        creditDebitSettlement.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        creditDebitSettlement.remarks = jsonObject.get("remarks").toString()
        creditDebitSettlement.financialYear = jsonObject.get("financialYear").toString()
        creditDebitSettlement.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        creditDebitSettlement.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        creditDebitSettlement.createdUser = Long.parseLong(jsonObject.get("userId").toString())
        creditDebitSettlement.modifiedUser = Long.parseLong(jsonObject.get("userId").toString())
        creditDebitSettlement.crdbNumber = jsonObject.get("crdbNumber").toString()
        creditDebitSettlement.save(flush: true)
        if (!creditDebitSettlement.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(creditDebitSettlement.dateCreated)
            String month = cal.get(Calendar.MONTH) + 1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00")
            month = mFormat.format(Double.valueOf(month));
            String crDbNumber = null;
            CreditDebitSettlement creditDebitSettlement1
            crDbNumber = creditDebitSettlement.entityId + "S" + month + year + creditDebitSettlement.id
            println("Invoice Number generated: " + crDbNumber)
            creditDebitSettlement.crdbNumber = crDbNumber
            creditDebitSettlement.isUpdatable = true
            creditDebitSettlement.save(flush: true)
            return creditDebitSettlement
        }
        else
        {
            throw new BadRequestException()
        }
    }

    CreditDebitSettlement update(JSONObject jsonObject, String id)
    {
        CreditDebitSettlement creditDebitSettlement = CreditDebitSettlement.findById(Long.parseLong(id))
        if (creditDebitSettlement)
        {
            creditDebitSettlement.isUpdatable = true
            creditDebitSettlement.finId =  Long.parseLong(jsonObject.get("finId").toString())
            creditDebitSettlement.date = jsonObject.get("date").toString()
            creditDebitSettlement.userId = Long.parseLong(jsonObject.get("userId").toString())
            creditDebitSettlement.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            creditDebitSettlement.status = jsonObject.get("status").toString()
            creditDebitSettlement.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            creditDebitSettlement.remarks = jsonObject.get("remarks").toString()
            creditDebitSettlement.financialYear = jsonObject.get("financialYear").toString()
            creditDebitSettlement.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            creditDebitSettlement.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            creditDebitSettlement.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            creditDebitSettlement.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            creditDebitSettlement.save(flush: true)
            if (!creditDebitSettlement.hasErrors())
            {
                return creditDebitSettlement
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
            CreditDebitSettlement CreditDebitSettlement = CreditDebitSettlement.findById(Long.parseLong(id))
            if (CreditDebitSettlement)
            {
                CreditDebitSettlement.isUpdatable = true
                CreditDebitSettlement.delete()
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
