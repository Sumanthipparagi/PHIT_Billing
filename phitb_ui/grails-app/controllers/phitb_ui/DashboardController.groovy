package phitb_ui

class DashboardController {

    def index() {
        render(view: 'index')
    }

    def forms()
    {
        render(view: 'forms')
    }

    def table()
    {
        render(view:'datatable')
    }

    def timeline()
    {
        render(view: 'timeline')
    }
}
