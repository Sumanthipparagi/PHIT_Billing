package phitb_ui

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'auth', action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/auth"(controller: "auth", action: "index")
        "/login"(controller: "auth", action: 'login')
        "/logout"(controller: "auth", action: 'logout')
        "/dashboard"(controller: 'dashboard', action: "index")
        "/dashboard/stats"(controller: 'dashboard', action: "dashboardStats")
        "/dashboard/graph"(controller: 'dashboard', action: "salesMonthWiseForGraph")
        "/forms"(controller: 'dashboard', action: 'forms')
        "/table"(controller: 'dashboard', action: 'table')
        "/timeline"(controller: 'dashboard', action: 'timeline')
        "/microservice-status"(controller: 'dashboard', action: 'microServiceStatus')
        "/api/media/$path**"(controller: "fileLocation", action: "index")
        "/user/update-details/$id"(controller: 'auth' , action: 'updateUser')
        "/user/update-password"(controller: 'userRegister' , action: 'updatePassword')
        "/day-end-details"(controller: 'dashboard', action: 'dayEndDetails')
        "/day-end"(controller: 'dashboard', action: 'dayEnd')


  /*<-------------------------------------------Microservice Status ------------------------------------------------->*/
        "/system-service-status"(controller: 'dashboard', action: 'systemServiceStatus')
        "/shipments-service-status"(controller: 'dashboard', action: 'shipmentsServiceStatus')
        "/sales-service-status"(controller: 'dashboard', action: 'salesServiceStatus')
        "/purchase-service-status"(controller: 'dashboard', action: 'purchaseServiceStatus')
        "/product-service-status"(controller: 'dashboard', action: 'productServiceStatus')
        "/inventory-service-status"(controller: 'dashboard', action: 'inventoryServiceStatus')
        "/facility-service-status"(controller: 'dashboard', action: 'facilityServiceStatus')
        "/entity-service-status"(controller: 'dashboard', action: 'entityServiceStatus')
        "/accounts-service-status"(controller: 'dashboard', action: 'accountsServiceStatus')

/*<-------------------------------------------System ------------------------------------------------->*/
        "/accountmodes"(controller: "accountMode") {
            action = [GET: "index", POST: "save"]
        }
        "/accountmodes/datatable"(controller: "accountMode", action: "dataTable")
        "/accountmodes/getallentity"(controller: "accountMode", action: 'getAllEntity')
        "/accountmodes/update/$id"(controller: "accountMode",action:"update")
        "/accountmodes/delete/$id"(controller: "accountMode",action:"delete")

//        State
        "/state"(controller: "state") {
            action = [GET: "index", POST: "save"]
        }
        "/state/datatable"(controller: "state", action: "dataTable")
        "/state/update/$id"(controller: "state",action:"update")
        "/state/delete/$id"(controller: "state",action:"delete")

//      City
        "/city"(controller: "city") {
            action = [GET: "index", POST: "save"]
        }
        "/city/get"(controller: "city", action: "show")
        "/city/datatable"(controller: "city", action: "dataTable")
        "/city/update/$id"(controller: "city",action:"update")
        "/city/delete/$id"(controller: "city",action:"delete")
        "/getcitybypincode"(controller: 'city', action: 'getCityByPincode')
        "/getcitybyid"(controller: 'city', action: 'getCityById')


//        //      Priority
//        "/priority"(controller: "priority") {
//            action = [GET: "index", POST: "save"]
//        }
//        "/priority/datatable"(controller: "priority", action: "dataTable")
//        "/priority/update/$id"(controller: "priority",action:"update")
//        "/priority/delete/$id"(controller: "priority",action:"delete")


        //   Region
        "/region"(controller: "region") {
            action = [GET: "index", POST: "save"]
        }
        "/region/datatable"(controller: "region", action: "dataTable")
        "/region/update/$id"(controller:"region",action:"update")
        "/region/delete/$id"(controller: "region",action:"delete")


        //   Division
        "/division-master"(controller: "divisionMaster") {
            action = [GET: "index", POST: "save"]
        }
        "/division-master/datatable"(controller: "divisionMaster", action: "dataTable")
        "/division-master/update/$id"(controller:"divisionMaster",action:"update")
        "/division-master/delete/$id"(controller: "divisionMaster",action:"delete")


        //   District
        "/district"(controller: "district") {
            action = [GET: "index", POST: "save"]
        }
        "/district/datatable"(controller: "district", action: "dataTable")
        "/district/update/$id"(controller:"district",action:"update")
        "/district/delete/$id"(controller: "district",action:"delete")



//      Country
        "/country"(controller: "country") {
            action = [GET: "index", POST: "save"]
        }
        "/country/datatable"(controller: "country", action: "dataTable")
        "/country/update/$id"(controller: "country",action:"update")
        "/country/delete/$id"(controller: "country",action:"delete")


//      Form Master
        "/form"(controller: "form") {
            action = [GET: "index", POST: "save"]
        }
        "/form/datatable"(controller: "form", action: "dataTable")
        "/form/update/$id"(controller: "form",action:"update")
        "/form/delete/$id"(controller: "form",action:"delete")


        //      Priority
        "/priority"(controller: "priority") {
            action = [GET: "index", POST: "save"]
        }
        "/priority/datatable"(controller: "priority", action: "dataTable")
        "/priority/update/$id"(controller: "priority",action:"update")
        "/priority/delete/$id"(controller: "priority",action:"delete")
        "/priority/entity/$id"(controller: "priority",action:"getPriorityByEntity")

        /*<-------------------------------------------Facility ------------------------------------------------->*/

