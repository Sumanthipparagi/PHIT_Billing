package phitb_facility

import gorm.logical.delete.LogicalDelete

class CcmRegister implements LogicalDelete<CcmRegister> {
    
    String kitName
    String kitNumber
    FridgeMaster fridge
    Date purchaseDate
    Date expiryDate
    String status
    long entityTypeId
    long entityId

    Date dateCreated
    Date lastUpdated

    static constraints = {

    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("CcmRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CcmRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
