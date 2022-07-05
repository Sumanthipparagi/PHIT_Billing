package phitb_inventory

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TempStockBookService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")

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

    def getAllByUserId(long userId) {
        return TempStockBook.findAllByUserId(userId)
    }

    /*
    get all stocks by products or (product and batch)
     */
    def getAllByProductAndBatch(long productId, String batch) {
        Date currentDate = new Date()
        ArrayList<TempStockBook> tempStockBooks = new ArrayList<>()
        if (batch == null) {
            tempStockBooks = TempStockBook.findAllByProductIdAndExpDateGreaterThanEquals(productId, currentDate)
        } else {
            tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumberAndExpDateGreaterThanEquals(productId, batch, currentDate)
        }
        if (tempStockBooks?.size() == 0) {
            //send batches which are not in temp stock
            ArrayList<StockBook> stockBooks = StockBook.findAllByProductIdAndExpDateGreaterThanEquals(productId, currentDate)
            return stockBooks
        } else {
            return tempStockBooks
        }
    }


    TempStockBook get(String id) {
        return TempStockBook.findById(Long.parseLong(id))
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

    TempStockBook save(JSONObject jsonObject) {
        //Date sanitize
        String expDate = jsonObject.get("expDate")
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        if (expDate.contains("T")) {
            expDate = sdf.format(sdf1.parse(expDate))
        }
        jsonObject.put("expDate", expDate)

        long remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
        long remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())
        long remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
        long productId = Long.parseLong(jsonObject.get("productId").toString())
        String batchNumber = jsonObject.get("batchNumber")
        Long userId = Long.parseLong(jsonObject.get("userId").toString())
        TempStockBook tempStockBook = TempStockBook.findByProductIdAndUserIdAndBatchNumber(productId, userId, batchNumber)

        if (tempStockBook == null)
            tempStockBook = new TempStockBook()
        else
            tempStockBook.isUpdatable = true

        tempStockBook.batchNumber = batchNumber
        tempStockBook.packingDesc = jsonObject.get("packingDesc")
        tempStockBook.productId = productId
        tempStockBook.userId = userId
        tempStockBook.userOrderQty = Long.parseLong(jsonObject.get("userOrderQty").toString())
        tempStockBook.userOrderFreeQty = Long.parseLong(jsonObject.get("userOrderFreeQty").toString())
        tempStockBook.userOrderReplQty = Long.parseLong(jsonObject.get("userOrderReplQty").toString())
        //tempStockBook.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        tempStockBook.redundantBatch = 0
        tempStockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
        tempStockBook.remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
        tempStockBook.remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())
        tempStockBook.remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
        tempStockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
        tempStockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        tempStockBook.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
        tempStockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
        tempStockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        tempStockBook.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        tempStockBook.originalId = Long.parseLong(jsonObject.get("originalId").toString())
        tempStockBook.originalSqty = Long.parseLong(jsonObject.get("originalSqty").toString())
        tempStockBook.originalFqty = Long.parseLong(jsonObject.get("originalFqty").toString())
        tempStockBook.uuid = jsonObject.get("uuid")
        tempStockBook.save(flush: true)
        if (!tempStockBook.hasErrors()) {

            //update stockbook
            StockBook stockBook = StockBook.findByProductIdAndBatchNumber(tempStockBook.productId, tempStockBook.batchNumber)
            stockBook.remainingQty = remainingQty
            stockBook.remainingFreeQty = remainingFreeQty
            stockBook.remainingReplQty = remainingReplQty
            stockBook.isUpdatable = true
            stockBook.save(flush:true)

            //update qty for tempstocks added by other users
            ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumberAndEntityId(productId, batchNumber, Long.parseLong(jsonObject.get("entityId").toString()))
            for (TempStockBook ts : tempStockBooks) {
                if(tempStockBook.id != ts.id) {
                    ts.remainingQty = remainingQty
                    ts.remainingFreeQty = remainingFreeQty
                    ts.remainingReplQty = remainingReplQty
                    ts.isUpdatable = true
                    ts.save(flush: true)
                }
            }
            return tempStockBook
        }
        else
            throw new BadRequestException()
    }

    TempStockBook update(JSONObject jsonObject, String id) {

        if (id) {
            //Date sanitize
            String expDate = jsonObject.get("expDate")
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            if (expDate.contains("T")) {
                expDate = sdf.format(sdf1.parse(expDate))
            }
            jsonObject.put("expDate", expDate)

            long remainingQty = Long.parseLong(jsonObject.get("remainingQty").toString())
            long remainingFreeQty = Long.parseLong(jsonObject.get("remainingFreeQty").toString())
            long remainingReplQty = Long.parseLong(jsonObject.get("remainingReplQty").toString())
            long productId = Long.parseLong(jsonObject.get("productId").toString())
            String batchNumber = jsonObject.get("batchNumber")
            TempStockBook tempStockBook = TempStockBook.findById(Long.parseLong(id))
            if (tempStockBook) {
                tempStockBook.isUpdatable = true
                tempStockBook.batchNumber = batchNumber
                tempStockBook.packingDesc = jsonObject.get("packingDesc")
                tempStockBook.productId = productId
                tempStockBook.userId = Long.parseLong(jsonObject.get("userId").toString())
                tempStockBook.userOrderQty = Long.parseLong(jsonObject.get("userOrderQty").toString())
                tempStockBook.userOrderFreeQty = Long.parseLong(jsonObject.get("userOrderFreeQty").toString())
                tempStockBook.userOrderReplQty = Long.parseLong(jsonObject.get("userOrderReplQty").toString())
                tempStockBook.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
                tempStockBook.remainingFreeQty = remainingFreeQty
                tempStockBook.remainingQty = remainingQty
                tempStockBook.remainingReplQty = remainingReplQty
                tempStockBook.expDate = sdf.parse(jsonObject.get("expDate").toString())
                tempStockBook.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
                tempStockBook.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
                tempStockBook.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                tempStockBook.taxId = Long.parseLong(jsonObject.get("taxId").toString())
                tempStockBook.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                tempStockBook.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                tempStockBook.originalId = Long.parseLong(jsonObject.get("originalId").toString())
                tempStockBook.uuid = jsonObject.get("uuid")
                tempStockBook.originalSqty = Long.parseLong(jsonObject.get("originalSqty").toString())
                tempStockBook.originalFqty = Long.parseLong(jsonObject.get("originalFqty").toString())
                tempStockBook.save(flush: true)
                if (!tempStockBook.hasErrors()) {
                    //update qty for tempstocks added by other users
                    ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumberAndEntityId(productId, batchNumber, Long.parseLong(jsonObject.get("entityId").toString()))
                    for (TempStockBook ts : tempStockBooks) {
                        if(tempStockBook.id != ts.id) {
                            ts.remainingQty = remainingQty
                            ts.remainingFreeQty = remainingFreeQty
                            ts.remainingReplQty = remainingReplQty
                            ts.isUpdatable = true
                            ts.save(flush: true)
                        }
                    }
                    return tempStockBook
                } else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()

        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id, boolean updateTempStock) {
        if (id) {
            TempStockBook tempStockBook = TempStockBook.findById(Long.parseLong(id))
            if (tempStockBook) {
                tempStockBook.isUpdatable = true
                if(!tempStockBook.hasErrors() && updateTempStock)
                {
                    //update stockbook
                    StockBook stockBook = StockBook.findByProductIdAndBatchNumber(tempStockBook.productId, tempStockBook.batchNumber)
                    stockBook.remainingQty += tempStockBook.userOrderQty
                    stockBook.remainingFreeQty += tempStockBook.userOrderFreeQty
                    stockBook.remainingReplQty += tempStockBook.userOrderReplQty
                    stockBook.isUpdatable = true
                    stockBook.save(flush:true)

                    //update qty for tempstocks added by other users
                    ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumberAndEntityId(tempStockBook.productId, tempStockBook.batchNumber, tempStockBook.entityId)
                    for (TempStockBook ts : tempStockBooks) {
                        if(tempStockBook.id != ts.id) {
                            ts.remainingQty += tempStockBook.userOrderQty
                            ts.remainingFreeQty += tempStockBook.userOrderFreeQty
                            ts.remainingReplQty += tempStockBook.userOrderReplQty
                            ts.isUpdatable = true
                            ts.save(flush: true)
                        }
                    }
                }
                tempStockBook.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def updateTempStocksOfAllUsers(TempStockBook tempStockBook)
    {
        ArrayList<TempStockBook> tempStockBooks = TempStockBook.findAllByProductIdAndBatchNumberAndEntityId(tempStockBook.productId, tempStockBook.batchNumber, tempStockBook.entityId)
        for (TempStockBook ts : tempStockBooks) {
            if(tempStockBook.id != ts.id) {
                if(ts.remainingQty <= tempStockBook.remainingQty)
                {
                    ts.remainingQty = ts.remainingQty - tempStockBook.userOrderQty
                }
                ts.remainingQty = remainingQty
                ts.remainingFreeQty = remainingFreeQty
                ts.remainingReplQty = remainingReplQty
                ts.isUpdatable = true
                ts.save(flush: true)
            }
        }
    }
}
