package phitb_sales

class SaleReturnDetails {
    long finId
    long serBillId
    long series
    String type
    String customerId
    String salesmanId
    Date dispatchDate
    Date entryDate
    String refId
    double maxDnAmount
    String supplierContact
    String supplierEmail
    double gross
    double taxable
    double totalGst
    double totalCgst
    double totalSgst
    double totalIgst
    double exempted
    double cashDiscount
    int items
    int quantity
    double totalAmount
    double balance
    double dbAdjAmount
    double totalDiscount
    String debitIds
    long syncStatus
    long lockStatus
    String adjustmentStatus
    String message
    int ignoreSold
    String financialYear
    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser
    Double adjAmount

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        debitIds  sqlType: 'longText'
        message  sqlType: 'longText'

    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleReturnDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturnDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
