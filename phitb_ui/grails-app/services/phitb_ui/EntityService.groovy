package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }
    }

    def putEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service : , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteEntity(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }
    }


//User Register
    def saveUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().USER_REGISTER_SAVE)
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

    /**
     *
     * @param jsonObject
     * @return
     */
    def showUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }
    }

    def putUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service : , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteUser(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }
    }

}
