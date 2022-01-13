package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseTransactionLog implements LogicalDelete<PurchaseTransactionLog> {

    long finId
    String transactionType
    String accountModeId
    String accountId
    long seriesId
    String billId
    String serBillId
    String description
    String context
    double transferredAmount
    double balance
    Date transactionDate
    long status
    String financialYear
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

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
            System.out.println("PurchaseTransactionLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseTransactionLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
