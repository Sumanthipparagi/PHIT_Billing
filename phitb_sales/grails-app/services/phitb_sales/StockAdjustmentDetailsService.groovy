package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class StockAdjustmentDetailsService
{
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD")
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy")


    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return StockAdjustmentDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }


    StockAdjustmentDetails save(JSONObject jsonObject)
    {
        StockAdjustmentDetails stockAdjustment = new StockAdjustmentDetails()
        stockAdjustment.productId =  Long.parseLong(jsonObject.get("productId").toString())
        stockAdjustment.series =  Long.parseLong(jsonObject.get("series").toString())
        stockAdjustment.batchNumber =  jsonObject.get("batchNumber").toString()
        stockAdjustment.expDate =  sdf.parse(jsonObject.get("expDate").toString())
        stockAdjustment.manfDate =  sdf.parse(jsonObject.get("manfDate").toString())
        stockAdjustment.sqty =  Long.parseLong(jsonObject.get("freeQty").toString())
        stockAdjustment.fqty =  Long.parseLong(jsonObject.get("freeQty").toString())
        stockAdjustment.pRate =  Double.parseDouble(jsonObject.get("purRate").toString())
        stockAdjustment.sRate =  Double.parseDouble(jsonObject.get("saleRate").toString())
        stockAdjustment.gst =   Double.parseDouble(jsonObject.get("gst").toString())
        stockAdjustment.taxId =  Long.parseLong(jsonObject.get("taxId").toString())
        stockAdjustment.mrp =  Long.parseLong(jsonObject.get("mrp").toString())
        stockAdjustment.previousSQty =  Long.parseLong(jsonObject.get("originalSqty").toString())
        stockAdjustment.previousFQty =  Long.parseLong(jsonObject.get("originalFQty").toString())
        stockAdjustment.uuid =  jsonObject.get("uuid").toString()
        stockAdjustment.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        stockAdjustment.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        stockAdjustment.createdUser = Long.parseLong(jsonObject.get('createdUser').toString())
        stockAdjustment.modifiedUser = Long.parseLong(jsonObject.get('modifiedUser').toString())
        stockAdjustment.save(flush: true)
        if (!stockAdjustment.hasErrors())
        {
            return stockAdjustment
        }
        else
        {
            throw new BadRequestException()
        }
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")
        String fromDate = paramsJsonObject.get("fromDate").toString()
        String toDate = paramsJsonObject.get("toDate").toString()
        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "batchNumber"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def stockAdjustmentDetails = StockAdjustmentDetails.createCriteria()
        def stockAdjustmentArrryList = stockAdjustmentDetails.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            if (fromDate != null && fromDate != '' && toDate != null && toDate != '')
            {
                between('dateCreated', sdf1.parse(fromDate), sdf1.parse(toDate))
            }
            eq('entityId', entityId)
//            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = stockAdjustmentArrryList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", stockAdjustmentArrryList)
        return jsonObject
    }
}
