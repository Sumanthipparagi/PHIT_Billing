package phitb_entity

import gorm.logical.delete.LogicalDelete


class DayEndMaster implements LogicalDelete<DayEndMaster>
{

    String date
    int status
    String endTime
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
            System.out.println("DayEndMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DayEndMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
