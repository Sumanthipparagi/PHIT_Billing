package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ReasonMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return ReasonMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return ReasonMaster.findAllByReasonNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    ReasonMaster get(String id)
    {
        return ReasonMaster.findById(Long.parseLong(id))
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
                orderColumn = "reasonName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def reasonMasterDetailsCriteria = ReasonMaster.createCriteria()
        def reasonMasterArrayList = reasonMasterDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('reasonName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = reasonMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", reasonMasterArrayList)
        return jsonObject
    }

    ReasonMaster save(JSONObject jsonObject)
    {
        ReasonMaster reasonMaster = new ReasonMaster()
        reasonMaster.reasonName =  jsonObject.get("billId").toString()
        reasonMaster.reasonCode = jsonObject.get("series").toString()
        reasonMaster.save(flush: true)
        if (!reasonMaster.hasErrors())
        {
            return reasonMaster
        }
        else
        {
            throw new BadRequestException()
        }
    }

    ReasonMaster update(JSONObject jsonObject, String id)
    {
        ReasonMaster reasonMaster = ReasonMaster.findById(Long.parseLong(id))
        if (reasonMaster)
        {
            reasonMaster.isUpdatable = true
            reasonMaster.reasonName =  jsonObject.get("billId").toString()
            reasonMaster.reasonCode = jsonObject.get("series").toString()
            reasonMaster.save(flush: true)
            if (!reasonMaster.hasErrors())
            {
                return reasonMaster
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
            ReasonMaster reasonMaster = ReasonMaster.findById(Long.parseLong(id))
            if (reasonMaster)
            {
                reasonMaster.isUpdatable = true
                reasonMaster.delete()
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
