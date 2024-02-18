package phitb_ui;

public class Links
{
    public static String API_GATEWAY = new Constants().API_GATEWAY;
    public static String APP_CONTEXT_PATH = "api/v1.0/";

    /*<-------------------------------------------System------------------------------------------------->*/

    //    Account modes
    public static String ACCOUNT_MODES_SHOW = APP_CONTEXT_PATH + "system/accountmodes";
    public static String ACCOUNT_MODES_SAVE = APP_CONTEXT_PATH + "system/accountmodes";
    public static String ACCOUNT_MODES_DATATABLE = APP_CONTEXT_PATH + "system/accountmodes/datatable";
    public static String ACCOUNT_MODES_UPDATE = APP_CONTEXT_PATH + "system/accountmodes/{id}";
    public static String ACCOUNT_MODES_DELETE = APP_CONTEXT_PATH + "system/accountmodes/{id}";
    public static String ACCOUNT_MODES_BY_ENTITY = APP_CONTEXT_PATH + "system/accountmodesbyenity";

    //    Status
    public static String SYSTEM_SERVICE_STATUS = APP_CONTEXT_PATH + "system/status";
    public static String SHIPMENT_SERVICE_STATUS = APP_CONTEXT_PATH + "shipments/status";
    public static String SALES_SERVICE_STATUS = APP_CONTEXT_PATH + "sales/status";
    public static String PURCHASE_SERVICE_STATUS = APP_CONTEXT_PATH + "purchase/status";
    public static String PRODUCT_SERVICE_STATUS = APP_CONTEXT_PATH + "product/status";
    public static String INVENTORY_SERVICE_STATUS = APP_CONTEXT_PATH + "inventory/status";
    public static String FACILITY_SERVICE_STATUS = APP_CONTEXT_PATH + "facility/status";
    public static String ENTITY_SERVICE_STATUS = APP_CONTEXT_PATH + "entity/status";
    public static String ACCOUNTS_SERVICE_STATUS = APP_CONTEXT_PATH + "accounts/status";

    //    Gender Master
    public static String GENDER_MASTER_SHOW = APP_CONTEXT_PATH + "system/gender";


    //    Payment Mode show
    public static String PAYMENT_MODE_SHOW = APP_CONTEXT_PATH + "system/paymentmode";


    //    Terms Condtions
    public static String TERMS_CONDITIONS = APP_CONTEXT_PATH + "entity/termconditiondetailsbyentity";


    //    State Master
    public static String STATE_MASTER_SAVE = APP_CONTEXT_PATH + "system/state";
    public static String STATE_MASTER_SHOW = APP_CONTEXT_PATH + "system/state";
    public static String STATE_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/state/datatable";
    public static String STATE_MASTER_UPDATE = APP_CONTEXT_PATH + "system/state/{id}";
    public static String STATE_MASTER_DELETE = APP_CONTEXT_PATH + "system/state/{id}";

    //Zone Master
    public static String ZONE_MASTER_SHOW = APP_CONTEXT_PATH + "system/zone";
    public static String ZONE_MASTER_BY_ENTITY = APP_CONTEXT_PATH + "system/zonebyentity";
    public static String ZONE_MASTER_SAVE = APP_CONTEXT_PATH + "system/zone";
    public static String ZONE_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/zone/datatable";
    public static String ZONE_MASTER_UPDATE = APP_CONTEXT_PATH + "system/zone/{id}";
    public static String ZONE_MASTER_DELETE = APP_CONTEXT_PATH + "system/zone/{id}";


    //Country Master
    public static String COUNTRY_MASTER_SHOW = APP_CONTEXT_PATH + "system/country";
    public static String COUNTRY_MASTER_SAVE = APP_CONTEXT_PATH + "system/country";
    public static String COUNTRY_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/country/datatable";
    public static String COUNTRY_MASTER_UPDATE = APP_CONTEXT_PATH + "system/country/{id}";
    public static String COUNTRY_MASTER_DELETE = APP_CONTEXT_PATH + "system/country/{id}";


    //Form Master
    public static String FORM_MASTER_SHOW = APP_CONTEXT_PATH + "system/form";
    public static String FORM_MASTER_GET_BY_ENTITY = APP_CONTEXT_PATH + "system/formbyentity";
    public static String FORM_MASTER_SAVE = APP_CONTEXT_PATH + "system/form";
    public static String FORM_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/form/datatable";
    public static String FORM_MASTER_UPDATE = APP_CONTEXT_PATH + "system/form/{id}";
    public static String FORM_MASTER_DELETE = APP_CONTEXT_PATH + "system/form/{id}";


    //    City Master
    public static String CITY_MASTER_SAVE = APP_CONTEXT_PATH + "system/city";
    public static String CITY_MASTER_SHOW = APP_CONTEXT_PATH + "system/city";
    public static String CITY_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/city/datatable";
    public static String CITY_MASTER_UPDATE = APP_CONTEXT_PATH + "system/city/{id}";
    public static String CITY_MASTER_DELETE = APP_CONTEXT_PATH + "system/city/{id}";
    public static String GET_CITY_BY_PINCODE = APP_CONTEXT_PATH + "system/getcitybypincode";
    public static String GET_CITY_BY_IDS = APP_CONTEXT_PATH + "system/citesbyids";


    //    Priorty Master
    public static String PRIORITY_SAVE = APP_CONTEXT_PATH + "system/priority";
    public static String PRIORITY_SHOW = APP_CONTEXT_PATH + "system/priority";
    public static String PRIORITY_DATATABLE = APP_CONTEXT_PATH + "system/priority/datatable";
    public static String PRIORITY_UPDATE = APP_CONTEXT_PATH + "system/priority/{id}";
    public static String PRIORITY_DELETE = APP_CONTEXT_PATH + "system/priority/{id}";
    public static String PRIORITY_BY_ENTITY = APP_CONTEXT_PATH + "system/prioritybyentity";


    //    Region Master
    public static String REGION_SAVE = APP_CONTEXT_PATH + "system/region";
    public static String REGION_SHOW = APP_CONTEXT_PATH + "system/region";
    public static String REGION_DATATABLE = APP_CONTEXT_PATH + "system/region/datatable";
    public static String REGION_UPDATE = APP_CONTEXT_PATH + "system/region/{id}";
    public static String REGION_DELETE = APP_CONTEXT_PATH + "system/region/{id}";


    //    Division Master
    public static String DIVISION_MASTER_SAVE = APP_CONTEXT_PATH + "system/division";
    public static String DIVISION_MASTER_SHOW = APP_CONTEXT_PATH + "system/division";
    public static String DIVISION_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/division/datatable";
    public static String DIVISION_MASTER_UPDATE = APP_CONTEXT_PATH + "system/division/{id}";
    public static String DIVISION_MASTER_DELETE = APP_CONTEXT_PATH + "system/division/{id}";


    //    District Master
    public static String DISTRICT_MASTER_SAVE = APP_CONTEXT_PATH + "system/district";
    public static String DISTRICT_MASTER_SHOW = APP_CONTEXT_PATH + "system/district";
    public static String DISTRICT_MASTER_DATATABLE = APP_CONTEXT_PATH + "system/district/datatable";
    public static String DISTRICT_MASTER_UPDATE = APP_CONTEXT_PATH + "system/district/{id}";
    public static String DISTRICT_MASTER_DELETE = APP_CONTEXT_PATH + "system/district/{id}";


    //    Reason Master
    public static String REASON_UPDATE = APP_CONTEXT_PATH + "system/reasonmaster/{id}";
    public static String REASON_DELETE = APP_CONTEXT_PATH + "system/reasonmaster/{id}";
    public static String REASON_SAVE = APP_CONTEXT_PATH + "system/reasonmaster";
    public static String REASON_SHOW = APP_CONTEXT_PATH + "system/reasonmaster";
    public static String REASON_DATATABLE = APP_CONTEXT_PATH + "system/reasonmaster/datatable";


    /*<-------------------------------------------Facility ------------------------------------------------->*/


    //Fridge
    public static String FRIDGE_SHOW = APP_CONTEXT_PATH + "facility/fridge";
    public static String FRIDGE_SAVE = APP_CONTEXT_PATH + "facility/fridge";
    public static String FRIDGE_DATATABLE = APP_CONTEXT_PATH + "facility/fridge/datatable";
    public static String FRIDGE_UPDATE = APP_CONTEXT_PATH + "facility/fridge/{id}";
    public static String FRIDGE_DELETE = APP_CONTEXT_PATH + "facility/fridge/{id}";

    //CCM
    public static String CCM_SHOW = APP_CONTEXT_PATH + "facility/ccmregister";
    public static String CCM_SAVE = APP_CONTEXT_PATH + "facility/ccmregister";
    public static String CCM_DATATABLE = APP_CONTEXT_PATH + "facility/ccmregister/datatable";
    public static String CCM_UPDATE = APP_CONTEXT_PATH + "facility/ccmregister/{id}";
    public static String CCM_DELETE = APP_CONTEXT_PATH + "facility/ccmregister/{id}";

    //godown
    public static String GODOWN_SHOW = APP_CONTEXT_PATH + "facility/godown";
    public static String GODOWN_SAVE = APP_CONTEXT_PATH + "facility/godown";
    public static String GODOWN_DATATABLE = APP_CONTEXT_PATH + "facility/godown/datatable";
    public static String GODOWN_UPDATE = APP_CONTEXT_PATH + "facility/godown/{id}";
    public static String GODOWN_DELETE = APP_CONTEXT_PATH + "facility/godown/{id}";

    //rack
    public static String RACK_SHOW = APP_CONTEXT_PATH + "facility/rack";
    public static String RACK_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "facility/rackbyentity";
    public static String RACK_SAVE = APP_CONTEXT_PATH + "facility/rack";
    public static String RACK_DATATABLE = APP_CONTEXT_PATH + "facility/rack/datatable";
    public static String RACK_UPDATE = APP_CONTEXT_PATH + "facility/rack/{id}";
    public static String RACK_DELETE = APP_CONTEXT_PATH + "facility/rack/{id}";

