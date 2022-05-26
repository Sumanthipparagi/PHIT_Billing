package phitb_system

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class AccountModeMasterService
{

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return AccountModeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return AccountModeMaster.findAllByModeIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }


    def getAllByEntityId(String limit, String offset, long entityId)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return AccountModeMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return AccountModeMaster.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    AccountModeMaster get(String id)
    {
        return AccountModeMaster.findById(Long.parseLong(id))
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
                orderColumn = "mode"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def accountModesMasterCriteria = AccountModeMaster.createCriteria()
        def accountModesMasterArrayList = accountModesMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('mode', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
//        def names = []
//        accountModesMasterArrayList.each {
//            println(it.entityId)
//            def apires = showAccountModesByEntityId(it.entityId.toString())
//            names.push(apires)
//        }
        def recordsTotal = accountModesMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", accountModesMasterArrayList)
        return jsonObject
    }

    AccountModeMaster save(JSONObject jsonObject)
    {
        String mode = jsonObject.get("mode")
        String entity = jsonObject.get("entity")
        if (mode && entity)
        {
            AccountModeMaster accountModesMaster = new AccountModeMaster()
            accountModesMaster.mode = mode
            accountModesMaster.entityId = Long.parseLong(entity)
            accountModesMaster.save(flush: true)
            println(accountModesMaster)
            if (!accountModesMaster.hasErrors())
            {
                return accountModesMaster
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }

    AccountModeMaster update(JSONObject jsonObject, String id)
    {
        String mode = jsonObject.get("mode")
        String entity = jsonObject.get("entity")
        if (mode && entity)
        {
            AccountModeMaster accountModesMaster = AccountModeMaster.findById(Long.parseLong(id))
            if (accountModesMaster)
            {
                accountModesMaster.isUpdatable = true
                accountModesMaster.mode = mode
                accountModesMaster.entityId = Long.parseLong(entity)
                accountModesMaster.save(flush: true)
                if (!accountModesMaster.hasErrors())
                {
                    return accountModesMaster
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

    void delete(String id)
    {
        if (id)
        {
            AccountModeMaster accountModesMaster = AccountModeMaster.findById(Long.parseLong(id))
            if (accountModesMaster)
            {
                println(accountModesMaster)
                accountModesMaster.isUpdatable = true
                accountModesMaster.delete(flush: true)
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

//    def showAccountModesByEntityId(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.ENTITY_REGISTER_SHOW+"/"+id
//            URL apiUrl = new URL(url)
//            def card = new JsonSlurper().parseText(apiUrl.text)
//            return card
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
//            log.error('Service :showAccountModesByEntityId , action :  show  , Ex:' + ex)
//        }
//    }


}
