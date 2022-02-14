package phitb_system

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class CountryMasterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CountryMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CountryMaster.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    CountryMaster get(String id) {
        return CountryMaster.findById(Long.parseLong(id))
    }

    def getAllByEntityId(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return CountryMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return CountryMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
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

        def countryMasterCriteria = CountryMaster.createCriteria()
        def countryMasterArrayList = countryMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def names = []
        countryMasterArrayList.each {
            println(it.entityId)
            def apires = showCountryByEntityId(it.entityId.toString())
            names.push(apires)
        }
        def recordsTotal = countryMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("names", names)
        jsonObject.put("data", countryMasterArrayList)
        return jsonObject
    }

    CountryMaster save(JSONObject jsonObject) {
        String name = jsonObject.get("name")
        if (name) {
            CountryMaster countryMaster = new CountryMaster()
            countryMaster.name = name
            countryMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            countryMaster.save(flush: true)
            if (!countryMaster.hasErrors())
                return countryMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    CountryMaster update(JSONObject jsonObject, String id) {
        String name = jsonObject.get("name")
        if (name && id) {
            CountryMaster countryMaster = CountryMaster.findById(Long.parseLong(id))
            if (countryMaster) {
                countryMaster.isUpdatable = true
                countryMaster.name = name
                countryMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                countryMaster.save(flush: true)
                if (!countryMaster.hasErrors())
                    return countryMaster
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
            CountryMaster countryMaster = CountryMaster.findById(Long.parseLong(id))
            if (countryMaster) {
                countryMaster.isUpdatable = true
                countryMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    def showCountryByEntityId(String id)
    {
        try
        {
            def url = "http://localhost/api/v1.0/entity/entityregister/"+id
            URL apiUrl = new URL(url)
            def entity = new JsonSlurper().parseText(apiUrl.text)
            return entity
        }
        catch (Exception ex)
        {
            System.err.println('Service :CountryMaster , action :  show  , Ex:' + ex)
            log.error('Service :CountryMaster , action :  show  , Ex:' + ex)
        }
    }
}
