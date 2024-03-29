package phitb_ui

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class EntityService {

    def entityServiceStatus() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :entity , action :  entityServiceStatus  , Ex:' + ex)
            log.error('Service :sales , action :  entityServiceStatus  , Ex:' + ex)
        }

    }

    def saveEntity(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveEntity  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showEntity(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  showEntity  , Ex:' + ex)
        }

    }

    def showParentEntities(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_PARENT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showParentEntities  , Ex:' + ex)
            log.error('Service :EntityService , action :  showParentEntities  , Ex:' + ex)
        }

    }


    def getTermsContionsByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().TERMS_CONDITIONS + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }


    def putEntity(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntity  , Ex:' + ex)
        }

    }


    def updatePassword(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().UPDATE_PASSWORD)
                    .resolveTemplate("id", jsonObject.id.toString())
                    .resolveTemplate("password", jsonObject.new_password.toString())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntity  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def userNameExists(String username) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_EXISTS)
                    .queryParam("username", URLEncoder.encode(username, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  showUser  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteEntity(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteEntity  , Ex:' + ex)
        }

    }


    //User Register
    def saveUser(JSONObject jsonObject) {
        if (!jsonObject.has("reportTo"))
            jsonObject.put("reportTo", 0)

        if (!jsonObject.has("referredBy"))
            jsonObject.put("referredBy", 0)

        if (!jsonObject.has("referenceRelation"))
            jsonObject.put("referenceRelation", "")

        if (jsonObject.has("department")) {
            String department = jsonObject.get("department")
            if (department.equalsIgnoreCase("--SELECT--")) {
                jsonObject.put("department", 0)
            }
        }

        if (jsonObject.has("entityRoute")) {
            JSONArray rIds = new JSONArray()
            def routeIds = jsonObject.get("entityRoute")
            for (Object rt : routeIds) {
                rIds.put(rt)
            }
            jsonObject.put("entityRoute", rIds)
        }

        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  save  , Ex:' + ex)
            log.error('Service :EntityService , action :  save  , Ex:' + ex)
        }

    }

    def getAuth(String username) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_AUTH + "/" + username)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                return obj
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAuth  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAuth  , Ex:' + ex)
        }

    }

    def getUser(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                return obj
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUser  , Ex:' + ex)
        }

    }
    /**
     *
     * @param jsonObject
     * @return
     */
    def showUser(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  showUser  , Ex:' + ex)
        }

    }

    def putUser(JSONObject jsonObject) {

        if (jsonObject.has("route")) {
            JSONArray rIds = new JSONArray()
            def routeIds = jsonObject.get("route")
            for (Object rt : routeIds) {
                rIds.put(rt)
            }
            jsonObject.put("route", rIds)
        }

        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  putUser  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteUser(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteUser  , Ex:' + ex)
        }

    }


    //Customer Group Register
    def saveCustomerGroup(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showCustomerGroup(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action : showCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action : showCustomerGroup  , Ex:' + ex)
        }

    }

    def putCustomerGroup(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  putCustomerGroup  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteCustomerGroup(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteCustomerGroup  , Ex:' + ex)
        }

    }


    //Day End Master
    def saveDayEnd(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
        }

    }


    def getDayEndByEntity(String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_ENTITY)
                    .resolveTemplate("id", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                return jsonArray
            } else {
                return []
            }

        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showDayEnd(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
        }

    }

    def putDayEnd(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  putDayEnd  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteDayEnd(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
        }

    }


    //HQ areas
    def saveHqArea(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().HQ_AREA_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showHqArea(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().HQ_AREA_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  showDayEnd  , Ex:' + ex)
        }

    }

    def putHqArea(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().HQ_AREA_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  putDayEnd  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteHqArea(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().HQ_AREA_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteDayEnd  , Ex:' + ex)
        }

    }

    def getHqAreaByEntity(String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().HQ_AREA_SHOW_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
        }

    }


    //Entity IRN
    def saveEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8088")
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveEntityIRN  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveEntityIRN  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showEntityIRN  , Ex:' + ex)
            log.error('Service :EntityService , action :  showEntityIRN  , Ex:' + ex)
        }

    }

    def putEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        // WebTarget target = client.target("http://localhost:8088")
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putEntityIRN  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntityIRN  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteEntityIRN  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteEntityIRN  , Ex:' + ex)
        }

    }


    //Financial year
    def saveFinancialYear(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveFinancialYear  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def getFinancialYearByEntity(String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showFinancialYear(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  showFinancialYear  , Ex:' + ex)
        }

    }

    def putFinancialYear(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  putFinancialYear  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteFinancialYear(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().FINANCIAL_YEAR_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteFinancialYear  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteFinancialYear  , Ex:' + ex)
        }

    }


    //Region Master
    def saveRegionMaster(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRegionMaster  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRegionMaster(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRegionMaster  , Ex:' + ex)
        }

    }

    def putRegionMaster(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRegionMaster  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRegionMaster(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().REGION_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteRegionMaster  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRegionMaster  , Ex:' + ex)
        }

    }


    //Route Master

    def getRouteByEntity(String id) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_GET_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
               JSONArray array = new JSONArray(apiResponse.readEntity(String.class))
                return array
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getRouteByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getRouteByEntity  , Ex:' + ex)
        }

    }

    def saveRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRoute  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRoute  , Ex:' + ex)
        }

    }


    def getRouteList() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :systemService , action :  getZoneList  , Ex:' + ex)
            log.error('Service :systemService , action :  getZoneList  , Ex:' + ex)
        }

    }

    def putRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRoute  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROUTE_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRoute  , Ex:' + ex)
        }

    }


    //Role Master
    def saveRole(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ROLE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRole  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRole(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROLE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRole  , Ex:' + ex)
        }

    }

    def putRole(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROLE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRole  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRole(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ROLE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteRole  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRole  , Ex:' + ex)
        }

    }

    //Tax
    def saveRule(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveRule  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showRule(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  showRule  , Ex:' + ex)
        }

    }

    def putRule(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  putRule  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteRule(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().RULE_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteRule  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteRule  , Ex:' + ex)
        }

    }


    //Tax
    def saveTax(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTax  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTax(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTax  , Ex:' + ex)
        }

    }

    def putTax(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTax  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTax(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TAX_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteTax  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTax  , Ex:' + ex)
        }

    }


    //Territory
    def saveTerritory(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTerritory  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTerritory(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTerritory  , Ex:' + ex)
        }

    }

    def putTerritory(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTerritory  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTerritory(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TERRITORY_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteTerritory  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTerritory  , Ex:' + ex)
        }

    }


    //Terms Condition
    def saveTermsCondition(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TC_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveTermsCondition  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showTermsCondition(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
        }

    }

    def getTermsAndConditionsById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class));
                return jsonObject1
            } else {
                return null;
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  showTermsCondition  , Ex:' + ex)
        }
    }

    def putTermsCondition(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putTermsCondition  , Ex:' + ex)
            log.error('Service :EntityService , action :  putTermsCondition  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTermsCondition(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TC_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteTermsCondtion  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteTermsCondtion  , Ex:' + ex)
        }

    }


    //Series Master
    def saveSeries(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveSeries  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showSeries(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  showSeries  , Ex:' + ex)
        }

    }

    def putSeries(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  putSeries  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteSeries(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERIES_MASTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteSeries  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteSeries  , Ex:' + ex)
        }

    }


    //Series Master
    def saveServiceType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveServiceType  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showServiceType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  showServiceType  , Ex:' + ex)
        }

    }

    def putServiceType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  putServiceType  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteServiceType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().SERVICE_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteServiceType  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteServiceType  , Ex:' + ex)
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

    def getEntityById(long id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
//        WebTarget target = client.target("http://localhost:8088");
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                String responseString = apiResponse.readEntity(String.class);
                JSONObject result = new JSONObject(responseString); // Use JSONObject here
                return result;
            } else
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


    JSONArray getTaxesByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().TAX_MASTER_BY_ENTITY)
                    .resolveTemplate("id", id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getTaxesByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getTaxesByEntity  , Ex:' + ex)
            return null
        }
    }


    def getTaxRegister(String id = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String link = new Links().TAX_MASTER_SHOW
        if (id)
            link += "/" + id

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


    def getTaxRegisterByValueAndEntity(String taxValue, String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().TAX_REGISTER_BY_VALUE_ENTITY)
                    .queryParam('entityId', entityId)
                    .queryParam('taxValue', taxValue)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getTaxRegisterByValueAndEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getTaxRegisterByValueAndEntity  , Ex:' + ex)
        }
    }


    def getTaxes() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String link = new Links().TAX_MASTER_SHOW
        try {

            Response apiResponse = target
                    .path(link)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
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
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityType  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityType  , Ex:' + ex)
        }

    }


    def getEntityTypeById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().ENTITY_TYPE_MASTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityType  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityType  , Ex:' + ex)
        }

    }

    def getParentEntities(String affiliateId = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        String url = new Links().ENTITY_REGISTER_PARENT
//        if (affiliateId)
//            url += "?affiliateId=" + affiliateId
        try {

            if (affiliateId) {
                Response apiResponse = target
                        .path(url)
                        .queryParam('affiliateId', affiliateId)
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get()
                return apiResponse
            } else {
                Response apiResponse = target
                        .path(url)
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get()
                return apiResponse
            }

        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getParentEntities  , Ex:' + ex)
            log.error('Service :EntityService , action :  getParentEntities  , Ex:' + ex)
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

    def getUserRegisterByEntity(String entityId, String roleId = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            if(roleId == null) {
                Response apiResponse = target
                        .path(new Links().USER_REGISTER_SHOW_BY_ENTITY + "/" + entityId)
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get()

                return apiResponse
            }
            else
            {
                Response apiResponse = target
                        .path(new Links().USER_REGISTER_SHOW_BY_ENTITY + "/" + entityId)
                        .queryParam("roleId", roleId)
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get()

                return apiResponse
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUserRegisterByEntity  , Ex:' + ex)
        }

    }

    def getSeries(String id = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        String uri = new Links().SERIES_MASTER_SHOW
        if (id)
            uri += "/" + id
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
                    .path(new Links().SERIES_MASTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
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
                    .path(new Links().SERIES_MASTER_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getSeriesByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getSeriesByEntity  , Ex:' + ex)
        }

    }


    def convert(MultipartFile multipartFile) {
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

            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
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
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
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
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
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
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
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

    def saveAccountRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def showAccountRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  showUser  , Ex:' + ex)
        }

    }

    def putAccountRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : EntityService, action :  putCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  putCustomerGroup  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteAccountRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteUser  , Ex:' + ex)
        }

    }

    def getAllAccountById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_SHOW + "/" + id)
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
                    .path(new Links().ACCOUNT_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAccountById  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAccountById  , Ex:' + ex)
        }

    }


    def getAllAccountByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getAllAccountByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getAllAccountByEntity  , Ex:' + ex)
        }

    }


    def updateAccountBalance(String amount, String entityId, String id, boolean add) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("amount", amount)
            jsonObject.put("entityId", entityId)
            jsonObject.put("id", id)
            jsonObject.put("add", add)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_REGISTER_UPDATE_BALANCE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :entityService , action :  updateAccountBalance  , Ex:' + ex)
            log.error('Service :entityService , action :  updateAccountBalance  , Ex:' + ex)
        }

    }


    def addEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :entityService , action :  addEntityIRN  , Ex:' + ex)
            log.error('Service :entityService , action :  addEntityIRN  , Ex:' + ex)
        }

    }

    def getEntityIrn(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityIrn  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityIrn  , Ex:' + ex)
        }

    }

    def getEntityIrnByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_SHOW_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityIrn  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityIrn  , Ex:' + ex)
        }

    }

    def updateEntityIRN(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_IRN_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :entityService , action :  updateEntityIRN  , Ex:' + ex)
            log.error('Service :entityService , action :  updateEntityIRN  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def getAllDepartment() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_MASTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(JSON.parse(apiResponse.readEntity(String.class)) as Collection)
                return jsonArray
            } else {
                return null
            }

        }
        catch (Exception ex) {
            System.err.println('Service :entityService , action :  getAllDepartment  , Ex:' + ex)
            log.error('Service :entityService , action :  getAllDepartment  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def getDepartmentDatatable(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getDepartmentDatatable  , Ex:' + ex)
            log.error('Service :EntityService , action :  getDepartmentDatatable  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def getDeparmentByEntityId(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_MASTER_ENTITY)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray

            } else {
                return null
            }

        }
        catch (Exception ex) {
            System.err.println('Service :getDeparmentByEntityId , action :  show  , Ex:' + ex)
            log.error('Service :getDeparmentByEntityId , action :  show  , Ex:' + ex)
        }

    }

    def saveDepartment(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_MASTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  saveDepartment  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDepartment  , Ex:' + ex)
        }

    }

    def putDepartment(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_MASTER_SHOW + "/" + jsonObject.get("id"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  putDepartment  , Ex:' + ex)
            log.error('Service :EntityService , action :  putDepartment  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteDepartment(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().DEPARTMENT_MASTER_DELETE + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  deleteDepartment  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteDepartment  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def getAllRoles() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().ROLE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                return apiResponse
            } else {
                return null
            }

        }
        catch (Exception ex) {
            System.err.println('Service :showAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :showAccountModes , action :  show  , Ex:' + ex)
        }

    }


