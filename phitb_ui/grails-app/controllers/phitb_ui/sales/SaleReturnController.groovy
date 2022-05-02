package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleReturnController {

    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def reason = new SalesService().getReason()
        ArrayList<String> salesmanList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }
        render(view: '/sales/saleRetrun/sale-returns', model: [customers                   : customers, divisions: divisions, series: series,
                                                               salesmanList                : salesmanList, priorityList:
                                                                       priorityList, reason: reason])
    }


    def getSaleBillByCustomer() {
        def salebills = new SalesService().getSaleBillByCustomer(params.custid, session.getAttribute('financialYear')
                .toString(), session.getAttribute('entityId').toString())
        def apiResponse = new SalesService().getRequestWithIdList(salebills.id, new Links().SALE_PRODUCT_OF_BILLIDS)
        def prod = JSON.parse(apiResponse.readEntity(String.class))
        prod.each { product ->
            def index = salebills.findIndexOf({
                it.id == product.billId
            })
            if (index != -1)
                product.put("billId", salebills[index])
        }
        respond prod, formats: ['json'], status: 200
    }


    def getSaleInvByProducts() {
        try {
            def products = new SalesService().getSaleProductDetailsByProductId(params.productId)
            products.each {
                def apiResponse = new SalesService().getRequestWithId(it.billId.toString(), new Links().SALE_BILL_SHOW)
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray) {
                    if (batch.batchNumber == it.batchNumber) {
                        it.put("batch", batch)
                    }
                }
                it.put("bill", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            }
            respond products, formats: ['json'], status: 200
        }
        catch (Exception ex) {
            log.error(controllerName + ":" + ex)
            println(controllerName + ":" + ex)
        }
    }


    def saveSaleReturn() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleReturn = new JSONObject()
        JSONArray saleReturnDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customer = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = params.message
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            if (recentBill != null && recentBill.size() != 0) {
                finId = Long.parseLong(recentBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentBill.get("serBillId").toString()) + 1
            } else {
                finId = 1
                serBillId = Long.parseLong(series.get("purId").toString())
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
        JSONArray saleRetrunData = new JSONArray(params.saleReturnData)
        for (JSONObject sr : saleRetrunData) {
            String reason = sr.get("1")
            String productId = sr.get("2")
            String batchNumber = sr.get("3")
            String expDate = sr.get("4")
            String saleQty = sr.get("5")
            String freeQty = sr.get("6")
            String saleRate = sr.get("7")
            String mrp = sr.get("8")
            String invoiceNumber = sr.get("16")
            double discount = UtilsService.round(Double.parseDouble(sr.get("9").toString()), 2)
            String packDesc = sr.get("10")
            double gst = UtilsService.round(Double.parseDouble(sr.get("11").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sr.get("12").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sr.get("13").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sr.get("14").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sr.get("15").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount
            JSONObject saleReturnDetail = new JSONObject()
            saleReturnDetail.put("finId", finId)
            saleReturnDetail.put("reason", reason)
            saleReturnDetail.put("billId", 0)
            saleReturnDetail.put("billType", 0)
            saleReturnDetail.put("serBillId", 0)
            saleReturnDetail.put("seriesId", seriesId)
            saleReturnDetail.put("productId", productId)
            saleReturnDetail.put("batchNumber", batchNumber)
            saleReturnDetail.put("expiryDate", expDate)
            saleReturnDetail.put("sqty", saleQty)
            saleReturnDetail.put("freeQty", freeQty)
            saleReturnDetail.put("repQty", 0)
            saleReturnDetail.put("invoiceNumber", invoiceNumber)
            saleReturnDetail.put("sRate", saleRate)
            saleReturnDetail.put("mrp", mrp)
            saleReturnDetail.put("discount", discount)
            saleReturnDetail.put("gstAmount", gst)
            saleReturnDetail.put("sgstAmount", sgst)
            saleReturnDetail.put("cgstAmount", cgst)
            saleReturnDetail.put("igstAmount", igst)
            saleReturnDetail.put("gstId", 1) //TODO: to be changed
            saleReturnDetail.put("amount", value)
            saleReturnDetail.put("reason", "") //TODO: to be changed
            saleReturnDetail.put("fridgeId", 0) //TODO: to be changed
            saleReturnDetail.put("kitName", 0) //TODO: to be changed
            saleReturnDetail.put("saleFinId", "") //TODO: to be changed
            saleReturnDetail.put("redundantBatch", 0) //TODO: to be changed
            saleReturnDetail.put("status", 0)
            saleReturnDetail.put("syncStatus", 0)
            saleReturnDetail.put("financialYear", financialYear)
            saleReturnDetail.put("entityId", entityId)
            saleReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //GST percentage Calculation
            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(saleQty) * Double.parseDouble(saleRate)), 2)
            if (discount > 0) {
                priceBeforeTaxes = priceBeforeTaxes - (priceBeforeTaxes * (discount / 100))
            }
            double gstPercentage = 0.0
            double sgstPercentage = 0.0
            double cgstPercentage = 0.0
            double igstPercentage = 0.0

            if (gst > 0)
                gstPercentage = (gst / priceBeforeTaxes) * 100
            if (sgst > 0)
                sgstPercentage = (sgst / priceBeforeTaxes) * 100
            if (cgst > 0)
                cgstPercentage = (cgst / priceBeforeTaxes) * 100
            if (igst > 0)
                igstPercentage = (igst / priceBeforeTaxes) * 100
            saleReturnDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
            saleReturnDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
            saleReturnDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
            saleReturnDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            saleReturnDetails.add(saleReturnDetail)

            //save to sale transaction log
            //save to sale transportation details

            def stocks = new InventoryService().stocksIncrease(batchNumber, saleQty, freeQty, reason, productId)
            if (stocks.status == 200) {
                println("Inc")
            } else {
                println("not inc")
            }

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleReturn.put("serBillId", serBillId)
        saleReturn.put("customer", customer)
        saleReturn.put("customerNumber", 0) //TODO: to be changed
        saleReturn.put("finId", finId)
        saleReturn.put("seriesId", seriesId)
        saleReturn.put("priorityId", priorityId)
        saleReturn.put("financialYear", financialYear)
        saleReturn.put("dueDate", duedate)
        saleReturn.put("paymentStatus", 0)
        saleReturn.put("dispatchDate", 0)
        saleReturn.put("supplierContact", 0)
        saleReturn.put("supplierEmail", 0)
        saleReturn.put("supplierEmail", 0)
        saleReturn.put("dispatchStatus", 0)
        saleReturn.put("debitIds", 0)
        saleReturn.put("refId", 0)
        saleReturn.put("userId", session.getAttribute("userId"))
        saleReturn.put("entryDate", entryDate)
        saleReturn.put("orderDate", orderDate)
        saleReturn.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleReturn.put("salesmanId", "0") //TODO: to be changed
        saleReturn.put("salesmanComm", "0") //TODO: to be changed
        saleReturn.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleReturn.put("deliveryManId", "0") //TODO: to be changed
        saleReturn.put("accountModeId", "0") //TODO: to be changed
        saleReturn.put("totalSqty", totalSqty)
        saleReturn.put("totalFqty", totalFqty)
        saleReturn.put("totalGst", totalGst)
        saleReturn.put("totalSgst", totalSgst)
        saleReturn.put("totalCgst", totalCgst)
        saleReturn.put("totalIgst", totalIgst)
        saleReturn.put("totalQuantity", totalSqty + totalFqty)
        saleReturn.put("totalItems", totalSqty + totalFqty)
        saleReturn.put("totalDiscount", totalDiscount)
        saleReturn.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleReturn.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleReturn.put("totalAmount", totalAmount)
        saleReturn.put("godownId", 0)
        saleReturn.put("maxDnAmount", 0)
        saleReturn.put("purcId", 0)
        saleReturn.put("items", 0)
        saleReturn.put("type", 0)
        saleReturn.put("supplierBillId", 0)
        saleReturn.put("billingDate", entryDate)
        saleReturn.put("balAmount", totalAmount)
        saleReturn.put("submitStatus", 0)//TODO: to be changed
        saleReturn.put("addAmount", 0)//TODO: to be changed
        saleReturn.put("lessAmount", 0)//TODO: to be changed
        saleReturn.put("adjustedAmount", 0)//TODO: to be changed
        saleReturn.put("entityId", entityId)
        saleReturn.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleReturn.put("createdUser", session.getAttribute("userId"))
        saleReturn.put("modifiedUser", session.getAttribute("userId"))
        saleReturn.put("message", message) //TODO: to be changed
        saleReturn.put("gstStatus", "0") //TODO: to be changed
        saleReturn.put("expectedDeliveryDate", entryDate) //TODO: to be changed
        saleReturn.put("billStatus", billStatus)
        saleReturn.put("quantity", 0)
        saleReturn.put("dbAdjAmount", 0)
        saleReturn.put("adjustmentStatus", 0)
        saleReturn.put("balance", 0)
        saleReturn.put("ignoreSold", 0)
        saleReturn.put("lockStatus", 0) //TODO: to be changed
        saleReturn.put("syncStatus", "0") //TODO: to be changed
        saleReturn.put("productDiscount", 0) //TODO: to be changed
        saleReturn.put("receivedDate", entryDate) //TODO: to be changed
        saleReturn.put("receivedBy", entityId) //TODO: to be changed
        saleReturn.put("creditId", 0) //TODO: to be changed
        saleReturn.put("debitId", 0) //TODO: to be changed
        saleReturn.put("crDbAmount", 0) //TODO: to be changed
        saleReturn.put("payableAmount", 0) //TODO: to be changed
        saleReturn.put("gross", 0) //TODO: to be changed
        saleReturn.put("netAmount", 0) //TODO: to be changed
        saleReturn.put("cashDiscount", "0") //TODO: to be changed
        saleReturn.put("taxable", "1") //TODO: to be changed
        saleReturn.put("cashDiscount", 0) //TODO: to be changed
        saleReturn.put("exempted", 0) //TODO: to be changed
        saleReturn.put("seriesCode", seriesCode)
        Response resp = new SalesService().saveSaleRetrun(saleReturn)
        if (resp.status == 200) {
            def saleReturns = new JSONObject(resp.readEntity(String.class))
            //save to sale return details
            for (JSONObject saleRetrunDetail : saleReturnDetails) {
                saleRetrunDetail.put("billId", saleReturns.get("id"))
                saleRetrunDetail.put("taxId", saleReturns.get("taxable"))
                saleRetrunDetail.put("billType", 0) //0 Sale, 1 Purchase
                saleRetrunDetail.put("serBillId", saleReturns.get("serBillId"))
                def resp1 = new SalesService().saveSaleRetrunDetails(saleRetrunDetail)
                if (resp1.status == 200) {
                    println("Return Detail Saved")
                } else {
                    println("Return Detail Failed")
                }
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleReturnDetail", saleReturns)
            respond responseJson, formats: ['json']
        } else {
            response.status == 400
        }
    }

    def printSaleReturn() {

        String saleReturnId = params.id
        JSONObject saleReturnDetail = new SalesService().getSaleReturnDetailsById(saleReturnId)
        if (saleReturnDetail != null) {
            JSONArray saleRetrunDetails = new SalesService().getSaleRetrunDetailsByBill(saleReturnId)
            JSONObject series = new EntityService().getSeriesById(saleReturnDetail.get("series").toString())
            JSONObject customer = new EntityService().getEntityById(saleReturnDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            saleRetrunDetails.each {
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray) {
                    if (batch.batchNumber == it.batchNumber) {
                        it.put("batch", batch)
                    }
                }
                def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            }
            def totalcgst = UtilsService.round(saleRetrunDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(saleRetrunDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(saleRetrunDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(saleRetrunDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : saleRetrunDetails) {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalBeforeTaxes += amountBeforeTaxes
                if (it.igstPercentage > 0) {
                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
                    if (igstPercentage == null) {
                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                    } else {
                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                } else {
                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
                    if (gstPercentage == null) {
                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
                    } else {
                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)
                    }

                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
                    if (sgstPercentage == null) {
                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
                    } else {
                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
                    if (cgstPercentage == null) {
                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
                    } else {
                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                }
            }

            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst

            render(view: "/sales/saleRetrun/sale-return-print", model: [saleBillDetail    : saleReturnDetail,
                                                                        saleProductDetails: saleRetrunDetails,
                                                                        series            : series, entity: entity, customer: customer, city: city,
                                                                        total             : total, custcity: custcity,
                                                                        termsConditions   : termsConditions,
                                                                        totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                        totaldiscount     : totaldiscount,
                                                                        gstGroup          : gstGroup,
                                                                        sgstGroup         : sgstGroup,
                                                                        cgstGroup         : cgstGroup,
                                                                        igstGroup         : igstGroup,
                                                                        totalBeforeTaxes  : totalBeforeTaxes
            ])
        } else {

            render("No Bill Found")
        }
    }
}
