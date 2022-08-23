package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DeliveryChallanProductService {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return DeliveryChallanProduct.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return DeliveryChallanProduct.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
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

    DeliveryChallanProduct get(String id)
    {
        return DeliveryChallanProduct.findById(Long.parseLong(id))
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
        def salesProductDetailsCriteria = DeliveryChallanProduct.createCriteria()
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

    DeliveryChallanProduct save(JSONObject jsonObject)
    {
        DeliveryChallanProduct deliveryChallanProduct = new DeliveryChallanProduct()
        deliveryChallanProduct.finId = Long.parseLong(jsonObject.get("finId").toString())
        deliveryChallanProduct.billId = Long.parseLong(jsonObject.get("billId").toString())
        deliveryChallanProduct.reason = jsonObject.get("reason").toString()
        deliveryChallanProduct.billType = Long.parseLong(jsonObject.get("billType").toString())
        deliveryChallanProduct.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        deliveryChallanProduct.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        deliveryChallanProduct.productId = Long.parseLong(jsonObject.get("productId").toString())
        deliveryChallanProduct.batchNumber = jsonObject.get("batchNumber").toString()
        deliveryChallanProduct.expiryDate = jsonObject.get("expiryDate").toString()
        deliveryChallanProduct.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        deliveryChallanProduct.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        deliveryChallanProduct.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        deliveryChallanProduct.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        deliveryChallanProduct.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        deliveryChallanProduct.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        deliveryChallanProduct.discount = Double.parseDouble(jsonObject.get("discount").toString())
        deliveryChallanProduct.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        deliveryChallanProduct.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        deliveryChallanProduct.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        deliveryChallanProduct.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        deliveryChallanProduct.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        deliveryChallanProduct.amount = Double.parseDouble(jsonObject.get("amount").toString())
        deliveryChallanProduct.reason = jsonObject.get("reason").toString()
        deliveryChallanProduct.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        deliveryChallanProduct.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        deliveryChallanProduct.saleFinId = jsonObject.get("saleFinId").toString()
        deliveryChallanProduct.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        deliveryChallanProduct.status = Long.parseLong(jsonObject.get("status").toString())
        deliveryChallanProduct.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        deliveryChallanProduct.financialYear = jsonObject.get("financialYear").toString()
        deliveryChallanProduct.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        deliveryChallanProduct.entityId = Long.parseLong(jsonObject.get("entityId").toString())

        deliveryChallanProduct.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        deliveryChallanProduct.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        deliveryChallanProduct.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        deliveryChallanProduct.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        deliveryChallanProduct.originalSqty = Long.parseLong(jsonObject.get("originalSqty").toString())
        deliveryChallanProduct.originalFqty = Long.parseLong(jsonObject.get("originalFqty").toString())
        deliveryChallanProduct.uuid = jsonObject.get("uuid").toString()

        deliveryChallanProduct.save(flush: true)
        if (!deliveryChallanProduct.hasErrors())
        {
            return deliveryChallanProduct
        }
        else
        {
            throw new BadRequestException()
        }
    }

    DeliveryChallanProduct update(JSONObject jsonObject, String id)
    {
        DeliveryChallanProduct deliveryChallanProduct = DeliveryChallanProduct.findById(Long.parseLong(id))
        if (DeliveryChallanProduct)
        {
            deliveryChallanProduct.isUpdatable = true
            deliveryChallanProduct.finId = Long.parseLong(jsonObject.get("finId").toString())
            deliveryChallanProduct.billId = Long.parseLong(jsonObject.get("billId").toString())
            deliveryChallanProduct.billType = Long.parseLong(jsonObject.get("billType").toString())
            deliveryChallanProduct.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            deliveryChallanProduct.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            deliveryChallanProduct.productId = Long.parseLong(jsonObject.get("productId").toString())
            deliveryChallanProduct.batchNumber = jsonObject.get("batchNumber").toString()
            deliveryChallanProduct.expiryDate = jsonObject.get("expiryDate").toString()
            deliveryChallanProduct.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            deliveryChallanProduct.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            deliveryChallanProduct.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            deliveryChallanProduct.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            deliveryChallanProduct.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            deliveryChallanProduct.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            deliveryChallanProduct.discount = Double.parseDouble(jsonObject.get("discount").toString())
            deliveryChallanProduct.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            deliveryChallanProduct.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            deliveryChallanProduct.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            deliveryChallanProduct.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            deliveryChallanProduct.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            deliveryChallanProduct.amount = Double.parseDouble(jsonObject.get("amount").toString())
            deliveryChallanProduct.reason = jsonObject.get("reason").toString()
            deliveryChallanProduct.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            deliveryChallanProduct.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            deliveryChallanProduct.saleFinId = jsonObject.get("saleFinId").toString()
            deliveryChallanProduct.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            deliveryChallanProduct.status = Long.parseLong(jsonObject.get("status").toString())
            deliveryChallanProduct.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            deliveryChallanProduct.financialYear = jsonObject.get("financialYear").toString()
            deliveryChallanProduct.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            deliveryChallanProduct.entityId = Long.parseLong(jsonObject.get("entityId").toString())

            deliveryChallanProduct.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            deliveryChallanProduct.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            deliveryChallanProduct.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            deliveryChallanProduct.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            deliveryChallanProduct.originalSqty = Long.parseLong(jsonObject.get("originalSqty").toString())
            deliveryChallanProduct.originalFqty = Long.parseLong(jsonObject.get("originalFqty").toString())
            deliveryChallanProduct.uuid = jsonObject.get("uuid").toString()
            deliveryChallanProduct.save(flush: true)
            if (!deliveryChallanProduct.hasErrors())
            {
                return deliveryChallanProduct
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
            DeliveryChallanProduct deliveryChallanProduct = DeliveryChallanProduct.findById(Long.parseLong(id))
            if (deliveryChallanProduct)
            {
                deliveryChallanProduct.isUpdatable = true
                deliveryChallanProduct.delete()
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

    def getByDeliveryChallan(String id)
    {
        if (id)
        {
            ArrayList<DeliveryChallanProduct> deliveryChallanProduct = DeliveryChallanProduct.findAllByBillId(Long.parseLong(id))
            if (deliveryChallanProduct)
            {
                return deliveryChallanProduct
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
        ArrayList<DeliveryChallanProduct> deliveryChallanProduct = DeliveryChallanProduct.findAllByBillIdInList(arrayListIds)
        if (deliveryChallanProduct)
        {
            return deliveryChallanProduct
        }
        else
        {
            throw new ResourceNotFoundException()
        }

    }

    Object getDeliveryChallanProductByProductId(String productId)
    {
        try
        {
            return DeliveryChallanProduct.findAllByProductId(Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("DeliveryChallanProductService" + ex)
            println("DeliveryChallanProductService" + ex)
        }
    }


    Object getDeliveryChallanProductByBillIdAndBatch(String billId, String batchNumber,String productId)
    {
        try
        {
            return DeliveryChallanProduct.findByBillIdAndBatchNumberAndProductId(Long.parseLong(billId),batchNumber,Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("DeliveryChallanProductService" + ex)
            println("DeliveryChallanProductService" + ex)
        }
    }

}
