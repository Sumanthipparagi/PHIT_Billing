package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EntitySettingService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return EntitySetting.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return EntitySetting.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    EntitySetting get(String id) {
        return EntitySetting.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "code"
                break;
            case '1':
                orderColumn = "entityId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def walletMasterCriteria = EntitySetting.createCriteria()
        def walletMasterArrayList = walletMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('code', '%' + searchTerm + '%')
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

    EntitySetting save(JSONObject jsonObject) {
        EntitySetting entitySetting = new EntitySetting()
        entitySetting.name = jsonObject.get("name").toString()
        entitySetting.code = jsonObject.get("code").toString()
        entitySetting.value = jsonObject.get("value").toString()
        entitySetting.save(flush: true)
        if (!entitySetting.hasErrors())
            return entitySetting
        else
            throw new BadRequestException()

    }

    EntitySetting update(JSONObject jsonObject, String id) {

        EntitySetting entitySetting = EntitySetting.findById(Long.parseLong(id))
        if (entitySetting) {
            entitySetting.isUpdatable = true
            entitySetting.name = jsonObject.get("name").toString()
            entitySetting.code = jsonObject.get("code").toString()
            entitySetting.value = jsonObject.get("value").toString()
            entitySetting.save(flush: true)
            if (!entitySetting.hasErrors())
                return entitySetting
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            EntitySetting entitySetting = EntitySetting.findById(Long.parseLong(id))
            if (entitySetting) {
                entitySetting.isUpdatable = true
                entitySetting.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
