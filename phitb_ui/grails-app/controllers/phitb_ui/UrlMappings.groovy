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
    }
}
