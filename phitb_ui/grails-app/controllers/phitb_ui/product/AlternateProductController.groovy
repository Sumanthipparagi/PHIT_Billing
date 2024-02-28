package phitb_ui.product

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.ProductService
import phitb_ui.SalesService

class AlternateProductController {

    def index() {
        try
        {
            render(view: '/product/alternateProduct/alternateProduct')
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getAllProduct()
    {
        try
        {
            def apiResponse = new ProductService().getAllProducts()
            if (apiResponse.status == 200)
            {
                JSONArray responseObject = new JSONArray(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getAllCompany()
    {
        try
        {
            def apiResponse = new ProductService().getAllCompany()
            if (apiResponse.status == 200)
            {
                JSONArray responseObject = new JSONArray(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getAllComposition()
    {
        try
        {
            def apiResponse = new ProductService().getAllComposition()
            if (apiResponse.status == 200)
            {
                JSONArray responseObject = new JSONArray(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getProductsByCompositionId()
    {

        try
        {
            JSONObject jsonObject = new JSONObject(params)
//            def entityId = session.getAttribute("entityId").toString()
            def apiResponse = new ProductService().getProductsByCompositionId(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getProductsByCompanyId()
    {

        try
        {
//            def entityId = session.getAttribute("entityId").toString()
            String id = params.companyId
            def apiResponse = new ProductService().getProductsByCompanyId(id)
            if (apiResponse.status == 200)
            {
                JSONArray responseObject = new JSONArray(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getCompositionListByProductId()
    {

        try
        {
//            def entityId = session.getAttribute("entityId").toString()
            String id = params.productId
            def apiResponse = new ProductService().getCompositionListByProductId(id)
            if (apiResponse.status == 200)
            {
                JSONArray responseObject = new JSONArray(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


}
