package phitb_inventory

import phitb_inventory.Exception.BadRequestException
import phitb_inventory.Exception.ResourceNotFoundException

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

        group "/api/v1.0/inventory", {
            //Stock Activity
            "/stockactivity(.$format)?"(controller: 'stockActivity') { action = [GET: 'index', POST: 'save'] }
            "/stockactivity/datatable(.$format)?"(controller: 'stockActivity') { action = [GET: 'dataTable'] }
            "/stockactivity/$id(.$format)?"(controller: 'stockActivity') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/stockactivitydaterangeentity"(controller: 'stockActivity', action: 'getByDateRangeAndEntity')
            "/stockactivity/closing"(controller: 'stockActivity', action: 'getClosingStockOfProduct')

            //Stock Book
            "/stockbook(.$format)?"(controller: 'stockBook') { action = [GET: 'index', POST: 'save'] }
            "/stockbook/datatable(.$format)?"(controller: 'stockBook') { action = [GET: 'dataTable'] }
            "/stockbook/$id(.$format)?"(controller: 'stockBook') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/stockbookbyentity/$id(.$format)?"(controller: 'stockBook') { action = [GET: 'getByEntityId'] }
            "/stockbookbyproduct/$id(.$format)?"(controller: 'stockBook') { action = [GET: 'getByProductId'] }
            "/stockbookbyproductsalereturn(.$format)?"(controller: 'stockBook',action: 'getByProductIdForSaleReturn')

            "/stockbook/purchase/batch/$batch/qty/$purQty(.$format)?"(controller: 'stockBook') {
                action = [GET:'stockPurchase'] }

            "/stockbook/salereturn(.$format)?"(controller: 'stockBook') {
                action = [GET:'stockIncrease'] }
            "/stockbook/purchasereturn(.$format)?"(controller: 'stockBook') {
                action = [GET:'stockDecrease'] }
            "/stockbook/product/$id/batch/$batch(.$format)?"(controller: 'stockBook') {
                action = [GET:'getByProductIdAndBatch'] }
            "/save-bulk-stocks"(controller: 'stockBook', action: 'saveBulkStocks')
            "/stockbook/update-batch-details"(controller: 'stockBook', action: 'updateBatchDetails')


            //Temp Stock Book
            "/tempstockbook(.$format)?"(controller: 'tempStockBook') { action = [GET: 'index', POST: 'save'] }
            "/tempstockbook/user/$id(.$format)?"(controller: 'tempStockBook') { action = [GET: 'getByUserId'] }
            "/tempstockbook/datatable(.$format)?"(controller: 'tempStockBook') { action = [GET: 'dataTable'] }
            "/tempstockbook/$id(.$format)?"(controller: 'tempStockBook') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/tempstockbookbyentity/$id(.$format)?"(controller: 'tempStockBook') { action = [GET: 'getByEntityId'] }
            "/tempstockbook/product/$id/batch/$batch(.$format)?"(controller: 'tempStockBook') {
                action = [GET:'getByProductIdAndBatch'] }
            "/tempstockbook/product/$id(.$format)?"(controller: 'tempStockBook') {
                action = [GET:'getByProductIdAndBatch'] }
            "/tempstockbook/product/$id/batch/$batch/entityid/$entityid(.$format)?"(controller: 'tempStockBook') {
                action = [GET:'getByProductIdAndBatchAndEntityId'] }



            "/status"(controller: 'status', action: 'index')

            "/reports/expiry(.$format)?"(controller: 'reports') { action = [POST: 'expiryReport'] }
        }
    }
}
