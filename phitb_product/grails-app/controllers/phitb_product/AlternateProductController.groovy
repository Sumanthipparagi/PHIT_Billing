package phitb_product


import grails.rest.*
import grails.converters.*
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

    def getProductsByCompositionId(String id) {
        try {

            if (id) {
                def products = alternateProductService.getProductByCompositionId(id)

                products.each { product ->
                    // Assuming `product` is a map. If it's a domain object, you might need to adjust this.
                    if (product.composition && product.company) {
                        def composition = MasterComposition.findById(product.composition)
                        def company = CompanyMaster.findById(product.company)
                        if (composition && company) {
                            product.putAt('composition', composition.compositionName)
                            product.putAt('company',company.companyName)
                        } else {
                            // Handle case where composition is not found
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

    def getProductsByCompanyId(String id) {
        try {
            if (id) {
                def products = alternateProductService.getProductByCompanyId(id)

                products.each { product ->
                    // Assuming `product` is a map. If it's a domain object, you might need to adjust this.
                    if (product.composition && product.company) {
                        def composition = MasterComposition.findById(product.composition)
                        def company = CompanyMaster.findById(product.company)
                        if (composition && company) {
                            product.putAt('composition', composition.compositionName)
                            product.putAt('company',company.companyName)
                        } else {
                            // Handle case where composition is not found
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
                            // Handle case where composition is not found
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
