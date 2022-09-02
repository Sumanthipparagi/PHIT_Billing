package phitb_ui.reports

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONException
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.ReportsService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.function.ToDoubleFunction

class SalesReportController {

    ReportsService reportsService
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

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
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy, paidInvoice)
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
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy, paidInvoice)
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
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = (JSONArray) areaWiseData.get(key)
            JSONObject customerJson = new JSONObject()
            for (JSONObject bill : (areaWiseBills as List<JSONObject>)) {
                if (bill.billStatus != "CANCELLED") {
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
                    } else {
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
                } else {
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

        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy, paidInvoice)
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
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = (JSONArray) areaWiseData.get(key)
            JSONObject products = new JSONObject()
            for (JSONObject bill : (areaWiseBills as List<JSONObject>)) {
                if (bill.billStatus != "CANCELLED") {
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
                } else {
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


    def saleProductWise() {
        render(view: '/reports/salesReport/productwise')
    }

    def saleProductWiseReport() {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        //String sortBy = params.sortBy
        boolean paidInvoice = true

        String sortBy = "id"
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy, paidInvoice)
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
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = (JSONArray) areaWiseData.get(key)
            for (JSONObject bill : (areaWiseBills as List<JSONObject>)) {
                if (bill.billStatus != "CANCELLED") {
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
                } else {
                    //ignore if cancelled
                }
            }
        }
        resultJson.addAll(products)
        respond resultJson, formats: ['json']
    }

    def salesGstReport() {
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

    def invoicePaymentReports() {
        def entities = new EntityRegisterController().getByAffiliateById(session.getAttribute("entityId").toString())
        render(view: '/reports/salesReport/invoice-payment-report', model: [entities: entities])
    }

    def getInvoicePaymentReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy")
        String entityId = session.getAttribute("entityId")
        String customerId = params.customerId
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        JSONObject customer = new EntityService().getEntityById(customerId)
        JSONArray saleBills = new SalesService().getSaleBillByCustomer(customerId, financialYear, entityId, dateRange)
        JSONArray invoicePaymentDetails = new JSONArray()
        for (JSONObject saleBill : saleBills) {
            if (saleBill.billStatus == "ACTIVE") {
                BigDecimal receiptAmount = 0.0
                BigDecimal saleReturnAmount = 0.0
                def receiptlogsinv = new AccountsService().getReceiptLogByBillTypeAndId(saleBill.id.toString(), "INVS", dateRange)
                if (receiptlogsinv.status == 200) {
                    JSONArray receipts = new JSONArray(receiptlogsinv.readEntity(String.class))
                    for (Object receipt : receipts) {
                        receiptAmount += receipt.amountPaid
                    }
                }
                JSONArray saleReturnAdjustments = new SalesService().getSaleReturnAdjustmentDetails(saleBill.id.toString(), "INVS", dateRange)
                for (Object saleReturnAdjustment : saleReturnAdjustments) {

                    if (saleReturnAdjustment.cancelledDate == null) {
                        saleReturnAmount += saleReturnAdjustment.adjAmount
                    }
                }
                double invoiceTotal = saleBill.invoiceTotal
                double balance = saleBill.balance
                JSONObject jsonObject = new JSONObject()
                jsonObject.put("invoiceTotal", invoiceTotal)
                jsonObject.put("invoiceNumber", saleBill.invoiceNumber)
                def orderDate = sdf.parse(saleBill.orderDate?.split("T")[0])
                if (orderDate) {
                    orderDate = sdf2.format(orderDate)
                    jsonObject.put("invoiceDate", orderDate)
                } else {
                    jsonObject.put("invoiceDate", "-")
                }
                if (Math.round(balance) <= 0) {
                    jsonObject.put("balance", 0)
                } else {
                    jsonObject.put("balance", invoiceTotal - (saleReturnAmount + receiptAmount))
                }
                jsonObject.put("receiptAmount", (saleReturnAmount + receiptAmount))

                jsonObject.put("entityName", customer.entityName)
                jsonObject.put("entityOpeningBalance", customer.entityName)
                invoicePaymentDetails.add(jsonObject)
            }
        }
        respond invoicePaymentDetails, formats: ['json']
    }


    def getCustomerLedger() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy")
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        double openingBalance = 0
        ArrayList<JSONObject> customerLedgerDetails = new ArrayList<>()

        //get data within daterange
        JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
        JSONArray saleReturn = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
        JSONArray goodsTransferNotes = new SalesService().getGTNByDateRange(dateRange, entityId)

        double receiptAmount = 0.0;
        double saleReturnBalance = 0.0
        for (Object sr : saleReturn) {
            if(sr?.returnStatus == "ACTIVE" && sr?.balance > 0) {
                saleReturnBalance += sr?.totalAmount
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sr?.dateCreated)
                customerLedgerEntry.put("transactionNumber", sr?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Sale Return")
                customerLedgerEntry.put("amount", sr?.totalAmount)
                customerLedgerEntry.put("type", "CREDIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ sr.totalAmount)
            }
        }

        double saleBillBalance = 0.0
        for (Object sb : saleBills) {
            if(sb?.billStatus == "ACTIVE" && sb?.deleted == false) {
                saleBillBalance += sb?.invoiceTotal
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sb?.orderDate)
                customerLedgerEntry.put("transactionNumber", sb?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Sale Invoice")
                customerLedgerEntry.put("amount", sb?.invoiceTotal)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)

               /* def receiptDetails = new AccountsService().getReceiptLogByBillTypeAndIdStartDate(sb.id.toString(), "INVS", dateRange)
                if (receiptDetails?.status == 200) {
                    JSONArray receipts = new JSONArray(receiptDetails.readEntity(String.class))
                    for (Object receipt : receipts) {
                        receiptAmount += receipt.amountPaid
                    }
                }*/
            }
            else
            {
                println("Not Included: "+ sb.invoiceTotal)
            }
        }


        for (Object gtn : goodsTransferNotes) {
            if(gtn?.billStatus == "ACTIVE" && gtn?.deleted == false) {
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", gtn?.orderDate)
                customerLedgerEntry.put("transactionNumber", gtn?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "GTN")
                customerLedgerEntry.put("amount", gtn?.invoiceTotal)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ gtn.invoiceTotal)
            }
        }


        //sort based on date
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        customerLedgerDetails.sort(new Comparator<JSONObject>() {
            @Override
            int compare(JSONObject o1, JSONObject o2) {
                String dateO1String = o1.get("transactionDate").toString().replace("Z", "")
                dateO1String = dateO1String.replace("T", " ")
                Date dateO1 = sdf1.parse(dateO1String)

                String dateO2String = o2.get("transactionDate").toString().replace("Z", "")
                dateO2String = dateO2String.replace("T", " ")
                Date dateO2 = sdf1.parse(dateO2String)

                return dateO1.compareTo(dateO2)
            }
        })

        openingBalance = 1000000 //TODO: to be removed
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("customerLedger", customerLedgerDetails)
        jsonObject.put("openingBalance", openingBalance)
        respond jsonObject, formats: ['json']
    }

    def customerLedger()
    {
        def entities = new EntityRegisterController().getByAffiliateById(session.getAttribute("entityId").toString())
        render(view: '/reports/salesReport/customer-ledger', model: [entities: entities])
    }

}
