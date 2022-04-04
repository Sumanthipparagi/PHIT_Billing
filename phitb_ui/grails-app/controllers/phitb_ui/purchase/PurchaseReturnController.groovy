package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class PurchaseReturnController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> supplier = new EntityRegisterController().show() as ArrayList<String>
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
        render(view: '/purchase/purchaseReturn/purchase-return', model: [supplier: supplier,
                                                                         divisions: divisions, series:series,
                                                                         salesmanList: salesmanList,
                                                                         priorityList:priorityList, reason: reason])
    }

    def getPurchaseBillBySupplier()
    {
        def purchasebills = new PurchaseService().getPurchaseBillBySupplier(params.supplierId)
        def apiResponse = new PurchaseService().getRequestWithIdList(purchasebills.id, new Links().PURCHASE_PRODUCT_OF_BILLIDS)
        if(apiResponse?.status == 200)
        {
            def prod = JSON.parse(apiResponse.readEntity(String.class))
            prod.each {product ->
                def index = purchasebills.findIndexOf({
                    it.id == product.billId
                })
                if (index != -1)
                    product.put("billId", purchasebills[index])
            }
            respond prod, formats: ['json'], status: 200
        }
        else
        {
            return []
        }
    }

    def savePurchaseReturn()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject purReturnDetail = new JSONObject()

        String entityId = session.getAttribute("entityId").toString()
        String supplierId = params.supplierId
        String salesmanId = params.salesmanId
        String priorityId = params.priority
        String seriesId = params.series
        String dispatchDate = params.dispatchDate
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
            def recentPurchaseBill = new PurchaseService().getRecentPurchaseBill(financialYear, entityId, billStatus)
            if(recentPurchaseBill != null)
            {
                finId = Long.parseLong(recentPurchaseBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentPurchaseBill.get("serBillId").toString()) + 1
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
        String entryDate = sdf.format(new Date())
        println(params.saleData)
        JSONArray saleData = new JSONArray(params.saleData)
        for (JSONArray sale : saleData) {
            String productId = sale[2]
            String batchNumber = sale[3]
            String purQty = sale[4]
            String freeQty = sale[5]
            String saleRate = sale[6]
            String mrp = sale[7]
            String value = sale[10]
            String gst = sale[9]
            String igst = sale[13]
            String cgst = sale[12]
            String sgst = sale[11]
            totalSqty += Long.parseLong(purQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble("0")
            purReturnDetail.put("finId", finId)
            purReturnDetail.put("billId",0)
            purReturnDetail.put("billType",0)
            purReturnDetail.put("serBillId",0)
            purReturnDetail.put("series", seriesId)
            purReturnDetail.put("productId", productId)
            purReturnDetail.put("batchNumber", batchNumber)
            purReturnDetail.put("sqty", purQty)
            purReturnDetail.put("supplierId", 1)
            purReturnDetail.put("salesmanId", salesmanId)
            purReturnDetail.put("dispatchDate", dispatchDate)
            purReturnDetail.put("totalDiscount", totalDiscount)
            purReturnDetail.put("freeQty", freeQty)
            purReturnDetail.put("entryDate", entryDate)
            purReturnDetail.put("refId", 0) //TODO: to be changed
            purReturnDetail.put("sRate", saleRate)
            purReturnDetail.put("mrp", mrp)
            purReturnDetail.put("gstAmount", gst)
            purReturnDetail.put("maxDnAmount", 1) //TODO: to be changed
            purReturnDetail.put("amount", value)
            purReturnDetail.put("supplierContact", 1) //TODO: to be changed
            purReturnDetail.put("totalGst", totalGst) //TODO: to be changed
            purReturnDetail.put("totalCgst", totalCgst) //TODO: to be changed
            purReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            purReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            purReturnDetail.put("totalIgst", totalIgst) //TODO: to be changed
            purReturnDetail.put("exempted", 0) //TODO: to be changed
            purReturnDetail.put("type", 0) //TODO: to be changed
            purReturnDetail.put("cashDiscount", 0) //TODO: to be changed
            purReturnDetail.put("items", 0) //TODO: to be changed
            purReturnDetail.put("quantity", 0) //TODO: to be changed
            purReturnDetail.put("totalAmount", totalAmount) //TODO: to be changed
            purReturnDetail.put("balance", totalAmount) //TODO: to be changed
            purReturnDetail.put("dbAdjAmount", 0) //TODO: to be changed
            purReturnDetail.put("debitIds", 0) //TODO: to be changed
            purReturnDetail.put("debitIds", 0) //TODO: to be changed
            purReturnDetail.put("supplierEmail", 0) //TODO: to be changed
            purReturnDetail.put("gross", 0) //TODO: to be changed
            purReturnDetail.put("taxable", 0) //TODO: to be changed
            purReturnDetail.put("saleFinId", "") //TODO: to be changed
            purReturnDetail.put("lockStatus", 0) //TODO: to be changed
            purReturnDetail.put("adjustmentStatus", 0) //TODO: to be changed
            purReturnDetail.put("message", 0)
            purReturnDetail.put("ignoreSold", 0)
            purReturnDetail.put("status", 0)
            purReturnDetail.put("syncStatus", 0)
            purReturnDetail.put("financialYear", financialYear)
            purReturnDetail.put("", financialYear)
            purReturnDetail.put("createdUser", 1)
            purReturnDetail.put("modifiedUser", 1)
            purReturnDetail.put("entityId", entityId)
            purReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //save to sale transaction log
            //save to sale transportation details
        }
        Response response = new PurchaseService().savePurchaseRetrun(purReturnDetail)
        if(response.status == 200)
        {
            def saleOrder = new JSONObject(response.readEntity(String.class))
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("purReturnDetail", saleOrder)
            respond responseJson, formats: ['json'],status: 200
        }
        else
        {
            response.status == 400
        }
    }


    def getPurchaseBillByCustomer()
    {
        def salebills = new SalesService().getSaleBillByCustomer(params.custid)
        def apiResponse = new PurchaseService().getRequestWithIdList(salebills.id, new Links().PURCHASE_PRODUCT_OF_BILLIDS)
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
}
