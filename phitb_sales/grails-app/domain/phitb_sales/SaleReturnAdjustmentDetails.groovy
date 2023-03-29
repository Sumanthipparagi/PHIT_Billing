package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleReturnAdjustmentDetails implements Serializable, LogicalDelete<SaleReturnAdjustmentDetails> {

    SaleReturnAdjustment saleReturnAdjustment
    SaleReturn saleReturn
    double totalAmount
    double adjAmount
    double balanceBefore
    double currentBalance
    long docId
    String docType //CRNT

    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser
    Date cancelledDate
    Date dateCreated
    Date lastUpdated

    static constraints = {
        cancelledDate nullable: true
        totalAmount scale:2
        adjAmount scale:2
        balanceBefore scale:2
        currentBalance scale:2
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleReturnAdjustmentDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturnAdjustmentDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
