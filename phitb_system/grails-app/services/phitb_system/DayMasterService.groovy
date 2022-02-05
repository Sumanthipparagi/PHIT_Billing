package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class DayMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return DayMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DayMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    DayMaster get(String id) {
        return DayMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(long limit, long offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return DayMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return DayMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
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
                orderColumn = "name"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = DayMaster.createCriteria()
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

    DayMaster save(JSONObject jsonObject) {
        String name = jsonObject.get("name")
        if (name) {
            DayMaster dayMaster = new DayMaster()
            dayMaster.name = name
            dayMaster.save(flush: true)
            if (!dayMaster.hasErrors())
                return dayMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    DayMaster update(JSONObject jsonObject, String id) {
        String name = jsonObject.get("name")
        if (name && id) {
            DayMaster dayMaster = DayMaster.findById(Long.parseLong(id))
            if (dayMaster) {
                dayMaster.isUpdatable = true
                dayMaster.name = name
                dayMaster.save(flush: true)
                if (!dayMaster.hasErrors())
                    return dayMaster
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
            DayMaster dayMaster = DayMaster.findById(Long.parseLong(id))
            if (dayMaster) {
                dayMaster.isUpdatable = true
                dayMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
