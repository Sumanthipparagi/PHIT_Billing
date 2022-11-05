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
                if(apiResponse.status == 200)
                {
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
                        println("exists: " + productId)
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
                        inventoryStatement.productName = productDetail.productName
                        inventoryStatement.packing = productDetail.unitPacking
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
                        println("return exists: " + productId)
                        inventoryStatement = productList.get(productId) as InventoryStatement
                        saleQty = inventoryStatement.saleQty + (product.sqty + product.freeQty)
                        saleAmt = UtilsService.round(inventoryStatement.saleAmt + (product.amount - product.gstAmount), 2)
                        inventoryStatement.saleQty = saleQty
                        inventoryStatement.saleAmt = saleAmt
                    } else {
                        println("return not exists: " + productId)
                        JSONObject productDetail = new ProductService().getProductById(productId.toString())
                        saleQty = product.sqty + product.freeQty
                        saleAmt = UtilsService.round((product.amount - product.gstAmount), 2)
                        inventoryStatement = new InventoryStatement()
                        inventoryStatement.productId = productId
                        inventoryStatement.productName = productDetail.productName
                        inventoryStatement.packing = productDetail.unitPacking
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
                        println("purchase exists: " + productId)
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
                        inventoryStatement.productName = productDetail.productName
                        inventoryStatement.packing = productDetail.unitPacking
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
                        println("purchase return exists: " + productId)
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
                        inventoryStatement.productName = productDetail.productName
                        inventoryStatement.packing = productDetail.unitPacking
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
                if(productList.containsKey(productId))
                {
                    inventoryStatement = productList.get(productId)
                    def btchs = productBatches.values()
                    if(btchs.contains(stocksActivity.batch))
                    {
                        openingQty = (stocksActivity.remainingQty + stocksActivity.remainingSchemeQty)
                        openingAmt = (UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2))
                    }
                    else
                    {
                        openingQty = inventoryStatement.openingQty + (stocksActivity.remainingQty + stocksActivity.remainingSchemeQty)
                        openingAmt = inventoryStatement.openingAmt + (UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2))
                    }
                    inventoryStatement.openingQty = openingQty
                    inventoryStatement.openingAmt = UtilsService.round(openingAmt,2)
                }
                else
                {
                    JSONObject productDetail = new ProductService().getProductById(productId.toString())
                    openingQty = stocksActivity.remainingQty + stocksActivity.remainingSchemeQty
                    openingAmt = UtilsService.round((stocksActivity.saleRate * stocksActivity.remainingQty), 2)
                    inventoryStatement = new InventoryStatement()
                    inventoryStatement.productId = productId
                    inventoryStatement.productName = productDetail.productName
                    inventoryStatement.packing = productDetail.unitPacking
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
                    return i1.getProductName().compareTo(i2.getProductName())
                }
            });

            respond inventoryStatements, formats: ['json']
        }
        catch (Exception ex) {
            System.out.print(ex)
        }
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
