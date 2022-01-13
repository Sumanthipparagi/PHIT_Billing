package phitb_accounts

import gorm.logical.delete.LogicalDelete

class WalletMaster implements LogicalDelete<WalletMaster> {

    String walletName
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        walletName maxSize: 500
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("WalletMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("WalletMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
