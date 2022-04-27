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
import phitb_ui.UtilsService
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
        def salebills = new SalesService().getSaleBillByCustomer(params.custid,session.getAttribute('financialYear')
                .toString(),session.getAttribute('entityId').toString())
        def apiResponse = new SalesService().getRequestWithIdList(salebills.id, new Links().SALE_PRODUCT_OF_BILLIDS)
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
        JSONObject saleReturnDetail = new JSONObject()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
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
            String igst = sale[13]
            String cgst = sale[12]
            String sgst = sale[11]

            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += Double.parseDouble(value)
            totalGst += Double.parseDouble(gst)
            totalSgst += Double.parseDouble(sgst)
            totalCgst += Double.parseDouble(cgst)
            totalIgst += Double.parseDouble(igst)
            totalDiscount += Double.parseDouble("0")
            saleReturnDetail.put("finId", finId)
            saleReturnDetail.put("billId",0)
            saleReturnDetail.put("billType",0)
            saleReturnDetail.put("serBillId",0)
            saleReturnDetail.put("series", seriesId)
            saleReturnDetail.put("productId", productId)
            saleReturnDetail.put("batchNumber", batchNumber)
            saleReturnDetail.put("sqty", saleQty)
            saleReturnDetail.put("customerId", customerId)
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

    def printSaleReturn()
    {

        String saleReturnId = params.id
        JSONObject saleReturnDetail = new SalesService().getSaleReturnDetailsById(saleReturnId)
        if (saleReturnDetail != null)
        {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleReturnId)
            JSONObject series = new EntityService().getSeriesById(saleReturnDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(saleReturnDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            saleProductDetails.each {
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray)
                {
                    if (batch.batchNumber == it.batchNumber)
                    {
                        it.put("batch", batch)
                    }
                }
                def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            }
            def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : saleProductDetails)
            {
                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                totalBeforeTaxes += amountBeforeTaxes
                if (it.igstPercentage > 0)
                {
                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
                    if (igstPercentage == null)
                    {
                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                }
                else
                {
                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
                    if (gstPercentage == null)
                    {
                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)
                    }

                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
                    if (sgstPercentage == null)
                    {
                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
                    if (cgstPercentage == null)
                    {
                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
                    }
                    else
                    {
                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
                    }
                }
            }

            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst

            render(view: "/sales/saleRetrun/sale-return-print", model: [saleBillDetail    : saleReturnDetail,
                                                        saleProductDetails: saleProductDetails,
                                                        series            : series, entity: entity, customer: customer, city: city,
                                                        total             : total, custcity: custcity,
                                                        termsConditions   : termsConditions,
                                                        totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                        totaldiscount     : totaldiscount,
                                                        gstGroup          : gstGroup,
                                                        sgstGroup         : sgstGroup,
                                                        cgstGroup         : cgstGroup,
                                                        igstGroup         : igstGroup,
                                                        totalBeforeTaxes  : totalBeforeTaxes
            ])
        }
        else
        {

            render("No Bill Found")
        }
    }
}
