package phitb_system

import gorm.logical.delete.LogicalDelete

class AccountModeMaster implements LogicalDelete<AccountModeMaster> {

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
            System.out.println("AccountModeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("AccountModeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
