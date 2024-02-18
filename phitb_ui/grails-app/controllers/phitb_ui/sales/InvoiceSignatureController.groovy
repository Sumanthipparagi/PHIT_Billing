package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.ProductService
import phitb_ui.SalesService

class InvoiceSignatureController {

    def index() {
        try
        {
            render(view: '/sales/Invoice Signature/invoiceSignature')
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

//    def getByInvoiceNumber(String id)
//    {
//        try
//        {
//            JSONObject jsonObject = new JSONObject()
//            jsonObject.put("id", id)
//            JSONObject product = new SalesService().getInvoiceDetailsByInvoiceNumber(jsonObject)
//            return product
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :getProductsById , action :  show  , Ex:' + ex)
//            log.error('Service :getProductsById , action :  show  , Ex:' + ex)
//        }
//    }

    def getByInvoiceNumber()
    {

        try
        {
            def entityId = session.getAttribute("entityId").toString()
            String id = params.id
                def jsonArray = new SalesService().getInvoiceDetailsByInvoiceNumber(id,entityId)
                respond jsonArray, formats: ['json']

        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
