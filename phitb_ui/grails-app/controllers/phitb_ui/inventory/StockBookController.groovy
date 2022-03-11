package phitb_ui.inventory

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.entity.TaxController

class StockBookController {

    def index() { }

    def getStocksOfProduct()
    {
        def apiResponse = new InventoryService().getStocksOfProduct(params.id)
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


    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new InventoryService().tempStockBookSave(jsonObject)
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



}
