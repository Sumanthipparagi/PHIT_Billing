package phitb_product

import com.sun.org.apache.xpath.internal.operations.Div
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class DivisionService
{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return Division.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return Division.findAllByDivisionNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return Division.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return Division.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    Division get(String id)
    {
        return Division.findById(Long.parseLong(id))
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
                orderColumn = "batchNumber"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def divisionCriteria = Division.createCriteria()
        def divisionArrayList = divisionCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('divisionName', '%' + searchTerm + '%')
                    ilike('divisionShortName', '%' + searchTerm + '%')
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

    Division save(JSONObject jsonObject)
    {
        Division division = new Division()
        division.divisionName = jsonObject.get("divisionName").toString()
        division.divisionShortName = jsonObject.get("divisionShortName").toString()
        division.zoneIds = jsonObject.get("zoneIds").toString()
        division.stateIds = jsonObject.get("stateIds").toString()
        division.cityIds = jsonObject.get("cityIds").toString()
        division.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        division.managerId = Long.parseLong(jsonObject.get("managerId").toString())
        division.customerIds = jsonObject.get("customerIds").toString()
        division.status = Long.parseLong(jsonObject.get("status").toString())
        division.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        division.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        division.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        division.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        division.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        division.save(flush: true)
        if (!division.hasErrors())
        {
            return division
        }
        else
        {
            throw new BadRequestException()
        }
    }

    Division update(JSONObject jsonObject, String id)
    {
        Division division = Division.findById(Long.parseLong(id))
        if (division)
        {
            division.isUpdatable = true
            division.divisionName = jsonObject.get("divisionName").toString()
            division.divisionShortName = jsonObject.get("divisionShortName").toString()
            division.zoneIds = jsonObject.get("zoneIds").toString()
            division.stateIds = jsonObject.get("stateIds").toString()
            division.cityIds = jsonObject.get("cityIds").toString()
            division.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            division.managerId = Long.parseLong(jsonObject.get("managerId").toString())
            division.customerIds = jsonObject.get("customerIds").toString()
            division.status = Long.parseLong(jsonObject.get("status").toString())
            division.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            division.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            division.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            division.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            division.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            division.save(flush: true)
            if (!division.hasErrors())
            {
                return division
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
            Division division = Division.findById(Long.parseLong(id))
            if (division)
            {
                division.isUpdatable = true
                division.delete()
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
