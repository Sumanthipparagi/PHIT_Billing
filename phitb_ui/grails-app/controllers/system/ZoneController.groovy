package system

import org.grails.web.json.JSONArray
import phitb_ui.SystemService

class ZoneController {

    def index()
    {}

    def show()
    {
        def apiResponse = new SystemService().getZoneList()
        if (apiResponse?.status == 200)
        {
            JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
            ArrayList<String> arrayList = new ArrayList<>(jsonArray)
            return arrayList
        }
        else
        {
            return []
        }
    }
}
