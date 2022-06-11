package phitb_ui.inventory

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.hibernate.engine.jdbc.batch.spi.Batch
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.TaxController

class StockBookController {

    def index() {
        def entityId = session.getAttribute("entityId").toString()
        JSONArray productList = new ProductService().getProductsByEntityId(entityId)
        def entityList = new EntityRegisterController().show()
        def taxList = new TaxController().show()
        render(view: "/inventory/stock-entry", model: [productList:productList, entityList:entityList, taxList:taxList])
    }

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            jsonObject.put("modifiedUser", session.getAttribute("userId"))
            jsonObject.put("createdUser", session.getAttribute("userId"))
           // jsonObject.put("mergedWith", session.getAttribute(""))
            def apiResponse = new InventoryService().stockBookSave(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            jsonObject.put("createdUser", session.getAttribute("userId"))
            jsonObject.put("modifiedUser", session.getAttribute("userId"))
            def apiResponse = new InventoryService().updateStockBook(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId",session.getAttribute('entityId'))
            def apiResponse = new InventoryService().showStockBooks(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject) {
                    JSONArray productData = new JSONObject()
                    JSONArray data = responseObject.get("data")
                    for (JSONObject dt : data) {
                        def product = new ProductService().getProductById(dt.productId.toString())
                        if(product)
                        {
                            dt.put("product", product)
                        }
                        productData.put(dt)
                    }
                    responseObject.put("data", productData)

                    respond responseObject, formats: ['json'], status: 200
                }
                else
                    response.status = 404
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getStocksOfProduct()
    {
        //get temp stockbook data
        def apiResp = new InventoryService().getTempStocksOfProductAndBatch(params.id, null)
        if(apiResp.status == 200)
        {
            JSONArray tempStockBookData = new JSONArray(apiResp.readEntity(String.class))
            if(tempStockBookData.size()>0) {
                def apiResponse = new InventoryService().getStocksOfProduct(params.id)
                if (apiResponse?.status == 200) {
                    JSONArray mainStockBookData = new JSONArray(apiResponse.readEntity(String.class))
                    JSONArray responseArray = new JSONArray()

                    //get main stockbook data
                    for (JSONObject mainStock : mainStockBookData) {
                        boolean toBeAddedToTmpStock = true
                        for (JSONObject tmpStock : tempStockBookData) {
                            if (mainStock.get("batchNumber") == tmpStock.get("batchNumber")) {
                                //if main stock batch = tmp stock batch skip outer loop
                                toBeAddedToTmpStock = false
                                break
                            }
                        }
                        if (toBeAddedToTmpStock) {
                            String id = mainStock["taxId"]
                            def tax = new TaxController().show(id)
                            println(tax.taxValue)
                            mainStock.put("gst", tax.taxValue)
                            mainStock.put("sgst", tax.salesSgst)
                            mainStock.put("cgst", tax.salesCgst)
                            mainStock.put("igst", tax.salesIgst)
                            responseArray.put(mainStock)
                        }
                    }

                    for (JSONObject tmpStock : tempStockBookData) {
                        String id = tmpStock["taxId"]
                        def tax = new TaxController().show(id)
                        println(tax.taxValue)
                        tmpStock.put("gst", tax.taxValue)
                        tmpStock.put("sgst", tax.salesSgst)
                        tmpStock.put("cgst", tax.salesCgst)
                        tmpStock.put("igst", tax.salesIgst)
                        responseArray.put(tmpStock)
                    }
                    respond responseArray, formats: ['json'], status: 200
                } else {
                    response.status = apiResponse?.status
                }
            }
            else {
                //if not available in temp, respond main stock
                def apiResponse = new InventoryService().getStocksOfProduct(params.id)
                if (apiResponse?.status == 200) {
                    JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
                    JSONArray responseArray = new JSONArray()
                    for (JSONObject json : stockBookData) {
                        String id = json["taxId"]
                        def tax = new TaxController().show(id)
                        println(tax.taxValue)
                        json.put("gst", tax.taxValue)
                        json.put("sgst", tax.salesSgst)
                        json.put("cgst", tax.salesCgst)
                        json.put("igst", tax.salesIgst)
                        responseArray.put(json)
                    }
                    respond responseArray, formats: ['json'], status: 200
                } else {
                    response.status = apiResponse?.status
                }
            }
        }
        else {
            response.status = apiResp?.status
        }
    }

    def getStocksOfProductSaleReturn()
    {
        try
        {
            def apiResponse = new InventoryService().getStocksOfProductSaleRetrun(params.id)
            if (apiResponse?.status == 200) {
                JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
                ArrayList<String> existingBatches = new ArrayList<>()
                for (Object st : stockBookData)
                {
                    existingBatches.add(st.batchNumber)
                }
                def productResponse = new ProductService().getBatchesOfProduct(params.id)
                JSONArray batchData = new JSONArray(productResponse.readEntity(String.class))
                for (Object bd : batchData)
                {
                    if(!existingBatches.contains(bd.batchNumber))
                    {
                        bd.put("expDate", bd.get("expiryDate"));
                        bd.put("manufacturingDate", bd.get("manfDate"));
                        bd.put("remainingQty", 0);
                        bd.put("remainingFreeQty", 0);
                        bd.put("purchaseRate", bd.get("purchaseRate"));
                        bd.put("saleRate", bd.get("saleRate"));
                        bd.put("mrp", bd.get("mrp"));
                        bd.put("packingDesc", bd.product.unitPacking);
                        stockBookData.add(bd)
                    }
                }
                JSONArray stockArray = new JSONArray()
                for (JSONObject stock : stockBookData) {
                    String id = stock["taxId"]
                    if(id)
                    {
                        def tax = new TaxController().show(id)
                        stock.put("gst", tax.taxValue)
                        stock.put("sgst", tax.salesSgst)
                        stock.put("cgst", tax.salesCgst)
                        stock.put("igst", tax.salesIgst)
                    }
                    else {
                        stock.put("gst", 0);
                        stock.put("sgst", 0);
                        stock.put("cgst", 0);
                        stock.put("igst", 0);
                    }
                    stockArray.put(stock)
                }
                respond stockArray, formats: ['json'], status: 200
            } else {
                response.status = apiResponse?.status
            }
        }
        catch(Exception e)
        {
            log.error(controllerName+":"+e)
            System.out.println(controllerName+":"+e)
        }
    }


    def getStocksOfProductForPurchase()
    {
        String productId = params.id
        //Get main stock
        def apiResponse = new InventoryService().getStocksOfProduct(productId)
        if (apiResponse?.status == 200) {
            JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : stockBookData) {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.purchaseSgst)
                json.put("cgst", tax.purchaseCgst)
                json.put("igst", tax.purchaseIgst)
                responseArray.put(json)
            }
            //Add New Batches
            def apiResp2 = new ProductService().getBatchesOfProduct(productId)
            if (apiResp2?.status == 200) {
                def addedBatches = responseArray.batchNumber
                JSONArray batches = new JSONArray(apiResp2.readEntity(String.class))
                for (Object batch : batches) {
                    if(!addedBatches.contains(batch.batchNumber))
                    {
                        JSONObject stockEntry = new JSONObject()
                        stockEntry.put("productId", batch.product.id)
                        stockEntry.put("batchNumber", batch.batchNumber)
                        stockEntry.put("expDate", batch.expiryDate)
                        stockEntry.put("purchaseRate", batch.purchaseRate)
                        stockEntry.put("saleRate", batch.saleRate)
                        stockEntry.put("mrp", batch.mrp)
                        stockEntry.put("purcTradeDiscount", "0")
                        stockEntry.put("purcSeriesId", "")
                        stockEntry.put("purcDate", new Date())
                        stockEntry.put("supplierId", "")
                        stockEntry.put("manufacturingDate", batch.manfDate)
                        stockEntry.put("packingDesc","" )
                        stockEntry.put("purcProductValue","" )
                        stockEntry.put("remainingQty",0 )
                        stockEntry.put("remainingFreeQty",0 )
                        stockEntry.put("remainingReplQty",0 )
                        stockEntry.put("status", "1")
                        stockEntry.put("syncStatus", "1")
                        stockEntry.put("mergedWith", "0")
                        stockEntry.put("entityTypeId", session.getAttribute("entityTypeId"))
                        stockEntry.put("entityId", session.getAttribute("entityId"))
                        stockEntry.put("createdUser", session.getAttribute("userId"))
                        stockEntry.put("modifiedUser", session.getAttribute("userId"))
                        stockEntry.put("openingStockQty", 0)
                        stockEntry.put("taxId", batch.product.taxId)
                        def tax = new TaxController().show(batch.product.taxId.toString())
                        stockEntry.put("gst", tax.taxValue)
                        stockEntry.put("sgst", tax.salesSgst)
                        stockEntry.put("cgst", tax.salesCgst)
                        stockEntry.put("igst", tax.salesIgst)
                        responseArray.add(stockEntry)
                    }
                }
            }
            respond responseArray, formats: ['json'], status: 200
        } else {
            response.status = apiResponse?.status
        }
    }

