package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class EntityRouteRegisterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return EntityRouteRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return EntityRouteRegister.findAllByRouteName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    EntityRouteRegister get(String id) {
        return EntityRouteRegister.findById(Long.parseLong(id))
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
                orderColumn = "name"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def entityRouteCriteria = EntityRouteRegister.createCriteria()
        def entityRouteArrayList = entityRouteCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('routeName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = entityRouteArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", entityRouteArrayList)
        return jsonObject
    }

    EntityRouteRegister save(JSONObject jsonObject) {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        EntityRouteRegister entityRouteRegister = new EntityRouteRegister()
        entityRouteRegister.routeName = jsonObject.get("routeName").toString()
        entityRouteRegister.entityRegister = entityRegister
        entityRouteRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        entityRouteRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        entityRouteRegister.save(flush: true)
        if (!entityRouteRegister.hasErrors())
            return entityRouteRegister
        else
            throw new BadRequestException()
    }

    EntityRouteRegister update(JSONObject jsonObject, String id) {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        EntityRouteRegister entityRouteRegister = EntityRouteRegister.findById(Long.parseLong(id))
        if (entityRouteRegister) {
            entityRouteRegister.isUpdatable = true
            entityRouteRegister.routeName = jsonObject.get("routeName").toString()
            entityRouteRegister.entityRegister = entityRegister
            entityRouteRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
           def save = entityRouteRegister.save(flush: true)
            if (!entityRouteRegister.hasErrors())
                return entityRouteRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            EntityRouteRegister entityRouteRegister = EntityRouteRegister.findById(Long.parseLong(id))
            if (entityRouteRegister) {
                entityRouteRegister.isUpdatable = true
                entityRouteRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def getByEntityRegister(String entityId)
    {
        if(entityId)
        {
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(entityId))
            ArrayList<EntityRouteRegister> entityRouteRegisters = EntityRouteRegister.findAllByEntityRegister(entityRegister)
            return entityRouteRegisters
        } else {
            throw new BadRequestException()
        }
    }
}
