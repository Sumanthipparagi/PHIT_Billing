package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SampleConversionDetailsService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SampleConversionDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SampleConversionDetails.findAllByBillStatusIlike("%" + query + "%", [sort: 'id', max: l, offset: o,
                                                                                    order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SampleConversionDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SampleConversionDetails.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }

    SampleConversionDetails get(String id)
    {
        return SampleConversionDetails.findById(Long.parseLong(id))
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
                orderColumn = "billStatus"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def sampleConversionDetailsCriteria = SampleConversionDetails.createCriteria()
        def sampleConversionDetailsArrayList = sampleConversionDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('billStatus', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = sampleConversionDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", sampleConversionDetailsArrayList)
        return jsonObject
    }

    SampleConversionDetails save(JSONObject jsonObject)
    {
        SampleConversionDetails sampleConversionDetails = new SampleConversionDetails()
        sampleConversionDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        sampleConversionDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        sampleConversionDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        sampleConversionDetails.agentId = Long.parseLong(jsonObject.get("agentId").toString())
        sampleConversionDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        sampleConversionDetails.totalItems = Long.parseLong(jsonObject.get("totalItems").toString())
        sampleConversionDetails.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        sampleConversionDetails.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        sampleConversionDetails.totalPayable = Double.parseDouble(jsonObject.get("cartonsCount").toString())
        sampleConversionDetails.billStatus = jsonObject.get("billStatus").toString()
        sampleConversionDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        sampleConversionDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        sampleConversionDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        sampleConversionDetails.save(flush: true)
        if (!sampleConversionDetails.hasErrors())
        {
            return sampleConversionDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SampleConversionDetails update(JSONObject jsonObject, String id)
    {
        SampleConversionDetails sampleConversionDetails = SampleConversionDetails.findById(Long.parseLong(id))
        if (sampleConversionDetails)
        {
            sampleConversionDetails.isUpdatable = true
            sampleConversionDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            sampleConversionDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            sampleConversionDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            sampleConversionDetails.agentId = Long.parseLong(jsonObject.get("agentId").toString())
            sampleConversionDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
            sampleConversionDetails.totalItems = Long.parseLong(jsonObject.get("totalItems").toString())
            sampleConversionDetails.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            sampleConversionDetails.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
            sampleConversionDetails.totalPayable = Double.parseDouble(jsonObject.get("cartonsCount").toString())
            sampleConversionDetails.billStatus = jsonObject.get("billStatus").toString()
            sampleConversionDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            sampleConversionDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            sampleConversionDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            sampleConversionDetails.save(flush: true)
            if (!sampleConversionDetails.hasErrors())
            {
                return sampleConversionDetails
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
            SampleConversionDetails sampleConversionDetails = SampleConversionDetails.findById(Long.parseLong(id))
            if (sampleConversionDetails)
            {
                sampleConversionDetails.isUpdatable = true
                sampleConversionDetails.delete()
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
