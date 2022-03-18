package phitb_accounts

import gorm.logical.delete.LogicalDelete

class BankRegister implements LogicalDelete<BankRegister> {
    String bankName
    long cityId
    String ifscCode
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        bankName maxSize: 500
        ifscCode maxSize: 500
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("BankRegisterService Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("BankRegisterService domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
