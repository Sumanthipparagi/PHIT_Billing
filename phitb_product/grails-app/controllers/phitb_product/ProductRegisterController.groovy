package phitb_product


import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

class ProductRegisterController
{
    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    ProductRegisterService productRegisterService
    /**
     * Gets all Product Register
     * @param query
     * @param offset
     * @param limit
     * @return list of Product Register
     */
    def index()
    {

        try
        {
            respond productRegisterService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Activity
     * @param id
     * @return get requested Stock Activity
     */
    def show()
    {
        try
        {
            String id = params.id
            if (id)
            {
                respond productRegisterService.get(id)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Stock Activity
     * @param id
     * @return get requested Stock Activity
     */
    def getAllByEntityId()
    {
        try
        {
            if (params.id)
            {
                respond productRegisterService.getAllByEntity(Long.parseLong(params.id))
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested product by div
     * @param id
     * @return get product by div
     */
    def getAllByDivision()
    {
        try
        {
            if (params.id)
            {
                String search = params.search
                String page = params.page
                String type = params.type
                JSONObject products = productRegisterService.getAllByDivision(params.id, page, search, type)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    /**
     * Save new Stock Activity
     * @param Stock Activity
     * @return saved Stock Activity
     */
    def save()
    {
        try
        {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond productRegisterService.save(jsonObject)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


/**
 * Save new bulk products
 * @param bulk products
 * @return saved  bulk products
 */
    def saveBulkProducts()
    {
        try
        {
            JSONArray jsonArray = JSON.parse(request.reader.text) as JSONArray
            respond productRegisterService.saveBulkProductRegister(jsonArray)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Update existing Stock Activity
     * @param id
     * @param Stock Activity
     * @return updated Product Register
     */
    def update()
    {
        try
        {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond productRegisterService.update(jsonObject, id)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Delete selected Stock Activity
     * @param id
     * @return returns status code 200
     */
    def delete()
    {
        try
        {
            String id = params.id
            productRegisterService.delete(id)
            response.status = 200
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Gets all Product Register in datatables format
     * @return list of Product Register
     */
    def dataTable()
    {
        try
        {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject.get("start")
            String length = paramsJsonObject.get("length")
            respond productRegisterService.dataTables(paramsJsonObject, start, length)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested product by div
     * @param id
     * @return get product by div
     */
    def getByHsnCodeAndEntityId()
    {
        try
        {
            long entityId = Long.parseLong(params.entityId)
            String hsnCode = params.hsnCode
            if (hsnCode)
            {
                respond productRegisterService.getByHsnCodeAndEntityId(hsnCode, entityId)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested product by entity
     * @param id
     * @return get product by entity
     */
    def getProductByEntity()
    {
        try
        {
            long entityId = Long.parseLong(params.id)
            if (entityId)
            {
                String search = params.search
                String page = params.page
                String type = params.type
                def products = productRegisterService.getByEntityId(entityId, page, search, type)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    /**
     * Get requested product by name
     * @param id
     * @return get product by name
     */
    def getProductByName()
    {
        try
        {
            String productName = params.productName
            String entityId = params.entityId
            if (productName)
            {
                long eId = Long.parseLong(entityId)
                respond productRegisterService.getProductByName(productName, eId)
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
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

}
