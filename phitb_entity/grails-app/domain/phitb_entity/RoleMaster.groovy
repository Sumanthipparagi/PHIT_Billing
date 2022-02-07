package phitb_entity

import gorm.logical.delete.LogicalDelete

class RoleMaster implements LogicalDelete<RoleMaster> {

    String name
    String description
    EntityRegister entity

    Date dateCreated
    Date lastUpdated


    static constraints = {
        description maxSize: 500
    }



    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("RoleMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RoleMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
