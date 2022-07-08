package phitb_purchase

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class PurchaseBillDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseBillDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseBillDetail.findAllByPurcIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    PurchaseBillDetail get(String id) {
        return PurchaseBillDetail.findById(Long.parseLong(id))
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
                orderColumn = "purcId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def purchaseBillDetailCriteria = PurchaseBillDetail.createCriteria()
        def purchaseBillDetailArrayList = purchaseBillDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('purcId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseBillDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseBillDetailArrayList)
        return jsonObject
    }

    PurchaseBillDetail save(JSONObject jsonObject) {
        PurchaseBillDetail purchaseBillDetail = new PurchaseBillDetail()
        purchaseBillDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseBillDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseBillDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseBillDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseBillDetail.purcId = jsonObject.get("purcId").toString()
        purchaseBillDetail.supplierBillId = jsonObject.get("supplierBillId").toString()
        purchaseBillDetail.supplierBillDate = sdf.parse(jsonObject.get("supplierBillDate").toString())
        purchaseBillDetail.billingDate = sdf.parse(jsonObject.get("billingDate").toString())
        purchaseBillDetail.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        purchaseBillDetail.dispatchStatus = jsonObject.get("dispatchStatus").toString()
        purchaseBillDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        purchaseBillDetail.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        purchaseBillDetail.productDiscount = Double.parseDouble(jsonObject.get("productDiscount").toString())
        purchaseBillDetail.receivedDate = sdf.parse(jsonObject.get("receivedDate").toString())
        purchaseBillDetail.receivedBy = jsonObject.get("receivedBy").toString()
        purchaseBillDetail.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        purchaseBillDetail.expectedDeliveryDate = sdf.parse(jsonObject.get("expectedDeliveryDate").toString())
        purchaseBillDetail.adjustedAmount = Double.parseDouble(jsonObject.get("adjustedAmount").toString())
        purchaseBillDetail.creditId = jsonObject.get("creditId").toString()
        purchaseBillDetail.debitId = jsonObject.get("debitId").toString()
        purchaseBillDetail.crDbAmount = Double.parseDouble(jsonObject.get("crDbAmount").toString())
        purchaseBillDetail.payableAmount = Double.parseDouble(jsonObject.get("payableAmount").toString())
        purchaseBillDetail.gross = Double.parseDouble(jsonObject.get("gross").toString())
        purchaseBillDetail.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        purchaseBillDetail.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        purchaseBillDetail.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        purchaseBillDetail.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        purchaseBillDetail.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        purchaseBillDetail.netAmount = Double.parseDouble(jsonObject.get("netAmount").toString())
        purchaseBillDetail.godownId = jsonObject.get("godownId").toString()
        purchaseBillDetail.totalItems = Long.parseLong(jsonObject.get("totalItems").toString())
        purchaseBillDetail.totalQuantity = Long.parseLong(jsonObject.get("totalQuantity").toString())
        purchaseBillDetail.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        purchaseBillDetail.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        purchaseBillDetail.balAmount = Double.parseDouble(jsonObject.get("balAmount").toString())
        purchaseBillDetail.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        purchaseBillDetail.submitStatus = jsonObject.get("submitStatus").toString()
        purchaseBillDetail.billStatus = jsonObject.get("billStatus").toString()
        purchaseBillDetail.gstStatus = jsonObject.get("gstStatus").toString()
        purchaseBillDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseBillDetail.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        purchaseBillDetail.addAmount = Double.parseDouble(jsonObject.get("addAmount").toString())
        purchaseBillDetail.lessAmount = Double.parseDouble(jsonObject.get("lessAmount").toString())
        purchaseBillDetail.financialYear = jsonObject.get("financialYear").toString()
        purchaseBillDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseBillDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseBillDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        purchaseBillDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        purchaseBillDetail.uuid = jsonObject.get("uuid").toString()
        purchaseBillDetail.save(flush: true)
        if (!purchaseBillDetail.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(purchaseBillDetail.entryDate)
            String month = cal.get(Calendar.MONTH)+1;
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            PurchaseBillDetail purchaseBillDetail1
            if (purchaseBillDetail.billStatus == "DRAFT")
            {
                println(purchaseBillDetail.billStatus)
                purchaseBillDetail.invoiceNumber = "DRAFT"
            }
            else
            {
                invoiceNumber = purchaseBillDetail.entityId + "P" + month + year +  seriesCode + purchaseBillDetail.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                purchaseBillDetail.invoiceNumber = invoiceNumber
                purchaseBillDetail.isUpdatable = true
                purchaseBillDetail.save(flush: true)
            }
            return purchaseBillDetail
        }
        else
        {
            throw new BadRequestException()
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

    PurchaseBillDetail update(JSONObject jsonObject, String id) {
        PurchaseBillDetail purchaseBillDetail = PurchaseBillDetail.findById(Long.parseLong(id))
        if (purchaseBillDetail) {
            purchaseBillDetail.isUpdatable = true
            purchaseBillDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseBillDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
            purchaseBillDetail.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseBillDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseBillDetail.purcId = jsonObject.get("purcId").toString()
            purchaseBillDetail.supplierBillId = jsonObject.get("supplierBillId").toString()
            purchaseBillDetail.supplierBillDate = sdf.parse(jsonObject.get("supplierBillDate").toString())
            purchaseBillDetail.billingDate = sdf.parse(jsonObject.get("billingDate").toString())
            purchaseBillDetail.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            purchaseBillDetail.dispatchStatus = jsonObject.get("dispatchStatus").toString()
            purchaseBillDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
            purchaseBillDetail.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
            purchaseBillDetail.productDiscount = Double.parseDouble(jsonObject.get("productDiscount").toString())
            purchaseBillDetail.receivedDate = sdf.parse(jsonObject.get("receivedDate").toString())
            purchaseBillDetail.receivedBy = jsonObject.get("receivedBy").toString()
            purchaseBillDetail.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
            purchaseBillDetail.expectedDeliveryDate = sdf.parse(jsonObject.get("expectedDeliveryDate").toString())
            purchaseBillDetail.adjustedAmount = Double.parseDouble(jsonObject.get("adjustedAmount").toString())
            purchaseBillDetail.creditId = jsonObject.get("creditId").toString()
            purchaseBillDetail.debitId = jsonObject.get("debitId").toString()
            purchaseBillDetail.crDbAmount = Double.parseDouble(jsonObject.get("crDbAmount").toString())
            purchaseBillDetail.payableAmount = Double.parseDouble(jsonObject.get("payableAmount").toString())
            purchaseBillDetail.gross = Double.parseDouble(jsonObject.get("gross").toString())
            purchaseBillDetail.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
            purchaseBillDetail.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
            purchaseBillDetail.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
            purchaseBillDetail.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
            purchaseBillDetail.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
            purchaseBillDetail.netAmount = Double.parseDouble(jsonObject.get("netAmount").toString())
            purchaseBillDetail.godownId = jsonObject.get("godownId").toString()
            purchaseBillDetail.totalItems = Long.parseLong(jsonObject.get("totalItems").toString())
            purchaseBillDetail.totalQuantity = Long.parseLong(jsonObject.get("totalQuantity").toString())
            purchaseBillDetail.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
            purchaseBillDetail.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
            purchaseBillDetail.balAmount = Double.parseDouble(jsonObject.get("balAmount").toString())
            purchaseBillDetail.submitStatus = jsonObject.get("submitStatus").toString()
            purchaseBillDetail.billStatus = jsonObject.get("billStatus").toString()
            purchaseBillDetail.gstStatus = jsonObject.get("gstStatus").toString()
            purchaseBillDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseBillDetail.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
            purchaseBillDetail.addAmount = Double.parseDouble(jsonObject.get("addAmount").toString())
            purchaseBillDetail.lessAmount = Double.parseDouble(jsonObject.get("lessAmount").toString())
            purchaseBillDetail.financialYear = jsonObject.get("financialYear").toString()
            purchaseBillDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseBillDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseBillDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            purchaseBillDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            purchaseBillDetail.uuid = jsonObject.get("uuid").toString()
            purchaseBillDetail.save(flush: true)
            if (!purchaseBillDetail.hasErrors())
                return purchaseBillDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseBillDetail purchaseBillDetail = PurchaseBillDetail.findById(Long.parseLong(id))
            if (purchaseBillDetail) {
                purchaseBillDetail.isUpdatable = true
                purchaseBillDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def getAllBySupplierId(String id,String financialYear,String entityId)
    {
        if(id)
        {
            return PurchaseBillDetail.findAllBySupplierIdAndFinancialYearAndEntityId(Long.parseLong(id), financialYear,Long.parseLong(entityId))
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
            ArrayList<PurchaseBillDetail> purchaseBillDetails = PurchaseBillDetail.findAllByEntityIdAndDateCreatedBetween(eid,
                    fromDate, toDate)
            for (PurchaseBillDetail purchaseBillDetail : purchaseBillDetails) {
                JSONObject saleBillDetail1 = new JSONObject((purchaseBillDetail as JSON).toString())
                def productDetails = PurchaseProductDetail.findAllByBillId(purchaseBillDetail.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    saleBillDetail1.put("products", prdt)
                }
                finalBills.add(saleBillDetail1)
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

    def cancelPurchaseBill(JSONObject jsonObject)
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
                for (PurchaseProductDetail saleProductDetail : purchaseProductDetails)
                {
                    saleProductDetail.status = 0
                    saleProductDetail.isUpdatable = true
                    saleProductDetail.save(flush: true)
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

}
