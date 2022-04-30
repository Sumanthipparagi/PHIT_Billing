package phitb_system

import gorm.logical.delete.LogicalDelete

class StateMaster implements Serializable, LogicalDelete<StateMaster> {

    String name
    CountryMaster country
    long entityId
    ZoneMaster zone

    String irnStateCode

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 50
        irnStateCode nullable: true
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("StateMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("StateMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
