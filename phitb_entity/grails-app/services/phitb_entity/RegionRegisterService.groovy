package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class RegionRegisterService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return RegionRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return RegionRegister.findAllByRegionName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    RegionRegister get(String id) {
        return RegionRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
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
        def regionRegisterCriteria = RegionRegister.createCriteria()
        def regionRegisterCriteriaArrayList = regionRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('regionName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = regionRegisterCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", regionRegisterCriteriaArrayList)
        return jsonObject
    }

    RegionRegister save(JSONObject jsonObject) {
        RegionRegister regionRegister = new RegionRegister()
        regionRegister.regionName = jsonObject.get("regionName").toString()
        regionRegister.shortName = jsonObject.get("shortName").toString()
        regionRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
        regionRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        regionRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        regionRegister.save(flush: true)
        if (!regionRegister.hasErrors())
            return regionRegister
        else
            throw new BadRequestException()
    }

    RegionRegister update(JSONObject jsonObject, String id) {
        RegionRegister regionRegister = RegionRegister.findById(Long.parseLong(id))
        if (regionRegister) {
            regionRegister.isUpdatable = true
            regionRegister.regionName = jsonObject.get("regionName").toString()
            regionRegister.shortName = jsonObject.get("shortName").toString()
            regionRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            regionRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            regionRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            regionRegister.save(flush: true)
            if (!regionRegister.hasErrors())
                return regionRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            RegionRegister regionRegister = RegionRegister.findById(Long.parseLong(id))
            if (regionRegister) {
                regionRegister.isUpdatable = true
                regionRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
