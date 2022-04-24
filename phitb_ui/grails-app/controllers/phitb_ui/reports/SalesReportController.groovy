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

    def salesCustomerWiseReport() {
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

    def salesDateWiseReport() {
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

    def salesAreaWiseReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object city : areaWiseData.keySet()) {
            def cityDetail = new SystemService().getCityById(city.toString())
            JSONArray bills = areaWiseData.get(city) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
                bill.put("cityDetail", cityDetail)
            }
        }
        respond areaWiseData, formats: ['json']
    }

    def areawiseWithProducts() {
        render(view: '/reports/salesReport/areaWiseWithProducts')
    }

    def areawiseWithProductsReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy)
        //get product details
        for (Object city : areaWiseData.keySet()) {
            def cityDetail = new SystemService().getCityById(city.toString())
            JSONArray bills = areaWiseData.get(city) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
                bill.put("cityDetail", cityDetail)
            }
        }
        JSONObject resultJson = new JSONObject()
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = areaWiseData.get(key)
            JSONObject customerJson = new JSONObject()
            JSONObject products = new JSONObject()
            for (JSONObject bill : areaWiseBills) {
                if (customerJson.has(bill.customer.entityName)) {
                   /* products = new JSONObject()
                    JSONObject customerProducts = customerJson.get(bill.customer.entityName)
                    for (String productName : customerProducts.keySet()) {
                        if(products.has(productName)) {
                            JSONObject prd = products.get(productName)
                            prd.put("sQty", prd.get("sQty") + customerProducts[productName].sQty)
                            prd.put("fQty", prd.get("fQty") + customerProducts[productName].freeQty)
                            prd.put("discount", prd.get("discount") + customerProducts[productName].discount)
                            prd.put("gst", prd.get("gst") + customerProducts[productName].gstAmount)
                            prd.put("netAmount", prd.get("netAmount") + customerProducts[productName].amount)
                            prd.put("ntv", prd.get("ntv") + (customerProducts[productName].amount - customerProducts[productName].gstAmount))
                            products.put(productName, prd)
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("sQty", customerProducts[productName].sQty)
                            prd.put("fQty", customerProducts[productName].freeQty)
                            prd.put("discount", customerProducts[productName].discount)
                            prd.put("gst", customerProducts[productName].gstAmount)
                            prd.put("netAmount", customerProducts[productName].amount)
                            prd.put("ntv", customerProducts[productName].amount - customerProducts[productName].gstAmount)
                            products.put(productName, prd)
                        }
                    }*/
                    //customerJson.put(bill.customer.entityName, products)
                } else {
                    for (JSONObject product : bill.products) {
                        if(products.has(product.productDetail.productName)) {
                            JSONObject prd = products.get(product.productDetail.name)
                            if(prd) {
                                prd.put("sQty", prd.get("sQty") + product.sqty)
                                prd.put("fQty", prd.get("fQty") + product.freeQty)
                                prd.put("discount", prd.get("discount") + product.discount)
                                prd.put("gst", prd.get("gst") + product.gstAmount)
                                prd.put("netAmount", prd.get("netAmount") + product.amount)
                                prd.put("ntv", prd.get("ntv") + (product.amount - product.gstAmount))
                                products.put(product.productDetail.productName, prd)
                            }
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("sQty", product.sqty)
                            prd.put("fQty", product.freeQty)
                            prd.put("discount", product.discount)
                            prd.put("gst", product.gstAmount)
                            prd.put("netAmount", product.amount)
                            prd.put("ntv", product.amount - product.gstAmount)
                            products.put(product.productDetail.productName, prd)
                        }
                    }
                    customerJson.put(bill.customer.entityName, products)
                }
            }
            def cityDetail = new SystemService().getCityById(key.toString())
            resultJson.put(cityDetail.name, customerJson)
        }
        respond resultJson, formats: ['json']
    }


    def consolidated() {
        render(view: '/reports/salesReport/consolidated')
    }

    def saleConsolidatedReport() {
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
            println(bills)
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
