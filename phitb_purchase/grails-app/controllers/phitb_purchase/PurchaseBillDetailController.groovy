package phitb_purchase


import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

class PurchaseBillDetailController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    PurchaseBillDetailService purchaseBillDetailService
    PurchaseProductDetailService purchaseProductDetailService
    /**
     * Gets all purchase bill details
     * @param query
     * @param offset
     * @param limit
     * @return list of purchase bill details
     */
    def index() {

        try {
            respond purchaseBillDetailService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested purchase bill detail
     * @param id
     * @return get requested purchase bill detail
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond purchaseBillDetailService.get(id)
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
     * Save new purchase bill detail
     * @param purchase bill detail
     * @return saved purchase bill detail
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond purchaseBillDetailService.save(jsonObject)
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
     * Update existing purchase bill detail
     * @param id
     * @param purchase bill detail
     * @return updated purchase bill details
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond purchaseBillDetailService.update(jsonObject,id)
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
     * Delete selected purchase bill detail
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            purchaseBillDetailService.delete(id)
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
     * Gets all purchase bill details in datatables format
     * @return list of purchase bill details
     */
    def dataTable() {
        try {
            String start = params.start
            String length = params.length
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            respond purchaseBillDetailService.dataTables(paramsJsonObject, start, length)
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

    def getRecentByFinancialYearAndEntity()
    {
        try {
            String financialYear = params.financialYear
            String entityId = params.entityId
            String billStatus = params.billStatus
            respond purchaseBillDetailService.getRecentByFinancialYearAndEntity(financialYear, entityId, billStatus)
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
     * Gets all Sale Bill Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Bill Details
     */
    def getAllBySupplierId()
    {
        try
        {
            respond purchaseBillDetailService.getAllBySupplierId(params.id,params.financialYear,params.entityId)
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    def updateBalance() {
        try {
            PurchaseBillDetail purchaseBillDetail = PurchaseBillDetail.findById(Long.parseLong(params.id))
            if(params.status=="NA" || params.status==null)
            {
                if (purchaseBillDetail)
                {
                    purchaseBillDetail.isUpdatable = true
                    Double balance = Double.parseDouble(params.balance)
                    if (balance > 0 && balance != "" && balance != null)
                    {
                        double diffBalance = Double.parseDouble(purchaseBillDetail.getBalAmount().toString()) - balance
                        purchaseBillDetail.balAmount = String.format("%.2f", diffBalance) as double
                        purchaseBillDetail.adjustedAmount = String.format("%.2f", purchaseBillDetail.getAdjustedAmount() + balance) as double
                    }
                    else
                    {
                        purchaseBillDetail.balAmount = String.format("%.2f", purchaseBillDetail.getBalAmount()) as double
                        purchaseBillDetail.adjustedAmount = String.format("%.2f", purchaseBillDetail.getAdjustedAmount()) as double
                    }
                    PurchaseBillDetail purchaseBillDetail1 = purchaseBillDetail.save(flush: true)
                    if (purchaseBillDetail1)
                    {
                        respond purchaseBillDetail1
                        return
                    }
                }
            }else
            {
                purchaseBillDetail.isUpdatable = true
                Double balance = Double.parseDouble(params.balance)
                if (balance > 0 && balance != "" && balance != null)
                {
                    double updateBalance = Double.parseDouble(purchaseBillDetail.getBalAmount().toString()) + balance
                    purchaseBillDetail.balAmount = String.format("%.2f", updateBalance) as double
                    purchaseBillDetail.adjustedAmount = String.format("%.2f", purchaseBillDetail.getAdjustedAmount() - balance) as double
                }
                else
                {
                    purchaseBillDetail.balAmount = String.format("%.2f", purchaseBillDetail.getBalAmount()) as double
                    purchaseBillDetail.adjustedAmount = String.format("%.2f", purchaseBillDetail.getAdjustedAmount()) as double
                }
                PurchaseBillDetail purchaseBillDetail1 = purchaseBillDetail.save(flush: true)
                if (purchaseBillDetail1)
                {
                    respond purchaseBillDetail1
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

    def getByDateRangeAndEntity()
    {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String dateRange = jsonObject.get("dateRange")
            String entityId = jsonObject.get("entityId")
            if (dateRange && entityId) {
                JSONArray purchaseBillDetails = purchaseBillDetailService.getByDateRangeAndEntity(dateRange, entityId)
                render purchaseBillDetails, formats: ['json']
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
            String supplier = jsonObject.get("supplier")
            if (dateRange && supplier) {
                JSONArray purchaseBillDetails = purchaseBillDetailService.getByDateRangeAndSupplier(dateRange, supplier)
                render purchaseBillDetails, formats: ['json']
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

    def cancelPurchaseBill() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject saleBillDetails = purchaseBillDetailService.cancelPurchaseBill(jsonObject)
            respond saleBillDetails
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

    /**
     * Update Sale Bill Details along with products
     * @param Sale Bill Details
     * @return saved Sale Bill Details
     */
    def updatePurchaseInvoice() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            PurchaseBillDetail purchaseBillDetail = purchaseBillDetailService.update(jsonObject.get("purchaseInvoice"), id)
            if (purchaseBillDetail) {
                UUID uuid
                JSONArray purchaseProducts = jsonObject.get("purchaseProducts")
                for (JSONObject product : purchaseProducts) {
                    uuid = UUID.randomUUID()
                    product.put("uuid", uuid)
                    product.put("billId", purchaseBillDetail.id)
                    product.put("billType", 0) //0 Sale, 1 Purchase
                    product.put("serBillId", purchaseBillDetail.serBillId)
                    String productId = product.get("id").toString()
                    if (!productId.equalsIgnoreCase("0"))
                        purchaseProductDetailService.update(product, productId)
                    else
                        purchaseProductDetailService.save(product)
                    println("product saved")
                }
            }
            respond purchaseBillDetail
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


}
