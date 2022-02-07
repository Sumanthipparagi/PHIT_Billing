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
            "/purchase/billdetail(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'index', POST: 'save'] }
            "/purchase/billdetail/datatable(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'dataTable'] }
            "/purchase/billdetail/$id(.$format)?"(controller: 'purchaseBillDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Order
            "/purchase/purchaseorder(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'index', POST: 'save'] }
            "/purchase/purchaseorder/datatable(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'dataTable'] }
            "/purchase/purchaseorder/$id(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/purchase/purchaseorderbydays/$days(.$format)?"(controller: 'purchaseOrder') { action = [GET: 'getAllByDays'] }

            //Purchase Product Detail
            "/purchase/productdetail(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'index', POST: 'save'] }
            "/purchase/productdetail/datatable(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'dataTable'] }
            "/purchase/productdetail/$id(.$format)?"(controller: 'purchaseProductDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Return Detail
            "/purchase/returndetail(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'index', POST: 'save'] }
            "/purchase/returndetail/datatable(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'dataTable'] }
            "/purchase/returndetail/$id(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/purchase/returndetailbydays/$days(.$format)?"(controller: 'purchaseReturnDetail') { action = [GET: 'getAllByDays'] }

            //Purchase Transaction log
            "/purchase/transactionlog(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'index', POST: 'save'] }
            "/purchase/transactionlog/datatable(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'dataTable'] }
            "/purchase/transactionlog/$id(.$format)?"(controller: 'purchaseTransactionLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Purchase Transportation Detail
            "/purchase/transportationdetail(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'index', POST: 'save'] }
            "/purchase/transportationdetail/datatable(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'dataTable'] }
            "/purchase/transportationdetail/$id(.$format)?"(controller: 'purchaseTransportationDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //Temp User Log
            "/purchase/tempuserlog(.$format)?"(controller: 'tempUserLog') { action = [GET: 'index', POST: 'save'] }
            "/purchase/tempuserlog/datatable(.$format)?"(controller: 'tempUserLog') { action = [GET: 'dataTable'] }
            "/purchase/tempuserlog/$id(.$format)?"(controller: 'tempUserLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }


        }
    }
}