    /*<-------------------------------------------Entity ------------------------------------------------->*/

    //Entity IRN
    public static String ENTITY_IRN_SHOW = APP_CONTEXT_PATH + "entity/entityirn";
    public static String ENTITY_IRN_SAVE = APP_CONTEXT_PATH + "entity/entityirn";
    public static String ENTITY_IRN_DATATABLE = APP_CONTEXT_PATH + "entity/entityirn/datatable";
    public static String ENTITY_IRN_UPDATE = APP_CONTEXT_PATH + "entity/entityirn/{id}";
    public static String ENTITY_IRN_DELETE = APP_CONTEXT_PATH + "entity/entityirn/{id}";
    public static String ENTITY_IRN_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity//entityirn/entity/";

    //Entity ROUTE
    public static String ENTITY_ROUTE_SHOW = APP_CONTEXT_PATH + "entity/entityroute";
    public static String ENTITY_ROUTE_SAVE = APP_CONTEXT_PATH + "entity/entityroute";
    public static String ENTITY_ROUTE_DATATABLE = APP_CONTEXT_PATH + "entity/entityroute/datatable";
    public static String ENTITY_ROUTE_UPDATE = APP_CONTEXT_PATH + "entity/entityroute/{id}";
    public static String ENTITY_ROUTE_DELETE = APP_CONTEXT_PATH + "entity/entityroute/{id}";
    public static String ENTITY_ROUTE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity/entityroute/entity/";

    public static String ENTITY_AUTH = APP_CONTEXT_PATH + "entity/authregister/username";

    public static String ENTITY_TYPE_MASTER_SHOW = APP_CONTEXT_PATH + "entity/entitytypemaster";
    public static String DEPARTMENT_MASTER_SHOW = APP_CONTEXT_PATH + "entity/departmentmaster";
    public static String DEPARTMENT_MASTER_DELETE = APP_CONTEXT_PATH + "entity/departmentmaster";
    public static String DEPARTMENT_MASTER_ENTITY = APP_CONTEXT_PATH + "entity/departmentmasterbyentity";
    public static String DEPARTMENT_DATATABLE = APP_CONTEXT_PATH + "entity/departmentmaster/datatable";
    public static String DIVISION_SHOW = APP_CONTEXT_PATH + "product/division";
    public static String DIVISION_BY_ENTITY = APP_CONTEXT_PATH + "product/divisionbyentity";
    public static String GENDER_SHOW = APP_CONTEXT_PATH + "system/gender";

    //Entity Register
    public static String ENTITY_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/entityregister";
    public static String ENTITY_REGISTER_GET_BY_USER_ROUTE = APP_CONTEXT_PATH + "entity/entityregister/getbyuserroute";
    public static String ENTITY_REGISTER_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity/entityregister/getbyentity";
    public static String ENTITY_REGISTER_SHOW_BY_ENTITY_PAGINATED = APP_CONTEXT_PATH + "entity/entityregister/getbyentity-paginated";
    public static String ENTITY_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/entityregister";
    public static String ENTITY_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/entityregister/datatable";
    public static String ENTITY_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/entityregister/{id}";
    public static String ENTITY_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/entityregister/{id}";
    public static String ENTITY_REGISTER_AFFILIATE = APP_CONTEXT_PATH + "entity/entityregister/affiliate";
    public static String ENTITY_REGISTER_PARENT = APP_CONTEXT_PATH + "entity/entityregister/getparententities";
    public static String ENTITY_REGISTER_PARENT_DATATABLE = APP_CONTEXT_PATH + "entity/entityregister/parententity/datatable";
    public static String ENTITY_REGISTER_GET_CITY_IDS = APP_CONTEXT_PATH + "entity/getCityIds";


    //Entity Settings
    public static String ENTITY_SETTINGS_SHOW = APP_CONTEXT_PATH + "entity/entitysetting";
    public static String ENTITY_SETTINGS_UPDATE = APP_CONTEXT_PATH + "entity/entitysetting/{id}";
    public static String ENTITY_SETTINGS_DELETE = APP_CONTEXT_PATH + "entity/entitysetting/{id}";
    public static String ENTITY_SETTINGS_SAVE = APP_CONTEXT_PATH + "entity/entitysetting";
    public static String ENTITY_SETTINGS_DATATABLE = APP_CONTEXT_PATH + "entity/entitysetting/datatable";
    public static String ENTITY_SETTINGS_BY_ENTITY = APP_CONTEXT_PATH + "entity/entitysetting/getbyentityid";

    //Email Settings
    public static String EMAIL_SETTINGS_SHOW = APP_CONTEXT_PATH + "entity/emailsetting";
    public static String EMAIL_SETTINGS_UPDATE = APP_CONTEXT_PATH + "entity/emailsetting/{id}";
    public static String EMAIL_SETTINGS_DELETE = APP_CONTEXT_PATH + "entity/emailsetting/{id}";
    public static String EMAIL_SETTINGS_SAVE = APP_CONTEXT_PATH + "entity/emailsetting";
    public static String EMAIL_SETTINGS_DATATABLE = APP_CONTEXT_PATH + "entity/emailsetting/datatable";
    public static String EMAIL_SETTINGS_BY_ENTITY = APP_CONTEXT_PATH + "entity/emailsettingbyentity";

    //Email Log
    public static String EMAIL_LOG_SHOW = APP_CONTEXT_PATH + "entity/emaillog";
    public static String EMAIL_LOG_UPDATE = APP_CONTEXT_PATH + "entity/emaillog/{id}";
    public static String EMAIL_LOG_DELETE = APP_CONTEXT_PATH + "entity/emaillog/{id}";
    public static String EMAIL_LOG_SAVE = APP_CONTEXT_PATH + "entity/emaillog";
    public static String EMAIL_LOG_DATATABLE = APP_CONTEXT_PATH + "entity/emaillog/datatable";
    public static String EMAIL_LOG_BY_ENTITY = APP_CONTEXT_PATH + "entity/emaillog/getbyentityid";


    //Email Config
    public static String SAVE_EMAIL_CONFIG = APP_CONTEXT_PATH + "entity/saveemailconfig";

    //Entity Config
    public static String ENTITY_CONFIG_SAVE = APP_CONTEXT_PATH + "entity/entityconfig";
    public static String ENTITY_CONFIG_BY_ENTITY = APP_CONTEXT_PATH + "entity/entityconfig/getbyentityid";



    //SMS Log
    public static String SMS_LOG_SHOW = APP_CONTEXT_PATH + "entity/smslog";
    public static String SMS_LOG_UPDATE = APP_CONTEXT_PATH + "entity/smslog/{id}";
    public static String SMS_LOG_DELETE = APP_CONTEXT_PATH + "entity/smslog/{id}";
    public static String SMS_LOG_SAVE = APP_CONTEXT_PATH + "entity/smslog";
    public static String SMS_LOG_DATATABLE = APP_CONTEXT_PATH + "entity/smslog/datatable";
    public static String SMS_LOG_BY_ENTITY = APP_CONTEXT_PATH + "entity/smslogbyentity";
    public static String SMS_TEMPLATE = APP_CONTEXT_PATH + "entity/smslog/template";

    //Account Register
    public static String ACCOUNT_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/accountregister";
    public static String ACCOUNT_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/accountregister";
    public static String ACCOUNT_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/accountregister/datatable";
    public static String ACCOUNT_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/accountregister/{id}";
    public static String ACCOUNT_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/accountregister/{id}";
    public static String ACCOUNT_REGISTER_AFFILIATE = APP_CONTEXT_PATH + "entity/accountregister/affiliate";
    public static String ACCOUNT_REGISTER_BY_ENTITY = APP_CONTEXT_PATH + "entity/accountregister/entity";
    public static String ACCOUNT_REGISTER_UPDATE_BALANCE = APP_CONTEXT_PATH + "entity/accountregister/updatebalance";

    //Account Type
    public static String ACCOUNT_TYPE_SAVE = APP_CONTEXT_PATH + "system/accounttype";
    public static String ACCOUNT_TYPE_SHOW = APP_CONTEXT_PATH + "system/accounttype";
    public static String ACCOUNT_TYPE_DATATABLE = APP_CONTEXT_PATH + "system/accounttype/datatable";
    public static String ACCOUNT_TYPE_UPDATE = APP_CONTEXT_PATH + "system/accounttype/{id}";
    public static String ACCOUNT_TYPE_DELETE = APP_CONTEXT_PATH + "system/accounttype/{id}";
    public static String ACCOUNT_TYPE_BY_ENTITY = APP_CONTEXT_PATH + "system/accounttypebyentity";

    //User Register
    public static String USER_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/userregister";
    public static String USER_REGISTER_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity/userregisterbyentity";
    public static String USER_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/userregister";
    public static String USER_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/userregister/datatable";
    public static String USER_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/userregister/{id}";
    public static String USER_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/userregister/{id}";
    public static String USER_EXISTS = APP_CONTEXT_PATH + "entity/userregister/usernameexists";

    //User Login Info
    public static String USER_LOGININFO = APP_CONTEXT_PATH + "entity/userloginfo";


    //Customer Group Register
    public static String CUSTOMER_GROUP_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/customergroupregister";
    public static String CUSTOMER_GROUP_REGISTER_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity/customergroupregisterbyentity";
    public static String CUSTOMER_GROUP_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/customergroupregister";
    public static String CUSTOMER_GROUP_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/customergroupregister/datatable";
    public static String CUSTOMER_GROUP_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/customergroupregister/{id}";
    public static String CUSTOMER_GROUP_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/customergroupregister/{id}";

