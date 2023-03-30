package phitb_accounts

import gorm.logical.delete.LogicalDelete

//Journal Voucher
class CreditJv implements LogicalDelete<CreditJv> {

    long id
    long finId //unique ID of the financial year
    long debitAccount
    long toAccount
    double amount
    double dbAdjAmount
    long reason
    String remarks
    Date transactionDate
    long employeeId
    long approverId
    Date approvedTime
    String financialYear
    String transactionId
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        approvedTime nullable: true
        amount scale:2
        dbAdjAmount scale:2
    }
    static mapping = {
        remarks sqlType: 'longText'
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
