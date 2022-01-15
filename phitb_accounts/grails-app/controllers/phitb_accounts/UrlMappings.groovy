package phitb_accounts

import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

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

            //bank register
            "/accounts/bankregister(.$format)?"(controller: 'bankRegister') { action = [GET: 'index', POST: 'save'] }
            "/accounts/bankregister/datatable(.$format)?"(controller: 'bankRegister') { action = [GET: 'dataTable'] }
            "/accounts/bankregister/$id(.$format)?"(controller: 'bankRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

        }
    }
}
