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
            println(params)
            StockBook stockBook = StockBook.findByBatchNumberAndProductId(params.batch, params.productId)
            if (stockBook)
            {
                def remQty = stockBook.getRemainingQty()
                def freeQty = stockBook.getRemainingFreeQty()
                if (params.reason == "R")
                {
                    stockBook.remainingQty = remQty + Long.parseLong(params.purQty)
                    stockBook.remainingFreeQty = freeQty + Long.parseLong(params.fqty)
                    System.out.println("Remaining Qty After Sale Return" + stockBook.getRemainingQty())
                    System.out.println("Remaining Qty After Sale Return" + stockBook.getRemainingFreeQty())
                }
                else if (params.reason == "E")
                {
//                stockBook.remainingQty = remQty - Long.parseLong(params.purQty)
//                stockBook.remainingFreeQty = freeQty - Long.parseLong(params.fqty)
                    println("Expiry - NO EFFECT ON CURRENT STOCK BOOK")

                }
                else if (params.reason == "B")
                {
//                stockBook.remainingQty = remQty - Long.parseLong(params.purQty)
//                stockBook.remainingFreeQty = freeQty - Long.parseLong(params.fqty)
                    println("Breakage - NO EFFECT ON CURRENT STOCK BOOK")
                }
                else if (params.reason == "OA")
                {
                    stockBook.remainingQty = remQty + Long.parseLong(params.purQty)
                    stockBook.remainingFreeQty = freeQty + Long.parseLong(params.fqty)
                    System.out.println("Remaining Qty After Others(ADD)" + stockBook.getRemainingQty())
                    System.out.println("Remaining Qty After Others(ADD)" + stockBook.getRemainingFreeQty())
                }
                else if (params.reason == "ONE")
                {
                    println("Others(No efft) - NO EFFECT ON CURRENT STOCK BOOK")
                }
                stockBook.isUpdatable = true
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
                if (params.reason == "R")
                {
                    stockBook1.setBatchNumber(params.batch)
                    stockBook1.setProductId(Long.parseLong(params.productId))
                    stockBook1.setRemainingQty(Long.parseLong(params.sQty))
                    stockBook1.setRemainingFreeQty(Long.parseLong(params.fqty))
                    stockBook1.setSaleRate(Double.parseDouble(params.saleRate))
                    stockBook1.setExpDate(sdf.parse(params.expDate))
                    stockBook1.setStatus("0")
                    stockBook1.setPurcDate(new Date())
                    stockBook1.setManufacturingDate(new Date())
//                    stockBook1.setMergedWith("0")
                    stockBook1.setTaxId(Long.parseLong(params.taxId))
                    stockBook1.setPackingDesc(params.pakingDesc)
                    stockBook1.setUuid(UUID.randomUUID().toString())
                }
                else if (params.reason == "E")
                {
//                stockBook.remainingQty = remQty - Long.parseLong(params.purQty)
//                stockBook.remainingFreeQty = freeQty - Long.parseLong(params.fqty)
                    println("Expiry - NO EFFECT ON CURRENT STOCK BOOK")

                }
                else if (params.reason == "B")
                {
//                stockBook.remainingQty = remQty - Long.parseLong(params.purQty)
//                stockBook.remainingFreeQty = freeQty - Long.parseLong(params.fqty)
                    println("Breakage - NO EFFECT ON CURRENT STOCK BOOK")
                }
                else if (params.reason == "OA")
                {
                    stockBook1.setBatchNumber(params.batch)
                    stockBook1.setProductId(Long.parseLong(params.productId))
                    stockBook1.setRemainingQty(Long.parseLong(params.sQty))
                    stockBook1.setRemainingFreeQty(Long.parseLong(params.fqty))
                    stockBook1.setSaleRate(Double.parseDouble(params.saleRate))
                    stockBook1.setExpDate(sdf.parse(params.expDate))
                    stockBook1.setStatus("0")
                    stockBook1.setPurcDate(new Date())
                    stockBook1.setManufacturingDate(new Date())
//                    stockBook1.setMergedWith("0")
                    stockBook1.setTaxId(Long.parseLong(params.taxId))
                    stockBook1.setPackingDesc(params.pakingDesc)
                    stockBook1.setUuid(UUID.randomUUID().toString())
                }
                else if (params.reason == "ONE")
                {
                    println("Others(No efft) - NO EFFECT ON CURRENT STOCK BOOK")
                }
                StockBook stockBook2 = stockBook1.save()
                if(stockBook2)
                {
                    println("New batches stock updated")
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
