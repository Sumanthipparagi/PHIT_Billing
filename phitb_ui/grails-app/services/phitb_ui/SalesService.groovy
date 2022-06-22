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

    /*
    contains both sale bill and products
     */
    def saveSaleInvoice(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_INVOICE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
            log.error('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
        }

    }


    def getSaleBillByDateRange(String dateRange, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().SALE_BILL_BY_DATERANGE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }

    }

    def getSaleReturnByDateRange(String dateRange, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_BY_DATERANGE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }

    }



    /*
contains both sale bill and products
 */
    def updateSaleInvoice(JSONObject jsonObject, String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_INVOICE_UPDATE + "/" +id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  updateSaleInvoice  , Ex:' + ex)
            log.error('Service :salesService , action :  updateSaleInvoice  , Ex:' + ex)
        }

    }


    /*
   contains both sale bill and products
    */
    def saveSaleOrder(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
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
            System.err.println('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
            log.error('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
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

    def updateSaleBill(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UPDATE)
                    .resolveTemplate("id",jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def updateSaleBillIRNDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UPDATE_IRN)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
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

    def saveSaleRetrunDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_DETAIL_SAVE)
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

    def saveSaleProductDetailList(JSONArray jsonArray)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonArray)
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_SAVE_LIST)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  saveSaleProductDetailList  , Ex:' + ex)
            log.error('Service :salesService , action :  saveSaleProductDetailList  , Ex:' + ex)
        }
    }

    def updateSaleProductDetail(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_UPDATE)
                    .resolveTemplate("id",jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
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


    def getRecentGTN(String financialYear, String entityId, String billStatus)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_RECENT)
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

    def approveGTN(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_APPROVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            if (apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
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

    def cancelGTN(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_CANCEL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            if (apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
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

    def getRecentSaleReturn(String financialYear, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_RECENT)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
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

    def getRecentSaleOrder(String financialYear, String entityId, String billStatus)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_ORDER_RECENT)
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

    def getSaleOrderDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_ORDER_SHOW + "/" + id)
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

    def getSaleReturnDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_SHOW + "/" + id)
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

    def getDraftSaleBillDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().DRAFT_SALE_BILL_SHOW)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
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
            System.err.println('Service :SalesService , action :  getDraftSaleBillDetailsById  , Ex:' + ex)
            log.error('Service :SalesService , action :  getDraftSaleBillDetailsById  , Ex:' + ex)
        }

    }

    def getSaleProductDetailsByBill(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_OF_BILL+"/"+id)
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


    def getSaleProductDetailsByOrder(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_ORDER+"/"+id)
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

    def showSaleOrder(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_ORDER_DATATABLE)
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

    def getSaleRetrunDetailsByBill(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_DETAIL_BILL+"/"+id)
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

    def getSaleProductDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONObject saleProductDetail = new JSONObject(JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
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


    def getSaleProductDetailsByProductId(String productId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_BY_PRODUCT)
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
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
            System.err.println('Service :SalesService , action :  getSaleProductDetailsByProductId  , Ex:' + ex)
            log.error('Service :SalesService , action :  getSaleProductDetailsByProductId  , Ex:' + ex)
        }

    }


    def getByBillBatchesProduct(String billId,String batch,String productId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_BILL_BATCH)
                    .queryParam("billId", URLEncoder.encode(billId.toString(), "UTF-8"))
                    .queryParam("batch", URLEncoder.encode(batch.toString(), "UTF-8"))
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
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
            Response apiResponse = target.path(link+"/"+id)
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


    def getSaleBillByCustomer(String custid, String financialYear, String entityId)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_CUSTOMER)
                    .resolveTemplate("custid", custid)
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
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
        WebTarget target = client.target(new Links().API_GATEWAY)
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

    def cancelOrder(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SALE_ORDER_CANCEL)
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

    def cancelReturns(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_CANCEL)
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

    def deleteSaleProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SALE_PRODUCT_DELETE)
                    .resolveTemplate("id",id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
            log.error('Service :InventoryService , action :  getTempStocks  , Ex:' + ex)
        }

    }


    def getReturnDetailsByBatchSalebillProductId(String productId, String batch, String saleBill)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_PRODUCT_BATCH_BILL)
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .queryParam("batch", URLEncoder.encode(batch.toString(), "UTF-8"))
                    .queryParam("salebill", URLEncoder.encode(saleBill.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getReturnDetailsByBatchSalebillProductId  , Ex:' + ex)
            log.error('Service :SalesService , action :  getReturnDetailsByBatchSalebillProductId  , Ex:' + ex)
        }
    }

    def salesReturnDatatable(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_DATATABLE)
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


    /*
 contains both sale bill and products
  */
    def saveGTN(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().GTN_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
            log.error('Service :salesService , action :  saveSaleInvoice  , Ex:' + ex)
        }
    }


    def showGTN(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_DATATABLE)
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

    def getGTNDetailsById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_SHOW + "/" + id)
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

    def getgtnProductDetailsByGtn(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_PRODUCTS_BY_GTN+"/"+id)
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

    def getGTNById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().GTN_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONObject gtn = new JSONObject(apiResponse.readEntity(String.class))
                return gtn
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

}
