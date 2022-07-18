package phitb_ui.sales


import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EInvoiceService
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.TaxController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class GoodsTransferNoteController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        JSONArray customers = new EntityService().getByEntity(entityId)
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        render(view: '/sales/goodsTransferNote/gtn', model: [customers   : customers, divisions: divisions, series: series,
                                                             salesmanList: salesmanList, priorityList: priorityList])
    }

    def saveGtn()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject gtn = new JSONObject()
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
            def recentGTN = new SalesService().getRecentGTN(financialYear, entityId, billStatus)
            if (recentGTN != null && recentGTN.size() != 0)
            {
                finId = Long.parseLong(recentGTN.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentGTN.get("serBillId").toString()) + 1
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

            JSONObject gtnProduct = new JSONObject()
            gtnProduct.put("finId", finId)
            gtnProduct.put("billId", 0)
            gtnProduct.put("billType", 0)
            gtnProduct.put("serBillId", 0)
            gtnProduct.put("seriesId", seriesId)
            gtnProduct.put("productId", productId)
            gtnProduct.put("batchNumber", batchNumber)
            gtnProduct.put("expiryDate", expDate)
            gtnProduct.put("sqty", saleQty)
            gtnProduct.put("freeQty", freeQty)
            gtnProduct.put("sqtyReturn", saleQty)
            gtnProduct.put("fqtyReturn", freeQty)
            gtnProduct.put("repQty", 0)
            gtnProduct.put("pRate", 0) //TODO: to be changed
            gtnProduct.put("sRate", saleRate)
            gtnProduct.put("mrp", mrp)
            gtnProduct.put("discount", discount)
            gtnProduct.put("gstAmount", gst)
            gtnProduct.put("sgstAmount", sgst)
            gtnProduct.put("cgstAmount", cgst)
            gtnProduct.put("igstAmount", igst)

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

            gtnProduct.put("gstPercentage", sale.get("16").toString())
            gtnProduct.put("sgstPercentage", sale.get("17").toString())
            gtnProduct.put("cgstPercentage", sale.get("18").toString())
            gtnProduct.put("igstPercentage", sale.get("19").toString())
            gtnProduct.put("originalSqty", sale.get("20").toString())
            gtnProduct.put("originalFqty", sale.get("21").toString())

            gtnProduct.put("gstId", 0) //TODO: to be changed
            gtnProduct.put("amount", value)
            gtnProduct.put("reason", "") //TODO: to be changed
            gtnProduct.put("fridgeId", 0) //TODO: to be changed
            gtnProduct.put("kitName", 0) //TODO: to be changed
            gtnProduct.put("saleFinId", "") //TODO: to be changed
            gtnProduct.put("redundantBatch", 0) //TODO: to be changed
            gtnProduct.put("status", 0)
            gtnProduct.put("syncStatus", 0)
            gtnProduct.put("financialYear", financialYear)
            gtnProduct.put("entityId", entityId)
            gtnProduct.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            gtnProducts.add(gtnProduct)

            //save to sale transaction log
            //save to sale transportation details

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        gtn.put("serBillId", serBillId)
        gtn.put("customerId", customerId)
        gtn.put("customerNumber", 0) //TODO: to be changed
        gtn.put("finId", finId)
        gtn.put("seriesId", seriesId)
        gtn.put("priorityId", priorityId)
        gtn.put("financialYear", financialYear)
        gtn.put("dueDate", duedate)
        gtn.put("paymentStatus", 0)
        gtn.put("userId", session.getAttribute("userId"))
        gtn.put("entryDate", entryDate)
        gtn.put("orderDate", orderDate)
        gtn.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        gtn.put("salesmanId", "0") //TODO: to be changed
        gtn.put("salesmanComm", "0") //TODO: to be changed
        gtn.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        gtn.put("deliveryManId", "0") //TODO: to be changed
        gtn.put("accountModeId", "0") //TODO: to be changed
        gtn.put("totalSqty", totalSqty)
        gtn.put("totalFqty", totalFqty)
        gtn.put("totalGst", totalGst)
        gtn.put("totalSgst", totalSgst)
        gtn.put("totalCgst", totalCgst)
        gtn.put("totalIgst", totalIgst)
        gtn.put("totalQty", totalSqty + totalFqty)
        gtn.put("totalItems", totalSqty + totalFqty)
        gtn.put("totalDiscount", totalDiscount)
        gtn.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        gtn.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        gtn.put("totalAmount", totalAmount)
        gtn.put("balance", totalAmount)
        gtn.put("entityId", entityId)
        gtn.put("entityTypeId", session.getAttribute("entityTypeId"))
        gtn.put("createdUser", session.getAttribute("userId"))
        gtn.put("modifiedUser", session.getAttribute("userId"))
        gtn.put("message", message) //TODO: to be changed
        gtn.put("gstStatus", "0") //TODO: to be changed
        gtn.put("billStatus", billStatus)
        gtn.put("lockStatus", 0) //TODO: to be changed
        gtn.put("syncStatus", "0") //TODO: to be changed
        gtn.put("creditadjAmount", 0) //TODO: to be changed
        gtn.put("creditIds", "0") //TODO: to be changed
        gtn.put("referralDoctor", "0") //TODO: to be changed
        gtn.put("taxable", "1") //TODO: to be changed
        gtn.put("cashDiscount", 0) //TODO: to be changed
        gtn.put("exempted", 0) //TODO: to be changed
        gtn.put("seriesCode", seriesCode)
        gtn.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("gtn", gtn)
        jsonObject.put("gtnProducts", gtnProducts)
        Response response = new SalesService().saveGTN(jsonObject)
        if (response.status == 200)
        {
            UUID uuid
            JSONObject gtnDetail = new JSONObject(response.readEntity(String.class))
            //update stockbook
            for (JSONObject sale : saleData)
            {
                uuid = UUID.randomUUID()
                long saleQty =  Long.parseLong(sale.get("4").toString())
                long saleFreeQty =  Long.parseLong(sale.get("5").toString())
//                String tempStockRowId = sale.get("15")
//                def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
                def stockBook = new InventoryService().getStocksOfProductAndBatch(sale.get("1").toString(),sale.get("2").toString(),
                        entityId)
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
            responseJson.put("gtn", gtnDetail)
            respond responseJson, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }


    def show()
    {
        try
        {
            def apiResponse = new SalesService().getSaleInvoice()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def showById(String id)
    {
        try
        {
            def apiResponse = new SalesService().getSaleInvoiceById(id)
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def saleBill()
    {
        render(view: '/sales/salebillDetails/saleBill')
    }

    def saleRetrun()
    {
        render(view: '/sales/saleRetrun/sale-return-print')
    }

    def crdDebS()
    {
        render(view: "/sales/credit-debit-settlement")
    }

    def DebJV()
    {
        render(view: "/sales/debit-jv")
    }

    def credJV()
    {
        render(view: '/sales/credit-jv')
    }

    def goodsSalesRecipt()
    {
        render(view: "/sales/goods-sales-recipt")
    }

    def paymentVocher()
    {
        render(view: "/sales/payment-vocher")
    }


    def checkSchemeConfiguration()
    {
        String productId = params.productId
        String batchNumber = params.batchNumber

        if (productId && batchNumber)
        {
            respond new SalesService().getSchemeConfiguration(productId, batchNumber)
        }
        else
        {
            response.status = 400
        }
    }


    def getProductsById(String id)
    {
        try
        {
            JSONObject product = new ProductService().getProductById(id)
            return product
        }
        catch (Exception ex)
        {
            System.err.println('Service :getProductsById , action :  show  , Ex:' + ex)
            log.error('Service :getProductsById , action :  show  , Ex:' + ex)
        }
    }



    def cancelGTN() {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new SalesService().cancelGTN(id, entityId, financialYear)
        if (jsonObject) {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails) {
                for (JSONObject productDetail : productDetails) {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, productDetail?.entityId?.toString())
                    double remainingQty = stockBook.get("remainingQty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty")

                    //checking to where the stocks to be returned
                    double originalSqty = productDetail.get("originalSqty")
                    double originalFqty = productDetail.get("originalFqty")
                    double sqty = productDetail.get("sqty")
                    double freeQty = productDetail.get("freeQty")

                    if ((originalSqty + originalFqty) == (sqty + freeQty) && originalSqty == sqty && originalFqty == freeQty) {
                        remainingQty += sqty
                        remainingFreeQty += freeQty
                    } else {
                        if (originalSqty >= sqty && originalFqty >= freeQty) {
                            remainingQty += sqty
                            remainingFreeQty += freeQty
                        } else {
                            if (sqty > originalSqty) {
                                remainingQty = sqty - (sqty - originalSqty)
                                remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
                            } else if (freeQty > originalFqty) {
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
        } else {
            response.status = 400
        }
    }


    def editSaleBillDetails()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def saleBillId = params.saleBillId
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        if (saleBillDetail != null && saleBillDetail.billStatus == 'DRAFT')
        {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
            render(view: '/sales/saleEntry/edit-sale-entry', model: [customers         : customers, divisions: divisions, series: series,
                                                                     priorityList      : priorityList, saleBillDetail: saleBillDetail,
                                                                     saleProductDetails: saleProductDetails])
        }
        else
        {
            render('No Draft invoice found!!')
        }

    }


    def getSaleProductDetailsByBill()
    {
        try
        {
            if (params.id)
            {
                JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(params.id)
                def saleBillResponse = new SalesService().getSaleBillDetailsById(params.id.toString())
                saleProductDetails.each {
                    def stockResponse = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(),
                            it.batchNumber.toString(), session.getAttribute('entityId').toString())
                    if (it.batchNumber == stockResponse.batchNumber)
                    {
                        def tax = new TaxController().show(stockResponse.taxId.toString())
                        it.put("gst", tax.taxValue)
                        it.put("sgst", tax.salesSgst)
                        it.put("cgst", tax.salesCgst)
                        it.put("igst", tax.salesIgst)
                    }
                    it.put("billId", saleBillResponse as JSONObject)
                    def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                    it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
                }
                respond saleProductDetails, formats: ['json'], status: 200;
            }
            else
            {
                return [];
            }
        }

        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }


    def updateSaleProductDetails()
    {
        try
        {

            JSONArray jsonArray = new JSONArray(params.rowData)
            JSONArray saleProductDetails = new JSONArray()
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(params.billId)
            Boolean isEdit = false
            int i = 0
            for (Object obj : jsonArray)
            {
                //15 if edit, 16 if being added
                if (i == 15 && obj != null)
                {
                    isEdit = true
                }
/*                else if(i == 16 && obj != null)
                    isEdit = false*/
                i++
            }
            String saleProductId
            if (jsonArray.isNull(15))
            {
                saleProductId = 0;
            }
            else
            {
                saleProductId = jsonArray[15]
            }
            String productId = jsonArray[1]
            String batchNumber = jsonArray[2]
            String expDate = jsonArray[3]
            String saleQty = jsonArray[4]
            String freeQty = jsonArray[5]
            String saleRate = jsonArray[6]
            String mrp = jsonArray[7]
            double discount = UtilsService.round(Double.parseDouble(jsonArray[8].toString()), 2)
            String packDesc = jsonArray[9]
            double gst = UtilsService.round(Double.parseDouble(jsonArray[10].toString()), 2)
            double value = UtilsService.round(Double.parseDouble(jsonArray[11].toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(jsonArray[12].toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(jsonArray[13].toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(jsonArray[14].toString()), 2)

            JSONObject saleProductDetail = new JSONObject()
            if (saleProductId != 0)
            {
                saleProductDetail.put("id", saleProductId)
            }
            saleProductDetail.put("finId", saleBillDetail.get('finId'))
            saleProductDetail.put("billType", 0)
            saleProductDetail.put("serBillId", saleBillDetail.get('serBillId'))
            saleProductDetail.put("seriesId", 1)
            saleProductDetail.put("billId", params.billId)
            saleProductDetail.put("productId", productId)
            saleProductDetail.put("batchNumber", batchNumber)
            saleProductDetail.put("expiryDate", expDate)
            saleProductDetail.put("sqty", saleQty)
            saleProductDetail.put("freeQty", freeQty)
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0) //TODO: to be changed
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)
            saleProductDetail.put("gstPercentage", jsonArray[16].toString())
            saleProductDetail.put("sgstPercentage", jsonArray[17].toString())
            saleProductDetail.put("cgstPercentage", jsonArray[18].toString())
            saleProductDetail.put("igstPercentage", jsonArray[19].toString())
            saleProductDetail.put("gstId", 0) //TODO: to be changed
            saleProductDetail.put("amount", value)
            saleProductDetail.put("reason", "") //TODO: to be changed
            saleProductDetail.put("fridgeId", 0) //TODO: to be changed
            saleProductDetail.put("kitName", 0) //TODO: to be changed
            saleProductDetail.put("saleFinId", "") //TODO: to be changed
            saleProductDetail.put("redundantBatch", 0) //TODO: to be changed
            saleProductDetail.put("status", 0)
            saleProductDetail.put("syncStatus", 0)
            saleProductDetail.put("financialYear", session.getAttribute('financialYear').toString())
            saleProductDetail.put("entityId", session.getAttribute("entityId").toString())
            saleProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleProductDetail.put("uuid", params.uuid)
            saleProductDetails.add(saleProductDetail)

            //save to sale transaction log
            //save to sale transportation details
            if (!isEdit)
            {
                def saveResponse = new SalesService().saveSaleProductDetail(saleProductDetail)
                if (saveResponse?.status == 200)
                {
                    JSONObject obj = new JSONObject(saveResponse.readEntity(String.class))
                    def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                    if (productDetail)
                    {
                        def stockBook = new InventoryService().getStocksOfProductAndBatch(obj.productId.toString(), saleProductDetail.batchNumber, session.getAttribute("entityId").toString())
                        double remainingQty = stockBook.get("remainingQty") - obj.get("sqty")
                        double remainingFreeQty = stockBook.get("remainingFreeQty") - obj.get("freeQty")
                        double remainingReplQty = stockBook.get("remainingReplQty") - obj.get("repQty")
                        stockBook.put("remainingQty", remainingQty.toLong())
                        stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                        stockBook.put("remainingReplQty", remainingReplQty.toLong())
                        stockBook.put("uuid", params.uuid)
                        new InventoryService().updateStockBook(stockBook)
                    }
                    respond obj, formats: ['json'], status: 200
                }
            }
            else
            {
                if (saleProductId != null)
                {
                    def productDetail1 = new SalesService().getSaleProductDetailsById(saleProductId.toString())
                    println(productDetail1.productId)
                    if (productDetail1)
                    {
                        def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail1.productId.toString(),
                                productDetail1.batchNumber.toString(), session.getAttribute("entityId").toString())
                        double remainingQty = stockBook.get("remainingQty") + productDetail1.sqty
                        double remainingFreeQty = stockBook.get("remainingFreeQty") + productDetail1.freeQty
                        double remainingReplQty = stockBook.get("remainingReplQty") + productDetail1.repQty
                        stockBook.put("remainingQty", remainingQty.toLong())
                        stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                        stockBook.put("remainingReplQty", remainingReplQty.toLong())
                        new InventoryService().updateStockBook(stockBook)
                    }
                    def updateResponse = new SalesService().updateSaleProductDetail(saleProductDetail)
                    if (updateResponse?.status == 200)
                    {
                        JSONObject obj = new JSONObject(updateResponse.readEntity(String.class))
                        def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                        if (productDetail)
                        {
                            def stockBook = new InventoryService().getStocksOfProductAndBatch(obj.productId.toString(), obj.batchNumber, session.getAttribute("entityId").toString())
                            double remainingQty = stockBook.get("remainingQty") - obj.get("sqty")
                            double remainingFreeQty = stockBook.get("remainingFreeQty") - obj.get("freeQty")
                            double remainingReplQty = stockBook.get("remainingReplQty") - obj.get("repQty")
                            stockBook.put("remainingQty", remainingQty.toLong())
                            stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                            stockBook.put("remainingReplQty", remainingReplQty.toLong())
                            new InventoryService().updateStockBook(stockBook)
                        }
                        respond obj, formats: ['json'], status: 200
                    }
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def deleteSaleProduct()
    {
        try
        {
            def id = params.id
            def productDetail = new SalesService().getSaleProductDetailsById(id)
            if (productDetail)
            {
                def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())
                double remainingQty = stockBook.get("remainingQty") + productDetail.get("sqty")
                double remainingFreeQty = stockBook.get("remainingFreeQty") + productDetail.get("freeQty")
                double remainingReplQty = stockBook.get("remainingReplQty") + productDetail.get("repQty")
                stockBook.put("remainingQty", remainingQty.toLong())
                stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                stockBook.put("remainingReplQty", remainingReplQty.toLong())
                new InventoryService().updateStockBook(stockBook)
            }
            def apiResponse = new SalesService().deleteSaleProduct(id);
            respond(text: id, status: apiResponse.status)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }

    def updateSaleBillDetails()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleBillDetails = new JSONObject()
        JSONArray saleProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = "NA"
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT"))
        {
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            println(recentSaleBill)
            if (recentSaleBill != null && recentSaleBill.size() != 0)
            {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
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
        for (JSONObject sale : saleData)
        {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            String saleProductId = sale.get("15")
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

            //save to sale transaction log
            //save to sale transportation details

            JSONObject saleProductDetail = new JSONObject()
            saleProductDetail.put("finId", finId)
            saleProductDetail.put("id", saleProductId)
            saleProductDetail.put("billId", 0)
            saleProductDetail.put("billType", 0)
            saleProductDetail.put("serBillId", 0)
            saleProductDetail.put("seriesId", seriesId)
            saleProductDetail.put("productId", productId)
            saleProductDetail.put("batchNumber", batchNumber)
            saleProductDetail.put("expiryDate", expDate)
            saleProductDetail.put("sqty", saleQty)
            saleProductDetail.put("freeQty", freeQty)
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0)
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)


            saleProductDetail.put("gstPercentage", sale.get("16").toString())
            saleProductDetail.put("sgstPercentage", sale.get("17").toString())
            saleProductDetail.put("cgstPercentage", sale.get("18").toString())
            saleProductDetail.put("igstPercentage", sale.get("19").toString())

            saleProductDetail.put("gstId", 0) //TODO: to be changed
            saleProductDetail.put("amount", value)
            saleProductDetail.put("reason", "") //TODO: to be changed
            saleProductDetail.put("fridgeId", 0) //TODO: to be changed
            saleProductDetail.put("kitName", 0) //TODO: to be changed
            saleProductDetail.put("saleFinId", "") //TODO: to be changed
            saleProductDetail.put("redundantBatch", 0) //TODO: to be changed
            saleProductDetail.put("status", 0)
            saleProductDetail.put("syncStatus", 0)
            saleProductDetail.put("financialYear", financialYear)
            saleProductDetail.put("entityId", entityId)
            saleProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleProductDetail.put("uuid", params.uuid)
            saleProductDetails.add(saleProductDetail)
        }

        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //update to sale bill details
        saleBillDetails.put("id", params.id)
        saleBillDetails.put("serBillId", serBillId)
        saleBillDetails.put("customerId", customerId)
        saleBillDetails.put("customerNumber", 0) //TODO: to be changed
        saleBillDetails.put("finId", finId)
        saleBillDetails.put("seriesId", seriesId)
        saleBillDetails.put("priorityId", priorityId)
        saleBillDetails.put("financialYear", session.getAttribute('financialYear'))
        saleBillDetails.put("dueDate", duedate)
        saleBillDetails.put("paymentStatus", 0)
        saleBillDetails.put("userId", session.getAttribute("userId"))
        saleBillDetails.put("entryDate", entryDate)
        saleBillDetails.put("orderDate", orderDate)
        saleBillDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleBillDetails.put("salesmanId", "0") //TODO: to be changed
        saleBillDetails.put("salesmanComm", "0") //TODO: to be changed
        saleBillDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleBillDetails.put("deliveryManId", "0") //TODO: to be changed
        saleBillDetails.put("accountModeId", "0") //TODO: to be changed
        saleBillDetails.put("totalSqty", totalSqty)
        saleBillDetails.put("totalFqty", totalFqty)
        saleBillDetails.put("totalGst", totalGst)
        saleBillDetails.put("totalSgst", totalSgst)
        saleBillDetails.put("totalCgst", totalCgst)
        saleBillDetails.put("totalIgst", totalIgst)
        saleBillDetails.put("totalQty", totalSqty + totalFqty)
        saleBillDetails.put("totalItems", totalSqty + totalFqty)
        saleBillDetails.put("totalDiscount", totalDiscount)
        saleBillDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleBillDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleBillDetails.put("totalAmount", totalAmount)
        saleBillDetails.put("balance", totalAmount)
        saleBillDetails.put("entityId", entityId)
        saleBillDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleBillDetails.put("createdUser", session.getAttribute("userId"))
        saleBillDetails.put("modifiedUser", session.getAttribute("userId"))
        saleBillDetails.put("message", message) //TODO: to be changed
        saleBillDetails.put("gstStatus", "0") //TODO: to be changed
        saleBillDetails.put("billStatus", billStatus)
        saleBillDetails.put("lockStatus", 0) //TODO: to be changed
        saleBillDetails.put("syncStatus", "0") //TODO: to be changed
        saleBillDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleBillDetails.put("creditIds", "0") //TODO: to be changed
        saleBillDetails.put("referralDoctor", "0") //TODO: to be changed
        saleBillDetails.put("taxable", "1") //TODO: to be changed
        saleBillDetails.put("cashDiscount", 0) //TODO: to be changed
        saleBillDetails.put("exempted", 0) //TODO: to be changed
        saleBillDetails.put("seriesCode", seriesCode)
        saleBillDetails.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleInvoice", saleBillDetails)
        jsonObject.put("saleProducts", saleProductDetails)
        Response response = new SalesService().updateSaleInvoice(jsonObject, saleBillDetails.get("id").toString())
        if (response.status == 200)
        {
            def saleBillDetail = new JSONObject(response.readEntity(String.class))
            if (saleBillDetail)
            {
                try
                {
                    if (billStatus.equalsIgnoreCase("ACTIVE"))
                    {
                        //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                        new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace()
                }
                JSONObject responseJson = new JSONObject()
                responseJson.put("series", series)
                responseJson.put("saleBillDetail", saleBillDetail)
                respond responseJson, formats: ['json']
            }
            else
            {
                response.status == 400
            }
        }
        else
        {
            response.status == 400
        }

    }


    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId",session.getAttribute('entityId'))
            def apiResponse = new SalesService().showGTN(jsonObject)
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


    def grn()
    {
        render(view: '/sales/goodsTransferNote/grn')
    }


    def approveGRN()
    {
        def gtn = new SalesService().getGTNById(params.gtn)
        if (gtn != null)
        {
            def gtnProduct = new SalesService().getgtnProductDetailsByGtn(gtn.id.toString())
            println(session.getAttribute('entityId').toString())
            UUID uuid
            for (JSONObject gtnObject : gtnProduct)
            {
                def stockBook = new InventoryService().getStocksOfProductAndBatch(gtnObject.productId.toString(),
                        gtnObject.batchNumber, session.getAttribute('entityId').toString())
                if (stockBook != null)
                {
                    double remainingQty = stockBook.get("remainingQty") + Double.parseDouble(gtnObject.sqty.toString())
                    double remainingFreeQty = stockBook.get("remainingFreeQty") + Double.parseDouble(gtnObject.freeQty.toString())
                    double remainingReplQty = stockBook.get("remainingReplQty") + Double.parseDouble(gtnObject.repQty.toString())
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", remainingReplQty.toLong())
                    new InventoryService().updateStockBook(stockBook)
                    new SalesService().approveGTN(gtn.id.toString(), gtn.entityId.toString(), gtn.financialYear
                            .toString())
                }
                else
                {
                    JSONArray stockArray = new JSONArray()
                    JSONObject stock = new JSONObject()
                    def stockBook1 = new InventoryService().getStocksOfProductAndBatch(gtnObject.productId.toString(),
                            gtnObject.batchNumber.toString(), gtnObject.entityId.toString())
                    stockBook1.put("remainingQty", Double.valueOf(Double.parseDouble(gtnObject.sqty.toString())).longValue())
                    stockBook1.put("remainingFreeQty", Double.valueOf(Double.parseDouble(gtnObject.freeQty.toString())).longValue())
                    stockBook1.put("remainingReplQty", Double.valueOf(Double.parseDouble(gtnObject.repQty.toString())).longValue())
                    stockBook1.put("entityId", session.getAttribute('entityId').toString())
                    stockBook1.put("uuid", UUID.randomUUID())
                    stockBook1.put("entityTypeId", session.getAttribute('entityTypeId').toString())
                    def apires = new InventoryService().stockBookSave(stockBook1)
                    new SalesService().approveGTN(gtn.id.toString(), gtn.entityId.toString(), gtn.financialYear
                            .toString())
                }
            }
            respond gtn, formats: ['json'], status: 200
        }
        else
        {
            response.status = 400
        }
    }

    def printGRN()
    {

        String gtnId = params.id
        JSONObject gtnDetail = new SalesService().getGTNDetailsById(gtnId)
        if (gtnDetail != null)
        {
            JSONArray gtnProductDetails = new SalesService().getgtnProductDetailsByGtn(gtnId)
            JSONObject series = new EntityService().getSeriesById(gtnDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(gtnDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(gtnDetail.get("entityId").toString())
            JSONObject parentEntity = new EntityService().getEntityById(customer.get("parentEntity").toString())
            JSONObject parentCity = new SystemService().getCityById(parentEntity.get('cityId').toString())
            parentEntity.put("city", parentCity)
            customer.put("parentEntity",parentEntity)
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(gtnDetail.get("entityId").toString())
            gtnProductDetails.each {
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
            def totalcgst = UtilsService.round(gtnProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(gtnProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(gtnProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(gtnProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : gtnProductDetails)
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

            render(view: "/sales/goodsTransferNote/grn-print", model: [grnBillDetail    : gtnDetail,
                                                                       grnProductDetails: gtnProductDetails,
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
