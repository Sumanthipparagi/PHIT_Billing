package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class RoleFormMappingService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return RoleFormMapping.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RoleFormMapping
    }

    RoleFormMapping get(String id) {
        return RoleFormMapping.findById(Long.parseLong(id))
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

        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def roleFormMappingCriteria = RoleFormMapping.createCriteria()
        def roleFormMappingArrayList = roleFormMappingCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('roleId', '%' + searchTerm + '%')
                    ilike('formIds', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = roleFormMappingArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", roleFormMappingArrayList)
        return jsonObject
    }

    RoleFormMapping save(JSONObject jsonObject) {
        RoleFormMapping roleFormMapping = new RoleFormMapping()
        roleFormMapping.roleId = Long.parseLong(jsonObject.get("roleId").toString())
        roleFormMapping.formIds = Long.parseLong(jsonObject.get("formIds").toString())
        roleFormMapping.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        roleFormMapping.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        roleFormMapping.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        roleFormMapping.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        roleFormMapping.save(flush: true)
        if (!roleFormMapping.hasErrors())
            return roleFormMapping
        else
            throw new BadRequestException()

    }

    RoleFormMapping update(JSONObject jsonObject, String id) {

        RoleFormMapping roleFormMapping = RoleFormMapping.findById(Long.parseLong(id))
        if (roleFormMapping) {
            roleFormMapping.isUpdatable = true
            roleFormMapping.roleId = Long.parseLong(jsonObject.get("roleId").toString())
            roleFormMapping.formIds = Long.parseLong(jsonObject.get("formIds").toString())
            roleFormMapping.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            roleFormMapping.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            roleFormMapping.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            roleFormMapping.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            roleFormMapping.save(flush: true)
            if (!roleFormMapping.hasErrors())
                return roleFormMapping
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            RoleFormMapping roleFormMapping = RoleFormMapping.findById(Long.parseLong(id))
            if (roleFormMapping) {
                roleFormMapping.isUpdatable = true
                roleFormMapping.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
