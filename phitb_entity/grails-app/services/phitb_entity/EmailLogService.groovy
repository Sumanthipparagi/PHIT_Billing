package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EmailLogService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return EmailLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EmailLog.findAllBySentTo("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return EmailLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EmailLog.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    EmailLog get(String id)
    {
        return EmailLog.findById(Long.parseLong(id))
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
        def emailLogCriteria = EmailLog.createCriteria()
        def emailLogArrayList = emailLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('sentTo', '%' + searchTerm + '%')
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

    EmailLog save(JSONObject jsonObject)
    {
        EmailLog emailLog = new EmailLog()
        emailLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        emailLog.sentTo = jsonObject.get("sentTo")
        emailLog.emailContent = jsonObject.get("emailContent")
        emailLog.emailSubject = jsonObject.get("emailSubject")
        emailLog.hasAttachments = jsonObject.get("hasAttachments")
        emailLog.emailService = jsonObject.get("emailService")
        if(jsonObject.has("deliveryStatus"))
            emailLog.deliveryStatus = jsonObject.get("deliveryStatus")
        if(jsonObject.has("docNo"))
            emailLog.docNo = jsonObject.get("docNo")
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

    EmailLog update(JSONObject jsonObject, String id)
    {
        EmailLog emailLog = EmailLog.findById(Long.parseLong(id))
        if (emailLog)
        {
            emailLog.isUpdatable = true
            emailLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            emailLog.sentTo = jsonObject.get("sentTo")
            emailLog.emailContent = jsonObject.get("emailContent")
            emailLog.hasAttachments = jsonObject.get("hasAttachments")
            emailLog.emailService = jsonObject.get("emailService")
            emailLog.emailSubject = jsonObject.get("emailSubject")
            if(jsonObject.has("deliveryStatus"))
                emailLog.deliveryStatus = jsonObject.get("deliveryStatus")
            if(jsonObject.has("docNo"))
                emailLog.docNo = jsonObject.get("docNo")
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
            EmailLog emailLog = EmailLog.findById(Long.parseLong(id))
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
