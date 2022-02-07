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

        group "/api/v1.0", {

            //Credit Debit Details
            "/sales/creditdebitdetails(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/creditdebitdetails/datatable(.$format)?"(controller: 'creditDebitDetails') {action = [GET: 'dataTable']}
            "/sales/creditdebitdetails/$id(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/creditdebitdetailsbydays/$days(.$format)?"(controller: 'creditDebitDetails') {
                action = [GET: 'getAllByDays']
            }

            //Credit Debit Settlement
            "/sales/creditdebitsettlement(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/creditdebitsettlement/datatable(.$format)?"(controller: 'creditDebitSettlement') {action = [GET: 'dataTable']}
            "/sales/creditdebitsettlement/$id(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/creditdebitsettlementbydays/$days(.$format)?"(controller: 'creditDebitSettlement') {
                action = [GET: 'getAllByDays']
            }

            //Old Software Return Details
            "/sales/oldsoftwarereturndetails(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/oldsoftwarereturndetails/datatable(.$format)?"(controller: 'oldSoftwareReturnDetails') {action = [GET: 'dataTable']}
            "/sales/oldsoftwarereturndetails/$id(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/oldsoftwarereturndetailsbydays/$days(.$format)?"(controller: 'oldSoftwareReturnDetails') {
                action = [GET: 'getAllByDays']
            }

            //Old Software sale Details
            "/sales/oldsoftwaresaledetails(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/oldsoftwaresaledetails/datatable(.$format)?"(controller: 'oldSoftwareSaleDetails') {action = [GET:
                                                                                                                         'dataTable']}
            "/sales/oldsoftwaresaledetails/$id(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/oldsoftwaresaledetailsbydays/$days(.$format)?"(controller: 'oldSoftwareSaleDetails') {
                action = [GET: 'getAllByDays']
            }

            //Reason Master
            "/sales/reasonmaster(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/reasonmaster/datatable(.$format)?"(controller: 'reasonMaster') {action = [GET: 'dataTable']}
            "/sales/reasonmaster/$id(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/reasonmasterbydays/$days(.$format)?"(controller: 'reasonMaster') {
                action = [GET: 'getAllByDays']
            }

            //Sale Bill Details
            "/sales/salebilldetails(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/salebilldetails/datatable(.$format)?"(controller: 'saleBillDetails') {action = [GET: 'dataTable']}
            "/sales/salebilldetails/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/salebilldetailsbydays/$days(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllByDays']
            }

            //Sale Order entry Controller
            "/sales/salebilldetails(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/salebilldetails/datatable(.$format)?"(controller: 'saleBillDetails') {action = [GET: 'dataTable']}
            "/sales/salebilldetails/$id(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/salebilldetailsbydays/$days(.$format)?"(controller: 'saleBillDetails') {
                action = [GET: 'getAllByDays']
            }

            //Sale Product Details
            "/sales/saleproductdetails(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'index', POST:
                        'save']
            }
            "/sales/saleproductdetails/datatable(.$format)?"(controller: 'saleProductDetails') {action = [GET: 'dataTable']}
            "/sales/saleproductdetails/$id(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/saleproductdetailsbydays/$days(.$format)?"(controller: 'saleProductDetails') {
                action = [GET: 'getAllByDays']
            }

            //Sale Transaction Log
            "/sales/saletransactionlog(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/sales/saletransactionlog/datatable(.$format)?"(controller: 'saleTransactionLog') {action = [GET: 'dataTable']}
            "/sales/saletransactionlog/$id(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/saletransactionlogbydays/$days(.$format)?"(controller: 'saleTransactionLog') {
                action = [GET: 'getAllByDays']
            }

            //Sale Transportation Details
            "/sales/saletransportationdetails(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/sales/saletransportationdetails/datatable(.$format)?"(controller: 'saleTransportationDetails') {action = [GET: 'dataTable']}
            "/sales/saletransportationdetails/$id(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/saletransportationdetailsbydays/$days(.$format)?"(controller: 'saleTransportationDetails') {
                action = [GET: 'getAllByDays']
            }
            //Sample Conversion Details
            "/sales/sampleconversiondetails(.$format)?"(controller:'sampleConversionDetails') {
                action = [GET: 'index', POST: 'save']
            }
            "/sales/sampleconversiondetails/datatable(.$format)?"(controller:'sampleConversionDetails') {action = [GET: 'dataTable']}
            "/sales/sampleconversiondetails/$id(.$format)?"(controller:'sampleConversionDetails') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/sampleconversiondetailsbydays/$days(.$format)?"(controller: 'sampleConversionDetails') {
                action = [GET: 'getAllByDays']
            }

            //Scheme Configuration
            "/sales/schemeconfiguration(.$format)?"(controller:'schemeConfiguration') {
                action = [GET: 'index', POST: 'save']
            }
            "/sales/schemeconfiguration/datatable(.$format)?"(controller:'schemeConfiguration') {action = [GET: 'dataTable']}
            "/sales/schemeconfiguration/$id(.$format)?"(controller:'schemeConfiguration') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/schemeconfigurationbydays/$days(.$format)?"(controller: 'schemeConfiguration') {
                action = [GET: 'getAllByDays']
            }

            //Temp User Log
            "/sales/tempuserlog(.$format)?"(controller:'tempUserLog') {
                action = [GET: 'index', POST: 'save']
            }
            "/sales/tempuserlog/datatable(.$format)?"(controller:'tempUserLog') {action = [GET: 'dataTable']}
            "/sales/tempuserlog/$id(.$format)?"(controller:'tempUserLog') {
                action = [GET: 'show', PUT: 'update', DELETE:
                        'delete']
            }
            "/sales/tempuserlogbydays/$days(.$format)?"(controller: 'tempUserLog') {
                action = [GET: 'getAllByDays']
            }
        }
    }
}
