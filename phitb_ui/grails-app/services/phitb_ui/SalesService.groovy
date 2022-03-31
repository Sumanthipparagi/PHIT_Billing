package phitb_ui

import grails.converters.JSON
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
class SalesService
{

    def getSaleBillDetails()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllSettledBillsByCustomer(String id,String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SETTLED + "/" + id)
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllUNSettledBillsByCustomer(String id,String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UNSETTLED + "/" + id)
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
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

    def saveSaleOrder(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_ORDER_SAVE)
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

    def saveSaleRetrun(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_SAVE)
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
                    .put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
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
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def getRecentSaleBill(String financialYear, String entityId, String billStatus)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_RECENT)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
                    .queryParam("billStatus", billStatus)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse.status == 200)
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

    def getSaleBillDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONObject saleBillDetail = new JSONObject(apiResponse.readEntity(String.class))
                return saleBillDetail
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getSaleProductDetails(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_OF_BILL + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONArray saleProductDetail = new JSONArray(apiResponse.readEntity(String.class))
                return saleProductDetail
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getSaleInvoiceById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getSaleInvoice()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().SALE_BILL_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getSchemeConfiguration(String productId, String batchNumber)
    {
        String url = new Links().SALE_SCHEME_CONFIG_GET_PRODUCT_BATCH
        url = url.replace("\$productId", productId)
        url = url.replace("\$batchNumber", batchNumber)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse.status == 200)
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
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }

    }


    /**
     * This methos is used for get data
     * @param accessToken
     * @param link
     * @return response
     */
    def getRequestWithId(String id, String link)
    {
        Client client = ClientBuilder.newClient();
        try
        {
            WebTarget target = client.target(new Links().API_GATEWAY);
            Response apiResponse = target.path(link + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println("API Response from server :" + apiResponse?.getStatus())
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println(ex)
        }
//        finally{
//            client.close()
//        }
    }


    def getRequestWithIdList(ArrayList<Long> idList, String link)
    {
        Client client = ClientBuilder.newClient()
        try
        {
            WebTarget target = client.target(new Links().API_GATEWAY);
            Response apiResponse = target.path(link)
                    .resolveTemplate("salebillsIds", idList.toString())
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

    def showSalesService(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :showSalesService , action :  show  , Ex:' + ex)
            log.error('Service :showSalesService , action :  show  , Ex:' + ex)
        }

    }

    def saveScheme(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_SCHEME_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :SaleService , action :  saveEntity  , Ex:' + ex)
            log.error('Service :SaleService , action :  saveEntity  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def showScheme(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_SCHEME_DATATABLE)
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


    def putScheme(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_SCHEME_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  putEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntity  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteScheme(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_SCHEME_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteEntity  , Ex:' + ex)
        }

    }

    def getSchemeById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_SCHEME_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
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
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }


    def getReason()
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().REASON_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONArray JSONArray = new JSONArray(apiResponse.readEntity(String.class))
                return JSONArray
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }


    def getSaleBillByCustomer(String custid)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_CUSTOMER)
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


    def getReasons() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().GET_REASON)
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
            System.err.println('Service :SalesService , action :  getReasons  , Ex:' + ex)
            log.error('Service :SalesService , action :  getReasons  , Ex:' + ex)
        }
    }

    def cancelInvoice(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_CANCEL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  cancelInvoice  , Ex:' + ex)
            log.error('Service :SalesService , action :  cancelInvoice  , Ex:' + ex)
        }
    }
}
