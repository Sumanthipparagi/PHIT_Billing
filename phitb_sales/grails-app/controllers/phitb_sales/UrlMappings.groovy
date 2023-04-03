package phitb_sales

import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

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

        group "/api/v1.0/sales", {

            //Credit Debit Details
            "/creditdebitdetails(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/creditdebitdetails/datatable(.$format)?"(controller: 'creditDebitDetails') { action = [GET: 'dataTable'] }
            "/creditdebitdetails/$id(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/creditdebitdetailsbydays/$days(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'getAllByDays']
            }

            //Credit Debit Settlement
            "/creditdebitsettlement(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/creditdebitsettlement/datatable(.$format)?"(controller: 'creditDebitSettlement') { action = [GET: 'dataTable'] }
            "/creditdebitsettlement/$id(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/creditdebitsettlementbydays/$days(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'getAllByDays']
            }
            "/save-crdb-settlement"(controller: 'creditDebitSettlement', action: 'saveCrDbSettlement')
            "/getcrdbsettlement"(controller: 'creditDebitSettlement', action: 'getCrDbDetails')

            //Old Software Return Details
            "/oldsoftwarereturndetails(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/oldsoftwarereturndetails/datatable(.$format)?"(controller: 'oldSoftwareReturnDetails') { action = [GET: 'dataTable'] }
            "/oldsoftwarereturndetails/$id(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/oldsoftwarereturndetailsbydays/$days(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'getAllByDays']
            }

            //Old Software sale Details
            "/oldsoftwaresaledetails(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/oldsoftwaresaledetails/datatable(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET:
                                  'dataTable']
            }
            "/oldsoftwaresaledetails/$id(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/oldsoftwaresaledetailsbydays/$days(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'getAllByDays']
            }

            //Sale Bill Details
            "/salebilldetails(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/draftsalebilldetails(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getDraftBillById']
            }

            "/salebilldetails/paymentpending(.$format)?"(controller: 'saleBillDetails', action: 'getPaymentPendingBills')


            "/salebilldetails/pendingirn(.$format)?"(controller: 'saleBillDetails') {
                action = [POST: 'getByPendingIrnAndEntity']
            }
            "/salebilldetails/datatable(.$format)?"(controller: 'saleBillDetails') { action = [GET: 'dataTable'] }
            "/salebilldetails/cancel(.$format)?"(controller: 'saleBillDetails') { action = [POST: 'cancelSaleBill'] }
            "/salebilldetails/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/salebilldetailsbydays/$days(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllByDays']
            }
            "/salebillunsettledbycustomer/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllUnsettledByCustId']
            }
            "/salebillsettledbycustomer/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllsettledByCustId']
            }

            "/salebillbycustomer/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllByCustomerId']
            }

            "/salebillbycustomerstartdate/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllByCustomerByStartDate']
            }

            "/salebillbydaterange(.$format)?"(controller: 'saleBillDetails', action: 'getByDateRangeAndEntity')

            "/salebillbydaterangecustomer(.$format)?"(controller: 'saleBillDetails', action: 'getByDateRangeAndCustomerId')


            "/salebilldetails/getrecent(.$format)?"(controller: 'saleBillDetails', action: 'getRecentByFinancialYearAndEntity')

            "/updatemassdiscount(.$format)?"(controller: 'saleProductDetails', action: 'updateMassDiscount')

            "/setpaymentstatus/$id/type/$paid(.$format)?"(controller: 'saleBillDetails') {
                action = [POST: 'updatePaymentStatus']
            }

            "/updatebalancebyid/id/$id/balance/$balance/status/$status"(controller: 'saleBillDetails')
                    { action = [POST: 'updateBalance'] }
            "/updatebalanceandadjustcredits(.$format)?"(controller: 'saleBillDetails') { action = [POST: 'adjustCredits'] }


            //      Sample Conversion
            "/sampleconversion(.$format)?"(controller: 'sampleConversion') {
                action = [GET: 'index', POST: 'save']
            }

            "/sampleconversion/getrecent"(controller: 'sampleConversion', action: 'getRecentByFinancialYearAndEntity')

            "/sampleconversion/datatable(.$format)?"(controller: 'sampleConversion') { action = [GET: 'dataTable'] }
            "/sampleconversion/cancel(.$format)?"(controller: 'sampleConversion') { action = [POST: 'cancelSampleBill'] }
            "/sampleconversion/$id(.$format)?"(controller: 'sampleConversion') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sampleconversion/save-invoice"(controller: 'sampleConversion', action: 'saveInvoice')
            "/sampleconversionbydays/$days(.$format)?"(controller: 'sampleConversion') {
                action = [GET: 'getAllByDays']
            }

            "/sampleconversionbycustomer/$id(.$format)?"(controller: 'sampleConversion') {
                action = [GET: 'getAllByCustomerId']
            }

            "/sampleconversionbydaterange(.$format)?"(controller: 'sampleConversion', action: 'getByDateRangeAndEntity')


            //      Sample Conversion  details
            "/sampleconversiondetails(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sampleconversiondetails/savelist(.$format)?"(controller: 'sampleConversionDetails', action: 'saveList')
            "/sampleconversiondetails/bill/$id(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBill']
            }

            "/sampleconversiondetailslist/bill/$salebillsIds(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/sampleconversiondetails/datatable(.$format)?"(controller: 'sampleConversionDetails') { action = [GET: 'dataTable'] }
            "/sampleconversiondetails/$id(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "sampleconversiondetailsbydays/$days(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getAllByDays']
            }

            "/sampleconversiondetailsbyproductId(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getSaleProductDetailsbyProductId']
            }

            "/sampleconversiondetailsbillandbatch(.$format)?"(controller: 'sampleConversionDetails', action:
                    'getSampleConversionDetailsbyProductId')

            "/sampleconversiondetailsbydaterange(.$format)?"(controller: 'sampleConversionDetails', action:
                    'getSampleConversion')


