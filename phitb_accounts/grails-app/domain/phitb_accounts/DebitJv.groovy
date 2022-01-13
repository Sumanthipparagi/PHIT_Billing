package phitb_accounts

import gorm.logical.delete.LogicalDelete

class DebitJv implements LogicalDelete<DebitJv> {

    String transId
    long employeeId
    long managerId
    double totalExpense
    Date transactionDate
    String referenceId
    Date finalSubmissionDate
    String financialYea
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
            System.out.println("DebitJv Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DebitJv domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
