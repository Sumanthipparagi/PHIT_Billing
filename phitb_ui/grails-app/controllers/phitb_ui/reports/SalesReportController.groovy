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
import java.util.stream.Collectors

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
        //TODO: 1. Get Sale Invoice - debit [DONE}
        //TODO: 2. Get Sale Order - debit [done]
        //TODO: 3. Get Purchase Return - debit [done}
        //TODO: 4. Get Delivery Challan - debit [done]
        //TODO: 5. Get CREDIT JV - debit
        //TODO: 6. Get Payments - debit
        //TODO: 7. Get GTN - debit [DONE}
        //TODO: 8. Get Sale Return - credit [DONE}
        //TODO: 9. Get Purchase Invoice - credit
        //TODO: 10. Get DEBIT JV - credit
        //TODO: 11. Get Receipts - credit [DONE}


        //sort based on date
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        double openingBalance = 0
        ArrayList<JSONObject> customerLedgerDetails = new ArrayList<>()

        //get data within daterange
        JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
        JSONArray saleReturn = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
        JSONArray saleOrder = new SalesService().getSaleOrderByDateRange(dateRange,entityId)
        JSONArray deliveryChallan = new SalesService().getDeliveryChallanByDateRange(dateRange,entityId)
        JSONArray goodsTransferNotes = new SalesService().getGTNByDateRange(dateRange, entityId)
        JSONArray receiptDetails = new AccountsService().getReceiptDetailsByDateRange(dateRange, entityId)
        JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
        JSONArray purchaseBills = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
        JSONArray creditJv = new AccountsService().getCreditJvByDateRange(dateRange, entityId)
        JSONArray debitJv = new AccountsService().getDebitJvByDateRange(dateRange,entityId)
        JSONArray paymentDetails = new AccountsService().getPaymentDetailsByDateRange(dateRange,entityId)
        JSONArray purchaseOrder = new PurchaseService().getPurchaseOrderByDateRange(dateRange,entityId)



        //======DEBITS=======
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
            }
            else
            {
                println("Not Included: "+ sb.invoiceTotal)
            }
        }

        double saleOrderBalance = 0.0
        for (Object so : saleOrder) {
            if(so?.billStatus == "ACTIVE" && so?.deleted == false) {
                saleOrderBalance += so?.totalAmount
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", so?.entryDate)
                customerLedgerEntry.put("transactionNumber", so?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Sale Order")
                customerLedgerEntry.put("amount", so?.totalAmount)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ so.totalAmount)
            }
        }

        double creditJvBalance = 0.0
        for (Object crjv : creditJv) {
            creditJvBalance += crjv?.amount
            JSONObject customerLedgerEntry = new JSONObject()
            customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(crjv?.transactionDate)))
            customerLedgerEntry.put("transactionNumber", crjv?.transactionId)
            customerLedgerEntry.put("transactionDescription", "Credit JV")
            customerLedgerEntry.put("amount", crjv?.amount)
            customerLedgerEntry.put("type", "DEBIT")
            customerLedgerDetails.add(customerLedgerEntry)
        }

        double deliveryChallanBalance = 0.0
        for (Object dc : deliveryChallan) {
            if(dc?.billStatus == "ACTIVE" && dc?.deleted == false) {
                deliveryChallanBalance += dc?.invoiceTotal
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", dc?.entryDate)
                customerLedgerEntry.put("transactionNumber", dc?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Delivery Challan")
                customerLedgerEntry.put("amount", dc?.invoiceTotal)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ dc.invoiceTotal)
            }
        }

        double purchaseRetrunBalance = 0.0
        for (Object pr : purchaseReturns) {
            if(pr?.billStatus == "ACTIVE" && pr?.deleted == false) {
                purchaseRetrunBalance += pr?.totalAmount
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(pr?.dateCreated)))
                customerLedgerEntry.put("transactionNumber", pr?.totalAmount)
                customerLedgerEntry.put("transactionDescription", "Purchase Return")
                customerLedgerEntry.put("amount", pr?.totalAmount)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ pr.totalAmount)
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

        double paymentAmount = 0.0
        for (Object pd : paymentDetails) {
            if(pd?.approvedStatus == "ACTIVE" && pd?.cancelledDate == null) {
                paymentAmount += pd?.amountPaid
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(pd?.dateCreated)))
                customerLedgerEntry.put("transactionNumber", pd?.paymentId)
                customerLedgerEntry.put("transactionDescription", "Payments")
                customerLedgerEntry.put("amount", pd?.amountPaid)
                customerLedgerEntry.put("type", "DEBIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ pd.totalAmount)
            }
        }


        //======CREDITS=======
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

        double receiptAmount = 0.0
        for (Object rd : receiptDetails) {
            if(rd?.approvedStatus == "ACTIVE" && rd?.cancelledDate == null) {
                receiptAmount += rd?.amountPaid
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(rd?.dateCreated)))
                customerLedgerEntry.put("transactionNumber", rd?.receiptId)
                customerLedgerEntry.put("transactionDescription", "Receipt")
                customerLedgerEntry.put("amount", rd?.amountPaid)
                customerLedgerEntry.put("type", "CREDIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ rd.totalAmount)
            }
        }

        double purchaseAmount = 0.0
        for (Object pb : purchaseBills) {
            if(pb?.billStatus == "ACTIVE" && pb?.cancelledDate == null) {
                purchaseAmount += pb?.totalAmount
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(pb?.dateCreated)))
                customerLedgerEntry.put("transactionNumber", pb?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Purchase Invoice")
                customerLedgerEntry.put("amount", pb?.totalAmount)
                customerLedgerEntry.put("type", "CREDIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ pb.totalAmount)
            }
        }

        double purchaseOrderAmount = 0.0
        for (Object po : purchaseOrder) {
            if(po?.billStatus == "ACTIVE" && po?.cancelledDate == null) {
                purchaseOrderAmount += po?.totalAmount
                JSONObject customerLedgerEntry = new JSONObject()
                customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(po?.dateCreated)))
                customerLedgerEntry.put("transactionNumber", po?.invoiceNumber)
                customerLedgerEntry.put("transactionDescription", "Purchase Order")
                customerLedgerEntry.put("amount", po?.totalAmount)
                customerLedgerEntry.put("type", "CREDIT")
                customerLedgerDetails.add(customerLedgerEntry)
            }
            else
            {
                println("Not Included: "+ po?.totalAmount)
            }
        }

        double debitJvBalance = 0.0
        for (Object dbjv : debitJv) {
            debitJvBalance += dbjv?.amount
            JSONObject customerLedgerEntry = new JSONObject()
            customerLedgerEntry.put("transactionDate", sdf1.format(sdf2.parse(dbjv?.transactionDate)))
            customerLedgerEntry.put("transactionNumber", dbjv?.transactionId)
            customerLedgerEntry.put("transactionDescription", "Debit JV")
            customerLedgerEntry.put("amount", dbjv?.amount)
            customerLedgerEntry.put("type", "CREDIT")
            customerLedgerDetails.add(customerLedgerEntry)
        }


        //sort by date
        customerLedgerDetails.sort(new Comparator<JSONObject>() {
            @Override
            int compare(JSONObject o1, JSONObject o2) {
                String dateO1String = o1.get("transactionDate").toString().replace("Z", "")
                dateO1String = dateO1String.replace("T", " ")
                dateO1String = dateO1String.replace("/", "-")
                Date dateO1 = sdf1.parse(dateO1String)

                String dateO2String = o2.get("transactionDate").toString().replace("Z", "")
                dateO2String = dateO2String.replace("T", " ")
                dateO2String = dateO2String.replace("/", "-")
                Date dateO2 = sdf1.parse(dateO2String)

                return dateO1.compareTo(dateO2)
            }
        })

        def entity = new EntityService().getEntityById(session.getAttribute('entityId').toString())
        def entityOpeningBalance = entity?.openingBalance
        println("entityOpeningBalance"+entityOpeningBalance)

        String newDateRange = session.getAttribute('startDate').toString() +" - "+ dateRange.split("-")[0].trim()
        println(session.getAttribute('startDate').toString())
        //Debit
        def saleBillsTotal = new SalesService().getSaleBillByDateRange(newDateRange, entityId).stream().filter({i ->
            i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.invoiceTotal}).sum()

        def saleOrderTotal = new SalesService().getSaleOrderByDateRange(newDateRange, entityId).stream().filter({i ->
            i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.totalAmount}).sum()

        def gtnTotal = new SalesService().getGTNByDateRange(newDateRange, entityId).stream().filter({i -> i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i?.totalAmount}).sum()

        def purchaseReturnTotal = new PurchaseService().getPurchaseRetrunByDateRange(newDateRange, entityId).stream()
                .filter({i -> i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.totalAmount}).sum()

        def saleReturnTotal = new SalesService().getSaleReturnByDateRange(newDateRange, entityId).stream().filter({i ->
            i?.returnStatus == "ACTIVE" && i?.balance > 0}).mapToDouble({i -> i.totalAmount}).sum()


        def deliveryChallanTotal = new SalesService().getDeliveryChallanByDateRange(newDateRange, entityId).stream().filter({i -> i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.totalAmount}).sum()

        def creditJvTotal = new AccountsService().getCreditJvByDateRange(newDateRange, entityId).stream().mapToDouble({i -> i.amount}).sum()

        def paymentTotal = new AccountsService().getPaymentDetailsByDateRange(newDateRange, entityId).stream().filter({i -> i?.approvedStatus == "ACTIVE" && i?.cancelledDate == null}).mapToDouble({i -> i.amountPaid}).sum()


