package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class OldSoftwareSaleDetailsService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return OldSoftwareSaleDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return OldSoftwareSaleDetails.findAllByBillIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    OldSoftwareSaleDetails get(String id)
    {
        return OldSoftwareSaleDetails.findById(Long.parseLong(id))
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
        def oldSoftwareSalesDetailsCriteria = OldSoftwareSaleDetails.createCriteria()
        def oldSoftwareSalesDetailsArrayList = oldSoftwareSalesDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('billId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = oldSoftwareSalesDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", oldSoftwareSalesDetailsArrayList)
        return jsonObject
    }

    OldSoftwareSaleDetails save(JSONObject jsonObject)
    {
        OldSoftwareSaleDetails oldSoftwareSaleDetails = new OldSoftwareSaleDetails()
        oldSoftwareSaleDetails.billId = jsonObject.get("billId").toString()
        oldSoftwareSaleDetails.series = Long.parseLong(jsonObject.get("series").toString())
        oldSoftwareSaleDetails.date = jsonObject.get("date").toString()
        oldSoftwareSaleDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
        oldSoftwareSaleDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        oldSoftwareSaleDetails.netAmount = Long.parseLong(jsonObject.get("netAmount").toString())
        oldSoftwareSaleDetails.balance = Long.parseLong(jsonObject.get("balance").toString())
        oldSoftwareSaleDetails.crAdjAmount = Long.parseLong(jsonObject.get("dbAdjamount").toString())
        oldSoftwareSaleDetails.creditIds = Long.parseLong(jsonObject.get("debitIds").toString())
        oldSoftwareSaleDetails.financialYear = jsonObject.get("financialYear").toString()
        oldSoftwareSaleDetails.status = jsonObject.get("status").toString()
        oldSoftwareSaleDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        oldSoftwareSaleDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        oldSoftwareSaleDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        oldSoftwareSaleDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        oldSoftwareSaleDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        oldSoftwareSaleDetails.save(flush: true)
        if (!oldSoftwareSaleDetails.hasErrors())
        {
            return oldSoftwareSaleDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    OldSoftwareSaleDetails update(JSONObject jsonObject, String id)
    {
        OldSoftwareSaleDetails oldSoftwareSaleDetails = OldSoftwareSaleDetails.findById(Long.parseLong(id))
        if (oldSoftwareSaleDetails)
        {
            oldSoftwareSaleDetails.isUpdatable = true
            oldSoftwareSaleDetails.billId = jsonObject.get("billId").toString()
            oldSoftwareSaleDetails.series = Long.parseLong(jsonObject.get("series").toString())
            oldSoftwareSaleDetails.date = jsonObject.get("date").toString()
            oldSoftwareSaleDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
            oldSoftwareSaleDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            oldSoftwareSaleDetails.netAmount = Long.parseLong(jsonObject.get("netAmount").toString())
            oldSoftwareSaleDetails.balance = Long.parseLong(jsonObject.get("balance").toString())
            oldSoftwareSaleDetails.crAdjAmount = Long.parseLong(jsonObject.get("dbAdjamount").toString())
            oldSoftwareSaleDetails.creditIds = Long.parseLong(jsonObject.get("debitIds").toString())
            oldSoftwareSaleDetails.financialYear = jsonObject.get("financialYear").toString()
            oldSoftwareSaleDetails.status = jsonObject.get("status").toString()
            oldSoftwareSaleDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            oldSoftwareSaleDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            oldSoftwareSaleDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            oldSoftwareSaleDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            oldSoftwareSaleDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            oldSoftwareSaleDetails.save(flush: true)
            if (!oldSoftwareSaleDetails.hasErrors())
            {
                return oldSoftwareSaleDetails
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
            OldSoftwareSaleDetails oldSoftwareSaleDetails = OldSoftwareSaleDetails.findById(Long.parseLong(id))
            if (oldSoftwareSaleDetails)
            {
                oldSoftwareSaleDetails.isUpdatable = true
                oldSoftwareSaleDetails.delete()
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
