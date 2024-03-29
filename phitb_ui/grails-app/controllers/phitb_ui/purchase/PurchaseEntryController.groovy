package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONException
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.ShipmentService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.inventory.StockBookController
import phitb_ui.product.DivisionController
import phitb_ui.product.ProductCategoryController
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
        if (session.getAttribute("financialYearValid")) {
            String entityId = session.getAttribute("entityId")?.toString()
            JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
            /*ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>*/
            def priorityList = new SystemService().getPriorityByEntity(entityId)
            def series = new SeriesController().getByEntity(entityId)
            def taxRegister = new EntityService().getTaxesByEntity(entityId)
            Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
            /*ArrayList<String> productlist = new ProductService().getProductByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>*/
            ArrayList<String> productcatList = new ProductCategoryController().getByEntity() as ArrayList<String>

            render(view: '/purchase/purchaseEntry/purchaseEntry', model: [divisions                                   : divisions, /*customers: customers,*/
                                                                          priorityList                                : priorityList, series: series,
                                                                          taxRegister                                 : taxRegister, transporter: transporter,
                                                                          /* productlist:productlist,*/ productcatList: productcatList
            ])
        }
        else
        {
            redirect(uri:"/dashboard")
        }
    }


    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new PurchaseService().savePurchaseBillDetails(jsonObject)
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

        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd")
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
                def recentPurchaseBill = new PurchaseService().getRecentPurchaseBill(financialYear, entityId, billStatus)
                if (recentPurchaseBill != null && recentPurchaseBill.size() != 0)
                {
                    finId = Long.parseLong(recentPurchaseBill.get("finId").toString()) + 1
                    serBillId = Long.parseLong(recentPurchaseBill.get("serBillId").toString()) + 1
                }
                else
                {
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
                purchaseProductDetail.put("expiryDate", sdf2.parse(expDate))
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
                purchaseProductDetail.put("gstPercentage", purchase.get("20").toString())
                purchaseProductDetail.put("sgstPercentage", purchase.get("21").toString())
                purchaseProductDetail.put("cgstPercentage", purchase.get("22").toString())
                purchaseProductDetail.put("igstPercentage", purchase.get("23").toString())
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
//                gstPercentage = (gst / priceBeforeTaxes) * 100
//            if (sgst > 0)
//                sgstPercentage = (sgst / priceBeforeTaxes) * 100
//            if (cgst > 0)
//                cgstPercentage = (cgst / priceBeforeTaxes) * 100
//            if (igst > 0)
//                igstPercentage = (igst / priceBeforeTaxes) * 100
//            purchaseProductDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
//            purchaseProductDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
//            purchaseProductDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
//            purchaseProductDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
//            purchaseProductDetail.put("uuid", params.uuid)
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
            purchaseBillDetails.put("refOrderId", "") //TODO: to be changed this is for purchase order conversion
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
            if (resp.status == 200)
            {
                def purchaseBillDetail = new JSONObject(resp.readEntity(String.class))
                //save to purchase product details
                for (JSONObject purchaseProductDetail : purchaseProductDetails)
                {
                    purchaseProductDetail.put("billId", purchaseBillDetail.get("id"))
                    purchaseProductDetail.put("taxId", purchaseBillDetail.get("taxable"))
                    purchaseProductDetail.put("billType", 0) //0 Sale, 1 Purchase
                    purchaseProductDetail.put("serBillId", purchaseBillDetail.get("serBillId"))
                    purchaseProductDetail.put("uuid", UUID.randomUUID())
                    def resp1 = new PurchaseService().savePurchaseProductDetails(purchaseProductDetail)
                    if (resp1.status == 200)
                    {
                        println("Product Detail Saved")
                    }
                    else
                    {
                        println("Product Detail Failed")
                    }
                }
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
                    }
                    else
                    {
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
                        if (taxId == "" || taxId == 0 || taxId == "0")
                        {
                            def tax = new EntityService().getTaxRegisterByValueAndEntity(purchase.get("20").toString(), entityId)
                            stockBook.put("taxId", tax.id)
                        }else{
                            stockBook.put("taxId", taxId)
                        }
                        stockBook.put("manufacturingDate", manfDate)
                        stockBook.put("openingStockQty", "0") //opening stock should be zero, as we are adding new stock via purchase entry
                        stockBook.put("uuid", UUID.randomUUID())
                        new InventoryService().stockBookSave(stockBook)
                    }
                }
                JSONArray schemeData = new JSONArray(params.schemeData)
                for (JSONObject scheme : schemeData)
                {
                    def resp1 = new SalesService().saveScheme(scheme)
                    if (resp1.status == 200)
                    {
                        println("Scheme Saved")
                    }
                    else
                    {
                        println("Scheme Failed")
                    }
                }
                //   Update shipment Details
                if (params.lrNumber != '' && params.lrDate != '' && params.transporter != '')
                {
                    JSONObject transportObject = new JSONObject();
                    transportObject.put("finId", finId)
                    transportObject.put("billId", purchaseBillDetail.id)
                    transportObject.put("billType", Constants.PURCHASE_INVOICE)
                    transportObject.put("serBillId", purchaseBillDetail.serBillId)
                    transportObject.put("series", purchaseBillDetail.seriesId)
                    transportObject.put("supplierId", purchaseBillDetail.supplierId)
                    transportObject.put("transporterId", params.transporter)
                    transportObject.put("lrDate", params.lrDate)
                    transportObject.put("lrNumber", params.lrNumber)
                    transportObject.put("cartonsCount", "")
                    transportObject.put("paid", 0)
                    transportObject.put("toPay", 0)
                    transportObject.put("generalInfo", 0)
                    transportObject.put("selfNo", 0)
                    transportObject.put("ccm", 0)
                    transportObject.put("receivedTemperature", 0)
                    transportObject.put("freightCharge", 0)
                    transportObject.put("vehicleId", 0)
                    transportObject.put("deliveryStatus", 0)
                    transportObject.put("dispatchDateTime", 0)
                    transportObject.put("deliveryDateTime", 0)
                    transportObject.put("trackingDetails", 0)
                    transportObject.put("ewaybillId", 0)
                    transportObject.put("genralInfo", 0)
                    transportObject.put("weight", 0)
                    transportObject.put("ewaysupplytype", 0)
                    transportObject.put("ewaysupplysubtype", 0)
                    transportObject.put("ewaydoctype", 0)
                    transportObject.put("consignmentNo", 0)
                    transportObject.put("syncStatus", 0)
                    transportObject.put("financialYear", 0)
                    transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                    transportObject.put("entityId", session.getAttribute('entityId'))
                    transportObject.put("createdUser", session.getAttribute('userId'))
                    transportObject.put("modifiedUser", session.getAttribute('userId'))
                    Response transportation = new PurchaseService().savePurchaseTransportation(transportObject)
                    if (transportation?.status == 200)
                    {
                        println("Transportation details added")
                    }
                    else
                    {
                        println("Failed to add transportation details")
                    }
                }
                else
                {
                    println("Transportation Details not found!")
                }
                JSONObject responseJson = new JSONObject()
                responseJson.put("series", series)
                responseJson.put("purchaseBillDetail", purchaseBillDetail)
                respond responseJson, formats: ['json']
            }
            else
            {
                response.status == 400
            }
        }catch(Exception e){
            log.error(controllerName+" "+e)
            print(controllerName+" "+e)
        }
    }

    def cancelPurchaseInvoice()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String userId = session.getAttribute("userId")
        def purchaseProductDetailsByBill = new PurchaseService().getPurchaseProductDetailsByBill(id)
        /*JSONObject jsonObject1 = new JSONObject()
        for(JSONObject purchaseProduct:purchaseProductDetailsByBill){
            def product = new ProductService().getProductById(purchaseProduct.productId.toString())
            purchaseProduct.put("product",product)
            def stocks = new InventoryService().getStocksOfProductAndBatch(purchaseProduct.productId.toString(), purchaseProduct.batchNumber,purchaseProduct.entityId.toString())
            if(stocks.remainingQty < purchaseProduct.sqty || stocks.remainingFreeQty < purchaseProduct.freeQty){
                jsonObject1.put("purchaseProduct",purchaseProduct)
            }
        }
        if(jsonObject1.size()!=0){
            respond jsonObject1, formats:['json'], status: 201;
            return
        }*/
        JSONObject jsonObject = new PurchaseService().cancelPurchaseInvoice(id, entityId, financialYear, userId)
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
            respond jsonObject, formats: ['json'], status: 200
        }
        else
        {
            response.status = 400
        }
    }


    def printPurchaseEntry()
    {
        String purchaseBillId = params.id
        JSONObject purchaseBillDetail = new PurchaseService().getPurchaseBillDetailsById(purchaseBillId)
        def checkUser = new EntityService().billDetailsCheckUserType(session.getAttribute('userId').toString())
        def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
        if(purchaseBillDetail.entityId == session.getAttribute('entityId'))
        {
            if (purchaseBillDetail != null)
            {
                JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(purchaseBillId)
                /* JSONObject transportDetails = new PurchaseService().getPurchaseTransportationByBill(purchaseReturnId,Constants.PURCHASE_RETURN)
             if (transportDetails != null)
             {
                 JSONObject transporter = new ShipmentService().getTransporterbyId(transportDetails?.transporterId?.toString());
                 if (transporter != null)
                 {
                     transportDetails.put("transporter", transporter)
                 }
             }*/
                JSONObject series = new EntityService().getSeriesById(purchaseBillDetail.get("seriesId").toString())
                JSONObject supplier = new EntityService().getEntityById(purchaseBillDetail.get("supplierId").toString())
                println("Entity ID is: " + session.getAttribute("entityId").toString())
                JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
                JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
                if (entity == null)
                {
                    println("Entity is null")
                }
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
//            println(termsConditions)
                JSONObject groupDetails = new JSONObject()
                JSONArray productDetail = new JSONArray()
                purchaseProductDetails.each {
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
                        if (groupDetails.containsKey(it?.gstPercentage))
                        {
                            productDetail = groupDetails.get(it?.gstPercentage) as JSONArray
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
                            groupDetails.put(it?.gstPercentage, sortedJsonArray)
                        }
                        else
                        {
                            productDetail = new JSONArray()
                            productDetail.add(it)
                            println(productDetail)
                            groupDetails.put(it?.gstPercentage, productDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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
                            divisionDetail.put("sortItem", divisionDetail.get("divisionName"))
                            divisionDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            divisionDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            divisionDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", divisionDetail)
                        }
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                {
                    for (Object tax : groupDetails.keySet())
                    {
                        Long taxValue = (Long) Double.parseDouble(tax.toString())
                        def taxDetail = new EntityService().getTaxRegisterByValueAndEntity(taxValue.toString(), session.getAttribute('entityId').toString())
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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

                            taxDetail.put("sortItem", taxDetail.get("taxName") + " (" + taxDetail.get("taxValue") + "%)")
//                            taxDetail.remove("divisionName");
                            taxDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            taxDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            taxDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", taxDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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

                            productGrpDetail.put("sortItem", productGrpDetail.get("groupName"))
//                            taxDetail.remove("divisionName");
                            productGrpDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            productGrpDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            productGrpDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", productGrpDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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


                def totalcgst = UtilsService.round(purchaseProductDetails.cgstAmount.sum(), 2)
                def totalsgst = UtilsService.round(purchaseProductDetails.sgstAmount.sum(), 2)
                def totaligst = UtilsService.round(purchaseProductDetails.igstAmount.sum(), 2)
                def totaldiscount = UtilsService.round(purchaseProductDetails.discount.sum(), 2)
                def totalDiscAmt = 0
                def totalBeforeTaxes = 0
                HashMap<String, Double> gstGroup = new HashMap<>()
                HashMap<String, Double> sgstGroup = new HashMap<>()
                HashMap<String, Double> cgstGroup = new HashMap<>()
                HashMap<String, Double> igstGroup = new HashMap<>()
                for (Object it : purchaseProductDetails)
                {
                    double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                    totalDiscAmt += amountBeforeTaxes / 100 * it.discount
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
                                                                                 totalBeforeTaxes      : totalBeforeTaxes,
                                                                                 groupDetails          : groupDetails,
                                                                                 settings              : settings, totalDiscAmt: totalDiscAmt

                ])
            }
            else
            {

                render("No Bill Found")
            }
        }else if(checkUser){
            if (purchaseBillDetail != null)
            {
                JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(purchaseBillId)
                /* JSONObject transportDetails = new PurchaseService().getPurchaseTransportationByBill(purchaseReturnId,Constants.PURCHASE_RETURN)
             if (transportDetails != null)
             {
                 JSONObject transporter = new ShipmentService().getTransporterbyId(transportDetails?.transporterId?.toString());
                 if (transporter != null)
                 {
                     transportDetails.put("transporter", transporter)
                 }
             }*/
                JSONObject series = new EntityService().getSeriesById(purchaseBillDetail.get("seriesId").toString())
                JSONObject supplier = new EntityService().getEntityById(purchaseBillDetail.get("supplierId").toString())
                println("Entity ID is: " + session.getAttribute("entityId").toString())
                JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
                JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
                if (entity == null)
                {
                    println("Entity is null")
                }
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
//            println(termsConditions)
                JSONObject groupDetails = new JSONObject()
                JSONArray productDetail = new JSONArray()
                purchaseProductDetails.each {
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
                        if (groupDetails.containsKey(it?.gstPercentage))
                        {
                            productDetail = groupDetails.get(it?.gstPercentage) as JSONArray
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
                            groupDetails.put(it?.gstPercentage, sortedJsonArray)
                        }
                        else
                        {
                            productDetail = new JSONArray()
                            productDetail.add(it)
                            println(productDetail)
                            groupDetails.put(it?.gstPercentage, productDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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
                            divisionDetail.put("sortItem", divisionDetail.get("divisionName"))
                            divisionDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            divisionDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            divisionDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", divisionDetail)
                        }
                    }
                }
                else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                {
                    for (Object tax : groupDetails.keySet())
                    {
                        Long taxValue = (Long) Double.parseDouble(tax.toString())
                        def taxDetail = new EntityService().getTaxRegisterByValueAndEntity(taxValue.toString(), session.getAttribute('entityId').toString())
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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

                            taxDetail.put("sortItem", taxDetail.get("taxName") + " (" + taxDetail.get("taxValue") + "%)")
//                            taxDetail.remove("divisionName");
                            taxDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            taxDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            taxDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", taxDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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

                            productGrpDetail.put("sortItem", productGrpDetail.get("groupName"))
//                            taxDetail.remove("divisionName");
                            productGrpDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                            productGrpDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                            productGrpDetail.put("amountAfterTaxes", amountAfterTaxes)
                            prodDetail.put("sortDetail", productGrpDetail)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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
                                def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                if (igstPercentage == null)
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                }
                                else
                                {
                                    divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
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


                def totalcgst = UtilsService.round(purchaseProductDetails.cgstAmount.sum(), 2)
                def totalsgst = UtilsService.round(purchaseProductDetails.sgstAmount.sum(), 2)
                def totaligst = UtilsService.round(purchaseProductDetails.igstAmount.sum(), 2)
                def totaldiscount = UtilsService.round(purchaseProductDetails.discount.sum(), 2)
                def totalDiscAmt = 0
                def totalBeforeTaxes = 0
                HashMap<String, Double> gstGroup = new HashMap<>()
                HashMap<String, Double> sgstGroup = new HashMap<>()
                HashMap<String, Double> cgstGroup = new HashMap<>()
                HashMap<String, Double> igstGroup = new HashMap<>()
                for (Object it : purchaseProductDetails)
                {
                    double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                    totalDiscAmt += amountBeforeTaxes / 100 * it.discount
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
                                                                                 totalBeforeTaxes      : totalBeforeTaxes,
                                                                                 groupDetails          : groupDetails,
                                                                                 settings              : settings, totalDiscAmt: totalDiscAmt

                ])
            }
            else
            {

                render("No Bill Found")
            }
        }else{
            render("No invoice  found")
        }
    }

    def purchaseReturn() {
        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
        render(view: '/purchase/purchaseRetun', model: [tempStockBook: tempStockBook])
    }

    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            if(!session.getAttribute("role").toString().equalsIgnoreCase(Constants.ENTITY_ADMIN))
                jsonObject.put("userId", session.getAttribute("userId"))
            jsonObject.put("entityId", session.getAttribute("entityId"))
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


    def editPurchaseBillDetails() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
       /* ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>*/
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def purchaseBillId = params.purchaseBillId
        JSONObject purchaseBillDetail = new PurchaseService().getPurchaseBillDetailsById(purchaseBillId)
        JSONObject purchaseTransportDetail = new PurchaseService().getPurchaseTransportationByBill(purchaseBillId,
                Constants.PURCHASE_INVOICE)
        Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
        JSONObject supplier = new EntityService().getEntityById(purchaseBillDetail.supplierId.toString())
        if (purchaseBillDetail != null && purchaseBillDetail.billStatus == 'DRAFT') {
            JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(purchaseBillId)
            render(view: '/purchase/purchaseEntry/purchaseEntry', model: [/*customers             : customers, */divisions: divisions,
                                                                          series                : series,
                                                                          priorityList          : priorityList, purchaseBillDetail: purchaseBillDetail,
                                                                          purchaseProductDetails: purchaseProductDetails,
                                                                          transporter           :transporter,
                                                                          supplier              : supplier, purchaseTransportDetail:purchaseTransportDetail])
        } else {
            redirect(uri: "/purchase-entry")
        }

    }


    def getPurchaseProductDetailsByBill() {
        try {
            if (params.id) {
                JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(params.id)
                def saleBillResponse = new PurchaseService().getPurchaseBillDetailsById(params.id.toString())
                purchaseProductDetails.each {
                    println(it.batchNumber)
                    def stockResponse = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(),
                            it.batchNumber.toString(), session.getAttribute('entityId').toString())
                    if (it.batchNumber == stockResponse.batchNumber) {
                        def tax = new TaxController().show(stockResponse.taxId.toString())
                        it.put("gst", tax.taxValue)
                        it.put("sgst", tax.purchaseSgst)
                        it.put("cgst", tax.purchaseCgst)
                        it.put("igst", tax.purchaseIgst)
                        it.put("taxId", tax.id)
                        it.put("manufacturingDate",stockResponse?.manufacturingDate)
                    }
                    it.put("billId", saleBillResponse as JSONObject)
                    def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                    it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
                }
                respond purchaseProductDetails, formats: ['json'], status: 200;
            } else {
                return [];
            }
        }

        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }


    def deletePurchaseProduct() {
        try {
            def id = params.id
            def productDetail = new PurchaseService().getPurchaseProductDetailsById(id)
            if (productDetail) {
                def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())

                double remainingQty = stockBook.get("remainingQty")
                double remainingFreeQty = stockBook.get("remainingFreeQty")

                //checking to where the stocks to be returned
//                double originalSqty = productDetail.get("originalSqty")
//                double originalFqty = productDetail.get("originalFqty")
                double sqty = productDetail.get("sqty")
                double freeQty = productDetail.get("freeQty")

//                if ((originalSqty + originalFqty) == (sqty + freeQty)  && originalSqty == sqty && originalFqty == freeQty) {
//                    remainingQty += sqty
//                    remainingFreeQty += freeQty
//                } else {
//                    if (originalSqty >= sqty && originalFqty >= freeQty) {
//                        remainingQty += sqty
//                        remainingFreeQty += freeQty
//                    } else {
//                        if (sqty > originalSqty) {
//                            remainingQty = sqty - (sqty - originalSqty)
//                            remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
//                        } else if (freeQty > originalFqty) {
//                            remainingQty = remainingQty + sqty + (freeQty - originalFqty)
//                            remainingFreeQty = freeQty - (freeQty - originalFqty)
//                        }
//                    }
//                }

//                remainingQty - sqty
//                remainingFreeQty - freeQty
                stockBook.put("remainingQty", remainingQty.toLong() - sqty.toLong())
                stockBook.put("remainingFreeQty", remainingFreeQty.toLong() - freeQty.toLong())
                stockBook.put("remainingReplQty", 0) //TODO: to be checked
                new InventoryService().updateStockBook(stockBook)
            }
            def apiResponse = new PurchaseService().deletePurchaseProduct(id);
            respond(text: id, status: apiResponse.status)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }


    def updatePurchaseBillDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd")
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
            String purProductId =""
            if (purchase.has("24")) //get saved draft product id
                purProductId = purchase.get("24")
            else
                purProductId = "0"
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
            purchaseProductDetail.put("id", purProductId)
            purchaseProductDetail.put("billId", 0)
            purchaseProductDetail.put("billType", 0)
            purchaseProductDetail.put("serBillId", 0)
            purchaseProductDetail.put("seriesId", seriesId)
            purchaseProductDetail.put("productId", productId)
            purchaseProductDetail.put("batchNumber", batchNumber)
            purchaseProductDetail.put("expiryDate", sdf2.parse(expDate))
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
            purchaseProductDetail.put("taxId", purchase.get('19'))
            purchaseProductDetail.put("syncStatus", 0)
            purchaseProductDetail.put("gstPercentage", purchase.get("20").toString())
            purchaseProductDetail.put("sgstPercentage", purchase.get("21").toString())
            purchaseProductDetail.put("cgstPercentage", purchase.get("22").toString())
            purchaseProductDetail.put("igstPercentage", purchase.get("23").toString())
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

//            if (gst > 0)
//                gstPercentage = (gst / priceBeforeTaxes) * 100
//            if (sgst > 0)
//                sgstPercentage = (sgst / priceBeforeTaxes) * 100
//            if (cgst > 0)
//                cgstPercentage = (cgst / priceBeforeTaxes) * 100
//            if (igst > 0)
//                igstPercentage = (igst / priceBeforeTaxes) * 100
//            purchaseProductDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
//            purchaseProductDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
//            purchaseProductDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
//            purchaseProductDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
//            purchaseProductDetail.put("uuid", params.uuid)
            purchaseProductDetails.add(purchaseProductDetail)

            //save to sale transaction log
            //save to sale transportation details
        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        purchaseBillDetails.put("serBillId", serBillId)
        purchaseBillDetails.put("id", params.id)
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
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("purchaseInvoice", purchaseBillDetails)
        jsonObject.put("purchaseProducts", purchaseProductDetails)
        Response resp = new PurchaseService().updatePurchaseInvoice(jsonObject,purchaseBillDetails.get("id").toString())
        if (resp.status == 200) {
            def purchaseBillDetail = new JSONObject(resp.readEntity(String.class))
            //save to purchase product details
//            for (JSONObject purchaseProductDetail : purchaseProductDetails) {
//                purchaseProductDetail.put("billId", purchaseBillDetail.get("id"))
//                purchaseProductDetail.put("taxId", purchaseBillDetail.get("taxable"))
//                purchaseProductDetail.put("billType", 0) //0 Sale, 1 Purchase
//                purchaseProductDetail.put("serBillId", purchaseBillDetail.get("serBillId"))
//                purchaseProductDetail.put("uuid", UUID.randomUUID())
//                def resp1 = new PurchaseService().savePurchaseProductDetails(purchaseProductDetail)
//                if (resp1.status == 200) {
//                    println("Product Detail Saved")
//                } else {
//                    println("Product Detail Failed")
//                }
//            }


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
                    JSONObject jsonObject1 = new ProductService().getProductById(productId)
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
                    if (taxId == "" || taxId == 0 || taxId == "0")
                    {
                        def tax = new EntityService().getTaxRegisterByValueAndEntity(purchase.get("20").toString(), entityId)
                        stockBook.put("taxId", tax.id)
                    }else{
                        stockBook.put("taxId", taxId)
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
            //   Update shipment Details
            if(params.lrNumber!='' && params.lrDate!='' && params.transporter!='')
            {
                JSONObject transportObject = new JSONObject();
                transportObject.put("finId", finId)
                transportObject.put("billId", purchaseBillDetail.id)
                transportObject.put("billType", Constants.PURCHASE_INVOICE)
                transportObject.put("serBillId", purchaseBillDetail.serBillId)
                transportObject.put("series", purchaseBillDetail.seriesId)
                transportObject.put("supplierId", purchaseBillDetail.supplierId)
                transportObject.put("transporterId", params.transporter)
                transportObject.put("lrDate", params.lrDate)
                transportObject.put("lrNumber", params.lrNumber)
                transportObject.put("cartonsCount", "")
                transportObject.put("paid", 0)
                transportObject.put("toPay", 0)
                transportObject.put("generalInfo", 0)
                transportObject.put("selfNo", 0)
                transportObject.put("ccm", 0)
                transportObject.put("receivedTemperature", 0)
                transportObject.put("freightCharge", 0)
                transportObject.put("vehicleId", 0)
                transportObject.put("deliveryStatus", 0)
                transportObject.put("dispatchDateTime", 0)
                transportObject.put("deliveryDateTime", 0)
                transportObject.put("trackingDetails", 0)
                transportObject.put("ewaybillId", 0)
                transportObject.put("genralInfo", 0)
                transportObject.put("weight", 0)
                transportObject.put("ewaysupplytype", 0)
                transportObject.put("ewaysupplysubtype", 0)
                transportObject.put("ewaydoctype", 0)
                transportObject.put("consignmentNo", 0)
                transportObject.put("syncStatus", 0)
                transportObject.put("financialYear", 0)
                transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                transportObject.put("entityId", session.getAttribute('entityId'))
                transportObject.put("createdUser", session.getAttribute('userId'))
                transportObject.put("modifiedUser", session.getAttribute('userId'))
                Response transportation = new PurchaseService().savePurchaseTransportation(transportObject)
                if (transportation?.status == 200)
                {
                    println("Transportation details added")
                }
                else
                {
                    println("Failed to add transportation details")
                }
            }else {
                println("Transportation Details not found!")
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("purchaseBillDetail", purchaseBillDetail)
            respond responseJson, formats: ['json']
        } else {
            response.status == 400
        }
    }
}
