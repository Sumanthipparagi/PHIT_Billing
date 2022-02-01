package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TransactionTypeMasterService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return TransactionTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TransactionTypeMaster.findAllByTransactionName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    TransactionTypeMaster get(String id) {
        return TransactionTypeMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "transactionName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def transctionTypeMasterCriteria = TransactionTypeMaster.createCriteria()
        def transctionTypeMasterArrayList = transctionTypeMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('transactionName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = transctionTypeMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", transctionTypeMasterArrayList)
        return jsonObject
    }

    TransactionTypeMaster save(JSONObject jsonObject) {
        TransactionTypeMaster transactionTypeMaster = new TransactionTypeMaster()
        transactionTypeMaster.transactionName = jsonObject.get("transactionName").toString()
        transactionTypeMaster.save(flush: true)
        if (!transactionTypeMaster.hasErrors())
            return transactionTypeMaster
        else
            throw new BadRequestException()
    }

    TransactionTypeMaster update(JSONObject jsonObject, String id) {
        TransactionTypeMaster transactionTypeMaster = TransactionTypeMaster.findById(Long.parseLong(id))
        if (transactionTypeMaster) {
            transactionTypeMaster.isUpdatable = true
            transactionTypeMaster.transactionName = jsonObject.get("transactionName").toString()
            transactionTypeMaster.save(flush: true)
            if (!transactionTypeMaster.hasErrors())
                return transactionTypeMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            TransactionTypeMaster transactionTypeMaster = TransactionTypeMaster.findById(Long.parseLong(id))
            if (transactionTypeMaster) {
                transactionTypeMaster.isUpdatable = true
                transactionTypeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
