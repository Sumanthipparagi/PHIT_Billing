package phitb_system

import gorm.logical.delete.LogicalDelete

class CountryMaster implements LogicalDelete<CountryMaster> {

    String name
    long entityId
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 50
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("CountryMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CountryMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
