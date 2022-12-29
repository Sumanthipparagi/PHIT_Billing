package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SMSLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return SMSLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SMSLog.findAllByMobileNumber("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return SMSLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SMSLog.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    SMSLog get(String id)
    {
        return SMSLog.findById(Long.parseLong(id))
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
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def emailLogCriteria = SMSLog.createCriteria()
        def emailLogArrayList = emailLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('mobileNumber', '%' + searchTerm + '%')
                }
            }
            entity {
                eq('id',entityId)
            }

            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = emailLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", emailLogArrayList)
        return jsonObject
    }

    SMSLog save(JSONObject jsonObject)
    {
        SMSLog emailLog = new SMSLog()
        emailLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        emailLog.sentByUser = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
        emailLog.mobileNumber = jsonObject.get("mobileNumber")
        emailLog.smsContent = jsonObject.get("smsContent")
        if(jsonObject.has("deliveryStatus"))
            emailLog.deliveryStatus = jsonObject.get("deliveryStatus")
        if(jsonObject.has("docNo"))
            emailLog.docNo = jsonObject.get("docNo")
        if(jsonObject.has("docId"))
            emailLog.docId = jsonObject.get("docId")
        if(jsonObject.has("docType"))
            emailLog.docType = jsonObject.get("docType")
        emailLog.save(flush: true)
        if (!emailLog.hasErrors())
        {
            return emailLog
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SMSLog update(JSONObject jsonObject, String id)
    {
        SMSLog emailLog = SMSLog.findById(Long.parseLong(id))
        if (emailLog)
        {
            emailLog.isUpdatable = true
            emailLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            emailLog.sentByUser = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
            emailLog.mobileNumber = jsonObject.get("mobileNumber")
            emailLog.smsContent = jsonObject.get("smsContent")
            if(jsonObject.has("deliveryStatus"))
                emailLog.deliveryStatus = jsonObject.get("deliveryStatus")
            if(jsonObject.has("docNo"))
                emailLog.docNo = jsonObject.get("docNo")
            if(jsonObject.has("docId"))
                emailLog.docId = jsonObject.get("docId")
            if(jsonObject.has("docType"))
                emailLog.docType = jsonObject.get("docType")
            emailLog.save(flush: true)
            if (!emailLog.hasErrors())
            {
                return emailLog
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
            SMSLog emailLog = SMSLog.findById(Long.parseLong(id))
            if (emailLog)
            {
                emailLog.isUpdatable = true
                emailLog.delete()
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
