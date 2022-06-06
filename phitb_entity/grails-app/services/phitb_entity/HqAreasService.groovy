package phitb_entity

import grails.gorm.transactions.Transactional
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class HqAreasService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return HqArea.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return HqArea.findAllByHqName("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return HqArea.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return HqArea.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    HqArea get(String id)
    {
        return HqArea.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")


        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "hqName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def dayEndMasterCriteria = HqArea.createCriteria()
        def dayEndMasterArrayList = dayEndMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('hqName', '%' + searchTerm + '%')
                }
            }
            entity {
                eq('id',entityId)
            }

            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = dayEndMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", dayEndMasterArrayList)
        return jsonObject
    }

    HqArea save(JSONObject jsonObject)
    {
        HqArea hqArea = new HqArea()
        hqArea.hqName =  jsonObject.get("hqName").toString()
        hqArea.cityId =   StringUtils.join(jsonObject.get("cityIds"), ",")
        hqArea.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        hqArea.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        hqArea.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        hqArea.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        hqArea.save(flush: true)
        if (!hqArea.hasErrors())
        {
            return hqArea
        }
        else
        {
            throw new BadRequestException()
        }
    }

    HqArea update(JSONObject jsonObject, String id)
    {
        HqArea hqArea = HqArea.findById(Long.parseLong(id))
        if (hqArea)
        {
            hqArea.isUpdatable = true
            hqArea.hqName =  jsonObject.get("hqName").toString()
            hqArea.cityId =   StringUtils.join(jsonObject.get("cityIds"), ",")
            hqArea.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            hqArea.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            hqArea.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            hqArea.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            hqArea.save(flush: true)
            if (!hqArea.hasErrors())
            {
                return hqArea
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            HqArea hqArea = HqArea.findById(Long.parseLong(id))
            if (hqArea)
            {
                hqArea.isUpdatable = true
                hqArea.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getByEntity(String entityId) {
        if(entityId) {
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(entityId))
            if (entityRegister)
                return HqArea.findAllByEntity(entityRegister)
            else
                throw new ResourceNotFoundException()
        }
        else
            throw new BadRequestException()
    }

}
