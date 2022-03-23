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
    //public String API_GATEWAY = "http://172.16.17.143:81/";
    public String API_GATEWAY = "http://localhost/";

    public static String ROLE_SALESMAN = "SALESMAN";
    public static String ROLE_MANAGER = "MANAGER";
    public static String ENTITY_CUSTOMER = "CUSTOMER";
    public static String ENTITY_MANUFACTURER = "MANUFACTURER";
    public static String ENTITY_COMPANY = "COMPANY";
    public static String ENTITY_DISTRIBUTOR = "DISTRIBUTOR";

    public static String DOCTYPE_SALE_INVOICE = "SLINV";
    public static String DOCTYPE_PURCHASE_INVOICE = "PRINV";
    public static String DOCTYPE_SALE_RETURN = "SR";
    public static String DOCTYPE_PURCHASE_RETURN = "PR";
    public static String DOCTYPE_PURCHASE_ORDER = "PO";
    public static String DOCTYPE_SALE_ORDER = "SO";
    public static String DOCTYPE_CREDITJV = "CRJV";
    public static String DOCTYPE_DEBITJV = "DBJV";



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

    public static String FEATURE_ROLE = "ROLE";
    public static String FEATURE_SALE_ORDER = "SALE_ORDER";
    public static String FEATURE_SALE_ENTRY = "SALE_ENTRY";
}
