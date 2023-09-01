package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class ZoneMasterService {

    /**
     * Gets all zones
     * @param query
     * @param offset
     * @param limit
     * @return list of zones
     */
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ZoneMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ZoneMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Get requested zone
     * @param id
     * @return get requested zone
     */
    ZoneMaster get(String id) {
        return ZoneMaster.findById(Long.parseLong(id))
    }

    /**
     * Get requested zone
     * @param id
     * @return get requested zone
     */
    def getAllByEntityId(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 1000
        if (!entityId)
            return ZoneMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ZoneMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }


    /**
     * Gets all zones in datatables format
     * @params jsonObject
     * @params start
     * @params length
     * @return list of zones
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
                orderColumn = "name"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = ZoneMaster.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
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

    /**
     * Save new zone
     * @param zone
     * @return saved zone
     */
    ZoneMaster save(JSONObject jsonObject) {
        String name = jsonObject.get("name")
        if (name) {
            ZoneMaster zoneMaster = new ZoneMaster()
            zoneMaster.name = name
            zoneMaster.entityId = Long.parseLong(jsonObject.get("entity").toString())
            zoneMaster.save(flush: true)
            if (!zoneMaster.hasErrors())
                return zoneMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }



    /**
     * Save new zone
     * @param zone
     * @return saved zone
     */
     ZoneMaster update(JSONObject jsonObject, String id) {
        String name = jsonObject.get("name")
        if (name && id) {
            ZoneMaster zoneMaster = ZoneMaster.findById(Long.parseLong(id))
            if (zoneMaster) {
                zoneMaster.isUpdatable = true
                zoneMaster.name = name
                zoneMaster.save(flush: true)
                if (!zoneMaster.hasErrors())
                    return zoneMaster
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    /**
     * Delete selected zone
     * @param id
     * @return returns status code 200
     */
    void delete(String id) {
        if (id) {
            ZoneMaster zoneMaster = ZoneMaster.findById(Long.parseLong(id))
            if (zoneMaster) {
                zoneMaster.isUpdatable = true
                zoneMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
