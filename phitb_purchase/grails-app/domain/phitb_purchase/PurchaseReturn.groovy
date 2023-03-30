package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseReturn implements LogicalDelete<PurchaseReturn>
{
    long finId
    long serBillId
    long series
    String type
    String supplierId
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

        maxDnAmount scale:2
        gross scale:2
        taxable scale:2
        totalGst scale:2
        totalCgst scale:2
        totalSgst scale:2
        totalIgst scale:2
        exempted scale:2
        cashDiscount scale:2
        totalAmount scale:2
        totalDiscount scale:2
        balance scale:2
        dbAdjAmount scale:2
        adjAmount scale:2
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
            System.out.println("Purchase Return Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("Purchase Return domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
