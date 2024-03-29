package phitb_sales

import gorm.logical.delete.LogicalDelete

class DeliveryChallanProduct implements Serializable, LogicalDelete<DeliveryChallanProduct>
{

    long finId
    long billId
    long billType
    long serBillId
    long seriesId
    long productId
    String batchNumber
    String expiryDate
    double sqty
    double freeQty
    double repQty
    double pRate
    double sRate
    double mrp
    double discount
    double gstId
    double gstAmount
    double sgstAmount
    double cgstAmount
    double igstAmount
    double amount
    String reason
    long fridgeId
    long kitName
    String saleFinId
    long redundantBatch
    long status
    long syncStatus
    String financialYear
    long originalSqty
    long originalFqty
    long entityTypeId
    long entityId

    Date dateCreated
    Date lastUpdated

    double gstPercentage
    double sgstPercentage
    double cgstPercentage
    double igstPercentage

    String uuid

    static constraints = {
        uuid unique: true
        sqty min: 0D, scale:2
        freeQty min: 0D, scale:2
        repQty min: 0D, scale:2
        originalSqty nullable:true
        originalFqty nullable:true
        pRate scale:2
        sRate scale:2
        mrp scale:2
        discount scale:2
        gstId scale:2
        gstAmount scale:2
        sgstAmount scale:2
        cgstAmount scale:2
        igstAmount scale:2
        amount scale:2
        gstPercentage scale:2
        sgstPercentage scale:2
        cgstPercentage scale:2
        igstPercentage scale:2
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DeliveryChallanProduct Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DeliveryChallanProduct domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
