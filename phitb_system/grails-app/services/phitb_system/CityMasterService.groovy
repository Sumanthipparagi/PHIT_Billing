package phitb_system

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class CityMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CityMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CityMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    CityMaster get(String id) {
        return CityMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return CityMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CityMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
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

        def cityMasterCriteria = CityMaster.createCriteria()
        def cityMasterArrayList = cityMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                    stateMaster{
                        ilike('name', '%' + searchTerm + '%')
                    }
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def names = []
//        cityMasterArrayList.each {
//            println(it.entityId)
//            def apires = showCityByEntityId(it.entityId.toString())
//            names.push(apires)
//        }

        def recordsTotal = cityMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", cityMasterArrayList)
        return jsonObject
    }

    CityMaster save(JSONObject jsonObject) {
        CityMaster cityMaster = new CityMaster()
        cityMaster.name = jsonObject.get("name")
        cityMaster.state = StateMaster.findById(Long.parseLong(jsonObject.get("stateId").toString()))
        cityMaster.save(flush: true)
        if (!cityMaster.hasErrors())
            return cityMaster
        else
            throw new BadRequestException()
    }

    CityMaster update(JSONObject jsonObject, String id) {
        if (id) {
            CityMaster cityMaster = CityMaster.findById(Long.parseLong(id))
            if (cityMaster) {
                cityMaster.isUpdatable = true
                cityMaster.name = jsonObject.get("name")
                cityMaster.state = StateMaster.findById(Long.parseLong(jsonObject.get("stateId").toString()))
                cityMaster.save(flush: true)
                if (!cityMaster.hasErrors())
                    return cityMaster
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
            CityMaster cityMaster = CityMaster.findById(Long.parseLong(id))
            if (cityMaster) {
                cityMaster.isUpdatable = true
                cityMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

}
