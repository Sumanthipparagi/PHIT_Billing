package phitb_facility

import gorm.logical.delete.LogicalDelete

class FridgeMaster implements LogicalDelete<FridgeMaster> {

    String fridgeName
    String floor
    Date dateOfPurchase
    String machinePartNumber
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    static constraints = {
        floor nullable: true
        dateOfPurchase nullable: true
        machinePartNumber nullable: true
        machinePartNumber nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("FridgeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("FridgeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
