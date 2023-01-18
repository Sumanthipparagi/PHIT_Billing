package phitb_ui

import com.google.gson.Gson
import grails.gorm.transactions.Transactional
import org.apache.commons.lang.StringUtils
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class InventoryService {

    def inventoryServiceStatus()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().INVENTORY_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :inventory , action :  salesServiceStatus  , Ex:' + ex)
            log.error('Service :inventory , action :  salesServiceStatus  , Ex:' + ex)
        }

    }

    def getStockBookById(long id)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK + "/" +id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
               return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service : InventoryService , action :  put  , Ex:' + ex)
            log.error('Service :InventoryService , action :  put  , Ex:' + ex)
        }

    }

    def getStockBookByEntity(long id)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_BY_ENTITY + "/" +id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONArray jSONArray = new JSONArray(apiResponse.readEntity(String.class))
                return jSONArray
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service : InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
        }

    }


    def getStockActivityDateRangeAndEntity(String dateRange,long id)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_ACTIVITY_DATERANGE_ENTITY)
                    .queryParam("entityId", URLEncoder.encode(id.toString(), "UTF-8"))
                    .queryParam("daterange", URLEncoder.encode(dateRange.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONArray jSONArray = new JSONArray(apiResponse.readEntity(String.class))
                return jSONArray
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service : InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
        }

    }

    def getClosingStock(String date,long entityId, Set pids, HashMap productBatches)
    {
        String productIds = StringUtils.join(pids, ',')
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
         WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Gson gson = new Gson()
            String json = gson.toJson(productBatches);
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("date", date)
            jsonObject.put("entityId", entityId)
            jsonObject.put("productIds", productIds)
            jsonObject.put("productBatches", new JSONObject(json))
            Response apiResponse = target
                    .path(new Links().STOCK_ACTIVITY_CLOSING)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse?.status == 200)
            {
                JSONArray jSONArray = new JSONArray(apiResponse.readEntity(String.class))
                return jSONArray
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service : InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getStockBookByEntity  , Ex:' + ex)
        }

    }


//    def stockPurchase(String batch,String sqty)
//    {
//        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
//        WebTarget target = client.target(new Links().API_GATEWAY);
//        try
//        {
//            Response apiResponse = target
//                    .path(new Links().STOCK_BOOK_BY_BATCH + "/" +batch)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .get()
//            if (apiResponse?.status == 200)
//            {
//                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
//                return jsonObject
//            }
//            else
//            {
//                return null
//            }
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service : InventoryService , action :  put  , Ex:' + ex)
//            log.error('Service :InventoryService , action :  put  , Ex:' + ex)
//        }
//    }



    def stocksPurchase(String batch, String purQty) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_PURCHASE+"batch/"+ batch+"/qty/"+purQty)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }


    def stocksReturn(JSONObject jsonObject, boolean isSale = true) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = new Links().STOCK_SALE_RETURN
        if(!isSale)
            url = new Links().STOCK_PURCHASE_RETURN
        try {
            Response apiResponse = target
                    .path(url)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse

        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }


    def stocksByProductAndBatch(String batch,String productId,String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_BY_PROD_BATCH)
                    .resolveTemplate("batch", batch)
                    .resolveTemplate("productId", productId)
                    .queryParam("entityId", URLEncoder.encode(entityId.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventroyService , action :  getProducts  , Ex:' + ex)
            log.error('Service :InventroyService , action :  getProducts  , Ex:' + ex)
        }

    }

    def stockBookSave(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :InventoryService , action :  save  , Ex:' + ex)
            log.error('Service :InventoryService , action :  save  , Ex:' + ex)
        }

    }

    def bulkStockSave(JSONArray jsonArray)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BULK_SAVE_STOCK_DETAIL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(),MediaType.APPLICATION_JSON_TYPE))
            if(apiResponse?.status== 200){
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return  jsonObject1
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :InventoryService , action :  save  , Ex:' + ex)
            log.error('Service :InventoryService , action :  save  , Ex:' + ex)
        }

    }

    def updateStockBook(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK + "/"+jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : InventoryService , action :  put  , Ex:' + ex)
            log.error('Service :InventoryService , action :  put  , Ex:' + ex)
        }

    }

    def showStockBooks(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :InventoryService , action :  show  , Ex:' + ex)
            log.error('Service :InventoryService , action :  show  , Ex:' + ex)
        }

    }


    def getStocksOfProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().GET_STOCKS_OF_PRODUCT + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
        }

    }


    def getStocksOfProductSaleRetrun(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().GET_STOCKS_OF_PRODUCT_SALE_RETURN)
                    .queryParam("id", URLEncoder.encode(id.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
        }

    }

    def getTempStocksOfProductAndBatch(String id, String batch) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = ""
        if(batch)
            url = new Links().GET_TEMP_STOCK_PRODUCT + "/product/" + id + "/batch/"+ batch
        else
            url = new Links().GET_TEMP_STOCK_PRODUCT + "/product/" + id
        try {
            Response apiResponse = target
                    .path(url)
                    //.queryParam("userId",session.getAttribute("userId"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
        }

    }

    def getTempStocksOfProductAndBatchAndEntityId(String id, String batch, String entityId){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = new Links().GET_TEMP_STOCK_PRODUCT+"/product/"+id+"/batch/"+batch+"/entityid/"+entityId
        try {
            Response apiResponse = target
                    .path(url)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
        }
    }

    def getStocksOfProductAndBatch(String id, String batch, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = new Links().STOCK_BOOK + "/product/" + id + "/batch/"+ batch
        try {
            Response apiResponse = target
                    .path(url)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
        }

    }


    def getStocksIncrease(String batch,String qty, String fqty, String reason) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = new Links().STOCK_SALE_RETURN + "/batch/" + batch + "/qty/"+ qty+"/fQty/"+fqty+"/reason/"+reason
        try {
            Response apiResponse = target
                    .path(url)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getStocksOfProductAndBatch  , Ex:' + ex)
        }

    }

