package phitb_sales


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

class ReportsController {
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
            if(sortby.equalsIgnoreCase("invoice-date"))
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
            if(sortby.equalsIgnoreCase("invoice-date"))
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
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if(sortby.equalsIgnoreCase("invoice-date"))
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
                    .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])
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
                    customerJson.put("cityId",cityJson)
                    customerJson.put("salebill",saleBillJson)
                    saleBillJson.put("customer",customerJson)
                    if (customerBills.containsKey(saleBillJson.customer.cityId.id)) {
                        bills = customerBills.get(saleBillJson.customer.cityId.id) as JSONArray
                        bills.add(saleBillJson)
                        customerBills.put(saleBillJson.customer.cityId.id, bills)
                    } else {
                        bills = new JSONArray()
                        bills.add(saleBillJson)
                        customerBills.put(saleBillJson.customer.cityId.id, bills)
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
            if(sortby.equalsIgnoreCase("invoice-date"))
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
                    .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong
                            (entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])

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
                    .path(new Constants().ENTITY_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
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
                    .path(new Constants().CITY_MASTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getSalesGSTReport()
    {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        String daterange = jsonObject.get("dateRange")
        String sortby = jsonObject.get("sortBy")
        String sort = "id"
        if(sortby.equalsIgnoreCase("invoice-date"))
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
        for (SaleBillDetails saleBillDetail  : saleBillDetails) {
            ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
            if(saleProductDetails?.size() > 0) {
                LinkedHashMap gstDetail = new LinkedHashMap()
                gstDetail.put("seriesId", saleBillDetail.seriesId)
                gstDetail.put("customerId", saleBillDetail.customerId)
                gstDetail.put("orderDate", saleBillDetail.orderDate)
                gstDetail.put("invoiceNumber", saleBillDetail.invoiceNumber)
                for (SaleProductDetails saleProductDetail : saleProductDetails) {
                    if(!taxes.contains(saleProductDetail.gstPercentage))
                        taxes.add(saleProductDetail.gstPercentage)
                    double amount = saleProductDetail.amount - saleProductDetail.gstAmount
                    if (gstDetail.containsKey(saleProductDetail.gstPercentage + "_gst_amount")) {
                        gstDetail.put(saleProductDetail.gstPercentage + "_gst_amount", amount + (gstDetail.get(saleProductDetail.gstPercentage + "_gst_amount") as Number))
                    }
                    else {
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


    def getSaleReturnAreaWiseBillDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String entityId = jsonObject.get("entityId")
            String financialYear = jsonObject.get("financialYear")
            String daterange = jsonObject.get("dateRange")
            String sortby = jsonObject.get("sortBy")
            String sort = "id"
            if(sortby.equalsIgnoreCase("invoice-date"))
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

            ArrayList<SaleReturn> saleReturns = SaleReturn.findAllByEntityIdAndFinancialYearAndEntryDateBetweenAndCancelledDateIsNull(Long.parseLong(entityId), financialYear, fromDate, toDate, [sort: sort, order: 'desc'])
            ArrayList<String> custIds = [];
            for (SaleReturn saleReturn : saleReturns) {
                def saleReturnJson = new JSONObject((saleReturn as JSON).toString())
                saleReturnJson.put("docType", "CRNT")
                ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillId(saleReturn.id)
                saleReturnJson.put("products", saleReturnDetails)
                def customerJson = getEntityById(saleReturnJson.customerId.toString())
                def cityJson = getCityById(customerJson.cityId.toString())
                customerJson.put("cityId",cityJson)
                customerJson.put("salebill",saleReturnJson)
                saleReturnJson.put("customer",customerJson)
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
}
