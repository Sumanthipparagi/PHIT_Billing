package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleReturnAdjustment implements Serializable, LogicalDelete<SaleReturnAdjustment> {

    String docNo //unique doc number
    long userId
    long docCounter
    long customerId
    String financialYear
    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    Date cancelledDate

    static constraints = {
        docNo nullable: true
        cancelledDate nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleReturnAdjustment Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturnAdjustment domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
