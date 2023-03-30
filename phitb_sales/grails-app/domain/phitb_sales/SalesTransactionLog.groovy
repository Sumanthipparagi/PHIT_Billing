package phitb_sales

import gorm.logical.delete.LogicalDelete

class SalesTransactionLog implements Serializable, LogicalDelete<SalesTransactionLog>
{

    long finId
    String transactionType
    String accountModeId
    String accountId
    long seriesId
    String billId
    String serBillId
    String description
    String context
    double transferedAmount
    Date transactionDate
    String status
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    static constraints = {
        transferedAmount scale:2
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SalesTransactionLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SalesTransactionLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
