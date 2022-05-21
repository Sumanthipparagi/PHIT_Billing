package phitb_ui.product

import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.facility.RackController
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Links
import phitb_ui.ProductService

class ProductController {

    def index() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().getByEntity() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            render(view: '/product/productRegister/productRegister', model: [productTypes     : productTypes,
                                                                             productGroups    : productGroups,
                                                                             productCategories: productCategories,
                                                                             productSchedules : productSchedules,
                                                                             racks            : racks,
                                                                             compositions     : compositions,
                                                                             divisions        : divisions,entity:entity])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }


    def addProduct() {
        try {
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> tax = new TaxController().show() as ArrayList<String>
            ArrayList<String> productTypes = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().getByEntity() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().getByEntity() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> manufacturerList = []
            ArrayList<String> companyList = []
            entity.each {
                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER)) {
                    manufacturerList.add(it)
                }

                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER_AND_MARKETING)) {
                    companyList.add(it)
                }
            }
            render(view: '/product/productRegister/add-product', model: [productTypes     : productTypes,
                                                                         productGroups    : productGroups,
                                                                         productCategories: productCategories,
                                                                         productSchedules : productSchedules,
                                                                         racks            : racks,
                                                                         compositions     : compositions,
                                                                         divisions        : divisions,
                                                                         entity: entity,
                                                                         productcost      : productcost,
                                                                         unittype         : unittype,
                                                                         tax              : tax,
                                                                         manufacturerList : manufacturerList,
                                                                         companyList      : companyList
            ])

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
/*            def entitytypeurl = Links.API_GATEWAY + Links.ENTITY_TYPE_MASTER_SHOW
            def productregisterbyidurl = Links.API_GATEWAY + Links.PRODUCT_REGISTER_SHOW + "/" + params.id
            URL apiUrl1 = new URL(entitytypeurl)
            URL apiUrl2 = new URL(productregisterbyidurl)
            def entitytype = new JsonSlurper().parseText(apiUrl1.text)
            def productregsiter = new JsonSlurper().parseText(apiUrl2.text)*/
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> tax = new TaxController().show() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().getByEntity() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            ArrayList<String> producttype = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().getByEntity() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> manufacturerList = []
            ArrayList<String> companyList = []
            entity.each {
                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER)) {
                    manufacturerList.add(it)
                }

                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_COMPANY)) {
                    companyList.add(it)
                }
            }
            render(view: '/product/productRegister/update-product', model: [productTypes     : productTypes,
                                                                            productGroups    : productGroups,
                                                                            productCategories: productCategories,
                                                                            productSchedules : productSchedules,
                                                                            racks            : racks,
                                                                            compositions     : compositions,
                                                                            divisions        : divisions, entity: entity,
                                                                           // entitytype       : entitytype,
                                                                            producttype      : producttype,
                                                                            //productregsiter  : productregsiter,
                                                                            productcost      : productcost,
                                                                            unittype         : unittype,
                                                                            tax              : tax,
                                                                            manufacturerList : manufacturerList,
                                                                            companyList      : companyList
            ])

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
            jsonObject.put("entityId", session.getAttribute("entityId"))
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
                redirect(uri: '/product')

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
                redirect(uri: '/product')

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
            def apiResponse = new ProductService().deleteProductRegister(jsonObject)
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

    def getProductByDivision() {
        try {
            JSONArray jsonArray = new ProductService().getProductsByDivision(params.id)
            if (jsonArray)
                respond jsonArray, formats: ['json']
            else
                response.status = 404
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getProductBySeries() {
        try {
            JSONArray jsonArray = new ProductService().getProductsBySeries(params.id, session.getAttribute("entityId").toString())
            if (jsonArray)
                respond jsonArray, formats: ['json']
            else
                response.status = 404
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    /* def getProductbyId()
     {
         try
         {
             def apiResponse = new ProductService().getProductById(params.id)
             if (apiResponse?.status == 200)
             {
                 JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                 ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                 return arrayList
             }
             else
             {
                 return []
             }
         }
         catch (Exception ex)
         {
             System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
             log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
             response.status = 400
         }
     }*/

}
