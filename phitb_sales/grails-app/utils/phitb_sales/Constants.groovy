package phitb_sales

import org.grails.web.json.JSONObject

import java.nio.file.Files
import java.nio.file.Paths

class Constants
{
    public String API_GATEWAY = "http://172.16.17.143:81/";
    public static String USER_REGISTER_SHOW = "api/v1.0/entity/userregister";
    public static String COUNTRY_MASTER_SHOW = "api/v1.0/system/country";
    public static String CITY_MASTER_SHOW = "api/v1.0/system/city";
    public static String STATE_MASTER_SHOW = "api/v1.0/system/state";
    public static String FORM_MASTER_SHOW = "api/v1.0/system/form";
    public static String ENTITY_REGISTER_SHOW = "api/v1.0/entity/entityregister";
    public static String ENTITY_TYPE_SHOW = "api/v1.0/entity/entitytypemaster";
    public static String PRODUCT_REGISTER_SHOW = "api/v1.0/product/productregister";


    public Constants()
    {
        String path = System.getProperty("user.home") + File.separator + ".phitb" + File.separator +
                "api_gateway";
        File folder = new File(path);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        try
        {
            String configString = new String(Files.readAllBytes(Paths.get(path + File.separator + "config.json")));
            JSONObject config = new JSONObject(configString);
            API_GATEWAY = config.get("API_GATEWAY").toString();
        }
        catch (Exception e)
        {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
