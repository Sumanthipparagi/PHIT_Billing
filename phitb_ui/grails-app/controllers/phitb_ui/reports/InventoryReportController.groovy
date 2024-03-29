package phitb_ui.reports

//import com.lowagie.text.html.simpleparser.ALink
import grails.converters.JSON
import org.grails.datastore.mapping.query.Query
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController

import java.text.SimpleDateFormat

class InventoryReportController {

    def index() {
    }

    def statement() {
        String entityId = session.getAttribute("entityId")
        JSONObject loggedInEntity = new EntityService().getEntityById(entityId)
        ArrayList entities = new ArrayList()
        //entities = new EntityRegisterController().getByAffiliates(entityId) as ArrayList
        entities[0] = loggedInEntity
        render(view: '/reports/inventoryReport/statement', model: [entities: entities])
    }

    def getStatement() {
        try {
            String entityId = params.entityId
            HashMap<Long, ArrayList<String>> productBatches = new HashMap<>()
            JSONArray products = new ProductService().getProductByEntity(entityId)
            for (Object product : products) {
                def apiResponse = new ProductService().getBatchesOfProduct(product.id.toString())
                if (apiResponse.status == 200) {
                    JSONArray jsonArray = JSON.parse(apiResponse.readEntity(String.class)) as JSONArray
                    ArrayList<String> batchNumbers = new ArrayList<>()
                    for (Object js : jsonArray) {
                        batchNumbers.add(js.batchNumber)
                    }
//                    batchNumbers.stream().forEach({b -> batchNumbers.add(b.batchNumber)})
                    productBatches.put(product.id, batchNumbers)
                }
            }

            String financialYear = session.getAttribute("financialYear")
            String dateRange = params.dateRange
            //String sortBy = params.sortBy
            String sortBy = "id"
            //get sale invoice and sales return
            JSONArray saleInvoices = new SalesService().getSaleBillByDateRange(dateRange, entityId)
            JSONArray saleReturns = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
            //get purchase invoice and purchase return
            JSONArray purchaseInvoices = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
            JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)

            HashMap<Long, InventoryStatement> productList = new HashMap<>()
            ArrayList<InventoryStatement> inventoryStatements = new ArrayList<>()


            //saleinvoice
            for (Object saleInvoice : saleInvoices) {
                long openingQty = 0
                double openingAmt = 0
                long purchaseQty = 0
                double purchaseAmt = 0
                long saleQty = 0
                double saleAmt = 0
                InventoryStatement inventoryStatement = null
                for (Object product : saleInvoice.products) {
                    long productId = product.productId
                    if (productList.containsKey(productId)) {
                        inventoryStatement = productList.get(productId) as InventoryStatement
                        saleQty = inventoryStatement.saleQty + (product.sqty + product.freeQty)
                        saleAmt = UtilsService.round(inventoryStatement.saleAmt + (product.amount - product.gstAmount), 2)
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                    } else {
                        JSONObject productDetail = new ProductService().getProductById(productId.toString())
                        saleQty = product.sqty + product.freeQty
                        saleAmt = UtilsService.round((product.amount - product.gstAmount), 2)
                        inventoryStatement = new InventoryStatement()
                        inventoryStatement.productId = productId
                        inventoryStatement.productName = productDetail?.productName
                        inventoryStatement.packing = productDetail?.unitPacking
                        inventoryStatement.openingQty = openingQty
                        inventoryStatement.openingAmt = openingAmt
                        inventoryStatement.purchaseQty = purchaseQty
                        inventoryStatement.purchaseAmt = purchaseAmt
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                        productList.put(productId, inventoryStatement)
                    }

                }
            }

            //salereturn
            for (Object saleReturn : saleReturns) {
                long openingQty = 0
                double openingAmt = 0
                long purchaseQty = 0
                double purchaseAmt = 0
                long saleQty = 0
                double saleAmt = 0
                InventoryStatement inventoryStatement = null
                for (Object product : saleReturn.products) {
                    long productId = product.productId
                    if (productList.containsKey(productId)) {
                        inventoryStatement = productList.get(productId) as InventoryStatement
                        saleQty = inventoryStatement.saleQty + (product.sqty + product.freeQty)
                        saleAmt = UtilsService.round(inventoryStatement.saleAmt + (product.amount - product.gstAmount), 2)
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                    } else {
                        JSONObject productDetail = new ProductService().getProductById(productId.toString())
                        saleQty = product.sqty + product.freeQty
                        saleAmt = UtilsService.round((product.amount - product.gstAmount), 2)
                        inventoryStatement = new InventoryStatement()
                        inventoryStatement.productId = productId
                        inventoryStatement.productName = productDetail?.productName
                        inventoryStatement.packing = productDetail?.unitPacking
                        inventoryStatement.openingQty = openingQty
                        inventoryStatement.openingAmt = openingAmt
                        inventoryStatement.purchaseQty = purchaseQty
                        inventoryStatement.purchaseAmt = purchaseAmt
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                        productList.put(productId, inventoryStatement)
                    }

                }
            }

            //purchase invoice
            for (Object purchaseInvoice : purchaseInvoices) {
                long openingQty = 0
                double openingAmt = 0
                long purchaseQty = 0
                double purchaseAmt = 0
                long saleQty = 0
                double saleAmt = 0
                InventoryStatement inventoryStatement = null
                for (Object product : purchaseInvoice.products) {
                    long productId = product.productId
                    if (productList.containsKey(productId)) {
                        inventoryStatement = productList.get(productId) as InventoryStatement
                        purchaseQty = inventoryStatement.purchaseQty + (product.sqty + product.freeQty)
                        purchaseAmt = UtilsService.round(inventoryStatement.purchaseAmt + (product.amount - product.gstAmount), 2)
                        inventoryStatement.purchaseQty = purchaseQty
                        inventoryStatement.purchaseAmt = purchaseAmt
                    } else {
                        JSONObject productDetail = new ProductService().getProductById(productId.toString())
                        purchaseQty = product.sqty + product.freeQty
                        purchaseAmt = UtilsService.round((product.amount - product.gstAmount), 2)
                        inventoryStatement = new InventoryStatement()
                        inventoryStatement.productId = productId
                        inventoryStatement.productName = productDetail?.productName
                        inventoryStatement.packing = productDetail?.unitPacking
                        inventoryStatement.openingQty = openingQty
                        inventoryStatement.openingAmt = openingAmt
                        inventoryStatement.purchaseQty = purchaseQty
                        inventoryStatement.purchaseAmt = purchaseAmt
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                        productList.put(productId, inventoryStatement)
                    }
                }
            }

            //purchase return
            for (Object purchaseReturn : purchaseReturns) {
                long openingQty = 0
                double openingAmt = 0
                long purchaseQty = 0
                double purchaseAmt = 0
                long saleQty = 0
                double saleAmt = 0
                InventoryStatement inventoryStatement = null
                for (Object product : purchaseReturn.products) {
                    long productId = product.productId
                    if (productList.containsKey(productId)) {
                        inventoryStatement = productList.get(productId) as InventoryStatement
                        purchaseQty = inventoryStatement.purchaseQty - (product.sqty + product.freeQty)
                        purchaseAmt = UtilsService.round(inventoryStatement.purchaseAmt + (product.amount - product.gstAmount), 2)
                        inventoryStatement.purchaseQty = purchaseQty
                        inventoryStatement.purchaseAmt = purchaseAmt
                    } else {
                        JSONObject productDetail = new ProductService().getProductById(productId.toString())
                        purchaseQty = product.sqty + product.freeQty
                        purchaseAmt = UtilsService.round((product.amount - product.gstAmount), 2)
                        inventoryStatement = new InventoryStatement()
                        inventoryStatement.productId = productId
                        inventoryStatement.productName = productDetail?.productName
                        inventoryStatement.packing = productDetail?.unitPacking
                        inventoryStatement.openingQty = openingQty
                        inventoryStatement.openingAmt = openingAmt
                        inventoryStatement.purchaseQty = -purchaseQty //remove from stocks
                        inventoryStatement.purchaseAmt = purchaseAmt
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                        productList.put(productId, inventoryStatement)
                    }
                }
            }

            //get stocks
            JSONArray stockActivities = new InventoryService().getClosingStock(dateRange.split("-")[0].trim().toString(), Long.parseLong(entityId), productList.keySet(), productBatches)


            //set opening stocks
            for (Object stocksActivity : stockActivities) {
                long productId = stocksActivity.productId
                long openingQty = 0
                double openingAmt = 0
                long purchaseQty = 0
                double purchaseAmt = 0
                long saleQty = 0
                double saleAmt = 0
                InventoryStatement inventoryStatement = null
                if (productList.containsKey(productId)) {
                    inventoryStatement = productList.get(productId)
                    def btchs = productBatches.values()
                    if (btchs.contains(stocksActivity.batch)) {
                        openingQty = (stocksActivity.remainingQty + stocksActivity.remainingSchemeQty)
                        openingAmt = (UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2))
                    } else {
                        openingQty = inventoryStatement.openingQty + (stocksActivity.remainingQty + stocksActivity.remainingSchemeQty)
                        openingAmt = inventoryStatement.openingAmt + (UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2))
                    }
                    inventoryStatement.openingQty = openingQty
                    inventoryStatement.openingAmt = UtilsService.round(openingAmt, 2)
                } else {
                    JSONObject productDetail = new ProductService().getProductById(productId.toString())
                    openingQty = stocksActivity.remainingQty + stocksActivity.remainingSchemeQty
                    openingAmt = UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2)
                    inventoryStatement = new InventoryStatement()
                    inventoryStatement.productId = productId
                    inventoryStatement.productName = productDetail?.productName
                    inventoryStatement.packing = productDetail?.unitPacking
                    inventoryStatement.openingQty = openingQty
                    inventoryStatement.openingAmt = openingAmt
                    inventoryStatement.purchaseQty = purchaseQty
                    inventoryStatement.purchaseAmt = purchaseAmt
                    inventoryStatement.saleQty = saleQty
                    inventoryStatement.saleAmt = saleAmt
                    productList.put(productId, inventoryStatement)
                }
            }
            inventoryStatements.addAll(productList.values())
            //sort alphabetically
            Collections.sort(inventoryStatements, new Comparator<InventoryStatement>() {
                public int compare(InventoryStatement i1, InventoryStatement i2) {
                    if(i1.getProductName() == null)
                    {
                        println(i1)
                    }
                    return i1.getProductName().compareTo(i2.getProductName())
                }
            });

            respond inventoryStatements, formats: ['json']
        }
        catch (Exception ex) {
            System.out.print(ex)
        }
    }

    def expiryReport() {
        render(view: '/reports/inventoryReport/expiry')
    }

    def generateExpiryReport() {
        try {
            String dateFrom = params.dateFrom
            String dateTo = params.dateTo
            String productids = params.productids
            String groupids = params.groupids
            String supplierids = params.supplierids
            String companyids = params.companyids
            long entityId = Long.parseLong(session.getAttribute("entityId").toString())
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM")
            Date fromDate = sdf.parse(dateFrom)
            Date toDate = sdf.parse(dateTo)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            String fromDateStr = calendar.getTime().format("dd/MM/yyyy HH:mm:ss")
            calendar.setTime(toDate)
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            calendar.set(Calendar.DAY_OF_MONTH, maxDay)
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 59)
            calendar.set(Calendar.SECOND, 59)
            String toDateStr = calendar.getTime().format("dd/MM/yyyy HH:mm:ss")
            JSONObject groupedObject = new JSONObject()
            JSONArray stocks = new InventoryService().getExpiryReport(fromDateStr, toDateStr, entityId)
            for (JSONObject stock : stocks) {

                String expDate = stock.get("expDate")
                expDate = expDate.split("T")[0]
                expDate = new SimpleDateFormat("yyyy-MM-dd").parse(expDate).format("MMM-yyyy")
                stock.put("expDate", expDate)

                if (productids != null && productids != "null") {
                    String[] productIds = productids.split(",")
                    if (productIds.contains(stock.productId.toString())) {
                        def batch = new ProductService().getByBatchAndProductId(stock.batchNumber, stock.productId.toString())
                        stock.put("productName", batch.product.productName)

                        if (groupedObject.containsKey(batch.product.productName)) {
                            JSONArray productWiseArray = groupedObject.get(batch.product.productName)
                            productWiseArray.add(stock)
                            groupedObject.put(batch.product.productName, productWiseArray)
                        } else {
                            JSONArray productWiseArray = new JSONArray()
                            productWiseArray.add(stock)
                            groupedObject.put(batch.product.productName, productWiseArray)
                        }
                    }
                } else if (groupids != null && groupids != "null") {
                    String[] groupIds = groupids.split(",")
                    def batch = new ProductService().getByBatchAndProductId(stock.batchNumber, stock.productId.toString())
                    if (groupIds.contains(batch?.product?.group?.id?.toString())) {
                        stock.put("productName", batch.product.productName)

                        def productGroup = new ProductService().getProductGroupById(batch?.product?.group?.id?.toString())
                        if (groupedObject.containsKey(productGroup.groupName)) {
                            JSONArray groupWiseArray = groupedObject.get(productGroup.groupName)
                            groupWiseArray.add(stock)
                            groupedObject.put(productGroup.groupName, groupWiseArray)
                        } else {
                            JSONArray groupWiseArray = new JSONArray()
                            groupWiseArray.add(stock)
                            groupedObject.put(productGroup.groupName, groupWiseArray)
                        }
                    }
                } else if (companyids != null && companyids != "null") {
                    def batch = new ProductService().getByBatchAndProductId(stock.batchNumber, stock.productId.toString())
                    String[] companyIds = companyids.split(",")
                    if (companyIds.contains(batch?.product?.mktCompanyId?.toString())) {
                        stock.put("productName", batch.product.productName)
                        def entity = new EntityService().getEntityById(batch?.product?.mktCompanyId?.toString())
                        if (groupedObject.containsKey(entity.entityName)) {
                            JSONArray companyWiseArray = groupedObject.get(entity.entityName)
                            companyWiseArray.add(stock)
                            groupedObject.put(entity.entityName, companyWiseArray)
                        } else {
                            JSONArray companyWiseArray = new JSONArray()
                            companyWiseArray.add(stock)
                            groupedObject.put(entity.entityName, companyWiseArray)
                        }
                    }
                } else if (supplierids != null && supplierids != "null") {
                    String[] supplierIds = supplierids.split(",")
                    if (supplierIds.contains(stock?.supplierId?.toString())) {
                        def batch = new ProductService().getByBatchAndProductId(stock.batchNumber, stock.productId.toString())
                        stock.put("productName", batch.product.productName)
                        def entity = new EntityService().getEntityById(stock?.supplierId?.toString())
                        if (groupedObject.containsKey(entity.entityName)) {
                            JSONArray supplierWiseArray = groupedObject.get(entity.entityName)
                            supplierWiseArray.add(stock)
                            groupedObject.put(entity.entityName, supplierWiseArray)
                        } else {
                            JSONArray supplierWiseArray = new JSONArray()
                            supplierWiseArray.add(stock)
                            groupedObject.put(entity.entityName, supplierWiseArray)
                        }
                    }
                } else {
                    def batch = new ProductService().getByBatchAndProductId(stock.batchNumber, stock.productId.toString())
                    stock.put("productName", batch.product.productName)
                    if (groupedObject.containsKey("ALL")) {
                        JSONArray allArray = groupedObject.get("ALL")
                        allArray.add(stock)
                        groupedObject.put("ALL", allArray)
                    } else {
                        JSONArray allArray = new JSONArray()
                        allArray.add(stock)
                        groupedObject.put("ALL", allArray)
                    }
                }
            }
            //respond jsonArray, formats: ['json']
            respond groupedObject, formats: ['json']
        }
        catch (Exception ex) {
            System.out.print(ex)
        }
    }

    def stockReport() {
        render(view: '/reports/inventoryReport/stockreport')
    }

    def getStockReport() {
        JSONArray stockReport = new JSONArray()
        JSONArray stockBooks = new InventoryService().getStockBookByEntity(session.getAttribute("entityId"))
        for (JSONObject stockBook : stockBooks) {
            JSONObject product = new ProductService().getProductById(stockBook.productId.toString())
            stockBook.put("product", product)
            def taxResponse = new EntityService().getTaxRegister(stockBook.taxId.toString())
            if(taxResponse.status == 200) {
                JSONObject tax = new JSONObject(taxResponse.readEntity(String.class))
                stockBook.put("tax", tax)
            }
            stockReport.add(stockBook)

        }
        respond stockReport, formats: ['json']
    }
}

class InventoryStatement {
    long productId
    String productName
    String packing
    long openingQty
    double openingAmt
    long purchaseQty
    double purchaseAmt
    long saleQty
    double saleAmt
}
