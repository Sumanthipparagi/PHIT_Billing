package phitb_sales

import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.hibernate.Criteria
import org.hibernate.SQLQuery

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.sql.ResultSet
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class ReportsController {

    def sessionFactory
    static responseFormats = ['json', 'xml']
    static allowedMethods = [getCustomerWiseBillDetails: ["GET", "POST"]]

    def getCustomerWiseBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if (sortby.equalsIgnoreCase("invoice-date"))
                sort = "orderDate"

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()

            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                def saleBillJson = new JSONObject((saleBillDetail as JSON).toString())
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                saleBillJson.put("products", saleProductDetails)
                if (customerBills.containsKey(saleBillJson.customerId)) {
                    bills = customerBills.get(saleBillJson.customerId) as JSONArray
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.customerId, bills)
                } else {
                    bills = new JSONArray()
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.customerId, bills)
                }
            }

            render customerBills as JSON
        }
        catch (Exception ex) {
            println(ex.stackTrace)
        }
    }

    def getDateWiseBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if (sortby.equalsIgnoreCase("invoice-date"))
                sort = "orderDate"

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            LinkedHashMap<String, LinkedList> customerBills = new LinkedHashMap<>()
            LinkedList bills = new LinkedList()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                def saleBillJson = new JSONObject((saleBillDetail as JSON).toString())
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                saleBillJson.put("products", saleProductDetails)
                if (customerBills.containsKey(saleBillJson.orderDate)) {
                    bills = customerBills.get(saleBillJson.orderDate) as LinkedList
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.orderDate.toString(), bills)
                } else {
                    bills = new JSONArray()
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.orderDate.toString(), bills)
                }
            }
            render customerBills as JSON
        }
        catch (Exception ex) {
            println(ex.stackTrace)
        }
    }

    def getAreaWiseBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            boolean paidInvoice = true
            if (jsonObject.has("paidInvoice"))
                paidInvoice = jsonObject.get("paidInvoice")
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if (sortby.equalsIgnoreCase("invoice-date"))
                sort = "orderDate"

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = new ArrayList<>()
            if (paidInvoice) {
                saleBillDetails = SaleBillDetails
                        .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])

            } else {
                saleBillDetails = SaleBillDetails
                        .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqualAndBalanceLessThanEquals(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", 0, [sort: sort, order: 'desc'])
            }
            ArrayList<String> custIds = [];
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                def saleBillJson = new JSONObject((saleBillDetail as JSON).toString())
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                saleBillJson.put("products", saleProductDetails)
                saleBillJson.put("docType", "INVS")
                def customerJson = getEntityById(saleBillJson.customerId.toString())
//                customerJson.put("netAmt",saleBillJson.balance.sum())
//                if(!custIds.contains(customerJson.id)){
                def cityJson = getCityById(customerJson.cityId.toString())
                customerJson.put("cityId", cityJson)
                customerJson.put("salebill", saleBillJson)
                saleBillJson.put("customer", customerJson)
                if (customerBills.containsKey(saleBillJson?.customer?.cityId?.id)) {
                    bills = customerBills.get(saleBillJson.customer.cityId.id) as JSONArray
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.customer.cityId.id, bills)
                } else {
                    bills = new JSONArray()
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson?.customer?.cityId?.id, bills)
                }
