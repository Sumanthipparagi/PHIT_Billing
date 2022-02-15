package phitb_facility

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_facility.Exception.BadRequestException
import phitb_facility.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class FridgeMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm")


    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return FridgeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FridgeMaster.findAllByFridgeNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return FridgeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return FridgeMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    FridgeMaster get(String id) {
        return FridgeMaster.findById(Long.parseLong(id))
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
                orderColumn = "fridgeName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def fridgeMasterCriteria = FridgeMaster.createCriteria()
        def fridgeMasterArrayList = fridgeMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('fridgeName', '%' + searchTerm + '%')
                    ilike('machinePartNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def entity = []
        fridgeMasterArrayList.each {
            println(it.entityId)
            def apires1 = showFormByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        fridgeMasterArrayList.each {
            println(it.entityTypeId)
            def apires2 = showFormByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }
        def createduser = []
        fridgeMasterArrayList.each {
            println(it.createdUser)
            def apires3 = showFormBycreatedUser(it.createdUser.toString())
            createduser.push(apires3)
        }
        def modifieduser = []
        fridgeMasterArrayList.each {
            println(it.modifiedUser)
            def apires4 = showFormBymodifiedUser(it.modifiedUser.toString())
            modifieduser.push(apires4)
        }

        def recordsTotal = fridgeMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("entity", entity)
        jsonObject.put("entityType", entityType)
        jsonObject.put("createduser", createduser)
        jsonObject.put("modifieduser", modifieduser)
        jsonObject.put("data", fridgeMasterArrayList)
        return jsonObject
    }

    FridgeMaster save(JSONObject jsonObject) {
        FridgeMaster fridgeMaster = new FridgeMaster()
        fridgeMaster.fridgeName = jsonObject.get("fridgeName").toString()
        fridgeMaster.floor = jsonObject.get("floor").toString()
        fridgeMaster.dateOfPurchase = sdf.parse(jsonObject.get("dateOfPurchase").toString())
        fridgeMaster.machinePartNumber = jsonObject.get("machinePartNumber").toString()
        fridgeMaster.status = Long.parseLong(jsonObject.get("status").toString())
        fridgeMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        fridgeMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        fridgeMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        fridgeMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        fridgeMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        fridgeMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        fridgeMaster.save(flush: true)
        if (!fridgeMaster.hasErrors())
            return fridgeMaster
        else
            throw new BadRequestException()
    }

    FridgeMaster update(JSONObject jsonObject, String id) {
        if (id) {
            FridgeMaster fridgeMaster = FridgeMaster.findById(Long.parseLong(id))
            if (fridgeMaster) {
                fridgeMaster.isUpdatable = true
                fridgeMaster.fridgeName = jsonObject.get("fridgeName").toString()
                fridgeMaster.floor = jsonObject.get("floor").toString()
                fridgeMaster.dateOfPurchase = sdf.parse(jsonObject.get("dateOfPurchase").toString())
                fridgeMaster.machinePartNumber = jsonObject.get("machinePartNumber")
                fridgeMaster.status = Long.parseLong(jsonObject.get("status").toString())
                fridgeMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                fridgeMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                fridgeMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                fridgeMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                fridgeMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                fridgeMaster.save(flush: true)
                if (!fridgeMaster.hasErrors())
                    return fridgeMaster
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
            FridgeMaster fridgeMaster = FridgeMaster.findById(Long.parseLong(id))
            if (fridgeMaster) {
                fridgeMaster.isUpdatable = true
                fridgeMaster.delete()
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
            def url = "http://localhost/api/v1.0/entity/entityregister/"+id
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
            def url = "http://localhost/api/v1.0/entity/entitytypemaster/"+id
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

    def showFormBycreatedUser(String id)
    {
        try
        {
            def url = "http://localhost/api/v1.0/entity/userregister/"+id
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

    def showFormBymodifiedUser(String id)
    {
        try
        {
            def url = "http://localhost/api/v1.0/entity/userregister/"+id
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
