package phitb_ui.entity

import com.google.gson.Gson
import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.system.AccountModeController
import phitb_ui.system.ZoneController

class AccountRegisterController {

    def getAllAccounts()
    {
        def apiResponse = new EntityService().getAllAccount()
        if(apiResponse.status==200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            return jsonArray
        }
        else {
            return []
        }
    }

    def getAllAccountsById(String id)
    {
        def apiResponse = new EntityService().getAllAccountById(id)
        if(apiResponse.status==200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
            return jsonArray
        }
        else {
            return []
        }
    }


    def getTreeData()
    {
//        ArrayList<String> account = new AccountRegisterController().getAllAccounts()
//        JSONArray array = new JSONArray();
//        JSONObject item
//        account.each {
//            item = new JSONObject();
//            item.put("text", it.accountName)
//            array.put(item)
//        }
//        account.each {
//            item = new JSONObject();
//            item.put("nodes", it.subAccountType)
//            array.put(item)
//        }

        ArrayList<String> account = new AccountRegisterController().getAllAccounts()
        def map = [:]
        account.each {val ->
            map['text'] = val.accountName
            map['nodes'] = val.accountName
        }
       render map as JSON
    }


    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().saveAccountRegister(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def index() {
        ArrayList<String> account = new AccountRegisterController().getAllAccounts()
        def map = [:]
        account.each {val ->
            map['text'] = val.accountName
            map['nodes'] = val.accountName
        }
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        render(view: '/entity/accountRegister/accounts',model: [account:account,accountMode:accountMode,entity:entity])
    }
}
