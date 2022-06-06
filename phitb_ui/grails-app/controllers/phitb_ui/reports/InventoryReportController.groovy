package phitb_ui.reports

class InventoryReportController {

    def index() { }

    def statement()
    {
        render(view: 'reports/inventory/reports/statement')
    }
}
