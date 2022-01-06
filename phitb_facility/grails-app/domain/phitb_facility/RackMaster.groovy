package phitb_facility

import gorm.logical.delete.LogicalDelete

class RackMaster implements LogicalDelete<RackMaster> {

    String rackName
    String floorNumber
    String generalInfo
    String rackCodeName
    long cccEnabled
    String companies
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated
    static constraints = {
        generalInfo sqlType: "longText"
        companies sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("RackMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RackMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
