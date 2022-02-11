package phitb_entity

import gorm.logical.delete.LogicalDelete

class CustomerGroupRegister implements LogicalDelete<CustomerGroupRegister>
{

    String customerGroupName
    String shortName
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
            System.out.println("CustomerGroupRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CustomerGroupRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
