package phitb_ui

import grails.gorm.transactions.Transactional

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import org.grails.web.json.JSONObject
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.util.WebUtils
import javax.ws.rs.core.Form


@Transactional
class SystemService
{
    //Accounut Modes
    /**
     * This method is used for the communication between master service for adding new account modes
     * @param jsonObject : which contains the parameter to add new case
     * @return response from the server
     */
    def saveAccountModes(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_MODES_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveAccountModes , action :  save  , Ex:' + ex)
            log.error('Service :saveAccountModes , action :  save  , Ex:' + ex)
        }
    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def putAccountMode(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_MODES_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :putAccountMode , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showAccountModes(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_MODES_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :showAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :showAccountModes , action :  show  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteAccountModes(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            Response apiResponse = target
                    .path(new Links().ACCOUNT_MODES_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .po(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :caseDetails , action :  delete  , Ex:' + ex)
            log.error('Service :caseDetails , action :  delete  , Ex:' + ex)
        }
    }

    //State
    /**
     * This method is used for the communication between master service for adding new account modes
     * @param jsonObject : which contains the parameter to add new case
     * @return response from the server
     */
    def saveStateMaster(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().ACCOUNT_MODES_SAVE)
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
    def showState(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost/");
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            Response apiResponse = target
                    .path(new Links().STATE_MASTER_DATATABLE)
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
}
