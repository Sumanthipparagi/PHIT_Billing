package phitb_system

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException
import grails.rest.*


@Transactional
class StateMasterService
{

    /**
     * Gets all states
     * @param query
     * @param offset
     * @param limit
     * @return list of states
     */
    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {

            return StateMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return StateMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    /**
     * Gets  state by selected Id
     * @param  id
     * @return state by Id
     */
    StateMaster get(String id)
    {
        return StateMaster.findById(Long.parseLong(id))
    }


    /**
     * Get entity by selected Id
     * @param  id
     * @return state by Id
     */
    def getAllByEntityId(String limit, String offset, long id) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!id)
            return StateMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return StateMaster.findAllById(id,[sort: 'id', max: l, offset: o, order: 'desc'])
    }


    /**
     * Gets all states in datatables format
     * @return list of states
     */
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
                orderColumn = "name"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 10000
        def stateMasterCriteria = StateMaster.createCriteria()
        def stateMasterArrayList = stateMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('name', '%' + searchTerm + '%')
//                    zone {
//                        ilike('name', '%' + searchTerm + '%')
//                    }
//                    country {
//                        ilike('name', '%' + searchTerm + '%')
//                    }
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
//        def names = []
//        stateMasterArrayList.each {
//            println(it.entityId)
//            def apires = showStateByEntityId(it.entityId.toString())
//            names.push(apires)
//        }

        def recordsTotal = stateMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", stateMasterArrayList)
        return jsonObject
    }

    /**
     * Save new state
     * @param state
     * @return saved state
     */
    StateMaster save(JSONObject jsonObject)
    {
        StateMaster stateMaster = new StateMaster()
        stateMaster.name = jsonObject.get("name").toString()
        stateMaster.gstStateCode = jsonObject.get("gstStateCode").toString()
        stateMaster.alphaCode = jsonObject.get("alphaCode").toString()
        stateMaster.save(flush: true)
        if (!stateMaster.hasErrors())
        {
            return stateMaster
        }
        else
        {
            throw new BadRequestException()
        }
    }

    /**
     * Update  new state
     * @param state
     * @return updated state
     */
    StateMaster update(JSONObject jsonObject, String id)
    {
        if (id)
        {
            StateMaster stateMaster = StateMaster.findById(Long.parseLong(id))
            if (stateMaster)
            {
                stateMaster.isUpdatable = true
                stateMaster.name = jsonObject.get("name").toString()
                stateMaster.gstStateCode = jsonObject.get("gstStateCode").toString()
                stateMaster.alphaCode = jsonObject.get("alphaCode").toString()
                stateMaster.save(flush: true)
                if (!stateMaster.hasErrors())
                {
                    return stateMaster
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
        else
        {
            throw new BadRequestException()
        }
    }

    /**
     * Delete selected state
     * @param id
     * @return returns status code 200
     */
    void delete(String id)
    {
        if (id)
        {
            StateMaster stateMaster = StateMaster.findById(Long.parseLong(id))
            if (stateMaster)
            {
                stateMaster.isUpdatable = true
                stateMaster.delete()
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


//    def showStateByEntityId(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.ENTITY_REGISTER_SHOW+"/"+id
//            URL apiUrl = new URL(url)
//            def entity = new JsonSlurper().parseText(apiUrl.text)
//            return entity
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
//            log.error('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
//        }
//    }
}
