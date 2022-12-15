package phitb_ui.sales


import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONException
import org.grails.web.json.JSONObject
import phitb_ui.Constants

//import org.springframework.messaging.simp.SimpMessagingTemplate
import phitb_ui.EInvoiceService
import phitb_ui.EmailService
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.Links
import phitb_ui.SalesService
import phitb_ui.ShipmentService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.ProductService
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.product.DivisionController

import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class SaleEntryController
{

//    SimpMessagingTemplate brokerMessagingTemplate
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a")


    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        String userId = session.getAttribute("userId")?.toString()
        def users = new UserRegisterController().getByEntity()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        JSONArray customerArray = new JSONArray(customers)
        for (JSONObject c : customerArray)
        {
            if (c?.cityId != 0)
            {
                def city = new SystemService().getCityById(c?.cityId?.toString())
                c.put("city", city)
            }
        }
        def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
        def entityConfigs = new EntityService().getEntityConfigByEntity(entityId)
        render(view: '/sales/saleEntry/sale-entry', model: [customers   : customerArray, divisions: divisions, series: series,
                                                            salesmanList: salesmanList, priorityList: priorityList,
                                                            transporter : transporter, settings: settings, users:
                                                                    users,entityConfigs:entityConfigs])
    }

/*    def getTempStocksOfUser()
    {
        def tempStocks
        def apiResponse = new InventoryService().getTempStocksByUser(userId)
        if(apiResponse.status == 200)
        {
            tempStocks = new JSONArray(apiResponse.readEntity(String.class))
            for (JSONObject jsonObject : tempStocks) {
                JSONObject product = new ProductService().getProductById(jsonObject.get("productId").toString())
                jsonObject.put("productName", product.get("productName"))
            }
        }
    }*/

    def editSaleBillDetails()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        def users = new UserRegisterController().getByEntity()
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def saleBillId = params.saleBillId
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        JSONObject saleTransportDetail = new SalesService().getSaleTransportationByBill(saleBillId,Constants.SALE_INVOICE)
        Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
        def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
        def entityConfigs = new EntityService().getEntityConfigByEntity(entityId)
        JSONObject customer = new EntityService().getEntityById(saleBillDetail.customerId.toString())
        JSONArray customerArray = new JSONArray(customers)
        for (JSONObject c : customerArray)
        {
            if (c?.cityId != 0)
            {
                def city = new SystemService().getCityById(c?.cityId?.toString())
                c.put("city", city)
            }
        }
        if(entityConfigs?.REGEN_NEW_DOC?.saleEntry == true)
        {
            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
            render(view: '/sales/saleEntry/sale-entry', model: [customers         : customerArray, divisions: divisions, series: series,
                                                                priorityList      : priorityList, saleBillDetail: saleBillDetail,
                                                                saleProductDetails: saleProductDetails,
                                                                transporter       : transporter,
                                                                customer          : customer, saleTransportDetail:
                                                                        saleTransportDetail,users:users,settings:
                                                                        settings,entityConfigs: entityConfigs])
        }else{
            if (saleBillDetail != null && saleBillDetail.billStatus == 'DRAFT')
            {
                JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
                render(view: '/sales/saleEntry/sale-entry', model: [customers         : customerArray, divisions: divisions, series: series,
                                                                    priorityList      : priorityList, saleBillDetail: saleBillDetail,
                                                                    saleProductDetails: saleProductDetails,
                                                                    transporter       : transporter,
                                                                    customer          : customer, saleTransportDetail:
                                                                            saleTransportDetail,users:users,settings:
                                                                            settings,entityConfigs: entityConfigs])
            }
            else
            {
                redirect(uri: "/sale-entry")
            }
        }
    }

  /*  def saveSaleEntry()
    {
//        def entityConfigs = new EntityService().getEntityConfigByEntity(session.getAttribute('entityId').toString())
//        if(entityConfigs.MODIFY_AFTER_DAYEND.saleEntry == true){
//            def dayEndMaster = new EntityService().getDayEndByEntity(session.getAttribute('entityId').toString())
//            if(dayEndMaster.size()!=0){
//                Date endDate = sdf.parse(dayEndMaster[0].endTime.toString());
//                println(endDate)
//            }
//        }
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
        String refDate = params.refDate
        String refNo = params.refNum
        String privateNote = params.privateNote
        String publicNote = params.publicNote
        String rep = params.rep
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
            if (recentSaleBill != null && recentSaleBill.size() != 0)
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
        boolean tempStocksSavedCheck = true
        for (JSONObject sale : saleData)
        {
            if (sale.has("15"))
            {
                String tempStockRowId = sale.get("15")
                if (tempStockRowId && Long.parseLong(tempStockRowId) > 0)
                {
                    tempStocksSavedCheck = true
                }
                else
                {
                    tempStocksSavedCheck = false
                }
            }
            else
            {
                tempStocksSavedCheck = false
            }
        }

        //safety check
        if (!tempStocksSavedCheck)
        {
            println("Safety Check Failed! attempted to generate sale invoice, but temp stock was not saved.")
            response.status == 400
            return
        }

        for (JSONObject sale : saleData)
        {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            double discount = UtilsService.round(Double.parseDouble(sale.get("8").toString()), 2)
            String packDesc = sale.get("9")
            double gst = UtilsService.round(Double.parseDouble(sale.get("10").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sale.get("11").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sale.get("12").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sale.get("13").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sale.get("14").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount

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
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0)
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)

            saleProductDetail.put("gstPercentage", sale.get("16").toString())
            saleProductDetail.put("sgstPercentage", sale.get("17").toString())
            saleProductDetail.put("cgstPercentage", sale.get("18").toString())
            saleProductDetail.put("igstPercentage", sale.get("19").toString())
            saleProductDetail.put("originalSqty", sale.get("20").toString())
            saleProductDetail.put("originalFqty", sale.get("21").toString())


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

        if (refDate != '')
        {
            saleBillDetails.put("refDate", refDate)
        }
        else
        {
            saleBillDetails.put("refDate", '')
        }

        saleBillDetails.put("refNo", refNo)
        saleBillDetails.put("publicNote", publicNote)
        saleBillDetails.put("privateNote", privateNote)
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
        saleBillDetails.put("invtype", params.invtype)
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
        saleBillDetails.put("uuid", params.uuid)
        saleBillDetails.put("rep", rep)

        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleInvoice", saleBillDetails)
        jsonObject.put("saleProducts", saleProductDetails)
        Response response = new SalesService().saveSaleInvoice(jsonObject)
        if (response.status == 200)
        {
            JSONObject saleBillDetail = new JSONObject(response.readEntity(String.class))
            UUID uuid
            //update stockbook
            for (JSONObject sale : saleData)
            {
                String tempStockRowId = sale.get("15")
                //clear tempstockbook but do not update stockbook
                new InventoryService().deleteTempStock(tempStockRowId, false)
                try
                {
                    if (billStatus.equalsIgnoreCase("ACTIVE"))
                    {
                        //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                        new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace()
                }

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
                 stockBook.put("uuid", UUID.randomUUID())
                 def apiRes = new InventoryService().updateStockBook(stockBook)
                 if (apiRes.status == 200) {
                     //clear tempstockbook
                     new InventoryService().deleteTempStock(tempStockRowId, false)
                     try {
                         if (billStatus.equalsIgnoreCase("ACTIVE")) {
                             //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                             new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                         }
                     }
                     catch (Exception ex) {
                         ex.printStackTrace()
                     }
                 }
            }
            if (params.lrNumber != '' && params.lrDate != '' && params.transporter != '')
            {
                JSONObject transportObject = new JSONObject();
                transportObject.put("finId", finId)
                transportObject.put("billId", saleBillDetail.id)
                transportObject.put("billType", "SALE_INVOICE")
                transportObject.put("serBillId", saleBillDetail.serBillId)
                transportObject.put("series", saleBillDetail.seriesId)
                transportObject.put("customerId", saleBillDetail.customerId)
                transportObject.put("transporterId", params.transporter)
                transportObject.put("lrDate", params.lrDate)
                transportObject.put("lrNumber", params.lrNumber)
                transportObject.put("cartonsCount", "")
                transportObject.put("paid", 0)
                transportObject.put("toPay", 0)
                transportObject.put("generalInfo", 0)
                transportObject.put("selfNo", 0)
                transportObject.put("ccm", 0)
                transportObject.put("recievedTemprature", 0)
                transportObject.put("freightCharge", 0)
                transportObject.put("vechileId", 0)
                transportObject.put("deliveryStatus", 0)
                transportObject.put("dispatchDateTime", 0)
                transportObject.put("deliveryDateTime", 0)
                transportObject.put("trackingDetails", 0)
                transportObject.put("ewaybillId", 0)
                transportObject.put("genralInfo", 0)
                transportObject.put("weight", 0)
                transportObject.put("ewaysupplytype", 0)
                transportObject.put("ewaysupplysubtype", 0)
                transportObject.put("ewaydoctype", 0)
                transportObject.put("consignmentNo", 0)
                transportObject.put("syncStatus", 0)
                transportObject.put("financialYear", 0)
                transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                transportObject.put("entityId", session.getAttribute('entityId'))
                Response transportation = new SalesService().saveSaleTransportation(transportObject)
                if (transportation?.status == 200)
                {
                    println("Transportation details added")
                }
                else
                {
                    println("something went wrong!!")
                }
            }
            else
            {
                println("Transportation Details not found!")
            }
            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject salesEmailConfig
            if (emailSettings != null)
            {
                if (emailSettings?.salesEmailConfig != null && emailSettings?.salesEmailConfig != "")
                {
                    salesEmailConfig = new JSONObject(emailSettings?.salesEmailConfig)
                }
                if (salesEmailConfig?.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY == "true")
                {
                    def entity = new EntityService().getEntityById(params.customer)
                    if (entity?.email != null && entity?.email != "" && entity?.email != "NA")
                    {
                        def email = new EmailService().sendEmail(entity.email.trim(), "Sale Invoice saved", saleBillDetail?.invoiceNumber, saleBillDetail?.invoiceNumber, Constants.SALE_INVOICE)
                        if (email)
                        {
                            println("Mail Sent..")
                        }
                        else
                        {
                            println("Mail not Sent..")
                        }
                    }
                    else
                    {
                        println("Email not found..")
                    }
                }
            }
            else
            {
                println("Email Settings not found!!")
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleBillDetail", saleBillDetail)
            respond responseJson, formats: ['json']
        }
        else
        {
            response.status = 400
        }
    }*/



    def saveSaleEntry() {
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
        String refNo = params.refNum
        String privateNote = params.privateNote
        String publicNote = params.publicNote
        String rep = params.rep
        String refDate = params.refDate
        if (!message) {
            message = "NA"
        }
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
        if (!billStatus.equalsIgnoreCase("DRAFT")) {
            def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
            if (recentSaleBill != null && recentSaleBill.size() != 0) {
                finId = Long.parseLong(recentSaleBill.get("finId").toString()) + 1
                serBillId = Long.parseLong(recentSaleBill.get("serBillId").toString()) + 1
            } else {
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
        boolean tempStocksSavedCheck = true
        for (JSONObject sale : saleData) {
            if (sale.has("16")) {
                String tempStockRowId = sale.get("16")
                if (tempStockRowId && Long.parseLong(tempStockRowId) > 0) {
                    tempStocksSavedCheck = true
                } else {
                    tempStocksSavedCheck = false
                }
            } else {
                tempStocksSavedCheck = false
            }
        }

        //safety check
        if (!tempStocksSavedCheck) {
            println("Safety Check Failed! attempted to generate sale invoice, but temp stock was not saved.")
            response.status == 400
            return
        }

        for (JSONObject sale : saleData) {
            String productId = sale.get("1")
            String batchNumber = sale.get("2")
            String expDate = sale.get("3")
            String saleQty = sale.get("4")
            String freeQty = sale.get("5")
            String saleRate = sale.get("6")
            String mrp = sale.get("7")
            double discount = UtilsService.round(Double.parseDouble(sale.get("8").toString()), 2)
            String packDesc = sale.get("9")
            double gst = UtilsService.round(Double.parseDouble(sale.get("10").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sale.get("11").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sale.get("12").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sale.get("13").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sale.get("14").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount

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
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0)
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)

            saleProductDetail.put("gstPercentage", sale.get("17").toString())
            saleProductDetail.put("sgstPercentage", sale.get("18").toString())
            saleProductDetail.put("cgstPercentage", sale.get("19").toString())
            saleProductDetail.put("igstPercentage", sale.get("20").toString())
            saleProductDetail.put("originalSqty", sale.get("21").toString())
            saleProductDetail.put("originalFqty", sale.get("22").toString())


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
            if(sale.has('15')){
                saleProductDetail.put("replacement",sale.get('15'))
            }else{
                saleProductDetail.put("replacement",false)
            }
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
        saleBillDetails.put("invtype", params.invtype)
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
        saleBillDetails.put("uuid", params.uuid)
        saleBillDetails.put("rep", rep)
        saleBillDetails.put("refNo", refNo)
        saleBillDetails.put("publicNote", publicNote)
        saleBillDetails.put("privateNote", privateNote)
        if (refDate != '')
        {
            saleBillDetails.put("refDate", refDate)
        }
        else
        {
            saleBillDetails.put("refDate", '')
        }

        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleInvoice", saleBillDetails)
        jsonObject.put("saleProducts", saleProductDetails)
        Response response = new SalesService().saveSaleInvoice(jsonObject)
        if (response.status == 200) {
            JSONObject saleBillDetail = new JSONObject(response.readEntity(String.class))
            UUID uuid
            //update stockbook
            for (JSONObject sale : saleData) {
                String tempStockRowId = sale.get("16")
                //clear tempstockbook but do not update stockbook
                new InventoryService().deleteTempStock(tempStockRowId, false)


                /* def stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
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
                 stockBook.put("uuid", UUID.randomUUID())
                 def apiRes = new InventoryService().updateStockBook(stockBook)
                 if (apiRes.status == 200) {
                     //clear tempstockbook
                     new InventoryService().deleteTempStock(tempStockRowId, false)
                     try {
                         if (billStatus.equalsIgnoreCase("ACTIVE")) {
                             //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                             new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                         }
                     }
                     catch (Exception ex) {
                         ex.printStackTrace()
                     }
                 }*/
            }
            if(params.lrNumber!='' && params.lrDate!='' && params.transporter!='')
            {
                JSONObject transportObject = new JSONObject();
                transportObject.put("finId", finId)
                transportObject.put("billId", saleBillDetail.id)
                transportObject.put("billType", "SALE_INVOICE")
                transportObject.put("serBillId", saleBillDetail.serBillId)
                transportObject.put("series", saleBillDetail.seriesId)
                transportObject.put("customerId", saleBillDetail.customerId)
                transportObject.put("transporterId", params.transporter)
                transportObject.put("lrDate", params.lrDate)
                transportObject.put("lrNumber", params.lrNumber)
                transportObject.put("cartonsCount", "")
                transportObject.put("paid", 0)
                transportObject.put("toPay", 0)
                transportObject.put("generalInfo", 0)
                transportObject.put("selfNo", 0)
                transportObject.put("ccm", 0)
                transportObject.put("recievedTemprature", 0)
                transportObject.put("freightCharge", 0)
                transportObject.put("vechileId", 0)
                transportObject.put("deliveryStatus", 0)
                transportObject.put("dispatchDateTime", 0)
                transportObject.put("deliveryDateTime", 0)
                transportObject.put("trackingDetails", 0)
                transportObject.put("ewaybillId", 0)
                transportObject.put("genralInfo", 0)
                transportObject.put("weight", 0)
                transportObject.put("ewaysupplytype", 0)
                transportObject.put("ewaysupplysubtype", 0)
                transportObject.put("ewaydoctype", 0)
                transportObject.put("consignmentNo", 0)
                transportObject.put("syncStatus", 0)
                transportObject.put("financialYear", 0)
                transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                transportObject.put("entityId", session.getAttribute('entityId'))
                Response transportation = new SalesService().saveSaleTransportation(transportObject)
                if (transportation?.status == 200)
                {
                    println("Transportation details added")
                }
                else
                {
                    println("something went wrong!!")
                }
            }else {
                println("Transportation Details not found!")
            }

            try {
                if (billStatus.equalsIgnoreCase("ACTIVE")) {
                    //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                    new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                }
            }
            catch (Exception ex) {
                ex.printStackTrace()
            }


            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject salesEmailConfig
            if(emailSettings!=null){
                if(emailSettings?.salesEmailConfig!=null && emailSettings?.salesEmailConfig!=""){
                    salesEmailConfig = new JSONObject(emailSettings?.salesEmailConfig)
                }
                if(salesEmailConfig?.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY == "true"){
                    def entity = new EntityService().getEntityById(params.customer)
                    if(entity?.email!=null && entity?.email!="" && entity?.email!="NA")
                    {
                        for(JSONObject jsonObject1: saleProductDetails){
                            def product = new ProductService().getProductById(jsonObject1.productId.toString())
                            jsonObject1.put("product",product)
                        }
                        JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
                        Object mailTemplate = g.render(template:'/templates/bill-template',model:
                                [saleProductDetails: saleProductDetails,saleBillDetail: saleBillDetail,customer:customer]) as
                                Object
                        def email = new EmailService().sendEmail(entity.email.trim(), "Sale Invoice saved", saleBillDetail?.invoiceNumber, saleBillDetail?.invoiceNumber, Constants.SALE_INVOICE, null, true,mailTemplate)
                        if (email)
                        {
                            println("Mail Sent..")
                        }
                        else
                        {
                            println("Mail not Sent..")
                        }
                    }
                    else{
                        println("Email not found..")
                    }
                }
            }
            else{
                println("Entity Settings not found!!")
            }
            JSONObject responseJson = new JSONObject()
            responseJson.put("series", series)
            responseJson.put("saleBillDetail", saleBillDetail)
            respond responseJson, formats: ['json']
        } else
            response.status = 400
    }


    def printSaleInvoice()
    {
        try{
            String saleBillId = params.id
            def checkUser = new EntityService().billDetailsCheckUserType(session.getAttribute('userId').toString())
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
            if(saleBillDetail.entityId == session.getAttribute('entityId')){
                def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
                if (saleBillDetail != null)
                {
                    JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
                    JSONObject transportDetails = new SalesService().getSaleTransportationByBill(saleBillId,Constants.SALE_INVOICE)
                    if (transportDetails != null)
                    {
                        JSONObject transporter = new ShipmentService().getTransporterbyId(transportDetails?.transporterId?.toString());
                        if (transporter != null)
                        {
                            transportDetails.put("transporter", transporter)
                        }
                    }
                    JSONObject series = new EntityService().getSeriesById(saleBillDetail.get("seriesId").toString())
                    JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
                    println("Entity ID is: " + session.getAttribute("entityId").toString())
                    JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
                    if (entity == null)
                    {
                        println("Entity is null")
                    }
                    JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
                    JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
                    JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())


                    termsConditions.each {
                        JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
                        if (formMaster != null)
                        {
                            if (it.formId == formMaster.id)
                            {
                                it.put("form", formMaster)
                            }
                        }
                    }
//            println(termsConditions)
                    JSONObject groupDetails = new JSONObject()
                    JSONArray productDetail = new JSONArray()
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
                        def stocks = new InventoryService().getStocksOfProductAndBatch(it.productId.id.toString(), it.batchNumber.toString(), it.entityId.toString())
                        it.put("stocks", stocks)
                        if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
                        {
                            if (groupDetails.containsKey(it?.productId?.division?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.division?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.division?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.productId?.division?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                        {
                            if (groupDetails.containsKey(it?.stocks?.taxId))
                            {
                                productDetail = groupDetails.get(it?.stocks?.taxId) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.stocks?.taxId, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.stocks?.taxId, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
                        {
                            if (groupDetails.containsKey(it?.productId?.group?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.group?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.group?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.productId?.group?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
                        {
                            if (groupDetails.containsKey(it?.productId?.schedule?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.schedule?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.schedule?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                groupDetails.put(it?.productId?.schedule?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
                        {
                            if (groupDetails.containsKey(it?.productId?.category?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.category?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.category?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                groupDetails.put(it?.productId?.category?.id, productDetail)
                            }
                        }
                    }


                    if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
                    {
                        for (Object divison : groupDetails.keySet())
                        {
                            def divisionDetail = new DivisionController().getDivisionById(divison as String)
                            JSONArray prodDetails = groupDetails.get(divison) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                }
                                divisionDetail.put("sortItem", divisionDetail.get("divisionName"))
                                divisionDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                divisionDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                divisionDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", divisionDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                    {
                        for (Object tax : groupDetails.keySet())
                        {
                            def taxDetail = new TaxController().show(tax as String)
                            JSONArray prodDetails = groupDetails.get(tax) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }

                                taxDetail.put("sortItem", taxDetail.get("taxName") + " (" + taxDetail.get("taxValue") + "%)")
//                            taxDetail.remove("divisionName");
                                taxDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                taxDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                taxDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", taxDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
                    {
                        for (Object productGrp : groupDetails.keySet())
                        {
                            def productGrpDetail = new ProductService().getProductGroupById(productGrp.toString())
                            JSONArray prodDetails = groupDetails.get(productGrp) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                }

                                productGrpDetail.put("sortItem", productGrpDetail.get("groupName"))
//                            taxDetail.remove("divisionName");
                                productGrpDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                productGrpDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                productGrpDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", productGrpDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
                    {
                        for (Object schedule : groupDetails.keySet())
                        {
                            def scheduleDetail = new ProductService().getProductSchedulebyId(schedule.toString())
                            JSONArray prodDetails = groupDetails.get(schedule) as JSONArray

                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }

                                scheduleDetail.put("sortItem", scheduleDetail.get("scheduleCode"))
                                scheduleDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                scheduleDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                scheduleDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", scheduleDetail)

                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
                    {
                        for (Object category : groupDetails.keySet())
                        {
                            def categoryDetail = new ProductService().getProductCategoryById(category.toString())
                            JSONArray prodDetails = groupDetails.get(category) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }
                                categoryDetail.put("sortItem", categoryDetail.get("categoryName"))
                                categoryDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                categoryDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                categoryDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", categoryDetail)
                            }
                        }
                    }



                    def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
                    def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
                    def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
                    def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
                    def totalDiscAmt = 0
                    def totalBeforeTaxes = 0
                    HashMap<String, Double> gstGroup = new HashMap<>()
                    HashMap<String, Double> sgstGroup = new HashMap<>()
                    HashMap<String, Double> cgstGroup = new HashMap<>()
                    HashMap<String, Double> igstGroup = new HashMap<>()
                    for (Object it : saleProductDetails)
                    {
                        double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                        double amountBeforeDiscount = it.sqty * it.sRate
                        totalDiscAmt += amountBeforeDiscount*(it.discount/100)
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

                    JSONObject irnDetails = null
                    if (saleBillDetail.has("irnDetails") && saleBillDetail.get("irnDetails") != null)
                    {
                        irnDetails = new JSONObject(saleBillDetail.get("irnDetails").toString())
                    }


                    println(groupDetails)
                    render(view: "/sales/saleEntry/sale-invoice", model: [saleBillDetail    : saleBillDetail,
                                                                          saleProductDetails: saleProductDetails,
                                                                          series            : series, entity: entity, customer: customer, city: city,
                                                                          total             : total, custcity: custcity,
                                                                          totalDiscAmt:totalDiscAmt,
                                                                          termsConditions   : termsConditions,
                                                                          totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                          totaldiscount     : totaldiscount,
                                                                          gstGroup          : gstGroup,
                                                                          sgstGroup         : sgstGroup,
                                                                          cgstGroup         : cgstGroup,
                                                                          igstGroup         : igstGroup,
                                                                          totalBeforeTaxes  : totalBeforeTaxes,
                                                                          irnDetails        : irnDetails,
                                                                          transportDetails  : transportDetails,
                                                                          groupDetails      : groupDetails,
                                                                          settings          : settings,
                    ])

                }
                else
                {

                    render("No Bill Found")
                }
            } else if(checkUser){
                def settings = new EntityService().getEntitySettingsByEntity(session.getAttribute('entityId').toString())
                if (saleBillDetail != null)
                {
                    JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
                    JSONObject transportDetails = new SalesService().getSaleTransportationByBill(saleBillId,Constants.SALE_INVOICE)
                    if (transportDetails != null)
                    {
                        JSONObject transporter = new ShipmentService().getTransporterbyId(transportDetails?.transporterId?.toString());
                        if (transporter != null)
                        {
                            transportDetails.put("transporter", transporter)
                        }
                    }
                    JSONObject series = new EntityService().getSeriesById(saleBillDetail.get("seriesId").toString())
                    JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
                    println("Entity ID is: " + session.getAttribute("entityId").toString())
                    JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
                    if (entity == null)
                    {
                        println("Entity is null")
                    }
                    JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
                    JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
                    JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())


                    termsConditions.each {
                        JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
                        if (formMaster != null)
                        {
                            if (it.formId == formMaster.id)
                            {
                                it.put("form", formMaster)
                            }
                        }
                    }
//            println(termsConditions)
                    JSONObject groupDetails = new JSONObject()
                    JSONArray productDetail = new JSONArray()
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
                        def stocks = new InventoryService().getStocksOfProductAndBatch(it.productId.id.toString(), it.batchNumber.toString(), it.entityId.toString())
                        it.put("stocks", stocks)
                        if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
                        {
                            if (groupDetails.containsKey(it?.productId?.division?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.division?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.division?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.productId?.division?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                        {
                            if (groupDetails.containsKey(it?.stocks?.taxId))
                            {
                                productDetail = groupDetails.get(it?.stocks?.taxId) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.stocks?.taxId, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.stocks?.taxId, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
                        {
                            if (groupDetails.containsKey(it?.productId?.group?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.group?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.group?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                println(productDetail)
                                groupDetails.put(it?.productId?.group?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
                        {
                            if (groupDetails.containsKey(it?.productId?.schedule?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.schedule?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.schedule?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                groupDetails.put(it?.productId?.schedule?.id, productDetail)
                            }
                        }
                        else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
                        {
                            if (groupDetails.containsKey(it?.productId?.category?.id))
                            {
                                productDetail = groupDetails.get(it?.productId?.category?.id) as JSONArray
                                productDetail.add(it)
                                Collections.sort(productDetail, new Comparator<JSONObject>() {
                                    @Override
                                    int compare(JSONObject o1, JSONObject o2)
                                    {
                                        String val1 = new String()
                                        String val2 = new String()
                                        try
                                        {

                                            if (settings?.IPS == Constants.ALPHABETIC)
                                            {
                                                val1 = (String) o1?.productId?.productName
                                                val2 = (String) o2.productId?.productName
                                            }
                                            else if (settings?.IPS == Constants.TAX_WISE)
                                            {
                                                val1 = (String) o1?.gstPercentage
                                                val2 = (String) o2?.gstPercentage
                                            }
                                        }
                                        catch (JSONException e)
                                        {
                                            //do something
                                            println("JSON Exception")
                                        }
                                        return val1.compareTo(val2);
                                    }
                                });
                                JSONArray sortedJsonArray = new JSONArray();
                                for (int i = 0; i < productDetail.length(); i++)
                                {
                                    sortedJsonArray.put(productDetail.get(i));
                                }
                                println(sortedJsonArray)
                                groupDetails.put(it?.productId?.category?.id, sortedJsonArray)
                            }
                            else
                            {
                                productDetail = new JSONArray()
                                productDetail.add(it)
                                groupDetails.put(it?.productId?.category?.id, productDetail)
                            }
                        }
                    }


                    if (settings.size() != 0 && settings?.IPG == Constants.DIVISION_WISE)
                    {
                        for (Object divison : groupDetails.keySet())
                        {
                            def divisionDetail = new DivisionController().getDivisionById(divison as String)
                            JSONArray prodDetails = groupDetails.get(divison) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                }
                                divisionDetail.put("sortItem", divisionDetail.get("divisionName"))
                                divisionDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                divisionDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                divisionDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", divisionDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.TAX_WISE)
                    {
                        for (Object tax : groupDetails.keySet())
                        {
                            def taxDetail = new TaxController().show(tax as String)
                            JSONArray prodDetails = groupDetails.get(tax) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }

                                taxDetail.put("sortItem", taxDetail.get("taxName") + " (" + taxDetail.get("taxValue") + "%)")
//                            taxDetail.remove("divisionName");
                                taxDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                taxDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                taxDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", taxDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.PRODUCT_GROUPING)
                    {
                        for (Object productGrp : groupDetails.keySet())
                        {
                            def productGrpDetail = new ProductService().getProductGroupById(productGrp.toString())
                            JSONArray prodDetails = groupDetails.get(productGrp) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                }

                                productGrpDetail.put("sortItem", productGrpDetail.get("groupName"))
//                            taxDetail.remove("divisionName");
                                productGrpDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                productGrpDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                productGrpDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", productGrpDetail)
                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.SCHEDULE)
                    {
                        for (Object schedule : groupDetails.keySet())
                        {
                            def scheduleDetail = new ProductService().getProductSchedulebyId(schedule.toString())
                            JSONArray prodDetails = groupDetails.get(schedule) as JSONArray

                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }

                                scheduleDetail.put("sortItem", scheduleDetail.get("scheduleCode"))
                                scheduleDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                scheduleDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                scheduleDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", scheduleDetail)

                            }
                        }
                    }
                    else if (settings.size() != 0 && settings?.IPG == Constants.CATEGORY)
                    {
                        for (Object category : groupDetails.keySet())
                        {
                            def categoryDetail = new ProductService().getProductCategoryById(category.toString())
                            JSONArray prodDetails = groupDetails.get(category) as JSONArray
                            HashMap<String, Double> divGstGroup = new HashMap<>()
                            HashMap<String, Double> divSgstGroup = new HashMap<>()
                            HashMap<String, Double> divCgstGroup = new HashMap<>()
                            HashMap<String, Double> divIgstGroup = new HashMap<>()
                            double amountBeforeTaxes = 0;
                            double amountAfterTaxes = 0;
                            for (Object prodDetail : prodDetails)
                            {
                                amountBeforeTaxes += prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount

                                amountAfterTaxes += prodDetail.amount
                                if (prodDetail.igstPercentage > 0)
                                {
                                    def igstPercentage = divIgstGroup.get(prodDetail.igstPercentage.toString())
                                    if (igstPercentage == null)
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), amountBeforeTaxes)
                                    }
                                    else
                                    {
                                        divIgstGroup.put(prodDetail.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                }
                                else
                                {
                                    def gstPercentage = divGstGroup.get(prodDetail.gstPercentage.toString())
                                    if (gstPercentage == null)
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divGstGroup.put(prodDetail.gstPercentage.toString(), gstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }

                                    def sgstPercentage = divSgstGroup.get(prodDetail.sgstPercentage.toString())
                                    if (sgstPercentage == null)
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divSgstGroup.put(prodDetail.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
                                    }
                                    def cgstPercentage = divCgstGroup.get(prodDetail.cgstPercentage.toString())
                                    if (cgstPercentage == null)
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }
                                    else
                                    {
                                        divCgstGroup.put(prodDetail.cgstPercentage.toString(), cgstPercentage.doubleValue() + prodDetail.amount - prodDetail.cgstAmount - prodDetail.sgstAmount - prodDetail.igstAmount)
                                    }


                                }
                                categoryDetail.put("sortItem", categoryDetail.get("categoryName"))
                                categoryDetail.put("divCgstGroup", new JSONObject(divCgstGroup))
                                categoryDetail.put("amountBeforeTaxes", amountBeforeTaxes)
                                categoryDetail.put("amountAfterTaxes", amountAfterTaxes)
                                prodDetail.put("sortDetail", categoryDetail)
                            }
                        }
                    }



                    def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
                    def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
                    def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
                    def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
                    def totalDiscAmt = 0
                    def totalBeforeTaxes = 0
                    HashMap<String, Double> gstGroup = new HashMap<>()
                    HashMap<String, Double> sgstGroup = new HashMap<>()
                    HashMap<String, Double> cgstGroup = new HashMap<>()
                    HashMap<String, Double> igstGroup = new HashMap<>()
                    for (Object it : saleProductDetails)
                    {
                        double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
                        totalDiscAmt += amountBeforeTaxes/100*it.discount
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

                    JSONObject irnDetails = null
                    if (saleBillDetail.has("irnDetails") && saleBillDetail.get("irnDetails") != null)
                    {
                        irnDetails = new JSONObject(saleBillDetail.get("irnDetails").toString())
                    }


                    println(groupDetails)
                    render(view: "/sales/saleEntry/sale-invoice", model: [saleBillDetail    : saleBillDetail,
                                                                          saleProductDetails: saleProductDetails,
                                                                          series            : series, entity: entity, customer: customer, city: city,
                                                                          total             : total, custcity: custcity,
                                                                          totalDiscAmt:totalDiscAmt,
                                                                          termsConditions   : termsConditions,
                                                                          totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
                                                                          totaldiscount     : totaldiscount,
                                                                          gstGroup          : gstGroup,
                                                                          sgstGroup         : sgstGroup,
                                                                          cgstGroup         : cgstGroup,
                                                                          igstGroup         : igstGroup,
                                                                          totalBeforeTaxes  : totalBeforeTaxes,
                                                                          irnDetails        : irnDetails,
                                                                          transportDetails  : transportDetails,
                                                                          groupDetails      : groupDetails,
                                                                          settings          : settings,
                    ])

                }
                else
                {

                    render("No Bill Found")
                }
            }else{
                render("No invoice found")
            }
        }catch(Exception ex){
            println(ex)
//            log.error(ex)
        }
    }




//    def printSaleInvoice() {
//
//        String saleBillId = params.id
//        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
//        if (saleBillDetail != null) {
//            JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
//            JSONObject series = new EntityService().getSeriesById(saleBillDetail.get("seriesId").toString())
//            JSONObject customer = new EntityService().getEntityById(saleBillDetail.get("customerId").toString())
//            println("Entity ID is: "+ session.getAttribute("entityId").toString())
//            JSONObject entity = new EntityService().getEntityById(session.getAttribute("entityId").toString())
//            if(entity == null)
//            {
//                println("Entity is null")
//            }
//            JSONObject city = new SystemService().getCityById(entity.get('cityId').toString())
//            JSONObject custcity = new SystemService().getCityById(customer.get('cityId').toString())
//            JSONArray termsConditions = new EntityService().getTermsContionsByEntity(session.getAttribute("entityId").toString())
//            termsConditions.each {
//                JSONObject formMaster = new SystemService().getFormById(it.formId.toString())
//                if (formMaster != null) {
//                    if (it.formId == formMaster.id) {
//                        it.put("form", formMaster)
//                    }
//                }
//            }
//            println(termsConditions)
//            saleProductDetails.each {
//                def batchResponse = new ProductService().getBatchesOfProduct(it.productId.toString())
//                JSONArray batchArray = JSON.parse(batchResponse.readEntity(String.class)) as JSONArray
//                for (JSONObject batch : batchArray) {
//                    if (batch.batchNumber == it.batchNumber) {
//                        it.put("batch", batch)
//                    }
//                }
//                def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
//                it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
//            }
//            def totalcgst = UtilsService.round(saleProductDetails.cgstAmount.sum(), 2)
//            def totalsgst = UtilsService.round(saleProductDetails.sgstAmount.sum(), 2)
//            def totaligst = UtilsService.round(saleProductDetails.igstAmount.sum(), 2)
//            def totaldiscount = UtilsService.round(saleProductDetails.discount.sum(), 2)
//            def totalBeforeTaxes = 0
//            HashMap<String, Double> gstGroup = new HashMap<>()
//            HashMap<String, Double> sgstGroup = new HashMap<>()
//            HashMap<String, Double> cgstGroup = new HashMap<>()
//            HashMap<String, Double> igstGroup = new HashMap<>()
//            for (Object it : saleProductDetails) {
//                double amountBeforeTaxes = it.amount - it.cgstAmount - it.sgstAmount - it.igstAmount
//                totalBeforeTaxes += amountBeforeTaxes
//                if (it.igstPercentage > 0) {
//                    def igstPercentage = igstGroup.get(it.igstPercentage.toString())
//                    if (igstPercentage == null) {
//                        igstGroup.put(it.igstPercentage.toString(), amountBeforeTaxes)
//                    } else {
//                        igstGroup.put(it.igstPercentage.toString(), igstPercentage.doubleValue() + amountBeforeTaxes)
//                    }
//                } else {
//                    def gstPercentage = gstGroup.get(it.gstPercentage.toString())
//                    if (gstPercentage == null) {
//                        gstGroup.put(it.gstPercentage.toString(), amountBeforeTaxes)
//                    } else {
//                        gstGroup.put(it.gstPercentage.toString(), gstPercentage.doubleValue() + amountBeforeTaxes)
//                    }
//
//                    def sgstPercentage = sgstGroup.get(it.sgstPercentage.toString())
//                    if (sgstPercentage == null) {
//                        sgstGroup.put(it.sgstPercentage.toString(), amountBeforeTaxes)
//                    } else {
//                        sgstGroup.put(it.sgstPercentage.toString(), sgstPercentage.doubleValue() + amountBeforeTaxes)
//                    }
//                    def cgstPercentage = cgstGroup.get(it.cgstPercentage.toString())
//                    if (cgstPercentage == null) {
//                        cgstGroup.put(it.cgstPercentage.toString(), amountBeforeTaxes)
//                    } else {
//                        cgstGroup.put(it.cgstPercentage.toString(), cgstPercentage.doubleValue() + amountBeforeTaxes)
//                    }
//                }
//            }
//
//            def total = totalBeforeTaxes + totalcgst + totalsgst + totaligst
//
//            JSONObject irnDetails = null
//            if (saleBillDetail.has("irnDetails") && saleBillDetail.get("irnDetails") != null)
//                irnDetails = new JSONObject(saleBillDetail.get("irnDetails").toString())
//
//            render(view: "/sales/saleEntry/sale-invoice", model: [saleBillDetail    : saleBillDetail,
//                                                                  saleProductDetails: saleProductDetails,
//                                                                  series            : series, entity: entity, customer: customer, city: city,
//                                                                  total             : total, custcity: custcity,
//                                                                  termsConditions   : termsConditions,
//                                                                  totalcgst         : totalcgst, totalsgst: totalsgst, totaligst: totaligst,
//                                                                  totaldiscount     : totaldiscount,
//                                                                  gstGroup          : gstGroup,
//                                                                  sgstGroup         : sgstGroup,
//                                                                  cgstGroup         : cgstGroup,
//                                                                  igstGroup         : igstGroup,
//                                                                  totalBeforeTaxes  : totalBeforeTaxes,
//                                                                  irnDetails        : irnDetails
//            ])
//        } else {
//
//            render("No Bill Found")
//        }
//    }


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
        def entityConfigs = new EntityService().getEntityConfigByEntity(session.getAttribute('entityId').toString())
       /* if(entityConfigs.MODIFY_AFTER_DAYEND.saleEntry == true){
            def dayEndMaster = new EntityService().getDayEndByEntity(session.getAttribute('entityId').toString())
            JSONObject jsonObject = new JSONObject()
            if(dayEndMaster.size()!=0){
                Date presentDate = new Date();
                Date endDateTime = sdf.parse(dayEndMaster[0].endTime.toString())
                println(endDateTime.compareTo(presentDate) > 0)
                if(endDateTime.compareTo(presentDate) > 0){
                    jsonObject.put("dayend",true)
                    respond jsonObject,formats: ['json'], status: 200;
                    return
                }
            }
        }*/
        String id = params.id
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String userId = session.getAttribute("userId")
        JSONObject saleObject = new SalesService().getSaleBillDetailsById(id)
        JSONObject jsonObject = new SalesService().cancelInvoice(id, entityId, financialYear, userId)
//        if(jsonObject.invoice.balance == jsonObject.invoice.totalAmount){
        if (jsonObject)
        {
            //adjust stocks
            JSONArray productDetails = jsonObject.get("products")
            if (productDetails)
            {
                for (JSONObject productDetail : productDetails)
                {
                    def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())
                    double remainingQty = stockBook.get("remainingQty")
                    double remainingFreeQty = stockBook.get("remainingFreeQty")

                    //checking to where the stocks to be returned
                    double originalSqty = productDetail.get("originalSqty")
                    double originalFqty = productDetail.get("originalFqty")
                    double sqty = productDetail.get("sqty")
                    double freeQty = productDetail.get("freeQty")

                    if ((originalSqty + originalFqty) == (sqty + freeQty) && originalSqty == sqty && originalFqty == freeQty)
                    {
                        remainingQty += sqty
                        remainingFreeQty += freeQty
                    }
                    else
                    {
                        if (originalSqty >= sqty && originalFqty >= freeQty)
                        {
                            remainingQty += sqty
                            remainingFreeQty += freeQty
                        }
                        else
                        {
                            if (sqty > originalSqty)
                            {
                                remainingQty = sqty - (sqty - originalSqty)
                                remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
                            }
                            else if (freeQty > originalFqty)
                            {
                                remainingQty = remainingQty + sqty + (freeQty - originalFqty)
                                remainingFreeQty = freeQty - (freeQty - originalFqty)
                            }
                        }
                    }

                    double remainingReplQty = stockBook.get("remainingReplQty") + productDetail.get("repQty")
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", remainingReplQty.toLong())
                    new InventoryService().updateStockBook(stockBook)
                }
            }
            JSONObject invoice = jsonObject.get("invoice") as JSONObject
            if (invoice.has("irnDetails"))
            {
                JSONObject irnDetails = new JSONObject(invoice.get("irnDetails").toString())
                new EInvoiceService().cancelIRN(session, irnDetails.get("Irn").toString(), invoice.get("id").toString())
            }

            //email
            def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
            JSONObject salesEmailConfig
            if (emailSettings != null)
            {
                if (emailSettings?.salesEmailConfig != null && emailSettings?.salesEmailConfig != "")
                {
                    salesEmailConfig = new JSONObject(emailSettings?.salesEmailConfig)
                }
                if (salesEmailConfig?.SALE_DOC_CANCELLED_SEND_MAIL == "true")
                {
                    def entity = new EntityService().getEntityById(invoice?.customerId?.toString())
                    if (entity?.email != null && entity?.email != "" && entity?.email != "NA")
                    {
                        def email = new EmailService().sendEmail(entity.email.trim(), "Sale Invoice cancelled", invoice?.invoiceNumber, invoice?.invoiceNumber, Constants.SALE_INVOICE)
                        if (email)
                        {
                            println("Mail Sent..")
                        }
                        else
                        {
                            println("Mail not Sent..")
                        }
                    }
                    else
                    {
                        println("Email not found..")
                    }
                }
            }
            else
            {
                println("Entity Settings not found!!")
            }
            respond jsonObject, formats: ['json']
        }
        else
        {
            response.status = 400
        }
//        }else{
//            response.status = 400
//        }
    }


    def getSaleProductDetailsByBill()
    {
        try
        {
            if (params.id)
            {
                JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(params.id)
                if(saleProductDetails!=null){
                    def saleBillResponse = new SalesService().getSaleBillDetailsById(params.id.toString())
                    saleProductDetails.each {
                        println(it.batchNumber)
                        def stockResponse = new InventoryService().getStocksOfProductAndBatch(it.productId.toString(),
                                it.batchNumber.toString(), session.getAttribute('entityId').toString())
                        if (it.batchNumber == stockResponse.batchNumber)
                        {
                            def tax = new TaxController().show(stockResponse.taxId.toString())
                            it.put("gst", tax.taxValue)
                            it.put("sgst", tax.salesSgst)
                            it.put("cgst", tax.salesCgst)
                            it.put("igst", tax.salesIgst)
                        }
                        it.put("billId", saleBillResponse as JSONObject)
                        def apiResponse = new SalesService().getRequestWithId(it.productId.toString(), new Links().PRODUCT_REGISTER_SHOW)
                        it.put("productId", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
                    }
                    respond saleProductDetails, formats: ['json'], status: 200;
                }else{
                    response.status = 400
                }

            }
            else
            {
                return [];
            }
        }

        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }


    def updateSaleProductDetails()
    {
        try
        {

            JSONArray jsonArray = new JSONArray(params.rowData)
            JSONArray saleProductDetails = new JSONArray()
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(params.billId)
            Boolean isEdit = false
            int i = 0
            for (Object obj : jsonArray)
            {
                //15 if edit, 16 if being added
                if (i == 16 && obj != null)
                {
                    isEdit = true
                }
/*                else if(i == 16 && obj != null)
                    isEdit = false*/
                i++
            }
            String saleProductId
            if (jsonArray.isNull(25))
            {
                saleProductId = 0;
            }
            else
            {
                saleProductId = jsonArray[24]
            }
            String productId = jsonArray[1]
            String batchNumber = jsonArray[2]
            String expDate = jsonArray[3]
            String saleQty = jsonArray[4]
            String freeQty = jsonArray[5]
            String saleRate = jsonArray[6]
            String mrp = jsonArray[7]
            double discount = UtilsService.round(Double.parseDouble(jsonArray[8].toString()), 2)
            String packDesc = jsonArray[9]
            double gst = UtilsService.round(Double.parseDouble(jsonArray[10].toString()), 2)
            double value = UtilsService.round(Double.parseDouble(jsonArray[11].toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(jsonArray[12].toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(jsonArray[13].toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(jsonArray[14].toString()), 2)

            JSONObject saleProductDetail = new JSONObject()
            if (saleProductId != 0)
            {
                saleProductDetail.put("id", saleProductId)
            }
            saleProductDetail.put("finId", saleBillDetail.get('finId'))
            saleProductDetail.put("billType", 0)
            saleProductDetail.put("serBillId", saleBillDetail.get('serBillId'))
            saleProductDetail.put("seriesId", 1)
            saleProductDetail.put("billId", params.billId)
            saleProductDetail.put("productId", productId)
            saleProductDetail.put("batchNumber", batchNumber)
            saleProductDetail.put("expiryDate", expDate)
            saleProductDetail.put("sqty", saleQty)
            saleProductDetail.put("freeQty", freeQty)
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0) //TODO: to be changed
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)
            saleProductDetail.put("gstPercentage", jsonArray[17].toString())
            saleProductDetail.put("sgstPercentage", jsonArray[18].toString())
            saleProductDetail.put("cgstPercentage", jsonArray[19].toString())
            saleProductDetail.put("igstPercentage", jsonArray[20].toString())
            saleProductDetail.put("gstId", 0) //TODO: to be changed
            saleProductDetail.put("amount", value)
            saleProductDetail.put("reason", "") //TODO: to be changed
            saleProductDetail.put("fridgeId", 0) //TODO: to be changed
            saleProductDetail.put("kitName", 0) //TODO: to be changed
            saleProductDetail.put("saleFinId", "") //TODO: to be changed
            saleProductDetail.put("redundantBatch", 0) //TODO: to be changed
            saleProductDetail.put("status", 0)
            saleProductDetail.put("syncStatus", 0)
            saleProductDetail.put("financialYear", session.getAttribute('financialYear').toString())
            saleProductDetail.put("entityId", session.getAttribute("entityId").toString())
            saleProductDetail.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            saleProductDetail.put("uuid", params.uuid)
            saleProductDetails.add(saleProductDetail)

            //save to sale transaction log
            //save to sale transportation details
            if (!isEdit)
            {
                def saveResponse = new SalesService().saveSaleProductDetail(saleProductDetail)
                if (saveResponse?.status == 200)
                {
                    JSONObject obj = new JSONObject(saveResponse.readEntity(String.class))
                    def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                    if (productDetail)
                    {
                        def stockBook = new InventoryService().getStocksOfProductAndBatch(obj.productId.toString(), saleProductDetail.batchNumber, session.getAttribute("entityId").toString())
                        double remainingQty = stockBook.get("remainingQty") - obj.get("sqty")
                        double remainingFreeQty = stockBook.get("remainingFreeQty") - obj.get("freeQty")
                        double remainingReplQty = stockBook.get("remainingReplQty") - obj.get("repQty")
                        stockBook.put("remainingQty", remainingQty.toLong())
                        stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                        stockBook.put("remainingReplQty", remainingReplQty.toLong())
                        stockBook.put("uuid", params.uuid)
                        new InventoryService().updateStockBook(stockBook)
                    }
                    respond obj, formats: ['json'], status: 200
                }
            }
            else
            {
                if (saleProductId != null)
                {
                    def productDetail1 = new SalesService().getSaleProductDetailsById(saleProductId.toString())
                    println(productDetail1.productId)
                    if (productDetail1)
                    {
                        def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail1.productId.toString(),
                                productDetail1.batchNumber.toString(), session.getAttribute("entityId").toString())
                        double remainingQty = stockBook.get("remainingQty") + productDetail1.sqty
                        double remainingFreeQty = stockBook.get("remainingFreeQty") + productDetail1.freeQty
                        double remainingReplQty = stockBook.get("remainingReplQty") + productDetail1.repQty
                        stockBook.put("remainingQty", remainingQty.toLong())
                        stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                        stockBook.put("remainingReplQty", remainingReplQty.toLong())
                        new InventoryService().updateStockBook(stockBook)
                    }
                    def updateResponse = new SalesService().updateSaleProductDetail(saleProductDetail)
                    if (updateResponse?.status == 200)
                    {
                        JSONObject obj = new JSONObject(updateResponse.readEntity(String.class))
                        def productDetail = new SalesService().getSaleProductDetailsById(obj.id.toString())
                        if (productDetail)
                        {
                            def stockBook = new InventoryService().getStocksOfProductAndBatch(obj.productId.toString(), obj.batchNumber, session.getAttribute("entityId").toString())
                            double remainingQty = stockBook.get("remainingQty") - obj.get("sqty")
                            double remainingFreeQty = stockBook.get("remainingFreeQty") - obj.get("freeQty")
                            double remainingReplQty = stockBook.get("remainingReplQty") - obj.get("repQty")
                            stockBook.put("remainingQty", remainingQty.toLong())
                            stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                            stockBook.put("remainingReplQty", remainingReplQty.toLong())
                            new InventoryService().updateStockBook(stockBook)
                        }
                        respond obj, formats: ['json'], status: 200
                    }
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def deleteSaleProduct()
    {
        try
        {
            def id = params.id
            def productDetail = new SalesService().getSaleProductDetailsById(id)
            if (productDetail)
            {
                def stockBook = new InventoryService().getStocksOfProductAndBatch(productDetail.productId.toString(), productDetail.batchNumber, session.getAttribute("entityId").toString())

                double remainingQty = stockBook.get("remainingQty")
                double remainingFreeQty = stockBook.get("remainingFreeQty")

                //checking to where the stocks to be returned
                double originalSqty = productDetail.get("originalSqty")
                double originalFqty = productDetail.get("originalFqty")
                double sqty = productDetail.get("sqty")
                double freeQty = productDetail.get("freeQty")

                if ((originalSqty + originalFqty) == (sqty + freeQty) && originalSqty == sqty && originalFqty == freeQty)
                {
                    remainingQty += sqty
                    remainingFreeQty += freeQty
                }
                else
                {
                    if (originalSqty >= sqty && originalFqty >= freeQty)
                    {
                        remainingQty += sqty
                        remainingFreeQty += freeQty
                    }
                    else
                    {
                        if (sqty > originalSqty)
                        {
                            remainingQty = sqty - (sqty - originalSqty)
                            remainingFreeQty = remainingFreeQty + freeQty + (sqty - originalSqty)
                        }
                        else if (freeQty > originalFqty)
                        {
                            remainingQty = remainingQty + sqty + (freeQty - originalFqty)
                            remainingFreeQty = freeQty - (freeQty - originalFqty)
                        }
                    }
                }
                stockBook.put("remainingQty", remainingQty.toLong())
                stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                stockBook.put("remainingReplQty", 0) //TODO: to be checked
                new InventoryService().updateStockBook(stockBook)
            }
            def apiResponse = new SalesService().deleteSaleProduct(id);
            respond(text: id, status: apiResponse.status)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
    }

    def updateSaleBillDetails()
    {
        println(params)
        def entityConfigs = new EntityService().getEntityConfigByEntity(session.getAttribute('entityId').toString())
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
        String message = "NA"
        String refDate = params.refDate
        String refNo = params.refNum
        String privateNote = params.privateNote
        String publicNote = params.publicNote
        String rep = params.rep
        long finId = 0
        long serBillId = 0
        String financialYear = session.getAttribute("financialYear")
        def series = new EntityService().getSeriesById(seriesId)
            if (!billStatus.equalsIgnoreCase("DRAFT"))
            {
                def recentSaleBill = new SalesService().getRecentSaleBill(financialYear, entityId, billStatus)
                println(recentSaleBill)
                if (recentSaleBill != null && recentSaleBill.size() != 0)
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
            String saleProductId = ""
            if (sale.has("25")) //get saved draft product id
            {
                saleProductId = sale.get("25")
            }
            else
            {
                //means present in tempstock but not in draft
                saleProductId = "0"
            }

            double discount = UtilsService.round(Double.parseDouble(sale.get("8").toString()), 2)
            String packDesc = sale.get("9")
            double gst = UtilsService.round(Double.parseDouble(sale.get("10").toString()), 2)
            double value = UtilsService.round(Double.parseDouble(sale.get("11").toString()), 2)
            double sgst = UtilsService.round(Double.parseDouble(sale.get("12").toString()), 2)
            double cgst = UtilsService.round(Double.parseDouble(sale.get("13").toString()), 2)
            double igst = UtilsService.round(Double.parseDouble(sale.get("14").toString()), 2)
            totalSqty += Long.parseLong(saleQty)
            totalFqty += Long.parseLong(freeQty)
            totalAmount += value
            totalGst += gst
            totalSgst += sgst
            totalCgst += cgst
            totalIgst += igst
            totalDiscount += discount

            //save to sale transaction log
            //save to sale transportation details

            JSONObject saleProductDetail = new JSONObject()
            saleProductDetail.put("finId", finId)
            saleProductDetail.put("id", saleProductId)
            saleProductDetail.put("billId", 0)
            saleProductDetail.put("billType", 0)
            saleProductDetail.put("serBillId", 0)
            saleProductDetail.put("seriesId", seriesId)
            saleProductDetail.put("productId", productId)
            saleProductDetail.put("batchNumber", batchNumber)
            saleProductDetail.put("expiryDate", expDate)
            saleProductDetail.put("sqty", saleQty)
            saleProductDetail.put("freeQty", freeQty)
            saleProductDetail.put("sqtyReturn", saleQty)
            saleProductDetail.put("fqtyReturn", freeQty)
            saleProductDetail.put("repQty", 0)
            saleProductDetail.put("pRate", 0) //TODO: to be changed
            saleProductDetail.put("sRate", saleRate)
            saleProductDetail.put("mrp", mrp)
            saleProductDetail.put("discount", discount)
            saleProductDetail.put("gstAmount", gst)
            saleProductDetail.put("sgstAmount", sgst)
            saleProductDetail.put("cgstAmount", cgst)
            saleProductDetail.put("igstAmount", igst)

            saleProductDetail.put("gstPercentage", sale.get("17").toString())
            saleProductDetail.put("sgstPercentage", sale.get("18").toString())
            saleProductDetail.put("cgstPercentage", sale.get("19").toString())
            saleProductDetail.put("igstPercentage", sale.get("20").toString())

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
            saleProductDetail.put("uuid", params.uuid)
            saleProductDetail.put("originalSqty", sale.get("21").toString())
            saleProductDetail.put("originalFqty", sale.get("22").toString())
            if(sale.has('15')){
                saleProductDetail.put("replacement",sale.get('15'))
            }else{
                saleProductDetail.put("replacement",false)
            }

            saleProductDetails.add(saleProductDetail)
        }

        String entryDate = sdf.format(new Date())
        String orderDate = sdf.format(new Date())
        //update to sale bill details
        if (refDate != '')
        {
            saleBillDetails.put("refDate", refDate)
        }
        else
        {
            saleBillDetails.put("refDate", '')
        }
        saleBillDetails.put("refNo", refNo)
        saleBillDetails.put("publicNote", publicNote)
        saleBillDetails.put("privateNote", privateNote)
        saleBillDetails.put("id", params.id)
        saleBillDetails.put("serBillId", serBillId)
        saleBillDetails.put("customerId", customerId)
        saleBillDetails.put("customerNumber", 0) //TODO: to be changed
        saleBillDetails.put("finId", finId)
        saleBillDetails.put("seriesId", seriesId)
        saleBillDetails.put("priorityId", priorityId)
        saleBillDetails.put("financialYear", session.getAttribute('financialYear'))
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
        saleBillDetails.put("invtype", params.invtype)
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
        saleBillDetails.put("rep", rep)
        saleBillDetails.put("uuid", params.uuid)
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("saleInvoice", saleBillDetails)
        jsonObject.put("saleProducts", saleProductDetails)
        Response response = new SalesService().updateSaleInvoice(jsonObject, saleBillDetails.get("id").toString())
        if (response.status == 200)
        {
            def saleBillDetail = new JSONObject(response.readEntity(String.class))
            if (saleBillDetail)
            {

                //update stockbook
                for (JSONObject sale : saleData)
                {
                    String productId = sale.get("1")
                    String batchNumber = sale.get("2")
                    String tempStockRowId = null
                    JSONObject tmpStockBook = null
                    if (sale.has("16"))
                    {
                        tmpStockBook = new InventoryService().getTempStocksById(Long.parseLong(sale.get("16").toString()))
                        tempStockRowId = tmpStockBook?.get("id")
                    }
                    else
                    {
                        println("Not in tmp stock")
                    }
                    def stockBook
                    long userOrderedSaleQty = Long.parseLong(sale.get("4").toString())
                    long userOrderedFreeQty = Long.parseLong(sale.get("5").toString())
                    long remainingQty = 0
                    long remainingFreeQty = 0


                    if (tmpStockBook)
                    {
                        stockBook = new InventoryService().getStockBookById(Long.parseLong(tmpStockBook.originalId))
                        stockBook.put("remainingQty", tmpStockBook.get("remainingQty"))
                        stockBook.put("remainingFreeQty", tmpStockBook.get("remainingFreeQty"))

                    }
                    else
                    {
                        stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber, entityId)
                        remainingQty = stockBook.get("remainingQty")
                        remainingFreeQty = stockBook.get("remainingFreeQty")

                        if (userOrderedSaleQty != sale.get("23"))
                        {
                            long finalSqty = remainingQty - (userOrderedSaleQty - sale.get("23"))
                            stockBook.put("remainingQty", finalSqty)
                        }
                        if (userOrderedFreeQty != sale.get("24"))
                        {
                            long finalFqty = remainingFreeQty - (userOrderedFreeQty - sale.get("24"))
                            stockBook.put("remainingFreeQty", finalFqty)
                        }
                    }
                    stockBook.put("remainingReplQty", 0)

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
                    stockBook.put("uuid", UUID.randomUUID())
                    def apiRes = new InventoryService().updateStockBook(stockBook)
                    if (apiRes.status == 200)
                    {
                        //clear tempstockbook
                        if (tmpStockBook && tempStockRowId)
                        {
                            new InventoryService().deleteTempStock(tempStockRowId, false)
                        } //just drop temp stock

                    }
                }

                if (params.saleTransportDetailsId != null || params.saleTransportDetailsId != '')
                {
                    if (params.lrNumber != '' && params.lrDate != '' && params.transporter != '')
                    {
                        JSONObject transportObject = new JSONObject();
                        transportObject.put("finId", finId)
                        transportObject.put("billId", saleBillDetail.id)
                        transportObject.put("billType", "SALE_INVOICE")
                        transportObject.put("serBillId", saleBillDetail.serBillId)
                        transportObject.put("series", saleBillDetail.seriesId)
                        transportObject.put("customerId", saleBillDetail.customerId)
                        transportObject.put("transporterId", params.transporter)
                        transportObject.put("lrDate", params.lrDate)
                        transportObject.put("lrNumber", params.lrNumber)
                        transportObject.put("cartonsCount", "")
                        transportObject.put("paid", 0)
                        transportObject.put("toPay", 0)
                        transportObject.put("generalInfo", 0)
                        transportObject.put("selfNo", 0)
                        transportObject.put("ccm", 0)
                        transportObject.put("recievedTemprature", 0)
                        transportObject.put("freightCharge", 0)
                        transportObject.put("vechileId", 0)
                        transportObject.put("deliveryStatus", 0)
                        transportObject.put("dispatchDateTime", 0)
                        transportObject.put("deliveryDateTime", 0)
                        transportObject.put("trackingDetails", 0)
                        transportObject.put("ewaybillId", 0)
                        transportObject.put("genralInfo", 0)
                        transportObject.put("weight", 0)
                        transportObject.put("ewaysupplytype", 0)
                        transportObject.put("ewaysupplysubtype", 0)
                        transportObject.put("ewaydoctype", 0)
                        transportObject.put("consignmentNo", 0)
                        transportObject.put("syncStatus", 0)
                        transportObject.put("financialYear", 0)
                        transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                        transportObject.put("entityId", session.getAttribute('entityId'))
                        transportObject.put("id", params.saleTransportDetailsId)
                        Response transportation = new SalesService().updateSaleTransportation(transportObject)
                        if (transportation?.status == 200)
                        {
                            println("Transportation details Updated")
                        }
                        else
                        {
                            println("something went wrong!!")
                        }
                    }
                    else
                    {
                        println("Transportation Details not found!")
                    }
                }
                else
                {

                    if (params.lrNumber != '' && params.lrDate != '' && params.transporter != '')
                    {
                        JSONObject transportObject = new JSONObject();
                        transportObject.put("finId", finId)
                        transportObject.put("id", params.shipmentDetailId)
                        transportObject.put("billId", saleBillDetail.id)
                        transportObject.put("billType", "SALE_INVOICE")
                        transportObject.put("serBillId", saleBillDetail.serBillId)
                        transportObject.put("series", saleBillDetail.seriesId)
                        transportObject.put("customerId", saleBillDetail.customerId)
                        transportObject.put("transporterId", params.transporter)
                        transportObject.put("lrDate", params.lrDate)
                        transportObject.put("lrNumber", params.lrNumber)
                        transportObject.put("cartonsCount", "")
                        transportObject.put("paid", 0)
                        transportObject.put("toPay", 0)
                        transportObject.put("generalInfo", 0)
                        transportObject.put("selfNo", 0)
                        transportObject.put("ccm", 0)
                        transportObject.put("recievedTemprature", 0)
                        transportObject.put("freightCharge", 0)
                        transportObject.put("vechileId", 0)
                        transportObject.put("deliveryStatus", 0)
                        transportObject.put("dispatchDateTime", 0)
                        transportObject.put("deliveryDateTime", 0)
                        transportObject.put("trackingDetails", 0)
                        transportObject.put("ewaybillId", 0)
                        transportObject.put("genralInfo", 0)
                        transportObject.put("weight", 0)
                        transportObject.put("ewaysupplytype", 0)
                        transportObject.put("ewaysupplysubtype", 0)
                        transportObject.put("ewaydoctype", 0)
                        transportObject.put("consignmentNo", 0)
                        transportObject.put("syncStatus", 0)
                        transportObject.put("financialYear", 0)
                        transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                        transportObject.put("entityId", session.getAttribute('entityId'))
                        Response transportation = new SalesService().saveSaleTransportation(transportObject)
                        if (transportation?.status == 200)
                        {
                            println("Transportation details added")
                        }
                        else
                        {
                            println("something went wrong!!")
                        }
                    }
                    else
                    {
                        println("Transportation Details not found!")
                    }
                }


                try
                {
                    if (billStatus.equalsIgnoreCase("ACTIVE"))
                    {
                        //push the invoice to e-Invoice service and generate IRN, save IRN to Sale Bill Details
                        new EInvoiceService().generateIRN(session, saleBillDetail, saleProductDetails)
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace()
                }
                //email
                def emailSettings = EmailService.getEmailSettingsByEntity(session.getAttribute("entityId").toString())
                JSONObject salesEmailConfig
                if (emailSettings != null)
                {
                    if (emailSettings?.salesEmailConfig != null && emailSettings?.salesEmailConfig != "")
                    {
                        salesEmailConfig = new JSONObject(emailSettings?.salesEmailConfig)
                    }
                    if (salesEmailConfig?.SALE_AUTO_EMAIL_AFTER_SAVE_SALE_ENTRY == "true")
                    {
                        Object emailContent = g.render(template: '/sales/saleEntry')
                        def entity = new EntityService().getEntityById(params.customer)
                        if (entity?.email != null && entity?.email != "" && entity?.email != "NA")
                        {
                            def email = new EmailService().sendEmail(entity.email.trim(), "Sale Invoice saved", saleBillDetail?.invoiceNumber, saleBillDetail?.invoiceNumber, Constants.SALE_INVOICE)
                            if (email)
                            {
                                println("Mail Sent..")
                            }
                            else
                            {
                                println("Mail not Sent..")
                            }
                        }
                        else
                        {
                            println("Email not found..")
                        }
                    }
                }
                else
                {
                    println("Entity Settings not found!!")
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
        else
        {
            response.status == 400
        }
    }


    def cloneSaleBillDetails()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        def users = new UserRegisterController().getByEntity()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def saleBillId = params.saleBillId
        JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleBillId)
        JSONObject saleTransportDetail = new SalesService().getSaleTransportationByBill(saleBillId,Constants.SALE_INVOICE)
        Object transporter = new ShipmentService().getAllTransporterByEntity(entityId)
        JSONObject customer = new EntityService().getEntityById(saleBillDetail.customerId.toString())
//        if (saleBillDetail != null && saleBillDetail.billStatus == 'DRAFT') {
        JSONArray saleProductDetails = new SalesService().getSaleProductDetailsByBill(saleBillId)
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
            it.put("product", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
        }
        JSONArray tempstockArray = new JSONArray();
        for (JSONObject saleProductObject : saleProductDetails)
        {
            def stockBook = new InventoryService().getStocksOfProductAndBatch(saleProductObject?.productId?.toString(), saleProductObject?.batchNumber?.toString(), session.getAttribute("entityId").toString())
            def tempStockBook = new InventoryService().getTempStocksOfProductAndBatchAndEntityId(saleProductObject?.productId?.toString(), saleProductObject?.batchNumber?.toString(), session.getAttribute("entityId").toString())
            if (stockBook != null)
            {
                Boolean stocksAvailable = false
                if ((stockBook.remainingQty + stockBook.remainingFreeQty) >= (saleProductObject?.sqty +
                        saleProductObject?.freeQty))
                {
                    stocksAvailable = true
                }

                if (tempStockBook.size() == 0)
                {
                    if (stocksAvailable)
                    {

                        if (tempStockBook.size() == 0)
                        {
                            long saleQty = saleProductObject?.sqty
                            long freeQty = saleProductObject?.freeQty

                            long remainingQty = stockBook.remainingQty
                            long remainingFreeQty = stockBook.remainingFreeQty
                            if (saleQty <= remainingQty)
                            {
                                remainingQty = remainingQty - saleQty

                            }
                            else if (saleQty > remainingQty && saleQty <= (remainingQty + remainingFreeQty))
                            {
                                remainingFreeQty = remainingFreeQty - (saleQty - remainingQty)
                                remainingQty = 0
                            }
                            if (freeQty <= remainingFreeQty)
                            {
                                remainingFreeQty = remainingFreeQty - freeQty
                            }
                            else if (freeQty > remainingFreeQty && freeQty <= (remainingQty + remainingFreeQty))
                            {
                                remainingQty = remainingQty - (freeQty - remainingFreeQty)
                                remainingFreeQty = 0
                            }

                            //Remove Negative
                            long userOrderQty = (saleQty < 0 ? -saleQty : saleQty)
                            long userOrderFreeQty = (freeQty < 0 ? -freeQty : freeQty)

                            JSONObject jsonObject = new JSONObject()
                            jsonObject.put("productId", saleProductObject?.productId)
                            jsonObject.put("batchNumber", saleProductObject?.batchNumber)
                            jsonObject.put("expDate", saleProductObject?.expiryDate)
                            jsonObject.put("remainingQty", remainingQty)
                            jsonObject.put("remainingFreeQty", remainingFreeQty)
                            jsonObject.put("remainingReplQty", 0)
                            jsonObject.put("saleRate", saleProductObject?.sRate)
                            jsonObject.put("purchaseRate", saleProductObject?.pRate)
                            jsonObject.put("mrp", saleProductObject?.mrp)
                            jsonObject.put("discount", saleProductObject?.discount)
                            jsonObject.put("packingDesc", saleProductObject?.product?.unitPacking)
                            jsonObject.put("userOrderQty", userOrderQty)
                            jsonObject.put("userOrderFreeQty", userOrderFreeQty)
                            jsonObject.put("userOrderReplQty", 0)
                            jsonObject.put("taxId", stockBook?.taxId)
                            jsonObject.put("userId", session.getAttribute("userId"))
                            jsonObject.put("entityId", session.getAttribute("entityId"))
                            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId"))
                            jsonObject.put("redundantBatch", "")
                            jsonObject.put("originalId", stockBook?.id)
                            jsonObject.put("uuid", UUID.randomUUID())
                            jsonObject.put("originalSqty", saleProductObject?.originalSqty)
                            jsonObject.put("originalFqty", saleProductObject?.originalFqty)
                            if(saleProductObject?.replacement!=null){
                                jsonObject.put("replacement", saleProductObject?.replacement)
                            }else{
                                jsonObject.put("replacement", false)
                            }
                            def apiResponse = new InventoryService().tempStockBookSave(jsonObject)
                            if (apiResponse?.status == 200)
                            {
//                emitTempStockPool()
                                println("Temp stock book added!")
//                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                            }
                        }
                    }
                    else
                    {
                        JSONObject tempStockObj = new JSONObject()
                        tempStockObj.put("productId", saleProductObject?.productId)
                        tempStockObj.put("batchNumber", saleProductObject?.batchNumber)
                        tempStockObj.put("expDate", saleProductObject?.expiryDate)
                        tempStockObj.put("remainingQty", stockBook?.remainingQty)
                        tempStockObj.put("remainingFreeQty", stockBook?.remainingFreeQty)
                        tempStockObj.put("remainingReplQty", 0)
                        tempStockObj.put("saleRate", saleProductObject?.sRate)
                        tempStockObj.put("purchaseRate", saleProductObject?.pRate)
                        tempStockObj.put("mrp", saleProductObject?.mrp)
                        tempStockObj.put("discount", saleProductObject?.discount)
                        tempStockObj.put("packingDesc", saleProductObject?.product?.unitPacking)
                        tempStockObj.put("userOrderQty", saleProductObject?.sqty)
                        tempStockObj.put("userOrderFreeQty", saleProductObject?.freeQty)
                        tempStockObj.put("userOrderReplQty", 0)
                        tempStockObj.put("taxId", stockBook?.taxId)
                        tempStockObj.put("userId", session.getAttribute("userId"))
                        tempStockObj.put("entityId", session.getAttribute("entityId"))
                        tempStockObj.put("entityTypeId", session.getAttribute("entityTypeId"))
                        tempStockObj.put("redundantBatch", "")
                        tempStockObj.put("originalId", stockBook?.id)
                        tempStockObj.put("uuid", UUID.randomUUID())
                        tempStockObj.put("originalSqty", saleProductObject?.originalSqty)
                        tempStockObj.put("originalFqty", saleProductObject?.originalFqty)
                        if(saleProductObject?.replacement!=null){
                            tempStockObj.put("replacement", saleProductObject?.replacement)
                        }else{
                            tempStockObj.put("replacement", false)
                        }
                        tempstockArray.add(tempStockObj)
                    }
                }
            }

        }
        tempstockArray.each {
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
            it.put("product", JSON.parse(apiResponse.readEntity(String.class)) as JSONObject)
        }
        println(tempstockArray)
        render(view: '/sales/saleEntry/sale-entry', model: [customers         : customers, divisions: divisions, series: series,
                                                            priorityList      : priorityList, saleBillDetail: saleBillDetail,
                                                            saleProductDetails: saleProductDetails,
                                                            transporter       : transporter, tempStockArray: tempstockArray,
                                                            customer          : customer, saleTransportDetail: saleTransportDetail,
                                                            users:users])
//        }
//    else {
//            redirect(uri: "/sale-entry")
//        }

    }

    def mailInvoice(){
        render(view: '/templates/_bill-template')
    }


    def updateMassDiscount(){
        try{
            JSONArray jsonArray = new JSONArray(params.data)
            def updateDiscount = new SalesService().updateMassDiscount(jsonArray,Double.parseDouble(params.discount.toString()))
            if(updateDiscount?.status == 200){
                JSONArray responseArray = new JSONArray(updateDiscount.readEntity(String.class))
                respond responseArray, formats: ['json'], status: 200;
            }else{
                response.status = 400
            }
        }
        catch(Exception ex){
            println(ex)
        }
    }
}
