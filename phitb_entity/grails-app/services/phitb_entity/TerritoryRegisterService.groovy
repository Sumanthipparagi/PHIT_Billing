package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TerritoryRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return TerritoryRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TerritoryRegister.findAllByTerritoryName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return TerritoryRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return TerritoryRegister.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }

    TerritoryRegister get(String id) {
        return TerritoryRegister.findById(Long.parseLong(id))
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
                orderColumn = "territoryName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def territoryRegisterCriteria = TerritoryRegister.createCriteria()
        def territoryRegisterArrayList = territoryRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('territoryName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = territoryRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", territoryRegisterArrayList)
        return jsonObject
    }

    TerritoryRegister save(JSONObject jsonObject) {
        TerritoryRegister territoryRegister = new TerritoryRegister()
        territoryRegister.territoryName = jsonObject.get("territoryName").toString()
        territoryRegister.shortName = Long.parseLong(jsonObject.get("shortName").toString())
        territoryRegister.territoryHq = Long.parseLong(jsonObject.get("territoryHq").toString())
        territoryRegister.cityIds = Long.parseLong(jsonObject.get("cityIds").toString())
        territoryRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
        territoryRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
        territoryRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        territoryRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        territoryRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        territoryRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        territoryRegister.save(flush: true)
        if (!territoryRegister.hasErrors())
            return territoryRegister
        else
            throw new BadRequestException()
    }

    TerritoryRegister update(JSONObject jsonObject, String id) {
        TerritoryRegister territoryRegister = TerritoryRegister.findById(Long.parseLong(id))
        if (territoryRegister) {
            territoryRegister.isUpdatable = true
            territoryRegister.territoryName = jsonObject.get("territoryName").toString()
            territoryRegister.shortName = Long.parseLong(jsonObject.get("shortName").toString())
            territoryRegister.territoryHq = Long.parseLong(jsonObject.get("territoryHq").toString())
            territoryRegister.cityIds = Long.parseLong(jsonObject.get("cityIds").toString())
            territoryRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
            territoryRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            territoryRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            territoryRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            territoryRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            territoryRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            territoryRegister.save(flush: true)
            if (!territoryRegister.hasErrors())
                return territoryRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            TerritoryRegister territoryRegister = TerritoryRegister.findById(Long.parseLong(id))
            if (territoryRegister) {
                territoryRegister.isUpdatable = true
                territoryRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
