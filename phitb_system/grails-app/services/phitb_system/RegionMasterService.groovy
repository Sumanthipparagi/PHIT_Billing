package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class RegionMasterService {

    /**
     * Gets all region master
     * @param query
     * @param offset
     * @param limit
     * @return list of region master
     */
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 10000

        if (!query)
            return RegionMaster.findAll([sort: 'regionName', max: l, offset: o, order: 'desc'])
        else
            return RegionMaster.findAllByRegionName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Get requested region master
     * @param id
     * @return get requested region master
     */
    RegionMaster get(String id) {
        return RegionMaster.findById(Long.parseLong(id))
    }

    /**
     * Get requested region master by entityId
     * @param entityId
     * @return get requested region master
     */
    def getAllByEntityId(String limit, String offset, long id) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!id)
            return RegionMaster.findAll([sort: 'id', max: l, offset: o, order: 'asc'])
        else
            return RegionMaster.findAllById(id,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Gets all region master in datatables format
     * @return list of region master
     */
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


    /**
     * Save new region master
     * @param region master
     * @return saved region master
     */
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

    /**
     * Update new region master
     * @param region master
     * @return saved region master
     */
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

    /**
     * Delete selected region master
     * @param id
     * @return returns status code 200
     */
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
