package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EntityTypeMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return EntityTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return EntityTypeMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    EntityTypeMaster get(String id) {
        return EntityTypeMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "walletName"
                break;
            case '1':
                orderColumn = "entityId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def walletMasterCriteria = EntityTypeMaster.createCriteria()
        def walletMasterArrayList = walletMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('walletName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = walletMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", walletMasterArrayList)
        return jsonObject
    }

    EntityTypeMaster save(JSONObject jsonObject) {
        EntityTypeMaster entityTypeMaster = new EntityTypeMaster()
        entityTypeMaster.name = jsonObject.get("name").toString()
        entityTypeMaster.description = Long.parseLong(jsonObject.get("description").toString())
        entityTypeMaster.save(flush: true)
        if (!entityTypeMaster.hasErrors())
            return entityTypeMaster
        else
            throw new BadRequestException()

    }

    EntityTypeMaster update(JSONObject jsonObject, String id) {

        EntityTypeMaster entityTypeMaster = EntityTypeMaster.findById(Long.parseLong(id))
        if (entityTypeMaster) {
            entityTypeMaster.isUpdatable = true
            entityTypeMaster.name = jsonObject.get("name").toString()
            entityTypeMaster.description = Long.parseLong(jsonObject.get("description").toString())
            entityTypeMaster.save(flush: true)
            if (!entityTypeMaster.hasErrors())
                return entityTypeMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            EntityTypeMaster entityTypeMaster = EntityTypeMaster.findById(Long.parseLong(id))
            if (entityTypeMaster) {
                entityTypeMaster.isUpdatable = true
                entityTypeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
