package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class InventoryService {

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


    def stocksIncrease(String batch,String purQty, String freeQty,String reason) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK_PURCHASE+"batch/"+
                            batch+"/qty/"+purQty+"/fqty/"+freeQty+"/reason/"+reason)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
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
        WebTarget target = client.target(new Links().API_GATEWAY);

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
        WebTarget target = client.target(new Links().API_GATEWAY);

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

    def getTempStocksOfProductAndBatch(String id, String batch) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        String url = ""
        if(batch)
            url = new Links().GET_TEMP_STOCK_PRODUCT + "/product/" + id + "/batch/"+ batch
        else
            url = new Links().GET_TEMP_STOCK_PRODUCT + "/product/" + id
        try {
            Response apiResponse = target
                    .path(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
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


    def deleteTempStock(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT+"/"+id)
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
        WebTarget target = client.target(new Links().API_GATEWAY);

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
}
