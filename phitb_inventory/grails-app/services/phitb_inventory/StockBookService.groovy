package phitb_inventory

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class StockBookService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy")
//    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return StockBook.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return StockBook.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    StockBook get(String id) {
        StockBook stockBook = StockBook.findById(Long.parseLong(id))
        //check if exists in tempstock before responding
        ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumber(stockBook.productId, stockBook.batchNumber)
        if (tempStockBooks.size() > 0) {
            stockBook.remainingQty = tempStockBooks.remainingQty.sort().get(0)
            stockBook.remainingFreeQty = tempStockBooks.remainingFreeQty.sort().get(0)
            stockBook.remainingReplQty = tempStockBooks.remainingReplQty.sort().get(0)
        }
        return stockBook
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return StockBook.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return StockBook.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByProduct(long limit, long offset, long productId) {
        Date currentDate = new Date()
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        return StockBook.findAllByProductIdAndExpDateGreaterThanEquals(productId, currentDate, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByProductSaleReturn(long limit, long offset, long productId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        return StockBook.findAllByProductId(productId)
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
                orderColumn = "batchNumber"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def stockBookMasterCriteria = StockBook.createCriteria()
        def stockBookMasterArrayList = stockBookMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = stockBookMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", stockBookMasterArrayList)
        return jsonObject
    }

    StockBook save(JSONObject jsonObject) {

        long entityId = Long.parseLong(jsonObject.get("entityId").toString())
        long productId = Long.parseLong(jsonObject.get("productId").toString())
        String batchNumber = jsonObject.get("batchNumber")

        //check if exists
        StockBook stockBook = StockBook.findByEntityIdAndProductIdAndBatchNumber(entityId, productId, batchNumber)
        if (stockBook == null) {
            stockBook = new StockBook()
        }

        //Date sanitize
        String manufacturingDate = jsonObject.get("manufacturingDate")
        String expDate = jsonObject.get("expDate")
        String purcDate = jsonObject.get("purcDate")
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        if (manufacturingDate.contains("T")) {
            manufacturingDate = sdf.format(sdf1.parse(manufacturingDate))
        }
        if (expDate.contains("T")) {
            expDate = sdf.format(sdf1.parse(expDate))
        }
        if (purcDate.contains("T")) {
            purcDate = sdf.format(sdf1.parse(purcDate))
        }
        jsonObject.put("manufacturingDate", manufacturingDate)
        jsonObject.put("expDate", expDate)
        jsonObject.put("purcDate", purcDate)

        double saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
        long remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
        long remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())
        stockBook.batchNumber = batchNumber
        stockBook.mergedWith = jsonObject.get("mergedWith")
        stockBook.packingDesc = jsonObject.get("packingDesc")
        stockBook.productId = productId
        stockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
        stockBook.purcDate = sdf.parse(jsonObject.get("purcDate").toString())
        stockBook.manufacturingDate = sdf.parse(jsonObject.get("manufacturingDate").toString())
        stockBook.remainingQty = remainingQty
        stockBook.purcProductValue = Double.parseDouble(jsonObject.get("purcProductValue").toString())
        stockBook.purcTradeDiscount = Double.parseDouble(jsonObject.get("purcTradeDiscount").toString())
        stockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
        stockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        stockBook.purcSeriesId = Long.parseLong(jsonObject.get("purcSeriesId").toString())
        stockBook.remainingFreeQty = remainingFreeQty
        stockBook.remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
        stockBook.saleRate = saleRate
        stockBook.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
        stockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
        stockBook.status = Long.parseLong(jsonObject.get("status").toString())
        stockBook.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        stockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        stockBook.entityId = entityId
        stockBook.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        stockBook.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        stockBook.openingStockQty = Long.parseLong(jsonObject.get("openingStockQty").toString())
        stockBook.uuid = jsonObject.get("uuid")
        stockBook.save(flush: true)
        if (!stockBook.hasErrors()) {
            StockActivity stockActivity = new StockActivity()
            stockActivity.productId = productId
            stockActivity.batch = batchNumber
            stockActivity.remainingQty = remainingQty
            stockActivity.remainingSchemeQty = remainingFreeQty
            stockActivity.prevRemQty = 0
            stockActivity.prevSchemeQty = 0
            stockActivity.saleRate = saleRate
            stockActivity.prevSaleRate = 0
            stockActivity.status = Long.parseLong(jsonObject.get("status").toString())
            stockActivity.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            stockActivity.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            stockActivity.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            stockActivity.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            stockActivity.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            stockActivity.save(flush: true)

            return stockBook
        } else
            throw new BadRequestException()


    }

    StockBook update(JSONObject jsonObject, String id) {

        if (id) {
            //Date sanitize
            String manufacturingDate = jsonObject.get("manufacturingDate")
            String expDate = jsonObject.get("expDate")
            String purcDate = jsonObject.get("purcDate")
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            if (manufacturingDate.contains("T")) {
                manufacturingDate = sdf.format(sdf1.parse(manufacturingDate))
            }
            if (expDate.contains("T")) {
                expDate = sdf.format(sdf1.parse(expDate))
            }
            if (purcDate.contains("T")) {
                purcDate = sdf.format(sdf1.parse(purcDate))
            }
            jsonObject.put("manufacturingDate", manufacturingDate)
            jsonObject.put("expDate", expDate)
            jsonObject.put("purcDate", purcDate)

            long productId = Long.parseLong(jsonObject.get("productId").toString())
            String batchNumber = jsonObject.get("batchNumber")
            double saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
            long remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
            long remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())

            StockBook stockBook = StockBook.findById(Long.parseLong(id))
            if (stockBook) {
                double prvSaleRate = stockBook.saleRate
                long prvRemainingQty = stockBook.remainingQty
                long prvRemainingFreeQty = stockBook.remainingFreeQty
                stockBook.isUpdatable = true
                stockBook.batchNumber = jsonObject.get("batchNumber")
                stockBook.mergedWith = jsonObject.get("mergedWith") //TODO:to be checked
                stockBook.packingDesc = jsonObject.get("packingDesc")
                stockBook.productId = Long.parseLong(jsonObject.get("productId").toString())
                stockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
                stockBook.purcDate = sdf.parse(jsonObject.get("purcDate").toString())
                stockBook.manufacturingDate = sdf.parse(jsonObject.get("manufacturingDate").toString())
                stockBook.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
                stockBook.purcProductValue = Double.parseDouble(jsonObject.get("purcProductValue").toString())
                stockBook.purcTradeDiscount = Double.parseDouble(jsonObject.get("purcTradeDiscount").toString())
                stockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
                stockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
                stockBook.purcSeriesId = Long.parseLong(jsonObject.get("purcSeriesId").toString())
                stockBook.remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())
                stockBook.remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
                stockBook.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                stockBook.supplierId = Long.parseLong(jsonObject.get("supplierId").toString())
                stockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
                stockBook.status = Long.parseLong(jsonObject.get("status").toString())
                stockBook.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                stockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                stockBook.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                stockBook.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                stockBook.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                stockBook.openingStockQty = Long.parseLong(jsonObject.get("openingStockQty").toString())
                stockBook.uuid = jsonObject.get("uuid")
                stockBook.save(flush: true)
                if (!stockBook.hasErrors()) {
                    StockActivity stockActivity = new StockActivity()
                    stockActivity.productId = productId
                    stockActivity.batch = batchNumber
                    stockActivity.remainingQty = remainingQty
                    stockActivity.remainingSchemeQty = remainingFreeQty
                    stockActivity.prevRemQty = prvRemainingQty
                    stockActivity.prevSchemeQty = prvRemainingFreeQty
                    stockActivity.saleRate = saleRate
                    stockActivity.prevSaleRate = prvSaleRate
                    stockActivity.status = Long.parseLong(jsonObject.get("status").toString())
                    stockActivity.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                    stockActivity.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                    stockActivity.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                    stockActivity.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                    stockActivity.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                    stockActivity.save(flush: true)
                    return stockBook
                } else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            StockBook stockBook = StockBook.findById(Long.parseLong(id))
            if (stockBook) {
                stockBook.isUpdatable = true
                stockBook.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    /*
    get stock entry by products or (product and batch)
     */

    def getByProductAndBatch(long productId, String batch, long entityId) {
        StockBook stockBook = StockBook.findByProductIdAndBatchNumberAndEntityId(productId, batch, entityId)
        //check if exists in tempstock before responding
        ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumber(productId, batch)
        if (tempStockBooks.size() > 0) {
            stockBook.remainingQty = tempStockBooks.remainingQty.sort().get(0)
            stockBook.remainingFreeQty = tempStockBooks.remainingFreeQty.sort().get(0)
            stockBook.remainingReplQty = tempStockBooks.remainingReplQty.sort().get(0)
        }
        return stockBook
    }


    def getByProduct(long productId, long entityId)
    {
        StockBook stockBook = StockBook.findByProductIdAndEntityId(productId, entityId)
        return stockBook
    }
}
