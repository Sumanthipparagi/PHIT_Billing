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
            "/accountmodes(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'index', POST: 'save'] }
            "/accountmodes/datatable(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'dataTable'] }
            "/accountmodes/$id(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Account Type
            "/accounttype(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'index', POST: 'save'] }
            "/accounttype/datatable(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'dataTable'] }
            "/accounttype/$id(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Country
            "/country(.$format)?"(controller: 'countryMaster') { action = [GET: 'index', POST: 'save'] }
            "/country/datatable(.$format)?"(controller: 'countryMaster') { action = [GET: 'dataTable'] }
            "/country/$id(.$format)?"(controller: 'countryMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Day
            "/day(.$format)?"(controller: 'dayMaster') { action = [GET: 'index', POST: 'save'] }
            "/day/datatable(.$format)?"(controller: 'dayMaster') { action = [GET: 'dataTable'] }
            "/day/$id(.$format)?"(controller: 'dayMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Form Master
            "/form(.$format)?"(controller: 'formMaster') { action = [GET: 'index', POST: 'save'] }
            "/form/datatable(.$format)?"(controller: 'formMaster') { action = [GET: 'dataTable'] }
            "/form/$id(.$format)?"(controller: 'formMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Gender Master
            "/gender(.$format)?"(controller: 'genderMaster') { action = [GET: 'index', POST: 'save'] }
            "/gender/datatable(.$format)?"(controller: 'genderMaster') { action = [GET: 'dataTable'] }
            "/gender/$id(.$format)?"(controller: 'genderMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Priority Master
            "/priority(.$format)?"(controller: 'priorityMaster') { action = [GET: 'index', POST: 'save'] }
            "/priority/datatable(.$format)?"(controller: 'priorityMaster') { action = [GET: 'dataTable'] }
            "/priority/$id(.$format)?"(controller: 'priorityMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Zone Master
            "/zone(.$format)?"(controller: 'zoneMaster') { action = [GET: 'index', POST: 'save'] }
            "/zone/datatable(.$format)?"(controller: 'zoneMaster') { action = [GET: 'dataTable'] }
            "/zone/$id(.$format)?"(controller: 'zoneMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //City Master
            "/city(.$format)?"(controller: 'cityMaster') { action = [GET: 'index', POST: 'save'] }
            "/city/datatable(.$format)?"(controller: 'cityMaster') { action = [GET: 'dataTable'] }
            "/city/$id(.$format)?"(controller: 'cityMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //State Master
            "/state(.$format)?"(controller: 'stateMaster') { action = [GET: 'index', POST: 'save'] }
            "/state/datatable(.$format)?"(controller: 'stateMaster') { action = [GET: 'dataTable'] }
            "/state/$id(.$format)?"(controller: 'stateMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Sub Account Type Master
            "/subaccounttype(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'index', POST: 'save'] }
            "/subaccounttype/datatable(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'dataTable'] }
            "/subaccounttype/$id(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Payment Mode Master
            "/paymentmode(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'index', POST: 'save'] }
            "/paymentmode/datatable(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'dataTable'] }
            "/paymentmode/$id(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
        }
    }
}