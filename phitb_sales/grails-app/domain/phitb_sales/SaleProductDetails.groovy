package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleProductDetails implements Serializable, LogicalDelete<SaleProductDetails>
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
    Boolean replacement
    long status
    long syncStatus
    String financialYear
    long entityTypeId
    long entityId

    //this is added to keep track from where the qty came from, helpful in case of cancellation to put into right coloumn
    double originalSqty
    double originalFqty

    Date dateCreated
    Date lastUpdated

    double gstPercentage
    double sgstPercentage
    double cgstPercentage
    double igstPercentage

    String uuid

//    retailer

    Long noOfDays
    Long morning
    Long afternoon
    Long night
    Long presqty

    static constraints = {
        uuid unique: true
        sqty min: 0D
        freeQty min: 0D
        repQty min: 0D
        originalSqty min: 0D
        originalFqty min: 0D
        replacement nullable:true
        noOfDays    nullable:true
        morning nullable:true
        afternoon nullable:true
        night nullable:true
        presqty nullable:true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleProductDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleProductDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
