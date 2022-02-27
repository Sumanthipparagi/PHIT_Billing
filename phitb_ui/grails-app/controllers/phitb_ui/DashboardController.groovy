package phitb_ui

import org.grails.web.json.JSONObject

class DashboardController {

    def index() {
        JSONObject userJson = new EntityService().getUser("1")
        session.setMaxInactiveInterval(3600)
        session.setAttribute("entityId", userJson.get("entity")["id"])
        session.setAttribute("entityName", userJson.get("entity")["entityName"])
        session.setAttribute("userName", userJson.get("userName"))
        session.setAttribute("entityAddress1", userJson.get("entity")["addressLine1"])
        session.setAttribute("entityAddress2", userJson.get("entity")["addressLine2"])
        session.setAttribute("entityPinCode", userJson.get("entity")["pinCode"])
        session.setAttribute("entityMobileNumber", userJson.get("entity")["mobileNumber"])
        render(view: 'index', model: [user:userJson])
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
