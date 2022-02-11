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
            "/entity/accountregister(.$format)?"(controller: 'accountRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/accountregister/datatable(.$format)?"(controller: 'accountRegister') {action = [GET: 'dataTable']}
            "/entity/accountregister/$id(.$format)?"(controller: 'accountRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Auth register
            "/entity/authregister(.$format)?"(controller: 'authRegister') {action = [GET: 'index', POST: 'save']}
            "/entity/authregister/datatable(.$format)?"(controller: 'authRegister') {action = [GET: 'dataTable']}
            "/entity/authregister/$id(.$format)?"(controller: 'authRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Customer Group register
            "/entity/customergroupregister(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/customergroupregister/datatable(.$format)?"(controller: 'customerGroupRegister') {action = [GET: 'dataTable']}
            "/entity/customergroupregister/$id(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/customergroupregisterbyentity/$id(.$format)?"(controller: 'customerGroupRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Day End Master
            "/entity/dayendmaster(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/dayendmaster/datatable(.$format)?"(controller: 'dayEndMaster') {action = [GET: 'dataTable']}
            "/entity/dayendmaster/$id(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/dayendmasterbyentity/$id(.$format)?"(controller: 'dayEndMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Department Master
            "/entity/departmentmaster(.$format)?"(controller: 'departmentMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/departmentmaster/datatable(.$format)?"(controller: 'departmentMaster') {action = [GET: 'dataTable']}
            "/entity/departmentmaster/$id(.$format)?"(controller: 'departmentMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Entity register
            "/entity/entityregister(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/entityregister/datatable(.$format)?"(controller: 'entityRegister') {action = [GET: 'dataTable']}
            "/entity/entityregister/$id(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'show', PUT: 'update', DELETE: 'delete']
            }
            "/entity/entityregisterbyentitytype/$id(.$format)?"(controller: 'entityRegister') {
                action = [GET: 'getAllByEntityTypeId']
            }


            //Entity Type Master
            "/entity/entitytypemaster(.$format)?"(controller: 'entityTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/entitytypemaster/datatable(.$format)?"(controller: 'entityTypeMaster') {action = [GET: 'dataTable']}
            "/entity/entitytypemaster/$id(.$format)?"(controller: 'entityTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }




            //Financial Year Master
            "/entity/financialyearmaster(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/financialyearmaster/datatable(.$format)?"(controller: 'financialYearMaster') {action = [GET: 'dataTable']}
            "/entity/financialyearmaster/$id(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/financialyearmasterbyentity/$id(.$format)?"(controller: 'financialYearMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Region Register
            "/entity/regionregister(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/regionregister/datatable(.$format)?"(controller: 'regionRegister') {action = [GET: 'dataTable']}
            "/entity/regionregister/$id(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/regionregisterbyentity/$id(.$format)?"(controller: 'regionRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Role Form Mapping
            "/entity/roleformmapping(.$format)?"(controller: 'roleFormMapping') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/roleformmapping/datatable(.$format)?"(controller: 'roleFormMapping') {action = [GET: 'dataTable']}
            "/entity/roleformmapping/$id(.$format)?"(controller: 'roleFormMapping') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Role Master
            "/entity/rolemaster(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/rolemaster/datatable(.$format)?"(controller: 'roleMaster') {action = [GET: 'dataTable']}
            "/entity/rolemaster/$id(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/rolemasterbyentity/$id(.$format)?"(controller: 'roleMaster') {
                action = [GET: 'getAllByEntityId']
            }


            //Route register Controller
            "/entity/routeregister(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/routeregister/datatable(.$format)?"(controller: 'routeRegister') {action = [GET: 'dataTable']}
            "/entity/routeregister/$id(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/routeregisterbyentity/$id(.$format)?"(controller: 'routeRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Rule Master Controller
            "/entity/rulemaster(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/rulemaster/datatable(.$format)?"(controller: 'ruleMaster') {action = [GET: 'dataTable']}
            "/entity/rulemaster/$id(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/rulemasterbyentity/$id(.$format)?"(controller: 'ruleMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Series Master
            "/entity/seriesmaster(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/seriesmaster/datatable(.$format)?"(controller: 'seriesMaster') {action = [GET: 'dataTable']}
            "/entity/seriesmaster/$id(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/seriesmasterbyentity/$id(.$format)?"(controller: 'seriesMaster') {
                action = [GET: 'getAllByEntityId']
            }


            //Tax Register
            "/entity/taxregister(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/taxregister/datatable(.$format)?"(controller: 'taxRegister') {action = [GET: 'dataTable']}
            "/entity/taxregister/$id(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/taxregisterbyentity/$id(.$format)?"(controller: 'taxRegister') {
                action = [GET: 'getAllByEntityId']
            }



            //Terms Condition Details
            "/entity/termconditiondetails(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/termconditiondetails/datatable(.$format)?"(controller: 'termsConditionDetails') {action = [GET: 'dataTable']}
            "/entity/termconditiondetails/$id(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/termconditiondetailsbyentity/$id(.$format)?"(controller: 'termsConditionDetails') {
                action = [GET: 'getAllByEntityId']
            }


            //Territory Register
            "/entity/territoryregister(.$format)?"(controller: 'territoryRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/territoryregister/datatable(.$format)?"(controller: 'territoryRegister') {action = [GET: 'dataTable']}
            "/entity/territoryregister/$id(.$format)?"(controller: 'territoryRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Transaction Type Master
            "/entity/transactiontype(.$format)?"(controller: 'transactionTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/transactiontype/datatable(.$format)?"(controller: 'transactionTypeMaster') {action = [GET: 'dataTable']}
            "/entity/transactiontype/$id(.$format)?"(controller: 'transactionTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //User Log Info
            "/entity/userloginfo(.$format)?"(controller: 'userLogInfo') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/userloginfo/datatable(.$format)?"(controller: 'userLogInfo') {action = [GET: 'dataTable']}
            "/entity/userloginfo/$id(.$format)?"(controller: 'userLogInfo') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //User Register
            "/entity/userregister(.$format)?"(controller: 'userRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/entity/userregister/datatable(.$format)?"(controller: 'userRegister') {action = [GET: 'dataTable']}
            "/entity/userregister/$id(.$format)?"(controller: 'userRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/entity/userregisterbydivision/$id(.$format)?"(controller: 'userRegister') {
                action = [GET: 'getAllByDivision']
            }

        }
    }
}
