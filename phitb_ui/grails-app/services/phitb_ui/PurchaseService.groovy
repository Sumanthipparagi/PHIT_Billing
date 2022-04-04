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

    }

    def getPurchaseBillBySupplier(String supplierId)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_SUPPLIER)
                    .resolveTemplate("supplierId", supplierId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :PurchaseService , action :  getPurchaseBillBySupplier  , Ex:' + ex)
            log.error('Service :PurchaseService , action :  getPurchaseBillBySupplier  , Ex:' + ex)
        }
    }


    def getRequestWithIdList(ArrayList<Long> idList, String link)
    {
        try
        {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(new Links().API_GATEWAY);
            Response apiResponse = target.path(link)
                    .resolveTemplate("purbillsIds", idList.toString())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println("API Response from server :" + apiResponse?.getStatus())
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println(ex)
        }
    }

    def savePurchaseRetrun(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    def getPurchaseBillByCustomer(String custid)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_CUSTOMER)
                    .resolveTemplate("custid", custid)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }
}
