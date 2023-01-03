package phitb_ui.reports

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
           /* String startDate = dateRange.split("-")[0]
            def saleInfos = new ReportsService().getSaleInfoTillDate(entityId, startDate)
            def purchaseInfo = new ReportsService().getPurchaseInfoTillDate(entityId, startDate)*/
            //TODO: Stock adjustment details to be added
            respond productDetails, formats: ['json']
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
        }
    }


    def getBatchStatement()
    {
        try {
            String entityId = session.getAttribute("entityId")
            String dateRange = params.dateRange
            String productId = params.productId

            JSONObject product = new ProductService().getProductById(productId)
            if(product)
            {
                Response batchResponse = new ProductService().getBatchesOfProduct(productId)
                if(batchResponse.status == 200) {
                    JSONArray batches = batchResponse.readEntity(JSONArray.class)
                    if(batches?.size() > 0) {
                        //Read Incoming
                        JSONArray purchaseBills = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
                        JSONArray purchaseOrders = new PurchaseService().getPurchaseOrderByDateRange(dateRange, entityId)
                        JSONArray saleReturns = new SalesService().getSaleReturnByDateRange(dateRange, entityId)

                        //Read Outgoing
                        JSONArray saleBills = new SalesService().getSaleBillByDateRange(dateRange, entityId)
                        JSONArray saleOrders = new SalesService().getSaleOrderByDateRange(dateRange, entityId)
                        JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
                        JSONArray gtns = new SalesService().getGTNByDateRange(dateRange, entityId)

                        //Calculate Opening balance


                        JSONObject docs = new JSONObject()

                        for (Object batch : batches) {
                            long saleQty = 0
                            long freeQty = 0
                            JSONArray jsonArray = new JSONArray()

                            //loop through incoming
                            for (Object pb : purchaseBills) {
                                if(pb.billStatus == "ACTIVE")
                                {
                                    for(Object prd : pb.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",pb.id)
                                            jsonObject.put("docNo",pb.invoiceNumber)
                                            jsonObject.put("docDate",pb.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Purchase Invoice")
                                            JSONObject entity = new EntityService().getEntityById(pb.supplierId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty",prd.sqty) //purchase
                                            jsonObject.put("incomingSchemeQty",prd.freeQty)
                                            jsonObject.put("outgoingQty","") //sale
                                            jsonObject.put("outgoingSchemeQty","")
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object po : purchaseOrders) {
                                if(po.billStatus == "ACTIVE")
                                {
                                    for(Object prd : po.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",po.id)
                                            jsonObject.put("docNo",po.invoiceNumber)
                                            jsonObject.put("docDate",po.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Purchase Order")
                                            JSONObject entity = new EntityService().getEntityById(po.supplierId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty",prd.sqty) //purchase
                                            jsonObject.put("incomingSchemeQty",prd.freeQty)
                                            jsonObject.put("outgoingQty","") //sale
                                            jsonObject.put("outgoingSchemeQty","")
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object sr : saleReturns) {
                                if(sr.returnStatus == "ACTIVE")
                                {
                                    for(Object prd : sr.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",sr.id)
                                            jsonObject.put("docNo",sr.invoiceNumber)
                                            jsonObject.put("docDate",sr.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Sale Return")
                                            JSONObject entity = new EntityService().getEntityById(sr.customerId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty",prd.sqty) //purchase
                                            jsonObject.put("incomingSchemeQty",prd.freeQty)
                                            jsonObject.put("outgoingQty","") //sale
                                            jsonObject.put("outgoingSchemeQty","")
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            //loop through outcoming
                            for (Object sb : saleBills) {
                                if(sb.billStatus == "ACTIVE")
                                {
                                    for(Object prd : sb.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",sb.id)
                                            jsonObject.put("docNo",sb.invoiceNumber)
                                            jsonObject.put("docDate",sb.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Sale Invoice")
                                            JSONObject entity = new EntityService().getEntityById(sb.customerId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty",prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty",prd.freeQty)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object so : saleOrders) {
                                if(so.billStatus == "ACTIVE")
                                {
                                    for(Object prd : so.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",so.id)
                                            jsonObject.put("docNo",so.invoiceNumber)
                                            jsonObject.put("docDate",so.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Sale Order")
                                            JSONObject entity = new EntityService().getEntityById(so.customerId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty",prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty",prd.freeQty)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object pr : purchaseReturns) {
                                if(pr.returnStatus == "ACTIVE")
                                {
                                    for(Object prd : pr.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",pr.id)
                                            jsonObject.put("docNo",pr.invoiceNumber)
                                            jsonObject.put("docDate",pr.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","Purchase Return")
                                            JSONObject entity = new EntityService().getEntityById(pr.supplierId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty",prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty",prd.freeQty)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            for (Object gtn : gtns) {
                                if(gtn.billStatus == "ACTIVE")
                                {
                                    for(Object prd : gtn.products)
                                    {
                                        if(!prd.deleted && prd.productId == batch.product.id && prd.batchNumber == batch.batchNumber)
                                        {
                                            JSONObject jsonObject = new JSONObject()
                                            jsonObject.put("docId",gtn.id)
                                            jsonObject.put("docNo",gtn.invoiceNumber)
                                            jsonObject.put("docDate",gtn.billingDate)
                                            jsonObject.put("expDate",prd.expiryDate)
                                            jsonObject.put("productId",prd.productId)
                                            jsonObject.put("docType","GTN")
                                            JSONObject entity = new EntityService().getEntityById(gtn.customerId.toString())
                                            jsonObject.put("entityName",entity.entityName)
                                            jsonObject.put("incomingQty", "") //purchase
                                            jsonObject.put("incomingSchemeQty", "")
                                            jsonObject.put("outgoingQty",prd.sqty) //sale
                                            jsonObject.put("outgoingSchemeQty",prd.freeQty)
                                            jsonArray.add(jsonObject)
                                        }
                                    }
                                }
                            }

                            docs.put(batch.batchNumber, jsonArray)
                        }

                        //sort based on date
                        for (Object key : docs.keySet()) {
                            JSONArray batch = docs.get(key)
                            JSONArray newArray = sortJsonArray(batch)
                            docs.put(key, newArray)
                        }
                        respond docs, formats: ['json']
                    }
                    else
                    {
                        response.status = 400
                    }
                }
                else
                {
                    response.status = 400
                }
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            println(ex.stackTrace)
            response.status = 400
        }

    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    public static JSONArray sortJsonArray(JSONArray array) {
        List<JSONObject> jsons = new ArrayList<JSONObject>()
        for (int i = 0; i < array.length(); i++) {
            jsons.add(array.getJSONObject(i))
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                String dateStr1 = lhs.getString("docDate")
                String dateStr2 = rhs.getString("docDate")
                Date date1 = sdf.parse(dateStr1)
                Date date2 = sdf.parse(dateStr2)
                // Here you could parse string id to integer and then compare.
                return date2.compareTo(date1);
            }
        });
        return new JSONArray(jsons);
    }
}
