package phitb_facility

import phitb_facility.Exception.BadRequestException
import phitb_facility.Exception.ResourceNotFoundException

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

        group "/api/v1.0/facility", {

            //Racks
            "/rack(.$format)?"(controller: 'rackMaster') { action = [GET: 'index', POST: 'save'] }
            "/rack(.$format)/$query/$value"(controller: 'rackMaster') { action = [GET: 'index', POST: 'save'] }
            "/rack/datatable(.$format)?"(controller: 'rackMaster') { action = [GET: 'dataTable'] }
            "/rack/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/rackbyentity/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'getByEntityId'] }

            //Fridges
            "/fridge(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'index', POST: 'save'] }
            "/fridge(.$format)/$query/$value"(controller: 'fridgeMaster') { action = [GET: 'index', POST: 'save'] }
            "/fridge/datatable(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'dataTable'] }
            "/fridge/$id(.$format)?"(controller: 'fridgeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/fridgebyentity/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'getByEntityId'] }

            //CCM Register
            "/ccmregister(.$format)?"(controller: 'ccmRegister') { action = [GET: 'index', POST: 'save'] }
            "/ccmregister(.$format)/$query/$value"(controller: 'ccmRegister') { action = [GET: 'index', POST: 'save'] }
            "/ccmregister/datatable(.$format)?"(controller: 'ccmRegister') { action = [GET: 'dataTable'] }
            "/ccmregister/$id(.$format)?"(controller: 'ccmRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/ccmregisterbyentity/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'getByEntityId'] }

            //Godown
            "/godown(.$format)?"(controller: 'godownRegister') { action = [GET: 'index', POST: 'save'] }
            "/godown(.$format)/$query/$value"(controller: 'godownRegister') { action = [GET: 'index', POST: 'save'] }
            "/godown/datatable(.$format)?"(controller: 'godownRegister') { action = [GET: 'dataTable'] }
            "/godown/$id(.$format)?"(controller: 'godownRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/godownbyentity/$id(.$format)?"(controller: 'rackMaster') { action = [GET: 'getByEntityId'] }

            "/status"(controller: 'status', action: 'index')
        }
    }
}
