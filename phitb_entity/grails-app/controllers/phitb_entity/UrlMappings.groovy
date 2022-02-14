package phitb_entity

import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

class UrlMappings
{

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

            //Account register
            "/accountregister(.$format)?"(controller: 'accountRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/accountregister/datatable(.$format)?"(controller: 'accountRegister') {action = [GET: 'dataTable']}
            "/accountregister/$id(.$format)?"(controller: 'accountRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Auth register
            "/authregister(.$format)?"(controller: 'authRegister') {action = [GET: 'index', POST: 'save']}
            "/authregister/datatable(.$format)?"(controller: 'authRegister') {action = [GET: 'dataTable']}
            "/authregister/$id(.$format)?"(controller: 'authRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Customer Group register
            "/customergroupregister(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/customergroupregister/datatable(.$format)?"(controller: 'customerGroupRegister') {action = [GET: 'dataTable']}
            "/customergroupregister/$id(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/customergroupregisterbyentity/$id(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Day End Master
            "/dayendmaster(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/dayendmaster/datatable(.$format)?"(controller: 'dayEndMaster') {action = [GET: 'dataTable']}
            "/dayendmaster/$id(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/dayendmasterbyentity/$id(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Department Master
            "/departmentmaster(.$format)?"(controller: 'departmentMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/departmentmaster/datatable(.$format)?"(controller: 'departmentMaster') {action = [GET: 'dataTable']}
            "/departmentmaster/$id(.$format)?"(controller: 'departmentMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Entity register
            "/entityregister(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entityregister/datatable(.$format)?"(controller: 'entityRegister') {action = [GET: 'dataTable']}
            "/entityregister/$id(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'show', PUT: 'update', DELETE: 'delete']
            }
            "/entityregisterbyentitytype/$id(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'getAllByEntityTypeId']
            }


            //Entity Type Master
            "/entitytypemaster(.$format)?"(controller: 'entityTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entitytypemaster/datatable(.$format)?"(controller: 'entityTypeMaster') {action = [GET: 'dataTable']}
            "/entitytypemaster/$id(.$format)?"(controller: 'entityTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }




            //Financial Year Master
            "/financialyearmaster(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/financialyearmaster/datatable(.$format)?"(controller: 'financialYearMaster') {action = [GET:
                                                                                                                'dataTable']}
            "/financialyearmaster/$id(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/financialyearmasterbyentity/$id(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Region Register
            "/regionregister(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/regionregister/datatable(.$format)?"(controller: 'regionRegister') {action = [GET: 'dataTable']}
            "/regionregister/$id(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/regionregisterbyentity/$id(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Role Form Mapping
            "/roleformmapping(.$format)?"(controller: 'roleFormMapping') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/roleformmapping/datatable(.$format)?"(controller: 'roleFormMapping') {action = [GET: 'dataTable']}
            "/roleformmapping/$id(.$format)?"(controller: 'roleFormMapping') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Role Master
            "/rolemaster(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/rolemaster/datatable(.$format)?"(controller: 'roleMaster') {action = [GET: 'dataTable']}
            "/rolemaster/$id(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/rolemasterbyentity/$id(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'getAllByEntityId']
            }


            //Route register Controller
            "/routeregister(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/routeregister/datatable(.$format)?"(controller: 'routeRegister') {action = [GET: 'dataTable']}
            "/routeregister/$id(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/routeregisterbyentity/$id(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Rule Master Controller
            "/rulemaster(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/rulemaster/datatable(.$format)?"(controller: 'ruleMaster') {action = [GET: 'dataTable']}
            "/rulemaster/$id(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/rulemasterbyentity/$id(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Series Master
            "/seriesmaster(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/seriesmaster/datatable(.$format)?"(controller: 'seriesMaster') {action = [GET: 'dataTable']}
            "/seriesmaster/$id(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/seriesmasterbyentity/$id(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'getAllByEntityId']
            }


            //Tax Register
            "/taxregister(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/taxregister/datatable(.$format)?"(controller: 'taxRegister') {action = [GET: 'dataTable']}
            "/taxregister/$id(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/taxregisterbyentity/$id(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'getAllByEntityId']
            }



            //Terms Condition Details
            "/termconditiondetails(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/termconditiondetails/datatable(.$format)?"(controller: 'termsConditionDetails') {action = [GET: 'dataTable']}
            "/termconditiondetails/$id(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/termconditiondetailsbyentity/$id(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'getAllByEntityId']
            }


            //Territory Register
            "/territoryregister(.$format)?"(controller: 'territoryRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/territoryregister/datatable(.$format)?"(controller: 'territoryRegister') {action = [GET: 'dataTable']}
            "/territoryregister/$id(.$format)?"(controller: 'territoryRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Transaction Type Master
            "/transactiontype(.$format)?"(controller: 'transactionTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/transactiontype/datatable(.$format)?"(controller: 'transactionTypeMaster') {action = [GET: 'dataTable']}
            "/transactiontype/$id(.$format)?"(controller: 'transactionTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //User Log Info
            "/userloginfo(.$format)?"(controller: 'userLogInfo') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/userloginfo/datatable(.$format)?"(controller: 'userLogInfo') {action = [GET: 'dataTable']}
            "/userloginfo/$id(.$format)?"(controller: 'userLogInfo') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //User Register
            "/userregister(.$format)?"(controller: 'userRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/userregister/datatable(.$format)?"(controller: 'userRegister') {action = [GET: 'dataTable']}
            "/userregister/$id(.$format)?"(controller: 'userRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/userregisterbydivision/$id(.$format)?"(controller: 'userRegister') {
                action = [GET: 'getAllByDivision']
            }

        }
    }
}
