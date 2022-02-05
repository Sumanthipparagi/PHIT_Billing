package phitb_entity

import gorm.logical.delete.LogicalDelete

class RuleMaster implements LogicalDelete<RuleMaster>
{

    long dlExpired
    long foodLicenseExpired
    long salesValueLimit
    long creditGraceCheck
    String checkDate
    long scheme
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser

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
            System.out.println("RuleMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RuleMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
