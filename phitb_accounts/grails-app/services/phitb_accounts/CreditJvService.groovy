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

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length, long entityId) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long status = 1
        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def creditJvCriteria = CreditJv.createCriteria()
        def creditJvArrayList = creditJvCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionId', '%' + searchTerm + '%')
                }
            }

            isNull('approvedTime')
            eq('status', status)
            eq('entityId', entityId)
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
        long entityId = Long.parseLong(jsonObject.get("entityId").toString())
        String financialYear = jsonObject.get("financialYear").toString()
        long finId = 1
        String transactionId = ""
        CreditJv previousCJv = CreditJv.findByEntityIdAndFinancialYear(entityId, financialYear, [sort: 'id', order: 'desc', max: 1])
        if(previousCJv)
        {
            finId = previousCJv.finId+1
        }
        transactionId = entityId + "/" + "CR" + "/" + finId
        CreditJv creditJv = new CreditJv()
        creditJv.transactionId = transactionId
        creditJv.financialYear = financialYear
        creditJv.finId = finId
        creditJv.remarks = jsonObject.get("remarks").toString()
        creditJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
        creditJv.approverId = Long.parseLong(jsonObject.get("approverId").toString())
        creditJv.toAccount = Long.parseLong(jsonObject.get("toAccount").toString())
        creditJv.debitAccount = Long.parseLong(jsonObject.get("debitAccount").toString())
        creditJv.reason = Long.parseLong(jsonObject.get("reason").toString())
        creditJv.amount = Double.parseDouble(jsonObject.get("amount").toString())
        creditJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
        creditJv.status = Long.parseLong(jsonObject.get("status").toString())
        creditJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        creditJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        creditJv.entityId = entityId
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
            creditJv.transactionId = jsonObject.get("transactionId").toString() //TODO:
            creditJv.financialYear = jsonObject.get("financialYear").toString()
            creditJv.remarks = jsonObject.get("remarks").toString()
            creditJv.employeeId = Long.parseLong(jsonObject.get("employeeId").toString())
            creditJv.approverId = Long.parseLong(jsonObject.get("approverId").toString())
            creditJv.amount = Double.parseDouble(jsonObject.get("amount").toString())
            creditJv.toAccount = Long.parseLong(jsonObject.get("toAccount").toString())
            creditJv.debitAccount = Long.parseLong(jsonObject.get("debitAccount").toString())
            creditJv.reason = Long.parseLong(jsonObject.get("reason").toString())
            creditJv.transactionDate = sdf.parse(jsonObject.get("transactionDate").toString())
            creditJv.status = Long.parseLong(jsonObject.get("status").toString())
            creditJv.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            creditJv.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            creditJv.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            creditJv.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            creditJv.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            creditJv.save(flush: true)
            if (!creditJv.hasErrors()) {
                return creditJv
            }
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

    def approveCreditJv(long id, long entityId, long approverId)
    {
        if(id && entityId)
        {
            CreditJv creditJv = CreditJv.findByIdAndEntityId(id,entityId)
            if(creditJv)
            {
                creditJv.isUpdatable = true
                creditJv.approvedTime = new Date()
                creditJv.approverId = approverId
                creditJv.save(flush:true)

                return creditJv
            }
        }
        return null
    }

    def rejectCreditJv(long id, long entityId, long approverId)
    {
        if(id && entityId)
        {
            CreditJv creditJv = CreditJv.findByIdAndEntityId(id,entityId)
            if(creditJv)
            {
                creditJv.isUpdatable = true
                creditJv.approvedTime = new Date()
                creditJv.approverId = approverId
                creditJv.status = 0
                creditJv.save(flush:true)

                return creditJv
            }
        }
        return null
    }
}
