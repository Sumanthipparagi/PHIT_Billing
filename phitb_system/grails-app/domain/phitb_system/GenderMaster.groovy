package phitb_system

import gorm.logical.delete.LogicalDelete

class GenderMaster implements LogicalDelete<GenderMaster> {

    String name
    long entityId
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 100
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("GenderMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("GenderMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
