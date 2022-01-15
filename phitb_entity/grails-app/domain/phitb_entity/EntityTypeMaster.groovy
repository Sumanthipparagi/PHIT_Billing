package phitb_entity

import gorm.logical.delete.LogicalDelete

class EntityTypeMaster implements LogicalDelete<EntityTypeMaster> {

    String name
    String description

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
            System.out.println("EntityTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
