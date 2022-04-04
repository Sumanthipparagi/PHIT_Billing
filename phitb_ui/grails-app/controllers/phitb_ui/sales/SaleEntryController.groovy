package phitb_ui.sales

import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.ProductService

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleEntryController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        render(view: '/sales/sale-entry', model: [customers   : customers, divisions: divisions, series: series,
                                                  salesmanList: salesmanList, priorityList: priorityList])
    }

    def saveSaleEntry()
    {
        println(params.saleData)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        JSONObject saleBillDetails = new JSONObject()
        JSONArray saleProductDetails = new JSONArray()
        String entityId = session.getAttribute("entityId").toString()
        String customerId = params.customer
        String priorityId = params.priority
        String seriesId = params.series
        String duedate = params.duedate
        String billStatus = params.billStatus
        String seriesCode = params.seriesCode
        String message = params.message
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
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            if (recentSaleBill != null)
            {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
            }
            else
            {
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
        for (JSONObject sale : saleData)
        {
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
            saleProductDetail.put("billId", 0)
            saleProductDetail.put("billType", 0)
            saleProductDetail.put("serBillId", 0)
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

            //GST percentage Calculation
            double priceBeforeTaxes = (Double.parseDouble(saleQty) * Double.parseDouble(saleRate))
            if(Double.parseDouble(discount)>0)
                priceBeforeTaxes = priceBeforeTaxes - (priceBeforeTaxes * (Double.parseDouble(discount)/100))

            double gstPercentage = 0.0
            double sgstPercentage = 0.0
            double cgstPercentage = 0.0
            double igstPercentage = 0.0

            if(Double.parseDouble(gst) >0)
                gstPercentage = (Double.parseDouble(gst) / priceBeforeTaxes) * 100
            if(Double.parseDouble(sgst) >0)
                sgstPercentage = (Double.parseDouble(sgst) / priceBeforeTaxes) * 100
            if(Double.parseDouble(cgst) >0)
                cgstPercentage = (Double.parseDouble(cgst) / priceBeforeTaxes) * 100
            if(Double.parseDouble(igst) >0)
                igstPercentage = (Double.parseDouble(igst) / priceBeforeTaxes) * 100

            saleProductDetail.put("gstPercentage", gstPercentage)
            saleProductDetail.put("sgstPercentage", sgstPercentage)
            saleProductDetail.put("cgstPercentage",cgstPercentage)
            saleProductDetail.put("igstPercentage", igstPercentage)

            saleProductDetail.put("gstId", 0) //TODO: to be changed
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
        saleBillDetails.put("billStatus", billStatus)
        saleBillDetails.put("lockStatus", 0) //TODO: to be changed
        saleBillDetails.put("syncStatus", "0") //TODO: to be changed
        saleBillDetails.put("creditadjAmount", 0) //TODO: to be changed
        saleBillDetails.put("creditIds", "0") //TODO: to be changed
        saleBillDetails.put("referralDoctor", "0") //TODO: to be changed
        saleBillDetails.put("taxable", "1") //TODO: to be changed
        saleBillDetails.put("cashDiscount", 0) //TODO: to be changed
        saleBillDetails.put("exempted", 0) //TODO: to be changed

        saleBillDetails.put("seriesCode", seriesCode)
        Response response = new SalesService().saveSaleBill(saleBillDetails)
        if (response.status == 200)
        {
            def saleBillDetail = new JSONObject(response.readEntity(String.class))
            //save to sale product details
            for (JSONObject saleProductDetail : saleProductDetails)
            {
                saleProductDetail.put("billId", saleBillDetail.get("id"))
                saleProductDetail.put("billType", 0) //0 Sale, 1 Purchase
                saleProductDetail.put("serBillId", saleBillDetail.get("serBillId"))
                def resp = new SalesService().saveSaleProductDetail(saleProductDetail)

                if (resp.status == 200)
                {
                    println("Product Detail Saved")
                }
                else
                {
                    println("Product Detail Failed")
                }
            }

            //update stockbook
            for (JSONObject sale : saleData)
            {
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
                expDate = sdf1.parse(expDate).format("dd-MM-yyyy")
                purcDate = sdf1.parse(purcDate).format("dd-MM-yyyy")
                manufacturingDate = sdf1.parse(manufacturingDate).format("dd-MM-yyyy")
                stockBook.put("expDate", expDate)
                stockBook.put("purcDate", purcDate)
                stockBook.put("manufacturingDate", manufacturingDate)
                def apiRes = new InventoryService().updateStockBook(stockBook)
                if (apiRes.status == 200)
                {
                    //clear tempstockbook
                    new InventoryService().deleteTempStock(tempStockRowId)
                }
            }

            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleBillDetail", saleBillDetail)
            respond responseJson, formats: ['json']

        }
        else
        {
            response.status == 400
        }
    }

    def printSaleInvoice()
    {

        String saleBillId = params.id
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        if (saleBillDetail != null)
        {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetails(saleBillId)
            JSONObject series = new EntityService().getSeriesById(saleBillDetail.get("seriesId").toString())
            JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
            saleProductDetails.each {
                def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
            }
            /*   def invoiceNumber;
               def datepart = saleBillDetail.entryDate.split("T")[0];
               def month = datepart.split("-")[1];
               def year = datepart.split("-")[0];
               def seriesCode = "__";
               if (saleBillDetail.billStatus == "DRAFT")
               {
                   invoiceNumber = saleBillDetail.entityId+"/DR/S/" + month + year + "/" + series.seriesCode + "/__";
               }
               else
               {
                   invoiceNumber = saleBillDetail.entityId+"/S/" + month + year + "/" + series.seriesCode + "/" + saleBillDetail.id
               }*/
            def totalcgst = saleProductDetails.cgstAmount.sum()
            def totalsgst = saleProductDetails.sgstAmount.sum()
            def totaligst = saleProductDetails.igstAmount.sum()
            def totaldiscount = saleProductDetails.discount.sum()
            ArrayList<Double> cgst = new ArrayList<>()
            ArrayList<Double> sgst = new ArrayList<>()
            ArrayList<Double> igst = new ArrayList<>()
            def total = saleProductDetails.amount.sum()
            def totalBeforeTaxes = total - totalcgst - totalsgst - totaligst

            ArrayList<Double> cgst5 = new ArrayList<>()
            ArrayList<Double> cgst12 = new ArrayList<>()
            ArrayList<Double> cgst18 = new ArrayList<>()
            ArrayList<Double> cgst28 = new ArrayList<>()
            ArrayList<Double> sgst5 = new ArrayList<>()
            ArrayList<Double> sgst12 = new ArrayList<>()
            ArrayList<Double> sgst18 = new ArrayList<>()
            ArrayList<Double> sgst28 = new ArrayList<>()
            saleProductDetails.each {
                float amount = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                cgst.push(it.cgstAmount / amount * 100)
                sgst.push(it.sgstAmount / amount * 100)
                igst.push(it.igstAmount / amount * 100)
            }
            def t = 0
            for (Double c : cgst)
            {
                if (c > 0 && c <= 2.5)
                {
                    cgst5.push(t + (0.025 * totalBeforeTaxes))
                }
                if(c > 2.5 && c <= 6)
                {
                    cgst12.push(t + (0.06 * totalBeforeTaxes))

                }
                if(c > 6 && c <= 9)
                {
                    cgst18.push(t + (0.09 * totalBeforeTaxes))
                }
                if(c > 9 && c <= 14)
                {
                    cgst28.push(t + (0.014 * totalBeforeTaxes))
                }
            }
            for (Double s : sgst)
            {
                if (s > 0 && s <= 2.5)
                {
                    sgst5.push(t + (0.025 * totalBeforeTaxes))
                }
                if(s > 2.5 && s <= 6)
                {
                    sgst12.push(t + (0.06 * totalBeforeTaxes))
                }
                if(s > 6 && s <= 9)
                {
                    sgst18.push(t + (0.09 * totalBeforeTaxes))

                }
                if(s > 9 && s <= 14)
                {
                    sgst28.push(t + (0.014 * totalBeforeTaxes))
                }
            }
            render(view: "/sales/sale-invoice", model: [saleBillDetail    : saleBillDetail,
                                                        saleProductDetails: saleProductDetails,
                                                        series            : series, entity: entity, customer: customer, city: city,
                                                        total             : saleProductDetails.amount.sum(), custcity: custcity,
                                                        termsConditions   : termsConditions,
                                                        totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                        totaldiscount     : totaldiscount,
                                                        cgst5:cgst5.sum(),cgst12:cgst12.sum(),
                                                        cgst18:cgst18.sum(),cgst28:cgst28.sum(),
                                                        sgst5:sgst5.sum(),sgst12:sgst12.sum(),
                                                        sgst18:sgst18.sum(),sgst28:sgst28.sum(),
                                                        totalBeforeTaxes:totalBeforeTaxes
            ])
        }

        else
        {

            render("No Bill Found")
        }
    }

    def show()
    {
        try
        {
            def apiResponse = new SalesService().getSaleInvoice()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def showById(String id)
    {
        try
        {
            def apiResponse = new SalesService().getSaleInvoiceById(id)
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def saleBill()
    {
        render(view: '/sales/salebillDetails/saleBill')
    }

    def saleRetrun()
    {
        render(view: '/sales/saleRetrun/sale-return-print')
    }

    def crdDebS()
    {
        render(view: "/sales/credit-debit-settlement")
    }

    def DebJV()
    {
        render(view: "/sales/debit-jv")
    }

    def credJV()
    {
        render(view: '/sales/credit-jv')
    }

    def goodsSalesRecipt()
    {
        render(view: "/sales/goods-sales-recipt")
    }

    def paymentVocher()
    {
        render(view: "/sales/payment-vocher")
    }


    def checkSchemeConfiguration()
    {
        String productId = params.productId
        String batchNumber = params.batchNumber

        if (productId && batchNumber)
        {
            respond new SalesService().getSchemeConfiguration(productId, batchNumber)
        }
        else
        {
            response.status = 400
        }
    }


    def getProductsById(String id)
    {
        try
        {
            JSONObject product = new ProductService().getProductById(id)
            return product
        }
        catch (Exception ex)
        {
            System.err.println('Service :getProductsById , action :  show  , Ex:' + ex)
            log.error('Service :getProductsById , action :  show  , Ex:' + ex)
        }
    }

    def cancelInvoice()
    {
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        JSONObject jsonObject = new SalesService().cancelInvoice(id, entityId, financialYear)
        if (jsonObject)
        {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails)
            {
                for (JSONObject productDetail : productDetails)
                {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())
                    double remainingQty = stockBook.get("remainingQty") + productDetail.get("sqty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty") + productDetail.get("freeQty")
                    double remainingReplQty = stockBook.get("remainingReplQty") + productDetail.get("repQty")
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
