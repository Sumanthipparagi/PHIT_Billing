package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DayEndMasterService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return DayEndMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return DayEndMaster.findAllByEndTimeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return DayEndMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return DayEndMaster.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    DayEndMaster get(String id)
    {
        return DayEndMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "customerGroupName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def dayEndMasterCriteria = DayEndMaster.createCriteria()
        def dayEndMasterArrayList = dayEndMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('endTime', '%' + searchTerm + '%')
                }
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

    DayEndMaster save(JSONObject jsonObject)
    {
        DayEndMaster dayEndMaster = new DayEndMaster()
        dayEndMaster.date = jsonObject.get("date").toString()
        dayEndMaster.status = Integer.parseInt(jsonObject.get("status").toString())
        dayEndMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        dayEndMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        dayEndMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        dayEndMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        dayEndMaster.save(flush: true)
        if (!dayEndMaster.hasErrors())
        {
            return dayEndMaster
        }
        else
        {
            throw new BadRequestException()
        }
    }

    DayEndMaster update(JSONObject jsonObject, String id)
    {
        DayEndMaster dayEndMaster = DayEndMaster.findById(Long.parseLong(id))
        if (dayEndMaster)
        {
            dayEndMaster.isUpdatable = true
            dayEndMaster.date = jsonObject.get("date").toString()
            dayEndMaster.status = Integer.parseInt(jsonObject.get("status").toString())
            dayEndMaster.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            dayEndMaster.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            dayEndMaster.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            dayEndMaster.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            dayEndMaster.save(flush: true)
            if (!dayEndMaster.hasErrors())
            {
                return dayEndMaster
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
            DayEndMaster dayEndMaster = DayEndMaster.findById(Long.parseLong(id))
            if (dayEndMaster)
            {
                dayEndMaster.isUpdatable = true
                dayEndMaster.delete()
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
}
