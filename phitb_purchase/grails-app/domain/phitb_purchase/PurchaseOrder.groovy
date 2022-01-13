package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseOrder implements LogicalDelete<PurchaseOrder> {

    long finId
    long serBillId
    long seriesId
    long supplierId
    double maxLimit
    Date entryDate
    String remarks
    long totalSqty
    long totalFqty
    double grossAmount
    double taxableAmount
    double totalGst
    double discount
    double estimatedTotalValue
    long transportTypeId
    String orderStatus
    String supplierSaleOrderId
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    
    Date dateCreated
    Date lastUpdated

    static constraints = {

    }

    static mapping = {
        remarks sqlType: 'longText'
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseOrder Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseOrder domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
