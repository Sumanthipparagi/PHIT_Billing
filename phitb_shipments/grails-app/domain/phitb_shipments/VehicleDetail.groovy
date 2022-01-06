package phitb_shipments

import gorm.logical.delete.LogicalDelete

class VehicleDetail implements LogicalDelete<VehicleDetail> {


    TransportType transportType
    String vehicleName
    String vehicleRegNo
    Date vehiclePurcDate
    long managerId
    String gpsKitId
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


    boolean isUpdatable
    static transients = ['isUpdatable']

    def beforeUpdate() {

        if (!this.isUpdatable) {
            System.out.println("VehicleDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        } else {
            System.out.println("VehicleDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
