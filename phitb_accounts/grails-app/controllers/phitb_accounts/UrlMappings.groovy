package phitb_accounts

import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

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

            //bank register
            "/accounts/bankregister(.$format)?"(controller: 'bankRegister') { action = [GET: 'index', POST: 'save'] }
            "/accounts/bankregister/datatable(.$format)?"(controller: 'bankRegister') { action = [GET: 'dataTable'] }
            "/accounts/bankregister/$id(.$format)?"(controller: 'bankRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //bill payment log
            "/accounts/billpaymentlog(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'index', POST: 'save'] }
            "/accounts/billpaymentlog/datatable(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'dataTable'] }
            "/accounts/billpaymentlog/$id(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //cheque returns
            "/accounts/chequereturns(.$format)?"(controller: 'chequeReturns') { action = [GET: 'index', POST: 'save'] }
            "/accounts/chequereturns/datatable(.$format)?"(controller: 'chequeReturns') { action = [GET: 'dataTable'] }
            "/accounts/chequereturns/$id(.$format)?"(controller: 'chequeReturns') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //credit jv
            "/accounts/creditjv(.$format)?"(controller: 'creditJv') { action = [GET: 'index', POST: 'save'] }
            "/accounts/creditjv/datatable(.$format)?"(controller: 'creditJv') { action = [GET: 'dataTable'] }
            "/accounts/creditjv/$id(.$format)?"(controller: 'creditJv') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //credit jv transaction detail
            "/accounts/creditjvtransactiondetail(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'index', POST: 'save'] }
            "/accounts/creditjvtransactiondetail/datatable(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'dataTable'] }
            "/accounts/creditjvtransactiondetail/$id(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //debit jv
            "/accounts/debitjv(.$format)?"(controller: 'debitJv') { action = [GET: 'index', POST: 'save'] }
            "/accounts/debitjv/datatable(.$format)?"(controller: 'debitJv') { action = [GET: 'dataTable'] }
            "/accounts/debitjv/$id(.$format)?"(controller: 'debitJv') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //debit jv transaction detail
            "/accounts/debitjvtransactiondetail(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'index', POST: 'save'] }
            "/accounts/debitjvtransactiondetail/datatable(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'dataTable'] }
            "/accounts/debitjvtransactiondetail/$id(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //finance transaction log
            "/accounts/financetransactionlog(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'index', POST: 'save'] }
            "/accounts/financetransactionlog/datatable(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'dataTable'] }
            "/accounts/financetransactionlog/$id(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //payment details
            "/accounts/paymentdetails(.$format)?"(controller: 'paymentDetail') { action = [GET: 'index', POST: 'save'] }
            "/accounts/paymentdetails/datatable(.$format)?"(controller: 'paymentDetail') { action = [GET: 'dataTable'] }
            "/accounts/paymentdetails/$id(.$format)?"(controller: 'paymentDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //receipt details
            "/accounts/receiptdetails(.$format)?"(controller: 'receiptDetail') { action = [GET: 'index', POST: 'save'] }
            "/accounts/receiptdetails/datatable(.$format)?"(controller: 'receiptDetail') { action = [GET: 'dataTable'] }
            "/accounts/receiptdetails/$id(.$format)?"(controller: 'receiptDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //wallet master
            "/accounts/wallet(.$format)?"(controller: 'walletMaster') { action = [GET: 'index', POST: 'save'] }
            "/accounts/wallet/datatable(.$format)?"(controller: 'walletMaster') { action = [GET: 'dataTable'] }
            "/accounts/wallet/$id(.$format)?"(controller: 'walletMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

        }
    }
}
