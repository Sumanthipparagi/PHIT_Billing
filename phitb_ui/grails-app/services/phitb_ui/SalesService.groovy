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
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class SalesService {

    def getSaleBillDetails() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }
    }

    def saveSaleBill(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SAVE)
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

    def putSaleBill(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : Godown , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }
    }


    def saveSaleProductDetail(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
    }

    def getRecentSaleBill(String financialYear, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_RECENT)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
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
    }

    def getSaleBillDetailsById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject saleBillDetail = new JSONObject(apiResponse.readEntity(String.class))
                return saleBillDetail
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getSaleProductDetails(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_OF_BILL+"/"+id)
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
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }
    }
}
