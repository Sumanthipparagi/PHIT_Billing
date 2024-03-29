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

    def purchaseServiceStatus()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :purchase , action :  purchaseServiceStatus  , Ex:' + ex)
            log.error('Service :purchase , action :  purchaseServiceStatus  , Ex:' + ex)
        }

    }

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

    def showPurchaseBillDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_DATATABLE)
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


    def getPurchaseProductDetailsById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject purchaseProductDetail = new JSONObject(apiResponse.readEntity(String.class))
                return purchaseProductDetail
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }

    }


    def cancelPurchaseInvoice(String id, String entityId, String financialYear, String userId)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("userId", userId)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_CANCEL)
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
                JSONArray purchaseProductDetail = new JSONArray(apiResponse.readEntity(String.class))
                return purchaseProductDetail
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

    def getPurchaseBillByDateRange(String dateRange, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_BY_DATERANGE)
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

    def getPurchaseBillByDateRangeSupplier(String dateRange, String supplierId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("supplierId", supplierId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_BY_DATERANGE_SUPPLIER)
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

    def getPurchaseOrderByDateRange(String dateRange, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_DATERANGE)
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

    def getPurchaseOrderByDateRangeSupplier(String dateRange, String supplierId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("supplierId", supplierId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_DATERANGE_SUPPLIER)
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

    def getPurchaseRetrunByDateRange(String dateRange, String entityId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_BY_DATERANGE)
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

    def getPurchaseRetrunByDateRangeSupplier(String dateRange, String supplierId)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("supplierId", supplierId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_BY_DATERANGE_SUPPLIER)
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

    def savePurchaseOrderDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8084/")
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_SAVE)
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


    def getRecentPurchaseOrder(String financialYear, String entityId, String billStatus)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_RECENT)
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


    def showPurchaseOrderDetails(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        //WebTarget target = client.target("http://localhost:8084/");
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_DATATABLE)
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

    def getPurchaseOrderDetailsById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_SHOW+"/"+id)
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

    def getPurchaseProductDetailsByOrder(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_PRODUCT_OF_BILL+"/"+id)
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

    def cancelPurchaseOrder(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_ORDER_CANCEL)
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

    def savePurchaseTransportation(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PURCHASE_TRANSPORTATION_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :purchaseService , action :  savePurchaseTransportation  , Ex:' + ex)
            log.error('Service :purchaseService , action :  savePurchaseTransportation  , Ex:' + ex)
        }
    }

    def updatePurchaseTransportation(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PURCHASE_TRANSPORTATION_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :purchaseService , action :  updatePurchaseTransportation  , Ex:' + ex)
            log.error('Service :purchaseService , action :  updatePurchaseTransportation  , Ex:' + ex)
        }
    }


    def deletePurchaseProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_DELETE)
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


    def updatePurchaseInvoice(JSONObject jsonObject, String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_UPDATE)
                    .resolveTemplate("id", id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :salesService , action :  updateSaleInvoice  , Ex:' + ex)
            log.error('Service :salesService , action :  updateSaleInvoice  , Ex:' + ex)
        }

    }

    def getPurchaseTransportationByBill(String billId, String billType)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_TRANSPORTATION_BY_BILL)
                    .queryParam("billid",billId)
                    .queryParam("billType",billType)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :getSaleTransportationByBill , action :  show  , Ex:' + ex)
            log.error('Service :getSaleTransportationByBill , action :  show  , Ex:' + ex)
        }
    }

    def getPurchaseReturnByDateRange(String dateRange, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_BY_DATERANGE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }
    }

    /**
     * This methos is used for get data
     * @param accessToken
     * @param link
     * @return response
     */
    def getRequestWithId(String id, String link) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(new Links().API_GATEWAY);
            Response apiResponse = target.path(link + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println("API Response from server :" + apiResponse?.getStatus())
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println(ex)
        }
