package phitb_shipments

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_shipments.Exception.BadRequestException
import phitb_shipments.Exception.ResourceNotFoundException
import phitb_shipments.TransportType


@Transactional
class TransportTypeService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return TransportType.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TransportType.findAllByTransportTypeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    TransportType get(String id) {
        return TransportType.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        try
        {

            String searchTerm = paramsJsonObject.get("search[value]")
            String orderColumnId = paramsJsonObject.get("order[0][column]")
            String orderDir = paramsJsonObject.get("order[0][dir]")

            String orderColumn = "id"
            switch (orderColumnId)
            {
                case '0':
                    orderColumn = "id"
                    break;
                case '1':
                    orderColumn = "transportType"
                    break;
            }

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100

            def transportTypeCriteria = TransportType.createCriteria()
            def dayMasterArrayList = transportTypeCriteria.list(max: max, offset: offset) {
                or {
                    if (searchTerm != "")
                    {
                        ilike('transportType', '%' + searchTerm + '%')
                        ilike('vehicleId', '%' + searchTerm + '%')
                    }
                }
                eq('deleted', false)
                order(orderColumn, orderDir)
            }

            def recordsTotal = dayMasterArrayList.totalCount
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("draw", paramsJsonObject.draw)
            jsonObject.put("recordsTotal", recordsTotal)
            jsonObject.put("recordsFiltered", recordsTotal)
            jsonObject.put("data", dayMasterArrayList)
            return jsonObject
        }
        catch(Exception ex)
        {
            System.out.println(ex)
        }
    }

    TransportType save(JSONObject jsonObject) {
        TransportType transportType = new TransportType()
        transportType.transportType = jsonObject.get("transportType")
        transportType.vehicleId = Long.parseLong(jsonObject.get("vehicleId").toString())
        transportType.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        transportType.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        transportType.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        transportType.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        transportType.save(flush: true)
        if (!transportType.hasErrors())
            return transportType
        else
            throw new BadRequestException()
    }

    TransportType update(JSONObject jsonObject, String id) {

        if (id) {
            TransportType transportType = TransportType.findById(Long.parseLong(id))
            if (transportType) {
                transportType.isUpdatable = true
                transportType.transportType = jsonObject.get("transportType")
                transportType.vehicleId = Long.parseLong(jsonObject.get("vehicleId").toString())
                transportType.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                transportType.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                transportType.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                transportType.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                transportType.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                transportType.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                transportType.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                transportType.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                transportType.save(flush: true)
                if (!transportType.hasErrors())
                    return transportType
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
            TransportType transportType = TransportType.findById(Long.parseLong(id))
            if (transportType) {
                transportType.isUpdatable = true
                transportType.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
