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
        try
        {
        def response = new SystemService().systemServiceStatus()
        JSONObject jsonObject = new JSONObject()
        if (response?.status == 200)
        {
            jsonObject.put("online", "online")
            jsonObject.put("status", "200")
            respond jsonObject, formats: ['json'], status: 200
        }
        else
        {
            jsonObject.put("offline", "offline")
            respond jsonObject, formats: ['json'], status: 200
        }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }
}