//            Sample Conversion Logs
            "/sampleconversionlogs(.$format)?"(controller: 'sampleConversionLogs') {
                action = [GET: 'index', POST: 'save']
            }
            "/sampleconversionlogs/datatable(.$format)?"(controller: 'sampleConversion') { action = [GET: 'dataTable'] }
            "/sampleconversionlogs/get(.$format)?"(controller: 'sampleConversionLogs') {
                action = [GET: 'getSampleConversionLog']
            }

            //Sale Product Details
            "/saleproductdetails(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/saleproductdetails/savelist(.$format)?"(controller: 'saleProductDetails', action: 'saveList')
            "/saleproductdetails/bill/$id(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBill']
            }

            "/saleproductdetailslist/bill/$salebillsIds(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/saleproductdetails/datatable(.$format)?"(controller: 'saleProductDetails') { action = [GET: 'dataTable'] }
            "/saleproductdetails/$id(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saleproductdetailsbydays/$days(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getAllByDays']
            }

            "/saleproductdetailsbyproductId(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getSaleProductDetailsbyProductId']
            }

            "/saleproductdetailsbillandbatch(.$format)?"(controller: 'saleProductDetails', action:
                    'getSaleProductDetailsbybatchAndBill')


//            Sale Order Product Details
            "/saleorderproductdetails(.$format)?"(controller: 'saleOrderProductDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/saleorderproductdetails/bill/$id(.$format)?"(controller: 'saleOrderProductDetails') {
                action = [GET: 'getSaleOrderProductDetailsOfBill']
            }

            "/saleorderproductdetailslist/bill/$salebillsIds(.$format)?"(controller: 'saleOrderProductDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/saleorderproductdetails/datatable(.$format)?"(controller: 'saleOrderProductDetails') { action = [GET: 'dataTable'] }
            "/saleorderproductdetails/$id(.$format)?"(controller: 'saleOrderProductDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saleorderproductdetailsbydays/$days(.$format)?"(controller: 'saleOrderProductDetails') {
                action = [GET: 'getAllByDays']
            }

//            "/saleorderproductdetailsbyproductId(.$format)?"(controller: 'saleOrderProductDetails') {
//                action = [GET: 'getSaleOrderProductDetailsbyProductId']
//            }

            "/saleorderproductdetailsbillandbatch(.$format)?"(controller: 'saleOrderProductDetails', action:
                    'getSaleProductDetailsbybatchAndBill')

            "/saleorderbydaterange(.$format)?"(controller: 'saleOrderEntry', action: 'getByDateRangeAndEntity')
            "/saleorderbydaterangecustomer(.$format)?"(controller: 'saleOrderEntry', action: 'getByDateRangeAndCustomerId')


//            Convert to Sale Entry
            "/convert-to-sale-entry(.$format)?"(controller: 'saleOrderEntry', action: 'convertToSaleEntry')


////            Goods transfer  note
//            "/gtn(.$format)?"(controller: 'goodsTransferNote') {
//                action = [GET: 'index', POST:
//                        'save']
//            }
//            "/gtn/datatable(.$format)?"(controller: 'goodsTransferNote') {action = [GET: 'dataTable']}
//            "/gtn/$id(.$format)?"(controller: 'goodsTransferNote') {
//                action = [GET: 'show', PUT: 'update', DELETE:
//                        'delete']
//            }
//            "/gtn/getrecent(.$format)?"(controller: 'goodsTransferNote', action: 'getRecentByFinancialYearAndEntity')
//            "/gtn/cancel(.$format)?"(controller: 'goodsTransferNote') {action = [POST: 'cancelGTN']}
//
//
//            //            Goods transfer  note products
//            "/gtn-products(.$format)?"(controller: 'goodsTransferNoteProduct') {
//                action = [GET: 'index', POST:
//                        'save']
//            }
//            "/gtn-products/datatable(.$format)?"(controller: 'goodsTransferNoteProduct') {action = [GET: 'dataTable']}
//            "/gtn-products/$id(.$format)?"(controller: 'goodsTransferNoteProduct') {
//                action = [GET: 'show', PUT: 'update', DELETE: 'delete']
//            }


            //Sale Transaction Log
            "/saletransactionlog(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/saletransactionlog/datatable(.$format)?"(controller: 'saleTransactionLog') { action = [GET: 'dataTable'] }
            "/saletransactionlog/$id(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saletransactionlogbydays/$days(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'getAllByDays']
            }

            //Sale Transportation Details
            "/saletransportationdetails(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/saletransportationdetails/datatable(.$format)?"(controller: 'saleTransportationDetails') { action = [GET: 'dataTable'] }
            "/saletransportationdetails/$id(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saletransportationdetails/billid"(controller: 'saleTransportationDetails', action: 'getByBillId')
            "/saletransportationdetailsbydays/$days(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'getAllByDays']
            }


            //Sample Conversion Details
            "/sampleconversiondetails(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/sampleconversiondetails/datatable(.$format)?"(controller: 'sampleConversionDetails') { action = [GET: 'dataTable'] }
            "/sampleconversiondetails/$id(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sampleconversiondetailsbydays/$days(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getAllByDays']
            }
            "/sampleconversiondetails/bill/$id(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getSampleConversionDetailsByBillId']
            }

            //Scheme Configuration
            "/schemeconfiguration(.$format)?"(controller: 'schemeConfiguration') {
                action = [GET: 'index', POST: 'save']
            }
            "/schemeconfiguration/datatable(.$format)?"(controller: 'schemeConfiguration') { action = [GET: 'dataTable'] }
            "/schemeconfiguration/$id(.$format)?"(controller: 'schemeConfiguration') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/schemeconfigurationbydays/$days(.$format)?"(controller: 'schemeConfiguration') {
                action = [GET: 'getAllByDays']
            }
            "/schemeconfiguration/product/$productId/batch/$batchNumber(.$format)?"(controller: 'schemeConfiguration', action: 'getByProductBatchNumber')

