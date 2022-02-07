package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class PaymentModeMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PaymentModeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PaymentModeMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    PaymentModeMaster get(String id) {
        return PaymentModeMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return PaymentModeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PaymentModeMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
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

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = PaymentModeMaster.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                    accountModeMaster{
                        ilike('name', '%' + searchTerm + '%')
                    }
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = dayMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", dayMasterArrayList)
        return jsonObject
    }

    PaymentModeMaster save(JSONObject jsonObject) {
        PaymentModeMaster paymentModeMaster = new PaymentModeMaster()
        paymentModeMaster.name = jsonObject.get("name")
        paymentModeMaster.accountMode = AccountModeMaster.findById(Long.parseLong(jsonObject.get("accountModeId").toString()))
        paymentModeMaster.save(flush: true)
        if (!paymentModeMaster.hasErrors())
            return paymentModeMaster
        else
            throw new BadRequestException()
    }

    PaymentModeMaster update(JSONObject jsonObject, String id) {
        if (id) {
            PaymentModeMaster paymentModeMaster = PaymentModeMaster.findById(Long.parseLong(id))
            if (paymentModeMaster) {
                paymentModeMaster.isUpdatable = true
                paymentModeMaster.name = jsonObject.get("name")
                paymentModeMaster.accountMode = AccountModeMaster.findById(Long.parseLong(jsonObject.get("accountModeId").toString()))
                paymentModeMaster.save(flush: true)
                if (!paymentModeMaster.hasErrors())
                    return paymentModeMaster
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            PaymentModeMaster paymentModeMaster = PaymentModeMaster.findById(Long.parseLong(id))
            if (paymentModeMaster) {
                paymentModeMaster.isUpdatable = true
                paymentModeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
