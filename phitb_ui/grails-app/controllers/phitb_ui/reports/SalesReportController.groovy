package phitb_ui.reports

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.ReportsService
import phitb_ui.SystemService

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
            for (JsonElement bill : bills) {
                JsonArray saleProducts = bill.products

                for (JsonElement saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    JsonObject productGsonObject = new JsonParser().parse(product.toString()).getAsJsonObject()
                    saleProduct.getAsJsonObject().add("productDetail", productGsonObject) //TODO: to be corrected
                }
                JSONObject customerDetail = new EntityService().getEntityById(bill.customerId.toString())
                JsonObject customerDetailGsonObject = new JsonParser().parse(customerDetail.toString()).getAsJsonObject()
                bill.getAsJsonObject().add("customerDetail", customerDetailGsonObject)
            }
        }
        render(text: dateWiseData.toString(), contentType: "application/json")
    }

    def areawise() {

        render(view: '/reports/salesReport/areaWise')
    }

    def salesAreaWiseReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object customer : areaWiseData.keySet()) {
            def customerDetail = new EntityService().getEntityById(customer.toString())
            JSONArray bills = areaWiseData.get(customer) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
                def cityResp = new SystemService().getCityById(customerDetail.cityId.toString())
                cityResp.put("entity",customerDetail)
                bill.put("customerDetail", customerDetail)
                bill.put("city",cityResp)
            }
        }
        respond areaWiseData, formats: ['json']
    }

    def consolidated() {
        render(view: '/reports/salesReport/consolidated')
    }

    def saleConsolidatedReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject consolidated = reportsService.getConsolidatedReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object customer : consolidated.keySet()) {
            def customerDetail = new EntityService().getEntityById(customer.toString())
            JSONArray bills = consolidated.get(customer) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
                bill.put("customerDetail", customerDetail)
            }
        }
        respond consolidated, formats: ['json']
    }
}
