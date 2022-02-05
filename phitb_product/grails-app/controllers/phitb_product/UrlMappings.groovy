package phitb_product

import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

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


            //Batch Register
            "/product/batchregister(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/batchregister/datatable(.$format)?"(controller: 'batchRegister') {action = [GET: 'dataTable']}
            "/product/batchregister/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/product/batchregisterbyentity/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'show']
            }

            "/product/batchregisterbyproduct/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'getAllByProduct']
            }

            //Composition Master Register
            "/product/compositionmasterregister(.$format)?"(controller: 'compositionMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/compositionmasterregister/datatable(.$format)?"(controller: 'compositionMaster') {action = [GET: 'dataTable']}
            "/product/compositionmasterregister/$id(.$format)?"(controller: 'compositionMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Division
            "/product/division(.$format)?"(controller: 'division') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/division/datatable(.$format)?"(controller: 'division') {action = [GET: 'dataTable']}
            "/product/division/$id(.$format)?"(controller: 'division') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Division group Register
            "/product/divisiongroupregister(.$format)?"(controller: 'divisionGroupRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/divisiongroupregister/datatable(.$format)?"(controller: 'divisionGroupRegister') {action = [GET: 'dataTable']}
            "/product/divisiongroupregister/$id(.$format)?"(controller: 'divisionGroupRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Product Category Master
            "/product/productcategorymaster(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productcategorymaster/datatable(.$format)?"(controller: 'productCategoryMaster') {action = [GET: 'dataTable']}
            "/product/productcategorymaster/$id(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Class
            "/product/productclass(.$format)?"(controller: 'productClass') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productclass/datatable(.$format)?"(controller: 'productClass') {action = [GET: 'dataTable']}
            "/product/productclass/$id(.$format)?"(controller: 'productClass') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Cost Range
            "/product/productcostrange(.$format)?"(controller: 'productCostRange') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productcostrange/datatable(.$format)?"(controller: 'productCostRange') {action = [GET: 'dataTable']}
            "/product/productcostrange/$id(.$format)?"(controller: 'productCostRange') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Group Master
            "/product/productgroupmaster(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productgroupmaster/datatable(.$format)?"(controller: 'productGroupMaster') {action = [GET: 'dataTable']}
            "/product/productgroupmaster/$id(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Group Master
            "/product/productgroupmaster(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productgroupmaster/datatable(.$format)?"(controller: 'productGroupMaster') {action = [GET:
                                                                                                               'dataTable']}
            "/product/productgroupmaster/$id(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Register
            "/product/productregister(.$format)?"(controller: 'productRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productregister/datatable(.$format)?"(controller: 'productRegister') {action = [GET:
                                                                                                             'dataTable']}
            "/product/productregister/$id(.$format)?"(controller: 'productRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            //Product Schedule Master
            "/product/productschdulemaster(.$format)?"(controller: 'productScheduleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/productschdulemaster/datatable(.$format)?"(controller: 'productScheduleMaster') {action = [GET:
                                                                                                             'dataTable']}
            "/product/productschdulemaster/$id(.$format)?"(controller: 'productScheduleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Product Type Master
            "/product/producttypemaster(.$format)?"(controller: 'productTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/producttypemaster/datatable(.$format)?"(controller: 'productTypeMaster') {action = [GET: 'dataTable']}
            "/product/producttypemaster/$id(.$format)?"(controller: 'productTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Unit Type Master
            "/product/unittypemaster(.$format)?"(controller: 'unitTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/product/unittypemaster/datatable(.$format)?"(controller: 'unitTypeMaster') {action = [GET: 'dataTable']}
            "/product/unittypemaster/$id(.$format)?"(controller: 'unitTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
        }
    }
}
