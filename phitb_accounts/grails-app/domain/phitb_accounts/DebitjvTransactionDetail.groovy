package phitb_accounts

import gorm.logical.delete.LogicalDelete

class DebitjvTransactionDetail implements LogicalDelete<DebitjvTransactionDetail> {

    long finId
    String transactionId
    Date expenseDate
    String expenseType
    String deductFrom
    double amount
    double balance
    double crAdjAmount
    String creditIds
    String note
    String paid
    String financialYear
    long syncStatus
    long entityTypeId
    long entityId

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
            System.out.println("DebitjvTransactionDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DebitjvTransactionDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