    //Day End Master
    public static String DAY_END_MASTER_SHOW = APP_CONTEXT_PATH + "entity/dayendmaster";
    public static String DAY_END_MASTER_SAVE = APP_CONTEXT_PATH + "entity/dayendmaster";
    public static String DAY_END_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/dayendmaster/datatable";
    public static String DAY_END_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/dayendmaster/{id}";
    public static String DAY_END_MASTER_DELETE = APP_CONTEXT_PATH + "entity/dayendmaster/{id}";
    public static String DAY_END_MASTER_ENTITY = APP_CONTEXT_PATH + "entity/dayendmasterbyentity/{id}";

    //Day End Master
    public static String HQ_AREA_SHOW = APP_CONTEXT_PATH + "entity/hqareas";
    public static String HQ_AREA_SAVE = APP_CONTEXT_PATH + "entity/hqareas";
    public static String HQ_AREA_DATATABLE = APP_CONTEXT_PATH + "entity/hqareas/datatable";
    public static String HQ_AREA_UPDATE = APP_CONTEXT_PATH + "entity/hqareas/{id}";
    public static String HQ_AREA_DELETE = APP_CONTEXT_PATH + "entity/hqareas/{id}";
    public static String HQ_AREA_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "entity/hqbyentity";


    //Financial Year master
    public static String FINANCIAL_YEAR_SHOW = APP_CONTEXT_PATH + "entity/financialyearmaster";
    public static String FINANCIAL_YEAR_SAVE = APP_CONTEXT_PATH + "entity/financialyearmaster";
    public static String FINANCIAL_YEAR_DATATABLE = APP_CONTEXT_PATH + "entity/financialyearmaster/datatable";
    public static String FINANCIAL_YEAR_UPDATE = APP_CONTEXT_PATH + "entity/financialyearmaster/{id}";
    public static String FINANCIAL_YEAR_DELETE = APP_CONTEXT_PATH + "entity/financialyearmaster/{id}";
    public static String FINANCIAL_YEAR_ENTITY = APP_CONTEXT_PATH + "entity/financialyearmasterbyentity";


    //Region Register
    public static String REGION_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/regionregister";
    public static String REGION_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/regionregister";
    public static String REGION_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/regionregister/datatable";
    public static String REGION_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/regionregister/{id}";
    public static String REGION_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/regionregister/{id}";

    //Route Register
    public static String ROUTE_REGISTER_SHOW = APP_CONTEXT_PATH + "entity/routeregister";
    public static String ROUTE_REGISTER_SAVE = APP_CONTEXT_PATH + "entity/routeregister";
    public static String ROUTE_REGISTER_GET_BY_ENTITY = APP_CONTEXT_PATH + "entity/routeregisterbyentity";
    public static String ROUTE_REGISTER_DATATABLE = APP_CONTEXT_PATH + "entity/routeregister/datatable";
    public static String ROUTE_REGISTER_UPDATE = APP_CONTEXT_PATH + "entity/routeregister/{id}";
    public static String ROUTE_REGISTER_DELETE = APP_CONTEXT_PATH + "entity/routeregister/{id}";

    //Role Master
    public static String ROLE_MASTER_SHOW = APP_CONTEXT_PATH + "entity/rolemaster";
    public static String ROLE_MASTER_SAVE = APP_CONTEXT_PATH + "entity/rolemaster";
    public static String ROLE_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/rolemaster/datatable";
    public static String ROLE_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/rolemaster/{id}";
    public static String ROLE_MASTER_DELETE = APP_CONTEXT_PATH + "entity/rolemaster/{id}";

    //Role
    public static String ROLE_SHOW = APP_CONTEXT_PATH + "entity/role";
    public static String ROLE_SAVE = APP_CONTEXT_PATH + "entity/role";
    public static String ROLE_DATATABLE = APP_CONTEXT_PATH + "entity/role/datatable";
    public static String ROLE_UPDATE = APP_CONTEXT_PATH + "entity/role/{id}";
    public static String ROLE_DELETE = APP_CONTEXT_PATH + "entity/role/{id}";

    //feature
    public static String FEATURE_SHOW = APP_CONTEXT_PATH + "entity/role/features";


    //Rule Master
    public static String RULE_MASTER_SHOW = APP_CONTEXT_PATH + "entity/rulemaster";
    public static String RULE_MASTER_SAVE = APP_CONTEXT_PATH + "entity/rulemaster";
    public static String RULE_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/rulemaster/datatable";
    public static String RULE_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/rulemaster/{id}";
    public static String RULE_MASTER_DELETE = APP_CONTEXT_PATH + "entity/rulemaster/{id}";

    //Tax Register
    public static String TAX_MASTER_SHOW = APP_CONTEXT_PATH + "entity/taxregister";
    public static String TAX_MASTER_SAVE = APP_CONTEXT_PATH + "entity/taxregister";
    public static String TAX_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/taxregister/datatable";
    public static String TAX_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/taxregister/{id}";
    public static String TAX_MASTER_DELETE = APP_CONTEXT_PATH + "entity/taxregister/{id}";
    public static String TAX_MASTER_BY_ENTITY = APP_CONTEXT_PATH + "entity/taxregisterbyentity/{id}";
    public static String TAX_REGISTER_BY_VALUE_ENTITY = APP_CONTEXT_PATH + "entity/taxregsiterby-entity-value";


    //Territory
    public static String TERRITORY_MASTER_SHOW = APP_CONTEXT_PATH + "entity/territoryregister";
    public static String TERRITORY_MASTER_SAVE = APP_CONTEXT_PATH + "entity/territoryregister";
    public static String TERRITORY_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/territoryregister/datatable";
    public static String TERRITORY_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/territoryregister/{id}";
    public static String TERRITORY_MASTER_DELETE = APP_CONTEXT_PATH + "entity/territoryregister/{id}";

    //Terms and Conditions
    public static String TC_MASTER_SHOW = APP_CONTEXT_PATH + "entity/termconditiondetails";
    public static String TC_MASTER_SAVE = APP_CONTEXT_PATH + "entity/termconditiondetails";
    public static String TC_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/termconditiondetails/datatable";
    public static String TC_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/termconditiondetails/{id}";
    public static String TC_MASTER_DELETE = APP_CONTEXT_PATH + "entity/termconditiondetails/{id}";

    //Series Master
    public static String SERIES_MASTER_SHOW = APP_CONTEXT_PATH + "entity/seriesmaster";
    public static String SERIES_MASTER_BY_ENTITY = APP_CONTEXT_PATH + "entity/seriesmasterbyentity";
    public static String SERIES_MASTER_SAVE = APP_CONTEXT_PATH + "entity/seriesmaster";
    public static String SERIES_MASTER_DATATABLE = APP_CONTEXT_PATH + "entity/seriesmaster/datatable";
    public static String SERIES_MASTER_UPDATE = APP_CONTEXT_PATH + "entity/seriesmaster/{id}";
    public static String SERIES_MASTER_DELETE = APP_CONTEXT_PATH + "entity/seriesmaster/{id}";


    //Service Type Master
    public static String SERVICE_TYPE_SHOW = APP_CONTEXT_PATH + "entity/servicetype";
    public static String SERVICE_TYPE_SAVE = APP_CONTEXT_PATH + "entity/servicetype";
    public static String SERVICE_TYPE_DATATABLE = APP_CONTEXT_PATH + "entity/servicetype/datatable";
    public static String SERVICE_TYPE_UPDATE = APP_CONTEXT_PATH + "entity/servicetype/{id}";
    public static String SERVICE_TYPE_DELETE = APP_CONTEXT_PATH + "entity/servicetype/{id}";


    /*<-------------------------------------------Product ------------------------------------------------->*/
    //Product Register
    public static String PRODUCT_REGISTER_SHOW = APP_CONTEXT_PATH + "product/productregister";
    public static String PRODUCT_REGISTER_BY_ENTITY = APP_CONTEXT_PATH + "product/productregisterbyentity";
    public static String PRODUCT_REGISTER_BY_DIVISION = APP_CONTEXT_PATH + "product/productregisterbydivision";
    public static String PRODUCT_REGISTER_SAVE = APP_CONTEXT_PATH + "product/productregister";
    public static String PRODUCT_REGISTER_DATATABLE = APP_CONTEXT_PATH + "product/productregister/datatable";
    public static String PRODUCT_REGISTER_SEARCH = APP_CONTEXT_PATH + "product/productregister/search";
    public static String PRODUCT_REGISTER_UPDATE = APP_CONTEXT_PATH + "product/productregister/{id}";
    public static String PRODUCT_REGISTER_DELETE = APP_CONTEXT_PATH + "product/productregister/{id}";
    public static String PRODUCT_REGISTER_BY_ID_HSN = APP_CONTEXT_PATH + "product/productregisterbyhsnandentity";
    public static String PRODUCT_REGISTER_GET_BY_BARCODE = APP_CONTEXT_PATH + "product/productregister/barcode";
    public static String PRODUCT_REGISTER_UPDATE_BARCODE = APP_CONTEXT_PATH + "product/updatebarcode";

    //Product Type
    public static String PRODUCT_TYPE_SHOW = APP_CONTEXT_PATH + "product/producttypemaster";
    public static String PRODUCT_TYPE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/producttypemasterbyentity";
    public static String PRODUCT_TYPE_SAVE = APP_CONTEXT_PATH + "product/producttypemaster";
    public static String PRODUCT_TYPE_DATATABLE = APP_CONTEXT_PATH + "product/producttypemaster/datatable";
    public static String PRODUCT_TYPE_UPDATE = APP_CONTEXT_PATH + "product/producttypemaster/{id}";
    public static String PRODUCT_TYPE_DELETE = APP_CONTEXT_PATH + "product/producttypemaster/{id}";

