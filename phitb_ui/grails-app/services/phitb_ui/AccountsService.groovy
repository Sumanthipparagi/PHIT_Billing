package phitb_ui

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class AccountsService {

    //Bank Register
    def saveBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
        }
    }

    def putBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
            log.error('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
        }
    }

}
