package phitb_system

import gorm.logical.delete.LogicalDelete

class AccountModesMaster implements LogicalDelete<AccountModesMaster> {

    String mode

    Date dateCreated
    Date lastUpdated

    static constraints = {
        mode maxSize: 50
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("AccountModesMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("AccountModesMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