    //Product Group
    public static String PRODUCT_GROUP_SHOW = APP_CONTEXT_PATH + "product/productgroupmaster";
    public static String PRODUCT_GROUP_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/productgroupmasterbyentity";
    public static String PRODUCT_GROUP_SAVE = APP_CONTEXT_PATH + "product/productgroupmaster";
    public static String PRODUCT_GROUP_DATATABLE = APP_CONTEXT_PATH + "product/productgroupmaster/datatable";
    public static String PRODUCT_GROUP_UPDATE = APP_CONTEXT_PATH + "product/productgroupmaster/{id}";
    public static String PRODUCT_GROUP_DELETE = APP_CONTEXT_PATH + "product/productgroupmaster/{id}";

    //unit type
    public static String UNIT_TYPE_SHOW = APP_CONTEXT_PATH + "product/unittypemaster";
    public static String UNIT_TYPE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/unittypemasterbyentity";
    public static String UNIT_TYPE_SAVE = APP_CONTEXT_PATH + "product/unittypemaster";
    public static String UNIT_TYPE_DATATABLE = APP_CONTEXT_PATH + "product/unittypemaster/datatable";
    public static String UNIT_TYPE_UPDATE = APP_CONTEXT_PATH + "product/unittypemaster/{id}";
    public static String UNIT_TYPE_DELETE = APP_CONTEXT_PATH + "product/unittypemaster/{id}";

    //division
    public static String DIVISION_SAVE = APP_CONTEXT_PATH + "product/division";
    public static String DIVISION_DATATABLE = APP_CONTEXT_PATH + "product/division/datatable";
    public static String DIVISION_UPDATE = APP_CONTEXT_PATH + "product/division/{id}";
    public static String DIVISION_DELETE = APP_CONTEXT_PATH + "product/division/{id}";

    //division group
    public static String DIVISION_GROUP_SAVE = APP_CONTEXT_PATH + "product/divisiongroupregister";
    public static String DIVISION_GROUP_DATATABLE = APP_CONTEXT_PATH + "product/divisiongroupregister/datatable";
    public static String DIVISION_GROUP_UPDATE = APP_CONTEXT_PATH + "product/divisiongroupregister/{id}";
    public static String DIVISION_GROUP_DELETE = APP_CONTEXT_PATH + "product/divisiongroupregister/{id}";

    //Product Category
    public static String PRODUCT_CATEGORY_SHOW = APP_CONTEXT_PATH + "product/productcategorymaster";
    public static String PRODUCT_CATEGORY_BY_ENTITY_SHOW = APP_CONTEXT_PATH + "product/productcategorymasterbyentity";
    public static String PRODUCT_CATEGORY_SAVE = APP_CONTEXT_PATH + "product/productcategorymaster";
    public static String PRODUCT_CATEGORY_DATATABLE = APP_CONTEXT_PATH + "product/productcategorymaster/datatable";
    public static String PRODUCT_CATEGORY_UPDATE = APP_CONTEXT_PATH + "product/productcategorymaster/{id}";
    public static String PRODUCT_CATEGORY_DELETE = APP_CONTEXT_PATH + "product/productcategorymaster/{id}";

    //Product Schedule
    public static String PRODUCT_SCHEDULE_SHOW = APP_CONTEXT_PATH + "product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/productschdulemasterbyentity";
    public static String PRODUCT_SCHEDULE_SAVE = APP_CONTEXT_PATH + "product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_DATATABLE = APP_CONTEXT_PATH + "product/productschdulemaster/datatable";
    public static String PRODUCT_SCHEDULE_UPDATE = APP_CONTEXT_PATH + "product/productschdulemaster/{id}";
    public static String PRODUCT_SCHEDULE_DELETE = APP_CONTEXT_PATH + "product/productschdulemaster/{id}";

    //Product Composition
    public static String PRODUCT_COMPOSITION_SHOW = APP_CONTEXT_PATH + "product/compositionmasterregister";
    public static String PRODUCT_COMPOSITION_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/composition/getbyentity";
    public static String PRODUCT_COMPOSITION_SAVE = APP_CONTEXT_PATH + "product/compositionmasterregister";
    public static String PRODUCT_COMPOSITION_DATATABLE = APP_CONTEXT_PATH + "product/compositionmasterregister/datatable";
    public static String PRODUCT_COMPOSITION_UPDATE = APP_CONTEXT_PATH + "product/compositionmasterregister/{id}";
    public static String PRODUCT_COMPOSITION_DELETE = APP_CONTEXT_PATH + "product/compositionmasterregister/{id}";

    //Product Class
    public static String PRODUCT_CLASS_SHOW = APP_CONTEXT_PATH + "product/productclass";
    public static String PRODUCT_CLASS_SAVE = APP_CONTEXT_PATH + "product/productclass";
    public static String PRODUCT_CLASS_DATATABLE = APP_CONTEXT_PATH + "product/productclass/datatable";
    public static String PRODUCT_CLASS_UPDATE = APP_CONTEXT_PATH + "product/productclass/{id}";
    public static String PRODUCT_CLASS_DELETE = APP_CONTEXT_PATH + "product/productclass/{id}";

    //Product Cost
    public static String PRODUCT_COST_SHOW = APP_CONTEXT_PATH + "product/productcostrange";
    public static String PRODUCT_COST_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "product/productcostrangebyentity";
    public static String PRODUCT_COST_SAVE = APP_CONTEXT_PATH + "product/productcostrange";
    public static String PRODUCT_COST_DATATABLE = APP_CONTEXT_PATH + "product/productcostrange/datatable";
    public static String PRODUCT_COST_UPDATE = APP_CONTEXT_PATH + "product/productcostrange/{id}";
    public static String PRODUCT_COST_DELETE = APP_CONTEXT_PATH + "product/productcostrange/{id}";

    //Batch Register
    public static String BATCH_REGISTER_SHOW = APP_CONTEXT_PATH + "product/batchregister";
    public static String GET_BATCH_BY_PRODUCT = APP_CONTEXT_PATH + "product/batchregisterbyproduct";
    public static String BATCH_REGISTER_SAVE = APP_CONTEXT_PATH + "product/batchregister";
    public static String BATCH_REGISTER_DATATABLE = APP_CONTEXT_PATH + "product/batchregister/datatable";
    public static String BATCH_REGISTER_UPDATE = APP_CONTEXT_PATH + "product/batchregister/{id}";
    public static String BATCH_REGISTER_DELETE = APP_CONTEXT_PATH + "product/batchregister/{id}";
    public static String GET_BY_BATCH_AND_PRODUCT = APP_CONTEXT_PATH + "product/batch-and-product";

    /*<--------------------------------------------Sales------------------------------------------------->*/
    //Sale Bill Details
    public static String GET_REASON = APP_CONTEXT_PATH + "sales/reasonmaster";

    //Sale Bill Details
    public static String SALE_BILL_SHOW = APP_CONTEXT_PATH + "sales/salebilldetails";
    public static String SALE_BILL_BY_DATERANGE = APP_CONTEXT_PATH + "sales/salebillbydaterange";
    public static String SALE_BILL_BY_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "sales/salebillbydaterangecustomer";
    public static String DRAFT_SALE_BILL_SHOW = APP_CONTEXT_PATH + "sales/draftsalebilldetails";
    public static String SALE_BILL_BALANCE_UPDATE = APP_CONTEXT_PATH + "sales/updatebalancebyid";
    /*    public static String SALE_BILL_BALANCE_CREDITS_UPDATE = APP_CONTEXT_PATH + "sales/updatebalanceandsettlecredits";*/
    public static String SALE_BILL_BALANCE_CREDITS_UPDATE = APP_CONTEXT_PATH + "sales/updatebalanceandadjustcredits";
    public static String SALE_BILL_SAVE = APP_CONTEXT_PATH + "sales/salebilldetails";
    public static String SALE_BILL_CANCEL = APP_CONTEXT_PATH + "sales/salebilldetails/cancel";
    public static String SALE_BILL_DATATABLE = APP_CONTEXT_PATH + "sales/salebilldetails/datatable";
    public static String SALE_BILL_UPDATE = APP_CONTEXT_PATH + "sales/salebilldetails/{id}";
    public static String SALE_BILL_DELETE = APP_CONTEXT_PATH + "sales/salebilldetails/{id}";
    public static String SALE_BILL_UNSETTLED = APP_CONTEXT_PATH + "sales/salebillunsettledbycustomer";
    public static String SALE_BILL_SETTLED = APP_CONTEXT_PATH + "sales/salebillsettledbycustomer";
    public static String SALE_BILL_PAYMENT = APP_CONTEXT_PATH + "sales/salebillbypaymentstatus";
    public static String SET_PAYMENT_BILL = APP_CONTEXT_PATH + "sales/setpaymentstatus/{id}/type/{type}";
    public static String SALE_BILL_RECENT = APP_CONTEXT_PATH + "sales/salebilldetails/getrecent";
    public static String SALE_BILL_PENDING_IRN = APP_CONTEXT_PATH + "sales/salebilldetails/pendingirn";
    public static String SALE_BILL_PENDING_PAYMENT = APP_CONTEXT_PATH + "sales/salebilldetails/paymentpending";
    public static String SALE_BILL_CUSTOMER = APP_CONTEXT_PATH + "sales/salebillbycustomer/{custid}";
    public static String SALE_BILL_CUSTOMER_START_DATE = APP_CONTEXT_PATH + "sales/salebillbycustomerstartdate/{custid}";
    public static String SALE_BILL_REPORTS = APP_CONTEXT_PATH + "sales/reports/customerwise";
    public static String SALE_BILL_DATEWISE_REPORTS = APP_CONTEXT_PATH + "sales/reports/datewise";
    public static String SALE_BILL_AREAWISE_REPORTS = APP_CONTEXT_PATH + "sales/reports/areawise";
    public static String SALE_BILL_CONSOLIDATED_REPORTS = APP_CONTEXT_PATH + "sales/reports/consolidated";
    public static String SALE_RETURN_AREAWISE = APP_CONTEXT_PATH + "sales/reports/salesreturn-areawise";
    public static String SALE_BILL_UPDATE_IRN = APP_CONTEXT_PATH + "sales/salebilldetails/updateirn";
    public static String SALE_BILL_UPDATE_EWAYBILL = APP_CONTEXT_PATH + "sales/salebilldetails/updateewaybill";
    public static String SALE_RETURN_BY_DATERANGE = APP_CONTEXT_PATH + "sales/salereturnbydaterange";
    public static String SALE_RETURN_BY_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "sales/salereturnbydaterangecustomer";
    public static String SALE_ENTRY_STATS = APP_CONTEXT_PATH + "sales/reports/statistics";
    public static String DELETE_DRAFTS_SALE_BILLS = APP_CONTEXT_PATH + "sales/delete-drafts-sale-bill";
    public static String GET_DRAFTS_SALE_BILLS = APP_CONTEXT_PATH + "sales/drafts-sale-bill";

