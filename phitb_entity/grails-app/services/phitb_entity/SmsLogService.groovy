package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class SmsLogService {

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

    def get(String id)
    {
        return SMSLog.findById(Long.parseLong(id))
    }

    def dataTables(JSONObject paramsJsonObject, String start, String length)
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
        def smsLogCriteria = SMSLog.createCriteria()
        def smsLogArrayList = smsLogCriteria.list(max: max, offset: offset) {
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
        def recordsTotal = smsLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", smsLogArrayList)
        return jsonObject
    }

    def save(JSONObject jsonObject)
    {
        SMSLog smsLog = new SMSLog()
        smsLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        smsLog.sentByUser = UserRegister.findById(Long.parseLong(jsonObject.get("sentByUser").toString()))
        smsLog.mobileNumber = jsonObject.get("mobileNumber")
        smsLog.smsContent = jsonObject.get("smsContent")
        if(jsonObject.has("deliveryStatus"))
            smsLog.deliveryStatus = jsonObject.get("deliveryStatus")
        if(jsonObject.has("docNo"))
            smsLog.docNo = jsonObject.get("docNo")
        if(jsonObject.has("docId"))
            smsLog.docId = jsonObject.get("docId")
        if(jsonObject.has("docType"))
            smsLog.docType = jsonObject.get("docType")
        if(jsonObject.has("messageId"))
            smsLog.messageId = jsonObject.get("messageId")

        smsLog.save(flush: true)
        if (!smsLog.hasErrors())
        {
            return smsLog
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def update(JSONObject jsonObject, String id)
    {
        SMSLog smsLog = SMSLog.findById(Long.parseLong(id))
        if (smsLog)
        {
            smsLog.isUpdatable = true
            smsLog.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            smsLog.sentByUser = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
            smsLog.mobileNumber = jsonObject.get("mobileNumber")
            smsLog.smsContent = jsonObject.get("smsContent")
            if(jsonObject.has("deliveryStatus"))
                smsLog.deliveryStatus = jsonObject.get("deliveryStatus")
            if(jsonObject.has("docNo"))
                smsLog.docNo = jsonObject.get("docNo")
            if(jsonObject.has("docId"))
                smsLog.docId = jsonObject.get("docId")
            if(jsonObject.has("docType"))
                smsLog.docType = jsonObject.get("docType")
            smsLog.save(flush: true)
            if (!smsLog.hasErrors())
            {
                return smsLog
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
            SMSLog smsLog = SMSLog.findById(Long.parseLong(id))
            if (smsLog)
            {
                smsLog.isUpdatable = true
                smsLog.delete()
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

    def getTemplate(String templateName, String entityId = null)
    {
        if(entityId)
        {
            long eid = Long.parseLong(entityId)
            EntityRegister entityRegister = EntityRegister.findById(eid)
            if(entityRegister)
                return SMSTemplate.findByTemplateNameAndEntityRegister(templateName, entityRegister)
            else
                throw new BadRequestException()
        }
        else
        {
            SMSTemplate smsTemplate = SMSTemplate.findByTemplateName(templateName)
            if(smsTemplate)
            {
                return smsTemplate
            }
            else
                throw new ResourceNotFoundException()
        }

    }
}
