package product

import facility.RackController
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import phitb_ui.Links
import phitb_ui.ProductService
import system.StateController

class ProductController {

    def index()
    {
        try
        {
            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().show() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().show() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().show() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().show() as ArrayList<String>
            ArrayList<String> racks = new RackController().show() as ArrayList<String>
            println(racks)
            ArrayList<String> compositions = new CompositionController().show() as ArrayList<String>
            render(view: '/product/productRegister/index', model: [productTypes: productTypes,
                                                                   productGroups:productGroups,
                                                                   productCategories: productCategories,
                                                                   productSchedules:productSchedules,
                                                                   racks:racks,
                                                                   compositions:compositions,
                                                                   divisions: divisions])

        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }
}
