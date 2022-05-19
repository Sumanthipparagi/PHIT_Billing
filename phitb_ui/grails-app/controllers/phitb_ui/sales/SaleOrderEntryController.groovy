package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleOrderEntryController {

    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        render(view: '/sales/saleOrderEntry/sale-order',model: [divisions:divisions,customers:customers, priorityList:priorityList,series:series])
    }

    def saveSaleOrder()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleOrderDetails = new JSONObject()
        JSONArray saleProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String message = params.message
        String uuid = params.uuid
        if(!message)
            message = "NA"
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if(!billStatus.equalsIgnoreCase("DRAFT"))
        {
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            if(recentSaleBill != null)
            {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
            }
            else {
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
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble(discount)
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
            saleProductDetail.put("uuid", uuid)
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
            saleProductDetail.put("gstPercentage", sale.get("16").toString())
            saleProductDetail.put("sgstPercentage", sale.get("17").toString())
            saleProductDetail.put("cgstPercentage", sale.get("18").toString())
            saleProductDetail.put("igstPercentage", sale.get("19").toString())
            saleProductDetail.put("", financialYear)
            saleProductDetail.put("entityId", entityId)
            saleProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleProductDetails.add(saleProductDetail)
            //save to sale transaction log
            //save to sale transportation details
        }

        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleOrderDetails.put("serBillId", serBillId)
        saleOrderDetails.put("customerId", customerId)
        saleOrderDetails.put("customerNumber", 0) //TODO: to be changed
        saleOrderDetails.put("finId", finId)
        saleOrderDetails.put("seriesId", seriesId)
        saleOrderDetails.put("priorityId", priorityId)
        saleOrderDetails.put("financialYear", financialYear)
        saleOrderDetails.put("dueDate", duedate)
        saleOrderDetails.put("transportTypeId", 1)
        saleOrderDetails.put("salesmanId", 1)
        saleOrderDetails.put("orderValidity", "20/01/2021")
        saleOrderDetails.put("orderId", 1)
        saleOrderDetails.put("orderMechanism", "1")
        saleOrderDetails.put("orderMechanism", "1")
        saleOrderDetails.put("gstId", 1)
        saleOrderDetails.put("refNumber", 1)
        saleOrderDetails.put("purchaseQuotationId", 1)
        saleOrderDetails.put("userId", session.getAttribute("userId"))
        saleOrderDetails.put("entryDate", entryDate)
        saleOrderDetails.put("orderDate", orderDate)
        saleOrderDetails.put("refDate", orderDate)
        saleOrderDetails.put("totalEstimate", 1)
        saleOrderDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleOrderDetails.put("salesmanId", "0") //TODO: to be changed
        saleOrderDetails.put("salesmanComm", "0") //TODO: to be changed
        saleOrderDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleOrderDetails.put("deliveryManId", "0") //TODO: to be changed
        saleOrderDetails.put("accountModeId", "0") //TODO: to be changed
        saleOrderDetails.put("totalSqty", totalSqty)
        saleOrderDetails.put("totalFqty", totalFqty)
        saleOrderDetails.put("totalGst", totalGst)
        saleOrderDetails.put("totalSgst", totalSgst)
        saleOrderDetails.put("totalCgst", totalCgst)
        saleOrderDetails.put("totalIgst", totalIgst)
        saleOrderDetails.put("totalQty", totalSqty + totalFqty)
        saleOrderDetails.put("totalItems", totalSqty + totalFqty)
        saleOrderDetails.put("totalDiscount", totalDiscount)
        saleOrderDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleOrderDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleOrderDetails.put("totalAmount", totalAmount)
        saleOrderDetails.put("balance", totalAmount)
        saleOrderDetails.put("entityId", entityId)
        saleOrderDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleOrderDetails.put("createdUser", session.getAttribute("userId"))
        saleOrderDetails.put("modifiedUser", session.getAttribute("userId"))
        saleOrderDetails.put("message", message) //TODO: to be changed
        saleOrderDetails.put("gstStatus", "0") //TODO: to be changed
        saleOrderDetails.put("billStatus", billStatus)
        saleOrderDetails.put("lockStatus", 0) //TODO: to be changed
        saleOrderDetails.put("syncStatus", "0") //TODO: to be changed
        saleOrderDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleOrderDetails.put("creditIds", "0") //TODO: to be changed
        saleOrderDetails.put("referralDoctor", "0") //TODO: to be changed
        saleOrderDetails.put("taxable", "1") //TODO: to be changed
        saleOrderDetails.put("cashDiscount", 0) //TODO: to be changed
        saleOrderDetails.put("exempted", 0) //TODO: to be changed
        Response response = new SalesService().saveSaleOrder(saleOrderDetails)
        if(response.status == 200)
        {
            def saleOrder = new JSONObject(response.readEntity(String.class))
            //save to sale product details
            for (JSONObject saleProductDetail : saleProductDetails) {
                saleProductDetail.put("billId",saleOrder.get("id"))
                saleProductDetail.put("billType",0) //0 Sale, 1 Purchase
                saleProductDetail.put("serBillId",saleOrder.get("serBillId"))
                saleProductDetail.put("saleFinId",saleOrder.get("finId"))
                def resp = new SalesService().saveSaleProductDetail(saleProductDetail)
                if(resp.status == 200)
                    println("Product Detail Saved")
                else {
                    println("Product Detail Failed")
                }
            }
            //update stockbook
            for (JSONObject sale : saleData) {
                String tempStockRowId = sale.get("15")
                def tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(tempStockRowId))
                def stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
                stockBook.put("remainingQty", tmpStockBook.get("remainingQty"))
                stockBook.put("remainingFreeQty", tmpStockBook.get("remainingFreeQty"))
                stockBook.put("remainingReplQty", tmpStockBook.get("remainingReplQty"))
                stockBook.put("mergedWith", 0)
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
                def apiRes = new InventoryService().updateStockBook(stockBook)
                if(apiRes.status == 200) {
                    //clear tempstockbook
                    apiRes = new InventoryService().deleteTempStock(tempStockRowId)
                    if(apiRes.status == 200) {
                        JSONObject responseJson = new JSONObject()
                        responseJson.put("series", series)
                        responseJson.put("saleBillDetail", saleOrder)
                        respond responseJson, formats: ['json']
                    }
                }
            }
            response.status == 400
        }
        else
        {
            response.status == 400
        }
    }
}
