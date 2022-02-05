package phitb_system

import gorm.logical.delete.LogicalDelete

class AccountTypeMaster implements LogicalDelete<AccountTypeMaster> {

    String accountType
    long entityId
    Date dateCreated
    Date lastUpdated

    static constraints = {
        accountType maxSize: 50
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("AccountTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("AccountTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
