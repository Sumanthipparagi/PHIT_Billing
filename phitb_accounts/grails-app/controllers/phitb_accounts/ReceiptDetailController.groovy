package phitb_accounts

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

class ReceiptDetailController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    ReceiptDetailService receiptDetailService
    /**
     * Gets all receipt detail
     * @param query
     * @param offset
     * @param limit
     * @return list of receipt detail
     */
    def index() {

        try {
            respond receiptDetailService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested receipt detail
     * @param id
     * @return get requested receipt detail
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond receiptDetailService.get(id)
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
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def getByEntityId() {
        try {

            if (params.id) {
                respond receiptDetailService.getAllByEntity(params.limit, params.offset,Long.parseLong(params.id))
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
                respond receiptDetailService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Save new receipt detail
     * @param receipt detail
     * @return saved receipt detail
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond receiptDetailService.save(jsonObject)
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
     * Update existing receipt detail
     * @param id
     * @param receipt detail
     * @return updated receipt detail
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond receiptDetailService.update(jsonObject,id)
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
     * Delete selected receipt detail
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            receiptDetailService.delete(id)
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
     * Gets all receipt detail in datatables format
     * @return list of receipt detail
     */
    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject?.start
            String length = paramsJsonObject?.length
            respond receiptDetailService.dataTables(paramsJsonObject, start, length)
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


    def approveReceipt() {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            JSONObject approveReceipt = receiptDetailService.approveReceipt(jsonObject)
            respond approveReceipt
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
    def cancelReceipt()
    {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject receipt = new JSONObject()
        ReceiptDetail receiptDetail = ReceiptDetail.findById(Long.parseLong(id))
        if (receiptDetail)
        {
            if (receiptDetail.financialYear.equalsIgnoreCase(financialYear) && receiptDetail.entityId == Long.parseLong(entityId))
            {
                ArrayList<BillDetailLog> billDetailLogs = BillDetailLog.findAllByReceiptId(receiptDetail.id.toString())
                for (BillDetailLog billDetailLog : billDetailLogs)
                {
                    billDetailLog.status = 0
                    billDetailLog.receiptStatus = "CANCELLED"
                    billDetailLog.isUpdatable = true
                    billDetailLog.save(flush: true)
                }
                receiptDetail.approvedStatus = "CANCELLED"
                receiptDetail.cancelledDate = new Date()
                receiptDetail.isUpdatable = true
                receiptDetail.save(flush: true)
                receipt.put("receiptDetails", receiptDetail)
                receipt.put("billDetailLogs", billDetailLogs)
                respond receipt
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    def getByDateRangeAndEntity() {
        try {
            String dateRange = params.dateRange
            String entityId = params.entityId
            if (dateRange && entityId) {
                JSONArray receiptDetails = receiptDetailService.getByDateRangeAndEntity(dateRange, entityId)
                respond receiptDetails, formats: ['json']
            } else {
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
}
