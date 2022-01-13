package phitb_inventory

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

@Transactional
class StockActivityService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return StockActivity.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return StockActivity.findAllBybatchIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    StockActivity get(String id) {
        return StockActivity.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "batch"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = StockActivity.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batch', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = dayMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", dayMasterArrayList)
        return jsonObject
    }

    StockActivity save(JSONObject jsonObject) {
        StockActivity stockActivity = new StockActivity()
        stockActivity.batch = jsonObject.get("batch")
        stockActivity.productId = Long.parseLong(jsonObject.get("productId").toString())
        stockActivity.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
        stockActivity.remainingSchemeQty = Long.parseLong(jsonObject.get("remainingSchemeQty").toString())
        stockActivity.prevRemQty = Long.parseLong(jsonObject.get("prevRemQty").toString())
        stockActivity.prevSchemeQty = Long.parseLong(jsonObject.get("prevSchemeQty").toString())
        stockActivity.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
        stockActivity.prevSaleRate = Double.parseDouble(jsonObject.get("prevSaleRate").toString())
        stockActivity.status = Long.parseLong(jsonObject.get("status").toString())
        stockActivity.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        stockActivity.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        stockActivity.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        stockActivity.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        stockActivity.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        stockActivity.save(flush: true)
        if (!stockActivity.hasErrors())
            return stockActivity
        else
            throw new BadRequestException()
    }

    StockActivity update(JSONObject jsonObject, String id) {

        if (id) {
            StockActivity stockActivity = StockActivity.findById(Long.parseLong(id))
            if (stockActivity) {
                stockActivity.isUpdatable = true
                stockActivity.batch = jsonObject.get("batch")
                stockActivity.productId = Long.parseLong(jsonObject.get("productId").toString())
                stockActivity.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
                stockActivity.remainingSchemeQty = Long.parseLong(jsonObject.get("remainingSchemeQty").toString())
                stockActivity.prevRemQty = Long.parseLong(jsonObject.get("prevRemQty").toString())
                stockActivity.prevSchemeQty = Long.parseLong(jsonObject.get("prevSchemeQty").toString())
                stockActivity.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                stockActivity.prevSaleRate = Double.parseDouble(jsonObject.get("prevSaleRate").toString())
                stockActivity.status = Long.parseLong(jsonObject.get("status").toString())
                stockActivity.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                stockActivity.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                stockActivity.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                stockActivity.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                stockActivity.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                stockActivity.save(flush: true)
                if (!stockActivity.hasErrors())
                    return stockActivity
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            StockActivity stockActivity = StockActivity.findById(Long.parseLong(id))
            if (stockActivity) {
                stockActivity.isUpdatable = true
                stockActivity.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
