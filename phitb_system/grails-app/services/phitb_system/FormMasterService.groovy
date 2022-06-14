package phitb_system

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
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
                    ilike('formName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
//        def entity = []
//        formMasterArrayList.each {
//            println(it.entityId)
//            def apires1 = showFormByEntityId(it.entityId.toString())
//            entity.push(apires1)
//        }
//        def entityType = []
//        formMasterArrayList.each {
//            println(it.entityTypeId)
//            def apires2 = showFormByEntityTypeId(it.entityTypeId.toString())
//            entityType.push(apires2)
//        }
//        def createduser = []
//        formMasterArrayList.each {
//            println(it.createdUser)
//            def apires3 = showFormBycreatedUser(it.createdUser.toString())
//            createduser.push(apires3)
//        }
//        def modifieduser = []
//        formMasterArrayList.each {
//            println(it.modifiedUser)
//            def apires4 = showFormBymodifiedUser(it.modifiedUser.toString())
//            modifieduser.push(apires4)
//        }
        def recordsTotal = formMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", formMasterArrayList)
//        jsonObject.put("entity", entity)
//        jsonObject.put("entityType", entityType)
//        jsonObject.put("createduser", createduser)
//        jsonObject.put("modifieduser", modifieduser)
        return jsonObject
    }



    FormMaster save(JSONObject jsonObject) {
        FormMaster formMaster = new FormMaster()
        formMaster.formName = jsonObject.get("formName").toString()
        formMaster.formButtonName = jsonObject.get("formButtonName").toString()
        formMaster.configAllowed = jsonObject.get("configAllowed").toString()
        formMaster.formType = jsonObject.get("formType").toString()
        formMaster.entityId = Long.parseLong(jsonObject.get("entity").toString())
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
                formMaster.formType = jsonObject.get("formType").toString()
                formMaster.entityId = Long.parseLong(jsonObject.get("entity").toString())
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

//    def showFormByEntityId(String id)
//    {
//        try
//        {
//            def url = "http://localhost/api/v1.0/entity/entityregister/"+id
//            URL apiUrl = new URL(url)
//            def entity = new JsonSlurper().parseText(apiUrl.text)
//            return entity
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
//            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
//        }
//    }
//
//    def showFormByEntityTypeId(String id)
//    {
//        try
//        {
//            def url = "http://localhost/api/v1.0/entity/entitytypemaster/"+id
//            URL apiUrl = new URL(url)
//            def entity = new JsonSlurper().parseText(apiUrl.text)
//            return entity
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
//            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
//        }
//    }

//    def showFormBycreatedUser(String id)
//    {
//        try
//        {
//            def url = "http://localhost/api/v1.0/entity/userregister/"+id
//            URL apiUrl = new URL(url)
//            def entity = new JsonSlurper().parseText(apiUrl.text)
//            return entity
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
//            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
//        }
//    }
//
//    def showFormBymodifiedUser(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.ENTITY_REGISTER_SHOW+"/"+id
//            URL apiUrl = new URL(url)
//            def entity = new JsonSlurper().parseText(apiUrl.text)
//            return entity
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
//            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
//        }
//    }
}
