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

    public static String ROLE_SALESMAN = "SALESMAN";
    public static String ROLE_MANAGER = "MANAGER";
    public static String ENTITY_CUSTOMER = "CUSTOMER";
    public static String ENTITY_MANUFACTURER = "MANUFACTURER";
    public static String ENTITY_MANUFACTURER_AND_MARKETING = "MANUFACTURER_AND_MARKETING";
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

    public static String E_INVOICE_ASP_ID = "29AALCP6561H000978";
    public static String E_INVOICE_ASP_SECRET = "v490411B91226v0I1cIWUPLcW6718690";



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

//Invoice Generation Methods
    public static String SINGLE_INV_ENTIRE_ENTITY = "SINGLE_INV_ENTIRE_ENTITY";
    public static String SEPARATE_INV_ENTIRE_ENTITY = "SEPARATE_INV_ENTIRE_ENTITY";
    public static String SEPARATE_INV_EACH_FLOOR = "SEPARATE_INV_EACH_FLOOR";

//Invoice Print Grouping
    public static String DIVISION_WISE = "DIVISION_WISE";
    public static String TAX_WISE = "TAX_WISE";
    public static String PRODUCT_GROUPING = "PRODUCT_GROUPING";
    public static String CATEGORY = "CATEGORY";
    public static String SCHEDULE = "SCHEDULE";
    public static String FLOOR_WISE = "FLOOR_WISE";

//Invoice Print Sorting
    public static String ALPHABETIC = "ALPHABETIC";
    public static String FLOOR_RACK = "PRODUCT_GROUPING";
    public static String RACK_ONLY = "RACK_ONLY";

//    Utilize Local or Universal Barcode
    public static String LOCAL = "LOCAL";
    public static String UNIVERSAL = "UNIVERSAL";

//    Credit Limit Management

    public static  String SINGLE_LIMIT_ENTIRE_ENTITY = "SINGLE_LIMIT_ENTIRE_ENTITY";
    public  static  String INDIVIDUAL_LIMIT_EACH_DIVISION = "INDIVIDUAL_LIMIT_EACH_DIVISION";

//    Round Off the Scheme Quantity?
    public  static  String HIGHER_SCHEME = "HIGHER_SCHEME";
    public  static  String LOWER_SCHEME = "LOWER_SCHEME";

//    How to Apply Scheme?
    public  static  String FOLLOW_SCHEME_CONFIGURATOR = "FOLLOW_SCHEME_CONFIGURATOR";
    public  static  String MANUAL_ENTRY = "MANUAL_ENTRY";

    //    How to Apply Scheme?
    public static  String NEXT_INTEGER_VALUE = "NEXT_INTEGER_VALUE";
    public static  String PREVIOUS_INTEGER_VALUE = "PREVIOUS_INTEGER_VALUE";
    public static  String REMAINS_SAME = "REMAINS_SAME";

    public static String YES = "YES";
    public static String NO = "NO";
}
