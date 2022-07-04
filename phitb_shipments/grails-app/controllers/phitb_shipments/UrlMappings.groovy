package phitb_shipments

import phitb_shipments.Exception.BadRequestException
import phitb_shipments.Exception.ResourceNotFoundException

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

        group "/api/v1.0/shipments", {
            //Transport Type
            "/transporttype(.$format)?"(controller: 'transportType') { action = [GET: 'index', POST: 'save'] }
            "/transporttype/datatable(.$format)?"(controller: 'transportType') { action = [GET: 'dataTable'] }
            "/transporttype/$id(.$format)?"(controller: 'transportType') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Vehicle Detail
            "/vehicledetail(.$format)?"(controller: 'vehicleDetail') { action = [GET: 'index', POST: 'save'] }
            "/vehicledetail/datatable(.$format)?"(controller: 'vehicleDetail') { action = [GET: 'dataTable'] }
            "/vehicledetail/$id(.$format)?"(controller: 'vehicleDetail') { action = [GET: 'show', PUT: 'update',
                                                                                     DELETE: 'delete'] }

//           Status
            "/status"(controller: 'status', action: 'index')

        }
    }
}