//    Entity Settings
    def saveSettings(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ENTITY_SETTINGS_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveCustomerGroup  , Ex:' + ex)
        }

    }

    def showSettings(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_SETTINGS_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  showUser  , Ex:' + ex)
        }

    }

    def putSettings(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_SETTINGS_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  putUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  putUser  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteSettings(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_SETTINGS_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteUser  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteUser  , Ex:' + ex)
        }

    }

    def getEntitySettingsByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_SETTINGS_BY_ENTITY)
                    .queryParam("id", URLEncoder.encode(id.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
    }

    def getEntityConfigByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_CONFIG_BY_ENTITY)
                    .queryParam("id", URLEncoder.encode(id.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
    }

    def saveEntityConfig(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_CONFIG_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  save  , Ex:' + ex)
            log.error('Service :EntityService , action :  save  , Ex:' + ex)
        }

    }


    def getByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getByEntity  , Ex:' + ex)
        }
    }

    def getCityIdsByEntity(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_GET_CITY_IDS)
                    .queryParam("entityId", id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getByEntity  , Ex:' + ex)
        }
    }


    def getByEntityPaginated(String id, String page = null, String search = null) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW_BY_ENTITY_PAGINATED + "/" + id)
                    .queryParam("search", search)
                    .queryParam("page", page)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getByEntityPaginated  , Ex:' + ex)
            log.error('Service :EntityService , action :  getByEntityPaginated  , Ex:' + ex)
        }
    }

    def getEntityDomainType() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_DOMAIN_TYPE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityDomainType  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityDomainType  , Ex:' + ex)
        }
    }

    //Day End Logs
    def saveDayEndLogs(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().SAVE_DAY_END_LOGS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
        }

    }


    boolean billDetailsCheckUserType(String userId) {
        def user = getUser(userId);
        if (user != null) {
            if (user.role.name == Constants.SUPER_USER) {
                return true
            } else {
                return false
            }
        }
    }


    def checkPhoneNumber(String phoneNumber, String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().CHECK_EXISTING_PHONE)
                    .queryParam("phoneNumber", URLEncoder.encode(phoneNumber, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveDayEnd  , Ex:' + ex)
        }
    }


    def savePatientDetails(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().REGISTER_PATIENT)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :InventoryService , action :  save  , Ex:' + ex)
            log.error('Service :InventoryService , action :  save  , Ex:' + ex)
        }

    }


    //Entity Route
    def saveEntityRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ENTITY_ROUTE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveEntityRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveEntityRoute  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showEntityRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_ROUTE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  showEntityIRN  , Ex:' + ex)
            log.error('Service :EntityService , action :  showEntityIRN  , Ex:' + ex)
        }

    }

    def putEntityRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_ROUTE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  putEntityRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  putEntityRoute  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteEntityRoute(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_ROUTE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  deleteEntityRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  deleteEntityRoute  , Ex:' + ex)
        }

    }

   /* JSONArray getRouteByEntity(String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_ROUTE_SHOW_BY_ENTITY + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getRouteByEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getRouteByEntity  , Ex:' + ex)
            return null
        }

    }*/


    /**
     Get Entities belongs to users route, by sending user id
     */
    def getEntityByUserRoute(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_GET_BY_USER_ROUTE + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntityByUserRoute  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntityByUserRoute  , Ex:' + ex)
        }

    }


    JSONObject saveUserLoginInfo(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_LOGININFO)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            } else {
               return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveUserLoginInfo  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveUserLoginInfo  , Ex:' + ex)
            return null
        }

    }

    JSONObject getUserLoginInfo(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_LOGININFO + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getUserLoginInfo  , Ex:' + ex)
            log.error('Service :EntityService , action :  getUserLoginInfo  , Ex:' + ex)
            return null
        }
    }

    JSONObject updateUserLoginInfo(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().USER_LOGININFO + "/"+jsonObject.get("id"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            } else {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  updateUserLoginInfo  , Ex:' + ex)
            log.error('Service :EntityService , action :  updateUserLoginInfo  , Ex:' + ex)
            return null
        }
    }
}
