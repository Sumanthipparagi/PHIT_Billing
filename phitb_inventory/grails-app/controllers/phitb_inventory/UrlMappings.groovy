package phitb_inventory

import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

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
            //Stock Activity
            "/inventory/stockactivity(.$format)?"(controller: 'stockActivity') { action = [GET: 'index', POST: 'save'] }
            "/inventory/stockactivity/datatable(.$format)?"(controller: 'stockActivity') { action = [GET: 'dataTable'] }
            "/inventory/stockactivity/$id(.$format)?"(controller: 'stockActivity') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Stock Book
            "/inventory/stockbook(.$format)?"(controller: 'stockBook') { action = [GET: 'index', POST: 'save'] }
            "/inventory/stockbook/datatable(.$format)?"(controller: 'stockBook') { action = [GET: 'dataTable'] }
            "/inventory/stockbook/$id(.$format)?"(controller: 'stockBook') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Temp Stock Book
            "/inventory/tempstockbook(.$format)?"(controller: 'tempStockBook') { action = [GET: 'index', POST: 'save'] }
            "/inventory/tempstockbook/datatable(.$format)?"(controller: 'tempStockBook') { action = [GET: 'dataTable'] }
            "/inventory/tempstockbook/$id(.$format)?"(controller: 'tempStockBook') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }


        }
    }
}
