package phitb_ui.system

import org.grails.web.json.JSONArray
import phitb_ui.SystemService

class PriorityController {

    def index() { }

   /* def getPriorityByEntity()
    {
        String entityId = params.id
        def apiResponse = new SystemService().getPriorityByEntity(entityId)
        if(apiResponse.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class).toString())
            respond jsonArray, status: 200
        }
        else
        {
            response.status = apiResponse.status
        }
    }*/
}
