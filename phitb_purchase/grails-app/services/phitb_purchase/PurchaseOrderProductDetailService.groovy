package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseOrderProductDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseProductDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseProductDetail.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    PurchaseProductDetail get(String id) {
        return PurchaseProductDetail.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "finId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def purchaseOrderProductDetailCriteria = PurchaseOrderProductDetail.createCriteria()
        def purchaseOrderProductDetailArrayList = purchaseOrderProductDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseOrderProductDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseOrderProductDetailArrayList)
        return jsonObject
    }



    PurchaseOrderProductDetail save(JSONObject jsonObject) {
        PurchaseOrderProductDetail purchaseOrderProductDetail = new PurchaseOrderProductDetail()
        purchaseOrderProductDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseOrderProductDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
        purchaseOrderProductDetail.billType = jsonObject.get("billType").toString()
        purchaseOrderProductDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseOrderProductDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseOrderProductDetail.productId = Long.parseLong(jsonObject.get("productId").toString())
        purchaseOrderProductDetail.batchNumber = jsonObject.get("batchNumber").toString()
        purchaseOrderProductDetail.expiryDate = sdf1.parse(jsonObject.get("expiryDate").toString())
        purchaseOrderProductDetail.sqty = Long.parseLong(jsonObject.get("sqty").toString())
        purchaseOrderProductDetail.freeQty = Long.parseLong(jsonObject.get("freeQty").toString())
        purchaseOrderProductDetail.repQty = Long.parseLong(jsonObject.get("repQty").toString())
        purchaseOrderProductDetail.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        purchaseOrderProductDetail.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        purchaseOrderProductDetail.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        purchaseOrderProductDetail.discount = Double.parseDouble(jsonObject.get("discount").toString())
        purchaseOrderProductDetail.taxId = Long.parseLong(jsonObject.get("taxId").toString())
        purchaseOrderProductDetail.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        purchaseOrderProductDetail.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        purchaseOrderProductDetail.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        purchaseOrderProductDetail.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        purchaseOrderProductDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
        purchaseOrderProductDetail.reason = jsonObject.get("reason").toString()
        purchaseOrderProductDetail.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        purchaseOrderProductDetail.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        purchaseOrderProductDetail.saleFinId = jsonObject.get("saleFinId").toString()
        purchaseOrderProductDetail.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        purchaseOrderProductDetail.status = Long.parseLong(jsonObject.get("status").toString())
        purchaseOrderProductDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseOrderProductDetail.financialYear = jsonObject.get("financialYear").toString()
        purchaseOrderProductDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseOrderProductDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseOrderProductDetail.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        purchaseOrderProductDetail.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        purchaseOrderProductDetail.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        purchaseOrderProductDetail.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        purchaseOrderProductDetail.uuid = jsonObject.get("uuid").toString()
        purchaseOrderProductDetail.save(flush: true)
        if (!purchaseOrderProductDetail.hasErrors())
            return purchaseOrderProductDetail
        else
            throw new BadRequestException()
    }

    PurchaseOrderProductDetail update(JSONObject jsonObject, String id) {
        PurchaseOrderProductDetail purchaseProductDetail = PurchaseOrderProductDetail.findById(Long.parseLong(id))
        if (purchaseProductDetail) {
            purchaseProductDetail.isUpdatable = true
            purchaseProductDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseProductDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
            purchaseProductDetail.billType = jsonObject.get("billType").toString()
            purchaseProductDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseProductDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseProductDetail.productId = Long.parseLong(jsonObject.get("productId").toString())
            purchaseProductDetail.batchNumber = jsonObject.get("batchNumber").toString()
            purchaseProductDetail.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
            purchaseProductDetail.sqty = Long.parseLong(jsonObject.get("sqty").toString())
            purchaseProductDetail.freeQty = Long.parseLong(jsonObject.get("freeQty").toString())
            purchaseProductDetail.repQty = Long.parseLong(jsonObject.get("repQty").toString())
            purchaseProductDetail.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            purchaseProductDetail.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            purchaseProductDetail.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            purchaseProductDetail.discount = Double.parseDouble(jsonObject.get("discount").toString())
            purchaseProductDetail.taxId = Long.parseLong(jsonObject.get("taxId").toString())
            purchaseProductDetail.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            purchaseProductDetail.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            purchaseProductDetail.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            purchaseProductDetail.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            purchaseProductDetail.amount = Double.parseDouble(jsonObject.get("amount").toString())
            purchaseProductDetail.reason = jsonObject.get("reason").toString()
            purchaseProductDetail.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            purchaseProductDetail.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            purchaseProductDetail.saleFinId = jsonObject.get("saleFinId").toString()
            purchaseProductDetail.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            purchaseProductDetail.status = Long.parseLong(jsonObject.get("status").toString())
            purchaseProductDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseProductDetail.financialYear = jsonObject.get("financialYear").toString()
            purchaseProductDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseProductDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseProductDetail.uuid = jsonObject.get("uuid").toString()
            purchaseProductDetail.save(flush: true)
            if (!purchaseProductDetail.hasErrors())
                return purchaseProductDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseOrderProductDetail purchaseProductDetail = PurchaseOrderProductDetail.findById(Long.parseLong(id))
            if (purchaseProductDetail) {
                purchaseProductDetail.isUpdatable = true
                purchaseProductDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def getByPurchaseBill(String id)
    {
        if (id)
        {
            ArrayList<PurchaseOrderProductDetail> purchaseProductDetails = PurchaseOrderProductDetail.findAllByBillId(Long.parseLong(id))
            if (purchaseProductDetails)
            {
                return purchaseProductDetails
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

    def getByPurchaseBillByList(ArrayList<Long> arrayListIds)
    {
        ArrayList<PurchaseOrderProductDetail> purchaseProductDetails = PurchaseOrderProductDetail.findAllByBillIdInList(arrayListIds)
        if (purchaseProductDetails)
        {
            return purchaseProductDetails
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

}
