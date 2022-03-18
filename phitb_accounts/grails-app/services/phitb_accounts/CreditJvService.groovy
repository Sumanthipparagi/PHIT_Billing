package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CreditJvService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CreditJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CreditJv.findAllByTransIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return CreditJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CreditJv.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return CreditJv.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date transactionDate = cal.getTime()
            return CreditJv.createCriteria().list {
                gt("transactionDate",transactionDate)
            }
        }
    }


    def getAllsettledByCustId(String id)
    {
        if (!id)
        {
            return CreditJv.findAll()
        }
        else
        {
            return CreditJv.findAllByReferenceIdAndStatus(id,1)
        }
    }


    def getAllUnsettledByCustId(String id)
    {
        if (!id)
        {
            return CreditJv.findAll()
        }
        else
        {
            return CreditJv.findAllByReferenceIdAndStatus(id,0)
        }
    }

    CreditJv get(String id) {
        return CreditJv.findById(Long.parseLong(id))
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

        def creditJvCriteria = CreditJv.createCriteria()
        def creditJvArrayList = creditJvCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = creditJvArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", creditJvArrayList)
        return jsonObject
    }

    CreditJv save(JSONObject jsonObject) {
        CreditJv creditJv = new CreditJv()
        creditJv.transId = jsonObject.get("transId").toString()
        creditJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
        creditJv.managerId = Long.parseLong(jsonObject.get("managerId").toString())
        creditJv.totalExpense = Double.parseDouble(jsonObject.get("totalExpense").toString())
        creditJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        creditJv.referenceId = jsonObject.get("referenceId").toString()
        creditJv.finalSubmissionDate = sdf.parse(jsonObject.get("finalSubmissionDate").toString())
        creditJv.financialYear = jsonObject.get("financialYear").toString()
        creditJv.status = Long.parseLong(jsonObject.get("status").toString())
        creditJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        creditJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        creditJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        creditJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        creditJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        creditJv.save(flush: true)
        if (!creditJv.hasErrors())
            return creditJv
        else
            throw new BadRequestException()

    }

    CreditJv update(JSONObject jsonObject, String id) {

        CreditJv creditJv = CreditJv.findById(Long.parseLong(id))
        if (creditJv) {
            creditJv.isUpdatable = true
            creditJv.transId = jsonObject.get("transId").toString()
            creditJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
            creditJv.managerId = Long.parseLong(jsonObject.get("managerId").toString())
            creditJv.totalExpense = Double.parseDouble(jsonObject.get("totalExpense").toString())
            creditJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            creditJv.referenceId = jsonObject.get("referenceId").toString()
            creditJv.finalSubmissionDate = sdf.parse(jsonObject.get("finalSubmissionDate").toString())
            creditJv.financialYear = jsonObject.get("financialYear").toString()
            creditJv.status = Long.parseLong(jsonObject.get("status").toString())
            creditJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            creditJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            creditJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            creditJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            creditJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            creditJv.save(flush: true)
            if (!creditJv.hasErrors())
                return creditJv
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            CreditJv creditJv = CreditJv.findById(Long.parseLong(id))
            if (creditJv) {
                creditJv.isUpdatable = true
                creditJv.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
