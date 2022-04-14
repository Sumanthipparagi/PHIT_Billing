package phitb_sales


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat

class ReportsController {
	static responseFormats = ['json', 'xml']
	
    def getCustomerWiseBillDetails() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        String daterange = jsonObject.get("dateRange")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
        Date fromDate = sdf.parse(daterange.split("-")[0].trim())
        Date toDate = sdf.parse(daterange.split("-")[1].trim())
        JSONObject customerBills = new JSONObject()
        JSONArray bills = new JSONArray()
        ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndFinancialYearAndOrderDateBetweenAndBillStatus(Long.parseLong(entityId),financialYear, fromDate, toDate, "ACTIVE")
        for (SaleBillDetails saleBillDetail : saleBillDetails) {
            if(customerBills.containsKey(saleBillDetail.customerId))
            {
                bills = customerBills.get(saleBillDetail.customerId)
                bills.add(saleBillDetail)
                customerBills.put(saleBillDetail.customerId, bills)
            }
            else
            {
                bills = new JSONArray()
                bills.add(saleBillDetail)
                customerBills.put(saleBillDetail.customerId, bills)
            }
        }

        respond customerBills
    }
}
