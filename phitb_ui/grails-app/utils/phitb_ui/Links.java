package phitb_ui;

public class Links
{
    public static String API_GATEWAY = new Constants().API_GATEWAY;

    /*<-------------------------------------------System------------------------------------------------->*/

    //    Account modes
    public static String ACCOUNT_MODES_SHOW = "api/v1.0/system/accountmodes";
    public static String ACCOUNT_MODES_SAVE = "api/v1.0/system/accountmodes";
    public static String ACCOUNT_MODES_DATATABLE = "api/v1.0/system/accountmodes/datatable";
    public static String ACCOUNT_MODES_UPDATE = "api/v1.0/system/accountmodes/{id}";
    public static String ACCOUNT_MODES_DELETE = "api/v1.0/system/accountmodes/{id}";

//    Status
    public static String SYSTEM_SERVICE_STATUS = "api/v1.0/system/status";
    public static String SHIPMENT_SERVICE_STATUS = "api/v1.0/shipments/status";
    public static String SALES_SERVICE_STATUS = "api/v1.0/sales/status";
    public static String PURCHASE_SERVICE_STATUS = "api/v1.0/purchase/status";
    public static String PRODUCT_SERVICE_STATUS = "api/v1.0/product/status";
    public static String INVENTORY_SERVICE_STATUS = "api/v1.0/inventory/status";
    public static String FACILITY_SERVICE_STATUS = "api/v1.0/facility/status";
    public static String ENTITY_SERVICE_STATUS = "api/v1.0/entity/status";
    public static String ACCOUNTS_SERVICE_STATUS = "api/v1.0/accounts/status";

    //    Gender Master
    public static String GENDER_MASTER_SHOW = "api/v1.0/system/gender";


    //    Payment Mode show
    public static String PAYMENT_MODE_SHOW = "api/v1.0/system/paymentmode";


    //    Terms Condtions
    public static String TERMS_CONDITIONS = "api/v1.0/entity/termconditiondetailsbyentity";


    //    State Master
    public static String STATE_MASTER_SAVE = "api/v1.0/system/state";
    public static String STATE_MASTER_SHOW = "api/v1.0/system/state";
    public static String STATE_MASTER_DATATABLE = "api/v1.0/system/state/datatable";
    public static String STATE_MASTER_UPDATE = "api/v1.0/system/state/{id}";
    public static String STATE_MASTER_DELETE = "api/v1.0/system/state/{id}";

    //Zone Master
    public static String ZONE_MASTER_SHOW = "api/v1.0/system/zone";


    //Country Master
    public static String COUNTRY_MASTER_SHOW = "api/v1.0/system/country";
    public static String COUNTRY_MASTER_SAVE = "api/v1.0/system/country";
    public static String COUNTRY_MASTER_DATATABLE = "api/v1.0/system/country/datatable";
    public static String COUNTRY_MASTER_UPDATE = "api/v1.0/system/country/{id}";
    public static String COUNTRY_MASTER_DELETE = "api/v1.0/system/country/{id}";


    //Form Master
    public static String FORM_MASTER_SHOW = "api/v1.0/system/form";
    public static String FORM_MASTER_SAVE = "api/v1.0/system/form";
    public static String FORM_MASTER_DATATABLE = "api/v1.0/system/form/datatable";
    public static String FORM_MASTER_UPDATE = "api/v1.0/system/form/{id}";
    public static String FORM_MASTER_DELETE = "api/v1.0/system/form/{id}";


    //    City Master
    public static String CITY_MASTER_SAVE = "api/v1.0/system/city";
    public static String CITY_MASTER_SHOW = "api/v1.0/system/city";
    public static String CITY_MASTER_DATATABLE = "api/v1.0/system/city/datatable";
    public static String CITY_MASTER_UPDATE = "api/v1.0/system/city/{id}";
    public static String CITY_MASTER_DELETE = "api/v1.0/system/city/{id}";
    public static String GET_CITY_BY_PINCODE = "api/v1.0/system/getcitybypincode";


    //    Priorty Master
    public static String PRIORITY_SAVE = "api/v1.0/system/priority";
    public static String PRIORITY_SHOW = "api/v1.0/system/priority";
    public static String PRIORITY_DATATABLE = "api/v1.0/system/priority/datatable";
    public static String PRIORITY_UPDATE = "api/v1.0/system/priority/{id}";
    public static String PRIORITY_DELETE = "api/v1.0/system/priority/{id}";
    public static String PRIORITY_BY_ENTITY = "api/v1.0/system/prioritybyentity";


    //    Region Master
    public static String REGION_SAVE = "api/v1.0/system/region";
    public static String REGION_SHOW = "api/v1.0/system/region";
    public static String REGION_DATATABLE = "api/v1.0/system/region/datatable";
    public static String REGION_UPDATE = "api/v1.0/system/region/{id}";
    public static String REGION_DELETE = "api/v1.0/system/region/{id}";


    //    Division Master
    public static String DIVISION_MASTER_SAVE = "api/v1.0/system/division";
    public static String DIVISION_MASTER_SHOW = "api/v1.0/system/division";
    public static String DIVISION_MASTER_DATATABLE = "api/v1.0/system/division/datatable";
    public static String DIVISION_MASTER_UPDATE = "api/v1.0/system/division/{id}";
    public static String DIVISION_MASTER_DELETE = "api/v1.0/system/division/{id}";


    //    District Master
    public static String DISTRICT_MASTER_SAVE = "api/v1.0/system/district";
    public static String DISTRICT_MASTER_SHOW = "api/v1.0/system/district";
    public static String DISTRICT_MASTER_DATATABLE = "api/v1.0/system/district/datatable";
    public static String DISTRICT_MASTER_UPDATE = "api/v1.0/system/district/{id}";
    public static String DISTRICT_MASTER_DELETE = "api/v1.0/system/district/{id}";


    /*<-------------------------------------------Facility ------------------------------------------------->*/


    //Fridge
    public static String FRIDGE_SHOW = "api/v1.0/facility/fridge";
    public static String FRIDGE_SAVE = "api/v1.0/facility/fridge";
    public static String FRIDGE_DATATABLE = "api/v1.0/facility/fridge/datatable";
    public static String FRIDGE_UPDATE = "api/v1.0/facility/fridge/{id}";
    public static String FRIDGE_DELETE = "api/v1.0/facility/fridge/{id}";

