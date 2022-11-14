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

        group "/api/v1.0/product", {
            //Batch Register
            "/batchregister(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/batchregister/datatable(.$format)?"(controller: 'batchRegister') {action = [GET: 'dataTable']}
            "/batchregister/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/batchregisterbyentity/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'getAllByEntityId']
            }

            "/batchregisterbyproduct/$id(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'getAllByProduct']
            }

            "/batch-and-product(.$format)?"(controller: 'batchRegister') {
                action = [GET: 'getByBatchAndProduct']
            }
            "/save-bulk-batch-register(.$format)?"(controller: 'batchRegister', action: 'saveBulkProducts')


            //Composition Master Register
            "/compositionmasterregister(.$format)?"(controller: 'compositionMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/compositionmasterregister/datatable(.$format)?"(controller: 'compositionMaster') {action = [GET: 'dataTable']}
            "/compositionmasterregister/$id(.$format)?"(controller: 'compositionMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/composition/getbyentity/$id(.$format)?" (controller: 'compositionMaster', action: 'getByEntity')

            //Division
            "/division(.$format)?"(controller: 'division') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/division/datatable(.$format)?"(controller: 'division') {action = [GET: 'dataTable']}
            "/division/$id(.$format)?"(controller: 'division') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/divisionbyentity/$id(.$format)?"(controller: 'division') {
                action = [GET: 'getAllByEntityId']
            }


            //Division group Register
            "/divisiongroupregister(.$format)?"(controller: 'divisionGroupRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/divisiongroupregister/datatable(.$format)?"(controller: 'divisionGroupRegister') {action = [GET: 'dataTable']}
            "/divisiongroupregister/$id(.$format)?"(controller: 'divisionGroupRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/divisiongroupregisterbyentity/$id(.$format)?"(controller: 'divisionGroupRegister') {
                action = [GET: 'getAllByEntityId']
            }

            //Product Category Master
            "/productcategorymaster(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productcategorymaster/datatable(.$format)?"(controller: 'productCategoryMaster') {action = [GET:
                                                                                                                  'dataTable']}
            "/productcategorymaster/$id(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productcategorymasterbyentity/$id(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Product Class
            "/productclass(.$format)?"(controller: 'productClass') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productclass/datatable(.$format)?"(controller: 'productClass') {action = [GET: 'dataTable']}
            "/productclass/$id(.$format)?"(controller: 'productClass') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productclassbyentity/$id(.$format)?"(controller: 'productCategoryMaster') {
                action = [GET: 'getAllByEntityId']
            }


            //Product Cost Range
            "/productcostrange(.$format)?"(controller: 'productCostRange') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productcostrange/datatable(.$format)?"(controller: 'productCostRange') {action = [GET: 'dataTable']}
            "/productcostrange/$id(.$format)?"(controller: 'productCostRange') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productcostrangebyentity/$id(.$format)?"(controller: 'productCostRange') {
                action = [GET: 'getAllByEntityId']
            }


            //Product Group Master
            "/productgroupmaster(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productgroupmaster/datatable(.$format)?"(controller: 'productGroupMaster') {action = [GET: 'dataTable']}
            "/productgroupmaster/$id(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productgroupmasterbyentity/$id(.$format)?"(controller: 'productGroupMaster') {
                action = [GET: 'getAllByEntityId']
            }

            //Product Register
            "/productregister(.$format)?"(controller: 'productRegister') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productregister/datatable(.$format)?"(controller: 'productRegister') {action = [GET: 'dataTable']}
            "/productregister/$id(.$format)?"(controller: 'productRegister') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productregisterbyentity/$id(.$format)?"(controller: 'productRegister', action: 'getProductByEntity')
            "/productregisterbydivision/$id(.$format)?"(controller: 'productRegister') {
                action = [GET: 'getAllByDivision']
            }
            "/productregisterbyhsnandentity"(controller: 'productRegister', action: 'getByHsnCodeAndEntityId')
            "/save-bulk-product-register(.$format)?"(controller: 'productRegister', action: 'saveBulkProducts')

            //Product Schedule Master
            "/productschdulemaster(.$format)?"(controller: 'productScheduleMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/productschdulemaster/datatable(.$format)?"(controller: 'productScheduleMaster') {action = [GET: 'dataTable']}
            "/productschdulemaster/$id(.$format)?"(controller: 'productScheduleMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/productschdulemasterbyentity/$id(.$format)?"(controller: 'productScheduleMaster') {
                action = [GET: 'getAllByEntityId']
            }






            //Product Type Master
            "/producttypemaster(.$format)?"(controller: 'productTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/producttypemaster/datatable(.$format)?"(controller: 'productTypeMaster') {action = [GET: 'dataTable']}
            "/producttypemaster/$id(.$format)?"(controller: 'productTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/producttypemasterbyentity/$id(.$format)?"(controller: 'productTypeMaster') {
                action = [GET: 'getAllByEntityId']
            }



            //Unit Type Master
            "/unittypemaster(.$format)?"(controller: 'unitTypeMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/unittypemaster/datatable(.$format)?"(controller: 'unitTypeMaster') {action = [GET: 'dataTable']}
            "/unittypemaster/$id(.$format)?"(controller: 'unitTypeMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/unittypemasterbyentity/$id(.$format)?"(controller: 'unitTypeMaster') {
                action = [GET: 'getAllByEntityId']
            }

            "/status"(controller: 'status',action: 'index')
        }
    }
}
