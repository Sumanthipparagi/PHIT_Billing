package phitb_ui;

import org.grails.web.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Constants
{
    public static String APPLICATION_FOLDER = "phitb";
    public static String APP_SHORT_NAME = "PHITB";
//    public String API_GATEWAY = "http://172.16.17.143:81/";
    public String API_GATEWAY = "http://localhost/";

    public static  String ROLE_SALESMAN = "SALESMAN";
    public static  String ROLE_MANAGER = "MANAGER";
    public static  String ENTITY_CUSTOMER = "CUSTOMER";



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
