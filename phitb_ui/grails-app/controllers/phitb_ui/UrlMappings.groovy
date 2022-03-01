package phitb_ui

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(controller: 'auth', action: 'index')
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/auth"(controller: "auth", action: 'index')
        "/dashboard"(controller: 'dashboard', action: 'index')
        "/forms"(controller: 'dashboard', action: 'forms')
        "/table"(controller: 'dashboard', action: 'table')
        "/timeline"(controller: 'dashboard', action: 'timeline')
/*<-------------------------------------------System ------------------------------------------------->*/

        "/accountmodes"(controller: "accountMode") {
            action = [GET: 'index', POST: 'save']
        }
        "/accountmodes/datatable"(controller: "accountMode", action: 'dataTable')
        "/accountmodes/getallentity"(controller: "accountMode", action: 'getAllEntity')
        "/accountmodes/update/$id"(controller: "accountMode",action:"update")
        "/accountmodes/delete/$id"(controller: "accountMode",action:"delete")

//        State
        "/state"(controller: "state") {
            action = [GET: 'index', POST: 'save']
        }
        "/state/datatable"(controller: "state", action: 'dataTable')
        "/state/update/$id"(controller: "state",action:"update")
        "/state/delete/$id"(controller: "state",action:"delete")

//      City
        "/city"(controller: "city") {
            action = [GET: 'index', POST: 'save']
        }
        "/city/datatable"(controller: "city", action: 'dataTable')
        "/city/update/$id"(controller: "city",action:"update")
        "/city/delete/$id"(controller: "city",action:"delete")



//      Country
        "/country"(controller: "country") {
            action = [GET: 'index', POST: 'save']
        }
        "/country/datatable"(controller: "country", action: 'dataTable')
        "/country/update/$id"(controller: "country",action:"update")
        "/country/delete/$id"(controller: "country",action:"delete")


//      Form Master
        "/form"(controller: "form") {
            action = [GET: 'index', POST: 'save']
        }
        "/form/datatable"(controller: "form", action: 'dataTable')
        "/form/update/$id"(controller: "form",action:"update")
        "/form/delete/$id"(controller: "form",action:"delete")

        /*<-------------------------------------------Facility ------------------------------------------------->*/

//        Fridge
        "/fridge"(controller: "fridge") {
            action = [GET: 'index', POST: 'save']
        }
        "/fridge/datatable"(controller: "fridge", action: 'dataTable')
        "/fridge/update/$id"(controller: "fridge",action:"update")
        "/fridge/delete/$id"(controller: "fridge",action:"delete")

//        ccm

        "/ccm"(controller: "ccm") {
            action = [GET: 'index', POST: 'save']
        }
        "/ccm/datatable"(controller: "ccm", action: 'dataTable')
        "/ccm/update/$id"(controller: "ccm",action:"update")
        "/cmm/delete/$id"(controller: "ccm",action:"delete")

        //        godown

        "/godown"(controller: "godown") {
            action = [GET: 'index', POST: 'save']
        }
        "/godown/datatable"(controller: "godown", action: 'dataTable')
        "/godown/update/$id"(controller: "godown",action:"update")
        "/godown/delete/$id"(controller: "godown",action:"delete")


        //        Rack
        "/rack"(controller: "rack") {
            action = [GET: 'index', POST: 'save']
        }
        "/rack/datatable"(controller: "rack", action: 'dataTable')
        "/rack/update/$id"(controller:"rack",action:"update")
        "/rack/delete/$id"(controller: "rack",action:"delete")

        /*<-------------------------------------------Entity ------------------------------------------------->*/

        "/entity-register"(controller: "entityRegister") {
            action = [GET: 'index', POST: 'save']
        }
        "/entity-register/add-entity-register"(controller: "entityRegister", action: 'addEntity')
        "/entity-register/update-entity-register/$id"(controller: "entityRegister", action: 'updateEntity')
        "/entity-register/datatable"(controller: "entityRegister", action: 'dataTable')
        "/entity-register/update/$id"(controller:"entityRegister",action:"update")
        "/entity-register/delete/$id"(controller: "entityRegister",action:"delete")


