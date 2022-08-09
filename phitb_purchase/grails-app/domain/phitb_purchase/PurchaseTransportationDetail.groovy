package phitb_purchase

import gorm.logical.delete.LogicalDelete

class PurchaseTransportationDetail implements LogicalDelete<PurchaseTransportationDetail> {

    long finId
    long billId
    String billType
    long serBillId
    long series
    long supplierId
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
    }

    static mapping = {
        generalInfo sqlType: 'longText'
        trackingDetails sqlType: 'longText'
        ewaybillId sqlType: 'longText'
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PurchaseTransportationDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PurchaseTransportationDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
