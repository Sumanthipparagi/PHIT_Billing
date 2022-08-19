package phitb_sales

class StockAdjustmentDetails {
    long productId
    String batchNumber
    Date expDate
    Date manfDate
    long series
    long priority
    long sqty
    long fqty
    Double pRate
    Double sRate
    Double gst
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
        priority nullable:true
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
