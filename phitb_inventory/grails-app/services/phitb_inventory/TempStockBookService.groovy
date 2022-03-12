package phitb_inventory

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TempStockBookService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-DD")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return TempStockBook.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TempStockBook.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return TempStockBook.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TempStockBook.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByProductAndBatch(long productId, String batch) {
        ArrayList<TempStockBook> tempStockBooks = new ArrayList<>()
        if(batch == null)
        {
           tempStockBooks = TempStockBook.findAllByProductId(productId)
        }
        else
        {
            tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumber(productId,batch)
        }

        if(tempStockBooks?.size() == 0)
        {
            ArrayList<StockBook> stockBooks = StockBook.findAllByProductId(productId)
            return stockBooks
        }
        else
        {
            return tempStockBooks
        }
    }


    TempStockBook get(String id) {
        return TempStockBook.findById(Long.parseLong(id))
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
                orderColumn = "batchNumber"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def tempStockBookMasterCriteria = TempStockBook.createCriteria()
        def tempStockBookMasterArrayList = tempStockBookMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = tempStockBookMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", tempStockBookMasterArrayList)
        return jsonObject
    }

//    TempStockBook save(JSONObject jsonObject) {
//        TempStockBook tempStockBook = new TempStockBook()
//        tempStockBook.batchNumber = jsonObject.get("batchNumber")
//        tempStockBook.packingDesc = jsonObject.get("packingDesc")
//        tempStockBook.productId = Long.parseLong(jsonObject.get("productId").toString())
//        tempStockBook.userId = Long.parseLong(jsonObject.get("userId").toString())
//        tempStockBook.userOrderQty = Long.parseLong(jsonObject.get("userOrderQty").toString())
//        tempStockBook.userOrderFreeQty = Long.parseLong(jsonObject.get("userOrderFreeQty").toString())
//        tempStockBook.userOrderReplQty = Long.parseLong(jsonObject.get("userOrderReplQty").toString())
//        tempStockBook.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
//        tempStockBook.remainingSchemeQty = Long.parseLong(jsonObject.get("remainingSchemeQty").toString())
//        tempStockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
//        tempStockBook.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
//        tempStockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
//        tempStockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
//        tempStockBook.remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
//        tempStockBook.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
//        tempStockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
//        tempStockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
//        tempStockBook.entityId = Long.parseLong(jsonObject.get("entityId").toString())
//        tempStockBook.originalId = Long.parseLong(jsonObject.get("originalId").toString())
//        tempStockBook.save(flush: true)
//        if (!tempStockBook.hasErrors())
//            return tempStockBook
//        else
//            throw new BadRequestException()
//    }


    TempStockBook save(JSONObject jsonObject) {
        TempStockBook tempStockBook = new TempStockBook()
        tempStockBook.batchNumber = jsonObject.get("json[Batch]")
        tempStockBook.packingDesc = jsonObject.get("json[Pack]")
        tempStockBook.productId = Long.parseLong(jsonObject.get("json[Product]").toString())
        tempStockBook.userId = Long.parseLong("1")
        tempStockBook.userOrderQty = Long.parseLong(jsonObject.get("json[PurchaseQty]").toString())
        tempStockBook.userOrderFreeQty = Long.parseLong(jsonObject.get("json[FreeQty]").toString())
        tempStockBook.userOrderReplQty = Long.parseLong("1")
        tempStockBook.redundantBatch = Long.parseLong("1")
        tempStockBook.remainingSchemeQty = Long.parseLong("1")
        tempStockBook.expDate = sdf.parse(jsonObject.get("json[ExpDt]").toString())
        tempStockBook.remainingQty = Long.parseLong("1")
        tempStockBook.purchaseRate = Double.parseDouble(jsonObject.get("json[PurchaseRate]").toString())
        tempStockBook.mrp = Double.parseDouble(jsonObject.get("json[MRP]").toString())
        tempStockBook.remainingReplQty = Long.parseLong("1")
        tempStockBook.saleRate = Double.parseDouble("1")
        tempStockBook.taxId = Long.parseLong("1")
        tempStockBook.entityTypeId = Long.parseLong("1")
        tempStockBook.entityId = Long.parseLong("1")
        tempStockBook.originalId = Long.parseLong("1")
        tempStockBook.save(flush: true)
        if (!tempStockBook.hasErrors())
            return tempStockBook
        else
            throw new BadRequestException()
    }

    TempStockBook update(JSONObject jsonObject, String id) {

        if (id) {
            TempStockBook tempStockBook = TempStockBook.findById(Long.parseLong(id))
            if (tempStockBook) {
                tempStockBook.isUpdatable = true
                tempStockBook.batchNumber = jsonObject.get("batchNumber")
                tempStockBook.packingDesc = jsonObject.get("packingDesc")
                tempStockBook.productId = Long.parseLong(jsonObject.get("productId").toString())
                tempStockBook.userId = Long.parseLong(jsonObject.get("userId").toString())
                tempStockBook.userOrderQty = Long.parseLong(jsonObject.get("userOrderQty").toString())
                tempStockBook.userOrderFreeQty = Long.parseLong(jsonObject.get("userOrderFreeQty").toString())
                tempStockBook.userOrderReplQty = Long.parseLong(jsonObject.get("userOrderReplQty").toString())
                tempStockBook.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
                tempStockBook.remainingSchemeQty = Long.parseLong(jsonObject.get("remainingSchemeQty").toString())
                tempStockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
                tempStockBook.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
                tempStockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
                tempStockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
                tempStockBook.remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
                tempStockBook.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                tempStockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
                tempStockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                tempStockBook.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                tempStockBook.originalId = Long.parseLong(jsonObject.get("originalId").toString())
                tempStockBook.save(flush: true)
                if (!tempStockBook.hasErrors())
                    return tempStockBook
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
            TempStockBook tempStockBook = TempStockBook.findById(Long.parseLong(id))
            if (tempStockBook) {
                tempStockBook.isUpdatable = true
                tempStockBook.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
