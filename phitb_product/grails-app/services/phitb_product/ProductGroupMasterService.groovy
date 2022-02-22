package phitb_product

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ProductGroupMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductGroupMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductGroupMaster.findAllByGroupNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(long limit, long offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return ProductGroupMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ProductGroupMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    ProductGroupMaster get(String id) {
        return ProductGroupMaster.findById(Long.parseLong(id))
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
                orderColumn = "groupName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productGroupMasterCriteria = ProductGroupMaster.createCriteria()
        def productGroupMasterArrayList = productGroupMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batchNumber', '%' + searchTerm + '%')
                    ilike('caseWt', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def entity = []
        productGroupMasterArrayList.each {
            println(it.entityId)
            def apires1 = showProductGroupByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        productGroupMasterArrayList.each {
            def apires2 = showProductGroupByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }

        def recordsTotal = productGroupMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productGroupMasterArrayList)
        jsonObject.put("entity", entity)
        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    ProductGroupMaster save(JSONObject jsonObject) {
        ProductGroupMaster productGroupMaster = new ProductGroupMaster()
        productGroupMaster.groupName = jsonObject.get("groupName").toString()
        productGroupMaster.groupDescription = jsonObject.get("groupDescription").toString()
        productGroupMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        productGroupMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productGroupMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productGroupMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productGroupMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productGroupMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productGroupMaster.save(flush: true)
        if (!productGroupMaster.hasErrors())
            return productGroupMaster
        else
            throw new BadRequestException()
    }

    ProductGroupMaster update(JSONObject jsonObject, String id) {
        ProductGroupMaster productGroupMaster = ProductGroupMaster.findById(Long.parseLong(id))
        if (productGroupMaster) {
            productGroupMaster.isUpdatable = true
            productGroupMaster.groupName = jsonObject.get("groupName").toString()
            productGroupMaster.groupDescription = jsonObject.get("groupDescription").toString()
            productGroupMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            productGroupMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productGroupMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productGroupMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productGroupMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productGroupMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productGroupMaster.save(flush: true)
            if (!productGroupMaster.hasErrors())
                return productGroupMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductGroupMaster productGroupMaster = ProductGroupMaster.findById(Long.parseLong(id))
            if (productGroupMaster) {
                productGroupMaster.isUpdatable = true
                productGroupMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def showProductGroupByEntityId(String id)
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

    def showProductGroupByEntityTypeId(String id)
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
