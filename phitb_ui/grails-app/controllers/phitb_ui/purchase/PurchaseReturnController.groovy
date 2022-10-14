package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
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

import javax.persistence.Id
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

                        if (purchaseReturn.purBillId == it.billId && (purchaseReturn.returnStatus!="CANCELLED")) {
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
        def billAndBatch = new PurchaseService().getByBillBatchesProduct(params.billId, params.batch, params.productId)
        JSONObject jsonObject = new JSONObject(billAndBatch.readEntity(String.class))
        def purchaseReturns = new PurchaseService().getReturnDetailsByBatchPurbillProductId(jsonObject.productId.toString()
                , jsonObject.batchNumber.toString(), jsonObject.billId.toString())
        JSONArray purchaseReturnArray = JSON.parse(purchaseReturns.readEntity(String.class)) as JSONArray

        if (purchaseReturnArray.size() > 0) {
            for (JSONObject purchaseReturn : purchaseReturnArray) {
                double sqty = 0;
                double fqty = 0;
                if (purchaseReturn.purBillId == jsonObject.billId && (purchaseReturn.returnStatus != "CANCELLED")) {
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
        JSONObject purchaseReturn = new JSONObject()
        JSONArray purchaseReturnDetails = new JSONArray()
        JSONArray stockArray = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String supplierId = params.supplier
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
            String purBillId;
            String reason = sr.get("1")
            String productId = sr.get("2")
            String batchNumber = sr.get("3")
            String expDate = sr.get("4")
            String saleQty = sr.get("5")
            String freeQty = sr.get("6")
            String pRate = sr.get("7")
            String mrp = sr.get("8")
            String taxId = sr.get("19")
            if (sr.has("18")) {
                purBillId = sr.get("18")
            } else {
                purBillId = ""
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
            JSONObject purchaseReturnDetail = new JSONObject()
            purchaseReturnDetail.put("finId", finId)
            purchaseReturnDetail.put("reason", reason)
            purchaseReturnDetail.put("billId", 0)
            purchaseReturnDetail.put("billType", 0)
            purchaseReturnDetail.put("billStatus", billStatus)
            purchaseReturnDetail.put("serBillId", 0)
            if (purBillId != "") {
                purchaseReturnDetail.put("purBillId", purBillId)
            } else {
                purchaseReturnDetail.put("purBillId", "")
            }
            purchaseReturnDetail.put("seriesId", seriesId)
            purchaseReturnDetail.put("productId", productId)
            purchaseReturnDetail.put("batchNumber", batchNumber)
            purchaseReturnDetail.put("expiryDate", expDate)
            purchaseReturnDetail.put("sqty", saleQty)
            purchaseReturnDetail.put("freeQty", freeQty)
            purchaseReturnDetail.put("repQty", 0)
            purchaseReturnDetail.put("invoiceNumber", invoiceNumber)
            purchaseReturnDetail.put("reason", reason)
            purchaseReturnDetail.put("pRate", pRate)
            purchaseReturnDetail.put("mrp", mrp)
            purchaseReturnDetail.put("discount", discount)
            purchaseReturnDetail.put("gstAmount", gst)
            purchaseReturnDetail.put("sgstAmount", sgst)
            purchaseReturnDetail.put("cgstAmount", cgst)
            purchaseReturnDetail.put("igstAmount", igst)
            purchaseReturnDetail.put("gstId", 1) //TODO: to be changed
            purchaseReturnDetail.put("amount", value)
            purchaseReturnDetail.put("fridgeId", 0) //TODO: to be changed
            purchaseReturnDetail.put("kitName", 0) //TODO: to be changed
            purchaseReturnDetail.put("saleFinId", "") //TODO: to be changed
            purchaseReturnDetail.put("redundantBatch", 0) //TODO: to be changed
            purchaseReturnDetail.put("status", 0)
            purchaseReturnDetail.put("syncStatus", 0)
            purchaseReturnDetail.put("financialYear", financialYear)
            purchaseReturnDetail.put("entityId", entityId)
            purchaseReturnDetail.put("uuid", params.uuid)
            purchaseReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //GST percentage Calculation
            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(saleQty) * Double.parseDouble(pRate)), 2)
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
            purchaseReturnDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
            purchaseReturnDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
            purchaseReturnDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
            purchaseReturnDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            purchaseReturnDetails.add(purchaseReturnDetail)
            //save to sale transaction log
            //save to sale transportation details

            JSONObject stock = new JSONObject()
            stock.put("batchNumber", batchNumber)
            stock.put("saleQty", saleQty)
            stock.put("freeQty", freeQty)
            stock.put("productId", productId)
            stock.put("taxId", taxId)
            stock.put("packDesc", packDesc)
            stock.put("saleRate", pRate)
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
        purchaseReturn.put("serBillId", serBillId)
        purchaseReturn.put("supplierId", supplierId)
        purchaseReturn.put("refNo", lrNo)
        purchaseReturn.put("refDate", lrDate)
        purchaseReturn.put("customerNumber", 0) //TODO: to be changed
        purchaseReturn.put("finId", finId)
        purchaseReturn.put("seriesId", seriesId)
        purchaseReturn.put("priorityId", priorityId)
        purchaseReturn.put("financialYear", financialYear)
        purchaseReturn.put("dueDate", duedate)
        purchaseReturn.put("uuid", params.uuid)
        purchaseReturn.put("paymentStatus", 0)
        purchaseReturn.put("dispatchDate", 0)
        purchaseReturn.put("supplierContact", 0)
        purchaseReturn.put("supplierEmail", 0)
        purchaseReturn.put("supplierEmail", 0)
        purchaseReturn.put("dispatchStatus", 0)
        purchaseReturn.put("debitIds", 0)
        purchaseReturn.put("refId", 0)
        purchaseReturn.put("userId", session.getAttribute("userId"))
        purchaseReturn.put("entryDate", entryDate)
        purchaseReturn.put("orderDate", orderDate)
        purchaseReturn.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        purchaseReturn.put("salesmanId", "0") //TODO: to be changed
        purchaseReturn.put("salesmanComm", "0") //TODO: to be changed
        purchaseReturn.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        purchaseReturn.put("deliveryManId", "0") //TODO: to be changed
        purchaseReturn.put("accountModeId", "0") //TODO: to be changed
        purchaseReturn.put("totalSqty", totalSqty)
        purchaseReturn.put("totalFqty", totalFqty)
        purchaseReturn.put("totalGst", totalGst)
        purchaseReturn.put("totalSgst", totalSgst)
        purchaseReturn.put("totalCgst", totalCgst)
        purchaseReturn.put("totalIgst", totalIgst)
        purchaseReturn.put("totalQuantity", totalSqty + totalFqty)
        purchaseReturn.put("totalItems", totalSqty + totalFqty)
        purchaseReturn.put("totalDiscount", totalDiscount)
        purchaseReturn.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        purchaseReturn.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        purchaseReturn.put("totalAmount", totalAmount)
        purchaseReturn.put("godownId", 0)
        purchaseReturn.put("maxDnAmount", 0)
        purchaseReturn.put("purcId", 0)
        purchaseReturn.put("items", 0)
        purchaseReturn.put("type", 0)
        purchaseReturn.put("supplierBillId", 0)
        purchaseReturn.put("billingDate", entryDate)
        purchaseReturn.put("balAmount", totalAmount)
        purchaseReturn.put("submitStatus", 0)//TODO: to be changed
        purchaseReturn.put("addAmount", 0)//TODO: to be changed
        purchaseReturn.put("lessAmount", 0)//TODO: to be changed
        purchaseReturn.put("adjustedAmount", 0)//TODO: to be changed
        purchaseReturn.put("entityId", entityId)
        purchaseReturn.put("entityTypeId", session.getAttribute("entityTypeId"))
        purchaseReturn.put("createdUser", session.getAttribute("userId"))
        purchaseReturn.put("modifiedUser", session.getAttribute("userId"))
        purchaseReturn.put("message", message) //TODO: to be changed
        purchaseReturn.put("gstStatus", "0") //TODO: to be changed
        purchaseReturn.put("expectedDeliveryDate", entryDate) //TODO: to be changed
        purchaseReturn.put("billStatus", billStatus)
        purchaseReturn.put("quantity", 0)
        purchaseReturn.put("returnStatus", "ACTIVE")
        purchaseReturn.put("dbAdjAmount", 0)
        purchaseReturn.put("adjustmentStatus", 0)
        purchaseReturn.put("balance", totalAmount)
        purchaseReturn.put("ignoreSold", 0)
        purchaseReturn.put("lockStatus", 0) //TODO: to be changed
        purchaseReturn.put("syncStatus", "0") //TODO: to be changed
        purchaseReturn.put("productDiscount", 0) //TODO: to be changed
        purchaseReturn.put("receivedDate", entryDate) //TODO: to be changed
        purchaseReturn.put("receivedBy", entityId) //TODO: to be changed
        purchaseReturn.put("creditId", 0) //TODO: to be changed
        purchaseReturn.put("debitId", 0) //TODO: to be changed
        purchaseReturn.put("crDbAmount", 0) //TODO: to be changed
        purchaseReturn.put("payableAmount", 0) //TODO: to be changed
        purchaseReturn.put("gross", 0) //TODO: to be changed
        purchaseReturn.put("netAmount", 0) //TODO: to be changed
        purchaseReturn.put("cashDiscount", "0") //TODO: to be changed
        purchaseReturn.put("taxable", "1") //TODO: to be changed
        purchaseReturn.put("cashDiscount", 0) //TODO: to be changed
        purchaseReturn.put("exempted", 0) //TODO: to be changed
        purchaseReturn.put("seriesCode", seriesCode)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("purchaseReturn", purchaseReturn)
        jsonObject.put("purchaseReturnDetail", purchaseReturnDetails)
        Response response = new PurchaseService().savePurchaseRetrun(jsonObject)
        if (response.status == 200) {
            for (JSONObject stock : stockArray) {
                def stocks = new InventoryService().stocksReturn(stock)
                if (stocks.status == 200) {
                    println("Stocks Updated!")
                } else {

                    println("Stocks not updated!")
                }
            }
            def purchaseReturns = new JSONObject(response.readEntity(String.class))
            println("Details Saved")

            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject creditEmailConfig
            if(emailSettings!=null){
                if(emailSettings?.creditEmailConfig!=null && emailSettings?.creditEmailConfig!=""){
                    creditEmailConfig = new JSONObject(emailSettings?.creditEmailConfig)
                }
                if(creditEmailConfig?.CRJV_AUTO_EMAIL_AFTER_SAVE == "true"){
                    def entity = new EntityService().getEntityById(purchaseReturns?.customerId?.toString())
                    if(entity?.email!=null && entity?.email!="" && entity?.email!="NA")
                    {
                        def email = new EmailService().sendEmail(entity.email.trim(), "Purchase return Saved",
                                purchaseReturns?.invoiceNumber, purchaseReturns?.invoiceNumber, "PURCHASE_RETURN")
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
            responseJson.put("purchaseReturnDetail", purchaseReturns)
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
