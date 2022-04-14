package phitb_ui.reports

import phitb_ui.ReportsService

class SalesReportController {

    ReportsService reportsService
    def index() { }

    def salesCustomerWiseReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String daterange = params.daterange
        respond reportsService.getCustomerWiseReport(entityId, daterange, financialYear)
    }
}
