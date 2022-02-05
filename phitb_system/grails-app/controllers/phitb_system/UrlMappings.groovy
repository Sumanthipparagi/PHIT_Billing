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
            "/system/accountmodes(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/accountmodes/datatable(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'dataTable'] }
            "/system/accountmodes/$id(.$format)?"(controller: 'accountModeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/accountmodesbyenity/$id(.$format)?"(controller: 'accountModeMaster') { action = [GET:
                                                                                                        'getAllByEntityId'] }

            //Account Type
            "/system/accounttype(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/accounttype/datatable(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'dataTable'] }
            "/system/accounttype/$id(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/accounttypebyentity/$id(.$format)?"(controller: 'accountTypeMaster') { action = [GET: 'getAllByEntityId'] }

            //Country
            "/system/country(.$format)?"(controller: 'countryMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/country/datatable(.$format)?"(controller: 'countryMaster') { action = [GET: 'dataTable'] }
            "/system/country/$id(.$format)?"(controller: 'countryMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/countrybyentity/$id(.$format)?"(controller: 'countryMaster') { action = [GET: 'getAllByEntityId'] }

            //Day
            "/system/day(.$format)?"(controller: 'dayMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/day/datatable(.$format)?"(controller: 'dayMaster') { action = [GET: 'dataTable'] }
            "/system/day/$id(.$format)?"(controller: 'dayMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/daybyentity/$id(.$format)?"(controller: 'dayMaster') { action = [GET: 'getAllByEntityId'] }


            //Form Master
            "/system/form(.$format)?"(controller: 'formMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/form/datatable(.$format)?"(controller: 'formMaster') { action = [GET: 'dataTable'] }
            "/system/form/$id(.$format)?"(controller: 'formMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/formbyentity/$id(.$format)?"(controller: 'formMaster') { action = [GET: 'getAllByEntityId'] }


            //Gender Master
            "/system/gender(.$format)?"(controller: 'genderMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/gender/datatable(.$format)?"(controller: 'genderMaster') { action = [GET: 'dataTable'] }
            "/system/gender/$id(.$format)?"(controller: 'genderMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/genderbyentity/$id(.$format)?"(controller: 'genderMaster') { action = [GET: 'getAllByEntityId'] }


            //Priority Master
            "/system/priority(.$format)?"(controller: 'priorityMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/priority/datatable(.$format)?"(controller: 'priorityMaster') { action = [GET: 'dataTable'] }
            "/system/priority/$id(.$format)?"(controller: 'priorityMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/prioritybyentity/$id(.$format)?"(controller: 'priorityMaster') { action = [GET: 'getAllByEntityId'] }


            //Zone Master
            "/system/zone(.$format)?"(controller: 'zoneMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/zone/datatable(.$format)?"(controller: 'zoneMaster') { action = [GET: 'dataTable'] }
            "/system/zone/$id(.$format)?"(controller: 'zoneMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/zonebyentity/$id(.$format)?"(controller: 'zoneMaster') { action = [GET: 'getAllByEntityId'] }


            //City Master
            "/system/city(.$format)?"(controller: 'cityMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/city/datatable(.$format)?"(controller: 'cityMaster') { action = [GET: 'dataTable'] }
            "/system/city/$id(.$format)?"(controller: 'cityMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/countrybyentity/$id(.$format)?"(controller: 'cityMaster') { action = [GET: 'getAllByEntityId'] }
            "/system/citybyentity/$id(.$format)?"(controller: 'cityMaster') { action = [GET: 'getAllByEntityId'] }



            //State Master
            "/system/state(.$format)?"(controller: 'stateMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/state/datatable(.$format)?"(controller: 'stateMaster') { action = [GET: 'dataTable'] }
            "/system/state/$id(.$format)?"(controller: 'stateMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/statebyentity/$id(.$format)?"(controller: 'stateMaster') { action = [GET: 'getAllByEntityId'] }


            //Sub Account Type Master
            "/system/subaccounttype(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/subaccounttype/datatable(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'dataTable'] }
            "/system/subaccounttype/$id(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/subaccounttypebyentity/$id(.$format)?"(controller: 'subAccountTypeMaster') { action = [GET: 'getAllByEntityId'] }


            //Payment Mode Master
            "/system/paymentmode(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'index', POST: 'save'] }
            "/system/paymentmode/datatable(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'dataTable'] }
            "/system/paymentmode/$id(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/system/paymentmodebyentity/$id(.$format)?"(controller: 'paymentModeMaster') { action = [GET: 'getAllByEntityId'] }

        }
    }
}