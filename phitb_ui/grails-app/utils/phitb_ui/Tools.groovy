package phitb_ui

import org.grails.web.json.JSONObject
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.HttpSession
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Function
import java.util.function.Predicate

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
        SimpleDateFormat input =  new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy")

        Date d = null;
        try
        {
            String[] dateArr = date.split("T")
            d = input.parse(dateArr[0]);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        return formatted
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return {t -> seen.add(keyExtractor.apply(t))};
    }
}
