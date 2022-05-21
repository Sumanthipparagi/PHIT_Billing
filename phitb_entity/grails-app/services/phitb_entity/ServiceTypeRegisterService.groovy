package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ServiceTypeRegisterService {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return ServiceType.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ServiceType.findAllByServiceType("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return ServiceType.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ServiceType.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }
    ServiceType get(String id) {
        return ServiceType.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")


        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "seriesName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def serviceTypeCriteria = ServiceType.createCriteria()
        def serviceTypeArrayList = serviceTypeCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('serviceType', '%' + searchTerm + '%')
                }
            }
            entity {
                eq('id',entityId)
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = serviceTypeArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", serviceTypeArrayList)
        return jsonObject
    }

    ServiceType save(JSONObject jsonObject) {
        ServiceType serviceType = new ServiceType()
        serviceType.serviceType = jsonObject.get("serviceType").toString()
        serviceType.description = jsonObject.get("description").toString()
        serviceType.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        serviceType.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        serviceType.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        serviceType.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        serviceType.save(flush: true)
        if (!serviceType.hasErrors())
            return serviceType
        else
            throw new BadRequestException()
    }

    ServiceType update(JSONObject jsonObject, String id) {
        ServiceType serviceType = ServiceType.findById(Long.parseLong(id))
        if (serviceType) {
            serviceType.isUpdatable = true
            serviceType.serviceType = jsonObject.get("serviceType").toString()
            serviceType.description = jsonObject.get("description").toString()
            serviceType.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            serviceType.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            serviceType.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            serviceType.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            serviceType.save(flush: true)
            if (!serviceType.hasErrors())
                return serviceType
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ServiceType serviceType = ServiceType.findById(Long.parseLong(id))
            if (serviceType) {
                serviceType.isUpdatable = true
                serviceType.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
