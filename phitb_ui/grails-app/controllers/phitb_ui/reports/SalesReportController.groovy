package phitb_ui.reports

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.ReportsService

class SalesReportController {

    ReportsService reportsService
    def index() {
        render(view: '/reports/salesReport/index')
    }

    def salesCustomerWiseReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject customerWiseData = reportsService.getCustomerWiseReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object customer : customerWiseData.keySet()) {
            def customerDetail = new EntityService().getEntityById(customer.toString())
            JSONArray bills = customerWiseData.get(customer) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
                bill.put("customerDetail", customerDetail)
            }
        }
        respond customerWiseData, formats: ['json']
    }

    def datewise() {
        render(view: '/reports/salesReport/dateWise')
    }

    def salesDateWiseReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "invoice-date"
        JsonObject dateWiseData = reportsService.getDateWiseReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object date : dateWiseData.keySet()) {
            JsonArray bills = dateWiseData.get(date.toString()) as JsonArray
            for (Object bill : bills) {
                JsonArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", new JsonParser().parse(product.toString()).getAsJsonObject()) //TODO: to be corrected
                }
                def customerDetail = new EntityService().getEntityById(bill.customerId.toString())
                bill.put("customerDetail", customerDetail)
            }
        }
        respond dateWiseData, formats: ['json']
    }
}
