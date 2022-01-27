package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductTypeMaster implements Serializable, LogicalDelete<ProductTypeMaster> {

    String productType
    String productDescription
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

    static mapping={
        productType sqlType: 'longText'
        productDescription sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ProductTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
