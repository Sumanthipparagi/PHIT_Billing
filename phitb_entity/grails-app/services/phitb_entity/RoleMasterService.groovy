package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import javax.management.relation.Role

@Transactional
class RoleMasterService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return RoleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RoleMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return RoleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RoleMaster.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
    }


    RoleMaster get(String id) {
        return RoleMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")

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
        def roleMasterCriteria = RoleMaster.createCriteria()
        def roleMasterCriteriaArrayList = roleMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = roleMasterCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", roleMasterCriteriaArrayList)
        return jsonObject
    }

    RoleMaster save(JSONObject jsonObject) {
        RoleMaster roleMaster = new RoleMaster()
        roleMaster.name = jsonObject.get("name").toString()
        roleMaster.description = jsonObject.get("description").toString()
        roleMaster.entity = EntityRegister.findById( Long.parseLong(jsonObject.get("entity").toString()))
        roleMaster.save(flush: true)
        if (!roleMaster.hasErrors())
            return roleMaster
        else
            throw new BadRequestException()
    }

    RoleMaster update(JSONObject jsonObject, String id) {
        RoleMaster roleMaster = RoleMaster.findById(Long.parseLong(id))
        if (roleMaster) {
            roleMaster.isUpdatable = true
            roleMaster.name = jsonObject.get("name").toString()
            roleMaster.description = jsonObject.get("description").toString()
            roleMaster.entity = EntityRegister.findById( Long.parseLong(jsonObject.get("entity").toString()))
            roleMaster.save(flush: true)
            if (!roleMaster.hasErrors())
                return roleMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            RoleMaster roleMaster = RoleMaster.findById(Long.parseLong(id))
            if (roleMaster) {
                roleMaster.isUpdatable = true
                roleMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
