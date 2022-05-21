package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CompositionMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return CompositionMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CompositionMaster.findAllByCompositionNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o,
                                                                                    order:
                    'desc'])
    }

    CompositionMaster get(String id) {
        return CompositionMaster.findById(Long.parseLong(id))
    }

    ArrayList<CompositionMaster> getByEntity(String id) {
        return CompositionMaster.findAllByEntityId(Long.parseLong(id))
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
                orderColumn = "compositionName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def compositionMasterCriteria = CompositionMaster.createCriteria()
        def compositionMasterArrayList = compositionMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('compositionName', '%' + searchTerm + '%')

                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def entity = []
//        compositionMasterArrayList.each {
//            def apires1 = showCompostionByEntityId(it.entityId.toString())
//            entity.push(apires1)
//        }
//        def entityType = []
//        compositionMasterArrayList.each {
//            def apires2 = showCompostionByEntityTypeId(it.entityTypeId.toString())
//            entityType.push(apires2)
//        }
        def recordsTotal = compositionMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", compositionMasterArrayList)
//        jsonObject.put("entity", entity)
//        jsonObject.put("entityType", entityType)
        return jsonObject
    }

    CompositionMaster save(JSONObject jsonObject) {
        CompositionMaster compositionMaster = new CompositionMaster()
        compositionMaster.compositionName = jsonObject.get("compositionName").toString()
        compositionMaster.status =  Long.parseLong(jsonObject.get("status").toString())
        compositionMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        compositionMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        compositionMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        compositionMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        compositionMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        compositionMaster.save(flush: true)
        if (!compositionMaster.hasErrors())
            return compositionMaster
        else
            throw new BadRequestException()
    }

    CompositionMaster update(JSONObject jsonObject, String id) {
        CompositionMaster compositionMaster = CompositionMaster.findById(Long.parseLong(id))
        if (compositionMaster) {
            compositionMaster.isUpdatable = true
            compositionMaster.compositionName = jsonObject.get("compositionName").toString()
            compositionMaster.status =  Long.parseLong(jsonObject.get("status").toString())
            compositionMaster.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            compositionMaster.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            compositionMaster.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            compositionMaster.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            compositionMaster.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            compositionMaster.save(flush: true)
            if (!compositionMaster.hasErrors())
                return compositionMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            CompositionMaster compositionMaster = CompositionMaster.findById(Long.parseLong(id))
            if (compositionMaster) {
                compositionMaster.isUpdatable = true
                compositionMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


//    def showCompostionByEntityId(String id)
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
//                return null
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
//    def showCompostionByEntityTypeId(String id)
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
//                return null
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
