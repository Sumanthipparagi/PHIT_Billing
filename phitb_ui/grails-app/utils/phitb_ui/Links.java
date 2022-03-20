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

    //    Priorty Master
    public static String PRIORITY_SAVE = "api/v1.0/system/priority";
    public static String PRIORITY_SHOW = "api/v1.0/system/priority";
    public static String PRIORITY_DATATABLE = "api/v1.0/system/priority/datatable";
    public static String PRIORITY_UPDATE = "api/v1.0/system/priority/{id}";
    public static String PRIORITY_DELETE = "api/v1.0/system/priority/{id}";
    public static String PRIORITY_BY_ENTITY = "api/v1.0/system/prioritybyentity";


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

    public static String ENTITY_AUTH = "api/v1.0/entity/authregister/username";

    public static String ENTITY_TYPE_MASTER_SHOW = "api/v1.0/entity/entitytypemaster";
    public static String DEPARTMENT_MASTER_SHOW = "api/v1.0/entity/departmentmaster";
    public static String DIVISION_SHOW = "api/v1.0/product/division";
    public static String DIVISION_BY_ENTITY = "api/v1.0/product/divisionbyentity";
    public static String GENDER_SHOW = "api/v1.0/system/gender";

    //Entity Register
    public static String ENTITY_REGISTER_SHOW = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_SAVE = "api/v1.0/entity/entityregister";
    public static String ENTITY_REGISTER_DATATABLE = "api/v1.0/entity/entityregister/datatable";
    public static String ENTITY_REGISTER_UPDATE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_DELETE = "api/v1.0/entity/entityregister/{id}";
    public static String ENTITY_REGISTER_AFFILIATE = "api/v1.0/entity/entityregister/affiliate";

    //Account Register
    public static String ACCOUNT_REGISTER_SHOW = "api/v1.0/entity/accountregister";
    public static String ACCOUNT_REGISTER_SAVE = "api/v1.0/entity/entityregister";
    public static String ACCOUNT_REGISTER_DATATABLE = "api/v1.0/entity/entityregister/datatable";
    public static String ACCOUNT_REGISTER_UPDATE = "api/v1.0/entity/entityregister/{id}";
    public static String ACCOUNT_REGISTER_DELETE = "api/v1.0/entity/entityregister/{id}";
    public static String ACCOUNT_REGISTER_AFFILIATE = "api/v1.0/entity/entityregister/affiliate";

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

    //Product TYPE
    public static String PRODUCT_TYPE_SHOW = "api/v1.0/product/producttypemaster";
    public static String PRODUCT_TYPE_SAVE = "api/v1.0/product/producttypemaster";
    public static String PRODUCT_TYPE_DATATABLE = "api/v1.0/product/producttypemaster/datatable";
    public static String PRODUCT_TYPE_UPDATE = "api/v1.0/product/producttypemaster/{id}";
    public static String PRODUCT_TYPE_DELETE = "api/v1.0/product/producttypemaster/{id}";

    //Product Group
    public static String PRODUCT_GROUP_SHOW = "api/v1.0/product/productgroupmaster";
    public static String PRODUCT_GROUP_SAVE = "api/v1.0/product/productgroupmaster";
    public static String PRODUCT_GROUP_DATATABLE = "api/v1.0/product/productgroupmaster/datatable";
    public static String PRODUCT_GROUP_UPDATE = "api/v1.0/product/productgroupmaster/{id}";
    public static String PRODUCT_GROUP_DELETE = "api/v1.0/product/productgroupmaster/{id}";

    //unit type
    public static String UNIT_TYPE_SHOW = "api/v1.0/product/unittypemaster";
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
    public static String PRODUCT_CATEGORY_SAVE = "api/v1.0/product/productcategorymaster";
    public static String PRODUCT_CATEGORY_DATATABLE = "api/v1.0/product/productcategorymaster/datatable";
    public static String PRODUCT_CATEGORY_UPDATE = "api/v1.0/product/productcategorymaster/{id}";
    public static String PRODUCT_CATEGORY_DELETE = "api/v1.0/product/productcategorymaster/{id}";

    //Product Schedule
    public static String PRODUCT_SCHEDULE_SHOW = "api/v1.0/product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_SAVE = "api/v1.0/product/productschdulemaster";
    public static String PRODUCT_SCHEDULE_DATATABLE = "api/v1.0/product/productschdulemaster/datatable";
    public static String PRODUCT_SCHEDULE_UPDATE = "api/v1.0/product/productschdulemaster/{id}";
    public static String PRODUCT_SCHEDULE_DELETE = "api/v1.0/product/productschdulemaster/{id}";

    //Product Composition
    public static String PRODUCT_COMPOSITION_SHOW = "api/v1.0/product/compositionmasterregister";
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

    /*<--------------------------------------------Sale Bill ------------------------------------------------->*/
    //Sale Bill Details
    public static String SALE_BILL_SHOW = "api/v1.0/sales/salebilldetails";
    public static String SALE_BILL_SAVE = "api/v1.0/sales/salebilldetails";
    public static String SALE_BILL_DATATABLE = "api/v1.0/sales/salebilldetails/datatable";
    public static String SALE_BILL_UPDATE = "api/v1.0/sales/salebilldetails/{id}";
    public static String SALE_BILL_DELETE = "api/v1.0/sales/salebilldetails/{id}";
    public static String SALE_BILL_UNSETTLED = "api/v1.0/sales/salebillunsettledbycustomer";
    public static String SALE_BILL_SETTLED = "api/v1.0/sales/salebillsettledbycustomer";
    public static String SALE_BILL_PAYMENT = "api/v1.0/sales/salebillbypaymentstatus";
    public static String SET_PAYMENT_BILL = "api/v1.0/sales/setpaymentstatus/{id}/type/{type}";
    public static String SALE_BILL_RECENT = "api/v1.0/sales/salebilldetails/getrecent";


    public static String SALE_SCHEME_CONFIG_GET_PRODUCT_BATCH = "api/v1.0/sales/schemeconfiguration/product/$productId/batch/$batchNumber";


    //Sale Bill Details
    public static String SALE_PRODUCT_SHOW = "api/v1.0/sales/saleproductdetails";
    public static String SALE_PRODUCT_SAVE = "api/v1.0/sales/saleproductdetails";
    public static String SALE_PRODUCT_DATATABLE = "api/v1.0/sales/saleproductdetails/datatable";
    public static String SALE_PRODUCT_UPDATE = "api/v1.0/sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_DELETE = "api/v1.0/sales/saleproductdetails/{id}";
    public static String SALE_PRODUCT_OF_BILL = "api/v1.0/sales/saleproductdetails/bill";



    /*<--------------------------------------------Inventory ------------------------------------------------->*/

    //Stock book
    public static String STOCK_BOOK = "api/v1.0/inventory/stockbook";
    public static String STOCK_BOOK_DATATABLE = "api/v1.0/inventory/stockbook/datatable";
    public static String GET_STOCKS_OF_PRODUCT = "api/v1.0/inventory/stockbookbyproduct";
    public static String STOCK_BOOK_PURCHASE = "api/v1.0/inventory/stockbook/purchase/";


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


    //Accounts
    public static String RECIPT_DETAIL = "api/v1.0/accounts/receiptdetails";
    public static String RECIPT_DETAIL_SAVE = "api/v1.0/accounts/receiptdetails";
    public static String RECIPT_DETAIL_DATATABLE = "api/v1.0/accounts/receiptdetails/datatable";
    public static String RECIPT_DETAIL_UPDATE = "api/v1.0/accounts/receiptdetails/{id}";
    public static String RECIPT_DETAIL_DELETE = "api/v1.0/accounts/receiptdetails/{id}";


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


    //Credit
    public static String CREDIT_SHOW = "api/v1.0/accounts/creditjv";
    public static String CREDIT_SAVE = "api/v1.0/accounts/creditjv";
    public static String CREDIT_UNSETTLED = "api/v1.0/accounts/creditjvunbycustomer";
    public static String CREDIT_SETTLED = "api/v1.0/accounts/creditjvsettledbycustomer";
    public static String CREDIT_DATATABLE = "api/v1.0/accounts/creditjv/datatable";
    public static String CREDIT_UPDATE = "api/v1.0/accounts/creditjv/{id}";
    public static String CREDIT_DELETE = "api/v1.0/accounts/creditjv/{id}";
    public static String SET_CREDIT_STATUS = "api/v1.0/accounts/setcreditstatus/{id}/type/{type}";


    /*<--------------------------------------------Purchase ------------------------------------------------->*/
    //purchase product details
    public static String PURCHASE_PRODUCT_SHOW = "api/v1.0/purchase/productdetail";
    public static String PURCHASE_PRODUCT_SAVE = "api/v1.0/purchase/productdetail";
    public static String PURCHASE_PRODUCT_DATATABLE = "api/v1.0/purchase/productdetail/datatable";
    public static String PURCHASE_PRODUCT_UPDATE = "api/v1.0/purchase/productdetail/{id}";
    public static String PURCHASE_PRODUCT_DELETE = "api/v1.0/purchase/productdetail/{id}";

}