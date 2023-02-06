package phitb_ui

import com.google.gson.JsonObject
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat
import java.time.LocalDate

class DashboardController
{

    def index()
    {
        render(view: 'index')
    }

    def forms()
    {
        render(view: 'forms')
    }

    def table()
    {
        render(view: 'datatable')
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
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
                respond jsonObject, formats: ['json'], status: 200
            }
            else
            {
                respond status: 400
            }
        }
        catch (Exception ex)
        {
            System.out.println(controllerName + " " + ex)
            log.error(controllerName + " " + ex)
        }
    }

    def dashboardStats()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //current month stats
        JSONObject jsonObject = new JSONObject()
        Calendar cal = Calendar.getInstance()
        cal.setTime(new Date())
        cal.set(Calendar.HOUR_OF_DAY, 23)
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

    def dayEnd()
    {
        try
        {
            JSONObject jsonObject = new JSONObject()
            def draftSaleBillDetails = new SalesService().getSaleBillDraftDetails(session.getAttribute('entityId').toString(), session.getAttribute('userId').toString())
            for (JSONObject dr : draftSaleBillDetails)
            {
                def cancelBills = new SalesService().cancelInvoice(dr.id.toString(), session.getAttribute('entityId').toString(), session.getAttribute("financialYear").toString(), session.getAttribute('userId').toString())
                if (cancelBills != null)
                {
                    jsonObject.put("saleStocks", "SUCCESS")
                }
            }
            def tempStocks = new InventoryService().getTempStocksByUser(session.getAttribute('userId').toString())
            if (tempStocks.status == 200)
            {
                JSONArray tempStockArray = new JSONArray(tempStocks.readEntity(String.class))
                for (JSONObject tempstock : tempStockArray)
                {
                    def deleteTempStock = new InventoryService().deleteTempStock(tempstock.id.toString())
                    if (deleteTempStock?.status == 200)
                    {
                        jsonObject.put("tempstocks", "SUCCESS")
                    }
                }
            }
            def deleteDraftSaleBillDetails = new SalesService().deleteAllDrafts(session.getAttribute('entityId').toString(), session.getAttribute('userId').toString())
            if (deleteDraftSaleBillDetails.status == 200)
            {
                jsonObject.put("draftSaleBillDelete", "SUCCESS")
            }

            def draftPurchaseBillDetails = new PurchaseService().getPurchaseBillDraftDetails(session.getAttribute('entityId').toString(), session.getAttribute('userId').toString())
            for (JSONObject dr : draftPurchaseBillDetails)
            {
                def cancelPurBills = new PurchaseService().cancelPurchaseInvoice(dr.id.toString(), session.getAttribute('entityId')
                        .toString(), session.getAttribute("financialYear").toString(), session.getAttribute('userId').toString())
                if (cancelPurBills != null)
                {
                    jsonObject.put("purchaseStocks", "SUCCESS")
                }
            }
            def deleteDraftPurchaseBillDetails = new PurchaseService().deleteAllDrafts(session.getAttribute('entityId')
                    .toString(), session.getAttribute('userId').toString())
            if (deleteDraftPurchaseBillDetails.status == 200)
            {
                jsonObject.put("deleteDraftPurchaseBill", "SUCCESS")
            }
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("entityId", session.getAttribute('entityId'))
            jsonObject1.put("entityTypeId", session.getAttribute('entityTypeId'))
            jsonObject1.put("userId", session.getAttribute('userId'))
            def saveDayEndLogs = new EntityService().saveDayEndLogs(jsonObject1)
            if (saveDayEndLogs.status == 200)
            {
                println("Day end Logs Saved!")
            }
            respond jsonObject, formats: ['json'], status: 200;
        }
        catch (Exception e)
        {
            log.error(controllerName + " " + e)
            println controllerName + " " + e
        }
    }


    def dayEndDetails()
    {
//        Sales
        def draftSaleBillDetails = new SalesService().getSaleBillDraftDetails(session.getAttribute('entityId').toString(), session.getAttribute('userId').toString())
        ArrayList<String> batches = new ArrayList<String>()
        if (draftSaleBillDetails != null)
        {
            for (JSONObject draftBill : draftSaleBillDetails)
            {
                for (JSONObject draftProduct : draftBill.products)
                {
                    def product = new ProductService().getProductById(draftProduct.productId.toString())
                    draftProduct.put("product", product)
                }
            }
        }

//        Purchase
        def draftPurchaseBillDetails = new PurchaseService().getPurchaseBillDraftDetails(session.getAttribute('entityId').toString(), session.getAttribute('userId').toString())
        if (draftPurchaseBillDetails != null)
        {
            for (JSONObject draftPurchaseBill : draftPurchaseBillDetails)
            {
                for (JSONObject draftPurchaseProduct : draftPurchaseBill.products)
                {
                    def product = new ProductService().getProductById(draftPurchaseProduct.productId.toString())
                    draftPurchaseProduct.put("product", product)
                }
            }
        }
        render(view: '/dashboard/day-end-detail', model: [draftSaleBillDetails    : draftSaleBillDetails,
                                                          draftPurchaseBillDetails: draftPurchaseBillDetails])
    }

    def salesMonthWiseForGraph()
    {

        //show last 12 months stats
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //current month stats
        Calendar cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, -11)
        JSONArray monthlySales = new JSONArray()
        for (int i=0; i<=12;i++) {
            JSONObject jsonObject = new JSONObject()
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            if(i != 0)
            {
                cal.set(Calendar.MONTH, i)
            }
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
            String toDate = sdf.format(cal.getTime())
            String monthName =  new SimpleDateFormat("YYYY-MM").format(cal.getTime())
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH))
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            String fromDate = sdf.format(cal.getTime())

            String entityId = session.getAttribute("entityId")
            String userId = session.getAttribute("userId")
            String financialYear = session.getAttribute("financialYear")
            jsonObject.put("fromDate", fromDate)
            jsonObject.put("toDate", toDate)
            jsonObject.put("entityId", entityId)
            jsonObject.put("userId", userId)
            jsonObject.put("financialYear", financialYear)
            JSONObject sales = new ReportsService().getSalesStats(jsonObject)

            if(sales) {
                sales.put("month", monthName)
                monthlySales.add(sales)
            }
        }

        respond monthlySales, formats: ['json']

    }

}
