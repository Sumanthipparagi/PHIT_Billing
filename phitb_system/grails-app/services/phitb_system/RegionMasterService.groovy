package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class RegionMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 10000

        if (!query)
            return RegionMaster.findAll([sort: 'regionName', max: l, offset: o, order: 'desc'])
        else
            return RegionMaster.findAllByRegionName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    RegionMaster get(String id) {
        return RegionMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long id) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!id)
            return RegionMaster.findAll([sort: 'id', max: l, offset: o, order: 'asc'])
        else
            return RegionMaster.findAllById(id,[sort: 'id', max: l, offset: o, order: 'desc'])
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
                orderColumn = "regionName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def regionCriteria = RegionMaster.createCriteria()
        def regionArrayList = regionCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def names = []
//        RegionArrayList.each {
//            println(it.entityId)
//            def apires = showCityByEntityId(it.entityId.toString())
//            names.push(apires)
//        }

        def recordsTotal = regionArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", regionArrayList)
        return jsonObject
    }

    RegionMaster save(JSONObject jsonObject) {
        RegionMaster region = new RegionMaster()
        region.regionName = jsonObject.get("regionName").toString()
        region.regionCode = jsonObject.get("regionCode").toString()
        region.save(flush: true)
        if (!region.hasErrors())
            return region
        else
            throw new BadRequestException()
    }

    RegionMaster update(JSONObject jsonObject, String id) {
        if (id) {
            RegionMaster region = RegionMaster.findById(Long.parseLong(id))
            if (region) {
                region.isUpdatable = true
                region.regionName = jsonObject.get("regionName").toString()
                region.regionCode = jsonObject.get("regionCode").toString()
                region.save(flush: true)
                if (!region.hasErrors())
                    return region
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
            RegionMaster region = RegionMaster.findById(Long.parseLong(id))
            if (region) {
                region.isUpdatable = true
                region.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

}
