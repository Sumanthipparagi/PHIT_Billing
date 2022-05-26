package phitb_accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

class ReportsController {
	static responseFormats = ['json', 'xml']
	
    def outstandingReport()
    {
        JSONArray jsonArray = new JSONArray(request.reader.text)
        ArrayList<OutstandingReport> outstandingReports = new ArrayList<>()
        for (JSONObject jsonObject : jsonArray) {
            if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE")) {
                long entityId = Long.parseLong(jsonObject.get("entityId").toString())
                String financialYear = jsonObject.get("financialYear")
                String transId = jsonObject.get("invoiceNumber").toString()
                String billType = "INVS"
                double due = 0.0
                ArrayList<BillDetailLog> billDetailLogs = BillDetailLog.findAllByEntityIdAndFinancialYearAndBillTypeAndBillId(entityId, financialYear, billType, Long.parseLong(jsonObject.get("id").toString()))
                for (BillDetailLog billDetailLog : billDetailLogs) {
                    due += billDetailLog.amountPaid
                }
                OutstandingReport outstandingReport = new OutstandingReport()
                outstandingReport.transactionType = billType
                outstandingReport.transactionNumber = transId
                outstandingReport.transactionDate = jsonObject.get("orderDate").toString()
                outstandingReport.lrDate = null
                outstandingReport.dueDate = null
                outstandingReport.due = due
                outstandingReport.dueDate = jsonObject.get("dueDate").toString()
                outstandingReport.balance = Double.parseDouble(jsonObject.get("balance").toString())
                outstandingReports.add(outstandingReport)
            }
        }

        respond outstandingReports
    }
}

class OutstandingReport{
    String transactionType
    String transactionNumber
    String transactionDate
    String lrDate
    String dueDate
    double due
    double balance
    double totalDue
    double days
}
