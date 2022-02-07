package phitb_sales

import gorm.logical.delete.LogicalDelete

class OldSoftwareSaleDetails implements Serializable, LogicalDelete<OldSoftwareSaleDetails>
{

    String billId
    long series
    String date
    long userId
    long customerId
    double netAmount
    double balance
    double crAdjAmount
    long creditIds
    String financialYear
    String status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    static constraints = {
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("OldSoftwareSaleDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("OldSoftwareSaleDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
