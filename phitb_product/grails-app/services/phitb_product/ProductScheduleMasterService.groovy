package phitb_product

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class ProductScheduleMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductScheduleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductScheduleMaster.findAllByScheduleCodeIlike("%" + query + "%", [sort: 'id', max: l, offset: o,
                                                                                    order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return ProductScheduleMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ProductScheduleMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    ProductScheduleMaster get(String id) {
        return ProductScheduleMaster.findById(Long.parseLong(id))
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
                orderColumn = "scheduleCode"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productScheduleMasterCriteria = ProductScheduleMaster.createCriteria()
        def productScheduleMasterArrayList = productScheduleMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('scheduleCode', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def entity = []
        productScheduleMasterArrayList.each {
            println(it.entityId)
            def apires1 = showProductScheduleByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        productScheduleMasterArrayList.each {
            def apires2 = showProductScheduleByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }
        def recordsTotal = productScheduleMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productScheduleMasterArrayList)
        jsonObject.put("entity", entity)
        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    ProductScheduleMaster save(JSONObject jsonObject) {
       ProductScheduleMaster productScheduleMaster = new ProductScheduleMaster()
        productScheduleMaster.scheduleCode = jsonObject.get("scheduleCode").toString()
        productScheduleMaster.scheduleDescription = jsonObject.get("scheduleDescription").toString()
        productScheduleMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        productScheduleMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productScheduleMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productScheduleMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productScheduleMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productScheduleMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productScheduleMaster.save(flush: true)
        if (!productScheduleMaster.hasErrors())
            return productScheduleMaster
        else
            throw new BadRequestException()
    }

    ProductScheduleMaster update(JSONObject jsonObject, String id) {
        ProductScheduleMaster productScheduleMaster = ProductScheduleMaster.findById(Long.parseLong(id))
        if (productScheduleMaster) {
            productScheduleMaster.isUpdatable = true
            productScheduleMaster.scheduleCode = jsonObject.get("scheduleCode").toString()
            productScheduleMaster.scheduleDescription = jsonObject.get("scheduleDescription").toString()
            productScheduleMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            productScheduleMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productScheduleMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productScheduleMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productScheduleMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productScheduleMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productScheduleMaster.save(flush: true)
            if (!productScheduleMaster.hasErrors())
                return productScheduleMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductScheduleMaster productScheduleMaster = ProductScheduleMaster.findById(Long.parseLong(id))
            if (productScheduleMaster) {
                productScheduleMaster.isUpdatable = true
                productScheduleMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


    def showProductScheduleByEntityId(String id)
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

    def showProductScheduleByEntityTypeId(String id)
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
}