    public static String SALE_INFO_REPORTS = APP_CONTEXT_PATH + "sales/reports/salesinfo";
    public static String PURCHASE_INFO_REPORTS = APP_CONTEXT_PATH + "purchase/reports/purchaseinfo";

    public static String GET_OUTSTANDING_REPORT = APP_CONTEXT_PATH + "accounts/reports/outstanding";

    public static String GST_SALES_REPORTS = APP_CONTEXT_PATH + "sales/reports/salesgst";
    public static String CREDIT_NOTE_GST_SALES_REPORTS = APP_CONTEXT_PATH + "sales/reports/creditnotegst";
    public static String SALE_FSN_REPORT = APP_CONTEXT_PATH + "sales/reports/fsn";

    public static String SALE_SCHEME_UPDATE = APP_CONTEXT_PATH + "sales/schemeconfiguration/{id}";
    public static String SALE_SCHEME_DELETE = APP_CONTEXT_PATH + "sales/schemeconfiguration/{id}";
    public static String SALE_SCHEME_SAVE = APP_CONTEXT_PATH + "sales/schemeconfiguration";
    public static String SALE_SCHEME_SHOW = APP_CONTEXT_PATH + "sales/schemeconfiguration";
    public static String SALE_SCHEME_DATATABLE = APP_CONTEXT_PATH + "sales/schemeconfiguration/datatable";


    public static String SALE_SCHEME_CONFIG_GET_PRODUCT_BATCH = APP_CONTEXT_PATH + "sales/schemeconfiguration/product/$productId/batch/$batchNumber";


    public static String SALE_INVOICE_SAVE = APP_CONTEXT_PATH + "sales/salebilldetails/save-invoice";
    public static String SALE_INVOICE_UPDATE = APP_CONTEXT_PATH + "sales/salebilldetails/update-invoice";

    //Sale Product Details
    public static String SALE_PRODUCT_SHOW = APP_CONTEXT_PATH + "sales/saleproductdetails";
    public static String SALE_PRODUCT_SAVE = APP_CONTEXT_PATH + "sales/saleproductdetails";
    public static String SALE_PRODUCT_SAVE_LIST = APP_CONTEXT_PATH + "sales/saleproductdetails/savelist";
    public static String SALE_PRODUCT_DATATABLE = APP_CONTEXT_PATH + "sales/saleproductdetails/datatable";
    public static String SALE_PRODUCT_UPDATE = APP_CONTEXT_PATH + "sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_DELETE = APP_CONTEXT_PATH + "sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_OF_BILL = APP_CONTEXT_PATH + "sales/saleproductdetails/bill";
    public static String SALE_PRODUCT_OF_BILLIDS = APP_CONTEXT_PATH + "sales/saleproductdetailslist/bill/{salebillsIds}";
    public static String SALE_PRODUCT_BY_PRODUCT = APP_CONTEXT_PATH + "sales/saleproductdetailsbyproductId";
    public static String SALE_PRODUCT_BILL_BATCH = APP_CONTEXT_PATH + "sales/saleproductdetailsbillandbatch";

    //     sale transportation details
    public static String SALE_TRANSPORTATION_SAVE = APP_CONTEXT_PATH + "sales/saletransportationdetails";
    public static String SALE_TRANSPORTATION_UPDATE = APP_CONTEXT_PATH + "sales/saletransportationdetails/{id}";
    public static String SALE_TRANSPORTATION_BY_BILL = APP_CONTEXT_PATH + "sales/saletransportationdetails/billid";

    // stock adjustment Details
    public static String STOCK_ADJUSTMENT_SAVE = APP_CONTEXT_PATH + "sales/stockadjustmentdetails";
    public static String STOCK_ADJUSTMENT_DATATABLE = APP_CONTEXT_PATH + "sales/stockadjustmentdetails/datatable";
    public static String STOCK_ADJUSTMENT_GET = APP_CONTEXT_PATH + "sales/stockadjustmentdetails/get";

    //Sale Return
    public static String SALE_RETURN_SHOW = APP_CONTEXT_PATH + "sales/salereturn";
    public static String SALE_RETURN_ADJUSTMENT = APP_CONTEXT_PATH + "sales/salereturn/adjustment";
    public static String SALE_RETURN_ADJUSTMENT_DETAILS = APP_CONTEXT_PATH + "sales/salereturn/adjustment-details";
    public static String SALE_RETURN_ADJUSTMENT_DETAILS_START_DATE = APP_CONTEXT_PATH + "sales/salereturn/adjustment-details-startdate";
    public static String SALE_RETURN_SAVE = APP_CONTEXT_PATH + "sales/salereturn";
    public static String SALE_RETURN_DATATABLE = APP_CONTEXT_PATH + "sales/salereturn/datatable";
    public static String SALE_RETURN_UNSETTLED = APP_CONTEXT_PATH + "sales/saleretrununsettledbycustomer";
    public static String SALE_RETURN_SETTLED = APP_CONTEXT_PATH + "sales/saleretrunsettledbycustomer";
    public static String SET_SALE_RETURN_STATUS = APP_CONTEXT_PATH + "sales/setsalereturnstatus/{id}/type/{type}/adj/{adj}";
    public static String UPDATE_SALE_RETURN_BALANCE = APP_CONTEXT_PATH + "sales/updatereturnbalancebyid";
    public static String SALE_RETURN_CUSTOMER = APP_CONTEXT_PATH + "sales/salereturnbycustomer";
    public static String SALE_RETURN_CUSTOMER_START_DATE = APP_CONTEXT_PATH + "sales/salereturnbycustomerstartdate";
    public static String SALE_RETURN_RECENT = APP_CONTEXT_PATH + "sales/salereturn/getrecent";
    public static String SALE_RETURN_PRODUCT_BATCH_BILL = APP_CONTEXT_PATH + "sales/getsalereturndetailsby-product-batch-salebill";
    public static String SALE_RETURN_CANCEL = APP_CONTEXT_PATH + "sales/salereturn/cancel";

    //Sale Return Details
    public static String SALE_RETURN_DETAIL_SHOW = APP_CONTEXT_PATH + "sales/salereturndetails";
    public static String SALE_RETURN_DETAIL_SAVE = APP_CONTEXT_PATH + "sales/salereturndetails";
    public static String SALE_RETURN_DETAIL_BILL = APP_CONTEXT_PATH + "sales/salereturndetails/bill";


    //Sale Order Details
    public static String SALE_ORDER_SAVE = APP_CONTEXT_PATH + "sales/saleorderdetails";
    public static String SALE_ORDER_SHOW = APP_CONTEXT_PATH + "sales/saleorderdetails";
    public static String SALE_PRODUCT_ORDER = APP_CONTEXT_PATH + "sales/saleorderproductdetails/bill";
    public static String SALE_ORDER_DATATABLE = APP_CONTEXT_PATH + "sales/saleorderdetails/datatable";
    public static String SALE_ORDER_RECENT = APP_CONTEXT_PATH + "sales/saleorderdetails/getrecent";
    public static String SALE_ORDER_CANCEL = APP_CONTEXT_PATH + "sales/saleorderdetails/cancel";
    public static String SALE_ORDER_BY_DATERANGE = APP_CONTEXT_PATH + "sales/saleorderbydaterange";
    public static String SALE_ORDER_BY_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "sales/saleorderbydaterangecustomer";


    //    convert to Sale Entry
    public static String SALE_ORDER_TO_SALE_ENTRY = APP_CONTEXT_PATH + "sales/convert-to-sale-entry";


//    public static String SALE_ORDER_DATATABLE = APP_CONTEXT_PATH + "sales/saleproductdetails/datatable";
//    public static String SALE_ORDER_UPDATE = APP_CONTEXT_PATH + "sales/saleproductdetails/{id}";
//    public static String SALE_ORDER_DELETE = APP_CONTEXT_PATH + "sales/saleproductdetails/{id}";
//    public static String SALE_ORDER = APP_CONTEXT_PATH + "sales/saleproductdetails";


    //    Goods Transfer Note
    public static String GTN_SAVE = APP_CONTEXT_PATH + "sales/gtn";
    public static String GTN_SHOW = APP_CONTEXT_PATH + "sales/gtn";
    public static String GTN_SHOW_DATERANGE = APP_CONTEXT_PATH + "sales/gtnbydaterange";
    public static String GTN_SHOW_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "sales/gtnbydaterangecustomer";
    public static String GTN_DATATABLE = APP_CONTEXT_PATH + "sales/gtn/datatable";
    public static String GTN_RECENT = APP_CONTEXT_PATH + "sales/gtn/getrecent";
    public static String GTN_CANCEL = APP_CONTEXT_PATH + "sales/gtn/cancel";
    public static String GTN_APPROVE = APP_CONTEXT_PATH + "sales/gtn/approve";
    public static String GTN_CUSTOMER = APP_CONTEXT_PATH + "sales/gtnbycustomer";
    public static String UPDATE_GTN_BALANCE = APP_CONTEXT_PATH + "sales/updategtnbalancebyid";


