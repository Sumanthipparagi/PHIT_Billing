package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseReturnDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseReturnDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseReturnDetail.findAllByMessageIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return PurchaseReturnDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date entryDate = cal.getTime()
            return PurchaseReturnDetail.createCriteria().list {
                gt("entryDate",entryDate)
            }
        }
    }

    PurchaseReturnDetail get(String id) {
        return PurchaseReturnDetail.findById(Long.parseLong(id))
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

        def purchaseReturnDetailCriteria = PurchaseReturnDetail.createCriteria()
        def purchaseReturnDetailArrayList = purchaseReturnDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('serBillId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseReturnDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseReturnDetailArrayList)
        return jsonObject
    }

    PurchaseReturnDetail save(JSONObject jsonObject) {
        PurchaseReturnDetail purchaseReturnDetail = new PurchaseReturnDetail()
        purchaseReturnDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseReturnDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseReturnDetail.series = Long.parseLong(jsonObject.get("series").toString())
        purchaseReturnDetail.type = jsonObject.get("type").toString()
        purchaseReturnDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseReturnDetail.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        purchaseReturnDetail.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        purchaseReturnDetail.refId = jsonObject.get("refId").toString()
        purchaseReturnDetail.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
        purchaseReturnDetail.supplierContact = jsonObject.get("supplierContact").toString()
        purchaseReturnDetail.supplierEmail = jsonObject.get("supplierEmail").toString()
        purchaseReturnDetail.gross = Double.parseDouble(jsonObject.get("gross").toString())
        purchaseReturnDetail.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        purchaseReturnDetail.nonTaxable = Double.parseDouble(jsonObject.get("nonTaxable").toString())
        purchaseReturnDetail.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        purchaseReturnDetail.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        purchaseReturnDetail.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        purchaseReturnDetail.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        purchaseReturnDetail.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        purchaseReturnDetail.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        purchaseReturnDetail.items = Long.parseLong(jsonObject.get("items").toString())
        purchaseReturnDetail.quantity = Long.parseLong(jsonObject.get("quantity").toString())
        purchaseReturnDetail.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        purchaseReturnDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
        purchaseReturnDetail.crdAdjAmount = Double.parseDouble(jsonObject.get("crdAdjAmount").toString())
        purchaseReturnDetail.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        purchaseReturnDetail.creditIds = jsonObject.get("creditIds").toString()
        purchaseReturnDetail.billStatus = jsonObject.get("billStatus").toString()
        purchaseReturnDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseReturnDetail.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        purchaseReturnDetail.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
        purchaseReturnDetail.message = jsonObject.get("message").toString()
        purchaseReturnDetail.ignorePurchase = Long.parseLong(jsonObject.get("ignorePurchase").toString())
        purchaseReturnDetail.financialYear = jsonObject.get("financialYear").toString()
        purchaseReturnDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseReturnDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseReturnDetail.created_user = Long.parseLong(jsonObject.get("created_user").toString())
        purchaseReturnDetail.modified_user = Long.parseLong(jsonObject.get("modified_user").toString())
        purchaseReturnDetail.save(flush: true)
        if (!purchaseReturnDetail.hasErrors())
            return purchaseReturnDetail
        else
            throw new BadRequestException()

    }

    PurchaseReturnDetail update(JSONObject jsonObject, String id) {

        PurchaseReturnDetail purchaseReturnDetail = PurchaseReturnDetail.findById(Long.parseLong(id))
        if (purchaseReturnDetail) {
            purchaseReturnDetail.isUpdatable = true
            purchaseReturnDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseReturnDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseReturnDetail.series = Long.parseLong(jsonObject.get("series").toString())
            purchaseReturnDetail.type = jsonObject.get("type").toString()
            purchaseReturnDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
            purchaseReturnDetail.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
            purchaseReturnDetail.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            purchaseReturnDetail.refId = jsonObject.get("refId").toString()
            purchaseReturnDetail.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
            purchaseReturnDetail.supplierContact = jsonObject.get("supplierContact").toString()
            purchaseReturnDetail.supplierEmail = jsonObject.get("supplierEmail").toString()
            purchaseReturnDetail.gross = Double.parseDouble(jsonObject.get("gross").toString())
            purchaseReturnDetail.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
            purchaseReturnDetail.nonTaxable = Double.parseDouble(jsonObject.get("nonTaxable").toString())
            purchaseReturnDetail.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
            purchaseReturnDetail.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
            purchaseReturnDetail.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
            purchaseReturnDetail.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
            purchaseReturnDetail.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
            purchaseReturnDetail.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
            purchaseReturnDetail.items = Long.parseLong(jsonObject.get("items").toString())
            purchaseReturnDetail.quantity = Long.parseLong(jsonObject.get("quantity").toString())
            purchaseReturnDetail.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
            purchaseReturnDetail.balance = Double.parseDouble(jsonObject.get("balance").toString())
            purchaseReturnDetail.crdAdjAmount = Double.parseDouble(jsonObject.get("crdAdjAmount").toString())
            purchaseReturnDetail.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
            purchaseReturnDetail.creditIds = jsonObject.get("creditIds").toString()
            purchaseReturnDetail.billStatus = jsonObject.get("billStatus").toString()
            purchaseReturnDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseReturnDetail.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
            purchaseReturnDetail.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
            purchaseReturnDetail.message = jsonObject.get("message").toString()
            purchaseReturnDetail.ignorePurchase = Long.parseLong(jsonObject.get("ignorePurchase").toString())
            purchaseReturnDetail.financialYear = jsonObject.get("financialYear").toString()
            purchaseReturnDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseReturnDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseReturnDetail.created_user = Long.parseLong(jsonObject.get("created_user").toString())
            purchaseReturnDetail.modified_user = Long.parseLong(jsonObject.get("modified_user").toString())
            purchaseReturnDetail.save(flush: true)
            if (!purchaseReturnDetail.hasErrors())
                return purchaseReturnDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseReturnDetail purchaseReturnDetail = PurchaseReturnDetail.findById(Long.parseLong(id))
            if (purchaseReturnDetail) {
                purchaseReturnDetail.isUpdatable = true
                purchaseReturnDetail.delete()
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
            return PurchaseReturnDetail.findAllBySupplierIdAndFinancialYearAndEntityId(Long.parseLong(id),
                    financialYear,Long.parseLong(entityId))
        }
    }
}
