package phitb_product

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

@Transactional
class ProductTypeMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductTypeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductTypeMaster.findAllByProductTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(long entityId)
    {
        return ProductTypeMaster.findAllByEntityId(entityId)
    }

    ProductTypeMaster get(String id) {
        return ProductTypeMaster.findById(Long.parseLong(id))
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
                orderColumn = "productType"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def  productTypeCriteria = ProductTypeMaster.createCriteria()
        def productTypeArrayList = productTypeCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('productType', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def entity = []
//        productTypeArrayList.each {
//            println(it.entityId)
//            def apires1 = showProductTypeByEntityId(it.entityId.toString())
//            entity.push(apires1)
//        }
//        def entityType = []
//        productTypeArrayList.each {
//            def apires2 = showProductTypeByEntityTypeId(it.entityTypeId.toString())
//            entityType.push(apires2)
//        }
        def recordsTotal = productTypeArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productTypeArrayList)
//        jsonObject.put("entity", entity)
//        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    ProductTypeMaster save(JSONObject jsonObject) {
        ProductTypeMaster productTypeMaster = new ProductTypeMaster()
        productTypeMaster.productType = jsonObject.get("productType").toString()
        productTypeMaster.productDescription = jsonObject.get("productDescription").toString()
        productTypeMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        productTypeMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productTypeMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productTypeMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productTypeMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productTypeMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productTypeMaster.save(flush: true)
        if (!productTypeMaster.hasErrors())
            return productTypeMaster
        else
            throw new BadRequestException()
    }

    ProductTypeMaster update(JSONObject jsonObject, String id) {
        ProductTypeMaster productTypeMaster = ProductTypeMaster.findById(Long.parseLong(id))
        if (productTypeMaster) {
            productTypeMaster.isUpdatable = true
            productTypeMaster.productType = jsonObject.get("productType").toString()
            productTypeMaster.productDescription = jsonObject.get("productDescription").toString()
            productTypeMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            productTypeMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productTypeMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productTypeMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productTypeMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productTypeMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productTypeMaster.save(flush: true)
            if (!productTypeMaster.hasErrors())
                return productTypeMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductTypeMaster productTypeMaster = ProductTypeMaster.findById(Long.parseLong(id))
            if (productTypeMaster) {
                productTypeMaster.isUpdatable = true
                productTypeMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

//
//    def showProductTypeByEntityId(String id)
//    {
//        Client client = ClientBuilder.newClient()
//        WebTarget target = client.target(new Constants().API_GATEWAY);
//        try
//        {
//            Response apiResponse = target
//                    .path(new Constants().ENTITY_REGISTER_SHOW + "/" +id)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .get()
//            if (apiResponse?.status == 200)
//            {
//                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
//                return jsonObject
//            }
//            else
//            {
//                return []
//            }
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service : CompositionMasterService , action :  showCompostionByEntityId  , Ex:' + ex)
//            log.error('Service :CompositionMasterService , action :  showCompostionByEntityId  , Ex:' + ex)
//        }
//
//    }
//
//
//
//    def showProductTypeByEntityTypeId(String id)
//    {
//        Client client = ClientBuilder.newClient()
//        WebTarget target = client.target(new Constants().API_GATEWAY);
//        try
//        {
//            Response apiResponse = target
//                    .path(new Constants().ENTITY_TYPE_SHOW + "/" +id)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .get()
//            if (apiResponse?.status == 200)
//            {
//                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
//                return jsonObject
//            }
//            else
//            {
//                return []
//            }
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service : CompositionMasterService , action :  showCompostionByEntityId  , Ex:' + ex)
//            log.error('Service :CompositionMasterService , action :  showCompostionByEntityId  , Ex:' + ex)
//        }
//
//    }
}
