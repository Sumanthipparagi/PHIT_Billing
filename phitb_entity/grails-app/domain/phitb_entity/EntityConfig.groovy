package phitb_entity

class EntityConfig {


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
            System.out.println("EntityConfig Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityConfig domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
