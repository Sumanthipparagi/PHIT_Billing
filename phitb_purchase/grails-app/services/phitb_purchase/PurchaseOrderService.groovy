package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class PurchaseOrderService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

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
        long entityId = paramsJsonObject.get("entityId")

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
            eq('entityId', entityId)
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
        purchaseOrder.gross = Double.parseDouble(jsonObject.get("gross").toString())
        purchaseOrder.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        purchaseOrder.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        purchaseOrder.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        purchaseOrder.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        purchaseOrder.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        purchaseOrder.netAmount = Double.parseDouble(jsonObject.get("netAmount").toString())
        purchaseOrder.godownId = jsonObject.get("godownId").toString()
        purchaseOrder.totalItems = Long.parseLong(jsonObject.get("totalItems").toString())
        purchaseOrder.totalQuantity = Long.parseLong(jsonObject.get("totalQuantity").toString())
        purchaseOrder.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        purchaseOrder.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        purchaseOrder.balAmount = Double.parseDouble(jsonObject.get("balAmount").toString())
        purchaseOrder.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        purchaseOrder.submitStatus = jsonObject.get("submitStatus").toString()
        purchaseOrder.billStatus = jsonObject.get("billStatus").toString()
        purchaseOrder.remarks = "0"
        purchaseOrder.gstStatus = jsonObject.get("gstStatus").toString()
        purchaseOrder.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseOrder.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        purchaseOrder.addAmount = Double.parseDouble(jsonObject.get("addAmount").toString())
        purchaseOrder.lessAmount = Double.parseDouble(jsonObject.get("lessAmount").toString())
        purchaseOrder.financialYear = jsonObject.get("financialYear").toString()
        purchaseOrder.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseOrder.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseOrder.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        purchaseOrder.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        purchaseOrder.uuid = jsonObject.get("uuid").toString()
        purchaseOrder.save(flush: true)
        if (!purchaseOrder.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(purchaseOrder.entryDate)
            String month = cal.get(Calendar.MONTH)+1;
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            PurchaseBillDetail purchaseBillDetail1
            if (purchaseOrder.billStatus == "DRAFT")
            {
                println(purchaseOrder.billStatus)
                purchaseOrder.invoiceNumber = "DRAFT"
            }
            else
            {
                invoiceNumber = purchaseOrder.entityId + "PO" + month + year +  seriesCode + purchaseOrder.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                purchaseOrder.invoiceNumber = invoiceNumber
                purchaseOrder.isUpdatable = true
                purchaseOrder.save(flush: true)
            }
            return purchaseOrder
        }
        else
        {
            throw new BadRequestException()
        }

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
        JSONObject purchaseOrder = new JSONObject()
        PurchaseOrder purchaseOrderDetails = PurchaseOrder.findById(Long.parseLong(id))
        if (purchaseOrderDetails)
        {
            if (purchaseOrderDetails.financialYear.equalsIgnoreCase(financialYear) && purchaseOrderDetails.entityId == Long.parseLong(entityId))
            {
                ArrayList<PurchaseOrderProductDetail> purchaseOrderProductDetails = PurchaseOrderProductDetail.findAllByBillId(purchaseOrderDetails.id)
                for (PurchaseOrderProductDetail purchaseProductDetail : purchaseOrderProductDetails)
                {
                    purchaseProductDetail.status = 0
                    purchaseProductDetail.isUpdatable = true
                    purchaseProductDetail.save(flush: true)
                }
                purchaseOrderDetails.billStatus = "CANCELLED"
                purchaseOrderDetails.cancelledDate = new Date()
                purchaseOrderDetails.isUpdatable = true
                purchaseOrderDetails.save(flush: true)
                purchaseOrder.put("products", purchaseOrderProductDetails)
                purchaseOrder.put("purchaseOrder", purchaseOrderDetails)
                return purchaseOrder
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
        ArrayList<PurchaseOrder> purchaseOrder =
                PurchaseOrder.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(purchaseOrder.serBillId)
        jsonObject.put("serBillId", purchaseOrder.serBillId.max())
        jsonObject.put("finId", purchaseOrder.finId.max())
        return jsonObject
    }

}
