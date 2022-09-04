package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EmailSettingService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return EmailSetting.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EmailSetting.findAllBySmtpUsername("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return EmailSetting.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EmailSetting.createCriteria().list(max: l, offset: o) {
                entity {
                    eq('id', entityId)
                }
            }
        }
    }


    EmailSetting get(String id)
    {
        return EmailSetting.findById(Long.parseLong(id))
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
        def emailSettingCriteria = EmailSetting.createCriteria()
        def emailSettingArrayList = emailSettingCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('smtpUsername', '%' + searchTerm + '%')
                }
            }
            entity {
                eq('id', entityId)
            }

            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = emailSettingArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", emailSettingArrayList)
        return jsonObject
    }

    EmailSetting save(JSONObject jsonObject)
    {
        EmailSetting emailSetting = null
        if (jsonObject.has("id"))
        {
            emailSetting = EmailSetting.findById(jsonObject.get("id"))
            emailSetting.isUpdatable = true
        }
        else
        {
            emailSetting = new EmailSetting()
        }

        emailSetting.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        emailSetting.senderMail = jsonObject.get("senderMail")
        emailSetting.smtpUsername = jsonObject.get("smtpUsername")
        emailSetting.smtpPassword = jsonObject.get("smtpPassword")
        emailSetting.smtpServer = jsonObject.get("smtpServer")
        emailSetting.smtpPort = jsonObject.get("smtpPort")
        emailSetting.reportMailBtn = false
        if (jsonObject.has("emailService"))
        {
            emailSetting.emailService = jsonObject.get("emailService")
        }
        if (jsonObject.has("encryptionType"))
        {
            emailSetting.encryptionType = jsonObject.get("encryptionType")
        }
        emailSetting.authenticationRequired = jsonObject.get("authenticationRequired")
        emailSetting.active = jsonObject.get("active")
        emailSetting.save(flush: true)
        if (!emailSetting.hasErrors())
        {
            return emailSetting
        }
        else
        {
            throw new BadRequestException()
        }
    }

    /* EmailSetting update(JSONObject jsonObject, String id)
     {
         EmailSetting emailSetting = EmailSetting.findById(Long.parseLong(id))
         if (emailSetting)
         {
             emailSetting.isUpdatable = true
             emailSetting.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
             emailSetting.senderMail = jsonObject.get("senderMail")
             emailSetting.smtpUsername = jsonObject.get("smtpUsername")
             emailSetting.smtpPassword = jsonObject.get("smtpPassword")
             emailSetting.smtpServer = jsonObject.get("smtpServer")
             emailSetting.smtpPort = jsonObject.get("smtpPort")
             if(jsonObject.has("emailService"))
                 emailSetting.emailService = jsonObject.get("emailService")
             if(jsonObject.has("encryptionType"))
                 emailSetting.encryptionType = jsonObject.get("encryptionType")
             emailSetting.authenticationRequired = jsonObject.get("authenticationRequired")
             emailSetting.active = jsonObject.get("active")
             emailSetting.save(flush: true)
             if (!emailSetting.hasErrors())
             {
                 return emailSetting
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
     }*/

    void delete(String id)
    {
        if (id)
        {
            EmailSetting emailSetting = EmailSetting.findById(Long.parseLong(id))
            if (emailSetting)
            {
                emailSetting.isUpdatable = true
                emailSetting.delete()
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

    Object saveEmailConfig(JSONObject jsonObject)
    {
        jsonObject.remove("controller")
        jsonObject.remove("action")
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.entityId.toString()))
        EmailSetting emailSetting = EmailSetting.findByEntity(entityRegister)
        if (emailSetting)
        {
            emailSetting.isUpdatable = true
            emailSetting.reportMailBtn = false
            if (jsonObject.type == "SALES")
            {
                emailSetting.salesEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "RECEIPT")
            {
                emailSetting.receiptEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "CRJV")
            {
                emailSetting.creditEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "PURCHASE")
            {
                emailSetting.purchaseConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "CRDB")
            {
                emailSetting.crDbSettlementEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else
            {
                emailSetting.reportMailBtn = Boolean.parseBoolean(jsonObject?.MAIL_BTN_REPORTS?.toString())
            }
            emailSetting.save(flush: true)
            return emailSetting
        }
        else
        {
            emailSetting = new EmailSetting()
            emailSetting.reportMailBtn = false
            if (jsonObject.type == "SALES")
            {
                emailSetting.salesEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "RECEIPT")
            {
                emailSetting.receiptEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "CRJV")
            {
                emailSetting.creditEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "PURCHASE")
            {
                emailSetting.purchaseConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else if (jsonObject.type == "CRDB")
            {
                emailSetting.crDbSettlementEmailConfig = jsonObject.toString()
                emailSetting.entity = entityRegister
            }
            else
            {
                emailSetting.reportMailBtn = Boolean.parseBoolean(jsonObject?.MAIL_BTN_REPORTS?.toString())
            }
            emailSetting.save(flush: true)
            return emailSetting
        }

    }
}
