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
            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()

            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails
                    .findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatusNotEqual(Long.parseLong(entityId), financialYear, fromDate, toDate, "DRAFT", [sort: sort, order: 'desc'])
            ArrayList<String> custIds = [];
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                def saleBillJson = new JSONObject((saleBillDetail as JSON).toString())
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                saleBillJson.put("products", saleProductDetails)
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

}
