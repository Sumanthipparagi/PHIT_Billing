package phitb_ui.purchase

import grails.converters.JSON
import org.grails.web.json.JSONArray
import phitb_ui.Constants
import phitb_ui.Links
import phitb_ui.ProductService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController

class PurchaseReturnController
{

    def index()
    {
        String entityId = session.getAttribute("entityId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> supplier = new EntityRegisterController().show() as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def reason = new SalesService().getReason()
        ArrayList<String> salesmanList = []
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
            {
                salesmanList.add(it)
            }
        }
        render(view: '/purchase/purchaseReturn/purchase-return', model: [supplier: supplier,
                                                                         divisions: divisions, series:series,
                                                                         salesmanList: salesmanList,
                                                                         priorityList:priorityList, reason: reason])
    }

    def getPurchaseBillBySupplier()
    {
        def purchasebills = new PurchaseService().getPurchaseBillBySupplier(params.supplierId)
        def apiResponse = new PurchaseService().getRequestWithIdList(purchasebills.id, new Links().PURCHASE_PRODUCT_OF_BILLIDS)
        if(apiResponse?.status == 200)
        {
            def prod = JSON.parse(apiResponse.readEntity(String.class))
            prod.each {product ->
                def index = purchasebills.findIndexOf({
                    it.id == product.billId
                })
                if (index != -1)
                    product.put("billId", purchasebills[index])
            }
            respond prod, formats: ['json'], status: 200
        }
        else
        {
            return []
        }
    }
}
