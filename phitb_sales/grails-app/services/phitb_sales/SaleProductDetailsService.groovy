package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SaleProductDetailsService
{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleProductDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
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
            return SaleBillDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
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

    SaleProductDetails get(String id)
    {
        return SaleProductDetails.findById(Long.parseLong(id))
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
        def salesProductDetailsCriteria = SaleProductDetails.createCriteria()
        def salesProductDetailsArrayList = salesProductDetailsCriteria.list(max: max, offset: offset) {
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

    SaleProductDetails save(JSONObject jsonObject)
    {
        SaleProductDetails saleProductDetails = new SaleProductDetails()
        saleProductDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleProductDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        saleProductDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
        saleProductDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleProductDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        saleProductDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
        saleProductDetails.batchNumber = jsonObject.get("batchNumber").toString()
        saleProductDetails.expiryDate = jsonObject.get("expiryDate").toString()
        saleProductDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        saleProductDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        saleProductDetails.sqtyReturn = Double.parseDouble(jsonObject.get("sqtyReturn").toString())
        saleProductDetails.fqtyReturn = Double.parseDouble(jsonObject.get("fqtyReturn").toString())
        saleProductDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        saleProductDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        saleProductDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        saleProductDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        saleProductDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
        saleProductDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        saleProductDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        saleProductDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        saleProductDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        saleProductDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        saleProductDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
        saleProductDetails.reason = jsonObject.get("reason").toString()
        saleProductDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        saleProductDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        saleProductDetails.saleFinId = jsonObject.get("saleFinId").toString()
        saleProductDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        saleProductDetails.status = Long.parseLong(jsonObject.get("status").toString())
        saleProductDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleProductDetails.financialYear = jsonObject.get("financialYear").toString()
        saleProductDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleProductDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())

        saleProductDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        saleProductDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        saleProductDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        saleProductDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())

        saleProductDetails.save(flush: true)
        if (!saleProductDetails.hasErrors())
        {
            return saleProductDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SaleProductDetails update(JSONObject jsonObject, String id)
    {
        SaleProductDetails saleProductDetails = SaleProductDetails.findById(Long.parseLong(id))
        if (SaleProductDetails)
        {
            saleProductDetails.isUpdatable = true
            saleProductDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            saleProductDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
            saleProductDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
            saleProductDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            saleProductDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            saleProductDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
            saleProductDetails.batchNumber = jsonObject.get("batchNumber").toString()
            saleProductDetails.expiryDate = jsonObject.get("expiryDate").toString()
            saleProductDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            saleProductDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            saleProductDetails.sqtyReturn = Double.parseDouble(jsonObject.get("sqtyReturn").toString())
            saleProductDetails.fqtyReturn = Double.parseDouble(jsonObject.get("fqtyReturn").toString())
            saleProductDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            saleProductDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            saleProductDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            saleProductDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            saleProductDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
            saleProductDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            saleProductDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            saleProductDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            saleProductDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            saleProductDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            saleProductDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
            saleProductDetails.reason = jsonObject.get("reason").toString()
            saleProductDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            saleProductDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            saleProductDetails.saleFinId = jsonObject.get("saleFinId").toString()
            saleProductDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            saleProductDetails.status = Long.parseLong(jsonObject.get("status").toString())
            saleProductDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            saleProductDetails.financialYear = jsonObject.get("financialYear").toString()
            saleProductDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            saleProductDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())

            saleProductDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            saleProductDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            saleProductDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            saleProductDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())

            saleProductDetails.save(flush: true)
            if (!saleProductDetails.hasErrors())
            {
                return saleProductDetails
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
            SaleProductDetails saleProductDetails = SaleProductDetails.findById(Long.parseLong(id))
            if (saleProductDetails)
            {
                saleProductDetails.isUpdatable = true
                saleProductDetails.delete()
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

    def getBySaleBill(String id)
    {
        if (id)
        {
            ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(Long.parseLong(id))
            if (saleProductDetails)
            {
                return saleProductDetails
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

    def getBySaleBillByList(ArrayList<Long> arrayListIds)
    {
        ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillIdInList(arrayListIds)
        if (saleProductDetails)
        {
            return saleProductDetails
        }
        else
        {
            throw new ResourceNotFoundException()
        }

    }

    Object getSaleProductDetailsByProductId(String productId)
    {
        try
        {
            return SaleProductDetails.findAllByProductId(Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }


    Object getSaleProductDetailsByBillIdAndBatch(String billId, String batchNumber)
    {
        try
        {
            return SaleProductDetails.findByBillIdAndBatchNumber(Long.parseLong(billId),batchNumber)
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }

}