    //CCM
    public static String CCM_SHOW = "api/v1.0/facility/ccmregister";
    public static String CCM_SAVE = "api/v1.0/facility/ccmregister";
    public static String CCM_DATATABLE = "api/v1.0/facility/ccmregister/datatable";
    public static String CCM_UPDATE = "api/v1.0/facility/ccmregister/{id}";
    public static String CCM_DELETE = "api/v1.0/facility/ccmregister/{id}";

    //godown
    public static String GODOWN_SHOW = "api/v1.0/facility/godown";
    public static String GODOWN_SAVE = "api/v1.0/facility/godown";
    public static String GODOWN_DATATABLE = "api/v1.0/facility/godown/datatable";
    public static String GODOWN_UPDATE = "api/v1.0/facility/godown/{id}";
    public static String GODOWN_DELETE = "api/v1.0/facility/godown/{id}";

    //rack
    public static String RACK_SHOW = "api/v1.0/facility/rack";
    public static String RACK_SHOW_BY_ENTITY = "api/v1.0/facility/rackbyentity";
    public static String RACK_SAVE = "api/v1.0/facility/rack";
    public static String RACK_DATATABLE = "api/v1.0/facility/rack/datatable";
    public static String RACK_UPDATE = "api/v1.0/facility/rack/{id}";
    public static String RACK_DELETE = "api/v1.0/facility/rack/{id}";

    /*<-------------------------------------------Entity ------------------------------------------------->*/

    //Entity IRN
    public static String ENTITY_IRN_SHOW = "api/v1.0/entity/entityirn";
    public static String ENTITY_IRN_SAVE = "api/v1.0/entity/entityirn";
    public static String ENTITY_IRN_DATATABLE = "api/v1.0/entity/entityirn/datatable";
    public static String ENTITY_IRN_UPDATE = "api/v1.0/entity/entityirn/{id}";
    public static String ENTITY_IRN_DELETE = "api/v1.0/entity/entityirn/{id}";
    public static String ENTITY_IRN_SHOW_BY_ENTITY = "api/v1.0/entity//entityirn/entity/";

    public static String ENTITY_AUTH = "api/v1.0/entity/authregister/username";

    public static String ENTITY_TYPE_MASTER_SHOW = "api/v1.0/entity/entitytypemaster";
    public static String DEPARTMENT_MASTER_SHOW = "api/v1.0/entity/departmentmaster";
    public static String DIVISION_SHOW = "api/v1.0/product/division";
    public static String DIVISION_BY_ENTITY = "api/v1.0/product/divisionbyentity";
    public static String GENDER_SHOW = "api/v1.0/system/gender";

    //Entity Register
    public static String ENTITY_REGISTER_SHOW = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_SHOW_BY_ENTITY = "api/v1.0/entity/entityregister/getbyentity";
    public static String ENTITY_REGISTER_SAVE = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_DATATABLE = "api/v1.0/entity/entityregister/datatable";
    public static String ENTITY_REGISTER_UPDATE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_DELETE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_AFFILIATE = "api/v1.0/entity/entityregister/affiliate";


    //    Entity Settings
    public static String ENTITY_SETTINGS_SHOW = "api/v1.0/entity/entitysetting";
    public static String ENTITY_SETTINGS_UPDATE = "api/v1.0/entity/entitysetting/{id}";
    public static String ENTITY_SETTINGS_DELETE = "api/v1.0/entity/entitysetting/{id}";
    public static String ENTITY_SETTINGS_SAVE = "api/v1.0/entity/entitysetting";
    public static String ENTITY_SETTINGS_DATATABLE = "api/v1.0/entity/entitysetting/datatable";
    public static String ENTITY_SETTINGS_BY_ENTITY = "api/v1.0/entity/entitysetting/getbyentityid";

//    Entity Config
    public static String ENTITY_CONFIG_SAVE = "api/v1.0/entity/entityconfig";
    public static String ENTITY_CONFIG_BY_ENTITY = "api/v1.0/entity/entityconfig/getbyentityid";


    //Account Register
    public static String ACCOUNT_REGISTER_SHOW = "api/v1.0/entity/accountregister";
    public static String ACCOUNT_REGISTER_SAVE = "api/v1.0/entity/accountregister";
    public static String ACCOUNT_REGISTER_DATATABLE = "api/v1.0/entity/accountregister/datatable";
    public static String ACCOUNT_REGISTER_UPDATE = "api/v1.0/entity/accountregister/{id}";
    public static String ACCOUNT_REGISTER_DELETE = "api/v1.0/entity/accountregister/{id}";
    public static String ACCOUNT_REGISTER_AFFILIATE = "api/v1.0/entity/accountregister/affiliate";
    public static String ACCOUNT_REGISTER_BY_ENTITY = "api/v1.0/entity/accountregister/entity";
    public static String ACCOUNT_REGISTER_UPDATE_BALANCE = "api/v1.0/entity/accountregister/updatebalance";

    //Account Type
    public static String ACCOUNT_TYPE_SAVE = "api/v1.0/system/accounttype";
    public static String ACCOUNT_TYPE_SHOW = "api/v1.0/system/accounttype";
    public static String ACCOUNT_TYPE_DATATABLE = "api/v1.0/system/accounttype/datatable";
    public static String ACCOUNT_TYPE_UPDATE = "api/v1.0/system/accounttype/{id}";
    public static String ACCOUNT_TYPE_DELETE = "api/v1.0/system/accounttype/{id}";
    public static String ACCOUNT_TYPE_BY_ENTITY = "api/v1.0/system/accounttypebyentity";

    //User Register
    public static String USER_REGISTER_SHOW = "api/v1.0/entity/userregister";
    public static String USER_REGISTER_SHOW_BY_ENTITY = "api/v1.0/entity/userregisterbyentity";
    public static String USER_REGISTER_SAVE = "api/v1.0/entity/userregister";
    public static String USER_REGISTER_DATATABLE = "api/v1.0/entity/userregister/datatable";
    public static String USER_REGISTER_UPDATE = "api/v1.0/entity/userregister/{id}";
    public static String USER_REGISTER_DELETE = "api/v1.0/entity/userregister/{id}";
    public static String USER_EXISTS = "api/v1.0/entity/userregister/usernameexists";



