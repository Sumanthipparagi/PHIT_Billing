package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SeriesMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return SeriesMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return SeriesMaster.findAllBySeriesName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return SeriesMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SeriesMaster.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }
    SeriesMaster get(String id) {
        return SeriesMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "seriesName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def seriesMasterCriteria = SeriesMaster.createCriteria()
        def seriesMasterArrayList = seriesMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('seriesName', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = seriesMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", seriesMasterArrayList)
        return jsonObject
    }

    SeriesMaster save(JSONObject jsonObject) {
        SeriesMaster seriesMaster = new SeriesMaster()
        seriesMaster.seriesName = jsonObject.get("seriesName").toString()
        seriesMaster.seriesCode = jsonObject.get("seriesCode").toString()
        seriesMaster.mode = Long.parseLong(jsonObject.get("mode").toString())
        seriesMaster.saleId = Long.parseLong(jsonObject.get("saleId").toString())
        seriesMaster.saleReturnId = Long.parseLong(jsonObject.get("saleReturnId").toString())
        seriesMaster.saleOrderId = Long.parseLong(jsonObject.get("saleReturnId").toString())
        seriesMaster.purId = Long.parseLong(jsonObject.get("purId").toString())
        seriesMaster.purchaseOrderId = Long.parseLong(jsonObject.get("purId").toString())
        seriesMaster.purchaseReturnId = Long.parseLong(jsonObject.get("purId").toString())
        seriesMaster.status = Long.parseLong(jsonObject.get("status").toString())
        seriesMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        seriesMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        seriesMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        seriesMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        seriesMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        seriesMaster.save(flush: true)
        if (!seriesMaster.hasErrors())
            return seriesMaster
        else
            throw new BadRequestException()
    }

    SeriesMaster update(JSONObject jsonObject, String id) {
        SeriesMaster seriesMaster = SeriesMaster.findById(Long.parseLong(id))
        if (seriesMaster) {
            seriesMaster.isUpdatable = true
            seriesMaster.seriesName = jsonObject.get("seriesName").toString()
            seriesMaster.seriesCode = jsonObject.get("seriesCode").toString()
            seriesMaster.mode = Long.parseLong(jsonObject.get("mode").toString())
            seriesMaster.saleId = Long.parseLong(jsonObject.get("saleId").toString())
            seriesMaster.saleReturnId = Long.parseLong(jsonObject.get("saleReturnId").toString())
            seriesMaster.saleOrderId = Long.parseLong(jsonObject.get("saleReturnId").toString())
            seriesMaster.purId = Long.parseLong(jsonObject.get("purId").toString())
            seriesMaster.purchaseOrderId = Long.parseLong(jsonObject.get("purId").toString())
            seriesMaster.purchaseReturnId = Long.parseLong(jsonObject.get("purId").toString())
            seriesMaster.status = Long.parseLong(jsonObject.get("status").toString())
            seriesMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            seriesMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            seriesMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            seriesMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            seriesMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            seriesMaster.save(flush: true)
            if (!seriesMaster.hasErrors())
                return seriesMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            SeriesMaster seriesMaster = SeriesMaster.findById(Long.parseLong(id))
            if (seriesMaster) {
                seriesMaster.isUpdatable = true
                seriesMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
