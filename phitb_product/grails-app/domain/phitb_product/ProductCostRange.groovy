package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductCostRange implements Serializable, LogicalDelete<ProductCostRange> {

    String priceType
    String minimumRate
    String maximumRate

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
            System.out.println("ProductCostRange Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductCostRange domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
