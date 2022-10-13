package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class PurchaseReturnService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return PurchaseReturn.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return PurchaseReturn.findAllByRefId("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    PurchaseReturn get(String id)
    {
        return PurchaseReturn.findById(Long.parseLong(id))
    }

    PurchaseReturn save(JSONObject jsonObject)
    {
        PurchaseReturn purchaseReturn = new PurchaseReturn()
        purchaseReturn.finId =  Long.parseLong(jsonObject.get("finId").toString())
        purchaseReturn.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseReturn.series = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseReturn.type = jsonObject.get("type").toString()
        purchaseReturn.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseReturn.nonTaxable = 0
        purchaseReturn.returnStatus = jsonObject.get("returnStatus").toString()
        purchaseReturn.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        purchaseReturn.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        purchaseReturn.refId = jsonObject.get("refId").toString()
        purchaseReturn.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
        purchaseReturn.supplierContact = jsonObject.get("supplierContact").toString()
        purchaseReturn.supplierEmail = jsonObject.get("supplierEmail").toString()
        purchaseReturn.gross = Double.parseDouble(jsonObject.get("gross").toString())
        purchaseReturn.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        purchaseReturn.refNo = jsonObject.get('refNo').toString()
        purchaseReturn.refDate = sdf1.parse(jsonObject.get('refDate').toString()).clearTime()
        purchaseReturn.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        purchaseReturn.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        purchaseReturn.totalSgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        purchaseReturn.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        purchaseReturn.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        purchaseReturn.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        purchaseReturn.items = Integer.parseInt(jsonObject.get("items").toString())
        purchaseReturn.quantity = Integer.parseInt(jsonObject.get("quantity").toString())
        purchaseReturn.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        purchaseReturn.balance = Double.parseDouble(jsonObject.get("balance").toString())
        purchaseReturn.crdAdjAmount = Double.parseDouble(jsonObject.get("crdAdjAmount").toString())
        purchaseReturn.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        purchaseReturn.adjAmount = 0
        purchaseReturn.ignorePurchase = 0
        purchaseReturn.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseReturn.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        purchaseReturn.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
        purchaseReturn.uuid = jsonObject.get("uuid").toString()
        purchaseReturn.message = jsonObject.get("message").toString()
        purchaseReturn.financialYear = jsonObject.get("financialYear").toString()
        purchaseReturn.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseReturn.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseReturn.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        purchaseReturn.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        purchaseReturn.save(flush: true)
        if (!purchaseReturn.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(purchaseReturn.entryDate)
            println(cal)
            println(Calendar.MONTH)
            String month = cal.get(Calendar.MONTH) + 1;
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            PurchaseReturn purchaseReturn1
            invoiceNumber = purchaseReturn1.entityId + "PR" + month + year + seriesCode + purchaseReturn1.serBillId
            println("Invoice Number generated: " + invoiceNumber)
            if (invoiceNumber)
            {
                purchaseReturn1.invoiceNumber = invoiceNumber
                purchaseReturn1.isUpdatable = true
                purchaseReturn1.save(flush: true)
            }
            return purchaseReturn1
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"1")
    }

    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"0")
    }

    def getAllByCustomerId(String supplierId, String entityId, String financialYear, String returnStatus = null)
    {
        if(returnStatus)
            return PurchaseReturn.findAllBySupplierIdAndEntityIdAndFinancialYearAndReturnStatus(Long.parseLong(supplierId),
                    Long.parseLong(entityId), financialYear, returnStatus)
        else
            return PurchaseReturn.findAllBySupplierIdAndEntityIdAndFinancialYear(Long.parseLong(supplierId),Long.parseLong(entityId),
                    financialYear)
    }


    JSONObject getRecentByFinancialYearAndEntity(String financialYear, String entityId)
    {

        JSONObject jsonObject = new JSONObject()
        ArrayList<PurchaseReturn> saleReturn =
                PurchaseReturn.findAllByFinancialYearAndEntityId(financialYear, Long.parseLong(entityId), [sort: 'id', order:
                        'desc'])
        jsonObject.put("serBillId", saleReturn.serBillId.max())
        jsonObject.put("finId", saleReturn.finId.max())
        return jsonObject
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")
        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "financialYear"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def saleReturnCriteria = PurchaseReturn.createCriteria()
        def purchaseReturnArrayList = saleReturnCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('financialYear', '%' + searchTerm + '%')
                    ilike('invoiceNumber', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = purchaseReturnArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseReturnArrayList)
        return jsonObject
    }


    def cancelPurchaseRetruns(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        PurchaseReturn purchaseReturn = PurchaseReturn.findById(Long.parseLong(id))
        if (purchaseReturn)
        {
            if (purchaseReturn.financialYear.equalsIgnoreCase(financialYear) && purchaseReturn.entityId == Long.parseLong(entityId))
            {
                ArrayList<PurchaseReturnDetail> saleReturnDetails = PurchaseReturnDetail.findAllByBillId(purchaseReturn.id)
                for (PurchaseReturnDetail purchaseReturnDetail : saleReturnDetails)
                {
                    purchaseReturn.syncStatus = 0
                    purchaseReturn.returnStatus = "CANCELLED"
                    purchaseReturn.isUpdatable = true
                    purchaseReturn.save(flush: true)
                }
                purchaseReturn.returnStatus = "CANCELLED"
                purchaseReturn.cancelledDate = new Date()
                purchaseReturn.isUpdatable = true
                purchaseReturn.save(flush: true)

                saleInvoice.put("products", saleReturnDetails)
                saleInvoice.put("invoice", purchaseReturn)
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