    //    Goods Transfer Note
    public static String DELIVERY_CHALLAN_SAVE = APP_CONTEXT_PATH + "sales/deliverychallan";
    public static String DELIVERY_CHALLAN_SHOW = APP_CONTEXT_PATH + "sales/deliverychallan";
    public static String DELIVERY_CHALLAN_DATATABLE = APP_CONTEXT_PATH + "sales/deliverychallan/datatable";
    public static String DELIVERY_CHALLAN_RECENT = APP_CONTEXT_PATH + "sales/deliverychallan/getrecent";
    public static String DELIVERY_CHALLAN_CANCEL = APP_CONTEXT_PATH + "sales/deliverychallan/cancel";
    public static String DELIVERY_CHALLAN_APPROVE = APP_CONTEXT_PATH + "sales/deliverychallan/approve";
    public static String DELIVERY_CHALLAN_CUSTOMER = APP_CONTEXT_PATH + "sales/deliverychallanbycustomer";
    public static String UPDATE_DELIVERY_CHALLAN_BALANCE = APP_CONTEXT_PATH + "sales/updatedeliverychallanbalancebyid";
    public static String DELIVERY_CHALLAN_DATERANGE = APP_CONTEXT_PATH + "sales/deliverychallanbydaterange";
    public static String DELIVERY_CHALLAN_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "sales/deliverychallanbydaterangecustomer";


    //    Goods transfer note products
    public static String GTN_PRODUCTS_SAVE = APP_CONTEXT_PATH + "sales/gtnproduct";
    public static String GTN_PRODUCTS_BY_GTN = APP_CONTEXT_PATH + "sales/gtnproduct/gtn";


    //   Delivery Challan
    public static String DELIVERY_CHALLAN_PRODUCT_SAVE = APP_CONTEXT_PATH + "sales/deliverychallanproduct";
    public static String DC_PRODUCTS_BY_DC = APP_CONTEXT_PATH + "sales/deliverychallanproduct/deliverychallan";


    // Invoice Signature
    public static String INVOICE_SIGN_GETBY_INVOICENUMBER = APP_CONTEXT_PATH + "sales/invoiceSignature/getbyinvoicenumber";



    /*<--------------------------------------------Inventory ------------------------------------------------->*/

    //Stock book
    public static String STOCK_BOOK = APP_CONTEXT_PATH + "inventory/stockbook";
    public static String STOCK_BOOK_BY_ENTITY = APP_CONTEXT_PATH + "inventory/stockbookbyentity";
    public static String STOCK_BOOK_DATATABLE = APP_CONTEXT_PATH + "inventory/stockbook/datatable";
    public static String GET_STOCKS_OF_PRODUCT = APP_CONTEXT_PATH + "inventory/stockbookbyproduct";
    public static String GET_STOCKS_OF_PRODUCT_SALE_RETURN = APP_CONTEXT_PATH + "inventory/stockbookbyproductsalereturn";
    public static String STOCK_BOOK_PURCHASE = APP_CONTEXT_PATH + "inventory/stockbook/purchase/";
    public static String STOCK_BOOK_BY_USER = APP_CONTEXT_PATH + "inventory/stockbook/user";
    public static String STOCK_BOOK_BY_BATCH = APP_CONTEXT_PATH + "inventory/stockbook/batch";
    public static String STOCK_BOOK_BY_PROD_BATCH = APP_CONTEXT_PATH + "inventory/stockbook/product/{productId}/batch/{batch}";
    public static String STOCK_SALE_RETURN = APP_CONTEXT_PATH + "inventory/stockbook/salereturn";
    public static String STOCK_PURCHASE_RETURN = APP_CONTEXT_PATH + "inventory/stockbook/purchasereturn";
    public static String STOCK_ACTIVITY_DATERANGE_ENTITY = APP_CONTEXT_PATH + "inventory/stockactivitydaterangeentity";
    public static String STOCK_ACTIVITY_CLOSING = APP_CONTEXT_PATH + "inventory/stockactivity/closing";
    public static String STOCK_EXPIRY_REPORT = APP_CONTEXT_PATH + "inventory/reports/expiry";


    //Temp Stock Book
    public static String GET_TEMP_STOCK_PRODUCT = APP_CONTEXT_PATH + "inventory/tempstockbook";
    public static String GET_TEMP_STOCK_PRODUCT_BATCH = APP_CONTEXT_PATH + "inventory/tempstockbookbyproductandbatch";
    public static String TEMP_STOCK_BOOK_SAVE = APP_CONTEXT_PATH + "inventory/tempstockbook";
    public static String TEMP_STOCK_BOOK_BY_USER = APP_CONTEXT_PATH + "inventory/tempstockbook/user";
    public static String UPDATE_STOCK_BATCH_DETAILS = APP_CONTEXT_PATH + "inventory/stockbook/update-batch-details";



    /*<--------------------------------------------Accounts ------------------------------------------------->*/

    //Bank Register
    public static String BANK_REGISTER_SHOW = APP_CONTEXT_PATH + "accounts/bankregister";
    public static String BANK_REGISTER_SAVE = APP_CONTEXT_PATH + "accounts/bankregister";
    public static String BANK_REGISTER_DATATABLE = APP_CONTEXT_PATH + "accounts/bankregister/datatable";
    public static String BANK_REGISTER_UPDATE = APP_CONTEXT_PATH + "accounts/bankregister/{id}";
    public static String BANK_REGISTER_DELETE = APP_CONTEXT_PATH + "accounts/bankregister/{id}";
    public static String BANK_REGISTER_BY_ENTITY = APP_CONTEXT_PATH + "accounts/bankregisterbyentity";


    //GL
    public static String GENERAL_LEDGER_SHOW = APP_CONTEXT_PATH + "accounts/generalledger";
    public static String GENERAL_LEDGER_SAVE = APP_CONTEXT_PATH + "accounts/generalledger";
    public static String GENERAL_LEDGER_DATATABLE = APP_CONTEXT_PATH + "accounts/generalledger/datatable";
    public static String GENERAL_LEDGER_UPDATE = APP_CONTEXT_PATH + "accounts/generalledger/{id}";

    //Accounts
    public static String RECIPT_DETAIL = APP_CONTEXT_PATH + "accounts/receiptdetails";
    public static String RECIPT_DETAIL_SAVE = APP_CONTEXT_PATH + "accounts/receiptdetails";
    public static String RECIPT_DETAIL_DATATABLE = APP_CONTEXT_PATH + "accounts/receiptdetails/datatable";
    public static String RECIPT_DETAIL_UPDATE = APP_CONTEXT_PATH + "accounts/receiptdetails/{id}";
    public static String RECIPT_DETAIL_DELETE = APP_CONTEXT_PATH + "accounts/receiptdetails/{id}";
    public static String RECIPT_APPROVE = APP_CONTEXT_PATH + "accounts/receipt-approve";
    public static String RECIPT_DETAIL_BY_DATERANGE = APP_CONTEXT_PATH + "accounts/receiptdetailsbydaterange";
    public static String RECEIPT_BY_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "accounts/receiptbydaterangecustomer";

    //    Reciept detail log
    public static String RECIPT_DETAIL_LOG = APP_CONTEXT_PATH + "accounts/reciptdetaillog";
    public static String RECEIPT_DETAIL_LOG = APP_CONTEXT_PATH + "accounts/receiptdetaillog";
    public static String RECEIPT_DETAIL_LOG_START_DATE = APP_CONTEXT_PATH + "accounts/receiptdetaillogstartdate";
    public static String RECIPT_DETAIL_LOG_INVS_ID = APP_CONTEXT_PATH + "accounts/reciptdetailloginvbyreciptId";
    public static String RECIPT_DETAIL_LOG_CRNT_ID = APP_CONTEXT_PATH + "accounts/reciptdetaillogcrntbyreciptId";
    public static String RECIPT_DETAIL_LOG_GTN_ID = APP_CONTEXT_PATH + "accounts/reciptdetailloggtnbyreciptId";
    public static String RECIPT_CANCEL = APP_CONTEXT_PATH + "accounts/receipt/cancel";


    //Wallet
    public static String WALLET_SHOW = APP_CONTEXT_PATH + "accounts/wallet";
    public static String WALLET_SAVE = APP_CONTEXT_PATH + "accounts/wallet";
    public static String WALLET_DATATABLE = APP_CONTEXT_PATH + "accounts/wallet/datatable";
    public static String WALLET_UPDATE = APP_CONTEXT_PATH + "accounts/wallet/{id}";
    public static String WALLET_DELETE = APP_CONTEXT_PATH + "accounts/wallet/{id}";


    //Payemnt Show
    public static String PAYMENT_SHOW = APP_CONTEXT_PATH + "accounts/paymentdetails";
    public static String PAYMENT_SAVE = APP_CONTEXT_PATH + "accounts/paymentdetails";
    public static String PAYMENT_DATATABLE = APP_CONTEXT_PATH + "accounts/paymentdetails/datatable";
    public static String PAYMENT_UPDATE = APP_CONTEXT_PATH + "accounts/paymentdetails/{id}";
    public static String PAYMENT_DELETE = APP_CONTEXT_PATH + "accounts/paymentdetails/{id}";
    public static String PAYMENT_DATERANGE = APP_CONTEXT_PATH + "accounts/paymentdetailsbydaterange";
    public static String PAYMENT_DATERANGE_CUSTOMER = APP_CONTEXT_PATH + "accounts/paymentbydaterangecustomer";


