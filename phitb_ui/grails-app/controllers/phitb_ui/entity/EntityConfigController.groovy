package phitb_ui.entity

import com.google.gson.JsonObject
import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService

class EntityConfigController {
    def saveEntityConfig() {
        try
        {
            JSONArray configArray = new JSONArray(JSON.parse(params.configData))
            JSONArray configDeatils = new JSONArray()
            for (JSONObject config : configArray)
            {
                String name = config.get("0")
                String code = config.get("11")
                String purchaseOrder = config.get("1")
                String purchaseEntry = config.get("2")
                String purchaseReturn = config.get("3")
                String payments = config.get("4")
                String saleOrder = config.get("5")
                String saleEntry = config.get("6")
                String saleReturn = config.get("7")
                String recipts = config.get("8")
                String creditJV = config.get("9")
                String debitJV = config.get("10")
                String entityId = config.get("12")
                String entityType = config.get("13")
                config.put("name", name)
                config.put("code", code)
                config.put("purchaseOrder", purchaseOrder)
                config.put("purchaseEntry", purchaseEntry)
                config.put("purchaseReturn", purchaseReturn)
                config.put("payments", payments)
                config.put("saleOrder", saleOrder)
                config.put("saleEntry", saleEntry)
                config.put("saleReturn", saleReturn)
                config.put("recipts", recipts)
                config.put("creditJV", creditJV)
                config.put("debitJV", debitJV)
                config.put("entityId", entityId)
                config.put("entityType", entityType)
                configDeatils.add(config)
            }
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("config", configDeatils)
            jsonObject.put("entity", params.entityId)
            def apiResponse = new EntityService().saveEntityConfig(jsonObject)
            if(apiResponse.status == 200)
            {
                response.status  = 200
            }
        }
        catch (Exception ex)
        {
            log.error(controllerName+":"+ex)
            println(controllerName+":"+ex)
        }
    }
}
