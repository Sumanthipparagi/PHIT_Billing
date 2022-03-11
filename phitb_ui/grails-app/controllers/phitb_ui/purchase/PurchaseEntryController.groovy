package phitb_ui.purchase

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.inventory.StockBookController
import phitb_ui.product.ProductController
import phitb_ui.system.AccountModeController

class PurchaseEntryController {

    def index() {
        String entityId = session.getAttribute("entityId")?.toString()
       JSONObject pur_entry = new JSONObject()
        ArrayList<String> series = new SeriesController().getByEntity(entityId) as ArrayList<String>
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> suppliers = new EntityRegisterController().getByAffiliates(entityId) as ArrayList<String>
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
        ArrayList<String> purc_product_detail = new PurchaseEntryController().purchaseDetailShow() as ArrayList<String>
        pur_entry.put("tempStockBook",tempStockBook)
        pur_entry.put("purc_product_detail",purc_product_detail)
        //ArrayList<String> salebilllist = new SalebillDetailsController().show() as ArrayList<String>
        ArrayList<String> companies = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> salesmanList = []
        ArrayList<String> companyList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }
        companies.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_COMPANY)) {
                companyList.add(it)
            }
        }
        def products = new ProductService().getProductsByEntityId(entityId)
        render(view: '/purchase/purchaseEntry/purchaseEntry', model: [series      : series, accountMode: accountMode,
                                                  users       : users, suppliers: suppliers,
                                                  salesmanList: salesmanList,
                                                  products    :products,companyList:companyList,
                                                                      tempStockBook:tempStockBook,pur_entry:pur_entry])
    }





    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new PurchaseService().savePurchaseDetails(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                redirect(uri: '/user-register')
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


    def purchaseDetailShow()
    {
        try
        {
            def apiResponse = new PurchaseService().getPurchaseProductDetails()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def purchaseReturn()
    {
        ArrayList<String> tempStockBook = new StockBookController().tempStockShow() as ArrayList<String>
        render(view: '/purchase/purchaseRetun',model: [tempStockBook:tempStockBook])
    }
}
