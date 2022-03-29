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
        JSONObject saleReturnDetail = new JSONObject()

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
            String saleQty = sale[4]
            String freeQty = sale[5]
            String saleRate = sale[6]
            String mrp = sale[7]
            String value = sale[10]
            String gst = sale[9]

            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble("0")
            totalCgst += Double.parseDouble("0")
            totalIgst += Double.parseDouble("0")
            totalDiscount += Double.parseDouble("0")
            saleReturnDetail.put("finId", finId)
            saleReturnDetail.put("billId",0)
            saleReturnDetail.put("billType",0)
            saleReturnDetail.put("serBillId",0)
            saleReturnDetail.put("series", seriesId)
            saleReturnDetail.put("productId", productId)
            saleReturnDetail.put("batchNumber", batchNumber)
            saleReturnDetail.put("sqty", saleQty)
            saleReturnDetail.put("supplierId", 1)
            saleReturnDetail.put("salesmanId", salesmanId)
            saleReturnDetail.put("dispatchDate", dispatchDate)
            saleReturnDetail.put("totalDiscount", totalDiscount)
            saleReturnDetail.put("freeQty", freeQty)
            saleReturnDetail.put("entryDate", entryDate)
            saleReturnDetail.put("refId", 0) //TODO: to be changed
            saleReturnDetail.put("sRate", saleRate)
            saleReturnDetail.put("mrp", mrp)
            saleReturnDetail.put("gstAmount", gst)
            saleReturnDetail.put("maxDnAmount", 1) //TODO: to be changed
            saleReturnDetail.put("amount", value)
            saleReturnDetail.put("supplierContact", 1) //TODO: to be changed
            saleReturnDetail.put("totalGst", totalGst) //TODO: to be changed
            saleReturnDetail.put("totalCgst", totalCgst) //TODO: to be changed
            saleReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            saleReturnDetail.put("totalSgst", totalSgst) //TODO: to be changed
            saleReturnDetail.put("totalIgst", totalIgst) //TODO: to be changed
            saleReturnDetail.put("exempted", 0) //TODO: to be changed
            saleReturnDetail.put("type", 0) //TODO: to be changed
            saleReturnDetail.put("cashDiscount", 0) //TODO: to be changed
            saleReturnDetail.put("items", 0) //TODO: to be changed
            saleReturnDetail.put("quantity", 0) //TODO: to be changed
            saleReturnDetail.put("totalAmount", totalAmount) //TODO: to be changed
            saleReturnDetail.put("balance", totalAmount) //TODO: to be changed
            saleReturnDetail.put("dbAdjAmount", 0) //TODO: to be changed
            saleReturnDetail.put("debitIds", 0) //TODO: to be changed
            saleReturnDetail.put("debitIds", 0) //TODO: to be changed
            saleReturnDetail.put("supplierEmail", 0) //TODO: to be changed
            saleReturnDetail.put("gross", 0) //TODO: to be changed
            saleReturnDetail.put("taxable", 0) //TODO: to be changed
            saleReturnDetail.put("saleFinId", "") //TODO: to be changed
            saleReturnDetail.put("lockStatus", 0) //TODO: to be changed
            saleReturnDetail.put("adjustmentStatus", 0) //TODO: to be changed
            saleReturnDetail.put("message", 0)
            saleReturnDetail.put("ignoreSold", 0)
            saleReturnDetail.put("status", 0)
            saleReturnDetail.put("syncStatus", 0)
            saleReturnDetail.put("financialYear", financialYear)
            saleReturnDetail.put("", financialYear)
            saleReturnDetail.put("createdUser", 1)
            saleReturnDetail.put("modifiedUser", 1)
            saleReturnDetail.put("entityId", entityId)
            saleReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //save to sale transaction log
            //save to sale transportation details
        }
        Response response = new SalesService().saveSaleRetrun(saleReturnDetail)
        if(response.status == 200)
        {
            def saleOrder = new JSONObject(response.readEntity(String.class))
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleReturnDetail", saleOrder)
            respond responseJson, formats: ['json'],status: 200
        }
        else
        {
            response.status == 400
        }
    }
}
