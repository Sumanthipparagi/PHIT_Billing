package phitb_facility

class UrlMappings {

    static mappings = {
        //delete "/$controller/$id(.$format)?"(action: "delete")
        //get "/$controller(.$format)?"(action: "index")
        //get "/$controller/$id(.$format)?"(action: "show")
        //post "/$controller(.$format)?"(action: "save")
        //put "/$controller/$id(.$format)?"(action: "update")
        //patch "/$controller/$id(.$format)?"(action: "patch")

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(controller: "error", action: "error404", exception: ResourceNotFoundException)
        "400"(controller: "error", action: "error400", exception: BadRequestException)

        group "/api/v1.0", {
            //Racks
            "/facility/rack(.$format)?"(controller: 'rackMaster') { action = [GET: 'index', POST: 'save'] }
            "/facility/rack(.$format)/$query/$value"(controller: 'rackMaster') { action = [GET: 'index', POST: 'save'] }
            "/facility/rack/datatable(.$format)?"(controller: 'rackMaster') { action = [GET: 'dataTable'] }
            "/facility/rack/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Fridges
            "/facility/fridge(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'index', POST: 'save'] }
            "/facility/fridge(.$format)/$query/$value"(controller: 'fridgeMaster') { action = [GET: 'index', POST: 'save'] }
            "/facility/fridge/datatable(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'dataTable'] }
            "/facility/fridge/$id(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //CCM Register
            "/facility/ccmregister(.$format)?"(controller: 'ccmRegister') { action = [GET: 'index', POST: 'save'] }
            "/facility/ccmregister(.$format)/$query/$value"(controller: 'ccmRegister') { action = [GET: 'index', POST: 'save'] }
            "/facility/ccmregister/datatable(.$format)?"(controller: 'ccmRegister') { action = [GET: 'dataTable'] }
            "/facility/ccmregister/$id(.$format)?"(controller: 'ccmRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Godown
            "/facility/godown(.$format)?"(controller: 'godownRegister') { action = [GET: 'index', POST: 'save'] }
            "/facility/godown(.$format)/$query/$value"(controller: 'godownRegister') { action = [GET: 'index', POST: 'save'] }
            "/facility/godown/datatable(.$format)?"(controller: 'godownRegister') { action = [GET: 'dataTable'] }
            "/facility/godown/$id(.$format)?"(controller: 'godownRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
        }
    }
}
