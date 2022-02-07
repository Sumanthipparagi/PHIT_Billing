package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException

import java.text.SimpleDateFormat

@Transactional
class ProductClassService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ProductClass.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductClass.findAllByProductClassNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return ProductClass.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ProductClass.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    ProductClass get(String id) {
        return ProductClass.findById(Long.parseLong(id))
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
                orderColumn = "productClassName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productClassCriteria = ProductClass.createCriteria()
        def productClassArrayList = productClassCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('productClassName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = productClassArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productClassArrayList)
        return jsonObject
    }

    ProductClass save(JSONObject jsonObject) {
        ProductClass productClass = new ProductClass()
        productClass.productClassName = jsonObject.get("productClassName").toString()
        productClass.shortName = jsonObject.get("shortName").toString()
        productClass.status =  Long.parseLong(jsonObject.get("status").toString())
        productClass.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productClass.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productClass.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productClass.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productClass.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productClass.save(flush: true)
        if (!productClass.hasErrors())
            return productClass
        else
            throw new BadRequestException()
    }

    ProductClass update(JSONObject jsonObject, String id) {
        ProductClass productClass = ProductClass.findById(Long.parseLong(id))
        if (productClass) {
            productClass.isUpdatable = true
            productClass.productClassName = jsonObject.get("productClassName").toString()
            productClass.shortName = jsonObject.get("shortName").toString()
            productClass.status =  Long.parseLong(jsonObject.get("status").toString())
            productClass.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productClass.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productClass.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productClass.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productClass.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productClass.save(flush: true)
            if (!productClass.hasErrors())
                return productClass
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductClass productClass = ProductClass.findById(Long.parseLong(id))
            if (productClass) {
                productClass.isUpdatable = true
                productClass.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

}
