package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseReturnDetail implements LogicalDelete<PurchaseReturnDetail> {

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
    String billStatus
    long syncStatus
    long lockStatus
    String adjustmentStatus
    String message
    long ignorePurchase
    String financialYear
    long entityTypeId
    long entityId
    long created_user
    long modified_user
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        adjAmount nullable:true
        invoiceNumber nullable: true
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
            System.out.println("PurchaseReturnDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseReturnDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
