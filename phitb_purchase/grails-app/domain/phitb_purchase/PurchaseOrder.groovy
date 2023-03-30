package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseOrder implements LogicalDelete<PurchaseOrder> {

    String invoiceNumber
    long finId
    long supplierId
    long seriesId
    long serBillId
    String purcId
    String supplierBillId
    Date supplierBillDate
    Date billingDate
    Date entryDate
    String dispatchStatus
    long accountModeId
    double cashDiscount
    double productDiscount
    Date receivedDate
    String receivedBy
    Date dueDate
    Date expectedDeliveryDate
    double adjustedAmount
    String creditId
    String debitId
    double crDbAmount
    double payableAmount
    double gross
    double taxable
    double totalGst
    double totalCgst
    double totalSgst
    double totalIgst
    double netAmount
    String godownId
    long totalItems
    long totalQuantity
    double exempted
    double totalDiscount
    double balAmount
    double totalAmount
    String submitStatus
    String billStatus
    String gstStatus
    long syncStatus
    long lockStatus
    double addAmount
    double lessAmount
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Date cancelledDate
    Date dateCreated
    Date lastUpdated
    String uuid
    String remarks
    String publicNote
    String privateNote
    static constraints = {
        invoiceNumber unique: true, nullable: true
        cancelledDate nullable: true
        totalAmount nullable: true
        supplierBillDate nullable: true
        publicNote nullable: true
        privateNote nullable: true
        uuid unique: true

        cashDiscount scale:2
        productDiscount scale:2
        adjustedAmount scale:2
        crDbAmount scale:2
        payableAmount scale:2
        gross scale:2
        taxable scale:2
        totalGst scale:2
        totalCgst scale:2
        totalSgst scale:2
        totalIgst scale:2
        netAmount scale:2
        exempted scale:2
        totalDiscount scale:2
        balAmount scale:2
        totalAmount scale:2
        addAmount scale:2
        lessAmount scale:2
    }

    static mapping = {
        remarks sqlType: 'longText'
        publicNote sqlType: 'longText'
        privateNote sqlType: 'longText'
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseOrder Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseOrder domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
