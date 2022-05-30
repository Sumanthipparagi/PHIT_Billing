package phitb_accounts

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

class ReportsController {
	static responseFormats = ['json', 'xml']
	
    def outstandingReport()
    {
        JSONArray jsonArray = new JSONArray(request.reader.text)
        ArrayList<MyOutstandingReport> outstandingReports = new ArrayList<>()
        for (JSONObject jsonObject : jsonArray) {
            String docType = jsonObject.get("docType").toString()
            String status = null
            if(jsonObject.has("returnStatus"))
                status = jsonObject.get("returnStatus").toString()
            else
                status = jsonObject.get("billStatus").toString()

            if(status.equalsIgnoreCase("ACTIVE")) {
                long entityId = Long.parseLong(jsonObject.get("entityId").toString())
                String financialYear = jsonObject.get("financialYear")
                String transId = jsonObject.get("invoiceNumber").toString()
                String billType = jsonObject.get("docType").toString()
                double due = 0.0
                String dueDate = ""
                String entryDate = ""
                if(docType.equalsIgnoreCase("INVS")) {
                    dueDate = jsonObject.get("dueDate").toString()
                    entryDate = jsonObject.get("orderDate").toString()
                    ArrayList<BillDetailLog> billDetailLogs = BillDetailLog.findAllByEntityIdAndFinancialYearAndBillTypeAndBillId(entityId, financialYear, billType, Long.parseLong(jsonObject.get("id").toString()))
                    for (BillDetailLog billDetailLog : billDetailLogs) {
                        due += billDetailLog.amountPaid
                    }
                }
                else if(docType.equalsIgnoreCase("CRNT"))
                {
                    dueDate = jsonObject.get("entryDate").toString()
                    entryDate = jsonObject.get("entryDate").toString()
                    due = -Double.parseDouble(jsonObject.get("totalAmount").toString()) //this should be negative number as this is sale return
                }
                MyOutstandingReport outstandingReport = new MyOutstandingReport()
                outstandingReport.transactionType = billType
                outstandingReport.transactionNumber = transId
                outstandingReport.transactionDate = entryDate
                outstandingReport.lrDate = null
                outstandingReport.dueDate = null
                outstandingReport.due = due
                outstandingReport.dueDate = dueDate
                outstandingReport.financialYear = jsonObject.get("financialYear").toString()
                outstandingReport.balance = Double.parseDouble(jsonObject.get("balance").toString())
                outstandingReport.entityId = Long.parseLong(jsonObject.get("customerId").toString())
                outstandingReports.add(outstandingReport)
            }
        }

        respond outstandingReports
    }

    //TODO: to be renamed
/*    def outstandingReport1()
    {

    }*/

    class MyOutstandingReport{
        long entityId
        String transactionType
        String transactionNumber
        String transactionDate
        String lrDate
        String dueDate
        double due
        double balance
        double totalDue
        double days
        String financialYear
    }
}

