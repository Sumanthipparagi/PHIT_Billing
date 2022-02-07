package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

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

    def getAllByEntity(String limit, String offset, long entityId)
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

    ProductTypeMaster get(String id) {
        return ProductTypeMaster.findById(Long.parseLong(id))
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
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = productTypeArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productTypeArrayList)
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
}
