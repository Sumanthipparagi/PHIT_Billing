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
        PurchaseReturnDetail purchaseReturnDetail = new PurchaseReturnDetail()
        purchaseReturnDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseReturnDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
        purchaseReturnDetail.reason = jsonObject.get("reason").toString()
        purchaseReturnDetail.invoiceNumber = jsonObject.get("invoiceNumber").toString()
        if(jsonObject.get("purBillId").toString()!=null && jsonObject.get("purBillId").toString()!="")
        {
            purchaseReturnDetail.purBillId = Long.parseLong(jsonObject.get("purBillId").toString())
        }
        else
        {
            purchaseReturnDetail.purBillId = null
        }
        purchaseReturnDetail.billType = Long.parseLong(jsonObject.get("billType").toString())
        purchaseReturnDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseReturnDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseReturnDetail.productId = Long.parseLong(jsonObject.get("productId").toString())
        purchaseReturnDetail.batchNumber = jsonObject.get("batchNumber").toString()
        purchaseReturnDetail.expiryDate = jsonObject.get("expiryDate").toString()
        purchaseReturnDetail.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        purchaseReturnDetail.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        purchaseReturnDetail.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        purchaseReturnDetail.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        purchaseReturnDetail.sRate = Double.parseDouble(jsonObject.get("pRate").toString())
        purchaseReturnDetail.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        purchaseReturnDetail.discount = Double.parseDouble(jsonObject.get("discount").toString())
        purchaseReturnDetail.returnStatus = jsonObject.get('returnStatus').toString()
        purchaseReturnDetail.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        purchaseReturnDetail.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        purchaseReturnDetail.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        purchaseReturnDetail.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        purchaseReturnDetail.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        purchaseReturnDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
        purchaseReturnDetail.reason = jsonObject.get("reason").toString()
        purchaseReturnDetail.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        purchaseReturnDetail.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        purchaseReturnDetail.saleFinId = jsonObject.get("saleFinId").toString()
        purchaseReturnDetail.uuid = jsonObject.get("uuid").toString()

        purchaseReturnDetail.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        purchaseReturnDetail.status = Long.parseLong(jsonObject.get("status").toString())
        purchaseReturnDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseReturnDetail.financialYear = jsonObject.get("financialYear").toString()
        purchaseReturnDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseReturnDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseReturnDetail.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        purchaseReturnDetail.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        purchaseReturnDetail.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        purchaseReturnDetail.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        purchaseReturnDetail.save(flush: true)
        if (!purchaseReturnDetail.hasErrors())
        {
            return purchaseReturnDetail
        }
        else
        {
            throw new BadRequestException()
        }
    }

    PurchaseReturnDetail update(JSONObject jsonObject, String id)
    {
        PurchaseReturnDetail purchaseReturnDetail = PurchaseReturnDetail.findById(Long.parseLong(id))
        if (purchaseReturnDetail)
        {
            purchaseReturnDetail.isUpdatable = true
            purchaseReturnDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseReturnDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
            purchaseReturnDetail.reason = jsonObject.get("reason").toString()
            purchaseReturnDetail.invoiceNumber = jsonObject.get("invoiceNumber").toString()
            if(jsonObject.get("purBillId").toString()!=null && jsonObject.get("purBillId").toString()!="")
            {
                purchaseReturnDetail.purBillId = Long.parseLong(jsonObject.get("purBillId").toString())
            }
            else
            {
                purchaseReturnDetail.purBillId = null
            }
            purchaseReturnDetail.billType = Long.parseLong(jsonObject.get("billType").toString())
            purchaseReturnDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseReturnDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseReturnDetail.productId = Long.parseLong(jsonObject.get("productId").toString())
            purchaseReturnDetail.batchNumber = jsonObject.get("batchNumber").toString()
            purchaseReturnDetail.expiryDate = jsonObject.get("expiryDate").toString()
            purchaseReturnDetail.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            purchaseReturnDetail.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            purchaseReturnDetail.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            purchaseReturnDetail.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            purchaseReturnDetail.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            purchaseReturnDetail.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            purchaseReturnDetail.discount = Double.parseDouble(jsonObject.get("discount").toString())
            purchaseReturnDetail.returnStatus = jsonObject.get('billStatus').toString()
            purchaseReturnDetail.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            purchaseReturnDetail.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            purchaseReturnDetail.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            purchaseReturnDetail.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            purchaseReturnDetail.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            purchaseReturnDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
            purchaseReturnDetail.reason = jsonObject.get("reason").toString()
            purchaseReturnDetail.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            purchaseReturnDetail.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            purchaseReturnDetail.saleFinId = jsonObject.get("saleFinId").toString()
            purchaseReturnDetail.uuid = jsonObject.get("uuid").toString()
            purchaseReturnDetail.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            purchaseReturnDetail.status = Long.parseLong(jsonObject.get("status").toString())
            purchaseReturnDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseReturnDetail.financialYear = jsonObject.get("financialYear").toString()
            purchaseReturnDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseReturnDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseReturnDetail.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            purchaseReturnDetail.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            purchaseReturnDetail.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            purchaseReturnDetail.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            purchaseReturnDetail.save(flush: true)
            if (!purchaseReturnDetail.hasErrors())
            {
                return purchaseReturnDetail
            }
            else
            {
                throw new BadRequestException()
            }
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

    def getByDateRangeAndEntity(String dateRange, String entityId)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(dateRange.split("-")[0].trim().toString())
            Date toDate = sdf.parse(dateRange.split("-")[1].trim().toString())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            long eid = Long.parseLong(entityId)
            JSONArray finalBills = new JSONArray()
            ArrayList<PurchaseReturn> purchaseReturnDetails = PurchaseReturn.findAllByEntityIdAndDateCreatedBetween(eid, fromDate, toDate)
            for (PurchaseReturn purchaseReturn : purchaseReturnDetails) {
                JSONObject purchaseRetrun = new JSONObject((purchaseReturn as JSON).toString())
                def productDetails = PurchaseProductDetail.findAllByBillId(purchaseReturn.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    purchaseRetrun.put("products", prdt)
                }
                finalBills.add(purchaseRetrun)
            }
            println(finalBills)
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }

    def getPurchaseReturnDetailsByBill(String id)
    {
        return PurchaseReturnDetail.findAllByBillId(Long.parseLong(id))
    }

    def getPurchaseDetailsByProductBatchPurBill(String productId, String batch, String saleBillId)
    {
        return PurchaseReturnDetail.findAllByProductIdAndBatchNumberAndPurBillId(productId as long,batch, saleBillId as Long)
    }

}
