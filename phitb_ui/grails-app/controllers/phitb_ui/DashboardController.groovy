package phitb_ui

import org.grails.web.json.JSONObject

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

    def microServiceStatus()
    {
        render(view: 'microservice-status')
    }

    def systemServiceStatus()
    {
        def status = new SystemService().systemServiceStatus()
        respond status
    }
}
