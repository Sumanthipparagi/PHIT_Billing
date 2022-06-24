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
import phitb_ui.entity.SeriesController

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
        boolean paidInvoice = true
        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy,paidInvoice)
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
        boolean paidInvoice = true
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy,paidInvoice)
        //get product details
        for (Object city : areaWiseData.keySet()) {
            JSONArray bills = areaWiseData.get(city) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
            }
        }

        JSONObject resultJson = new JSONObject()
        for (Object key : areaWiseData.keySet())
        {
            JSONArray areaWiseBills = (JSONArray)areaWiseData.get(key)
            JSONObject customerJson = new JSONObject()
            for (JSONObject bill : (areaWiseBills as List<JSONObject>))
            {
                if(bill.billStatus != "CANCELLED")
                {
                    JSONObject products = new JSONObject()
                    if (customerJson.has(bill.customer.entityName)) {
                        for (JSONObject product : bill.products) {
                            products = (JSONObject) customerJson.get(bill.customer.entityName)
                            if (products.has(product.productDetail.productName)) {
                                JSONObject customerProduct = (JSONObject) products.get(product.productDetail.productName)
                                customerProduct.put("sQty", customerProduct.get("sQty") + product.sqty)
                                customerProduct.put("fQty", customerProduct.get("fQty") + product.freeQty)
                                customerProduct.put("discount", customerProduct.get("discount") + product.discount)
                                customerProduct.put("gst", customerProduct.get("gst") + product.gstAmount)
                                customerProduct.put("netAmount", customerProduct.get("netAmount") + product.amount)
                                customerProduct.put("ntv", customerProduct.get("ntv") + (product.amount - product.gstAmount))
                                products.put(product.productDetail.productName.toString(), customerProduct)
                            } else {
                                JSONObject prd = new JSONObject()
                                prd.put("sQty", product.sqty)
                                prd.put("fQty", product.freeQty)
                                prd.put("discount", product.discount)
                                prd.put("gst", product.gstAmount)
                                prd.put("netAmount", product.amount)
                                prd.put("ntv", product.amount - product.gstAmount)
                                products.put(product.productDetail.productName.toString(), prd)
                            }
                        }
                        customerJson.put(bill.customer.entityName.toString(), products)
                    } else
                    {
                        for (JSONObject product : bill.products) {
                            if (products.has(product.productDetail.productName)) {
                                JSONObject prd = (JSONObject) products.get(product.productDetail.productName)
                                prd.put("sQty", prd.get("sQty") + product.sqty)
                                prd.put("fQty", prd.get("fQty") + product.freeQty)
                                prd.put("discount", prd.get("discount") + product.discount)
                                prd.put("gst", prd.get("gst") + product.gstAmount)
                                prd.put("netAmount", prd.get("netAmount") + product.amount)
                                prd.put("ntv", prd.get("ntv") + (product.amount - product.gstAmount))
                                products.put(product.productDetail.productName.toString(), prd)
                            } else {
                                JSONObject prd = new JSONObject()
                                prd.put("sQty", product.sqty)
                                prd.put("fQty", product.freeQty)
                                prd.put("discount", product.discount)
                                prd.put("gst", product.gstAmount)
                                prd.put("netAmount", product.amount)
                                prd.put("ntv", product.amount - product.gstAmount)
                                products.put(product.productDetail.productName.toString(), prd)
                            }
                        }
                        customerJson.put(bill.customer.entityName.toString(), products)
                    }
                }
                else
                {
                    //ignore if cancelled
                }
            }
            def cityDetail = new SystemService().getCityById(key.toString())
            resultJson.put(cityDetail?.districtName?.toString(), customerJson)
        }
        respond resultJson, formats: ['json']
    }

    def areawiseConsolidatedProducts() {
        render(view: '/reports/salesReport/areaWiseConsolidatedProducts')
    }

    def areawiseConsolidatedProductsReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        boolean paidInvoice = true

        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy,paidInvoice)
        //get product details
        for (Object city : areaWiseData.keySet()) {
            JSONArray bills = areaWiseData.get(city) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
            }
        }

        JSONObject resultJson = new JSONObject()
        for (Object key : areaWiseData.keySet())
        {
            JSONArray areaWiseBills = (JSONArray)areaWiseData.get(key)
            JSONObject products = new JSONObject()
            for (JSONObject bill : (areaWiseBills as List<JSONObject>))
            {
                if(bill.billStatus != "CANCELLED")
                {
                    for (JSONObject product : bill.products) {
                        if (products.has(product.productDetail.productName)) {
                            JSONObject prd = (JSONObject) products.get(product.productDetail.productName)
                            prd.put("sQty", prd.get("sQty") + product.sqty)
                            prd.put("fQty", prd.get("fQty") + product.freeQty)
                            prd.put("discount", prd.get("discount") + product.discount)
                            prd.put("gst", prd.get("gst") + product.gstAmount)
                            prd.put("netAmount", prd.get("netAmount") + product.amount)
                            prd.put("ntv", prd.get("ntv") + (product.amount - product.gstAmount))
                            products.put(product.productDetail.productName.toString(), prd)
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("sQty", product.sqty)
                            prd.put("fQty", product.freeQty)
                            prd.put("discount", product.discount)
                            prd.put("gst", product.gstAmount)
                            prd.put("netAmount", product.amount)
                            prd.put("ntv", product.amount - product.gstAmount)
                            products.put(product.productDetail.productName.toString(), prd)
                        }
                    }
                }
                else
                {
                    //ignore if cancelled
                }
            }
            def cityDetail = new SystemService().getCityById(key.toString())
            resultJson.put(cityDetail?.districtName?.toString(), products)
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


    def saleProductWise()
    {
        render(view: '/reports/salesReport/productwise')
    }

    def saleProductWiseReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        boolean paidInvoice = true

        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy,paidInvoice)
        //get product details
        for (Object city : areaWiseData.keySet()) {
            JSONArray bills = areaWiseData.get(city) as JSONArray
            for (Object bill : bills) {
                JSONArray saleProducts = bill.products
                for (Object saleProduct : saleProducts) {
                    JSONObject product = new ProductService().getProductById(saleProduct.productId.toString())
                    saleProduct.put("productDetail", product)
                }
            }
        }

        JSONArray resultJson = new JSONArray()
        JSONObject products = new JSONObject()
        for (Object key : areaWiseData.keySet())
        {
            JSONArray areaWiseBills = (JSONArray)areaWiseData.get(key)
            for (JSONObject bill : (areaWiseBills as List<JSONObject>))
            {
                if(bill.billStatus != "CANCELLED")
                {
                    for (JSONObject product : bill.products) {
                        if (products.has(product.productDetail.productName)) {
                            JSONObject prd = (JSONObject) products.get(product.productDetail.productName)
                            prd.put("sQty", prd.get("sQty") + product.sqty)
                            prd.put("fQty", prd.get("fQty") + product.freeQty)
                            prd.put("discount", prd.get("discount") + product.discount)
                            prd.put("gst", prd.get("gst") + product.gstAmount)
                            prd.put("netAmount", prd.get("netAmount") + product.amount)
                            prd.put("ntv", prd.get("ntv") + (product.amount - product.gstAmount))
                            products.put(product.productDetail.productName.toString(), prd)
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("sQty", product.sqty)
                            prd.put("fQty", product.freeQty)
                            prd.put("discount", product.discount)
                            prd.put("gst", product.gstAmount)
                            prd.put("netAmount", product.amount)
                            prd.put("ntv", product.amount - product.gstAmount)
                            products.put(product.productDetail.productName.toString(), prd)
                        }
                    }
                }
                else
                {
                    //ignore if cancelled
                }
            }
        }
        resultJson.addAll(products)
        respond resultJson, formats: ['json']
    }

    def salesGstReport()
    {
        render(view: '/reports/salesReport/gstreport')
    }

    def getSalesGstReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject gstData = reportsService.getSalesGstRport(entityId, dateRange, financialYear, sortBy)
        JSONArray gstDetails = gstData.gstDetails
        for (JSONObject jsonObject : gstDetails) {
            JSONObject entity = new EntityService().getEntityById(jsonObject.get("customerId").toString())
            JSONObject city = new SystemService().getCityById(entity.get("cityId").toString())
            JSONObject series = new EntityService().getSeriesById(jsonObject.get("seriesId").toString())
            jsonObject.put("customerId", entity.entityName)
            jsonObject.put("town", city?.districtName)
            jsonObject.put("gstin", entity.gstn)
            jsonObject.put("seriesId", series.seriesCode)
        }
        gstData.put("gstDetails", gstDetails)
        respond gstData, formats: ['json']
    }
}
