package phitb_accounts

import gorm.logical.delete.LogicalDelete

class CreditJvTransactionDetail implements LogicalDelete<CreditJvTransactionDetail> {

    long finId
    String transactionId
    Date expenseDate
    String expenseType
    String deductFrom
    double amount
    double balance
    double dbAdjAmount
    String debitIds
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
            System.out.println("CreditJvTransactionDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CreditJvTransactionDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
