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
        purchaseOrder.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseOrder.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        purchaseOrder.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseOrder.maxLimit = Double.parseDouble(jsonObject.get("maxLimit").toString())
        purchaseOrder.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        purchaseOrder.remarks = jsonObject.get("remarks").toString()
        purchaseOrder.totalSqty = Long.parseLong(jsonObject.get("totalSqty").toString())
        purchaseOrder.totalFqty = Long.parseLong(jsonObject.get("totalFqty").toString())
        purchaseOrder.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        purchaseOrder.taxableAmount = Double.parseDouble(jsonObject.get("taxableAmount").toString())
        purchaseOrder.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        purchaseOrder.discount = Double.parseDouble(jsonObject.get("discount").toString())
        purchaseOrder.estimatedTotalValue = Double.parseDouble(jsonObject.get("estimatedTotalValue").toString())
        purchaseOrder.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
        purchaseOrder.orderStatus = jsonObject.get("orderStatus").toString()
        purchaseOrder.supplierSaleOrderId = jsonObject.get("supplierSaleOrderId").toString()
        purchaseOrder.financialYear = jsonObject.get("financialYear").toString()
        purchaseOrder.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseOrder.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseOrder.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        purchaseOrder.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
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
            purchaseOrder.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseOrder.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            purchaseOrder.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
            purchaseOrder.maxLimit = Double.parseDouble(jsonObject.get("maxLimit").toString())
            purchaseOrder.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            purchaseOrder.remarks = jsonObject.get("remarks").toString()
            purchaseOrder.totalSqty = Long.parseLong(jsonObject.get("totalSqty").toString())
            purchaseOrder.totalFqty = Long.parseLong(jsonObject.get("totalFqty").toString())
            purchaseOrder.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
            purchaseOrder.taxableAmount = Double.parseDouble(jsonObject.get("taxableAmount").toString())
            purchaseOrder.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
            purchaseOrder.discount = Double.parseDouble(jsonObject.get("discount").toString())
            purchaseOrder.estimatedTotalValue = Double.parseDouble(jsonObject.get("estimatedTotalValue").toString())
            purchaseOrder.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
            purchaseOrder.orderStatus = jsonObject.get("orderStatus").toString()
            purchaseOrder.supplierSaleOrderId = jsonObject.get("supplierSaleOrderId").toString()
            purchaseOrder.financialYear = jsonObject.get("financialYear").toString()
            purchaseOrder.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseOrder.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseOrder.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            purchaseOrder.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
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
}
