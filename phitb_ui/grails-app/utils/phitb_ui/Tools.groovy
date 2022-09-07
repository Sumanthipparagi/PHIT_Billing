package phitb_ui

import org.grails.web.json.JSONObject
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.HttpSession
import java.text.ParseException
import java.text.SimpleDateFormat

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

    public static String dateStringToDate(String date){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");

        Date d = null;
        try
        {
            d = input.parse("2018-02-02T06:54:57.744Z");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        return formatted
    }
}