    //   payment detail log
    public static String PAYMENT_DETAIL_LOG = APP_CONTEXT_PATH + "accounts/billpaymentlog";
    public static String PAYMENT_DETAIL_LOG_INVS_ID = APP_CONTEXT_PATH + "accounts/billpaymentloginvbypaymentId";
    public static String PAYMENT_DETAIL_LOG_CRNT_ID = APP_CONTEXT_PATH + "accounts/billpaymentlogcrntbypaymentId";
    public static String PAYMENT_DETAIL_LOG_GRN_ID = APP_CONTEXT_PATH + "accounts/billpaymentloggrnbypaymentId";


    //Credit
    public static String CREDIT_SHOW = APP_CONTEXT_PATH + "accounts/creditjv";
    public static String CREDIT_SAVE = APP_CONTEXT_PATH + "accounts/creditjv";
    public static String CREDIT_APPROVE = APP_CONTEXT_PATH + "accounts/creditjv/approve";
    public static String CREDIT_UNSETTLED = APP_CONTEXT_PATH + "accounts/creditjvunbycustomer";
    public static String CREDIT_SETTLED = APP_CONTEXT_PATH + "accounts/creditjvsettledbycustomer";
    public static String CREDIT_DATATABLE = APP_CONTEXT_PATH + "accounts/creditjv/datatable";
    public static String CREDIT_UPDATE = APP_CONTEXT_PATH + "accounts/creditjv/{id}";
    public static String CREDIT_DELETE = APP_CONTEXT_PATH + "accounts/creditjv/{id}";
    public static String CREDIT_DATERANGE = APP_CONTEXT_PATH + "accounts/creditjvbydaterange";
    public static String SET_CREDIT_STATUS = APP_CONTEXT_PATH + "accounts/setcreditstatus/{id}/type/{type}";


    //Debit
    public static String DEBIT_SHOW = APP_CONTEXT_PATH + "accounts/debitjv";
    public static String DEBIT_SAVE = APP_CONTEXT_PATH + "accounts/debitjv";
    public static String DEBIT_DATATABLE = APP_CONTEXT_PATH + "accounts/debitjv/datatable";
    public static String DEBIT_APPROVE = APP_CONTEXT_PATH + "accounts/debitjv/approve";
    public static String DEBIT_DATERANGE = APP_CONTEXT_PATH + "accounts/debitjvbydaterange";


    /*<--------------------------------------------Purchase ------------------------------------------------->*/
    //purchase product details
    public static String PURCHASE_PRODUCT_SHOW = APP_CONTEXT_PATH + "purchase/productdetail";
    public static String PURCHASE_BILL_BY_DATERANGE = APP_CONTEXT_PATH + "purchase/purchasebillbydaterange";
    public static String PURCHASE_BILL_BY_DATERANGE_SUPPLIER = APP_CONTEXT_PATH + "purchase/purchasebillbydaterangesupplier";
    public static String PURCHASE_PRODUCT_SAVE = APP_CONTEXT_PATH + "purchase/productdetail";
    public static String PURCHASE_PRODUCT_DATATABLE = APP_CONTEXT_PATH + "purchase/productdetail/datatable";
    public static String PURCHASE_PRODUCT_UPDATE = APP_CONTEXT_PATH + "purchase/productdetail/{id}";
    public static String PURCHASE_PRODUCT_DELETE = APP_CONTEXT_PATH + "purchase/productdetail/{id}";
    public static String PURCHASE_PRODUCT_OF_BILL = APP_CONTEXT_PATH + "purchase/productdetail/bill";
    public static String PURCHASE_BILL_CUSTOMER = APP_CONTEXT_PATH + "purchase/purchasebillbysupplier";
    public static String PURCHASE_BILL_BALANCE_UPDATE = APP_CONTEXT_PATH + "purchase/updatebillbalancebyid";
    public static String PURCHASE_RETURN_BALANCE_UPDATE = APP_CONTEXT_PATH + "purchase/updatereturnbalancebyid";
    public static String PURCHASE_BILL_CANCEL = APP_CONTEXT_PATH + "purchase/billdetail/cancel";
    public static String PURCHASE_PRODUCT_BY_PRODUCT = APP_CONTEXT_PATH + "purchase/purchaseproductdetailsbyproductId";
    public static String PURCHASE_PRODUCT_BILL_BATCH = APP_CONTEXT_PATH + "purchase/purchaseproductdetailsbillandbatch";

    public static String DELETE_DRAFTS_PURCHASE_BILLS = APP_CONTEXT_PATH + "purchase/delete-drafts-purchase-bill";
    public static String GET_DRAFTS_PURCHASE_BILLS = APP_CONTEXT_PATH + "purchase/drafts-purchase-bill";


    //purchase product bill details
    public static String PURCHASE_BILL_SHOW = APP_CONTEXT_PATH + "purchase/billdetail";
    public static String PURCHASE_BILL_SAVE = APP_CONTEXT_PATH + "purchase/billdetail";
    public static String PURCHASE_BILL_DATATABLE = APP_CONTEXT_PATH + "purchase/billdetail/datatable";
    public static String PURCHASE_BILL_UPDATE = APP_CONTEXT_PATH + "purchase/update-purchase-invoice/{id}";
    public static String PURCHASE_BILL_DELETE = APP_CONTEXT_PATH + "purchase/billdetail/{id}";

    public static String PURCHASE_BILL_RECENT = APP_CONTEXT_PATH + "purchase/billdetail/getrecent";
    public static String PURCHASE_BILL_SUPPLIER = APP_CONTEXT_PATH + "purchase/purchasebillbysupplier";
    public static String PURCHASE_PRODUCT_OF_BILLIDS = APP_CONTEXT_PATH + "purchase/purchaseproductdetailslist/bill" +
            "/{purbillsIds}";

    //purchase return
    public static String PURCHASE_RETURN_SHOW = APP_CONTEXT_PATH + "purchase/purchase-returns";
    public static String PURCHASE_RETURN_DETAIL_BILL = APP_CONTEXT_PATH + "purchase/returndetail/bill";
    public static String PURCHASE_RETURN_SAVE = APP_CONTEXT_PATH + "purchase/purchase-return-save";
    public static String PURCHASE_RETURN_DATATABLE = APP_CONTEXT_PATH + "purchase/purchase-return/datatable";
    public static String PURCHASE_RETURN_UPDATE = APP_CONTEXT_PATH + "purchase/returndetail/{id}";
    public static String PURCHASE_RETURN_DELETE = APP_CONTEXT_PATH + "purchase/returndetail/{id}";
    public static String PURCHASE_RETURN_CUSTOMER = APP_CONTEXT_PATH + "purchase/returndetailbysupplier";
    public static String PURCHASE_RETURN_BY_DATERANGE = APP_CONTEXT_PATH + "purchase/returndetailbydaterange";
    public static String PURCHASE_RETURN_BY_DATERANGE_SUPPLIER = APP_CONTEXT_PATH + "purchase/returndetailbydaterangesupplier";
    public static String PURCHASE_RETURN_PRODUCT_BATCH_BILL = APP_CONTEXT_PATH + "purchase/getpurchasereturndetailsby-product-batch-purbill";
    public static String PURCHASE_RETURN_RECENT = APP_CONTEXT_PATH + "purchase/purchase-return/recent";
    public static String PURCHASE_RETURN_CANCEL = APP_CONTEXT_PATH + "purchase/purchase-return/cancel";


    //e-Invoice
    //public static String E_INVOICE_BASE_URL = "https://nsdlgsp.co.in";
    public static String E_INVOICE_BASE_URL = "https://test.nsdlgsp.co.in";
    public static String E_INVOICE_VERSION = "/ewb_v1.04";
   // public static String E_INVOICE_VERSION = "/ewb_v1.03";
    public static String E_INVOICE_GET_KEY = E_INVOICE_BASE_URL + "/GSPUtility/getKey";
    public static String E_INVOICE_AUTH_TOKEN = E_INVOICE_BASE_URL + E_INVOICE_VERSION + "/einv/authtoken";
    public static String E_INVOICE_GEN_IRN = E_INVOICE_BASE_URL + E_INVOICE_VERSION + "/einv/gen-irn";
    public static String E_INVOICE_CANCEL_IRN = E_INVOICE_BASE_URL + E_INVOICE_VERSION + "/einv/can-irn";
    public static String E_INVOICE_GET_IRN = E_INVOICE_BASE_URL +  E_INVOICE_VERSION + "/einv/get-irn";

    public static String E_WAYBILL_GEN = E_INVOICE_BASE_URL + E_INVOICE_VERSION + "/einv/gen-ewb-irn";
    public static String E_WAYBILL_CANCEL = E_INVOICE_BASE_URL + E_INVOICE_VERSION + "/einv/can-ewb";

    //e-invoice-v2
    //public static String E_INVOICE_V2_BASE_URL = "https://test.proteangsp.co.in/gus/irp/nic";
    public static String E_INVOICE_V2_BASE_URL = "https://test.proteangsp.co.in/gus/irp/nic";
    public static String E_INVOICE_V2_GET_KEY = E_INVOICE_V2_BASE_URL + "/GSPUtility/getKey";
    public static String E_INVOICE_V2_AUTH_TOKEN = E_INVOICE_V2_BASE_URL + "/eivital/v1.04/auth";
    public static String E_INVOICE_V2_GEN_IRN = E_INVOICE_V2_BASE_URL  + "/eicore/v1.03/Invoice";
    public static String E_INVOICE_V2_CANCEL_IRN = E_INVOICE_V2_BASE_URL  + "/eicore/v1.03/Invoice/Cancel";
    public static String E_INVOICE_V2_GET_IRN = E_INVOICE_V2_BASE_URL  + "/eicore/v1.03/Invoice/irn";

