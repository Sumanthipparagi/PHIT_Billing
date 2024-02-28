package phitb_product


import grails.rest.*
import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

class AlternateProductController {
    static responseFormats = ['json', 'xml']

    static allowedMethods = [index    : "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE",
                             dataTable: "GET", updateIRNDetails: "PUT", saveInvoice: "POST", updateInvoice: "PUT"]


    AlternateProductService alternateProductService

    def showProduct() {
        try {
              def res = alternateProductService.getAllProducts()
              respond res
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    def showCompany() {
        try {
            def res = alternateProductService.getAllCompany()
            respond res
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    def showComposition() {
        try {
            def res = alternateProductService.getAllComposition()
            respond res
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def getProductsByCompositionId() {
        try {
                GrailsParameterMap parameterMap = getParams()
                JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
                String start = paramsJsonObject.get("start")
                String length = paramsJsonObject.get("length")
                def products = alternateProductService.getProductByCompositionId(paramsJsonObject,start,length)

                products.productList.each { product ->
                // Assuming product has a 'composition' field that is the ID for MasterComposition
                if (product.composition && product.company) {
                    def composition = MasterComposition.findById(product.composition)
                    def company = CompanyMaster.findById(product.company)
                    // Now, add details from 'composition' to 'product'
                    // This assumes 'product' is a Map or something you can put key-value pairs into.
                    // If 'product' is a domain object, you'll need to convert it to a Map or adjust its properties directly if they
                    // Add any specific property you need, for example:
                    product.putAt('composition', composition.compositionName)
                    product.putAt('company',company.companyName)
                }
            }

// Assuming you want to update the productList with the enhanced products
            products.put("productList", products.productList)
            respond products
            }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def getProductsByCompanyId(String id) {
        try {
            if (id) {
                def products = alternateProductService.getProductByCompanyId(id)

                products.each { product ->
                    if (product.composition && product.company) {
                        def composition = MasterComposition.findById(product.composition)
                        def company = CompanyMaster.findById(product.company)
                        if (composition && company) {
                            product.putAt('composition', composition.compositionName)
                            product.putAt('company',company.companyName)
                        } else {

                            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
                        }
                    }
                }
                respond products
            }
        } catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def getCompositionListByProductId(String id) {
        try {

            if (id) {
                def products = alternateProductService.getCompositionListByProductId(id)

                products.each { product ->
                    // Assuming `product` is a map. If it's a domain object, you might need to adjust this.
                    if (product.composition && product.company) {
                        def composition = MasterComposition.findById(product.composition)
                        def company = CompanyMaster.findById(product.company)
                        if (composition && company) {
                            product.putAt('composition', composition.compositionName)
                            product.putAt('company',company.companyName)
                        } else {
                            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
                        }
                    }
                }
                respond products
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }
}
