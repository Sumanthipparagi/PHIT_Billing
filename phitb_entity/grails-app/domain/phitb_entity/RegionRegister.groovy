package phitb_entity

import gorm.logical.delete.LogicalDelete

class RegionRegister implements LogicalDelete<RegionRegister>
{

    String regionName
    String shortName
    String regionStateIds
    long countryId
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser

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
            System.out.println("RegionRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RegionRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