//                }
                custIds.add(customerJson.id)
            }
            render customerBills as JSON
        }
        catch (Exception ex) {
            println(ex.stackTrace)
        }
    }

    def getConsolidatedBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if (sortby.equalsIgnoreCase("invoice-date"))
                sort = "orderDate"
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails
                    .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong (entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])

            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                def saleBillJson = new JSONObject((saleBillDetail as JSON).toString())
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                saleBillJson.put("products", saleProductDetails)
                if (customerBills.containsKey(saleBillJson.customerId)) {
                    bills = customerBills.get(saleBillJson.customerId) as JSONArray
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.customerId, bills)
                } else {
                    bills = new JSONArray()
                    bills.add(saleBillJson)
                    customerBills.put(saleBillJson.customerId, bills)
                }
            }
            render customerBills as JSON
        }
        catch (Exception ex) {
            println(ex.stackTrace)
        }
    }


    def getEntityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Constants().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Constants().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getCityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Constants().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Constants().CITY_MASTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getSalesGSTReport() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        String daterange = jsonObject.get("dateRange")
        String sortby = jsonObject.get("sortBy")
        String sort = "id"
        if (sortby.equalsIgnoreCase("invoice-date"))
            sort = "orderDate"

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        Date fromDate = sdf.parse(daterange.split("-")[0].trim())
        Date toDate = sdf.parse(daterange.split("-")[1].trim())
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        toDate = cal.getTime()
        JSONObject gstReport = new JSONObject()
        ArrayList taxes = new ArrayList()
        LinkedList invoiceGstDetails = new LinkedList()
        ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByOrderDateBetweenAndBillStatusAndEntityIdAndFinancialYear(fromDate, toDate, "ACTIVE", Long.parseLong(entityId), financialYear)
        for (SaleBillDetails saleBillDetail : saleBillDetails) {
            ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
            if (saleProductDetails?.size() > 0) {
                LinkedHashMap gstDetail = new LinkedHashMap()
                gstDetail.put("seriesId", saleBillDetail.seriesId)
                gstDetail.put("customerId", saleBillDetail.customerId)
                gstDetail.put("orderDate", saleBillDetail.orderDate)
                gstDetail.put("invoiceNumber", saleBillDetail.invoiceNumber)
                for (SaleProductDetails saleProductDetail : saleProductDetails) {
                    if (!taxes.contains(saleProductDetail.gstPercentage))
                        taxes.add(saleProductDetail.gstPercentage)
                    double amount = saleProductDetail.amount - saleProductDetail.gstAmount
                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_gst_amount")) {
                        gstDetail.put(saleProductDetail.gstPercentage + "_gst_amount", amount + (gstDetail.get(saleProductDetail.gstPercentage + "_gst_amount") as Number))
                    } else {
                        gstDetail.put(saleProductDetail.gstPercentage + "_gst_amount", amount)
                    }

                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_gst"))
                        gstDetail.put(saleProductDetail.gstPercentage + "_gst", saleProductDetail.gstAmount + (gstDetail.get(saleProductDetail.gstPercentage + "_gst") as Number))
                    else
                        gstDetail.put(saleProductDetail.gstPercentage + "_gst", saleProductDetail.gstAmount)

                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_cgst_" + saleProductDetail.cgstPercentage))
                        gstDetail.put(saleProductDetail.gstPercentage + "_cgst_" + saleProductDetail.cgstPercentage, saleProductDetail.cgstAmount + (gstDetail.get(saleProductDetail.gstPercentage + "_cgst_" + saleProductDetail.cgstPercentage) as Number))
                    else
                        gstDetail.put(saleProductDetail.gstPercentage + "_cgst_" + saleProductDetail.cgstPercentage, saleProductDetail.cgstAmount)

                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_sgst_" + saleProductDetail.sgstPercentage))
                        gstDetail.put(saleProductDetail.gstPercentage + "_sgst_" + saleProductDetail.sgstPercentage, saleProductDetail.sgstAmount + (gstDetail.get(saleProductDetail.gstPercentage + "_sgst_" + saleProductDetail.sgstPercentage) as Number))
                    else
                        gstDetail.put(saleProductDetail.gstPercentage + "_sgst_" + saleProductDetail.sgstPercentage, saleProductDetail.sgstAmount)

                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_igst_" + saleProductDetail.igstPercentage))
                        gstDetail.put(saleProductDetail.gstPercentage + "_igst_" + saleProductDetail.igstPercentage, saleProductDetail.igstAmount + (gstDetail.get(saleProductDetail.gstPercentage + "_igst_" + saleProductDetail.igstPercentage) as Number))
                    else
                        gstDetail.put(saleProductDetail.gstPercentage + "_igst_" + saleProductDetail.igstPercentage, saleProductDetail.igstAmount)
                }
                gstDetail.put("invoiceTotal", saleBillDetail.invoiceTotal)
                invoiceGstDetails.add(gstDetail)
            }
        }

        gstReport.put("gstDetails", invoiceGstDetails)
        gstReport.put("taxes", taxes)

        respond gstReport
    }

    def getCreditNoteGSTReport() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        String daterange = jsonObject.get("dateRange")
        String sortby = jsonObject.get("sortBy")
        String sort = "id"
        if (sortby.equalsIgnoreCase("invoice-date"))
            sort = "entryDate"

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        Date fromDate = sdf.parse(daterange.split("-")[0].trim())
        Date toDate = sdf.parse(daterange.split("-")[1].trim())
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        toDate = cal.getTime()
        JSONObject gstReport = new JSONObject()
        ArrayList taxes = new ArrayList()
        LinkedList retrunGstDetails = new LinkedList()
        ArrayList<SaleReturn> saleReturns = SaleReturn.findAllByEntryDateBetweenAndReturnStatusAndEntityIdAndFinancialYear(fromDate, toDate, "ACTIVE", Long.parseLong(entityId), financialYear)
        for (SaleReturn saleReturn : saleReturns) {
            ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillId(saleReturn.id)
            if (saleReturnDetails?.size() > 0) {
                LinkedHashMap gstDetail = new LinkedHashMap()
                gstDetail.put("seriesId", saleReturn.series)
                gstDetail.put("customerId", saleReturn.customerId)
                gstDetail.put("orderDate", saleReturn.entryDate)
                gstDetail.put("invoiceNumber", saleReturn.invoiceNumber)
                for (SaleReturnDetails saleReturnDetail : saleReturnDetails) {
                    if (!taxes.contains(saleReturnDetail.gstPercentage))
                        taxes.add(saleReturnDetail.gstPercentage)
                    double amount = saleReturnDetail.amount - saleReturnDetail.gstAmount
                    if (gstDetail.containsKey(saleReturnDetail.gstPercentage + "_gst_amount")) {
                        gstDetail.put(saleReturnDetail.gstPercentage + "_gst_amount", amount + (gstDetail.get(saleReturnDetail.gstPercentage + "_gst_amount") as Number))
                    } else {
                        gstDetail.put(saleReturnDetail.gstPercentage + "_gst_amount", amount)
                    }

                    if (gstDetail.containsKey(saleReturnDetail.gstPercentage + "_gst"))
                        gstDetail.put(saleReturnDetail.gstPercentage + "_gst", saleReturnDetail.gstAmount + (gstDetail.get(saleReturnDetail.gstPercentage + "_gst") as Number))
                    else
                        gstDetail.put(saleReturnDetail.gstPercentage + "_gst", saleReturnDetail.gstAmount)

                    if (gstDetail.containsKey(saleReturnDetail.gstPercentage + "_cgst_" + saleReturnDetail.cgstPercentage))
                        gstDetail.put(saleReturnDetail.gstPercentage + "_cgst_" + saleReturnDetail.cgstPercentage, saleReturnDetail.cgstAmount + (gstDetail.get(saleReturnDetail.gstPercentage + "_cgst_" + saleReturnDetail.cgstPercentage) as Number))
                    else
                        gstDetail.put(saleReturnDetail.gstPercentage + "_cgst_" + saleReturnDetail.cgstPercentage, saleReturnDetail.cgstAmount)

                    if (gstDetail.containsKey(saleReturnDetail.gstPercentage + "_sgst_" + saleReturnDetail.sgstPercentage))
                        gstDetail.put(saleReturnDetail.gstPercentage + "_sgst_" + saleReturnDetail.sgstPercentage, saleReturnDetail.sgstAmount + (gstDetail.get(saleReturnDetail.gstPercentage + "_sgst_" + saleReturnDetail.sgstPercentage) as Number))
                    else
                        gstDetail.put(saleReturnDetail.gstPercentage + "_sgst_" + saleReturnDetail.sgstPercentage, saleReturnDetail.sgstAmount)

                    if (gstDetail.containsKey(saleReturnDetail.gstPercentage + "_igst_" + saleReturnDetail.igstPercentage))
                        gstDetail.put(saleReturnDetail.gstPercentage + "_igst_" + saleReturnDetail.igstPercentage, saleReturnDetail.igstAmount + (gstDetail.get(saleReturnDetail.gstPercentage + "_igst_" + saleReturnDetail.igstPercentage) as Number))
                    else
                        gstDetail.put(saleReturnDetail.gstPercentage + "_igst_" + saleReturnDetail.igstPercentage, saleReturnDetail.igstAmount)
                }
                gstDetail.put("invoiceTotal", saleReturn.totalAmount)
                retrunGstDetails.add(gstDetail)
            }
        }
        gstReport.put("gstDetails", retrunGstDetails)
        gstReport.put("taxes", taxes)
        respond gstReport
    }

    def getSaleReturnAreaWiseBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            String sortby = jsonObject.get("sortBy")

            boolean paidInvoice = true
            if (jsonObject.has("paidInvoice"))
                paidInvoice = jsonObject.get("paidInvoice")

            String sort = "id"
            if (sortby.equalsIgnoreCase("invoice-date"))
                sort = "orderDate"

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()
            ArrayList<SaleReturn> saleReturns = new ArrayList<>()
            if (paidInvoice) {
                saleReturns = SaleReturn.findAllByEntityIdAndFinancialYearAndEntryDateBetweenAndCancelledDateIsNull(Long.parseLong(entityId), financialYear, fromDate, toDate, [sort: sort, order: 'desc'])
            } else {
                saleReturns = SaleReturn.findAllByEntityIdAndFinancialYearAndEntryDateBetweenAndCancelledDateIsNullAndBalanceLessThanEquals(Long.parseLong(entityId), financialYear, fromDate, toDate, 0, [sort: sort, order: 'desc'])
            }
            ArrayList<String> custIds = [];
            for (SaleReturn saleReturn : saleReturns) {
                def saleReturnJson = new JSONObject((saleReturn as JSON).toString())
                saleReturnJson.put("docType", "CRNT")
                ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillId(saleReturn.id)
                saleReturnJson.put("products", saleReturnDetails)
                def customerJson = getEntityById(saleReturnJson.customerId.toString())
                def cityJson = getCityById(customerJson.cityId.toString())
                customerJson.put("cityId", cityJson)
                customerJson.put("salebill", saleReturnJson)
                saleReturnJson.put("customer", customerJson)
                if (customerBills.containsKey(saleReturnJson.customer.cityId.id)) {
                    bills = customerBills.get(saleReturnJson.customer.cityId.id) as JSONArray
                    bills.add(saleReturnJson)
                    customerBills.put(saleReturnJson.customer.cityId.id, bills)
                } else {
                    bills = new JSONArray()
                    bills.add(saleReturnJson)
                    customerBills.put(saleReturnJson.customer.cityId.id, bills)
                }
                custIds.add(customerJson.id)
            }
            render customerBills as JSON
        }
        catch (Exception ex) {
            println(ex.stackTrace)
        }
    }

    def getSalesStats() {
        DecimalFormat df = new DecimalFormat("0.00");
        JSONObject jsonObject = new JSONObject(request.reader.text)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        long entityId = Long.parseLong(jsonObject.get("entityId").toString())
        long userId = Long.parseLong(jsonObject.get("userId").toString())
        Date fromDate = sdf.parse(jsonObject.get("fromDate").toString())
        Date toDate = sdf.parse(jsonObject.get("toDate").toString())
        String financialYear = jsonObject.get("financialYear").toString()
        double totalSales = 0
        double totalSalesReturn = 0
        double totalOutstanding = 0
        if (entityId && userId) {
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndUserIdAndBillStatusAndFinancialYearAndOrderDateBetween(entityId, userId, "ACTIVE", financialYear, fromDate, toDate)
            if (saleBillDetails?.size() > 0) {
                for (SaleBillDetails saleBillDetail : saleBillDetails) {
                    ArrayList<SaleProductDetails> productDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                    if (productDetails) {
                        totalSales += productDetails?.amount?.sum()
                        totalOutstanding += saleBillDetail.balance
                    }
                }

            }

            ArrayList<SaleReturn> saleReturns = SaleReturn.findAllByEntityIdAndCreatedUserAndReturnStatusAndFinancialYearAndEntryDateBetween(entityId, userId, "ACTIVE", financialYear, fromDate, toDate)
            if (saleReturns?.size() > 0) {
                for (SaleReturn saleReturn : saleReturns) {
                    ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllById(saleReturn.id)
                    if (saleReturnDetails)
                        totalSalesReturn += saleReturnDetails?.amount?.sum()
                }
            }
        }

        long totalDraftInvoice = SaleBillDetails.countByBillStatusAndEntityIdAndUserIdAndFinancialYear("DRAFT", entityId, userId, financialYear)

        JSONObject stats = new JSONObject()
        stats.put("totalSales", Double.parseDouble(df.format(totalSales)))
        stats.put("totalSalesReturn", Double.parseDouble(df.format(totalSalesReturn)))
        stats.put("totalOutstanding", Double.parseDouble(df.format(totalOutstanding)))
        stats.put("totalDraftInvoice", totalDraftInvoice)
        respond stats, formats: ['json']
    }

    def getSalesInfoTillDate() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            println(jsonObject.toString())
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date date = sdf.parse(jsonObject.get("date").toString())
            long entityId = jsonObject.get("entityId")
            HashMap<Long, JSONArray> totalSales = new SaleReportsService().getTotalSalesTillDate(date, entityId)
            HashMap<Long, JSONArray> totalSaleReturns = new SaleReportsService().getTotalSaleReturnTillDate(date, entityId)
            HashMap<Long, JSONArray> totalSampleSales = new SaleReportsService().getTotalSampleSalesTillDate(date, entityId)
            HashMap<Long, JSONArray> totalGtn = new SaleReportsService().getGTNTillDate(date, entityId)
            JSONObject responseJson = new JSONObject()
            responseJson.put("totalSales", totalSales)
            responseJson.put("totalSaleReturns", totalSaleReturns)
            responseJson.put("totalSampleSales", totalSampleSales)
            responseJson.put("totalGtn", totalGtn)
            respond responseJson
        }
        catch (Exception e) {
            e.printStackTrace()
            response.status = 400
        }
    }

    def getFSN()
    {
        try {
            long entityId = Long.parseLong(params.entityId)
            String fromDateStr = params.fromDate
            String toDateStr = params.toDate
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            Date fromDate = sdf.parse(fromDateStr)
            Date toDate = sdf.parse(toDateStr)
            def session = sessionFactory.getCurrentSession()
            SQLQuery fsnQuery = session.createSQLQuery('call calculateFSN(:entityId, :fromDate, :toDate);')
                    .setParameter("entityId", entityId)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate)
                    .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
            def fsn = fsnQuery.list()
            respond fsn
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
        }
    }

    def productDeleteClearance() {
        try {
            String id = params.id
            JSONObject jsonObject = new JSONObject()
            if (id) {
                long salesCount = SaleProductDetails.countByProductId(Long.parseLong(id))
                long salesOrderCount = SaleOrderProductDetails.countByProductId(Long.parseLong(id))
                long salesReturnCount = SaleReturnDetails.countByProductId(Long.parseLong(id))
                long gtnCount = GoodsTransferNoteProduct.countByProductId(Long.parseLong(id))
                long stockAdjCount = StockAdjustmentDetails.countByProductId(Long.parseLong(id))
                long sampleConversion = SampleConversionDetails.countByProductId(Long.parseLong(id))
                long schemeConfig = SchemeConfiguration.countByProductId(Long.parseLong(id))
                long deliveryChallan = DeliveryChallanProduct.countByProductId(Long.parseLong(id))

                long total = salesCount + salesOrderCount + salesReturnCount + gtnCount + stockAdjCount + sampleConversion + schemeConfig + deliveryChallan
                if (total == 0) {
                    jsonObject.put("delete", true)
                } else {
                    jsonObject.put("delete", false)
                }
            } else {
                jsonObject.put("delete", false)
            }
            respond jsonObject
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

    def batchDeleteClearance() {
        try {
            String id = params.id
            String batchNumber = params.batchNumber
            JSONObject jsonObject = new JSONObject()
            if (id) {
                long salesCount = SaleProductDetails.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long salesOrderCount = SaleOrderProductDetails.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long salesReturnCount = SaleReturnDetails.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long gtnCount = GoodsTransferNoteProduct.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long stockAdjCount = StockAdjustmentDetails.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long sampleConversion = SampleConversionDetails.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long schemeConfig = SchemeConfiguration.countByProductIdAndBatch(Long.parseLong(id),batchNumber)
                long deliveryChallan = DeliveryChallanProduct.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)

                long total = salesCount + salesOrderCount + salesReturnCount + gtnCount + stockAdjCount + sampleConversion + schemeConfig + deliveryChallan
                if (total == 0) {
                    jsonObject.put("delete", true)
                } else {
                    jsonObject.put("delete", false)
                }
            } else {
                jsonObject.put("delete", false)
            }
            respond jsonObject
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

}
