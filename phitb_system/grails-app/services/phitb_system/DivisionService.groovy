package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class DivisionService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 10000

        if (!query)
            return DivisionMaster.findAll([sort: 'divisionName', max: l, offset: o, order: 'desc'])
        else
            return DivisionMaster.findAllByDivisionName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    DivisionMaster get(String id) {
        return DivisionMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long id) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!id)
            return DivisionMaster.findAll([sort: 'id', max: l, offset: o, order: 'asc'])
        else
            return DivisionMaster.findAllById(id,[sort: 'id', max: l, offset: o, order: 'desc'])
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
                orderColumn = "divisionName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def divisionCriteria = DivisionMaster.createCriteria()
        def divisionArrayList = divisionCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('divisionName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }


        def recordsTotal = divisionArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", divisionArrayList)
        return jsonObject
    }

    DivisionMaster save(JSONObject jsonObject) {
        DivisionMaster division = new DivisionMaster()
        division.divisionName = jsonObject.get("divisionName").toString()
        division.regionCode = jsonObject.get("regionCode").toString()
        division.divisionCode = jsonObject.get("divisionCode").toString()
        division.save(flush: true)
        if (!division.hasErrors())
            return division
        else
            throw new BadRequestException()
    }

    DivisionMaster update(JSONObject jsonObject, String id) {
        if (id) {
            DivisionMaster division = DivisionMaster.findById(Long.parseLong(id))
            if (division) {
                division.isUpdatable = true
                division.divisionName = jsonObject.get("divisionName").toString()
                division.regionCode = jsonObject.get("regionCode").toString()
                division.divisionCode = jsonObject.get("divisionCode").toString()
                division.save(flush: true)
                if (!division.hasErrors())
                    return division
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
            DivisionMaster division = DivisionMaster.findById(Long.parseLong(id))
            if (division) {
                division.isUpdatable = true
                division.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
