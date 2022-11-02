package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException

@Transactional
class DayEndLogService {

    DayEndLogs save(JSONObject jsonObject)
    {
        DayEndLogs dayEndLogs = new DayEndLogs()
        dayEndLogs.endDate = new Date();
        dayEndLogs.entityId = Long.parseLong(jsonObject.get('entityId').toString())
        dayEndLogs.entityTypeId = Long.parseLong(jsonObject.get('entityTypeId').toString())
        dayEndLogs.userId = Long.parseLong(jsonObject.get('userId').toString())
        dayEndLogs.save(flush: true)
        if (!dayEndLogs.hasErrors())
        {
            return dayEndLogs
        }
        else
        {
            throw new BadRequestException()
        }
    }
}
