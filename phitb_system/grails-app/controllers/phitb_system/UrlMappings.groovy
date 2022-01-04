package phitb_system

import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

class UrlMappings {

    static mappings = {
        //delete "/$controller/$id(.$format)?"(action: "delete")
        //post "/$controller(.$format)?"(action: "save")
        //get "/$controller(.$format)?"(action: "index")
        //get "/$controller/$id(.$format)?"(action: "show")
        //put "/$controller/$id(.$format)?"(action: "update")

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(controller: "error", action: "error404", exception: ResourceNotFoundException)
        "400"(controller: "error", action: "error400", exception: BadRequestException)

        group "/api/v1.0", {
            //Account Modes
            "/accountmodes(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'index', POST: 'save'] }
            "/accountmodes/datatable(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'dataTable'] }
            "/accountmodes/$id(.$format)?"(controller: 'accountModesMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Account Type
            "/accounttype(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'index', POST: 'save'] }
            "/accounttype/datatable(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'dataTable'] }
            "/accounttype/$id(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
        }
    }
}