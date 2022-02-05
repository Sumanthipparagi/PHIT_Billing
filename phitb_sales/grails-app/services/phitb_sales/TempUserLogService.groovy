package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TempUserLogService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return TempUserLog.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return TempUserLog.findAllByBillIdIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    TempUserLog get(String id)
    {
        return TempUserLog.findById(Long.parseLong(id))
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
                orderColumn = "billId"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def tempUserLogCriteria = TempUserLog.createCriteria()
        def tempUserLogArrayList = tempUserLogCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('billId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = tempUserLogArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", tempUserLogArrayList)
        return jsonObject
    }

    TempUserLog save(JSONObject jsonObject)
    {
        TempUserLog tempUserLog = new TempUserLog()
        tempUserLog.userId = Long.parseLong(jsonObject.get("userId").toString())
        tempUserLog.billId = jsonObject.get("serBillId").toString()
        tempUserLog.billType = jsonObject.get("billType").toString()
        tempUserLog.serBillId = jsonObject.get("serBillId").toString()
        tempUserLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        tempUserLog.entityId =Long.parseLong(jsonObject.get("entityId").toString())
        tempUserLog.save(flush: true)
        if (!tempUserLog.hasErrors())
        {
            return tempUserLog
        }
        else
        {
            throw new BadRequestException()
        }
    }

    TempUserLog update(JSONObject jsonObject, String id)
    {
        TempUserLog tempUserLog = TempUserLog.findById(Long.parseLong(id))
        if (tempUserLog)
        {
            tempUserLog.isUpdatable = true
            tempUserLog.userId = Long.parseLong(jsonObject.get("userId").toString())
            tempUserLog.billId = jsonObject.get("serBillId").toString()
            tempUserLog.billType = jsonObject.get("billType").toString()
            tempUserLog.serBillId = jsonObject.get("serBillId").toString()
            tempUserLog.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            tempUserLog.entityId =Long.parseLong(jsonObject.get("entityId").toString())
            tempUserLog.save(flush: true)
            if (!tempUserLog.hasErrors())
            {
                return tempUserLog
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
            TempUserLog tempUserLog = TempUserLog.findById(Long.parseLong(id))
            if (tempUserLog)
            {
                tempUserLog.isUpdatable = true
                tempUserLog.delete()
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
