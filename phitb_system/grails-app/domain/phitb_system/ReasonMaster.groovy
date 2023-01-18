package phitb_system

import gorm.logical.delete.LogicalDelete

class ReasonMaster implements Serializable, LogicalDelete<ReasonMaster> {

    String reasonName
    String reasonCode
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ReasonMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ReasonMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
