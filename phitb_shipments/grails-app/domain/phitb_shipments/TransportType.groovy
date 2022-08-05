package phitb_shipments

import gorm.logical.delete.LogicalDelete

class TransportType  implements LogicalDelete<TransportType> {

    String transportType //Airways, Road Transport, Railways etc
    String vehicleId
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

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
            System.out.println("TransportType Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TransportType domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