    //Customer Group Register
    public static String CUSTOMER_GROUP_REGISTER_SHOW = "api/v1.0/entity/customergroupregister";
    public static String CUSTOMER_GROUP_REGISTER_SHOW_BY_ENTITY = "api/v1.0/entity/customergroupregisterbyentity";
    public static String CUSTOMER_GROUP_REGISTER_SAVE = "api/v1.0/entity/customergroupregister";
    public static String CUSTOMER_GROUP_REGISTER_DATATABLE = "api/v1.0/entity/customergroupregister/datatable";
    public static String CUSTOMER_GROUP_REGISTER_UPDATE = "api/v1.0/entity/customergroupregister/{id}";
    public static String CUSTOMER_GROUP_REGISTER_DELETE = "api/v1.0/entity/customergroupregister/{id}";

    //Day End Master
    public static String DAY_END_MASTER_SHOW = "api/v1.0/entity/dayendmaster";
    public static String DAY_END_MASTER_SAVE = "api/v1.0/entity/dayendmaster";
    public static String DAY_END_MASTER_DATATABLE = "api/v1.0/entity/dayendmaster/datatable";
    public static String DAY_END_MASTER_UPDATE = "api/v1.0/entity/dayendmaster/{id}";
    public static String DAY_END_MASTER_DELETE = "api/v1.0/entity/dayendmaster/{id}";

    //Day End Master
    public static String HQ_AREA_SHOW = "api/v1.0/entity/hqareas";
    public static String HQ_AREA_SAVE = "api/v1.0/entity/hqareas";
    public static String HQ_AREA_DATATABLE = "api/v1.0/entity/hqareas/datatable";
    public static String HQ_AREA_UPDATE = "api/v1.0/entity/hqareas/{id}";
    public static String HQ_AREA_DELETE = "api/v1.0/entity/hqareas/{id}";
    public static String HQ_AREA_SHOW_BY_ENTITY = "api/v1.0/entity/hqbyentity";



    //Financial Year master
    public static String FINANCIAL_YEAR_SHOW = "api/v1.0/entity/financialyearmaster";
    public static String FINANCIAL_YEAR_SAVE = "api/v1.0/entity/financialyearmaster";
    public static String FINANCIAL_YEAR_DATATABLE = "api/v1.0/entity/financialyearmaster/datatable";
    public static String FINANCIAL_YEAR_UPDATE = "api/v1.0/entity/financialyearmaster/{id}";
    public static String FINANCIAL_YEAR_DELETE = "api/v1.0/entity/financialyearmaster/{id}";
    public static String FINANCIAL_YEAR_ENTITY = "api/v1.0/entity/financialyearmasterbyentity";


    //Region Register
    public static String REGION_REGISTER_SHOW = "api/v1.0/entity/regionregister";
    public static String REGION_REGISTER_SAVE = "api/v1.0/entity/regionregister";
    public static String REGION_REGISTER_DATATABLE = "api/v1.0/entity/regionregister/datatable";
    public static String REGION_REGISTER_UPDATE = "api/v1.0/entity/regionregister/{id}";
    public static String REGION_REGISTER_DELETE = "api/v1.0/entity/regionregister/{id}";

    //Route Register
    public static String ROUTE_REGISTER_SHOW = "api/v1.0/entity/routeregister";
    public static String ROUTE_REGISTER_SAVE = "api/v1.0/entity/routeregister";
    public static String ROUTE_REGISTER_DATATABLE = "api/v1.0/entity/routeregister/datatable";
    public static String ROUTE_REGISTER_UPDATE = "api/v1.0/entity/routeregister/{id}";
    public static String ROUTE_REGISTER_DELETE = "api/v1.0/entity/routeregister/{id}";

    //Role Master
    public static String ROLE_MASTER_SHOW = "api/v1.0/entity/rolemaster";
    public static String ROLE_MASTER_SAVE = "api/v1.0/entity/rolemaster";
    public static String ROLE_MASTER_DATATABLE = "api/v1.0/entity/rolemaster/datatable";
    public static String ROLE_MASTER_UPDATE = "api/v1.0/entity/rolemaster/{id}";
    public static String ROLE_MASTER_DELETE = "api/v1.0/entity/rolemaster/{id}";

    //Role
    public static String ROLE_SHOW = "api/v1.0/entity/role";
    public static String ROLE_SAVE = "api/v1.0/entity/role";
    public static String ROLE_DATATABLE = "api/v1.0/entity/role/datatable";
    public static String ROLE_UPDATE = "api/v1.0/entity/role/{id}";
    public static String ROLE_DELETE = "api/v1.0/entity/role/{id}";

    //feature
    public static String FEATURE_SHOW = "api/v1.0/entity/role/features";


    //Rule Master
    public static String RULE_MASTER_SHOW = "api/v1.0/entity/rulemaster";
    public static String RULE_MASTER_SAVE = "api/v1.0/entity/rulemaster";
    public static String RULE_MASTER_DATATABLE = "api/v1.0/entity/rulemaster/datatable";
    public static String RULE_MASTER_UPDATE = "api/v1.0/entity/rulemaster/{id}";
    public static String RULE_MASTER_DELETE = "api/v1.0/entity/rulemaster/{id}";

    //Tax Register
    public static String TAX_MASTER_SHOW = "api/v1.0/entity/taxregister";
    public static String TAX_MASTER_SAVE = "api/v1.0/entity/taxregister";
    public static String TAX_MASTER_DATATABLE = "api/v1.0/entity/taxregister/datatable";
    public static String TAX_MASTER_UPDATE = "api/v1.0/entity/taxregister/{id}";
    public static String TAX_MASTER_DELETE = "api/v1.0/entity/taxregister/{id}";


    //Territory
    public static String TERRITORY_MASTER_SHOW = "api/v1.0/entity/territoryregister";
    public static String TERRITORY_MASTER_SAVE = "api/v1.0/entity/territoryregister";
    public static String TERRITORY_MASTER_DATATABLE = "api/v1.0/entity/territoryregister/datatable";
    public static String TERRITORY_MASTER_UPDATE = "api/v1.0/entity/territoryregister/{id}";
    public static String TERRITORY_MASTER_DELETE = "api/v1.0/entity/territoryregister/{id}";

