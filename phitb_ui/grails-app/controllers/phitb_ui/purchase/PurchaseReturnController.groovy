package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONException
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
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.product.DivisionController

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
//            JSONArray productArray = new JSONArray()
            products.each {
                def saleBillShow = new PurchaseService().getRequestWithId(it.billId.toString(), new Links().PURCHASE_BILL_SHOW)
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray) {
                    if (batch.batchNumber == it.batchNumber) {
                        it.put("batch", batch)
                    }
                }
                it.put("prevsqty", 0)
                it.put("prevfqty", 0)
                it.put("bill", JSON.parse(saleBillShow.readEntity(String.class)) as JSONObject)
                def purchaseReturns = new PurchaseService().getReturnDetailsByBatchPurbillProductId(it.productId.toString()
                        , it.batchNumber.toString(), it.billId.toString())

                JSONArray purchaseReturnArray = JSON.parse(purchaseReturns.readEntity(String.class)) as JSONArray
                if (purchaseReturnArray.size() > 0) {
                    double sqty = 0;
                    double fqty = 0;
                    for (JSONObject purchaseReturn : purchaseReturnArray) {

                        if (purchaseReturn.purBillId == it.billId && (purchaseReturn.returnStatus != "CANCELLED")) {
                            if (purchaseReturn.sqty != 0) {
                                sqty += Double.parseDouble(purchaseReturn.sqty.toString())
                                it.put("prevsqty", sqty)
                            }
                            if (purchaseReturn.freeQty != 0) {
                                fqty += Double.parseDouble(purchaseReturn.freeQty.toString())
                                it.put("prevfqty", fqty)
                            }
                        }
                    }
                }
            }


