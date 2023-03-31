package phitb_ui.reports

import grails.converters.JSON

//import com.lowagie.text.html.simpleparser.ALink
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.ReportsService
import phitb_ui.SalesService
import phitb_ui.product.ProductController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class ProductReportController {

    ReportsService reportsService
    static SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy/MM/dd")

    def productStatement() {
        render(view: '/reports/productReport/productStatement')
    }

    def getProductStatement() {
        try {

            String entityId = session.getAttribute("entityId")
            String dateRange = params.dateRange
            HashMap<String, JSONArray> productDetails = new HashMap()
            //get sales
            JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
            if (saleBills?.size() > 0) {
                for (JSONObject saleBill : saleBills) {
                    for (def product : saleBill.products) {
                        if (productDetails.containsKey(product.productId)) {
                            JSONArray batches = productDetails.get(product.productId)
                            for (Object batch : batches) {
                                JSONArray updatedBatches = new JSONArray()
                                updatedBatches.addAll(batches)
                                if (batch.batchNumber.equalsIgnoreCase(product.batchNumber)) {
                                    for (Object updatedBatch : updatedBatches) {
                                        if (updatedBatch.batchNumber.equalsIgnoreCase(batch.batchNumber)) {
                                            updatedBatch.saleSqty += product.sqty
                                            updatedBatch.saleFreeQty += product.freeQty
                                        }
                                    }
                                } else {
                                    JSONObject prd = new JSONObject()
                                    prd.put("batchNumber", product.batchNumber)
                                    prd.put("expiryDate", product.expiryDate)
                                    prd.put("mrp", product.mrp)
                                    prd.put("saleSqty", product.sqty)
                                    prd.put("saleFreeQty", product.freeQty)
                                    prd.put("saleReturn", 0)
                                    prd.put("saleReturnBreakage", 0)
                                    prd.put("purchaseSqty", 0)
                                    prd.put("purchaseFreeQty", 0)
                                    prd.put("purchaseReturn", 0)
                                    prd.put("purchaseReturnBreakage", 0)
                                    //get product from already added batches, this prevents additional API calls
                                    prd.put("productName", updatedBatches[0].productName)
                                    prd.put("productId", updatedBatches[0].id)
                                    updatedBatches.put(prd)
                                }
                                productDetails.put(product.productId, updatedBatches)
                            }
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", product.batchNumber)
                            prd.put("expiryDate", product.expiryDate)
                            prd.put("mrp", product.mrp)
                            prd.put("saleSqty", product.sqty)
                            prd.put("saleFreeQty", product.freeQty)
                            prd.put("saleReturn", 0)
                            prd.put("saleReturnBreakage", 0)
                            prd.put("purchaseSqty", 0)
                            prd.put("purchaseFreeQty", 0)
                            prd.put("purchaseReturn", 0)
                            prd.put("purchaseReturnBreakage", 0)
                            JSONObject productJson = new ProductService().getProductById(product.productId.toString())
                            prd.put("productName", productJson.productName)
                            prd.put("productId", productJson.id)
                            productDetails.put(product.productId, new JSONArray().put(prd))
                        }
                    }
                }
            }
            //get purchases
            JSONArray purchaseBills = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
            if (purchaseBills?.size() > 0) {
                for (JSONObject purchase : purchaseBills) {
                    for (JSONObject product : purchase.products) {
                        if (productDetails.containsKey(product.productId)) {
                            JSONArray batches = productDetails.get(product.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if (batchNumbers.contains(product.batchNumber)) {
                                for (Object batch : batches) {
                                    if (product.batchNumber.equalsIgnoreCase(batch.batchNumber)) {
                                        batch.purchaseSqty += product.sqty
                                        batch.purchaseFreeQty += product.freeQty
                                    }
                                }
                            } else {
                                //add batch to product
                                JSONObject prd = new JSONObject()
                                prd.put("batchNumber", product.batchNumber)
                                prd.put("expiryDate", product.expiryDate)
                                prd.put("mrp", product.mrp)
                                prd.put("saleSqty", 0)
                                prd.put("saleFreeQty", 0)
                                prd.put("saleReturn", 0)
                                prd.put("saleReturnBreakage", 0)
                                prd.put("purchaseSqty", product.sqty)
                                prd.put("purchaseFreeQty", product.freeQty)
                                prd.put("purchaseReturn", 0)
                                prd.put("purchaseReturnBreakage", 0)
                                JSONObject productJson = new ProductService().getProductById(product.productId.toString())
                                prd.put("productName", productJson.productName)
                                prd.put("productId", productJson.id)
                                batches.put(prd)
                            }
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", product.batchNumber)
                            prd.put("expiryDate", product.expiryDate)
                            prd.put("mrp", product.mrp)
                            prd.put("saleSqty", 0)
                            prd.put("saleFreeQty", 0)
                            prd.put("saleReturn", 0)
                            prd.put("saleReturnBreakage", 0)
                            prd.put("purchaseSqty", product.sqty)
                            prd.put("purchaseFreeQty", product.freeQty)
                            prd.put("purchaseReturn", 0)
                            prd.put("purchaseReturnBreakage", 0)
                            JSONObject productJson = new ProductService().getProductById(product.productId.toString())
                            prd.put("productName", productJson.productName)
                            prd.put("productId", productJson.id)
                            productDetails.put(product.productId, new JSONArray().put(prd))
                        }
                    }
                }
            }

            //TODO: to be checked
            //get sale returns
            JSONArray saleReturns = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
            if (saleReturns?.size() > 0) {
                for (Object saleReturn : saleReturns) {
                    for (JSONObject saleReturnProduct : saleReturn.products) {
                        if (productDetails.containsKey(saleReturnProduct.productId)) {
                            JSONArray batches = productDetails.get(saleReturnProduct.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if (batchNumbers.contains(saleReturnProduct.batchNumber)) {
                                for (Object batch : batches) {
                                    if (saleReturnProduct.batchNumber.equalsIgnoreCase(batch.batchNumber)) {
                                        if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                                || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                            batch.saleReturn += (saleReturnProduct.sqty + saleReturnProduct.freeQty)
                                        }

                                        if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                                || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                                || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                            batch.saleReturnBreakage += (saleReturnProduct.sqty + saleReturnProduct.freeQty)
                                        }
                                    }
                                }
                            } else {
                                JSONObject prd = new JSONObject()
                                prd.put("batchNumber", saleReturnProduct.batchNumber)
                                prd.put("expiryDate", saleReturnProduct.expiryDate)
                                prd.put("mrp", saleReturnProduct.mrp)
                                prd.put("saleSqty", 0)
                                prd.put("saleFreeQty", 0)
                                if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                    prd.put("saleReturn", (saleReturnProduct.sqty + saleReturnProduct.freeQty))
                                    prd.put("saleReturnBreakage", 0)
                                }
                                if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                    //count breakage, expired and other
                                    prd.put("saleReturn", 0)
                                    prd.put("saleReturnBreakage", (saleReturnProduct.sqty + saleReturnProduct.freeQty))
                                }
                                prd.put("purchaseSqty", 0)
                                prd.put("purchaseFreeQty", 0)
                                prd.put("purchaseReturn", 0)
                                prd.put("purchaseReturnBreakage", 0)
                                JSONObject productJson = new ProductService().getProductById(saleReturnProduct.productId.toString())
                                prd.put("productName", productJson.productName)
                                prd.put("productId", productJson.id)
                                batches.put(prd)
                            }
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", saleReturnProduct.batchNumber)
                            prd.put("expiryDate", saleReturnProduct.expiryDate)
                            prd.put("mrp", saleReturnProduct.mrp)
                            prd.put("saleSqty", 0)
                            prd.put("saleFreeQty", 0)
                            if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                prd.put("saleReturn", (saleReturnProduct.sqty + saleReturnProduct.freeQty))
                                prd.put("saleReturnBreakage", 0)
                            }
                            if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                //count breakage, expired and other
                                prd.put("saleReturn", 0)
                                prd.put("saleReturnBreakage", (saleReturnProduct.sqty + saleReturnProduct.freeQty))
                            }
                            prd.put("purchaseSqty", 0)
                            prd.put("purchaseFreeQty", 0)
                            prd.put("purchaseReturn", 0)
                            prd.put("purchaseReturnBreakage", 0)
                            JSONObject productJson = new ProductService().getProductById(saleReturnProduct.productId.toString())
                            prd.put("productName", productJson.productName)
                            prd.put("productId", productJson.id)
                            productDetails.put(saleReturnProduct.productId, new JSONArray().put(prd))
                        }
                    }
                }
            }

            //get purchase returns
            JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
            if (purchaseReturns?.size() > 0) {
                //count and add purchase returns

                for (Object purchaseReturn : purchaseReturns) {
                    for (JSONObject purchaseReturnProduct : purchaseReturn.products) {
                        if (productDetails.containsKey(purchaseReturnProduct.productId)) {
                            JSONArray batches = productDetails.get(purchaseReturnProduct.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if (batchNumbers.contains(purchaseReturnProduct.batchNumber)) {
                                for (Object batch : batches) {
                                    if (purchaseReturnProduct.batchNumber.equalsIgnoreCase(batch.batchNumber)) {
                                        if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                                || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                            batch.purchaseReturn += (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty)
                                        }

                                        if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                                || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                                || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                            batch.purchaseReturnBreakage += (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty)
                                        }
                                    }
                                }
                            } else {
                                JSONObject prd = new JSONObject()
                                prd.put("batchNumber", purchaseReturnProduct.batchNumber)
                                prd.put("expiryDate", purchaseReturnProduct.expiryDate)
                                prd.put("mrp", purchaseReturnProduct.mrp)
                                prd.put("saleSqty", 0)
                                prd.put("saleFreeQty", 0)
                                if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                    prd.put("purchaseReturn", (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                    prd.put("purchaseReturnBreakage", 0)
                                }

                                if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                    //count breakage, expired and other
                                    prd.put("purchaseReturn", 0)
                                    prd.put("purchaseReturnBreakage", (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                }
                                prd.put("purchaseSqty", 0)
                                prd.put("purchaseFreeQty", 0)
                                prd.put("saleReturn", 0)
                                prd.put("saleReturnBreakage", 0)
                                JSONObject productJson = new ProductService().getProductById(purchaseReturnProduct.productId.toString())
                                prd.put("productName", productJson.productName)
                                prd.put("productId", productJson.id)
                            }
                        } else {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", purchaseReturnProduct.batchNumber)
                            prd.put("expiryDate", purchaseReturnProduct.expiryDate)
                            prd.put("mrp", purchaseReturnProduct.mrp)
                            prd.put("saleSqty", 0)
                            prd.put("saleFreeQty", 0)
                            if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                prd.put("purchaseReturn", (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                prd.put("purchaseReturnBreakage", 0)
                            }
                            if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                //count breakage, expired and other
                                prd.put("purchaseReturn", 0)
                                prd.put("purchaseReturnBreakage", (purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                            }
                            prd.put("purchaseSqty", 0)
                            prd.put("purchaseFreeQty", 0)
                            prd.put("saleReturn", 0)
                            prd.put("saleReturnBreakage", 0)
                            JSONObject productJson = new ProductService().getProductById(purchaseReturnProduct.productId.toString())
                            prd.put("productName", productJson.productName)
                            prd.put("productId", productJson.id)
                            productDetails.put(purchaseReturnProduct.productId, new JSONArray().put(prd))
                        }
                    }
                }
            }

            //TODO: get opening and closing stock
            /* String startDate = dateRange.split("-")[0]
             def saleInfos = new ReportsService().getSaleInfoTillDate(entityId, startDate)
             def purchaseInfo = new ReportsService().getPurchaseInfoTillDate(entityId, startDate)*/
            //TODO: Stock adjustment details to be added
            respond productDetails, formats: ['json']
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

    def batchStatement() {
        JSONArray products = new ProductService().getProductByEntity(session.getAttribute("entityId").toString())
        render(view: '/reports/productReport/batchStatement', model: [products: products])
    }
    def getBatchStatement() {
        try {
            String entityId = session.getAttribute("entityId")
            String dateRange = params.dateRange
            String productId = params.productId

            JSONObject product = new ProductService().getProductById(productId)
            if (product) {
                Response batchResponse = new ProductService().getBatchesOfProduct(productId)
                if (batchResponse.status == 200) {
                    JSONArray batches = batchResponse.readEntity(JSONArray.class)
                    if (batches?.size() > 0) {
                        //Calculate Opening balance
                        def finYearStartDate = session.getAttribute("startDate")
                        def fromDate = dateRange.split("-")[0]
                        Calendar cal = Calendar.getInstance()
                        cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate))
                        cal.add(Calendar.DAY_OF_MONTH, -1)
                        String openingBalanceEndDate = cal.getTime().format("dd/MM/yyyy")
                        String openingDateRange = finYearStartDate + " - " +openingBalanceEndDate
                        JSONObject finalReport = new JSONObject()
                        //Read Incoming
                        JSONArray openingPurchaseBills = new PurchaseService().getPurchaseBillByDateRange(openingDateRange, entityId)
                        JSONArray openingSaleReturns = new SalesService().getSaleReturnByDateRange(openingDateRange, entityId)

                        //Read Outgoing
                        JSONArray openingSaleBills = new SalesService().getSaleBillByDateRange(openingDateRange, entityId)
                        JSONArray openingSaleOrders = new SalesService().getSaleOrderByDateRange(openingDateRange, entityId)
                        JSONArray openingPurchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(openingDateRange, entityId)
                        JSONArray openingGtns = new SalesService().getGTNByDateRange(openingDateRange, entityId)
                        JSONArray openingSampleConversionInvoice = new SalesService().getSampleConversionByDateRange(openingDateRange, entityId)

                        //Inter doc stock transfer
                        JSONArray openingSampleConversionLogs = new SalesService().getSampleConversionLogByDateRange(openingDateRange, entityId)
                        JSONArray openingStockAdjustments = new SalesService().getStockAdjustmentByDateRange(openingDateRange, entityId)


                        JSONObject openingBalances = new JSONObject<>()
                        for (Object batch : batches) {
                            JSONObject openingBalance = new JSONObject()
                            long openingStockQty = 0
                            long openingQty = 0
                            long openingFreeQty = 0
                            JSONObject inventory = new InventoryService().getStocksOfProductAndBatch(productId, batch.batchNumber, entityId)
                            if(inventory)
                            {
                                openingStockQty = inventory.get("openingStockQty")
                                openingQty = openingStockQty
                            }
                            else
                            {
                                response.status = 400
                                return
                            }

                            for (Object pb : openingPurchaseBills) {
                                if (pb.billStatus == "ACTIVE") {
                                    for (Object prd : pb.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty += prd.get("sqty")
                                            openingFreeQty += prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object sr : openingSaleReturns) {
                                if (sr.returnStatus == "ACTIVE") {
                                    for (Object prd : sr.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty += prd.get("sqty")
                                            openingFreeQty += prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object sb : openingSaleBills) {
                                if (sb.billStatus == "ACTIVE") {
                                    for (Object prd : sb.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty -= prd.get("sqty")
                                            openingFreeQty -= prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object so : openingSaleOrders) {
                                if (so.billStatus == "ACTIVE") {
                                    for (Object prd : so.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty -= prd.get("sqty")
                                            openingFreeQty -= prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object pr : openingPurchaseReturns) {
                                if (pr.returnStatus == "ACTIVE") {
                                    for (Object prd : pr.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty -= prd.get("sqty")
                                            openingFreeQty -= prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object gtn : openingGtns) {
                                if (gtn.billStatus == "ACTIVE") {
                                    for (Object prd : gtn.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            openingQty -= prd.get("sqty")
                                            openingFreeQty -= prd.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object sc : openingSampleConversionInvoice) {
                                if (sc.billStatus == "ACTIVE") {
                                    for (Object prd : sc.products) {
                                        if (prd && !prd?.deleted && prd?.productId == batch.product.id && prd?.batchNumber == batch.batchNumber) {
                                            openingQty -= prd?.get("sqty")
                                            openingFreeQty -= prd?.get("freeQty")
                                        }
                                    }
                                }
                            }

                            for (Object scl : openingSampleConversionLogs) {
                                if (scl.saleableProductId == batch.product.id && scl.saleableBatch == batch.batchNumber) {
                                    openingQty -= scl.get("sampleQty")
                                    openingFreeQty -= 0
                                }
                                else  if (scl.sampleProductId == batch.product.id && scl.sampleBatch == batch.batchNumber)
                                {
                                    openingQty += scl.get("sampleQty")
                                    openingFreeQty += 0
                                }
                            }

                            for (Object sa : openingStockAdjustments) {
                                if(sa.productId == batch.product.id && sa.batchNumber == batch.batchNumber)
                                {
                                    openingQty += sa.get("sqty")
                                    openingFreeQty += sa.get("fqty")
                                }
                            }

                            openingBalance.put("openingQty", openingQty)
                            openingBalance.put("openingFreeQty", openingFreeQty)
                            openingBalance.put("openingStockQty", openingStockQty)
                            openingBalances.put(batch.batchNumber, openingBalance)
                        }

                        finalReport.put("openingBalance", openingBalances)

                        //Read Incoming
                        JSONArray purchaseBills = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
                        JSONArray saleReturns = new SalesService().getSaleReturnByDateRange(dateRange, entityId)

                        //Read Outgoing
                        JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
                        JSONArray saleOrders = new SalesService().getSaleOrderByDateRange(dateRange, entityId)
                        JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
                        JSONArray gtns = new SalesService().getGTNByDateRange(dateRange, entityId)
                        JSONArray sampleConversionInvoice = new SalesService().getSampleConversionByDateRange(dateRange, entityId)

                        //Inter doc stock transfer
                        JSONArray sampleConversionLogs = new SalesService().getSampleConversionLogByDateRange(dateRange, entityId)
                        JSONArray stockAdjustments = new SalesService().getStockAdjustmentByDateRange(dateRange, entityId)


                        JSONObject docs = new JSONObject()

                        for (Object batch : batches) {
                            JSONArray jsonArray = new JSONArray()

                            //loop through incoming
                            for (Object pb : purchaseBills) {
                                if (pb.billStatus == "ACTIVE") {
                                    for (Object prd : pb.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", pb.id)
                                            jsonObject.put("docNo", pb.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(pb.billingDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate, true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Purchase Invoice")
                                            JSONObject entity = new EntityService().getEntityById(pb.supplierId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", prd.sqty) //purchase
                                            jsonObject.put("incomingSchemeQty", prd.freeQty)
                                            jsonObject.put("outgoingQty", "") //sale
                                            jsonObject.put("outgoingSchemeQty", "")
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object sr : saleReturns) {
                                if (sr.returnStatus == "ACTIVE") {
                                    for (Object prd : sr.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", sr.id)
                                            jsonObject.put("docNo", sr.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(sr.entryDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate,true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Sale Return")
                                            JSONObject entity = new EntityService().getEntityById(sr.customerId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", prd.sqty) //purchase
                                            jsonObject.put("incomingSchemeQty", prd.freeQty)
                                            jsonObject.put("outgoingQty", "") //sale
                                            jsonObject.put("outgoingSchemeQty", "")
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            //loop through outcoming
                            for (Object sb : saleBills) {
                                if (sb.billStatus == "ACTIVE") {
                                    for (Object prd : sb.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", sb.id)
                                            jsonObject.put("docNo", sb.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(sb.orderDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate, true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Sale Invoice")
                                            JSONObject entity = new EntityService().getEntityById(sb.customerId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty", prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty", prd.freeQty)
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object so : saleOrders) {
                                if (so.billStatus == "ACTIVE") {
                                    for (Object prd : so.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", so.id)
                                            jsonObject.put("docNo", so.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(so.entryDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate,true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Sale Order")
                                            JSONObject entity = new EntityService().getEntityById(so.customerId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty", prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty", prd.freeQty)
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object pr : purchaseReturns) {
                                if (pr.returnStatus == "ACTIVE") {
                                    for (Object prd : pr.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", pr.id)
                                            jsonObject.put("docNo", pr.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(pr.entryDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate,true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Purchase Return")
                                            JSONObject entity = new EntityService().getEntityById(pr.supplierId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty", prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty", prd.freeQty)
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object gtn : gtns) {
                                if (gtn.billStatus == "ACTIVE") {
                                    for (Object prd : gtn.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", gtn.id)
                                            jsonObject.put("docNo", gtn.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(gtn.orderDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate,true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "GTN")
                                            JSONObject entity = new EntityService().getEntityById(gtn.customerId.toString())
                                            jsonObject.put("entityName", entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty", prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty", prd.freeQty)
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object sc : sampleConversionInvoice) {
                                if (sc.billStatus == "ACTIVE") {
                                    for (Object prd : sc.products) {
                                        if (!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber) {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId", sc.id)
                                            jsonObject.put("docNo", sc.invoiceNumber)
                                            jsonObject.put("docDate", convertDate(sc.orderDate))
                                            jsonObject.put("expDate", convertDate(prd.expiryDate,true))
                                            jsonObject.put("productId", prd.productId)
                                            jsonObject.put("docType", "Sample Invoice")
                                            JSONObject user = new EntityService().getUser(sc.customerId.toString())
                                            jsonObject.put("entityName", user.name)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty", prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty", prd.freeQty)
                                            jsonObject.put("dateCreated", prd.dateCreated)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for(Object scl : sampleConversionLogs)
                            {
                                if (scl.saleableBatch == batch.batchNumber && scl.saleableProductId == batch.product.id) {
                                    JSONObject jsonObject = new JSONObject()
                                    jsonObject.put("docId", scl.id)
                                    jsonObject.put("docNo", "-")
                                    jsonObject.put("docDate", convertDate(scl.dateCreated))
                                    jsonObject.put("expDate", "-")
                                    jsonObject.put("productId", scl.saleableProductId)
                                    jsonObject.put("docType", "Sample Conversion")
                                    jsonObject.put("entityName", "-")
                                    jsonObject.put("incomingQty", "") //purchase
                                    jsonObject.put("incomingSchemeQty", "")
                                    jsonObject.put("outgoingQty", scl.sampleQty) //sale
                                    jsonObject.put("outgoingSchemeQty", 0)
                                    jsonObject.put("dateCreated", scl.dateCreated)
                                    jsonArray.add(jsonObject)
                                }
                                else if (scl.sampleBatch == batch.batchNumber && scl.sampleProductId == batch.product.id) {
                                    JSONObject jsonObject = new JSONObject()
                                    jsonObject.put("docId", scl.id)
                                    jsonObject.put("docNo", "-")
                                    jsonObject.put("docDate", convertDate(scl.dateCreated))
                                    jsonObject.put("expDate", "-")
                                    jsonObject.put("productId", scl.sampleProductId)
                                    jsonObject.put("docType", "Sample Conversion")
                                    jsonObject.put("entityName", "-")
                                    jsonObject.put("incomingQty", scl.sampleQty) //purchase
                                    jsonObject.put("incomingSchemeQty", 0)
                                    jsonObject.put("outgoingQty", "") //sale
                                    jsonObject.put("outgoingSchemeQty", "")
                                    jsonObject.put("dateCreated", scl.dateCreated)
                                    jsonArray.add(jsonObject)
                                }
                            }

                            for (Object sa : stockAdjustments) {
                                if(sa.productId == batch.product.id && sa.batchNumber == batch.batchNumber)
                                {
                                    long sqty = sa.get("sqty")
                                    long fqty = sa.get("fqty")

                                    JSONObject jsonObject = new JSONObject()
                                    jsonObject.put("docId", sa.id)
                                    jsonObject.put("docNo", "-")
                                    jsonObject.put("docDate", convertDate(sa.dateCreated))
                                    jsonObject.put("expDate", "-")
                                    jsonObject.put("productId", sa.productId)
                                    jsonObject.put("docType", "Stock Adjustment")
                                    jsonObject.put("entityName", "-")
                                    jsonObject.put("dateCreated", sa.dateCreated)
                                    if(sqty >= 0) {
                                        jsonObject.put("incomingQty", Math.abs(sqty))
                                        jsonObject.put("outgoingQty", "")
                                    }
                                    else {
                                        jsonObject.put("incomingQty", "")
                                        jsonObject.put("outgoingQty", Math.abs(sqty))
                                    }

                                    if(fqty >= 0) {
                                        jsonObject.put("incomingSchemeQty", Math.abs(fqty))
                                        jsonObject.put("outgoingSchemeQty", "")
                                    }
                                    else {
                                        jsonObject.put("incomingSchemeQty", "")
                                        jsonObject.put("outgoingSchemeQty", Math.abs(fqty))
                                    }
                                    jsonArray.add(jsonObject)
                                }
                            }
                            //sort based on date created
                            Collections.sort(jsonArray, new Comparator<JSONObject>() {
                                private String KEY_NAME = "dateCreated";
                                @Override
                                public int compare(JSONObject a, JSONObject b) {
                                    Date valA = null
                                    Date valB = null
                                    try {
                                        valA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(a.get(KEY_NAME).toString());
                                        valB = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(b.get(KEY_NAME).toString());
                                    }
                                    catch (Exception e) {
                                        //do something
                                    }
                                    if(valA != null && valB != null) {
                                        return valA.compareTo(valB);
                                    }
                                    else
                                        return 0
                                }
                            });


                            docs.put(batch.batchNumber, jsonArray)


                        }

                        //sort based on date
                      /*  for (Object key : docs.keySet()) {
                            JSONArray batch = docs.get(key)
                            JSONArray newArray = sortJsonArray(batch)
                            docs.put(key, newArray)
                        }*/

                        finalReport.put("docs", docs)

                        respond finalReport, formats: ['json']
                    } else {
                        response.status = 400
                    }
                } else {
                    response.status = 400
                }
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
            response.status = 400
        }

    }


   /* public static JSONArray sortJsonArray(JSONArray array) {
        List<JSONObject> jsons = new ArrayList<JSONObject>()
        for (int i = 0; i < array.length(); i++) {
            jsons.add(array.getJSONObject(i))
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                String dateStr1 = lhs.getString("docDate")
                String dateStr2 = rhs.getString("docDate")
                Date date1 = dateFormat1.parse(dateStr1)
                Date date2 = dateFormat1.parse(dateStr2)
                // Here you could parse string id to integer and then compare.
                return date1.compareTo(date2);
            }
        });
        return new JSONArray(jsons);
    }*/

    static convertDate(String date, Boolean isExpiry = false) {
        try {

            if (date.contains("T")) {
                date = date.replaceAll("T", " ")
                date = date.replaceAll("Z", "")
                if(!isExpiry)
                    return dateFormat2.parse(date).format("dd/MM/yyyy hh:mm:ss")
                else
                    return dateFormat2.parse(date).format("MMM-yyyy")
            }
            else if(date.contains("-"))
            {
                date = date.replaceAll("-", "/")
                if(!isExpiry) {
                    return date
                }
                else
                {
                    return dateFormat3.parse(date).format("MMM-yyyy")
                }
            }
            else {
                if(!isExpiry)
                    return dateFormat1.parse(date).format("dd/MM/yyyy hh:mm:ss")
                else
                    return dateFormat1.parse(date).format("MMM-yyyy")
            }
        }
        catch (Exception ex) {
            ex.printStackTrace()
            return date
        }
    }

    def priceList()
    {
        render(view: '/reports/productReport/priceList')
    }

    def getPriceList()
    {
        String entityId = session.getAttribute("entityId")
        String groupids = params.groupids
        String companyids = params.companyids
        JSONObject groupedObject = new JSONObject()
        JSONArray products = new ProductService().getProductsByEntityId(entityId)
        for (JSONObject product : products) {

            JSONObject jsonObject = new JSONObject()
            jsonObject.put("saleRate", product.saleRate)
            jsonObject.put("mrp", product.mrp)
            jsonObject.put("purchaseRate", product.purchaseRate)
            jsonObject.put("productName", product.productName)
            jsonObject.put("unitPacking", product.unitPacking)

            if(groupids != null && groupids != "null")
            {
                String[] groupIds = groupids.split(",")
                if(groupIds.contains(product?.group?.id?.toString()))
                {
                    def productGroup = new ProductService().getProductGroupById(product?.group?.id?.toString())
                    if(groupedObject.containsKey(productGroup.groupName))
                    {
                        JSONArray array = groupedObject.get(productGroup.groupName)
                        array.add(jsonObject)
                        groupedObject.put(productGroup.groupName, array)
                    }
                    else
                    {
                        JSONArray array = new JSONArray()
                        array.put(jsonObject)
                        groupedObject.put(productGroup.groupName, array)
                    }
                }
            }
            else if(companyids != null && companyids != "null")
            {
                String[] companyIds = companyids.split(",")
                if(companyIds.contains(product?.manufacturerId?.toString()))
                {
                    def entity = new EntityService().getEntityById(product?.manufacturerId?.toString())
                    if(groupedObject.containsKey(entity.entityName))
                    {
                        JSONArray array = groupedObject.get(entity.entityName)
                        array.add(jsonObject)
                        groupedObject.put(entity.entityName, array)
                    }
                    else
                    {
                        JSONArray array = new JSONArray()
                        array.put(jsonObject)
                        groupedObject.put(entity.entityName, array)
                    }
                }
            }
            else
            {
                if(groupedObject.containsKey("ALL"))
                {
                    JSONArray array = groupedObject.get("ALL")
                    array.add(jsonObject)
                    groupedObject.put("ALL", array)
                }
                else
                {
                    JSONArray array = new JSONArray()
                    array.put(jsonObject)
                    groupedObject.put("ALL", array)
                }
            }
        }

        respond groupedObject, formats: ['json']
    }
}