    //Terms and Conditions
    public static String TC_MASTER_SHOW = "api/v1.0/entity/termconditiondetails";
    public static String TC_MASTER_SAVE = "api/v1.0/entity/termconditiondetails";
    public static String TC_MASTER_DATATABLE = "api/v1.0/entity/termconditiondetails/datatable";
    public static String TC_MASTER_UPDATE = "api/v1.0/entity/termconditiondetails/{id}";
    public static String TC_MASTER_DELETE = "api/v1.0/entity/termconditiondetails/{id}";

    //Series Master
    public static String SERIES_MASTER_SHOW = "api/v1.0/entity/seriesmaster";
    public static String SERIES_MASTER_BY_ENTITY = "api/v1.0/entity/seriesmasterbyentity";
    public static String SERIES_MASTER_SAVE = "api/v1.0/entity/seriesmaster";
    public static String SERIES_MASTER_DATATABLE = "api/v1.0/entity/seriesmaster/datatable";
    public static String SERIES_MASTER_UPDATE = "api/v1.0/entity/seriesmaster/{id}";
    public static String SERIES_MASTER_DELETE = "api/v1.0/entity/seriesmaster/{id}";


    //Service Type Master
    public static String SERVICE_TYPE_SHOW = "api/v1.0/entity/servicetype";
    public static String SERVICE_TYPE_SAVE = "api/v1.0/entity/servicetype";
    public static String SERVICE_TYPE_DATATABLE = "api/v1.0/entity/servicetype/datatable";
    public static String SERVICE_TYPE_UPDATE = "api/v1.0/entity/servicetype/{id}";
    public static String SERVICE_TYPE_DELETE = "api/v1.0/entity/servicetype/{id}";



    /*<-------------------------------------------Product ------------------------------------------------->*/
    //Product Register
    public static String PRODUCT_REGISTER_SHOW = "api/v1.0/product/productregister";
    public static String PRODUCT_REGISTER_BY_ENTITY = "api/v1.0/product/productregisterbyentity";
    public static String PRODUCT_REGISTER_BY_DIVISION = "api/v1.0/product/productregisterbydivision";
    public static String PRODUCT_REGISTER_SAVE = "api/v1.0/product/productregister";
    public static String PRODUCT_REGISTER_DATATABLE = "api/v1.0/product/productregister/datatable";
    public static String PRODUCT_REGISTER_UPDATE = "api/v1.0/product/productregister/{id}";
    public static String PRODUCT_REGISTER_DELETE = "api/v1.0/product/productregister/{id}";

    //Product Type
    public static String PRODUCT_TYPE_SHOW = "api/v1.0/product/producttypemaster";
    public static String PRODUCT_TYPE_SHOW_BY_ENTITY = "api/v1.0/product/producttypemasterbyentity";
    public static String PRODUCT_TYPE_SAVE = "api/v1.0/product/producttypemaster";
    public static String PRODUCT_TYPE_DATATABLE = "api/v1.0/product/producttypemaster/datatable";
    public static String PRODUCT_TYPE_UPDATE = "api/v1.0/product/producttypemaster/{id}";
    public static String PRODUCT_TYPE_DELETE = "api/v1.0/product/producttypemaster/{id}";

    //Product Group
    public static String PRODUCT_GROUP_SHOW = "api/v1.0/product/productgroupmaster";
    public static String PRODUCT_GROUP_SHOW_BY_ENTITY = "api/v1.0/product/productgroupmasterbyentity";
    public static String PRODUCT_GROUP_SAVE = "api/v1.0/product/productgroupmaster";
    public static String PRODUCT_GROUP_DATATABLE = "api/v1.0/product/productgroupmaster/datatable";
    public static String PRODUCT_GROUP_UPDATE = "api/v1.0/product/productgroupmaster/{id}";
    public static String PRODUCT_GROUP_DELETE = "api/v1.0/product/productgroupmaster/{id}";

    //unit type
    public static String UNIT_TYPE_SHOW = "api/v1.0/product/unittypemaster";
    public static String UNIT_TYPE_SHOW_BY_ENTITY = "api/v1.0/product/unittypemasterbyentity";
    public static String UNIT_TYPE_SAVE = "api/v1.0/product/unittypemaster";
    public static String UNIT_TYPE_DATATABLE = "api/v1.0/product/unittypemaster/datatable";
    public static String UNIT_TYPE_UPDATE = "api/v1.0/product/unittypemaster/{id}";
    public static String UNIT_TYPE_DELETE = "api/v1.0/product/unittypemaster/{id}";

    //division
    public static String DIVISION_SAVE = "api/v1.0/product/division";
    public static String DIVISION_DATATABLE = "api/v1.0/product/division/datatable";
    public static String DIVISION_UPDATE = "api/v1.0/product/division/{id}";
    public static String DIVISION_DELETE = "api/v1.0/product/division/{id}";

    //division group
    public static String DIVISION_GROUP_SAVE = "api/v1.0/product/divisiongroupregister";
    public static String DIVISION_GROUP_DATATABLE = "api/v1.0/product/divisiongroupregister/datatable";
    public static String DIVISION_GROUP_UPDATE = "api/v1.0/product/divisiongroupregister/{id}";
    public static String DIVISION_GROUP_DELETE = "api/v1.0/product/divisiongroupregister/{id}";

    //Product Category
    public static String PRODUCT_CATEGORY_SHOW = "api/v1.0/product/productcategorymaster";
    public static String PRODUCT_CATEGORY_BY_ENTITY_SHOW = "api/v1.0/product/productcategorymasterbyentity";
    public static String PRODUCT_CATEGORY_SAVE = "api/v1.0/product/productcategorymaster";
    public static String PRODUCT_CATEGORY_DATATABLE = "api/v1.0/product/productcategorymaster/datatable";
    public static String PRODUCT_CATEGORY_UPDATE = "api/v1.0/product/productcategorymaster/{id}";
    public static String PRODUCT_CATEGORY_DELETE = "api/v1.0/product/productcategorymaster/{id}";

