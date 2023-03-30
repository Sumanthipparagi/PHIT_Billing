package phitb_sales

import gorm.logical.delete.LogicalDelete

class SchemeConfiguration implements Serializable, LogicalDelete<SchemeConfiguration>
{
    String zoneIds
    String stateIds
    String cityIds
    Long hqAreaId
    String customerIds
    String distributorId
    long productId
    String batch
    long slab1MinQty
    long slab1SchemeQty
    long slab1BulkStatus
    long slab1Status
    long slab2MinQty
    long slab2SchemeQty
    long slab2BulkStatus
    long slab2Status
    long slab3MinQty
    long slab3SchemeQty
    long slab3BulkStatus
    long slab3Status
    Date slabValidityFrom
    Date slabValidityTo
    double specialRate
    Date specialRateValidFrom
    Date specialRateValidTo
    double specialDiscount
    Date specialDiscountValidFrom
    Date specialDiscountValidTo
    String schemeStatus
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Date dateCreated
    Date lastUpdated

    static constraints = {
        zoneIds nullable: true
        stateIds nullable: true
        cityIds nullable: true
        customerIds nullable: true
        distributorId nullable: true
        hqAreaId nullable: true
        specialRateValidFrom nullable: true
        specialRateValidTo nullable: true
        specialDiscountValidFrom nullable: true
        specialDiscountValidTo nullable: true
        slabValidityTo nullable: true
        slabValidityFrom nullable: true
        specialRate scale: 2
        specialDiscount scale: 2
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SchemeConfiguration Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SchemeConfiguration domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
