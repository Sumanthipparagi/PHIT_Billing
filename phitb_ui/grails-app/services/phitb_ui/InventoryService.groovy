package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.util.WebUtils

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
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

    def getTempStocksOfProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }
    }

    def getTempStocksOfEntity(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try {

            Response apiResponse = target
                    .path(new Links().GET_TEMP_STOCK_PRODUCT_ENTITY + "/" + id)
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
