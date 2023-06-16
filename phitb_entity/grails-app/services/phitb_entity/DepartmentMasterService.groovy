package phitb_entity

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DepartmentMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DepartmentMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DepartmentMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    DepartmentMaster get(String id) {
        return DepartmentMaster.findById(Long.parseLong(id))
    }

    def getAllByEntity(long entityId)
    {
        if (!entityId)
        {
            return DepartmentMaster.findAll()
        }
        else
        {
            return DepartmentMaster.createCriteria().list(){
                entity{
                    eq('id',entityId)
                }
                eq('deleted', false)
            }
        }
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
        def departmentMasterCriteria = DepartmentMaster.createCriteria()
        def departmentMasterMasterArrayList = departmentMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            entity {
                eq('id', entityId)
            }
            order(orderColumn, orderDir)
        }
        def recordsTotal = departmentMasterMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", departmentMasterMasterArrayList)
        return jsonObject
    }

    DepartmentMaster save(JSONObject jsonObject) {
        DepartmentMaster departmentMaster = new DepartmentMaster()
        departmentMaster.name = jsonObject.get("name").toString()
        departmentMaster.description = jsonObject.get("description").toString()
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        departmentMaster.entity = entityRegister
        departmentMaster.entityType = entityRegister.entityType
        departmentMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        departmentMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        departmentMaster.save(flush: true)
        if (!departmentMaster.hasErrors())
            return departmentMaster
        else
            throw new BadRequestException()
    }

    DepartmentMaster update(JSONObject jsonObject, String id) {
        DepartmentMaster departmentMaster = DepartmentMaster.findById(Long.parseLong(id))
        if (departmentMaster) {
            departmentMaster.isUpdatable = true
            departmentMaster.name = jsonObject.get("name").toString()
            departmentMaster.description = jsonObject.get("description").toString()
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            departmentMaster.entity = entityRegister
            departmentMaster.entityType = entityRegister.entityType
            departmentMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            departmentMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            departmentMaster.save(flush: true)
            if (!departmentMaster.hasErrors())
                return departmentMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DepartmentMaster departmentMaster = DepartmentMaster.findById(Long.parseLong(id))
            if (departmentMaster) {
                departmentMaster.isUpdatable = true
                departmentMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