//            JSONArray array = new JSONArray(products)
//            for(int i = 0; i < array.length(); ++i)
//            {
//                JSONObject obj = array.getJSONObject(i);
//                double sqty1 = obj.getDouble("sqty");
//                double freeQty1 = obj.getDouble("freeQty");
//                if(sqty1 > 0 || freeQty1 > 0)
//                {
//                    productArray.add(products)
//                }
//            }
//            println(products)
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
        String customer = params.supplier
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String returnStatus = params.returnStatus
        String seriesCode = params.seriesCode
        String message = params.message
        String refNo = params.lrno
        String refDate = params.lrDate
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!returnStatus.equalsIgnoreCase("DRAFT")) {
            def recentReturn = new PurchaseService().getRecentPurchaseReturn(financialYear, entityId)
            if (recentReturn != null && recentReturn.size() != 0) {
                finId = Long.parseLong(recentReturn.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentReturn.get("serBillId").toString()) + 1
            } else {
                finId = 1
                serBillId = Long.parseLong(series.get("purchaseReturnId").toString())
            }
        }
        long totalPurQty = 0
        long totalFqty = 0
        double totalAmount = 0.00
        double totalGst = 0.00
        double totalCgst = 0.00
        double totalSgst = 0.00
        double totalIgst = 0.00
        double totalDiscount = 0.00
        JSONArray purchaseReturnData = new JSONArray(params.purchaseReturnData)
        for (JSONObject pr : purchaseReturnData) {
            String purBillId;
            String reason = pr.get("1")
            String productId = pr.get("2")
            String batchNumber = pr.get("3")
            String expDate = pr.get("4")
            String purQty = pr.get("5")
            String freeQty = pr.get("6")
            String purchaseRate = pr.get("7")
            String mrp = pr.get("8")
            String taxId = pr.get("19")
            if (pr.has("18")) {
                purBillId = pr.get("18")
            } else {
                purBillId = ""
            }
            String invoiceNumber = pr.get("17")
            double discount = UtilsService.round(Double.parseDouble(pr.get("9").toString()), 2)
            String packDesc = pr.get("10")
            double gst = UtilsService.round(Double.parseDouble(pr.get("12").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(pr.get("13").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(pr.get("14").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(pr.get("15").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(pr.get("16").toString()), 2)
            totalPurQty += Long.parseLong(purQty)
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
            purchaseReturnDetail.put("returnStatus", returnStatus)
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
            purchaseReturnDetail.put("sqty", purQty)
            purchaseReturnDetail.put("freeQty", freeQty)
            purchaseReturnDetail.put("repQty", 0)
            purchaseReturnDetail.put("invoiceNumber", invoiceNumber)
            purchaseReturnDetail.put("reason", reason)
            purchaseReturnDetail.put("pRate", purchaseRate)
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
            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(purQty) * Double.parseDouble(purchaseRate)), 2)
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
            stock.put("saleQty", purQty)
            stock.put("freeQty", freeQty)
            stock.put("productId", productId)
            stock.put("taxId", taxId)
            stock.put("packDesc", packDesc)
            stock.put("saleRate", purchaseRate)
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
        purchaseReturn.put("supplierId", customer)
        purchaseReturn.put("refNo", refNo)
        purchaseReturn.put("refDate", refDate)
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
        purchaseReturn.put("totalSqty", totalPurQty)
        purchaseReturn.put("totalFqty", totalFqty)
        purchaseReturn.put("totalGst", totalGst)
        purchaseReturn.put("totalSgst", totalSgst)
        purchaseReturn.put("totalCgst", totalCgst)
        purchaseReturn.put("totalIgst", totalIgst)
        purchaseReturn.put("totalQuantity", totalPurQty + totalFqty)
        purchaseReturn.put("totalItems", totalPurQty + totalFqty)
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
        purchaseReturn.put("returnStatus", returnStatus)
        purchaseReturn.put("quantity", 0)
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
                        def email = new EmailService().sendEmail(entity.email.trim(), "Purchase Return Saved",
                                purchaseReturns?.invoiceNumber, purchaseReturns?.invoiceNumber, "PURCHASE_RETRUN")
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


    def purchaseReturnList(){
        render(view: '/purchase/purchaseReturn/purchase-return-list')
    }

    def purchaseReturnDatatables() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new PurchaseService().purchaseReturnDatatable(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject) {
                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("supplierId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
                        customer?.put("city", city)
                        json.put("customer", customer)
                    }
                    responseObject.put("data", jsonArray)
                }
                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }



    def printPurchaseReturn()
    {
        String purchaseReturnId = params.id
        JSONObject purhcaseReturn = new PurchaseService().getPurchaseReturnById(purchaseReturnId)
        def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
        if (purhcaseReturn != null)
        {
            JSONArray purchaseReturnProductDetails = new PurchaseService().getPurchaseReturnProductDetailsByBill(purchaseReturnId)
           /* JSONObject transportDetails = new PurchaseService().getPurchaseTransportationByBill(purchaseReturnId,Constants.PURCHASE_RETURN)
            if (transportDetails != null)
            {
                JSONObject transporter = new ShipmentService().getTransporterbyId(transportDetails?.transporterId?.toString());
                if (transporter != null)
                {
                    transportDetails.put("transporter", transporter)
                }
            }*/
            JSONObject series = new EntityService().getSeriesById(purhcaseReturn.get("series").toString())
            JSONObject customer = new EntityService().getEntityById(purhcaseReturn.get("supplierId").toString())
            println("Entity ID is: " + session.getAttribute("entityId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            if (entity == null)
            {
                println("Entity is null")
            }
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            termsConditions.each {
                JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
                if (formMaster != null)
                {
                    if (it.formId == formMaster.id)
                    {
                        it.put("form", formMaster)
                    }
                }
            }
//            println(termsConditions)
            JSONObject groupDetails = new JSONObject()
            JSONArray productDetail = new JSONArray()
            purchaseReturnProductDetails.each {
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
                def stocks = new InventoryService().getStocksOfProductAndBatch(it.productId.id.toString(), it.batchNumber.toString(), it.entityId.toString())
                it.put("stocks", stocks)
                if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
                {
                    if (groupDetails.containsKey(it?.productId?.division?.id))
                    {
                        productDetail = groupDetails.get(it?.productId?.division?.id) as JSONArray
                        productDetail.add(it)
                        Collections.sort(productDetail, new Comparator<JSONObject>() {
                            @Override
                            int compare(JSONObject o1, JSONObject o2)
                            {
                                String val1 = new String()
                                String val2 = new String()
                                try
                                {

                                    if (settings?.IPS == Constants.ALPHABETIC)
                                    {
                                        val1 = (String) o1?.productId?.productName
                                        val2 = (String) o2.productId?.productName
                                    }
                                    else if (settings?.IPS == Constants.TAX_WISE)
                                    {
                                        val1 = (String) o1?.gstPercentage
                                        val2 = (String) o2?.gstPercentage
                                    }
                                }
                                catch (JSONException e)
                                {
                                    //do something
                                    println("JSON Exception")
                                }
                                return val1.compareTo(val2);
                            }
                        });
                        JSONArray sortedJsonArray = new JSONArray();
                        for (int i = 0; i < productDetail.length(); i++)
                        {
                            sortedJsonArray.put(productDetail.get(i));
                        }
                        println(sortedJsonArray)
                        groupDetails.put(it?.productId?.division?.id, sortedJsonArray)
                    }
                    else
                    {
                        productDetail = new JSONArray()
                        productDetail.add(it)
                        println(productDetail)
                        groupDetails.put(it?.productId?.division?.id, productDetail)
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                {
                    if (groupDetails.containsKey(it?.stocks?.taxId))
                    {
                        productDetail = groupDetails.get(it?.stocks?.taxId) as JSONArray
                        productDetail.add(it)
                        Collections.sort(productDetail, new Comparator<JSONObject>() {
                            @Override
                            int compare(JSONObject o1, JSONObject o2)
                            {
                                String val1 = new String()
                                String val2 = new String()
                                try
                                {

                                    if (settings?.IPS == Constants.ALPHABETIC)
                                    {
                                        val1 = (String) o1?.productId?.productName
                                        val2 = (String) o2.productId?.productName
                                    }
                                    else if (settings?.IPS == Constants.TAX_WISE)
                                    {
                                        val1 = (String) o1?.gstPercentage
                                        val2 = (String) o2?.gstPercentage
                                    }
                                }
                                catch (JSONException e)
                                {
                                    //do something
                                    println("JSON Exception")
                                }
                                return val1.compareTo(val2);
                            }
                        });
                        JSONArray sortedJsonArray = new JSONArray();
                        for (int i = 0; i < productDetail.length(); i++)
                        {
                            sortedJsonArray.put(productDetail.get(i));
                        }
                        println(sortedJsonArray)
                        groupDetails.put(it?.stocks?.taxId, sortedJsonArray)
                    }
                    else
                    {
                        productDetail = new JSONArray()
                        productDetail.add(it)
                        println(productDetail)
                        groupDetails.put(it?.stocks?.taxId, productDetail)
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
                {
                    if (groupDetails.containsKey(it?.productId?.group?.id))
                    {
                        productDetail = groupDetails.get(it?.productId?.group?.id) as JSONArray
                        productDetail.add(it)
                        Collections.sort(productDetail, new Comparator<JSONObject>() {
                            @Override
                            int compare(JSONObject o1, JSONObject o2)
                            {
                                String val1 = new String()
                                String val2 = new String()
                                try
                                {

                                    if (settings?.IPS == Constants.ALPHABETIC)
                                    {
                                        val1 = (String) o1?.productId?.productName
                                        val2 = (String) o2.productId?.productName
                                    }
                                    else if (settings?.IPS == Constants.TAX_WISE)
                                    {
                                        val1 = (String) o1?.gstPercentage
                                        val2 = (String) o2?.gstPercentage
                                    }
                                }
                                catch (JSONException e)
                                {
                                    //do something
                                    println("JSON Exception")
                                }
                                return val1.compareTo(val2);
                            }
                        });
                        JSONArray sortedJsonArray = new JSONArray();
                        for (int i = 0; i < productDetail.length(); i++)
                        {
                            sortedJsonArray.put(productDetail.get(i));
                        }
                        println(sortedJsonArray)
                        groupDetails.put(it?.productId?.group?.id, sortedJsonArray)
                    }
                    else
                    {
                        productDetail = new JSONArray()
                        productDetail.add(it)
                        println(productDetail)
                        groupDetails.put(it?.productId?.group?.id, productDetail)
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
                {
                    if (groupDetails.containsKey(it?.productId?.schedule?.id))
                    {
                        productDetail = groupDetails.get(it?.productId?.schedule?.id) as JSONArray
                        productDetail.add(it)
                        Collections.sort(productDetail, new Comparator<JSONObject>() {
                            @Override
                            int compare(JSONObject o1, JSONObject o2)
                            {
                                String val1 = new String()
                                String val2 = new String()
                                try
                                {

                                    if (settings?.IPS == Constants.ALPHABETIC)
                                    {
                                        val1 = (String) o1?.productId?.productName
                                        val2 = (String) o2.productId?.productName
                                    }
                                    else if (settings?.IPS == Constants.TAX_WISE)
                                    {
                                        val1 = (String) o1?.gstPercentage
                                        val2 = (String) o2?.gstPercentage
                                    }
                                }
                                catch (JSONException e)
                                {
                                    //do something
                                    println("JSON Exception")
                                }
                                return val1.compareTo(val2);
                            }
                        });
                        JSONArray sortedJsonArray = new JSONArray();
                        for (int i = 0; i < productDetail.length(); i++)
                        {
                            sortedJsonArray.put(productDetail.get(i));
                        }
                        println(sortedJsonArray)
                        groupDetails.put(it?.productId?.schedule?.id, sortedJsonArray)
                    }
                    else
                    {
                        productDetail = new JSONArray()
                        productDetail.add(it)
                        groupDetails.put(it?.productId?.schedule?.id, productDetail)
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
                {
                    if (groupDetails.containsKey(it?.productId?.category?.id))
                    {
                        productDetail = groupDetails.get(it?.productId?.category?.id) as JSONArray
                        productDetail.add(it)
                        Collections.sort(productDetail, new Comparator<JSONObject>() {
                            @Override
                            int compare(JSONObject o1, JSONObject o2)
                            {
                                String val1 = new String()
                                String val2 = new String()
                                try
                                {

                                    if (settings?.IPS == Constants.ALPHABETIC)
                                    {
                                        val1 = (String) o1?.productId?.productName
                                        val2 = (String) o2.productId?.productName
                                    }
                                    else if (settings?.IPS == Constants.TAX_WISE)
                                    {
                                        val1 = (String) o1?.gstPercentage
                                        val2 = (String) o2?.gstPercentage
                                    }
                                }
                                catch (JSONException e)
                                {
                                    //do something
                                    println("JSON Exception")
                                }
                                return val1.compareTo(val2);
                            }
                        });
                        JSONArray sortedJsonArray = new JSONArray();
                        for (int i = 0; i < productDetail.length(); i++)
                        {
                            sortedJsonArray.put(productDetail.get(i));
                        }
                        println(sortedJsonArray)
                        groupDetails.put(it?.productId?.category?.id, sortedJsonArray)
                    }
                    else
                    {
                        productDetail = new JSONArray()
                        productDetail.add(it)
                        groupDetails.put(it?.productId?.category?.id, productDetail)
                    }
                }
            }
            if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
            {
                for (Object divison : groupDetails.keySet())
                {
                    def divisionDetail = new DivisionController().getDivisionById(divison as String)
                    JSONArray prodDetails = groupDetails.get(divison) as JSONArray
                    HashMap<String, Double> divGstGroup = new HashMap<>()
                    HashMap<String, Double> divSgstGroup = new HashMap<>()
                    HashMap<String, Double> divCgstGroup = new HashMap<>()
                    HashMap<String, Double> divIgstGroup = new HashMap<>()
                    double amountBeforeTaxes = 0;
                    double amountAfterTaxes = 0;
                    for (Object prodDetail : prodDetails)
                    {
                        amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                        amountAfterTaxes += prodDetail.amount
                        if (prodDetail.igstPercentage > 0)
                        {
                            def igstPercentage = divIgstGroup.get(it.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            divisionDetail.put("sortItem", divisionDetail.get("divisionName"))
//                            divisionDetail.remove("divisionName");
                            divisionDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            divisionDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            divisionDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", divisionDetail)
                        }
                    }
                }
            }
            else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
            {
                for (Object tax : groupDetails.keySet())
                {
                    def taxDetail = new TaxController().show(tax as String)
                    JSONArray prodDetails = groupDetails.get(tax) as JSONArray
                    HashMap<String, Double> divGstGroup = new HashMap<>()
                    HashMap<String, Double> divSgstGroup = new HashMap<>()
                    HashMap<String, Double> divCgstGroup = new HashMap<>()
                    HashMap<String, Double> divIgstGroup = new HashMap<>()
                    double amountBeforeTaxes = 0;
                    double amountAfterTaxes = 0;
                    for (Object prodDetail : prodDetails)
                    {
                        amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                        amountAfterTaxes += prodDetail.amount
                        if (prodDetail.igstPercentage > 0)
                        {
                            def igstPercentage = divIgstGroup.get(it.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            taxDetail.put("sortItem", taxDetail.get("taxName") + " (" + taxDetail.get("taxValue") + "%)")
//                            taxDetail.remove("divisionName");
                            taxDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            taxDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            taxDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", taxDetail)
                        }
                    }
                }
            }
            else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
            {
                for (Object productGrp : groupDetails.keySet())
                {
                    def productGrpDetail = new ProductService().getProductGroupById(productGrp.toString())
                    JSONArray prodDetails = groupDetails.get(productGrp) as JSONArray
                    HashMap<String, Double> divGstGroup = new HashMap<>()
                    HashMap<String, Double> divSgstGroup = new HashMap<>()
                    HashMap<String, Double> divCgstGroup = new HashMap<>()
                    HashMap<String, Double> divIgstGroup = new HashMap<>()
                    double amountBeforeTaxes = 0;
                    double amountAfterTaxes = 0;
                    for (Object prodDetail : prodDetails)
                    {
                        amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                        amountAfterTaxes += prodDetail.amount
                        if (prodDetail.igstPercentage > 0)
                        {
                            def igstPercentage = divIgstGroup.get(it.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            productGrpDetail.put("sortItem", productGrpDetail.get("groupName"))
//                            taxDetail.remove("divisionName");
                            productGrpDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            productGrpDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            productGrpDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", productGrpDetail)
                        }
                    }
                }
            }
            else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
            {
                for (Object schedule : groupDetails.keySet())
                {
                    def scheduleDetail = new ProductService().getProductSchedulebyId(schedule.toString())
                    JSONArray prodDetails = groupDetails.get(schedule) as JSONArray

                    HashMap<String, Double> divGstGroup = new HashMap<>()
                    HashMap<String, Double> divSgstGroup = new HashMap<>()
                    HashMap<String, Double> divCgstGroup = new HashMap<>()
                    HashMap<String, Double> divIgstGroup = new HashMap<>()
                    double amountBeforeTaxes = 0;
                    double amountAfterTaxes = 0;
                    for (Object prodDetail : prodDetails)
                    {
                        amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                        amountAfterTaxes += prodDetail.amount
                        if (prodDetail.igstPercentage > 0)
                        {
                            def igstPercentage = divIgstGroup.get(it.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }


                        }
                        scheduleDetail.put("sortItem", scheduleDetail.get("scheduleCode"))
                        scheduleDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                        scheduleDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                        scheduleDetail.put("amountAfterTaxes", amountAfterTaxes)
                        prodDetail.put("sortDetail", scheduleDetail)
                    }
                }
            }
            else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
            {
                for (Object category : groupDetails.keySet())
                {
                    def categoryDetail = new ProductService().getProductCategoryById(category.toString())
                    JSONArray prodDetails = groupDetails.get(category) as JSONArray
                    HashMap<String, Double> divGstGroup = new HashMap<>()
                    HashMap<String, Double> divSgstGroup = new HashMap<>()
                    HashMap<String, Double> divCgstGroup = new HashMap<>()
                    HashMap<String, Double> divIgstGroup = new HashMap<>()
                    double amountBeforeTaxes = 0;
                    double amountAfterTaxes = 0;
                    for (Object prodDetail : prodDetails)
                    {
                        amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                        amountAfterTaxes += prodDetail.amount
                        if (prodDetail.igstPercentage > 0)
                        {
                            def igstPercentage = divIgstGroup.get(it.igstPercentage.toString())
                            if (igstPercentage == null)
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                            }
                            else
                            {
                                divIgstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                        }
                        else
                        {
                            def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                            if (gstPercentage == null)
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }

                            def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                            if (sgstPercentage == null)
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                            }
                            def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                            if (cgstPercentage == null)
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }
                            else
                            {
                                divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                            }


                        }
                        categoryDetail.put("sortItem", categoryDetail.get("categoryName"))
                        categoryDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                        categoryDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                        categoryDetail.put("amountAfterTaxes", amountAfterTaxes)
                        prodDetail.put("sortDetail", categoryDetail)
                    }
                }
            }



            def totalcgst = UtilsService.round(purchaseReturnProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(purchaseReturnProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(purchaseReturnProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(purchaseReturnProductDetails.discount.sum(), 2)
            def totalDiscAmt = 0
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : purchaseReturnProductDetails)
            {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalDiscAmt += amountBeforeTaxes/100*it.discount
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




            render(view: "/purchase/purchaseReturn/purchase-return-invoice", model: [saleInvDetail      : purhcaseReturn,
                                                                                     sampleProductDetail: purchaseReturnProductDetails,
                                                                                     series             : series, entity: entity, customer: customer, city: city,
                                                                                     total              : total, custcity: custcity,
                                                                                     totalDiscAmt       :totalDiscAmt,
                                                                                     termsConditions    : termsConditions,
                                                                                     totalcgst          : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                                     totaldiscount      : totaldiscount,
                                                                                     gstGroup           : gstGroup,
                                                                                     sgstGroup          : sgstGroup,
                                                                                     cgstGroup          : cgstGroup,
                                                                                     igstGroup          : igstGroup,
                                                                                     totalBeforeTaxes  : totalBeforeTaxes,
                                                                                     /* irnDetails        : irnDetails,*/
                                                                                     /* transportDetails  : transportDetails,*/
                                                                                     groupDetails      : groupDetails,
                                                                                     settings          : settings
            ])

        }
        else
        {

            render("No Bill Found")
        }
    }
}
