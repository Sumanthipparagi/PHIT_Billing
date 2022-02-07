package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class UnitTypeMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return UnitTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return UnitTypeMaster.findAllByUnitNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return UnitTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return UnitTypeMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    UnitTypeMaster get(String id) {
        return UnitTypeMaster.findById(Long.parseLong(id))
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
                orderColumn = "unitName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def unitTypeMasterCriteria = UnitTypeMaster.createCriteria()
        def unitTypeMasterArrayList = unitTypeMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('unitName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = unitTypeMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", batchRegisterArrayList)
        return jsonObject
    }

    UnitTypeMaster save(JSONObject jsonObject) {
        UnitTypeMaster unitTypeMaster = new UnitTypeMaster()

        unitTypeMaster.unitName = jsonObject.get("unitName").toString()
        unitTypeMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        unitTypeMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        unitTypeMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        unitTypeMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        unitTypeMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        unitTypeMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        unitTypeMaster.save(flush: true)
        if (!unitTypeMaster.hasErrors())
            return unitTypeMaster
        else
            throw new BadRequestException()
    }

    UnitTypeMaster update(JSONObject jsonObject, String id) {
        UnitTypeMaster unitTypeMaster = UnitTypeMaster.findById(Long.parseLong(id))
        if (unitTypeMaster) {
            unitTypeMaster.isUpdatable = true
            unitTypeMaster.unitName = jsonObject.get("unitName").toString()
            unitTypeMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            unitTypeMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            unitTypeMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            unitTypeMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            unitTypeMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            unitTypeMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            unitTypeMaster.save(flush: true)
            if (!unitTypeMaster.hasErrors())
                return unitTypeMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            UnitTypeMaster unitTypeMaster = UnitTypeMaster.findById(Long.parseLong(id))
            if (unitTypeMaster) {
                unitTypeMaster.isUpdatable = true
                unitTypeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