//            Sale order Details
            "/saleorderdetails(.$format)?"(controller: 'saleOrderEntry') {
                action = [GET: 'index', POST:
                        'saveSaleOrder']
            }
            "/saleorderdetails/$id(.$format)?"(controller: 'saleOrderEntry') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }

            "/saleorderdetails/getrecent(.$format)?"(controller: 'saleOrderEntry') {
                action = [GET: 'getRecentByFinancialYearAndEntity']
            }
            "/saleorderdetails/datatable(.$format)?"(controller: 'saleOrderEntry') { action = [GET: 'dataTable'] }
            "/saleorderdetails/cancel(.$format)?"(controller: 'saleOrderEntry') { action = [POST: 'cancelSaleOrder'] }

            //Sale return
            "/salereturn(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'index', POST: 'saveSaleReturn']
            }
            "/salereturn/$id(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/salereturn/datatable(.$format)?"(controller: 'saleReturn') { action = [GET: 'dataTable'] }
            "/saleretrununsettledbycustomer/$id(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'getAllUnsettledByCustId']
            }
            "/saleretrunsettledbycustomer/$id(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'getAllsettledByCustId']
            }
            "/salereturn/adjustment(.$format)?"(controller: 'saleReturn') { action = [POST: 'adjustSaleReturn'] }
            "/salereturn/adjustment-details/$docId/$docType(.$format)?"(controller: 'saleReturn') { action = [GET: 'getSaleReturnAdjustmentDetailsByDocId'] }
            "/salereturn/adjustment-details-startdate/$docId/$docType(.$format)?"(controller: 'saleReturn') {
                action =
                        [GET: 'getSaleReturnAdjustmentDetailsByDocIdStartDate']
            }


            "/setsalereturnstatus/$id/type/$type/adj/$adj(.$format)?"(controller: 'saleReturn') {
                action = [POST: 'updateStatus']
            }
            "/updatereturnbalancebyid/id/$id/balance/$balance/status/$status"(controller: 'saleReturn')
                    { action = [POST: 'updateBalance'] }

            "/salereturnbycustomer(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'getAllByCustomerId']
            }

            "/salereturnbycustomerstartdate(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'getAllByCustomerIdStartDate']
            }

            "/salereturn/getrecent(.$format)?"(controller: 'saleReturn') {
                action = [GET: 'getRecentByFinancialYearAndEntity']
            }

            "/salereturn/datatable(.$format)?"(controller: 'saleReturn') { action = [GET: 'dataTable'] }

            "/salereturn/cancel(.$format)?"(controller: 'saleReturn') { action = [POST: 'cancelSaleReturn'] }

            "/salereturnbydaterange(.$format)?"(controller: 'saleReturn', action: 'getByDateRangeAndEntity')

            "/salereturnbydaterangecustomer(.$format)?"(controller: 'saleReturn', action: 'getByDateRangeAndCustomerId')


            //Sale Return Details
            "/salereturndetails(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/salereturndetails/bill/$id(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBill']
            }

            "/salereturndetailslist/bill/$salebillsIds(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/salereturndetails/datatable(.$format)?"(controller: 'saleReturnDetails') { action = [GET: 'dataTable'] }
            "/salereturndetails/$id(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/salereturndetailsbydays/$days(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'getAllByDays']
            }

            "/salereturndetails/bill(.$format)?"(controller: 'saleReturnDetails') {
                action = [GET: 'getSaleReturnDetailsById']
            }
            "/getsalereturndetailsby-product-batch-salebill(.$format)?"(controller: 'saleReturnDetails', action: 'getSaleReturnDetailsByProductBatchSaleBill')

            //Temp User Log
            "/tempuserlog(.$format)?"(controller: 'tempUserLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/tempuserlog/datatable(.$format)?"(controller: 'tempUserLog') { action = [GET: 'dataTable'] }
            "/tempuserlog/$id(.$format)?"(controller: 'tempUserLog') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/tempuserlogbydays/$days(.$format)?"(controller: 'tempUserLog') {
                action = [GET: 'getAllByDays']
            }

            "/reports/customerwise(.$format)?"(controller: 'reports', action: 'getCustomerWiseBillDetails')
            "/reports/datewise(.$format)?"(controller: 'reports', action: 'getDateWiseBillDetails')
            "/reports/areawise(.$format)?"(controller: 'reports', action: 'getAreaWiseBillDetails')
            "/reports/consolidated(.$format)?"(controller: 'reports', action: 'getConsolidatedBillDetails')
            "/reports/salesgst(.$format)?"(controller: 'reports', action: 'getSalesGSTReport')
            "/reports/creditnotegst(.$format)?"(controller: 'reports', action: 'getCreditNoteGSTReport')
            "/reports/salesreturn-areawise(.$format)?"(controller: 'reports', action: 'getSaleReturnAreaWiseBillDetails')
            "/reports/statistics(.$format)?"(controller: 'reports', action: 'getSalesStats')
            "/reports/salesinfo(.$format)?"(controller: 'reports', action: 'getSalesInfoTillDate')
            "/reports/fsn(.$format)?"(controller: 'reports', action: 'getFSN')

            "/salebilldetails/updateirn(.$format)?"(controller: 'saleBillDetails', action: 'updateIRNDetails')
            "/salebilldetails/save-invoice(.$format)?"(controller: 'saleBillDetails', action: 'saveInvoice')
            "/salebilldetails/update-invoice/$id(.$format)?"(controller: 'saleBillDetails', action: 'updateInvoice')
            "/delete-drafts-sale-bill(.$format)"(controller: 'saleBillDetails', action: 'deleteAllDraftsSaleBill')
            "/drafts-sale-bill(.$format)"(controller: 'saleBillDetails', action: 'getAllDraftSaleBillsByEntityAndUser')


            //Goods Transfer Note
            "/gtn(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'index', POST:
                        'saveGTN']
            }
            "/gtnbydaterange(.$format)?"(controller: "goodsTransferNote", action: "getGTNByDateRange")
            "/gtnbydaterangecustomer(.$format)?"(controller: "goodsTransferNote", action: "getGTNByDateRangeCustomer")

            "/draftgtn(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getDraftBillById']
            }
            "/gtn/getrecent(.$format)?"(controller: 'goodsTransferNote', action: 'getRecentByFinancialYearAndEntity')

            "/gtn/datatable(.$format)?"(controller: 'goodsTransferNote') { action = [GET: 'dataTable'] }

            "/gtn/cancel(.$format)?"(controller: 'goodsTransferNote') { action = [POST: 'cancelGTN'] }

            "/gtn/approve(.$format)?"(controller: 'goodsTransferNote', action: 'approveGTN')

            "/gtn/$id(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/gtnbydays/$days(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getAllByDays']
            }
            "/gtnunsettledbycustomer/$id(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getAllUnsettledByCustId']
            }
            "/gtnsettledbycustomer/$id(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getAllsettledByCustId']
            }

            "/gtnbycustomer(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getAllByCustomerId']
            }

            "/updategtnbalancebyid/id/$id/balance/$balance/status/$status"(controller: 'goodsTransferNote')
                    { action = [POST: 'updateBalance'] }


            //GTN Product Details
            "/gtnproduct(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/gtnproduct/savelist(.$format)?"(controller: 'goodsTransferNoteProduct', action: 'saveList')
            "/gtnproduct/gtn/$id(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'getByGtn']
            }

            "/gtnproductlist/bill/$salebillsIds(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/gtnproduct/datatable(.$format)?"(controller: 'goodsTransferNoteProduct') { action = [GET: 'dataTable'] }
            "/gtnproduct/$id(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/gtnproductbydays/$days(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'getAllByDays']
            }

            "/gtnproductbyproductId(.$format)?"(controller: 'goodsTransferNoteProduct') {
                action = [GET: 'getGoodsTransferNoteProductbyProductId']
            }

            "/gtnproductbillandbatch(.$format)?"(controller: 'goodsTransferNoteProduct', action:
                    'getGoodsTransferNoteProductbybatchAndBill')


            //Delivery Challan
            "/deliverychallan(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'index', POST:
                        'saveDeliveryChallan']
            }
            "/draftdeliverychallan(.$format)?"(controller: 'goodsTransferNote') {
                action = [GET: 'getDraftBillById']
            }
            "/deliverychallan/getrecent(.$format)?"(controller: 'deliveryChallan', action: 'getRecentByFinancialYearAndEntity')

            "/deliverychallan/datatable(.$format)?"(controller: 'deliveryChallan') { action = [GET: 'dataTable'] }

            "/deliverychallan/cancel(.$format)?"(controller: 'deliveryChallan') { action = [POST: 'cancelDeliveryChallan'] }

            "/deliverychallan/approve(.$format)?"(controller: 'deliveryChallan', action: 'approveDeliveryChallan')

            "/deliverychallan/$id(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/deliverychallanbydays/$days(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'getAllByDays']
            }
            "/gtnunsettledbycustomer/$id(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'getAllUnsettledByCustId']
            }
            "/deliverychallansettledbycustomer/$id(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'getAllsettledByCustId']
            }

            "/deliverychallanbycustomer(.$format)?"(controller: 'deliveryChallan') {
                action = [GET: 'getAllByCustomerId']
            }

            "/updatedeliverychallanbalancebyid/id/$id/balance/$balance/status/$status"(controller: 'goodsTransferNote')
                    { action = [POST: 'updateBalance'] }

            "/deliverychallanbydaterange(.$format)?"(controller: 'deliveryChallan', action: 'getByDateRangeAndEntity')
            "/deliverychallanbydaterangecustomer(.$format)?"(controller: 'deliveryChallan', action: 'getByDateRangeAndCustomerId')


            //GTN Product Details
            "/deliverychallanproduct(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'index', POST: 'save']
            }
            "/deliverychallanproduct/savelist(.$format)?"(controller: 'deliveryChallanProduct', action: 'saveList')
            "/deliverychallanproduct/deliverychallan/$id(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'getByDeliveryChallan']
            }

            "/deliverychallanproductlist/bill/$salebillsIds(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/deliverychallanproduct/datatable(.$format)?"(controller: 'deliveryChallanProduct') { action = [GET: 'dataTable'] }
            "/deliverychallanproduct/$id(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/deliverychallanproductbydays/$days(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'getAllByDays']
            }

            "/deliverychallanproductbyproductId(.$format)?"(controller: 'deliveryChallanProduct') {
                action = [GET: 'getGoodsTransferNoteProductbyProductId']
            }

            "/deliverychallanproductbillandbatch(.$format)?"(controller: 'deliveryChallanProduct', action:
                    'getGoodsTransferNoteProductbybatchAndBill')


//            Stock Adjustment
            "/stockadjustmentdetails(.$format)?"(controller: 'stockAdjustmentDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/stockadjustmentdetails/datatable(.$format)?"(controller: 'stockAdjustmentDetails') { action = [GET: 'dataTable'] }
            "/stockadjustmentdetails/get(.$format)?"(controller: 'stockAdjustmentDetails') { action = [GET: 'getByDateRange'] }

            "/save-retailer-entry"(controller: 'saleBillDetails', action: 'saveRetailerInvoice')

        }
    }
}
