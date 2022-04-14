package phitb_ui.reports

import phitb_ui.ReportsService

class SalesReportController {

    ReportsService reportsService
    def index() {
        render(view: '/reports/salesReport/index')
    }

    def salesCustomerWiseReport()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        respond reportsService.getCustomerWiseReport(entityId, dateRange, financialYear), formats: ['json']
    }
}
