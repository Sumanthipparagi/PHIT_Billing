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
    public static String USER_REGISTER_SHOW = "api/v1.0/entity/userregister";
    public static String ENTITY_TYPE_MASTER_SHOW = "api/v1.0/entity/entitytypemaster";


    //Entity Register
    public static String ENTITY_REGISTER_SHOW = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_SAVE = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_DATATABLE = "api/v1.0/entity/entityregister/datatable";
    public static String ENTITY_REGISTER_UPDATE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_DELETE = "api/v1.0/entity/entityregister/{id}";

}