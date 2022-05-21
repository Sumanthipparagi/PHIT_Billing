package phitb_facility

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_facility.Exception.BadRequestException
import phitb_facility.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CcmRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CcmRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CcmRegister.findAllByKitNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return CcmRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CcmRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    CcmRegister get(String id) {
        return CcmRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        long entityId = paramsJsonObject.get("entityId")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "kitName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def ccmMasterCriteria = CcmRegister.createCriteria()
        def ccmMasterArrayList = ccmMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('kitName', '%' + searchTerm + '%')
                    ilike('kitNumber', '%' + searchTerm + '%')
                }
                fridge{
                    ilike('fridgeName', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def entity = []
//        ccmMasterArrayList.each {
//            println(it.entityId)
//            def apires1 = showFormByEntityId(it.entityId.toString())
//            entity.push(apires1)
//        }
//        def entityType = []
//        ccmMasterArrayList.each {
//            println(it.entityTypeId)
//            def apires2 = showFormByEntityTypeId(it.entityTypeId.toString())
//            entityType.push(apires2)
//        }

        def recordsTotal = ccmMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
//        jsonObject.put("entity", entity)
//        jsonObject.put("entityType", entityType)
        jsonObject.put("data", ccmMasterArrayList)
        return jsonObject
    }

    CcmRegister save(JSONObject jsonObject) {
        CcmRegister ccmRegister = new CcmRegister()
        ccmRegister.kitName = jsonObject.get("kitName")
        ccmRegister.kitNumber = jsonObject.get("kitNumber")
        ccmRegister.fridge = FridgeMaster.findById(Long.parseLong(jsonObject.get("fridgeId").toString()))
        ccmRegister.purchaseDate = sdf.parse(jsonObject.get("purchaseDate").toString())
        ccmRegister.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
        ccmRegister.status = Long.parseLong(jsonObject.get("status").toString())
        ccmRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        ccmRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        ccmRegister.save(flush: true)
        if (!ccmRegister.hasErrors())
            return ccmRegister
        else
            throw new BadRequestException()
    }

    CcmRegister update(JSONObject jsonObject, String id) {

        if (id) {
            CcmRegister ccmRegister = CcmRegister.findById(Long.parseLong(id))
            if (ccmRegister) {
                ccmRegister.isUpdatable = true
                ccmRegister.kitName = jsonObject.get("kitName")
                ccmRegister.kitNumber = jsonObject.get("kitNumber")
                ccmRegister.fridge = FridgeMaster.findById(Long.parseLong(jsonObject.get("fridgeId").toString()))
                ccmRegister.purchaseDate = sdf.parse(jsonObject.get("purchaseDate").toString())
                ccmRegister.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
                ccmRegister.status = Long.parseLong(jsonObject.get("status").toString())
                ccmRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                ccmRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                ccmRegister.save(flush: true)
                if (!ccmRegister.hasErrors())
                    return ccmRegister
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
            CcmRegister ccmRegister = CcmRegister.findById(Long.parseLong(id))
            if (ccmRegister) {
                ccmRegister.isUpdatable = true
                ccmRegister.delete()
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
//
//    def showFormByEntityTypeId(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.ENTITY_TYPE_SHOW+"/"+id
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
//    def showFormBycreatedUser(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.USER_REGISTER_SHOW+"/"+id
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
//            def url = Constants.API_GATEWAY+Constants.USER_REGISTER_SHOW+"/"+id
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