//    def getTempStocksOfEntity(String id) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(new Links().API_GATEWAY);
//
//        try {
//
//            Response apiResponse = target
//                    .path(new Links().GET_TEMP_STOCK_PRODUCT_ENTITY + "/" + id)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .get()
//
//            return apiResponse
//        }
//        catch (Exception ex) {
//            System.err.println('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
//            log.error('Service :InventoryService , action :  getBatchesOfProduct  , Ex:' + ex)
//        }
//    }

    //Temp Stock Book Save
    def tempStockBookSave(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TEMP_STOCK_BOOK_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :InventoryService , action :  save  , Ex:' + ex)
            log.error('Service :InventoryService , action :  save  , Ex:' + ex)
        }

    }




    def StockBookPurchase(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        try
        {

            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_PURCHASE+"/batch/"+jsonObject.batch+"/qty/"+jsonObject.qty)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :InventoryService , action :  save  , Ex:' + ex)
            log.error('Service :InventoryService , action :  save  , Ex:' + ex)
        }

    }


    def deleteTempStock(String id, boolean updateTempStock = true) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT+"/"+id)
                    .queryParam("updateTempStock", updateTempStock)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def deleteStockBook(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def getTempStocks() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def getTempStocksById(long id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def getTempStocksByUser(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().TEMP_STOCK_BOOK_BY_USER + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def getStocksByUser(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_BY_USER + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }

    def calculateSaleQty(long saleQty, long saleFreeQty, long draftSqty, long draftFqty)
    {
        if (draftSqty == saleQty && draftFqty != saleFreeQty) {
            saleQty = 0
        } else {
            if (saleQty > draftSqty) {
                saleQty = saleQty - draftSqty
            } else if (saleQty < draftSqty) {
                saleQty = -(draftSqty - saleQty)
            }
        }
        return saleQty
    }

    def calculateSaleFreeQty(long saleQty, long saleFreeQty, long draftSqty, long draftFqty)
    {
        if (draftSqty != saleQty && draftFqty == saleFreeQty) {
            saleFreeQty = 0
        } else {
            if (saleFreeQty > draftFqty) {
                saleFreeQty = saleFreeQty - draftFqty
            } else if (saleFreeQty < draftFqty) {
                saleFreeQty = -(draftFqty - saleFreeQty)
            }
        }
        return saleFreeQty
    }

    def updateBatchDetailsToStock(JSONObject jsonObject){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().UPDATE_STOCK_BATCH_DETAILS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  updateBatchDetailsToStock  , Ex:' + ex)
            log.error('Service :InventoryService , action :  updateBatchDetailsToStock  , Ex:' + ex)
        }
    }

}
