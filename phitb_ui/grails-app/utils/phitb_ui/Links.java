package phitb_ui;

public class Links
{
    public static String API_GATEWAY = new Constants().API_GATEWAY;

    /*<-------------------------------------------System------------------------------------------------->*/

    //    Account modes
    public static String ACCOUNT_MODES_SAVE = "api/v1.0/system/accountmodes";
    public static String ACCOUNT_MODES_DATATABLE = "api/v1.0/system/accountmodes/datatable";
    public static String ACCOUNT_MODES_UPDATE = "api/v1.0/system/accountmodes/{id}";
    public static String ACCOUNT_MODES_DELETE = "api/v1.0/system/accountmodes/{id}";

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
    public static String RACK_SAVE = "api/v1.0/facility/rack";
    public static String RACK_DATATABLE = "api/v1.0/facility/rack/datatable";
    public static String RACK_UPDATE = "api/v1.0/facility/rack/{id}";
    public static String RACK_DELETE = "api/v1.0/facility/rack/{id}";

    /*<-------------------------------------------Entity ------------------------------------------------->*/

    public static String ENTITY_TYPE_MASTER_SHOW = "api/v1.0/entity/entitytypemaster";
    public static String DEPARTMENT_MASTER_SHOW = "api/v1.0/entity/departmentmaster";
    public static String BANK_REGISTER_SHOW = "api/v1.0/accounts/bankregister";
    public static String ROLE_MASTER_SHOW = "api/v1.0/entity/rolemaster";
    public static String ACCOUNT_REGISTER_SHOW = "api/v1.0/entity/accountregister";
    public static String DIVISION_SHOW = "api/v1.0/product/division";
    public static String GENDER_SHOW = "api/v1.0/system/gender";

    //Entity Register
    public static String ENTITY_REGISTER_SHOW = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_SAVE = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_DATATABLE = "api/v1.0/entity/entityregister/datatable";
    public static String ENTITY_REGISTER_UPDATE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_DELETE = "api/v1.0/entity/entityregister/{id}";


    //User Register
    public static String USER_REGISTER_SHOW = "api/v1.0/entity/userregister";
    public static String USER_REGISTER_SAVE = "api/v1.0/entity/userregister";
    public static String USER_REGISTER_DATATABLE = "api/v1.0/entity/userregister/datatable";
    public static String USER_REGISTER_UPDATE = "api/v1.0/entity/userregister/{id}";
    public static String USER_REGISTER_DELETE = "api/v1.0/entity/userregister/{id}";


    //Customer Group Register
    public static String CUSTOMER_GROUP_REGISTER_SHOW = "api/v1.0/entity/customergroupregister";
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


    //Financial Year master
    public static String FINANCIAL_YEAR_SHOW = "api/v1.0/entity/financialyearmaster";
    public static String FINANCIAL_YEAR_SAVE = "api/v1.0/entity/financialyearmaster";
    public static String FINANCIAL_YEAR_DATATABLE = "api/v1.0/entity/financialyearmaster/datatable";
    public static String FINANCIAL_YEAR_UPDATE = "api/v1.0/entity/financialyearmaster/{id}";
    public static String FINANCIAL_YEAR_DELETE = "api/v1.0/entity/financialyearmaster/{id}";


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
}