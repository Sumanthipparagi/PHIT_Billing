package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseBillDetail implements LogicalDelete<PurchaseBillDetail> {

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
    
    static constraints = {
        invoiceNumber unique: true, nullable: true
        cancelledDate nullable: true
        totalAmount nullable: true
        supplierBillDate nullable: true
        uuid unique: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseBillDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseBillDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
