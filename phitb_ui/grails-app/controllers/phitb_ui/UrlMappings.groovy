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



    }
}
