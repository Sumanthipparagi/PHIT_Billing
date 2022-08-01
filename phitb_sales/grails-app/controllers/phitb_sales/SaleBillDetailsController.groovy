package phitb_sales


import grails.rest.*
import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class SaleBillDetailsController {
    static responseFormats = ['json', 'xml']

    static allowedMethods = [index    : "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE",
                             dataTable: "GET", updateIRNDetails: "PUT", saveInvoice: "POST", updateInvoice: "PUT", getByDateRangeAndEntity: "POST"]
    SaleBillDetailsService saleBillDetailsService
    SaleProductDetailsService saleProductDetailsService
    /**
     * Gets all Sale Bill Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Bill Details
     */
    def index() {

        try {
            respond saleBillDetailsService.getAll()
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Sale Bill Details
     * @param id
     * @return get requested Sale Bill Details
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond saleBillDetailsService.get(id)
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


    def getDraftBillById() {
        try {
            String id = params.id
            if (id) {
                respond saleBillDetailsService.getDraftBillById(id)
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
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllByDays() {
        try {
            String days = params.days
            if (days) {
                respond saleBillDetailsService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Get requested Credit Debit Details
     * @param id
     * @return get requested Credit Debit Details
     */
    def getAllUnsettledByCustId() {
        try {
            String customerId = params.id
            String entityId = params.entityId
            String financialYear = params.financialYear
            respond saleBillDetailsService.getAllUnsettledByCustId(customerId, entityId, financialYear)
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
            respond saleBillDetailsService.getAllsettledByCustId(customerId, entityId, financialYear)
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
     * Save new Sale Bill Details
     * @param Sale Bill Details
     * @return saved Sale Bill Details
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond saleBillDetailsService.save(jsonObject)
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
     * Update existing Sale Product Details
     * @param id
     * @param Sale Product Details
     * @return updated Sale Product Details
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond saleBillDetailsService.update(jsonObject, id)
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
     * Delete selected Sale Bill Details
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            saleBillDetailsService.delete(id)
            response.status = 200
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
     * Gets all Sale Bill Details in datatables format
     * @return list of  Sale Bill Details
     */
    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject.get("start")
            String length = paramsJsonObject.get("length")
            def saleBillDetails = saleBillDetailsService.dataTables(paramsJsonObject, start, length)
            respond saleBillDetails
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


//    def updatePaymentStatus(Long id)
//    {
//        try
//        {
//            SaleBillDetails saleBillDetails = SaleBillDetails.findById(id)
//            if (saleBillDetails)
//            {
//                saleBillDetails.isUpdatable = true
//                if (params.type == "settled")
//                {
//                    saleBillDetails.paymentStatus = Long.parseLong("1")
//                    saleBillDetails.adjAmount = saleBillDetails.getBalance() - Double.parseDouble(params.adj)
//
//                }
//                else
//                {
//                    saleBillDetails.adjAmount = 0
//                    saleBillDetails.paymentStatus = Long.parseLong("0")
//                }
//                SaleBillDetails saleBillDetails1 = saleBillDetails.save(flush: true)
//                if (saleBillDetails1)
//                {
//                    respond saleBillDetails1
//                    return
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//        }
//        response.status = 400
//    }


    def updateBalance() {
        try {
            SaleBillDetails saleBillDetails = SaleBillDetails.findById(Long.parseLong(params.id))
            if (params.status == "NA" || params.status == null) {
                if (saleBillDetails) {
                    saleBillDetails.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double diffBalance = Double.parseDouble(saleBillDetails.getBalance().toString()) - balance
                        saleBillDetails.balance = String.format("%.2f", diffBalance) as double
                        saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount() + balance) as double
                    } else {
                        saleBillDetails.balance = String.format("%.2f", saleBillDetails.getBalance()) as double
                        saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount()) as double
                    }
                    SaleBillDetails saleBillDetails1 = saleBillDetails.save(flush: true)
                    if (saleBillDetails1) {
                        respond saleBillDetails1
                        return
                    }
                }
            } else {
                saleBillDetails.isUpdatable = true
                Double balance = Double.parseDouble(params.balance)
                if (balance > 0 && balance != "" && balance != null) {
                    double updateBalance = Double.parseDouble(saleBillDetails.getBalance().toString()) + balance
                    saleBillDetails.balance = String.format("%.2f", updateBalance) as double
                    saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount() - balance) as double
                } else {
                    saleBillDetails.balance = String.format("%.2f", saleBillDetails.getBalance()) as double
                    saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount()) as double
                }
                SaleBillDetails saleBillDetails1 = saleBillDetails.save(flush: true)
                if (saleBillDetails1) {
                    respond saleBillDetails1
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


    def updateBalanceAndSettleCredits() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            String saleReturnIds = jsonObject.saleReturnIds
            double creditsApplied = 0
            if (jsonObject.creditsApplied) {
                creditsApplied = jsonObject.creditsApplied
            }
            SaleBillDetails saleBillDetails = SaleBillDetails.findById(jsonObject.id)
            if (jsonObject.status == "NA" || jsonObject.status == null) {
                if (saleBillDetails) {
                    saleBillDetails.isUpdatable = true
                    double totalDue = saleBillDetails.balance
                    Double paidNow = jsonObject.paidNow
                    if (creditsApplied > 0) {
                        //updating sale return / credit note
                        ArrayList<String> saleRtrnIds = saleReturnIds.split(",")
                        for (String id : saleRtrnIds) {
                            SaleReturn saleReturn = SaleReturn.findByIdAndBalanceGreaterThan(Long.parseLong(id), 0)
                            double saleReturnBalanceBefore = saleReturn.balance
                            double saleReturnBalance = saleReturn.balance
                            totalDue = totalDue - saleReturnBalance
                            if (totalDue >= 0) {
                                saleReturn.balance = 0
                            } else {
                                saleReturn.balance = Math.abs(totalDue)
                                totalDue = 0
                            }
                            println("Total Due: " + totalDue)
                            saleReturn.isUpdatable = true
                            def savedSaleReturn = saleReturn.save()
                            //saving log
                            if (savedSaleReturn) {
                                SaleReturnAdjustment saleReturnAdjustment = new SaleReturnAdjustment()
                                saleReturnAdjustment.saleReturn = savedSaleReturn
                                saleReturnAdjustment.totalAmount = saleBillDetails.invoiceTotal
                                saleReturnAdjustment.adjAmount = saleReturnBalanceBefore
                                saleReturnAdjustment.balanceBefore = saleReturnBalanceBefore
                                saleReturnAdjustment.currentBalance = savedSaleReturn.balance
                                saleReturnAdjustment.docId = Long.parseLong(jsonObject.get("docId").toString())
                                saleReturnAdjustment.docType = jsonObject.get("docType")
                                saleReturnAdjustment.entityId = savedSaleReturn.entityId
                                saleReturnAdjustment.entityTypeId = savedSaleReturn.entityTypeId
                                saleReturnAdjustment.createdUser = savedSaleReturn.createdUser
                                saleReturnAdjustment.modifiedUser = savedSaleReturn.modifiedUser
                                saleReturnAdjustment.save()
                            }
                        }
                        paidNow += creditsApplied
                        saleBillDetails.creditadjAmount = creditsApplied
                        saleBillDetails.creditIds = saleReturnIds

                    }

                    //updating sale invoice
                    if (paidNow > 0 && paidNow != "" && paidNow != null) {
                        double diffBalance = Double.parseDouble(saleBillDetails.getBalance().toString()) - paidNow
                        saleBillDetails.balance = String.format("%.2f", diffBalance) as double
                        saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount() + paidNow) as double
                    } else {
                        saleBillDetails.balance = String.format("%.2f", saleBillDetails.getBalance()) as double
                        saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount()) as double
                    }
                    SaleBillDetails saleBillDetails1 = saleBillDetails.save(flush: true)
                    if (saleBillDetails1) {
                        respond saleBillDetails1
                        return
                    }
                }
            } else {
/*                saleBillDetails.isUpdatable = true
                Double balance = Double.parseDouble(jsonObject.balance)
                if (balance > 0 && balance != "" && balance != null) {
                    double updateBalance = Double.parseDouble(saleBillDetails.getBalance().toString()) + balance
                    saleBillDetails.balance = String.format("%.2f", updateBalance) as double
                    saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount() - balance) as double
                } else {
                    saleBillDetails.balance = String.format("%.2f", saleBillDetails.getBalance()) as double
                    saleBillDetails.adjAmount = String.format("%.2f", saleBillDetails.getAdjAmount()) as double
                }
                SaleBillDetails saleBillDetails1 = saleBillDetails.save(flush: true)
                if (saleBillDetails1) {
                    respond saleBillDetails1
                    return
                }*/
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
            respond saleBillDetailsService.getRecentByFinancialYearAndEntity(financialYear, entityId, billStatus)
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
     * Gets all Sale Bill Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Bill Details
     */
    def getAllByCustomerId() {
        try {
            respond saleBillDetailsService.getAllByCustomerId(params.id, params.financialYear, params.entityId)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    def cancelSaleBill() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject saleBillDetails = saleBillDetailsService.cancelSaleBill(jsonObject)
            respond saleBillDetails
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

    def updateIRNDetails() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            SaleBillDetails saleBillDetails = saleBillDetailsService.updateIRNDetails(jsonObject)
            respond saleBillDetails
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
    def saveInvoice() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            SaleBillDetails saleBillDetails = saleBillDetailsService.save(jsonObject.get("saleInvoice"))
            if (saleBillDetails) {
                UUID uuid
                JSONArray saleProducts = jsonObject.get("saleProducts")
                for (JSONObject product : saleProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", saleBillDetails.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", saleBillDetails.serBillId)
                    saleProductDetailsService.save(product)
                    println("product saved")
                }
            }
            respond saleBillDetails
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
     * Update Sale Bill Details along with products
     * @param Sale Bill Details
     * @return saved Sale Bill Details
     */
    def updateInvoice() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            SaleBillDetails saleBillDetails = saleBillDetailsService.update(jsonObject.get("saleInvoice"), id)
            if (saleBillDetails) {
                UUID uuid
                JSONArray saleProducts = jsonObject.get("saleProducts")
                for (JSONObject product : saleProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", saleBillDetails.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", saleBillDetails.serBillId)
                    String productId = product.get("id").toString()
                    if (!productId.equalsIgnoreCase("0"))
                        saleProductDetailsService.update(product, productId)
                    else
                        saleProductDetailsService.save(product)
                    println("product saved")
                }
            }
            respond saleBillDetails
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
                JSONArray saleBillDetails = saleBillDetailsService.getByDateRangeAndEntity(dateRange, entityId)
                respond saleBillDetails, formats: ['json']
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
}
