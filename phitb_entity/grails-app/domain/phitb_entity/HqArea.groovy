package phitb_entity

import gorm.logical.delete.LogicalDelete

class HqArea implements LogicalDelete<HqArea>
{
    String hqName
    String cityId
    EntityRegister entity
    EntityTypeMaster entityType

    UserRegister createdUser
    UserRegister modifiedUser

    Date dateCreated
    Date lastUpdated


    static constraints = {
        cityId nullable: true
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("HqArea Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("HqArea domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }

}
