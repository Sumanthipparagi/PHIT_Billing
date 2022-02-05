package phitb_sales

import gorm.logical.delete.LogicalDelete

class OldSoftwareReturnDetails implements Serializable, LogicalDelete<OldSoftwareReturnDetails>
{

    String billId
    long series
    String date
    long userId
    long customerId
    long netAmount
    long balance
    long dbAdjamount
    long debitIds
    long adjustmentStatus
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
            System.out.println("OldSoftwareReturnDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("OldSoftwareReturnDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
