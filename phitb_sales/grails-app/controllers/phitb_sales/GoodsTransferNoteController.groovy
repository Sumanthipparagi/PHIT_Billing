package phitb_sales


import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class GoodsTransferNoteController {
    static responseFormats = ['json', 'xml']

    static allowedMethods = [index    : "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE",
                             dataTable: "GET", updateIRNDetails: "PUT", saveGTN: "POST", updateInvoice: "PUT"]
    GoodsTransferNoteService goodsTransferNoteService
    GoodsTransferNoteProductService goodsTransferNoteProductService
    /**
     * Gets all Goods Transfer Note
     * @param query
     * @param offset
     * @param limit
     * @return list of Goods Transfer Note
     */
    def index() {

        try {
            respond goodsTransferNoteService.getAll()
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested Goods Transfer Note
     * @param id
     * @return get requested Goods Transfer Note
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond goodsTransferNoteService.get(id)
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
                respond goodsTransferNoteService.getDraftBillById(id)
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
                respond goodsTransferNoteService.getAllByNoOfDays(params.limit, params.offset, days)
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
            respond goodsTransferNoteService.getAllUnsettledByCustId(customerId, entityId, financialYear)
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
            respond goodsTransferNoteService.getAllsettledByCustId(customerId, entityId, financialYear)
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
     * Save new Goods Transfer Note
     * @param Goods Transfer Note
     * @return saved Goods Transfer Note
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond goodsTransferNoteService.save(jsonObject)
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
     * Update existing Goods Transfer Note Product
     * @param id
     * @param Goods Transfer Note Product
     * @return updated Goods Transfer Note Product
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond goodsTransferNoteService.update(jsonObject, id)
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
     * Delete selected Goods Transfer Note
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            goodsTransferNoteService.delete(id)
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
     * Gets all Goods Transfer Note in datatables format
     * @return list of  Goods Transfer Note
     */
    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject.get("start")
            String length = paramsJsonObject.get("length")
            def goodsTransferNote = goodsTransferNoteService.dataTables(paramsJsonObject, start, length)
            respond goodsTransferNote
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
            GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(params.id))
            if (params.status == "NA" || params.status == null) {
                if (goodsTransferNote) {
                    goodsTransferNote.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double diffBalance = Double.parseDouble(goodsTransferNote.getBalance().toString()) - balance
                        goodsTransferNote.balance = diffBalance
                        goodsTransferNote.adjAmount = goodsTransferNote.getAdjAmount() + balance
                    } else {
                        goodsTransferNote.balance = goodsTransferNote.getBalance()
                        goodsTransferNote.adjAmount = goodsTransferNote.getAdjAmount()
                    }
                    GoodsTransferNote goodsTransferNote1 = goodsTransferNote.save(flush: true)
                    if (goodsTransferNote1) {
                        respond goodsTransferNote1
                        return
                    }
                }
            } else {
                if (goodsTransferNote) {
                    goodsTransferNote.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null) {
                        double updateBalance = Double.parseDouble(goodsTransferNote.getBalance().toString()) + balance
                        goodsTransferNote.balance = updateBalance
                        goodsTransferNote.adjAmount = goodsTransferNote.getAdjAmount() - balance
                    } else {
                        goodsTransferNote.balance = goodsTransferNote.getBalance()
                        goodsTransferNote.adjAmount = goodsTransferNote.getAdjAmount()
                    }
                    GoodsTransferNote goodsTransferNote1 = goodsTransferNote.save(flush: true)
                    if (goodsTransferNote1) {
                        respond goodsTransferNote1
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
            respond goodsTransferNoteService.getRecentByFinancialYearAndEntity(financialYear, entityId, billStatus)
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
     * Gets all Goods Transfer Note
     * @param query
     * @param offset
     * @param limit
     * @return list of Goods Transfer Note
     */
    def getAllByCustomerId() {
        try {
            respond goodsTransferNoteService.getAllByCustomerId(params.id, params.financialYear, params.entityId)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }


    def cancelGTN() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject goodsTransferNote = goodsTransferNoteService.cancelGTN(jsonObject)
            respond goodsTransferNote
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

    def approveGTN() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject goodsTransferNote = goodsTransferNoteService.approveGTN(jsonObject)
            respond goodsTransferNote
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
            GoodsTransferNote goodsTransferNote = goodsTransferNoteService.updateIRNDetails(jsonObject)
            respond goodsTransferNote
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
     * Save new Goods Transfer Note along with products
     * @param Goods Transfer Note
     * @return saved Goods Transfer Note
     */
    def saveGTN() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            GoodsTransferNote goodsTransferNote = goodsTransferNoteService.save(jsonObject.get("gtn"))
            if (goodsTransferNote) {
                UUID uuid
                JSONArray saleProducts = jsonObject.get("gtnProducts")
                for (JSONObject product : saleProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", goodsTransferNote.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", goodsTransferNote.serBillId)
                    goodsTransferNoteProductService.save(product)
                    println("product saved")
                }
            }
            respond goodsTransferNote
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
     * Update Goods Transfer Note along with products
     * @param Goods Transfer Note
     * @return saved Goods Transfer Note
     */
    def updateInvoice() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            GoodsTransferNote goodsTransferNote = goodsTransferNoteService.update(jsonObject.get("saleInvoice"), id)
            if (goodsTransferNote) {
                UUID uuid
                JSONArray saleProducts = jsonObject.get("saleProducts")
                for (JSONObject product : saleProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", goodsTransferNote.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", goodsTransferNote.serBillId)
                    goodsTransferNoteProductService.update(product, product.get("id").toString())
                    println("product saved")
                }
            }
            respond goodsTransferNote
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
     * get Goods Transfer Note between daterange
     * @param daterange
     * @param entityId
     * @return saved Goods Transfer Note
     */
    def getGTNByDateRange() {
        try {
            String dateRange = params.dateRange
            long entityId = params.entityId
            if (dateRange && entityId) {
                respond goodsTransferNoteService.getGTNByDateRange(dateRange, entityId)
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
