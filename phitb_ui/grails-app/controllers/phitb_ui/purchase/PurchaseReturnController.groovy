package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EmailService
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
import phitb_ui.entity.UserRegisterController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class PurchaseReturnController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> supplier = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def reason = new SalesService().getReason()
        def taxRegister = new EntityService().getTaxesByEntity(entityId)
        ArrayList<String> salesmanList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }
        render(view: '/purchase/purchaseReturn/purchase-return', model: [supplier: supplier,
                                                                         divisions: divisions, series:series,
                                                                         salesmanList: salesmanList,
                                                                         priorityList:priorityList, reason: reason,
                                                                         taxRegister:taxRegister])
    }


    def getPurchaseInvByProducts() {
        try {
            def products = new PurchaseService().getPurchaseProductDetailsByProductId(params.productId)
            JSONArray productArray = new JSONArray()
            products.each {
                def purchaseBillShow = new PurchaseService().getRequestWithId(it.billId.toString(), new Links().PURCHASE_BILL_SHOW)
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray) {
                    if (batch.batchNumber == it.batchNumber) {
                        it.put("batch", batch)
                    }
                }
                it.put("prevsqty", 0)
                it.put("prevfqty", 0)
                it.put("bill", JSON.parse(purchaseBillShow.readEntity(String.class)) as JSONObject)
                def purchaseReturns = new SalesService().getReturnDetailsByBatchSalebillProductId(it.productId.toString(), it.batchNumber.toString(), it.billId.toString())
                JSONArray purchaseReturnArray = JSON.parse(purchaseReturns.readEntity(String.class)) as JSONArray
                if (purchaseReturnArray.size() > 0) {
                    double sqty = 0;
                    double fqty = 0;
                    for (JSONObject purchaseReturn : purchaseReturnArray) {

                        if (purchaseReturn.saleBillId == it.billId && (purchaseReturn.returnStatus!="CANCELLED")) {
                            if (purchaseReturn.sqty!= 0) {
                                sqty += Double.parseDouble(purchaseReturn.sqty.toString())
                                it.put("prevsqty", sqty)
                            }
                            if (purchaseReturn.freeQty!= 0) {
                                fqty += Double.parseDouble(purchaseReturn.freeQty.toString())
                                it.put("prevfqty", fqty)
                            }
                        }
                    }
                }
            }
            respond products, formats: ['json'], status: 200
        }
        catch (Exception ex) {
            log.error(controllerName + ":" + ex)
            println(controllerName + ":" + ex)
        }
    }


    def getPurchaseDetailsByProductAndBatch() {
        def billAndBatch = new SalesService().getByBillBatchesProduct(params.billId, params.batch, params.productId)
        JSONObject jsonObject = new JSONObject(billAndBatch.readEntity(String.class))
        def purchaseReturns = new PurchaseService().getReturnDetailsByBatchPurbillProductId(jsonObject.productId.toString()
                , jsonObject.batchNumber.toString(), jsonObject.billId.toString())
        JSONArray purchaseReturnArray = JSON.parse(purchaseReturns.readEntity(String.class)) as JSONArray

        if (purchaseReturnArray.size() > 0) {
            for (JSONObject purchaseReturn : purchaseReturnArray) {
                double sqty = 0;
                double fqty = 0;
                if (purchaseReturn.saleBillId == jsonObject.billId && (purchaseReturn.returnStatus != "CANCELLED")) {
                    if (purchaseReturn.sqty != 0) {
                        sqty += purchaseReturn.sqty
                        jsonObject.put("sqty", jsonObject.sqty - sqty)
                    }
                    if (purchaseReturn.freeQty != 0) {
                        fqty += purchaseReturn.freeQty
                        jsonObject.put("freeQty", jsonObject.freeQty - fqty)
                    }
                }

            }
        }
        respond jsonObject, formats: ['json'], status: 200
    }


    def savePurchaseReturn() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleReturn = new JSONObject()
        JSONArray saleReturnDetails = new JSONArray()
        JSONArray stockArray = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customer = params.supplier
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = params.message
        String lrNo = params.lrno
        String lrDate = params.lrDate
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentReturn = new SalesService().getRecentSaleReturn(financialYear, entityId)
            if (recentReturn != null && recentReturn.size() != 0) {
                finId = Long.parseLong(recentReturn.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentReturn.get("serBillId").toString()) + 1
            } else {
                finId = 1
                serBillId = Long.parseLong(series.get("saleReturnId").toString())
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
            String saleBillId;
            String reason = sr.get("1")
            String productId = sr.get("2")
            String batchNumber = sr.get("3")
            String expDate = sr.get("4")
            String saleQty = sr.get("5")
            String freeQty = sr.get("6")
            String saleRate = sr.get("7")
            String mrp = sr.get("8")
            String taxId = sr.get("19")
            if (sr.has("18")) {
                saleBillId = sr.get("18")
            } else {
                saleBillId = ""
            }
            String invoiceNumber = sr.get("17")
            double discount = UtilsService.round(Double.parseDouble(sr.get("9").toString()), 2)
            String packDesc = sr.get("10")
            double gst = UtilsService.round(Double.parseDouble(sr.get("12").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sr.get("13").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sr.get("14").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sr.get("15").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sr.get("16").toString()), 2)
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
            saleReturnDetail.put("billStatus", billStatus)
            saleReturnDetail.put("serBillId", 0)
            if (saleBillId != "") {
                saleReturnDetail.put("saleBillId", saleBillId)
            } else {
                saleReturnDetail.put("saleBillId", "")
            }
            saleReturnDetail.put("seriesId", seriesId)
            saleReturnDetail.put("productId", productId)
            saleReturnDetail.put("batchNumber", batchNumber)
            saleReturnDetail.put("expiryDate", expDate)
            saleReturnDetail.put("sqty", saleQty)
            saleReturnDetail.put("freeQty", freeQty)
            saleReturnDetail.put("repQty", 0)
            saleReturnDetail.put("invoiceNumber", invoiceNumber)
            saleReturnDetail.put("reason", reason)
            saleReturnDetail.put("sRate", saleRate)
            saleReturnDetail.put("mrp", mrp)
            saleReturnDetail.put("discount", discount)
            saleReturnDetail.put("gstAmount", gst)
            saleReturnDetail.put("sgstAmount", sgst)
            saleReturnDetail.put("cgstAmount", cgst)
            saleReturnDetail.put("igstAmount", igst)
            saleReturnDetail.put("gstId", 1) //TODO: to be changed
            saleReturnDetail.put("amount", value)
            saleReturnDetail.put("fridgeId", 0) //TODO: to be changed
            saleReturnDetail.put("kitName", 0) //TODO: to be changed
            saleReturnDetail.put("saleFinId", "") //TODO: to be changed
            saleReturnDetail.put("redundantBatch", 0) //TODO: to be changed
            saleReturnDetail.put("status", 0)
            saleReturnDetail.put("syncStatus", 0)
            saleReturnDetail.put("financialYear", financialYear)
            saleReturnDetail.put("entityId", entityId)
            saleReturnDetail.put("uuid", params.uuid)
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

            if (gst > 0) {
                gstPercentage = (gst / priceBeforeTaxes) * 100
            }
            if (sgst > 0) {
                sgstPercentage = (sgst / priceBeforeTaxes) * 100
            }
            if (cgst > 0) {
                cgstPercentage = (cgst / priceBeforeTaxes) * 100
            }
            if (igst > 0) {
                igstPercentage = (igst / priceBeforeTaxes) * 100
            }
            saleReturnDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
            saleReturnDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
            saleReturnDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
            saleReturnDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            saleReturnDetails.add(saleReturnDetail)
            //save to sale transaction log
            //save to sale transportation details

            JSONObject stock = new JSONObject()
            stock.put("batchNumber", batchNumber)
            stock.put("saleQty", saleQty)
            stock.put("freeQty", freeQty)
            stock.put("productId", productId)
            stock.put("taxId", taxId)
            stock.put("packDesc", packDesc)
            stock.put("saleRate", saleRate)
            stock.put("reason", reason)
            stock.put("entityId", session.getAttribute('entityId'))
            stock.put("entityTypeId", session.getAttribute('entityTypeId'))
            stock.put("userId", session.getAttribute('userId'))
            def batch = new ProductService().getByBatchAndProductId(batchNumber, productId)
            if (batch.batchNumber == batchNumber && batch.product.id == Integer.parseInt(productId)) {
                stock.put("batch", batch)
            }
            stockArray.add(stock)
//            if (stocks.status == 200)
//            {
//                println("Stocks modified")
//            }
//            else
//            {
//                println("Stocks not modified")
//            }
        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleReturn.put("serBillId", serBillId)
        saleReturn.put("customer", customer)
        saleReturn.put("lrNo", lrNo)
        saleReturn.put("lrDate", lrDate)
        saleReturn.put("customerNumber", 0) //TODO: to be changed
        saleReturn.put("finId", finId)
        saleReturn.put("seriesId", seriesId)
        saleReturn.put("priorityId", priorityId)
        saleReturn.put("financialYear", financialYear)
        saleReturn.put("dueDate", duedate)
        saleReturn.put("uuid", params.uuid)
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
        saleReturn.put("balance", totalAmount)
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
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleReturn", saleReturn)
        jsonObject.put("saleReturnDetails", saleReturnDetails)
        Response response = new SalesService().saveSaleRetrun(jsonObject)
        if (response.status == 200) {
            for (JSONObject stock : stockArray) {
                def stocks = new InventoryService().stocksReturn(stock)
                if (stocks.status == 200) {
                    println("Stocks Updated!")
                } else {

                    println("Stocks not updated!")
                }
            }
            def saleReturns = new JSONObject(response.readEntity(String.class))
            println("Details Saved")

            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject creditEmailConfig
            if(emailSettings!=null){
                if(emailSettings?.creditEmailConfig!=null && emailSettings?.creditEmailConfig!=""){
                    creditEmailConfig = new JSONObject(emailSettings?.creditEmailConfig)
                }
                if(creditEmailConfig?.CRJV_AUTO_EMAIL_AFTER_SAVE == "true"){
                    def entity = new EntityService().getEntityById(saleReturns?.customerId?.toString())
                    if(entity?.email!=null && entity?.email!="" && entity?.email!="NA")
                    {
                        def email = new EmailService().sendEmail(entity.email.trim(), "Sale return Saved", saleReturns?.invoiceNumber, saleReturns?.invoiceNumber, "SALES_RETURN")
                        if (email)
                        {
                            println("Mail Sent..")
                        }
                        else
                        {
                            println("Mail not Sent..")
                        }
                    }
                    else{
                        println("Email not found..")
                    }
                }
            }
            else{
                println("Entity Settings not found!!")
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleReturnDetail", saleReturns)
            respond responseJson, formats: ['json']
        } else {
            response.status == 400
        }
    }







    /*def getPurchaseBillBySupplier()
    {
        def purchasebills = new PurchaseService().getPurchaseBillBySupplier(params.supplierId)
        def apiResponse = new PurchaseService().getRequestWithIdList(purchasebills.id, new Links().PURCHASE_PRODUCT_OF_BILLIDS)
        if(apiResponse?.status == 200)
        {
            def prod = JSON.parse(apiResponse.readEntity(String.class))
            prod.each {product ->
                def index = purchasebills.findIndexOf({
                    it.id == product.billId
                })
                if (index != -1)
                    product.put("billId", purchasebills[index])
            }
            respond prod, formats: ['json'], status: 200
        }
        else
        {
            return []
        }
    }

    def savePurchaseReturn()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject purReturnDetail = new JSONObject()

        String entityId = session.getAttribute("entityId").toString()
        String supplierId = params.supplierId
        String salesmanId = params.salesmanId
        String priorityId = params.priority
        String seriesId = params.series
        String dispatchDate = params.dispatchDate
        String billStatus = params.billStatus
        String message = params.message
        if(!message)
            message = "NA"
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if(!billStatus.equalsIgnoreCase("DRAFT"))
        {
            def recentPurchaseBill = new PurchaseService().getRecentPurchaseBill(financialYear, entityId, billStatus)
            if(recentPurchaseBill != null)
            {
                finId = Long.parseLong(recentPurchaseBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentPurchaseBill.get("serBillId").toString()) + 1
            }
            else {
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
        String entryDate = sdf.format(new Date())
        println(params.saleData)
        JSONArray saleData = new JSONArray(params.saleData)
        for (JSONArray sale : saleData) {
            String productId = sale[2]
            String batchNumber = sale[3]
            String purQty = sale[4]
            String freeQty = sale[5]
            String saleRate = sale[6]
            String mrp = sale[7]
            String value = sale[10]
            String gst = sale[9]
            String igst = sale[13]
            String cgst = sale[12]
            String sgst = sale[11]
            totalSqty += Long.parseLong(purQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble("0")
            purReturnDetail.put("finId", finId)
            purReturnDetail.put("billId",0)
            purReturnDetail.put("billType",0)
            purReturnDetail.put("serBillId",0)
            purReturnDetail.put("series", seriesId)
            purReturnDetail.put("productId", productId)
            purReturnDetail.put("batchNumber", batchNumber)
            purReturnDetail.put("sqty", purQty)
            purReturnDetail.put("supplierId", 1)
            purReturnDetail.put("salesmanId", salesmanId)
            purReturnDetail.put("dispatchDate", dispatchDate)
            purReturnDetail.put("totalDiscount", totalDiscount)
            purReturnDetail.put("freeQty", freeQty)
            purReturnDetail.put("entryDate", entryDate)
            purReturnDetail.put("refId", 0) //TODO: to be changed
            purReturnDetail.put("sRate", saleRate)
            purReturnDetail.put("mrp", mrp)
            purReturnDetail.put("gstAmount", gst)
            purReturnDetail.put("maxDnAmount", 1) //TODO: to be changed
            purReturnDetail.put("amount", value)
            purReturnDetail.put("supplierContact", 1) //TODO: to be changed
            purReturnDetail.put("totalGst", totalGst) //TODO: to be changed
            purReturnDetail.put("totalCgst", totalCgst) //TODO: to be changed
            purReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            purReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            purReturnDetail.put("totalIgst", totalIgst) //TODO: to be changed
            purReturnDetail.put("exempted", 0) //TODO: to be changed
            purReturnDetail.put("type", 0) //TODO: to be changed
            purReturnDetail.put("cashDiscount", 0) //TODO: to be changed
            purReturnDetail.put("items", 0) //TODO: to be changed
            purReturnDetail.put("quantity", 0) //TODO: to be changed
            purReturnDetail.put("totalAmount", totalAmount) //TODO: to be changed
            purReturnDetail.put("balance", totalAmount) //TODO: to be changed
            purReturnDetail.put("dbAdjAmount", 0) //TODO: to be changed
            purReturnDetail.put("debitIds", 0) //TODO: to be changed
            purReturnDetail.put("debitIds", 0) //TODO: to be changed
            purReturnDetail.put("supplierEmail", 0) //TODO: to be changed
            purReturnDetail.put("gross", 0) //TODO: to be changed
            purReturnDetail.put("taxable", 0) //TODO: to be changed
            purReturnDetail.put("saleFinId", "") //TODO: to be changed
            purReturnDetail.put("lockStatus", 0) //TODO: to be changed
            purReturnDetail.put("adjustmentStatus", 0) //TODO: to be changed
            purReturnDetail.put("message", 0)
            purReturnDetail.put("ignoreSold", 0)
            purReturnDetail.put("status", 0)
            purReturnDetail.put("syncStatus", 0)
            purReturnDetail.put("financialYear", financialYear)
            purReturnDetail.put("", financialYear)
            purReturnDetail.put("createdUser", 1)
            purReturnDetail.put("modifiedUser", 1)
            purReturnDetail.put("entityId", entityId)
            purReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //save to sale transaction log
            //save to sale transportation details
        }
        Response response = new PurchaseService().savePurchaseRetrun(purReturnDetail)
        if(response.status == 200)
        {
            def saleOrder = new JSONObject(response.readEntity(String.class))
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("purReturnDetail", saleOrder)
            respond responseJson, formats: ['json'],status: 200
        }
        else
        {
            response.status == 400
        }
    }


    def getPurchaseBillByCustomer()
    {
        def salebills = new SalesService().getSaleBillByCustomer(params.custid)
        def apiResponse = new PurchaseService().getRequestWithIdList(salebills.id, new Links().PURCHASE_PRODUCT_OF_BILLIDS)
        def prod = JSON.parse(apiResponse.readEntity(String.class))
        prod.each {product ->
            def index = salebills.findIndexOf({
                it.id == product.billId
            })
            if(index!= -1)
                product.put("billId", salebills[index])
        }
        respond prod, formats: ['json'], status: 200
    }*/
}
