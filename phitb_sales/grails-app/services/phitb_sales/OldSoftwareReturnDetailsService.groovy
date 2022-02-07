package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class OldSoftwareReturnDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return OldSoftwareReturnDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return OldSoftwareReturnDetails.findAllByBillIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return OldSoftwareSaleDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return OldSoftwareSaleDetails.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }

    OldSoftwareReturnDetails get(String id)
    {
        return OldSoftwareReturnDetails.findById(Long.parseLong(id))
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
                orderColumn = "billId"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def oldSoftwareReturnDetailsCriteria = OldSoftwareReturnDetails.createCriteria()
        def oldSoftwareReturnDetailsArrayList = oldSoftwareReturnDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('billId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = oldSoftwareReturnDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", oldSoftwareReturnDetailsArrayList)
        return jsonObject
    }

    OldSoftwareReturnDetails save(JSONObject jsonObject)
    {
        OldSoftwareReturnDetails oldSoftwareReturnDetails = new OldSoftwareReturnDetails()
        oldSoftwareReturnDetails.billId =  jsonObject.get("billId").toString()
        oldSoftwareReturnDetails.series = Long.parseLong(jsonObject.get("series").toString())
        oldSoftwareReturnDetails.date = jsonObject.get("date").toString()
        oldSoftwareReturnDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
        oldSoftwareReturnDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        oldSoftwareReturnDetails.netAmount = Double.parseDouble(jsonObject.get("netAmount").toString())
        oldSoftwareReturnDetails.balance = Double.parseDouble(jsonObject.get("balance").toString())
        oldSoftwareReturnDetails.dbAdjamount = Double.parseDouble(jsonObject.get("dbAdjamount").toString())
        oldSoftwareReturnDetails.debitIds = Long.parseLong(jsonObject.get("debitIds").toString())
        oldSoftwareReturnDetails.adjustmentStatus = Long.parseLong(jsonObject.get("adjustmentStatus").toString())
        oldSoftwareReturnDetails.financialYear = jsonObject.get("financialYear").toString()
        oldSoftwareReturnDetails.status = jsonObject.get("status").toString()
        oldSoftwareReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        oldSoftwareReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        oldSoftwareReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        oldSoftwareReturnDetails.save(flush: true)
        if (!oldSoftwareReturnDetails.hasErrors())
        {
            return oldSoftwareReturnDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    OldSoftwareReturnDetails update(JSONObject jsonObject, String id)
    {
        OldSoftwareReturnDetails oldSoftwareReturnDetails = OldSoftwareReturnDetails.findById(Long.parseLong(id))
        if (oldSoftwareReturnDetails)
        {
            oldSoftwareReturnDetails.isUpdatable = true
            oldSoftwareReturnDetails.billId =  jsonObject.get("billId").toString()
            oldSoftwareReturnDetails.series = Long.parseLong(jsonObject.get("series").toString())
            oldSoftwareReturnDetails.date = jsonObject.get("date").toString()
            oldSoftwareReturnDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
            oldSoftwareReturnDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            oldSoftwareReturnDetails.netAmount = Double.parseDouble(jsonObject.get("netAmount").toString())
            oldSoftwareReturnDetails.balance = Double.parseDouble(jsonObject.get("balance").toString())
            oldSoftwareReturnDetails.dbAdjamount = Double.parseDouble(jsonObject.get("dbAdjamount").toString())
            oldSoftwareReturnDetails.debitIds = Long.parseLong(jsonObject.get("debitIds").toString())
            oldSoftwareReturnDetails.adjustmentStatus = Long.parseLong(jsonObject.get("adjustmentStatus").toString())
            oldSoftwareReturnDetails.financialYear = jsonObject.get("financialYear").toString()
            oldSoftwareReturnDetails.status = jsonObject.get("status").toString()
            oldSoftwareReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            oldSoftwareReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            oldSoftwareReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            oldSoftwareReturnDetails.save(flush: true)
            if (!oldSoftwareReturnDetails.hasErrors())
            {
                return oldSoftwareReturnDetails
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
            OldSoftwareReturnDetails oldSoftwareReturnDetails = OldSoftwareReturnDetails.findById(Long.parseLong(id))
            if (oldSoftwareReturnDetails)
            {
                oldSoftwareReturnDetails.isUpdatable = true
                oldSoftwareReturnDetails.delete()
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