//        credit
        def receiptTotal= new AccountsService().getReceiptDetailsByDateRange(newDateRange, entityId).stream().filter({i -> i?.approvedStatus == "ACTIVE" && i?.cancelledDate == null}).mapToDouble({i -> i.amountPaid}).sum()

        def purchaseBillsTotal = new PurchaseService().getPurchaseBillByDateRange(newDateRange, entityId).stream()
                .filter({i -> i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.totalAmount}).sum()


        def debitJvTotal = new AccountsService().getDebitJvByDateRange(newDateRange, entityId).stream().mapToDouble({i -> i.amount}).sum()

        def purchaseOrderTotal = new PurchaseService().getPurchaseOrderByDateRange(newDateRange, entityId).stream()
                .filter({i -> i?.billStatus == "ACTIVE" && i?.deleted == false}).mapToDouble({i -> i.totalAmount}).sum()


        println("SaleBillTotal "+ saleBillsTotal)
        println("SaleRetrunTotal "+ saleReturnTotal)
        println("SaleOrderTotal "+ saleOrderTotal)
        println("deliveryChallanTotal "+ deliveryChallanTotal)
        println("gtnTotal "+ gtnTotal)
        println("receiptTotal "+ receiptTotal)
        println("purchaseReturnTotal "+ purchaseReturnTotal)
        println("purchaseBillsTotal "+ purchaseBillsTotal)
        println("creditJvTotal "+ creditJvTotal)
        println("debitJvTotal "+ debitJvTotal)
        println("purchaseOrderTotal "+ purchaseOrderTotal)
        println("paymentTotal "+ paymentTotal)

//        openingBalance = 1000000 //TODO: to be removed
            openingBalance = entityOpeningBalance+(saleBillsTotal.toDouble() + saleOrderTotal.toDouble() + gtnTotal
                    .toDouble() + deliveryChallanTotal.toDouble() + debitJvTotal.toDouble() + receiptTotal.toDouble() + purchaseReturnTotal.toDouble()) - (purchaseBillsTotal.toDouble() - purchaseOrderTotal.toDouble() - saleReturnTotal.toDouble() - creditJvTotal.toDouble() - paymentTotal.toDouble())
        println(openingBalance)
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
