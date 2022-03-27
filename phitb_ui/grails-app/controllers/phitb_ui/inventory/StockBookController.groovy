package phitb_ui.inventory

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
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
            def apiResponse = new InventoryService().showStockBooks(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
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
                else if(i == 16 && obj != null)
                    isEdit = false
                i++
            }
            def stockBook = null
            if(!isEdit)
                stockBook = new InventoryService().getStockBookById(jsonArray[16])
            else {
                def tmpStockBook = new InventoryService().getTempStocksById(jsonArray[15])
                stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
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

    def stockIncrease()
    {
        def batch = params.batch
        def purqty = params.sqty
        def freeqty = params.fqty
        def reason = params.reason
        def apiResponse = new InventoryService().stocksIncrease(batch,purqty,freeqty,reason)
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

}
