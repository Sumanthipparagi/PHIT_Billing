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

        group "/api/v1.0/accounts", {

            //bank register
            "/bankregister(.$format)?"(controller: 'bankRegister') { action = [GET: 'index', POST: 'save'] }
            "/bankregister/datatable(.$format)?"(controller: 'bankRegister') { action = [GET: 'dataTable'] }
            "/bankregister/$id(.$format)?"(controller: 'bankRegister') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/bankregisterbyentity/$id(.$format)?"(controller: 'bankRegister') { action = [GET: 'getByEntityId'] }

            //bank register
            "/generalledger(.$format)?"(controller: 'generalLedger') { action = [GET: 'index', POST: 'save'] }
            "/generalledger/datatable(.$format)?"(controller: 'generalLedger') { action = [GET: 'dataTable'] }
            "/generalledger/$id(.$format)?"(controller: 'generalLedger') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/generalledgerbyentity/$id(.$format)?"(controller: 'generalLedger') { action = [GET: 'getByEntityId'] }

            //bill payment log
            "/billpaymentlog(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'index', POST: 'save'] }
            "/billpaymentlog/datatable(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'dataTable'] }
            "/billpaymentlog/$id(.$format)?"(controller: 'billPaymentLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //cheque returns
            "/chequereturns(.$format)?"(controller: 'chequeReturns') { action = [GET: 'index', POST: 'save'] }
            "/chequereturns/datatable(.$format)?"(controller: 'chequeReturns') { action = [GET: 'dataTable'] }
            "/chequereturns/$id(.$format)?"(controller: 'chequeReturns') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/chequereturnsbyentity/$id(.$format)?"(controller: 'chequeReturns') { action = [GET: 'getByEntityId'] }


            //credit jv
            "/creditjv(.$format)?"(controller: 'creditJv') { action = [GET: 'index', POST: 'save'] }
            "/creditjv/approve(.$format)?"(controller: 'creditJv') { action = [POST: 'approveCreditJv'] }
            "/creditjv/datatable(.$format)?"(controller: 'creditJv') { action = [GET: 'dataTable'] }
            "/creditjv/$id(.$format)?"(controller: 'creditJv') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/creditjvbyentity/$id(.$format)?"(controller: 'creditJv') { action = [GET: 'getByEntityId'] }
            "/creditjvunbycustomer/$id(.$format)?"(controller: 'creditJv') { action = [GET: 'getAllUnsettledByCustId'] }
            "/creditjvsettledbycustomer/$id(.$format)?"(controller: 'creditJv') { action = [GET: 'getAllsettledByCustId'] }
            "/creditjvbydays/$days(.$format)?"(controller: 'creditJv') { action = [GET: 'getAllByDays'] }
            "/setcreditstatus/$id/type/$type(.$format)?"(controller: 'creditJv') {
                action = [POST: 'updateStatus']
            }

            //credit jv transaction detail
            "/creditjvtransactiondetail(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'index', POST: 'save'] }
            "/creditjvtransactiondetail/datatable(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'dataTable'] }
            "/creditjvtransactiondetail/$id(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/creditjvtransactiondetailbyentity/$id(.$format)?"(controller: 'creditJvTransactionDetail') { action = [GET:
                                                                                                              'getByEntityId'] }


            //debit jv
            "/debitjv(.$format)?"(controller: 'debitJv') { action = [GET: 'index', POST: 'save'] }
            "/debitjv/approve(.$format)?"(controller: 'debitJv') { action = [POST: 'approveDebitJv'] }
            "/debitjv/datatable(.$format)?"(controller: 'debitJv') { action = [GET: 'dataTable'] }
            "/debitjv/$id(.$format)?"(controller: 'debitJv') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/debitjvbyentity/$id(.$format)?"(controller: 'debitJv') { action = [GET: 'getByEntityId'] }
            "/debitjvbydays/$days(.$format)?"(controller: 'debitJv') { action = [GET: 'getAllByDays'] }

            //debit jv transaction detail
            "/debitjvtransactiondetail(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'index', POST: 'save'] }
            "/debitjvtransactiondetail/datatable(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'dataTable'] }
            "/debitjvtransactiondetail/$id(.$format)?"(controller: 'debitJvTransactionDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }

            //finance transaction log
            "/financetransactionlog(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'index', POST: 'save'] }
            "/financetransactionlog/datatable(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'dataTable'] }
            "/financetransactionlog/$id(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/financetransactionlogbyentity/$id(.$format)?"(controller: 'financeTransactionLog') { action = [GET: 'getByEntityId'] }


            //payment details
            "/paymentdetails(.$format)?"(controller: 'paymentDetail') { action = [GET: 'index', POST: 'save'] }
            "/paymentdetails/datatable(.$format)?"(controller: 'paymentDetail') { action = [GET: 'dataTable'] }
            "/paymentdetails/$id(.$format)?"(controller: 'paymentDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/paymentdetailsbyentity/$id(.$format)?"(controller: 'paymentDetail') { action = [GET:
                                                                                                          'getByEntityId'] }
            "/paymentdetailsbydays/$days(.$format)?"(controller: 'paymentDetail') { action = [GET:
                                                                                                            'getAllByDays'] }



            //receipt details
            "/receiptdetails(.$format)?"(controller: 'receiptDetail') { action = [GET: 'index', POST: 'save'] }
            "/receiptdetails/datatable(.$format)?"(controller: 'receiptDetail') { action = [GET: 'dataTable'] }
            "/receiptdetails/$id(.$format)?"(controller: 'receiptDetail') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/receiptdetailsbyentity/$id(.$format)?"(controller: 'receiptDetail') { action = [GET: 'getByEntityId'] }
            "/receiptdetailsbydays/$days(.$format)?"(controller: 'receiptDetail') { action = [GET:
                                                                                                            'getAllByDays'] }


            //wallet master
            "/wallet(.$format)?"(controller: 'walletMaster') { action = [GET: 'index', POST: 'save'] }
            "/wallet/datatable(.$format)?"(controller: 'walletMaster') { action = [GET: 'dataTable'] }
            "/wallet/$id(.$format)?"(controller: 'walletMaster') { action = [GET: 'show', PUT: 'update', DELETE: 'delete'] }
            "/walletbyentity/$id(.$format)?"(controller: 'walletMaster') { action = [GET: 'getByEntityId'] }

        }
    }
}
