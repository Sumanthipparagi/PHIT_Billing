package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
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

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class PurchaseOrderController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def taxRegister = new TaxController().show() as ArrayList<String>
        render(view: '/purchase/purchaseOrder/purchaseOrder', model: [divisions   : divisions, customers: customers,
                                                                      priorityList: priorityList, series: series,
                                                                      taxRegister : taxRegister])
    }


    def savePurchaseOrder()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject purchaseOrderDetails = new JSONObject()
        JSONArray purchaseProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String supplierId = params.supplier
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String supplierBillId = params.supplierBillId
        String supplierBillDate = params.supplierBillDate
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
            def recentPurchaseOrder = new PurchaseService().getRecentPurchaseOrder(financialYear, entityId, billStatus)
            if (recentPurchaseOrder != null && recentPurchaseOrder.size() != 0)
            {
                finId = Long.parseLong(recentPurchaseOrder.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentPurchaseOrder.get("serBillId").toString()) + 1
            }
            else
            {
                finId = 1
                serBillId = Long.parseLong(series.get("purchaseOrderId").toString())
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
        JSONArray purchaseData = new JSONArray(params.purchaseData)
        for (JSONObject purchase : purchaseData)
        {
            String productId = purchase.get("1")
            String batchNumber = purchase.get("2")
            String expDate = purchase.get("3")
            String saleQty = purchase.get("4")
            String freeQty = purchase.get("5")
            String purchaseRate = purchase.get("6")
            String saleRate = purchase.get("7")
            String mrp = purchase.get("8")
            String taxId = purchase.get("18")
            double discount = UtilsService.round(Double.parseDouble(purchase.get("9").toString()), 2)
            String packDesc = purchase?.get("10")
            double gst = UtilsService.round(Double.parseDouble(purchase.get("12").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(purchase.get("13").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(purchase.get("14").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(purchase.get("15").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(purchase.get("16").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount
            JSONObject purchaseProductDetail = new JSONObject()
            purchaseProductDetail.put("finId", finId)
            purchaseProductDetail.put("billId", 0)
            purchaseProductDetail.put("billType", 0)
            purchaseProductDetail.put("serBillId", 0)
            purchaseProductDetail.put("seriesId", seriesId)
            purchaseProductDetail.put("productId", productId)
            purchaseProductDetail.put("batchNumber", batchNumber)
            purchaseProductDetail.put("expiryDate", expDate)
            purchaseProductDetail.put("sqty", saleQty)
            purchaseProductDetail.put("freeQty", freeQty)
            purchaseProductDetail.put("repQty", 0)
            purchaseProductDetail.put("pRate", purchaseRate)
            purchaseProductDetail.put("sRate", saleRate)
            purchaseProductDetail.put("mrp", mrp)
            purchaseProductDetail.put("discount", discount)
            purchaseProductDetail.put("gstAmount", gst)
            purchaseProductDetail.put("sgstAmount", sgst)
            purchaseProductDetail.put("cgstAmount", cgst)
            purchaseProductDetail.put("igstAmount", igst)
            purchaseProductDetail.put("gstId", 1) //TODO: to be changed
            purchaseProductDetail.put("amount", value)
            purchaseProductDetail.put("reason", "") //TODO: to be changed
            purchaseProductDetail.put("fridgeId", 0) //TODO: to be changed
            purchaseProductDetail.put("kitName", 0) //TODO: to be changed
            purchaseProductDetail.put("saleFinId", "") //TODO: to be changed
            purchaseProductDetail.put("redundantBatch", 0) //TODO: to be changed
            purchaseProductDetail.put("status", 0)
            purchaseProductDetail.put("syncStatus", 0)
            purchaseProductDetail.put("gstPercentage", purchase.get("19").toString())
            purchaseProductDetail.put("sgstPercentage", purchase.get("20").toString())
            purchaseProductDetail.put("cgstPercentage", purchase.get("21").toString())
            purchaseProductDetail.put("igstPercentage", purchase.get("22").toString())
            purchaseProductDetail.put("taxId", taxId)
            purchaseProductDetail.put("financialYear", financialYear)
            purchaseProductDetail.put("entityId", entityId)
            purchaseProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //GST percentage Calculation
            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(saleQty) * Double.parseDouble(saleRate)), 2)
            if (discount > 0)
            {
                priceBeforeTaxes = priceBeforeTaxes - (priceBeforeTaxes * (discount / 100))
            }
            double gstPercentage = 0.0
            double sgstPercentage = 0.0
            double cgstPercentage = 0.0
            double igstPercentage = 0.0

//            if (gst > 0)
//            {
//                gstPercentage = (gst / priceBeforeTaxes) * 100
//            }
//            if (sgst > 0)
//            {
//                sgstPercentage = (sgst / priceBeforeTaxes) * 100
//            }
//            if (cgst > 0)
//            {
//                cgstPercentage = (cgst / priceBeforeTaxes) * 100
//            }
//            if (igst > 0)
//            {
//                igstPercentage = (igst / priceBeforeTaxes) * 100
//            }
//            purchaseProductDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
//            purchaseProductDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
//            purchaseProductDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
//            purchaseProductDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            purchaseProductDetail.put("uuid", params.uuid)
            purchaseProductDetails.add(purchaseProductDetail)

            //save to sale transaction log
            //save to sale transportation details
        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        purchaseOrderDetails.put("serBillId", serBillId)
        purchaseOrderDetails.put("supplierId", supplierId)
        purchaseOrderDetails.put("customerNumber", 0) //TODO: to be changed
        purchaseOrderDetails.put("finId", finId)
        purchaseOrderDetails.put("seriesId", seriesId)
        purchaseOrderDetails.put("priorityId", priorityId)
        purchaseOrderDetails.put("financialYear", financialYear)
        purchaseOrderDetails.put("dueDate", duedate)
        purchaseOrderDetails.put("paymentStatus", 0)
        purchaseOrderDetails.put("dispatchStatus", 0)
        purchaseOrderDetails.put("userId", session.getAttribute("userId"))
        purchaseOrderDetails.put("entryDate", entryDate)
        purchaseOrderDetails.put("orderDate", orderDate)
        purchaseOrderDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        purchaseOrderDetails.put("salesmanId", "0") //TODO: to be changed
        purchaseOrderDetails.put("salesmanComm", "0") //TODO: to be changed
        purchaseOrderDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        purchaseOrderDetails.put("deliveryManId", "0") //TODO: to be changed
        purchaseOrderDetails.put("accountModeId", "0") //TODO: to be changed
        purchaseOrderDetails.put("totalSqty", totalSqty)
        purchaseOrderDetails.put("totalFqty", totalFqty)
        purchaseOrderDetails.put("totalGst", totalGst)
        purchaseOrderDetails.put("totalSgst", totalSgst)
        purchaseOrderDetails.put("totalCgst", totalCgst)
        purchaseOrderDetails.put("totalIgst", totalIgst)
        purchaseOrderDetails.put("totalQuantity", totalSqty + totalFqty)
        purchaseOrderDetails.put("totalItems", totalSqty + totalFqty)
        purchaseOrderDetails.put("totalDiscount", totalDiscount)
        purchaseOrderDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        purchaseOrderDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        purchaseOrderDetails.put("totalAmount", totalAmount)
        purchaseOrderDetails.put("godownId", 0)
        purchaseOrderDetails.put("purcId", 0)
        purchaseOrderDetails.put("supplierBillId", supplierBillId)
        purchaseOrderDetails.put("supplierBillDate", supplierBillDate)
        purchaseOrderDetails.put("billingDate", entryDate)
        purchaseOrderDetails.put("balAmount", totalAmount)
        purchaseOrderDetails.put("totalAmount", totalAmount)
        purchaseOrderDetails.put("submitStatus", 0)//TODO: to be changed
        purchaseOrderDetails.put("addAmount", 0)//TODO: to be changed
        purchaseOrderDetails.put("lessAmount", 0)//TODO: to be changed
        purchaseOrderDetails.put("adjustedAmount", 0)//TODO: to be changed
        purchaseOrderDetails.put("entityId", entityId)
        purchaseOrderDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        purchaseOrderDetails.put("createdUser", session.getAttribute("userId"))
        purchaseOrderDetails.put("modifiedUser", session.getAttribute("userId"))
        purchaseOrderDetails.put("message", message) //TODO: to be changed
        purchaseOrderDetails.put("gstStatus", "0") //TODO: to be changed
        purchaseOrderDetails.put("expectedDeliveryDate", entryDate) //TODO: to be changed
        purchaseOrderDetails.put("billStatus", billStatus)
        purchaseOrderDetails.put("lockStatus", 0) //TODO: to be changed
        purchaseOrderDetails.put("syncStatus", "0") //TODO: to be changed
        purchaseOrderDetails.put("productDiscount", 0) //TODO: to be changed
        purchaseOrderDetails.put("receivedDate", entryDate) //TODO: to be changed
        purchaseOrderDetails.put("receivedBy", entityId) //TODO: to be changed
        purchaseOrderDetails.put("creditId", 0) //TODO: to be changed
        purchaseOrderDetails.put("debitId", 0) //TODO: to be changed
        purchaseOrderDetails.put("crDbAmount", 0) //TODO: to be changed
        purchaseOrderDetails.put("payableAmount", 0) //TODO: to be changed
        purchaseOrderDetails.put("gross", 0) //TODO: to be changed
        purchaseOrderDetails.put("netAmount", 0) //TODO: to be changed
        purchaseOrderDetails.put("cashDiscount", "0") //TODO: to be changed
        purchaseOrderDetails.put("taxable", "1") //TODO: to be changed
        purchaseOrderDetails.put("cashDiscount", 0) //TODO: to be changed
        purchaseOrderDetails.put("exempted", 0) //TODO: to be changed
        purchaseOrderDetails.put("seriesCode", seriesCode)
        purchaseOrderDetails.put("uuid", params.uuid)
        purchaseOrderDetails.put("publicNote", params.publicNote)
        purchaseOrderDetails.put("privateNote", params.privateNote)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("purchaseOrder", purchaseOrderDetails)
        jsonObject.put("purchaseProducts", purchaseProductDetails)
        Response resp = new PurchaseService().savePurchaseOrderDetails(jsonObject)
        if (resp.status == 200)
        {
            def purchaseOrderDetail = new JSONObject(resp.readEntity(String.class))
            //update stockbook
            for (JSONObject purchase : purchaseData)
            {
                UUID uuid
                //check if selected product and batch exists for the entity, if so update data, else add new
                String productId = purchase.get("1")
                String batchNumber = purchase.get("2")
                JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber, session.getAttribute("entityId").toString())
                if (stockBook)
                {
                    long saleQty = Long.parseLong(purchase.get("4").toString())
                    long freeQty = Long.parseLong(purchase.get("5").toString())
                    long remainingQty = Long.parseLong(stockBook.get("remainingQty").toString()) + saleQty
                    long remainingFreeQty = Long.parseLong(stockBook.get("remainingFreeQty").toString()) + freeQty
                    String expDate = stockBook.get("expDate").toString().split("T")[0]
                    String purcDate = stockBook.get("purcDate").toString().split("T")[0]
                    String manufacturingDate = stockBook.get("manufacturingDate").toString().split("T")[0]
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd")
                    expDate = sdf1.parse(expDate).format("dd-MM-yyyy")
                    purcDate = sdf1.parse(purcDate).format("dd-MM-yyyy")
                    manufacturingDate = sdf1.parse(manufacturingDate).format("dd-MM-yyyy")
                    stockBook.put("packingDesc", purchase.get("10"))
                    stockBook.put("expDate", expDate)
                    stockBook.put("purcDate", purcDate)
                    stockBook.put("manufacturingDate", manufacturingDate)
                    stockBook.put("remainingQty", remainingQty)
                    stockBook.put("remainingFreeQty", remainingFreeQty)
                    stockBook.put("remainingReplQty", 0)
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
                    stockBook.put("uuid", UUID.randomUUID())
                    new InventoryService().updateStockBook(stockBook)
                }
                else
                {
//                    JSONObject jsonObject = new ProductService().getProductById(productId)
                    String value = purchase.get("12")
                    String purchaseRate = purchase.get("6")
                    String saleRate = purchase.get("7")
                    String mrp = purchase.get("8")
                    String discount = purchase.get("9")
                    String packDesc = purchase.get("10")
                    String expDate = purchase.get("3")
                    String saleQty = purchase.get("4")
                    String freeQty = purchase.get("5")
                    String manfDate = purchase.get("17")
                    String taxId = purchase.get("18")
                    String purchaseDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date())
                    expDate = new SimpleDateFormat("yyyy-MM-dd").parse(expDate).format("dd-MM-yyyy")
                    manfDate = new SimpleDateFormat("yyyy-MM-dd").parse(manfDate).format("dd-MM-yyyy")
                    stockBook = new JSONObject()
                    stockBook.put("productId", productId)
                    stockBook.put("batchNumber", batchNumber)
                    stockBook.put("expDate", expDate)
                    stockBook.put("purcDate", purchaseDate)
                    stockBook.put("supplierId", supplierId)
                    stockBook.put("entityTypeId", session.getAttribute("entityTypeId"))
                    stockBook.put("entityId", session.getAttribute("entityId"))
                    stockBook.put("createdUser", session.getAttribute("userId"))
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
                    stockBook.put("status", "1")
                    stockBook.put("syncStatus", "1")
                    stockBook.put("mergedWith", "1")
                    stockBook.put("purcSeriesId", seriesId)
                    stockBook.put("saleRate", saleRate)
                    stockBook.put("purchaseRate", purchaseRate)
                    stockBook.put("mrp", mrp)
                    stockBook.put("purcTradeDiscount", discount)
                    stockBook.put("packingDesc", packDesc)
                    stockBook.put("purcProductValue", value)
                    stockBook.put("remainingQty", saleQty)
                    stockBook.put("remainingFreeQty", freeQty)
                    stockBook.put("remainingReplQty", 0)
                    if (taxId != "" || taxId != 0)
                    {
                        stockBook.put("taxId", taxId) //TODO: to be set from front end
                    }
                    else
                    {
                        stockBook.put("taxId", 0)
                    }
                    stockBook.put("manufacturingDate", manfDate)
                    stockBook.put("openingStockQty", saleQty) //opening stock is same as sale while adding
                    stockBook.put("uuid", UUID.randomUUID())
                    new InventoryService().stockBookSave(stockBook)
                }
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("purchaseOrderDetail", purchaseOrderDetail)
            respond responseJson, formats: ['json']
        }
        else
        {
            response.status == 400
        }
    }


    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new PurchaseService().showPurchaseOrderDetails(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {

                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("supplierId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
                        customer?.put("city", city)
                        json.put("supplier", customer)
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

    def purchaseOrderList()
    {
        render(view:'/purchase/purchaseOrder/purchase-order-list')
    }

    def printPurchaseOrder() {
        String purchaseBillId = params.id
        JSONObject purchaseOrderDetail = new PurchaseService().getPurchaseOrderDetailsById(purchaseBillId)
        def checkUser = new EntityService().billDetailsCheckUserType(session.getAttribute('userId').toString())
        if(purchaseOrderDetail.entityId == session.getAttribute('entityId'))
        {
            JSONArray purchaseOrderProductDetails = new PurchaseService().getPurchaseProductDetailsByOrder(purchaseBillId)
            JSONObject series = new EntityService().getSeriesById(purchaseOrderDetail.get("seriesId").toString())
            JSONObject supplier = new EntityService().getEntityById(purchaseOrderDetail.get("supplierId").toString())
            JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
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
            println(termsConditions)
            purchaseOrderProductDetails.each {
                JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(), it.batchNumber, session.getAttribute("entityId").toString())
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
                it.put("packingDesc", stockBook?.packingDesc)
            }
            def totalcgst = UtilsService.round(purchaseOrderProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(purchaseOrderProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(purchaseOrderProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(purchaseOrderProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : purchaseOrderProductDetails)
            {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalBeforeTaxes += amountBeforeTaxes
                if (it.igstPercentage > 0)
                {
                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
                    if (igstPercentage == null)
                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                    else
                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                }
                else
                {
                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
                    if (gstPercentage == null)
                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
                    else
                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)

                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
                    if (sgstPercentage == null)
                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
                    else
                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
                    if (cgstPercentage == null)
                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
                    else
                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                }
            }

            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst
            render(view: "/purchase/purchaseOrder/purchase-order-print", model: [purchaseOrderDetail        : purchaseOrderDetail,
                                                                                 purchaseOrderProductDetails: purchaseOrderProductDetails,
                                                                                 series                     : series, entity: entity,
                                                                                 supplier                   : supplier, city: city, supcity: supcity,
                                                                                 total                      : total,
                                                                                 totalcgst                  : totalcgst, totalsgst: totalsgst,
                                                                                 totaligst                  : totaligst,
                                                                                 totaldiscount              : totaldiscount,
                                                                                 termsConditions            : termsConditions,
                                                                                 gstGroup                   : gstGroup,
                                                                                 sgstGroup                  : sgstGroup,
                                                                                 cgstGroup                  : cgstGroup,
                                                                                 igstGroup                  : igstGroup,
                                                                                 totalBeforeTaxes           : totalBeforeTaxes

            ])
        }else if(checkUser){
            JSONArray purchaseOrderProductDetails = new PurchaseService().getPurchaseProductDetailsByOrder(purchaseBillId)
            JSONObject series = new EntityService().getSeriesById(purchaseOrderDetail.get("seriesId").toString())
            JSONObject supplier = new EntityService().getEntityById(purchaseOrderDetail.get("supplierId").toString())
            JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
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
            println(termsConditions)
            purchaseOrderProductDetails.each {
                JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(), it.batchNumber, session.getAttribute("entityId").toString())
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
                it.put("packingDesc", stockBook?.packingDesc)
            }
            def totalcgst = UtilsService.round(purchaseOrderProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(purchaseOrderProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(purchaseOrderProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(purchaseOrderProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : purchaseOrderProductDetails)
            {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalBeforeTaxes += amountBeforeTaxes
                if (it.igstPercentage > 0)
                {
                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
                    if (igstPercentage == null)
                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                    else
                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                }
                else
                {
                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
                    if (gstPercentage == null)
                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
                    else
                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)

                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
                    if (sgstPercentage == null)
                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
                    else
                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
                    if (cgstPercentage == null)
                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
                    else
                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                }
            }

            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst
            render(view: "/purchase/purchaseOrder/purchase-order-print", model: [purchaseOrderDetail        : purchaseOrderDetail,
                                                                                 purchaseOrderProductDetails: purchaseOrderProductDetails,
                                                                                 series                     : series, entity: entity,
                                                                                 supplier                   : supplier, city: city, supcity: supcity,
                                                                                 total                      : total,
                                                                                 totalcgst                  : totalcgst, totalsgst: totalsgst,
                                                                                 totaligst                  : totaligst,
                                                                                 totaldiscount              : totaldiscount,
                                                                                 termsConditions            : termsConditions,
                                                                                 gstGroup                   : gstGroup,
                                                                                 sgstGroup                  : sgstGroup,
                                                                                 cgstGroup                  : cgstGroup,
                                                                                 igstGroup                  : igstGroup,
                                                                                 totalBeforeTaxes           : totalBeforeTaxes

            ])
        }else{

            render("No invoice found")
        }
    }


    def cancelPurchaseOrder()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new PurchaseService().cancelPurchaseOrder(id, entityId, financialYear)
        if (jsonObject)
        {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails)
            {
                for (JSONObject productDetail : productDetails)
                {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())
                    double remainingQty = stockBook.get("remainingQty") - productDetail.get("sqty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty") - productDetail.get("freeQty")
                    double remainingReplQty = stockBook.get("remainingReplQty") - productDetail.get("repQty")
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

}