//      User Register
        "/user-register"(controller: "userRegister") {
            action = [GET: 'index', POST: 'save']
        }
        "/user-register/add-user-register"(controller: "userRegister", action: 'addUser')
        "/user-register/update-user-register/$id"(controller: "userRegister", action: 'updateUser')
        "/user-register/datatable"(controller: "userRegister", action: 'dataTable')
        "/user-register/update/$id"(controller:"userRegister",action:"update")
        "/user-register/delete/$id"(controller: "userRegister",action:"delete")


        //      Customer Group register
        "/customer-group-register"(controller: "customerGroup") {
            action = [GET: 'index', POST: 'save']
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/customer-group-register/datatable"(controller: "customerGroup", action: 'dataTable')
        "/customer-group-register/update/$id"(controller:"customerGroup",action:"update")
        "/customer-group-register/delete/$id"(controller: "customerGroup",action:"delete")


        //     Day End Master
        "/day-end-master"(controller: "dayEnd") {
            action = [GET: 'index', POST: 'save']
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/day-end-master/datatable"(controller: "dayEnd", action: 'dataTable')
        "/day-end-master/update/$id"(controller:"dayEnd",action:"update")
        "/day-end-master/delete/$id"(controller: "dayEnd",action:"delete")


        //    Financial Year Master
        "/financial-year-master"(controller: "financialYear") {
            action = [GET: 'index', POST: 'save']
        }
        "/financial-year-master/datatable"(controller: "financialYear", action: 'dataTable')
        "/financial-year-master/update/$id"(controller:"financialYear",action:"update")
        "/financial-year-master/delete/$id"(controller: "financialYear",action:"delete")

        //   Region Register
        "/region-master"(controller: "regionMaster") {
            action = [GET: 'index', POST: 'save']
        }
        "/region-master/datatable"(controller: "regionMaster", action: 'dataTable')
        "/region-master/update/$id"(controller:"regionMaster",action:"update")
        "/region-master/delete/$id"(controller: "regionMaster",action:"delete")


        //   Route Register
        "/route-regitser"(controller: "route") {
            action = [GET: 'index', POST: 'save']
        }
        "/route-regitser/datatable"(controller: "route", action: 'dataTable')
        "/route-regitser/update/$id"(controller:"route",action:"update")
        "/route-regitser/delete/$id"(controller: "route",action:"delete")


        //   Role
        "/role"(controller: "role") {
            action = [GET: 'index', POST: 'save']
        }
        "/role/datatable"(controller: "role", action: 'dataTable')
        "/role/update/$id"(controller:"role",action:"update")
        "/role/delete/$id"(controller: "role",action:"delete")

        //   Rule
        "/rule"(controller: "rule") {
            action = [GET: 'index', POST: 'save']
        }
        "/rule/datatable"(controller: "rule", action: 'dataTable')
        "/rule/update/$id"(controller:"rule",action:"update")
        "/rule/delete/$id"(controller: "rule",action:"delete")


        //   Tax
        "/tax"(controller: "tax") {
            action = [GET: 'index', POST: 'save']
        }
        "/tax/datatable"(controller: "tax", action: 'dataTable')
        "/tax/update/$id"(controller:"tax",action:"update")
        "/tax/delete/$id"(controller: "tax",action:"delete")


        //   Territory
        "/territory"(controller: "territory") {
            action = [GET: 'index', POST: 'save']
        }
        "/territory/datatable"(controller: "territory", action: 'dataTable')
        "/territory/update/$id"(controller:"territory",action:"update")
        "/territory/delete/$id"(controller: "territory",action:"delete")

        //   Terms Conditions
        "/terms-conditions"(controller: "termsCondition") {
            action = [GET: 'index', POST: 'save']
        }
        "/terms-conditions/datatable"(controller: "termsCondition", action: 'dataTable')
        "/terms-conditions/update/$id"(controller:"termsCondition",action:"update")
        "/terms-conditions/delete/$id"(controller: "termsCondition",action:"delete")


        //   Series
        "/series"(controller: "series") {
            action = [GET: 'index', POST: 'save']
        }
        "/series/datatable"(controller: "series", action: 'dataTable')
        "/series/update/$id"(controller:"series",action:"update")
        "/series/delete/$id"(controller: "series",action:"delete")


        //   Service type
        "/service-type"(controller: "serviceType") {
            action = [GET: 'index', POST: 'save']
        }
        "/service-type/datatable"(controller: "serviceType", action: 'dataTable')
        "/service-type/update/$id"(controller:"serviceType",action:"update")
        "/service-type/delete/$id"(controller: "serviceType",action:"delete")

        /*<-------------------------------------------Product------------------------------------------------->*/
//      Product Register
        "/phitb_ui.product"(controller: "phitb_ui.product") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product/add-phitb_ui.product"(controller: "phitb_ui.product", action: 'addProduct')
        "/phitb_ui.product/update-phitb_ui.product/$id"(controller: "phitb_ui.product", action: 'updateProduct')
        "/phitb_ui.product/datatable"(controller: "phitb_ui.product", action: 'dataTable')
        "/phitb_ui.product/update/$id"(controller:"phitb_ui.product",action:"update")
        "/phitb_ui.product/delete/$id"(controller: "phitb_ui.product",action:"delete")


        //      Division
        "/division"(controller: "division") {
            action = [GET: 'index', POST: 'save']
        }
        "/division/datatable"(controller: "division", action: 'dataTable')
        "/division/update/$id"(controller:"division",action:"update")
        "/division/delete/$id"(controller: "division",action:"delete")


        // Product Category
        "/phitb_ui.product-category"(controller: "productCategory") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-category/datatable"(controller: "productCategory", action: 'dataTable')
        "/phitb_ui.product-category/update/$id"(controller:"productCategory",action:"update")
        "/phitb_ui.product-category/delete/$id"(controller: "productCategory",action:"delete")

        // Product Schedule
        "/phitb_ui.product-schedule"(controller: "productSchedule") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-schedule/datatable"(controller: "productSchedule", action: 'dataTable')
        "/phitb_ui.product-schedule/update/$id"(controller:"productSchedule",action:"update")
        "/phitb_ui.product-schedule/delete/$id"(controller: "productSchedule",action:"delete")


        // Product Composition
        "/phitb_ui.product-composition"(controller: "composition") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-composition/datatable"(controller: "composition", action: 'dataTable')
        "/phitb_ui.product-composition/update/$id"(controller:"composition",action:"update")
        "/phitb_ui.product-composition/delete/$id"(controller: "composition",action:"delete")


        // Product Type
        "/phitb_ui.product-type"(controller: "productType") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-type/datatable"(controller: "productType", action: 'dataTable')
        "/phitb_ui.product-type/update/$id"(controller:"productType",action:"update")
        "/phitb_ui.product-type/delete/$id"(controller: "productType",action:"delete")


        // Product group
        "/phitb_ui.product-group"(controller: "productGroup") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-group/datatable"(controller: "productGroup", action: 'dataTable')
        "/phitb_ui.product-group/update/$id"(controller:"productGroup",action:"update")
        "/phitb_ui.product-group/delete/$id"(controller: "productGroup",action:"delete")


        //Unit Type
        "/unit-type"(controller: "unitType") {
            action = [GET: 'index', POST: 'save']
        }
        "/unit-type/datatable"(controller: "unitType", action: 'dataTable')
        "/unit-type/update/$id"(controller:"unitType",action:"update")
        "/unit-type/delete/$id"(controller: "unitType",action:"delete")


        //Division Group register
        "/division-group"(controller: "divisionGroup") {
            action = [GET: 'index', POST: 'save']
        }
        "/division-group/datatable"(controller: "divisionGroup", action: 'dataTable')
        "/division-group/update/$id"(controller:"divisionGroup",action:"update")
        "/division-group/delete/$id"(controller: "divisionGroup",action:"delete")


        //Product Class
        "/phitb_ui.product-class"(controller: "productClass") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-class/datatable"(controller: "productClass", action: 'dataTable')
        "/phitb_ui.product-class/update/$id"(controller:"productClass",action:"update")
        "/phitb_ui.product-class/delete/$id"(controller: "productClass",action:"delete")



        //Product cost range
        "/phitb_ui.product-cost-range"(controller: "productCostRange") {
            action = [GET: 'index', POST: 'save']
        }
        "/phitb_ui.product-cost-range/datatable"(controller: "productCostRange", action: 'dataTable')
        "/phitb_ui.product-cost-range/update/$id"(controller:"productCostRange",action:"update")
        "/phitb_ui.product-cost-range/delete/$id"(controller: "productCostRange",action:"delete")


        //Batch register
        "/batch-register"(controller: "batchRegister") {
            action = [GET: 'index', POST: 'save']
        }
        "/batch-register/datatable"(controller: "batchRegister", action: 'dataTable')
        "/batch-register/update/$id"(controller:"batchRegister",action:"update")
        "/batch-register/delete/$id"(controller: "batchRegister",action:"delete")
        "/batch-register/phitb_ui.product/$id"(controller: "batchRegister",action:"getByProduct")


        /*<-------------------------------------------Sales------------------------------------------------->*/

        //Sale Entry
        "/sale-entry"(controller: "saleEntry") {
            action = [GET: 'index', POST: 'save']
        }
        "/sale-entry/update/$id"(controller:"batchRegister",action:"update")
//        "/sale-entry/datatable"(controller: "batchRegister", action: 'dataTable')

//        "/sale-entry/delete/$id"(controller: "batchRegister",action:"delete")

        //Inventory
        "/stockbook/phitb_ui.product/$id"(controller: "stockBook",action:"getStocksOfProduct")

    }
}
