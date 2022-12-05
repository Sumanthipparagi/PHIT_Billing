package phitb_ui.inventory

import com.fasterxml.jackson.databind.ObjectMapper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONException
import org.grails.web.json.JSONObject
import org.hibernate.engine.jdbc.batch.spi.Batch
import org.springframework.messaging.simp.SimpMessagingTemplate
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.Tools
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.TaxController

import java.text.SimpleDateFormat

class StockBookController
{

    SimpMessagingTemplate brokerMessagingTemplate

    def index()
    {
        def entityId = session.getAttribute("entityId").toString()
        JSONArray productList = new ProductService().getProductsByEntityId(entityId)
        def entityList = new EntityRegisterController().show()
        def taxList = new EntityService().getTaxesByEntity(session.getAttribute("entityId").toString())
        render(view: "/inventory/stock-entry", model: [productList: productList, entityList: entityList, taxList: taxList])
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
            jsonObject.put("entityId", session.getAttribute('entityId'))
            def apiResponse = new InventoryService().showStockBooks(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject)
                {
                    JSONArray productData = new JSONObject()
                    JSONArray data = responseObject.get("data")
                    for (JSONObject dt : data)
                    {
                        def product = new ProductService().getProductById(dt.productId.toString())
                        if (product)
                        {
                            dt.put("product", product)
                        }
                        productData.put(dt)
                    }
                    responseObject.put("data", productData)

                    respond responseObject, formats: ['json'], status: 200
                }
                else
                {
                    response.status = 404
                }
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
        if (apiResp.status == 200)
        {
            JSONArray tempStockBookData = new JSONArray(apiResp.readEntity(String.class))
            if (tempStockBookData.size() > 0)
            {
                def apiResponse = new InventoryService().getStocksOfProduct(params.id)
                if (apiResponse?.status == 200)
                {
                    JSONArray mainStockBookData = new JSONArray(apiResponse.readEntity(String.class))
                    JSONArray responseArray = new JSONArray()

                    //get main stockbook data
                    for (JSONObject mainStock : mainStockBookData)
                    {
                        long remainingQty = mainStock.remainingQty
                        long remainingFreeQty = mainStock.remainingFreeQty
                        long remainingReplQty = mainStock.remainingReplQty
                        boolean toBeAddedToTmpStock = true
                        for (JSONObject tmpStock : tempStockBookData)
                        {
                            long userId = 0
                            if (tmpStock.has("userId"))
                            {
                                userId = Long.parseLong(tmpStock.get("userId")?.toString())
                            }
                            if (mainStock.get("batchNumber") == tmpStock.get("batchNumber")
                                    && userId == session.getAttribute("userId"))
                            {
                                //if main stock batch = tmp stock batch skip outer loop
                                toBeAddedToTmpStock = false
                                break
                            }
                        }
                        for (JSONObject tmpStock : tempStockBookData)
                        {
                            if (mainStock.productId == tmpStock.productId && mainStock.batchNumber == tmpStock.batchNumber)
                            {
                                if (tmpStock.remainingQty < remainingQty)
                                {
                                    remainingQty = tmpStock.remainingQty
                                }
                                if (tmpStock.remainingFreeQty < remainingFreeQty)
                                {
                                    remainingFreeQty = tmpStock.remainingFreeQty
                                }
                                if (tmpStock.remainingReplQty < remainingReplQty)
                                {
                                    remainingReplQty = tmpStock.remainingReplQty
                                }
                            }
                        }

                        if (toBeAddedToTmpStock)
                        {
                            String id = mainStock["taxId"]
                            def tax = new TaxController().show(id)
                            println(tax.taxValue)
                            mainStock.put("gst", tax.taxValue)
                            mainStock.put("sgst", tax.salesSgst)
                            mainStock.put("cgst", tax.salesCgst)
                            mainStock.put("igst", tax.salesIgst)
                            mainStock.put("remainingQty", remainingQty)
                            mainStock.put("remainingFreeQty", remainingFreeQty)
                            mainStock.put("remainingReplQty", remainingReplQty)
                            responseArray.put(mainStock)
                        }
                    }

                    for (JSONObject tmpStock : tempStockBookData)
                    {
                        long userId = 0
                        if (tmpStock.has("userId"))
                        {
                            userId = Long.parseLong(tmpStock.get("userId")?.toString())
                        }
                        if (userId == session.getAttribute("userId"))
                        {
                            String id = tmpStock["taxId"]
                            def tax = new TaxController().show(id)
                            println(tax.taxValue)
                            tmpStock.put("gst", tax.taxValue)
                            tmpStock.put("sgst", tax.salesSgst)
                            tmpStock.put("cgst", tax.salesCgst)
                            tmpStock.put("igst", tax.salesIgst)
                            responseArray.put(tmpStock)
                        }
                    }

//                    Remove duplicates batch while loading batches
                    Set<String> set =new HashSet<String>();
                    JSONArray tempArray=new JSONArray();
                    for(int i=0;i<responseArray.size();i++){
                        String  batch = responseArray.getJSONObject(i).getString("batchNumber")
                        if(set.contains(batch)){
                            continue;
                        }else{
                            set.add(batch);
                            tempArray.add(responseArray.getJSONObject(i));
                        }
                    }
                    responseArray = tempArray; //assign temp to original
                    respond responseArray, formats: ['json'], status: 200
                }
                else
                {
                    response.status = apiResponse?.status
                }
            }
            else
            {
                //if not available in temp, respond main stock
                def apiResponse = new InventoryService().getStocksOfProduct(params.id)
                if (apiResponse?.status == 200)
                {
                    JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
                    JSONArray responseArray = new JSONArray()
                    for (JSONObject json : stockBookData)
                    {
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
        }
        else
        {
            response.status = apiResp?.status
        }
    }

    def getStocksOfProductSaleReturn()
    {
        try
        {
            def apiResponse = new InventoryService().getStocksOfProductSaleRetrun(params.id)
            if (apiResponse?.status == 200)
            {
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
                    if (!existingBatches.contains(bd.batchNumber))
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
                for (JSONObject stock : stockBookData)
                {
                    String id = stock["taxId"]
                    if (id)
                    {
                        def tax = new TaxController().show(id)
                        stock.put("gst", tax.taxValue)
                        stock.put("sgst", tax.salesSgst)
                        stock.put("cgst", tax.salesCgst)
                        stock.put("igst", tax.salesIgst)
                    }
                    else
                    {
                        stock.put("gst", 0);
                        stock.put("sgst", 0);
                        stock.put("cgst", 0);
                        stock.put("igst", 0);
                    }
                    stockArray.put(stock)
                }
                respond stockArray, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status
            }
        }
        catch (Exception e)
        {
            log.error(controllerName + ":" + e)
            System.out.println(controllerName + ":" + e)
        }
    }


    def getStocksOfProductForPurchase()
    {
        String productId = params.id
        //Get main stock
        def apiResponse = new InventoryService().getStocksOfProduct(productId)
        if (apiResponse?.status == 200)
        {
            JSONArray stockBookData = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : stockBookData)
            {
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
            if (apiResp2?.status == 200)
            {
                def addedBatches = responseArray.batchNumber
                JSONArray batches = new JSONArray(apiResp2.readEntity(String.class))
                for (Object batch : batches)
                {
                    if (!addedBatches.contains(batch.batchNumber))
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
                        stockEntry.put("packingDesc", "")
                        stockEntry.put("purcProductValue", "")
                        stockEntry.put("remainingQty", 0)
                        stockEntry.put("remainingFreeQty", 0)
                        stockEntry.put("remainingReplQty", 0)
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
        }
        else
        {
            response.status = apiResponse?.status
        }
    }

    def getTempStocksOfProductAndBatch()
    {
        def apiResponse = new InventoryService().getTempStocksOfProductAndBatch(params.id, params.batch)
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray)
            {
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
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray)
            {
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
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray)
            {
                String id = json["taxId"]
                def tax = new TaxController().show(id)
                json.put("gst", tax.taxValue)
                json.put("sgst", tax.salesSgst)
                json.put("cgst", tax.salesCgst)
                json.put("igst", tax.salesIgst)
                responseArray.put(json)
            }
            emitTempStockPool()
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
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            JSONArray responseArray = new JSONArray()
            for (JSONObject json : jsonArray)
            {
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
            String productId = jsonArray[1].toString()
            String batchNumber = jsonArray[2].toString()
            double discount = Double.parseDouble(jsonArray[8].toString())
            long saleQty = jsonArray[4]
            long saleFreeQty = jsonArray[5]
            def draftEdit = Boolean.parseBoolean(params.draftEdit)
            Boolean isEdit = false
            int i = 0
            long stockBookId = 0
            long draftProductId = 0
            for (Object obj : jsonArray)
            {
                //16 if edit, 17 if being added
                if (i == 16 && obj != null)
                {
                    isEdit = true
                }

                if (i == 25 && obj != null)
                {
                    stockBookId = obj
                    draftProductId = obj
                }
                else if (i == 26 && obj != null)
                {
                    isEdit = false
                    stockBookId = obj
                    draftProductId = obj
                }
                i++
            }
            def stockBook = null
            if (!isEdit)
            {
                //adding for first time
                if (!draftEdit)
                {
                    stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber, session.getAttribute("entityId").toString())

                    //This is for multi user
                    def tmpStockBookResponse = new InventoryService().getTempStocksOfProductAndBatch(productId, batchNumber)
                    if (tmpStockBookResponse.status == 200)
                    {
                        long remainingQty = 0
                        long remainingFreeQty = 0
                        long remainingReplQty = 0
                        JSONArray tmpStocks = new JSONArray(tmpStockBookResponse.readEntity(String.class))
                        if (tmpStocks.size() > 0)
                        {
                            for (Object tmpStock : tmpStocks)
                            {
                                if (stockBook.productId == tmpStock.productId && stockBook.batchNumber == tmpStock.batchNumber)
                                {
                                    if (remainingQty < tmpStock.remainingQty)
                                    {
                                        remainingQty = tmpStock.remainingQty
                                    }

                                    if (remainingFreeQty < tmpStock.remainingFreeQty)
                                    {
                                        remainingFreeQty = tmpStock.remainingFreeQty
                                    }

                                    if (remainingReplQty < tmpStock.remainingReplQty)
                                    {
                                        remainingReplQty = tmpStock.remainingReplQty
                                    }
                                }
                            }
                            stockBook.remainingQty = remainingQty
                            stockBook.remainingFreeQty = remainingFreeQty
                            stockBook.remainingReplQty = remainingReplQty
                        }

                    }

                }
                else
                {
                 /*   JSONArray tempStockBookData = new InventoryService().getTempStocksOfProductAndBatchAndEntityId(productId,
                            batchNumber,session.getAttribute('entityId').toString())
                    if(tempStockBookData.size()!=0 || tempStockBookData!=null)
                    {
                        JSONObject jsonObject = new JSONObject()
                        jsonObject.put("temp_stock",false)
                        respond jsonObject,formats: ['json'],status: 200;
                        return;
                    }else{*/
                        stockBook = new InventoryService().getStockBookById(stockBookId)
//                    }
                }
            }
            else
            {
                if (jsonArray[16] != 0 && !draftEdit)
                {
                    //editing while adding for first time
                    def tmpStockBook = new InventoryService().getTempStocksById(jsonArray[16])
                    stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
                }
                else
                {
                    //editing draft
                    JSONObject draftProduct = new SalesService().getSaleProductDetailsById(draftProductId.toString())
                    if (draftProduct)
                    {
                        stockBook = new InventoryService().getStocksOfProductAndBatch(draftProduct.productId.toString(), draftProduct.batchNumber, draftProduct.entityId.toString())

                        //if sale qty or free qty is edited only difference qty to be pulled in to tmp stockbook
                        //ex: draftSqty = 5 and sqty = 6, then 1 qty to be added into tmp stock from stock
                        //if draftSqty = 5 and sqty = 4, then sqty to be returned to tmpstock, will be set as negative
                        // value, because while calculation below it will become (-) * (-) = (+)
                        //if draftSqty and draftFqty = to sqty and freeQty then no need to add into temp stock
                        long draftSqty = draftProduct.sqty
                        long draftFqty = draftProduct.freeQty
                        if (draftSqty == saleQty && draftFqty == saleFreeQty)
                        {
                            //no need to add to tempstocks
                            //remove existing tempstocks
                            respond jsonArray, formats: ['json']
                            return
                        }
                        else
                        {
                            if (draftSqty == saleQty && draftFqty != saleFreeQty)
                            {
                                saleQty = 0
                            }
                            else
                            {
                                if (saleQty > draftSqty)
                                {
                                    saleQty = saleQty - draftSqty
                                }
                                else if (saleQty < draftSqty)
                                {
                                    saleQty = -(draftSqty - saleQty)
                                }
                            }

                            if (draftSqty != saleQty && draftFqty == saleFreeQty)
                            {
                                saleFreeQty = 0
                            }
                            else
                            {
                                if (saleFreeQty > draftFqty)
                                {
                                    saleFreeQty = saleFreeQty - draftFqty
                                }
                                else if (saleFreeQty < draftFqty)
                                {
                                    saleFreeQty = -(draftFqty - saleFreeQty)
                                }
                            }
                        }
                    }
                    else
                    {
                        //throw error
                        println("Selected product not in draft")
                        response.status = 400
                        return
                    }
                }
            }

            long remainingQty = stockBook.remainingQty
            long remainingFreeQty = stockBook.remainingFreeQty
            if (saleQty <= remainingQty)
            {
                remainingQty = remainingQty - saleQty

            }
            else if (saleQty > remainingQty && saleQty <= (remainingQty + remainingFreeQty))
            {
                remainingFreeQty = remainingFreeQty - (saleQty - remainingQty)
                remainingQty = 0
            }
            if (saleFreeQty <= remainingFreeQty)
            {
                remainingFreeQty = remainingFreeQty - saleFreeQty
            }
            else if (saleFreeQty > remainingFreeQty && saleFreeQty <= (remainingQty + remainingFreeQty))
            {
                remainingQty = remainingQty - (saleFreeQty - remainingFreeQty)
                remainingFreeQty = 0
            }

            //Remove Negative
            long userOrderQty = (saleQty < 0 ? -saleQty : saleQty)
            long userOrderFreeQty = (saleFreeQty < 0 ? -saleFreeQty : saleFreeQty)

            JSONObject jsonObject = new JSONObject()
            jsonObject.put("productId", jsonArray[1])
            jsonObject.put("batchNumber", jsonArray[2])
            jsonObject.put("expDate", jsonArray[3])
            jsonObject.put("remainingQty", remainingQty)
            jsonObject.put("remainingFreeQty", remainingFreeQty)
            jsonObject.put("remainingReplQty", 0)
            jsonObject.put("saleRate", jsonArray[6])
            jsonObject.put("discount", discount)
            jsonObject.put("purchaseRate", stockBook.purchaseRate)
            jsonObject.put("mrp", jsonArray[7])
            jsonObject.put("discount", jsonArray[8])
            jsonObject.put("packingDesc", jsonArray[9])
            jsonObject.put("userOrderQty", userOrderQty)
            jsonObject.put("userOrderFreeQty", userOrderFreeQty)
            jsonObject.put("userOrderReplQty", 0)
            jsonObject.put("taxId", stockBook.taxId)
            jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
            jsonObject.put("redundantBatch", "")
            jsonObject.put("originalId", stockBook.id)
            jsonObject.put("uuid", params.uuid)
            jsonObject.put("originalSqty", jsonArray[21])
            jsonObject.put("originalFqty", jsonArray[22])
            try{
                if(jsonArray[15]){
                    jsonObject.put("replacement", jsonArray[15])
                }
            }catch(Exception ignored){
                println(ignored)
                jsonObject.put("replacement",false)
            }

            JSONObject settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
            if (settings == null)
            {
                settings.put("settings", "")
            }
            if (settings?.ALLOW_SAME_BATCH == "YES")
            {
                jsonObject.put("ALLOW_SAME_BATCH", true)
            }
            else
            {
                jsonObject.put("ALLOW_SAME_BATCH", false)
            }
            //editing draft
/*            def tmp = jsonArray.indexOf(22)
            if(jsonArray.indexOf(22) > 0)
                jsonObject.put("draftSqty", jsonArray[22])
            if(jsonArray.indexOf(23) > 0)
                jsonObject.put("draftFqty", jsonArray[23])*/

            def apiResponse = new InventoryService().tempStockBookSave(jsonObject)
            if (apiResponse?.status == 200)
            {
                emitTempStockPool()
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                obj.put("temp_stock",true)
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse.status
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
            for (Object obj : jsonArray)
            {
                //15 if edit, 16 if being added
                if (i == 15 && obj != null)
                {
                    isEdit = true
                }
                else if (i == 16 && obj != null)
                {
                    isEdit = false
                }
                i++
            }
            def stockBook = null
            if (!isEdit)
            {
                stockBook = new InventoryService().getStockBookById(jsonArray[16])
            }
            else
            {
//                def tmpStockBook = new InventoryService().getTempStocksById(jsonArray[15])
                stockBook = new InventoryService().getStockBookById(Long.parseLong(jsonArray[16]))
            }

            long remainingQty = stockBook.remainingQty
            long remainingFreeQty = stockBook.remainingFreeQty
            long saleQty = jsonArray[4]
            long saleFreeQty = jsonArray[5]
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
            jsonObject.put("saleRate", 0)
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
        if (apiResponse.status == 200)
        {
            emitTempStockPool()
        }
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
        def apiResponse = new InventoryService().stocksPurchase(batch, purqty)
        if (apiResponse?.status == 200)
        {
            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
            respond jsonObject, formats: ['json'], status: 200
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
        def apiResponse = new InventoryService().stocksByProductAndBatch(batch, productId, entityId)
        if (apiResponse?.status == 200)
        {
            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
            respond jsonObject, formats: ['json'], status: 200
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
                data.put("success", "success")
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

    def emitTempStockPool()
    {
        def tempStockResp = new InventoryService().getTempStocksByUser(session.getAttribute("userId").toString())
        if (tempStockResp.status == 200)
        {
            JSONArray tempStocks = new JSONArray(tempStockResp.readEntity(String.class))
            for (Object tmpStk : tempStocks)
            {
                JSONObject product = new ProductService().getProductById(tmpStk["productId"].toString())
                tmpStk.put("productName", product.productName)
            }
            String emitLink = "/topicTempStockPool/get/" + session.getAttribute("userId")
            String message = tempStocks.toString()
            brokerMessagingTemplate.convertAndSend emitLink, message
        }
    }


    def bulkStockBookSave(){
        SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd')
        JSONArray jsonArray = new JSONArray(params.stockData)
        JSONArray unusedArray = new JSONArray()
        for(JSONObject jsonObject:jsonArray){

            def taxId = new EntityService().getTaxRegisterByValueAndEntity(jsonObject.get('8').toString(),session.getAttribute('entityId').toString())
            if(taxId)
            {
                jsonObject.put("taxId",taxId.id)
                jsonObject.put("productId", jsonObject.get('0'))
                jsonObject.put("batchNumber", jsonObject.get('1'))
                jsonObject.put("manufacturingDate", sdf.parse(jsonObject.get('2').toString()))
                jsonObject.put("expDate", sdf.parse(jsonObject.get('3').toString()))
                jsonObject.put("packingDesc", jsonObject.get('4'))
                jsonObject.put("remainingQty", jsonObject.get('5'))
                jsonObject.put("remainingFreeQty", jsonObject.get('6'))
                jsonObject.put("openingStockQty", jsonObject.get('7'))
                jsonObject.put("purchaseRate", jsonObject.get('9'))
                jsonObject.put("mrp", jsonObject.get('10'))
                jsonObject.put("saleRate", jsonObject.get('11'))
                jsonObject.put("remainingReplQty", 0)
                jsonObject.put("purcTradeDiscount", 0)
                jsonObject.put("purcProductValue", 1)
                jsonObject.put("mergedWith", 0)
                jsonObject.put("status", 0)
                jsonObject.put("syncStatus", 0)
                jsonObject.put("createdUser", 1)
                jsonObject.put("modifiedUser", 1)
                jsonObject.put("supplierId", session.getAttribute('userId').toString())
                jsonObject.put("purcSeriesId", 1)
                jsonObject.put("userId", session.getAttribute("userId").toString())
                jsonObject.put("entityId", session.getAttribute("entityId").toString())
                jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
                jsonObject.put("redundantBatch", "")
                jsonObject.put("uuid", '')
            }else{
                unusedArray.add(jsonObject)
            }
            JSONObject jsonObject1 = new InventoryService().bulkStockSave(jsonArray)
            if(jsonObject1!=null){
                respond jsonObject1, formats: ['json'], status: 200;
            }else {
                response.status = 400
            }
        }
    }
}
