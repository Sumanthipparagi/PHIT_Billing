package phitb_purchase


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat

class ReportsController {
	static responseFormats = ['json', 'xml']

    def getPurchaseInfoTillDate()
    {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            println(jsonObject.toString())
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date date = sdf.parse(jsonObject.get("date").toString())
            long entityId = jsonObject.get("entityId")
            HashMap<Long, JSONArray> totalSales = new PurchaseReportsService().getTotalPurchasesTillDate(date, entityId)
            HashMap<Long, JSONArray> totalSaleReturns = new PurchaseReportsService().getTotalPurchaseReturnTillDate(date, entityId)
            JSONObject responseJson = new JSONObject()
            responseJson.put("totalPurchases", totalSales)
            responseJson.put("totalPurchaseReturns", totalSaleReturns)
            respond responseJson
        }
        catch (Exception e)
        {
            e.printStackTrace()
            response.status = 400
        }
    }
}
