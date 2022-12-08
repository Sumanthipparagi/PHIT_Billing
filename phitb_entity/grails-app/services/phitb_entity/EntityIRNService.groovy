package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class EntityIRNService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return EntityIRN.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return EntityIRN.findAllByIrnUsername("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    EntityIRN get(String id) {
        return EntityIRN.findById(Long.parseLong(id))
    }

    EntityIRN getByEntity(String id) {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id))
        return EntityIRN.findByEntity(entityRegister)
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
        def entityIRNCriteria = EntityIRN.createCriteria()
        def entityIRNArrayList = entityIRNCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = entityIRNArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", entityIRNArrayList)
        return jsonObject
    }

    EntityIRN save(JSONObject jsonObject) {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        EntityIRN entityIRN = null
        EntityIRN.withDeleted {
            entityIRN = EntityIRN.findByEntity(entityRegister)
            if(entityIRN == null)
                entityIRN = new EntityIRN()
            else {
                entityIRN.isUpdatable = true
                entityIRN.unDelete()
            }
        }
        entityIRN.irnUsername = jsonObject.get("irnUsername").toString()
        entityIRN.irnPassword = jsonObject.get("irnPassword").toString()
        entityIRN.irnGSTIN = jsonObject.get("irnGSTIN").toString()
        entityIRN.forceRefreshAccessToken = Boolean.parseBoolean(jsonObject.get("forceRefreshAccessToken").toString())
        entityIRN.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        entityIRN.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        entityIRN.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        entityIRN.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        entityIRN.save(flush: true)
        if (!entityIRN.hasErrors())
            return entityIRN
        else
            throw new BadRequestException()
    }

    EntityIRN update(JSONObject jsonObject, String id) {
        EntityIRN entityIRN = EntityIRN.findById(Long.parseLong(id))
        if (entityIRN) {
            entityIRN.isUpdatable = true
            entityIRN.irnUsername = jsonObject.get("irnUsername").toString()
            entityIRN.irnPassword = jsonObject.get("irnPassword").toString()
            entityIRN.irnGSTIN = jsonObject.get("irnGSTIN").toString()
//            entityIRN.authToken = jsonObject.get("authToken").toString()
//            entityIRN.tokenExpiry = jsonObject.get("tokenExpiry").toString()
//            entityIRN.sessionId = jsonObject.get("sessionId").toString()
//            entityIRN.appKey = jsonObject.get("appKey").toString()
//            entityIRN.aspSecretKey = jsonObject.get("aspSecretKey").toString()
//            entityIRN.sek = jsonObject.get("sek").toString()
            entityIRN.forceRefreshAccessToken = Boolean.parseBoolean(jsonObject.get("forceRefreshAccessToken").toString())
            entityIRN.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            entityIRN.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            entityIRN.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            entityIRN.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            entityIRN.setActive(jsonObject.has("isActive"))
           def save = entityIRN.save(flush: true)
            if (!entityIRN.hasErrors())
                return entityIRN
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            EntityIRN entityIRN = EntityIRN.findById(Long.parseLong(id))
            if (entityIRN) {
                entityIRN.isUpdatable = true
                entityIRN.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
