package phitb_ui

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'auth', action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/auth"(controller: "auth", action: "index")
        "/login"(controller: "auth", action: 'login')
        "/logout"(controller: "auth", action: 'logout')
        "/dashboard"(controller: 'dashboard', action: "index")
        "/forms"(controller: 'dashboard', action: 'forms')
        "/table"(controller: 'dashboard', action: 'table')
        "/timeline"(controller: 'dashboard', action: 'timeline')
        "/api/media/$path**"(controller: "fileLocation", action: "index")
        "/user/update-details/$id"(controller: 'auth' , action: 'updateUser')
        "/user/update-password"(controller: 'userRegister' , action: 'updatePassword')
/*<-------------------------------------------System ------------------------------------------------->*/
        "/accountmodes"(controller: "accountMode") {
            action = [GET: "index", POST: "save"]
        }
        "/accountmodes/datatable"(controller: "accountMode", action: "dataTable")
        "/accountmodes/getallentity"(controller: "accountMode", action: 'getAllEntity')
        "/accountmodes/update/$id"(controller: "accountMode",action:"update")
        "/accountmodes/delete/$id"(controller: "accountMode",action:"delete")

//        State
        "/state"(controller: "state") {
            action = [GET: "index", POST: "save"]
        }
        "/state/datatable"(controller: "state", action: "dataTable")
        "/state/update/$id"(controller: "state",action:"update")
        "/state/delete/$id"(controller: "state",action:"delete")

//      City
        "/city"(controller: "city") {
            action = [GET: "index", POST: "save"]
        }
        "/city/datatable"(controller: "city", action: "dataTable")
        "/city/update/$id"(controller: "city",action:"update")
        "/city/delete/$id"(controller: "city",action:"delete")


//        //      Priority
//        "/priority"(controller: "priority") {
//            action = [GET: "index", POST: "save"]
//        }
//        "/priority/datatable"(controller: "priority", action: "dataTable")
//        "/priority/update/$id"(controller: "priority",action:"update")
//        "/priority/delete/$id"(controller: "priority",action:"delete")


//      Country
        "/country"(controller: "country") {
            action = [GET: "index", POST: "save"]
        }
        "/country/datatable"(controller: "country", action: "dataTable")
        "/country/update/$id"(controller: "country",action:"update")
        "/country/delete/$id"(controller: "country",action:"delete")


//      Form Master
        "/form"(controller: "form") {
            action = [GET: "index", POST: "save"]
        }
        "/form/datatable"(controller: "form", action: "dataTable")
        "/form/update/$id"(controller: "form",action:"update")
        "/form/delete/$id"(controller: "form",action:"delete")


        //      Priority
        "/priority"(controller: "priority") {
            action = [GET: "index", POST: "save"]
        }
        "/priority/datatable"(controller: "priority", action: "dataTable")
        "/priority/update/$id"(controller: "priority",action:"update")
        "/priority/delete/$id"(controller: "priority",action:"delete")
        "/priority/entity/$id"(controller: "priority",action:"getPriorityByEntity")

        /*<-------------------------------------------Facility ------------------------------------------------->*/

//        Fridge
        "/fridge"(controller: "fridge") {
            action = [GET: "index", POST: "save"]
        }
        "/fridge/datatable"(controller: "fridge", action: "dataTable")
        "/fridge/update/$id"(controller: "fridge",action:"update")
        "/fridge/delete/$id"(controller: "fridge",action:"delete")

