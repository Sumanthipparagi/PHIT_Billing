package phitb_system

import gorm.logical.delete.LogicalDelete

class DistrictMaster implements LogicalDelete<DistrictMaster>
{

    String district
    String districtCode
    StateMaster state

    Date dateCreated
    Date lastUpdated
    static constraints = {
        district nullable: true
        districtCode nullable: true
        state nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DistrictMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DistrictMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
