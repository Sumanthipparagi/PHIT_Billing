package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class UserLogInfoService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return UserLogInfo.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return UserLogInfo.findAllByLoginIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    UserLogInfo get(String id)
    {
        return UserLogInfo.findById(Long.parseLong(id))
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
                orderColumn = "loginId"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def userLogInfoCriteria = UserLogInfo.createCriteria()
        def userLogInfoArrayList = userLogInfoCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('loginId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = userLogInfoArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", userLogInfoArrayList)
        return jsonObject
    }

    UserLogInfo save(JSONObject jsonObject)
    {
        UserLogInfo userLogInfo = new UserLogInfo()
        userLogInfo.loginId = jsonObject.get("loginId").toString()
        userLogInfo.ipAddress = jsonObject.get("ipAddress").toString()
        userLogInfo.formId = jsonObject.get("formId").toString()
        userLogInfo.userId = Long.parseLong(jsonObject.get("userId").toString())
        userLogInfo.loginTime = sdf.parse(jsonObject.get("loginTime").toString())
        userLogInfo.logoutTime = sdf.parse(jsonObject.get("logoutTime").toString())
        userLogInfo.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        userLogInfo.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        userLogInfo.save(flush: true)
        if (!userLogInfo.hasErrors())
        {
            return userLogInfo
        }
        else
        {
            throw new BadRequestException()
        }
    }

    UserLogInfo update(JSONObject jsonObject, String id)
    {
        UserLogInfo userLogInfo = UserLogInfo.findById(Long.parseLong(id))
        if (userLogInfo)
        {
            userLogInfo.isUpdatable = true
            userLogInfo.loginId = jsonObject.get("loginId").toString()
            userLogInfo.ipAddress = jsonObject.get("ipAddress").toString()
            userLogInfo.formId = jsonObject.get("formId").toString()
            userLogInfo.userId = Long.parseLong(jsonObject.get("userId").toString())
            userLogInfo.loginTime = sdf.parse(jsonObject.get("loginTime").toString())
            userLogInfo.logoutTime = sdf.parse(jsonObject.get("logoutTime").toString())
            userLogInfo.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            userLogInfo.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            userLogInfo.save(flush: true)
            if (!userLogInfo.hasErrors())
            {
                return userLogInfo
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
            UserLogInfo userLogInfo = UserLogInfo.findById(Long.parseLong(id))
            if (userLogInfo)
            {
                userLogInfo.isUpdatable = true
                userLogInfo.delete()
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
