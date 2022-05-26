package phitb_ui.reports

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
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
        //String sortBy = params.sortBy
        String sortBy = "id"
        JSONObject resultJson = new JSONObject()
        JSONObject areaWiseData = reportsService.getAreaWiseReport(entityId, dateRange, financialYear, sortBy)
        for (Object key : areaWiseData.keySet()) {
            JSONArray areaWiseBills = (JSONArray) areaWiseData.get(key)
            //get outstanding details
            def cityDetail = new SystemService().getCityById(key.toString())
            JSONArray outstandingReport = reportsService.getOutstandingReport(areaWiseBills)
            if(outstandingReport?.size() > 0)
                resultJson.put(cityDetail.name.toString(), outstandingReport)
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
