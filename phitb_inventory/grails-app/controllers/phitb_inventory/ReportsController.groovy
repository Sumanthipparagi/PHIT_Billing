package phitb_inventory


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat

class ReportsController {
	static responseFormats = ['json', 'xml']
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
	
    def inventoryReport() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        ArrayList<StockBook> stocks = StockBook.findAllByEntityId(Long.parseLong(jsonObject.get("entityId").toString()))
    }

    def expiryReport()
    {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        Date fromDate = sdf.parse(jsonObject.get("fromDate").toString())
        Date toDate = sdf.parse(jsonObject.get("toDate").toString())
        long entityId = Long.parseLong(jsonObject.get("entityId").toString())
        ArrayList<StockBook> stocks = StockBook.findAllByEntityIdAndExpDateBetweenAndRemainingQtyGreaterThanAndRemainingFreeQtyGreaterThan(entityId, fromDate, toDate, 0 , 0)
        respond stocks
    }
}
