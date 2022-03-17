package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.InventoryService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.Constants
import phitb_ui.ProductService
import phitb_ui.system.AccountModeController
import phitb_ui.system.PriorityController

class SaleEntryController {

    def index() {

        String entityId = session.getAttribute("entityId")?.toString()
        ArrayList<String> series = new SeriesController().getByEntity(entityId) as ArrayList<String>
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        def products = new ProductService().getProductsByEntityId(entityId) //TODO: this should be called from ajax
        render(view: '/sales/sale-entry', model: [series : series, customers: customers,
                                                           salesmanList: salesmanList, priorityList:priorityList,
                                                           products    :products])
    }

    def saveSaleEntry()
    {
        String customerId = params.customer
        String seriesId = params.series
        String duedate = params.duedate
        JSONArray saleData = new JSONArray(params.saleData)
        println(saleData);
        for (JSONObject sale : saleData) {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            String discount = sale.get("8")
            String packDesc = sale.get("9")
            String gst = sale.get("10")
            String value = sale.get("11")
            String sgst = sale.get("12")
            String cgst = sale.get("13")
            String igst = sale.get("14")
            String tempStockRowId = sale.get("15")

            new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
        }
    }

    def saleBill()
    {
        render(view: '/sales/salebillDetails/saleBill')
    }

    def saleRetrun()
    {
        render(view: '/sales/saleReturn')
    }

    def crdDebS()
    {
        render(view:"/sales/credit-debit-settlement")
    }

    def DebJV()
    {
        render(view:"/sales/debit-jv")
    }

    def credJV()
    {
        render(view:'/sales/credit-jv')
    }

    def goodsSalesRecipt()
    {
        render(view:"/sales/goods-sales-recipt")
    }

    def paymentVocher()
    {
        render(view:"/sales/payment-vocher")
    }
}
