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
import phitb_ui.ShipmentService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.system.AccountModeController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class DeliveryChallanController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        String userId = session.getAttribute("userId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
   /*     ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>*/
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        render(view: '/sales/deliveryChallan/deliveryChallan', model: [/*customers   : customers,*/ divisions: divisions, series: series,
                                                                       salesmanList: salesmanList, priorityList: priorityList,
                                                                       transporter : transporter])
    }

    def saveDeliveryChallan()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject dc = new JSONObject()
        JSONArray deliveryChallanProducts = new JSONArray()
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
            def recentDeliveryChallan = new SalesService().getRecentDeliveryChallan(financialYear, entityId, billStatus)
            if (recentDeliveryChallan != null && recentDeliveryChallan.size() != 0)
            {
                finId = Long.parseLong(recentDeliveryChallan.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentDeliveryChallan.get("serBillId").toString()) + 1
            }
            else
            {
                finId = 1
                serBillId = Long.parseLong(series.get("deliveryChallanId").toString())
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
        JSONArray deliveryChallan = new JSONArray(params.deliveryChallan)
        boolean tempStocksSavedCheck = true
//        for (JSONObject sale : saleData)
//        {
//            if (sale.has("15"))
//            {
//                String tempStockRowId = sale.get("15")
//                if (tempStockRowId && Long.parseLong(tempStockRowId) > 0)
//                {
//                    tempStocksSavedCheck = true
//                }
//                else
//                {
//                    tempStocksSavedCheck = false
//                }
//            }
//            else
//            {
//                tempStocksSavedCheck = false
//            }
//        }
//
//        //safety check
//        if (!tempStocksSavedCheck)
//        {
//            println("Safety Check Failed! attempted to generate sale invoice, but temp stock was not saved.")
//            response.status == 400
//            return
//        }

        for (JSONObject sale : deliveryChallan)
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

            JSONObject dcProduct = new JSONObject()
            dcProduct.put("finId", finId)
            dcProduct.put("billId", 0)
            dcProduct.put("billType", 0)
            dcProduct.put("serBillId", 0)
            dcProduct.put("seriesId", seriesId)
            dcProduct.put("productId", productId)
            dcProduct.put("batchNumber", batchNumber)
            dcProduct.put("expiryDate", expDate)
            dcProduct.put("sqty", saleQty)
            dcProduct.put("freeQty", freeQty)
            dcProduct.put("sqtyReturn", saleQty)
            dcProduct.put("fqtyReturn", freeQty)
            dcProduct.put("repQty", 0)
            dcProduct.put("pRate", 0) //TODO: to be changed
            dcProduct.put("sRate", saleRate)
            dcProduct.put("mrp", mrp)
            dcProduct.put("discount", discount)
            dcProduct.put("gstAmount", gst)
            dcProduct.put("sgstAmount", sgst)
            dcProduct.put("cgstAmount", cgst)
            dcProduct.put("igstAmount", igst)

            //GST percentage Calculation
/*            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(saleQty) * Double.parseDouble(saleRate)), 2)
            if(discount>0)
                priceBeforeTaxes = priceBeforeTaxes - (priceBeforeTaxes * (discount/100))

            double gstPercentage = 0.0
            double sgstPercentage = 0.0
            double cgstPercentage = 0.0
            double igstPercentage = 0.0

            if(gst >0)
                gstPercentage = (gst / priceBeforeTaxes) * 100
            if(sgst >0)
                sgstPercentage = (sgst / priceBeforeTaxes) * 100
            if(cgst >0)
                cgstPercentage = (cgst / priceBeforeTaxes) * 100
            if(igst >0)
                igstPercentage = (igst / priceBeforeTaxes) * 100

            saleProductDetail.put("gstPercentage", UtilsService.round(gstPercentage,2))
            saleProductDetail.put("sgstPercentage", UtilsService.round(sgstPercentage,2))
            saleProductDetail.put("cgstPercentage", UtilsService.round(cgstPercentage,2))
            saleProductDetail.put("igstPercentage", UtilsService.round(igstPercentage,2))*/

            dcProduct.put("gstPercentage", sale.get("16").toString())
            dcProduct.put("sgstPercentage", sale.get("17").toString())
            dcProduct.put("cgstPercentage", sale.get("18").toString())
            dcProduct.put("igstPercentage", sale.get("19").toString())
            dcProduct.put("originalSqty", sale.get("20").toString())
            dcProduct.put("originalFqty", sale.get("21").toString())

            dcProduct.put("gstId", 0) //TODO: to be changed
            dcProduct.put("amount", value)
            dcProduct.put("reason", "") //TODO: to be changed
            dcProduct.put("fridgeId", 0) //TODO: to be changed
            dcProduct.put("kitName", 0) //TODO: to be changed
            dcProduct.put("saleFinId", "") //TODO: to be changed
            dcProduct.put("redundantBatch", 0) //TODO: to be changed
            dcProduct.put("status", 0)
            dcProduct.put("syncStatus", 0)
            dcProduct.put("financialYear", financialYear)
            dcProduct.put("entityId", entityId)
            dcProduct.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            deliveryChallanProducts.add(dcProduct)

            //save to sale transaction log
            //save to sale transportation details

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        dc.put("serBillId", serBillId)
        dc.put("customerId", customerId)
        dc.put("customerNumber", 0) //TODO: to be changed
        dc.put("finId", finId)
        dc.put("seriesId", seriesId)
        dc.put("priorityId", priorityId)
        dc.put("financialYear", financialYear)
        dc.put("dueDate", duedate)
        dc.put("paymentStatus", 0)
        dc.put("userId", session.getAttribute("userId"))
        dc.put("entryDate", entryDate)
        dc.put("orderDate", orderDate)
        dc.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        dc.put("salesmanId", "0") //TODO: to be changed
        dc.put("salesmanComm", "0") //TODO: to be changed
        dc.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        dc.put("deliveryManId", "0") //TODO: to be changed
        dc.put("accountModeId", "0") //TODO: to be changed
        dc.put("totalSqty", totalSqty)
        dc.put("totalFqty", totalFqty)
        dc.put("totalGst", totalGst)
        dc.put("totalSgst", totalSgst)
        dc.put("totalCgst", totalCgst)
        dc.put("totalIgst", totalIgst)
        dc.put("totalQty", totalSqty + totalFqty)
        dc.put("totalItems", totalSqty + totalFqty)
        dc.put("totalDiscount", totalDiscount)
        dc.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        dc.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        dc.put("totalAmount", totalAmount)
        dc.put("balance", totalAmount)
        dc.put("entityId", entityId)
        dc.put("entityTypeId", session.getAttribute("entityTypeId"))
        dc.put("createdUser", session.getAttribute("userId"))
        dc.put("modifiedUser", session.getAttribute("userId"))
        dc.put("message", message) //TODO: to be changed
        dc.put("gstStatus", "0") //TODO: to be changed
        dc.put("billStatus", billStatus)
        dc.put("lockStatus", 0) //TODO: to be changed
        dc.put("syncStatus", "0") //TODO: to be changed
        dc.put("creditadjAmount", 0) //TODO: to be changed
        dc.put("creditIds", "0") //TODO: to be changed
        dc.put("referralDoctor", "0") //TODO: to be changed
        dc.put("taxable", "1") //TODO: to be changed
        dc.put("cashDiscount", 0) //TODO: to be changed
        dc.put("exempted", 0) //TODO: to be changed
        dc.put("seriesCode", seriesCode)
        dc.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("deliveryChallan", dc)
        jsonObject.put("deliveryChallanProduct", deliveryChallanProducts)
        Response response = new SalesService().saveDeliveryChallan(jsonObject)
        if (response.status == 200)
        {
            UUID uuid
            JSONObject deliveryChallanResp = new JSONObject(response.readEntity(String.class))
            //update stockbook
            for (JSONObject d : deliveryChallan)
            {
                uuid = UUID.randomUUID()
                long saleQty = Long.parseLong(d.get("4").toString())
                long saleFreeQty = Long.parseLong(d.get("5").toString())
//                String tempStockRowId = sale.get("15")
//                def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
                def stockBook = new InventoryService().getStocksOfProductAndBatch(d.get("1").toString(), d.get("2").toString(),
                        entityId)
                long remainingQty = Long.parseLong(stockBook.get("remainingQty").toString())
                long remainingFreeQty = Long.parseLong(stockBook.get("remainingFreeQty").toString())

                if (saleQty <= remainingQty)
                {
                    remainingQty = remainingQty - saleQty

                }
                else if (saleQty > remainingQty && saleQty <= (remainingQty + remainingFreeQty))
                {
                    remainingFreeQty = remainingFreeQty - (saleQty - remainingQty)
                    remainingQty = 0
                }
                if (saleFreeQty <= remainingFreeQty)
                {
                    remainingFreeQty = remainingFreeQty - saleFreeQty
                }
                else if (saleFreeQty > remainingFreeQty && saleFreeQty <= (remainingQty + remainingFreeQty))
                {
                    remainingQty = remainingQty - (saleFreeQty - remainingFreeQty)
                    remainingFreeQty = 0
                }
                stockBook.put("remainingQty", remainingQty)
                stockBook.put("remainingFreeQty", remainingFreeQty)
                stockBook.put("remainingReplQty", stockBook.get("remainingReplQty"))
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
                stockBook.put("uuid", uuid)
                def apiRes = new InventoryService().updateStockBook(stockBook)
                if (apiRes.status == 200)
                {
////                    //clear tempstockbook
//                    def deleteTemp = new InventoryService().deleteTempStock(tempStockRowId)
//                    println(deleteTemp)
                    println("stocks modified!!")
                }
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("deliveryChallan", deliveryChallanResp)
            respond responseJson, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }


    def cancelDeliveryChallan()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new SalesService().cancelDeliveryChallan(id, entityId, financialYear)
        if (jsonObject)
        {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails)
            {
                for (JSONObject productDetail : productDetails)
                {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, productDetail?.entityId?.toString())
                    double remainingQty = stockBook.get("remainingQty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty")

                    //checking to where the stocks to be returned
                    double originalSqty = productDetail.get("originalSqty")
                    double originalFqty = productDetail.get("originalFqty")
                    double sqty = productDetail.get("sqty")
                    double freeQty = productDetail.get("freeQty")

                    if ((originalSqty + originalFqty) == (sqty + freeQty) && originalSqty == sqty && originalFqty == freeQty)
                    {
                        remainingQty += sqty
                        remainingFreeQty += freeQty
                    }
                    else
                    {
                        if (originalSqty >= sqty && originalFqty >= freeQty)
                        {
                            remainingQty += sqty
                            remainingFreeQty += freeQty
                        }
                        else
                        {
                            if (sqty > originalSqty)
                            {
                                remainingQty = sqty - (sqty - originalSqty)
                                remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
                            }
                            else if (freeQty > originalFqty)
                            {
                                remainingQty = remainingQty + sqty + (freeQty - originalFqty)
                                remainingFreeQty = freeQty - (freeQty - originalFqty)
                            }
                        }
                    }

                    double remainingReplQty = stockBook.get("remainingReplQty") + productDetail.get("repQty")
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", remainingReplQty.toLong())
                    new InventoryService().updateStockBook(stockBook)
                }
            }
            respond jsonObject, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }

    def approveDeliveryChallan()
    {
        def dc = new SalesService().getDeliveryChallanById(params.deliveryChallan)
        if (dc != null)
        {
            def dcProduct = new SalesService().getDCProductDetailsByDeliveryChallan(dc.id.toString())
            println(session.getAttribute('entityId').toString())
            UUID uuid
            for (JSONObject dcObject : dcProduct)
            {
                def stockBook = new InventoryService().getStocksOfProductAndBatch(dcObject.productId.toString(),
                        dcObject.batchNumber, session.getAttribute('entityId').toString())
                if (stockBook != null)
                {
                    double remainingQty = stockBook.get("remainingQty") + Double.parseDouble(dcObject.sqty.toString())
                    double remainingFreeQty = stockBook.get("remainingFreeQty") + Double.parseDouble(dcObject.freeQty.toString())
                    double remainingReplQty = stockBook.get("remainingReplQty") + Double.parseDouble(dcObject.repQty.toString())
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("purchaseRate", dcObject?.sRate)
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", remainingReplQty.toLong())
                    new InventoryService().updateStockBook(stockBook)
                    new SalesService().approveDeliveryChallan(dc.id.toString(), dc.entityId.toString(), dc.financialYear.toString())
                }
                else
                {
                    JSONArray stockArray = new JSONArray()
                    JSONObject stock = new JSONObject()
                    def stockBook1 = new InventoryService().getStocksOfProductAndBatch(dcObject.productId.toString(),
                            dcObject.batchNumber.toString(), dcObject.entityId.toString())
                    stockBook1.put("remainingQty", Double.valueOf(Double.parseDouble(dcObject.sqty.toString())).longValue())
                    stockBook1.put("remainingFreeQty", Double.valueOf(Double.parseDouble(dcObject.freeQty.toString())).longValue())
                    stockBook1.put("remainingReplQty", Double.valueOf(Double.parseDouble(dcObject.repQty.toString())).longValue())
                    stockBook1.put("purchaseRate", dcObject?.sRate)
                    stockBook1.put("entityId", session.getAttribute('entityId').toString())
                    stockBook1.put("uuid", UUID.randomUUID())
                    stockBook1.put("entityTypeId", session.getAttribute('entityTypeId').toString())
                    def apires = new InventoryService().stockBookSave(stockBook1)
                    new SalesService().approveDeliveryChallan(dc.id.toString(), dc.entityId.toString(), dc.financialYear.toString())
                }
            }
            respond dc, formats: ['json'], status: 200
        }
        else
        {
            response.status = 400
        }
    }

    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId",session.getAttribute('entityId'))
            if (!session.getAttribute("role").toString().equalsIgnoreCase(Constants.ENTITY_ADMIN))
                jsonObject.put("userId", session.getAttribute("userId"))

            def apiResponse = new SalesService().showDeliveryChallan(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject)
                {
                    JSONArray jsonArray = responseObject.data
//                    JSONArray jsonArray2 = new JSONArray()
//                    JSONArray jsonArray3 = new JSONArray()
//                    JSONArray entityArray = new JSONArray()
//                    JSONArray cityArray = new JSONArray()
//                    for (JSONObject json : jsonArray)
//                    {
//                        json.put("customer", new EntityService().getEntityById(json.get("customerId").toString()))
//                        jsonArray2.put(json)
//                    }
//                    for (JSONObject json1 : jsonArray2)
//                    {
//                        entityArray.put(json1.get("customer"))
//                    }
//                    entityArray.each {
//                        def cityResp = new SystemService().getCityById(it.cityId.toString())
//                        it.put("cityId", cityResp)
//                    }
//                    responseObject.put("data", jsonArray2)
//                    responseObject.put("city", entityArray)
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("customerId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
                        customer?.put("city", city)
                        json.put("customer", customer)
                    }
                    responseObject.put("data", jsonArray)
                }
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def devliveryChallanList(){

        render(view: '/sales/deliveryChallan/delivery-challan-list')
    }


    def printDeliveryChallan()
    {

        String deliveryChallanId = params.id
        JSONObject deliveryChallanDetail = new SalesService().getDeliveryChallanById(deliveryChallanId)
        if (deliveryChallanDetail != null)
        {
            JSONArray deliveryChallanProductDetails = new SalesService().getDCProductDetailsByDeliveryChallan(deliveryChallanId)
            JSONObject series = new EntityService().getSeriesById(deliveryChallanDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(deliveryChallanDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(deliveryChallanDetail.get("entityId").toString())
            JSONObject parentEntity = new EntityService().getEntityById(customer.get("parentEntity").toString())
            JSONObject parentCity = new SystemService().getCityById(parentEntity.get('cityId').toString())
            parentEntity.put("city", parentCity)
            customer.put("parentEntity",parentEntity)
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(deliveryChallanDetail.get("entityId").toString())
            deliveryChallanProductDetails.each {
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
            def totalcgst = UtilsService.round(deliveryChallanProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(deliveryChallanProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(deliveryChallanProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(deliveryChallanProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : deliveryChallanProductDetails)
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

//            JSONObject irnDetails = null
//            if(gtnDetail.has("irnDetails") && gtnDetail.get("irnDetails") != null)
//                irnDetails = new JSONObject(gtnDetail.get("irnDetails").toString())

            render(view: "/sales/deliveryChallan/delivery-challan-print", model: [deliveryChallanDetail    : deliveryChallanDetail,
                                                                       deliveryChallanProductDetails: deliveryChallanProductDetails,
                                                                       series           : series, entity: entity, customer: customer, city: city,
                                                                       total            : total, custcity: custcity,
                                                                       termsConditions  : termsConditions,
                                                                       totalcgst        : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                       totaldiscount    : totaldiscount,
                                                                       gstGroup         : gstGroup,
                                                                       sgstGroup        : sgstGroup,
                                                                       cgstGroup        : cgstGroup,
                                                                       igstGroup        : igstGroup,
                                                                       totalBeforeTaxes : totalBeforeTaxes,

            ])
        }
        else
        {

            render("No Bill Found")
        }
    }
}
