package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EInvoiceService
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.ProductService
import phitb_ui.entity.TaxController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleEntryController {

    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        render(view: '/sales/saleEntry/sale-entry', model: [customers   : customers, divisions: divisions, series: series,
                                                            salesmanList: salesmanList, priorityList: priorityList])
    }

    def editSaleBillDetails() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def saleBillId = params.saleBillId
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        JSONObject customer = new EntityService().getEntityById(saleBillDetail.customerId.toString())
        if (saleBillDetail != null && saleBillDetail.billStatus == 'DRAFT') {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
            render(view: '/sales/saleEntry/sale-entry', model: [customers         : customers, divisions: divisions, series: series,
                                                                priorityList      : priorityList, saleBillDetail: saleBillDetail,
                                                                saleProductDetails: saleProductDetails, customer:customer])
        } else {
            render('No Draft invoice found!!')
        }

    }

    def saveSaleEntry() {
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
        String message = params.message
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            if (recentSaleBill != null && recentSaleBill.size() != 0) {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
            } else {
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
        for (JSONObject sale : saleData) {
            if (sale.has("15")) {
                String tempStockRowId = sale.get("15")
                if (tempStockRowId && Long.parseLong(tempStockRowId) > 0) {
                    tempStocksSavedCheck = true
                } else {
                    tempStocksSavedCheck = false
                }
            } else {
                tempStocksSavedCheck = false
            }
        }

        //safety check
        if (!tempStocksSavedCheck) {
            println("Safety Check Failed! attempted to generate sale invoice, but temp stock was not saved.")
            response.status == 400
            return
        }

        for (JSONObject sale : saleData) {
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

            JSONObject saleProductDetail = new JSONObject()
            saleProductDetail.put("finId", finId)
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
            saleProductDetail.put("igstAmount", igst)

            saleProductDetail.put("gstPercentage", sale.get("16").toString())
            saleProductDetail.put("sgstPercentage", sale.get("17").toString())
            saleProductDetail.put("cgstPercentage", sale.get("18").toString())
            saleProductDetail.put("igstPercentage", sale.get("19").toString())
            saleProductDetail.put("originalSqty", sale.get("20").toString())
            saleProductDetail.put("originalFqty", sale.get("21").toString())


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
            saleProductDetails.add(saleProductDetail)

            //save to sale transaction log
            //save to sale transportation details

        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleBillDetails.put("serBillId", serBillId)
        saleBillDetails.put("customerId", customerId)
        saleBillDetails.put("customerNumber", 0) //TODO: to be changed
        saleBillDetails.put("finId", finId)
        saleBillDetails.put("seriesId", seriesId)
        saleBillDetails.put("priorityId", priorityId)
        saleBillDetails.put("financialYear", financialYear)
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
        Response response = new SalesService().saveSaleInvoice(jsonObject)
        if (response.status == 200) {
            JSONObject saleBillDetail = new JSONObject(response.readEntity(String.class))
            UUID uuid
            //update stockbook
            for (JSONObject sale : saleData) {
                String tempStockRowId = sale.get("15")
                def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
                def stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
                stockBook.put("remainingQty", tmpStockBook.get("remainingQty"))
                stockBook.put("remainingFreeQty", tmpStockBook.get("remainingFreeQty"))
                stockBook.put("remainingReplQty", tmpStockBook.get("remainingReplQty"))
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
                stockBook.put("uuid", UUID.randomUUID())
                def apiRes = new InventoryService().updateStockBook(stockBook)
                if (apiRes.status == 200) {
                    //clear tempstockbook
                    new InventoryService().deleteTempStock(tempStockRowId)
                    try {
                        if (billStatus.equalsIgnoreCase("ACTIVE")) {
                            //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                            new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace()
                    }
                }
            }

            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleBillDetail", saleBillDetail)
            respond responseJson, formats: ['json']
        } else
            response.status = 400
    }

    def printSaleInvoice() {

        String saleBillId = params.id
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        if (saleBillDetail != null) {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
            JSONObject series = new EntityService().getSeriesById(saleBillDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            termsConditions.each {
                JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
                if (formMaster != null) {
                    if (it.formId == formMaster.id) {
                        it.put("form", formMaster)
                    }
                }
            }
            println(termsConditions)
            saleProductDetails.each {
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
            def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : saleProductDetails) {
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
            if (saleBillDetail.has("irnDetails") && saleBillDetail.get("irnDetails") != null)
                irnDetails = new JSONObject(saleBillDetail.get("irnDetails").toString())

            render(view: "/sales/saleEntry/sale-invoice", model: [saleBillDetail    : saleBillDetail,
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
                                                                  irnDetails        : irnDetails
            ])
        } else {

            render("No Bill Found")
        }
    }


    def show() {
        try {
            def apiResponse = new SalesService().getSaleInvoice()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def showById(String id) {
        try {
            def apiResponse = new SalesService().getSaleInvoiceById(id)
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def saleBill() {
        render(view: '/sales/salebillDetails/saleBill')
    }

    def saleRetrun() {
        render(view: '/sales/saleRetrun/sale-return-print')
    }

    def crdDebS() {
        render(view: "/sales/credit-debit-settlement")
    }

    def DebJV() {
        render(view: "/sales/debit-jv")
    }

    def credJV() {
        render(view: '/sales/credit-jv')
    }

    def goodsSalesRecipt() {
        render(view: "/sales/goods-sales-recipt")
    }

    def paymentVocher() {
        render(view: "/sales/payment-vocher")
    }


    def checkSchemeConfiguration() {
        String productId = params.productId
        String batchNumber = params.batchNumber

        if (productId && batchNumber) {
            respond new SalesService().getSchemeConfiguration(productId, batchNumber)
        } else {
            response.status = 400
        }
    }


    def getProductsById(String id) {
        try {
            JSONObject product = new ProductService().getProductById(id)
            return product
        }
        catch (Exception ex) {
            System.err.println('Service :getProductsById , action :  show  , Ex:' + ex)
            log.error('Service :getProductsById , action :  show  , Ex:' + ex)
        }
    }

    def cancelInvoice() {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new SalesService().cancelInvoice(id, entityId, financialYear)
        if (jsonObject) {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails) {
                for (JSONObject productDetail : productDetails) {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())
                    double remainingQty = stockBook.get("remainingQty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty")

                    //checking to where the stocks to be returned
                    double originalSqty = productDetail.get("originalSqty")
                    double originalFqty = productDetail.get("originalFqty")
                    double sqty = productDetail.get("sqty")
                    double freeQty = productDetail.get("freeQty")

                    if((originalSqty + originalFqty) == (sqty + freeQty))
                    {
                        remainingQty += sqty
                        remainingFreeQty += freeQty
                    }
                    else
                    {
                        if(originalSqty >= sqty && originalFqty >= freeQty)
                        {
                            remainingQty += sqty
                            remainingFreeQty += freeQty
                        }
                        else
                        {
                            if(sqty > originalSqty){
                                remainingQty = sqty - (sqty - originalSqty)
                                remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
                            }
                            else if(freeQty > originalFqty)
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
            JSONObject invoice = jsonObject.get("invoice") as JSONObject
            if (invoice.has("irnDetails")) {
                JSONObject irnDetails = new JSONObject(invoice.get("irnDetails").toString())
                new EInvoiceService().cancelIRN(session, irnDetails.get("Irn").toString(), invoice.get("id").toString())
            }
            respond jsonObject, formats: ['json']
        } else {
            response.status = 400
        }
    }


    def getSaleProductDetailsByBill() {
        try {
            if (params.id) {
                JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(params.id)
                def saleBillResponse = new SalesService().getSaleBillDetailsById(params.id.toString())
                saleProductDetails.each {
                    def stockResponse = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(),
                            it.batchNumber.toString(), session.getAttribute('entityId').toString())
                    if (it.batchNumber == stockResponse.batchNumber) {
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
            } else {
                return [];
            }
        }

        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }


    def updateSaleProductDetails() {
        try {

            JSONArray jsonArray = new JSONArray(params.rowData)
            JSONArray saleProductDetails = new JSONArray()
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(params.billId)
            Boolean isEdit = false
            int i = 0
            for (Object obj : jsonArray) {
                //15 if edit, 16 if being added
                if (i == 15 && obj != null) {
                    isEdit = true
                }
/*                else if(i == 16 && obj != null)
                    isEdit = false*/
                i++
            }
            String saleProductId
            if (jsonArray.isNull(24)) {
                saleProductId = 0;
            } else {
                saleProductId = jsonArray[24]
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
            if (saleProductId != 0) {
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
            if (!isEdit) {
                def saveResponse = new SalesService().saveSaleProductDetail(saleProductDetail)
                if (saveResponse?.status == 200) {
                    JSONObject obj = new JSONObject(saveResponse.readEntity(String.class))
                    def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                    if (productDetail) {
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
            } else {
                if (saleProductId != null) {
                    def productDetail1 = new SalesService().getSaleProductDetailsById(saleProductId.toString())
                    println(productDetail1.productId)
                    if (productDetail1) {
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
                    if (updateResponse?.status == 200) {
                        JSONObject obj = new JSONObject(updateResponse.readEntity(String.class))
                        def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                        if (productDetail) {
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
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def deleteSaleProduct() {
        try {
            def id = params.id
            def productDetail = new SalesService().getSaleProductDetailsById(id)
            if (productDetail) {
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
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }

    def updateSaleBillDetails() {
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
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            println(recentSaleBill)
            if (recentSaleBill != null && recentSaleBill.size() != 0) {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
            } else {
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
        for (JSONObject sale : saleData) {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            String saleProductId = ""
            if(sale.has("24")) //get saved draft product id
                saleProductId = sale.get("24")
            else
                saleProductId = sale.get("15")
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
            saleProductDetail.put("originalSqty", sale.get("20").toString())
            saleProductDetail.put("originalFqty", sale.get("21").toString())
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
        if (response.status == 200) {
            def saleBillDetail = new JSONObject(response.readEntity(String.class))
            if (saleBillDetail) {
                try {
                    if (billStatus.equalsIgnoreCase("ACTIVE")) {
                        //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                        new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace()
                }
                JSONObject responseJson = new JSONObject()
                responseJson.put("series", series)
                responseJson.put("saleBillDetail", saleBillDetail)
                respond responseJson, formats: ['json']
            } else {
                response.status == 400
            }
        } else {
            response.status == 400
        }

    }

}
