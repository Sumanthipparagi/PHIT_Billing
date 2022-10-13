package phitb_purchase

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

class PurchaseReturnDetailController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    PurchaseReturnDetailService purchaseReturnDetailService
    /**
     * Gets all purchase return details
     * @param query
     * @param offset
     * @param limit
     * @return list of purchase return details
     */
    def index() {

        try {
            respond purchaseReturnDetailService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested purchase return detail
     * @param id
     * @return get requested purchase return detail
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond purchaseReturnDetailService.get(id)
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
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllByDays() {
        try {
            String days = params.days
            if (days) {
                respond purchaseReturnDetailService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Save new purchase return detail
     * @param purchase return detail
     * @return saved purchase return detail
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond purchaseReturnDetailService.save(jsonObject)
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
     * Update existing purchase return detail
     * @param id
     * @param purchase return detail
     * @return updated purchase return details
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond purchaseReturnDetailService.update(jsonObject,id)
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
     * Delete selected purchase return detail
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            purchaseReturnDetailService.delete(id)
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
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Gets all purchase return details in datatables format
     * @return list of purchase return details
     */
    def dataTable() {
        try {
            String start = params.start
            String length = params.length
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            respond purchaseReturnDetailService.dataTables(paramsJsonObject, start, length)
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

    def getAllBySupplierId()
    {
        try
        {
            respond purchaseReturnDetailService.getAllBySupplierId(params.id,params.financialYear,params.entityId)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    def updateBalance()
    {
        try
        {
            PurchaseReturnDetail purchaseReturn = PurchaseReturnDetail.findById(Long.parseLong(params.id))
            if (purchaseReturn)
            {
                purchaseReturn.isUpdatable = true
                Double balance = Double.parseDouble(params.balance)
                if (balance > 0 && balance!="" && balance!=null)
                {
                    double diffBalance = Double.parseDouble(purchaseReturn.getBalance().toString()) - balance
                    purchaseReturn.balance = diffBalance
                    purchaseReturn.adjAmount = purchaseReturn.getAdjAmount() + balance
                }
                else
                {
                    purchaseReturn.balance = purchaseReturn.getBalance()
                    purchaseReturn.adjAmount = purchaseReturn.getAdjAmount()
                }
                PurchaseReturnDetail purchaseReturnDetail = purchaseReturn.save(flush: true)
                if (purchaseReturnDetail)
                {
                    respond purchaseReturnDetail
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

    def getByDateRangeAndEntity()
    {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String dateRange = jsonObject.get("dateRange")
            String entityId = jsonObject.get("entityId")
            if (dateRange && entityId) {
                JSONArray saleBillDetails = purchaseReturnDetailService.getByDateRangeAndEntity(dateRange, entityId)
                render saleBillDetails, formats: ['json']
            }
            else
            {
                response.status = 400
            }
        }
        catch (org.springframework.boot.context.config.ResourceNotFoundException ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def getByDateRangeAndSupplier()
    {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String dateRange = jsonObject.get("dateRange")
            String supplier = jsonObject.get("supplierId")
            if (dateRange && supplier) {
                JSONArray saleBillDetails = purchaseReturnDetailService.getByDateRangeAndSupplier(dateRange, supplier)
                render saleBillDetails, formats: ['json']
            }
            else
            {
                response.status = 400
            }
        }
        catch (org.springframework.boot.context.config.ResourceNotFoundException ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def getPurReturnDetailsByProductBatchPurchaseBill()
    {
        try
        {
            String productId = params.productId
            String batch = params.batch
            String purchaseBill = params.salebill
            if(purchaseBill!=null && purchaseBill!="")
            {
                respond purchaseReturnDetailService.getPurchaseDetailsByProductBatchPurBill(productId,batch,purchaseBill)
            }
            else {
                return []
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
