package phitb_product

import gorm.logical.delete.LogicalDelete

class DivisionGroupRegister implements Serializable, LogicalDelete<DivisionGroupRegister> {

    String divisionGroupName
    String divGroupShortName
    String divisionIds

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
        divisionIds sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DivisionGroupRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DivisionGroupRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
