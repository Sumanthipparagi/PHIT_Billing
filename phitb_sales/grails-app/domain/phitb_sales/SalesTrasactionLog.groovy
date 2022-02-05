package phitb_sales

import gorm.logical.delete.LogicalDelete

class SalesTrasactionLog implements Serializable, LogicalDelete<SalesTrasactionLog>
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
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SalesTrasactionLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SalesTrasactionLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