//        finally{
//            client.close()
//        }
    }

    def getReturnDetailsByBatchPurbillProductId(String productId, String batch, String purBill) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_PRODUCT_BATCH_BILL)
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .queryParam("batch", URLEncoder.encode(batch.toString(), "UTF-8"))
                    .queryParam("purBillId", URLEncoder.encode(purBill.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getReturnDetailsByBatchSalebillProductId  , Ex:' + ex)
            log.error('Service :SalesService , action :  getReturnDetailsByBatchSalebillProductId  , Ex:' + ex)
        }
    }

    def getPurchaseProductDetailsByProductId(String productId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_BY_PRODUCT)
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray saleProductDetail = new JSONArray(apiResponse.readEntity(String.class))
                return saleProductDetail
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getSaleProductDetailsByProductId  , Ex:' + ex)
            log.error('Service :SalesService , action :  getSaleProductDetailsByProductId  , Ex:' + ex)
        }
    }

    def getByBillBatchesProduct(String billId, String batch, String productId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_BILL_BATCH)
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

    def getPurchaseReturnById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject sampleInvoiceDetail = new JSONObject(apiResponse.readEntity(String.class))
                return sampleInvoiceDetail
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getPurchaseReturnProductDetailsByBill(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_DETAIL_BILL + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray sampleProductDetail = new JSONArray(apiResponse.readEntity(String.class))
                return sampleProductDetail
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getProducts  , Ex:' + ex)
            log.error('Service :SalesService , action :  getProducts  , Ex:' + ex)
        }
    }

    def purchaseReturnDatatable(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        //WebTarget target = client.target("http://localhost:8084/");
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :showSalesService , action :  show  , Ex:' + ex)
            log.error('Service :showSalesService , action :  show  , Ex:' + ex)
        }
    }

    def getRecentPurchaseReturn(String financialYear, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_RECENT)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    def cancelReturns(String id, String entityId, String financialYear) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_CANCEL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            if (apiResponse.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  cancelInvoice  , Ex:' + ex)
            log.error('Service :SalesService , action :  cancelInvoice  , Ex:' + ex)
        }
    }

    def deleteAllDrafts(String entityId, String userId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().DELETE_DRAFTS_PURCHASE_BILLS)
                    .queryParam("entityId", entityId)
                    .queryParam("userId", userId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            if (apiResponse.status == 200) {
                return apiResponse
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
    }


    def getPurchaseBillDraftDetails(String entityId, String userId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().GET_DRAFTS_PURCHASE_BILLS)
                    .queryParam("entityId", entityId)
                    .queryParam("userId", userId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray saleBillDraftDetails = new JSONArray(apiResponse.readEntity(String.class))
                return saleBillDraftDetails
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :SalesService , action :  getSaleDraftDetails  , Ex:' + ex)
            log.error('Service :SalesService , action :  getSaleDraftDetails  , Ex:' + ex)
        }
    }

    boolean purchaseProductDeleteCheck(String productId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_PRODUCT_DEL_CHECK)
                    .queryParam("id", productId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                if(jsonObject.get("delete"))
                    return true
                else
                    return false
            }
            else
            {
                return false
            }
        }
        catch (Exception ex) {
            System.err.println('Service :purchase , action :  purchaseProductDeleteCheck  , Ex:' + ex)
            log.error('Service :purchase , action :  purchaseProductDeleteCheck  , Ex:' + ex)
            return false
        }
    }

    boolean purchaseBatchDeleteCheck(String productId, String batchNumber) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BATCH_DEL_CHECK)
                    .queryParam("id", productId)
                    .queryParam("batchNumber", batchNumber)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                if(jsonObject.get("delete"))
                    return true
                else
                    return false
            }
            else
            {
                return false
            }
        }
        catch (Exception ex) {
            System.err.println('Service :purchase , action :  purchaseBatchDeleteCheck  , Ex:' + ex)
            log.error('Service :purchase , action :  purchaseBatchDeleteCheck  , Ex:' + ex)
            return false
        }
    }

}