//        Fridge
        "/fridge"(controller: "fridge") {
            action = [GET: "index", POST: "save"]
        }
        "/fridge/datatable"(controller: "fridge", action: "dataTable")
        "/fridge/update/$id"(controller: "fridge",action:"update")
        "/fridge/delete/$id"(controller: "fridge",action:"delete")

//        ccm

        "/ccm"(controller: "ccm") {
            action = [GET: "index", POST: "save"]
        }
        "/ccm/datatable"(controller: "ccm", action: "dataTable")
        "/ccm/update/$id"(controller: "ccm",action:"update")
        "/cmm/delete/$id"(controller: "ccm",action:"delete")

        //        godown

        "/godown"(controller: "godown") {
            action = [GET: "index", POST: "save"]
        }
        "/godown/datatable"(controller: "godown", action: "dataTable")
        "/godown/update/$id"(controller: "godown",action:"update")
        "/godown/delete/$id"(controller: "godown",action:"delete")


        //        Rack
        "/rack"(controller: "rack") {
            action = [GET: "index", POST: "save"]
        }
        "/rack/datatable"(controller: "rack", action: "dataTable")
        "/rack/update/$id"(controller:"rack",action:"update")
        "/rack/delete/$id"(controller: "rack",action:"delete")

        /*<-------------------------------------------Entity ------------------------------------------------->*/

        "/entity-register"(controller: "entityRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-register/add-entity-register"(controller: "entityRegister", action: 'addEntity')
        "/entity-register/update-entity-register/$id"(controller: "entityRegister", action: 'updateEntity')
        "/entity-register/datatable"(controller: "entityRegister", action: "dataTable")
        "/entity-register/parent/datatable"(controller: "entityRegister", action: "parentEntityDataTable")
        "/entity-register/update/$id"(controller:"entityRegister",action:"update")
        "/entity-register/delete/$id"(controller: "entityRegister",action:"delete")
        "/entity-register/getparententities"(controller: "entityRegister",action:"getParentEntities")
        "/entity-register/getentities"(controller: "entityRegister",action:"getByParent")
        "/entity-register/getbyaffiliates/$id"(controller: "entityRegister",action:"getByAffiliates")
        "/getentitytypebyId"(controller: 'entityRegister', action: 'getEntityTypeById')


        //Entity on board information
        "/entity-onborad"(controller: 'entityRegister', action: 'entityOnBoardInfo')
        "/get-onoboard-info"(controller: 'entityRegister', action: 'getOnBoardDetails')
        "/save-entity-onboard-info"(controller: 'entityRegister', action: 'saveEntityOnBoardInfo')

//        Bulk import customer
            "/bulk-import"(controller: 'entityRegister',     action: 'bulkImport')
            "/customer-import/import"(controller: 'entityRegister',action: 'customerImport')


        //HQ- areas
        "/hq-area"(controller: "HQAreas") {
            action = [GET: "index", POST: "save"]
        }
        "/hq-area/datatable"(controller: "HQAreas", action: "dataTable")
        "/hq-area/update/$id"(controller:"HQAreas",action:"update")
        "/hq-area/delete/$id"(controller: "HQAreas",action:"delete")



        //entity-irn
        "/entity-irn"(controller: "entityIRN") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-irn/datatable"(controller: "entityIRN", action: "dataTable")
        "/entity-irn/update/$id"(controller:"entityIRN",action:"update")
        "/entity-irn/delete/$id"(controller: "entityIRN",action:"delete")

        //entity-route
        "/entity-route"(controller: "entityRoute") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-route/datatable"(controller: "entityRoute", action: "dataTable")
        "/entity-route/update/$id"(controller:"entityRoute",action:"update")
        "/entity-route/delete/$id"(controller: "entityRoute",action:"delete")


        //  Entity Settings

        "/entity-settings"(controller: "entitySettings") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-settings/datatable"(controller: "entitySettings", action: "dataTable")
        "/entity-settings/update/$id"(controller: "entitySettings",action:"update")
        "/entity-settings/delete/$id"(controller: "entitySettings",action:"delete")
        "/entity-settings/settings"(controller: 'entitySettings', action: 'settings')



        "/email-settings"(controller: 'emailSettings', action: 'index')
        "/email-settings/save"(controller: 'emailSettings', action: 'emailSettingsSave')
        "/email-settings/update"(controller: 'emailSettings', action: 'emailSettingsUpdate')
        "/email-settings/testmail"(controller: 'emailSettings', action: 'sendTestMail')
        "/email-log/datatable"(controller: 'emailSettings', action: 'emailLogDataTable')

        "/email-config"(controller: 'emailSettings', action: 'emailConfig')
        "/email-config"(controller: "emailSettings") {
            action = [GET: "emailConfig", POST: "saveEmailConfig"]
        }


//        Entity Config
        "/entity-config"(controller: 'entityConfig', action: 'saveEntityConfig')

//      User Register
        "/user-register"(controller: "userRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/user-register/add-user-register"(controller: "userRegister", action: 'addUser')
        "/user-register/update-user-register/$id"(controller: "userRegister", action: 'updateUser')
        "/user-register/datatable"(controller: "userRegister", action: "dataTable")
        "/user-register/update/$id"(controller:"userRegister",action:"update")
        "/user-register/delete/$id"(controller: "userRegister",action:"delete")
        "/user-register/userexists"(controller: "userRegister",action:"userExists")


        //      Customer Group register
        "/customer-group-register"(controller: "customerGroup") {
            action = [GET: "index", POST: "save"]
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/customer-group-register/datatable"(controller: "customerGroup", action: "dataTable")
        "/customer-group-register/update/$id"(controller:"customerGroup",action:"update")
        "/customer-group-register/delete/$id"(controller: "customerGroup",action:"delete")


        //     Day End Master
        "/day-end-master"(controller: "dayEnd") {
            action = [GET: "index", POST: "save"]
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/day-end-master/datatable"(controller: "dayEnd", action: "dataTable")
        "/day-end-master/update/$id"(controller:"dayEnd",action:"update")
        "/day-end-master/delete/$id"(controller: "dayEnd",action:"delete")


        //    Financial Year Master
        "/financial-year-master"(controller: "financialYear") {
            action = [GET: "index", POST: "save"]
        }
        "/financial-year-master/datatable"(controller: "financialYear", action: "dataTable")
        "/financial-year-master/update/$id"(controller:"financialYear",action:"update")
        "/financial-year-master/delete/$id"(controller: "financialYear",action:"delete")

        //   Region Register
        "/region-master"(controller: "regionMaster") {
            action = [GET: "index", POST: "save"]
        }
        "/region-master/datatable"(controller: "regionMaster", action: "dataTable")
        "/region-master/update/$id"(controller:"regionMaster",action:"update")
        "/region-master/delete/$id"(controller: "regionMaster",action:"delete")


        //   Route Register
        "/route-regitser"(controller: "route") {
            action = [GET: "index", POST: "save"]
        }
        "/route-regitser/datatable"(controller: "route", action: "dataTable")
        "/route-regitser/update/$id"(controller:"route",action:"update")
        "/route-regitser/delete/$id"(controller: "route",action:"delete")

        //   Account Register
        "/accounts"(controller: "accountRegister") {
            action = [GET: 'index', POST: 'save']
        }
        "/accounts-list"(controller: "accountRegister", action: 'accountsList')
        "/accounts/datatable"(controller: "accountRegister", action: 'dataTable')
        "/accounts/update/$id"(controller:"accountRegister",action:"update")
        "/accounts/delete/$id"(controller: "accountRegister",action:"delete")

        //   Role
        "/role"(controller: "role") {
            action = [GET: "index", POST: "save"]
        }
        "/role/datatable"(controller: "role", action: "dataTable")
        "/role/update/$id"(controller:"role",action:"update")
        "/role/delete/$id"(controller: "role",action:"delete")

        //   Rule
        "/rule"(controller: "rule") {
            action = [GET: "index", POST: "save"]
        }
        "/rule/datatable"(controller: "rule", action: "dataTable")
        "/rule/update/$id"(controller:"rule",action:"update")
        "/rule/delete/$id"(controller: "rule",action:"delete")


        //   Tax
        "/tax"(controller: "tax") {
            action = [GET: "index", POST: "save"]
        }
        "/tax/datatable"(controller: "tax", action: "dataTable")
        "/tax/update/$id"(controller:"tax",action:"update")
        "/tax/delete/$id"(controller: "tax",action:"delete")
        "/tax/showtax/$id"(controller: 'tax', action: 'showTaxForReturn')
        "/tax/tax-value-and-entity"(controller: 'tax', action: 'getTaxByTaxValueAndEntity')



        //   Territory
        "/territory"(controller: "territory") {
            action = [GET: "index", POST: "save"]
        }
        "/territory/datatable"(controller: "territory", action: "dataTable")
        "/territory/update/$id"(controller:"territory",action:"update")
        "/territory/delete/$id"(controller: "territory",action:"delete")

        //   Terms Conditions
        "/terms-conditions"(controller: "termsCondition") {
            action = [GET: "index", POST: "save"]
        }
        "/get-terms-conditionby-id/$id"(controller: 'termsCondition', action: 'gettermsConditionsById')
        "/terms-conditions/datatable"(controller: "termsCondition", action: "dataTable")
        "/terms-conditions/update/$id"(controller:"termsCondition",action:"update")
        "/terms-conditions/delete/$id"(controller: "termsCondition",action:"delete")


        //   Series
        "/series"(controller: "series") {
            action = [GET: "index", POST: "save"]
        }
        "/series/$id"(controller: "series", action: "getSeriesById")
        "/series/datatable"(controller: "series", action: "dataTable")
        "/series/update/$id"(controller:"series",action:"update")
        "/series/delete/$id"(controller: "series",action:"delete")


        //   Service type
        "/service-type"(controller: "serviceType") {
            action = [GET: "index", POST: "save"]
        }
        "/service-type/datatable"(controller: "serviceType", action: "dataTable")
        "/service-type/update/$id"(controller:"serviceType",action:"update")
        "/service-type/delete/$id"(controller: "serviceType",action:"delete")

        //Department
        "/department"(controller: "department") {
            action = [GET: "index", POST: "save"]
        }
        "/department/datatable"(controller: "department", action: "dataTable")
        "/department/update/$id"(controller:"department",action:"update")
        "/department/delete/$id"(controller: "department",action:"delete")

        /*<-------------------------------------------Accounts------------------------------------------------->*/
        "/credit-jv"(controller: "creditJv",action:"index")
        "/credit-jv/save"(controller: "creditJv",action:"saveCreditJv")
        "/credit-jv/approval"(controller: "creditJv",action:"approval")
        "/credit-jv/approval-table"(controller: "creditJv",action:"dataTable")
        "/credit-jv/approve"(controller: "creditJv",action:"approveReject")
        "/debit-jv"(controller: "debitJv",action:"index")
        "/debit-jv/save"(controller: "debitJv",action:"saveDebitJv")
        "/debit-jv/approval"(controller: "debitJv",action:"approval")
        "/debit-jv/approval-table"(controller: "debitJv",action:"dataTable")
        "/debit-jv/approve"(controller: "debitJv",action:"approveReject")

        /*<-------------------------------------------Product------------------------------------------------->*/
//      Product Register
        "/product"(controller: "product") {
            action = [GET: "index", POST: "save"]
        }
        "/product/add-product"(controller: "product", action: 'addProduct')
        "/product/division/$id"(controller: "product", action: 'getProductByDivision')
        "/product/series/$id"(controller: "product", action: 'getProductBySeries')
        "/product/update-product/$id"(controller: "product", action: 'updateProduct')
        "/product/datatable"(controller: "product", action: "dataTable")
        "/product/update/$id"(controller:"product",action:"update")
        "/product/delete/$id"(controller: "product",action:"delete")
        "/product/product-export"(controller: 'product', action: 'productReportExport')
        "/product/save-bulk-products"(controller: 'product', action: 'saveBulkProducts')
        "/product/get-products-by-entity"(controller: 'product', action: 'productByEntityId')
        "/product/search"(controller: 'product', action: 'searchByName')


        //Division
        "/division"(controller: "division") {
            action = [GET: "index", POST: "save"]
        }
        "/division/datatable"(controller: "division", action: "dataTable")
        "/division/update/$id"(controller:"division",action:"update")
        "/division/delete/$id"(controller: "division",action:"delete")


        // Product Category
        "/product-category"(controller: "productCategory") {
            action = [GET: "index", POST: "save"]
        }
        "/product-category/datatable"(controller: "productCategory", action: "dataTable")
        "/product-category/update/$id"(controller:"productCategory",action:"update")
        "/product-category/delete/$id"(controller: "productCategory",action:"delete")

        // Product Schedule
        "/product-schedule"(controller: "productSchedule") {
            action = [GET: "index", POST: "save"]
        }
        "/product-schedule/datatable"(controller: "productSchedule", action: "dataTable")
        "/product-schedule/update/$id"(controller:"productSchedule",action:"update")
        "/product-schedule/delete/$id"(controller: "productSchedule",action:"delete")


        // Product Composition
        "/product-composition"(controller: "composition") {
            action = [GET: "index", POST: "save"]
        }
        "/product-composition/datatable"(controller: "composition", action: "dataTable")
        "/product-composition/update/$id"(controller:"composition",action:"update")
        "/product-composition/delete/$id"(controller: "composition",action:"delete")


        // Product Type
        "/product-type"(controller: "productType") {
            action = [GET: "index", POST: "save"]
        }
        "/product-type/datatable"(controller: "productType", action: "dataTable")
        "/product-type/update/$id"(controller:"productType",action:"update")
        "/product-type/delete/$id"(controller: "productType",action:"delete")


        // Product group
        "/product-group"(controller: "productGroup") {
            action = [GET: "index", POST: "save"]
        }
        "/product-group/datatable"(controller: "productGroup", action: "dataTable")
        "/product-group/getbyentity"(controller: "productGroup", action: "respondByEntity")
        "/product-group/update/$id"(controller:"productGroup",action:"update")
        "/product-group/delete/$id"(controller: "productGroup",action:"delete")


        //Unit Type
        "/unit-type"(controller: "unitType") {
            action = [GET: "index", POST: "save"]
        }
        "/unit-type/datatable"(controller: "unitType", action: "dataTable")
        "/unit-type/update/$id"(controller:"unitType",action:"update")
        "/unit-type/delete/$id"(controller: "unitType",action:"delete")


        //Division Group register
        "/division-group"(controller: "divisionGroup") {
            action = [GET: "index", POST: "save"]
        }
        "/division-group/datatable"(controller: "divisionGroup", action: "dataTable")
        "/division-group/update/$id"(controller:"divisionGroup",action:"update")
        "/division-group/delete/$id"(controller: "divisionGroup",action:"delete")


        //Product Class
        "/product-class"(controller: "productClass") {
            action = [GET: "index", POST: "save"]
        }
        "/product-class/datatable"(controller: "productClass", action: "dataTable")
        "/product-class/update/$id"(controller:"productClass",action:"update")
        "/product-class/delete/$id"(controller: "productClass",action:"delete")



        //Product cost range
        "/product-cost-range"(controller: "productCostRange") {
            action = [GET: "index", POST: "save"]
        }
        "/product-cost-range/datatable"(controller: "productCostRange", action: "dataTable")
        "/product-cost-range/update/$id"(controller:"productCostRange",action:"update")
        "/product-cost-range/delete/$id"(controller: "productCostRange",action:"delete")


        //Batch register
        "/batch-register"(controller: "batchRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/batch-register/datatable"(controller: "batchRegister", action: "dataTable")
        "/batch-register/update/$id"(controller:"batchRegister",action:"update")
        "/batch-register/delete/$id"(controller: "batchRegister",action:"delete")
        "/batch-register/product/$id"(controller: "batchRegister",action:"getByProduct")
        "/batch-register/batchesforpurchase/$id"(controller: "batchRegister",action:"getBatchesForPurchase")
        "/batch-register/save-bulk-batches"(controller: 'batchRegister', action: 'savebulkBatchRegister')

        /*<-------------------------------------------Sales------------------------------------------------->*/

//        My Invoices
        "/sale-bill/$id"(controller: "salebillDetails", action: 'getSaleBillById')
        "/sale-bill-list"(controller: "salebillDetails", action: 'saleBillList')
        "/sale-bill/datatable"(controller: "salebillDetails", action: 'dataTable')
        "/sale-bill/record-payment"(controller: "salebillDetails", action: 'recordPayment')
        "/sale-bill/adjust-credits"(controller: "salebillDetails", action: 'adjustCredits')
        "/sale-bill/download-irn"(controller: "salebillDetails", action: 'exportGSTEInvoiceJSON')
        "/sale-bill/download-irn/$id"(controller: "salebillDetails", action: 'exportSingleGSTEInvoiceJSON')
        "/sale-bill/genarate-irn"(controller: 'salebillDetails', action: 'genrateIrn')
        "/mail-template"(controller: 'saleEntry', action: 'mailInvoice')


//        stock Adjustment

        "/stock-adjustment"(controller: 'stockAdjustmentDetails', action: 'stockAdjustment')
        "/stock-adjustment-save"(controller: 'stockAdjustmentDetails', action: 'saveStockAdjustmentDetails')
        "/stock-adjustment-list"(controller: 'stockAdjustmentDetails', action: 'stockAdjustmentList')
        "/stock-adjustment-datatable"(controller: 'stockAdjustmentDetails', action: 'dataTable')


//        Sale Retrun
        "/sale-return"(controller: "saleReturn") {
            action = [GET: "index", POST: 'saveSaleReturn']
        }
        "/sale-return/list"(controller: "saleReturn", action: "salesReturnList")
        "/sale-return/datatables"(controller: "saleReturn", action: "salesReturnDatatables")
        "/salebill/customer/$custid"(controller: 'saleReturn', action: 'getSaleBillByCustomer')
        "/getinvoicedetails"(controller:'saleReturn', action: 'getSaleInvByProducts')
        "/sale-return/print-invoice"(controller: "saleReturn", action: "printSaleReturn")
        "/sale-return/my-returns"(controller: "saleReturn", action: "salesReturnList")
        "/sale-return/datatables"(controller: "saleReturn", action: "salesReturnDatatables")
        "/sale-return/cancel-invoice"(controller: "saleReturn", action: "cancelReturns")
        "/sale-return/sale-return-adjustment/print/$saleBillId"(controller: "saleReturn", action: 'printSaleReturnAdjustment')

//        Purchase Return
        "/purchase-return"(controller: "purchaseReturn") {
            action = [GET: "index", POST: 'savePurchaseReturn']
        }
        "/purchase-bill/supplier/$supplierId"(controller: 'purchaseReturn', action: 'getPurchaseBillBySupplier')
        "/purchaseproductdetailsbillandbatch"(controller: 'purchaseReturn', action: 'getPurchaseDetailsByProductAndBatch')
        "/getpurinvoicedetails"(controller:'purchaseReturn', action: 'getPurchaseInvByProducts')
        "/purchase-return/print-invoice"('controller':'purchaseReturn', action: 'printPurchaseReturn')
        "/purchase-return/datatable"(controller: 'purchaseReturn', action: 'purchaseReturnDatatables')
        "/purchase-return/purchase-return-list"(controller: 'purchaseReturn', action: 'purchaseReturnList')
        "/purchase-return/cancel"(controller: 'purchaseReturn', action: 'cancelReturns')


        //Sale Entry
        "/sale-entry"(controller: "saleEntry") {
            action = [GET: "index", POST: 'saveSaleEntry']
        }
        "/edit-sale-entry"(controller: "saleEntry") {
            action = [GET: "editSaleBillDetails", POST: 'updateSaleBillDetails']
        }
/*        "/edit-sale-entry"(controller: "saleEntry") {
            action = [GET: "editSaleBillDetails", POST: 'updateSaleBillDetails']
        }*/
        "/edit-sale-products"(controller: 'saleEntry'){
            action=[POST:'updateSaleProductDetails']
        }
        "/sale-product-details/sale-bill"(controller: 'saleEntry', action: 'getSaleProductDetailsByBill')

        "/sale-product-details/delete"(controller: 'saleEntry', action: 'deleteSaleProduct')

        "/sale-entry/print-invoice"(controller: "saleEntry", action: "printSaleInvoice")
        "/sale-entry/print-invoice-furniture"(controller: "saleEntry", action: "printSaleInvoiceFurniture")
        "/sale-entry/update/$id"(controller:"batchRegister",action:"update")
        "/sale-invoice"(controller: "saleEntry",action:"saleBill")
        "/sales/check-scheme"(controller: "saleEntry", action: "checkSchemeConfiguration")
        "/sale-entry/cancel-invoice"(controller: "saleEntry", action: "cancelInvoice")
        "/sale-entry-clone"(controller: "saleEntry", action: "cloneSaleBillDetails")
        "/update-mass-discount"(controller: 'saleEntry', action: 'updateMassDiscount')

//        Scheme Entry
        "/scheme-entry" (controller: "schemeEntry", action: "index")
        "/scheme-entry/datatable" (controller: "schemeEntry", action: "dataTable")
//        "/sale-return"(controller: "saleEntry",action:"saleRetrun")
        "/add-scheme-entry"(controller: "schemeEntry") {
            action = [GET: "addScheme", POST: 'saveScheme']
        }
        "/update-scheme-entry/$id"(controller: "schemeEntry") {
            action = [GET: "updateScheme", POST: 'update']
        }
        "/scheme-entry/delete/$id"(controller: "schemeEntry",action:"delete")


        "/credit-debit-settlement"(controller: "saleEntry",action:"crdDebS")
        "/debit-jv-print"(controller: "saleEntry",action:"DebJV")
        "/credit-jv-print"(controller: "saleEntry",action:"credJV")
        "/goods-sales-recipt"(controller: "saleEntry",action:"goodsSalesRecipt")
        "/payment-vocher"(controller: "saleEntry",action:"paymentVocher")



        "/saleproductdetailsbillandbatch"(controller: 'saleReturn', action: 'getSaleDetailsByProductAndBatch')

        //GTN
        "/gtn"(controller: "goodsTransferNote") {
            action = [GET: "index", POST: 'saveGtn']
        }
        "/gtn/cancel"(controller: 'goodsTransferNote', action: 'cancelGTN')



        //grn
        "/grn"(controller: 'goodsTransferNote', action: 'grn')
        "/grn/datatable"(controller: "goodsTransferNote",action:"dataTable")
        "/grn/approveGRN"(controller: 'goodsTransferNote', action: 'approveGRN')
        "/grn/print-grn"(controller: "goodsTransferNote", action: "printGRN")
        "/grn/bill"(controller: "goodsTransferNote", action: "getGrnProductList")


        //Delivery Challan
        "/delivery-challan"(controller: "deliveryChallan") {
            action = [GET: "index", POST: 'saveDeliveryChallan']
        }
        "/delivery-challan/cancel"(controller: 'deliveryChallan', action: 'cancelDeliveryChallan')
        "/delivery-challan/approve"(controller: 'deliveryChallan', action: 'approveDeliveryChallan')
        "/delivery-challan/datatable"(controller: "deliveryChallan",action:"dataTable")
        "/delivery-challan-list"(controller: 'deliveryChallan', action: 'devliveryChallanList')
        "/delivery-challan/print-delivery-challan"(controller: "deliveryChallan", action: "printDeliveryChallan")







//        Sample conversion
        "/sample-conversion/save"(controller: 'sampleConversion', action: 'saveSampleConversion')
        "/sample-conversion"(controller: 'sampleConversion', action: 'sampleConversion')
        "/sample-conversion/sample-invoicing"(controller: 'sampleConversion', action: 'sampleInvoicing')
        "/sample-conversion/save-sample-invoicing"(controller: 'sampleConversion', action: 'saveSampleInvoicing')
        "/sample-conversion/print-invoice"(controller: 'sampleConversion', action: 'printSampleInvoice')
        "/sample-conversion/datatable"(controller: 'sampleConversion', action:'dataTable')
        "/sample-conversion/sample-invoice-list"(controller: 'sampleConversion', action: 'sampleConversionList')
        "/sample-conversion/cancel"(controller: 'sampleConversion', action: 'cancelSampleInvoice')

        /*<-------------------------------------------Purchase Entry------------------------------------------------->*/


        "/purchase-entry"(controller: "purchaseEntry") {
            action = [GET: "index", POST: "savePurchaseEntry"]
        }
        "/purchase-entry/print-invoice"(controller: "purchaseEntry", action: "printPurchaseEntry")

        "/purchase-entry/cancel-invoice"(controller: "purchaseEntry", action: "cancelPurchaseInvoice")


        "/edit-purchase-entry"(controller: "purchaseEntry") {
            action = [GET: "editPurchaseBillDetails", POST: 'updatePurchaseBillDetails']
        }

        "/purchase-product-details/purchase-bill"(controller: 'purchaseEntry', action: 'getPurchaseProductDetailsByBill')

        "/purchase-product-details/delete"(controller: 'purchaseEntry', action: 'deletePurchaseProduct')




        //        My Purchase
        "/purchase-bill-list"(controller: "purchaseEntry", action: 'purchasebillList')
        "/purchase-bill/datatable"(controller: "purchaseEntry", action: 'dataTable')





//        purchase-order
        "/purchase-order"(controller: "purchaseOrder") {
            action = [GET: "index", POST: "savePurchaseOrder"]
        }
        "/purchase-order/print-order"(controller: "purchaseOrder", action: "printPurchaseOrder")

        "/purchase-order/cancel-order"(controller: 'purchaseOrder', action: 'cancelPurchaseOrder')

        //        My Orders
        "/purchase-order-list"(controller: "purchaseOrder", action: 'purchaseOrderList')
        "/purchase-order/datatable"(controller: "purchaseOrder", action: 'dataTable')

        //Inventory
        "/stockbook"(controller: "stockBook"){
            action = [GET: "index", POST: "save"]
        }
        "/stockbook/delete/$id"(controller: 'stockBook',action: 'delete')
        "/stockbook/save"(controller: "stockBook"){action = [POST: "save"]}
        "/stockbook/update/$id"(controller: "stockBook"){
            action = [POST: 'update']
        }
        "/stockbook/datatable"(controller: "stockBook",action:"dataTable")
        "/stockbook/product/$id"(controller: "stockBook",action:"getStocksOfProduct")
        "/stockbook/purchase/product/$id"(controller: "stockBook",action:"getStocksOfProductForPurchase")
        "/stockbook/purchase"(controller: "stockBook",action:"StockBookPurchase")
        "/stockbook/purchase/batch"(controller: 'stockBook', action: 'stockPurchase')
        "/stockbook/productreturn/$id"(controller: "stockBook",action:"getStocksOfProductSaleReturn")
        "/stockbook/bulk-stock-save"(controller: 'stockBook',action: 'bulkStockBookSave')



//        Temp Stock Book
        "/tempstockbook"(controller: "stockBook") {
            action = [POST: 'tempStockBookSave']
        }
        "/tempstockbook/delete/$id"(controller: "stockBook") {
            action = [POST: 'deleteTempStock']
        }
        "/tempstockbook/product/$id"(controller: "stockBook",action:"getStocksOfProduct")
        "/tempstockbook/entity/$id"(controller: "stockBook",action:"getTempStocksOfEntity")
        "/tempstockbook/user/$id"(controller: "stockBook",action:"getTempStocksOfUser")

        "/tempstockbook/product/$id/batch/$batch"(controller: "stockBook",action:"getTempStocksOfProductAndBatch")
        "/tempstockbook/product/$id/"(controller: "stockBook",action:"getTempStocksOfProductAndBatch")


//        Stock Book
        "/stockbook"(controller: "stockBook") {action = [POST: 'stockBookSave']}
        "/stockbook/user/$id"(controller: "stockBook",action:"getStocksOfUser")
        "/stockbook/increase/"(controller: 'stockBook', action: 'stockIncrease')
        "/stockbook/product/$productId/batch/$batch"(controller: 'stockBook',action: 'stockByProductAndBatch')
//        Sale Order Entry
        "/sale-order-entry"(controller: "saleOrderEntry") {
            action = [GET: "index", POST: 'saveSaleOrder']
        }
        "/sale-order-entry/print-order"(controller: 'saleOrderEntry', action: 'printSaleOrder')
        "/sale-order-entry/datatable"(controller: 'saleOrderEntry', action: 'dataTable')
        "/sale-order-entry/my-orders"(controller: 'saleOrderEntry', action: 'saleOrderList')
        "/sale-order-entry/cancel"(controller: 'saleOrderEntry', action: 'cancelOrder')
        "/convert-to-sale-entry"(controller: 'saleOrderEntry', action: 'convertToSaleEntry')



        /*<-------------------------------------------Accounts------------------------------------------------->*/

        //Bank register
        "/bank-register"(controller: "bankRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/bank-register/datatable"(controller: "bankRegister", action: "dataTable")
        "/bank-register/update/$id"(controller:"bankRegister",action:"update")
        "/bank-register/delete/$id"(controller: "bankRegister",action:"delete")
        "/bank-register/product/$id"(controller: "bankRegister",action:"getByProduct")

//        ReciptDetail
        "/receipt-list"(controller: 'reciptDetail', action: 'reciptList')
        "/receipt"(controller: "reciptDetail") {
            action = [GET: "index", POST: "save"]
        }
        "/receipt-list/datatable"(controller: "reciptDetail", action: "dataTable")
        "/add-recipt"(controller: "reciptDetail", action: 'addRecipt')
        "/print-recipt/$custid/recipt/$id"(controller: "reciptDetail", action: 'printRecipt')
        "/receipt-approval"(controller: 'reciptDetail', action: 'recieptApproval')
        "/receipt-approve"(controller: 'reciptDetail', action: 'receiptApprove')
        "/receipt/cancel"(controller: 'reciptDetail', action: 'cancelReceipt')



        "/salesettledvocher/$id"(controller: "reciptDetail", action: 'settledVocher')
        "/saleunsettledvocher/$id"(controller: "reciptDetail", action: 'unsettledVocher')
        "/creditsettledvocher/$id"(controller: "reciptDetail", action: 'creditSettledVocher')
        "/creditunsettledvocher/$id"(controller: "reciptDetail", action: 'creditUnsettledVocher')


        "/getbyentity/$id"(controller: "reciptDetail", action: 'getAllEntityById')
        "/getallunsettledbycustomer/$id"(controller: "reciptDetail", action: 'getAllUNSaleBillCustomerId')
        "/getallsettledbycustomer/$id"(controller: "reciptDetail", action: 'getAllSaleBillsettled')


        "/updatesalebalance"(controller: 'reciptDetail', action: 'updateSaleBalance')

        "/updatesalereturnbalance"(controller: 'reciptDetail', action: 'updateSaleReturnBalance')

        "/updatereciptlog"(controller: 'reciptDetail',action: 'updateRecieptLog')

        "/recipts/getallbilldetails"(controller: 'reciptDetail', action: 'getAllBillDetailsByCustomerId')

//        Payment Details
        "/payments"(controller: "paymentDetail") { action = [GET: "index", POST: "save"]}
        "/payments-list/datatable"(controller: "paymentDetail", action: "dataTable")
        "/payments-list"(controller: "paymentDetail", action: 'paymentList')
        "/print-payment/$custid/payment/$id"(controller: "paymentDetail", action: 'printPayment')
        "/payments/getallbilldetails"(controller: 'paymentDetail', action: 'getAllBillDetailsBySupplierId')


        //reports
        "/reports/sales/customerwise"(controller: "salesReport", action: "index")
        "/reports/sales/getcustomerwise"(controller: "salesReport", action: "salesCustomerWiseReport")
        "/reports/sales/datewise"(controller: "salesReport", action: "datewise")
        "/reports/sales/getdatewise"(controller: "salesReport", action: "salesDateWiseReport")
        "/reports/sales/areawise"(controller: "salesReport", action: "areawise")
        "/reports/sales/getareawise"(controller: "salesReport", action: "salesAreaWiseReport")
        "/reports/sales/areawisewithproducts"(controller: "salesReport", action: "areawiseWithProducts")
        "/reports/sales/getareawisewithproducts"(controller: "salesReport", action: "areawiseWithProductsReport")
        "/reports/sales/areawiseconsolidatedproducts"(controller: "salesReport", action: "areawiseConsolidatedProducts")
        "/reports/sales/getareawiseconsolidatedproducts"(controller: "salesReport", action: "areawiseConsolidatedProductsReport")
        "/reports/sales/consolidated"(controller: "salesReport", action: "consolidated")
        "/reports/sales/getconsolidated"(controller: "salesReport", action: "saleConsolidatedReport")
        "/reports/sales/productwise"(controller: "salesReport", action: "saleProductWise")
        "/reports/sales/getproductwise"(controller: "salesReport", action: "saleProductWiseReport")
        "/reports/sales/gstreport"(controller: "salesReport", action: "saleProductWiseReport")
        "/reports/sales/gstreport"(controller: "salesReport", action: "salesGstReport")
        "/reports/sales/getgstreport"(controller: "salesReport", action: "getSalesGstReport")
        "/reports/sales/get-credit-note-gst-report"(controller: "salesReport", action: "getCreditNoteGstReport")
        "/reports/sales/invoice-payment-report"(controller: "salesReport", action: "invoicePaymentReports")
        "/reports/sales/get-invoice-payment-report"(controller: "salesReport", action: "getInvoicePaymentReport")

        "/reports/sales/get-customer-ledger"(controller: "salesReport", action: "getCustomerLedger")
        "/reports/sales/customer-ledger"(controller: "salesReport", action: "customerLedger")
        "/reports/sales/fastslowunsold"(controller: "salesReport", action: "fastSlowUnsoldProducts")
        "/reports/sales/getfastslowunsold"(controller: "salesReport", action: "getFastSlowUnsoldProducts")

        "/reports/accounts/outstanding"(controller: "accountsReport", action: "outstandingReport")
        "/reports/accounts/getoutstanding"(controller: "accountsReport", action: "getOutstandingReport")
        "/reports/accounts/payments"(controller: "accountsReport", action: "paymentReport")
        "/reports/accounts/getpayments"(controller: "accountsReport", action: "getPaymentReport")

        "/reports/inventory/statement"(controller: "inventoryReport", action: "statement")
        "/reports/inventory/getstatement"(controller: "inventoryReport", action: "getStatement")
        "/reports/inventory/expiry"(controller: "inventoryReport", action: "expiryReport")
        "/reports/inventory/getexpiry"(controller: "inventoryReport", action: "generateExpiryReport")
        "/reports/inventory/stockreport"(controller: "inventoryReport", action: "stockReport")
        "/reports/inventory/getstockreport"(controller: "inventoryReport", action: "getStockReport")

        "/reports/products/statement"(controller: "productReport", action: "productStatement")
        "/reports/products/getstatement"(controller: "productReport", action: "getProductStatement")
        "/reports/batches/statement"(controller: "productReport", action: "batchStatement")
        "/reports/batches/getreport"(controller: "productReport", action: "getBatchStatement")
        "/reports/products/price-list"(controller: "productReport", action: "priceList")
        "/reports/products/getpricelist"(controller: "productReport", action: "getPriceList")



        /*<-------------------------------------------Shipments------------------------------------------------->*/

        //Transport Type
        "/transport-type"(controller: "transportType") {
            action = [GET: "index", POST: "save"]
        }
        "/transport-type/datatable"(controller: "transportType", action: "dataTable")
        "/transport-type/update/$id"(controller:"transportType",action:"update")
        "/transport-type/delete/$id"(controller: "transportType",action:"delete")

        //Transporter
        "/transporter"(controller: "transporter") {
            action = [GET: "index", POST: "save"]
        }
        "/transporter/datatable"(controller: "transporter", action: "dataTable")
        "/transporter/update/$id"(controller:"transporter",action:"update")
        "/transporter/delete/$id"(controller: "transporter",action:"delete")

        //Vehicle Details
        "/vehicle-detail"(controller: "vehicleDetail") {
            action = [GET: "index", POST: "save"]
        }
        "/vehicle-detail/datatable"(controller: "vehicleDetail", action: "dataTable")
        "/vehicle-detail/update/$id"(controller:"vehicleDetail",action:"update")
        "/vehicle-detail/delete/$id"(controller: "vehicleDetail",action:"delete")


//       Credit-debit Settlement

        "/credit-debit-settlement"(controller: 'creditDebitSettlement', action: 'index')
        "/save-crdb-settlement"(controller: 'creditDebitSettlement',action: 'saveCrDbSettlement')
        "/credit-debit-settlement/print-crdb"(controller: 'creditDebitSettlement', action: 'printCrDbDetails')
        "/credit-debit-settlement/crdb-list"(controller: 'creditDebitSettlement',action: 'crdbList')
        "/get-all-crdb"(controller: 'creditDebitSettlement', action: 'dataTable')
        "/getall-bills-crdb"(controller: 'creditDebitSettlement', action: 'getAllBillDetailsByCustomerId')


//        sale entry retailer
        "/sale-entry-retailer"(controller: 'saleEntry', action: 'saleEntryRetailer')
        "/check-phone-exists"(controller: 'entityRegister', action: 'checkExistingPhone')
        "/savesaleRetailerEntry"(controller: 'saleEntry', action: 'saveRetailerSaleEntry')
        "/printRetailerEntry"(controller: 'saleEntry', action: 'printRetailerInvoice')
        "/patient-register"(controller: 'entityRegister', action: 'registerPatient')
        "/retailer-bill-list"(controller: 'salebillDetails', action: 'retailerBillDetails')


        //open document
        "/opendoc"(controller: 'openDocument', action: 'getLink')
        "/e/$uniqueCode"(controller: 'openDocument', action: 'decodeLink')

        //payment collection
        "/payment-collection"(controller: 'paymentCollection', action: 'index')
        "/payment-collection/get-invoices"(controller: 'paymentCollection', action: 'getPendingSaleInvoices')
        "/payment-collection/logs"(controller: 'paymentCollection', action: 'paymentCollectionLogs')
        "/payment-collection/getlogs"(controller: 'paymentCollection', action: 'dataTable')
        "/payment-collection/change-status"(controller: 'paymentCollection',action: 'paymentCollectionChangeStatus')
        "/payment-collection/finalize-approve"(controller: 'paymentCollection', action: 'approveAllPaymentCollection')
        "/payment-collection/bulk-update"(controller: 'paymentCollection',action: 'updateBulkPaymentCollection')

    }
}
