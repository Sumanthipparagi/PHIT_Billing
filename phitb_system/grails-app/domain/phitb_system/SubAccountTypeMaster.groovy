package phitb_system

import gorm.logical.delete.LogicalDelete

class SubAccountTypeMaster implements LogicalDelete<SubAccountTypeMaster> {

    String name
    AccountTypeMaster accountType
    long entityId

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 50
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SubAccountTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SubAccountTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
