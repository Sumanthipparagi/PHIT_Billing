package phitb_ui.inventory

import org.grails.web.json.JSONArray
import phitb_ui.InventoryService

class StockBookController {

    def index() { }

    def getStocksOfProduct()
    {
        def apiResponse = new InventoryService().getStocksOfProduct(params.id)
        if(apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
            respond jsonArray, formats: ['json'], status: 200
        }
        else
        {
            response.status = apiResponse?.status
        }

    }
}
