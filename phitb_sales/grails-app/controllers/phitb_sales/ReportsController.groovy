package phitb_sales


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(daterange.split("-")[0].trim())
            Date toDate = sdf.parse(daterange.split("-")[1].trim())
            JSONObject customerBills = new JSONObject()
            JSONArray bills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatus(Long.parseLong(entityId), financialYear, fromDate, toDate, "ACTIVE")
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
}