    //Product Schedule
    public static String PRODUCT_SCHEDULE_SHOW = "api/v1.0/product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_SHOW_BY_ENTITY = "api/v1.0/product/productschdulemasterbyentity";
    public static String PRODUCT_SCHEDULE_SAVE = "api/v1.0/product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_DATATABLE = "api/v1.0/product/productschdulemaster/datatable";
    public static String PRODUCT_SCHEDULE_UPDATE = "api/v1.0/product/productschdulemaster/{id}";
    public static String PRODUCT_SCHEDULE_DELETE = "api/v1.0/product/productschdulemaster/{id}";

    //Product Composition
    public static String PRODUCT_COMPOSITION_SHOW = "api/v1.0/product/compositionmasterregister";
    public static String PRODUCT_COMPOSITION_SHOW_BY_ENTITY = "api/v1.0/product/composition/getbyentity";
    public static String PRODUCT_COMPOSITION_SAVE = "api/v1.0/product/compositionmasterregister";
    public static String PRODUCT_COMPOSITION_DATATABLE = "api/v1.0/product/compositionmasterregister/datatable";
    public static String PRODUCT_COMPOSITION_UPDATE = "api/v1.0/product/compositionmasterregister/{id}";
    public static String PRODUCT_COMPOSITION_DELETE = "api/v1.0/product/compositionmasterregister/{id}";

    //Product Class
    public static String PRODUCT_CLASS_SHOW = "api/v1.0/product/productclass";
    public static String PRODUCT_CLASS_SAVE = "api/v1.0/product/productclass";
    public static String PRODUCT_CLASS_DATATABLE = "api/v1.0/product/productclass/datatable";
    public static String PRODUCT_CLASS_UPDATE = "api/v1.0/product/productclass/{id}";
    public static String PRODUCT_CLASS_DELETE = "api/v1.0/product/productclass/{id}";

    //Product Cost
    public static String PRODUCT_COST_SHOW = "api/v1.0/product/productcostrange";
    public static String PRODUCT_COST_SHOW_BY_ENTITY = "api/v1.0/product/productcostrangebyentity";
    public static String PRODUCT_COST_SAVE = "api/v1.0/product/productcostrange";
    public static String PRODUCT_COST_DATATABLE = "api/v1.0/product/productcostrange/datatable";
    public static String PRODUCT_COST_UPDATE = "api/v1.0/product/productcostrange/{id}";
    public static String PRODUCT_COST_DELETE = "api/v1.0/product/productcostrange/{id}";

    //Batch Register
    public static String BATCH_REGISTER_SHOW = "api/v1.0/product/batchregister";
    public static String GET_BATCH_BY_PRODUCT = "api/v1.0/product/batchregisterbyproduct";
    public static String BATCH_REGISTER_SAVE = "api/v1.0/product/batchregister";
    public static String BATCH_REGISTER_DATATABLE = "api/v1.0/product/batchregister/datatable";
    public static String BATCH_REGISTER_UPDATE = "api/v1.0/product/batchregister/{id}";
    public static String BATCH_REGISTER_DELETE = "api/v1.0/product/batchregister/{id}";
    public static String GET_BY_BATCH_AND_PRODUCT = "api/v1.0/product/batch-and-product";

    /*<--------------------------------------------Sales------------------------------------------------->*/
    //Sale Bill Details
    public static String GET_REASON = "api/v1.0/sales/reasonmaster";

    //Sale Bill Details
    public static String SALE_BILL_SHOW = "api/v1.0/sales/salebilldetails";
    public static String SALE_BILL_BY_DATERANGE = "api/v1.0/sales/salebillbydaterange";
    public static String DRAFT_SALE_BILL_SHOW = "api/v1.0/sales/draftsalebilldetails";
    public static String SALE_BILL_BALANCE_UPDATE = "api/v1.0/sales/updatebalancebyid";
    public static String SALE_BILL_SAVE = "api/v1.0/sales/salebilldetails";
    public static String SALE_BILL_CANCEL = "api/v1.0/sales/salebilldetails/cancel";
    public static String SALE_BILL_DATATABLE = "api/v1.0/sales/salebilldetails/datatable";
    public static String SALE_BILL_UPDATE = "api/v1.0/sales/salebilldetails/{id}";
    public static String SALE_BILL_DELETE = "api/v1.0/sales/salebilldetails/{id}";
    public static String SALE_BILL_UNSETTLED = "api/v1.0/sales/salebillunsettledbycustomer";
    public static String SALE_BILL_SETTLED = "api/v1.0/sales/salebillsettledbycustomer";
    public static String SALE_BILL_PAYMENT = "api/v1.0/sales/salebillbypaymentstatus";
    public static String SET_PAYMENT_BILL = "api/v1.0/sales/setpaymentstatus/{id}/type/{type}";
    public static String SALE_BILL_RECENT = "api/v1.0/sales/salebilldetails/getrecent";
    public static String SALE_BILL_CUSTOMER = "api/v1.0/sales/salebillbycustomer/{custid}";
    public static String SALE_BILL_REPORTS = "api/v1.0/sales/reports/customerwise";
    public static String SALE_BILL_DATEWISE_REPORTS = "api/v1.0/sales/reports/datewise";
    public static String SALE_BILL_AREAWISE_REPORTS = "api/v1.0/sales/reports/areawise";
    public static String SALE_BILL_CONSOLIDATED_REPORTS = "api/v1.0/sales/reports/consolidated";
    public static String SALE_RETURN_AREAWISE = "api/v1.0/sales/reports/salesreturn-areawise";
    public static String SALE_BILL_UPDATE_IRN = "api/v1.0/sales/salebilldetails/updateirn";
    public static String SALE_RETURN_BY_DATERANGE = "api/v1.0/sales/salereturnbydaterange";

    public static String GET_OUTSTANDING_REPORT = "api/v1.0/accounts/reports/outstanding";

    public static String GST_SALES_REPORTS = "api/v1.0/sales/reports/salesgst";

