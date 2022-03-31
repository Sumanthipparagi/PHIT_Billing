package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class RoleService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return Role.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return Role.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    Role get(String id) {
        return Role.findById(Long.parseLong(id))
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
        def roleCriteria = Role.createCriteria()
        def roleCriteriaArrayList = roleCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = roleCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", roleCriteriaArrayList)
        return jsonObject
    }

    Role save(JSONObject jsonObject) {
        Role role = new Role()
        role.name = jsonObject.get("name").toString()
        role.permittedFeatures = jsonObject.get("permittedFeatures").toString()
        role.save(flush: true)
        if (!role.hasErrors())
            return role
        else
            throw new BadRequestException()
    }

    Role update(JSONObject jsonObject, String id) {
        Role role = Role.findById(Long.parseLong(id))
        if (role) {
            role.isUpdatable = true
            role.name = jsonObject.get("name").toString()
            role.permittedFeatures = jsonObject.get("permittedFeatures").toString()
            role.save(flush: true)
            if (!role.hasErrors())
                return role
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            Role role = Role.findById(Long.parseLong(id))
            if (role) {
                role.isUpdatable = true
                role.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def getAllFeatures() {
        return Feature.findAll()
    }

    Feature getFeature(String id) {
        return Feature.findById(Long.parseLong(id))
    }

    ArrayList<Feature> getFeatureList(String ids) {
        String[] fIds = ids.split(",")
        ArrayList<Long> featureIds = new ArrayList<>()
        for (String fid : fIds) {
            featureIds.add(Long.parseLong(fid))
        }
        return Feature.findAllByIdInList(featureIds)
    }
}
