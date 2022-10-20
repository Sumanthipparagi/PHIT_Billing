package phitb_entity

class EntityDomainType {

    String domainType
    Date dateCreated
    Date lastUpdated

    static constraints = {
        domainType nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("EntityDomainType Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityDomainType domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
