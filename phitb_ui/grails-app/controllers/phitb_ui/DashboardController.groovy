package phitb_ui

import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat
import java.time.LocalDate

class DashboardController {

    def index() {
        render(view: 'index')
    }

    def forms()
    {
        render(view: 'forms')
    }

    def table()
    {
        render(view:'datatable')
    }

    def timeline()
    {
        render(view: 'timeline')
    }

    def microServiceStatus()
    {
        render(view: 'microservice-status')
    }

    def systemServiceStatus()
    {
        try
        {
        def response = new SystemService().systemServiceStatus()
        JSONObject jsonObject = new JSONObject()
        if (response?.status == 200)
        {
            respond jsonObject,formats: ['json'], status: 200
        }
        else
        {
            respond status: 400
        }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def shipmentsServiceStatus()
    {
        try
        {
            def response = new ShipmentService().shipmentServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def salesServiceStatus()
    {
        try
        {
            def response = new SalesService().salesServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def purchaseServiceStatus()
    {
        try
        {
            def response = new PurchaseService().purchaseServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def productServiceStatus()
    {
        try
        {
            def response = new ProductService().productServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def inventoryServiceStatus()
    {
        try
        {
            def response = new InventoryService().inventoryServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def facilityServiceStatus()
    {
        try
        {
            def response = new FacilityService().facilityServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def entityServiceStatus()
    {
        try
        {
            def response = new EntityService().entityServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def accountsServiceStatus()
    {
        try
        {
            def response = new AccountsService().accountsServiceStatus()
            JSONObject jsonObject = new JSONObject()
            if (response?.status == 200)
            {
                respond jsonObject,formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch(Exception ex)
        {
            System.out.println(controllerName+" "+ex)
            log.error(controllerName+" "+ex)
        }
    }

    def dashboardStats()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //current month stats
        JSONObject jsonObject = new JSONObject()
        Calendar cal = Calendar.getInstance()
        cal.setTime(new Date())
        cal.set(Calendar.HOUR_OF_DAY, 11)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        String toDate = sdf.format(cal.getTime())

        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        String fromDate = sdf.format(cal.getTime())

        String entityId = session.getAttribute("entityId")
        String userId = session.getAttribute("userId")
        String financialYear = session.getAttribute("financialYear")
        jsonObject.put("fromDate", fromDate)
        jsonObject.put("toDate", toDate)
        jsonObject.put("entityId", entityId)
        jsonObject.put("userId", userId)
        jsonObject.put("financialYear", financialYear)
        JSONObject currentMonthSales = new ReportsService().getSalesStats(jsonObject)

        //sales of previous month
        cal.setTime(new Date())
        cal.set(Calendar.HOUR_OF_DAY, 11)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.add(Calendar.MONTH, -1)
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        toDate = sdf.format(cal.getTime())

        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        fromDate = sdf.format(cal.getTime())
        jsonObject.put("fromDate", fromDate)
        jsonObject.put("toDate", toDate)
        JSONObject previousMonthSales = new ReportsService().getSalesStats(jsonObject)

        JSONObject dashboardStats = new JSONObject()
        dashboardStats.put("salesCurrentMonth", currentMonthSales.get("totalSales"))
        dashboardStats.put("salesReturnCurrentMonth", currentMonthSales.get("totalSalesReturn"))
        dashboardStats.put("salesPreviousMonth", previousMonthSales.get("totalSales"))
        dashboardStats.put("salesReturnPreviousMonth", previousMonthSales.get("totalSalesReturn"))

        //this data is of current month only
        dashboardStats.put("totalDraftInvoice", previousMonthSales.get("totalDraftInvoice"))
        dashboardStats.put("totalOutstanding", currentMonthSales.get("totalOutstanding"))

        respond dashboardStats, formats: ['json']
    }


    def dayEnd(){
        def saleBills = new SalesService().deleteAllDrafts(session.getAttribute('entityId').toString())
        return saleBills;
    }
}
