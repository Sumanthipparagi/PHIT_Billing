package phitb_ui.reports

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.ReportsService
import phitb_ui.SystemService

class AccountsReportController {

    ReportsService reportsService
    def outstandingReport()
    {
        render(view: '/reports/accountsReport/outstanding')
    }

    def getOutstandingReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        boolean paidInvoice = Boolean.parseBoolean(params.paidInvoice)
        double balance = 0;
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject resultJson = new JSONObject()
        //Sale Bill Details and Sale Return or similar, so adding to same JSONObject
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy, paidInvoice)
        JSONObject saleReturnAreaWise = reportsService.getSaleReturnAreaWiseReport(entityId, dateRange, financialYear, sortBy,paidInvoice)
        for (Object key : saleReturnAreaWise.keySet()) {
            if(areaWiseData.containsKey(key))
            {
                JSONArray invoices = (JSONArray) areaWiseData.get(key)
                invoices.addAll(saleReturnAreaWise.get(key))
                areaWiseData.put(key, invoices)
            }
            else
            {
                areaWiseData.put(key, saleReturnAreaWise.get(key))
            }
        }
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = (JSONArray) areaWiseData.get(key)
            //get outstanding details
            def cityDetail = new SystemService().getCityById(key.toString())
            JSONArray outstandingReport = reportsService.getOutstandingReport(areaWiseBills)
            //group by customer
            HashMap<Long, JSONArray> customers = new HashMap<>()
            HashMap<String, JSONArray> customers2 = new HashMap<>()
            for (JSONObject otr : outstandingReport) {
                JSONArray customerOutstanding = new JSONArray()
                long entityId1 = Long.parseLong(otr.get("entityId").toString())
                if(customers.containsKey(entityId1))
                {
                    customerOutstanding = customers.get(entityId1)
                    customerOutstanding.add(otr)
                }
                else
                {
                    customerOutstanding.add(otr)
                }
                customers.put(entityId1, customerOutstanding)
            }

            for (Long e : customers.keySet()) {
                JSONObject entity = new EntityService().getEntityById(e.toString())
                customers2.put(entity.get("entityName").toString(), customers.get(e))
            }
            //get city name
            if(outstandingReport?.size() > 0)
                resultJson.put(cityDetail.district.district.toString(), customers2)
        }
        respond resultJson, formats: ['json']
    }

}

class OutstandingReport{
    String transactionType
    String transactionNumber
    String transactionDate
    String lrDate
    String dueDate
    double dueOn
    double notDue
    double totalDue
    double days
}
