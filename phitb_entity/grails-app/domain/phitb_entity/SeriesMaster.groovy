package phitb_entity

import gorm.logical.delete.LogicalDelete

class SeriesMaster implements LogicalDelete<SeriesMaster>
{

    String seriesCode
    String seriesName
    long mode
    long saleId
    long purId
    long saleReturnId
    long saleOrderId
    long purchaseReturnId
    long purchaseOrderId
    Long goodsTransferId
    Long sampleInvoiceId
    Long deliveryChallanId
    long status
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser


    Date dateCreated
    Date lastUpdated

    static constraints = {
        saleId nullable:true
        purId nullable:true
        saleReturnId nullable:true
        saleOrderId nullable:true
        purchaseReturnId nullable:true
        purchaseOrderId nullable:true
        mode nullable:true
        goodsTransferId nullable: true
        sampleInvoiceId nullable: true
        deliveryChallanId nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SeriesMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SeriesMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
