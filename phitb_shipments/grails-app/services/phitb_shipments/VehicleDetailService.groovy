package phitb_shipments

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_shipments.Exception.BadRequestException
import phitb_shipments.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat


@Transactional
class VehicleDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return VehicleDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return VehicleDetail.findAllByVehicleNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    VehicleDetail get(String id) {
        return VehicleDetail.findById(Long.parseLong(id))
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
                    orderColumn = "vehicleName"
                    break;
            }

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100

            def dayMasterCriteria = VehicleDetail.createCriteria()
            def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
                or {
                    if (searchTerm != "")
                    {
                        ilike('vehicleName', '%' + searchTerm + '%')
                        ilike('gpsKitId', '%' + searchTerm + '%')
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

    VehicleDetail save(JSONObject jsonObject) {
        VehicleDetail vehicleDetail = new VehicleDetail()
        vehicleDetail.vehicleName = jsonObject.get("vehicleName")
        vehicleDetail.gpsKitId = jsonObject.get("gpsKitId")
        vehicleDetail.managerId = Long.parseLong(jsonObject.get("managerId").toString())
        vehicleDetail.vehiclePurcDate = sdf.parse(jsonObject.get("vehiclePurcDate").toString())
        vehicleDetail.vehicleRegNo = jsonObject.get("vehicleRegNo").toString()
        vehicleDetail.transportType = TransportType.findById(Long.parseLong(jsonObject.get("transportType").toString()))
        vehicleDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        vehicleDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        vehicleDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        vehicleDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        vehicleDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        vehicleDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        vehicleDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        vehicleDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        vehicleDetail.save(flush: true)
        if (!vehicleDetail.hasErrors())
            return vehicleDetail
        else
            throw new BadRequestException()
    }

    VehicleDetail update(JSONObject jsonObject, String id) {

        if (id) {
            VehicleDetail vehicleDetail = VehicleDetail.findById(Long.parseLong(id))
            if (vehicleDetail) {
                vehicleDetail.isUpdatable = true
                vehicleDetail.vehicleName = jsonObject.get("vehicleName")
                vehicleDetail.gpsKitId = jsonObject.get("gpsKitId")
                vehicleDetail.managerId = Long.parseLong(jsonObject.get("managerId").toString())
                vehicleDetail.vehiclePurcDate = sdf.parse(jsonObject.get("vehiclePurcDate").toString())
                vehicleDetail.vehicleRegNo = jsonObject.get("vehicleRegNo").toString()
                vehicleDetail.transportType = TransportType.findById(Long.parseLong(jsonObject.get("transportType").toString()))
                vehicleDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                vehicleDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                vehicleDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                vehicleDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                vehicleDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                vehicleDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                vehicleDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                vehicleDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                vehicleDetail.save(flush: true)
                if (!vehicleDetail.hasErrors())
                    return vehicleDetail
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
            VehicleDetail vehicleDetail = VehicleDetail.findById(Long.parseLong(id))
            if (vehicleDetail) {
                vehicleDetail.isUpdatable = true
                vehicleDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
