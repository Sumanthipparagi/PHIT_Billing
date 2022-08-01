package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleReturn implements Serializable, LogicalDelete<SaleReturn>
{
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
    double totalDiscount
    String debitIds
    long syncStatus
    long lockStatus
    String message
    int ignoreSold
    String financialYear
    String invoiceNumber
    String returnStatus
    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser
    Date cancelledDate
    String refNo
    Date refDate

    double balance
    String adjustmentStatus //UNSETTLED, SETTLED, PARTIALLY_SETTLED
    double dbAdjAmount
    double adjAmount //total adjusted amount till now, if this is equal to totalAmount then this sale return is settled.

    Date dateCreated
    Date lastUpdated

    String uuid
    static constraints = {
        invoiceNumber nullable: true
        returnStatus nullable: true
        cancelledDate nullable: true
        refNo nullable: true
        refDate nullable: true
        uuid nullable: true, unique: true
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
            System.out.println("SaleReturn Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturn domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}

