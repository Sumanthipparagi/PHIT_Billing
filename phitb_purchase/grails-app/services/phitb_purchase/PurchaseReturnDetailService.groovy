package phitb_purchase

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseReturnDetailService {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return PurchaseReturnDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return PurchaseReturnDetail.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
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

    PurchaseReturnDetail get(String id)
    {
        return PurchaseReturnDetail.findById(Long.parseLong(id))
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
        def salesProductDetailsCriteria = SaleReturnDetails.createCriteria()
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

    PurchaseReturnDetail save(JSONObject jsonObject)
    {
        PurchaseReturnDetail purchaseReturnDetails = new PurchaseReturnDetail()
        purchaseReturnDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseReturnDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        purchaseReturnDetails.reason = jsonObject.get("reason").toString()
        purchaseReturnDetails.invoiceNumber = jsonObject.get("invoiceNumber").toString()
        if(jsonObject.get("purBillId").toString()!=null && jsonObject.get("purBillId").toString()!="")
        {
            purchaseReturnDetails.purBillId = Long.parseLong(jsonObject.get("purBillId").toString())
        }
        else
        {
            purchaseReturnDetails.purBillId = null
        }
        purchaseReturnDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
        purchaseReturnDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseReturnDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseReturnDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
        purchaseReturnDetails.batchNumber = jsonObject.get("batchNumber").toString()
        purchaseReturnDetails.expiryDate = jsonObject.get("expiryDate").toString()
        purchaseReturnDetails.sqty = Long.parseLong(jsonObject.get("sqty").toString())
        purchaseReturnDetails.freeQty = Long.parseLong(jsonObject.get("freeQty").toString())
        purchaseReturnDetails.repQty = Long.parseLong(jsonObject.get("repQty").toString())
        purchaseReturnDetails.pRate = Double.parseDouble("0")
        purchaseReturnDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        purchaseReturnDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        purchaseReturnDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
        purchaseReturnDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        purchaseReturnDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        purchaseReturnDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        purchaseReturnDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        purchaseReturnDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
        purchaseReturnDetails.reason = jsonObject.get("reason").toString()
        purchaseReturnDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        purchaseReturnDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        purchaseReturnDetails.saleFinId = jsonObject.get("saleFinId").toString()
        purchaseReturnDetails.uuid = jsonObject.get("uuid").toString()

        purchaseReturnDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        purchaseReturnDetails.status = Long.parseLong(jsonObject.get("status").toString())
        purchaseReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseReturnDetails.financialYear = jsonObject.get("financialYear").toString()
        purchaseReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseReturnDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        purchaseReturnDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        purchaseReturnDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        purchaseReturnDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        purchaseReturnDetails.save(flush: true)
        if (!purchaseReturnDetails.hasErrors())
        {
            return purchaseReturnDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    PurchaseReturnDetail update(JSONObject jsonObject, String id)
    {
        PurchaseReturnDetail purchaseReturnDetails = PurchaseReturnDetail.findById(Long.parseLong(id))
        if (purchaseReturnDetails)
        {
            purchaseReturnDetails.isUpdatable = true
            purchaseReturnDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseReturnDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
            purchaseReturnDetails.reason = jsonObject.get("reason").toString()
            purchaseReturnDetails.invoiceNumber = jsonObject.get("invoiceNumber").toString()
            if(jsonObject.get("purBillId").toString()!=null && jsonObject.get("purBillId").toString()!="")
            {
                purchaseReturnDetails.purBillId = Long.parseLong(jsonObject.get("purBillId").toString())
            }
            else
            {
                purchaseReturnDetails.purBillId = null
            }
            purchaseReturnDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
            purchaseReturnDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseReturnDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseReturnDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
            purchaseReturnDetails.batchNumber = jsonObject.get("batchNumber").toString()
            purchaseReturnDetails.expiryDate = jsonObject.get("expiryDate").toString()
            purchaseReturnDetails.sqty = Long.parseLong(jsonObject.get("sqty").toString())
            purchaseReturnDetails.freeQty = Long.parseLong(jsonObject.get("freeQty").toString())
            purchaseReturnDetails.repQty = Long.parseLong(jsonObject.get("repQty").toString())
            purchaseReturnDetails.pRate = Double.parseDouble("0")
            purchaseReturnDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            purchaseReturnDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            purchaseReturnDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
            purchaseReturnDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            purchaseReturnDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            purchaseReturnDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            purchaseReturnDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            purchaseReturnDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
            purchaseReturnDetails.reason = jsonObject.get("reason").toString()
            purchaseReturnDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            purchaseReturnDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            purchaseReturnDetails.saleFinId = jsonObject.get("saleFinId").toString()
            purchaseReturnDetails.uuid = jsonObject.get("uuid").toString()

            purchaseReturnDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            purchaseReturnDetails.status = Long.parseLong(jsonObject.get("status").toString())
            purchaseReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseReturnDetails.financialYear = jsonObject.get("financialYear").toString()
            purchaseReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseReturnDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            purchaseReturnDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            purchaseReturnDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            purchaseReturnDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            purchaseReturnDetails.save(flush: true)
            if (!purchaseReturnDetails.hasErrors())
            {
                return purchaseReturnDetails
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
            PurchaseReturnDetail returnDetails = PurchaseReturnDetail.findById(Long.parseLong(id))
            if (returnDetails)
            {
                returnDetails.isUpdatable = true
                returnDetails.delete()
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

    def getByPurchaseBill(String id)
    {
        if (id)
        {
            ArrayList<PurchaseReturnDetail> purchaseReturnDetails = PurchaseReturnDetail.findAllByBillId(Long.parseLong(id))
            if (purchaseReturnDetails)
            {
                return purchaseReturnDetails
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



    def getPurchaseReturnDetailsByBill(String id)
    {
        return PurchaseReturnDetail.findByBillId(Long.parseLong(id))
    }

    def getPurchaseDetailsByProductBatchPurBill(String productId, String batch, String saleBillId)
    {
        return PurchaseReturnDetail.findAllByProductIdAndBatchNumberAndPurBillId(productId as long,batch, saleBillId as Long)
    }

}
