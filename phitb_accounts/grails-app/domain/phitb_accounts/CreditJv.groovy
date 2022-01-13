package phitb_accounts

import gorm.logical.delete.LogicalDelete

//Journal Voucher
class CreditJv implements LogicalDelete<CreditJv> {

    String transId
    long employeeId
    long managerId
    double totalExpense
    Date transactionDate
    String referenceId
    Date finalSubmissionDate
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
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("CreditJv Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CreditJv domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
