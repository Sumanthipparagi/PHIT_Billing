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
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleReturnController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def reason = new SalesService().getReason()
        def taxRegister = new TaxController().show() as ArrayList<String>
        ArrayList<String> salesmanList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
            {
                salesmanList.add(it)
            }
        }
        render(view: '/sales/saleRetrun/sale-returns', model: [customers: customers, divisions: divisions, series: series,
                                                               salesmanList: salesmanList, priorityList: priorityList, reason:reason,taxRegister:taxRegister])
    }


    def getSaleBillByCustomer()
    {
        def salebills = new SalesService().getSaleBillByCustomer(params.custid, session.getAttribute('financialYear')
                .toString(), session.getAttribute('entityId').toString())
        def apiResponse = new SalesService().getRequestWithIdList(salebills.id, new Links().SALE_PRODUCT_OF_BILLIDS)
        def prod = JSON.parse(apiResponse.readEntity(String.class))
        prod.each {product ->
            def index = salebills.findIndexOf({
                it.id == product.billId
            })
            if (index != -1)
            {
                product.put("billId", salebills[index])
            }
        }
        respond prod, formats: ['json'], status: 200
    }


    def getSaleInvByProducts()
    {
        try
        {
            def products = new SalesService().getSaleProductDetailsByProductId(params.productId)
            JSONArray productArray = new JSONArray()
            products.each {
                def saleBillShow = new SalesService().getRequestWithId(it.billId.toString(), new Links().SALE_BILL_SHOW)
                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
                for (JSONObject batch : batchArray)
                {
                    if (batch.batchNumber == it.batchNumber)
                    {
                        it.put("batch", batch)
                    }
                }
                it.put("prevsqty", 0)
                it.put("prevfqty", 0)
                it.put("bill", JSON.parse(saleBillShow.readEntity(String.class)) as JSONObject)
                def saleReturns = new SalesService().getReturnDetailsByBatchSalebillProductId(it.productId.toString()
                        , it.batchNumber.toString(), it.billId.toString())
                JSONArray saleReturnArray = JSON.parse(saleReturns.readEntity(String.class)) as JSONArray
                if (saleReturnArray.size() > 0)
                {
                    double sqty = 0;
                    double fqty = 0;
                    for (JSONObject saleReturn : saleReturnArray)
                    {

                        if (saleReturn.saleBillId == it.billId && (saleReturn.returnStatus!="CANCELLED"))
                        {
                            if (saleReturn.sqty != 0)
                            {

                                sqty += Double.parseDouble(saleReturn.sqty.toString())
                                it.put("prevsqty", sqty)
                            }
                            if (saleReturn.freeQty != 0)
                            {
                                fqty += Double.parseDouble(saleReturn.freeQty.toString())
                                it.put("prevfqty", fqty)
                            }
                        }
                    }
                }
            }
//            JSONArray array = new JSONArray(products)
//            for(int i = 0; i < array.length(); ++i)
//            {
//                JSONObject obj = array.getJSONObject(i);
//                double sqty1 = obj.getDouble("sqty");
//                double freeQty1 = obj.getDouble("freeQty");
//                if(sqty1 > 0 || freeQty1 > 0)
//                {
//                    productArray.add(products)
//                }
//            }
//            println(products)
            respond products, formats: ['json'], status: 200
        }
        catch (Exception ex)
        {
            log.error(controllerName + ":" + ex)
            println(controllerName + ":" + ex)
        }
    }


    def getSaleDetailsByProductAndBatch()
    {
        def billAndBatch = new SalesService().getByBillBatchesProduct(params.billId, params.batch, params.productId)
        JSONObject jsonObject = new JSONObject(billAndBatch.readEntity(String.class))
        def saleReturns = new SalesService().getReturnDetailsByBatchSalebillProductId(jsonObject.productId.toString()
                , jsonObject.batchNumber.toString(), jsonObject.billId.toString())
        JSONArray saleReturnArray = JSON.parse(saleReturns.readEntity(String.class)) as JSONArray

        if (saleReturnArray.size() > 0)
        {
            for (JSONObject saleReturn : saleReturnArray)
            {
                double sqty = 0;
                double fqty = 0;
                if (saleReturn.saleBillId == jsonObject.billId && (saleReturn.returnStatus!="CANCELLED"))
                {
                    if (saleReturn.sqty != 0)
                    {
                        sqty+=saleReturn.sqty
                        jsonObject.put("sqty", jsonObject.sqty-sqty)
                    }
                    if (saleReturn.freeQty != 0)
                    {
                        fqty+=saleReturn.freeQty
                        jsonObject.put("freeQty", jsonObject.freeQty-fqty)
                    }
                }

            }
        }
        respond jsonObject, formats: ['json'], status: 200
    }


    def saveSaleReturn()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleReturn = new JSONObject()
        JSONArray saleReturnDetails = new JSONArray()
        JSONArray stockArray = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customer = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = params.message
        String lrNo = params.lrno
        String lrDate = params.lrDate
        if (!message)
        {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT"))
        {
            def recentReturn = new SalesService().getRecentSaleReturn(financialYear, entityId)
            if (recentReturn != null && recentReturn.size() != 0)
            {
                finId = Long.parseLong(recentReturn.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentReturn.get("serBillId").toString()) + 1
            }
            else
            {
                finId = 1
                serBillId = Long.parseLong(series.get("saleReturnId").toString())
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
        JSONArray saleRetrunData = new JSONArray(params.saleReturnData)
        for (JSONObject sr : saleRetrunData)
        {
            String saleBillId;
            String reason = sr.get("1")
            String productId = sr.get("2")
            String batchNumber = sr.get("3")
            String expDate = sr.get("4")
            String saleQty = sr.get("5")
            String freeQty = sr.get("6")
            String saleRate = sr.get("7")
            String mrp = sr.get("8")
            String taxId = sr.get("19")
            if (sr.has("18"))
            {
                saleBillId = sr.get("18")
            }
            else
            {
                saleBillId = ""
            }
            String invoiceNumber = sr.get("17")
            double discount = UtilsService.round(Double.parseDouble(sr.get("9").toString()), 2)
            String packDesc = sr.get("10")
            double gst = UtilsService.round(Double.parseDouble(sr.get("12").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sr.get("13").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sr.get("14").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sr.get("15").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sr.get("16").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount
            JSONObject saleReturnDetail = new JSONObject()
            saleReturnDetail.put("finId", finId)
            saleReturnDetail.put("reason", reason)
            saleReturnDetail.put("billId", 0)
            saleReturnDetail.put("billType", 0)
            saleReturnDetail.put("billStatus", billStatus)
            saleReturnDetail.put("serBillId", 0)
            if (saleBillId != "")
            {
                saleReturnDetail.put("saleBillId", saleBillId)
            }
            else
            {
                saleReturnDetail.put("saleBillId", "")
            }
            saleReturnDetail.put("seriesId", seriesId)
            saleReturnDetail.put("productId", productId)
            saleReturnDetail.put("batchNumber", batchNumber)
            saleReturnDetail.put("expiryDate", expDate)
            saleReturnDetail.put("sqty", saleQty)
            saleReturnDetail.put("freeQty", freeQty)
            saleReturnDetail.put("repQty", 0)
            saleReturnDetail.put("invoiceNumber", invoiceNumber)
            saleReturnDetail.put("reason", reason)
            saleReturnDetail.put("sRate", saleRate)
            saleReturnDetail.put("mrp", mrp)
            saleReturnDetail.put("discount", discount)
            saleReturnDetail.put("gstAmount", gst)
            saleReturnDetail.put("sgstAmount", sgst)
            saleReturnDetail.put("cgstAmount", cgst)
            saleReturnDetail.put("igstAmount", igst)
            saleReturnDetail.put("gstId", 1) //TODO: to be changed
            saleReturnDetail.put("amount", value)
            saleReturnDetail.put("fridgeId", 0) //TODO: to be changed
            saleReturnDetail.put("kitName", 0) //TODO: to be changed
            saleReturnDetail.put("saleFinId", "") //TODO: to be changed
            saleReturnDetail.put("redundantBatch", 0) //TODO: to be changed
            saleReturnDetail.put("status", 0)
            saleReturnDetail.put("syncStatus", 0)
            saleReturnDetail.put("financialYear", financialYear)
            saleReturnDetail.put("entityId", entityId)
            saleReturnDetail.put("uuid", params.uuid)
            saleReturnDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            //GST percentage Calculation
            double priceBeforeTaxes = UtilsService.round((Double.parseDouble(saleQty) * Double.parseDouble(saleRate)), 2)
            if (discount > 0)
            {
                priceBeforeTaxes = priceBeforeTaxes - (priceBeforeTaxes * (discount / 100))
            }
            double gstPercentage = 0.0
            double sgstPercentage = 0.0
            double cgstPercentage = 0.0
            double igstPercentage = 0.0

            if (gst > 0)
            {
                gstPercentage = (gst / priceBeforeTaxes) * 100
            }
            if (sgst > 0)
            {
                sgstPercentage = (sgst / priceBeforeTaxes) * 100
            }
            if (cgst > 0)
            {
                cgstPercentage = (cgst / priceBeforeTaxes) * 100
            }
            if (igst > 0)
            {
                igstPercentage = (igst / priceBeforeTaxes) * 100
            }
            saleReturnDetail.put("gstPercentage", UtilsService.round(gstPercentage, 2))
            saleReturnDetail.put("sgstPercentage", UtilsService.round(sgstPercentage, 2))
            saleReturnDetail.put("cgstPercentage", UtilsService.round(cgstPercentage, 2))
            saleReturnDetail.put("igstPercentage", UtilsService.round(igstPercentage, 2))
            saleReturnDetails.add(saleReturnDetail)
            //save to sale transaction log
            //save to sale transportation details

            JSONObject stock = new JSONObject()
            stock.put("batchNumber", batchNumber)
            stock.put("saleQty", saleQty)
            stock.put("freeQty", freeQty)
            stock.put("productId", productId)
            stock.put("taxId", taxId)
            stock.put("packDesc", packDesc)
            stock.put("saleRate", saleRate)
            stock.put("reason", reason)
            stock.put("entityId",session.getAttribute('entityId'))
            stock.put("entityTypeId",session.getAttribute('entityTypeId'))
            stock.put("userId",session.getAttribute('userId'))
            def batch = new ProductService().getByBatchAndProductId(batchNumber,productId)
            if(batch.batchNumber == batchNumber && batch.product.id == Integer.parseInt(productId))
            {
                stock.put("batch",batch)
            }
            stockArray.add(stock)
//            if (stocks.status == 200)
//            {
//                println("Stocks modified")
//            }
//            else
//            {
//                println("Stocks not modified")
//            }
        }
        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //save to sale bill details
        saleReturn.put("serBillId", serBillId)
        saleReturn.put("customer", customer)
        saleReturn.put("lrNo",lrNo)
        saleReturn.put("lrDate",lrDate)
        saleReturn.put("customerNumber", 0) //TODO: to be changed
        saleReturn.put("finId", finId)
        saleReturn.put("seriesId", seriesId)
        saleReturn.put("priorityId", priorityId)
        saleReturn.put("financialYear", financialYear)
        saleReturn.put("dueDate", duedate)
        saleReturn.put("uuid", params.uuid)
        saleReturn.put("paymentStatus", 0)
        saleReturn.put("dispatchDate", 0)
        saleReturn.put("supplierContact", 0)
        saleReturn.put("supplierEmail", 0)
        saleReturn.put("supplierEmail", 0)
        saleReturn.put("dispatchStatus", 0)
        saleReturn.put("debitIds", 0)
        saleReturn.put("refId", 0)
        saleReturn.put("userId", session.getAttribute("userId"))
        saleReturn.put("entryDate", entryDate)
        saleReturn.put("orderDate", orderDate)
        saleReturn.put("dispatchDate", sdf.format(new Date())) //TODO: to be changed
        saleReturn.put("salesmanId", "0") //TODO: to be changed
        saleReturn.put("salesmanComm", "0") //TODO: to be changed
        saleReturn.put("refOrderId", "") //TODO: to be changed this is for sale order conversion
        saleReturn.put("deliveryManId", "0") //TODO: to be changed
        saleReturn.put("accountModeId", "0") //TODO: to be changed
        saleReturn.put("totalSqty", totalSqty)
        saleReturn.put("totalFqty", totalFqty)
        saleReturn.put("totalGst", totalGst)
        saleReturn.put("totalSgst", totalSgst)
        saleReturn.put("totalCgst", totalCgst)
        saleReturn.put("totalIgst", totalIgst)
        saleReturn.put("totalQuantity", totalSqty + totalFqty)
        saleReturn.put("totalItems", totalSqty + totalFqty)
        saleReturn.put("totalDiscount", totalDiscount)
        saleReturn.put("grossAmount", totalAmount + totalDiscount) //TODO: to be checked once
        saleReturn.put("invoiceTotal", totalAmount) //TODO: adjusted amount
        saleReturn.put("totalAmount", totalAmount)
        saleReturn.put("godownId", 0)
        saleReturn.put("maxDnAmount", 0)
        saleReturn.put("purcId", 0)
        saleReturn.put("items", 0)
        saleReturn.put("type", 0)
        saleReturn.put("supplierBillId", 0)
        saleReturn.put("billingDate", entryDate)
        saleReturn.put("balAmount", totalAmount)
        saleReturn.put("submitStatus", 0)//TODO: to be changed
        saleReturn.put("addAmount", 0)//TODO: to be changed
        saleReturn.put("lessAmount", 0)//TODO: to be changed
        saleReturn.put("adjustedAmount", 0)//TODO: to be changed
        saleReturn.put("entityId", entityId)
        saleReturn.put("entityTypeId", session.getAttribute("entityTypeId"))
        saleReturn.put("createdUser", session.getAttribute("userId"))
        saleReturn.put("modifiedUser", session.getAttribute("userId"))
        saleReturn.put("message", message) //TODO: to be changed
        saleReturn.put("gstStatus", "0") //TODO: to be changed
        saleReturn.put("expectedDeliveryDate", entryDate) //TODO: to be changed
        saleReturn.put("billStatus", billStatus)
        saleReturn.put("quantity", 0)
        saleReturn.put("dbAdjAmount", 0)
        saleReturn.put("adjustmentStatus", 0)
        saleReturn.put("balance", totalAmount)
        saleReturn.put("ignoreSold", 0)
        saleReturn.put("lockStatus", 0) //TODO: to be changed
        saleReturn.put("syncStatus", "0") //TODO: to be changed
        saleReturn.put("productDiscount", 0) //TODO: to be changed
        saleReturn.put("receivedDate", entryDate) //TODO: to be changed
        saleReturn.put("receivedBy", entityId) //TODO: to be changed
        saleReturn.put("creditId", 0) //TODO: to be changed
        saleReturn.put("debitId", 0) //TODO: to be changed
        saleReturn.put("crDbAmount", 0) //TODO: to be changed
        saleReturn.put("payableAmount", 0) //TODO: to be changed
        saleReturn.put("gross", 0) //TODO: to be changed
        saleReturn.put("netAmount", 0) //TODO: to be changed
        saleReturn.put("cashDiscount", "0") //TODO: to be changed
        saleReturn.put("taxable", "1") //TODO: to be changed
        saleReturn.put("cashDiscount", 0) //TODO: to be changed
        saleReturn.put("exempted", 0) //TODO: to be changed
        saleReturn.put("seriesCode", seriesCode)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleReturn", saleReturn)
        jsonObject.put("saleReturnDetails", saleReturnDetails)
        Response response = new SalesService().saveSaleRetrun(jsonObject)
        if (response.status == 200)
        {
            for(JSONObject stock: stockArray)
            {
                def stocks = new InventoryService().stocksReturn(stock)
                if(stocks.status == 200)
                {
                    println("Stocks Updated!")
                }
                else {

                    println("Stocks not updated!")
                }
            }
            def saleReturns = new JSONObject(response.readEntity(String.class))
            println("Details Saved")
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleReturnDetail", saleReturns)
            respond responseJson, formats: ['json']
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
            JSONArray saleRetrunDetails = new SalesService().getSaleRetrunDetailsByBill(saleReturnId)
            JSONObject series = new EntityService().getSeriesById(saleReturnDetail.get("series").toString())
            JSONObject customer = new EntityService().getEntityById(saleReturnDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            termsConditions.each {
                JSONObject formMaster =  new SystemService().getFormById(it.formId.toString())
                if(formMaster!=null)
                {
                    if(it.formId == formMaster.id)
                    {
                        it.put("form", formMaster)
                    }
                }
            }
            println(termsConditions)
            saleRetrunDetails.each {
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
            def totalcgst = UtilsService.round(saleRetrunDetails.cgstAmount.sum(), 2)
            def totalsgst = UtilsService.round(saleRetrunDetails.sgstAmount.sum(), 2)
            def totaligst = UtilsService.round(saleRetrunDetails.igstAmount.sum(), 2)
            def totaldiscount = UtilsService.round(saleRetrunDetails.discount.sum(), 2)
            def totalBeforeTaxes = 0
            HashMap<String, Double> gstGroup = new HashMap<>()
            HashMap<String, Double> sgstGroup = new HashMap<>()
            HashMap<String, Double> cgstGroup = new HashMap<>()
            HashMap<String, Double> igstGroup = new HashMap<>()
            for (Object it : saleRetrunDetails)
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
                                                                        saleProductDetails: saleRetrunDetails,
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

    def salesReturnList()
    {
        render(view:'/sales/saleRetrun/sale-return-list')
    }

    def salesReturnDatatables()
    {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().salesReturnDatatable(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {
                    JSONArray jsonArray = responseObject.data
//                    JSONArray jsonArray2 = new JSONArray()
//                    JSONArray jsonArray3 = new JSONArray()
//                    JSONArray entityArray = new JSONArray()
//                    JSONArray cityArray = new JSONArray()
//                    for (JSONObject json : jsonArray) {
//                        json.put("customer", new EntityService().getEntityById(json.get("customerId").toString()))
//                        jsonArray2.put(json)
//                    }
//                    for(JSONObject json1 : jsonArray2)
//                    {
//                        if(json1.has("customer"))
//                            entityArray.put(json1.get("customer"))
//                    }
//                    entityArray.each {
//                        def cityResp = new SystemService().getCityById(it.cityId.toString())
//                        it.put("cityId", cityResp)
//                    }
//                    responseObject.put("data", jsonArray2)
//                    responseObject.put("city",entityArray)
                    for (JSONObject json : jsonArray) {
                        JSONObject customer = new EntityService().getEntityById(json.get("customerId").toString())
                        def city = new SystemService().getCityById(customer?.cityId?.toString())
                        customer?.put("city", city)
                        json.put("customer", customer)
                    }
                    responseObject.put("data", jsonArray)                }
                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }



    def cancelReturns()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new SalesService().cancelReturns(id, entityId, financialYear)
        if (jsonObject)
        {
            //adjust stocks
            JSONArray returnDetails = jsonObject.get("products") as JSONArray
            if (returnDetails)
            {
                for (JSONObject returnDetail : returnDetails)
                {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(returnDetail.productId.toString(), returnDetail.batchNumber, session.getAttribute("entityId").toString())
                    double remainingQty;
                    double remainingFreeQty;
                    if(returnDetail.reason.toString() == "R")
                    {
                        remainingQty = stockBook.get("remainingQty") - returnDetail.get("sqty")
                        remainingFreeQty = stockBook.get("remainingFreeQty") - returnDetail.get("freeQty")
                        System.out.println("Remaining Qty After Update"+remainingQty)
                        System.out.println("Remaining Qty After Update"+remainingFreeQty)
                    }
                    else if(returnDetail.reason.toString() == "E")
                    {
                        println("Expiry - NO EFFECT ON CURRENT STOCK BOOK")
                    }
                    else if(returnDetail.reason.toString() == "B")
                    {
                        println("Breakage - NO EFFECT ON CURRENT STOCK BOOK")
                    }
                    else if(returnDetail.reason.toString()  == "OA")
                    {
                        remainingQty = stockBook.get("remainingQty") - returnDetail.get("sqty")
                        remainingFreeQty = stockBook.get("remainingFreeQty") - returnDetail.get("freeQty")
                        System.out.println("Remaining Qty After Others(ADD)"+remainingQty)
                        System.out.println("Remaining Qty After Others(ADD)"+remainingFreeQty)
                    }
                    else if(returnDetail.reason.toString()  == "ONE")
                    {
                        println("Others(No efft) - NO EFFECT ON CURRENT STOCK BOOK")
                    }

                    double remainingReplQty = stockBook.get("remainingReplQty") + returnDetail.get("repQty")
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", remainingReplQty.toLong())
                    new InventoryService().updateStockBook(stockBook)
                }
            }
            respond jsonObject, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }
}
