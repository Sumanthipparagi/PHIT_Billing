package phitb_ui

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class ReportsService {

    def getCustomerWiseReport(String entityId, String dateRange, String financialYear, String sortBy) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_REPORTS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }

    def getDateWiseReport(String entityId, String dateRange, String financialYear, String sortBy) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_DATEWISE_REPORTS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            if(apiResponse.status == 200)
            {
                String json = apiResponse.readEntity(String.class)
                //gson used to preserve order
                JsonObject jsonObject1 = new JsonParser().parse(json).getAsJsonObject()
                return jsonObject1
            }
            else
            {
                return null
            }

        }
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }

    def getSaleReturnAreaWiseReport(String entityId, String dateRange, String financialYear, String sortBy, boolean paidInvoice) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        jsonObject.put("paidInvoice", paidInvoice)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_AREAWISE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getSaleReturnAreaWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getSaleReturnAreaWiseReport  , Ex:' + ex)
        }
    }

    def getAreaWiseReport(String entityId, String dateRange, String financialYear, String sortBy, boolean paidInvoice) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        jsonObject.put("paidInvoice", paidInvoice)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_AREAWISE_REPORTS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }

    def getConsolidatedReport(String entityId, String dateRange, String financialYear, String sortBy) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_CONSOLIDATED_REPORTS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }


    def getSalesGstRport(String entityId, String dateRange, String financialYear, String sortBy) {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("entityId",entityId)
        jsonObject.put("dateRange", dateRange)
        jsonObject.put("financialYear", financialYear)
        jsonObject.put("sortBy", sortBy)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().GST_SALES_REPORTS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }

    def getOutstandingReport(JSONArray jsonArray)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().GET_OUTSTANDING_REPORT)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(),MediaType.APPLICATION_JSON_TYPE))
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray1 = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray1
            }
            else
            {
                return null
            }

        }
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }

    def getSalesStats(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8083")
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_ENTRY_STATS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
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
        catch (Exception ex)
        {
            System.err.println('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
            log.error('Service :ReportsService , action :  getCustomerWiseReport  , Ex:' + ex)
        }
    }
}
