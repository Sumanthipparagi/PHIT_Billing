package phitb_inventory

class PurchaseTempStockBook {
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

    Date dateCreated
    Date lastUpdated
    static constraints = {
        originalId nullable: true
        entityTypeId nullable: true
        entityId nullable: true
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
