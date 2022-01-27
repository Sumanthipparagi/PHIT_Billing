package phitb_product

import gorm.logical.delete.LogicalDelete

class CompositionMaster implements Serializable, LogicalDelete<CompositionMaster> {

    String compositionName

    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

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
            System.out.println("CompositionMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CompositionMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
