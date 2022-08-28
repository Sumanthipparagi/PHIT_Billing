package phitb_sales


import grails.rest.*
import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class SaleReturnController {
    static responseFormats = ['json', 'xml']


    SaleReturnService saleReturnService
    SaleReturnDetailsService saleReturnDetailsService
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
        catch (ResourceNotFoundException ex) {
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
        catch (ResourceNotFoundException ex) {
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

    /**
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllsettledByCustId() {
        try {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleReturnService.getAllsettledByCustId(customerId, entityId, financialYear)
        }
        catch (ResourceNotFoundException ex) {
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

    def getAllUnsettledByCustId() {
        try {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleReturnService.getAllUnsettledByCustId(customerId, entityId, financialYear)
        }
        catch (ResourceNotFoundException ex) {
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

    def getAllByCustomerId() {
        try {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            String returnStatus = params.returnStatus
            respond saleReturnService.getAllByCustomerId(customerId, entityId, financialYear, returnStatus)
        }
        catch (ResourceNotFoundException ex) {
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

    def getAllByCustomerIdStartDate() {
        try {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            String dateRange = params.dateRange
            respond saleReturnService.getAllByCustomerIdStartDate(customerId, entityId, financialYear,dateRange)
        }
        catch (ResourceNotFoundException ex) {
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

    def updateStatus(Long id) {
        try {
            SaleReturnDetails saleReturnDetails = SaleReturnDetails.findById(id)
            if (saleReturnDetails) {
                saleReturnDetails.isUpdatable = true
                if (params.type == "settled") {
                    saleReturnDetails.adjustmentStatus = "1"
                    saleReturnDetails.adjAmount = saleReturnDetails.getBalance() - Double.parseDouble(params.adj)
                } else {
                    saleReturnDetails.adjAmount = 0
                    saleReturnDetails.adjustmentStatus = "0"
                }
                SaleReturnDetails saleReturnDetails1 = saleReturnDetails.save(flush: true)
                if (saleReturnDetails1) {
                    respond saleReturnDetails1
                    return
                }
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        response.status = 400
    }

    def updateBalance() {
        try {
            SaleReturn saleReturn = SaleReturn.findById(Long.parseLong(params.id))
            if (params.status == null || params.status == "NA") {
                if (saleReturn) {
                    saleReturn.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double diffBalance = Double.parseDouble(saleReturn.getBalance().toString()) - balance
                        saleReturn.balance = String.format("%.2f", diffBalance) as double
                        saleReturn.adjAmount = String.format("%.2f", saleReturn.getAdjAmount() + balance) as double
                    } else {
                        saleReturn.balance = String.format("%.2f", saleReturn.getBalance()) as double
                        saleReturn.adjAmount = String.format("%.2f", saleReturn.getAdjAmount()) as double
                    }
                    SaleReturn saleReturn1 = saleReturn.save(flush: true)
                    if (saleReturn1) {
                        respond saleReturn1
                        return
                    }
                }
            } else {
                if (saleReturn) {
                    saleReturn.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double updatebalance = Double.parseDouble(saleReturn.getBalance().toString()) + balance
                        saleReturn.balance = String.format("%.2f", updatebalance) as double
                        saleReturn.adjAmount = String.format("%.2f", saleReturn.getAdjAmount() - balance) as double
                    } else {
                        saleReturn.balance = String.format("%.2f", saleReturn.getBalance()) as double
                        saleReturn.adjAmount = String.format("%.2f", saleReturn.getAdjAmount()) as double
                    }
                    SaleReturn saleReturn1 = saleReturn.save(flush: true)
                    if (saleReturn1) {
                        respond saleReturn1
                        return
                    }
                }
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        response.status = 400
    }

    def getRecentByFinancialYearAndEntity() {
        try {
            String financialYear = params.financialYear
            String entityId = params.entityId
            String billStatus = params.billStatus
            respond saleReturnService.getRecentByFinancialYearAndEntity(financialYear, entityId)
        }
        catch (ResourceNotFoundException ex) {
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


    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject.get("start")
            String length = paramsJsonObject.get("length")
            def saleReturnDetails = saleReturnService.dataTables(paramsJsonObject, start, length)
            respond saleReturnDetails
        }
        catch (ResourceNotFoundException ex) {
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


    def cancelSaleReturn() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject saleReturn = saleReturnService.cancelSaleRetruns(jsonObject)
            respond saleReturn
        }
        catch (ResourceNotFoundException ex) {
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


    /**
     * Save new Sale Bill Details along with products
     * @param Sale Bill Details
     * @return saved Sale Bill Details
     */
    def saveSaleReturn() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            SaleReturn saleReturn = saleReturnService.save(jsonObject.get("saleReturn"))
            if (saleReturn) {
                UUID uuid
                JSONArray saleProducts = jsonObject.get("saleReturnDetails")
                for (JSONObject product : saleProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", saleReturn.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", saleReturn.serBillId)
                    saleReturnDetailsService.save(product)
                    println("product saved")
                }
            }
            respond saleReturn
        }
        catch (ResourceNotFoundException ex) {
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


    def getByDateRangeAndEntity() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String dateRange = jsonObject.get("dateRange")
            String entityId = jsonObject.get("entityId")
            if (dateRange && entityId) {
                JSONArray salesReturns = saleReturnService.getByDateRangeAndEntity(dateRange, entityId)
                render salesReturns, formats: ['json']
            } else {
                response.status = 400
            }
        }
        catch (ResourceNotFoundException ex) {
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

    def adjustSaleReturn() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            respond saleReturnService.saleReturnAdjustment(jsonObject)
        }
        catch (ResourceNotFoundException ex) {
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

    def getSaleReturnAdjustmentDetailsByDocId() {
        try {
            String docId = params.docId
            String docType = params.docType
            String dateRange = params.dateRange
            respond saleReturnService.getSaleReturnAdjustmentDetailsByDocId(docId, docType, dateRange)
        }
        catch (ResourceNotFoundException ex) {
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

    def getSaleReturnAdjustmentDetailsByDocIdStartDate() {
        try {
            String docId = params.docId
            String docType = params.docType
            String dateRange = params.dateRange
            respond saleReturnService.getSaleReturnAdjustmentDetailsByDocIdStartDate(docId, docType, dateRange)
        }
        catch (ResourceNotFoundException ex) {
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

}
