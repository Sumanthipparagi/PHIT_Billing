package phitb_sales


import grails.rest.*
import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import groovy.json.internal.ArrayUtils
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class SaleProductDetailsController
{
    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    SaleProductDetailsService saleProductDetailsService
    /**
     * Gets all Sale Product Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Product Details
     */
    def index()
    {

        try
        {
            respond saleProductDetailsService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Sale Product Details
     * @param id
     * @return get requested Sale Product Details
     */
    def show()
    {
        try
        {
            String id = params.id
            if (id)
            {
                respond saleProductDetailsService.get(id)
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
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllByDays()
    {
        try
        {
            String days = params.days
            if (days)
            {
                respond saleProductDetailsService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Save new Sale Product Details
     * @param Sale Product Details
     * @return saved Sale Product Details
     */
    def save()
    {
        try
        {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond saleProductDetailsService.save(jsonObject)
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
     * Update existing Sale Product Details
     * @param id
     * @param Sale Product Details
     * @return updated Sale Product Details
     */
    def update()
    {
        try
        {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond saleProductDetailsService.update(jsonObject, id)
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
     * Delete selected account register
     * @param id
     * @return returns status code 200
     */
    def delete()
    {
        try
        {
            String id = params.id
            saleProductDetailsService.delete(id)
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
     * Gets all bank register in datatables format
     * @return list of bank register
     */
    def dataTable()
    {
        try
        {
            String start = params.start
            String length = params.length
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            respond saleProductDetailsService.dataTables(paramsJsonObject, start, length)
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

    def getSaleProductDetailsOfSaleBill()
    {
        try
        {
            String id = params.id
            respond saleProductDetailsService.getBySaleBill(id)
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

    def getSaleProductDetailsOfSaleBillList()
    {
        try
        {
            JSONArray jsonArray = new JSONArray()
            String idArrayString = params.salebillsIds
            def idArray = idArrayString.trim().replaceAll(~/^\[|\]$/, '').split(',').collect{ it.trim()}
            if(idArray.toString()!='[]')
            respond saleProductDetailsService.getBySaleBillByList(idArray as ArrayList<Long>)
            else
                respond jsonArray,formats: ['json'],status: 200
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


    def getSaleProductDetailsbyProductId()
    {
        try
        {
            String productId = params.productId
            respond saleProductDetailsService.getSaleProductDetailsByProductId(productId)
        }
        catch(Exception ex)
        {
            log.error(controllerName+":"+ex)
            println(controllerName+":"+ex)
        }
    }

    def getSaleProductDetailsbybatchAndBill()
    {
        try
        {
            String billId = params.billId
            String batch = params.batch
            String productId = params.productId
            respond saleProductDetailsService.getSaleProductDetailsByBillIdAndBatch(billId,batch,productId)
        }
        catch(Exception ex)
        {
            log.error(controllerName+":"+ex)
            println(controllerName+":"+ex)
        }
    }
}
