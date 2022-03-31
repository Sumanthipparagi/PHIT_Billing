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
            "/creditdebitdetails/datatable(.$format)?"(controller: 'creditDebitDetails') {action = [GET: 'dataTable']}
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
            "/creditdebitsettlement/datatable(.$format)?"(controller: 'creditDebitSettlement') {action = [GET: 'dataTable']}
            "/creditdebitsettlement/$id(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/creditdebitsettlementbydays/$days(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'getAllByDays']
            }

            //Old Software Return Details
            "/oldsoftwarereturndetails(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/oldsoftwarereturndetails/datatable(.$format)?"(controller: 'oldSoftwareReturnDetails') {action = [GET: 'dataTable']}
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
            "/oldsoftwaresaledetails/datatable(.$format)?"(controller: 'oldSoftwareSaleDetails') {action = [GET:
                                                                                                                         'dataTable']}
            "/oldsoftwaresaledetails/$id(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/oldsoftwaresaledetailsbydays/$days(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'getAllByDays']
            }

            //Reason Master
            "/reasonmaster(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/reasonmaster/datatable(.$format)?"(controller: 'reasonMaster') {action = [GET: 'dataTable']}
            "/reasonmaster/$id(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/reasonmasterbydays/$days(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'getAllByDays']
            }

            //Sale Bill Details
            "/salebilldetails(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/salebilldetails/datatable(.$format)?"(controller: 'saleBillDetails') {action = [GET: 'dataTable']}
            "/salebilldetails/cancel(.$format)?"(controller: 'saleBillDetails') {action = [POST: 'cancelSaleBill']}
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

//            //Sale Order entry Controller
//            "/salebilldetails(.$format)?"(controller: 'saleBillDetails') {
//                action = [GET: 'index', POST:
//                        'save']
//            }
//            "/salebilldetails/datatable(.$format)?"(controller: 'saleBillDetails') {action = [GET: 'dataTable']}
//            "/salebilldetails/$id(.$format)?"(controller: 'saleBillDetails') {
//                action = [GET: 'show', PUT: 'update', DELETE:
//                        'delete']
//            }
//            "/salebilldetailsbydays/$days(.$format)?"(controller: 'saleBillDetails') {
//                action = [GET: 'getAllByDays']
//            }
            "/salebilldetails/getrecent(.$format)?"(controller: 'saleBillDetails', action: 'getRecentByFinancialYearAndEntity')

            "/setpaymentstatus/$id/type/$type(.$format)?"(controller: 'saleBillDetails') {
                action = [POST: 'updatePaymentStatus']
            }

            //Sale Product Details
            "/saleproductdetails(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/saleproductdetails/bill/$id(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBill']
            }

            "/saleproductdetailslist/bill/$salebillsIds(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getSaleProductDetailsOfSaleBillList']
            }

            "/saleproductdetails/datatable(.$format)?"(controller: 'saleProductDetails') {action = [GET: 'dataTable']}
            "/saleproductdetails/$id(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saleproductdetailsbydays/$days(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getAllByDays']
            }

            //Sale Transaction Log
            "/saletransactionlog(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/saletransactionlog/datatable(.$format)?"(controller: 'saleTransactionLog') {action = [GET: 'dataTable']}
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
            "/saletransportationdetails/datatable(.$format)?"(controller: 'saleTransportationDetails') {action = [GET: 'dataTable']}
            "/saletransportationdetails/$id(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/saletransportationdetailsbydays/$days(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'getAllByDays']
            }
            //Sample Conversion Details
            "/sampleconversiondetails(.$format)?"(controller:'sampleConversionDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/sampleconversiondetails/datatable(.$format)?"(controller:'sampleConversionDetails') {action = [GET: 'dataTable']}
            "/sampleconversiondetails/$id(.$format)?"(controller:'sampleConversionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sampleconversiondetailsbydays/$days(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getAllByDays']
            }

            //Scheme Configuration
            "/schemeconfiguration(.$format)?"(controller:'schemeConfiguration') {
                action = [GET: 'index', POST: 'save']
            }
            "/schemeconfiguration/datatable(.$format)?"(controller:'schemeConfiguration') {action = [GET: 'dataTable']}
            "/schemeconfiguration/$id(.$format)?"(controller:'schemeConfiguration') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/schemeconfigurationbydays/$days(.$format)?"(controller: 'schemeConfiguration') {
                action = [GET: 'getAllByDays']
            }
            "/schemeconfiguration/product/$productId/batch/$batchNumber(.$format)?"(controller:'schemeConfiguration', action: 'getByProductBatchNumber')

//            Sale order Details
            "/saleorderdetails(.$format)?"(controller: 'saleOrderEntry') {
                action = [GET: 'index', POST:
                        'save']
            }


            //Sale return details
            "/salereturndetails(.$format)?"(controller:'saleReturnDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/salereturndetails/$id(.$format)?"(controller:'saleReturnDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }


            //Temp User Log
            "/tempuserlog(.$format)?"(controller:'tempUserLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/tempuserlog/datatable(.$format)?"(controller:'tempUserLog') {action = [GET: 'dataTable']}
            "/tempuserlog/$id(.$format)?"(controller:'tempUserLog') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/tempuserlogbydays/$days(.$format)?"(controller: 'tempUserLog') {
                action = [GET: 'getAllByDays']
            }
        }
    }
}
