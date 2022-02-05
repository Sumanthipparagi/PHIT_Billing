package phitb_sales

import gorm.logical.delete.LogicalDelete

class SampleConversionDetails implements Serializable, LogicalDelete<SampleConversionDetails>
{

    long finId
    long seriesId
    long serBillId
    long agentId
    double totalQty
    long totalItems
    Date transactionDate
    double totalAmount
    double totalPayable
    String billStatus
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
            System.out.println("SampleConversionDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SampleConversionDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
