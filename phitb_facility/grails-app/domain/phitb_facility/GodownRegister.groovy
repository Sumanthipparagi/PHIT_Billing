package phitb_facility

import gorm.logical.delete.LogicalDelete

class GodownRegister  implements LogicalDelete<GodownRegister>{

    String godownName
    long premises
    long ccmEnabled
    long managerId
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
            System.out.println("GodownRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("GodownRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
