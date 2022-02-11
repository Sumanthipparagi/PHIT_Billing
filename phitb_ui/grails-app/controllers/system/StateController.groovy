package system

import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_ui.SystemService

class StateController {

    def index()
    {
        def url = "http://localhost/api/v1.0/entity/entityregister"
        URL apiUrl = new URL(url)
        def entity = new JsonSlurper().parseText(apiUrl.text)
        render(view: '/system/state/state',model: [entity:entity])
    }

    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SystemService().showState(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
