package phitb_accounts

import gorm.logical.delete.LogicalDelete

class GeneralLedger implements LogicalDelete<GeneralLedger> {

    String docType
    String docNo
    String narration
    long fromAccount
    long toAccount
    double amount
    double balance
    long status
    String financialYear

    long entityId
    long entityType
    long createdUser
    long modifiedUser
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        narration sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("GeneralLedger Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("GeneralLedger domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
