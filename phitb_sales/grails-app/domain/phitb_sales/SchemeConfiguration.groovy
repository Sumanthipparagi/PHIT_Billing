package phitb_sales

import gorm.logical.delete.LogicalDelete

class SchemeConfiguration implements Serializable, LogicalDelete<SchemeConfiguration>
{

    String zoneIds
    String stateIds
    String cityIds
    String customerIds
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
    long specialRate
    Date specialRateValidFrom
    Date specialRateValidTo
    String schemeStatus
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
