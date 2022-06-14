package phitb_inventory

import gorm.logical.delete.LogicalDelete

class TempStockBook implements LogicalDelete<TempStockBook> {

    long productId
    String batchNumber
    Date expDate
    long remainingQty
    long remainingFreeQty
    long remainingReplQty
    long userId
    long userOrderQty
    long userOrderFreeQty
    long userOrderReplQty
    double purchaseRate
    double saleRate
    double mrp
    String packingDesc
    long taxId
    String originalId
    long redundantBatch
    long entityTypeId
    long entityId
    String uuid

    long originalSqty
    long originalFqty

    Date dateCreated
    Date lastUpdated

    static constraints = {
        originalId nullable: true
        entityTypeId nullable: true
        entityId nullable: true
        uuid unique: true
        remainingQty min: 0L
        remainingFreeQty min: 0L
        remainingReplQty min: 0L
        userOrderQty min: 0L
        userOrderFreeQty min: 0L
        userOrderReplQty min: 0L
        originalSqty min: 0L
        originalFqty min: 0L
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("TempStockBook Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TempStockBook domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
