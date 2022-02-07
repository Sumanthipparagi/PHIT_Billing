package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class FormMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return FormMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FormMaster.findAllByFormNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    FormMaster get(String id) {
        return FormMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return FormMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FormMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "formName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def formMasterCriteria = FormMaster.createCriteria()
        def formMasterArrayList = formMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = formMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", formMasterArrayList)
        return jsonObject
    }

//    String formName
//    String formButtonName
//    String configAllowed
//    long entityType
//    long entityTypeId
//    long createdUser
//    long modifiedUser


    FormMaster save(JSONObject jsonObject) {
        FormMaster formMaster = new FormMaster()
        formMaster.formName = jsonObject.get("formName")
        formMaster.formButtonName = jsonObject.get("formButtonName")
        formMaster.configAllowed = jsonObject.get("configAllowed")
        formMaster.entityType = Long.parseLong(jsonObject.get("entityType").toString())
        formMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        formMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        formMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        formMaster.save(flush: true)
        if (!formMaster.hasErrors())
            return formMaster
        else
            throw new BadRequestException()
    }

    FormMaster update(JSONObject jsonObject, String id) {
        if (id) {
            FormMaster formMaster = FormMaster.findById(Long.parseLong(id))
            if (formMaster) {
                formMaster.isUpdatable = true
                formMaster.formName = jsonObject.get("formName").toString()
                formMaster.formButtonName = jsonObject.get("formButtonName").toString()
                formMaster.configAllowed = jsonObject.get("configAllowed").toString()
                formMaster.entityType = Long.parseLong(jsonObject.get("entityType").toString())
                formMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                formMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                formMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                formMaster.save(flush: true)
                if (!formMaster.hasErrors())
                    return formMaster
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            FormMaster formMaster = FormMaster.findById(Long.parseLong(id))
            if (formMaster) {
                formMaster.isUpdatable = true
                formMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
