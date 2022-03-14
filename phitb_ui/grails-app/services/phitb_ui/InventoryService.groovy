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

    //Temp Stock Book Save
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
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
    }

    def updateStockBook(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().STOCK_BOOK)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
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
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }
    }


    def getStocksOfProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try {

            Response apiResponse = target
                    .path(new Links().GET_STOCKS_OF_PRODUCT + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }
    }

    def getTempStocksOfProductAndBatch(String id, String batch) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT_BATCH + "/product/" + id + "/batch/"+ batch)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
            log.error('Service:InventoryService , action :  getTempStocksOfProduct  , Ex:' + ex)
        }
    }

//    def getTempStocksOfEntity(String id) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(new Links().API_GATEWAY);
//        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
//            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
//            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
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
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
    }


    def StockBookPurchase(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        try
        {
            print(jsonObject)

            JSONObject objects = new JSONObject()
            JSONArray key = objects.names ();

//            jsonObject.items.each {
//                println "item:"+it


//            }
//            Response apiResponse = target
//                    .path(new Links().STOCK_BOOK_PURCHASE+"/batch/"+jsonObject.batch+"/qty/"+jsonObject.qty)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
////                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
//                    .post(Entity.form(form))
//            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
    }




    def getTempStocks(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }
    }


}
