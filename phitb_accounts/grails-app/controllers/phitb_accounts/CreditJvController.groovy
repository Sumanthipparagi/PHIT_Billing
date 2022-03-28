package phitb_accounts

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

class CreditJvController {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET", approveCreditJv: "POST"]

    CreditJvService creditJvService
    /**
     * Gets all credit journal voucher
     * @param query
     * @param offset
     * @param limit
     * @return list of credit journal voucher
     */
    def index() {

        try {
            respond creditJvService.getAll(params.limit, params.offset, params.query)
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
                respond creditJvService.get(id)
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
     * Get requested Stock Book
     * @param id
     * @return get requested Stock Book
     */
    def getByEntityId() {
        try {

            if (params.id) {
                respond creditJvService.getAllByEntity(params.limit, params.offset, Long.parseLong(params.id))
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
            long id = Long.parseLong(params.id)
            String financialYear = params.financialYear
            JSONArray creditJvs = creditJvService.getAllUnsettledByCustId(financialYear, id) as JSONArray
            respond creditJvs, formats: ['json']
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
            long id = Long.parseLong(params.id)
            String financialYear = params.financialYear
            respond creditJvService.getAllsettledByCustId(financialYear, id)
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
                respond creditJvService.getAllByNoOfDays(params.limit, params.offset, days)
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
     * Save new credit journal voucher
     * @param credit journal voucher
     * @return saved credit journal voucher
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond creditJvService.save(jsonObject)
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
     * Update existing credit journal voucher
     * @param id
     * @param credit journal voucher
     * @return updated credit journal voucher
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond creditJvService.update(jsonObject, id)
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
     * Delete selected credit journal voucher
     * @param id
     * @return returns status code 200
     */
    def delete() {
        try {
            String id = params.id
            creditJvService.delete(id)
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
            render creditJvService.dataTables(paramsJsonObject, start, length, Long.parseLong(entityId)) as JSON
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
            response.status = 400
        }
    }


    def updateStatus(Long id) {
        try {
            CreditJv creditJv = CreditJv.findById(id)
            if (creditJv) {
                creditJv.isUpdatable = true
                if (params.type == "settled") {
                    creditJv.status = Long.parseLong("1")
                } else {
                    creditJv.status = Long.parseLong("0")
                }
                CreditJv creditJv1 = creditJv.save(flush: true)
                if (creditJv1) {
                    respond creditJv1
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

    def approveCreditJv() {
        JSONObject jsonObject = new JSONObject(request.reader.text)
        def status = Long.parseLong(jsonObject.get("status").toString())
        def id = Long.parseLong(jsonObject.get("id").toString())
        def entityId = Long.parseLong(jsonObject.get("entityId").toString())
        def approverId = Long.parseLong(jsonObject.get("approverId").toString())
        double debitAcCurrentBalance = Double.parseDouble(jsonObject.get("debitAcCurrentBalance").toString())
        double toAcCurrentBalance =  Double.parseDouble(jsonObject.get("toAcCurrentBalance").toString())
        if (status == 1) {
            CreditJv creditJv = new CreditJvService().approveCreditJv(id, entityId, approverId)
            if (creditJv) {
                //add general ledger to debit account
                GeneralLedger generalLedger = new GeneralLedger()
                generalLedger.docType = "CREDIT-JV"
                generalLedger.docNo = creditJv.transactionId
                generalLedger.narration = creditJv.reason
                generalLedger.account = creditJv.debitAccount
                generalLedger.debitAmount = creditJv.amount
                generalLedger.creditAmount = 0.00
                generalLedger.balance = debitAcCurrentBalance - creditJv.amount
                generalLedger.status = 1
                generalLedger.financialYear = creditJv.financialYear
                generalLedger.entityId = creditJv.entityId
                generalLedger.entityType = creditJv.entityTypeId
                generalLedger.createdUser = creditJv.approverId
                generalLedger.createdUser = creditJv.approverId
                generalLedger.save(flush: true)

                //add general ledger to credit account
                generalLedger = new GeneralLedger()
                generalLedger.docType = "CREDIT-JV"
                generalLedger.docNo = creditJv.transactionId
                generalLedger.narration = creditJv.reason
                generalLedger.account = creditJv.toAccount
                generalLedger.debitAmount = 0.00
                generalLedger.creditAmount = creditJv.amount
                generalLedger.balance = toAcCurrentBalance + creditJv.amount
                generalLedger.status = 1
                generalLedger.financialYear = creditJv.financialYear
                generalLedger.entityId = creditJv.entityId
                generalLedger.entityType = creditJv.entityTypeId
                generalLedger.createdUser = creditJv.approverId
                generalLedger.createdUser = creditJv.approverId
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
