package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
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
import phitb_ui.inventory.StockBookController
import phitb_ui.product.ProductController
import phitb_ui.system.AccountModeController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class PurchaseEntryController {

//    def index() {
//        String entityId = session.getAttribute("entityId")?.toString()
//       JSONObject pur_entry = new JSONObject()
//        ArrayList<String> series = new SeriesController().getByEntity(entityId) as ArrayList<String>
//        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
//        ArrayList<String> suppliers = new EntityRegisterController().getByAffiliates(entityId) as ArrayList<String>
//        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
//        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
//        ArrayList<String> purc_product_detail = new PurchaseEntryController().purchaseDetailShow() as ArrayList<String>
//        pur_entry.put("tempStockBook",tempStockBook)
//        pur_entry.put("purc_product_detail",purc_product_detail)
//        //ArrayList<String> salebilllist = new SalebillDetailsController().show() as ArrayList<String>
//        ArrayList<String> companies = new EntityRegisterController().show() as ArrayList<String>
//        ArrayList<String> salesmanList = []
//        ArrayList<String> companyList = []
//        users.each {
//            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
//                salesmanList.add(it)
//            }
//        }
//        companies.each {
//            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_COMPANY)) {
//                companyList.add(it)
//            }
//        }
//        def products = new ProductService().getProductsByEntityId(entityId)
//        render(view: '/purchase/purchaseEntry/purchaseEntry', model: [series      : series, accountMode: accountMode,
//                                                  users       : users, suppliers: suppliers,
//                                                  salesmanList: salesmanList,
//                                                  products    :products,companyList:companyList,
//                                                                      tempStockBook:tempStockBook,pur_entry:pur_entry])
//    }


    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def taxRegister = new TaxController().show() as ArrayList<String>
        render(view: '/purchase/purchaseEntry/purchaseEntry', model: [divisions   : divisions, customers: customers,
                                                                      priorityList: priorityList, series: series,
                                                                      taxRegister:taxRegister])
    }


    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new PurchaseService().savePurchaseDetails(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                redirect(uri: '/user-register')
                respond obj, formats: ['json'], status: 200
            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def purchaseDetailShow() {
        try {
            def apiResponse = new PurchaseService().getPurchaseProductDetails()
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

    def savePurchaseEntry() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject purchaseBillDetails = new JSONObject()
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
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentPurchaseBill = new PurchaseService().getRecentPurchaseBill(financialYear, entityId, billStatus)
            if (recentPurchaseBill != null && recentPurchaseBill.size() != 0) {
                finId = Long.parseLong(recentPurchaseBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentPurchaseBill.get("serBillId").toString()) + 1
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
        JSONArray purchaseData = new JSONArray(params.purchaseData)
        for (JSONObject purchase : purchaseData) {
            String productId = purchase.get("1")
            String batchNumber = purchase.get("2")
            String expDate = purchase.get("3")
            String saleQty = purchase.get("4")
            String freeQty = purchase.get("5")
            String purchaseRate = purchase.get("6")
            String saleRate = purchase.get("7")
            String mrp = purchase.get("8")
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
            purchaseProductDetail.put("financialYear", financialYear)
            purchaseProductDetail.put("entityId", entityId)
            purchaseProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
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
            purchaseProductDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
            purchaseProductDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
            purchaseProductDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
            purchaseProductDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            purchaseProductDetail.put("uuid", params.uuid)
            purchaseProductDetails.add(purchaseProductDetail)

            //save to sale transaction log
            //save to sale transportation details
        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        purchaseBillDetails.put("serBillId", serBillId)
        purchaseBillDetails.put("supplierId", supplierId)
        purchaseBillDetails.put("customerNumber", 0) //TODO: to be changed
        purchaseBillDetails.put("finId", finId)
        purchaseBillDetails.put("seriesId", seriesId)
        purchaseBillDetails.put("priorityId", priorityId)
        purchaseBillDetails.put("financialYear", financialYear)
        purchaseBillDetails.put("dueDate", duedate)
        purchaseBillDetails.put("paymentStatus", 0)
        purchaseBillDetails.put("dispatchStatus", 0)
        purchaseBillDetails.put("userId", session.getAttribute("userId"))
        purchaseBillDetails.put("entryDate", entryDate)
        purchaseBillDetails.put("orderDate", orderDate)
        purchaseBillDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        purchaseBillDetails.put("salesmanId", "0") //TODO: to be changed
        purchaseBillDetails.put("salesmanComm", "0") //TODO: to be changed
        purchaseBillDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        purchaseBillDetails.put("deliveryManId", "0") //TODO: to be changed
        purchaseBillDetails.put("accountModeId", "0") //TODO: to be changed
        purchaseBillDetails.put("totalSqty", totalSqty)
        purchaseBillDetails.put("totalFqty", totalFqty)
        purchaseBillDetails.put("totalGst", totalGst)
        purchaseBillDetails.put("totalSgst", totalSgst)
        purchaseBillDetails.put("totalCgst", totalCgst)
        purchaseBillDetails.put("totalIgst", totalIgst)
        purchaseBillDetails.put("totalQuantity", totalSqty + totalFqty)
        purchaseBillDetails.put("totalItems", totalSqty + totalFqty)
        purchaseBillDetails.put("totalDiscount", totalDiscount)
        purchaseBillDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        purchaseBillDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        purchaseBillDetails.put("totalAmount", totalAmount)
        purchaseBillDetails.put("godownId", 0)
        purchaseBillDetails.put("purcId", 0)
        purchaseBillDetails.put("supplierBillId", supplierBillId)
        purchaseBillDetails.put("supplierBillDate", supplierBillDate)
        purchaseBillDetails.put("billingDate", entryDate)
        purchaseBillDetails.put("balAmount", totalAmount)
        purchaseBillDetails.put("totalAmount", totalAmount)
        purchaseBillDetails.put("submitStatus", 0)//TODO: to be changed
        purchaseBillDetails.put("addAmount", 0)//TODO: to be changed
        purchaseBillDetails.put("lessAmount", 0)//TODO: to be changed
        purchaseBillDetails.put("adjustedAmount", 0)//TODO: to be changed
        purchaseBillDetails.put("entityId", entityId)
        purchaseBillDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        purchaseBillDetails.put("createdUser", session.getAttribute("userId"))
        purchaseBillDetails.put("modifiedUser", session.getAttribute("userId"))
        purchaseBillDetails.put("message", message) //TODO: to be changed
        purchaseBillDetails.put("gstStatus", "0") //TODO: to be changed
        purchaseBillDetails.put("expectedDeliveryDate", entryDate) //TODO: to be changed
        purchaseBillDetails.put("billStatus", billStatus)
        purchaseBillDetails.put("lockStatus", 0) //TODO: to be changed
        purchaseBillDetails.put("syncStatus", "0") //TODO: to be changed
        purchaseBillDetails.put("productDiscount", 0) //TODO: to be changed
        purchaseBillDetails.put("receivedDate", entryDate) //TODO: to be changed
        purchaseBillDetails.put("receivedBy", entityId) //TODO: to be changed
        purchaseBillDetails.put("creditId", 0) //TODO: to be changed
        purchaseBillDetails.put("debitId", 0) //TODO: to be changed
        purchaseBillDetails.put("crDbAmount", 0) //TODO: to be changed
        purchaseBillDetails.put("payableAmount", 0) //TODO: to be changed
        purchaseBillDetails.put("gross", 0) //TODO: to be changed
        purchaseBillDetails.put("netAmount", 0) //TODO: to be changed
        purchaseBillDetails.put("cashDiscount", "0") //TODO: to be changed
        purchaseBillDetails.put("taxable", "1") //TODO: to be changed
        purchaseBillDetails.put("cashDiscount", 0) //TODO: to be changed
        purchaseBillDetails.put("exempted", 0) //TODO: to be changed
        purchaseBillDetails.put("seriesCode", seriesCode)
        purchaseBillDetails.put("uuid", params.uuid)
        Response resp = new PurchaseService().savePurchaseBillDetails(purchaseBillDetails)
        if (resp.status == 200) {
            def purchaseBillDetail = new JSONObject(resp.readEntity(String.class))
            //save to purchase product details
            for (JSONObject purchaseProductDetail : purchaseProductDetails) {
                purchaseProductDetail.put("billId", purchaseBillDetail.get("id"))
                purchaseProductDetail.put("taxId", purchaseBillDetail.get("taxable"))
                purchaseProductDetail.put("billType", 0) //0 Sale, 1 Purchase
                purchaseProductDetail.put("serBillId", purchaseBillDetail.get("serBillId"))
                purchaseProductDetail.put("uuid", UUID.randomUUID())
                def resp1 = new PurchaseService().savePurchaseProductDetails(purchaseProductDetail)
                if (resp1.status == 200) {
                    println("Product Detail Saved")
                } else {
                    println("Product Detail Failed")
                }
            }
            //update stockbook
            for (JSONObject purchase : purchaseData) {
                UUID uuid
                //check if selected product and batch exists for the entity, if so update data, else add new
                String productId = purchase.get("1")
                String batchNumber = purchase.get("2")
                JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber, session.getAttribute("entityId").toString())
                if (stockBook) {
                    String saleQty = purchase.get("4")
                    String freeQty = purchase.get("5")
                    long sQty = Long.parseLong(stockBook.get("remainingQty").toString()) + Long.parseLong(saleQty)
                    long fQty = Long.parseLong(stockBook.get("remainingFreeQty").toString()) + Long.parseLong(freeQty)
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
                    stockBook.put("remainingQty", sQty)
                    stockBook.put("remainingFreeQty", fQty)
                    stockBook.put("remainingReplQty", 0)
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
                    stockBook.put("uuid", UUID.randomUUID())
                    new InventoryService().updateStockBook(stockBook)
                } else {
                    JSONObject jsonObject = new ProductService().getProductById(productId)
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
                    String taxId = purchase.get("19")
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
                    if(taxId!="" || taxId!=0)
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
            JSONArray schemeData = new JSONArray(params.schemeData)
            for (JSONObject scheme : schemeData) {
                def resp1 = new SalesService().saveScheme(scheme)
                if (resp1.status == 200) {
                    println("Scheme Saved")
                } else {
                    println("Scheme Failed")
                }
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("purchaseBillDetail", purchaseBillDetail)
            respond responseJson, formats: ['json']
        } else {
            response.status == 400
        }
    }

    def cancelPurchaseInvoice()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new PurchaseService().cancelPurchaseInvoice(id, entityId, financialYear)
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
            JSONObject invoice = jsonObject.get("invoice") as JSONObject
//            if(invoice.has("irnDetails")) {
//                JSONObject irnDetails = new JSONObject(invoice.get("irnDetails").toString())
//                new EInvoiceService().cancelIRN(session, irnDetails.get("Irn").toString(), invoice.get("id").toString())
//            }
            respond jsonObject, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }

    def printPurchaseEntry() {
        String purchaseBillId = params.id
        JSONObject purchaseBillDetail = new PurchaseService().getPurchaseBillDetailsById(purchaseBillId)
        JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(purchaseBillId)
        JSONObject series = new EntityService().getSeriesById(purchaseBillDetail.get("seriesId").toString())
        JSONObject supplier = new EntityService().getEntityById(purchaseBillDetail.get("supplierId").toString())
        JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
        JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
        JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
        JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
        termsConditions.each {
            JSONObject formMaster =  new SystemService().getFormById(it.formId.toString())
            if(formMaster!=null)
            {
                if(it.formId == formMaster.id)
                {
                    it.put("form", formMaster)
                }
            }
        }
        println(termsConditions)
        purchaseProductDetails.each {
            JSONObject stockBook = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(), it.batchNumber, session.getAttribute("entityId").toString())
            def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
            JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
            for (JSONObject batch : batchArray) {
                if (batch.batchNumber == it.batchNumber) {
                    it.put("batch", batch)
                }
            }
            def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
            it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            it.put("packingDesc",stockBook?.packingDesc)
        }

        def totalcgst = UtilsService.round(purchaseProductDetails.cgstAmount.sum(), 2)
        def totalsgst = UtilsService.round(purchaseProductDetails.sgstAmount.sum(), 2)
        def totaligst = UtilsService.round(purchaseProductDetails.igstAmount.sum(), 2)
        def totaldiscount = UtilsService.round(purchaseProductDetails.discount.sum(), 2)
        def totalBeforeTaxes = 0
        HashMap<String, Double> gstGroup = new HashMap<>()
        HashMap<String, Double> sgstGroup = new HashMap<>()
        HashMap<String, Double> cgstGroup = new HashMap<>()
        HashMap<String, Double> igstGroup = new HashMap<>()
        for (Object it : purchaseProductDetails) {
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
        render(view: "/purchase/purchaseEntry/purchase-invoice", model: [purchaseBillDetail    : purchaseBillDetail,
                                                                         purchaseProductDetails: purchaseProductDetails,
                                                                         series                : series, entity: entity,
                                                                         supplier              : supplier, city: city, supcity: supcity,
                                                                         total                 : total,
                                                                         totalcgst             : totalcgst, totalsgst: totalsgst,
                                                                         totaligst             : totaligst,
                                                                         totaldiscount         : totaldiscount,
                                                                         termsConditions       : termsConditions,
                                                                         gstGroup              : gstGroup,
                                                                         sgstGroup             : sgstGroup,
                                                                         cgstGroup             : cgstGroup,
                                                                         igstGroup             : igstGroup,
                                                                         totalBeforeTaxes      : totalBeforeTaxes

        ])
    }

    def purchaseReturn() {
        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
        render(view: '/purchase/purchaseRetun', model: [tempStockBook: tempStockBook])
    }

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new PurchaseService().showPurchaseBillDetails(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {
//                    JSONArray jsonArray = responseObject.data
//                    JSONArray jsonArray2 = new JSONArray()
//                    JSONArray jsonArray3 = new JSONArray()
//                    JSONArray entityArray = new JSONArray()
//                    JSONArray cityArray = new JSONArray()
//                    for (JSONObject json : jsonArray) {
//                        json.put("supplier", new EntityService().getEntityById(json.get("supplierId").toString()))
//                        jsonArray2.put(json)
//                    }
//                    for(JSONObject json1 : jsonArray2)
//                    {
//                        entityArray.put(json1.get("supplier"))
//                    }
//                    entityArray.each {
//                        def cityResp = new SystemService().getCityById(it.cityId.toString())
//                        it.put("cityId", cityResp)
//                    }
//                    responseObject.put("data", jsonArray2)
//                    responseObject.put("city",entityArray)
                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject supplier = new EntityService().getEntityById(json.get("supplierId").toString())
                        def city = new SystemService().getCityById(supplier?.cityId?.toString())
                        supplier?.put("city", city)
                        json.put("supplier", supplier)
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


    def purchasebillList()
    {
        render(view:'/purchase/purchaseEntry/purchase-invoice-list')
    }
}
