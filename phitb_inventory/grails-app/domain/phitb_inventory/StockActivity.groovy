package phitb_inventory

import gorm.logical.delete.LogicalDelete

class StockActivity implements LogicalDelete<StockActivity>{


    long productId
    String batch
    long remainingQty
    long remainingSchemeQty
    long prevRemQty
    long prevSchemeQty
    double saleRate
    double prevSaleRate
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
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("StockActivity Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("StockActivity domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
