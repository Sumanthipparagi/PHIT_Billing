package phitb_product

import gorm.logical.delete.LogicalDelete

class Division implements Serializable, LogicalDelete<Division> {

    String divisionName
    String divisionShortName
    String zoneIds
    String stateIds
    String cityIds
    long seriesId
    long managerId
    String customerIds
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        zoneIds sqlType: 'longText'
        stateIds sqlType: 'longText'
        cityIds sqlType: 'longText'
        customerIds sqlType: 'longText'
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("Division Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("Division domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
