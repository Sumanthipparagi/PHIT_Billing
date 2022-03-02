package phitb_ui

import org.grails.web.json.JSONObject
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.HttpSession

public class Tools {

    public static JSONObject setCreatedUser(JSONObject jsonObject)
    {
        if(jsonObject)
        {
            jsonObject.put("createdUser", session.getAttribute("userId"))
        }
        return jsonObject
    }

    public static JSONObject setModifiedUser(JSONObject jsonObject)
    {
        if(jsonObject)
        {
            jsonObject.put("modifiedUser", session.getAttribute("userId"))
        }
        return jsonObject
    }

    private static HttpSession getSession() {
        return RequestContextHolder.currentRequestAttributes().getSession()
    }
}
