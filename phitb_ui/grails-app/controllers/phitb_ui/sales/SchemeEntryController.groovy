package phitb_ui.sales

import org.grails.web.json.JSONArray
import phitb_ui.ProductService

class SchemeEntryController {

    def index() {
        JSONArray products = new ProductService().getProductsByEntityId(session.getAttribute("entityId").toString())
        render(view: "/sales/schemeEntry/index", model: [products:products])
    }

    def dataTable()
    {

    }
}
