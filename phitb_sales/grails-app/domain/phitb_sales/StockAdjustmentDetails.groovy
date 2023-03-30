package phitb_sales

import gorm.logical.delete.LogicalDelete

class StockAdjustmentDetails implements Serializable, LogicalDelete<StockAdjustmentDetails>
{
    long productId
    String batchNumber
    Date expDate
    Date manfDate
    long series
    long priority
    long sqty
    long fqty
    double pRate
    double sRate
    double gst
    long taxId
    double mrp
    long previousSQty
    long previousFQty
    String uuid

    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    static constraints = {
        pRate nullable: true
        sRate nullable: true
        gst nullable: true
        uuid unique: true
        priority nullable: true
        pRate scale:2
        sRate scale:2
        gst scale:2
        mrp scale:2
    }
    boolean isUpdatable
    static transients = ['isUpdatable']

    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("StockAdjustmentDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("StockAdjustmentDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
