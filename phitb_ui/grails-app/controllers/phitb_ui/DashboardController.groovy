package phitb_ui

import org.grails.web.json.JSONObject

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
}
