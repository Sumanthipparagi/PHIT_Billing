package phitb_entity

import gorm.logical.delete.LogicalDelete

class AccountRegister implements LogicalDelete<AccountRegister> {

    long generalId
    String accountName
    long accountStatus
    long accountType
    long subAccountType
    long accountMode
    long responsibleUserId
    String yearlyBudget
    String balance
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    long createdUser
    long modifiedUser

    boolean showInDebit
    boolean showInCredit

    Date dateCreated
    Date lastUpdated

    static constraints = {
        accountName maxSize: 500
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("AccountRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("AccountRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
