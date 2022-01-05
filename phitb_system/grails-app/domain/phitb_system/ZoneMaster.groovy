package phitb_system

import gorm.logical.delete.LogicalDelete

class ZoneMaster implements LogicalDelete<ZoneMaster> {

    String name

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
            System.out.println("ZoneMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ZoneMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
