package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SampleConversionController
{

    def sampleInvoicing()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        String userId = session.getAttribute("userId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        render(view: '/sales/sampleConversion/sampleInvoicing', model: [customers   : customers, divisions: divisions, series: series,
                                                                        salesmanList: salesmanList, priorityList: priorityList])
    }

    def sampleConversion()
    {
        try
        {
            String entityId = session.getAttribute("entityId")?.toString()
            JSONArray productList = new ProductService().getProductsByEntityId(entityId)
            render(view: '/sales/sampleConversion/sample-conversion', model: [productList: productList])
        }
        catch (Exception ex)
        {
            println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
        }
    }


    def saveSampleConversion()
    {
        try
        {
            println(params)
            String entityId = session.getAttribute("entityId")?.toString()
//            Saleable Stock
            def saleableStock = new InventoryService().getStocksOfProductAndBatch(params.saleableProduct, params.saleableBatch, entityId)
            if(saleableStock)
            {
                long saleableQty = Long.parseLong(saleableStock.remainingQty.toString()) - Long.parseLong(params.sampleQty.toString())
                saleableStock.put("remainingQty", saleableQty)
                saleableStock.put("remainingFreeQty", saleableStock.get("remainingFreeQty"))
                saleableStock.put("remainingReplQty", saleableStock.get("remainingReplQty"))
                saleableStock.put("uuid", UUID.randomUUID())
                def saleableStockUpdate = new InventoryService().updateStockBook(saleableStock)
                if(saleableStockUpdate?.status!= 200)
                {
                    response.status = 400
                    return
                }
            }
            else {

                response.status = 400
                return
            }

//            Sample Stock
            def sampleStock = new InventoryService().getStocksOfProductAndBatch(params.sampleProduct, params.sampleBatch, entityId)
            if(sampleStock)
            {
                long sampleQty = Long.parseLong(sampleStock.remainingQty.toString()) + Long.parseLong(params.sampleQty.toString())
                sampleStock.put("remainingQty", sampleQty)
                sampleStock.put("remainingFreeQty", sampleStock.get("remainingFreeQty"))
                sampleStock.put("remainingReplQty", sampleStock.get("remainingReplQty"))
                def sampleStockUpdate = new InventoryService().updateStockBook(sampleStock)
                if(sampleStockUpdate?.status!= 200)
                {
                    response.status = 400
                    return
                }
            }
            else {

                response.status = 400
                return
            }
            JSONObject sampleConverisonLogs = new JSONObject()
            sampleConverisonLogs.put("saleableProductId",params.saleableProduct)
            sampleConverisonLogs.put("saleableBatch",params.saleableBatch)
            sampleConverisonLogs.put("saleableQty",params.saleableQty)
            sampleConverisonLogs.put("sampleProductId",params.sampleProduct)
            sampleConverisonLogs.put("sampleBatch",params.sampleBatch)
            sampleConverisonLogs.put("sampleQty",params.sampleQty)
            sampleConverisonLogs.put("entityId",session.getAttribute('entityId').toString())
            sampleConverisonLogs.put("entityTypeId",session.getAttribute('entityTypeId').toString())
            sampleConverisonLogs.put("userId",session.getAttribute('userId').toString())
            def samplelogs = new SalesService().saveSampleConversionLogs(sampleConverisonLogs)
            if(samplelogs?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(samplelogs.readEntity(String.class))
                println(jsonObject)
            }
            respond sampleConverisonLogs, formats: ['json'],status: 200
        }
        catch (Exception ex)
        {
            println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
        }
    }



    def saveSampleInvoicing()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject sampleInvoice = new JSONObject()
        JSONArray gtnProducts = new JSONArray()
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
            def recentSampleInvoice = new SalesService().getRecentSampleInvoice(financialYear, entityId, billStatus)
            if (recentSampleInvoice != null && recentSampleInvoice.size() != 0)
            {
                finId = Long.parseLong(recentSampleInvoice.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSampleInvoice.get("serBillId").toString()) + 1
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
        JSONArray saleData = new JSONArray(params.saleData)
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

        for (JSONObject sale : saleData)
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

            JSONObject sampleInvoicingProducts = new JSONObject()
            sampleInvoicingProducts.put("finId", finId)
            sampleInvoicingProducts.put("billId", 0)
            sampleInvoicingProducts.put("billType", 0)
            sampleInvoicingProducts.put("serBillId", 0)
            sampleInvoicingProducts.put("seriesId", seriesId)
            sampleInvoicingProducts.put("productId", productId)
            sampleInvoicingProducts.put("batchNumber", batchNumber)
            sampleInvoicingProducts.put("expiryDate", expDate)
            sampleInvoicingProducts.put("sqty", saleQty)
            sampleInvoicingProducts.put("freeQty", freeQty)
            sampleInvoicingProducts.put("sqtyReturn", saleQty)
            sampleInvoicingProducts.put("fqtyReturn", freeQty)
            sampleInvoicingProducts.put("repQty", 0)
            sampleInvoicingProducts.put("pRate", 0) //TODO: to be changed
            sampleInvoicingProducts.put("sRate", saleRate)
            sampleInvoicingProducts.put("mrp", mrp)
            sampleInvoicingProducts.put("discount", discount)
            sampleInvoicingProducts.put("gstAmount", gst)
            sampleInvoicingProducts.put("sgstAmount", sgst)
            sampleInvoicingProducts.put("cgstAmount", cgst)
            sampleInvoicingProducts.put("igstAmount", igst)

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

            sampleInvoicingProducts.put("gstPercentage", sale.get("16").toString())
            sampleInvoicingProducts.put("sgstPercentage", sale.get("17").toString())
            sampleInvoicingProducts.put("cgstPercentage", sale.get("18").toString())
            sampleInvoicingProducts.put("igstPercentage", sale.get("19").toString())
            sampleInvoicingProducts.put("originalSqty", sale.get("20").toString())
            sampleInvoicingProducts.put("originalFqty", sale.get("21").toString())

            sampleInvoicingProducts.put("gstId", 0) //TODO: to be changed
            sampleInvoicingProducts.put("amount", value)
            sampleInvoicingProducts.put("reason", "") //TODO: to be changed
            sampleInvoicingProducts.put("fridgeId", 0) //TODO: to be changed
            sampleInvoicingProducts.put("kitName", 0) //TODO: to be changed
            sampleInvoicingProducts.put("saleFinId", "") //TODO: to be changed
            sampleInvoicingProducts.put("redundantBatch", 0) //TODO: to be changed
            sampleInvoicingProducts.put("status", 0)
            sampleInvoicingProducts.put("syncStatus", 0)
            sampleInvoicingProducts.put("financialYear", financialYear)
            sampleInvoicingProducts.put("entityId", entityId)
            sampleInvoicingProducts.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            gtnProducts.add(sampleInvoicingProducts)

            //save to sale transaction log
            //save to sale transportation details

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        sampleInvoice.put("serBillId", serBillId)
        sampleInvoice.put("customerId", customerId)
        sampleInvoice.put("customerNumber", 0) //TODO: to be changed
        sampleInvoice.put("finId", finId)
        sampleInvoice.put("seriesId", seriesId)
        sampleInvoice.put("priorityId", priorityId)
        sampleInvoice.put("financialYear", financialYear)
        sampleInvoice.put("dueDate", duedate)
        sampleInvoice.put("paymentStatus", 0)
        sampleInvoice.put("userId", session.getAttribute("userId"))
        sampleInvoice.put("entryDate", entryDate)
        sampleInvoice.put("orderDate", orderDate)
        sampleInvoice.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        sampleInvoice.put("salesmanId", "0") //TODO: to be changed
        sampleInvoice.put("salesmanComm", "0") //TODO: to be changed
        sampleInvoice.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        sampleInvoice.put("deliveryManId", "0") //TODO: to be changed
        sampleInvoice.put("accountModeId", "0") //TODO: to be changed
        sampleInvoice.put("totalSqty", totalSqty)
        sampleInvoice.put("totalFqty", totalFqty)
        sampleInvoice.put("totalGst", totalGst)
        sampleInvoice.put("totalSgst", totalSgst)
        sampleInvoice.put("totalCgst", totalCgst)
        sampleInvoice.put("totalIgst", totalIgst)
        sampleInvoice.put("totalQty", totalSqty + totalFqty)
        sampleInvoice.put("totalItems", totalSqty + totalFqty)
        sampleInvoice.put("totalDiscount", totalDiscount)
        sampleInvoice.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        sampleInvoice.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        sampleInvoice.put("totalAmount", totalAmount)
        sampleInvoice.put("balance", totalAmount)
        sampleInvoice.put("entityId", entityId)
        sampleInvoice.put("entityTypeId", session.getAttribute("entityTypeId"))
        sampleInvoice.put("createdUser", session.getAttribute("userId"))
        sampleInvoice.put("modifiedUser", session.getAttribute("userId"))
        sampleInvoice.put("message", message) //TODO: to be changed
        sampleInvoice.put("gstStatus", "0") //TODO: to be changed
        sampleInvoice.put("billStatus", billStatus)
        sampleInvoice.put("lockStatus", 0) //TODO: to be changed
        sampleInvoice.put("syncStatus", "0") //TODO: to be changed
        sampleInvoice.put("creditadjAmount", 0) //TODO: to be changed
        sampleInvoice.put("creditIds", "0") //TODO: to be changed
        sampleInvoice.put("referralDoctor", "0") //TODO: to be changed
        sampleInvoice.put("taxable", "1") //TODO: to be changed
        sampleInvoice.put("cashDiscount", 0) //TODO: to be changed
        sampleInvoice.put("exempted", 0) //TODO: to be changed
        sampleInvoice.put("seriesCode", seriesCode)
        sampleInvoice.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("sampleInvoice", sampleInvoice)
        jsonObject.put("sampleInvoicingProducts", gtnProducts)
        Response response = new SalesService().saveSampleInvocing(jsonObject)
        if (response.status == 200)
        {
            UUID uuid
            JSONObject sampleInvoiceDetail = new JSONObject(response.readEntity(String.class))
            //update stockbook
            for (JSONObject sale : saleData)
            {
                uuid = UUID.randomUUID()
//                String tempStockRowId = sale.get("15")
//                def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
                def stockBook = new InventoryService().getStocksOfProductAndBatch(sale.get("1").toString(), sale.get("2").toString(),session.getAttribute('entityId').toString())

                long saleQty =  Long.parseLong(sale.get("4").toString())
                long saleFreeQty =  Long.parseLong(sale.get("5").toString())
                long remainingQty = Long.parseLong(stockBook.get("remainingQty").toString())
                long  remainingFreeQty = Long.parseLong(stockBook.get("remainingFreeQty").toString())

                if (saleQty <= remainingQty) {
                    remainingQty = remainingQty - saleQty

                } else if (saleQty > remainingQty && saleQty <= (remainingQty + remainingFreeQty)) {
                    remainingFreeQty = remainingFreeQty - (saleQty - remainingQty)
                    remainingQty = 0
                }
                if (saleFreeQty <= remainingFreeQty) {
                    remainingFreeQty = remainingFreeQty - saleFreeQty
                } else if (saleFreeQty > remainingFreeQty && saleFreeQty <= (remainingQty + remainingFreeQty)) {
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
            responseJson.put("sampleInvoiceDetail", sampleInvoiceDetail)
            respond responseJson, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }


    def printSampleInvoice() {
        String sampleInvId = params.id
        JSONObject sampleInvDetail = new SalesService().getSampleBillDetailsById(sampleInvId)
        if (sampleInvDetail != null) {
            JSONArray sampleProductDetails = new SalesService().getSampleProductDetailsByBill(sampleInvId)
            JSONObject series = new EntityService().getSeriesById(sampleInvDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(sampleInvDetail.get("customerId").toString())
            println("Entity ID is: "+ session.getAttribute("entityId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            if(entity == null)
            {
                println("Entity is null")
            }
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            termsConditions.each {
                JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
                if (formMaster!= null) {
                    if (it.formId == formMaster.id) {
                        it.put("form", formMaster)
                    }
                }
            }
            sampleProductDetails.each {
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
            def totalcgst = UtilsService.round(sampleProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(sampleProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(sampleProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(sampleProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : sampleProductDetails) {
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

            JSONObject irnDetails = null
            if (sampleInvDetail.has("irnDetails") && sampleInvDetail.get("irnDetails") != null)
                irnDetails = new JSONObject(sampleInvDetail.get("irnDetails").toString())

            render(view: "/sales/sampleConversion/sample-invoice-print", model: [sampleBillDetail    : sampleInvDetail,
                                                                                 sampleProductDetails: sampleProductDetails,
                                                                                 series              : series, entity: entity, customer: customer, city: city,
                                                                                 total               : total, custcity: custcity,
                                                                                 termsConditions     : termsConditions,
                                                                                 totalcgst           : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                                 totaldiscount       : totaldiscount,
                                                                                 gstGroup            : gstGroup,
                                                                                 sgstGroup           : sgstGroup,
                                                                                 cgstGroup           : cgstGroup,
                                                                                 igstGroup           : igstGroup,
                                                                                 totalBeforeTaxes    : totalBeforeTaxes,
                                                                                 irnDetails        : irnDetails
            ])
        } else {

            render("No Bill Found")
        }
    }

    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().showSampleInvoice(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if (responseObject)
                {
                    JSONArray jsonArray = responseObject.data
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
}
