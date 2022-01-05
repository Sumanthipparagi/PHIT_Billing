package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class GenderMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return GenderMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return GenderMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    GenderMaster get(String id) {
        return GenderMaster.findById(Long.parseLong(id))
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

        def dayMasterCriteria = GenderMaster.createCriteria()
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

    GenderMaster save(JSONObject jsonObject) {
        String name = jsonObject.get("name")
        if (name) {
            GenderMaster genderMaster = new GenderMaster()
            genderMaster.name = name
            genderMaster.save(flush: true)
            if (!genderMaster.hasErrors())
                return genderMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    GenderMaster update(JSONObject jsonObject, String id) {
        String name = jsonObject.get("name")
        if (name && id) {
            GenderMaster genderMaster = GenderMaster.findById(Long.parseLong(id))
            if (genderMaster) {
                genderMaster.isUpdatable = true
                genderMaster.name = name
                genderMaster.save(flush: true)
                if (!genderMaster.hasErrors())
                    return genderMaster
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
            GenderMaster genderMaster = GenderMaster.findById(Long.parseLong(id))
            if (genderMaster) {
                genderMaster.isUpdatable = true
                genderMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
