package phitb_purchase


import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_purchase.Exception.BadRequestException

class PurchaseReturnController {
    static responseFormats = ['json', 'xml']


    PurchaseReturnService purchaseReturnService
    PurchaseReturnDetailService purchaseReturnDetailService
    /**
     * Gets all Sale Product Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Product Details
     */
    def index() {

        try {
            respond purchaseReturnService.getAll(params.limit, params.offset, params.query)
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
                respond purchaseReturnService.get(id)
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
            respond purchaseReturnService.save(jsonObject)
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
            respond purchaseReturnService.getAllsettledByCustId(customerId, entityId, financialYear)
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
            respond purchaseReturnService.getAllUnsettledByCustId(customerId, entityId, financialYear)
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
            respond purchaseReturnService.getAllByCustomerId(customerId, entityId, financialYear, returnStatus)
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
            respond purchaseReturnService.getAllByCustomerIdStartDate(customerId, entityId, financialYear,dateRange)
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


    def updateBalance() {
        try {
            PurchaseReturn purchaseReturn = PurchaseReturn.findById(Long.parseLong(params.id))
            if (params.status == null || params.status == "NA") {
                if (purchaseReturn) {
                    purchaseReturn.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double diffBalance = Double.parseDouble(purchaseReturn.getBalance().toString()) - balance
                        purchaseReturn.balance = String.format("%.2f", diffBalance) as double
                        purchaseReturn.adjAmount = String.format("%.2f", purchaseReturn.getAdjAmount() + balance) as double
                    } else {
                        purchaseReturn.balance = String.format("%.2f", purchaseReturn.getBalance()) as double
                        purchaseReturn.adjAmount = String.format("%.2f", purchaseReturn.getAdjAmount()) as double
                    }
                    PurchaseReturn purchaseReturn1 = purchaseReturn.save(flush: true)
                    if (purchaseReturn1) {
                        respond purchaseReturn1
                        return
                    }
                }
            } else {
                if (purchaseReturn) {
                    purchaseReturn.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double updatebalance = Double.parseDouble(purchaseReturn.getBalance().toString()) + balance
                        purchaseReturn.balance = String.format("%.2f", updatebalance) as double
                        purchaseReturn.adjAmount = String.format("%.2f", purchaseReturn.getAdjAmount() - balance) as double
                    } else {
                        purchaseReturn.balance = String.format("%.2f", purchaseReturn.getBalance()) as double
                        purchaseReturn.adjAmount = String.format("%.2f", purchaseReturn.getAdjAmount()) as double
                    }
                    PurchaseReturn purchaseReturn1 = purchaseReturn.save(flush: true)
                    if (purchaseReturn1) {
                        respond purchaseReturn1
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
            respond purchaseReturnService.getRecentByFinancialYearAndEntity(financialYear, entityId)
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
            def saleReturnDetails = purchaseReturnService.dataTables(paramsJsonObject, start, length)
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


    def cancelPurchaseReturn() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject saleReturn = purchaseReturnService.cancelPurchaseRetruns(jsonObject)
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
    def savePurchaseReturn() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            PurchaseReturn purchaseReturn = purchaseReturnService.save(jsonObject.get("purchaseReturn") as JSONObject)
            if (purchaseReturn) {
                UUID uuid
                JSONArray purchaseProducts = jsonObject.get("purchaseReturnDetail") as JSONArray
                for (JSONObject product : purchaseProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", purchaseReturn.id)
                    product.put("billType", 0)
                    product.put("serBillId", purchaseReturn.serBillId)
                    println(product)
                    purchaseReturnDetailService.save(product)
                    println("product saved")
                }
            }
            respond purchaseReturn
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
