package phitb_system

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class PriorityMasterService {

    /**
     * Gets all priority
     * @param query
     * @param offset
     * @param limit
     * @return list of priority
     */
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PriorityMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PriorityMaster.findAllByPriorityIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Get all priority by id
     * @param id
     * @return list of priority
     */
    PriorityMaster get(String id) {
        return PriorityMaster.findById(Long.parseLong(id))
    }

    /**
     * Get all priority by entityId
     * @param entityId
     * @return list of priority by entity
     */
    def getAllByEntityId(String limit, String offset, long entityId) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
            return PriorityMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PriorityMaster.findAllByEntityId(entityId,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Gets all priority in datatables format
     * @return list of priority
     */
    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "priority"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def dayMasterCriteria = PriorityMaster.createCriteria()
        def dayMasterArrayList = dayMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('priority', '%' + searchTerm + '%')
                }
            }
            eq('entityId',entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = dayMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", dayMasterArrayList)
        return jsonObject
    }

    /**
     * Save new priority
     * @param priority
     * @return saved priority
     */
    PriorityMaster save(JSONObject jsonObject) {
        String priority = jsonObject.get("priority")
        if (priority) {
            PriorityMaster priorityMaster = new PriorityMaster()
            priorityMaster.priority = priority
            priorityMaster.entityId = Long.parseLong(jsonObject.get("entity").toString())
            priorityMaster.save(flush: true)
            if (!priorityMaster.hasErrors())
                return priorityMaster
            else
                throw new BadRequestException()
        } else {
            throw new BadRequestException()
        }
    }

    /**
     * Update new priority
     * @param priority
     * @return saved priority
     */
    PriorityMaster update(JSONObject jsonObject, String id) {
        String priority = jsonObject.get("priority")
        if (priority && id) {
            PriorityMaster priorityMaster = PriorityMaster.findById(Long.parseLong(id))
            if (priorityMaster) {
                priorityMaster.isUpdatable = true
                priorityMaster.priority = priority
                priorityMaster.save(flush: true)
                if (!priorityMaster.hasErrors())
                    return priorityMaster
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
            PriorityMaster priorityMaster = PriorityMaster.findById(Long.parseLong(id))
            if (priorityMaster) {
                priorityMaster.isUpdatable = true
                priorityMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
