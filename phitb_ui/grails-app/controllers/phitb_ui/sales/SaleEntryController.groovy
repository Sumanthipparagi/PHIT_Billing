package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.Constants
import phitb_ui.ProductService
import phitb_ui.product.ProductController
import phitb_ui.system.AccountModeController
import phitb_ui.system.PriorityController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleBillDetails = new JSONObject()
        JSONArray saleProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String message = params.message
        if(!message)
            message = "NA"
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId)
        def series = new EntityService().getSeriesById(seriesId)
        if(recentSaleBill != null)
        {
            finId += recentSaleBill.get("finId")
            serBillId += recentSaleBill.get("serBillId")
        }
        else {
            finId = 1
            serBillId = series.get("saleId")
        }
        long totalSqty = 0
        long totalFqty = 0
        double totalAmount = 0.00
        double totalGst = 0.00
        double totalCgst = 0.00
        double totalSgst = 0.00
        double totalIgst = 0.00
        double totalDiscount = 0.00
        JSONArray saleData = new JSONArray(params.saleData)
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

            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble(discount)

            def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
            def stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
            //def product = new ProductService().getProductById(stockBook.productId.toString())

            JSONObject saleProductDetail = new JSONObject()
            saleProductDetail.put("finId", finId)
            saleProductDetail.put("billId",0)
            saleProductDetail.put("billType",0)
            saleProductDetail.put("serBillId",0)
            saleProductDetail.put("seriesId", seriesId)
            saleProductDetail.put("productId", productId)
            saleProductDetail.put("batchNumber", batchNumber)
            saleProductDetail.put("expiryDate", expDate)
            saleProductDetail.put("sqty", saleQty)
            saleProductDetail.put("freeQty", freeQty)
            saleProductDetail.put("repQty", 0)
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)
            saleProductDetail.put("gstId", 1) //TODO: to be changed
            saleProductDetail.put("amount", value)
            saleProductDetail.put("reason", "") //TODO: to be changed
            saleProductDetail.put("fridgeId", 0) //TODO: to be changed
            saleProductDetail.put("kitName", 0) //TODO: to be changed
            saleProductDetail.put("saleFinId", "") //TODO: to be changed
            saleProductDetail.put("redundantBatch", 0) //TODO: to be changed
            saleProductDetail.put("status", 0)
            saleProductDetail.put("syncStatus", 0)
            saleProductDetail.put("financialYear", financialYear)
            saleProductDetail.put("entityId", entityId)
            saleProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleProductDetails.add(saleProductDetail)

            //save to sale transaction log
            //save to sale transportation details
            //update stockbook
            //clear tempstockbook
        }

        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleBillDetails.put("serBillId", serBillId)
        saleBillDetails.put("customerId", customerId)
        saleBillDetails.put("customerNumber", 0) //TODO: to be changed
        saleBillDetails.put("finId", finId)
        saleBillDetails.put("seriesId", seriesId)
        saleBillDetails.put("priorityId", priorityId)
        saleBillDetails.put("financialYear", financialYear)
        saleBillDetails.put("dueDate", duedate)
        saleBillDetails.put("paymentStatus", 0)
        saleBillDetails.put("userId", session.getAttribute("userId"))
        saleBillDetails.put("entryDate", entryDate)
        saleBillDetails.put("orderDate", orderDate)
        saleBillDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleBillDetails.put("salesmanId", "0") //TODO: to be changed
        saleBillDetails.put("salesmanComm", "0") //TODO: to be changed
        saleBillDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleBillDetails.put("deliveryManId", "0") //TODO: to be changed
        saleBillDetails.put("accountModeId", "0") //TODO: to be changed
        saleBillDetails.put("totalSqty", totalSqty)
        saleBillDetails.put("totalFqty", totalFqty)
        saleBillDetails.put("totalGst", totalGst)
        saleBillDetails.put("totalSgst", totalSgst)
        saleBillDetails.put("totalCgst", totalCgst)
        saleBillDetails.put("totalIgst", totalIgst)
        saleBillDetails.put("totalQty", totalSqty + totalFqty)
        saleBillDetails.put("totalItems", totalSqty + totalFqty)
        saleBillDetails.put("totalDiscount", totalDiscount)
        saleBillDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleBillDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleBillDetails.put("totalAmount", totalAmount)
        saleBillDetails.put("balance", totalAmount)
        saleBillDetails.put("entityId", entityId)
        saleBillDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleBillDetails.put("createdUser", session.getAttribute("userId"))
        saleBillDetails.put("modifiedUser", session.getAttribute("userId"))
        saleBillDetails.put("message", message) //TODO: to be changed
        saleBillDetails.put("gstStatus", "0") //TODO: to be changed
        saleBillDetails.put("billStatus", "ACTIVE") //TODO: to be changed
        saleBillDetails.put("lockStatus", 0) //TODO: to be changed
        saleBillDetails.put("syncStatus", "0") //TODO: to be changed
        saleBillDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleBillDetails.put("creditIds", "0") //TODO: to be changed
        saleBillDetails.put("referralDoctor", "0") //TODO: to be changed
        saleBillDetails.put("taxable", "1") //TODO: to be changed
        saleBillDetails.put("cashDiscount", 0) //TODO: to be changed
        saleBillDetails.put("exempted", 0) //TODO: to be changed

        Response response = new SalesService().saveSaleBill(saleBillDetails)
        if(response.status == 200)
        {
            def saleBillDetail = new JSONObject(response.readEntity(String.class))
            //save to sale product details
            for (JSONObject saleProductDetail : saleProductDetails) {
                saleProductDetail.put("billId",saleBillDetail.get("id"))
                saleProductDetail.put("billType",0) //0 Sale, 1 Purchase
                saleProductDetail.put("serBillId",saleBillDetail.get("serBillId"))
                def resp = new SalesService().saveSaleProductDetail(saleProductDetail)

                if(resp.status == 200)
                    println("Product Detail Saved")
                else {
                    println("Product Detail Failed")
                }
            }
            respond saleBillDetail, formats: ['json']
        }
        else
        {
            response.status == 400
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
