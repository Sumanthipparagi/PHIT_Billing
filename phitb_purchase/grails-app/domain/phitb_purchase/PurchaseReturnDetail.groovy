package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseReturnDetail implements LogicalDelete<PurchaseReturnDetail> {

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
    String returnStatus
    String saleFinId
    long redundantBatch
    long status
    long syncStatus
    String financialYear
    long entityTypeId
    long entityId
    String invoiceNumber
    Long purBillId
    Date dateCreated
    Date lastUpdated

    String uuid

    double gstPercentage
    double sgstPercentage
    double cgstPercentage
    double igstPercentage

    static constraints = {
        purBillId nullable: true
        invoiceNumber nullable: true
        returnStatus nullable: true
        uuid nullable: true, unique: true

        sqty scale:2
        freeQty scale:2
        repQty scale:2
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
            System.out.println("PurchaseReturnDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseReturnDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
