package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class GoodsTransferNoteProductService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return GoodsTransferNoteProduct.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return GoodsTransferNoteProduct.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
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

    GoodsTransferNoteProduct get(String id)
    {
        return GoodsTransferNoteProduct.findById(Long.parseLong(id))
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
        def salesProductDetailsCriteria = GoodsTransferNoteProduct.createCriteria()
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

    GoodsTransferNoteProduct save(JSONObject jsonObject)
    {
        GoodsTransferNoteProduct goodsTransferNoteProduct = new GoodsTransferNoteProduct()
        goodsTransferNoteProduct.finId = Long.parseLong(jsonObject.get("finId").toString())
        goodsTransferNoteProduct.billId = Long.parseLong(jsonObject.get("billId").toString())
        goodsTransferNoteProduct.reason = jsonObject.get("reason").toString()
        goodsTransferNoteProduct.billType = Long.parseLong(jsonObject.get("billType").toString())
        goodsTransferNoteProduct.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        goodsTransferNoteProduct.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        goodsTransferNoteProduct.productId = Long.parseLong(jsonObject.get("productId").toString())
        goodsTransferNoteProduct.batchNumber = jsonObject.get("batchNumber").toString()
        goodsTransferNoteProduct.expiryDate = jsonObject.get("expiryDate").toString()
        goodsTransferNoteProduct.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        goodsTransferNoteProduct.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        goodsTransferNoteProduct.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        goodsTransferNoteProduct.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        goodsTransferNoteProduct.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        goodsTransferNoteProduct.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        goodsTransferNoteProduct.discount = Double.parseDouble(jsonObject.get("discount").toString())
        goodsTransferNoteProduct.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        goodsTransferNoteProduct.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        goodsTransferNoteProduct.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        goodsTransferNoteProduct.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        goodsTransferNoteProduct.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        goodsTransferNoteProduct.amount = Double.parseDouble(jsonObject.get("amount").toString())
        goodsTransferNoteProduct.reason = jsonObject.get("reason").toString()
        goodsTransferNoteProduct.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        goodsTransferNoteProduct.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        goodsTransferNoteProduct.saleFinId = jsonObject.get("saleFinId").toString()
        goodsTransferNoteProduct.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        goodsTransferNoteProduct.status = Long.parseLong(jsonObject.get("status").toString())
        goodsTransferNoteProduct.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        goodsTransferNoteProduct.financialYear = jsonObject.get("financialYear").toString()
        goodsTransferNoteProduct.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        goodsTransferNoteProduct.entityId = Long.parseLong(jsonObject.get("entityId").toString())

        goodsTransferNoteProduct.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        goodsTransferNoteProduct.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        goodsTransferNoteProduct.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        goodsTransferNoteProduct.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        goodsTransferNoteProduct.uuid = jsonObject.get("uuid").toString()

        goodsTransferNoteProduct.save(flush: true)
        if (!goodsTransferNoteProduct.hasErrors())
        {
            return goodsTransferNoteProduct
        }
        else
        {
            throw new BadRequestException()
        }
    }

    GoodsTransferNoteProduct update(JSONObject jsonObject, String id)
    {
        GoodsTransferNoteProduct goodsTransferNoteProduct = GoodsTransferNoteProduct.findById(Long.parseLong(id))
        if (GoodsTransferNoteProduct)
        {
            goodsTransferNoteProduct.isUpdatable = true
            goodsTransferNoteProduct.finId = Long.parseLong(jsonObject.get("finId").toString())
            goodsTransferNoteProduct.billId = Long.parseLong(jsonObject.get("billId").toString())
            goodsTransferNoteProduct.billType = Long.parseLong(jsonObject.get("billType").toString())
            goodsTransferNoteProduct.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            goodsTransferNoteProduct.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            goodsTransferNoteProduct.productId = Long.parseLong(jsonObject.get("productId").toString())
            goodsTransferNoteProduct.batchNumber = jsonObject.get("batchNumber").toString()
            goodsTransferNoteProduct.expiryDate = jsonObject.get("expiryDate").toString()
            goodsTransferNoteProduct.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            goodsTransferNoteProduct.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            goodsTransferNoteProduct.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            goodsTransferNoteProduct.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            goodsTransferNoteProduct.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            goodsTransferNoteProduct.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            goodsTransferNoteProduct.discount = Double.parseDouble(jsonObject.get("discount").toString())
            goodsTransferNoteProduct.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            goodsTransferNoteProduct.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            goodsTransferNoteProduct.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            goodsTransferNoteProduct.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            goodsTransferNoteProduct.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            goodsTransferNoteProduct.amount = Double.parseDouble(jsonObject.get("amount").toString())
            goodsTransferNoteProduct.reason = jsonObject.get("reason").toString()
            goodsTransferNoteProduct.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            goodsTransferNoteProduct.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            goodsTransferNoteProduct.saleFinId = jsonObject.get("saleFinId").toString()
            goodsTransferNoteProduct.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            goodsTransferNoteProduct.status = Long.parseLong(jsonObject.get("status").toString())
            goodsTransferNoteProduct.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            goodsTransferNoteProduct.financialYear = jsonObject.get("financialYear").toString()
            goodsTransferNoteProduct.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            goodsTransferNoteProduct.entityId = Long.parseLong(jsonObject.get("entityId").toString())

            goodsTransferNoteProduct.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            goodsTransferNoteProduct.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            goodsTransferNoteProduct.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            goodsTransferNoteProduct.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            goodsTransferNoteProduct.uuid = jsonObject.get("uuid").toString()
            goodsTransferNoteProduct.save(flush: true)
            if (!goodsTransferNoteProduct.hasErrors())
            {
                return goodsTransferNoteProduct
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
            GoodsTransferNoteProduct goodsTransferNoteProduct = GoodsTransferNoteProduct.findById(Long.parseLong(id))
            if (goodsTransferNoteProduct)
            {
                goodsTransferNoteProduct.isUpdatable = true
                goodsTransferNoteProduct.delete()
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

    def getByGtn(String id)
    {
        if (id)
        {
            ArrayList<GoodsTransferNoteProduct> goodsTransferNoteProduct = GoodsTransferNoteProduct.findAllByBillId(Long.parseLong(id))
            if (goodsTransferNoteProduct)
            {
                return goodsTransferNoteProduct
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
        ArrayList<GoodsTransferNoteProduct> goodsTransferNoteProduct = GoodsTransferNoteProduct.findAllByBillIdInList(arrayListIds)
        if (goodsTransferNoteProduct)
        {
            return goodsTransferNoteProduct
        }
        else
        {
            throw new ResourceNotFoundException()
        }

    }

    Object getGoodsTransferNoteProductByProductId(String productId)
    {
        try
        {
            return GoodsTransferNoteProduct.findAllByProductId(Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("GoodsTransferNoteProductService" + ex)
            println("GoodsTransferNoteProductService" + ex)
        }
    }


    Object getGoodsTransferNoteProductByBillIdAndBatch(String billId, String batchNumber,String productId)
    {
        try
        {
            return GoodsTransferNoteProduct.findByBillIdAndBatchNumberAndProductId(Long.parseLong(billId),batchNumber,Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("GoodsTransferNoteProductService" + ex)
            println("GoodsTransferNoteProductService" + ex)
        }
    }

}
