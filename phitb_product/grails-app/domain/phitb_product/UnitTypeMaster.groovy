package phitb_product

import gorm.logical.delete.LogicalDelete

class UnitTypeMaster implements Serializable, LogicalDelete<UnitTypeMaster> {

    String unitName
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
            System.out.println("UnitTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("UnitTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
