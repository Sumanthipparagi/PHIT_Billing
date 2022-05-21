package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SaleOrderProductDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleOrderProductDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleOrderProductDetails.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SaleOrderProductDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleBillDetails.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }

    SaleOrderProductDetails get(String id)
    {
        return SaleOrderProductDetails.findById(Long.parseLong(id))
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
        def salesOrderProductDetails = SaleOrderProductDetails.createCriteria()
        def salesProductDetailsArrayList = salesOrderProductDetails.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = salesProductDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", salesProductDetailsArrayList)
        return jsonObject
    }

    SaleOrderProductDetails save(JSONObject jsonObject)
    {
        SaleOrderProductDetails saleOrderProductDetails = new SaleOrderProductDetails()
        saleOrderProductDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleOrderProductDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        saleOrderProductDetails.reason = jsonObject.get("reason").toString()
        saleOrderProductDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
        saleOrderProductDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleOrderProductDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        saleOrderProductDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
        saleOrderProductDetails.batchNumber = jsonObject.get("batchNumber").toString()
        saleOrderProductDetails.expiryDate = jsonObject.get("expiryDate").toString()
        saleOrderProductDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        saleOrderProductDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        saleOrderProductDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        saleOrderProductDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        saleOrderProductDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        saleOrderProductDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        saleOrderProductDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
        saleOrderProductDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        saleOrderProductDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        saleOrderProductDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        saleOrderProductDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        saleOrderProductDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        saleOrderProductDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
        saleOrderProductDetails.reason = jsonObject.get("reason").toString()
        saleOrderProductDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        saleOrderProductDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        saleOrderProductDetails.saleFinId = jsonObject.get("saleFinId").toString()
        saleOrderProductDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        saleOrderProductDetails.status = Long.parseLong(jsonObject.get("status").toString())
        saleOrderProductDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleOrderProductDetails.financialYear = jsonObject.get("financialYear").toString()
        saleOrderProductDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleOrderProductDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())

        saleOrderProductDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        saleOrderProductDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        saleOrderProductDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        saleOrderProductDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        saleOrderProductDetails.uuid = jsonObject.get("uuid").toString()

        saleOrderProductDetails.save(flush: true)
        if (!saleOrderProductDetails.hasErrors())
        {
            return saleOrderProductDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SaleOrderProductDetails update(JSONObject jsonObject, String id)
    {
        SaleOrderProductDetails saleOrderProductDetails = SaleOrderProductDetails.findById(Long.parseLong(id))
        if (saleOrderProductDetails)
        {
            saleOrderProductDetails.isUpdatable = true
            saleOrderProductDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            saleOrderProductDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
            saleOrderProductDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
            saleOrderProductDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            saleOrderProductDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            saleOrderProductDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
            saleOrderProductDetails.batchNumber = jsonObject.get("batchNumber").toString()
            saleOrderProductDetails.expiryDate = jsonObject.get("expiryDate").toString()
            saleOrderProductDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            saleOrderProductDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            saleOrderProductDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            saleOrderProductDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            saleOrderProductDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            saleOrderProductDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            saleOrderProductDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
            saleOrderProductDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            saleOrderProductDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            saleOrderProductDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            saleOrderProductDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            saleOrderProductDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            saleOrderProductDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
            saleOrderProductDetails.reason = jsonObject.get("reason").toString()
            saleOrderProductDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            saleOrderProductDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            saleOrderProductDetails.saleFinId = jsonObject.get("saleFinId").toString()
            saleOrderProductDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            saleOrderProductDetails.status = Long.parseLong(jsonObject.get("status").toString())
            saleOrderProductDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            saleOrderProductDetails.financialYear = jsonObject.get("financialYear").toString()
            saleOrderProductDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            saleOrderProductDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            saleOrderProductDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            saleOrderProductDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            saleOrderProductDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            saleOrderProductDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            saleOrderProductDetails.uuid = jsonObject.get("uuid").toString()
            saleOrderProductDetails.save(flush: true)
            if (!saleOrderProductDetails.hasErrors())
            {
                return saleOrderProductDetails
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
            SaleOrderProductDetails saleOrderProductDetails = SaleOrderProductDetails.findById(Long.parseLong(id))
            if (saleOrderProductDetails)
            {
                saleOrderProductDetails.isUpdatable = true
                saleOrderProductDetails.delete()
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

    def getBySaleOrder(String id)
    {
        if (id)
        {
            ArrayList<SaleOrderProductDetails> saleOrderProductDetails = SaleOrderProductDetails.findAllByBillId(Long.parseLong(id))
            if (saleOrderProductDetails)
            {
                return saleOrderProductDetails
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


    Object getSaleOrderProductDetailsByProductId(String productId)
    {
        try
        {
            return SaleOrderProductDetails.findAllByProductId(Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }


    Object getSaleOrderProductDetailsByBillIdAndBatch(String billId, String batchNumber,String productId)
    {
        try
        {
            return SaleOrderProductDetails.findByBillIdAndBatchNumberAndProductId(Long.parseLong(billId),batchNumber,Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }
}
