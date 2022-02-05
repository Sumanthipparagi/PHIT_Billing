package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class DivisionGroupRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DivisionGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DivisionGroupRegister.findAllByDivisionGroupNameIlike("%" + query + "%", [sort: 'id', max: l, offset:
                    o, order:
                    'desc'])
    }

    DivisionGroupRegister get(String id) {
        return DivisionGroupRegister.findById(Long.parseLong(id))
    }

    def getAllByEntity(long limit, long offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return DivisionGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return DivisionGroupRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
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
                orderColumn = "divisionGroupName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def divisiongroupRegisterCriteria = BatchRegister.createCriteria()
        def divisiongroupRegisterArrayList = divisiongroupRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('divisionGroupName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = divisiongroupRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", divisiongroupRegisterArrayList)
        return jsonObject
    }

    DivisionGroupRegister save(JSONObject jsonObject) {
        DivisionGroupRegister divisionGroupRegister = new DivisionGroupRegister()
        divisionGroupRegister.divisionGroupName = jsonObject.get("divisionGroupName").toString()
        divisionGroupRegister.divGroupShortName = jsonObject.get("divGroupShortName").toString()
        divisionGroupRegister.divisionIds = jsonObject.get("divisionIds").toString()
        divisionGroupRegister.status =  Long.parseLong(jsonObject.get("status").toString())
        divisionGroupRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        divisionGroupRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        divisionGroupRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        divisionGroupRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        divisionGroupRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        divisionGroupRegister.save(flush: true)
        if (!divisionGroupRegister.hasErrors())
            return divisionGroupRegister
        else
            throw new BadRequestException()
    }

    DivisionGroupRegister update(JSONObject jsonObject, String id) {
        DivisionGroupRegister divisionGroupRegister = DivisionGroupRegister.findById(Long.parseLong(id))
        if (divisionGroupRegister) {
            divisionGroupRegister.isUpdatable = true
            divisionGroupRegister.divisionGroupName = jsonObject.get("divisionGroupName").toString()
            divisionGroupRegister.divGroupShortName = jsonObject.get("divGroupShortName").toString()
            divisionGroupRegister.divisionIds = jsonObject.get("divisionIds").toString()
            divisionGroupRegister.status =  Long.parseLong(jsonObject.get("status").toString())
            divisionGroupRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            divisionGroupRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            divisionGroupRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            divisionGroupRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            divisionGroupRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            divisionGroupRegister.save(flush: true)
            if (!divisionGroupRegister.hasErrors())
                return divisionGroupRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DivisionGroupRegister divisionGroupRegister = DivisionGroupRegister.findById(Long.parseLong(id))
            if (divisionGroupRegister) {
                divisionGroupRegister.isUpdatable = true
                divisionGroupRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
