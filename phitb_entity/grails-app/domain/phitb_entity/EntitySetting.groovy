package phitb_entity

import gorm.logical.delete.LogicalDelete

class EntitySetting implements LogicalDelete<EntitySetting> {

    EntityRegister entity
    EntityTypeMaster entityType
    String code
    String name
    String value

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
            System.out.println("EntitySetting Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntitySetting domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
