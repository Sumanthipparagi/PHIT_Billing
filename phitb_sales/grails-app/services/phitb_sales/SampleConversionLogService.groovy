package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SampleConversionLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SampleConversionlog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SampleConversionlog.findAllBySampleBatch("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }


    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SampleConversionlog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SampleConversionlog.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }

    SampleConversionlog get(String id)
    {
        return SampleConversionlog.findById(Long.parseLong(id))
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
                orderColumn = "reasonName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def reasonMasterDetailsCriteria = SampleConversionlog.createCriteria()
        def reasonMasterArrayList = reasonMasterDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('reasonName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = reasonMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", reasonMasterArrayList)
        return jsonObject
    }

    SampleConversionlog save(JSONObject jsonObject)
    {
        SampleConversionlog sampleConversionlog = new SampleConversionlog()
        sampleConversionlog.saleableProductId = Long.parseLong(jsonObject.get("saleableProductId").toString())
        sampleConversionlog.saleableBatch = jsonObject.get("saleableBatch").toString()
        sampleConversionlog.saleableQty = Long.parseLong(jsonObject.get("saleableQty").toString())
        sampleConversionlog.sampleProductId = Long.parseLong(jsonObject.get("sampleProductId").toString())
        sampleConversionlog.sampleBatch = jsonObject.get("sampleBatch").toString()
        sampleConversionlog.sampleQty = Long.parseLong(jsonObject.get("sampleQty").toString())
        sampleConversionlog.save(flush: true)
        if (!sampleConversionlog.hasErrors())
        {
            return sampleConversionlog
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SampleConversionlog update(JSONObject jsonObject, String id)
    {
        SampleConversionlog sampleConversionlog = SampleConversionlog.findById(Long.parseLong(id))
        if (sampleConversionlog)
        {
            sampleConversionlog.isUpdatable = true
            sampleConversionlog.saleableProductId = Long.parseLong(jsonObject.get("saleableProductId").toString())
            sampleConversionlog.saleableBatch = jsonObject.get("saleableBatch").toString()
            sampleConversionlog.saleableQty = Long.parseLong(jsonObject.get("saleableQty").toString())
            sampleConversionlog.sampleProductId = Long.parseLong(jsonObject.get("sampleProductId").toString())
            sampleConversionlog.sampleBatch = jsonObject.get("sampleBatch").toString()
            sampleConversionlog.sampleQty = Long.parseLong(jsonObject.get("sampleQty").toString())
            sampleConversionlog.save(flush: true)
            if (!sampleConversionlog.hasErrors())
            {
                return sampleConversionlog
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
            SampleConversionlog sampleConversionlog = SampleConversionlog.findById(Long.parseLong(id))
            if (sampleConversionlog)
            {
                sampleConversionlog.isUpdatable = true
                sampleConversionlog.delete()
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
