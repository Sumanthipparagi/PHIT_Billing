package phitb_inventory


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

class ReportsController {
	static responseFormats = ['json', 'xml']
	
    def inventoryReport() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        ArrayList<StockBook> stocks = StockBook.findAllByEntityId(Long.parseLong(jsonObject.get("entityId").toString()))
    }


}