//        ccm

        "/ccm"(controller: "ccm") {
            action = [GET: "index", POST: "save"]
        }
        "/ccm/datatable"(controller: "ccm", action: "dataTable")
        "/ccm/update/$id"(controller: "ccm",action:"update")
        "/cmm/delete/$id"(controller: "ccm",action:"delete")

        //        godown

        "/godown"(controller: "godown") {
            action = [GET: "index", POST: "save"]
        }
        "/godown/datatable"(controller: "godown", action: "dataTable")
        "/godown/update/$id"(controller: "godown",action:"update")
        "/godown/delete/$id"(controller: "godown",action:"delete")


        //        Rack
        "/rack"(controller: "rack") {
            action = [GET: "index", POST: "save"]
        }
        "/rack/datatable"(controller: "rack", action: "dataTable")
        "/rack/update/$id"(controller:"rack",action:"update")
        "/rack/delete/$id"(controller: "rack",action:"delete")

        /*<-------------------------------------------Entity ------------------------------------------------->*/

        "/entity-register"(controller: "entityRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-register/add-entity-register"(controller: "entityRegister", action: 'addEntity')
        "/entity-register/update-entity-register/$id"(controller: "entityRegister", action: 'updateEntity')
        "/entity-register/datatable"(controller: "entityRegister", action: "dataTable")
        "/entity-register/update/$id"(controller:"entityRegister",action:"update")
        "/entity-register/delete/$id"(controller: "entityRegister",action:"delete")

        "/getentitytypebyId"(controller: 'entityRegister', action: 'getEntityTypeById')



        //  Entity Settings

        "/entity-settings"(controller: "entitySettings") {
            action = [GET: "index", POST: "save"]
        }
        "/entity-settings/datatable"(controller: "entitySettings", action: "dataTable")
        "/entity-settings/update/$id"(controller: "entitySettings",action:"update")
        "/entity-settings/delete/$id"(controller: "entitySettings",action:"delete")
        "/entity-settings/settings"(controller: 'entitySettings', action: 'settings')


//        Entity Config
        "/entity-config"(controller: 'entityConfig', action: 'saveEntityConfig')

//      User Register
        "/user-register"(controller: "userRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/user-register/add-user-register"(controller: "userRegister", action: 'addUser')
        "/user-register/update-user-register/$id"(controller: "userRegister", action: 'updateUser')
        "/user-register/datatable"(controller: "userRegister", action: "dataTable")
        "/user-register/update/$id"(controller:"userRegister",action:"update")
        "/user-register/delete/$id"(controller: "userRegister",action:"delete")


        //      Customer Group register
        "/customer-group-register"(controller: "customerGroup") {
            action = [GET: "index", POST: "save"]
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/customer-group-register/datatable"(controller: "customerGroup", action: "dataTable")
        "/customer-group-register/update/$id"(controller:"customerGroup",action:"update")
        "/customer-group-register/delete/$id"(controller: "customerGroup",action:"delete")


        //     Day End Master
        "/day-end-master"(controller: "dayEnd") {
            action = [GET: "index", POST: "save"]
        }
//        "/customer-group-register/add-customer-group"(controller: "customerGroup", action: 'addUser')
//        "/customer-group-register/update-customer-group/$id"(controller: "userRegister", action: 'updateUser')
        "/day-end-master/datatable"(controller: "dayEnd", action: "dataTable")
        "/day-end-master/update/$id"(controller:"dayEnd",action:"update")
        "/day-end-master/delete/$id"(controller: "dayEnd",action:"delete")


        //    Financial Year Master
        "/financial-year-master"(controller: "financialYear") {
            action = [GET: "index", POST: "save"]
        }
        "/financial-year-master/datatable"(controller: "financialYear", action: "dataTable")
        "/financial-year-master/update/$id"(controller:"financialYear",action:"update")
        "/financial-year-master/delete/$id"(controller: "financialYear",action:"delete")

        //   Region Register
        "/region-master"(controller: "regionMaster") {
            action = [GET: "index", POST: "save"]
        }
        "/region-master/datatable"(controller: "regionMaster", action: "dataTable")
        "/region-master/update/$id"(controller:"regionMaster",action:"update")
        "/region-master/delete/$id"(controller: "regionMaster",action:"delete")


        //   Route Register
        "/route-regitser"(controller: "route") {
            action = [GET: "index", POST: "save"]
        }
        "/route-regitser/datatable"(controller: "route", action: "dataTable")
        "/route-regitser/update/$id"(controller:"route",action:"update")
        "/route-regitser/delete/$id"(controller: "route",action:"delete")

        //   Account Register
        "/accounts"(controller: "accountRegister") {
            action = [GET: 'index', POST: 'save']
        }
        "/accounts-list"(controller: "accountRegister", action: 'accountsList')
        "/accounts/datatable"(controller: "accountRegister", action: 'dataTable')
        "/accounts/update/$id"(controller:"accountRegister",action:"update")
        "/accounts/delete/$id"(controller: "accountRegister",action:"delete")

        //   Role
        "/role"(controller: "role") {
            action = [GET: "index", POST: "save"]
        }
        "/role/datatable"(controller: "role", action: "dataTable")
        "/role/update/$id"(controller:"role",action:"update")
        "/role/delete/$id"(controller: "role",action:"delete")

        //   Rule
        "/rule"(controller: "rule") {
            action = [GET: "index", POST: "save"]
        }
        "/rule/datatable"(controller: "rule", action: "dataTable")
        "/rule/update/$id"(controller:"rule",action:"update")
        "/rule/delete/$id"(controller: "rule",action:"delete")


        //   Tax
        "/tax"(controller: "tax") {
            action = [GET: "index", POST: "save"]
        }
        "/tax/datatable"(controller: "tax", action: "dataTable")
        "/tax/update/$id"(controller:"tax",action:"update")
        "/tax/delete/$id"(controller: "tax",action:"delete")
        "/tax/showtax/$id"(controller: 'tax', action: 'showTaxForReturn')


        //   Territory
        "/territory"(controller: "territory") {
            action = [GET: "index", POST: "save"]
        }
        "/territory/datatable"(controller: "territory", action: "dataTable")
        "/territory/update/$id"(controller:"territory",action:"update")
        "/territory/delete/$id"(controller: "territory",action:"delete")

        //   Terms Conditions
        "/terms-conditions"(controller: "termsCondition") {
            action = [GET: "index", POST: "save"]
        }
        "/terms-conditions/datatable"(controller: "termsCondition", action: "dataTable")
        "/terms-conditions/update/$id"(controller:"termsCondition",action:"update")
        "/terms-conditions/delete/$id"(controller: "termsCondition",action:"delete")


        //   Series
        "/series"(controller: "series") {
            action = [GET: "index", POST: "save"]
        }
        "/series/$id"(controller: "series", action: "getSeriesById")
        "/series/datatable"(controller: "series", action: "dataTable")
        "/series/update/$id"(controller:"series",action:"update")
        "/series/delete/$id"(controller: "series",action:"delete")


        //   Service type
        "/service-type"(controller: "serviceType") {
            action = [GET: "index", POST: "save"]
        }
        "/service-type/datatable"(controller: "serviceType", action: "dataTable")
        "/service-type/update/$id"(controller:"serviceType",action:"update")
        "/service-type/delete/$id"(controller: "serviceType",action:"delete")

        /*<-------------------------------------------Accounts------------------------------------------------->*/
        "/credit-jv"(controller: "creditJv",action:"index")
        "/credit-jv/save"(controller: "creditJv",action:"saveCreditJv")
        "/credit-jv/approval"(controller: "creditJv",action:"approval")
        "/credit-jv/approval-table"(controller: "creditJv",action:"dataTable")
        "/credit-jv/approve"(controller: "creditJv",action:"approveReject")
        "/debit-jv"(controller: "debitJv",action:"index")
        "/debit-jv/save"(controller: "debitJv",action:"saveDebitJv")
        "/debit-jv/approval"(controller: "debitJv",action:"approval")
        "/debit-jv/approval-table"(controller: "debitJv",action:"dataTable")
        "/debit-jv/approve"(controller: "debitJv",action:"approveReject")

        /*<-------------------------------------------Product------------------------------------------------->*/
//      Product Register
        "/product"(controller: "product") {
            action = [GET: "index", POST: "save"]
        }
        "/product/add-product"(controller: "product", action: 'addProduct')
        "/product/division/$id"(controller: "product", action: 'getProductByDivision')
        "/product/series/$id"(controller: "product", action: 'getProductBySeries')
        "/product/update-product/$id"(controller: "product", action: 'updateProduct')
        "/product/datatable"(controller: "product", action: "dataTable")
        "/product/update/$id"(controller:"product",action:"update")
        "/product/delete/$id"(controller: "product",action:"delete")


        //      Division
        "/division"(controller: "division") {
            action = [GET: "index", POST: "save"]
        }
        "/division/datatable"(controller: "division", action: "dataTable")
        "/division/update/$id"(controller:"division",action:"update")
        "/division/delete/$id"(controller: "division",action:"delete")


        // Product Category
        "/product-category"(controller: "productCategory") {
            action = [GET: "index", POST: "save"]
        }
        "/product-category/datatable"(controller: "productCategory", action: "dataTable")
        "/product-category/update/$id"(controller:"productCategory",action:"update")
        "/product-category/delete/$id"(controller: "productCategory",action:"delete")

        // Product Schedule
        "/product-schedule"(controller: "productSchedule") {
            action = [GET: "index", POST: "save"]
        }
        "/product-schedule/datatable"(controller: "productSchedule", action: "dataTable")
        "/product-schedule/update/$id"(controller:"productSchedule",action:"update")
        "/product-schedule/delete/$id"(controller: "productSchedule",action:"delete")


        // Product Composition
        "/product-composition"(controller: "composition") {
            action = [GET: "index", POST: "save"]
        }
        "/product-composition/datatable"(controller: "composition", action: "dataTable")
        "/product-composition/update/$id"(controller:"composition",action:"update")
        "/product-composition/delete/$id"(controller: "composition",action:"delete")


        // Product Type
        "/product-type"(controller: "productType") {
            action = [GET: "index", POST: "save"]
        }
        "/product-type/datatable"(controller: "productType", action: "dataTable")
        "/product-type/update/$id"(controller:"productType",action:"update")
        "/product-type/delete/$id"(controller: "productType",action:"delete")


        // Product group
        "/product-group"(controller: "productGroup") {
            action = [GET: "index", POST: "save"]
        }
        "/product-group/datatable"(controller: "productGroup", action: "dataTable")
        "/product-group/update/$id"(controller:"productGroup",action:"update")
        "/product-group/delete/$id"(controller: "productGroup",action:"delete")


        //Unit Type
        "/unit-type"(controller: "unitType") {
            action = [GET: "index", POST: "save"]
        }
        "/unit-type/datatable"(controller: "unitType", action: "dataTable")
        "/unit-type/update/$id"(controller:"unitType",action:"update")
        "/unit-type/delete/$id"(controller: "unitType",action:"delete")


        //Division Group register
        "/division-group"(controller: "divisionGroup") {
            action = [GET: "index", POST: "save"]
        }
        "/division-group/datatable"(controller: "divisionGroup", action: "dataTable")
        "/division-group/update/$id"(controller:"divisionGroup",action:"update")
        "/division-group/delete/$id"(controller: "divisionGroup",action:"delete")


        //Product Class
        "/product-class"(controller: "productClass") {
            action = [GET: "index", POST: "save"]
        }
        "/product-class/datatable"(controller: "productClass", action: "dataTable")
        "/product-class/update/$id"(controller:"productClass",action:"update")
        "/product-class/delete/$id"(controller: "productClass",action:"delete")



        //Product cost range
        "/product-cost-range"(controller: "productCostRange") {
            action = [GET: "index", POST: "save"]
        }
        "/product-cost-range/datatable"(controller: "productCostRange", action: "dataTable")
        "/product-cost-range/update/$id"(controller:"productCostRange",action:"update")
        "/product-cost-range/delete/$id"(controller: "productCostRange",action:"delete")


        //Batch register
        "/batch-register"(controller: "batchRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/batch-register/datatable"(controller: "batchRegister", action: "dataTable")
        "/batch-register/update/$id"(controller:"batchRegister",action:"update")
        "/batch-register/delete/$id"(controller: "batchRegister",action:"delete")
        "/batch-register/product/$id"(controller: "batchRegister",action:"getByProduct")
        "/batch-register/batchesforpurchase/$id"(controller: "batchRegister",action:"getBatchesForPurchase")


        /*<-------------------------------------------Sales------------------------------------------------->*/

//        My Invoices
        "/sale-bill-list"(controller: "salebillDetails", action: 'saleBillList')
        "/sale-bill/datatable"(controller: "salebillDetails", action: 'dataTable')


//        Sale Retrun
        "/sale-return"(controller: "saleReturn") {
            action = [GET: "index", POST: 'saveSaleReturn']
        }
        "/sale-return/list"(controller: "saleReturn", action: "salesReturnList")
        "/sale-return/datatables"(controller: "saleReturn", action: "salesReturnDatatables")
        "/salebill/customer/$custid"(controller: 'saleReturn', action: 'getSaleBillByCustomer')
        "/getinvoicedetails"(controller:'saleReturn', action: 'getSaleInvByProducts')
        "/sale-return/print-invoice"(controller: "saleReturn", action: "printSaleReturn")
        "/sale-return/my-returns"(controller: "saleReturn", action: "salesReturnList")
        "/sale-return/datatables"(controller: "saleReturn", action: "salesReturnDatatables")
        "/sale-return/cancel-invoice"(controller: "saleReturn", action: "cancelReturns")

//        Purchase Return
        "/purchase-return"(controller: "purchaseReturn") {
            action = [GET: "index", POST: 'savePurchaseReturn']
        }
        "/purchase-bill/supplier/$supplierId"(controller: 'purchaseReturn', action: 'getPurchaseBillBySupplier')


        //Sale Entry
        "/sale-entry"(controller: "saleEntry") {
            action = [GET: "index", POST: 'saveSaleEntry']
        }
        "/edit-sale-entry"(controller: "saleEntry") {
            action = [GET: "editSaleBillDetails", POST: 'updateSaleBillDetails']
        }
        "/edit-sale-products"(controller: 'saleEntry'){
            action=[POST:'updateSaleProductDetails']
        }
        "/sale-product-details/sale-bill"(controller: 'saleEntry', action: 'getSaleProductDetailsByBill')

        "/sale-product-details/delete"(controller: 'saleEntry', action: 'deleteSaleProduct')

        "/sale-entry/print-invoice"(controller: "saleEntry", action: "printSaleInvoice")
        "/sale-entry/update/$id"(controller:"batchRegister",action:"update")
        "/sale-invoice"(controller: "saleEntry",action:"saleBill")
        "/sales/check-scheme"(controller: "saleEntry", action: "checkSchemeConfiguration")
        "/sale-entry/cancel-invoice"(controller: "saleEntry", action: "cancelInvoice")

//        Scheme Entry
        "/scheme-entry" (controller: "schemeEntry", action: "index")
        "/scheme-entry/datatable" (controller: "schemeEntry", action: "dataTable")
//        "/sale-return"(controller: "saleEntry",action:"saleRetrun")
        "/add-scheme-entry"(controller: "schemeEntry") {
            action = [GET: "addScheme", POST: 'saveScheme']
        }
        "/update-scheme-entry/$id"(controller: "schemeEntry") {
            action = [GET: "updateScheme", POST: 'update']
        }
        "/scheme-entry/delete/$id"(controller: "schemeEntry",action:"delete")


        "/credit-debit-settlement"(controller: "saleEntry",action:"crdDebS")
        "/debit-jv-print"(controller: "saleEntry",action:"DebJV")
        "/credit-jv-print"(controller: "saleEntry",action:"credJV")
        "/goods-sales-recipt"(controller: "saleEntry",action:"goodsSalesRecipt")
        "/payment-vocher"(controller: "saleEntry",action:"paymentVocher")



        "/saleproductdetailsbillandbatch"(controller: 'saleReturn', action: 'getSaleDetailsByProductAndBatch')

        //GTN
        "/gtn"(controller: "goodsTransferNote") {
            action = [GET: "index", POST: 'saveGtn']
        }
        "/gtn/cancel"(controller: 'goodsTransferNote', action: 'cancelGTN')



        //grn
        "/grn"(controller: 'goodsTransferNote', action: 'grn')
        "/grn/datatable"(controller: "goodsTransferNote",action:"dataTable")
        "/grn/approveGRN"(controller: 'goodsTransferNote', action: 'approveGRN')
        "/grn/print-grn"(controller: "goodsTransferNote", action: "printGRN")



        /*<-------------------------------------------Purchase Entry------------------------------------------------->*/


        "/purchase-entry"(controller: "purchaseEntry") {
            action = [GET: "index", POST: "savePurchaseEntry"]
        }
        "/purchase-retrun"(controller: 'purchaseEntry', action: 'purchaseReturn')
        "/purchase-entry/print-invoice"(controller: "purchaseEntry", action: "printPurchaseEntry")

        //Inventory
        "/stockbook"(controller: "stockBook"){
            action = [GET: "index", POST: "save"]
        }
        "/stockbook/save"(controller: "stockBook"){action = [POST: "save"]}
        "/stockbook/update/$id"(controller: "stockBook"){
            action = [POST: 'update']
        }
        "/stockbook/datatable"(controller: "stockBook",action:"dataTable")
        "/stockbook/product/$id"(controller: "stockBook",action:"getStocksOfProduct")
        "/stockbook/purchase/product/$id"(controller: "stockBook",action:"getStocksOfProductForPurchase")
        "/stockbook/purchase"(controller: "stockBook",action:"StockBookPurchase")
        "/stockbook/purchase/batch"(controller: 'stockBook', action: 'stockPurchase')
        "/stockbook/productreturn/$id"(controller: "stockBook",action:"getStocksOfProductSaleReturn")



//        Temp Stock Book
        "/tempstockbook"(controller: "stockBook") {
            action = [POST: 'tempStockBookSave']
        }
        "/tempstockbook/delete/$id"(controller: "stockBook") {
            action = [POST: 'deleteTempStock']
        }
        "/tempstockbook/product/$id"(controller: "stockBook",action:"getStocksOfProduct")
        "/tempstockbook/entity/$id"(controller: "stockBook",action:"getTempStocksOfEntity")
        "/tempstockbook/user/$id"(controller: "stockBook",action:"getTempStocksOfUser")

        "/tempstockbook/product/$id/batch/$batch"(controller: "stockBook",action:"getTempStocksOfProductAndBatch")
        "/tempstockbook/product/$id/"(controller: "stockBook",action:"getTempStocksOfProductAndBatch")


//        Stock Book
        "/stockbook"(controller: "stockBook") {action = [POST: 'stockBookSave']}
        "/stockbook/user/$id"(controller: "stockBook",action:"getStocksOfUser")
        "/stockbook/increase/"(controller: 'stockBook', action: 'stockIncrease')
        "/stockbook/product/$productId/batch/$batch"(controller: 'stockBook',action: 'stockByProductAndBatch')
//        Sale Order Entry
        "/sale-order-entry"(controller: "saleOrderEntry") {
            action = [GET: "index", POST: 'saveSaleOrder']
        }
        "/sale-order-entry/print-order"(controller: 'saleOrderEntry', action: 'printSaleOrder')
        "/sale-order-entry/datatable"(controller: 'saleOrderEntry', action: 'dataTable')
        "/sale-order-entry/my-orders"(controller: 'saleOrderEntry', action: 'saleOrderList')
        "/sale-order-entry/cancel"(controller: 'saleOrderEntry', action: 'cancelOrder')




        /*<-------------------------------------------Accounts------------------------------------------------->*/

        //Bank register
        "/bank-register"(controller: "bankRegister") {
            action = [GET: "index", POST: "save"]
        }
        "/bank-register/datatable"(controller: "bankRegister", action: "dataTable")
        "/bank-register/update/$id"(controller:"bankRegister",action:"update")
        "/bank-register/delete/$id"(controller: "bankRegister",action:"delete")
        "/bank-register/product/$id"(controller: "bankRegister",action:"getByProduct")

//        ReciptDetail
        "/recipt-list"(controller: 'reciptDetail', action: 'reciptList')
        "/recipt"(controller: "reciptDetail") {
            action = [GET: "index", POST: "save"]
        }
        "/recipt-list/datatable"(controller: "reciptDetail", action: "dataTable")
        "/add-recipt"(controller: "reciptDetail", action: 'addRecipt')
        "/print-recipt/$custid/recipt/$id"(controller: "reciptDetail", action: 'printRecipt')
        "/receipt-approval"(controller: 'reciptDetail', action: 'recieptApproval')



        "/salesettledvocher/$id"(controller: "reciptDetail", action: 'settledVocher')
        "/saleunsettledvocher/$id"(controller: "reciptDetail", action: 'unsettledVocher')
        "/creditsettledvocher/$id"(controller: "reciptDetail", action: 'creditSettledVocher')
        "/creditunsettledvocher/$id"(controller: "reciptDetail", action: 'creditUnsettledVocher')


        "/getbyentity/$id"(controller: "reciptDetail", action: 'getAllEntityById')
        "/getallunsettledbycustomer/$id"(controller: "reciptDetail", action: 'getAllUNSaleBillCustomerId')
        "/getallsettledbycustomer/$id"(controller: "reciptDetail", action: 'getAllSaleBillsettled')


        "/updatesalebalance"(controller: 'reciptDetail', action: 'updateSaleBalance')

        "/updatesalereturnbalance"(controller: 'reciptDetail', action: 'updateSaleReturnBalance')

        "/updatereciptlog"(controller: 'reciptDetail',action: 'updateRecieptLog')

        "/recipts/getallbilldetails"(controller: 'reciptDetail', action: 'getAllBillDetailsByCustomerId')

//        Payment Details
        "/payments"(controller: "paymentDetail") { action = [GET: "index", POST: "save"]}
        "/payments-list/datatable"(controller: "paymentDetail", action: "dataTable")
        "/payments-list"(controller: "paymentDetail", action: 'paymentList')
        "/print-payment/$custid/payment/$id"(controller: "paymentDetail", action: 'printPayment')
        "/payments/getallbilldetails"(controller: 'paymentDetail', action: 'getAllBillDetailsBySupplierId')


        //reports
        "/reports/sales/customerwise"(controller: "salesReport", action: "index")
        "/reports/sales/getcustomerwise"(controller: "salesReport", action: "salesCustomerWiseReport")
        "/reports/sales/datewise"(controller: "salesReport", action: "datewise")
        "/reports/sales/getdatewise"(controller: "salesReport", action: "salesDateWiseReport")
        "/reports/sales/areawise"(controller: "salesReport", action: "areawise")
        "/reports/sales/getareawise"(controller: "salesReport", action: "salesAreaWiseReport")
        "/reports/sales/areawisewithproducts"(controller: "salesReport", action: "areawiseWithProducts")
        "/reports/sales/getareawisewithproducts"(controller: "salesReport", action: "areawiseWithProductsReport")
        "/reports/sales/areawiseconsolidatedproducts"(controller: "salesReport", action: "areawiseConsolidatedProducts")
        "/reports/sales/getareawiseconsolidatedproducts"(controller: "salesReport", action: "areawiseConsolidatedProductsReport")
        "/reports/sales/consolidated"(controller: "salesReport", action: "consolidated")
        "/reports/sales/getconsolidated"(controller: "salesReport", action: "saleConsolidatedReport")
        "/reports/sales/productwise"(controller: "salesReport", action: "saleProductWise")
        "/reports/sales/getproductwise"(controller: "salesReport", action: "saleProductWiseReport")
        "/reports/sales/gstreport"(controller: "salesReport", action: "saleProductWiseReport")
        "/reports/sales/gstreport"(controller: "salesReport", action: "salesGstReport")
        "/reports/sales/getgstreport"(controller: "salesReport", action: "getSalesGstReport")

    }
}
