package phitb_shipments

import gorm.logical.delete.LogicalDelete

class Transporter implements LogicalDelete<Transporter> {

    String name //ex: VRL, DTDC, Professional etc.,
    String address
    String gstNo
    String phone
    TransportType transportType

    long createdUser
    long modifiedUser

    long entityId
    long entityTypeId

    Date dateCreated
    Date lastUpdated

    static constraints = {
        address nullable: true
        gstNo nullable: true
        phone nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("Transporter Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("Transporter domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
