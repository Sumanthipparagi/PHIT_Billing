package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException


@Transactional
class GeneralLedgerService {


    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        return GeneralLedger.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100         
        return GeneralLedger.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    GeneralLedger get(String id) {
        return GeneralLedger.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length, String entityId) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def generalLedgerCriteria = GeneralLedger.createCriteria()
        def bankRegisterArrayList = generalLedgerCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('docNo', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = bankRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", bankRegisterArrayList)
        return jsonObject
    }

    GeneralLedger save(JSONObject jsonObject) {
        GeneralLedger generalLedger = new GeneralLedger()
        generalLedger.docType = jsonObject.get("docType").toString()
        generalLedger.narration = jsonObject.get("narration").toString()
        generalLedger.fromAccount = Long.parseLong(jsonObject.get("fromAccount").toString())
        generalLedger.toAccount = Long.parseLong(jsonObject.get("toAccount").toString())
        generalLedger.docNo = jsonObject.get("docNo").toString()
        generalLedger.amount = Double.parseDouble(jsonObject.get("amount").toString())
        generalLedger.balance = Double.parseDouble(jsonObject.get("balance").toString())
        generalLedger.financialYear = Double.parseDouble(jsonObject.get("financialYear").toString())
        generalLedger.status = Long.parseLong(jsonObject.get("status").toString())
        generalLedger.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        generalLedger.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        generalLedger.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

        generalLedger.save(flush: true)
        if (!generalLedger.hasErrors())
            return generalLedger
        else
            throw new BadRequestException()

    }

    GeneralLedger update(JSONObject jsonObject, String id) {

        GeneralLedger generalLedger = GeneralLedger.findById(Long.parseLong(id))
        if (generalLedger) {
            generalLedger.isUpdatable = true
            generalLedger.docType = jsonObject.get("docType").toString()
            generalLedger.narration = jsonObject.get("narration").toString()
            generalLedger.fromAccount = Long.parseLong(jsonObject.get("fromAccount").toString())
            generalLedger.toAccount = Long.parseLong(jsonObject.get("toAccount").toString())
            generalLedger.docNo = jsonObject.get("docNo").toString()
            generalLedger.amount = Double.parseDouble(jsonObject.get("amount").toString())
            generalLedger.balance = Double.parseDouble(jsonObject.get("balance").toString())
            generalLedger.financialYear = Double.parseDouble(jsonObject.get("financialYear").toString())
            generalLedger.status = Long.parseLong(jsonObject.get("status").toString())
            generalLedger.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            generalLedger.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            generalLedger.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            generalLedger.save(flush: true)
            if (!generalLedger.hasErrors())
                return generalLedger
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            GeneralLedger generalLedger = GeneralLedger.findById(Long.parseLong(id))
            if (generalLedger) {
                generalLedger.isUpdatable = true
                generalLedger.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

}
