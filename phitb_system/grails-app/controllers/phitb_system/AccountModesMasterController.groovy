package phitb_system

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONObject

class AccountModesMasterController {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

    /**
     * Gets all account modes
     * @param query
     * @param offset
     * @param limit
     * @return list of account modes
     */
    def index() {

        try {
            String query = params.query
            Integer offset = params.offset ? Integer.parseInt(params.offset.toString()) : 0
            Integer limit = params.limit ? Integer.parseInt(params.limit.toString()) : 100
            if (!query)
                respond AccountModesMaster.findAll([sort: 'id', max: limit, offset: offset, order: 'desc'])
            else
                respond AccountModesMaster.findAllByModeIlike("%" + query + "%", [sort: 'id', max: limit, offset: offset, order: 'desc'])
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Get requested account mode
     * @param id
     * @return get requested account mode
     */
    def show() {
        try {
            String id = params.id
            if (id) {
                AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
                if (accountModesMaster)
                    respond accountModesMaster
                else
                    render status: 404, view: "/notFound"
            } else {
                render status: 400, view: "/clientError"
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Save new account mode
     * @param mode
     * @return saved account mode
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String mode = jsonObject.get("mode")
            if (mode) {
                AccountModesMaster accountModesMaster = new AccountModesMaster()
                accountModesMaster.mode = mode
                accountModesMaster.save(flush: true)
                if (!accountModesMaster.hasErrors())
                    respond accountModesMaster
                else
                    render status: 400, view: "/clientError"
            } else {
                render status: 400, view: "/clientError"
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Update existing account mode
     * @param id
     * @param mode
     * @return updated account modes
     */
    def update() {
        try {
            String id = params.id
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            String mode = jsonObject.get("mode")
            if (mode && id) {
                AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
                if (accountModesMaster) {
                    accountModesMaster.isUpdatable = true
                    accountModesMaster.mode = mode
                    accountModesMaster.save(flush: true)
                    if (!accountModesMaster.hasErrors())
                        respond accountModesMaster
                    else
                        render status: 400, view: "/clientError"
                } else
                    render status: 404, view: "/notFound"
            } else {
                render status: 400, view: "/clientError"
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Delete selected account mode
     * @param id
     * @return returns ststus code 200
     */
    def delete() {
        try {
            String id = params.id
            if (id) {
                AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
                if (accountModesMaster) {
                    accountModesMaster.delete()
                    response.status = 200
                } else {
                    render status: 404, view: "/notFound"
                }
            } else {
                render status: 400, view: "/clientError"
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }

    /**
     * Gets all account modes in datatables format
     * @return list of account modes
     */
    def dataTable() {
        try {
            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(parameterMap.params)

            String searchTerm = paramsJsonObject.get("search[value]")
            String orderColumnId = paramsJsonObject.get("order[0][column]")
            String orderDir = paramsJsonObject.get("order[0][dir]")

            String orderColumn = "id"
            switch (orderColumnId) {
                case '0':
                    orderColumn = "id"
                    break;
                case '1':
                    orderColumn = "name"
                    break;
            }

            Integer offset = params.start ? Integer.parseInt(params.start.toString()) : 0
            Integer max = params.length ? Integer.parseInt(params.length.toString()) : 100

            def accountModesMasterCriteria = AccountModesMaster.createCriteria()
            def accountModesMasterArrayList = accountModesMasterCriteria.list(max: max, offset: offset) {
                or {
                    if (searchTerm != "") {
                        ilike('mode', '%' + searchTerm + '%')
                    }
                }
                eq('deleted', false)
                order(orderColumn, orderDir)
            }

            def recordsTotal = accountModesMasterArrayList.totalCount
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("draw", paramsJsonObject.draw)
            jsonObject.put("recordsTotal", recordsTotal)
            jsonObject.put("recordsFiltered", recordsTotal)
            jsonObject.put("data", accountModesMasterArrayList)
            respond jsonObject, status: 200
            return
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
        render status: 400, view: "/clientError"
    }
}
