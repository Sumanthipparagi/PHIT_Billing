package phitb_purchase

import phitb_purchase.Exception.BadRequestException
import phitb_purchase.Exception.ResourceNotFoundException

class UrlMappings {

    static mappings = {
        //delete "/$controller/$id(.$format)?"(action: "delete")
        //post "/$controller(.$format)?"(action: "save")
        //get "/$controller(.$format)?"(action: "index")
        //get "/$controller/$id(.$format)?"(action: "show")
        //put "/$controller/$id(.$format)?"(action: "update")

        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(controller: "error", action: "error404", exception: ResourceNotFoundException)
        "400"(controller: "error", action: "error400", exception: BadRequestException)

        group "/api/v1.0", {
            //Purchase Bill Detail
            "/billdetail(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'index', POST: 'save'] }
            "/billdetail/datatable(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'dataTable'] }
            "/billdetail/$id(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Order
            "/purchaseorder(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'index', POST: 'save'] }
            "/purchaseorder/datatable(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'dataTable'] }
            "/purchaseorder/$id(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/purchaseorderbydays/$days(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'getAllByDays'] }

            //Purchase Product Detail
            "/productdetail(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'index', POST: 'save'] }
            "/productdetail/datatable(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'dataTable'] }
            "/productdetail/$id(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Return Detail
            "/returndetail(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'index', POST: 'save'] }
            "/returndetail/datatable(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'dataTable'] }
            "/returndetail/$id(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/returndetailbydays/$days(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'getAllByDays'] }

            //Purchase Transaction log
            "/transactionlog(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'index', POST: 'save'] }
            "/transactionlog/datatable(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'dataTable'] }
            "/transactionlog/$id(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Transportation Detail
            "/transportationdetail(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'index', POST: 'save'] }
            "/transportationdetail/datatable(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'dataTable'] }
            "/transportationdetail/$id(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Temp User Log
            "/tempuserlog(.$format)?"(controller: 'tempUserLog') { action = [GET: 'index', POST: 'save'] }
            "/tempuserlog/datatable(.$format)?"(controller: 'tempUserLog') { action = [GET: 'dataTable'] }
            "/tempuserlog/$id(.$format)?"(controller: 'tempUserLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }


        }
    }
}
