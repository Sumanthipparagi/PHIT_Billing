package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class InventoryService {

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


}
