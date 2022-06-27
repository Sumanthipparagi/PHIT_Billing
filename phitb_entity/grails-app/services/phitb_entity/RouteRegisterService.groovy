package phitb_entity

import grails.gorm.transactions.Transactional
import org.apache.commons.lang.StringUtils
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class RouteRegisterService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return RouteRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return RouteRegister.findAllByRouteNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return RouteRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return RouteRegister.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }

    RouteRegister get(String id)
    {
        return RouteRegister.findById(Long.parseLong(id))
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
                orderColumn = "routeName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def routeRegisterCriteria = RouteRegister.createCriteria()
        def routeRegisterArrayList = routeRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('routeName', '%' + searchTerm + '%')
                }
            }
            entity {
                eq('id',entityId)
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = routeRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", routeRegisterArrayList)
        return jsonObject
    }

    RouteRegister save(JSONObject jsonObject)
    {
        RouteRegister routeRegister = new RouteRegister()
        routeRegister.routeName = jsonObject.get("routeName").toString()
        routeRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
        routeRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
        routeRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
        if(jsonObject.get("salesman")!= "0")
        {
            routeRegister.salesman = UserRegister.findById(Long.parseLong(jsonObject.get("salesman").toString()))
        }
        else
        {
            routeRegister.salesman = null
        }

        if(jsonObject.get("areaManager")!= "0")
        {
            routeRegister.areaManager = UserRegister.findById(Long.parseLong(jsonObject.get("areaManager").toString()))
        }
        else
        {
            routeRegister.areaManager = null
        }

        routeRegister.ccmEnabled = Long.parseLong(jsonObject.get("ccmEnabled").toString())
        routeRegister.daysOfWeek = StringUtils.join(jsonObject.get("daysOfWeek"), ",")
        if(jsonObject.get("salesman")!= "0")
        {
            routeRegister.salesman = UserRegister.findById(Long.parseLong(jsonObject.get("salesman").toString()))
        }
        else
        {
            routeRegister.salesman = null
        }

        routeRegister.ccmId = jsonObject.get("ccmId").toString()
        if(jsonObject.get("ccmId")!="0")
        {
            routeRegister.ccmId = jsonObject.get("ccmId").toString()
        }
        else
        {
            routeRegister.ccmId = 0
        }
        routeRegister.apprExpense = Double.parseDouble(jsonObject.get("apprExpense").toString())
        routeRegister.status = jsonObject.get("status").toString()
        routeRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        routeRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        routeRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        routeRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        routeRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        routeRegister.save(flush: true)
        if (!routeRegister.hasErrors())
        {
            return routeRegister
        }
        else
        {
            throw new BadRequestException()
        }
    }

    RouteRegister update(JSONObject jsonObject, String id)
    {
        RouteRegister routeRegister = RouteRegister.findById(Long.parseLong(id))
        if (routeRegister)
        {
            routeRegister.isUpdatable = true
            routeRegister.routeName = jsonObject.get("routeName").toString()
            routeRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
            routeRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
            routeRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            routeRegister.salesman = UserRegister.findById(Long.parseLong(jsonObject.get("salesman").toString()))
            routeRegister.areaManager = UserRegister.findById(Long.parseLong(jsonObject.get("areaManager").toString()))
            routeRegister.ccmEnabled = Long.parseLong(jsonObject.get("ccmEnabled").toString())
            routeRegister.daysOfWeek = jsonObject.get("daysOfWeek").toString()
            routeRegister.ccmId = jsonObject.get("ccmId").toString()
            routeRegister.apprExpense = Double.parseDouble(jsonObject.get("apprExpense").toString())
            routeRegister.status = jsonObject.get("status").toString()
            routeRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            routeRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            routeRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            routeRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            routeRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            routeRegister.save(flush: true)
            if (!routeRegister.hasErrors())
            {
                return routeRegister
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
            RouteRegister routeRegister = RouteRegister.findById(Long.parseLong(id))
            if (routeRegister)
            {
                routeRegister.isUpdatable = true
                routeRegister.delete()
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
