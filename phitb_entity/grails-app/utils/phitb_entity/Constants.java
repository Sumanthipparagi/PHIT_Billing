package phitb_entity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.grails.web.json.JSONObject;

public class Constants
{

    public static String API_GATEWAY = "http://172.16.17.143:81/";
    public static String USER_REGISTER_SHOW = "api/v1.0/entity/userregister";
    public static String COUNTRY_MASTER_SHOW = "api/v1.0/system/country";
    public static String FORM_MASTER_SHOW = "api/v1.0/system/form";


     Constants()
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
