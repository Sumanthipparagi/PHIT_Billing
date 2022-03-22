package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.product.BatchRegisterController
import phitb_ui.product.ProductController
import phitb_ui.system.CityController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

import javax.swing.text.html.parser.Entity

class SchemeEntryController {

    def index() {
        JSONArray products = new ProductService().getProductsByEntityId(session.getAttribute("entityId").toString())
        render(view: "/sales/schemeEntry/index", model: [products:products])
    }


    def addGenralScheme()
    {
        ArrayList<String> zoneList = new ZoneController().show()
        ArrayList<String> stateList = new StateController().show() as ArrayList<String>
        ArrayList<String> cityList = new CityController().show() as ArrayList<String>
        ArrayList<String> entityList = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> productList = new ProductController().show() as ArrayList<String>
        ArrayList<String> batchList = new BatchRegisterController().show() as ArrayList<String>
        ArrayList<String> distributorList = []
        entityList.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_DISTRIBUTOR))
            {
                distributorList.add(it)
            }
        }
        render(view: '/sales/schemeEntry/add-general-scheme',model: [zoneList:zoneList,stateList:stateList,
                                                                     cityList:cityList,
                                                                     distributorList:distributorList,
                                                                     productList:productList,batchList:batchList])
    }


    def saveGeneralScheme()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new SalesService().saveGeneralScheme(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }
}
