package phitb_system

class UrlMappings {

    static mappings = {
        //delete "/$controller/$id(.$format)?"(action: "delete")
        //post "/$controller(.$format)?"(action: "save")
        //get "/$controller(.$format)?"(action: "index")
        //get "/$controller/$id(.$format)?"(action: "show")
        //put "/$controller/$id(.$format)?"(action: "update")

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
        "400"(view: '/clientError')

        group "/api/v1.0", {
            //Account Modes
            "/accountmodes(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'index', POST: 'save'] }
            "/accountmodes/datatable(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'dataTable'] }
            "/accountmodes/$id(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
        }
    }
}