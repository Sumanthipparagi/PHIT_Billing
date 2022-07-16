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
    public static String ENTITY_C_F = "C_F";



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

//    Sale Type
    public static String SALEABLE = "SALEABLE";
    public static String SAMPLE = "SAMPLE";
    public static String PROMOTIONAL  = "PROMOTIONAL";


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
    public static String SEPARATE_INV_EACH_DIVISION = "SEPARATE_INV_EACH_DIVISION";
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


//    Entity Configs
    public static String DE = "DE";
    public static String SEND_MAIL = "SEND_MAIL";
    public static String REGEN_NEW_DOC = "REGEN_NEW_DOC";
    public static String ALLOW_MANUAL = "ALLOW_MANUAL";
    public static String CDE = "CDE";
    public static String DAD = "DAD";
    public static String DISCOUNT_BUTTON = "DISCOUNT_BUTTON";
    public static String RE_CAL = "RE_CAL";
    public static String PRESCRIPTION_UPLOAD = "PRESCRIPTION_UPLOAD";
    public static String BLOCK = "BLOCK";
    public static String DISABLE_SCHEME_QTY = "DISABLE_SCHEME_QTY";
    public static String MAD = "MAD";
    public static String AUTO = "AUTO";
    public static String NEW_BATCH_SELECTION = "NEW_BATCH_SELECTION";
    public static String ENABLE_CASH_DISC = "ENABLE_CASH_DISC";
    public static String BLOCK_INV = "BLOCK_INV";
    public static String WITHOUT_QR = "WITHOUT_QR";
    public static String UPLOAD_PROOF = "UPLOAD_PROOF";



    //    Form Types

    public static String SALE_ORDER = "SALE_ORDER";
    public static String SALE_INVOICE = "SALE_INVOICE";
    public static String SALE_RETURN = "SALE_RETURN";

    public static String PURCHASE_ORDER = "PURCHASE_ORDER";
    public static String PURCHASE_INVOICE = "PURCHASE_INVOICE";
    public static String PURCHASE_RETURN = "PURCHASE_RETURN";

    public static String GRN = "GRN";



//    Days of week

    public static String MONDAY = "MONDAY";
    public static String TUESDAY = "TUESDAY";
    public static String WEDNESDAY = "WEDNESDAY";
    public static String THURSDAY = "THURSDAY";
    public static String FRIDAY = "FRIDAY";
    public static String SATURDAY = "SATURDAY";
    public static String SUNDAY = "SUNDAY";


    public static String SUPER_USER = "SUPER_USER";
    public static String ENTITY_ADMIN = "ENTITY_ADMIN";
    public static String MANAGER = "MANAGER";
    public static String SALESMAN = "SALESMAN";


}
