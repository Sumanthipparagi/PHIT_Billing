package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleRetrunController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def reason = new SalesService().getReason()
        ArrayList<String> salesmanList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
            {
                salesmanList.add(it)
            }
        }
        render(view: '/sales/saleRetrun/sale-returns', model: [customers                   : customers, divisions: divisions, series: series,
                                                               salesmanList                : salesmanList, priorityList:
                                                                       priorityList, reason: reason])
    }


    def getSaleBillByCustomer()
    {
        def salebills = new SalesService().getSaleBillByCustomer(params.custid)
        def apiResponse = new SalesService().getRequestWithIdList(salebills.id, new Links().SALE_PRODUCT_OF_BILL2)
        def prod = JSON.parse(apiResponse.readEntity(String.class))
        prod.each {product ->
            def index = salebills.findIndexOf({
                it.id == product.billId
            })
            if(index!= -1)
            product.put("billId", salebills[index])
        }
        respond prod, formats: ['json'], status: 200
    }

    def saveSaleReturn()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleReturnDetails = new JSONObject()
        JSONArray saleProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String message = params.message
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
        println(saleData)
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
        saleReturnDetails.put("serBillId", serBillId)
        saleReturnDetails.put("customerId", customerId)
        saleReturnDetails.put("customerNumber", 0) //TODO: to be changed
        saleReturnDetails.put("finId", finId)
        saleReturnDetails.put("seriesId", seriesId)
        saleReturnDetails.put("priorityId", priorityId)
        saleReturnDetails.put("financialYear", financialYear)
        saleReturnDetails.put("dueDate", duedate)
        saleReturnDetails.put("transportTypeId", 1)
        saleReturnDetails.put("salesmanId", 1)
        saleReturnDetails.put("orderValidity", "20/01/2021")
        saleReturnDetails.put("orderId", 1)
        saleReturnDetails.put("orderMechanism", "1")
        saleReturnDetails.put("orderMechanism", "1")
        saleReturnDetails.put("gstId", 1)
        saleReturnDetails.put("refNumber", 1)
        saleReturnDetails.put("purchaseQuotationId", 1)
        saleReturnDetails.put("userId", session.getAttribute("userId"))
        saleReturnDetails.put("entryDate", entryDate)
        saleReturnDetails.put("orderDate", orderDate)
        saleReturnDetails.put("refDate", orderDate)
        saleReturnDetails.put("totalEstimate", 1)
        saleReturnDetails.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleReturnDetails.put("salesmanId", "0") //TODO: to be changed
        saleReturnDetails.put("salesmanComm", "0") //TODO: to be changed
        saleReturnDetails.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleReturnDetails.put("deliveryManId", "0") //TODO: to be changed
        saleReturnDetails.put("accountModeId", "0") //TODO: to be changed
        saleReturnDetails.put("totalSqty", totalSqty)
        saleReturnDetails.put("totalFqty", totalFqty)
        saleReturnDetails.put("totalGst", totalGst)
        saleReturnDetails.put("totalSgst", totalSgst)
        saleReturnDetails.put("totalCgst", totalCgst)
        saleReturnDetails.put("totalIgst", totalIgst)
        saleReturnDetails.put("supplierContact", totalIgst)
        saleReturnDetails.put("totalQty", totalSqty + totalFqty)
        saleReturnDetails.put("totalItems", totalSqty + totalFqty)
        saleReturnDetails.put("totalDiscount", totalDiscount)
        saleReturnDetails.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleReturnDetails.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleReturnDetails.put("totalAmount", totalAmount)
        saleReturnDetails.put("balance", totalAmount)
        saleReturnDetails.put("entityId", entityId)
        saleReturnDetails.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleReturnDetails.put("createdUser", session.getAttribute("userId"))
        saleReturnDetails.put("modifiedUser", session.getAttribute("userId"))
        saleReturnDetails.put("message", message) //TODO: to be changed
        saleReturnDetails.put("gstStatus", "0") //TODO: to be changed
        saleReturnDetails.put("billStatus", billStatus)
        saleReturnDetails.put("lockStatus", 0) //TODO: to be changed
        saleReturnDetails.put("syncStatus", "0") //TODO: to be changed
        saleReturnDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleReturnDetails.put("creditIds", "0") //TODO: to be changed
        saleReturnDetails.put("referralDoctor", "0") //TODO: to be changed
        saleReturnDetails.put("taxable", "1") //TODO: to be changed
        saleReturnDetails.put("cashDiscount", 0) //TODO: to be changed
        saleReturnDetails.put("exempted", 0) //TODO: to be changed
        Response response = new SalesService().saveSaleRetrun(saleReturnDetails)
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
                String expDate = stockBook.get("expDate").toString().split("T")[0]
                String purcDate = stockBook.get("purcDate").toString().split("T")[0]
                String manufacturingDate = stockBook.get("manufacturingDate").toString().split("T")[0]
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd")
                expDate = sdf1.parse(expDate).format("dd/MM/yyyy")
                purcDate = sdf1.parse(purcDate).format("dd/MM/yyyy")
                manufacturingDate = sdf1.parse(manufacturingDate).format("dd/MM/yyyy")
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
