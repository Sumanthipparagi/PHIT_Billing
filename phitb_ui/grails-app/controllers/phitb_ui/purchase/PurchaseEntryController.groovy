package phitb_ui.purchase

import phitb_ui.Constants
import phitb_ui.ProductService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.system.AccountModeController

class PurchaseEntryController {

    def index() {

        String entityId = session.getAttribute("entityId")?.toString()
        ArrayList<String> series = new SeriesController().getByEntity(entityId) as ArrayList<String>
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> suppliers = new EntityRegisterController().getByAffiliates(entityId) as ArrayList<String>
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
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
                                                  products    :products,companyList:companyList])
    }


    def purchaseReturn()
    {
        render(view: "/purchase/purchaseRetun")
    }
}