    public static String E_WAYBILL_V2_GEN = E_INVOICE_V2_BASE_URL  + "/eiewb/v1.03/ewaybill";
    public static String E_WAYBILL_V2_GET = E_INVOICE_V2_BASE_URL  + "/eiewb/v1.03/ewaybill/irn/";
    public static String E_WAYBILL_V2_CANCEL = E_INVOICE_V2_BASE_URL  + "/ewaybillapi/v1.03/ewayapi";

    //    Bill detail log
    public static String BILL_DETAIL_LOG = APP_CONTEXT_PATH + "accounts/reciptdetaillog";


    //      Update Password
    public static String UPDATE_PASSWORD = APP_CONTEXT_PATH + "entity/update-password/id/{id}/password/{password}";



    /*<-------------------------------------------Product ------------------------------------------------->*/

    //Transport Type
    public static String TRANSPORT_TYPE_SHOW = APP_CONTEXT_PATH + "shipments/transporttype";
    public static String TRANSPORT_TYPE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "shipments/transporttype/entity";
    public static String TRANSPORT_TYPE_SAVE = APP_CONTEXT_PATH + "shipments/transporttype";
    public static String TRANSPORT_TYPE_DATATABLE = APP_CONTEXT_PATH + "shipments/transporttype/datatable";
    public static String TRANSPORT_TYPE_UPDATE = APP_CONTEXT_PATH + "shipments/transporttype/{id}";
    public static String TRANSPORT_TYPE_DELETE = APP_CONTEXT_PATH + "shipments/transporttype/{id}";

    //Vechicle Detail
    public static String VECHILE_DETAIL_SHOW = APP_CONTEXT_PATH + "shipments/vehicledetail";
    public static String VECHILE_DETAIL_SAVE = APP_CONTEXT_PATH + "shipments/vehicledetail";
    public static String VECHILE_DETAIL_DATATABLE = APP_CONTEXT_PATH + "shipments/vehicledetail/datatable";
    public static String VECHILE_DETAIL_UPDATE = APP_CONTEXT_PATH + "shipments/vehicledetail/{id}";
    public static String VECHILE_DETAIL_DELETE = APP_CONTEXT_PATH + "shipments/vehicledetail/{id}";

    //Transporter
    public static String TRANSPORTER_TYPE_SHOW = APP_CONTEXT_PATH + "shipments/transporter";
    public static String TRANSPORTER_TYPE_SHOW_BY_ENTITY = APP_CONTEXT_PATH + "shipments/transporter/entity";
    public static String TRANSPORTER_TYPE_SAVE = APP_CONTEXT_PATH + "shipments/transporter";
    public static String TRANSPORTER_TYPE_DATATABLE = APP_CONTEXT_PATH + "shipments/transporter/datatable";
    public static String TRANSPORTER_TYPE_UPDATE = APP_CONTEXT_PATH + "shipments/transporter/{id}";
    public static String TRANSPORTER_TYPE_DELETE = APP_CONTEXT_PATH + "shipments/transporter/{id}";


    //    Sample Conversion logs
    public static String SAMPLE_CONVERSION_LOGS = APP_CONTEXT_PATH + "sales/sampleconversionlogs";
    public static String SAMPLE_CONVERSION_LOGS_DATATABLE = APP_CONTEXT_PATH + "sales/sampleconversionlogs/datatable";
    public static String SAMPLE_CONVERSION_LOGS_GET = APP_CONTEXT_PATH + "sales/sampleconversionlogs/get";


    public static String SAMPLE_CONVERSION = APP_CONTEXT_PATH + "sales/sampleconversiondetailsbydaterange";

    //    Sample invoicing
    public static String SAMPLE_INVOICE_SAVE = APP_CONTEXT_PATH + "sales//sampleconversion/save-invoice";
    public static String SAMPLE_INVOICE_RECENT = APP_CONTEXT_PATH + "sales/sampleconversion/getrecent";
    public static String SAMPLE_INVOICE_SHOW = APP_CONTEXT_PATH + "sales/sampleconversion";
    public static String SAMPLE_INVOICE_OF_BILL = APP_CONTEXT_PATH + "sales/sampleconversiondetails/bill";
    public static String SAMPLE_INVOICE_DATATABLE = APP_CONTEXT_PATH + "sales/sampleconversion/datatable";
    public static String SAMPLE_INVOICE_CANCEL = APP_CONTEXT_PATH + "sales/sampleconversion/cancel";


    //   purchase transportation details
    public static String PURCHASE_TRANSPORTATION_SAVE = APP_CONTEXT_PATH + "purchase/transportationdetail";
    public static String PURCHASE_TRANSPORTATION_UPDATE = APP_CONTEXT_PATH + "purchase/transportationdetail/{id}";
    public static String PURCHASE_TRANSPORTATION_BY_BILL = APP_CONTEXT_PATH + "purchase/transportationdetail/billid";


    //Purchase Order Details
    public static String PURCHASE_ORDER_SAVE = APP_CONTEXT_PATH + "purchase/purchaseorder";
    public static String PURCHASE_ORDER_SHOW = APP_CONTEXT_PATH + "purchase/purchaseorder";
    public static String PURCHASE_ORDER_DATATABLE = APP_CONTEXT_PATH + "purchase/purchaseorder/datatable";
    public static String PURCHASE_ORDER_RECENT = APP_CONTEXT_PATH + "purchase/purchaseorder/getrecent";
    public static String PURCHASE_ORDER_CANCEL = APP_CONTEXT_PATH + "purchase/purchaseorder/cancel";
    public static String PURCHASE_ORDER_DATERANGE = APP_CONTEXT_PATH + "purchase/purchaseorderbydaterange";
    public static String PURCHASE_ORDER_DATERANGE_SUPPLIER = APP_CONTEXT_PATH + "purchase" + "/purchaseorderbydaterangesupplier";


    //    Purchase Order Product Details
    public static String PURCHASE_ORDER_PRODUCT_SHOW = APP_CONTEXT_PATH + "purchase/orderproductdetail";
    public static String PURCHASE_ORDER_PRODUCT_SAVE = APP_CONTEXT_PATH + "purchase/orderproductdetail";
    public static String PURCHASE_ORDER_PRODUCT_DELETE = APP_CONTEXT_PATH + "purchase/orderproductdetail/{id}";
    public static String PURCHASE_ORDER_PRODUCT_OF_BILL = APP_CONTEXT_PATH + "purchase/orderproductdetail/bill";


    public static String ENTITY_DOMAIN_TYPE = APP_CONTEXT_PATH + "entity/entity-domain-type";

    //    save day end logs
    public static String SAVE_DAY_END_LOGS = APP_CONTEXT_PATH + "entity/day-end-logs";


    public static String BULK_SAVE_PRODUCT_DETAIL = APP_CONTEXT_PATH + "product/save-bulk-product-register";
    public static String BULK_SAVE_BATCH_DETAIL = APP_CONTEXT_PATH + "product/save-bulk-batch-register";
    public static String BULK_SAVE_STOCK_DETAIL = APP_CONTEXT_PATH + "inventory/save-bulk-stocks";


//    CrDb Settlement
    public static String SAVE_CRDB_SETTLEMENT = APP_CONTEXT_PATH + "sales/save-crdb-settlement";
    public static String GET_CRDB_DETAILS_BY_ID = APP_CONTEXT_PATH +"sales/getcrdbsettlement";
    public static String CRDB_DATATABLE = APP_CONTEXT_PATH +"sales/creditdebitsettlement/datatable";

//    Update mass Discount
    public static String UPDATE_MASS_DISCOUNT = APP_CONTEXT_PATH +"sales/updatemassdiscount";

//    Retailer

    public static String CHECK_EXISTING_PHONE = APP_CONTEXT_PATH +"entity/check-phone-exists";

    public static String SAVE_RETAILER_SALE_ENTRY = APP_CONTEXT_PATH+"sales/save-retailer-entry";

    public static String REGISTER_PATIENT = APP_CONTEXT_PATH+"entity/registerPatient";


//    payment collection
    public static String SAVE_PAYMENT_COLLECTION = APP_CONTEXT_PATH +"accounts/payment-collection/save";
    public static String PAYMENT_COLLECTION_DATATABLE = APP_CONTEXT_PATH +"accounts/payment-collection/datatable";
    public static String PAYMENT_COLLECTION_CHANGE_STATUS = APP_CONTEXT_PATH +"accounts/payment-collection/change-status";
    public static String PAYMENT_COLLECTION_APPROVE_ALL = APP_CONTEXT_PATH +"accounts/payment-collection/approve-all";
    public static String PAYMENT_COLLECTION_CANCEL_RECEIPT = APP_CONTEXT_PATH +"accounts/payment-collection/cancel-receipt";
    public static String PAYMENT_COLLECTION_BULK_UPDATE = APP_CONTEXT_PATH +"accounts/payment-collection/update-bulk-payments";

    public static String PAYMENT_COLLECTION_RECEIPTID = APP_CONTEXT_PATH +"accounts/payment-collection/receiptId";


    public static String PURCHASE_PRODUCT_DEL_CHECK = APP_CONTEXT_PATH +"purchase/product-del-check";
    public static String PURCHASE_BATCH_DEL_CHECK = APP_CONTEXT_PATH +"purchase/batch-del-check";

    public static String SALES_PRODUCT_DEL_CHECK = APP_CONTEXT_PATH +"sales/product-del-check";
    public static String SALES_BATCH_DEL_CHECK = APP_CONTEXT_PATH +"sales/batch-del-check";

    //Files Upload
    public static String FILE_UPLOAD = APP_CONTEXT_PATH +"files/upload";
    public static String FILE_DOWNLOAD = APP_CONTEXT_PATH +"files/download";
    public static String FILE_DELETE = APP_CONTEXT_PATH +"files/delete";

    public static String FILE_UPLOAD_TO_FTP = APP_CONTEXT_PATH +"files/canvasimageuploadtoftp";





}