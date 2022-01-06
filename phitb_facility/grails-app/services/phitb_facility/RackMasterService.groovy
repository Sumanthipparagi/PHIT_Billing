package phitb_facility

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_facility.Exception.BadRequestException
import phitb_facility.Exception.ResourceNotFoundException

@Transactional
class RackMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return RackMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RackMaster.findAllByRackNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    RackMaster get(String id) {
        return RackMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "rackName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = RackMaster.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('rackName', '%' + searchTerm + '%')
                    ilike('rackCodeName', '%' + searchTerm + '%')
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

    RackMaster save(JSONObject jsonObject) {
        RackMaster rackMaster = new RackMaster()
        rackMaster.rackName = jsonObject.get("rackName")
        rackMaster.floorNumber = jsonObject.get("floorNumber")
        rackMaster.generalInfo = jsonObject.get("generalInfo")
        rackMaster.rackCodeName = jsonObject.get("rackCodeName")
        rackMaster.companies = jsonObject.get("companies")
        rackMaster.status = Long.parseLong(jsonObject.get("status").toString())
        rackMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        rackMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        rackMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        rackMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        rackMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        rackMaster.cccEnabled = Long.parseLong(jsonObject.get("cccEnabled").toString())
        rackMaster.save(flush: true)
        if (!rackMaster.hasErrors())
            return rackMaster
        else
            throw new BadRequestException()
    }

    RackMaster update(JSONObject jsonObject, String id) {
        if (id) {
            RackMaster rackMaster = RackMaster.findById(Long.parseLong(id))
            if (rackMaster) {
                rackMaster.isUpdatable = true
                rackMaster.rackName = jsonObject.get("rackName")
                rackMaster.floorNumber = jsonObject.get("floorNumber")
                rackMaster.generalInfo = jsonObject.get("generalInfo")
                rackMaster.rackCodeName = jsonObject.get("rackCodeName")
                rackMaster.companies = jsonObject.get("companies")
                rackMaster.status = Long.parseLong(jsonObject.get("status").toString())
                rackMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                rackMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                rackMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                rackMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                rackMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                rackMaster.cccEnabled = Long.parseLong(jsonObject.get("cccEnabled").toString())
                rackMaster.save(flush: true)
                if (!rackMaster.hasErrors())
                    return rackMaster
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
            RackMaster rackMaster = RackMaster.findById(Long.parseLong(id))
            if (rackMaster) {
                rackMaster.isUpdatable = true
                rackMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
