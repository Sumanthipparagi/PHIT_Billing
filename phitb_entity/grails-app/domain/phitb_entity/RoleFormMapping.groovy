package phitb_entity

import gorm.logical.delete.LogicalDelete

class RoleFormMapping implements LogicalDelete<RoleFormMapping>
{
    long roleId
    long formIds
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        formIds sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("RoleFormMapping Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RoleFormMapping domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
