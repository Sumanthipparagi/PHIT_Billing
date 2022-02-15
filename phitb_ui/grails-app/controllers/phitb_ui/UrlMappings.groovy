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

        /*<-------------------------------------------State ------------------------------------------------->*/
        "/state"(controller: "state") {
            action = [GET: 'index', POST: 'save']
        }
        "/state/datatable"(controller: "state", action: 'dataTable')
        "/state/update/$id"(controller: "state",action:"update")
        "/state/delete/$id"(controller: "state",action:"delete")

        /*<-------------------------------------------City ------------------------------------------------->*/

        "/city"(controller: "city") {
            action = [GET: 'index', POST: 'save']
        }
        "/city/datatable"(controller: "city", action: 'dataTable')
        "/city/update/$id"(controller: "city",action:"update")
        "/city/delete/$id"(controller: "city",action:"delete")



        /*<-------------------------------------------Country ------------------------------------------------->*/

        "/country"(controller: "country") {
            action = [GET: 'index', POST: 'save']
        }
        "/country/datatable"(controller: "country", action: 'dataTable')
        "/country/update/$id"(controller: "country",action:"update")
        "/country/delete/$id"(controller: "country",action:"delete")


        /*<-------------------------------------------Form Master ------------------------------------------------->*/

        "/form"(controller: "form") {
            action = [GET: 'index', POST: 'save']
        }
        "/form/datatable"(controller: "form", action: 'dataTable')
        "/form/update/$id"(controller: "form",action:"update")
        "/form/delete/$id"(controller: "form",action:"delete")

        /*<-------------------------------------------Facility ------------------------------------------------->*/
        /*<-------------------------------------------Fridge ------------------------------------------------->*/
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

    }
}
