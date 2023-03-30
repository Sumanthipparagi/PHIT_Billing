package phitb_accounts

import gorm.logical.delete.LogicalDelete

class FinanceTransactionLog implements LogicalDelete<FinanceTransactionLog> {
    
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
    String financialYear
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        transferredAmount scale:2
        balance scale:2
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("FinanceTransactionLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("FinanceTransactionLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
