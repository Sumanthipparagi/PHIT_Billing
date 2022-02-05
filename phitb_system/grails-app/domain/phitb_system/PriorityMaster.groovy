package phitb_system

import gorm.logical.delete.LogicalDelete

class PriorityMaster implements LogicalDelete<PriorityMaster> {

    String priority
    long entityId
    Date dateCreated
    Date lastUpdated

    static constraints = {
        priority maxSize: 50
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PriorityMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PriorityMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
