package phitb_sales


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class SaleReturnController {
	static responseFormats = ['json', 'xml']


    SaleReturnService saleReturnService
    /**
     * Gets all Sale Product Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Product Details
     */
    def index() {

        try {
            respond saleReturnService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Sale Product Details
     * @param id
     * @return get requested Sale Product Details
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond saleReturnService.get(id)
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

    /**
     * Save new Sale Product Details
     * @param Sale Product Details
     * @return saved Sale Product Details
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond saleReturnService.save(jsonObject)
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

    /**
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllsettledByCustId()
    {
        try
        {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleReturnService.getAllsettledByCustId(customerId, entityId, financialYear)
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

    def getAllUnsettledByCustId()
    {
        try
        {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleReturnService.getAllUnsettledByCustId(customerId, entityId, financialYear)
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

    def getAllByCustomerId()
    {
        try
        {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleReturnService.getAllByCustomerId(customerId, entityId, financialYear)
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

    def updateStatus(Long id)
    {
        try
        {
            SaleReturnDetails saleReturnDetails = SaleReturnDetails.findById(id)
            if (saleReturnDetails)
            {
                saleReturnDetails.isUpdatable = true
                if (params.type == "settled")
                {
                    saleReturnDetails.adjustmentStatus = "1"
                    saleReturnDetails.adjAmount = saleReturnDetails.getBalance() - Double.parseDouble(params.adj)
                }
                else
                {
                    saleReturnDetails.adjAmount = 0
                    saleReturnDetails.adjustmentStatus = "0"
                }
                SaleReturnDetails saleReturnDetails1 = saleReturnDetails.save(flush: true)
                if (saleReturnDetails1)
                {
                    respond saleReturnDetails1
                    return
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        response.status = 400
    }

    def updateBalance()
    {
        try
        {
            SaleReturnDetails saleReturnDetails = SaleReturnDetails.findById(Long.parseLong(params.id))
            if (saleReturnDetails)
            {
                saleReturnDetails.isUpdatable = true
                Double balance = Double.parseDouble(params.balance)
                if (balance > 0 && balance!="" && balance!=null)
                {
                    double diffBalance = Double.parseDouble(saleReturnDetails.getBalance().toString()) - balance
                    saleReturnDetails.balance = diffBalance
                    saleReturnDetails.adjAmount = saleReturnDetails.getAdjAmount() + balance
                }
                else
                {
                    saleReturnDetails.balance = saleReturnDetails.getBalance()
                    saleReturnDetails.adjAmount = saleReturnDetails.getAdjAmount()
                }
                SaleReturnDetails saleReturnDetails1 = saleReturnDetails.save(flush: true)
                if (saleReturnDetails1)
                {
                    respond saleReturnDetails1
                    return
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        response.status = 400
    }

    def getRecentByFinancialYearAndEntity()
    {
        try {
            String financialYear = params.financialYear
            String entityId = params.entityId
            String billStatus = params.billStatus
            respond saleReturnService.getRecentByFinancialYearAndEntity(financialYear, entityId)
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
