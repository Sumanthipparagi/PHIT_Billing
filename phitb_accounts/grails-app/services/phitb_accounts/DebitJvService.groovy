package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DebitJvService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJv.findAllByTransIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DebitJv.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return DebitJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date transactionDate = cal.getTime()
            return DebitJv.createCriteria().list {
                gt("transactionDate",transactionDate)
            }
        }
    }
    DebitJv get(String id) {
        return DebitJv.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "employeeId"
                break;
            case '1':
                orderColumn = "transId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def debitJvCriteria = DebitJv.createCriteria()
        def debitJvArrayList = debitJvCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = debitJvArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", debitJvArrayList)
        return jsonObject
    }

    DebitJv save(JSONObject jsonObject) {
        DebitJv debitJv = new DebitJv()
        debitJv.transId = jsonObject.get("transId").toString()
        debitJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
        debitJv.managerId = Long.parseLong(jsonObject.get("managerId").toString())
        debitJv.totalExpense = Double.parseDouble(jsonObject.get("totalExpense").toString())
        debitJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        debitJv.referenceId = jsonObject.get("referenceId").toString()
        debitJv.finalSubmissionDate = sdf.parse(jsonObject.get("finalSubmissionDate").toString())
        debitJv.financialYear = jsonObject.get("financialYear").toString()
        debitJv.status = Long.parseLong(jsonObject.get("status").toString())
        debitJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        debitJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        debitJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        debitJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        debitJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        debitJv.save(flush: true)
        if (!debitJv.hasErrors())
            return debitJv
        else
            throw new BadRequestException()

    }

    DebitJv update(JSONObject jsonObject, String id) {

        DebitJv debitJv = DebitJv.findById(Long.parseLong(id))
        if (debitJv) {
            debitJv.isUpdatable = true
            debitJv.transId = jsonObject.get("transId").toString()
            debitJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
            debitJv.managerId = Long.parseLong(jsonObject.get("managerId").toString())
            debitJv.totalExpense = Double.parseDouble(jsonObject.get("totalExpense").toString())
            debitJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            debitJv.referenceId = jsonObject.get("referenceId").toString()
            debitJv.finalSubmissionDate = sdf.parse(jsonObject.get("finalSubmissionDate").toString())
            debitJv.financialYear = jsonObject.get("financialYear").toString()
            debitJv.status = Long.parseLong(jsonObject.get("status").toString())
            debitJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            debitJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            debitJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            debitJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            debitJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            debitJv.save(flush: true)
            if (!debitJv.hasErrors())
                return debitJv
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DebitJv debitJv = DebitJv.findById(Long.parseLong(id))
            if (debitJv) {
                debitJv.isUpdatable = true
                debitJv.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
