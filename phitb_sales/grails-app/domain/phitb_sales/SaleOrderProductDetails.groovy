package phitb_sales

class SaleOrderProductDetails {

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
        sqty min: 0D
        freeQty min: 0D
        repQty min: 0D
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
