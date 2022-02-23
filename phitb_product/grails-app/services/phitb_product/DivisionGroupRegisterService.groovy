package phitb_product

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class DivisionGroupRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DivisionGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DivisionGroupRegister.findAllByDivisionGroupNameIlike("%" + query + "%", [sort: 'id', max: l, offset:
                    o, order:
                    'desc'])
    }

    DivisionGroupRegister get(String id) {
        return DivisionGroupRegister.findById(Long.parseLong(id))
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return DivisionGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return DivisionGroupRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
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
                orderColumn = "divisionGroupName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def divisiongroupRegisterCriteria = DivisionGroupRegister.createCriteria()
        def divisiongroupRegisterArrayList = divisiongroupRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('divisionGroupName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        JSONArray division= []
        divisiongroupRegisterArrayList.each {
            def id = it.divisionIds
            def apires3 = showDivisionGroupByDivisionIds(id.toString().split(",") as List<String>)
            println(apires3)
            division.add(apires3 as String)
        }

        def entity = []
        divisiongroupRegisterArrayList.each {
            def apires1 = showDivisionGroupByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        divisiongroupRegisterArrayList.each {
            def apires2 = showDivisionGroupByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }


        def recordsTotal = divisiongroupRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", divisiongroupRegisterArrayList)
        jsonObject.put("entity", entity)
        jsonObject.put("division", division)
        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    DivisionGroupRegister save(JSONObject jsonObject) {
        DivisionGroupRegister divisionGroupRegister = new DivisionGroupRegister()
        divisionGroupRegister.divisionGroupName = jsonObject.get("divisionGroupName").toString()
        divisionGroupRegister.divGroupShortName = jsonObject.get("divGroupShortName").toString()
        divisionGroupRegister.divisionIds = StringUtils.join(jsonObject.get("divisionIds"),",")
        divisionGroupRegister.status =  Long.parseLong(jsonObject.get("status").toString())
        divisionGroupRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        divisionGroupRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        divisionGroupRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        divisionGroupRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        divisionGroupRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        divisionGroupRegister.save(flush: true)
        if (!divisionGroupRegister.hasErrors())
            return divisionGroupRegister
        else
            throw new BadRequestException()
    }

    DivisionGroupRegister update(JSONObject jsonObject, String id) {
        DivisionGroupRegister divisionGroupRegister = DivisionGroupRegister.findById(Long.parseLong(id))
        if (divisionGroupRegister) {
            divisionGroupRegister.isUpdatable = true
            divisionGroupRegister.divisionGroupName = jsonObject.get("divisionGroupName").toString()
            divisionGroupRegister.divGroupShortName = jsonObject.get("divGroupShortName").toString()
            divisionGroupRegister.divisionIds = StringUtils.join(jsonObject.get("divisionIds"),",")
            divisionGroupRegister.status =  Long.parseLong(jsonObject.get("status").toString())
            divisionGroupRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            divisionGroupRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            divisionGroupRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            divisionGroupRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            divisionGroupRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            divisionGroupRegister.save(flush: true)
            if (!divisionGroupRegister.hasErrors())
                return divisionGroupRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            DivisionGroupRegister divisionGroupRegister = DivisionGroupRegister.findById(Long.parseLong(id))
            if (divisionGroupRegister) {
                divisionGroupRegister.isUpdatable = true
                divisionGroupRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def showDivisionGroupByEntityId(String id)
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

    def showDivisionGroupByEntityTypeId(String id)
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

    def showDivisionGroupByDivisionIds(List<String> ids)
    {

        try
        {
            ArrayList<JSONObject> d =[]
            ids.eachWithIndex { it,index->
                def url = Constants.API_GATEWAY + Constants.DIVISION_MASTER + "/" + it
                URL apiUrl = new URL(url)
                def divisionGroup = new JsonSlurper().parseText(apiUrl.text)
                d.add(divisionGroup as JSONObject)
            }
            return d
        }
        catch (Exception ex)
        {
            System.err.println('Service :DivisionGroup , action :  show  , Ex:' + ex)
            log.error('Service :DivisionGroup , action :  show  , Ex:' + ex)
        }
    }

}
