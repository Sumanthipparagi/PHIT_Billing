package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EInvoiceService
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleOrderEntryController {

    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        render(view: '/sales/saleOrderEntry/sale-order',model: [divisions:divisions,customers:customers, priorityList:priorityList,series:series])
    }


    def saveSaleOrder()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleOrderDetails = new JSONObject()
        JSONArray saleOrderProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = params.message
        if (!message)
        {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT"))
        {
            def recentSaleOrder = new SalesService().getRecentSaleOrder(financialYear, entityId, billStatus)
            if (recentSaleOrder != null && recentSaleOrder.size()!=0)
            {
                finId = Long.parseLong(recentSaleOrder.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleOrder.get("serBillId").toString()) + 1
            }
            else
            {
                finId = 1
                serBillId = Long.parseLong(series.get("saleId").toString())
            }
        }

        long totalSqty = 0
        long totalFqty = 0
        double totalAmount = 0.00
        double totalGst = 0.00
        double totalCgst = 0.00
        double totalSgst = 0.00
        double totalIgst = 0.00
        double totalDiscount = 0.00
        JSONArray saleOrderData = new JSONArray(params.saleData)

        for (JSONObject sale : saleOrderData)
        {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            double discount = UtilsService.round(Double.parseDouble(sale.get("8").toString()), 2)
            String packDesc = sale.get("9")
            double gst = UtilsService.round(Double.parseDouble(sale.get("10").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sale.get("11").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sale.get("12").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sale.get("13").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sale.get("14").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount

            JSONObject saleOrderProductDetail = new JSONObject()
            saleOrderProductDetail.put("finId", finId)
            saleOrderProductDetail.put("billId", 0)
            saleOrderProductDetail.put("billType", 0)
            saleOrderProductDetail.put("serBillId", 0)
            saleOrderProductDetail.put("seriesId", seriesId)
            saleOrderProductDetail.put("productId", productId)
            saleOrderProductDetail.put("batchNumber", batchNumber)
            saleOrderProductDetail.put("expiryDate", expDate)
            saleOrderProductDetail.put("sqty", saleQty)
            saleOrderProductDetail.put("freeQty", freeQty)
            saleOrderProductDetail.put("sqtyReturn", saleQty)
            saleOrderProductDetail.put("fqtyReturn", freeQty)
            saleOrderProductDetail.put("repQty", 0)
            saleOrderProductDetail.put("pRate", 0) //TODO: to be changed
            saleOrderProductDetail.put("sRate", saleRate)
            saleOrderProductDetail.put("mrp", mrp)
            saleOrderProductDetail.put("discount", discount)
            saleOrderProductDetail.put("gstAmount", gst)
            saleOrderProductDetail.put("sgstAmount", sgst)
            saleOrderProductDetail.put("cgstAmount", cgst)
            saleOrderProductDetail.put("igstAmount", igst)



            saleOrderProductDetail.put("gstPercentage", sale.get("16").toString())
            saleOrderProductDetail.put("sgstPercentage", sale.get("17").toString())
            saleOrderProductDetail.put("cgstPercentage", sale.get("18").toString())
            saleOrderProductDetail.put("igstPercentage", sale.get("19").toString())

            saleOrderProductDetail.put("gstId", 0) //TODO: to be changed
            saleOrderProductDetail.put("amount", value)
            saleOrderProductDetail.put("reason", "") //TODO: to be changed
            saleOrderProductDetail.put("fridgeId", 0) //TODO: to be changed
            saleOrderProductDetail.put("kitName", 0) //TODO: to be changed
            saleOrderProductDetail.put("saleFinId", "") //TODO: to be changed
            saleOrderProductDetail.put("redundantBatch", 0) //TODO: to be changed
            saleOrderProductDetail.put("status", 0)
            saleOrderProductDetail.put("syncStatus", 0)
            saleOrderProductDetail.put("financialYear", financialYear)
            saleOrderProductDetail.put("entityId", entityId)
            saleOrderProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleOrderProductDetails.add(saleOrderProductDetail)

            //save to sale transaction log
            //save to sale transportation details

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleOrderDetails.put("serBillId", serBillId)
        saleOrderDetails.put("customerId", customerId)
        saleOrderDetails.put("refNumber", "0")
        saleOrderDetails.put("refDate", entryDate)
        saleOrderDetails.put("orderValidity", entryDate)
        saleOrderDetails.put("salesmanId", 0)
        saleOrderDetails.put("transportTypeId", 0)
        saleOrderDetails.put("totalEstimate", 0)
        saleOrderDetails.put("orderId", 0)
        saleOrderDetails.put("gstId", 0)
        saleOrderDetails.put("orderMechanism", 0)
        saleOrderDetails.put("purchaseQuotationId", 0)
        saleOrderDetails.put("confirmationStatus", 0)
        saleOrderDetails.put("customerNumber", 0) //TODO: to be changed
        saleOrderDetails.put("finId", finId)
        saleOrderDetails.put("seriesId", seriesId)
        saleOrderDetails.put("priorityId", priorityId)
        saleOrderDetails.put("financialYear", financialYear)
        saleOrderDetails.put("dueDate", duedate)
        saleOrderDetails.put("paymentStatus", 0)
        saleOrderDetails.put("userId", session.getAttribute("userId"))
        saleOrderDetails.put("entryDate", entryDate)
        saleOrderDetails.put("orderDate", orderDate)
        saleOrderDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleOrderDetails.put("salesmanId", "0") //TODO: to be changed
        saleOrderDetails.put("salesmanComm", "0") //TODO: to be changed
        saleOrderDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleOrderDetails.put("deliveryManId", "0") //TODO: to be changed
        saleOrderDetails.put("accountModeId", "0") //TODO: to be changed
        saleOrderDetails.put("totalSqty", totalSqty)
        saleOrderDetails.put("totalFqty", totalFqty)
        saleOrderDetails.put("totalGst", totalGst)
        saleOrderDetails.put("totalSgst", totalSgst)
        saleOrderDetails.put("totalCgst", totalCgst)
        saleOrderDetails.put("totalIgst", totalIgst)
        saleOrderDetails.put("totalQty", totalSqty + totalFqty)
        saleOrderDetails.put("totalItems", totalSqty + totalFqty)
        saleOrderDetails.put("totalDiscount", totalDiscount)
        saleOrderDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleOrderDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleOrderDetails.put("totalAmount", totalAmount)
        saleOrderDetails.put("balance", totalAmount)
        saleOrderDetails.put("entityId", entityId)
        saleOrderDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleOrderDetails.put("createdUser", session.getAttribute("userId"))
        saleOrderDetails.put("modifiedUser", session.getAttribute("userId"))
        saleOrderDetails.put("message", message) //TODO: to be changed
        saleOrderDetails.put("gstStatus", "0") //TODO: to be changed
        saleOrderDetails.put("billStatus", billStatus)
        saleOrderDetails.put("lockStatus", 0) //TODO: to be changed
        saleOrderDetails.put("syncStatus", "0") //TODO: to be changed
        saleOrderDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleOrderDetails.put("creditIds", "0") //TODO: to be changed
        saleOrderDetails.put("referralDoctor", "0") //TODO: to be changed
        saleOrderDetails.put("taxable", "1") //TODO: to be changed
        saleOrderDetails.put("cashDiscount", 0) //TODO: to be changed
        saleOrderDetails.put("exempted", 0) //TODO: to be changed
        saleOrderDetails.put("seriesCode", seriesCode)
        saleOrderDetails.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleOrder", saleOrderDetails)
        jsonObject.put("saleOrderProducts", saleOrderProductDetails)
        Response response = new SalesService().saveSaleOrder(jsonObject)
        if (response.status == 200)
        {
            JSONObject saleBillDetail = new JSONObject(response.readEntity(String.class))
            //update stockbook
            for (JSONObject saleOrder : saleOrderData) {
                //check if selected product and batch exists for the entity, if so update data, else add new
                String productId = saleOrder.get("1")
                String batchNumber = saleOrder.get("2")
                JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber, session.getAttribute("entityId").toString())
                if (stockBook) {
                    String saleQty = saleOrder.get("4")
                    String freeQty = saleOrder.get("5")
                    long sQty = Long.parseLong(stockBook.get("remainingQty").toString()) - Long.parseLong(saleQty)
                    long fQty = Long.parseLong(stockBook.get("remainingFreeQty").toString()) - Long.parseLong(freeQty)
                    String expDate = stockBook.get("expDate").toString().split("T")[0]
                    String purcDate = stockBook.get("purcDate").toString().split("T")[0]
                    String manufacturingDate = stockBook.get("manufacturingDate").toString().split("T")[0]
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd")
                    expDate = sdf1.parse(expDate).format("dd-MM-yyyy")
                    purcDate = sdf1.parse(purcDate).format("dd-MM-yyyy")
                    manufacturingDate = sdf1.parse(manufacturingDate).format("dd-MM-yyyy")
                    stockBook.put("expDate", expDate)
                    stockBook.put("purcDate", purcDate)
                    stockBook.put("manufacturingDate", manufacturingDate)
                    stockBook.put("remainingQty", sQty)
                    stockBook.put("remainingFreeQty", fQty)
                    stockBook.put("remainingReplQty", 0)
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
                    stockBook.put("uuid", params.uuid)
                    new InventoryService().updateStockBook(stockBook)
                }
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleBillDetail", saleBillDetail)
            respond responseJson, formats: ['json']
        }
        else
            response.status = 400
    }


    def printSaleOrder()
    {
        String saleOrderId = params.id
        JSONObject saleOrderDetail = new SalesService().getSaleOrderDetailsById(saleOrderId)
        if (saleOrderDetail != null)
        {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByOrder(saleOrderId)
            JSONObject series = new EntityService().getSeriesById(saleOrderDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(saleOrderDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            saleProductDetails.each {
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray)
                {
                    if (batch.batchNumber == it.batchNumber)
                    {
                        it.put("batch", batch)
                    }
                }
                def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            }
            def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : saleProductDetails)
            {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalBeforeTaxes += amountBeforeTaxes
                if (it.igstPercentage > 0)
                {
                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
                    if (igstPercentage == null)
                    {
                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                }
                else
                {
                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
                    if (gstPercentage == null)
                    {
                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)
                    }

                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
                    if (sgstPercentage == null)
                    {
                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
                    if (cgstPercentage == null)
                    {
                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                }
            }

            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst
            render(view: "/sales/saleOrderEntry/sale-order-print", model: [saleBillDetail    : saleOrderDetail,
                                                        saleProductDetails: saleProductDetails,
                                                        series            : series, entity: entity, customer: customer, city: city,
                                                        total             : total, custcity: custcity,
                                                        termsConditions   : termsConditions,
                                                        totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                        totaldiscount     : totaldiscount,
                                                        gstGroup          : gstGroup,
                                                        sgstGroup         : sgstGroup,
                                                        cgstGroup         : cgstGroup,
                                                        igstGroup         : igstGroup,
                                                        totalBeforeTaxes  : totalBeforeTaxes,
            ])
        }
        else
        {

            render("No Bill Found")
        }
    }

}
