package phitb_entity

class RegionRegister {

    String regionName
    String shortName
    String regionStateIds
    long countryId
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {

    }

    static mapping = {
        regionStateIds sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SeriesMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SeriesMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
