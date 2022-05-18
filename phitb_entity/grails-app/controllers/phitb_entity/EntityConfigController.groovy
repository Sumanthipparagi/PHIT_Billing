package phitb_entity


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

class EntityConfigController {
	static responseFormats = ['json', 'xml']
    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE", dataTable: "GET"]

EntityConfigService entityConfigService

    /**
     * Save new entity setting
     * @param entity setting
     * @return saved entity setting
     */
    def save() {
        try {
            JSONObject jsonObject = JSON.parse(request.reader.text) as JSONObject
            respond entityConfigService.save(jsonObject)
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

    /**
     * Get requested entity setting
     * @param id
     * @return get requested entity setting
     */
    def  getAllbyEntityId(){
        try {
            String id = params.id
            if (id) {
                respond entityConfigService.getAllByEntityId(id)
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
