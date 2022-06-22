package phitb_system

import gorm.logical.delete.LogicalDelete

class DivisionMaster implements LogicalDelete<DivisionMaster>
{

    String divisionName
    String regionCode
    String divisionCode
    static constraints = {
        divisionName nullable: true
        regionCode nullable: true
        divisionCode nullable: true
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
