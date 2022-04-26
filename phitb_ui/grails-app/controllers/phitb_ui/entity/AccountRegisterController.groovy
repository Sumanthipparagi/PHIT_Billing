package phitb_ui.entity

import com.google.gson.Gson
import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.SystemService
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
            JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
            return jsonObject
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
            jsonObject.put("generalId", "1")
            jsonObject.put("accountStatus", "1")
            jsonObject.put("responsibleUserId", session.getAttribute("userId"))
            jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            jsonObject.put("entity", session.getAttribute("entityId"))
            if(jsonObject.has("showInDebit"))
            {
                if(jsonObject.get("showInDebit").toString().equalsIgnoreCase("on"))
                {
                    jsonObject.put("showInDebit", true)
                }
                else
                {
                    jsonObject.put("showInDebit", false)
                }
            }
            else
            {
                jsonObject.put("showInDebit", false)
            }

            if(jsonObject.has("showInCredit"))
            {
                if(jsonObject.get("showInCredit").toString().equalsIgnoreCase("on"))
                {
                    jsonObject.put("showInCredit", true)
                }
                else
                {
                    jsonObject.put("showInCredit", false)
                }
            }
            else
            {
                jsonObject.put("showInCredit", false)
            }

            def apiResponse = new EntityService().saveAccountRegister(jsonObject)
            if (apiResponse?.status == 200)
            {
                //JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: "/accounts")
            }
            else
            {
                redirect(uri: "/accounts")
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
        JSONArray accountList = new EntityService().getAllAccountByEntity(session.getAttribute("entityId").toString())
        JSONArray accountTypes = new SystemService().getAccountTypes(session.getAttribute("entityId").toString())
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        render(view: '/entity/accountRegister/accounts',model: [account:accountList,accountMode:accountMode,entity:entity, accountTypes: accountTypes])
    }
}
