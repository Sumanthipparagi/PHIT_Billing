package phitb_sales

import gorm.logical.delete.LogicalDelete

class GoodsTransferNoteProduct implements LogicalDelete<GoodsTransferNoteProduct>{
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
        sqty min: 0D
        freeQty min: 0D
        repQty min: 0D
        originalSqty nullable:true
        originalFqty nullable:true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("GoodsTransferNoteProduct Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("GoodsTransferNoteProduct domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
