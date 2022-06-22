package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class DistrictMasterService
{

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 10000

        if (!query)
            return DistrictMaster.findAll([sort: 'district', max: l, offset: o, order: 'desc'])
        else
            return DistrictMaster.findAllByDistrict("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    DistrictMaster get(String id) {
        return DistrictMaster.findById(Long.parseLong(id))
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
                orderColumn = "districtName"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def districtCriteria = DistrictMaster.createCriteria()
        def districtArrayList = districtCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('district', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }


        def recordsTotal = districtArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", districtArrayList)
        return jsonObject
    }

    DistrictMaster save(JSONObject jsonObject) {
        DistrictMaster districtMaster = new DistrictMaster()
        districtMaster.district = jsonObject.get("district").toString()
        districtMaster.districtCode = jsonObject.get("districtCode").toString()
        districtMaster.state = StateMaster.findById(Long.parseLong(jsonObject.get("state").toString()))
        districtMaster.save(flush: true)
        if (!districtMaster.hasErrors())
            return districtMaster
        else
            throw new BadRequestException()
    }

    DistrictMaster update(JSONObject jsonObject, String id) {
        if (id) {
            DistrictMaster districtMaster = DistrictMaster.findById(Long.parseLong(id))
            if (districtMaster) {
                districtMaster.isUpdatable = true
                districtMaster.district = jsonObject.get("district").toString()
                districtMaster.districtCode = jsonObject.get("districtCode").toString()
                districtMaster.state = StateMaster.findById(Long.parseLong(jsonObject.get("state").toString()))
                districtMaster.save(flush: true)
                if (!districtMaster.hasErrors())
                    return districtMaster
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
            DistrictMaster districtMaster = DistrictMaster.findById(Long.parseLong(id))
            if (districtMaster) {
                districtMaster.isUpdatable = true
                districtMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