    def getTempStocksOfProductAndBatch()
    {
        def apiResponse = new InventoryService().getTempStocksOfProductAndBatch(params.id,params.batch)
        if(apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray) {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                println(tax.taxValue)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            respond responseArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }

    }

    def getTempStocksOfEntity()
    {
        def apiResponse = new InventoryService().getTempStocksOfEntity(params.id)
        if(apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray) {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                println(tax.taxValue)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            respond responseArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }
    }

    def getTempStocksOfUser()
    {
        def apiResponse = new InventoryService().getTempStocksByUser(params.id)
        if(apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray) {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            respond responseArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }
    }

    def getStocksOfUser()
    {
        def apiResponse = new InventoryService().getStocksByUser(params.id)
        if(apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray) {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            respond responseArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }
    }


    def tempStockBookSave()
    {
        try
        {
            JSONArray jsonArray = new JSONArray(params.rowData)
            Boolean isEdit = false
            int i = 0
            for (Object obj : jsonArray) {
                //15 if edit, 16 if being added
                if(i == 15 && obj != null)
                    isEdit = true
/*                else if(i == 16 && obj != null)
                    isEdit = false*/
                i++
            }
            def stockBook = null
            if(!isEdit)
                stockBook = new InventoryService().getStockBookById(jsonArray[20])
            else {
                def tmpStockBook = new InventoryService().getTempStocksById(jsonArray[15])
                stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
            }
            long remainingQty = stockBook.remainingQty
            long remainingFreeQty = stockBook.remainingFreeQty
            long saleQty  = jsonArray[4]
            long saleFreeQty  = jsonArray[5]
            if(saleQty<=remainingQty)
            {
                remainingQty = remainingQty - saleQty
            }
            else if(saleQty>remainingQty && saleQty<(remainingQty+remainingFreeQty))
            {
                remainingFreeQty = remainingFreeQty - (saleQty - remainingQty)
                remainingQty = 0
            }
            if(saleFreeQty<=remainingFreeQty)
            {
                remainingFreeQty = remainingFreeQty - saleFreeQty
            }
            else if(saleFreeQty>remainingFreeQty && saleFreeQty<(remainingQty+remainingFreeQty))
            {
                remainingQty = remainingQty - (saleFreeQty - remainingFreeQty)
                remainingFreeQty = 0
            }
           /* if(saleQty>remainingQty && remainingFreeQty>saleQty)
            {
                remainingQty = 0
                remainingFreeQty = remainingFreeQty - saleQty
            }
            else {
                remainingQty = remainingQty - saleQty
                if(remainingFreeQty<saleFreeQty)
                {
                    remainingFreeQty = 0
                }
                else {
                    remainingFreeQty = remainingFreeQty - saleFreeQty
                }
            }*/
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("productId", jsonArray[1])
            jsonObject.put("batchNumber", jsonArray[2])
            jsonObject.put("expDate", jsonArray[3])
            jsonObject.put("remainingQty", remainingQty)
            jsonObject.put("remainingFreeQty", remainingFreeQty)
            jsonObject.put("remainingReplQty", 0)
            jsonObject.put("saleRate", jsonArray[6])
            jsonObject.put("purchaseRate", stockBook.purchaseRate)
            jsonObject.put("mrp", jsonArray[7])
            jsonObject.put("discount", jsonArray[8])
            jsonObject.put("packingDesc", jsonArray[9])
            jsonObject.put("userOrderQty", saleQty)
            jsonObject.put("userOrderFreeQty", saleFreeQty)
            jsonObject.put("userOrderReplQty", 0)
            jsonObject.put("taxId", stockBook.taxId)
            jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            jsonObject.put("redundantBatch", "")
            jsonObject.put("originalId", stockBook.id)
            jsonObject.put("uuid", params.uuid)
            def apiResponse = new InventoryService().tempStockBookSave(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def stockBookSave()
    {
        try
        {
            def supplier = params.supplier
            def series = params.series
            JSONArray jsonArray = new JSONArray(params.rowData)
            Boolean isEdit = false
            int i = 0
            for (Object obj : jsonArray) {
                //15 if edit, 16 if being added
                if(i == 15 && obj != null)
                    isEdit = true
                else if(i == 16 && obj != null)
                    isEdit = false
                i++
            }
            def stockBook = null
            if(!isEdit)
                stockBook = new InventoryService().getStockBookById(jsonArray[16])
            else {
//                def tmpStockBook = new InventoryService().getTempStocksById(jsonArray[15])
                stockBook = new InventoryService().getStockBookById(Long.parseLong(jsonArray[15]))
            }

            long remainingQty = stockBook.remainingQty
            long remainingFreeQty = stockBook.remainingFreeQty
            long saleQty  = jsonArray[4]
            long saleFreeQty  = jsonArray[5]
            remainingQty = remainingQty - saleQty
            remainingFreeQty = remainingFreeQty - saleFreeQty

            JSONObject jsonObject = new JSONObject()
            jsonObject.put("productId", jsonArray[1])
            jsonObject.put("batchNumber", jsonArray[2])
            jsonObject.put("expDate", jsonArray[3])
            jsonObject.put("manufacturingDate", jsonArray[3])
            jsonObject.put("remainingQty", remainingQty)
            jsonObject.put("remainingFreeQty", remainingFreeQty)
            jsonObject.put("remainingReplQty", 0)
            jsonObject.put("purcTradeDiscount", 0)
            jsonObject.put("purcProductValue", 1)
            jsonObject.put("purchaseRate", stockBook.purchaseRate)
            jsonObject.put("saleRate",0)
            jsonObject.put("mrp", jsonArray[7])
            jsonObject.put("purcDate", jsonArray[3])
            jsonObject.put("discount", jsonArray[8])
            jsonObject.put("packingDesc", jsonArray[9])
            jsonObject.put("userOrderQty", saleQty)
            jsonObject.put("userOrderFreeQty", saleFreeQty)
            jsonObject.put("userOrderReplQty", 0)
            jsonObject.put("mergedWith", 0)
            jsonObject.put("status", 0)
            jsonObject.put("syncStatus", 0)
            jsonObject.put("createdUser", 1)
            jsonObject.put("modifiedUser", 1)
            jsonObject.put("supplierId", supplier)
            jsonObject.put("purcSeriesId", series)
            jsonObject.put("taxId", stockBook.taxId)
            jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            jsonObject.put("redundantBatch", "")
            jsonObject.put("originalId", stockBook.id)
            jsonObject.put("uuid", params.uuid)
            def apiResponse = new InventoryService().stockBookSave(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def tempStockShow()
    {
        try
        {
            def apiResponse = new InventoryService().getTempStocks()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def deleteTempStock()
    {
        def id = params.id
        def apiResponse = new InventoryService().deleteTempStock(id)
        respond(text: id, status: apiResponse.status)
    }


    def StockBookPurchase()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new InventoryService().StockBookPurchase(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                render(view: '/entity/entityRegister/add-entity-register')
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def stockPurchase()
    {
        def batch = params.batch
        def purqty = params.sqty
        def apiResponse = new InventoryService().stocksPurchase(batch,purqty)
        if (apiResponse?.status == 200)
        {
            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
            respond jsonObject,formats: ['json'],status: 200
        }
        else
        {
            return null
        }

    }

//    def stockIncrease()
//    {
//        def batch = params.batch
//        def purqty = params.sqty
//        def freeqty = params.fqty
//        def reason = params.reason
//        def apiResponse = new InventoryService().stocksIncrease(batch,purqty,freeqty,reason)
//        if (apiResponse?.status == 200)
//        {
//            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
//            respond jsonObject,formats: ['json'],status: 200
//        }
//        else
//        {
//            return null
//        }
//
//    }


    def stockByProductAndBatch()
    {
        def batch = params.batch
        def productId = params.productId
        String entityId = session.getAttribute('entityId').toString()
        def apiResponse = new InventoryService().stocksByProductAndBatch(batch,productId,entityId)
        if (apiResponse?.status == 200)
        {
            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
            respond jsonObject,formats: ['json'],status: 200
        }
        else
        {
            return null
        }
    }

    def delete()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new InventoryService().deleteStockBook(jsonObject.id)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success","success")
                respond data, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
