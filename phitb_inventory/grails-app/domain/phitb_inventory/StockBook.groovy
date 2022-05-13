package phitb_inventory

import gorm.logical.delete.LogicalDelete

class StockBook implements LogicalDelete<StockBook> {

    long productId
    String batchNumber
    Date expDate
    double purchaseRate
    double saleRate
    double mrp
    double purcTradeDiscount
    long purcSeriesId
    Date purcDate
    long supplierId
    Date manufacturingDate
    String packingDesc
    double purcProductValue
    long remainingQty
    long remainingFreeQty
    long remainingReplQty
    String status
    long syncStatus
    String mergedWith
    long taxId
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    long openingStockQty
    String uuid

    Date dateCreated
    Date lastUpdated

    static constraints = {
        packingDesc nullable: true
        purcProductValue nullable: true
        purcTradeDiscount nullable: true
        remainingQty min: 0L
        remainingFreeQty min: 0L
        remainingReplQty min: 0L
        uuid unique: true

        purcDate nullable: true
        manufacturingDate nullable:true
        mergedWith nullable: true
        purchaseRate nullable:true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("StockBook Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("StockBook domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
