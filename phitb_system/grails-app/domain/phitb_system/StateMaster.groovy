package phitb_system

import gorm.logical.delete.LogicalDelete

class StateMaster implements Serializable, LogicalDelete<StateMaster> {

    String name
    String stateCode
    String alphaCode
    String gstStateCode
    String irnStateCode

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: true, maxSize: 50
        irnStateCode nullable: true
        name nullable: true
        stateCode nullable: true
        alphaCode nullable: true
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
