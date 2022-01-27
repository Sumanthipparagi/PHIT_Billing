package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductClass implements Serializable, LogicalDelete<ProductClass> {

    String productClassName
    String shortName

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
            System.out.println("ProductClass Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductClass domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