    public static String SALE_SCHEME_UPDATE = "api/v1.0/sales/schemeconfiguration/{id}";
    public static String SALE_SCHEME_DELETE = "api/v1.0/sales/schemeconfiguration/{id}";
    public static String SALE_SCHEME_SAVE = "api/v1.0/sales/schemeconfiguration";
    public static String SALE_SCHEME_SHOW = "api/v1.0/sales/schemeconfiguration";
    public static String SALE_SCHEME_DATATABLE = "api/v1.0/sales/schemeconfiguration/datatable";

    //    Reason Master
    public static String REASON_UPDATE = "api/v1.0/sales/reasonmaster/{id}";
    public static String REASON_DELETE = "api/v1.0/sales/reasonmaster/{id}";
    public static String REASON_SAVE = "api/v1.0/sales/reasonmaster";
    public static String REASON_SHOW = "api/v1.0/sales/reasonmaster";
    public static String REASON_DATATABLE = "api/v1.0/sales/reasonmaster/datatable";


    public static String SALE_SCHEME_CONFIG_GET_PRODUCT_BATCH = "api/v1.0/sales/schemeconfiguration/product/$productId/batch/$batchNumber";


    public static String SALE_INVOICE_SAVE = "api/v1.0/sales/salebilldetails/save-invoice";
    public static String SALE_INVOICE_UPDATE = "api/v1.0/sales/salebilldetails/update-invoice";

    //Sale Product Details
    public static String SALE_PRODUCT_SHOW = "api/v1.0/sales/saleproductdetails";
    public static String SALE_PRODUCT_SAVE = "api/v1.0/sales/saleproductdetails";
    public static String SALE_PRODUCT_SAVE_LIST = "api/v1.0/sales/saleproductdetails/savelist";
    public static String SALE_PRODUCT_DATATABLE = "api/v1.0/sales/saleproductdetails/datatable";
    public static String SALE_PRODUCT_UPDATE = "api/v1.0/sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_DELETE = "api/v1.0/sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_OF_BILL = "api/v1.0/sales/saleproductdetails/bill";
    public static String SALE_PRODUCT_OF_BILLIDS = "api/v1.0/sales/saleproductdetailslist/bill/{salebillsIds}";
    public static String SALE_PRODUCT_BY_PRODUCT = "api/v1.0/sales/saleproductdetailsbyproductId";
    public static String SALE_PRODUCT_BILL_BATCH = "api/v1.0/sales/saleproductdetailsbillandbatch";


    //Sale Return
    public static String SALE_RETURN_SHOW = "api/v1.0/sales/salereturn";
    public static String SALE_RETURN_SAVE = "api/v1.0/sales/salereturn";
    public static String SALE_RETURN_DATATABLE = "api/v1.0/sales/salereturn/datatable";
    public static String SALE_RETURN_UNSETTLED = "api/v1.0/sales/saleretrununsettledbycustomer";
    public static String SALE_RETURN_SETTLED = "api/v1.0/sales/saleretrunsettledbycustomer";
    public static String SET_SALE_RETURN_STATUS = "api/v1.0/sales/setsalereturnstatus/{id}/type/{type}/adj/{adj}";
    public static String UPDATE_SALE_RETURN_BALANCE = "api/v1.0/sales/updatereturnbalancebyid";
    public static String SALE_RETURN_CUSTOMER = "api/v1.0/sales/salereturnbycustomer";
    public static String SALE_RETURN_RECENT = "api/v1.0/sales/salereturn/getrecent";
    public static String SALE_RETURN_PRODUCT_BATCH_BILL = "api/v1.0/sales/getsalereturndetailsby-product-batch-salebill";
    public static String SALE_RETURN_CANCEL = "api/v1.0/sales/salereturn/cancel";

    //Sale Return Details
    public static String SALE_RETURN_DETAIL_SHOW = "api/v1.0/sales/salereturndetails";
    public static String SALE_RETURN_DETAIL_SAVE = "api/v1.0/sales/salereturndetails";
    public static String SALE_RETURN_DETAIL_BILL = "api/v1.0/sales/salereturndetails/bill";


    //Sale Order Details
    public static String SALE_ORDER_SAVE = "api/v1.0/sales/saleorderdetails";
    public static String SALE_ORDER_SHOW = "api/v1.0/sales/saleorderdetails";
    public static String SALE_PRODUCT_ORDER = "api/v1.0/sales/saleorderproductdetails/bill";
    public static String SALE_ORDER_DATATABLE = "api/v1.0/sales/saleorderdetails/datatable";
    public static String SALE_ORDER_RECENT = "api/v1.0/sales/saleorderdetails/getrecent";
    public static String SALE_ORDER_CANCEL = "api/v1.0/sales/saleorderdetails/cancel";

//    public static String SALE_ORDER_DATATABLE = "api/v1.0/sales/saleproductdetails/datatable";
//    public static String SALE_ORDER_UPDATE = "api/v1.0/sales/saleproductdetails/{id}";
//    public static String SALE_ORDER_DELETE = "api/v1.0/sales/saleproductdetails/{id}";
//    public static String SALE_ORDER = "api/v1.0/sales/saleproductdetails";


//    Goods Transfer Note
    public static String GTN_SAVE = "api/v1.0/sales/gtn";
    public static String GTN_SHOW = "api/v1.0/sales/gtn";
    public static String GTN_DATATABLE = "api/v1.0/sales/gtn/datatable";
    public static String GTN_RECENT = "api/v1.0/sales/gtn/getrecent";
    public static String GTN_CANCEL = "api/v1.0/sales/gtn/cancel";
    public static String GTN_APPROVE = "api/v1.0/sales/gtn/approve";
    public static String GTN_CUSTOMER = "api/v1.0/sales/gtnbycustomer";
    public static String UPDATE_GTN_BALANCE = "api/v1.0/sales/updategtnbalancebyid";


    //    Goods transfer note products
    public static String GTN_PRODUCTS_SAVE = "api/v1.0/sales/gtnproduct";
    public static String GTN_PRODUCTS_BY_GTN = "api/v1.0/sales/gtnproduct/gtn";

    /*<--------------------------------------------Inventory ------------------------------------------------->*/

