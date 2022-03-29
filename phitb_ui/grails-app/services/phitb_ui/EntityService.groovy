package phitb_ui

import grails.gorm.transactions.Transactional
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class EntityService {

    def saveEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveEntity  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  showEntity  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getTermsContionsByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().TERMS_CONDITIONS + "/"+id)
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
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    def putEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  putEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntity  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_DELETE)
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
        finally{
            client.close()
        }
    }


//User Register
    def saveUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  save  , Ex:' + ex)
            log.error('Service :EntityService , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getAuth(String username)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ENTITY_AUTH + "/"+username)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                return obj
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getAuth  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAuth  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getUser(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                return obj
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUser  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }
    /**
     *
     * @param jsonObject
     * @return
     */
    def showUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  showUser  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putUser(JSONObject jsonObject, MultipartFile multipartFile)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        File file = convert(multipartFile)
        try
        {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  putUser  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteUser  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }



    //Customer Group Register
    def saveCustomerGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showCustomerGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action : showCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action : showCustomerGroup  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putCustomerGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : EntityService, action :  putCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  putCustomerGroup  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteCustomerGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteCustomerGroup  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Day End Master
    def saveDayEnd(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showDayEnd(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putDayEnd(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  putDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  putDayEnd  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteDayEnd(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Financial year
    def saveFinancialYear(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveFinancialYear  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def getFinancialYearByEntity(String entityId)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try
        {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_ENTITY + "/" + entityId)
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
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showFinancialYear(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putFinancialYear(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : EntityService, action :  putFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  putFinancialYear  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteFinancialYear(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteFinancialYear  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Region Master
    def saveRegionMaster(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRegionMaster  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRegionMaster(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRegionMaster  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putRegionMaster(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRegionMaster  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRegionMaster(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRegionMaster  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Route Master
    def saveRoute(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRoute  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRoute(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRoute  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putRoute(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRoute  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRoute(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRoute  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Role Master
    def saveRole(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ROLE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRole  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRole(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROLE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRole  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putRole(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROLE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRole  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRole(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().ROLE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRole  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    //Tax
    def saveRule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRule  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRule  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putRule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  putRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRule  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRule  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Tax
    def saveTax(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTax  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTax(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTax  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putTax(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTax  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTax(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTax  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Territory
    def saveTerritory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTerritory  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTerritory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTerritory  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putTerritory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : EntityService, action :  putTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTerritory  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTerritory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTerritory  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Terms Condition
    def saveTermsCondition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TC_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTermsCondition  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTermsCondition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putTermsCondition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : EntityService, action :  putTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTermsCondition  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTermsCondition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteTermsCondtion  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTermsCondtion  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Series Master
    def saveSeries(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveSeries  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showSeries(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  showSeries  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putSeries(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : EntityService, action :  putSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  putSeries  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteSeries(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteSeries  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Series Master
    def saveServiceType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveServiceType  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showServiceType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  showServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  showServiceType  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putServiceType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  putServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  putServiceType  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteServiceType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
       
        try
        {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteServiceType  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    def getEntity() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW)
                    .queryParam("offset", "0") //TODO:to remove
                    .queryParam("limit", "10000")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getEntityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getEntityByAffiliates(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_AFFILIATE + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityByAffiliates  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityByAffiliates  , Ex:' + ex)
        }

    }

/*    def getEntityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
    }*/


    def getTaxRegister(String id = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String link = new Links().TAX_MASTER_SHOW
        if(id)
            link += "/"+id

        try {

            Response apiResponse = target
                    .path(link)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getTaxRegister  , Ex:' + ex)
            log.error('Service :EntityService , action :  getTaxRegister  , Ex:' + ex)
        }

    }

    def getEntityType() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().ENTITY_TYPE_MASTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityType  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityType  , Ex:' + ex)
        }

    }

    def getUserRegister() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUserRegister  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUserRegister  , Ex:' + ex)
        }

    }

    def getSeries(String id = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String uri = new Links().SERIES_MASTER_SHOW
        if(id)
            uri += "/"+id
        try {

            Response apiResponse = target
                    .path(uri)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  getSeries  , Ex:' + ex)
        }

    }

    def getSeriesById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  getSeries  , Ex:' + ex)
        }

    }

    def getSeriesByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_BY_ENTITY + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getSeriesByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getSeriesByEntity  , Ex:' + ex)
        }

    }


    def convert(MultipartFile multipartFile)
    {
        String fileName = UtilsService.uploadFile('user/', multipartFile, null)
        File file = new File(new FileManagementService().getApplicationPath() + 'user' + File.separator + fileName)
        return file
    }


    def getRoles() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().ROLE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getRoles  , Ex:' + ex)
            log.error('Service :EntityService , action :  getRoles  , Ex:' + ex)
        }

    }

    def getFeatures(String query = null) {
        String url = new Links().FEATURE_SHOW
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(url).queryParam("query", query)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getFeatures  , Ex:' + ex)
            log.error('Service :EntityService , action :  getFeatures  , Ex:' + ex)
        }

    }

    def getFeature(String id) {
        String url = new Links().FEATURE_SHOW + "/" + id
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getFeature  , Ex:' + ex)
            log.error('Service :EntityService , action :  getFeature  , Ex:' + ex)
        }

    }

    def getFeatureList(String ids) {
        String url = new Links().FEATURE_SHOW + "/list/" + ids
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getFeatureList  , Ex:' + ex)
            log.error('Service :EntityService , action :  getFeatureList  , Ex:' + ex)
        }

    }

    def getAllAccount() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAccountById  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAccountById  , Ex:' + ex)
        }

    }

    def saveAccountRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SAVE)
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

    def getAllAccountById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAccountById  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAccountById  , Ex:' + ex)
        }

    }

    def getAccountById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAccountById  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAccountById  , Ex:' + ex)
        }

    }


    def getAllAccountByEntity(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_BY_ENTITY + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAllAccountByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAllAccountByEntity  , Ex:' + ex)
        }

    }


    def updateAccountBalance(String amount, String entityId, String id, boolean add)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("amount", amount)
            jsonObject.put("entityId", entityId)
            jsonObject.put("id", id)
            jsonObject.put("add", add)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_UPDATE_BALANCE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :entityService , action :  updateAccountBalance  , Ex:' + ex)
            log.error('Service :entityService , action :  updateAccountBalance  , Ex:' + ex)
        }

    }

}
