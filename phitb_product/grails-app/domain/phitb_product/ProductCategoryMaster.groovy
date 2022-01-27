package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductCategoryMaster implements Serializable, LogicalDelete<ProductCategoryMaster> {

    String categoryName
    String restrictedCategory
    String accessRestriction

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
            System.out.println("ProductCategoryMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductCategoryMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }

}
