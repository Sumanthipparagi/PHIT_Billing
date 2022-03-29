package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class PurchaseService {

    def savePurchaseProductDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def savePurchaseBillDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getPurchaseProductDetails(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getRecentPurchaseBill(String financialYear, String entityId, String billStatus)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_RECENT)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
                    .queryParam("billStatus", billStatus)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if(apiResponse.status == 200)
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
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getPurchaseBillDetailsById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject purchaseBillDetail = new JSONObject(apiResponse.readEntity(String.class))
                return purchaseBillDetail
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :PurchaseService , action :  getProducts  , Ex:' + ex)
            log.error('Service :PurchaseService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getPurchaseProductDetailsByBill(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_OF_BILL+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray saleProductDetail = new JSONArray(apiResponse.readEntity(String.class))
                return saleProductDetail
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :PurchaseService , action :  getProducts  , Ex:' + ex)
            log.error('Service :PurchaseService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

}
