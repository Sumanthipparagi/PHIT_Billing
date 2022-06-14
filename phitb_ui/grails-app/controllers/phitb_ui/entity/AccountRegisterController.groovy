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

    def index() {
        JSONArray accountList = new EntityService().getAllAccountByEntity(session.getAttribute("entityId").toString())
        JSONArray accountTypes = new SystemService().getAccountTypes(session.getAttribute("entityId").toString())
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        render(view: '/entity/accountRegister/accounts',model: [account:accountList,accountMode:accountMode,entity:entity, accountTypes: accountTypes])
    }


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


    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new EntityService().showAccountRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def update()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("generalId", "1")
            jsonObject.put("accountStatus", "1")
            jsonObject.put("responsibleUserId", session.getAttribute("userId"))
            jsonObject.put("entityType", session.getAttribute("entityTypeId"))
            jsonObject.put("entity", session.getAttribute("entityId"))
//            if(params.showInCredit==null)
//            {
//                jsonObject.put("showInCredit", "off")
//            }
//            if(params.showInDebit==null)
//            {
//                jsonObject.put("showInDebit", "off")
//            }
            if(params.subAccountType==null)
            {
                jsonObject.put("subAccountType", 0)
            }
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

            def apiResponse = new EntityService().putAccountRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().deleteAccountRegister(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success","success")
                respond data, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def accountsList()
    {
        JSONArray accountList = new EntityService().getAllAccountByEntity(session.getAttribute("entityId").toString())
        JSONArray accountTypes = new SystemService().getAccountTypes(session.getAttribute("entityId").toString())
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        render(view:'/entity/accountRegister/accounts-list',model: [account:accountList,accountMode:accountMode,entity:entity, accountTypes: accountTypes])
    }


    def show()
    {
        def apiResponse = new EntityService().getAllAccount()
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
            ArrayList<String> arrayList = new ArrayList<>(jsonArray)
            return arrayList
        }
        else
        {
            return []
        }
    }

}
