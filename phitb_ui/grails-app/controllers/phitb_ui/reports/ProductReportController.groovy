package phitb_ui.reports

import phitb_ui.ReportsService

class ProductReportController {

    ReportsService reportsService
    def productStatement() {
        render(view: '/reports/productReport/productStatement')
    }

    def getProductStatement()
    {
        String entityId = session.getAttribute("entityId")
        String financialYear = session.getAttribute("financialYear")
        String dateRange = params.dateRange
        String sortBy = "id"
    }
}
