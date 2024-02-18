package phitb_sales


import grails.gorm.transactions.Transactional

@Transactional
class InvoiceSignatureService {

//    SaleBillDetails getDetailsByInvoiceNumber(String id)
//    {
//        return SaleBillDetails.findByInvoiceNumber(id)
//    }

        def getDetailsByInvoiceNumber(String invoiceNumber, String entityId)
        {
            try {
                return SaleBillDetails.findByInvoiceNumberAndEntityId(invoiceNumber,Long.parseLong(entityId))
            }
            catch (Exception ex)
            {
                log.error("SaleProductDeatilsService" + ex)
                println("SaleProductDeatilsService" + ex)
            }
        }
}
