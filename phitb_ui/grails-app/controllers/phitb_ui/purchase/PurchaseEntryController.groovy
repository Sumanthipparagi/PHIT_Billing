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
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.inventory.StockBookController
import phitb_ui.product.ProductController
import phitb_ui.system.AccountModeController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class PurchaseEntryController
{

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


    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        render(view: '/purchase/purchaseEntry/purchaseEntry', model: [divisions   : divisions, customers: customers,
                                                                      priorityList: priorityList, series: series])
    }


    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new PurchaseService().savePurchaseDetails(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                redirect(uri: '/user-register')
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def purchaseDetailShow()
    {
        try
        {
            def apiResponse = new PurchaseService().getPurchaseProductDetails()
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

    def savePurchaseEntry()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject purchaseBillDetails = new JSONObject()
        JSONArray purchaseProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String supplierId = params.supplier
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
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
            if (recentPurchaseBill != null)
            {
                finId = Long.parseLong(recentPurchaseBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentPurchaseBill.get("serBillId").toString()) + 1
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
            String discount = purchase.get("9")
            String packDesc = purchase.get("10")
            String gst = purchase.get("11")
            String value = purchase.get("12")
            String sgst = purchase.get("13")
            String cgst = purchase.get("14")
            String igst = purchase.get("15")
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble(discount)

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
        purchaseBillDetails.put("supplierBillId", 0)
        purchaseBillDetails.put("billingDate", entryDate)
        purchaseBillDetails.put("balAmount", totalAmount)
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
                    stockBook.put("expDate", expDate)
                    stockBook.put("purcDate", purcDate)
                    stockBook.put("manufacturingDate", manufacturingDate)
                    stockBook.put("remainingQty", sQty)
                    stockBook.put("remainingFreeQty", fQty)
                    stockBook.put("remainingReplQty", 0)
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
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
                    String manfDate = purchase.get("16")
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
                    stockBook.put("taxId", jsonObject.get("taxId"))
                    stockBook.put("manufacturingDate", manfDate)
                    new InventoryService().stockBookSave(stockBook)
                }
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
    }


    def printPurchaseEntry()
    {
        String purchaseBillId = params.id
        JSONObject purchaseBillDetail = new PurchaseService().getPurchaseBillDetailsById(purchaseBillId)
        JSONArray purchaseProductDetails = new PurchaseService().getPurchaseProductDetailsByBill(purchaseBillId)
        JSONObject series = new EntityService().getSeriesById(purchaseBillDetail.get("seriesId").toString())
        JSONObject supplier = new EntityService().getEntityById(purchaseBillDetail.get("supplierId").toString())
        JSONObject supcity = new SystemService().getCityById(supplier.get('cityId').toString())
        JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
        JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
        JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
        purchaseProductDetails.each {
            def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
            it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
        }
        def invoiceNumber;
        def datepart = purchaseBillDetail.dateCreated.split("T")[0];
        def month = datepart.split("-")[1];
        def year = datepart.split("-")[0];
        def seriesCode = "__";
        if (purchaseBillDetail.billStatus == "DRAFT")
        {
            invoiceNumber = purchaseBillDetail.entityId + "/DR/S/" + month + year + "/" + series.seriesCode + "/__";
        }
        else
        {
            invoiceNumber = purchaseBillDetail.entityId + "/S/" + month + year + "/" + series.seriesCode + "/" + purchaseBillDetail.id
        }
        def totalcgst = purchaseProductDetails.cgstAmount.sum()
        def totalsgst = purchaseProductDetails.sgstAmount.sum()
        def totaligst = purchaseProductDetails.igstAmount.sum()
        def totaldiscount = purchaseProductDetails.discount.sum()
        render(view: "/purchase/purchaseEntry/purchase-invoice", model: [purchaseBillDetail      :
                                                                                 purchaseBillDetail, invoiceNumber:
                invoiceNumber, purchaseProductDetails  : purchaseProductDetails,series: series, entity: entity,
                                                                         supplier: supplier, city: city, supcity   :
                                                                                 supcity, total :
                                                                                 purchaseProductDetails.amount.sum(),
                                                                         totalcgst:totalcgst,totalsgst:totalsgst,
                                                                         totaligst:totaligst,
                                                                         totaldiscount:totaldiscount,termsConditions:termsConditions])
    }

    def purchaseReturn()
    {
        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
        render(view: '/purchase/purchaseRetun', model: [tempStockBook: tempStockBook])
    }
}
