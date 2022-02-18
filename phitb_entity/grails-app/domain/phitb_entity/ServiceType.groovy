package phitb_entity

import gorm.logical.delete.LogicalDelete
import org.apache.catalina.User

class ServiceType implements LogicalDelete<ServiceType>
{

    String serviceType
    String description
    EntityRegister entity
    EntityTypeMaster entityType
    UserRegister createdUser
    UserRegister modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {

        sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ServiceType Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ServiceType domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
