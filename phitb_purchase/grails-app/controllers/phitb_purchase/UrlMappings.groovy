package phitb_purchase

import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

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
            //Purchase Bill Detail
            "/purchase/billdetail(.$format)?"(controller: 'stockActivity') { action = [GET: 'index', POST: 'save'] }
            "/purchase/billdetail/datatable(.$format)?"(controller: 'stockActivity') { action = [GET: 'dataTable'] }
            "/purchase/billdetail/$id(.$format)?"(controller: 'stockActivity') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Bill Detail
            "/purchase/purchaseorder(.$format)?"(controller: 'stockActivity') { action = [GET: 'index', POST: 'save'] }
            "/purchase/purchaseorder/datatable(.$format)?"(controller: 'stockActivity') { action = [GET: 'dataTable'] }
            "/purchase/purchaseorder/$id(.$format)?"(controller: 'stockActivity') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

        }
    }
}