    //Stock book
    public static String STOCK_BOOK = "api/v1.0/inventory/stockbook";
    public static String STOCK_BOOK_BY_ENTITY = "api/v1.0/inventory/stockbookbyentity";
    public static String STOCK_BOOK_DATATABLE = "api/v1.0/inventory/stockbook/datatable";
    public static String GET_STOCKS_OF_PRODUCT = "api/v1.0/inventory/stockbookbyproduct";
    public static String GET_STOCKS_OF_PRODUCT_SALE_RETURN = "api/v1.0/inventory/stockbookbyproductsalereturn";
    public static String STOCK_BOOK_PURCHASE = "api/v1.0/inventory/stockbook/purchase/";
    public static String STOCK_BOOK_BY_USER = "api/v1.0/inventory/stockbook/user";
    public static String STOCK_BOOK_BY_BATCH = "api/v1.0/inventory/stockbook/batch";
    public static String STOCK_BOOK_BY_PROD_BATCH = "api/v1.0/inventory/stockbook/product/{productId}/batch/{batch}";
    public static String STOCK_INCREASE = "api/v1.0/inventory/stockbook/return";
    public static String STOCK_ACTIVITY_DATERANGE_ENTITY = "api/v1.0/inventory/stockactivitydaterangeentity";
    public static String STOCK_ACTIVITY_CLOSING = "api/v1.0/inventory/stockactivity/closing";


    //Temp Stock Book
    public static String GET_TEMP_STOCK_PRODUCT = "api/v1.0/inventory/tempstockbook";
    public static String GET_TEMP_STOCK_PRODUCT_BATCH = "api/v1.0/inventory/tempstockbookbyproductandbatch";
    public static String TEMP_STOCK_BOOK_SAVE = "api/v1.0/inventory/tempstockbook";
    public static String TEMP_STOCK_BOOK_BY_USER = "api/v1.0/inventory/tempstockbook/user";



    /*<--------------------------------------------Accounts ------------------------------------------------->*/

    //Bank Register
    public static String BANK_REGISTER_SHOW = "api/v1.0/accounts/bankregister";
    public static String BANK_REGISTER_SAVE = "api/v1.0/accounts/bankregister";
    public static String BANK_REGISTER_DATATABLE = "api/v1.0/accounts/bankregister/datatable";
    public static String BANK_REGISTER_UPDATE = "api/v1.0/accounts/bankregister/{id}";
    public static String BANK_REGISTER_DELETE = "api/v1.0/accounts/bankregister/{id}";




    //GL
    public static String GENERAL_LEDGER_SHOW = "api/v1.0/accounts/generalledger";
    public static String GENERAL_LEDGER_SAVE = "api/v1.0/accounts/generalledger";
    public static String GENERAL_LEDGER_DATATABLE = "api/v1.0/accounts/generalledger/datatable";
    public static String GENERAL_LEDGER_UPDATE = "api/v1.0/accounts/generalledger/{id}";

    //Accounts
    public static String RECIPT_DETAIL = "api/v1.0/accounts/receiptdetails";
    public static String RECIPT_DETAIL_SAVE = "api/v1.0/accounts/receiptdetails";
    public static String RECIPT_DETAIL_DATATABLE = "api/v1.0/accounts/receiptdetails/datatable";
    public static String RECIPT_DETAIL_UPDATE = "api/v1.0/accounts/receiptdetails/{id}";
    public static String RECIPT_DETAIL_DELETE = "api/v1.0/accounts/receiptdetails/{id}";
    public static String RECIPT_APPROVE = "api/v1.0/accounts/receipt-approve";

    //    Reciept detail log
    public static String RECIPT_DETAIL_LOG = "api/v1.0/accounts/reciptdetaillog";
    public static String RECIPT_DETAIL_LOG_INVS_ID = "api/v1.0/accounts/reciptdetailloginvbyreciptId";
    public static String RECIPT_DETAIL_LOG_CRNT_ID = "api/v1.0/accounts/reciptdetaillogcrntbyreciptId";
    public static String RECIPT_DETAIL_LOG_GTN_ID = "api/v1.0/accounts/reciptdetailloggtnbyreciptId";
    public static String RECIPT_CANCEL = "api/v1.0/accounts/receipt/cancel";


    //Wallet
    public static String WALLET_SHOW = "api/v1.0/accounts/wallet";
    public static String WALLET_SAVE = "api/v1.0/accounts/wallet";
    public static String WALLET_DATATABLE = "api/v1.0/accounts/wallet/datatable";
    public static String WALLET_UPDATE = "api/v1.0/accounts/wallet/{id}";
    public static String WALLET_DELETE = "api/v1.0/accounts/wallet/{id}";


    //Payemnt Show
    public static String PAYMENT_SHOW = "api/v1.0/accounts/paymentdetails";
    public static String PAYMENT_SAVE = "api/v1.0/accounts/paymentdetails";
    public static String PAYMENT_DATATABLE = "api/v1.0/accounts/paymentdetails/datatable";
    public static String PAYMENT_UPDATE = "api/v1.0/accounts/paymentdetails/{id}";
    public static String PAYMENT_DELETE = "api/v1.0/accounts/paymentdetails/{id}";


    //   payment detail log
    public static String PAYMENT_DETAIL_LOG = "api/v1.0/accounts/billpaymentlog";
    public static String PAYMENT_DETAIL_LOG_INVS_ID = "api/v1.0/accounts/billpaymentloginvbypaymentId";
    public static String PAYMENT_DETAIL_LOG_CRNT_ID = "api/v1.0/accounts/billpaymentlogcrntbypaymentId";
    public static String PAYMENT_DETAIL_LOG_GRN_ID = "api/v1.0/accounts/billpaymentloggrnbypaymentId";



    //Credit
    public static String CREDIT_SHOW = "api/v1.0/accounts/creditjv";
    public static String CREDIT_SAVE = "api/v1.0/accounts/creditjv";
    public static String CREDIT_APPROVE = "api/v1.0/accounts/creditjv/approve";
    public static String CREDIT_UNSETTLED = "api/v1.0/accounts/creditjvunbycustomer";
    public static String CREDIT_SETTLED = "api/v1.0/accounts/creditjvsettledbycustomer";
    public static String CREDIT_DATATABLE = "api/v1.0/accounts/creditjv/datatable";
    public static String CREDIT_UPDATE = "api/v1.0/accounts/creditjv/{id}";
    public static String CREDIT_DELETE = "api/v1.0/accounts/creditjv/{id}";
    public static String SET_CREDIT_STATUS = "api/v1.0/accounts/setcreditstatus/{id}/type/{type}";

