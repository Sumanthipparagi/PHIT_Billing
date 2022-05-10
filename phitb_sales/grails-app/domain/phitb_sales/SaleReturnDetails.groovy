package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleReturnDetails implements Serializable, LogicalDelete<SaleReturnDetails>
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
    String returnStatus
    String saleFinId
    long redundantBatch
    long status
    long syncStatus
    String financialYear
    long entityTypeId
    long entityId
    String invoiceNumber
    Long saleBillId
    Date dateCreated
    Date lastUpdated

    String uuid

    double gstPercentage
    double sgstPercentage
    double cgstPercentage
    double igstPercentage

    static constraints = {
        saleBillId nullable: true
        invoiceNumber nullable: true
        returnStatus nullable: true
        uuid nullable: true, unique: true

    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleReturnDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturnDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
