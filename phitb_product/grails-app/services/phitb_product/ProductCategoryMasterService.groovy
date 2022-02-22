package phitb_product

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ProductCategoryMasterService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductCategoryMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductCategoryMaster.findAllByCategoryNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o,
                                                                                    order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return ProductCategoryMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ProductCategoryMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    ProductCategoryMaster get(String id) {
        return ProductCategoryMaster.findById(Long.parseLong(id))
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
                orderColumn = "categoryName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productCategoryMasterCriteria = ProductCategoryMaster.createCriteria()
        def productCategoryMasterArrayList = productCategoryMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('categoryName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def entity = []
        productCategoryMasterArrayList.each {
            def apires1 = showProductCatByEntityId(it.entityId.toString())
            entity.push(apires1)
        }
        def entityType = []
        productCategoryMasterArrayList.each {
            def apires2 = showProductCatByEntityTypeId(it.entityTypeId.toString())
            entityType.push(apires2)
        }

        def recordsTotal = productCategoryMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("entity", entity)
        jsonObject.put("entityType", entityType)
        jsonObject.put("data", productCategoryMasterArrayList)
        return jsonObject
    }

    ProductCategoryMaster save(JSONObject jsonObject) {
        ProductCategoryMaster productCategoryMaster = new ProductCategoryMaster()
        productCategoryMaster.categoryName = jsonObject.get("categoryName").toString()
        productCategoryMaster.restrictedCategory = jsonObject.get("restrictedCategory").toString()
        productCategoryMaster.accessRestriction = jsonObject.get("accessRestriction").toString()
        productCategoryMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        productCategoryMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productCategoryMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productCategoryMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productCategoryMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productCategoryMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productCategoryMaster.save(flush: true)
        if (!productCategoryMaster.hasErrors())
            return productCategoryMaster
        else
            throw new BadRequestException()
    }

    ProductCategoryMaster update(JSONObject jsonObject, String id) {
        ProductCategoryMaster productCategoryMaster = ProductCategoryMaster.findById(Long.parseLong(id))
        if (productCategoryMaster) {
            productCategoryMaster.isUpdatable = true
            productCategoryMaster.categoryName = jsonObject.get("categoryName").toString()
            productCategoryMaster.restrictedCategory = jsonObject.get("restrictedCategory").toString()
            productCategoryMaster.accessRestriction = jsonObject.get("accessRestriction").toString()
            productCategoryMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            productCategoryMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productCategoryMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productCategoryMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productCategoryMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productCategoryMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productCategoryMaster.save(flush: true)
            if (!productCategoryMaster.hasErrors())
                return productCategoryMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductCategoryMaster productCategoryMaster = ProductCategoryMaster.findById(Long.parseLong(id))
            if (productCategoryMaster) {
                productCategoryMaster.isUpdatable = true
                productCategoryMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


    def showProductCatByEntityId(String id)
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

    def showProductCatByEntityTypeId(String id)
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
