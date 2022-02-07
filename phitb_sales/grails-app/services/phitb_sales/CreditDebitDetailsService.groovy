package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CreditDebitDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return CreditDebitDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleProductDetails.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }


    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return CreditDebitDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
                Date today = new Date()
                Calendar cal = new GregorianCalendar()
                cal.setTime(today)
                cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
                Date dateCreated = cal.getTime()
                return CreditDebitDetails.createCriteria().list {
                    gt("dateCreated",dateCreated)
                }
        }
    }

    CreditDebitDetails get(String id)
    {
        return CreditDebitDetails.findById(Long.parseLong(id))
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
                orderColumn = "entityName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def creditDebitDetailsCriteria = CreditDebitDetails.createCriteria()
        def creditDebitDetailsArrayList = creditDebitDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('entityName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = creditDebitDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", creditDebitDetailsArrayList)
        return jsonObject
    }

    CreditDebitDetails save(JSONObject jsonObject)
    {
        CreditDebitDetails creditDebitDetails = new CreditDebitDetails()
        creditDebitDetails.cId =  Long.parseLong(jsonObject.get("cId").toString())
        creditDebitDetails.debitId = Long.parseLong(jsonObject.get("debitId").toString())
        creditDebitDetails.debitSeries = Long.parseLong(jsonObject.get("debitSeries").toString())
        creditDebitDetails.debitFinancialYear = jsonObject.get("debitFinancialYear").toString()
        creditDebitDetails.crdbAdjusted = Long.parseLong(jsonObject.get("crdbAdjusted").toString())
        creditDebitDetails.creditId = Long.parseLong(jsonObject.get("creditId").toString())
        creditDebitDetails.creditSeries = Long.parseLong(jsonObject.get("creditSeries").toString())
        creditDebitDetails.creditFinancialYear = jsonObject.get("creditFinancialYear").toString()
        creditDebitDetails.financialYear = jsonObject.get("financialYear").toString()
        creditDebitDetails.status = Long.parseLong(jsonObject.get("status").toString())
        creditDebitDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        creditDebitDetails.financialYear = jsonObject.get("financialYear").toString()
        creditDebitDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        creditDebitDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        creditDebitDetails.save(flush: true)
        if (!creditDebitDetails.hasErrors())
        {
            return creditDebitDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    CreditDebitDetails update(JSONObject jsonObject, String id)
    {
        CreditDebitDetails creditDebitDetails = CreditDebitDetails.findById(Long.parseLong(id))
        if (creditDebitDetails)
        {
            creditDebitDetails.isUpdatable = true
            creditDebitDetails.cId =  Long.parseLong(jsonObject.get("cId").toString())
            creditDebitDetails.debitId = Long.parseLong(jsonObject.get("debitId").toString())
            creditDebitDetails.debitSeries = Long.parseLong(jsonObject.get("debitSeries").toString())
            creditDebitDetails.debitFinancialYear = jsonObject.get("debitFinancialYear").toString()
            creditDebitDetails.crdbAdjusted = Long.parseLong(jsonObject.get("crdbAdjusted").toString())
            creditDebitDetails.creditId = Long.parseLong(jsonObject.get("creditId").toString())
            creditDebitDetails.creditSeries = Long.parseLong(jsonObject.get("creditSeries").toString())
            creditDebitDetails.creditFinancialYear = jsonObject.get("creditFinancialYear").toString()
            creditDebitDetails.financialYear = jsonObject.get("financialYear").toString()
            creditDebitDetails.status = Long.parseLong(jsonObject.get("status").toString())
            creditDebitDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            creditDebitDetails.financialYear = jsonObject.get("financialYear").toString()
            creditDebitDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            creditDebitDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            creditDebitDetails.save(flush: true)
            if (!creditDebitDetails.hasErrors())
            {
                return creditDebitDetails
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
            CreditDebitDetails creditDebitDetails = CreditDebitDetails.findById(Long.parseLong(id))
            if (creditDebitDetails)
            {
                creditDebitDetails.isUpdatable = true
                creditDebitDetails.delete()
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
