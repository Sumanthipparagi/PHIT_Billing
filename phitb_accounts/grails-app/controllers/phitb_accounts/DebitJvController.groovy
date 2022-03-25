package phitb_accounts

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

class DebitJvController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    DebitJvService debitJvService
    /**
     * Gets all credit journal voucher
     * @param query
     * @param offset
     * @param limit
     * @return list of credit journal voucher
     */
    def index() {

        try {
            respond debitJvService.getAll(params.limit, params.offset, params.query)
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested credit journal voucher
     * @param id
     * @return get requested credit journal voucher
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                respond debitJvService.get(id)
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
                respond debitJvService.getAllByEntity(params.limit, params.offset,Long.parseLong(params.id))
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
                respond debitJvService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Save new credit journal voucher
     * @param credit journal voucher
     * @return saved credit journal voucher
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond debitJvService.save(jsonObject)
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
     * Update existing credit journal voucher
     * @param id
     * @param credit journal voucher
     * @return updated credit journal voucher
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond debitJvService.update(jsonObject,id)
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
     * Delete selected credit journal voucher
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            debitJvService.delete(id)
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
     * Gets all credit journal voucher in datatables format
     * @return list of credit journal voucher
     */
    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)
            String start = paramsJsonObject.get("start")
            String length = paramsJsonObject.get("length")
            String entityId = paramsJsonObject.get("entityId")
            respond debitJvService.dataTables(paramsJsonObject, start, length, Long.parseLong(entityId))
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

    def approveDebitJv()
    {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        def status = Long.parseLong(jsonObject.get("status").toString())
        def id = Long.parseLong(jsonObject.get("id").toString())
        def entityId = Long.parseLong(jsonObject.get("entityId").toString())
        def approverId = Long.parseLong(jsonObject.get("approverId").toString())
        double creditAcCurrentBalance = Double.parseDouble(jsonObject.get("creditAcCurrentBalance"))
        double fromAcCurrentBalance =Double.parseDouble(jsonObject.get("fromAcCurrentBalance"))
        if (status == 1) {
            DebitJv debitJv = new DebitJvService().approveDebitJv(id, entityId, approverId)
            if (debitJv) {
                //add general ledger to debit account
                GeneralLedger generalLedger = new GeneralLedger()
                generalLedger.docType = "DEBIT-JV"
                generalLedger.docNo = debitJv.transactionId
                generalLedger.narration = debitJv.reason
                generalLedger.account = debitJv.creditAccount
                generalLedger.debitAmount = 0.00
                generalLedger.creditAmount = debitJv.amount
                generalLedger.balance = creditAcCurrentBalance + debitJv.amount
                generalLedger.status = 1
                generalLedger.financialYear = debitJv.financialYear
                generalLedger.entityId = debitJv.entityId
                generalLedger.entityType = debitJv.entityTypeId
                generalLedger.createdUser = debitJv.approverId
                generalLedger.createdUser = debitJv.approverId
                generalLedger.save(flush: true)

                //add general ledger to credit account
                generalLedger = new GeneralLedger()
                generalLedger.docType = "DEBIT-JV"
                generalLedger.docNo = debitJv.transactionId
                generalLedger.narration = debitJv.reason
                generalLedger.account = debitJv.fromAccount
                generalLedger.debitAmount = debitJv.amount
                generalLedger.creditAmount = 0.00
                generalLedger.balance = fromAcCurrentBalance - debitJv.amount
                generalLedger.status = 1
                generalLedger.financialYear = debitJv.financialYear
                generalLedger.entityId = debitJv.entityId
                generalLedger.entityType = debitJv.entityTypeId
                generalLedger.createdUser = debitJv.approverId
                generalLedger.createdUser = debitJv.approverId
                generalLedger.save(flush: true)

                response.status = 200
            } else
                response.status = 400
        } else {

            CreditJv creditJv = new CreditJvService().rejectCreditJv(id, entityId, approverId)
            if (creditJv)
                response.status = 200
            else
                response.status = 400
        }
    }
}