    //Debit
    public static String DEBIT_SHOW = "api/v1.0/accounts/debitjv";
    public static String DEBIT_SAVE = "api/v1.0/accounts/debitjv";
    public static String DEBIT_DATATABLE = "api/v1.0/accounts/debitjv/datatable";
    public static String DEBIT_APPROVE = "api/v1.0/accounts/debitjv/approve";

    /*<--------------------------------------------Purchase ------------------------------------------------->*/
    //purchase product details
    public static String PURCHASE_PRODUCT_SHOW = "api/v1.0/purchase/productdetail";
    public static String PURCHASE_BILL_BY_DATERANGE = "api/v1.0/purchase/purchasebillbydaterange";
    public static String PURCHASE_PRODUCT_SAVE = "api/v1.0/purchase/productdetail";
    public static String PURCHASE_PRODUCT_DATATABLE = "api/v1.0/purchase/productdetail/datatable";
    public static String PURCHASE_PRODUCT_UPDATE = "api/v1.0/purchase/productdetail/{id}";
    public static String PURCHASE_PRODUCT_DELETE = "api/v1.0/purchase/productdetail/{id}";
    public static String PURCHASE_PRODUCT_OF_BILL = "api/v1.0/purchase/productdetail/bill";
    public static String PURCHASE_BILL_CUSTOMER = "api/v1.0/purchase/purchasebillbysupplier";
    public static String PURCHASE_BILL_BALANCE_UPDATE = "api/v1.0/purchase/updatebillbalancebyid";
    public static String PURCHASE_RETURN_BALANCE_UPDATE = "api/v1.0/purchase/updatereturnbalancebyid";
    public static String PURCHASE_BILL_CANCEL = "api/v1.0/purchase/billdetail/cancel";



    //purchase product bill details
    public static String PURCHASE_BILL_SHOW = "api/v1.0/purchase/billdetail";
    public static String PURCHASE_BILL_SAVE = "api/v1.0/purchase/billdetail";
    public static String PURCHASE_BILL_DATATABLE = "api/v1.0/purchase/billdetail/datatable";
    public static String PURCHASE_BILL_UPDATE = "api/v1.0/purchase/billdetail/{id}";
    public static String PURCHASE_BILL_DELETE = "api/v1.0/purchase/billdetail/{id}";

    public static String PURCHASE_BILL_RECENT = "api/v1.0/purchase/billdetail/getrecent";
    public static String PURCHASE_BILL_SUPPLIER = "api/v1.0/purchase/purchasebillbysupplier";
    public static String PURCHASE_PRODUCT_OF_BILLIDS = "api/v1.0/purchase/purchaseproductdetailslist/bill" +
            "/{purbillsIds}";

    //purchase return
    public static String PURCHASE_RETURN_SHOW = "api/v1.0/purchase/returndetail";
    public static String PURCHASE_RETURN_SAVE = "api/v1.0/purchase/returndetail";
    public static String PURCHASE_RETURN_DATATABLE = "api/v1.0/purchase/returndetail/datatable";
    public static String PURCHASE_RETURN_UPDATE = "api/v1.0/purchase/returndetail/{id}";
    public static String PURCHASE_RETURN_DELETE = "api/v1.0/purchase/returndetail/{id}";
    public static String PURCHASE_RETURN_CUSTOMER = "api/v1.0/purchase/returndetailbysupplier";
    public static String PURCHASE_RETURN_BY_DATERANGE = "api/v1.0/purchase/returndetailbydaterange";


    //e-Invoice
    public static String E_INVOICE_BASE_URL = "https://test.nsdlgsp.co.in";
    public static String E_INVOICE_GET_KEY = E_INVOICE_BASE_URL + "/GSPUtility/getKey";
    public static String E_INVOICE_AUTH_TOKEN = E_INVOICE_BASE_URL + "/ewb_v1.03/einv/authtoken";
    public static String E_INVOICE_GEN_IRN = E_INVOICE_BASE_URL + "/ewb_v1.03/einv/gen-irn";
    public static String E_INVOICE_CANCEL_IRN = E_INVOICE_BASE_URL + "/ewb_v1.03/einv/can-irn";
    public static String E_INVOICE_GET_IRN = E_INVOICE_BASE_URL + "/ewb_v1.03/einv/get-irn";

    //    Bill detail log
    public static String BILL_DETAIL_LOG = "api/v1.0/accounts/reciptdetaillog";


    //      Update Password
    public static String UPDATE_PASSWORD = "api/v1.0/entity/update-password/id/{id}/password/{password}";



    /*<-------------------------------------------Product ------------------------------------------------->*/

    //Transport Type
    public static String TRANSPORT_TYPE_SHOW = "api/v1.0/shipments/transporttype";
    public static String TRANSPORT_TYPE_SAVE = "api/v1.0/shipments/transporttype";
    public static String TRANSPORT_TYPE_DATATABLE = "api/v1.0/shipments/transporttype/datatable";
    public static String TRANSPORT_TYPE_UPDATE = "api/v1.0/shipments/transporttype/{id}";
    public static String TRANSPORT_TYPE_DELETE = "api/v1.0/shipments/transporttype/{id}";

    //Vechicle Detail
    public static String VECHILE_DETAIL_SHOW = "api/v1.0/shipments/vehicledetail";
    public static String VECHILE_DETAIL_SAVE = "api/v1.0/shipments/vehicledetail";
    public static String VECHILE_DETAIL_DATATABLE = "api/v1.0/shipments/vehicledetail/datatable";
    public static String VECHILE_DETAIL_UPDATE = "api/v1.0/shipments/vehicledetail/{id}";
    public static String VECHILE_DETAIL_DELETE = "api/v1.0/shipments/vehicledetail/{id}";



//    Sample Conversion logs
    public  static  String SAMPLE_CONVERSION_LOGS = "api/v1.0/sales/sampleconversionlogs";


//    Sample invoicing
    public static String SAMPLE_INVOICE_SAVE = "api/v1.0/sales/sampleconversion";
}