package phitb_accounts


import grails.converters.*
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

class BillDetailLogController
{
	static responseFormats = ['json', 'xml']

    BillDetailLogService billDetailLogService
    /**
     * Gets all bill payment log
     * @param query
     * @param offset
     * @param limit
     * @return list of bill payment log
     */
    def index() {

        try {
            respond billDetailLogService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested bill payment log
     * @param id
     * @return get requested bill payment log
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond billDetailLogService.get(id)
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

    def getReceiptLogsByBillTypeAndId()
    {
        try {
            String invoiceId = params.id
            String billType = params.billType
            String dateRange = params.dateRange
            if (invoiceId) {
                respond billDetailLogService.getRecieptDetailsByBillIdAndBillType(Long.parseLong(invoiceId), billType, dateRange)
            } else {
                response.status = 404
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

    def getReceiptLogsByBillTypeAndIdStartDate()
    {
        try {
            String invoiceId = params.id
            String billType = params.billType
            String dateRange = params.dateRange
            if (invoiceId) {
                respond billDetailLogService.getReceiptDetailsByBillIdAndBillTypeStartDate(Long.parseLong(invoiceId),
                        billType, dateRange)
            } else {
                response.status = 404
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
     * Get requested bill payment log
     * @param id
     * @return get requested bill payment log
     */
    def recieptDetailsByInvId() {
        try {
            String id = params.id
            if (id) {
                def bill = BillDetailLog.findAllByReceiptIdAndBillType(id,"INVS")
                JSONArray jsonArray = new JSONArray(bill)
                respond jsonArray,formats: ['json']
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
     * Get requested bill payment log
     * @param id
     * @return get requested bill payment log
     */
    def recieptDetailsByCrntId() {
        try {
            String id = params.id
            if (id) {
                def bill = BillDetailLog.findAllByReceiptIdAndBillType(id,"CRNT")
                JSONArray jsonArray = new JSONArray(bill)
                respond jsonArray,formats: ['json']
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
     * Get requested bill payment log
     * @param id
     * @return get requested bill payment log
     */
    def recieptDetailsByGTNId() {
        try {
            String id = params.id
            if (id) {
                def bill = BillDetailLog.findAllByReceiptIdAndBillType(id,"GTN")
                JSONArray jsonArray = new JSONArray(bill)
                respond jsonArray,formats: ['json']
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
     * Save new bill payment log
     * @param bill payment log
     * @return saved bill payment log
     */
    def save() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            respond billDetailLogService.save(jsonObject)
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
     * Update existing bill payment log
     * @param id
     * @param bill payment log
     * @return updated bill payment log
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond billDetailLogService.update(jsonObject,id)
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
     * Delete selected bill payment log
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            billDetailLogService.delete(id)
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
     * Gets all bill payment log in datatables format
     * @return list of bill payment log
     */
    def dataTable() {
        try {
            String start = params.start
            String length = params.length
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            respond billDetailLogService.dataTables(paramsJsonObject, start, length)
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
