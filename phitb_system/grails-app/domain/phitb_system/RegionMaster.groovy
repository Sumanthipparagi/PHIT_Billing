package phitb_system

import gorm.logical.delete.LogicalDelete

class RegionMaster implements LogicalDelete<RegionMaster>
{

    String regionName
    String regionCode

    Date dateCreated
    Date lastUpdated
    static constraints = {
        regionName nullable: true
        regionCode nullable: true
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("Region Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("Region domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
