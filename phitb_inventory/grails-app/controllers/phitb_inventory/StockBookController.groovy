package phitb_inventory

import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONObject
import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

class StockBookController
{
    static responseFormats = ['json', 'xml']

    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")

    StockBookService stockBookService
    /**
     * Gets all Stock Books
     * @param query
     * @param offset
     * @param limit
     * @return list of Stock Books
     */
    def index()
    {
        try
        {
            respond stockBookService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def show()
    {
        try
        {
            String id = params.id
            if (id)
            {
                respond stockBookService.get(id)
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def getByEntityId()
    {
        try
        {
            if (params.id)
            {
                respond stockBookService.getAllByEntity(params.limit, params.offset, Long.parseLong(params.id))
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def getByProductId()
    {
        try
        {
            if (params.id)
            {
                respond stockBookService.getAllByProduct(0, 0, Long.parseLong(params.id))
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def getByProductIdForSaleReturn()
    {
        try
        {
            if (params.id)
            {
                respond stockBookService.getAllByProductSaleReturn(0, 0, Long.parseLong(params.id))
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }
    /**
     * Save new Stock Book
     * @param Stock Book
     * @return saved Stock Book
     */
    def save()
    {
        try
        {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond stockBookService.save(jsonObject)
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Update existing Stock Book
     * @param id
     * @param Stock Book
     * @return updated Stock Books
     */
    def update()
    {
        try
        {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond stockBookService.update(jsonObject, id)
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Delete selected Stock Book
     * @param id
     * @return returns status code 200
     */
    def delete()
    {
        try
        {
            String id = params.id
            stockBookService.delete(id)
            response.status = 200
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Gets all Stock Books in datatables format
     * @return list of Stock Books
     */
    def dataTable()
    {
        try
        {
            String start = params.start
            String length = params.length
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            respond stockBookService.dataTables(paramsJsonObject, start, length)
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def stockPurchase()
    {
        try
        {
            StockBook stockBook = StockBook.findByBatchNumber(params.batch)
            def remQty = stockBook.getRemainingQty()
            stockBook.remainingQty = remQty + Long.parseLong(params.purQty)
            stockBook.isUpdatable = true
            StockBook savedStockBook = stockBook.save(flush: true)
            if (savedStockBook)
            {
                respond savedStockBook
                return
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        response.status = 400
    }


    def stockIncrease()
    {
        try
        {
            JSONObject paramsJsonObject = new JSONObject(params)
            JSONObject stockObject = new JSONObject(JSON.parse(paramsJsonObject.params))
            StockBook stockBook = StockBook.findByBatchNumberAndProductId(stockObject.batchNumber, stockObject.productId)
            if (stockBook)
            {
                stockBook.isUpdatable = true
                def remQty = stockBook.getRemainingQty()
                def freeQty = stockBook.getRemainingFreeQty()
                if (stockObject.reason == "R")
                {
                    StockActivity stockActivity = new StockActivity()
                    stockActivity.setPrevRemQty(Long.parseLong(stockBook.remainingQty.toString()))
                    stockActivity.setPrevSchemeQty(Long.parseLong(stockBook.remainingFreeQty.toString()))

//                   Update Stock book
                    stockBook.remainingQty = remQty + Long.parseLong(stockObject.saleQty)
                    stockBook.remainingFreeQty = freeQty + Long.parseLong(stockObject.freeQty)
                    System.out.println("Remaining Qty After Sale Return " + stockBook.getRemainingQty())
                    System.out.println("Remaining Free Qty After Sale Return " + stockBook.getRemainingFreeQty())

                    stockActivity.setProductId(Long.parseLong(stockObject.productId))
                    stockActivity.setBatch(stockObject.batchNumber)
                    stockActivity.setRemainingQty(Long.parseLong(stockBook.remainingQty.toString())+Long.parseLong(stockObject.saleQty.toString()))
                    stockActivity.setRemainingSchemeQty(Long.parseLong(stockObject.freeQty.toString())+Long.parseLong(stockBook.remainingFreeQty.toString()))
                    stockActivity.setPrevSaleRate(Double.parseDouble(stockBook.saleRate.toString()))
                    stockActivity.setSaleRate(Double.parseDouble(stockObject.saleRate.toString()))
                    stockActivity.setStatus(0)
                    stockActivity.setSyncStatus(0)
                    stockActivity.setEntityTypeId(Long.parseLong(stockObject.entityTypeId.toString()))
                    stockActivity.setEntityId(Long.parseLong(stockObject.entityId.toString()))
                    stockActivity.setCreatedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.setModifiedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.save(flush:true)
                    StockActivity stockActivity1 = stockActivity.save(flush:true)
                    if(stockActivity1)
                    {
                        println("Stock Activity Updated")
                    }
                    else
                    {
                        println("Unable to update Stock Activity")
                    }
                }
                else if (stockObject.reason == "E")
                {

                    println("Expiry - NO EFFECT ON CURRENT STOCK BOOK")

                }
                else if (stockObject.reason == "B")
                {

                    println("Breakage - NO EFFECT ON CURRENT STOCK BOOK")
                }
                else if (stockObject.reason == "OA")
                {

                    StockActivity stockActivity = new StockActivity()
                    stockActivity.setPrevRemQty(Long.parseLong(stockBook.remainingQty.toString()))
                    stockActivity.setPrevSchemeQty(Long.parseLong(stockBook.remainingFreeQty.toString()))

//                    Stock update
                    stockBook.remainingQty = remQty + Long.parseLong(stockObject.saleQty)
                    stockBook.remainingFreeQty = freeQty + Long.parseLong(stockObject.freeQty)
                    System.out.println("Remaining Qty After Others(ADD)" + stockBook.getRemainingQty())
                    System.out.println("Remaining Free Qty After Others(ADD)" + stockBook.getRemainingFreeQty())

                    stockActivity.setProductId(Long.parseLong(stockObject.productId))
                    stockActivity.setBatch(stockObject.batchNumber)
                    stockActivity.setRemainingQty(Long.parseLong(stockBook.remainingQty.toString())+Long.parseLong(stockObject.saleQty.toString()))
                    stockActivity.setRemainingSchemeQty(Long.parseLong(stockObject.freeQty.toString())+Long.parseLong(stockBook.remainingFreeQty.toString()))
                    stockActivity.setPrevSaleRate(Double.parseDouble(stockBook.saleRate.toString()))
                    stockActivity.setSaleRate(Double.parseDouble(stockObject.saleRate.toString()))
                    stockActivity.setStatus(0)
                    stockActivity.setSyncStatus(0)
                    stockActivity.setEntityTypeId(Long.parseLong(stockObject.entityTypeId.toString()))
                    stockActivity.setEntityId(Long.parseLong(stockObject.entityId.toString()))
                    stockActivity.setCreatedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.setModifiedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.save(flush:true)
                    StockActivity stockActivity1 = stockActivity.save(flush:true)
                    if(stockActivity1)
                    {
                        println("Stock Activity Updated")
                    }
                    else
                    {
                        println("Unable to update Stock Activity")
                    }

                }
                else if (stockObject.reason == "ONE")
                {
                    println("Others(No efft) - NO EFFECT ON CURRENT STOCK BOOK")
                }
                StockBook savedStockBook = stockBook.save(flush: true)
                if (savedStockBook)
                {
                    respond savedStockBook
                    return
                }
            }
            else
            {
                StockBook stockBook1 = new StockBook()
                UUID uuid
                if (stockObject.reason == "R")
                {
                    stockBook1.setBatchNumber(stockObject.batchNumber)
                    stockBook1.setProductId(Long.parseLong(stockObject.productId))
                    stockBook1.setRemainingQty(Long.parseLong(stockObject.saleQty))
                    stockBook1.setRemainingFreeQty(Long.parseLong(stockObject.freeQty))
                    stockBook1.setSaleRate(Double.parseDouble(stockObject.batch.saleRate.toString()))
                    stockBook1.setPurchaseRate(Double.parseDouble(stockObject.batch.purchaseRate.toString()))
                    String expDate = stockObject.batch.expiryDate.toString()
                    stockBook1.setExpDate(sdf.parse(expDate.substring(0, 10)))
                    stockBook1.setPurcDate(new Date())
                    String manfDate = stockObject.batch.manfDate.toString()
                    stockBook1.setManufacturingDate(sdf.parse(manfDate.substring(0, 10)))
                    stockBook1.setMrp(Double.parseDouble(stockObject.batch.mrp.toString()))
                    stockBook1.setStatus("0")
                    stockBook1.setMergedWith("0")
                    stockBook1.setTaxId(Long.parseLong(stockObject.taxId))
                    stockBook1.setPackingDesc(stockObject.packDesc)
                    long openStockQty = Long.parseLong(stockObject.saleQty.toString()) + Long.parseLong(stockObject.freeQty.toString())
                    stockBook1.setOpeningStockQty(openStockQty)
                    stockBook1.setEntityId(stockObject.entityId)
                    stockBook1.setEntityTypeId(stockObject.entityTypeId)
                    stockBook1.setCreatedUser(stockObject.userId)
                    stockBook1.setModifiedUser(stockObject.userId)
                    stockBook1.setUuid(UUID.randomUUID().toString())

//                    Stock Activity
                    StockActivity stockActivity = new StockActivity()
                    stockActivity.setPrevRemQty(Long.parseLong("0"))
                    stockActivity.setPrevSchemeQty(Long.parseLong("0"))
                    stockActivity.setProductId(Long.parseLong(stockObject.productId))
                    stockActivity.setBatch(stockObject.batchNumber)
                    stockActivity.setRemainingQty(Long.parseLong(stockObject.saleQty.toString()))
                    stockActivity.setRemainingSchemeQty(Long.parseLong(stockObject.freeQty.toString()))
                    stockActivity.setPrevSaleRate(Double.parseDouble("0"))
                    stockActivity.setSaleRate(Double.parseDouble(stockObject.saleRate.toString()))
                    stockActivity.setStatus(0)
                    stockActivity.setSyncStatus(0)
                    stockActivity.setEntityTypeId(Long.parseLong(stockObject.entityTypeId.toString()))
                    stockActivity.setEntityId(Long.parseLong(stockObject.entityId.toString()))
                    stockActivity.setCreatedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.setModifiedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.save(flush:true)
                    StockActivity stockActivity1 = stockActivity.save(flush:true)
                    if(stockActivity1)
                    {
                        println("Stock Activity Updated")
                    }
                    else
                    {
                        println("Unable to update Stock Activity")
                    }
                }
                else if (stockObject.reason == "E")
                {
                    println("Expiry - NO EFFECT ON CURRENT STOCK BOOK")
                }
                else if (stockObject.reason == "B")
                {
                    println("Breakage - NO EFFECT ON CURRENT STOCK BOOK")
                }
                else if (stockObject.reason == "OA")
                {
                    stockBook1.setBatchNumber(stockObject.batchNumber)
                    stockBook1.setProductId(Long.parseLong(stockObject.productId))
                    stockBook1.setRemainingQty(Long.parseLong(stockObject.saleQty))
                    stockBook1.setRemainingFreeQty(Long.parseLong(stockObject.freeQty))
                    stockBook1.setSaleRate(Double.parseDouble(stockObject.batch.saleRate.toString()))
                    stockBook1.setPurchaseRate(Double.parseDouble(stockObject.batch.purchaseRate.toString()))
                    String expDate = stockObject.batch.expiryDate.toString()
                    stockBook1.setExpDate(sdf.parse(expDate.substring(0, 10)))
                    stockBook1.setPurcDate(new Date())
                    String manfDate = stockObject.batch.manfDate.toString()
                    stockBook1.setManufacturingDate(sdf.parse(manfDate.substring(0, 10)))
                    stockBook1.setMrp(Double.parseDouble(stockObject.batch.mrp.toString()))
                    stockBook1.setStatus("0")
                    stockBook1.setMergedWith("0")
                    stockBook1.setTaxId(Long.parseLong(stockObject.taxId))
                    stockBook1.setPackingDesc(stockObject.packDesc)
                    long openStockQty = Long.parseLong(stockObject.saleQty.toString()) + Long.parseLong(stockObject.freeQty.toString())
                    stockBook1.setOpeningStockQty(openStockQty)
                    stockBook1.setEntityId(stockObject.entityId)
                    stockBook1.setEntityTypeId(stockObject.entityTypeId)
                    stockBook1.setCreatedUser(stockObject.userId)
                    stockBook1.setModifiedUser(stockObject.userId)
                    stockBook1.setUuid(UUID.randomUUID().toString())

                    //                    Stock Activity
                    StockActivity stockActivity = new StockActivity()
                    stockActivity.setPrevRemQty(Long.parseLong("0"))
                    stockActivity.setPrevSchemeQty(Long.parseLong("0"))
                    stockActivity.setProductId(Long.parseLong(stockObject.productId))
                    stockActivity.setBatch(stockObject.batchNumber)
                    stockActivity.setRemainingQty(Long.parseLong(stockObject.saleQty.toString()))
                    stockActivity.setRemainingSchemeQty(Long.parseLong(stockObject.freeQty.toString()))
                    stockActivity.setPrevSaleRate(Double.parseDouble("0"))
                    stockActivity.setSaleRate(Double.parseDouble(stockObject.saleRate.toString()))
                    stockActivity.setStatus(0)
                    stockActivity.setSyncStatus(0)
                    stockActivity.setEntityTypeId(Long.parseLong(stockObject.entityTypeId.toString()))
                    stockActivity.setEntityId(Long.parseLong(stockObject.entityId.toString()))
                    stockActivity.setCreatedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.setModifiedUser(Long.parseLong(stockObject.userId.toString()))
                    stockActivity.save(flush:true)
                    StockActivity stockActivity1 = stockActivity.save(flush:true)
                    if(stockActivity1)
                    {
                        println("Stock Activity Updated")
                    }
                    else
                    {
                        println("Unable to update Stock Activity")
                    }

                }
                else if (stockObject.reason == "ONE")
                {
                    println("Others(No efft) - NO EFFECT ON CURRENT STOCK BOOK")
                }
                StockBook stockBook2 = stockBook1.save()
                if (stockBook2)
                {
                    println("New batches stock updated")
                    respond stockBook2
                    return
                }
                else
                {
                    println("Something went Wrong!")
                }
            }
        }

    catch (Exception ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
    }
    response.status = 400
}

/**
 * Get requested Stock Book
 * @param id
 * @return get requested Stock Book
 */
def getByUserId()
{
    try
    {

        if (params.id)
        {
            respond stockBookService.getAllByUserId(Long.parseLong(params.id))
        }
    }
    catch (ResourceNotFoundException ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        response.status = 404
    }
    catch (BadRequestException ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        response.status = 400
    }
    catch (Exception ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
    }
}

def getByProductIdAndBatch()
{
    try
    {
        String id = params.id
        long entityId = Long.parseLong(params.entityId)
        if (id)
        {
            respond stockBookService.getByProductAndBatch(Long.parseLong(id), params.batch, entityId)
        }
    }
    catch (ResourceNotFoundException ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        response.status = 404
    }
    catch (BadRequestException ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        response.status = 400
    }
    catch (Exception ex)
    {
        System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
    }
}

}
