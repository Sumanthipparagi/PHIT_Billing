package phitb_sales


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONObject
import org.springframework.boot.context.config.ResourceNotFoundException
import phitb_sales.Exception.BadRequestException

class InvoiceSignatureController {
	static responseFormats = ['json', 'xml']

    static allowedMethods = [index    : "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE",
                             dataTable: "GET", updateIRNDetails: "PUT", saveInvoice: "POST", updateInvoice: "PUT"]

    InvoiceSignatureService invoiceSignatureService

    /**
     * Gets all Sale Bill Details
     * @param query
     * @param offset
     * @param limit
     * @return list of Sale Bill Details
     */



//    def getDetailsByInvoiceNumber() {
//        try {
//            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
//            String id = jsonObject.get("id").toString()
//            if (id) {
//                respond invoiceSignatureService.getDetailsByInvoiceNumber(id)
//            }
//        }
//        catch (ResourceNotFoundException ex) {
//            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//            response.status = 404
//        }
//        catch (BadRequestException ex) {
//            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//            response.status = 400
//        }
//        catch (Exception ex) {
//            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
//        }
//    }

    def getDetailsByInvoiceNumber(String id, String entityId) {
        try {

            if (id) {
                respond invoiceSignatureService.getDetailsByInvoiceNumber(id,entityId)
            }
        }
        catch (ResourceNotFoundException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 404
        }
        catch (BadRequestException ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
        }
    }
}
