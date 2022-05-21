package phitb_entity

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.SimpleDateFormat

@Transactional
class TermsConditionDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return TermsConditionsDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TermsConditionsDetails.findAllByTermConditionIlike("%" + query + "%", [sort: 'id', max: l, offset: o,
                                                                                      order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return TermsConditionsDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return TermsConditionsDetails.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }
    TermsConditionsDetails get(String id) {
        return TermsConditionsDetails.findById(Long.parseLong(id))
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
                orderColumn = "termCondition"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def termConditionDetailsCriteria = TermsConditionsDetails.createCriteria()
        def termConditionDetailsArrayList = termConditionDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('termCondition', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
//        def form = []
//        termConditionDetailsArrayList.each {
//            println(it.formId)
//            def apires = showFormById(it.formId.toString())
//            form.push(apires)
//        }
        def recordsTotal = termConditionDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("form", form)
        jsonObject.put("data", termConditionDetailsArrayList)
        return jsonObject
    }

    TermsConditionsDetails save(JSONObject jsonObject) {
        TermsConditionsDetails termsConditionsDetails = new TermsConditionsDetails()
        termsConditionsDetails.formId = Long.parseLong(jsonObject.get("formId").toString())
        termsConditionsDetails.termCondition = jsonObject.get("termCondition").toString()
        termsConditionsDetails.status = Long.parseLong(jsonObject.get("status").toString())
        termsConditionsDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        termsConditionsDetails.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        termsConditionsDetails.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        termsConditionsDetails.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        termsConditionsDetails.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        termsConditionsDetails.save(flush: true)
        if (!termsConditionsDetails.hasErrors())
            return termsConditionsDetails
        else
            throw new BadRequestException()
    }

    TermsConditionsDetails update(JSONObject jsonObject, String id) {
        TermsConditionsDetails termsConditionsDetails = TermsConditionsDetails.findById(Long.parseLong(id))
        if (termsConditionsDetails) {
            termsConditionsDetails.isUpdatable = true
            termsConditionsDetails.formId = Long.parseLong(jsonObject.get("formId").toString())
            termsConditionsDetails.termCondition = jsonObject.get("termCondition").toString()
            termsConditionsDetails.status = Long.parseLong(jsonObject.get("status").toString())
            termsConditionsDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            termsConditionsDetails.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            termsConditionsDetails.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            termsConditionsDetails.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            termsConditionsDetails.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            termsConditionsDetails.save(flush: true)
            if (!termsConditionsDetails.hasErrors())
                return termsConditionsDetails
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            TermsConditionsDetails termsConditionsDetails = TermsConditionsDetails.findById(Long.parseLong(id))
            if (termsConditionsDetails) {
                termsConditionsDetails.isUpdatable = true
                termsConditionsDetails.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

//    def showFormById(String id)
//    {
//        try
//        {
//            def url = Constants.API_GATEWAY+Constants.FORM_MASTER_SHOW+"/"+id
//            URL apiUrl = new URL(url)
//            def card = new JsonSlurper().parseText(apiUrl.text)
//            return card
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :showFormById , action :  show  , Ex:' + ex)
//            log.error('Service :showFormById , action :  show  , Ex:' + ex)
//        }
//    }


//    def showFormById(String id)
//    {
//        Client client = ClientBuilder.newClient()
//        WebTarget target = client.target(new Constants().API_GATEWAY)
//        try
//        {
//            Response apiResponse = target
//                    .path(new Constants().FORM_MASTER_SHOW+"/"+id)
//                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .get()
//            if(apiResponse.status == 200)
//            {
//                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                return obj
//            }
//
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :EntityService , action :  putUser  , Ex:' + ex)
//            log.error('Service :EntityService , action :  putUser  , Ex:' + ex)
//        }
//
//    }
}
