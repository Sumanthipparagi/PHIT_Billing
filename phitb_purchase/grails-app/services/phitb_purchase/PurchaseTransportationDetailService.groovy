package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PurchaseTransportationDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PurchaseTransportationDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PurchaseTransportationDetail.findAllByLrNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    PurchaseTransportationDetail get(String id) {
        return PurchaseTransportationDetail.findById(Long.parseLong(id))
    }

    PurchaseTransportationDetail getbyBillId(String billId)
    {
        return PurchaseTransportationDetail.findByBillId(Long.parseLong(billId))
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

        def purchaseTransportationDetailCriteria = PurchaseTransportationDetail.createCriteria()
        def purchaseTransportationDetailArrayList = purchaseTransportationDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('lrNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = purchaseTransportationDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", purchaseTransportationDetailArrayList)
        return jsonObject
    }

    PurchaseTransportationDetail save(JSONObject jsonObject) {
        PurchaseTransportationDetail purchaseTransportationDetail = new PurchaseTransportationDetail()
        purchaseTransportationDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        purchaseTransportationDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
        purchaseTransportationDetail.billType = jsonObject.get("billType").toString()
        purchaseTransportationDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        purchaseTransportationDetail.series = Long.parseLong(jsonObject.get("series").toString())
        purchaseTransportationDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        purchaseTransportationDetail.transporterId = Long.parseLong(jsonObject.get("transporterId").toString())
        purchaseTransportationDetail.lrDate = sdf.parse(jsonObject.get("lrDate").toString())
        purchaseTransportationDetail.lrNumber = jsonObject.get("lrNumber").toString()
        purchaseTransportationDetail.cartonsCount = jsonObject.get("cartonsCount").toString()
        purchaseTransportationDetail.paid = jsonObject.get("paid").toString()
        purchaseTransportationDetail.toPay = jsonObject.get("toPay").toString()
        purchaseTransportationDetail.generalInfo = jsonObject.get("generalInfo").toString()
        purchaseTransportationDetail.selfNo = jsonObject.get("selfNo").toString()
        purchaseTransportationDetail.ccm = jsonObject.get("ccm").toString()
        purchaseTransportationDetail.receivedTemperature = jsonObject.get("receivedTemperature").toString()
        purchaseTransportationDetail.freightCharge = jsonObject.get("freightCharge").toString()
        purchaseTransportationDetail.vehicleId = Long.parseLong(jsonObject.get("vehicleId").toString())
        purchaseTransportationDetail.weight = jsonObject.get("weight").toString()
        purchaseTransportationDetail.deliveryStatus = jsonObject.get("deliveryStatus").toString()
        purchaseTransportationDetail.dispatchDateTime = jsonObject.get("dispatchDateTime").toString()
        purchaseTransportationDetail.deliveryDateTime = jsonObject.get("deliveryDateTime").toString()
        purchaseTransportationDetail.trackingDetails = jsonObject.get("trackingDetails").toString()
        purchaseTransportationDetail.ewaybillId = jsonObject.get("ewaybillId").toString()
        purchaseTransportationDetail.ewaysupplytype = jsonObject.get("ewaysupplytype").toString()
        purchaseTransportationDetail.ewaysupplysubtype = jsonObject.get("ewaysupplysubtype").toString()
        purchaseTransportationDetail.ewaydoctype = jsonObject.get("ewaydoctype").toString()
        purchaseTransportationDetail.consignmentNo = jsonObject.get("consignmentNo").toString()
        purchaseTransportationDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        purchaseTransportationDetail.financialYear = jsonObject.get("financialYear").toString()
        purchaseTransportationDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        purchaseTransportationDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        purchaseTransportationDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        purchaseTransportationDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        purchaseTransportationDetail.save(flush: true)
        if (!purchaseTransportationDetail.hasErrors())
            return purchaseTransportationDetail
        else
            throw new BadRequestException()

    }

    PurchaseTransportationDetail update(JSONObject jsonObject, String id) {

        PurchaseTransportationDetail purchaseTransportationDetail = PurchaseTransportationDetail.findById(Long.parseLong(id))
        if (purchaseTransportationDetail) {
            purchaseTransportationDetail.isUpdatable = true
            purchaseTransportationDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            purchaseTransportationDetail.billId = Long.parseLong(jsonObject.get("billId").toString())
            purchaseTransportationDetail.billType = jsonObject.get("billType").toString()
            purchaseTransportationDetail.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            purchaseTransportationDetail.series = Long.parseLong(jsonObject.get("series").toString())
            purchaseTransportationDetail.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
            purchaseTransportationDetail.transporterId = Long.parseLong(jsonObject.get("transportTypeId").toString())
            purchaseTransportationDetail.lrDate = sdf.parse(jsonObject.get("lrDate").toString())
            purchaseTransportationDetail.lrNumber = jsonObject.get("lrNumber").toString()
            purchaseTransportationDetail.cartonsCount = jsonObject.get("cartonsCount").toString()
            purchaseTransportationDetail.paid = jsonObject.get("paid").toString()
            purchaseTransportationDetail.toPay = jsonObject.get("toPay").toString()
            purchaseTransportationDetail.generalInfo = jsonObject.get("generalInfo").toString()
            purchaseTransportationDetail.selfNo = jsonObject.get("selfNo").toString()
            purchaseTransportationDetail.ccm = jsonObject.get("ccm").toString()
            purchaseTransportationDetail.receivedTemperature = jsonObject.get("receivedTemperature").toString()
            purchaseTransportationDetail.freightCharge = jsonObject.get("freightCharge").toString()
            purchaseTransportationDetail.vehicleId = Long.parseLong(jsonObject.get("vehicleId").toString())
            purchaseTransportationDetail.weight = jsonObject.get("weight").toString()
            purchaseTransportationDetail.deliveryStatus = jsonObject.get("deliveryStatus").toString()
            purchaseTransportationDetail.dispatchDateTime = jsonObject.get("dispatchDateTime").toString()
            purchaseTransportationDetail.deliveryDateTime = jsonObject.get("deliveryDateTime").toString()
            purchaseTransportationDetail.trackingDetails = jsonObject.get("trackingDetails").toString()
            purchaseTransportationDetail.ewaybillId = jsonObject.get("ewaybillId").toString()
            purchaseTransportationDetail.ewaysupplytype = jsonObject.get("ewaysupplytype").toString()
            purchaseTransportationDetail.ewaysupplysubtype = jsonObject.get("ewaysupplysubtype").toString()
            purchaseTransportationDetail.ewaydoctype = jsonObject.get("ewaydoctype").toString()
            purchaseTransportationDetail.consignmentNo = jsonObject.get("consignmentNo").toString()
            purchaseTransportationDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            purchaseTransportationDetail.financialYear = jsonObject.get("financialYear").toString()
            purchaseTransportationDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            purchaseTransportationDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            purchaseTransportationDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            purchaseTransportationDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())

            purchaseTransportationDetail.save(flush: true)
            if (!purchaseTransportationDetail.hasErrors())
                return purchaseTransportationDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PurchaseTransportationDetail purchaseTransportationDetail = PurchaseTransportationDetail.findById(Long.parseLong(id))
            if (purchaseTransportationDetail) {
                purchaseTransportationDetail.isUpdatable = true
                purchaseTransportationDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
