package phitb_ui.sales


import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController

class SampleConversionController
{

    def sampleInvoicing()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        String userId = session.getAttribute("userId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        render(view: '/sales/sampleConversion/sampleInvoicing', model: [customers   : customers, divisions: divisions, series: series,
                                                                        salesmanList: salesmanList, priorityList: priorityList])
    }

    def sampleConversion()
    {
        try
        {
            String entityId = session.getAttribute("entityId")?.toString()
            JSONArray productList = new ProductService().getProductsByEntityId(entityId)
            render(view: '/sales/sampleConversion/sample-conversion', model: [productList: productList])
        }
        catch (Exception ex)
        {
            println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
        }
    }


    def saveSampleConversion()
    {
        try
        {
            println(params)
            String entityId = session.getAttribute("entityId")?.toString()
//            Saleable Stock
            def saleableStock = new InventoryService().getStocksOfProductAndBatch(params.saleableProduct, params.saleableBatch, entityId)
            if(saleableStock)
            {
                long saleableQty = Long.parseLong(saleableStock.remainingQty.toString()) - Long.parseLong(params.sampleQty.toString())
                saleableStock.put("remainingQty", saleableQty)
                saleableStock.put("remainingFreeQty", saleableStock.get("remainingFreeQty"))
                saleableStock.put("remainingReplQty", saleableStock.get("remainingReplQty"))
                saleableStock.put("uuid", UUID.randomUUID())
                def saleableStockUpdate = new InventoryService().updateStockBook(saleableStock)
                if(saleableStockUpdate?.status!= 200)
                {
                    response.status = 400
                    return
                }
            }
            else {

                response.status = 400
                return
            }

//            Sample Stock
            def sampleStock = new InventoryService().getStocksOfProductAndBatch(params.sampleProduct, params.sampleBatch, entityId)
            if(sampleStock)
            {
                long sampleQty = Long.parseLong(sampleStock.remainingQty.toString()) + Long.parseLong(params.sampleQty.toString())
                sampleStock.put("remainingQty", sampleQty)
                sampleStock.put("remainingFreeQty", sampleStock.get("remainingFreeQty"))
                sampleStock.put("remainingReplQty", sampleStock.get("remainingReplQty"))
                def sampleStockUpdate = new InventoryService().updateStockBook(sampleStock)
                if(sampleStockUpdate?.status!= 200)
                {
                    response.status = 400
                    return
                }
            }
            else {

                response.status = 400
                return
            }
            JSONObject sampleConverisonLogs = new JSONObject()
            sampleConverisonLogs.put("saleableProductId",params.saleableProduct)
            sampleConverisonLogs.put("saleableBatch",params.saleableBatch)
            sampleConverisonLogs.put("saleableQty",params.saleableQty)
            sampleConverisonLogs.put("sampleProductId",params.sampleProduct)
            sampleConverisonLogs.put("sampleBatch",params.sampleBatch)
            sampleConverisonLogs.put("sampleQty",params.sampleQty)
            def samplelogs = new SalesService().saveSampleConversionLogs(sampleConverisonLogs)
            if(samplelogs?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(samplelogs.readEntity(String.class))
                println(jsonObject)
            }
            respond sampleConverisonLogs, formats: ['json'],status: 200
        }
        catch (Exception ex)
        {
            println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
        }
    }



    def saveSampleInvoicing()
    {

    }
}
