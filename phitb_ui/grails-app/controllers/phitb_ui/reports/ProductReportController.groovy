package phitb_ui.reports

import com.lowagie.text.html.simpleparser.ALink
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.ReportsService
import phitb_ui.SalesService

class ProductReportController {

    ReportsService reportsService
    def productStatement() {
        render(view: '/reports/productReport/productStatement')
    }

    def getProductStatement()
    {
        try {

            String entityId = session.getAttribute("entityId")
            String dateRange = params.dateRange
            HashMap<String, JSONArray> productDetails = new HashMap()
            //get sales
            JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
            if(saleBills?.size() > 0) {
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
            if(purchaseBills?.size() > 0) {
                for (JSONObject purchase : purchaseBills) {
                    for (JSONObject product : purchase.products) {
                        if(productDetails.containsKey(product.productId)) {
                            JSONArray batches = productDetails.get(product.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if(batchNumbers.contains(product.batchNumber))
                            {
                                for (Object batch : batches) {
                                    if (product.batchNumber.equalsIgnoreCase(batch.batchNumber)) {
                                        batch.purchaseSqty += product.sqty
                                        batch.purchaseFreeQty += product.freeQty
                                    }
                                }
                            }
                            else
                            {
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
                        }
                        else
                        {
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
            if(saleReturns?.size() >0) {
                for (Object saleReturn : saleReturns) {
                    for (JSONObject saleReturnProduct : saleReturn.products) {
                        if(productDetails.containsKey(saleReturnProduct.productId)) {
                            JSONArray batches = productDetails.get(saleReturnProduct.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if(batchNumbers.contains(saleReturnProduct.batchNumber))
                            {
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
                            }
                            else
                            {
                                JSONObject prd = new JSONObject()
                                prd.put("batchNumber", saleReturnProduct.batchNumber)
                                prd.put("expiryDate", saleReturnProduct.expiryDate)
                                prd.put("mrp", saleReturnProduct.mrp)
                                prd.put("saleSqty", 0)
                                prd.put("saleFreeQty", 0)
                                if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                    prd.put("saleReturn",(saleReturnProduct.sqty + saleReturnProduct.freeQty))
                                    prd.put("saleReturnBreakage",0)
                                }
                                if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                        || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                    //count breakage, expired and other
                                    prd.put("saleReturn",0)
                                    prd.put("saleReturnBreakage",(saleReturnProduct.sqty + saleReturnProduct.freeQty))
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
                        }
                        else
                        {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", saleReturnProduct.batchNumber)
                            prd.put("expiryDate", saleReturnProduct.expiryDate)
                            prd.put("mrp", saleReturnProduct.mrp)
                            prd.put("saleSqty", 0)
                            prd.put("saleFreeQty", 0)
                            if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                prd.put("saleReturn",(saleReturnProduct.sqty + saleReturnProduct.freeQty))
                                prd.put("saleReturnBreakage",0)
                            }
                            if (saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                    || saleReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                //count breakage, expired and other
                                prd.put("saleReturn",0)
                                prd.put("saleReturnBreakage",(saleReturnProduct.sqty + saleReturnProduct.freeQty))
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
            if(purchaseReturns?.size() > 0) {
                //count and add purchase returns

                for (Object purchaseReturn : purchaseReturns) {
                    for (JSONObject purchaseReturnProduct : purchaseReturn.products) {
                        if(productDetails.containsKey(purchaseReturnProduct.productId)) {
                            JSONArray batches = productDetails.get(purchaseReturnProduct.productId)
                            ArrayList<String> batchNumbers = batches.batchNumber
                            if(batchNumbers.contains(purchaseReturnProduct.batchNumber))
                            {
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
                            }
                            else
                            {
                                JSONObject prd = new JSONObject()
                                prd.put("batchNumber", purchaseReturnProduct.batchNumber)
                                prd.put("expiryDate", purchaseReturnProduct.expiryDate)
                                prd.put("mrp", purchaseReturnProduct.mrp)
                                prd.put("saleSqty", 0)
                                prd.put("saleFreeQty", 0)
                                if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                    prd.put("purchaseReturn",(purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                    prd.put("purchaseReturnBreakage",0)
                                }

                                if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                        || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER))
                                {
                                    //count breakage, expired and other
                                    prd.put("purchaseReturn",0)
                                    prd.put("purchaseReturnBreakage",(purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                }
                                prd.put("purchaseSqty", 0)
                                prd.put("purchaseFreeQty", 0)
                                prd.put("saleReturn", 0)
                                prd.put("saleReturnBreakage", 0)
                                JSONObject productJson = new ProductService().getProductById(purchaseReturnProduct.productId.toString())
                                prd.put("productName", productJson.productName)
                                prd.put("productId", productJson.id)
                            }
                        }
                        else
                        {
                            JSONObject prd = new JSONObject()
                            prd.put("batchNumber", purchaseReturnProduct.batchNumber)
                            prd.put("expiryDate", purchaseReturnProduct.expiryDate)
                            prd.put("mrp", purchaseReturnProduct.mrp)
                            prd.put("saleSqty", 0)
                            prd.put("saleFreeQty", 0)
                            if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_SALE_RETURN)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER_ADD)) {
                                prd.put("purchaseReturn",(purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
                                prd.put("purchaseReturnBreakage",0)
                            }
                            if (purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_EXPIRED)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_BREAKAGE)
                                    || purchaseReturnProduct.reason.equalsIgnoreCase(Constants.REASON_OTHER)) {
                                //count breakage, expired and other
                                prd.put("purchaseReturn",0)
                                prd.put("purchaseReturnBreakage",(purchaseReturnProduct.sqty + purchaseReturnProduct.freeQty))
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
            String startDate = dateRange.split("-")[0]
            def test = new ReportsService().getSaleInfoTillDate(entityId, startDate)
            respond productDetails, formats: ['json']
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
        }
    }
}
