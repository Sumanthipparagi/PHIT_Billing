package phitb_ui.reports

import com.lowagie.text.html.simpleparser.ALink
import grails.converters.JSON
import org.grails.datastore.mapping.query.Query
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController

class InventoryReportController
{

    def index()
    {
    }

    def statement()
    {
        String entityId = session.getAttribute("entityId")
        JSONObject loggedInEntity = new EntityService().getEntityById(entityId)
        ArrayList entities = new ArrayList()
        //entities = new EntityRegisterController().getByAffiliates(entityId) as ArrayList
        entities[0] = loggedInEntity
        render(view: '/reports/inventoryReport/statement', model: [entities: entities])
    }

    def getStatement()
    {
        try
        {
            String entityId = session.getAttribute("entityId")
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
            //get stocks
            JSONArray stockActivity = new InventoryService().getStockActivityDateRangeAndEntity(dateRange, Long.parseLong(entityId))
            ArrayList<InventoryStatement> inventoryStatements = new ArrayList<>()
            for (Object stock : stockActivity)
            {
                JSONObject product = new ProductService().getProductById(stock.productId.toString())
                InventoryStatement inventoryStatement = new InventoryStatement()
//                if (inventoryStatements.size() == 0)
//                {
                inventoryStatement.productId = stock.productId
                inventoryStatement.productName = product.productName
                inventoryStatement.packing = product.unitPacking
                inventoryStatement.openingQty = stock.remainingQty
                inventoryStatement.openingAmt = stock.saleRate
                for (JSONObject saleinv : saleInvoices)
                {
                    for (JSONObject saleproducts : saleinv.products)
                    {
                        if (stock.productId == saleproducts.productId)
                        {
                            inventoryStatement.saleQty = saleproducts.sqty
                            inventoryStatement.saleAmt = saleproducts.mrp
                        }
                    }
                }

                for (JSONObject salereturn : saleReturns)
                {
                    for (JSONObject salereturnproducts : salereturn.products)
                    {
                        if (stock.productId == salereturnproducts.productId)
                        {
                            inventoryStatement.saleReturnQty = salereturnproducts.sqty
                            inventoryStatement.saleReturnAmt = salereturnproducts.mrp
                        }
                    }
                }


//                for (JSONObject purchaseinv : purchaseInvoices)
//                {
//                    for (JSONObject purchaseproducts : purchaseinv.products)
//                    {
//                        if (stock.productId == purchaseproducts.productId)
//                        {
//                            inventoryStatement.purchaseQty = purchaseproducts.sqty
//                            inventoryStatement.purchaseAmt = purchaseproducts.mrp
//                        }
//                    }
//                }

//                for (JSONObject purchaseReturn : purchaseReturns)
//                {
//                    for (JSONObject purchasereturnProducts : purchaseReturns.products)
//                    {
//                        if (stock.productId == purchasereturnProducts.productId)
//                        {
//                            inventoryStatement.purchaseReturnQty = purchasereturnProducts.sqty
//                            inventoryStatement.purchaseReturnAmt = purchasereturnProducts.mrp
//                        }
//                    }
//                }
                inventoryStatements.add(inventoryStatement)
//                }
//                else
//                {
//                    if (inventoryStatements.productId.contains(stock.productId))
//                    {
////                        for(JSONObject saleinv: saleInvoices)
////                        {
////                            inventoryStatement.saleQty = saleinv.sqty
////                            inventoryStatement.saleAmt = saleinv.saleAmt
////                            inventoryStatements.add(inventoryStatement)
////                        }
//                    }
//                }
            }
            respond inventoryStatements, formats: ['json']
        }
        catch (Exception ex)
        {
            System.out.print(ex)
        }
    }
}

class InventoryStatement
{
    long productId
    String productName
    String packing
    long openingQty
    double openingAmt
    long purchaseQty
    long purchaseReturnQty
    long purchaseReturnAmt
    double purchaseAmt
    long saleQty
    long saleReturnQty
    long saleReturnAmt
    double saleAmt
}
