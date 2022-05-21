package phitb_product

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ProductCostRangeService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductCostRange.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductCostRange.findAllByPriceTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return ProductCostRange.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ProductCostRange.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    ProductCostRange get(String id) {
        return ProductCostRange.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
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
                orderColumn = "priceType"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productCostRangeCriteria = ProductCostRange.createCriteria()
        def productCostRangeArrayList = productCostRangeCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('priceType', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def entity = []
//        productCostRangeArrayList.each {
//            def apires1 = showProductCostByEntityId(it.entityId.toString())
//            entity.push(apires1)
//        }
//        def entityType = []
//        productCostRangeArrayList.each {
//            def apires2 = showProductCostByEntityTypeId(it.entityTypeId.toString())
//            entityType.push(apires2)
//        }

        def recordsTotal = productCostRangeArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productCostRangeArrayList)
//        jsonObject.put("entity", entity)
//        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    ProductCostRange save(JSONObject jsonObject) {
        ProductCostRange productCostRange = new ProductCostRange()
        productCostRange.priceType = jsonObject.get("priceType").toString()
        productCostRange.minimumRate = Double.parseDouble(jsonObject.get("minimumRate").toString())
        productCostRange.maximumRate =  Double.parseDouble(jsonObject.get("maximumRate").toString())
        productCostRange.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productCostRange.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productCostRange.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productCostRange.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productCostRange.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productCostRange.save(flush: true)
        if (!productCostRange.hasErrors())
            return productCostRange
        else
            throw new BadRequestException()
    }

    ProductCostRange update(JSONObject jsonObject, String id) {
        ProductCostRange productCostRange = ProductCostRange.findById(Long.parseLong(id))
        if (productCostRange) {
            productCostRange.isUpdatable = true
            productCostRange.priceType = jsonObject.get("priceType").toString()
            productCostRange.minimumRate = Double.parseDouble(jsonObject.get("minimumRate").toString())
            productCostRange.maximumRate =  Double.parseDouble(jsonObject.get("maximumRate").toString())
            productCostRange.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productCostRange.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productCostRange.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productCostRange.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productCostRange.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productCostRange.save(flush: true)
            if (!productCostRange.hasErrors())
                return productCostRange
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductCostRange productCostRange = ProductCostRange.findById(Long.parseLong(id))
            if (productCostRange) {
                productCostRange.isUpdatable = true
                productCostRange.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

//
//    def showProductCostByEntityId(String id)
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
//    def showProductCostByEntityTypeId(String id)
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
}
