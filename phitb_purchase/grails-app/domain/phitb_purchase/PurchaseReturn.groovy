package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseReturn implements LogicalDelete<PurchaseReturn>
{
    long finId
    long serBillId
    long series
    String type
    long supplierId
    Date dispatchDate
    double adjAmount
    Date entryDate
    String refId
    double maxDnAmount
    String supplierContact
    String supplierEmail
    double gross
    double taxable
    double nonTaxable
    double totalGst
    double totalCgst
    double totalSgst
    double totalIgst
    double exempted
    double cashDiscount
    long items
    long quantity
    double totalAmount
    double balance
    String invoiceNumber
    double crdAdjAmount
    double totalDiscount
    String creditIds
    String returnStatus
    long syncStatus
    long lockStatus
    String adjustmentStatus
    String message
    long ignorePurchase
    String financialYear
    String refNo
    Date refDate
    Date cancelledDate
    long entityTypeId
    long entityId
    String uuid
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        adjAmount nullable:true
        invoiceNumber nullable: true
        cancelledDate nullable: true
        uuid nullable: true, unique: true
    }

    static mapping = {
        message sqlType: 'longText'
        creditIds sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseReturn Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseReturn domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
