package phitb_ui.reports

import com.lowagie.text.html.simpleparser.ALink
import org.grails.datastore.mapping.query.Query
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController

class InventoryReportController {

    def index() { }

    def statement()
    {
        String entityId = session.getAttribute("entityId")
        JSONObject loggedInEntity = new EntityService().getEntityById(entityId)
        ArrayList entities = new ArrayList()
        //entities = new EntityRegisterController().getByAffiliates(entityId) as ArrayList
        entities[0] = loggedInEntity
        render(view: '/reports/inventoryReport/statement', model: [entities:entities])
    }

    def getStatement()
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

        //get stocks
        JSONArray stocks = new InventoryService().getStockBookByEntity(Long.parseLong(entityId))

        ArrayList<InventoryStatement> inventoryStatements = new ArrayList<>()
        for (Object stock : stocks) {
            JSONObject product = new ProductService().getProductById(stock.productId)
            InventoryStatement inventoryStatement = new InventoryStatement()
            if(inventoryStatements.size() == 0)
            {
                inventoryStatement.productId = stock.productId
                inventoryStatement.productName = product.productName
                inventoryStatements.add(inventoryStatement)
            }
            else
            {
                if(inventoryStatements.productId.contains(stock.productId))
                {

                }
                else
                {

                }
            }
        }

        respond inventoryStatements,  formats: ['json']

    }

    class InventoryStatement{
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
}
