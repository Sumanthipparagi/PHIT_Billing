package phitb_entity

class TerritoryRegister
{
    String territoryName
    String shortName
    long territoryHq
    long cityIds
    long stateId
    long countryId
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
            System.out.println("TerritoryRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TerritoryRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
