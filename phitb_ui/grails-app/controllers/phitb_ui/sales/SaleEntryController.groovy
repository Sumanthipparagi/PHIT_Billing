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
        ArrayList<String> series = new SeriesController().getByEntity(entityId) as ArrayList<String>
        //ArrayList<String> accountMode = new AccountModeController().show() as ArrayList<String>
        ArrayList<String> customers = new EntityRegisterController().getByAffiliates(entityId) as ArrayList<String>
        //ArrayList<String> users = new UserRegisterController().show() as ArrayList<String>
        //ArrayList<String> salebilllist = new SalebillDetailsController().show() as ArrayList<String>

        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        def products = new ProductService().getProductsByEntityId(entityId) //TODO: this should be called from ajax
        render(view: '/sales/sale-entry', model: [series : series, /*accountMode: accountMode,
                                                           users       : users,*/ customers: customers,
                                                           salesmanList: salesmanList,
                                                           products    :products])
    }


    def saleBill()
    {
        render(view: '/sales/sale-invoice')
    }

    def saleRetrun()
    {
        render(view: '/sales/saleReturn')
    }

    def crdDebS()
    {
        render(view:"/sales/credit-debit-settlement")
    }

    def DebJV()
    {
        render(view:"/sales/debit-jv")
    }

    def credJV()
    {
        render(view:'/sales/credit-jv')
    }

    def goodsSalesRecipt()
    {
        render(view:"/sales/goods-sales-recipt")
    }

    def paymentVocher()
    {
        render(view:"/sales/payment-vocher")
    }
}
