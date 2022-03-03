package phitb_ui.sales

import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.UserRegisterController
import phitb_ui.Constants
import phitb_ui.ProductService
import phitb_ui.system.AccountModeController

class SaleEntryController {

    def index() {

        String entityId = session.getAttribute("entityId")?.toString()
        ArrayList<String> series = new SeriesController().show() as ArrayList<String>
        ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
        ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        ArrayList<String> salebilllist = new SalebillDetailsController().show() as ArrayList<String>
        ArrayList<String> customerList = []
        ArrayList<String> salesmanList = []
        entity.each {
            if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_CUSTOMER)) {
                customerList.add(it)
            }
        }
        users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }
        def products = new ProductService().getProductsByEntityId(entityId)
        render(view: '/sales/sale-entry', model: [series      : series, accountMode: accountMode, entity: entity,
                                                           users       : users, customerList: customerList,
                                                           salesmanList: salesmanList, salebilllist:salebilllist,
                                                           products    :products])
    }


    def saleBill()
    {
        render(view: '/sales/sale-invoice')
    }
}
