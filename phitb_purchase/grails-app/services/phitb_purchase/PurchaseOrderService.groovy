package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseOrderService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseOrder.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseOrder.findAllByRemarksIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return PurchaseOrder.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date entryDate = cal.getTime()
            return PurchaseOrder.createCriteria().list {
                gt("entryDate",entryDate)
            }
        }
    }

    PurchaseOrder get(String id) {
        return PurchaseOrder.findById(Long.parseLong(id))
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
                orderColumn = "supplierId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def purchaseOrderCriteria = PurchaseOrder.createCriteria()
        def purchaseOrderArrayList = purchaseOrderCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('supplierId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseOrderArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseOrderArrayList)
        return jsonObject
    }

    PurchaseOrder save(JSONObject jsonObject) {
        PurchaseOrder purchaseOrder = new PurchaseOrder()
        purchaseOrder.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseOrder.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseOrder.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseOrder.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseOrder.purcId = jsonObject.get("purcId").toString()
        purchaseOrder.supplierBillId = jsonObject.get("supplierBillId").toString()
        purchaseOrder.supplierBillDate = sdf.parse(jsonObject.get("supplierBillDate").toString())
        purchaseOrder.billingDate = sdf.parse(jsonObject.get("billingDate").toString())
        purchaseOrder.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        purchaseOrder.dispatchStatus = jsonObject.get("dispatchStatus").toString()
        purchaseOrder.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        purchaseOrder.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        purchaseOrder.productDiscount = Double.parseDouble(jsonObject.get("productDiscount").toString())
        purchaseOrder.receivedDate = sdf.parse(jsonObject.get("receivedDate").toString())
        purchaseOrder.receivedBy = jsonObject.get("receivedBy").toString()
        purchaseOrder.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        purchaseOrder.expectedDeliveryDate = sdf.parse(jsonObject.get("expectedDeliveryDate").toString())
        purchaseOrder.adjustedAmount = Double.parseDouble(jsonObject.get("adjustedAmount").toString())
        purchaseOrder.creditId = jsonObject.get("creditId").toString()
        purchaseOrder.debitId = jsonObject.get("debitId").toString()
        purchaseOrder.crDbAmount = Double.parseDouble(jsonObject.get("crDbAmount").toString())
        purchaseOrder.payableAmount = Double.parseDouble(jsonObject.get("payableAmount").toString())
        purchaseOrder.save(flush: true)
        if (!purchaseOrder.hasErrors())
            return purchaseOrder
        else
            throw new BadRequestException()

    }

    PurchaseOrder update(JSONObject jsonObject, String id) {

        PurchaseOrder purchaseOrder = PurchaseOrder.findById(Long.parseLong(id))
        if (purchaseOrder) {
            purchaseOrder.isUpdatable = true
            purchaseOrder.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseOrder.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
            purchaseOrder.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseOrder.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseOrder.purcId = jsonObject.get("purcId").toString()
            purchaseOrder.supplierBillId = jsonObject.get("supplierBillId").toString()
            purchaseOrder.supplierBillDate = sdf.parse(jsonObject.get("supplierBillDate").toString())
            purchaseOrder.billingDate = sdf.parse(jsonObject.get("billingDate").toString())
            purchaseOrder.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            purchaseOrder.dispatchStatus = jsonObject.get("dispatchStatus").toString()
            purchaseOrder.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
            purchaseOrder.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
            purchaseOrder.productDiscount = Double.parseDouble(jsonObject.get("productDiscount").toString())
            purchaseOrder.receivedDate = sdf.parse(jsonObject.get("receivedDate").toString())
            purchaseOrder.receivedBy = jsonObject.get("receivedBy").toString()
            purchaseOrder.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
            purchaseOrder.expectedDeliveryDate = sdf.parse(jsonObject.get("expectedDeliveryDate").toString())
            purchaseOrder.adjustedAmount = Double.parseDouble(jsonObject.get("adjustedAmount").toString())
            purchaseOrder.creditId = jsonObject.get("creditId").toString()
            purchaseOrder.debitId = jsonObject.get("debitId").toString()
            purchaseOrder.crDbAmount = Double.parseDouble(jsonObject.get("crDbAmount").toString())
            purchaseOrder.payableAmount = Double.parseDouble(jsonObject.get("payableAmount").toString())
            purchaseOrder.save(flush: true)
            if (!purchaseOrder.hasErrors())
                return purchaseOrder
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseOrder purchaseOrder = PurchaseOrder.findById(Long.parseLong(id))
            if (purchaseOrder) {
                purchaseOrder.isUpdatable = true
                purchaseOrder.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def cancelPurchaseOrder(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        PurchaseBillDetail purchaseBillDetails = PurchaseBillDetail.findById(Long.parseLong(id))
        if (purchaseBillDetails)
        {
            if (purchaseBillDetails.financialYear.equalsIgnoreCase(financialYear) && purchaseBillDetails.entityId == Long.parseLong(entityId))
            {
                ArrayList<PurchaseProductDetail> purchaseProductDetails = PurchaseProductDetail.findAllByBillId(purchaseBillDetails.id)
                for (PurchaseProductDetail purchaseProductDetail : purchaseProductDetails)
                {
                    purchaseProductDetail.status = 0
                    purchaseProductDetail.isUpdatable = true
                    purchaseProductDetail.save(flush: true)
                }
                purchaseBillDetails.billStatus = "CANCELLED"
                purchaseBillDetails.cancelledDate = new Date()
                purchaseBillDetails.isUpdatable = true
                purchaseBillDetails.save(flush: true)
                saleInvoice.put("products", purchaseProductDetails)
                saleInvoice.put("invoice", purchaseBillDetails)
                return saleInvoice
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    JSONObject getRecentByFinancialYearAndEntity(String financialYear, String entityId, billStatus)
    {
        JSONObject jsonObject = new JSONObject()
        ArrayList<PurchaseBillDetail> purchaseBillDetails =
                PurchaseBillDetail.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(purchaseBillDetails.serBillId)
        jsonObject.put("serBillId", purchaseBillDetails.serBillId.max())
        jsonObject.put("finId", purchaseBillDetails.finId.max())
        return jsonObject
    }

}
