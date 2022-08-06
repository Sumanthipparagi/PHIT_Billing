package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleTransportationDetails implements Serializable, LogicalDelete<SaleTransportationDetails>
{
    long finId
    long billId
    String billType
    long serBillId
    long series
    long customerId
    long transporterId
    Date lrDate
    String lrNumber
    String cartonsCount
    String paid
    String toPay
    String generalInfo
    String selfNo
    String ccm
    String receivedTemperature
    String freightCharge
    long vehicleId
    String weight
    String deliveryStatus
    String dispatchDateTime
    String deliveryDateTime
    String trackingDetails
    String ewaybillId
    String ewaysupplytype
    String ewaysupplysubtype
    String ewaydoctype
    String consignmentNo
    long syncStatus
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        finId nullable:true
        billId nullable:true
        billType nullable: true
        serBillId nullable:true
        series nullable:true
        customerId nullable:true
        transporterId nullable:true
        lrDate nullable: true
        lrNumber nullable: true
        cartonsCount nullable: true
        paid nullable: true
        toPay nullable: true
        generalInfo nullable: true
        selfNo nullable: true
        ccm nullable: true
        receivedTemperature nullable: true
        freightCharge nullable: true
        vehicleId nullable:true
        weight nullable: true
        deliveryStatus nullable: true
        dispatchDateTime nullable: true
        deliveryDateTime nullable: true
        trackingDetails nullable: true
        ewaybillId nullable: true
        ewaysupplytype nullable: true
        ewaysupplysubtype nullable: true
        ewaydoctype nullable: true
        consignmentNo nullable: true
        financialYear nullable: true

    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SaleTransportationDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleTransportationDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
