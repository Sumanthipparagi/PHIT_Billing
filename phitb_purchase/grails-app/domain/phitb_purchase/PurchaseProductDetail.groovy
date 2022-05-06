package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseProductDetail implements LogicalDelete<PurchaseProductDetail> {

    long finId
    long billId
    String billType
    long serBillId
    long seriesId
    long productId
    String batchNumber
    Date expiryDate
    long sqty
    long freeQty
    long repQty
    double pRate
    double sRate
    double mrp
    double discount
    long taxId
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
        gstPercentage nullable:true
        sgstPercentage nullable:true
        cgstPercentage nullable:true
        igstPercentage nullable:true
        uuid unique: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseProductDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseProductDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
