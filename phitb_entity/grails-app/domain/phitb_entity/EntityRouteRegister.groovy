package phitb_entity

import gorm.logical.delete.LogicalDelete

///TODO: to be removed
class EntityRouteRegister implements LogicalDelete<EntityRouteRegister> {

    String routeName
    EntityRegister entityRegister
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
            System.out.println("EntityRouteRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityRouteRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
