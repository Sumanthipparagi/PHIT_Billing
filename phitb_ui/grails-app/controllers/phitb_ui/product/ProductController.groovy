package phitb_ui.product

import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.TaxController
import phitb_ui.facility.RackController
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Links
import phitb_ui.ProductService

class ProductController {

//    def index()
//    {
//        try
//        {
//            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
//            ArrayList<String> productGroups = new ProductGroupController().show() as ArrayList<String>
//            ArrayList<String> divisions = new DivisionController().show() as ArrayList<String>
//            ArrayList<String> productCategories = new ProductCategoryController().show() as ArrayList<String>
//            ArrayList<String> productSchedules = new ProductScheduleController().show() as ArrayList<String>
//            ArrayList<String> racks = new RackController().show() as ArrayList<String>
//            ArrayList<String> compositions = new CompositionController().show() as ArrayList<String>
//            render(view: '/phitb_ui.product/productRegister/index', model: [productTypes: productTypes,
//                                                                   productGroups:productGroups,
//                                                                   productCategories: productCategories,
//                                                                   productSchedules:productSchedules,
//                                                                   racks:racks,
//                                                                   compositions:compositions,
//                                                                   divisions: divisions])
//
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//            response.status = 400
//        }
//
//    }


    def index() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().show() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().show() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().show() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().show() as ArrayList<String>
            ArrayList<String> racks = new RackController().show() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().show() as ArrayList<String>
            render(view: '/phitb_ui/product/productRegister/productRegister', model: [productTypes     : productTypes,
                                                                                      productGroups    : productGroups,
                                                                                      productCategories: productCategories,
                                                                                      productSchedules : productSchedules,
                                                                                      racks            : racks,
                                                                                      compositions     : compositions,
                                                                                      divisions        : divisions])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }


    def addProduct() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
            def entitytypeurl = Links.API_GATEWAY + Links.ENTITY_TYPE_MASTER_SHOW
            URL apiUrl1 = new URL(entitytypeurl)
            def entitytype = new JsonSlurper().parseText(apiUrl1.text)
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> tax = new TaxController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().show() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().show() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().show() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().show() as ArrayList<String>
            ArrayList<String> racks = new RackController().show() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().show() as ArrayList<String>
            ArrayList<String> producttype = new ProductTypeController().show() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().show() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().show() as ArrayList<String>
            render(view: '/phitb_ui/product/productRegister/add-product', model: [productTypes     : productTypes,
                                                                                  productGroups    : productGroups,
                                                                                  productCategories: productCategories,
                                                                                  productSchedules : productSchedules,
                                                                                  racks            : racks,
                                                                                  compositions     : compositions,
                                                                                  divisions        : divisions, entity: entity,
                                                                                  entitytype       : entitytype,
                                                                                  producttype      : producttype,
                                                                                  productcost      : productcost,
                                                                                  unittype         : unittype, tax: tax])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }

    def updateProduct() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
            def entitytypeurl = Links.API_GATEWAY + Links.ENTITY_TYPE_MASTER_SHOW
            def productregisterbyidurl = Links.API_GATEWAY + Links.PRODUCT_REGISTER_SHOW + "/" + params.id
            URL apiUrl1 = new URL(entitytypeurl)
            URL apiUrl2 = new URL(productregisterbyidurl)
            def entitytype = new JsonSlurper().parseText(apiUrl1.text)
            def productregsiter = new JsonSlurper().parseText(apiUrl2.text)
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> tax = new TaxController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().show() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().show() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().show() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().show() as ArrayList<String>
            ArrayList<String> racks = new RackController().show() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().show() as ArrayList<String>
            ArrayList<String> producttype = new ProductTypeController().show() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().show() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().show() as ArrayList<String>
            render(view: '/phitb_ui/product/productRegister/update-product', model: [productTypes     : productTypes,
                                                                                     productGroups    : productGroups,
                                                                                     productCategories: productCategories,
                                                                                     productSchedules : productSchedules,
                                                                                     racks            : racks,
                                                                                     compositions     : compositions,
                                                                                     divisions        : divisions, entity: entity,
                                                                                     entitytype       : entitytype,
                                                                                     producttype      : producttype,
                                                                                     productregsiter  : productregsiter,
                                                                                     productcost      : productcost,
                                                                                     unittype         : unittype, tax: tax])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }


    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().showProductRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().saveProductRegister(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri: '/phitb_ui.product')

            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update() {
        try {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().putProductRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri: '/phitb_ui.product')

            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new ProductService().deleteBatchRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def show() {
        try {
            def apiResponse = new ProductService().getProducts()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
