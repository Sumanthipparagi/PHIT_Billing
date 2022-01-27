package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductGroupMaster implements Serializable, LogicalDelete<ProductGroupMaster> {

    String groupName
    String groupDescription
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
            System.out.println("ProductGroupMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductGroupMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
