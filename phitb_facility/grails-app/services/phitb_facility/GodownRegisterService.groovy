package phitb_facility

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_facility.Exception.BadRequestException
import phitb_facility.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class GodownRegisterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return GodownRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return GodownRegister.findAllByGodownNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return GodownRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return GodownRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    GodownRegister get(String id) {
        return GodownRegister.findById(Long.parseLong(id))
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
                orderColumn = "godownName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def godownMasterCriteria = GodownRegister.createCriteria()
        def godownMasterArrayList = godownMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('godownName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def entity = []
        godownMasterArrayList.each {
            println(it.entityId)
            def apires1 = showFormByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        godownMasterArrayList.each {
            println(it.entityTypeId)
            def apires2 = showFormByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }

        def manager = []
        godownMasterArrayList.each {
            println(it.managerId)
            def apires3 = showGodownByManager(it.managerId.toString())
            manager.push(apires3)
        }
        def recordsTotal = godownMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("entity", entity)
        jsonObject.put("manager", manager)
        jsonObject.put("entityType", entityType)
        jsonObject.put("data", godownMasterArrayList)
        return jsonObject
    }

    GodownRegister save(JSONObject jsonObject) {
        GodownRegister godownRegister = new GodownRegister()
        godownRegister.godownName = jsonObject.get("godownName")
        godownRegister.premises = Long.parseLong(jsonObject.get("premises").toString())
        godownRegister.ccmEnabled = Long.parseLong(jsonObject.get("ccmEnabled").toString())
        godownRegister.managerId = Long.parseLong(jsonObject.get("managerId").toString())
        godownRegister.status = Long.parseLong(jsonObject.get("status").toString())
        godownRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        godownRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        godownRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        godownRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        godownRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        godownRegister.save(flush: true)
        if (!godownRegister.hasErrors())
            return godownRegister
        else
            throw new BadRequestException()
    }

    GodownRegister update(JSONObject jsonObject, String id) {

        if (id) {

            GodownRegister godownRegister = GodownRegister.findById(Long.parseLong(id))
            if (godownRegister) {
                godownRegister.isUpdatable = true
                godownRegister.godownName = jsonObject.get("godownName")
                godownRegister.premises = Long.parseLong(jsonObject.get("premises").toString())
                godownRegister.ccmEnabled = Long.parseLong(jsonObject.get("ccmEnabled").toString())
                godownRegister.managerId = Long.parseLong(jsonObject.get("managerId").toString())
                godownRegister.status = Long.parseLong(jsonObject.get("status").toString())
                godownRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                godownRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                godownRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                godownRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                godownRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                godownRegister.save(flush: true)
                if (!godownRegister.hasErrors())
                    return godownRegister
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
            GodownRegister godownRegister = GodownRegister.findById(Long.parseLong(id))
            if (godownRegister) {
                godownRegister.isUpdatable = true
                godownRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


    def showFormByEntityId(String id)
    {
        try
        {
            def url = Constants.API_GATEWAY+Constants.ENTITY_REGISTER_SHOW+"/"+id
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            return entity
        }
        catch (Exception ex)
        {
            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
        }
    }

    def showFormByEntityTypeId(String id)
    {
        try
        {
            def url = Constants.API_GATEWAY+Constants.ENTITY_TYPE_SHOW+"/"+id
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            return entity
        }
        catch (Exception ex)
        {
            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
        }
    }

    def showGodownByManager(String id)
    {
        try
        {
            def url = Constants.API_GATEWAY+Constants.USER_REGISTER_SHOW+"/"+id
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            return entity
        }
        catch (Exception ex)
        {
            System.err.println('Service :GodownRegister , action :  show  , Ex:' + ex)
            log.error('Service :GodownRegister , action :  show  , Ex:' + ex)
        }
    }

    def showFormBymodifiedUser(String id)
    {
        try
        {
            def url = Constants.API_GATEWAY+Constants.USER_REGISTER_SHOW+"/"+id
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            return entity
        }
        catch (Exception ex)
        {
            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
        }
    }
}
